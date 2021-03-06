package com.example.workapplication.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.workapplication.R
import com.example.workapplication.utils.FetchResult
import com.example.workapplication.utils.FormatChecker.isValidEmail
import com.google.android.material.snackbar.Snackbar

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        val button = view.findViewById<Button>(R.id.logInButton)
        val usernameEdit = view.findViewById<EditText>(R.id.usernameEdit)
        val passwordEdit = view.findViewById<EditText>(R.id.passwordEdit)
        val progressBar = view.findViewById<ProgressBar>(R.id.progressMain)

        button.setOnClickListener {
            if (usernameEdit.text.toString().isValidEmail()) {
                viewModel.tryLogin(usernameEdit.text.toString(), passwordEdit.text.toString())
                progressBar.visibility = View.VISIBLE
                button.visibility = View.INVISIBLE
            } else Snackbar.make(view, "帳號格式有誤", Snackbar.LENGTH_LONG).show()
        }

        viewModel.userInfo.observe(viewLifecycleOwner) {
            when (it) {
                is FetchResult.Success -> {
                    progressBar.visibility = View.GONE
                    button.visibility = View.VISIBLE
                    findNavController().navigate(R.id.action_mainFragment_to_listFragment)
                }
                is FetchResult.Error -> {
                    progressBar.visibility = View.GONE
                    button.visibility = View.VISIBLE
                    Snackbar.make(view, it.exception.message.toString(), Snackbar.LENGTH_LONG)
                        .show()
                }
            }
        }
    }

}