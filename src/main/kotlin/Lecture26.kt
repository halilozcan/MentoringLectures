import java.lang.reflect.Constructor
import kotlin.reflect.KProperty
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.isAccessible

/**
 * Reflection yazılan kodun yapısının çalışma zamanında incelenmesine olanak sağlayan bir yapıdır. Örneğin bir
 * property nin tipini öğrenme, private ise public yapma gibi işlemler yapılabilir
 */

class ReflectionClass

/**
 * Eğer bir sınıfın üyesi veya extension fonksiyonu kullanılmak istenirse; String::length
 */

val isEmptyStringList: List<String>.() -> Boolean = List<String>::isEmpty

fun isEven(x: Int) = x % 2 == 0

fun length(x: String) = x.length

fun <A, B, C> unionCondition(f: (B) -> C, g: (A) -> B): (A) -> C {
    return {
        f(g(it))
    }
}

var counter = 1

data class UserData(var name: String)

class Empty

fun createEmpty(initializer: () -> Empty) {
    val empty = initializer()
}

class ClassWithPrivateConstructor private constructor() {

    fun getName(): String = "Hello"
}

fun createPrivateClassWithReflection(): ClassWithPrivateConstructor {
    return (ClassWithPrivateConstructor::class.java.declaredConstructors[0].apply {
        isAccessible = true
    } as Constructor<ClassWithPrivateConstructor>).newInstance()
}

class ClassWithPrivateProperty {
    private val name: String = "Hello From Private Property"
}

fun main() {
    /**
     * Sınıf referansını alma
     * Burada dönen tip KClass şeklindedir. Kotlin Class, Java Class (JVM tarafındaki) referansı ile aynı değildir.
     */
    val reflectionKotlin = ReflectionClass::class
    println(reflectionKotlin.constructors.size)
    println(reflectionKotlin.isOpen)

    // val name: String = "Hello"

    // val length = name.length

    /**
     * Kotlin Class, Java Class referansı ile aynı değildir. JVM tarafındaki sınıfı almak için .java kullanılmalıdır.
     */
    val reflectionJava = ReflectionClass::class.java
    println(reflectionJava.constructors.size)

    val names = listOf("Halil", "Metehan", "Serdar", "İbrahim")
    println(names.isEmptyStringList())

    val numbers = listOf(1, 2, 3)
    println(numbers.filter(::isEven))

    val oddLengthPredicate = unionCondition(::isEven, ::length)
    println(names.filter(oddLengthPredicate))

    println(::counter.get())
    println(::counter.name)
    ::counter.set(2)
    println(counter)

    /**
     * Property referansı tek bir parametre beklenen fonksiyonlar kullanılabilir
     */
    println(names.map(String::length))
    println(names.map {
        it.length
    })

    val name = UserData::name
    println(name.get(UserData("Halil")))

    /**
     * Constructorsız bir sınıfın reflection ile kullanımı
     */
    createEmpty(::Empty)

    val private = createPrivateClassWithReflection()
    private.getName()

    val classWithPrivateProperty = ClassWithPrivateProperty()
    val field = ClassWithPrivateProperty::class.memberProperties.find {
        it.name == "name"
    }

    field?.let {
        it.isAccessible = true
        val value = it.get(classWithPrivateProperty)
        println(value)
    }


}