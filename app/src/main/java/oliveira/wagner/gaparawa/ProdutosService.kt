package oliveira.wagner.gaparawa

import android.content.Context

object ProdutosService {

    fun getProdutos(context: Context): List<Produtos> {
        val produtos = mutableListOf<Produtos>()
            val v = Produtos()
            v.nome = "Verduras"
            v.categoria = "1"
            v.foto = "https://static.portaldacidade.com/unsafe/1150x767/https://s3.amazonaws.com/painel-do-franqueado/img/news/2017-11/sacolao-tera-produtos-naturais-por-preco-abaixo-do-mercado-em-umuarama-5a1098b122f2e.jpg"
            produtos.add(v)

            val f = Produtos()
            f.nome = "Frutas"
            f.categoria = "2"
            f.foto = "https://s2.glbimg.com/7inpIUNcR9cTFdKl4Ib-lEZBToM=/0x0:724x483/984x0/smart/filters:strip_icc()/s.glbimg.com/es/ge/f/original/2019/05/15/frutas_variadas_nao_engordam.jpg"
            produtos.add(f)

            val l = Produtos()
            l.nome = "Legumes"
            l.categoria = "3"
            l.foto = "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQhMVkW8js6ob8BlI_6oSoH5jIaM9auy5Yx3cgrNkDYYH4ckv--&usqp=CAU"
            produtos.add(l)

        return produtos
    }
    fun getLegumes(context: Context): List<Produtos> {
        val produtos = mutableListOf<Produtos>()
        for (i in 1..10) {
            val v = Produtos()
            v.nome = "Legumes $i"
            v.categoria = "1 $i"
            v.foto =
                "https://static.portaldacidade.com/unsafe/1150x767/https://s3.amazonaws.com/painel-do-franqueado/img/news/2017-11/sacolao-tera-produtos-naturais-por-preco-abaixo-do-mercado-em-umuarama-5a1098b122f2e.jpg"
            produtos.add(v)
        }


        return produtos
    }



}
