package com.example.a3track.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel(val repository: TrackerRepository): ViewModel() {

    var loginResult: MutableLiveData<LoginResult> = MutableLiveData()

}