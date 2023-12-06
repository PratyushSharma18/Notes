package com.pratyushvkp.notes.Repository


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pratyushvkp.notes.API.UserAPI
import com.pratyushvkp.notes.Utils.NetworkResult
import com.pratyushvkp.notes.models.UserRequest
import com.pratyushvkp.notes.models.UserResponse
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject

class UserRepository @Inject constructor(private val userAPI: UserAPI){

    private val _userResponseLiveData = MutableLiveData<NetworkResult<UserResponse>>()
    val userResponseLiveData : LiveData<NetworkResult<UserResponse>>
            get() = _userResponseLiveData

    suspend fun registerUser(userRequest: UserRequest){
        _userResponseLiveData.postValue(NetworkResult.Loading())
        val response = userAPI.signup(userRequest)

        handleResponse(response)

    }
    suspend fun loginUser(userRequest: UserRequest){
        _userResponseLiveData.postValue(NetworkResult.Loading())
        val response = userAPI.signin(userRequest)
        handleResponse(response)
    }

    private fun handleResponse(response: Response<UserResponse>) {
        if (response.isSuccessful && response.body() != null) {
            _userResponseLiveData.postValue(NetworkResult.Success(response.body()!!))
        } else if (response.errorBody() != null) {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _userResponseLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))

        } else {
            _userResponseLiveData.postValue(NetworkResult.Error("Something went wrong"))
        }
    }


}