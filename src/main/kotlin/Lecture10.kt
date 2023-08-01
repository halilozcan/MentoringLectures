/**
 * Sınıflar belirli bir gruba ait ortak özelliklerin veya kabiliyetlerin toplandığı toplandığı yerdir
 * constructor pharanthesis
 * sınıf isimlendirmesi fiil içermez
 */

// @Inject

class PersonEmpty

class PersonConstructor constructor(firstName: String)

/**
 * Eğer primary constructor herhangi bir annotation veya visibility modifier (public,internal,protected) içermiyorsa
 * constructor kelimesi silinebilir.
 */

// studentArray:Array<Int> -> students nameString -> name

class PersonWithoutConstructor(firstName: String)

/**
 * Constructorlar da val veya var kullanılmazsa init blockları haricinde bu parametreler görünmez olur.
 * yani init dışından bunlara erişim sağlayamazsınız. sadece yapılandırıcıya ait olur. sınıfa ait olmaz.
 * initler sırasıyla çalışır.
 */
class PersonInitializer(val firstName: String, val lastName: String, isStudent: Boolean) {
    val fullName: String

    //fun getPersonName() = firstName + lastName
    init {
        println("first init block:$firstName")
    }

    val variable1 = "Variable1".also(::println)

    init {
        fullName = firstName + lastName
        println("full name init block:$fullName")
    }

    val variable2 = "Variable2".also(::println)

    init {
        println("second init block:$firstName")
    }
}

val invalidLectureId = -1

/**
 * İçinde bulunduğumuz sınıfa erişmek için kullandığımız yapılara this denir.
 * Programtik olarak view oluştururken. ImageView(context:Context)
 */

/**
 * Initializer blockları constructorlardan önce çalışır
 */

class Lecture(val id: Int) {

    constructor(person: PersonX) : this(person.id) {
        println("person constructor worked")
        person.lectures.add(this)
    }

    constructor(name: String) : this(invalidLectureId) {
        println("name constructor worked")
    }

    init {
        println("init worked")
    }
}

class PersonX(val id: Int, val lectures: MutableList<Lecture> = mutableListOf())

fun main() {
    val personInitializer = PersonInitializer("", "", false)

    val lectureWithPrimary = Lecture(1)
    val lectureWithPersonAsSecondary = Lecture(PersonX(1))
    val lectureWithName = Lecture("Hello")

    // val wontBeCreated = WontBeCreated()

    val defaultParameter = DefaultParameter()

    println(lectureWithPrimary.id)
    println(lectureWithPersonAsSecondary.id)
    println(lectureWithName.id)
}

/**
 * Eğer bir sınıfın üretilmesini istemiyorsanız constuctructor ını private olarak işaretlemek
 * yeterli olacaktır.
 */

/**
 * private -> sadece kendi sınıfı içerisinden erişilebilir.
 * public -> her taraftan erişilebilir
 * internal -> sadece aynı modüller içerisinden erişilebilir.
 * protected -> türetilen sınıflardan (kalıtım alan sınıflardan) içerisinden erişilebilir.
 */

// Nothing -> contructor ı private ı dır. Burada değer yok kardeşim demek :)
class WontBeCreated private constructor()

/***
 * Bir sınıfın primary constructor ın daki tüm değerleri default value ya sahipse
 * JVM parametresiz bir constructor üretir
 */

class DefaultParameter @JvmOverloads constructor(val name: String = "", val lastName: String = "", val age: Int = 0)
