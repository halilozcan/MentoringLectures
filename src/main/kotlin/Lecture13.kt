/**
 * null durumu değerin gelmediği durumlarda oluşur.
 * Null, bir propertynin veya bir fonksiyon sonucunun ya da servisten gelen cevaplarda fieldların bulunmaması vb.
 * durumlarda oluşur. Değerin bulunmadığı durumlarda property e güvenli bir şekilde erişim olmazsa
 * npe (null pointer exception) ile karşılaşılır.
 */

fun main() {
    /**
     * nullable olmayan bir değişkene null değeri atanamaz
     * val immutable - var mutable
     */

    val a = 10

    // Bir property yada değişkene null ataması yapılır tipi belirtilmez o değişken ya da property Nothing olur
    val variable = null

    // List<Nothing>
    val anotherVariable = listOf(null)

    var info: String? = ""

    val anotherInfo = "Hello"

    // som operations
    info = null

    //println(info)

    val textLength = if (info != null) info.length else -1

    val result: String? = if (info != null && info.length > 0) {
        info
    } else {
        null
    }

    // safety ?. operatörü kullanarak üyelere erişim sağlanabilir
    println(result?.length)
    anotherInfo.length.plus(2)

    //println(textLength)

    val sum = result?.length?.plus(2)

    val nullablePerson: NullablePerson? = null

    val nameLength = nullablePerson?.name?.length

    println(nameLength)

    // atamanın sol tarafında chained call null veriyorsa o ifade es geçilir. sağ tarafındaki atama değerlendirilmez
    // ve yapılmaz.
    nullablePerson?.name = "NewName"

    println(nullablePerson?.name)

    // val textLength = if (info != null) info.length else -1
    // ?: elvis operatörü

    val newTextLength = info?.length ?: -1

    /**
     * compiler a bir nullable olan property nin kesinlikle null dönmediğini anlatmak istenirse
     * !! (not-null assertion operator) kullanılır. Eğer property null ise npe (null pointer exception alınır)
     */
    nullablePerson!!.name
}

fun getPersonInfo(person: NullablePerson?): String? {
    val name = person?.name ?: return null
    val gender = person?.gender ?: throw Exception("failed")
    return "user name:$name gender:$gender"
}


class NullablePerson(var name: String? = null, var gender: String? = null)