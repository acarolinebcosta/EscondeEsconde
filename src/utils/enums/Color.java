package utils.enums;

/**
 * A enumeração `Color` define códigos de cores ANSI para formatação de texto no console.
 * Cada valor enum corresponde a uma cor específica.
 */
public enum Color {
    NONE("\033[0m"),    // Código de cor para reverter a formatação para padrão.
    RED("\033[0;31m"),  // Código de cor para texto vermelho.
    GREEN("\033[0;32m"),  // Código de cor para texto verde.
    YELLOW("\033[0;33m"),  // Código de cor para texto amarelo.
    PURPLE("\033[0;35m");  // Código de cor para texto roxo.

    /**
     * O código de cor ANSI associado a esta cor.
     */
    public final String CODE;

    /**
     * Constrói uma instância da enumeração `Color` com um código de cor ANSI.
     *
     * @param CODE O código de cor ANSI.
     */
    private Color(String CODE) {
        this.CODE = CODE;
    }
}
