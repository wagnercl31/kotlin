package oliveira.wagner.gaparawa

import android.app.Application

class GaparawaApplication: Application(){

    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }

    companion object {
        private var appInstance: GaparawaApplication? = null

        fun getInstance(): GaparawaApplication {
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