package com.justo.user.domain.models

import androidx.recyclerview.widget.DiffUtil
import java.io.Serializable

data class User(
    val id: Int,
    val name: String,
    var email: String,
    val phone: String,
    val image: String,
    var isSelectable : Boolean = false,
    var isSelected : Boolean = false
) : Serializable {

    object DiffCallback : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(
            oldItem: User,
            newItem: User
        ): Boolean {
            return oldItem.id == newItem.id && oldItem.isSelectable == newItem.isSelectable
        }

        override fun areContentsTheSame(
            oldItem: User,
            newItem: User
        ): Boolean {
            return oldItem == newItem
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

}