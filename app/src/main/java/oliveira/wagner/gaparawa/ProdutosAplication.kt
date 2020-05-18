package oliveira.wagner.gaparawa

import android.app.Application

class ProdutosAplication: Application(){

    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }

    companion object {
        private var appInstance: ProdutosAplication? = null

        fun getInstance(): ProdutosAplication {
            if (appInstance == null) {
                throw IllegalStateException("Configurar application no Android Manifest")
            }
            return appInstance!!
        }
    }

    override fun onTerminate() {
        super.onTerminate()
    }
}