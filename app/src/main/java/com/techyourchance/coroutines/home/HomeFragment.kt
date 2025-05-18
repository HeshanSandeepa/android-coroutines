package com.techyourchance.coroutines.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.techyourchance.coroutines.MainActivity
import com.techyourchance.coroutines.R
import com.techyourchance.coroutines.common.BaseFragment
import com.techyourchance.coroutines.demonstrations.backgroundthread.BackgroundThreadDemoFragment
import com.techyourchance.coroutines.demonstrations.basiccoroutines.BasicCoroutinesDemoFragment
import com.techyourchance.coroutines.demonstrations.concurrentcoroutines.ConcurrentCoroutinesDemoFragment
import com.techyourchance.coroutines.demonstrations.coroutinescancellation.CoroutinesCancellationDemoFragment
import com.techyourchance.coroutines.demonstrations.coroutinescancellationcooperative.CoroutinesCancellationCooperativeDemoFragment
import com.techyourchance.coroutines.demonstrations.coroutinescancellationcooperative2.CoroutinesCancellationCooperative2DemoFragment
import com.techyourchance.coroutines.demonstrations.design.DesignDemoFragment
import com.techyourchance.coroutines.demonstrations.noncancellable.NonCancellableDemoFragment
import com.techyourchance.coroutines.demonstrations.scopecancellation.ScopeCancellationDemoFragment
import com.techyourchance.coroutines.demonstrations.scopechildrencancellation.ScopeChildrenCancellationDemoFragment
import com.techyourchance.coroutines.demonstrations.uithread.UiThreadDemoFragment
import com.techyourchance.coroutines.demonstrations.uncaughtexception.UncaughtExceptionDemoFragment
import com.techyourchance.coroutines.demonstrations.viewmodel.ViewModelDemoFragment
import com.techyourchance.coroutines.exercises.exercise1.Exercise1Fragment
import com.techyourchance.coroutines.exercises.exercise10.Exercise10Fragment
import com.techyourchance.coroutines.exercises.exercise2.Exercise2Fragment
import com.techyourchance.coroutines.exercises.exercise3.Exercise3Fragment
import com.techyourchance.coroutines.exercises.exercise4.Exercise4Fragment
import com.techyourchance.coroutines.exercises.exercise5.Exercise5Fragment
import com.techyourchance.coroutines.exercises.exercise6.Exercise6Fragment
import com.techyourchance.coroutines.exercises.exercise8.Exercise8Fragment
import com.techyourchance.coroutines.exercises.exercise9.Exercise9Fragment

class HomeFragment : BaseFragment(), HomeArrayAdapter.Listener {

    override val screenTitle get() = "Coroutines Course"

    private lateinit var listScreensReachableFromHome: ListView
    private lateinit var adapterScreensReachableFromHome: HomeArrayAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        adapterScreensReachableFromHome = HomeArrayAdapter(requireContext(), this)

        listScreensReachableFromHome = view.findViewById(R.id.list_screens)
        listScreensReachableFromHome.adapter = adapterScreensReachableFromHome

        adapterScreensReachableFromHome.addAll(*ScreenReachableFromHome.values())
        adapterScreensReachableFromHome.notifyDataSetChanged()

        return view
    }

    fun push(fragment: Fragment) {
        (activity as? MainActivity)?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.frame_content, fragment)
            ?.addToBackStack(null) // Add transaction to the back stack
            ?.commit()
    }

    override fun onScreenClicked(screenReachableFromHome: ScreenReachableFromHome) {
        when (screenReachableFromHome) {
            ScreenReachableFromHome.UI_THREAD_DEMO -> push(UiThreadDemoFragment.newInstance())
            ScreenReachableFromHome.BACKGROUND_THREAD_DEMO -> push(BackgroundThreadDemoFragment.newInstance())
            ScreenReachableFromHome.BASIC_COROUTINES_DEMO -> push(BasicCoroutinesDemoFragment.newInstance())
            ScreenReachableFromHome.EXERCISE_1 -> push(Exercise1Fragment.newInstance())
            ScreenReachableFromHome.COROUTINES_CANCELLATION_DEMO -> push(CoroutinesCancellationDemoFragment.newInstance())
            ScreenReachableFromHome.EXERCISE_2 -> push(Exercise2Fragment.newInstance())
            ScreenReachableFromHome.CONCURRENT_COROUTINES_DEMO -> push(ConcurrentCoroutinesDemoFragment.newInstance())
            ScreenReachableFromHome.SCOPE_CHILDREN_CANCELLATION_DEMO -> push(ScopeChildrenCancellationDemoFragment.newInstance())
            ScreenReachableFromHome.EXERCISE_3 -> push(Exercise3Fragment.newInstance())
            ScreenReachableFromHome.SCOPE_CANCELLATION_DEMO -> push(ScopeCancellationDemoFragment.newInstance())
            ScreenReachableFromHome.VIEWMODEL_DEMO -> push(ViewModelDemoFragment.newInstance())
            ScreenReachableFromHome.EXERCISE_4 -> push(Exercise4Fragment.newInstance())
            ScreenReachableFromHome.DESIGN_DEMO -> push(DesignDemoFragment.newInstance())
            ScreenReachableFromHome.EXERCISE_5 -> push(Exercise5Fragment.newInstance())
            ScreenReachableFromHome.COROUTINES_CANCELLATION_COOPERATIVE_DEMO -> push(CoroutinesCancellationCooperative2DemoFragment.newInstance())
            ScreenReachableFromHome.COROUTINES_CANCELLATION_COOPERATIVE_2_DEMO -> push(CoroutinesCancellationCooperativeDemoFragment.newInstance())
            ScreenReachableFromHome.EXERCISE_6 -> push(Exercise6Fragment.newInstance())
            ScreenReachableFromHome.NON_CANCELLABLE_DEMO -> push(NonCancellableDemoFragment.newInstance())
            ScreenReachableFromHome.EXERCISE_8 -> push(Exercise8Fragment.newInstance())
            ScreenReachableFromHome.EXERCISE_9 -> push(Exercise9Fragment.newInstance())
            ScreenReachableFromHome.UNCAUGHT_EXCEPTION_DEMO -> push(UncaughtExceptionDemoFragment.newInstance())
            ScreenReachableFromHome.EXERCISE_10 -> push(Exercise10Fragment.newInstance())
        }
    }

    companion object {
        fun newInstance(): Fragment {
            return HomeFragment()
        }
    }
}