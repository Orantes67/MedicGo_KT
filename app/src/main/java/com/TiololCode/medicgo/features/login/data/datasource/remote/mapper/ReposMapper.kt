package com.TiololCode.medicgo.features.login.data.datasource.remote.mapper

import com.TiololCode.medicgo.features.login.data.datasource.remote.model.LoginResponseDto
import com.TiololCode.medicgo.features.login.domain.entities.LoginResult
import com.TiololCode.medicgo.features.login.domain.entities.User

fun LoginResponseDto.toDomain(): LoginResult {
    return LoginResult(
        message = message.orEmpty(),
        token = token.orEmpty(),
        user = user?.let {
            User(
                id = it.id.orEmpty(),
                name = it.name.orEmpty(),
                email = it.email.orEmpty(),
                licenseNumber = it.licenseNumber.orEmpty(),
                role = it.role.orEmpty()
            )
        }
    )
}
