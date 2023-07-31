fun main(args: Array<String>) {

    val x: Any = ""

    when (x) {
        is Int -> x + 1
        is String -> x.lastIndex
        is FloatArray -> x.size
    }

    // val-> value var -> variable
    // ilk başta ne olursa val ile tanımlama yapıyoruz. technical debt
    // access modifier -> val/var -> name // Type
    // type inference
    // Credit Card Number // Vatandaşlık Numarası // Veritabanı Id
    // val numberDouble(!)
    val integerType: Int? = null
    // UnsignedInteger bakın!
    // 1000000
    //val a = 10
    //val j = 20
    // j,k

    // BigDouble
    // 1.248,08 $ 1,248.08 $ // 100000,00 ->
    val rowCounter = 0
    val columnCounter = 0

    val pi: Double = 3.14
    val e: Float = 2.7182617f

    // Hexadecimal 0X0f
    // Binary 0b0001
    // val million = 1_000_000
    // val million = 1000000

    // -128 ile 127 bellek optimizasyonu
    var a: Int? = null
    a = 10
    val b: Int = 10

    // 1 + 3L -> Long
    val c: Long = 1.toLong()
    // Küçük tipler büyük tiplere çevrilemezler. Kendisine yazılan extension fonksiyonlarla beraber çevrilirler

    // Booleans
    // || && !
    val isOdd = false
    if (isOdd.not() || !isOdd) {

    }

    // Characters
    // val letter = 'a' "Halil"

    // Strings
    // mutable - immutable
    var fullName = "Halil"
    fullName = "Murat"

    fullName.forEach {

    }

    val upperCase = fullName.toUpperCase()

    println(fullName)

    println(upperCase)

    val s = "abc" + 1

    val template = "Result is:$s"

    val number1 = 5
    val number2 = 6

    val sumTemplate = "Sum of $number1 and $number2 is equal to:${number1 + number2}"

    println(sumTemplate)

    // Arrays
    val numbers = intArrayOf(1, 2, 3, 4, 5)
    val grades = floatArrayOf(24.36f, 25.32f)

    val numbersOther = Array(5) { index ->
        println(index)
        (index + 1) * (index + 1)
    }

    numbers.toTypedArray()

    numbers.forEachIndexed { index, i ->

    }

    // numbers[index]

    println(numbersOther.toList())

    val mixedElements = arrayOfNulls<Int>(5)

    //x.length
    /*
     // some operations
     */

    if (x is String) {
        x.length
    } else {
        // x.length
    }

    if (x !is String) {
        if (x is Int) {
            x.minus(2)
        }
    } else {
        x.length
    }

    // ||
    if (x !is String || x.length > 0) {

    }

    if (x is String && x.length > 0) {

    }

    val y: Any? = null
    val casted:String? = y as? String
    val casted2:String? = y as String?

    if (x !is String) return

    x.length


}