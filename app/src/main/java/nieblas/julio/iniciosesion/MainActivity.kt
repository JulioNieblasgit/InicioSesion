package nieblas.julio.iniciosesion

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task

class MainActivity : AppCompatActivity() {
    lateinit var mGoogleSignInClient: GoogleSignInClient
    val RC_SIGN_IN= 123
    private val TAG ="MyActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

val  gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestEmail()
        .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        var sign_in_button : SignInButton = findViewById(R.id.sign_in_button)
        sign_in_button.setOnClickListener{
            val signInIntent = mGoogleSignInClient.signInIntent

            startActivityForResult(signInIntent, RC_SIGN_IN)

    }
}

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN){
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private  fun handleSignInResult(completeTask: Task<GoogleSignInAccount>){
    try {
        val account = completeTask.getResult(ApiException::class.java)

        updateUI(account)
    }catch (e: ApiException){
        // Log.w(TAG, "signInResult:failed code="+ e.statusCode)
        // Log.w(TAG, e.message.toString())
        updateUI(null)
        }
    }

    private fun updateUI(account: GoogleSignInAccount){
        if(account!= null){
            val intent = Intent(this, PrincipalActivity::class.java)
            intent.putExtra("name",account.displayName)
            intent.putExtra("email", account.email)
            startActivity(intent)
        }
    }
}