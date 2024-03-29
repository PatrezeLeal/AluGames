package br.com.alura.alugames.principal

import br.com.alura.alugames.modelo.Gamer
import br.com.alura.alugames.modelo.Jogo
import br.com.alura.alugames.servicos.ConsumoApi
import java.util.*

fun main() {

    val leitura = Scanner(System.`in`)

    val gamer = Gamer.criarGamer(leitura)
    println("Cadastro concluído com sucesso")
    println(gamer)

    do {
        println("Digite um código de jogo para buscar:")

        val busca = leitura.nextLine()

        val buscaApi = ConsumoApi()
        val informacaoJogo = buscaApi.buscaJogo(busca)


        var meuJogo: Jogo? = null

        val resultado = runCatching {
            meuJogo = informacaoJogo?.info?.let { Jogo(it.title, informacaoJogo.info.thumb) }
        }

        resultado.onFailure {
            println("br.com.alura.alugames.modelo.Jogo inexistente. Tente outro id.")
        }

        resultado.onSuccess {

            if (informacaoJogo != null) { // Verifica se o jogo foi encontrado
                println("Deseja inserir uma descrição personalizada? S/N")
                val opcao = leitura.nextLine()

                if (opcao.equals("s", true)) {
                    println("Insira a descrição personalizada para o jogo:")
                    val descricaoPersonalizada = leitura.nextLine()
                    meuJogo?.descricao = descricaoPersonalizada
                } else {
                    meuJogo?.descricao = meuJogo?.titulo
                }

                gamer.jogosBuscados.add(meuJogo)
            } else {
                println("Jogo não encontrado.")
            }
        }

        println("Deseja buscar um novo jogo? S/N")
        val resposta = leitura.nextLine()

    } while (resposta.equals("S", true))

    print("Jogos buscados:")
    println(gamer.jogosBuscados)

    println("\n Jogos ordenados por titulo")
    gamer.jogosBuscados.sortBy {
        it?.titulo
    }

    gamer.jogosBuscados.forEach {
        println("Titulo: " + it?.titulo)
    }
val jogosFiltrados = gamer.jogosBuscados.forEach {
    it?.titulo?.contains("Batman", true) ?: false
}
    println("\nJogos filtrados:")
    println(jogosFiltrados)

    println("Deseja excluir algum jogo da lista original? S/N")
    val opcao = leitura.nextLine()
    if(opcao.equals("S", true)){
        println(gamer.jogosBuscados)
        println("\nInforme a posição do jogo que deseja excluir: ")
        val posicao = leitura.nextInt()
        gamer.jogosBuscados.removeAt(posicao)
    }

    println("\n Lista atualizada:")
    println(gamer.jogosBuscados)

    println("Busca finalizada com sucesso.")
}