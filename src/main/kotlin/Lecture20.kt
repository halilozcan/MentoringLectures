/**
 * Enum classlar durum tiplerini veya varyasyonlarını handle etmek için kullanılan yapılar. Her bir durum için
 * bir sabit oluştururarak kullanım yapılır.
 */

/**
 * Enum classlar yapılandırıcılarından bir property bulundururlarsa bütün sabitler de aynı parametreye sahip olmak
 * zorundadır.
 */

/**
 * Enum classlar sınıfları kalıtım olarak alamazlar ancak interfaceleri implemente edebilirler. Implemente edilen
 * interface in fonksiyonları ya bütün sabitlerde ya da enum class ın içerisinde override edilmelidir.
 */

/**
 * Enum sabitleri kendi var olan anonymous fonksiyonları override ederek kendileri fonksiyon tanımlayabilir
 */

/**
 * Enum classlar inherit edilemezler.
 */

/**
 * Enumlar ikiye ayrılır. Non-Ordinal - Ordinal
 */

// non-ordinal
enum class UserType {
    ADMIN,
    NORMAL,
    MANAGER,
    OTHER
}

// ordinal
enum class WeekDay {
    SUNDAY,
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY
}

enum class Side(var takenDistance: Int) : Move, Direction {
    LEFT(0) {
        override fun getOpposite() = RIGHT
        override fun walk(distance: Int) {
            takenDistance += distance
        }

        override fun toString(): String {
            return "Side is left: walked distance:$takenDistance"
        }
    },

    RIGHT(0) {
        override fun getOpposite() = LEFT
        override fun walk(distance: Int) {
            takenDistance += distance
        }
    },

    UP(0) {
        override fun getOpposite() = DOWN
        override fun walk(distance: Int) {
            takenDistance += distance
        }
    },

    DOWN(0) {
        override fun getOpposite() = UP
        override fun walk(distance: Int) {
            takenDistance += distance
        }
    };

    override fun goto(distance: Int) {
        walk(distance)
    }

    abstract fun getOpposite(): Side
}

interface Move {
    fun walk(distance: Int)
}

interface Direction {
    fun goto(distance: Int)
}

fun main() {
    println(Side.LEFT)

    val returnSide = "UP"

    val side = Side.valueOf(returnSide)
    println(side)

    println(side.ordinal)

    Side.values().forEach {

    }
}