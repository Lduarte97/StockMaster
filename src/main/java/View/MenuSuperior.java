package View;


import Model.Usuarios;
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
        JMenu menuSair = new JMenu("Sair do Sistema");

        // Itens do menu Cadastro
        JMenuItem cadastroProduto = new JMenuItem("Cadastro de Produto");
        JMenuItem cadastroUsuario = new JMenuItem("Cadastro do Usuário");
        JMenuItem perfilUsuario = new JMenuItem("Perfil do Usuário");

        // Adicionando itens ao menu Cadastro
        menuCadastro.add(cadastroProduto);
        menuCadastro.add(cadastroUsuario);
        menuCadastro.add(perfilUsuario);

        // Itens para outros menus que não possuem submenus
        JMenuItem homeItem = new JMenuItem("Home");
        JMenuItem entradaSaidaItem = new JMenuItem("Entrada e Saída");
        JMenuItem relatorioItem = new JMenuItem("Relatório");

        menuHome.add(homeItem);
        menuEntradaSaida.add(entradaSaidaItem);
        menuRelatorio.add(relatorioItem);

        // Adicionando menus à barra
        menuBar.add(menuHome);
        menuBar.add(menuCadastro);
        menuBar.add(menuEntradaSaida);
        menuBar.add(menuRelatorio);
        menuBar.add(menuSair);

        // Ações para os itens do menu
        homeItem.addActionListener(e -> showTela(frame, "Home", usuario));
        entradaSaidaItem.addActionListener(e -> showTela(frame, "Entrada e Saída", usuario));
        relatorioItem.addActionListener(e -> showTela(frame, "Relatório", usuario));

        cadastroProduto.addActionListener(e -> showTela(frame, "Cadastro de Produto", usuario));
        cadastroUsuario.addActionListener(e -> showTela(frame, "Cadastro do Usuário", usuario));
        perfilUsuario.addActionListener(e -> showTela(frame, "Perfil do Usuário", usuario));

        // Ação para sair do sistema
        menuSair.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                int resposta = JOptionPane.showConfirmDialog(frame, "Deseja realmente sair do sistema?", "Confirmação", JOptionPane.YES_NO_OPTION);
                if (resposta == JOptionPane.YES_OPTION) {
                    TelaDeLogin telalogin = new TelaDeLogin();
                    telalogin.setVisible(true);
                    frame.dispose();
                }
            }

            @Override
            public void menuDeselected(MenuEvent e) {}

            @Override
            public void menuCanceled(MenuEvent e) {}
        });

        return menuBar;
    }

    // Método para abrir as telas correspondentes
    private static void showTela(JFrame frame, String tela, Usuarios usuario) {
        // Fecha a tela atual
        frame.dispose();

        // Criar e exibir a nova tela
        JFrame novaTela = null;
        
        switch (tela) {
            case "Home":
                novaTela = new Tela_home(usuario);  // Passa o usuário para a tela
                break;
            case "Entrada e Saída":
                novaTela = new TelaEntradaSaida(usuario);  // Passa o usuário para a tela
                break;
            case "Relatório":
                novaTela = new TelaRequisicoes(usuario);  // Passa o usuário para a tela
                break;
            case "Cadastro de Produto":
                novaTela = new TelaDeCadastroDeProdutos(usuario);  // Passa o usuário para a tela
                break;
            case "Cadastro do Usuário":
                novaTela = new TelaCadastroUsuario(usuario);  // Passa o usuário para a tela
                break;
            case "Perfil do Usuário":
                novaTela = new Tela_Perfil(usuario);  // Passa o usuário para a tela
                break;
            default:
                JOptionPane.showMessageDialog(frame, "Opção inválida");
                return; // Sai do método se for uma opção inválida
        }

        // Recriar o menu para a nova tela
        novaTela.setJMenuBar(MenuSuperior.criarMenu(novaTela, usuario));  // Passa o usuário para a nova tela
        novaTela.setVisible(true);
    }
}


