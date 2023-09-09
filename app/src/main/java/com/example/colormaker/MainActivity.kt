package com.example.colormaker

import android.graphics.Color
import android.content.res.Configuration
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider

const val LOG_TAG = "MainActivity"
const val FIRST_COUNTER_KEY = "firstCounter"
const val SECOND_COUNTER_KEY = "secondCounter"
const val THIRD_COUNTER_KEY = "thirdCounter"




class MainActivity : AppCompatActivity() {
    //colors
    var BLUE: Float = 0.0f
    var RED: Float = 0.0f
    var GREEN: Float = 0.0f

    private lateinit var view: View
    private lateinit var resetButton: Button
    private lateinit var seekBar1: SeekBar
    private lateinit var seekBar2: SeekBar
    private lateinit var seekBar3: SeekBar
    private lateinit var switch1: Switch
    private lateinit var switch2: Switch
    private lateinit var switch3: Switch
    private lateinit var firstTextView: EditText
    private lateinit var secondTextView: EditText
    private lateinit var thirdTextView: EditText


    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(baseContext, "Landscape Mode", Toast.LENGTH_SHORT).show()
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Toast.makeText(baseContext, "Portrait Mode", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val orientation = resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            // Load portrait layout
            setContentView(R.layout.activity_main)
        } else {
            // Load landscape layout
            setContentView(R.layout.activity_main_land)
        }

        view = findViewById(R.id.view)
        resetButton = findViewById(R.id.resetButton)
        seekBar1 = findViewById(R.id.seekBar1)
//        seekBar1.isEnabled = false
        seekBar2 = findViewById(R.id.seekBar2)
//        seekBar2.isEnabled = false
        seekBar3 = findViewById(R.id.seekBar3)
//        seekBar3.isEnabled = false
        switch1 = findViewById(R.id.switch1)
        switch2 = findViewById(R.id.switch2)
        switch3 = findViewById(R.id.switch3)
        firstTextView = findViewById(R.id.firstTextView)
        firstTextView.setFocusableInTouchMode(false)
        secondTextView = findViewById(R.id.secondTextView)
        secondTextView.setFocusableInTouchMode(false)
        thirdTextView = findViewById(R.id.thirdTextView)
        thirdTextView.setFocusableInTouchMode(false)
        resetButton.setOnClickListener { view: View ->
            val btn = view as Button
            val txt = btn.text.toString()
            val progress = Integer.parseInt(0.toString())


            Log.i(LOG_TAG, "my button \"$txt\" was called ${progress}")
            viewModel.setCount(0.toFloat())
            firstTextView.setText(viewModel.getCount1().toString())
            viewModel.setSecondCount(0.toFloat())
            secondTextView.setText(viewModel.getCount2().toString())
            viewModel.setThirdCount(0.toFloat())
            thirdTextView.setText(viewModel.getCount3().toString())
//            seekBar1.setProgress(progress)
            seekBar2.setProgress(progress)
            seekBar3.setProgress(progress)

            seekBar1.setProgress(viewModel.getCount1().toInt())

            viewModel.setSwitch1(false)
            viewModel.setSwitch2(false)
            viewModel.setSwitch3(false)
            switch1.isChecked =viewModel.getSwitch1()
            switch2.isChecked =viewModel.getSwitch2()
            switch3.isChecked =viewModel.getSwitch3()


            viewModel.setSeekBar1(0)

            seekBar1.isEnabled = false
            seekBar2.isEnabled = false
            seekBar3.isEnabled = false
            view.setBackgroundColor(Color.rgb(0, 0, 0));
        }
        switch1.setOnClickListener {
            val sw: Switch = it as Switch

            Log.i(LOG_TAG, "switch clicked " + sw.isChecked)
            if (sw.isChecked == false) {
                seekBar1.setEnabled(false)
                firstTextView.setFocusable(false)
                viewModel.setSwitch1(false)
            } else {
                seekBar1.setEnabled(true)
                firstTextView.setFocusableInTouchMode(true)
                viewModel.setSwitch1(true)

            }
        }

        switch2.setOnClickListener {
            val sw: Switch = it as Switch
            Log.i(LOG_TAG, "switch clicked " + sw.isChecked)
            if (sw.isChecked == false) {
                seekBar2.setEnabled(false)
                secondTextView.setFocusable(false)
                viewModel.setSwitch2(false)
            } else {
                seekBar2.setEnabled(true)
                secondTextView.setFocusableInTouchMode(true)
                viewModel.setSwitch2(true)

            }
        }

