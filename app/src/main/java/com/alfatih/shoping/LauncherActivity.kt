package com.alfatih.shoping
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.alfatih.shoping.auth.UserFirebase
import com.alfatih.shoping.databinding.ActivityLauncherBinding
import com.alfatih.shoping.home.HomeActivity
import com.alfatih.shoping.main.WelcomeActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth

class LauncherActivity : AppCompatActivity(){
    private lateinit var launcherBinding: ActivityLauncherBinding
    private var userFirebase = UserFirebase()
    private val user = FirebaseAuth.getInstance(FirebaseApp.getInstance()).currentUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT < 17) {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        } else {
            // Hide the status bar.
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
            // Remember that you should never show the action bar if the
            // status bar is hidden, so hide that too if necessary.
            actionBar?.hide()
        }


        launcherBinding = DataBindingUtil.setContentView(this, R.layout.activity_launcher)


        if(user != null){
                startActivity(Intent(this,HomeActivity::class.java))
            }else{
            Handler().postDelayed(
                {
                startActivity(Intent(this,WelcomeActivity::class.java))
            },5000
        )}

        //userFirebase.checkCurrentUser(user,this, HomeActivity::class.java,this)
        //userFirebase.themeAndLogo()
    }

    override fun onStop() {
        super.onStop()
        this.finish()
    }

    companion object {

        private const val RC_SIGN_IN = 123
    }

}