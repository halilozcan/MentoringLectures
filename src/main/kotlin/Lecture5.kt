fun main() {
    val number = 15
    println(number.findClosestUpperValue())

    println(3.addString(" Hello"))

    val numbers = Array(5) {
        it + 1
    }

    numbers.swap(index1 = 0, index2 = 4)

    println(numbers.toList())

    var number2: Int? = null

    println(number.nullableExtension())

    numbers.lastIndex


    println(3 addStringInfix "Hello")

    val name = "Hello"

    if (name isOkWithLength 3) {

    }

    5 * (4 + 3)
    println(5 multiplyWith 4 + 3)

    println(5 + 3 * 5)

    mapOf("1" to "1")

    println(infixParameterFunction(3, "Hello", fun Int.(a: String) = "$this$a"))
}

// sınıf oluştururken infix fonksiyon kullanımı

// sadece 1 parametre alır. default value olamaz. extensions functions olmak zorundadır.
infix fun String.isOkWithLength(length: Int): Boolean {
    return this.length > length
}

infix fun Int.multiplyWith(other: Int): Int = this * other

// fun Type.funcName(parameters):ReturnType
// 15
fun Int.findClosestUpperValue(): Int {
    return this + 1
}

fun Int.addString(placeholder: String): String {
    return "$this$placeholder"
}

// Array<Int> ifadesi receiver olarak adlandırılıyor. kotlin context receivers - kotlin function receivers
fun Array<Int>.swap(index1: Int, index2: Int) {
    val temp = this[index1]
    this[index1] = this[index2]
    this[index2] = temp
}

fun Int?.nullableExtension(): String {
    return if (this == null) {
        "oops! error"
    } else {
        toString()
    }
}

// infix functions
infix fun Int.addStringInfix(placeholder: String): String {
    return "$this$placeholder"
}

fun infixParameterFunction(number: Int, text: String, function: Int.(String) -> String): String {
    return function(number, text)
}