        switch3.setOnClickListener {
            val sw: Switch = it as Switch
            Log.i(LOG_TAG, "switch clicked " + sw.isChecked)
            if (sw.isChecked == false) {
                seekBar3.setEnabled(false)
                thirdTextView.setFocusable(false)
                viewModel.setSwitch3(false)
            } else {
                seekBar3.setEnabled(true)
                thirdTextView.setFocusableInTouchMode(true)
                viewModel.setSwitch3(true)
            }
        }

        seekBar1.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onProgressChanged(sb: SeekBar?, p1: Int, p2: Boolean) {
                val seekBarValue = p1.toFloat() / 100
                viewModel.setCount(seekBarValue)
                viewModel.setSeekBar1(p1)
                firstTextView.setText(viewModel.getCount1().toString())
                RED = seekBarValue
                view.setBackgroundColor(Color.rgb(RED, GREEN, BLUE));
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })
        seekBar2.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onProgressChanged(sb: SeekBar?, p1: Int, p2: Boolean) {
                val seekBarValue = p1.toFloat() / 100
                viewModel.setSecondCount(seekBarValue)
                viewModel.setSeekBar2(p1)
                secondTextView.setText(viewModel.getCount2().toString())
                GREEN = seekBarValue
                view.setBackgroundColor(Color.rgb(RED, GREEN, BLUE));
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })
        seekBar3.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onProgressChanged(sb: SeekBar?, p1: Int, p2: Boolean) {
                val seekBarValue = p1.toFloat() / 100
                viewModel.setThirdCount(seekBarValue)
                viewModel.setSeekBar3(p1)
                thirdTextView.setText(viewModel.getCount3().toString())
                BLUE = seekBarValue
                view.setBackgroundColor(Color.rgb(RED, GREEN, BLUE));
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })
        firstTextView.setOnKeyListener { view, i, keyEvent ->
            Boolean
            val txt = view as EditText
            viewModel.setSeekBar1(i)
            val progress= viewModel.getSeekBar1()
            seekBar1.setProgress(progress)
            false
        }

        secondTextView.setOnKeyListener { view, i, keyEvent ->
            Boolean
            val txt = view as EditText
            viewModel.setSeekBar2(i)
            val progress= viewModel.getSeekBar2()
            seekBar2.setProgress(progress)
            false
        }
        thirdTextView.setOnKeyListener { view, i, keyEvent ->
            Boolean
            val txt = view as EditText
            viewModel.setSeekBar3(i)
            val progress= viewModel.getSeekBar3()
            seekBar3.setProgress(progress)
            false
        }

        viewModel.loadCounter1()
        viewModel.loadCounter2()
        viewModel.loadCounter3()
//        viewModel.loadFirstSwitch()
//        viewModel.loadSecondSwitch()
//        viewModel.loadThirdSwitch()
        viewModel.loadFirstSeekBar()
        viewModel.loadSecondSeekBar()
        viewModel.loadThirdSeekBar()

        Log.d(LOG_TAG, "setting counter after loading it from DataStore to ${viewModel.getCount1()}")
        firstTextView.setText(viewModel.getCount1().toString())
        secondTextView.setText(viewModel.getCount2().toString())
        thirdTextView.setText(viewModel.getCount3().toString())
        val progress1= viewModel.getSeekBar1()
        val progress2= viewModel.getSeekBar2()
        val progress3= viewModel.getSeekBar3()


        seekBar1.setProgress(progress1)
        seekBar2.setProgress(progress2)
        seekBar3.setProgress(progress3)
//        seekBar2.setProgress(viewModel.getCount2().toInt())
//        seekBar3.setProgress(viewModel.getCount3().toInt())
        switch1.isChecked =viewModel.getSwitch1()
        switch2.isChecked =viewModel.getSwitch2()
        switch3.isChecked =viewModel.getSwitch3()

    }

    override fun onDestroy() {
        super.onDestroy()
    }
    override fun onStart() {
        super.onStart()
    }
    override fun onStop() {
        super.onStop()
    }
    override fun onResume() {
        super.onResume()
    }
    override fun onPause() {
        super.onPause()
    }
    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        savedInstanceState.putInt(FIRST_COUNTER_KEY, this.viewModel.getCount1().toInt())
        savedInstanceState.putInt(SECOND_COUNTER_KEY, this.viewModel.getCount2().toInt())
        savedInstanceState.putInt(THIRD_COUNTER_KEY, this.viewModel.getCount3().toInt())
    }

    private val viewModel: MyViewModel by lazy {
        PreferencesRepository.initialize(this)
        ViewModelProvider(this)[MyViewModel::class.java]
    }
}




