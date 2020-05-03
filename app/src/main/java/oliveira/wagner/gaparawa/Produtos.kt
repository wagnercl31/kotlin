package oliveira.wagner.gaparawa

import java.io.Serializable

class Produtos: Serializable{
    var id: Long = 0
    var nome: String = ""
    var categoria: String = ""
    var foto: String = ""

    override fun toString(): String {
        return "Produtos(nome='$nome')"
    }
}