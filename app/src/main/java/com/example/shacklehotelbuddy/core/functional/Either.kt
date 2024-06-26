package com.example.shacklehotelbuddy.core.functional

sealed class Either<out L, out R> {
    // by FP conventions left would be the failure of this com.nytimes.newsapp.common.functional.Either
    data class Left<out L>(val error: L) : Either<L, Nothing>()

    // by FP conventions right would be the Success of this com.nytimes.newsapp.common.functional.Either
    data class Right<out R>(val reponse: R) : Either<Nothing, R>()

    fun <L> left(a: L) = Left(a)
    fun <R> right(b: R) = Right(b)

    fun either(fnL: (L) -> Any, fnR: (R) -> Unit): Any =
        when (this) {
            is Left -> fnL(error)
            is Right -> fnR(reponse)
        }

}