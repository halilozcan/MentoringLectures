import kotlin.properties.Delegates
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Propertylerin her zaman manuel olarak implementasyonunun yapılmasının istenmediği durumlarda delegation yapılır ve
 * böylece sadece bir kere tanımlanmış olurlar. Daha sonra delegation ile beraber tekrar kullanılabilirler
 */

/**
 * Delegation by anahtar kelimesi ile yapılır. Proprty den değer alınması ve değer atanması getValue() ve setValue()
 * fonksiyonlarına verilir. Property delegation işleminde interface implemente etmeye gerek yoktur ancak getValue()
 * veya setValue() fonksiyonları tanımlanmak zorundadır.
 */

class DelegationSample {
    var delegated: String by Delegate()
}

/**
 * Eğer delegation da val kullanılırsa sadece getValue() fonksiyonunu yazmak yeterlidir ancak var ile kullanılacaksa
 * o zaman setValue() fonksiyonu da yazılmalıdır
 */
class Delegate {
    /**
     * thisRef objenin kendisi
     * property parametresi ise property e ait bilgileri tutar
     */
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return "$thisRef, thank you for delegating ${property.name} to me"
    }

    /**
     * value -> atanan değer
     */
    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("$value has been assigned to ${property.name} in $thisRef")
    }
}

/**
 * Lazy Delegation
 * İlk çağırmada property nin değeri hesaplanır. Daha sonraki çağrımlar ilk olarak hesaplanan değeri döndürür.
 * Değerlendirme işlemi varsayılan olarak synchronized dır. Yani değer sadece bir thread de hesaplanır ancak
 * bütün threadler aynı değeri görürler. Eğer initialization işleminde multiple threading erişilmesi istenmiyor
 * ise lazy fonksiyonuna LazyThreadSafetyMode.PUBLICATION gönderilebilir
 */

val value: String by lazy {
    println("First time calling")
    "Hello"
}

class PersonDelegate {
    var name: String by Delegates.observable("Initial") { property, oldValue, newValue ->
        println("observable -> old:$oldValue new:$newValue")
    }

    var lastName: String by Delegates.vetoable("Initial") { property, oldValue, newValue ->
        println("vetoable -> old:$oldValue new:$newValue")
        return@vetoable newValue.length >= 5
    }
}

/**
 * Json parsing vb. işlemleri kolaylaştırmak için map delegation kullanılabilir.
 * Sadece val değil var propertylerde de kullanılabilir
 */
class UserMapDelegate(val map: Map<String, Any?>) {
    val name: String by map
    val age: Int by map
}

/**
 * Bir property başka bir property e delegate edilebilir.
 * Aşağıdaki örnekte deprecated property i silmeden eski sürümleri de destekleyerek geliştirme ve yazılımcıyı
 * notify etme amacıyla kullanım vardır.
 */
class DelegateAnotherProperty {
    var privacyPermission: String = ""

    @Deprecated("Use privacy permission instead", ReplaceWith("privacyPermission"))
    var permission: String by this::privacyPermission
}

class UserDelegate

/**
 * İki tane interface var
 * ReadWriteProperty
 * ReadOnlyProperty
 */

fun userDelegation(userDelegate: UserDelegate = UserDelegate()): ReadWriteProperty<Any?, UserDelegate> =
    object : ReadWriteProperty<Any?, UserDelegate> {
        var currentValue = userDelegate
        override fun getValue(thisRef: Any?, property: KProperty<*>): UserDelegate {
            return currentValue
        }

        override fun setValue(thisRef: Any?, property: KProperty<*>, value: UserDelegate) {
            currentValue = value
        }
    }

val userDelegateVal by userDelegation()
var userDelegateVar by userDelegation()

/**
 * lateinit
 * Kotlin de bir değişkene tanımlama anında değer atanması yapılmak istenmezse ve daha sonradan değer ataması yapılmak
 * isteniyorsa lateinit ve var anahtar kelimeleri ile beraber property tanımlaması yapılır. lateinit anahtar kelimesi
 * primitive tiplerde kullanılmaz. Initialize edilmesinin kontrolü reflection ile beraber sınıf referansı alınarak
 * yapılır
 */

class LateInitProperty {
    private lateinit var info: String

    fun setInfo(info: String) {
        this.info = info
    }

    fun getInfo(): String? {
        return if (this::info.isInitialized) {
            info
        } else {
            null
        }
    }
}

/**
 * Primitive bir tipin tanımlanması yapılırken ilk değer atanması yapılmak istenmediği durumlarda lateinit kullanılamaz.
 * Bundan dolayı bu tip genellikle nullable hale getirilir. Daha sonra her yerde null safety ile kontrol yapılmaya
 * çalışılır. Bunu yapmak yerine notNull() delegation ı kullanabilir.
 */
var notNull: Int by Delegates.notNull()



fun main() {
    val delegationSample = DelegationSample()
    println(delegationSample.delegated)
    delegationSample.delegated = "Hello"

    println(value)
    println(value)

    val personDelegate = PersonDelegate()
    personDelegate.name = "Hello"
    personDelegate.name = "Halil"

    personDelegate.lastName = "Sevban"
    personDelegate.lastName = "Abc"
    println("vetoable -> last value:${personDelegate.lastName}")

    val userMapDelegate = UserMapDelegate(mapOf("name" to "Halil", "age" to 25))

    println(userMapDelegate.name)
    println(userMapDelegate.age)

    val delegateAnotherProperty = DelegateAnotherProperty()
    delegateAnotherProperty.permission = "Camera"
    println(delegateAnotherProperty.privacyPermission)

    notNull = 15
    println(notNull)
}