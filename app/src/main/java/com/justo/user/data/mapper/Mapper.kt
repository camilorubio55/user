package com.justo.user.data.mapper

import com.justo.user.data.db.entities.UserDB
import com.justo.user.data.dto.UserDTO
import com.justo.user.domain.models.User
import javax.inject.Inject

interface IMapper {
    fun mapToListUser(listUserDTO : List<UserDTO>): List<User>
    fun mapToUser(userDTO: UserDTO): User
}

class Mapper @Inject constructor() : IMapper {

    override fun mapToListUser(listUserDTO: List<UserDTO>): List<User> {
        val listUser = arrayListOf<User>()
        listUserDTO.forEach {
            listUser.add( mapToUser(it) )
        }
        return listUser
    }

    override fun mapToUser(userDTO: UserDTO): User {
        return User(
            id = null,
            name = userDTO.name.first,
            email = userDTO.email,
            phone = userDTO.phone,
            image = userDTO.picture.large
        )
    }

}