
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
    private static final String USER = "SYSTEM";
    private static final String PASS = "2001a";
    private static Connection conn = null;

    private void ConexaoOracleJDBC() {}

    public static Connection getConnection() {
        if (conn == null) {
            try {
                conn = DriverManager.getConnection(URL, USER, PASS);
                System.out.println("Conexão bem-sucedida!");
            } catch (SQLException e) {
                System.err.println("Erro na conexão: " + e.getMessage());
            }
        }
        return conn;
    }
}