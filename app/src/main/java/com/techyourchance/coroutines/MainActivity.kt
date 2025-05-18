package com.techyourchance.coroutines

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.techyourchance.coroutines.common.ToolbarDelegate
import com.techyourchance.coroutines.home.HomeFragment

class MainActivity : AppCompatActivity(), ToolbarDelegate {

    private lateinit var btnBack: ImageButton
    private lateinit var txtScreenTitle: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txtScreenTitle = findViewById(R.id.txt_screen_title)


            (this as? MainActivity)?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.frame_content, HomeFragment.newInstance())
                ?.addToBackStack(null) // Add transaction to the back stack
                ?.commit()

    }

    override fun setScreenTitle(screenTitle: String) {
        txtScreenTitle.text = screenTitle
    }

    override fun showUpButton() {
        btnBack.visibility = View.VISIBLE
    }

    override fun hideUpButton() {
        btnBack.visibility = View.INVISIBLE
    }
}