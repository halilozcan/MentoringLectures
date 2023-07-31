private const val MINIMUM_AGE = 13

private const val INVALID_INDEX = -1

fun main() {
    val a = 10
    var max = a
    val b = 15

    // age >
    // name.isEmpty()

    // if(age > MINIMUM_AGE && name.isEmpty.not())
    val isAgeOk = a > b || a < 0
    val isFieldEmpty = a / b > 0

    /**
     * if(condition && condition || condition && condition)
     */
    if (a < b) {
        max = b
    }

    if (a < b)
        max = b

    if (a < b)
        if (isAgeOk)
            max = b
        else
            max = a

    max = if (a > b) a else b

    max = if (a > b) {
        /**
         * Herhangi bir hesaplama işlemini değer döndürmeden önce yapabilirsiniz.
         */
        println("a > b")
        val result = a * a
        result
    } else {
        println("b > a")
        val result = b / b
        result
    }

    max = if (a > b) {
        a
    } else if (b > a) {
        b
    } else {
        b
    }

    val name = "Halil"

    val isNameOkWithConditions = name.length > 10 && name.length < 18
    val isNameOk = if (name.length > 10 && name.length < 18) {
        val c = a + b
        val d = a * b
        val e = a / b
        Unit
        print("")
    } else {
        Unit
        print("")
    }

    val bound = 2f
    // 0101 0011
    // and 0001
    // or 0111
    // 01010 00011 -> 00010
    when (max) {
        // bitwise operation
        1L.toInt() and bound.toInt() -> {

            println("Ok")
        }

        else -> {
            println("Not Ok")
        }
    }

    // Enum değeri bulunamazsa exception fırlatır.
    // try - catch - finally
    val index: Int = when (Color.valueOf("BROWN")) {
        Color.BLUE -> {
            // some operations
            0
        }

        Color.BROWN -> {
            // if(someCondition)
            2
        }

        else -> {
            INVALID_INDEX
        }
    }

    max = when {
        a > b -> a
        b > a -> b
        else -> a
    }

    when (max) {
        a and b -> {
            println("int")
        }

        is Any -> {
            println("any")
        }
    }

    // guard clauses
    if (index != INVALID_INDEX) {

    } else {

    }

    if (index == INVALID_INDEX) {
        return
    }

    for (i in 0 until 10) {

    }

    for (i in 0 until 10 step 2) {

    }

    for (i in 10 downTo 0 step 2) {

    }

    // Kotlin Range
    for (i in 0..10) {

    }

    // for(i in 0..<10)
    val numbers = intArrayOf(1, 2, 3, 4, 5, 6)

    // numbers.indices 0..5
    for (i in numbers.indices) {
        println(numbers[i])
    }

    for (i in 0 until numbers.size) {

    }

    numbers.sort()

    numbers.forEach {
        println(it)
    }

    // Lambda expressions, higher order functions
    // break, continue, return ve jump farklı bir context de. ondan dolayı kullanım daha da zorlaşıyor
    numbers.forEachIndexed { index, element ->
        //println("index:$index element:$element")
    }

    // Destructuring declarations
    // break, continue, return ve jump aynı context de. bu çağırımlar direkt olarak döngüye etki ediyor
    for ((index, element) in numbers.withIndex()) {
        //println("index:$index element:$element")
    }

    var x = 10
    while (x > 0) {
        println(x)
        x--
    }

    // döngü kesinlikle 1 kere çalışıyor
    do {
        println(x)
        x--
    } while (x > 0)

}

enum class Color {
    BLUE,
    RED,
    BROWN
}

enum class LoginType {
    PHONE_NUMBER,
    EMAIL,
    SPECIAL_TOKEN
}