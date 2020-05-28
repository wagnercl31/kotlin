package oliveira.wagner.gaparawa

import androidx.room.Room

object DatabaseManager {

    private var dbInstace: ProdutoDatabase

    init{
        val appContext = ProdutosAplication.getInstance().applicationContext
        dbInstace = Room.databaseBuilder(
            appContext, ProdutoDatabase::class.java, "gprw.sqlite"
        ).build()
    }

    fun getProdutosDAO(): ProdutosDAO {
        return dbInstace.produtosDAO()
    }
}