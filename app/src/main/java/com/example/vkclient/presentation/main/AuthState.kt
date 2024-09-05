package com.example.vkclient.presentation.main

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