#Trechos de Código Kotlin para Android

## Sumario
1. [Datablind](#datablind)

 
### Datablind
(Fonte)[https://developer.android.com/topic/libraries/data-binding]

Em `builg.gradle` adicionar:
```
android{ 
...
    buildFeatures {
        viewBinding = true
    }
}
```
na Activity:
```
class MainActivity : AppCompatActivity(){

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.tvNome.setText("Nome")
    }
```