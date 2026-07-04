public class Cliente {

	private static int ultimoID = 10_000;

    private String nome;
    private int documento;

    /**
     * Construtor do cliente. Cria um novo cliente a partir do nome informado.
     * Utiliza o método setNome para atribuir o nome ao cliente e atribui um número
     * de documento gerado sequencialmente a partir do contador estático ultimoID.
     */
    public Cliente (String nome) {

    	setNome(nome);
    	this.documento = ultimoID++;

    }

    /**
     * Retorna o nome do cliente.
     */
    public String getNome() {
    	return nome;
    }

    /**
     * Atribui ao cliente o nome informado como parâmetro.
     * Esse nome deve conter, pelo menos, duas palavras; caso contrário,
     * lança IllegalArgumentException.
     */
    public void setNome(String nome) {

    	if (nome.trim().split("\\s+").length < 2) {
    		throw new IllegalArgumentException("O nome do cliente deve conter duas palavras!");
    	}
    	this.nome = nome;

    }

    /**
     * Retorna uma representação textual do cliente, incluindo seu nome e documento.
     */
    @Override
    public String toString() {
        return nome + " (documento: " + documento + ")";
    }

    /**
     * Retorna um código hash para o cliente, que corresponde a seu documento.
     */
    @Override
    public int hashCode(){
        return documento;
    }
}
