import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

/**
 * Durum tiplerini belirtmek için kullanılır. Enum dan en büyük farkı her bir durumun kendine ait farklı bir propertysi
 * bulunabilir. Property si bulunmayan durumlar da olabilir. Android tarafından en çok kullanılan yerler UiState
 * ve Android Network yada Local sorguların state inin bildirildiği yerlerdir.
 */

sealed class Response<T> {
    data class Success<T>(val data: T) : Response<T>()
    data object Loading : Response<Nothing>()
    sealed class Error : Response<Nothing>() {
        data object Network : Error()
        sealed class Writing : Error() {
            data object FullMemory : Writing()
            data object Permission : Writing()
        }
    }
}

sealed class HomeScreenUiState {
    data object Loading : HomeScreenUiState()
    data class Success(val data: HomeScreenUiData) : HomeScreenUiState()
    data object Error : HomeScreenUiState()
}

data class HomeScreenUiData(val isTextVisible: Boolean, val isButtonVisible: String, val buttonText: String)

data class ResultDto(val message: String)

fun main() = runBlocking {
    println("Error will not be thrown")

    delay(2000)

    makeNetworkRequest().collect {
        println(it)
    }

    delay(2000)

    println()

    println("Error will be thrown")
    makeNetworkRequest(isError = true).collect {
        when (it) {
            Response.Error.Network -> {
                println("Network Error")
            }

            Response.Error.Writing.FullMemory -> {
                println("Full Memory Error")
            }

            Response.Error.Writing.Permission -> {
                println("Permission Error")
            }

            Response.Loading -> {
                println("Loading")
            }

            is Response.Success -> {
                println("Response:${it.data.message}")
            }
        }
    }
}

suspend fun makeNetworkRequest(isError: Boolean = false) = flow {
    emit(Response.Loading)
    delay(2000)
    try {
        if (isError) {
            throw WritingException("Your internet connection is off")
        } else {
            emit(Response.Success(ResultDto("hello")))
        }
    } catch (e: Exception) {
        when (e) {
            is NetworkException -> {
                emit(Response.Error.Network)
            }

            is WritingException -> {
                emit(Response.Error.Writing.FullMemory)
            }
        }
    }
}

class NetworkException(override val message: String) : Exception(message)
class WritingException(override val message: String) : Exception(message)