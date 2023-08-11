/**
 * Uygulamanın çalışma flow unu bozan, veri akışının doğru handle edilmediği durumlarda ortaya çıkan hatalara
 * exception denir. Örn; sıfıra bölünme, null değere güvensiz erişim, internet olmaması durumu vb.
 */

fun main() {
    /**
     * Kotlin de exception fırlatma throw anahtar kelimesi ile yapılır.
     */

    try {
        throw Exception("Hello Mother Fucker!")
    } catch (e: Exception) {
        // println(e.message)
    }

    val input = "abc"

    /** Uygulamanın flow u fırlatılan hatalara göre değişiklik sağlayacak ise o zaman özel olarak
     * yakalanacak tüm exceptionların catch ifadesi yazılır
     */
    val operation = MathOperation(10, 1, 1)

    val result = try {
        println("try block()")
        operation.doOperation()
    } catch (e: Exception) {
        println("catch block()")
        0
    } finally {
        // camera?.release()
        // camera = null
        println("finally block()")
        operation.closeOperation()
    }

    println(result)


    val mappedInput = try {
        input.toInt()
    } catch (e: ArithmeticException) {
        20
    } catch (e: NumberFormatException) {
        null
    }

    if (mappedInput != null) {
        println(mappedInput)
    }

    /**
     * Nothing değer döndürülmeyen ve değerin asla olmadığını belirtmek için kullanılan bir sınıftır. Bir exception
     * fırlatılması ifadesinin sonucu Nothing dir. Bu tip hiç bi zaman erişilmeyecek code konumlarını işaretlemek
     * için kullanılır. Nothin bir final classtır. Constructor ı private dır. Genellikle sadece exception
     * fırlatan fonksiyonlar için kullanılır. Üst sınıf Any dir.
     */

    var name: String? = null
    val nothingValue = null
    listOf<String?>(null, null)
    //val length = name?.length ?: error("name can not be null")
    // println(name)

    println(Unit)
    println(Any().toString())
    println(Any().hashCode())
    println(Any() == Any())

    /**
     * Any bütün sınıflar atasıdır. equals, hashcode ve toString methodlarına sahiptir.
     * Bu methodlar opendır ve kalıtım alan sınıflar tarafından override edilebilir. Nesnesi oluşturulabilir.
     * Javadaki Object sınıfı gibi düşünülebilir
     */

    /**
     * Unit object classtır. Kalıtım alınamaz ya da nesnesi oluşturulamaz.
     * Javadaki void gibi düşünülebilir. Üst sınıfı Anydir ve override edilmiş
     * toString() methoduna sahiptir.
     */

    /**
     *
     */
}

@Throws(Exception::class)
private fun error(message: String): Nothing {
    throw Exception(message)
}

class MathOperation(var number1: Int, var number2: Int, var number3: Int) {

    private fun minusTwoInteger(number1: Int, number2: Int): Int {
        return number1 - number2
    }

    /**
     * Java tarafından kotlin kodu çağrılırken hangi exception ın handle edilmesini gerektiğini belirtmek için
     * böyle bir annotation kullanılması gereklidir. Yoksa compiler hata verir.
     */
    @Throws(ArithmeticException::class)
    fun doOperation(): Int {
        return number1 / minusTwoInteger(number2, number3)
    }

    fun closeOperation() {
        number1 = 0
        number2 = 0
        number3 = 0
    }
}

/**
 * Kotlin de Java da bulunan checheck exception yoktur
 * private int getCharacter() : throws Exception{ }
 * yani bir method(java) fonksiyon tanımlaması yaparken bunun aynı zamanda exception fırlatacağını belirtemezsiniz.
 */

