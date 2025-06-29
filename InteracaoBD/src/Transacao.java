import java.util.Date;

public class Transacao {
    private int id;
    private Date data;
    private String descricao;
    private String tipo;
    private double valor;
    private int usuarioId;
    private int fornecedorId;

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Date getData() { return data; }
    public void setData(Date data) { this.data = data; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }

    public int getUsuarioId() { return usuarioId; }
    public void setUsuarioId(int usuarioId) { this.usuarioId = usuarioId; }

    public int getFornecedorId() { return fornecedorId; }
    public void setFornecedorId(int fornecedorId) { this.fornecedorId = fornecedorId; }

    public void mostrarDados() {
        System.out.println("ID: " + id);
        System.out.println("Data: " + data);
        System.out.println("Descrição: " + descricao);
        System.out.println("Tipo: " + tipo);
        System.out.println("Valor: R$ " + valor);
        System.out.println("Usuário ID: " + usuarioId);
        System.out.println("Fornecedor ID: " + fornecedorId);
        System.out.println("-----------------------------");
    }
}