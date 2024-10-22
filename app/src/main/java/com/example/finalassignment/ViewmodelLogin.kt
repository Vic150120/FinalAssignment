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

        // Launch coroutine in viewModelScope to handle background task
        viewModelScope.launch {

            try {
                // Use API service to call login
                val loginResponse = apiService.login(UsernamePassword(username, password))
                val keypass = loginResponse.keypass

                // Save keypass in SharedPreferences
                // APP_PREFS -> storage for data
                // Context.MODE_PRIVATE -> Ensures only this app can access
                val sharedPreferences = appContext.getSharedPreferences("APP_PREFS", Context.MODE_PRIVATE)
                sharedPreferences.edit().putString("keypass", keypass).apply()

                // Check on logCat
                Log.d("ViewmodeLogin", "Received keypass: ${loginResponse.keypass}")

                // If successful, pass the keypass to the onSuccess callback
                onSuccess(loginResponse.keypass)

            } // Error handling
            catch (e: HttpException) {
                // Handle HTTP exceptions
                Log.e("LoginViewModel", "Error: ${e.message}")
                onError("Error: ${e.message}")

            } catch (e: IOException) {
                // Handle network or conversion issues
                onError("Network error: ${e.message}")

            } catch (e: Exception) {
                // Handle any other exception
                onError("An error occurred: ${e.message}")
            }
        }
    }
}
