package oliveira.wagner.gaparawa


import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities=arrayOf(Produtos::class), version = 1)
abstract class ProdutosDatabase: RoomDatabase() {
    abstract fun produtosDAO(): ProdutosDAO
}