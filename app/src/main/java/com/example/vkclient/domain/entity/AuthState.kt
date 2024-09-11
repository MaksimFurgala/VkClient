package com.example.vkclient.domain.entity

/**
 * State авторизации.
 *
 * @constructor Create empty Auth state
 */
sealed class AuthState {

    object Initial: AuthState()

    object Authorized: AuthState()

    object NotAuthorized: AuthState()
}