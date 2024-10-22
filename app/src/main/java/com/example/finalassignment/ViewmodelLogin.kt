package com.example.finalassignment

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalassignment.datas.UsernamePassword
import com.example.finalassignment.network.Service
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class ViewmodelLogin @Inject constructor(
    private val apiService: Service,
    @ApplicationContext private val appContext: Context
) : ViewModel() {

    fun signIn(username: String, password: String, onSuccess: (String) -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                val loginResponse = apiService.login(UsernamePassword(username, password))
                val keypass = loginResponse.keypass

                val sharedPreferences = appContext.getSharedPreferences("APP_PREFS", Context.MODE_PRIVATE)
                sharedPreferences.edit().putString("keypass", keypass).apply()

                Log.d("ViewmodeLogin", "Received keypass: ${loginResponse.keypass}")
                onSuccess(loginResponse.keypass)

            } catch (e: HttpException) {
                Log.e("LoginViewModel", "Error: ${e.message}")
                onError("Error: ${e.message}")

            } catch (e: IOException) {
                onError("Network error: ${e.message}")

            } catch (e: Exception) {
                onError("An error occurred: ${e.message}")
            }
        }
    }
}
