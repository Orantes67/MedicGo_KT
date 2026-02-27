package com.TiololCode.medicgo.features.register.data.datasource.remote.mapper

import com.TiololCode.medicgo.features.register.data.datasource.remote.model.RegisterResponseDto
import com.TiololCode.medicgo.features.register.domain.entities.RegisterResult
import com.TiololCode.medicgo.features.register.domain.entities.RegisteredUser

fun RegisterResponseDto.toDomain(): RegisterResult {
    return RegisterResult(
        message = message.orEmpty(),
        token = token.orEmpty(),
        user = user?.let {
            RegisteredUser(
                id = it.id.orEmpty(),
                name = it.name.orEmpty(),
                email = it.email.orEmpty(),
                licenseNumber = it.licenseNumber.orEmpty(),
                specialty = it.specialty.orEmpty()
            )
        }
    )
}

