/**
 * Bazı durumlarda primitive typeları wrap edip bir sınıfın içerisine gömmek gerekir. Bu durumlarda runtime
 * performance fazlasıyla azalır. Bunun nedeni primitive tipleri ağır bir şekilde runtime tarafından optimize
 * edilmeye çalışılmasıdır.
 */

/**
 * Bu durumdan kurtulmamızı sağlayan içerisine nesne konulsa bile primitive tip olarak davranmasını compiler a
 * söyleyen yapı value (inline) classlardır. Inline classlar value anahtar kelimesi ile tanımlanır.
 */

// data class UserInput(val data: String)
// Jvm de desteklenmesi için başına @JvmInline annotation ı konur.
@JvmInline
value class UserRequest(private val loginId: String) : Logging {

    init {
        require(loginId.isNotEmpty()) {
            throw Exception("login id can not be empty")
        }
    }

    /**
     * Kotlin 1.9.0 ile birlikte inline value classlar body e sahip olabilirler.
     */

    constructor(name: String, hashCode: String) : this("$name$hashCode") {
        require(name.isNotEmpty()) {
            throw Exception("name can not be empty")
        }

        require(hashCode.isNotEmpty()) {
            throw Exception("hashcode can not be empty")
        }
    }

    override fun getLoginId(): String {
        return loginId
    }
}

interface Logging {
    fun getLoginId(): String
}

/**
 * Aşağıdaki fonksiyonların ikisi de JVM tarafında parametreyi string olarak alacağı gözüktüğü için JVM tarafında
 * fonksiyon public final void logUserId-<hashcode>(String id) olarak gözükür.
 */

fun logUserId(id: String) {

}


@JvmName("logUserRequest")
fun logUserId(id: UserRequest) {

}

fun main() {
    /**
     * Bu ifade runtime da sadece String olarak tutulur
     * Sınıfın kendisini beklediği durumlarda String e çevirilir. Diğer durumlarda sınıf olarak hareket eder.
     */
    val request = UserRequest("hello")

    asInline(request) // String
    asGeneric(request) // userRequest
    asInterface(request) // userRequest
    asNullable(request) // String?

    /**
     * UserRequest ilk başta UserRequest olarak gitse de kendisini döndüğü için geriye dönen ifade Stringdir.
     */
    val result = asReturnItSelf(request)
}

fun asInline(x: UserRequest) {}
fun <T> asGeneric(x: T) {}
fun asInterface(x: Logging) {}
fun asNullable(x: UserRequest?) {}

fun <T> asReturnItSelf(x: T): T = x