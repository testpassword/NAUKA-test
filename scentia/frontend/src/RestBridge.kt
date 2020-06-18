import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class RestBridge(uri: String) {

    private val url = URL(uri)
    private lateinit var connection: HttpURLConnection
    private var message: String? = null

    private enum class Methods {
        GET { override fun toString() = "GET" },
        POST { override fun toString() = "POST" },
        PUT { override fun toString() = "PUT" },
        DELETE { override fun toString() = "DELETE" }
    }

    private fun setUp(httpMethod: Methods): RestBridge {
        connection = url.openConnection() as HttpURLConnection
        connection.setRequestProperty("Content-Type", "application/json")
        connection.requestMethod = httpMethod.toString()
        return this
    }

    fun setMessage(message: String): RestBridge {
        this.message = message
        return this
    }

    fun send(): String {
        message?.let {
            connection.doOutput = true
            val outputStream = connection.outputStream
            outputStream.write(it.toByteArray())
            outputStream.flush()
        }
        if (connection.responseCode !in (200..299))
            throw RuntimeException("HTTP-код ответа = " + connection.responseCode)
        val reader = BufferedReader(InputStreamReader(connection.inputStream))
        return buildString {
            do {
                val line = reader.readLine()
                if (line != null) append(line)
            } while (line != null)
            connection.disconnect()
            message = null
        }
    }

    fun get() = setUp(Methods.GET)

    fun post() = setUp(Methods.POST)

    fun delete() = setUp(Methods.DELETE)

    fun put() = setUp(Methods.PUT)
}