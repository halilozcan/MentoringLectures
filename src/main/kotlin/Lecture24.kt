/**
 * Sadece bir tipe bağımlı kalmamak için bir yapının birden fazla veri tipiyle çalışmasını sağlamak için genericler
 * kullanılır.
 * Genericler <> arasına tanımlama tarafında bir harf ya da kelime kullanılarak tanımlanır. Çağırma aşamasında
 * ise tip belirtilir
 */

fun <T> log(x: T) {
    println(x)
}

/*fun log(x: String) {
    println(x)
}

fun log(x: Int) {
    println(x)
}

fun log(x: Double) {
    println(x)
}

fun log(x: Float) {
    println(x)
}*/

interface Logger<T> {
    fun log(x: T)
}

/**
 * Buradaki interface implemente edileceği zaman tip belirtilmelidir. Belirtilen tiple beraber
 * compiler x in String olacağını bilir ve ona göre override eder.
 */
class StringLogger : Logger<String> {
    override fun log(x: String) {

    }
}

/**
 * Everything is about subtyping
 * Dog -> Animal ---------> List<Dog> -> List<Animal> ın alt sınıfıdır diye her zaman diyemeyiz.
 */

interface AnimalGeneric

interface DogAnimal : AnimalGeneric

interface CatAnimal : AnimalGeneric

/**
 * Parametre -> in position -> contravariance -> alt sınıf yerine üst sınıf kullanabilme
 * Return -> out position -> covariance -> üst sınıf yerine alt sınıf kullanabilme
 */

class Labrador : DogAnimal

class British : CatAnimal

interface SuperType {
    /**
     * A subtype must accept at least the same range of types as its supertype declares
     * A subtype must return at most the same range of types as its supertype declares
     */
    fun match(subject: CatAnimal): DogAnimal
}

/**
 * Invariance -> Asıl verilen tipin kullanılması
 * Covariance -> Üst sınıf yerine alt sınıf kullanılabilmesi
 * Contravariance -> Alt sınıf yerine üst sınıf kullanılabilmesi
 */

open class SuperHuman {
    // some methods & member variables
}

class Child : SuperHuman() {
    // some methods & member variables
}

// invariance
class InvarianceGenericClass<T : SuperHuman> {

}

/**
 * Covariance; super type yerine subtype kullanılması
 * Sadece out position da kullanılabilir. Yani fonksiyonun return type ı olarak kullanılabilir.
 * val property olarak kullanılabilir, var property olarak kullanılmaz
 */
class CovarianceGenericClass<out T : SuperHuman>

/**
 * Contravariance; subtype yerine supertype ın kullanılması
 * Sadece in position da yani parametre olarak kullanılabilir.
 * out position olarak kullanılmaz yani val ya da var property olarak kullanılmaz
 */
class ContravarianceGenericClass<in T : SuperHuman>

open class Mammal(val name: String) {
    fun eat() {
        println("eat()")
    }
}

data class Kitty(val kittyName: String, val isTwoFingered: Boolean) : Mammal(kittyName)

data class Lion(val lionName: String) : Mammal(lionName) {

}

fun feed(animals: List<Mammal>) {
    animals.forEach {
        it.eat()
    }
}


interface SubType : SuperType {
    override fun match(subject: CatAnimal): DogAnimal
}

class SubTypeImplementation : SubType {
    override fun match(subject: CatAnimal): DogAnimal {
        return Labrador()
    }
}

fun addComputer(list: MutableList<Any>) {
    list.add(Computer())
}

/**
 * Out ve in positionlarda constructorlar bu parametre olarak alma kuralına tabi tutulmazlar.
 * Java nın aksine Kotlin tarafından declaration-site variance şeklinde sınıflar yazılırken type
 * parametreleri tanımlanabilir. Java da WildCards'a (*) ihtiyaç bulunmaktadır. Böylece fazlasıyla
 * boilerplate code yazılmamış olur
 */
class ReadOnlyBox<out T>(private var item: T) {
    fun getItem(): T = item
    // Önerilmez. Ama gerekli olan durumlarda kullanılır
    // fun setItem(item: @UnsafeVariance T) = item
}

