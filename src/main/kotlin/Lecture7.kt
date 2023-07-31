import java.util.*

// Kotlin context receiver scope functions lar olacak.
fun main() {
    /**
     * Bir fonksiyona parametre olacak fonksiyon geliyorsa o fonksiyonun kendisi higher order fonksiyondur.
     * bir higher order fonksiyon parametre olarak fonksiyon veya lambda expression alır.
     * eğer lambda expression veya fonksiyon son argümentse süslü parantezler fonksiyon parantezi dışına çıkarılarak
     * lambda expression yazılabilir.
     */


    /*higherOrderFunction(5, 4, { a, b ->
        a - b
    })

    higherOrderFunction(5, 4, { number1, number2 ->
        number1 * number2
    })

    notifyMe(5, {
        println(it)
    })*/

    higherOrderFunction(5, 4) { a, b ->
        a * b
    }

    higherOrderFunction(5, 4, { number1: Int, number2: Int ->
        number1 * number2
    })

    val sumFunction = fun(a: Int, b: Int): Int {
        return a + b
    }

    higherOrderFunction(5, 4, sumFunction)

    // kotlin function references
    // kotlin reflection -> bir performans sıkıntısı yaratır. ama uygulamanızı etkileyecek kadar değil
    higherOrderFunction(5, 4, ::sumHigher)

    val lambda = returnLambda()

    println(lambda.invoke())
    println(lambda())

    val names = mutableListOf("Halil", "Mert", "Murat A")

    // LINGQ style chained calling
    names.filter {
        it.length > 4
    }.forEach {
        println(it)
    }

    // Uzunluğu 5 olanları al. Değerlere göre sırala. Sıralanan elemanların hepsini büyük harfe çevir.
    names.filter {
        it.length > 4
    }.sortedBy {
        it.length
    }.map {
        it.uppercase()
    }.forEach {
        println(it)
    }
}

// () -> String
fun returnLambda(): () -> String {
    val lambda = { "Hello" }
    return lambda
}

fun sumHigher(number1: Int, number2: Int) = number1 + number2

// (String,String)-> Int = fonksiyon referans gösterimi

fun higherOrderFunction(a: Int, b: Int, operation: (Int, Int) -> Int) {
    // operation(a, b)
    operation.invoke(a, b)
}

fun notifyMe(a: Int, actionNotifier: (String) -> Unit) {
    actionNotifier.invoke("Hello I started")
    val result = a * a
    actionNotifier.invoke("Hello I did something for you")
    // some operations
    actionNotifier.invoke("Finally ready!")
}
