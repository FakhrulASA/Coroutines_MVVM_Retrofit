package com.example.coroutineretrofit.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coroutineretrofit.interactor.LoginUserUseCase
import com.example.coroutineretrofit.model.PostData
import com.example.coroutineretrofit.repository.PostRepo
import com.example.coroutineretrofit.util.ErrorBody
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import retrofit2.Response

class PostViewModel : ViewModel() {
    var postRepo: PostRepo = PostRepo()
    var loginUserUseCase: LoginUserUseCase = LoginUserUseCase()
    var myResponse: MutableLiveData<PostData> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val error:MutableLiveData<ErrorBody> = MutableLiveData()


    fun getWeather(lat:Double,lon:Double){
        CoroutineScope(Dispatchers.IO).launch {
            loginUserUseCase.invoke(lat,lon,{
                error.postValue(ErrorBody(false,"success"))
                myResponse.postValue(it)
            },{
                error.postValue(ErrorBody(true,it))
            })
        }

    }


}