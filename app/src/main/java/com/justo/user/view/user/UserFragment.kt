package com.justo.user.view.user

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.justo.user.databinding.UserFragmentBinding
import com.justo.user.utility.viewModel.ViewModelFactory
import com.justo.user.viewModel.user.UserViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class UserFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel: UserViewModel by viewModels { viewModelFactory }
    private lateinit var binding: UserFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = UserFragmentBinding.inflate(inflater, container, false)

        viewModel.getUser()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.users.observe(viewLifecycleOwner, {
            if (it.isNullOrEmpty()) {
                Log.d("--- Response User", "List empty")
            } else {
                Log.d("--- Response User", it.toString())
            }
        })

    }

}