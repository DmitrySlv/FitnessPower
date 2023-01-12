package com.example.fitnesspower.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fitnesspower.models.ExerciseModel

class MainViewModel: ViewModel() {

    val listExercise = MutableLiveData<ArrayList<ExerciseModel>>()


}