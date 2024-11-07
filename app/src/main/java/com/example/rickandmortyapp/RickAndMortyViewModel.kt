package com.example.rickandmortyapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapp.api.NetworkResponse
import com.example.rickandmortyapp.api.RetrofitInstance
import com.example.rickandmortyapp.api.RickAndMortyModel
import kotlinx.coroutines.launch

class RickAndMortyViewModel : ViewModel() {
    private val rickandmortyApi = RetrofitInstance.rickandmortyApi
    private val _result = MutableLiveData<NetworkResponse<RickAndMortyModel>>()
    val result : LiveData<NetworkResponse<RickAndMortyModel>> = _result

        fun getData(){
            _result.value =NetworkResponse.Loading
            viewModelScope.launch {
                try {
                    val response = rickandmortyApi.getCharacter()
                    if (response.isSuccessful){
                        response.body()?.let {
                            _result.value = NetworkResponse.Success(it)
                        }
                    }else{
                        _result.value = NetworkResponse.Error("Failed Load Data")
                    }


                }catch (e : Exception){
                    _result.value = NetworkResponse.Error("Failed Load Data")
                }
            }
        }
}