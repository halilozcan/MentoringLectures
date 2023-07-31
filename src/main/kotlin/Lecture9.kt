fun main() {
    callingFunction()
}


// sadece kendi scope u
private val privateMemberVariable = "private"

// her yer
val publicMember = "public"

// modül, paket vs
@PublishedApi
internal val internalMember = "internal"

/**
 * inline fonksiyonlar kendisini çevreleyen sınıf veya scope daki private değişkenlere ve methodlara erişim sağlayamaz.
 * Erişmek için bunların public veya internal olması(internal olanların da @PublishedApi ile işaretlenmesi) gereklidir
 */

fun callingFunction() {
    println("calling started")
    /**
     * Higher order fonksiyon kotlin byte code çevriminde parametreler nesnelere dönüşür. Bunu örneğin bir döngüde
     * kullandığımız zaman her bir çağırımda nesne üretilir. bir fonksiyonun inline haline getirilmesi nesne oluşması
     * durumunu engeller
     */

    /**
     * inline fonksiyonlar reflection kullandığından dolayı en fazla 2-3 satırlı fonksiyonlar inline olarak
     * işaretlenmelidir. reflection her ne kadar yararlı olsa da performansa fazlasıyla etki eden bir şeydir.
     */


    println("lambda")

    higherOrderFunction {
        // bu scope local olarak adlandırılır
        /**
         * inline fonksiyonlar non-local returnlere izin verir. aynı zamanda lokal return de kullanabilirsiniz.
         */
        println("lambda")
        // non-local return return
        // local return return@higherOrderFunction
        return@higherOrderFunction
    }

    higherOrderFunctionNoInlined(lambda = {

    }, noInlinedLambda = {
        // noinline fonksiyonlar non local returne izin vermezler
        //return
        return@higherOrderFunctionNoInlined
    })

    /**
     * Elimde bir higher order fonksiyon var. Bu fonksiyon kendi içerisinde bir higher order fonksiyon çalıştırıyorsa
     * ve içinde çalışan kod parçacığı parametre olarak gelen fonksiyon referansıysa crossinline kullanılır.
     */

    higherOrderFunctionCrossInlined {
        // cross inline fonksiyonlar non-local return e izin vermezler. local returne izin verirler.
        return@higherOrderFunctionCrossInlined
    }

    println("calling ended")
}

inline fun noPermittedAsFunctionReference(lambda: () -> Unit, anotherLambda: () -> Unit) {
    action(lambda)
}

inline fun action(lambda: () -> Unit) {

}

inline fun higherOrderFunction(noinline lambda: () -> Unit) {
    println("higher order function started")
    // println("lambda")
    lambda()
    println("higher order function ended")
}

/**
 *
 */

// noinline kullanımı kotlin byte code a çevirip inceleme
inline fun higherOrderFunctionNoInlined(lambda: () -> Unit, noinline noInlinedLambda: () -> Unit) {
    doSomething()
    noInlinedLambda()
    doAnotherThing()
}

// crossinline kullanımı kotlin byte code a çevirip inceleme
inline fun higherOrderFunctionCrossInlined(crossinline lambda: () -> Unit) {
    // scope switching yapılırken lambda expression kullanımına izin verme
    normalFunction {
        lambda()
    }
}

fun normalFunction(lambda: () -> Unit) {
    println("normal function started")
    lambda()
    println("normal function ended")
}

fun doSomething() {
    println("do something()")
}

fun doAnotherThing() {
    println("do another thing()")
}

inline fun access(lambda: () -> Unit) {
    internalMember
    publicMember
    //privateMemberVariable
    doAnotherThing()
    //doSomething()
}