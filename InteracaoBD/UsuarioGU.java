import javax.swing.*;
import java.awt.*;

public class UsuarioGU {
    private JFrame frameTela;
    private JTabbedPane abas;
    private JPanel painelUsuario, painelProduto, painelFornecedor, painelTransacao;

    private JTextField fieldIdUsuario, fieldNomeUsuario, fieldEmailUsuario, fieldIdadeUsuario, fieldCreditoUsuario;
    private JTextField fieldCodigoProduto, fieldNomeProduto, fieldPrecoProduto;
    private JTextField fieldIdFornecedor, fieldNomeFornecedor, fieldContatoFornecedor;
    private JTextField fieldIdTransacao, fieldUsuarioTransacao, fieldProdutoTransacao, fieldValorTransacao;

    private UsuarioDAO usuarioDao = new UsuarioDAO();
    private ProdutoDao produtoDao = new ProdutoDao();
    private FornecedorDAO fornecedorDao = new FornecedorDAO();

    public UsuarioGU() {
        inicializarJanela();
        inicializarAbas();
        frameTela.setVisible(true);
    }

    private void inicializarJanela() {
        frameTela = new JFrame("Sistema de Gestão");
        frameTela.setSize(600, 500);
        frameTela.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frameTela.setLocationRelativeTo(null);
    }

    private void inicializarAbas() {
        abas = new JTabbedPane();

        painelUsuario = criarPainelUsuario();
        painelProduto = criarPainelProduto();
        painelFornecedor = criarPainelFornecedor();
        painelTransacao = criarPainelTransacao();

        abas.addTab("Usuário", painelUsuario);
        abas.addTab("Produto", painelProduto);
        abas.addTab("Fornecedor", painelFornecedor);
        abas.addTab("Transação", painelTransacao);

        frameTela.setContentPane(abas);
    }

    private JPanel criarPainelUsuario() {
        JPanel painel = new JPanel(new GridLayout(6, 2, 10, 10));
        fieldIdUsuario = new JTextField();
        fieldNomeUsuario = new JTextField();
        fieldEmailUsuario = new JTextField();
        fieldIdadeUsuario = new JTextField();
        fieldCreditoUsuario = new JTextField();

        painel.add(new JLabel("ID:"));
        painel.add(fieldIdUsuario);
        painel.add(new JLabel("Nome:"));
        painel.add(fieldNomeUsuario);
        painel.add(new JLabel("E-mail:"));
        painel.add(fieldEmailUsuario);
        painel.add(new JLabel("Idade:"));
        painel.add(fieldIdadeUsuario);
        painel.add(new JLabel("Crédito:"));
        painel.add(fieldCreditoUsuario);

        JPanel painelBotoes = criarPainelBotoes(
                this::salvarUsuario,
                this::buscarUsuario,
                this::limparCamposUsuario
        );

        painel.add(painelBotoes);
        return painel;
    }

    private JPanel criarPainelProduto() {
        JPanel painel = new JPanel(new GridLayout(4, 2, 10, 10));
        fieldCodigoProduto = new JTextField();
        fieldNomeProduto = new JTextField();
        fieldPrecoProduto = new JTextField();

        painel.add(new JLabel("Código:"));
        painel.add(fieldCodigoProduto);
        painel.add(new JLabel("Nome do Produto:"));
        painel.add(fieldNomeProduto);
        painel.add(new JLabel("Preço:"));
        painel.add(fieldPrecoProduto);

        JPanel painelBotoes = criarPainelBotoes(
                this::salvarProduto,
                this::buscarProduto,
                this::limparCamposProduto
        );

        painel.add(painelBotoes);
        return painel;
    }

    private JPanel criarPainelFornecedor() {
        JPanel painel = new JPanel(new GridLayout(4, 2, 10, 10));
        fieldIdFornecedor = new JTextField();
        fieldNomeFornecedor = new JTextField();
        fieldContatoFornecedor = new JTextField();

        painel.add(new JLabel("ID Fornecedor:"));
        painel.add(fieldIdFornecedor);
        painel.add(new JLabel("Nome:"));
        painel.add(fieldNomeFornecedor);
        painel.add(new JLabel("Contato:"));
        painel.add(fieldContatoFornecedor);

        JPanel painelBotoes = criarPainelBotoes(
                this::salvarFornecedor,
                this::buscarFornecedor,
                this::limparCamposFornecedor
        );

        painel.add(painelBotoes);
        return painel;
    }

