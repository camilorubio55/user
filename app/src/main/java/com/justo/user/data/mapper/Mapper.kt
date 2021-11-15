package com.justo.user.data.mapper

import com.justo.user.data.db.entities.UserDB
import com.justo.user.data.dto.UserDTO
import com.justo.user.domain.models.User
import javax.inject.Inject

interface IMapper {
    fun mapListUserDTOToListUser(listUserDTO : List<UserDTO>): List<User>
    fun mapListUserDBToListUser(listUserDB : List<UserDB>): List<User>
    fun mapListUserToListUserDB(listUser : List<User>): List<UserDB>
    fun mapToUser(userDTO: UserDTO): User
    fun mapToUser(userDB: UserDB): User
    fun mapToUserDB(user: User): UserDB
}

class Mapper @Inject constructor() : IMapper {

    override fun mapListUserDTOToListUser(listUserDTO: List<UserDTO>): List<User> {
        val listUser = arrayListOf<User>()
        listUserDTO.forEach {
            listUser.add( mapToUser(userDTO = it) )
        }
        return listUser
    }

    override fun mapListUserDBToListUser(listUserDB : List<UserDB>): List<User> {
        val listUser = arrayListOf<User>()
        listUserDB.forEach {
            listUser.add( mapToUser(userDB = it) )
        }
        return listUser
    }

    override fun mapListUserToListUserDB(listUser: List<User>): List<UserDB> {
        val listUserDB = arrayListOf<UserDB>()
        listUser.forEach {
            listUserDB.add( mapToUserDB(user = it) )
        }
        return listUserDB
    }

    override fun mapToUser(userDTO: UserDTO): User {
        return User(
            id = 0,
            name = userDTO.name.first,
            email = userDTO.email,
            phone = userDTO.phone,
            image = userDTO.picture.large
        )
    }

    override fun mapToUser(userDB: UserDB): User {
        return User(
            id = userDB.id,
            name = userDB.name,
            email = userDB.email,
            phone = userDB.phone,
            image = userDB.image,
            isSelectable = userDB.isSelectable,
            isSelected = userDB.isSelected
        )
    }

    override fun mapToUserDB(user: User): UserDB {
        return UserDB(
            id = 0,
            name = user.name,
            email = user.email,
            phone = user.phone,
            image = user.image
        )
    }

}