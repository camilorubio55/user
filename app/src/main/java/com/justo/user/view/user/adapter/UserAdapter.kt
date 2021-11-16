package com.justo.user.view.user.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.justo.user.databinding.UserItemBinding
import com.justo.user.domain.models.User

class UserAdapter(
    private val onUserChecked : (id : Int, checked : Boolean) -> Unit
) : ListAdapter<User, UserAdapter.UserViewHolder>(User.DiffCallback){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserViewHolder {
        return UserViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(onUserChecked, user)
    }

    class UserViewHolder private constructor(
        private val binding: UserItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            onUserChecked : (id : Int, checked : Boolean) -> Unit,
            user: User
        ) {
            binding.apply {
                textViewName.text = user.name
                textViewEmail.text = user.email
                textViewPhone.text = user.phone
                checkBoxUser.isVisible = user.isSelectable
                checkBoxUser.isChecked = user.isSelected

                checkBoxUser.setOnCheckedChangeListener { _, state ->
                    onUserChecked(user.id, state)
                }

                Glide.with(imageViewAvatar.context)
                    .load(user.image)
                    .circleCrop()
                    .into(imageViewAvatar)

                executePendingBindings()
            }
        }

        companion object {
            fun from(parent: ViewGroup): UserViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = UserItemBinding.inflate(
                    layoutInflater,
                    parent,
                    false
                )
                return UserViewHolder(binding)
            }
        }
    }

}