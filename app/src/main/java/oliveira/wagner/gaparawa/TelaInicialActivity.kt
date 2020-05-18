package oliveira.wagner.gaparawa

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.login.*
import kotlinx.android.synthetic.main.activity_tela_inicial.*
import kotlinx.android.synthetic.main.toolbar.*

class TelaInicialActivity : DebugActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val context: Context get() = this
    private var produtos = listOf<Produtos>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_inicial)


        val args = intent.extras
        val nome = args?.getString("nome")

//        val numero = intent.getIntExtra("nome", 0)


        setSupportActionBar(toolbar)

        supportActionBar?.title = "Compras"

        //up navigation
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        configuraMenuLateral()


        recycleProdutos?.layoutManager = LinearLayoutManager(context)
        recycleProdutos?.itemAnimator = DefaultItemAnimator()
        recycleProdutos?.setHasFixedSize(true)
    }

    override fun onResume() {
        super.onResume()
        taskProdutos()
    }

    fun taskProdutos() {
        Thread {
            this.produtos = ProdutosService.getProdutos(context)
            runOnUiThread {
                recycleProdutos?.adapter = ProdutoAdapter(produtos) { onclickProduto(it) }
            }

        }.start()
    }

    fun onclickProduto(produto: Produtos) {
        val intent = Intent(context, ProdutosActivity::class.java)
        intent.putExtra("produto", produto)
        startActivity(intent)
    }

    fun onClickLogout(){
        val returnIntent = Intent();
        returnIntent.putExtra("result", "Saída do BrewerApp");
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu_main, menu)
        //vincular evento de buscar
        (menu?.findItem(R.id.action_buscar)?.actionView as SearchView?)?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String?): Boolean {
                //ação enquanto está digitando
                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                // ação quando terminou de buscar e enviou
                return false
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        // id do item clicado
        val id = item?.itemId
        //verifica o item clicado e mostra a msg toast na tela
        if (id == R.id.action_buscar) {
            Toast.makeText(context, "Botão de buscar", Toast.LENGTH_LONG).show()
        } else if (id == R.id.action_atualizar) {
            Toast.makeText(context, "Botão de atualizar", Toast.LENGTH_LONG).show()
        } else if (id == R.id.action_config) {
            Toast.makeText(context, "Botão de configurações", Toast.LENGTH_LONG).show()
        }
        else if (id == android.R.id.home) {
            finish()
        }

        return super.onOptionsItemSelected(item)
    }


    private fun configuraMenuLateral() {
        var toogle = ActionBarDrawerToggle(this,
            layoutMenuLateral,
            toolbar,
            R.string.nav_open,
            R.string.nav_close)
        layoutMenuLateral.addDrawerListener(toogle)
        toogle.syncState()

        menu_lateral.setNavigationItemSelectedListener (this) // pra esse menu_latera > vincula isso \/
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_produtos -> {
                Toast.makeText(this, "clicou em produtas", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_carrinho -> {
                Toast.makeText(this, "clicou em carrinho", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_localizacao -> {
                Toast.makeText(this, "clicou em localização", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_config -> {
                Toast.makeText(this, "clicou em config", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_fale_conosco -> {
                Toast.makeText(this, "clicou em fale conosco", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_logout -> {
               this.onClickLogout()
            }

        }
        layoutMenuLateral.closeDrawer(GravityCompat.START) //volta pro inicio após clicar em algum item
        return true
    }
}
