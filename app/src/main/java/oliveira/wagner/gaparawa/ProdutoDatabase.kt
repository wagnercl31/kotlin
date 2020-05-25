package oliveira.wagner.gaparawa


import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities=arrayOf(Produtos::class), version = 1)
abstract class ProdutoDatabase: RoomDatabase() {
    abstract fun produtosDAO(): ProdutosDAO
}