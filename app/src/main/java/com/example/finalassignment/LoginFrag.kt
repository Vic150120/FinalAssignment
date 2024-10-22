package com.example.finalassignment

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
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

//s4685483
@AndroidEntryPoint
class LoginFrag : Fragment() {

    private lateinit var userName: EditText
    private lateinit var pass: EditText
    private lateinit var errorWord: TextView

    private val model: ViewmodelLogin by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userName = view.findViewById(R.id.name)
        pass = view.findViewById(R.id.studentId)
        errorWord = view.findViewById(R.id.errorTextView)

        ViewCompat.setOnApplyWindowInsetsListener(view) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        view.findViewById<Button>(R.id.SignIn).setOnClickListener {
            val username = userName.text.toString().trim()
            val password = pass.text.toString().trim()

            if (username.isEmpty() || password.isEmpty()) {
                errorWord.text = "Empty"
            } else {
                lifecycleScope.launch {
                    model.signIn(username, password, onSuccess = { keypass ->
                        navigateToDashboard()

                    }, onError = { errorMessage ->
                        errorWord.text = "Failed: $errorMessage"
                    })
                }
            }
        }
    }

    private fun navigateToDashboard() {
        val navOptions = NavOptions.Builder()
            .setPopUpTo(R.id.fragmentLogin, true)
            .build()

        findNavController().navigate(R.id.action_loginFrag_to_dashboardFrag, null, navOptions)

        activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)?.selectedItemId = R.id.Dashboard
    }
}
