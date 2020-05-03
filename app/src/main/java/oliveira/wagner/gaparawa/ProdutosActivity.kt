package oliveira.wagner.gaparawa

import android.os.Bundle
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_produtos.*
import kotlinx.android.synthetic.main.toolbar.*

class ProdutosActivity : DebugActivity() {

    var produto: Produtos? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_produtos)

        //recuperar onjeto de Produto da Intent
        produto = intent.getSerializableExtra("produto") as? Produtos


        //configurar título com nome do produto e botão de voltar da toolbar
        //colocar toolbar
        setSupportActionBar(toolbar)

        //alterar título da ActionBar
        supportActionBar?.title = produto?.nome

        //up navigation
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //atualizar dados do carro
        nomeProduto.text = produto?.nome
        Picasso.with(this).load(produto?.foto).fit().into(imagemProduto,
            object: com.squareup.picasso.Callback{
            override fun onSuccess() { }

            override fun onError() {  }
        })
    }
}
