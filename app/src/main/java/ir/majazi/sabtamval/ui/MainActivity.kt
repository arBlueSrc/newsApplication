package ir.majazi.sabtamval.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.example.global.utils.Save
import dagger.hilt.android.AndroidEntryPoint
import ir.majazi.sabtamval.R
import ir.majazi.sabtamval.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

     private lateinit var binding: ActivityMainBinding
     private var isLogin :String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }

    override fun onStart() {
        super.onStart()
        isLogin = Save.fetch(applicationContext,"password").toString()
        if(isLogin.equals("")){
            Navigation.findNavController(this, R.id.fragmentContainerView).navigate(R.id.loginFragment)
        }else{
            Navigation.findNavController(this,R.id.fragmentContainerView).navigate(R.id.scannerFragment)
        }
    }
}