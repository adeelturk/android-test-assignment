package com.example.shacklehotelbuddy.core.networks.error

import com.example.shacklehotelbuddy.presentation.utils.UiErrorKeys


/**
 * Base Class for handling errors/failures/exceptions.
 */
sealed class ErrorEntity {

    object NetworkConnection : ErrorEntity()
    data class ServerError(var errorCode:Int=0) : ErrorEntity()
    object AuthError : ErrorEntity()
    object Forbidden : ErrorEntity()
    object BadRequest : ErrorEntity()
    object NotFound : ErrorEntity()
    object UnSupportedMediaType : ErrorEntity()
    object MalFormedJson : ErrorEntity()
    object IllegalStateException : ErrorEntity()
    object JsonSyntaxException : ErrorEntity()
    object SocketTimedOutException : ErrorEntity()
    object InternalServerError : ErrorEntity()
    object AndroidError : ErrorEntity()
    object UniqueConstraintError : ErrorEntity()
    object UserNotFound : ErrorEntity()
    object UNKOWN : ErrorEntity()
    object None : ErrorEntity()


    data class ApiRateLimitExceeded(val message:String) : ErrorEntity()
    data class FrontEndError(val uiErrorKeys:UiErrorKeys) : ErrorEntity()




}
