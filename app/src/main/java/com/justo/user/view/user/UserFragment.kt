package com.justo.user.view.user

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Menu
import android.view.MenuItem
import android.view.MenuInflater
import androidx.fragment.app.viewModels
import com.justo.user.R
import com.justo.user.databinding.UserFragmentBinding
import com.justo.user.utility.ActionModeMenu
import com.justo.user.utility.viewModel.ViewModelFactory
import com.justo.user.view.user.adapter.UserAdapter
import com.justo.user.viewModel.user.UserViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class UserFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel: UserViewModel by viewModels { viewModelFactory }
    private lateinit var binding: UserFragmentBinding
    private lateinit var adapter: UserAdapter
    private lateinit var actionMode: ActionModeMenu

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)

        binding = UserFragmentBinding.inflate(inflater, container, false)

        viewModel.updateUserListUseCase()

        setupActionModeMenu()

        setupAdapter()

        setupListeners()

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_users, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.delete_users -> {
                viewModel.updateUserSelectionStatus(status = true)
                if (actionMode.getMode() == null) {
                    actionMode.startActionMode(view, R.menu.menu_selection_user)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.users.observe(viewLifecycleOwner, {
            if (it.isNullOrEmpty()) {
                Log.d("--- Response User", "List empty")
            } else {
                adapter.submitList(it)
            }
            binding.swipeRefresh.isRefreshing = false
        })

    }

    private fun setupActionModeMenu() {
        actionMode = ActionModeMenu(clickSelect = {
            actionMode.getMode()?.let { actionMode ->
                actionMode.finish()
            }
        }, clickBack = {
            viewModel.updateUserSelectionStatus(status = false)
            actionMode.getMode()?.let { actionMode ->
                actionMode.finish()
            }
        })
    }

    private fun setupAdapter() {
        adapter = UserAdapter(onUserChecked = { id, isChecked ->
            viewModel.updateUserChecked(id = id, isChecked = isChecked)
        })
        binding.recyclerViewUsers.adapter = adapter
    }

    private fun setupListeners() {
        binding.apply {
            swipeRefresh.setOnRefreshListener {
                viewModel.updateUserListUseCase()
            }
        }
    }

}