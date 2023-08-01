/**
 * Field lar bir sınıfın private memberlarıdır. Memory i kullanırlar.
 * Propery private fieldlara erişmeye izin veren getter setter fonksiyonlarıdır.
 * Aslında her şet bir propertydir.
 */

// val && var
/**
 * val ile tanımlama yapıldığı zaman arkaplanda sadece getter ı oluşturulur.
 * var ile tanımlama yapıldığı zaman arkaplanda getter ve setter ı oluşturulmuş oluyor. Bu durumda aslında
 * encapsulation ı oluşturur.
 */

/**
 * Encapsulation bir field ı private hale getirip o field ın değerininin alınmasının veya değiştirilmesinin
 * public fonksiyonlar aracılığıyla yapılmasıdır.
 */

class Person(val name: String, var lastName: String, val born: Int) {

    /**
     * Burada böyle bir tanımlama compile olmadan hata vermez. Getter ve setter fonksiyonlar val ve var durumuna
     * göre oluştuğu için get ve set isimli fonksiyonlar tanımlanamaz.
     */
    //fun getName() = name

    //val fullNameField = name + lastName
    val fullNameNotField: String get() = name + lastName

    /**
     * var olan bir property nin getter ve setter ı tanımlanırsa arka planda field oluşturmaz.
     */
    var info
        get() = "$name $lastName $born"
        set(value) {
            parsePersonInfo(value)
        }

    var gender: String = ""
        private set

    fun setGenderInternally(gender: String) {
        this.gender = gender
    }

    // Backing field olarak adlandırılıyor
    var height = 0
        set(value) {
            if (value > 0) {
                // height = value
                field = value
            }
        }

    /**
     * Özellikle Reactive yapılarda LiveData, Flow vs. gibi çok kullanılır. Sadece private olan güncellenir.
     * Public olana erişim sağlanır.
     * Backing property deniyor
     */
    private var _grades: IntArray? = null
        private set
    val grades: IntArray
        get() {
            if (_grades == null) {
                _grades = intArrayOf()
            }
            return _grades!!
        }

    fun setGrades(grades: IntArray) {
        this._grades = grades
    }

    fun setGradesNull() {
        _grades = null
    }
}

private fun parsePersonInfo(value: String): String {
    return ""
}

fun main() {
    val person = Person("Sevban", "", 1990)
    println(person.name)

    person.info = "dsadaksljdhjaskljda"

    person.setGenderInternally("male")

    person.height = -10

    println(person.height)

    person.height = 180

    println(person.height)

    println(person.grades.size)

    person.setGrades(intArrayOf(90, 60, 46))

    println(person.grades.size)

}