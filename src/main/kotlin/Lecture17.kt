/**
 * Bir sınıftan başka bir sınıf türetilmesi işlemine inheritance(kalıtım) denir. Kotlin de bütün sınıflar
 * implicit olarak Any i kalıtım alır.
 */

// Implicitly - üstü kapalı inherits Any -> explicit -> açık
class Test

// Kalıtılmaya açık (open)
/**
 * Kotlin tüm sınıflar default olarak finaldır. Bundan dolayı başlarına open anahtar kelimesinin (keyword) koyulması
 * gereklidir.
 */

open class Base(val number: Int)

// Türetilmiş
class Derived : Base(10) {

}

/**
 * Eğer base sınıfın bir parametresi varsa onu kalıtım alan sınıf işlem sırasında base sınıfa bu parametreyi
 * vermelidir
 */
open class BaseWithSecondaryConstructor() {
    constructor(number: Int) : this()
    constructor(number: Int, age: Int) : this()
}

class DerivedWithSecondaryConstructor : BaseWithSecondaryConstructor {
    constructor(number: Int) : super(number)
    constructor(number: Int, age: Int) : super(number, age)
}

/**
 * Super classlar (base class) (kalıtım alınan sınıf) child class (kalıtım alan-türetilen sınıf)
 * Super classlar kendisini kalıtım alan sınıfa özellik verebilmesi için propery ve fonksiyonların da open olması
 * gereklidir.
 */
open class Pen {
    open val brand: Int = 0
    open fun draw() {
        // repairPenIfNeeded()
        println("pen drawing")
    }

    private fun repairPenIfNeeded() {
        println("pen repairing")
    }

    fun changeColor() {
        println("pen change color")
    }
}

open class Pencil : Pen() {

    /**
     * Open olmayan bir classtakj open bir property nin kalıtıma herhangi bir etkisi yoktur. Yani kalıtım yapılamaz.
     */
    open val height = 15

    override val brand: Int = BRAND_PENCIL

    /**
     * Kalıtım alan sınıfa ait bir fonksiyonun kendisini kalıtım alan sınıfta override edilmesini engellemek
     * istiyorsanız final anahtar kelimesini koymanız gerekir.
     */
    final override fun draw() {
        super.draw()
        println("pencil drawing")
    }

    // function signature -> name + parameters + return type + visibility modifiers (private,internal,public)
    /**
     * Eğer kalıtım alınan sınıftaki fonksiyon open değilse kalıtım alan sınıf
     * aynı signature ile bu fonksiyonu oluşturamaz
     * fun changeColor() {
     *
     *     }
     */
}

class FaberCastelPencil : Pencil() {}

class BallPointPen : Pen() {

    override fun draw() {
        println("ball point pen drawing")
    }

    override val brand: Int = BRAND_BALL_POINT_PEN
}

open class BaseInitialization(val name: String) {
    init {
        println("init in base")
    }

    open val nameLength: Int = name.length.also {
        println("nameLength in base")
    }
}

class DerivedInitialization(name: String) : BaseInitialization(name.also { println("argument initialization") }) {
    init {
        println("init in derived")
    }

    override val nameLength: Int = super.nameLength.also {
        println("nameLength in derived")
    }
}

class SpecialPen : Pen() {

    override fun draw() {
        println("special pen drawing")
        val painter = Painter()
        painter.drawAndWrite()
    }

    fun doSomethingSpecial() {
        println("do something special")
    }

    inner class Painter {
        private fun write() {
            println("painter is writing")
        }

        fun drawAndWrite() {
            doSomethingSpecial()
            // Scope
            // this@SpecialPen
            this@SpecialPen.doSomethingSpecial()
            super@SpecialPen.draw()
            write()
        }
    }
}

fun main() {
    val specialPen = SpecialPen()
    specialPen.draw()
    //val pencil = Pencil()
    // pencil.draw()
    val derived = DerivedInitialization("Hello")
}


val BRAND_PENCIL = 1
val BRAND_BALL_POINT_PEN = 2

