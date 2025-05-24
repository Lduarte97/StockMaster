/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author lldua
 */
public class ButtonColumnRenderer extends DefaultTableCellRenderer{
    private JButton button;

    public ButtonColumnRenderer() {
        button = new JButton();
        try {
            // Ajuste o caminho para a sua imagem de ícone
            button.setIcon(new ImageIcon(getClass().getResource("/imagens/iconeEditar.png")));
        } catch (Exception e) {
            System.err.println("Erro ao carregar ícone do botão para o renderer: " + e.getMessage());
            button.setText("Ver"); // Texto de fallback
        }
        button.setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        // Configura a aparência do botão com base na seleção da linha
        if (isSelected) {
            button.setForeground(table.getSelectionForeground());
            button.setBackground(table.getSelectionBackground());
        } else {
            button.setForeground(table.getForeground());
            button.setBackground(table.getBackground());
        }
        return button;
    }
}
