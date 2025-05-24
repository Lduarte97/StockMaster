/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Model.Usuarios;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author lldua
 */
public class ButtonColumnEditor extends AbstractCellEditor implements TableCellEditor, ActionListener {
    private JButton button;
    private int selectedRow;
    private JTable table;
    private Usuarios usuarioLogado;
    private Tela_home telaHome; // Referência para a tela home para atualizar

    public ButtonColumnEditor(JTable table, Usuarios usuario, Tela_home home) {
        this.table = table;
        this.usuarioLogado = usuario;
        this.telaHome = home;
        button = new JButton();
        // Definir o ícone para o botão
        try {
            // Ajuste o caminho para a sua imagem de ícone
            button.setIcon(new ImageIcon(getClass().getResource("/imagens/iconeEditar.png")));
        } catch (Exception e) {
            System.err.println("Erro ao carregar ícone do botão: " + e.getMessage());
            button.setText("Ver"); // Texto de fallback se o ícone não carregar
        }
        button.setOpaque(true);
        button.addActionListener(this);
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        // Armazena a linha selecionada para uso no ActionListener
        this.selectedRow = row;
        // Você pode ajustar o texto do botão ou o ícone dinamicamente aqui se necessário
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        return ""; // Não precisamos de um valor real do editor
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Dispara a parada de edição para garantir que o evento seja processado corretamente
        fireEditingStopped();

        if (selectedRow != -1) {
            try {
                // Obter o ID do produto da coluna 0 (a coluna oculta do ID)
                int produtoId = (int) table.getModel().getValueAt(selectedRow, 0);

                // Abre a tela de detalhes do produto
                ProdutosView detailView = new ProdutosView(produtoId, usuarioLogado, telaHome);
                detailView.setVisible(true);

            } catch (ClassCastException ex) {
                JOptionPane.showMessageDialog(table, "Erro ao obter o ID do produto. Verifique se a coluna 0 contém inteiros.", "Erro", JOptionPane.ERROR_MESSAGE);
                System.err.println("Erro de ClassCast ao obter produtoId: " + ex.getMessage());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(table, "Erro ao abrir a tela de detalhes: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                System.err.println("Erro ao abrir ProdutosView: " + ex.getMessage());
            }
        }
    }
}
