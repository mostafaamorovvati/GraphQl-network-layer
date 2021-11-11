package com.example.networklayer.data.commun

import com.apollographql.apollo.api.Error

sealed class DataSourceException(
    val messageResource: Any?
) : RuntimeException() {
    class Unexpected(messageResource: Int) : DataSourceException(messageResource)
    class Server(error: Error?) : DataSourceException(error)
}