    private JPanel criarPainelTransacao() {
        JPanel painel = new JPanel(new GridLayout(5, 2, 10, 10));
        fieldIdTransacao = new JTextField();
        fieldUsuarioTransacao = new JTextField();
        fieldProdutoTransacao = new JTextField();
        fieldValorTransacao = new JTextField();

        painel.add(new JLabel("ID Transação:"));
        painel.add(fieldIdTransacao);
        painel.add(new JLabel("Usuário:"));
        painel.add(fieldUsuarioTransacao);
        painel.add(new JLabel("Produto:"));
        painel.add(fieldProdutoTransacao);
        painel.add(new JLabel("Valor Total:"));
        painel.add(fieldValorTransacao);

        JPanel painelBotoes = criarPainelBotoes(
                this::salvarTransacao,
                this::buscarTransacao,
                this::limparCamposTransacao
        );

        painel.add(painelBotoes);
        return painel;
    }

    private JPanel criarPainelBotoes(Runnable salvar, Runnable buscar, Runnable cancelar) {
        JPanel painel = new JPanel();
        JButton btnSalvar = new JButton("Salvar");
        JButton btnBuscar = new JButton("Buscar");
        JButton btnCancelar = new JButton("Cancelar");

        btnSalvar.addActionListener(e -> salvar.run());
        btnBuscar.addActionListener(e -> buscar.run());
        btnCancelar.addActionListener(e -> cancelar.run());

        painel.add(btnSalvar);
        painel.add(btnBuscar);
        painel.add(btnCancelar);
        return painel;
    }


    private void salvarUsuario() {
        try {
            int id = Integer.parseInt(fieldIdUsuario.getText().trim());
            String nome = fieldNomeUsuario.getText().trim();
            String email = fieldEmailUsuario.getText().trim();
            int idade = Integer.parseInt(fieldIdadeUsuario.getText().trim());
            double credito = Double.parseDouble(fieldCreditoUsuario.getText().trim());

            Usuario usuario = new Usuario(id, nome, email, idade, credito);
            usuarioDao.salvar(usuario);
            JOptionPane.showMessageDialog(frameTela, "✅ Usuário salvo com sucesso!");
            limparCamposUsuario();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frameTela, "⚠️ Erro: Certifique-se de preencher todos os campos corretamente.");
        }
    }

    private void buscarUsuario() {
        try {
            int id = Integer.parseInt(fieldIdUsuario.getText().trim());
            Usuario usuario = usuarioDao.buscarUsuario(id);

            if (usuario != null) {
                fieldNomeUsuario.setText(usuario.getNome());
                fieldEmailUsuario.setText(usuario.getEmail());
                fieldIdadeUsuario.setText(String.valueOf(usuario.getIdade()));
                fieldCreditoUsuario.setText(String.valueOf(usuario.getCredito()));
                JOptionPane.showMessageDialog(frameTela, "🔍 Usuário encontrado!");
            } else {
                JOptionPane.showMessageDialog(frameTela, "⚠️ Usuário não encontrado.");
                limparCamposUsuario();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frameTela, "⚠️ Erro: Digite um ID válido.");
        }
    }

    private void limparCamposUsuario() {
        fieldIdUsuario.setText("");
        fieldNomeUsuario.setText("");
        fieldEmailUsuario.setText("");
        fieldIdadeUsuario.setText("");
        fieldCreditoUsuario.setText("");
    }


    private void salvarProduto() {


    }

    private void buscarProduto() {

    }

    private void limparCamposProduto() {
        fieldCodigoProduto.setText("");
        fieldNomeProduto.setText("");
        fieldPrecoProduto.setText("");
    }


    private void salvarFornecedor() {

    }

    private void buscarFornecedor() {
        // Implementar lógica de buscar fornecedor
    }

    private void limparCamposFornecedor() {
        fieldIdFornecedor.setText("");
        fieldNomeFornecedor.setText("");
        fieldContatoFornecedor.setText("");
    }

    // Métodos de ação para Transação (esqueleto)
    private void salvarTransacao() {
        // Implementar lógica de salvar transação
    }

    private void buscarTransacao() {
        // Implementar lógica de buscar transação
    }

    private void limparCamposTransacao() {
        fieldIdTransacao.setText("");
        fieldUsuarioTransacao.setText("");
        fieldProdutoTransacao.setText("");
        fieldValorTransacao.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(UsuarioGU::new);
    }

    public ProdutoDao getProdutoDao() {
        return produtoDao;
    }

    public void setProdutoDao(ProdutoDao produtoDao) {
        this.produtoDao = produtoDao;
    }

    public FornecedorDAO getFornecedorDao() {
        return fornecedorDao;
    }

    public void setFornecedorDao(FornecedorDAO fornecedorDao) {
        this.fornecedorDao = fornecedorDao;
    }
}
