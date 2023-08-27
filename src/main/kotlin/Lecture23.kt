/**
 * Kotlinde toplama (+) çıkarma (-) çarpma (*) bölme(/) gibi hali hazırda operatörler bulunmaktadır. Bu operatörler
 * başka sınıflara davranış kazandırabilirler.
 */

/**
 * x1 + x2	        x1.plus(x2)
 * x1 – x2	        x1.minus(x2)
 * x1 * x2	        x1.times(x2)
 * x1/ x2	        x1.div(x2)
 * x1 % x2	        x1.rem(x2)
 * x1..x2	        x1.rangeTo(x2)
 * x1 in x2	        x2.contains(x1)
 * x1 !in x2	    !x2.contains(x1)
 * x[i]	            x.get(i)
 * x[i, j]	        x.get(i, j)
 * x[i] = b	        x.set(i, b)
 * x[i, j] = b	    x.set(i, j, b)
 * x()	            x.invoke()
 * x(i)	            x.invoke(i)
 * x(i, j)	        x.invoke(i, j)
 * +x1              x1.unaryPlus()
 * -x1              x1.unaryMinus()
 * x1 += x2	        x1.plusAssign(x2)
 * x1 -= x2	        x1.minusAssign(x2)
 * x1 *= x2	        x1.timesAssign(x2)
 * x1 /= x2	        x1.divAssign(x2)
 * x1 %= x2	        x1.remAssign(x2)
 */

data class Point(val x: Int, val y: Int) {

    operator fun invoke(): String {
        return "X:$x Y:$y"
    }

    operator fun minus(other: Point): Point {
        return Point(x - other.x, y - other.y)
    }

    operator fun times(other: Point): Point {
        return Point(x * other.x, y * other.y)
    }
}

operator fun Point.plus(other: Point): Point {
    return Point(x + other.x, y + other.y)
}

operator fun String.unaryMinus() = reversed()

class ShapeOperator {
    private val points = mutableListOf<Point>()

    operator fun Point.unaryPlus() {
        points.add(this)
    }

    operator fun get(index: Int) = points[index]

    operator fun set(index: Int, point: Point) {
        points[index] = point
    }
}

fun shape(init: ShapeOperator.() -> Unit): ShapeOperator {
    val shape = ShapeOperator()
    shape.init()
    return shape
}

fun main() {
    val shape = shape {
        +Point(1, 2)
        +Point(2, 3)
        +Point(3, 4)
    }

    println(shape[0])
    shape[1] = Point(3, 4)
    println(shape[1])


    /*val point1 = Point(1, 2)
    val point2 = Point(2, 3)
    val sumPoint = point1 + point2
    val minusPoint = point1 - point2
    val multiplicationPoint = point1 * point2
    println(sumPoint)
    println(minusPoint)
    println(multiplicationPoint)

    println(point1())

    val name = "Hello"

    println(-name)

    val extracted = "Hello" - 'e'
    println(extracted)*/
}

operator fun String.minus(char: Char): String {
    return dropLastWhile {
        it != char
    }
}


