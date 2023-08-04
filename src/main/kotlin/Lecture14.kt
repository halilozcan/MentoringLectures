/**
 * Bazı nesneleri yapılandırmak, üzerinde işlem yapmak,
 * ona özel veya kendi başına geçici bir scope(alan) oluşturmak için
 * scope functions lar kullanılır
 */

/**
 * Bu fonksiyonlarda objelerin isimleri lambda expression ın receiver veya
 * argument olamasına göre değişir. eğer receiver ise (this) argument ise it
 * olarak adlandırılır.
 */

private var globalName: String? = "Hello"

private var born = 1995

// parametresi this olarak adlandırılır.
fun exampleOperationReceiver(block: String.() -> Unit) {

}

// parametresi it olarak adlandırılır.
fun exampleOperationArgument(block: (String) -> Unit) {

}

val name = run {
    // operations
    globalName
}

fun main() {

    /**
     * let
     * nesne it olarak adlandırılır. dönüş değeri ise lambdanın sonucu
     */

    /**
     * chaining call yapılan ve sonucun bir değere atanmadığı durumlarda
     * let kullanılır.
     */

    val numbers = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9)

    // chaining call
    numbers.size.plus(3).plus(34).plus(2)

    println(numbers)

    numbers.map { it / 2 }.filter { it > 3 }.let {
        println(it)
    }

    numbers.map { it / 2 }.filter { it > 3 }.let(::println)

    /**
     * genellikle null olmayan değerleri kullanmak (null safety) ve bu amaçla
     * bir scope oluşturmak için kullanılır.
     */

    val name: String? = "Hello"

    val length = name?.let {
        println(it.length)
        it.length
    }

    /**
     * with
     * nesneye this olarak erişilir. return değeri lambdanın sonucudur.
     * with extension function olmadığı için nesne parametre olarak verilir
     */

    /**
     * ilk kullanımı dönen sonuca ihtiyaç olmayan durumlar içindir
     * "with this object, do the following"
     */

    with(numbers) {
        println("Size is $size")
        // some operations
    }

    /**
     * bir nesnening değerleri bir sonuç hesaplamak için gerektiğinde kullanılır
     */

    val sum = numbers.first() + numbers.last()

    with(numbers) {
        first() + last()
    }

    /**
     * run
     * nesneye this kullanılarak erişilir. dönüş değeri lambdanın sonucudur
     * run ile with aynı işlemi yapar ancak run extension function olarak implemente edilmiştir.
     */

    /**
     * run aynı anda hem nesneyi initialize etmek ve yapılandırmak(değiştirmek) hem de bu işlemle bir dönüş
     * değeri hesaplamak için kullanılır.
     */

    val person = ScopePerson("", "", 1994)

    getPersonInfoRun(person, 1995)

    /**
     * run aynı zamanda extension fonksiyon olmadan da kullanılır. Bu durumda nesne üzerinden çağrılmaz.
     * dönüş değeri lambdanın sonucudur
     */

    val fullName: String? = "Hello Hello"

    val result = fullName ?: run {
        println("Name is null")
        throw Exception("Hello Mother Fucker")
    }

    /**
     * apply
     * nesneye this kullanılarak ulaşılır. dönüş değeri nesnenin kendisidir
     */

    /**
     * Sonuç döndürmeyen ve genellikle nesnenin propertyleri üzerinde işlem yapılan durumlar için kullanılır.
     * "apply the following assignment to the object"
     */

    val scopePerson = ScopePerson("", "", 1994)

    val newPerson = scopePerson.apply {
        born = 1995
    }

    val fullNameWith = with(newPerson) {
        this.name + lastName
    }

    val newPersonData = DataPersonData().copy(name = "Halil", lastName = "Özcan")

    val dataPerson = DataPersonData().apply {
        this.name = "Halil"
        lastName = "Özcan"
    }

    /**
     * also
     * nesneye it kullanılarak erişilir. dönüş değeri nesnenin kendisidir.
     */

    /**
     * also nesnenin propertylerine veya fonksiyonlarına erişmekten çok nesnenin kendisi üzerinde
     * yapılan aksiyonlar ve diğer context deki this shadowunu engellemek için kullanılır.
     * "and also the following with the object"
     */

    val scopePersonNew = ScopePerson("Outer Name", "Outer Last Name", 1994).let {
        born = 1995
        ScopePerson("Inner Name", "Inner Last Name", born).apply {
            it.lastName = lastName
        }
    }

    with(scopePersonNew) {
        println("New Name:${this.name} born:${born} LastName${this.lastName}")
    }

    numbers.also {
        println("before filtering the size of numbers:${it.size}")
    }.filter {
        it > 3
    }.let {
        println("after filtering the size of numbers:${it.size}")
    }
}

data class DataPersonData(var name: String = "", var lastName: String = "")


fun getPersonInfoRun(person: ScopePerson?, born: Int): String? {
    val result = person?.run {
        // prevent name shadowing this ile
        this.born = born
        "$name $lastName ${this.born}"
    }
    return result
}

fun parseNameLet(name: String?) {
    if (name != null) {
        // smart cast
        name.length
    }

    // guard let
    name ?: return

    name?.let {
        // Some operations
    }
}

fun parseNameGlobalLet() {
    if (globalName != null) {
        // smart cast yapılamaz. çünkü global name mutable dır
        //globalName.length
    }

    globalName?.let {

    }
}

// preventing shadowing it and this.
fun parsePersonInfo(person: ScopePerson?) {
    var born = 1004

    born = person?.born ?: 1004

    person?.let {
        born = it.born
    }
}

/**
 * chain ile kullanım arkaplanda bir sürü null  kontrole sebep olacağı için sadece null olan değer için let kullanılır.
 */

fun sumBornOfPersonWithValueLet(person: ScopePerson?): Int? {
    //  return person?.born?.sumWith(3)
    return person?.let {
        born sumWith 3
    }
}

fun processNumbersLet(array: IntArray?): Int? {
    array ?: return null

    array?.filter { it > 3 }?.map { it * 2 }?.sum()
    var sum = 0

    if (array != null) {
        sum = array.filter { it > 3 }.map { it * 2 }.sum()
    }

    return array?.let {
        it.filter { number ->
            number > 3
        }.map { mappedNumber ->
            mappedNumber * 2
        }.sum()
    }
}


infix fun Int.sumWith(a: Int): Int = this + a

class ScopePerson(val name: String, var lastName: String, var born: Int)