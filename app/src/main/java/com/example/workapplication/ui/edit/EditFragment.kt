package com.example.workapplication.ui.edit

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.workapplication.R
import com.example.workapplication.utils.FetchResultWithNoResponse
import com.google.android.material.snackbar.Snackbar

class EditFragment : Fragment() {

    companion object {
        fun newInstance() = EditFragment()
    }

    private lateinit var viewModel: EditViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.edit_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(EditViewModel::class.java)

        val emailText = view.findViewById<TextView>(R.id.emailText)
        emailText.text = "Email: ${viewModel.username}"

        // BackButton Setup
        val backButton = view.findViewById<Button>(R.id.backButton)
        backButton.setOnClickListener { activity?.onBackPressed() }

        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)
        val timezoneAutoTextView =
            view.findViewById<AutoCompleteTextView>(R.id.timezoneAutoTextView)
        val timezoneArray = (-12..14).toList().toTypedArray()
        val arrayAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_dropdown_item_1line,
            timezoneArray
        )
        timezoneAutoTextView.setText(viewModel.timezone.toString())
        timezoneAutoTextView.setAdapter(arrayAdapter)
        timezoneAutoTextView.setOnItemClickListener { _, _, i, _ ->
            timezoneAutoTextView.setText(timezoneArray[i].toString(), false)
            viewModel.editTimezone(timezoneArray[i])
            progressBar.visibility = View.VISIBLE
        }

        viewModel.editResult.observe(viewLifecycleOwner) {
            when (it) {
                is FetchResultWithNoResponse.Success -> {
                    Snackbar.make(view, "修改完成", Snackbar.LENGTH_LONG).show()
                    progressBar.visibility = View.GONE
                }
                is FetchResultWithNoResponse.Error -> {
                    Snackbar.make(view, it.exception.message.toString(), Snackbar.LENGTH_LONG)
                        .show()
                    progressBar.visibility = View.GONE
                }
            }
        }
    }

}