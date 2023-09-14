/**
 * A classe `App` representa o ponto de entrada principal da aplicação para o jogo.
 * Ela inicializa e inicia o jogo criando um objeto `Jogo` e chamando
 * o método `iniciarJogo`.
 */
public class App {
    /**
     * O método principal da aplicação.
     *
     * @param args Argumentos de linha de comando (não usados nesta aplicação).
     * @throws Exception Se ocorrer uma exceção durante a inicialização ou execução do jogo.
     */
    public static void main(String[] args) throws Exception {
        // Cria uma nova instância da classe Jogo
        Jogo jogo = new Jogo();

        // Inicia o jogo
        jogo.iniciarJogo();
    }
}
