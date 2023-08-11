/**
 * Sınıf içerisindeki sınıf tanımlanmasına nested class denir.
 * Nested class lar inner ile işaretlenmediği sürece static classlardır.
 * Bundan dolayı üst sınıfın üyelerine erişim sağlayamazlar. Direkt olarak . ile erişilebilirler.
 */

/**
 * inner ile işaretlenen nested class lar üst sınıfın(ların) üyelerine erişebilir.
 * inner class ile işaretlenmiş bir sınıfın içerisine nested class (static) yani inner olmayan bir sınıf
 * tanımlanamaz ancak inner sınıf tanımlanabilir.
 */

/**
 * Normalde Java da aynı isimle nested class tanımlanamazken Kotlin de tanımlanabilir.
 */

class Outer {
    val firstName: String = ""

    inner class Outer {
        init {
            firstName
        }

        fun getFirstName() = firstName

        inner class Outer {
            init {
                firstName
            }

            fun getFuckingFirstName(): String = firstName
        }
    }
}

fun main() {
    // static access
    // Outer.Nested()
    // Outer().Nested().FuckingNested().firstName
    val outer = Outer()
    val innerOuter = outer.Outer()
}