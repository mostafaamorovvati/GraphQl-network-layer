package com.example.networklayer.data.datasource.remote.commun


sealed class DataSourceException(val messageResource: Any?) : RuntimeException() {

    class Unexpected(messageResource: Int) : DataSourceException(messageResource)
    class Server(error: String?) : DataSourceException(error)

}