package oliveira.wagner.gaparawa

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities=arrayOf(Produto::class), version = 1)
abstract class GaparawaDatabase: RoomDatabase() {
    abstract fun produtoDAO(): ProdutoDAO
}