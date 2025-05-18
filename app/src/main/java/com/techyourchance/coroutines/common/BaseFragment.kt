package com.techyourchance.coroutines.common

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.techyourchance.coroutines.MainActivity

abstract class BaseFragment : Fragment() {

    protected open val screenTitle = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }


}