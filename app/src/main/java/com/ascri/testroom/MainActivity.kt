package com.ascri.testroom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityOptionsCompat
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*
import org.intellij.lang.annotations.Flow
import java.lang.Exception
import java.util.*
import kotlin.coroutines.CoroutineContext
import kotlin.random.Random

@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {
    private val job = Job()
    private val handler = CoroutineExceptionHandler { con, thr ->
        Log.d(TAG, "CORU_EXCEPTIONS: cathed $thr in $con")
    }
    private val crtScope = CoroutineScope(Dispatchers.Main) + job + handler
    private val table = Channel<Ball>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        output.text = "23"
        crtScope.launch {
            runBlocking {
                Log.d(TAG, "rb: I'm in")
                delay(2000)
                Log.d(TAG, "rb: bae")
            }
            launch {
                Log.d(TAG, "l: I'm in")
                delay(2000)
                Log.d(TAG, "l: bae")
            }
            delay(5000)
            withContext(Dispatchers.Default + job + handler) {
                Log.d(TAG, "ctx: I'm in")
                delay(10000)
                Log.d(TAG, "ctx: bae")
            }
        }
        rand_button.setOnClickListener {
            launchActivity<SecondActivity>()
//            crtScope.coroutineContext.cancelChildren() // cancel previous games
//            crtScope.launch {
//                launch { player("ping", table) }
//                launch { player("pong", table) }
//                table.send(Ball(0))
//                delay(3000)
//                crtScope.coroutineContext.cancelChildren()// game over, cancel them
//            }
        }
    }

    suspend fun player(name: String, table: Channel<Ball>) {
        for (ball in table) { // receive the ball in a loop
            ball.hits++
            output.text = ball.hits.toString()
            Log.d(TAG, "$name $ball")
            delay(300) // wait a bit
            table.send(ball) // send the ball back
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        crtScope.cancel()
    }

    companion object {
        const val TAG = "MAIN_TAG"
    }
}

data class Ball(var hits: Int)
