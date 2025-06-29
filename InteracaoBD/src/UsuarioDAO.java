import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO implements Dao<Usuario> {

    @Override
    public void salvar(Usuario usuario) {
        String sql = "INSERT INTO SYSTEM.usuarios (id, nome, email, idade, credito) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, usuario.getId());
            stmt.setString(2, usuario.getNome());
            stmt.setString(3, usuario.getEmail());
            stmt.setInt(4, usuario.getIdade());
            stmt.setDouble(5, usuario.getCredito());

            stmt.executeUpdate();
            System.out.println("✅ Usuário cadastrado com sucesso!");

        } catch (SQLException e) {
            System.err.println("⚠️ Erro ao inserir registro: " + e.getMessage());
        }
    }

    @Override
    public List<Usuario> listar() {
        String sql = "SELECT * FROM SYSTEM.usuarios";
        List<Usuario> usuarios = new ArrayList<>();

        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setIdade(rs.getInt("idade"));
                usuario.setCredito(rs.getDouble("credito"));
                usuarios.add(usuario);
            }

        } catch (SQLException e) {
            System.err.println("⚠️ Erro ao listar usuários: " + e.getMessage());
        }

        return usuarios;
    }

    @Override
    public void alterar(int id, Usuario usuario) {
        String sql = "UPDATE SYSTEM.usuarios SET nome = ?, email = ?, idade = ?, credito = ? WHERE id = ?";

        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setInt(3, usuario.getIdade());
            stmt.setDouble(4, usuario.getCredito());
            stmt.setInt(5, id);

            stmt.executeUpdate();
            System.out.println("✅ Usuário atualizado com sucesso!");

        } catch (SQLException e) {
            System.err.println("⚠️ Erro ao atualizar registro: " + e.getMessage());
        }
    }

    @Override
    public void deletar(int id) {
        String sql = "DELETE FROM SYSTEM.usuarios WHERE id = ?";

        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("✅ Usuário removido com sucesso!");

        } catch (SQLException e) {
            System.err.println("⚠️ Erro ao excluir registro: " + e.getMessage());
        }
    }

    public Usuario buscarUsuario(int id) {
        String sql = "SELECT * FROM SYSTEM.usuarios WHERE id = ?";

        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setId(rs.getInt("id"));
                    usuario.setNome(rs.getString("nome"));
                    usuario.setEmail(rs.getString("email"));
                    usuario.setIdade(rs.getInt("idade"));
                    usuario.setCredito(rs.getDouble("credito"));
                    return usuario;
                }
            }

        } catch (SQLException e) {
            System.err.println("⚠️ Erro ao buscar usuário: " + e.getMessage());
        }

        return null;
    }
}