class Jogo (val titulo:String, var capa:String ){
    val descricao = ""
    override fun toString(): String {
        return "Meu jogo:\n" +
                "Título: $titulo\n" +
                "Capa: $capa\n" +
                "Descricao: $descricao"
    }


}