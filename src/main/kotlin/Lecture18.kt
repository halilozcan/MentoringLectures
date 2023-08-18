/**
 * GUI ( Graphical User Interface) -> Arayüz
 * Mother is a Human ->
 * Ability
 * eat, sleep, walk -> bunların toplandığı yer -> interfaceler
 * Mother can eat
 * Mother can sleep
 * Mother can walk
 */

/**
 * Interfaceler sınıfların sahip oldukları ortak soyut(abstract) veya somut fonksiyonların ve propertylerin
 * yer aldığı yapılardır. Sınıflar veya interfaceler tarafından implemente edilirler. Interface programming ile
 * fakeler oluşturularak test yazılabilir. Interfaceler abstract sınıflar a benzer ancak birden fazla
 * sınıftan implemente edilir ve stateleri bulunmaz.
 */

interface Animal {
    // bunu implemente eden sınıflarda override etmenize gerek yok
    fun eat() {

    }

    // abstract(soyut) fonksiyon haline gelir ve override edilmesi zorunludur
    fun sleep()

    val type: Int
}

/**
 * Interface i implemente eden sınıflar bütün abstract fonksiyonları override etmek zorundadır. Interfaceler
 * içerisinde tanımlanan propertylerin backing field ları olmaz!
 */
class Cat : Animal {
    override fun eat() {
        println("cat eat")
    }

    override fun sleep() {
        println("cat sleep")
    }

    override val type = 1
}

class Turtle : Animal {

    override val type = 1

    override fun sleep() {
        println("turtle sleep")
    }

    override fun eat() {
        println("turtle eat")
    }
}

interface Connection {
    fun close()
    fun connect()
}

class DatabaseConnection : Connection {
    override fun close() {
        println("close database connection")
    }

    override fun connect() {}
}

class NetworkConnection : Connection {
    override fun close() {
        println("close network connection")
    }

    override fun connect() {
        println("connect to network")
    }
}

fun eat(animal: Animal) {
    animal.eat()
}

fun sleep(animal: Animal) {
    animal.sleep()
}

fun closeConnection(connection: Connection) {
    connection.connect()
}

fun main() {
    val turtle = Turtle()
    eat(turtle)

    val cat = Cat()
    sleep(cat)
}

/**
 * Interface ler başka bir interface i implemente edebilirler. Bu durumda kendilerini implemente eden sınıflar da
 * override ettikleri propertyler bulundurmak zorunda kalmazlar.
 */

interface BaseAnimalInterface {
    val type: Int
}

interface Pet : BaseAnimalInterface {
    val mappedType: String

    override val type: Int
        get() = parseType(mappedType)
}

data class Dog(override val mappedType: String) : Pet


private fun parseType(value: String): Int {
    return value.toInt()
}

interface Puppy {
    fun eat() {
        println("puppy eat")
    }

    fun sleep() {
        println("puppy sleep")
    }
}

interface Human {
    fun eat() {
        println("human eat")
    }

    fun sleep()
}

class Creature : Puppy, Human {
    override fun eat() {
        /**super<Puppy>.eat()
        super<Human>.eat()*/
    }

    override fun sleep() {
        super<Puppy>.sleep()
        // Abstract üyelere direkt olarak erişim sağlanamaz
        // super<Human>.sleep()
    }
}

/**
 * Uygulamalarda test yazarken genellikle fake,mock ve stub kavramları karşınıza çıkar. Interface programming fake
 * oluştururken senaryoların takip edileceği şekilde yazılması üzerine kurgulanır.
 */

interface Source {
    fun loadData(): String
}

class NetworkResource : Source {
    override fun loadData(): String {
        return "Hello"
    }
}

class FakeNetworkSource : Source {
    override fun loadData(): String {
        return "Actual"
    }
}