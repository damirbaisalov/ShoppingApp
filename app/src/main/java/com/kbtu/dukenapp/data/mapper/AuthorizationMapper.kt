package com.kbtu.dukenapp.data.mapper

import com.kbtu.dukenapp.data.model.user.UserDBModel
import com.kbtu.dukenapp.domain.model.User

class AuthorizationMapper {

    fun mapEntityToDBModel(user: User): UserDBModel = UserDBModel(
        id = user.id,
        name = user.name,
        email = user.email,
        password = user.password
    )

    fun mapDBModelToEntity(userDBModel: UserDBModel?): User? {
        if (userDBModel == null)
            return null
        return User(
            id = userDBModel.id,
            name = userDBModel.name,
            email = userDBModel.email,
            password = userDBModel.password
        )
    }
}