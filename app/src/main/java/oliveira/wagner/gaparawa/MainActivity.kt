package oliveira.wagner.gaparawa

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.login.*

class MainActivity : DebugActivity() {

    private val context: Context get() = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login) //conteudo da view

        imageLogin.setImageResource(R.drawable.imagem_login) // colocar imagem no imageLogin
        mensagemInicial.text = getString(R.string.str_msg_login)

        buttonLogin.setOnClickListener { onClickLogin() }
    }

    fun onClickLogin() {
        progressBar.visibility = View.VISIBLE
        //val intent = Intent(context, TelaInicialActivity::class.java)
        val valorUsuario = campoUsuario.text.toString()
        val valorSenha = campoSenha.text.toString()
        if(valorUsuario.equals("aluno")  && valorSenha.equals("1234") ){
            intent.putExtra("result", getString(R.string.login_OK))
            val intent = Intent(context, TelaInicialActivity::class.java)
            val params = Bundle()
            params.putString("nome", "wagner")

            intent.putExtras(params)
            startActivityForResult(intent, 1)
        }else {
            Toast.makeText(context, R.string.login_ERR, Toast.LENGTH_LONG).show()
        }
//        val intent = Intent(context, TelaInicialActivity::class.java)

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            val result = data?.getStringExtra("result")
            Toast.makeText(context, "$result", Toast.LENGTH_LONG).show()
        }
    }
}
