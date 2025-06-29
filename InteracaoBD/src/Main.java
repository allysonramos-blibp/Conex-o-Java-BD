
import java.util.List;
import java.util.Scanner;

public class Main {
    static UsuarioDAO usuarioDao = new UsuarioDAO();
    static ProdutoDao produtoDao = new ProdutoDao();
    static FornecedorDAO fornecedorDao = new FornecedorDAO();
    static TransacaoDAO transacaoDao = new TransacaoDAO();


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int tabela;

        do {
            System.out.println("\n===== Menu Principal =====");
            System.out.println("1: Usuários");
            System.out.println("2: Produtos");
            System.out.println("3: Fornecedores");
            System.out.println("4: Transações Financeiras");
            System.out.println("5: Sair");
            System.out.print("Escolha uma opção: ");
            tabela = scanner.nextInt();
            scanner.nextLine();

            switch (tabela) {
                case 1 -> menuUsuario(scanner);
                case 2 -> menuProduto(scanner);
                case 3 -> menuFornecedor(scanner);
                case 4 -> menuTransacao(scanner);
                case 5 -> System.out.println("Saindo...");
                default -> System.out.println("Não foi dessa vez. Tente novamente.");
            }
        } while (tabela != 5);

        scanner.close();
    }


    public static void menuUsuario(Scanner scanner) {
        int op;
        do {
            System.out.println("\n--- Menu Usuário ---");
            System.out.println("1: Cadastrar");
            System.out.println("2: Listar");
            System.out.println("3: Alterar");
            System.out.println("4: Remover");
            System.out.println("5: Voltar ao menu principal");

            System.out.print("Escolha uma opção: ");
            op = scanner.nextInt();
            scanner.nextLine();

            switch (op) {
                case 1 -> cadastrarUsuario(scanner);
                case 2 -> listarUsuarios();
                case 3 -> alterarUsuario(scanner);
                case 4 -> removerUsuario(scanner);
                case 5 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }

        } while (op != 5);
    }

    public static void cadastrarUsuario(Scanner scanner) {
        Usuario usuario = new Usuario();
        System.out.print("Digite o ID do usuário: ");
        usuario.setId(scanner.nextInt());
        scanner.nextLine();
        System.out.print("Digite o nome: ");
        usuario.setNome(scanner.nextLine().trim());
        System.out.print("Digite o email: ");
        usuario.setEmail(scanner.nextLine().trim());
        System.out.print("Digite a idade: ");
        usuario.setIdade(scanner.nextInt());
        System.out.print("Digite o crédito: ");
        usuario.setCredito(scanner.nextDouble());
        scanner.nextLine();

        usuarioDao.salvar(usuario);
    }

    public static void listarUsuarios() {
        List<Usuario> usuarios = usuarioDao.listar();
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário encontrado.");
        } else {
            for (Usuario u : usuarios) {
                u.mostrarDados();
            }
        }
    }

    public static void alterarUsuario(Scanner scanner) {
        System.out.print("ID do usuário a alterar: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Usuario usuario = new Usuario();
        System.out.print("Novo nome: ");
        usuario.setNome(scanner.nextLine().trim());
        System.out.print("Novo email: ");
        usuario.setEmail(scanner.nextLine().trim());
        System.out.print("Nova idade: ");
        usuario.setIdade(scanner.nextInt());
        System.out.print("Novo crédito: ");
        usuario.setCredito(scanner.nextDouble());
        scanner.nextLine();

        usuarioDao.alterar(id, usuario);
    }

    public static void removerUsuario(Scanner scanner) {
        System.out.print("ID do usuário a remover: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        usuarioDao.deletar(id);
    }


    public static void menuProduto(Scanner scanner) {
        int op;
        do {
            System.out.println("\n--- Menu Produto ---");
            System.out.println("1: Cadastrar");
            System.out.println("2: Listar");
            System.out.println("3: Alterar");
            System.out.println("4: Remover");
            System.out.println("5: Voltar ao menu principal");

            System.out.print("Escolha uma opção: ");
            op = scanner.nextInt();
            scanner.nextLine();

            switch (op) {
                case 1 -> cadastrarProduto(scanner);
                case 2 -> listarProdutos();
                case 3 -> alterarProduto(scanner);
                case 4 -> removerProduto(scanner);
                case 5 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida.");
            }
        } while (op != 5);
    }

    public static void cadastrarProduto(Scanner scanner) {
        Produto produto = new Produto();
        System.out.print("ID do produto: ");
        produto.setId(scanner.nextInt());
        scanner.nextLine();
        System.out.print("Nome: ");
        produto.setNome(scanner.nextLine().trim());
        System.out.print("Categoria: ");
        produto.setCategoria(scanner.nextLine().trim());
        System.out.print("Preço: ");
        produto.setPreco(scanner.nextDouble());
        System.out.print("Quantidade: ");
        produto.setQuantidade(scanner.nextInt());
        scanner.nextLine();

        produtoDao.salvar(produto);
    }

    public static void listarProdutos() {
        List<Produto> produtos = produtoDao.listar();
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto encontrado.");
        } else {
            for (Produto p : produtos) {
                p.mostrarDados();
            }
        }
    }

    public static void alterarProduto(Scanner scanner) {
        System.out.print("ID do produto a alterar: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Produto produto = new Produto();
        System.out.print("Novo nome: ");
        produto.setNome(scanner.nextLine().trim());
        System.out.print("Nova categoria: ");
        produto.setCategoria(scanner.nextLine().trim());
        System.out.print("Novo preço: ");
        produto.setPreco(scanner.nextDouble());
        System.out.print("Nova quantidade: ");
        produto.setQuantidade(scanner.nextInt());
        scanner.nextLine();

        produtoDao.alterar(id, produto);
    }

    public static void removerProduto(Scanner scanner) {
        System.out.print("ID do produto a remover: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        produtoDao.deletar(id);
    }


    public static void cadastrarFornecedor(Scanner scanner) {
        Fornecedor fornecedor = new Fornecedor();

        System.out.print("Digite o ID do fornecedor: ");
        fornecedor.setId(scanner.nextInt());
        scanner.nextLine();

        System.out.print("Digite o nome do fornecedor: ");
        fornecedor.setNome(scanner.nextLine().trim());

        System.out.print("Digite o contato (telefone/email): ");
        fornecedor.setContato(scanner.nextLine().trim());

        System.out.print("Digite o endereço: ");
        fornecedor.setEndereco(scanner.nextLine().trim());

        System.out.print("Digite o tipo (Produtos/Serviços): ");
        fornecedor.setTipo(scanner.nextLine().trim());

        System.out.print("Digite o CNPJ do fornecedor: ");
        fornecedor.setCnpj(scanner.nextLine().trim());

        System.out.print("Digite o saldo devedor do fornecedor: ");
        fornecedor.setSaldoDevedor(scanner.nextDouble());
        scanner.nextLine();

        fornecedorDao.salvar(fornecedor);
    }

    public static void listarFornecedores() {
        List<Fornecedor> fornecedores = fornecedorDao.listar();
        if (fornecedores.isEmpty()) {
            System.out.println("Nenhum fornecedor encontrado.");
        } else {
            for (Fornecedor f : fornecedores) {
                f.mostrarDados();
            }
        }
    }

    public static void menuFornecedor(Scanner scanner) {
        System.out.print("ID do fornecedor a alterar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Fornecedor fornecedor = new Fornecedor();
        System.out.print("Novo nome: ");
        fornecedor.setNome(scanner.nextLine().trim());

        System.out.print("Novo contato: ");
        fornecedor.setContato(scanner.nextLine().trim());

        System.out.print("Novo endereço: ");
        fornecedor.setEndereco(scanner.nextLine().trim());

        System.out.print("Novo tipo: ");
        fornecedor.setTipo(scanner.nextLine().trim());

        System.out.print("Novo CNPJ: ");
        fornecedor.setCnpj(scanner.nextLine().trim());

        System.out.print("Novo saldo devedor: ");
        fornecedor.setSaldoDevedor(scanner.nextDouble());
        scanner.nextLine();

        fornecedorDao.alterar(id, fornecedor);
    }

    public static void removerFornecedor(Scanner scanner) {
        System.out.print("ID do fornecedor a remover: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        fornecedorDao.deletar(id);
    }

    public static void menuTransacao(Scanner scanner) {
        int op;
        do {
            System.out.println("\n--- Menu Transações ---");
            System.out.println("1: Registrar Transação");
            System.out.println("2: Listar Transações");
            System.out.println("3: Alterar Transação");
            System.out.println("4: Remover Transação");
            System.out.println("5: Voltar ao menu principal");

            System.out.print("Escolha uma opção: ");
            op = scanner.nextInt();
            scanner.nextLine();

            switch (op) {
                case 1 -> cadastrarTransacao(scanner);
                case 2 -> listarTransacoes();
                case 3 -> alterarTransacao(scanner);
                case 4 -> removerTransacao(scanner);
                case 5 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida.");
            }
        } while (op != 5);
    }

    public static void cadastrarTransacao(Scanner scanner) {
        Transacao transacao = new Transacao();

        System.out.print("ID da transação: ");
        transacao.setId(scanner.nextInt());
        scanner.nextLine();

        System.out.print("Digite a data (YYYY-MM-DD): ");
        transacao.setData(java.sql.Date.valueOf(scanner.nextLine().trim()));

        System.out.print("Digite a descrição: ");
        transacao.setDescricao(scanner.nextLine().trim());

        System.out.print("Tipo ('entrada' ou 'saida'): ");
        transacao.setTipo(scanner.nextLine().trim());

        System.out.print("Valor da transação: ");
        transacao.setValor(scanner.nextDouble());
        scanner.nextLine();

        System.out.print("ID do usuário relacionado (ou 0 se for um fornecedor): ");
        transacao.setUsuarioId(scanner.nextInt());
        scanner.nextLine();

        System.out.print("ID do fornecedor relacionado (ou 0 se for um usuário): ");
        transacao.setFornecedorId(scanner.nextInt());
        scanner.nextLine();

        transacaoDao.salvar(transacao);
    }

    public static void listarTransacoes() {
        List<Transacao> transacoes = transacaoDao.listar();
        if (transacoes.isEmpty()) {
            System.out.println("Nenhuma transação encontrada.");
        } else {
            for (Transacao t : transacoes) {
                t.mostrarDados();
            }
        }
    }

    public static void alterarTransacao(Scanner scanner) {
        System.out.print("ID da transação a alterar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Transacao transacao = new Transacao();
        System.out.print("Nova data (YYYY-MM-DD): ");
        transacao.setData(java.sql.Date.valueOf(scanner.nextLine().trim()));

        System.out.print("Nova descrição: ");
        transacao.setDescricao(scanner.nextLine().trim());

        System.out.print("Novo tipo ('entrada' ou 'saida'): ");
        transacao.setTipo(scanner.nextLine().trim());

        System.out.print("Novo valor: ");
        transacao.setValor(scanner.nextDouble());
        scanner.nextLine();

        System.out.print("ID do usuário relacionado (ou 0 se for um fornecedor): ");
        transacao.setUsuarioId(scanner.nextInt());
        scanner.nextLine();

        System.out.print("ID do fornecedor relacionado (ou 0 se for um usuário): ");
        transacao.setFornecedorId(scanner.nextInt());
        scanner.nextLine();

        transacaoDao.alterar(id, transacao);
    }

    public static void removerTransacao(Scanner scanner) {
        System.out.print("ID da transação a remover: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        transacaoDao.deletar(id);
    }


}