class WriteOnlyBox<in T>(private var item: T) {
    fun setItem(newItem: T) {
        item = newItem
    }
}

/**
 * Declaration Site Variance
 */
interface SomeInterface<in I, out O> {
    fun someFunction(item: I): O
}


interface Device

class Computer : Device

class Telephone : Device

fun <T> copyData(source: MutableList<T>, destination: MutableList<in T>) {
    for (element in source) {
        destination.add(element)
    }
}

fun main() {
    /**
     * Genericlerde type inference vardır. Burada x direkt olarak String olarak değerlendirilir. Listelerde
     * generic type lar vardır. İlk tanımlama esnasında alınacak değerler verilerek type inference la Listenin
     * tipi otomatik olarak belirtilir. Bu da ilk tanımlama yapıldıktan sonra compile-time da hata oluşur
     */
    log(15)
    log("Hello")
    log(12.4f)

    /**
     * Child super human ın alt sınıfıdır. Type atamasında verilebilir. Ama burada invariance1 değişkeni
     * artık InvarianceGenericClass<Child> type ı haline gelir
     */
    /**var invariance1 = InvarianceGenericClass<Child>()
    invariance1 = InvarianceGenericClass<SuperHuman>()
    val invariance2: InvarianceGenericClass<SuperHuman> = InvarianceGenericClass<Child>()*/

    /**
     * Sınıfın alt tipinin kullanılabilmesi için out ile işaretlenmesi gereklidir
     */
    val covarianceGenericClass: CovarianceGenericClass<SuperHuman> = CovarianceGenericClass<Child>()

    /**
     * Sınıfın üst tipinin kullanılabilmesi için in ile işaretlenmesi gereklidir
     * use-site variance
     */
    val contravarianceGenericClass: ContravarianceGenericClass<Child> = ContravarianceGenericClass<SuperHuman>()

    /**
     * Listenin tipi otomatik olarak kitty olur
     */
    val kitties = listOf(Kitty("A", false), Kitty("B", false), Kitty("C", false))

    val lions = listOf(Lion("D"), Lion("E"))

    /**
     * Kitty -> Mammal ---> List<Kitty> -> List<Mammal>
     */

    /**
     * Covariance
     */
    feed(kitties)
    feed(lions)

    val allElements = listOf(
        Kitty("Jerry", false),
        Kitty("Bae", true),
        Kitty("Alex", false),
        Lion("Tegan"),
        Lion("Peggy"),
    )

    val compareNames = Comparator { m1: Mammal, m2: Mammal ->
        m1.name.first().code - m2.name.first().code
    }

    /**
     * Contravariance
     */
    println(allElements.sortedWith(compareNames))

    val telephones = mutableListOf(Telephone(), Telephone(), Telephone())

    /**
     * Bu eklemenin yapılamamasının sebebi Mutable List in invariant olmasıdır.
     * Invariance sadece aynı tipi kabul eder. Burada MutableList invariant
     * olmasaydı bir Exception fırlatılması söz konusu olurdu
     *
     * Class = Telephone
     * Types = Telephone, Telephone?
     * Subtypes = Telephone inherit eden herhangi bir şey ya da Telephone un kendisi veya Telephone un üst sınıfı
     */

    /**
     * Nullable değer kabul eden bir değişken içerisinde null olmayan bir değer tutulabilir ama tam tersi olamaz
     *
     * A -> A?
     * Telephone -> Telephone?
     * Telephone? -> Telephone
     */
    // addComputer(telephones)

    val newTelephones = mutableListOf(Telephone(), Telephone(), Telephone())

    val computers = mutableListOf(Computer(), Computer(), Computer())

    val devices = mutableListOf(Telephone(), Telephone(), Computer())

    copyData(computers, devices) // List<Device> is subtype of List<Computer>
    copyData(newTelephones, devices) // List<Device> is subtype of List<Telephone>

    copyData(newTelephones, newTelephones) // List<Telephone> is subtype of List<Telephone>

    /**
     * Compile time hatası verir. Computer ve Telephone arasında bir typing yoktur
     */
    // copyData(computers,newTelephones)
}