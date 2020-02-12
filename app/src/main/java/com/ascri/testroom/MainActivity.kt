package com.ascri.testroom

import android.Manifest
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.content.pm.PackageManager
import android.media.MediaScannerConnection
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.coroutines.ExperimentalCoroutinesApi


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val permissionCheck = ContextCompat.checkSelfPermission(
            this,
            WRITE_EXTERNAL_STORAGE
        )
        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            doLabs()
        } else {
            ActivityCompat.requestPermissions(this,
                arrayOf(WRITE_EXTERNAL_STORAGE),
                REQUEST_RW_CODE)
        }
    }

    private fun doLabs(){
        val res = Labs().doAllLabs()
        MediaScannerConnection.scanFile(this, res.map { it?.path }.toTypedArray(), null, null)
        Log.d(LABS_TAG, "doLabs: $res")
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_RW_CODE -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    doLabs()
                }
                return
            }
        }
    }

    companion object{
        const val REQUEST_RW_CODE = 213
        const val LABS_TAG = "LABS"
    }
}
