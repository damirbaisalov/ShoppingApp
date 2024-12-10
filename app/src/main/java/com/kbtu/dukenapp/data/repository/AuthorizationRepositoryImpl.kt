package com.kbtu.dukenapp.data.repository

import com.kbtu.dukenapp.data.local.AuthorizationDao
import com.kbtu.dukenapp.data.mapper.AuthorizationMapper
import com.kbtu.dukenapp.domain.model.User
import com.kbtu.dukenapp.domain.repository.AuthorizationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthorizationRepositoryImpl(
    private val authorizationDao: AuthorizationDao,
    private val mapper: AuthorizationMapper
) : AuthorizationRepository {

    override suspend fun checkUser(email: String): Boolean =
        withContext(Dispatchers.IO) {
            authorizationDao.checkUserByEmail(email)
        }

    override suspend fun createUser(user: User) {
        withContext(Dispatchers.IO) {
            authorizationDao.createUser(mapper.mapEntityToDBModel(user))
        }
    }

    override suspend fun loginUser(email: String, password: String): Int? =
        withContext(Dispatchers.IO) {
            mapper.mapDBModelToEntity(authorizationDao.loginUser(email, password))?.id
        }

    override suspend fun getUserByTokenId(id: Int): User? =
        withContext(Dispatchers.IO) {
            mapper.mapDBModelToEntity(authorizationDao.getUserById(id))
        }
}