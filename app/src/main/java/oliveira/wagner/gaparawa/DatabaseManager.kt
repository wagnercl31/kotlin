package oliveira.wagner.gaparawa

import androidx.room.Room

object DatabaseManager {

    private var dbInstace: GaparawaDatabase

    init{
        val appContext = GaparawaApplication.getInstance().applicationContext
        dbInstace = Room.databaseBuilder(
            appContext, GaparawaDatabase::class.java, "gprw.sqlite"
        ).build()
    }

    fun getProdutoDAO(): ProdutoDAO {
        return dbInstace.produtoDAO()
    }
}