import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransacaoDAO {

    public void salvar(Transacao transacao) {
        String sql = "INSERT INTO SYSTEM.transacoes (id, data, descricao, tipo, valor, usuario_id, fornecedor_id) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, transacao.getId());
            stmt.setDate(2, new java.sql.Date(transacao.getData().getTime()));
            stmt.setString(3, transacao.getDescricao());
            stmt.setString(4, transacao.getTipo());
            stmt.setDouble(5, transacao.getValor());
            stmt.setInt(6, transacao.getUsuarioId());
            stmt.setInt(7, transacao.getFornecedorId());

            stmt.executeUpdate();
            System.out.println("✅ Transação registrada com sucesso!");

        } catch (SQLException e) {
            System.err.println("⚠️ Erro ao registrar transação: " + e.getMessage());
        }
    }

    public List<Transacao> listar() {
        List<Transacao> transacoes = new ArrayList<>();
        String sql = "SELECT * FROM SYSTEM.transacoes";

        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Transacao t = new Transacao();
                t.setId(rs.getInt("id"));
                t.setData(rs.getDate("data"));
                t.setDescricao(rs.getString("descricao"));
                t.setTipo(rs.getString("tipo"));
                t.setValor(rs.getDouble("valor"));
                t.setUsuarioId(rs.getInt("usuario_id"));
                t.setFornecedorId(rs.getInt("fornecedor_id"));
                transacoes.add(t);
            }

        } catch (SQLException e) {
            System.err.println("⚠️ Erro ao listar transações: " + e.getMessage());
        }

        return transacoes;
    }

    public void alterar(int id, Transacao transacao) {
        String sql = "UPDATE SYSTEM.transacoes SET data = ?, descricao = ?, tipo = ?, valor = ?, usuario_id = ?, fornecedor_id = ? WHERE id = ?";

        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, new java.sql.Date(transacao.getData().getTime()));
            stmt.setString(2, transacao.getDescricao());
            stmt.setString(3, transacao.getTipo());
            stmt.setDouble(4, transacao.getValor());
            stmt.setInt(5, transacao.getUsuarioId());
            stmt.setInt(6, transacao.getFornecedorId());
            stmt.setInt(7, id);

            stmt.executeUpdate();
            System.out.println("✅ Transação atualizada com sucesso!");

        } catch (SQLException e) {
            System.err.println("⚠️ Erro ao atualizar transação: " + e.getMessage());
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM SYSTEM.transacoes WHERE id = ?";

        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("✅ Transação removida com sucesso!");

        } catch (SQLException e) {
            System.err.println("⚠️ Erro ao remover transação: " + e.getMessage());
        }
    }

    public Transacao buscarPorId(int id) {
        String sql = "SELECT * FROM SYSTEM.transacoes WHERE id = ?";

        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Transacao t = new Transacao();
                    t.setId(rs.getInt("id"));
                    t.setData(rs.getDate("data"));
                    t.setDescricao(rs.getString("descricao"));
                    t.setTipo(rs.getString("tipo"));
                    t.setValor(rs.getDouble("valor"));
                    t.setUsuarioId(rs.getInt("usuario_id"));
                    t.setFornecedorId(rs.getInt("fornecedor_id"));
                    return t;
                }
            }

        } catch (SQLException e) {
            System.err.println("⚠️ Erro ao buscar transação: " + e.getMessage());
        }

        return null;
    }
}