package com.justo.user.view.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Menu
import android.view.MenuItem
import android.view.MenuInflater
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import com.justo.user.R
import com.justo.user.databinding.UserFragmentBinding
import com.justo.user.utility.ActionModeMenu
import com.justo.user.utility.Utils.Companion.gone
import com.justo.user.utility.Utils.Companion.visible
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
                showActionModeMenu()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.users.observe(viewLifecycleOwner, { userList ->
            binding.apply {
                if (userList.isNullOrEmpty()) {
                    containerEmptyState.visible()
                    recyclerViewUsers.gone()
                    hideActionModeMenu()
                } else {
                    userList.find { user ->  user.isSelectable }?.let {
                        showActionModeMenu()
                    } ?: run {
                        hideActionModeMenu()
                    }
                    recyclerViewUsers.visible()
                    containerEmptyState.gone()
                }
                adapter.submitList(userList)
                swipeRefresh.isRefreshing = false
            }
        })

    }

    private fun hideActionModeMenu() {
        actionMode.getMode()?.let { actionMode ->
            actionMode.finish()
        }
    }

    private fun setupActionModeMenu() {
        actionMode = ActionModeMenu(clickSelect = {
            val dialog = AlertDialog.Builder(requireContext())
                .setTitle(R.string.title_delete_users)
                .setMessage(R.string.title_description_delete_users)
                .setCancelable(true)
                .setPositiveButton(R.string.title_button_delete) { _, _ ->
                    viewModel.deleteUsersSelected()
                    hideActionModeMenu()
                }
                .setNegativeButton(R.string.title_button_cancel) { dialog, _ ->
                    dialog.dismiss()
                }
                .create()

            dialog.show()
        }, clickBack = {
            viewModel.updateUserSelectionStatus(status = false)
            hideActionModeMenu()
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

    private fun showActionModeMenu() {
        if (actionMode.getMode() == null) {
            actionMode.startActionMode(view, R.menu.menu_selection_user)
        }
    }

}