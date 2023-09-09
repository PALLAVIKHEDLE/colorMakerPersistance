package com.example.colormaker

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collectLatest
import kotlin.math.roundToInt

import java.math.RoundingMode
import java.text.DecimalFormat

const val INITIAL_COUNTER_VALUE =1.0
const val INITIAL_SEEKBAR_VALUE=0

class MyViewModel : ViewModel() {
        private var firstCounter: Double = INITIAL_COUNTER_VALUE
        private var secondCounter: Double = INITIAL_COUNTER_VALUE
        private var thirdCounter: Double = INITIAL_COUNTER_VALUE
        private var firstSwitch:Boolean=false
        private var secondSwitch:Boolean=false
        private var thirdSwitch:Boolean=false
        private var firstSeekBar:Int= INITIAL_SEEKBAR_VALUE
        private var secondSeekBar:Int= INITIAL_SEEKBAR_VALUE
        private var thirdSeekBar:Int= INITIAL_SEEKBAR_VALUE



    override fun onCleared() {
        super.onCleared()
        Log.d("MainActivity", "OnCleared of CounterViewActivity called")
    }

    private val prefs = PreferencesRepository.getRepository()
    fun firstSaveCounter(firstCounter: Float) {
        viewModelScope.launch {
            prefs.firstSaveCounter(firstCounter)
        }
    }
    fun secondSaveCounter(secondCounter: Float) {
        viewModelScope.launch {
            prefs.secondSaveCounter(secondCounter)
        }
    }
    fun thirdSaveCounter(thirdCounter: Float) {
        viewModelScope.launch {
            prefs.thirdSaveCounter(thirdCounter)
        }
    }

    fun firstSwitch(b: Boolean) {
        viewModelScope.launch {
            prefs.firstSwitch(b)
        }
    }

    fun secondSwitch(b:Boolean){
        viewModelScope.launch {
            prefs.secondSwitch(b)
        }
    }
    fun thirdSwitch(b:Boolean){
        viewModelScope.launch {
            prefs.thirdSwitch(b)
        }
    }

    fun seekBar1(value: Int) {
        viewModelScope.launch {
            prefs.seekBar1(value)
        }
    }

    fun seekBar2(value: Int) {
        viewModelScope.launch {
            prefs.seekBar2(value)
        }
    }
    fun seekBar3(value: Int) {
        viewModelScope.launch {
            prefs.seekBar3(value)
        }
    }


fun loadCounter1() {
    GlobalScope.launch {
        prefs.firstCounter.collectLatest{
            val df = DecimalFormat("#.##")
            df.roundingMode = RoundingMode.DOWN
             firstCounter=df.format(it).toDouble()
        }
    }
    Thread.sleep(1000)
}

    fun loadCounter2() {
        GlobalScope.launch {
            prefs.secondCounter.collectLatest {
                secondCounter = (it * 100.0).roundToInt() / 100.0
            }
        }
        Thread.sleep(1000)
    }

    fun loadCounter3() {
        GlobalScope.launch {
            prefs.thirdCounter.collectLatest {
                thirdCounter = (it * 100.0).roundToInt() / 100.0
            }
        }
        Thread.sleep(1000)
    }

    fun loadFirstSwitch() {
        GlobalScope.launch {
            prefs.firstSwitch.collectLatest {
                firstSwitch = it
            }
        }
        Thread.sleep(1000)
    }

    fun loadSecondSwitch() {
        GlobalScope.launch {
            prefs.secondSwitch.collectLatest {
                secondSwitch = it
            }
        }
        Thread.sleep(1000)
    }

    fun loadThirdSwitch() {
        GlobalScope.launch {
            prefs.thirdSwitch.collectLatest {
                thirdSwitch = it
            }
        }
        Thread.sleep(1000)
    }

    fun loadFirstSeekBar() {
        GlobalScope.launch {
            prefs.firstSeekBar.collectLatest {
                firstSeekBar = it
            }
        }
        Thread.sleep(1000)
    }
    fun loadSecondSeekBar() {
        GlobalScope.launch {
            prefs.secondSeekBar.collectLatest {
                secondSeekBar = it
            }
        }
        Thread.sleep(1000)
    }
    fun loadThirdSeekBar() {
        GlobalScope.launch {
            prefs.thirdSeekBar.collectLatest {
                thirdSeekBar = it
            }
        }
        Thread.sleep(1000)
    }
    fun getCount1(): Double {
        return this.firstCounter
    }
    fun getCount2(): Double {
        return this.secondCounter
    }
    fun getCount3(): Double {
        return this.thirdCounter
    }
    fun setCount(firstCounter: Float) {
            firstSaveCounter(firstCounter)
    }
    fun setSecondCount(secondCounter: Float) {
        secondSaveCounter(secondCounter)
    }
    fun setThirdCount(thirdCounter: Float) {
        thirdSaveCounter(thirdCounter)
    }
     fun setSeekBar1(value:Int){
         seekBar1(value)
     }
    fun getSeekBar1(): Int {
        return this.firstSeekBar
    }
    fun setSeekBar2(value:Int){
        seekBar2(value)
    }
    fun getSeekBar2(): Int {
        return this.secondSeekBar
    }
    fun setSeekBar3(value:Int){
        seekBar3(value)
    }
    fun getSeekBar3(): Int {
        return this.thirdSeekBar
    }
    fun setSwitch1(b: Boolean) {
        firstSwitch(b)
    }
    fun getSwitch1(): Boolean {
        return this.firstSwitch
    }

    fun setSwitch2(b:Boolean){
        secondSwitch(b)
    }
    fun getSwitch2(): Boolean {
        return this.secondSwitch
    }
    fun setSwitch3(b: Boolean) {
        thirdSwitch(b)
    }
    fun getSwitch3(): Boolean {
        return this.thirdSwitch
    }




}