package com.example.api_project_aurelio.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.api_project_aurelio.R
import com.example.finalassignment.ViewmodelLogin
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch

//@AndroidEntryPoint
class LoginFrag : Fragment() {

    // Initialized var
    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var errorTextView: TextView

    // Use Hilt's ViewModel injection from LoginViewModel
    private val loginViewModel: ViewmodelLogin by viewModels()

    // Layout: fragment_login
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize views
        usernameEditText = view.findViewById(R.id.usernameInput)
        passwordEditText = view.findViewById(R.id.passwordInput)
        errorTextView = view.findViewById(R.id.errorTextView)

        // Handle edge-to-edge display adjustments
        ViewCompat.setOnApplyWindowInsetsListener(view) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Sign in button OnClickListener
        view.findViewById<Button>(R.id.SignIn).setOnClickListener {
            // Trim() removes any unnecessary space
            val username = usernameEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()


            // VALIDATION: If else condition
            // If username or password field is empty, show error
            if (username.isEmpty() || password.isEmpty()) {
                errorTextView.text = "Username and password cannot be empty"
                errorTextView.visibility = View.VISIBLE
            } else {
                lifecycleScope.launch {
//                    loginViewModel.login(username, password, onSuccess = { keypass ->
//                        // If keypass return then set the visibility of the error to GONE(Already GONE default)
//                        errorTextView.visibility = View.GONE
//                        // Navigate to the dashboard if login is successful
//                        // function declare at line:102
//                        navigateToDashboard()
//
//                    }, onError = { errorMessage ->
//                        // Error condition - All in the LoginViewModel
//                        errorTextView.text = "Login failed: $errorMessage"
//                        errorTextView.visibility = View.VISIBLE
//                    })
                }
            }
        }
    }

    // Function to navigate to Dashboard
    private fun navigateToDashboard() {
        // Create value navOptions to handle builder (Configuration) for the function
        val navOptions = NavOptions.Builder()
            // This removes the back stack when navigating to different fragment
            .setPopUpTo(R.id.fragmentLogin, true)
            .build()

        // Move from login to dashboard - while calling the navOptions value
        findNavController().navigate(R.id.action_loginFrag_to_dashboardFrag, null, navOptions)

        // Update the bottom navigation selectedId to Dashboard, so the selected navbar goes to Dashboard
        activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)?.selectedItemId = R.id.Dashboard
    }
}
