package com.example.networklayer.data.datasource.remote.commun


sealed class ResponseResult<out T> {

    data class Success<out T>(val data: T) : ResponseResult<T>()

    data class Error(val exception: DataSourceException) : ResponseResult<Nothing>()

    object Loading : ResponseResult<Nothing>()

}

inline fun <T : Any> ResponseResult<T>.onSuccess(action: (T) -> Unit): ResponseResult<T> {
    if (this is ResponseResult.Success) action(data)
    return this
}

inline fun <T : Any> ResponseResult<T>.onError(action: (DataSourceException) -> Unit): ResponseResult<T> {
    if (this is ResponseResult.Error) action(exception)
    return this
}

inline fun <T : Any> ResponseResult<T>.onLoading(action: () -> Unit): ResponseResult<T> {
    if (this is ResponseResult.Loading) action()
    return this
}