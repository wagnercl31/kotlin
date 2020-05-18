package oliveira.wagner.gaparawa;

data class Response (val status: String, val msg: String){
    fun isOk(): Boolean {
        return "Ok".equals(status, ignoreCase = true)
    }
}