import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

interface ViewBinding

class BaseView

class HomeViewBinding : ViewBinding {

    companion object {
        fun bind(view: BaseView): HomeViewBinding = HomeViewBinding()
    }
}

class DetailViewBinding : ViewBinding {
    companion object {
        fun bind(view: BaseView): DetailViewBinding = DetailViewBinding()
    }
}


open class BaseSampleFragment : LifecycleOwner {

    private var lifecycleOwner: LifecycleOwner? = null

    fun onViewCreated() {
        lifecycleOwner = this
    }

    fun getLifecycleOwner() = lifecycleOwner!!

    fun requireView() = BaseView()

    override val lifecycle: Lifecycle
        get() = LifecycleRegister()
}

class SampleFragment : BaseSampleFragment() {
    val binding: HomeViewBinding by viewBinding(HomeViewBinding::bind)
}

class DetailFragment : BaseSampleFragment() {
    private val detailViewBinding: DetailViewBinding by viewBinding(DetailViewBinding::bind)
}

abstract class Lifecycle {
    abstract fun addObserver(lifecycleObserver: LifecycleObserver)
}

class LifecycleRegister : Lifecycle() {
    override fun addObserver(lifecycleObserver: LifecycleObserver) {
        // add observer
    }
}

interface LifecycleOwner {
    fun isViewCreated() = true

    val lifecycle: Lifecycle
}

interface LifecycleObserver {
    fun onDestroy(lifecycleOwner: LifecycleOwner) {}
}

fun <T : ViewBinding> BaseSampleFragment.viewBinding(factory: (BaseView) -> T): ReadOnlyProperty<BaseSampleFragment, T> =
    object : ReadOnlyProperty<BaseSampleFragment, T>, LifecycleObserver {

        private var binding: T? = null

        override fun getValue(thisRef: BaseSampleFragment, property: KProperty<*>): T {
            binding ?: factory(requireView()).also {
                if (getLifecycleOwner().isViewCreated()) {
                    getLifecycleOwner().lifecycle.addObserver(this)
                    binding = it
                }
            }
            return binding!!
        }

        override fun onDestroy(lifecycleOwner: LifecycleOwner) {
            binding = null
        }
    }