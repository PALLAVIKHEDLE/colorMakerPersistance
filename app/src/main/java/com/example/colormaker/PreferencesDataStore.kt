package com.example.colormaker

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStoreFile
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

class PreferencesRepository private constructor(private val dataStore: DataStore<Preferences>) {

    private val FIRST_COUNTER_KEY= floatPreferencesKey("firstCounter")
    private val SECOND_COUNTER_KEY= floatPreferencesKey("SecondCounter")
    private val THIRD_COUNTER_KEY= floatPreferencesKey("thirdCounter")
    private val FIRST_SWITCH_KEY= booleanPreferencesKey("firstSwitch")
    private val SECOND_SWITCH_KEY= booleanPreferencesKey("secondSwitch")
    private val THIRD_SWITCH_KEY= booleanPreferencesKey("thirdSwitch")
    private val FIRST_SEEKBAR_KEY= intPreferencesKey("firstSeekBar")
    private val SECOND_SEEKBAR_KEY= intPreferencesKey("secondSeekBar")
    private val THIRD_SEEKBAR_KEY= intPreferencesKey("thirdSeekBar")


    val firstCounter: Flow<Float> = this.dataStore.data.map { prefs ->
        prefs[FIRST_COUNTER_KEY] ?:  INITIAL_COUNTER_VALUE
    }.distinctUntilChanged() as Flow<Float>

    val secondCounter: Flow<Float> = this.dataStore.data.map { prefs ->
        prefs[SECOND_COUNTER_KEY] ?:  INITIAL_COUNTER_VALUE
    }.distinctUntilChanged() as Flow<Float>

    val thirdCounter: Flow<Float> = this.dataStore.data.map { prefs ->
        prefs[THIRD_COUNTER_KEY] ?:  INITIAL_COUNTER_VALUE
    }.distinctUntilChanged() as Flow<Float>

    val firstSwitch: Flow<Boolean> = this.dataStore.data.map { prefs ->
        prefs[FIRST_SWITCH_KEY] ?:  INITIAL_COUNTER_VALUE
    }.distinctUntilChanged() as Flow<Boolean>

    val secondSwitch: Flow<Boolean> = this.dataStore.data.map { prefs ->
        prefs[SECOND_SWITCH_KEY] ?:  INITIAL_COUNTER_VALUE
    }.distinctUntilChanged() as Flow<Boolean>


    val thirdSwitch: Flow<Boolean> = this.dataStore.data.map { prefs ->
        prefs[THIRD_SWITCH_KEY] ?:  INITIAL_COUNTER_VALUE
    }.distinctUntilChanged() as Flow<Boolean>

    val firstSeekBar: Flow<Int> = this.dataStore.data.map { prefs ->
        prefs[FIRST_SEEKBAR_KEY] ?:  INITIAL_SEEKBAR_VALUE
    }.distinctUntilChanged() as Flow<Int>

    val secondSeekBar: Flow<Int> = this.dataStore.data.map { prefs ->
        prefs[SECOND_SEEKBAR_KEY] ?:  INITIAL_SEEKBAR_VALUE
    }.distinctUntilChanged() as Flow<Int>

    val thirdSeekBar: Flow<Int> = this.dataStore.data.map { prefs ->
        prefs[THIRD_SEEKBAR_KEY] ?:  INITIAL_SEEKBAR_VALUE
    }.distinctUntilChanged() as Flow<Int>

    private suspend fun firstSaveIntValue(key: Preferences.Key<Float>, value: Float) {
        this.dataStore.edit { prefs ->
            prefs[key] = value
        }
    }
    private suspend fun secondSaveIntValue(key: Preferences.Key<Float>, value: Float) {
        this.dataStore.edit { prefs ->
            prefs[key] = value
        }
    }

    private suspend fun thirdSaveIntValue(key: Preferences.Key<Float>, value: Float) {
        this.dataStore.edit { prefs ->
            prefs[key] = value
        }
    }
    private suspend fun firstSwitchValue(key:Preferences.Key<Boolean>, value: Boolean){
        this.dataStore.edit { prefs ->
            prefs[key] = value
        }
    }
    private suspend fun secondSwitchValue(key:Preferences.Key<Boolean>, value: Boolean){
        this.dataStore.edit { prefs ->
            prefs[key] = value
        }
    }
    private suspend fun thirdSwitchValue(key:Preferences.Key<Boolean>, value: Boolean){
        this.dataStore.edit { prefs ->
            prefs[key] = value
        }
    }


    private suspend fun firstSeekBar(key:Preferences.Key<Int>, value:
    Int){
        this.dataStore.edit { prefs ->
            prefs[key] = value
        }
    }
    private suspend fun secondSeekBar(key:Preferences.Key<Int>, value:
    Int){
        this.dataStore.edit { prefs ->
            prefs[key] = value
        }
    }
    private suspend fun thirdSeekBar(key:Preferences.Key<Int>, value:
    Int){
        this.dataStore.edit { prefs ->
            prefs[key] = value
        }
    }


    suspend fun firstSaveCounter(value: Float) {
        firstSaveIntValue(FIRST_COUNTER_KEY, value)
    }

    suspend fun secondSaveCounter(value: Float) {
        secondSaveIntValue(SECOND_COUNTER_KEY, value)
    }
    suspend fun thirdSaveCounter(value: Float) {
        thirdSaveIntValue(THIRD_COUNTER_KEY, value)
    }

    suspend fun firstSwitch(value: Boolean) {
        firstSwitchValue(FIRST_SWITCH_KEY,value)
    }
    suspend fun secondSwitch(value: Boolean){
        secondSwitchValue(SECOND_SWITCH_KEY,value)
    }
    suspend fun thirdSwitch(value: Boolean){
        thirdSwitchValue(THIRD_SWITCH_KEY,value)
    }
    suspend fun seekBar1(value:Int){
        firstSeekBar(FIRST_SEEKBAR_KEY,value)
    }
    suspend fun seekBar2(value:Int){
        firstSeekBar(SECOND_SEEKBAR_KEY,value)
    }
    suspend fun seekBar3(value:Int){
        firstSeekBar(THIRD_SEEKBAR_KEY,value)
    }


    companion object {
        private const val PREFERENCES_DATA_FILE_NAME = "settings"
        private var INSTANCE: PreferencesRepository? = null
        fun initialize(context: Context) {
            if (INSTANCE == null) {
                val dataStore = PreferenceDataStoreFactory.create {
                    context.preferencesDataStoreFile(PREFERENCES_DATA_FILE_NAME)
                }
                INSTANCE = PreferencesRepository(dataStore)
            }
        }
        fun getRepository(): PreferencesRepository {
            return INSTANCE ?: throw IllegalStateException("AppPreferencesRepository not initialized yet")
        }
    }
}