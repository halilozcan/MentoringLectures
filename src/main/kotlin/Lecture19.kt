/**
 * Abstract classlar initiate edilemezler sadece kalıtım alınabilirler. Abstract classlar hem abstract(bodysiz) fonksiyonları
 * hem de body li fonksiyonları içerebilir.
 *
 * Kendisini kalıtım alan sınıflar abstract olan bütün fonksiyonları override etmek zorundadırlar.
 */

/**
 * Nesnesi üretilemez.
 * Bütün propertyler ve üye fonksiyonlar default olarak abstract değildirler.
 * Eğer bu fonksiyonlar override edilmek istenirse abstract sınıfta open olarak olarak işaretlenmelidir.
 * Eğer fonksiyon abstract olarak işaretliyse open anahtar kelimesine gerek yoktur. Varsayılan olarak open dırlar
 */

/**
 * Abstract class lar template sınıftırlar. Bir taslak gibi davranırlar. Diğer sınıflarla beraber
 * bir "is-a" ilişkisi oluştururlar
 */

abstract class AbstractClass {
    abstract fun printSomething()
}

open class DerivedFromAbstract : AbstractClass() {
    override fun printSomething() {

    }
}

class FuckingDerivedFromDerived : DerivedFromAbstract() {
    override fun printSomething() {
        super.printSomething()
    }
}

abstract class Employee(val name: String, val experience: Int, open val dateOfBirth: Int) {

    abstract fun getDateOfBirthFormatted(): String

    fun printDetails() {
        println("Name:$name")
        println("Experience:$experience")
        println("Employee birthday message:${getDateOfBirthFormatted()}")
    }
}

class SoftwareEngineer(name: String, experience: Int, override val dateOfBirth: Int) :
    Employee(name, experience, dateOfBirth) {

    override fun getDateOfBirthFormatted(): String {
        return "println($dateOfBirth)"
    }
}

class TestEngineer(name: String, experience: Int, override val dateOfBirth: Int) :
    Employee(name, experience, dateOfBirth) {

    override fun getDateOfBirthFormatted(): String {
        return "testing v1:$dateOfBirth"
    }
}

/**
 * Abstract classlar interfacelerin bütün implementasyonlarının override edilmesi işlemini kendine alarak
 * kod fazlalığını engeller
 */

interface AnimationListener {
    fun onStart()
    fun onResume()
    fun onFinish()
}

abstract class AnimationContract : AnimationListener {
    override fun onStart() {}
    override fun onResume() {}
    override fun onFinish() {}
}

abstract class Shape {
    abstract fun draw()
    abstract fun changeColor(newColor: Int)

    fun print() {
        println("Shape Print")
        draw()
    }

    fun updateColor(newColor: Int) {
        println("Shape Change Color")
        changeColor(newColor)
    }
}

class Circle : Shape() {

    private var drawColor: Int = 0

    override fun draw() {
        println("circle draw")
    }

    override fun changeColor(newColor: Int) {
        println("change color")
        drawColor = newColor
    }
}


fun main() {
    val softwareEngineer = SoftwareEngineer("Halil", 6, 1994)
    // softwareEngineer.printDetails()

    val listener = object : AnimationListener {
        override fun onStart() {
            TODO("Not yet implemented")
        }

        override fun onResume() {
            TODO("Not yet implemented")
        }

        override fun onFinish() {
            TODO("Not yet implemented")
        }
    }

    val animationListener = object : AnimationContract() {
        override fun onFinish() {
        }
    }

    val circle = Circle()
    circle.print()

    val circle2: Shape = Circle()
    circle2.updateColor(10)
    printShape(circle2)
}

abstract class Fragment {
    abstract fun onCreateView(): View
    abstract fun onViewCreated()
    abstract fun onCreate()
}

abstract class BaseFragment : Fragment() {

    abstract fun initArguments()
    abstract fun getLayoutResId(): Int
    abstract fun observeUi()

    override fun onCreate() {
        initArguments()
    }

    override fun onCreateView(): View {
        return View(getLayoutResId())
    }

    override fun onViewCreated() {
        observeUi()
    }
}

class HomeFragment : BaseFragment() {

    override fun initArguments() {

    }

    override fun getLayoutResId() = 1

    override fun observeUi() {

    }
}

// @ColorRes
// @DrawableRes
// @String
// @LayoutRes

// R.drawable.ic_close
// R.color.color_black

fun setImage(id: Int) {
    // imageView.setImageResourceId(id)
}

data class View(private val layoutResId: Int)

private fun printShape(shape: Shape) {
    shape.print()
}