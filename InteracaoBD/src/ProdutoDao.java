import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDao {

    public void salvar(Produto produto) {
        String sql = "INSERT INTO SYSTEM.produtos_novo (id, nome, categoria, preco, quantidade) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, produto.getId());
            stmt.setString(2, produto.getNome());
            stmt.setString(3, produto.getCategoria());
            stmt.setDouble(4, produto.getPreco());
            stmt.setInt(5, produto.getQuantidade());

            stmt.executeUpdate();
            System.out.println("✅ Produto cadastrado com sucesso!");

        } catch (SQLException e) {
            System.err.println("⚠️ Erro ao inserir produto: " + e.getMessage());
        }
    }

    public List<Produto> listar() {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM SYSTEM.produtos_novo";

        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Produto p = new Produto();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setCategoria(rs.getString("categoria"));
                p.setPreco(rs.getDouble("preco"));
                p.setQuantidade(rs.getInt("quantidade"));
                produtos.add(p);
            }

        } catch (SQLException e) {
            System.err.println("⚠️ Erro ao listar produtos: " + e.getMessage());
        }

        return produtos;
    }

    public void alterar(int id, Produto produto) {
        String sql = "UPDATE SYSTEM.produtos_novo SET nome = ?, categoria = ?, preco = ?, quantidade = ? WHERE id = ?";

        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getCategoria());
            stmt.setDouble(3, produto.getPreco());
            stmt.setInt(4, produto.getQuantidade());
            stmt.setInt(5, id);

            stmt.executeUpdate();
            System.out.println("✅ Produto atualizado com sucesso!");

        } catch (SQLException e) {
            System.err.println("⚠️ Erro ao atualizar produto: " + e.getMessage());
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM SYSTEM.produtos_novo WHERE id = ?";

        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("✅ Produto removido com sucesso!");

        } catch (SQLException e) {
            System.err.println("⚠️ Erro ao remover produto: " + e.getMessage());
        }
    }

    public Produto buscarPorId(int id) {
        String sql = "SELECT * FROM SYSTEM.produtos_novo WHERE id = ?";

        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Produto p = new Produto();
                    p.setId(rs.getInt("id"));
                    p.setNome(rs.getString("nome"));
                    p.setCategoria(rs.getString("categoria"));
                    p.setPreco(rs.getDouble("preco"));
                    p.setQuantidade(rs.getInt("quantidade"));
                    return p;
                }
            }

        } catch (SQLException e) {
            System.err.println("⚠️ Erro ao buscar produto: " + e.getMessage());
        }

        return null;
    }
}