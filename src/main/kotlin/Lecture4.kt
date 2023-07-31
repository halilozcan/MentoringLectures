private const val INVALID_INDEX = -1

private const val DEFAULT_MULTIPLIER = 10

fun main() {
    val result = getDefaultParameterFunc(10)
    val result2 = getDefaultParameterFunc(multiplier = 10, number = 20)

    // vararg son parametre değilse diğer parametreler named argument şeklinde kullanılabilir.
    add("", "", "", "", "", "", *arrayOf("2", "3", "4"), number = 1)
}

// fun name(parameters):ReturnType{ // operation }
// parametre pascal case name:Type, , , , ,
fun findIndexOfName(name: String?): Int {
    name ?: return INVALID_INDEX

    // operations
    return if (name.length > 5) 10 else 0

}

// fiil + isim -> boolean is + Isim -> isEqual -> getColorOfAnimal(parametre:Int)
fun getDefaultParameterFunc(number: Int, multiplier: Int = DEFAULT_MULTIPLIER): Int {
    return if (number > 10) {
        number * multiplier
    } else {
        number * -multiplier
    }
}

fun sumSingleExpression(a: Int, b: Int): Int = a + b

fun sumWithReturn(a: Int, b: Int): Int {
    return a + b
}

fun printSumOfTwoNumbers(a: Int, b: Int) {
    println("sum:${a + b}")
}


// Parent class ın fonksiyonlarından biri default argumente sahip olsa bile override edildiği zaman normal argumente
// çevrilir.

fun add(vararg sentences: String, number: Int) {
    sentences[0]
}

fun login(userName: String, password: String): Boolean {
    // Referans tipe dönüşüyor
    var counter = 1
    fun validateInput(input: String) {
        if (input.isEmpty()) {
            counter++
            throw Exception("Input can not be empty")
        }
    }
    validateInput(userName)
    validateInput(password)
    return true
}


data class IntClass(var element: Int = 1)



