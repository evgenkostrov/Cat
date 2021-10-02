package com.task5.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.task5.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainCatActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_cat)

    }

}