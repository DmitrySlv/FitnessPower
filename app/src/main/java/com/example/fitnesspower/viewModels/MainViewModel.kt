package com.example.fitnesspower.viewModels

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fitnesspower.models.ExerciseModel

class MainViewModel: ViewModel() {

    val listExercise = MutableLiveData<ArrayList<ExerciseModel>>()
    var pref: SharedPreferences? = null
    var currentDay = 0

    fun savePref(key: String, value: Int) {
        pref?.edit()?.putInt(key, value)?.apply()
    }

    fun getPref(key: String): Int {
        return pref?.getInt(key, DEFAULT_VALUE) ?: RETURN_VALUE
    }

    companion object {
       private const val DEFAULT_VALUE = 0
       private const val RETURN_VALUE = 0
    }
}