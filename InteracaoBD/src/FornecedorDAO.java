
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FornecedorDAO {

    public void salvar(Fornecedor fornecedor) {
        String sql = "INSERT INTO SYSTEM.fornecedores (id, nome, contato, endereco, tipo, cnpj, saldo_devedor) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, fornecedor.getId());
            stmt.setString(2, fornecedor.getNome());
            stmt.setString(3, fornecedor.getContato());
            stmt.setString(4, fornecedor.getEndereco());
            stmt.setString(5, fornecedor.getTipo());
            stmt.setString(6, fornecedor.getCnpj());
            stmt.setDouble(7, fornecedor.getSaldoDevedor());

            stmt.executeUpdate();
            System.out.println("✅ Fornecedor cadastrado com sucesso!");

        } catch (SQLException e) {
            System.err.println("⚠️ Erro ao inserir fornecedor: " + e.getMessage());
        }
    }

    public List<Fornecedor> listar() {
        List<Fornecedor> fornecedores = new ArrayList<>();
        String sql = "SELECT * FROM SYSTEM.fornecedores";

        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Fornecedor f = new Fornecedor();
                f.setId(rs.getInt("id"));
                f.setNome(rs.getString("nome"));
                f.setContato(rs.getString("contato"));
                f.setEndereco(rs.getString("endereco"));
                f.setTipo(rs.getString("tipo"));
                f.setCnpj(rs.getString("cnpj"));
                f.setSaldoDevedor(rs.getDouble("saldo_devedor"));
                fornecedores.add(f);
            }

        } catch (SQLException e) {
            System.err.println("⚠️ Erro ao listar fornecedores: " + e.getMessage());
        }

        return fornecedores;
    }

    public void alterar(int id, Fornecedor fornecedor) {
        String sql = "UPDATE SYSTEM.fornecedores SET nome = ?, contato = ?, endereco = ?, tipo = ?, cnpj = ?, saldo_devedor = ? WHERE id = ?";

        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, fornecedor.getNome());
            stmt.setString(2, fornecedor.getContato());
            stmt.setString(3, fornecedor.getEndereco());
            stmt.setString(4, fornecedor.getTipo());
            stmt.setString(5, fornecedor.getCnpj());
            stmt.setDouble(6, fornecedor.getSaldoDevedor());
            stmt.setInt(7, id);

            stmt.executeUpdate();
            System.out.println("✅ Fornecedor atualizado com sucesso!");

        } catch (SQLException e) {
            System.err.println("⚠️ Erro ao atualizar fornecedor: " + e.getMessage());
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM SYSTEM.fornecedores WHERE id = ?";

        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("✅ Fornecedor removido com sucesso!");

        } catch (SQLException e) {
            System.err.println("⚠️ Erro ao remover fornecedor: " + e.getMessage());
        }
    }

    public Fornecedor buscarPorId(int id) {
        String sql = "SELECT * FROM SYSTEM.fornecedores WHERE id = ?";

        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Fornecedor f = new Fornecedor();
                    f.setId(rs.getInt("id"));
                    f.setNome(rs.getString("nome"));
                    f.setContato(rs.getString("contato"));
                    f.setEndereco(rs.getString("endereco"));
                    f.setTipo(rs.getString("tipo"));
                    f.setCnpj(rs.getString("cnpj"));
                    f.setSaldoDevedor(rs.getDouble("saldo_devedor"));
                    return f;
                }
            }

        } catch (SQLException e) {
            System.err.println("⚠️ Erro ao buscar fornecedor: " + e.getMessage());
        }

        return null;
    }
}