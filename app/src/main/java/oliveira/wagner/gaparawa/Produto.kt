package oliveira.wagner.gaparawa

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.io.Serializable

@Entity(tableName = "produto")
class Produto : Serializable {

    @PrimaryKey
    var id:Long = 0
    var nome:String = ""
    var ementa:String = ""
    var professor:String = ""
    var foto:String = ""

    override fun toString(): String {
        return "Produto(nome='$nome')"
    }

    fun toJson(): String {
        return GsonBuilder().create().toJson(this)
    }
}