package View;


import Model.Usuarios;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class MenuSuperior {
    
    public static JMenuBar criarMenu(JFrame frame, Usuarios usuario) {
        JMenuBar menuBar = new JMenuBar();

        // Menus principais
        JMenu menuHome = new JMenu("Home");
        JMenu menuCadastro = new JMenu("Cadastro");
        JMenu menuEntradaSaida = new JMenu("Entrada e Saída");
        JMenu menuRelatorio = new JMenu("Relatório");
        JMenu menuSair = new JMenu("Sair"); // Agora "Sair" é um menu normal

        // Itens do menu Cadastro
        JMenuItem cadastroProduto = new JMenuItem("Cadastro de Produto");
        JMenuItem cadastroUsuario = new JMenuItem("Cadastro do Usuário");
        JMenuItem perfilUsuario = new JMenuItem("Perfil do Usuário");

        // Itens do menu Sair
        JMenuItem sairDoSistema = new JMenuItem("Sair do Sistema");

        // Verifica se o usuário é comum (IsAdmin = 0)
        if (usuario.getIsAdmin() == 0) {
            cadastroUsuario.setEnabled(false);
            cadastroUsuario.setForeground(Color.GRAY);
            perfilUsuario.setEnabled(false);
            perfilUsuario.setForeground(Color.GRAY);
        }

        // Adicionando itens ao menu Cadastro
        menuCadastro.add(cadastroProduto);
        menuCadastro.add(cadastroUsuario);
        menuCadastro.add(perfilUsuario);

        // Itens para outros menus
        JMenuItem homeItem = new JMenuItem("Home");
        JMenuItem entradaSaidaItem = new JMenuItem("Entrada e Saída");
        JMenuItem relatorioItem = new JMenuItem("Relatório");

        menuHome.add(homeItem);
        menuEntradaSaida.add(entradaSaidaItem);
        menuRelatorio.add(relatorioItem);
        menuSair.add(sairDoSistema); // Agora "Sair do Sistema" está dentro do menu "Sair"

        // Adicionando menus à barra
        menuBar.add(menuHome);
        menuBar.add(menuCadastro);
        menuBar.add(menuEntradaSaida);
        menuBar.add(menuRelatorio);
        menuBar.add(menuSair); // Agora "Sair" fica com o mesmo estilo dos outros menus

        // Ações para os itens do menu
        homeItem.addActionListener(e -> showTela(frame, "Home", usuario));
        entradaSaidaItem.addActionListener(e -> showTela(frame, "Entrada e Saída", usuario));
        relatorioItem.addActionListener(e -> showTela(frame, "Relatório", usuario));

        cadastroProduto.addActionListener(e -> showTela(frame, "Cadastro de Produto", usuario));
        cadastroUsuario.addActionListener(e -> showTela(frame, "Cadastro do Usuário", usuario));
        perfilUsuario.addActionListener(e -> showTela(frame, "Perfil do Usuário", usuario));

        // Ação para sair do sistema (exibe confirmação apenas ao clicar)
        sairDoSistema.addActionListener(e -> {
            int resposta = JOptionPane.showConfirmDialog(frame, "Deseja realmente sair do sistema?", "Confirmação", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
            if (resposta == JOptionPane.YES_OPTION) {
                TelaDeLogin telalogin = new TelaDeLogin();
                telalogin.setVisible(true);
                frame.dispose();
            }
        });

        return menuBar;
    }

    // Método para abrir as telas correspondentes
    private static void showTela(JFrame frame, String tela, Usuarios usuario) {
        frame.dispose(); // Fecha a tela atual

        JFrame novaTela = null;
        
        switch (tela) {
            case "Home":
                novaTela = new Tela_home(usuario);
                break;
            case "Entrada e Saída":
                novaTela = new TelaEntradaSaida(usuario);
                break;
            case "Relatório":
                novaTela = new TelaRequisicoes(usuario);
                break;
            case "Cadastro de Produto":
                novaTela = new TelaDeCadastroDeProdutos(usuario);
                break;
            case "Cadastro do Usuário":
                novaTela = new TelaCadastroUsuario(usuario);
                break;
            case "Perfil do Usuário":
                novaTela = new Tela_Perfil(usuario);
                break;
            default:
                JOptionPane.showMessageDialog(frame, "Opção inválida");
                return;
        }

        novaTela.setJMenuBar(MenuSuperior.criarMenu(novaTela, usuario));
        novaTela.setVisible(true);
    }
}