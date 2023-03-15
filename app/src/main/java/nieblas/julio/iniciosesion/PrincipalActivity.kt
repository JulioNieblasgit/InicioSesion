package nieblas.julio.iniciosesion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class PrincipalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)


        val bundle = intent.extras
        var tv_nombre : TextView = findViewById(R.id.tv_nombre)
        var tv_email : TextView = findViewById(R.id.tv_email)
        var btn_cerrar : TextView = findViewById(R.id.btn_cerrar)
        // nuevo

        if (bundle != null){
            val name = bundle.getString("name")
            val email = bundle.getString("email")
            tv_nombre.setText(name)
            tv_email.setText(email)
        }

        btn_cerrar.setOnClickListener{
            finish()
        }

    }

    override fun onBackPressed() {

    }

}