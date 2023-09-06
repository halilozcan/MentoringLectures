/**
 * Bazen bir sınıfı üretmek istediğimizde o sınıfa ait küçük bir değişiklik yaparak kullanmak isteriz. Bu tür
 * durumlarda sınıfı üretmek ve kullanmamıza yarayan yapılar object expressionslar ve object declarationslardır.
 */

/**
 * Object expressionslar anonymous sınıfların nesnelerini üretir. Aynı zamanda class declaration ı olmadan
 * sınıf tanımlamamızı sağlar. Bu sınıflar sadece tek kullanımlık sınıflar olarak geçer. Anonymous sınıfın
 * nesneleri anonymous(abstract) object olarak adlandırılır.
 */

// object expressionslar object anahtar kelimesi ile başlar.

val sum = object {
    val number1 = 1
    val number2 = 2

    override fun toString(): String {
        return "Sum is:${number1 + number2}"
    }
}

/**
 * Object declarations genelde singleton oluşturmak için kullanılır.
 * Singleton Pattern: tek bir nesne üretme ve onu kullanma
 */

object Database {

    /**
     * Double checked locking her zaman garanti sağlamaz. Volatile anahtar kelimesi derleyiciye bu değişkenin
     * asenkron çalışan threadler tarafından değiştirilebileceğini söyler.
     */
    @Volatile
    private var access: Access? = null

    /**
     * Bu fonksiyonlara javada erişmek için Database.INSTANCE.getConnection() yazılır ama INSTANCE olmadan Javada
     * kullanma amacıyla static hale getirmek için @JvmStatic annotation ı kullanılır
     */
    @JvmStatic
    fun getConnection(): Access {
        /**
         * Bu yapıya double checked locking denir.
         */
        if (access == null) {
            /**
             * Herhangi bir zamanda başka bir thread instance ı create ederken bir thread ilk checki geçebilir. Bundan
             * dolayı senkronize olacak şekilde başka bir null kontrol check i konulmalıdır. Burada connection null
             * ise, synchronized anahtar kelimesi burayı kilitler ve ikinci kontrol durumunda instance ın null
             * olduğunu garanti eder. Eğer nesne null ise nesne oluşturulur.
             */

            synchronized(Database::class.java) {
                if (access == null) {
                    access = Access()
                }
            }
        }

        return access!!
    }
}

class Access

interface BluetoothListener {
    fun onConnected()
    fun onLowBattery()
    fun onDisconnected()
}

class BluetoothService {
    private var bluetoothListener: BluetoothListener? = null

    fun setBluetoothListener(bluetoothListener: BluetoothListener?) {
        this.bluetoothListener = bluetoothListener
    }

    fun connect() {
        bluetoothListener?.onConnected()
    }

    fun disconnect() {
        bluetoothListener?.onDisconnected()
    }

    fun informLowBattery() {
        bluetoothListener?.onLowBattery()
    }
}

/**
 * Contract
 */
abstract class EventListener {
    abstract fun onClick()
    abstract fun onDoubleClick()
    abstract fun onTouch()
}

/**
 * data object şeklinde işaretleyebilirsiniz.
 */

/**
 * Kotlinde objectleri yazdırmak istediğiniz zaman MyObject@123 gibi bir çıktı üretirler. Bu sonuç nesnenin
 * adı ve hashcode unu barındırır. Eğer özel bir şekilde yazılması isteniyorsa toString() fonksiyonu override
 * edilmelidir. Ancak buna gerek kalmadan data ile işaretlenerek direkt olarak MyObject şeklinde çıktı üretilmesi
 * sağlanabilir.
 * Data objectlere özel olarak equals ve hashcode implementasyonları yazılamaz.
 * ancak copy ve componentN fonksiyonlarını üretmez
 */

sealed class ResponseSample {
    data object Downloading : ResponseSample()
    data class Success(val data: String) : ResponseSample()
    data class Error(val exception: Exception) : ResponseSample()
}

data object Error

class MyFragment {

    companion object : Creator {

        @JvmStatic
        fun newInstance() = MyFragment()

        @JvmStatic
        override fun createInstance(): MyFragment {
            return MyFragment()
        }
    }
}

interface Creator {
    fun createInstance(): MyFragment
}

/**
 * Object expressionslar kullanıldığı yerde hemen tanımlanırlar ve çalışırlar.
 * Object declarationslar lazy bir şekilde kendilerine erişim yapıldığı zaman initialize edilirler.
 * companion objectler tanımlandığı sınıf yüklendiğinde(kullanıldığında) initialize edilirler
 */


fun main() {

    // MyFragment.newInstance();
    // MyFragment.Companion.newInstance();

    MyFragment.newInstance()
    println(sum)
    Database.getConnection()

    val eventListener = object : EventListener() {
        override fun onClick() {
            TODO("Not yet implemented")
        }

        override fun onDoubleClick() {
            TODO("Not yet implemented")
        }

        override fun onTouch() {
            TODO("Not yet implemented")
        }
    }

    val deviceName = "AndroMeda"

    val bluetoothListener = object : BluetoothListener {
        override fun onConnected() {
            println("Connected. We are in at $deviceName")
        }

        override fun onLowBattery() {
            println("Fuckk! Low Battery! Please charge $deviceName")
        }

        override fun onDisconnected() {
            println("Pfff. We are kicked from $deviceName")
        }
    }

    val bluetoothService = BluetoothService().apply {
        setBluetoothListener(bluetoothListener)
    }

    bluetoothService.connect()
    bluetoothService.informLowBattery()
    bluetoothService.disconnect()

    bluetoothService.setBluetoothListener(null)

    println(Database)
}





