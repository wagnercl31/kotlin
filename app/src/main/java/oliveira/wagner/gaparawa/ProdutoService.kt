package oliveira.wagner.gaparawa

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object ProdutoService {

    val host = "https://rafaelsdlima.pythonanywhere.com"
    val TAG = "WS_Gaparawa"

    fun getProdutos(context: Context): List<Produto> {
        var produtos: ArrayList<Produto>
        if (AndroidUtils.isInternetDisponivel()) {
            val url = "$host/produtos"
            val json = HttpHelper.get(url)
            produtos = parserJson(json)
            // salvar offline
            for (a in produtos) {
                                                                                                                                                                                                                                                                                                                                                            saveOffline(a)
            }
            Log.d(TAG, json)
            return produtos
        } else {
            val dao = DatabaseManager.getProdutoDAO()
            val produtos = dao.findAll()
            return produtos
        }

    }

    fun getProduto(context: Context, id: Long): Produto? {

        if (AndroidUtils.isInternetDisponivel()) {
            val url = "$host/produtos/${id}"
            val json = HttpHelper.get(url)

            return parserJson<Produto>(json)
        } else {
            val dao = DatabaseManager.getProdutoDAO()
            val produtos = dao.getById(id)
            return produtos
        }

    }

    fun save(produto: Produto): Response {
        if (AndroidUtils.isInternetDisponivel()) {
            val json = HttpHelper.post("$host/produtos", produto.toJson())
            return parserJson(json)
        }else {
            saveOffline(produto)
            return Response("OK", "Produtos salvo no dispositivo")
        }
    }

    private fun saveOffline(produto: Produto): Boolean {
        val dao = DatabaseManager.getProdutoDAO()

        if (! existeProdutos(produto)) {
            dao.insert(produto)
        }

        return true

    }

    private fun existeProdutos(produto: Produto): Boolean {
        val dao = DatabaseManager.getProdutoDAO()
        return dao.getById(produto.id) != null
    }

    fun delete(produto: Produto): Response {
        return if (AndroidUtils.isInternetDisponivel()) {
            val url = "$host/produtos/${produto.id}"
            val json = HttpHelper.delete(url)

            parserJson(json)
        } else {
            val dao = DatabaseManager.getProdutoDAO()
            dao.delete(produto)
            Response(status = "OK", msg = "Dados salvos localmente")
        }

    }

    inline fun <reified T> parserJson(json: String): T {
        val type = object : TypeToken<T>(){}.type
        return Gson().fromJson<T>(json, type)
    }
}

