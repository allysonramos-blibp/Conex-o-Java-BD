import java.util.List;

public interface Dao <T> {

    void salvar(T objeto);
    List<T> listar();
    void  alterar(int id, T objeto);

    void deletar(int id);
}

