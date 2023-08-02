/**
 * Data classlar adından anlaşılacağı üzere veri tutmak için kullanılan sınıflar. Okunabilir çıktı üretme,
 * instance ları karşılaştırma-kopyalama gibi bir sürü özelliği kendisi otomatik olarak bulundurur.
 */

// data keyword
data class PersonData(val name: String, val age: Int)

/**
 * Primary constructorın en az bir parametresi olmalıdır.
 * Primary constructor a ait bütün parametreler val ya da var ile işaretlenmelidir.
 * Data classlar abstract, open, sealed veya inner class olamazlar.
 */


data class PersonParameterless(val name: String = "Empty", val age: Int = 30)

/**
 * Compiler data classlar ın primary constructor ında tanımlanan tüm parametreleri alarak;
 *
 * equals ve hashCode fonksiyonunu üretirler
 * toString() User(name=Halil, age=30)
 * componentN şeklinde desctructuring declarations sağlar.
 * copy ile propertylerin içeriklerinin kopyalanmasını sağlar
 */

data class Equality(val name: String) {
    var age: Int = 0
}

class AnotherEquality(val name: String) {
    var age: Int = 0
}

/**
 * Data classlar da implicit olarak val değerlerini değiştirmek mümkündür. Ancak bu nesnenin kendisini değiştirerek
 * değil kopyasını oluşturarak yapılır. Bu işlem data class üzerinde copy fonksiyonu çağırılarak yapılır.
 */

data class DataCopy(val name: String, val age: Int, val number: Int = 0)

fun main() {
    val personParameterless = PersonParameterless()

    val person1 = Equality("Halil")
    person1.age = 20
    val person2 = Equality("Halil")
    person2.age = 30

    val person3 = AnotherEquality("Halil")
    person3.age = 20
    val person4 = AnotherEquality("Halil")
    person4.age = 30

    println(personParameterless)
    println(person3)
    println(person1)

    println(person3 == person4)


    println(person1 == person2)

    println(personParameterless.age)
    println(personParameterless.name)

    val dataCopy = DataCopy("Halil", 30)
    val anotherCopy = dataCopy.copy(age = 25)

    println(anotherCopy)
    println(dataCopy)

    // Destructuring declarations
    // Pair ve Triple sınıflar. key - value şeklinde

    val (name, age, number) = dataCopy

    val numbers = intArrayOf(123, 34, 3, 2, 22)

    for ((index, value) in numbers.withIndex())

        println("Name:$name Age:$age")

}