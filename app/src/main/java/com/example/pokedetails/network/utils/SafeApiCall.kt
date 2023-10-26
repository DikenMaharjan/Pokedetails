package com.example.pokedetails.network.utils

import com.example.pokedetails.utils.dispatchers.AppDispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.cancellation.CancellationException

@Singleton
class SafeApiCall @Inject constructor(
    private val appDispatchers: AppDispatchers
) {
    suspend fun <T> execute(apiCall: suspend () -> Response<T>): Result<T> {
        return withContext(appDispatchers.io) {
            try {
                val response = apiCall.invoke()
                if (response.isSuccessful) {
                    Result.success(response.body()!!)
                } else {
                    //todo parse error message stream.
                    Result.failure(Exception("Something went wrong"))
                }
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}


