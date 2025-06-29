public class Usuario {
    private int id;
    private String nome;
    private String email;
    private int idade;
    private double credito;

    // Construtor vazio
    public Usuario() {}

    // Construtor completo
    public Usuario(int id, String nome, String email, int idade, double credito) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.idade = idade;
        this.credito = credito;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public String getNome() {

        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public int getIdade()
    {
        return idade;
    }

    public void setIdade(int idade)
    {
        this.idade = idade;
    }

    public double getCredito()
    {
        return credito;
    }

    public void setCredito(double credito)
    {
        this.credito = credito;
    }

    public void mostrarDados() {
        System.out.println("ID: " + id);
        System.out.println("Nome: " + nome);
        System.out.println("E-mail: " + email);
        System.out.println("Idade: " + idade);
        System.out.println("Crédito: " + credito);
        System.out.println("-----------------------------------\n");
    }
}