package entities;

import java.util.Scanner;

import utils.Utils;
import utils.enums.Color;

/**
 * A classe `Jogador` representa um jogador no jogo. Ela possui métodos para interagir
 * com o tabuleiro do jogo e esconder pessoas.
 */
public class Jogador {
    private char simbolo; // O símbolo associado ao jogador
    private int zorrilhos; // O número de zorrilhos (pessoas escondidas) pelo jogador

    /**
     * Constrói um objeto `Jogador` com o símbolo especificado.
     *
     * @param simbolo O símbolo associado ao jogador.
     */
    public Jogador(char simbolo) {
        this.simbolo = simbolo;
    }

    /**
     * Obtém o símbolo associado ao jogador.
     *
     * @return O símbolo do jogador.
     */
    public char getSimbolo() {
        return simbolo;
    }

    /**
     * Obtém o número de zorrilhos (pessoas escondidas) pelo jogador.
     *
     * @return O número de zorrilhos escondidos pelo jogador.
     */
    public int getZorrilhos() {
        return zorrilhos;
    }

    /**
     * Incrementa a contagem de zorrilhos (pessoas escondidas) pelo jogador.
     */
    public void incrementarZorrilhos() {
        zorrilhos++;
    }

    /**
     * Permite ao jogador esconder pessoas no tabuleiro do jogo.
     *
     * @param jogador O símbolo associado ao jogador.
     * @param scanner O objeto scanner para entrada de usuário.
     * @param matriz  O tabuleiro do jogo representado por um objeto Matriz.
     */
    public void esconderPessoas(char jogador, Scanner scanner, Matriz matriz) {
        int linha, coluna;
        boolean erro = false;

        Matriz auxiliar = new Matriz();
        auxiliar.inicializarMatriz();
        auxiliar.exibirMatriz();

        do {
            for (int i = 0; i < matriz.getQuantidadePessoas(); i++) {
                for (int j = 0; j < matriz.getBlocoPorPessoa(); j++) {
                    Utils.printf(Color.YELLOW, "\nJogador %c escondendo pessoas.\n\n", jogador);
                    System.out.print("Escolha uma posição (linha coluna): ");
                    linha = scanner.nextInt();
                    coluna = scanner.nextInt();

                    try {
                        matriz.setPosicaoMatriz(linha, coluna, simbolo);
                        auxiliar.setPosicaoMatriz(linha, coluna, simbolo);
                        Utils.clearScreen();
                        auxiliar.exibirMatrizResposta();
                        erro = false;
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        erro = true;
                    }
                }
            }
        } while (erro);
    }
}
