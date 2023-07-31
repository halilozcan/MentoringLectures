fun main() {

    val names = mutableListOf("Halil", "Mert", "Murat")

    /**
     * Lambda expressionlar ve anonymous functions literal olarak adlandırılır. Bir fonksiyon tanımlanmadan anlık olarak
     * kullanıldığı yapıya functions literal diyoruz
     */


    /**
     * () -> Unit,Int,String vs.
     * val function = (Int, Int) -> Int
     * 1. parametre Int, 2. parametre Int ve bu fonksiyonun dönüş değeri Int olur.
     * (Float, Double) -> String
     * (String, String) -> String
     * (Double, String, Int) -> String
     * (A,B) -> C
     * A = parametre tipi
     * B = parametre tipi
     * C = return tipi
     *
     * () -> A
     * (A,B,C,D)-> E
     * (String,String)-> Int
     * (A,B)-> X
     * (a:Int,b:Int) -> Unit
     */

    /**
     * Lambda expressionlar her zaman süslü parantezle çevrilir { }
     * ilk süslü parantezden sonra parametreler belirtilir. parametrelerin arasına virgül konur. parametrelerden sonra
     * -> (ok) işareti konulur ve daha sonra expression 'ın body si normal fonksiyon gibi yazılır.
     * lambda expressionların son ifadenin tipi return tipi olarak tanımlanır. return demenize gerek yoktur.
     */


    val sumOperation: (Int, Int) -> Int = { a: Int, b: Int ->
        println("Hello")
        a + b
    }

    // lambda expressionlar type inferencedır
    val sumOperationPure = { a: Int, b: Int ->
        println("Hello")
        a + b
    }

    for (i in 1..10) {
        //sumOperation(i, i + 1)
    }

    names.forEach {
        names.forEach {

        }
        //println(it)
        //println("I wrote")
    }


    // fonksiyon referansını tutan değişken null olabilir. sumOperation?.invoke(3,4)
    //sumOperation.invoke(3, 4)
    //sumOperation(3, 4)

    /**
     * lambda expressionlar tek parametre aldığı zaman bu otomatik olarak it diye isimlendirilir. eğer bir lambda
     * expression tek parametreliyse bu durumda ok işaretini koymamıza da gerek yoktur. it - implicit (kapalı) olarak
     * kendi içerisinde isimlendirmesi böyle yapılır.
     */

    // Uzunluğu 4 ten büyük olan isimleri filtrele
    val filteredNames = names.filter {
        println("Name:$it")
        val result = if (it.length > 4) {
            true
        } else {
            false
        }
        result
        //it.length > 4

    }.toTypedArray()

    filteredNames.forEach {
        println(it)
    }

    // Anonymous functions
    /**
     * Lambda expressionlar return type ı explicit(açık) olarak belirtemezler. Burada devreye anonymous fonksiyonlar
     * girer. Anonymous normal fonksiyonlar gibidir ancak tek farkları isimsiz olmalarıdır. fun (a:String):Boolean
     * single expression veya fonksiyon body si şeklinde yazılabilir. normal fonksiyonlar single expression olduğunda
     * return tipini açık olarak belirtmeye gerek yoktur.
     */

    names.filter {
        it.length > 4
    }

    val filterFunction = fun(element: String): Boolean {
        return element.length > 4
    }

    names.filter(filterFunction)
    names.filter(fun(element: String): Boolean {
        return element.length > 4
    })

    // İkisi de aynı
    val sumAnonymousSingleExpression = fun(a: Int, b: Int): Int = a + b
    val sumAnonymousFunctionBody = fun(a: Int, b: Int): Int {
        return a + b
    }

    sumAnonymousSingleExpression.invoke(1, 2)

    // Lambda expressionlar kendisini çevreleyen (scope alan parantezler bölüm) deki değişkenlere erişebilirler

    var availableNameCounter = 0

    names.filter {
        if (it.length > 4) {
            availableNameCounter++
        }
        it.length > 4
    }

    println(availableNameCounter)
}

fun sumOperation(a: Int, b: Int): Int {
    println("Hello")
    println("Hi")
    return a + b
}


// fun oguz(): A
// fun nuray(parameter1:A, parameter2:B,parameter3:C,para....):E

