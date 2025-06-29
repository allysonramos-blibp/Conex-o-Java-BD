import java.sql.Connection;

public class TesteConexao {
    public static void main(String[] args) {
        Connection conn = ConexaoBD.getConnection();
        if (conn != null) {
            System.out.println("Conexão estabelecida com sucesso!");
        } else {
            System.err.println("Falha na conexão.");
        }
    }
}
