package oliveira.wagner.gaparawa

import androidx.room.Room

object DatabaseManager {

    private var dbInstace: ProdutosDatabase

    init{
        val appContext = ProdutosAplication.getInstance().applicationContext
        dbInstace = Room.databaseBuilder(
            appContext, ProdutosDatabase::class.java, "gprw.sqlite"
        ).build()
    }

    fun getProdutosDAO(): ProdutosDAO {
        return dbInstace.produtosDAO()
    }
}