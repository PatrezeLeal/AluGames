import br.com.alura.alugames.modelo.Gamer

fun main(){
    val gamer1 = Gamer( "  ", "Jacque@email.com")
    println(gamer1)

    val gamer2 = Gamer("Jaque", "Jacque@email.com","19/19/1992", "jeniblo")
    println(gamer2)

    gamer1.let {
        it.dataNascimento = "18/09/2000"
        it.usuario="jaqueskywalker"

    }.also{
        println(gamer1.idInterno)
    }
    println(gamer1)
    gamer1.usuario="jacque"
    println(gamer1)
}