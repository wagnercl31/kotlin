package oliveira.wagner.gaparawa

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object ProdutosService {
    val host = "https://rafaelsdlima.pythonanywhere.com"
    val TAG = "WS_LMSApp"

    fun getProdutos (context: Context): List<Produtos> {
        var produtos = ArrayList<Produtos>()
        if (AndroidUtils.isInternetDisponivel()) {
            val url = "$host/produtos"
            val json = HttpHelper.get(url)
            produtos = parserJson(json)
            // salvar offline
            for (d in produtos) {
                saveOffline(d)
            }
            Log.d(TAG, json)
            return produtos
        } else {
            val dao = DatabaseManager.getProdutosDAO()
            val produtos = dao.findAll()
            return produtos
        }

    }

    fun getProdutos (context: Context, id: Long): Produtos? {

        if (AndroidUtils.isInternetDisponivel()) {
            val url = "$host/produtos/${id}"
            val json = HttpHelper.get(url)
            val produtos = parserJson<Produtos>(json)

            return produtos
        } else {
            val dao = DatabaseManager.getProdutosDAO()
            val produtos = dao.getById(id)
            return produtos
        }

    }

    fun save(produtos: Produtos): Response {
        if (AndroidUtils.isInternetDisponivel()) {
            val json = HttpHelper.post("$host/produtos", produtos.toJson())
            return parserJson(json)
        }
        else {
            saveOffline(produtos)
            return Response("OK", "Produto salvo no dispositivo")
        }
    }

    fun saveOffline(produtos: Produtos) : Boolean {
        val dao = DatabaseManager.getProdutosDAO()

        if (! existeProduto(produtos)) {
            dao.insert(produtos)
        }

        return true

    }

    fun existeProduto(produtos: Produtos): Boolean {
        val dao = DatabaseManager.getProdutosDAO()
        return dao.getById(produtos.id) != null
    }

    fun delete(produtos: Produtos): Response {
        if (AndroidUtils.isInternetDisponivel()) {
            val url = "$host/produtos/${produtos.id}"
            val json = HttpHelper.delete(url)

            return parserJson(json)
        } else {
            val dao = DatabaseManager.getProdutosDAO()
            dao.delete(produtos)
            return Response(status = "OK", msg = "Dados salvos localmente")
        }

    }

    inline fun <reified T> parserJson(json: String): T {
        val type = object : TypeToken<T>(){}.type
        return Gson().fromJson<T>(json, type)
    }
}