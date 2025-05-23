package com.techyourchance.coroutines.demonstrations.concurrentcoroutines

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.techyourchance.coroutines.R
import com.techyourchance.coroutines.common.BaseFragment
import com.techyourchance.coroutines.common.ThreadInfoLogger
import com.techyourchance.coroutines.home.ScreenReachableFromHome
import kotlinx.coroutines.*

class ConcurrentCoroutinesDemoFragment : BaseFragment() {

    private val coroutineScope = CoroutineScope(Dispatchers.Main.immediate)

    override val screenTitle get() = ScreenReachableFromHome.CONCURRENT_COROUTINES_DEMO.description

    private lateinit var btnStart: Button
    private lateinit var txtRemainingTime: TextView

    private var jobCounter: Job? = null
    private var job: Job? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_loop_iterations_demo, container, false)

        txtRemainingTime = view.findViewById(R.id.txt_remaining_time)

        btnStart = view.findViewById(R.id.btn_start)
        btnStart.setOnClickListener {
            logThreadInfo("button callback")

            val benchmarkDurationSeconds = 15

            jobCounter = coroutineScope.launch {
                updateRemainingTime(benchmarkDurationSeconds)
            }

            job = coroutineScope.launch {
                btnStart.isEnabled = false
                val iterationsCount = executeBenchmark(benchmarkDurationSeconds)
                Toast.makeText(requireContext(), "$iterationsCount", Toast.LENGTH_SHORT).show()
                btnStart.isEnabled = true
            }
        }

        return view
    }

    override fun onStop() {
        logThreadInfo("onStop()")
        super.onStop()
        job?.cancel()
        btnStart.isEnabled = true
        jobCounter?.apply {
            cancel()
            txtRemainingTime.text = "done!"
        }
    }

    private suspend fun executeBenchmark(benchmarkDurationSeconds: Int) = withContext(Dispatchers.Default) {
        logThreadInfo("benchmark started")

        val stopTimeNano = System.nanoTime() + benchmarkDurationSeconds * 1_000_000_000L

        var iterationsCount: Long = 0
        while (System.nanoTime() < stopTimeNano) {
            iterationsCount++
        }

        logThreadInfo("benchmark completed")

        iterationsCount
    }

    private suspend fun updateRemainingTime(remainingTimeSeconds: Int) {
        for (time in remainingTimeSeconds downTo 0) {
            if (time > 0) {
                logThreadInfo("updateRemainingTime: $time seconds")
                txtRemainingTime.text = "$time seconds remaining"
                delay(1000)
            } else {
                txtRemainingTime.text = "done!"
            }
        }
    }

    private fun logThreadInfo(message: String) {
        ThreadInfoLogger.logThreadInfo(message)
    }

    companion object {
        fun newInstance(): Fragment {
            return ConcurrentCoroutinesDemoFragment()
        }
    }
}