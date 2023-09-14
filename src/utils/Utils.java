package utils;

import utils.enums.Color;

/**
 * A classe `Utils` fornece métodos utilitários para operações comuns, como limpar a tela do console e formatar a saída de texto.
 */
public class Utils {
    /**
     * Código ANSI para a cor verde.
     */
    public final String COLOR_GREEN = "\033[32m";

    /**
     * Código ANSI para a cor vermelha.
     */
    public final String COLOR_RED = "\033[31m";
s
    /**
     * Código ANSI para reverter a formatação para o padrão.
     */
    public final String COLOR_RESET = "\033[0m";

    /**
     * Limpa o conteúdo da tela do console.
     */
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Imprime uma linha de texto no console seguida de uma quebra de linha.
     *
     * @param text O texto a ser impresso.
     */
    public static void println(String text) {
        printf(Color.NONE, text + "\n");
    }

    /**
     * Imprime uma quebra de linha no console.
     */
    public static void println() {
        println("");
    }

    /**
     * Formata e imprime texto no console usando um formato especificado e argumentos.
     *
     * @param format O formato de texto.
     * @param args   Os argumentos a serem formatados e impressos.
     */
    public static void printf(String format, Object... args) {
        System.out.printf(format, args);
    }

    /**
     * Formata e imprime texto no console usando um formato e uma cor específicos.
     *
     * @param color  A cor a ser aplicada ao texto.
     * @param format O formato de texto.
     * @param args   Os argumentos a serem formatados e impressos.
     */
    public static void printf(Color color, String format, Object... args) {
        System.out.printf(color.CODE);  // Define a cor antes de imprimir o texto
        System.out.printf(format, args); // Imprime o texto formatado
        System.out.printf(Color.NONE.CODE); // Restaura a formatação para o padrão
    }
}
