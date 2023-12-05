package com.sviatkuzbyt.library.ui.other

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.sviatkuzbyt.library.data.other.CurrentUserManager
import com.sviatkuzbyt.library.ui.main.MainActivity
import com.sviatkuzbyt.library.ui.other.login.LoginActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            val currentUser = CurrentUserManager.getUser(application)
            withContext(Dispatchers.Main){
                if(currentUser != CurrentUserManager.NO_LOGIN)
                    openActivity(MainActivity::class.java)
                else
                    openActivity(LoginActivity::class.java)
            }
        }
    }

    private fun openActivity(activity: Class<*>){
        startActivity(Intent(this, activity))
        finish()
    }
}