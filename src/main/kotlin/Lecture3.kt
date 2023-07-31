import java.io.File

fun main() {
    var name: String? = null

    // val nameLength = name?.length ?: return
    //findBitmapWidth(name)

    name = "Halil"
    //findBitmapWidth(name)

    sampleLoop@ for (i in 1..10) {
        /**
         * for
         */
    }

    // break döngünün o adımının bitirilmesini sağlıyor

    outerLoop@ for (i in 1..10) {
        // eğer içerideki döngüdeki sayaca veya şarta bağlıysanız o zaman labellama gerekiyor - dış döngüden çıkmak için
        innerLoop@ for (j in 1..10) {
            if (i == 5) {
                break
            }
            //println("i:$i j:$j")
        }
    }

    intArrayOf(1, 2, 3, 4, 5).forEach {
        // Alttakiler paketleniyor - kopyalanıyor
        //if (it == 3) return
        println(it)
    }

    for ((index, value) in intArrayOf(1, 2, 3, 4, 5).withIndex()) {
        if (value == 3) {
            //return
        }

        //println(value)
    }

    // continue


    intArrayOf(1, 2, 3, 4, 5).forEach expression@{
        // Alttakiler paketleniyor - kopyalanıyor
        if (it == 3) return@expression
        //println(it)
    }
    //

    //
    run loop@{
        intArrayOf(1, 2, 3, 4, 5).forEach {
            // Alttakiler paketleniyor - kopyalanıyor
            if (it == 3) return
            println(it)
        }
    }


    println("Reached here")

    // Some operation
}

/*fun findBitmapWidth(name: String?) {
    name ?: return // buranın altına devam etme

    println(name.length.toString())
}

fun traverseElements(element: Int) {
    if (element == 3) {
        return
    }
}*/

