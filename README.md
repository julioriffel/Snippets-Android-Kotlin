#Trechos de Código Kotlin para Android

## Sumario
1. [Datablind](#datablind)
2. [Permissoes](#permissoes)
3. [Codigo de Barras](#CodigodeBarras)

 
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

### Permissoes
Em `builg.gradle` adicionar:
```
dependencies {
...
    implementation 'com.vmadalin:easypermissions-ktx:1.0.0'
}
```

Em `AndroidManifest.xml` adicionar:
```
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.exemplo.exemplo">

    <uses-permission android:name="android.permission.CAMERA" />
    <uses-permission android:name="android.permission.FLASHLIGHT" />
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
    <uses-permission android:name="android.permission.READ_CONTACTS" />
    <uses-permission android:name="android.permission.READ_SMS" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />

    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher" ...
```

[codigo Activity](app/src/main/java/com/julioriffel/snippets_android_kotlin/ui/PermissoesActivity.kt)

### Codido de Barras
```
    implementation('me.dm7.barcodescanner:zxing:1.9.8') {
        exclude module: 'support-v4'
    }
```
[codigo Activity](app/src/main/java/com/julioriffel/snippets_android_kotlin/ui/LerCodigoBarrasActivity.kt)
Fonte
[Leitor de Códigos no Android com Barcode Scanner API - ZXing Vinícius Thiengo](https://www.thiengo.com.br/leitor-de-codigos-no-android-com-barcode-scanner-api-zxing)
