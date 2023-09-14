package entities;

import java.util.Scanner;
import utils.Utils;
import utils.enums.Color;

/**
 * A classe `Jogo` representa a lógica principal do jogo e o controle do fluxo.
 */
public class Jogo {
    private Matriz matriz;
    private Jogador[] jogadores = new Jogador[2];

    /**
     * Constrói um objeto `Jogo`, inicializa o tabuleiro do jogo (Matriz) e cria dois jogadores.
     */
    public Jogo() {
        matriz = new Matriz();
        jogadores[0] = new Jogador('1');
        jogadores[1] = new Jogador('2');
    }

    /**
     * Obtém o jogador no índice especificado (1 ou 2).
     *
     * @param indice O índice do jogador (1 ou 2).
     * @return O objeto do jogador.
     * @throws IllegalArgumentException se o índice for inválido.
     */
    public Jogador getJogador(int indice) {
        if (indice < 1 || indice > 2) {
            throw new IllegalArgumentException("Índice inválido");
        }
        return jogadores[indice - 1];
    }

    /**
     * Inicia o jogo, controla os turnos dos jogadores e exibe o vencedor.
     */
    public void iniciarJogo() {
        int opcaoMenu, jogadorAtual = 2;
        boolean temErro = false, primeiraRodada = true;
        Jogador vencedor = null;

        Utils.clearScreen();
        matriz.inicializarMatriz();

        try (Scanner input = new Scanner(System.in)) {
            getJogador(1).esconderPessoas(getJogador(1).getSimbolo(), input, matriz);
            Utils.clearScreen();
            getJogador(2).esconderPessoas(getJogador(2).getSimbolo(), input, matriz);

            do {
                Utils.clearScreen();
                matriz.exibirMatriz();

                String mensagem = primeiraRodada ? "\nJogador %c começa o jogo.\n\n" : "\nVez do Jogador %c.\n\n";
                Utils.printf(Color.YELLOW, mensagem, getJogador(jogadorAtual).getSimbolo());
                primeiraRodada = false;

                do {
                    temErro = false;

                    menu();
                    opcaoMenu = input.nextInt();

                    switch (opcaoMenu) {
                        case 1 -> {
                            do {
                                temErro = false;

                                try {
                                    Utils.printf(Color.YELLOW, "Escolha uma posição (linha coluna): ",
                                        getJogador(jogadorAtual).getSimbolo());
                                    matriz.queroQuero(input.nextInt(), input.nextInt(), getJogador(jogadorAtual));
                                    matriz.exibirMatriz();
                                    vencedor = matriz.vencedor(getJogador(jogadorAtual));
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                    temErro = true;
                                }
                            } while (temErro);
                        }
                        case 2 -> {
                            do {
                                temErro = false;

                                if (getJogador(jogadorAtual).getZorrilhos() == 3) {
                                    Utils.printf(Color.RED, "\nVocê não tem mais zorrilhos! Escolha outro!\n");
                                    temErro = true;
                                    break;
                                }

                                try {
                                    Utils.printf(Color.YELLOW, "Escolha uma posição (linha coluna): ",
                                        getJogador(jogadorAtual).getSimbolo());
                                    matriz.zorrilho(input.nextInt(), input.nextInt(), getJogador(jogadorAtual));
                                    matriz.exibirMatriz();
                                    vencedor = matriz.vencedor(getJogador(jogadorAtual));
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                    temErro = true;
                                }
                            } while (temErro);
                        }
                        default -> {
                            Utils.printf(Color.RED, "\nOpção inválida!\n");
                            temErro = true;
                        }
                    }
                } while (temErro);

                jogadorAtual = jogadorAtual == 2 ? 1 : 2;
            } while (vencedor == null);

            Utils.clearScreen();
            matriz.exibirMatriz();
            Utils.printf(Color.GREEN, "\nVencedor é o Jogador " + vencedor.getSimbolo() + "!\n");
        }
    }

    /**
     * Exibe as opções do menu do jogo.
     */
    public void menu() {
        System.out.println("Escolha o seu lance\n");
        System.out.println("\t1 - Quero-Quero");
        System.out.println("\t2 - Zorrilho\n");
        Utils.printf(Color.YELLOW, "Digite uma opção: ");
    }
}
