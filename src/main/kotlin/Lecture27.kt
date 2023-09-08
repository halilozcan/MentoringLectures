/**
 * Delegation bir nesnenin işini başka bir nesneye aktarmaktır. Örneğin sınıf B  nin A ya verilerek
 * A sınıfında kendisine ait bir şey çağırmasının yetkisinin verilmesine delegation denir.
 */

/**
 * Delegation explicit (açık) ve implicit(kapalı) olmak üzere ikiye ayrılır.
 */

/**
 * Explicit Delegation
 */

interface Destroyer {
    fun destroy()
}

class SpecialRemover : Destroyer {
    override fun destroy() {
        println("SpecialRemover.destroy()")
    }
}

open class UserView {
    open fun show() {
        println("View.show()")
    }
}

class CustomView : UserView() {
    override fun show() {
        println("CustomView.show()")
    }
}

class Screen(private val view: UserView, private val destroyer: Destroyer) {

    fun show() {
        view.show()
    }

    fun destroy() {
        destroyer.destroy()
    }
}

/**
 * Implicit delegation (by anahtar kelimesi ile delegate ediyoruz)
 * Kotlin delegation işlemininn implicit olarak yapılmasını sağlar. Class Delegation ve Delegated Properties
 * feature larını bize sunar
 */

interface Nameable {
    var name: String
}

class Halil : Nameable {
    override var name = "Halil"
}

interface Runner {
    fun run()
}

class LongDistanceRunner : Runner {
    override fun run() {
        println("long distance runner run()")
    }
}

class PersonDelegation(name: Nameable, runner: Runner) : Nameable by name, Runner by runner

interface Printer {
    fun printText()
}

class DesktopPrinter(private val text: String) : Printer {
    override fun printText() {
        println(text)
    }
}

class User(printer: Printer) : Printer by printer

fun main() {
    val view = UserView()
    val customView = CustomView()
    val specialRemover = SpecialRemover()
    val screen = Screen(customView, specialRemover)
    screen.show()
    screen.destroy()

    val person = PersonDelegation(Halil(), LongDistanceRunner())
    println(person.name)
    person.run()

    val desktopPrinter = DesktopPrinter("The text will be printed")
    val user = User(desktopPrinter)
    user.printText()
}