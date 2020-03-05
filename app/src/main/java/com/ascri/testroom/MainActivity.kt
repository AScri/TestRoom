package com.ascri.testroom

import android.Manifest.permission.CAMERA
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.Camera
import android.media.MediaScannerConnection
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.ncorti.slidetoact.SlideToActView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        coroutineTest()
        tryLabs()
        example.onSlideCompleteListener = object : SlideToActView.OnSlideCompleteListener{
            override fun onSlideComplete(view: SlideToActView) {
                output.text = "Rand = ${Random.nextInt(0, 100)}"
            }
        }
        example.onSlideToActAnimationEventListener = object : SlideToActView.OnSlideToActAnimationEventListener{
            override fun onSlideCompleteAnimationStarted(view: SlideToActView, threshold: Float) {
                view.animate().alpha(0.0f).setDuration(300).start()
                pin.animate().alpha(1.0f).setDuration(400).start()
                CoroutineScope(Dispatchers.Default).launch {
                    delay(3000)
                    withContext(Dispatchers.Main){
                        example.animate().alpha(1.0f).setDuration(300).start()
                        pin.animate().alpha(0.0f).setDuration(300).start()
                    }
                }
            }

            override fun onSlideCompleteAnimationEnded(view: SlideToActView) {
                view.resetSlider()
            }

            override fun onSlideResetAnimationEnded(view: SlideToActView) {
                Log.d("TAG", "q:")
            }

            override fun onSlideResetAnimationStarted(view: SlideToActView) {
                Log.d("TAG", "q:")
            }
        }
        rand_button.setOnClickListener { example.animate().alpha(1.0f).setDuration(300).start()}
    }

    fun openCamera(){
//        Camera.open()
//        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//        cameraIntent.putExtra("android.intent.extras.CAMERA_FACING", Camera.CameraInfo.CAMERA_FACING_FRONT)
//        cameraIntent.putExtra("android.intent.extras.LENS_FACING_FRONT", 1)
//        cameraIntent.putExtra("android.intent.extra.USE_FRONT_CAMERA", true);
        // cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);

        // cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
//        startActivityForResult(cameraIntent, 234)
    }

//    private fun coroutineTest() {
//        val job = SupervisorJob()
//        val workScope = CoroutineScope(Dispatchers.IO + job)
//        val workList = mutableListOf<Deferred<Long>>()
//        val resultList = mutableListOf<Long>()
//        for (i in 1..12) {
//            val randomLong = Random.nextLong(0, 10)
//            workList.add(workScope.async {
//                Log.d("COR_TEST", "fromAsync: iterate $i $randomLong")
//                delay(if (i == 4) 5000L else 100 * randomLong)
//                if (i == 6) throw Exception()
//                randomLong
//            })
//        }
//        workScope.launch {
//            for (i in workList) {
//                try {
//                    resultList.add(i.await())
//                } catch (e: Exception) {
//                    Log.d("COR_TEST", "coroutineTest: EXCEPTION!")
//                }
//            }
//            Log.d("COR_TEST", "result: $resultList")
//        }
//    }

    private fun coroutineTest() {
        val scope = CoroutineScope(Dispatchers.IO)
        scope.launch {
            supervisorScope {
                (1..12).map {
                    val randomLong = Random.nextLong(0, 10)
                    async {
                        Log.d("COR_TEST", "fromAsync: iterate $it $randomLong")
                        delay(if (it == 4) 5000L else 100 * randomLong)
                        if (it == 6) throw Exception()
                        randomLong
                    }
                }.map {
                    try {
                        it.await()
                    } catch (e: Exception) {
                        Log.d("COR_TEST", "coroutineTest: EXCEPTION!")
                        -100L
                    }
                }
            }.apply { Log.d("COR_TEST", "result: $this") }
        }
    }


    private fun tryLabs() {
        val permissionCheck = ContextCompat.checkSelfPermission(
            this,
            CAMERA
        )
        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
//            doLabs()
            openCamera()
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(CAMERA),
                REQUEST_RW_CODE
            )
        }
    }

    private fun doLabs() {
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
//                    doLabs()
                    openCamera()
                }
                return
            }
        }
    }

    companion object {
        const val REQUEST_RW_CODE = 213
        const val LABS_TAG = "LABS"
    }
}
