package com.example.workapplication.ui.list

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.workapplication.R
import com.example.workapplication.api.FetchResult
import com.example.workapplication.ui.edit.EditFragment

class ListFragment : Fragment() {

    companion object {
        fun newInstance() = ListFragment()
    }

    private lateinit var viewModel: ListViewModel
    private val listAdapter by lazy { NewsRecyclerAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_menu, menu)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)

        // toolbar setting
        val toolbar = view.findViewById<Toolbar>(R.id.listToolbar)
        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_timezone -> {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.container, EditFragment.newInstance())
                        .addToBackStack(null)
                        .commit()
                    true
                }
                else -> false
            }
        }

        val recyclerList = view.findViewById<RecyclerView>(R.id.list)
        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)
        viewModel.fetchNews()
        progressBar.visibility = View.VISIBLE

        viewModel.news.observe(viewLifecycleOwner) {
            when (it) {
                is FetchResult.Success -> {
                    listAdapter.setNews(it.value.news)
                    listAdapter.setItemClick { url ->
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                        startActivity(intent)
                    }
                    recyclerList.adapter = listAdapter
                    progressBar.visibility = View.GONE
                }
                is FetchResult.Error -> {
                    progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), it.exception.message.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}