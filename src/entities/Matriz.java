package entities;

import utils.Utils;
import utils.enums.Color;

/**
 * A classe `Matriz` representa o tabuleiro do jogo, onde as ações dos jogadores são realizadas.
 */
public class Matriz {
    private final int TAMANHO_MATRIZ = 10;
    private final int QUANTIDADE_PESSOAS = 3;
    private final int BLOCO_POR_PESSOA = 4;

    private char[][] matriz;
    private char[][] matrizResposta;

    /**
     * Constrói um objeto `Matriz` e inicializa as matrizes do jogo.
     */
    public Matriz() {
        matriz = new char[TAMANHO_MATRIZ][TAMANHO_MATRIZ];
        matrizResposta = new char[TAMANHO_MATRIZ][TAMANHO_MATRIZ];
    }

    /**
     * Inicializa a matriz do jogo com caracteres '.'.
     */
    public void inicializarMatriz() {
        for (int i = 0; i < TAMANHO_MATRIZ; i++) {
            for (int j = 0; j < TAMANHO_MATRIZ; j++) {
                matriz[i][j] = '.';
                matrizResposta[i][j] = '.';
            }
        }
    }

    /**
     * Obtém o número de blocos por pessoa.
     *
     * @return O número de blocos por pessoa.
     */
    public int getBlocoPorPessoa() {
        return BLOCO_POR_PESSOA;
    }

    /**
     * Obtém a quantidade de pessoas no jogo.
     *
     * @return A quantidade de pessoas.
     */
    public int getQuantidadePessoas() {
        return QUANTIDADE_PESSOAS;
    }

    /**
     * Exibe a matriz do jogo no console.
     */
    public void exibirMatriz() {
        Utils.printf(Color.PURPLE, "[TABULEIRO DO JOGO]\n\n");
        System.out.printf("%2s", " ");

        for (int i = 0; i < TAMANHO_MATRIZ; i++) {
            System.out.printf("%2d", i);
        }
        System.out.println();

        for (int i = 0; i < TAMANHO_MATRIZ; i++) {
            System.out.printf("%2d", i);

            for (int j = 0; j < TAMANHO_MATRIZ; j++) {
                Utils.printf("%2c", matriz[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * Exibe a matriz de resposta do jogo no console.
     */
    public void exibirMatrizResposta() {
        Utils.printf(Color.PURPLE, "[TABULEIRO DO JOGO]\n\n");
        System.out.printf("%2s", " ");

        for (int i = 0; i < TAMANHO_MATRIZ; i++) {
            System.out.printf("%2d", i);
        }
        System.out.println();

        for (int i = 0; i < TAMANHO_MATRIZ; i++) {
            System.out.printf("%2d", i);

            for (int j = 0; j < TAMANHO_MATRIZ; j++) {
                Utils.printf("%2c", matrizResposta[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * Realiza a ação "Quero-Quero" na matriz do jogo.
     *
     * @param linha   A linha escolhida pelo jogador.
     * @param coluna  A coluna escolhida pelo jogador.
     * @param jogador O jogador que realiza a ação.
     * @throws Exception Se a posição escolhida for inválida ou já contiver uma pessoa.
     */
    public void queroQuero(int linha, int coluna, Jogador jogador) throws Exception {
        if (posicaoInvalida(linha, coluna)) {
            throw new Exception("\n\033[31mPosição inválida!\033[31m\n");
        }

        if (matrizResposta[linha][coluna] == jogador.getSimbolo()) {
            throw new Exception("\n\033[31mPessoa escondida por você! Escolha outra.\033[0m\n");
        }

        if (matrizResposta[linha][coluna] == '.') {
            matriz[linha][coluna] = 'X';
        }

        if (matrizResposta[linha][coluna] != '.') {
            matriz[linha][coluna] = matrizResposta[linha][coluna];
        }
    }

    /**
     * Realiza a ação "Zorrilho" na matriz do jogo.
     *
     * @param linha   A linha escolhida pelo jogador.
     * @param coluna  A coluna escolhida pelo jogador.
     * @param jogador O jogador que realiza a ação.
     * @throws Exception Se a posição escolhida for inválida ou já contiver uma pessoa.
     */
    public void zorrilho(int linha, int coluna, Jogador jogador) throws Exception {
        if (posicaoInvalida(linha, coluna)) {
            throw new Exception("\n\033[31mPosição inválida!\033[31m\n");
        }

        if (matrizResposta[linha][coluna] == jogador.getSimbolo()) {
            throw new Exception("\n\033[31mPessoa escondida por você! Escolha outra.\033[0m\n");
        }

        if (matrizResposta[linha][coluna] == '.') {
            matriz[linha][coluna] = 'X';
        } else {
            matriz[linha][coluna] = matrizResposta[linha][coluna];
        }

        // Outras ações do Zorrilho
        // ...

        jogador.incrementarZorrilhos();
    }

    /**
     * Verifica se há um vencedor no jogo.
     *
     * @param jogador O jogador atual.
     * @return O jogador vencedor ou null se não houver vencedor.
     */
    public Jogador vencedor(Jogador jogador) {
        int contador = 0;

        for (int i = 0; i < TAMANHO_MATRIZ; i++) {
            for (int j = 0; j < TAMANHO_MATRIZ; j++) {
                if (matriz[i][j] != 'X' &&
                    matriz[i][j] != '.' &&
                    matriz[i][j] != jogador.getSimbolo() ||
                    matriz[i][j] == '3') {
                    contador++;
                }
            }
        }

        if (contador == QUANTIDADE_PESSOAS * BLOCO_POR_PESSOA) {
            return jogador;
        }
        return null;
    }

    /**
     * Define uma posição na matriz de resposta do jogo.
     *
     * @param linha   A linha escolhida pelo jogador.
     * @param coluna  A coluna escolhida pelo jogador.
     * @param simbolo O símbolo do jogador.
     * @throws Exception Se a posição escolhida for inválida ou já contiver um símbolo.
     */
    public void setPosicaoMatriz(int linha, int coluna, char simbolo) throws Exception {
        if (posicaoInvalida(linha, coluna)) {
            throw new Exception("\033[31mPosição inválida!\033[31m");
        }

        if (matrizResposta[linha][coluna] != '.' && matrizResposta[linha][coluna] == simbolo) {
            throw new Exception("\033[31mPosição já usada! Escolha outra.\033[0m");
        }

        if (matrizResposta[linha][coluna] == '.') {
            matrizResposta[linha][coluna] = simbolo;
        }

        if (matrizResposta[linha][coluna] != '.' && matrizResposta[linha][coluna] != simbolo) {
            matrizResposta[linha][coluna] = '3';
        }
    }

    private boolean posicaoInvalida(int linha, int coluna) {
        return linha < 0 || linha >= TAMANHO_MATRIZ || coluna < 0 || coluna >= TAMANHO_MATRIZ;
    }
}
