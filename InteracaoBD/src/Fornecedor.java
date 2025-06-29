
public class Fornecedor {
    private int id;
    private String nome;
    private String contato;
    private String endereco;
    private String tipo;
    private String cnpj;
    private double saldoDevedor;

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getContato() { return contato; }
    public void setContato(String contato) { this.contato = contato; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getCnpj() { return cnpj; }
    public void setCnpj(String cnpj) { this.cnpj = cnpj; }

    public double getSaldoDevedor() { return saldoDevedor; }
    public void setSaldoDevedor(double saldoDevedor) { this.saldoDevedor = saldoDevedor; }

    // Método para exibir os dados do fornecedor
    public void mostrarDados() {
        System.out.println("ID: " + id);
        System.out.println("Nome: " + nome);
        System.out.println("Contato: " + contato);
        System.out.println("Endereço: " + endereco);
        System.out.println("Tipo: " + tipo);
        System.out.println("CNPJ: " + cnpj);
        System.out.println("Saldo devedor: R$ " + saldoDevedor);
        System.out.println("-----------------------------");
    }
}