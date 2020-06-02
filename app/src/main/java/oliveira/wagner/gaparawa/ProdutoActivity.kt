package oliveira.wagner.gaparawa

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_produto.*
import kotlinx.android.synthetic.main.toolbar.*

class ProdutoActivity : DebugActivity() {

    private val context: Context get() = this
    var produto: Produto? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_produto)

        //recuperar onjeto de Produto da Intent
        if (intent.getSerializableExtra("produto") is Produto)
            produto = intent.getSerializableExtra("produto") as Produto

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
    // método sobrescrito para inflar o menu na Actionbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // infla o menu com os botões da ActionBar
        menuInflater.inflate(R.menu.menu_main_produto, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        // id do item clicado
        val id = item?.itemId
        // verificar qual item foi clicado
        // remover a disciplina no WS
        if  (id == R.id.action_remover) {
            // alerta para confirmar a remeção
            // só remove se houver confirmação positiva
            AlertDialog.Builder(this)
                .setTitle(R.string.app_name)
                .setMessage("Deseja excluir o produto")
                .setPositiveButton("Sim") {
                        dialog, which ->
                    dialog.dismiss()
                    taskExcluir()
                }.setNegativeButton("Não") {
                        dialog, which -> dialog.dismiss()
                }.create().show()
        }
        // botão up navigation
        else if (id == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun taskExcluir() {
        if (this.produto != null && this.produto is Produto) {
            // Thread para remover a produto
            Thread {
                ProdutoService.delete(this.produto as Produto)
                runOnUiThread {
                    // após remover, voltar para activity anterior
                    finish()
                }
            }.start()
        }
    }
}
