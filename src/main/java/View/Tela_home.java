/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Controller.ProdutosController;
import Model.Produtos;
import Model.Usuarios;
import java.awt.Image;
import java.awt.Insets;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JMenuBar;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import View.ButtonColumnEditor; // Importe as novas classes
import View.ButtonColumnRenderer;
import javax.swing.table.TableColumn; // Adicione esta importação



/**
 *
 * @author devmat
 */
public class Tela_home extends javax.swing.JFrame {

    /**
     * Creates new form Tela_home_2
     */
    Usuarios user;
    // criação de um objeto para chamar a classe controller para o método listarprodutostabela()
    ProdutosController produtosController = new ProdutosController();
    public Tela_home(Usuarios u) {
         initComponents();
        user = u;
        // chamada do menu superior
        setJMenuBar(MenuSuperior.criarMenu(this, user));
        produtosController = new ProdutosController(); // Inicializa o controller aqui
        listarprodutostabela();
        

        campoPesquisa.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filtrarEAtualizarTabela(campoPesquisa.getText().trim());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filtrarEAtualizarTabela(campoPesquisa.getText().trim());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // Não é necessário para JTextFields simples
            }
        });

        iconeTela();
    }

    
    // método para listar os produtos cadastrados no sitema
    public void listarprodutostabela() {
        List<Produtos> listaProdutos = produtosController.listarProdutos();
        Collections.sort(listaProdutos, Comparator.comparing(Produtos::getTipoProduto));

        DefaultTableModel modeloTabela = (DefaultTableModel) tabela_produto.getModel();
        modeloTabela.setRowCount(0); // Limpa as linhas da tabela

        // Adiciona os dados dos produtos, incluindo o ID na primeira coluna (índice 0)
        for (Produtos produto : listaProdutos) {
            modeloTabela.addRow(new Object[]{
                produto.getProdutoID(),             // ID do Produto (coluna 0 - oculta)
                produto.getTipoProduto(),    // Nome do Produto (coluna 1)
                produto.getCodigo(),         // Código do Produto (coluna 2)
                produto.getUnidadeVenda(),   // Unidade de Medida (coluna 3)
                produto.getEstoqueAtual(),   // Estoque Atual (coluna 4)
                produto.getCategoria(),       // Categoria (coluna 5)
                "Ver Detalhes" 
            });
        }
         configurarColunasTabela();
        // Ocultar a coluna do ID (coluna 0)
        //ocultarColunaID();
    }

    private void configurarColunasTabela() {
        TableColumnModel tcm = tabela_produto.getColumnModel();

        // Ocultar a coluna do ID (coluna 0)
        if (tcm.getColumnCount() > 0) {
            tcm.getColumn(0).setMinWidth(0);
            tcm.getColumn(0).setMaxWidth(0);
            tcm.getColumn(0).setPreferredWidth(0);
            tcm.getColumn(0).setResizable(false);
        }

        // Configurar a nova coluna de ação (será a última coluna)
        // O índice da última coluna será o número total de colunas - 1
        int colunaAcaoIndex = tabela_produto.getColumnCount() - 1;

        if (colunaAcaoIndex >= 0) { // Garante que a coluna existe
            TableColumn colunaAcao = tcm.getColumn(colunaAcaoIndex);
            colunaAcao.setHeaderValue("Acessar info."); // Nome do cabeçalho da nova coluna

            // Atribui o Renderer e o Editor customizados
            colunaAcao.setCellRenderer(new ButtonColumnRenderer());
            colunaAcao.setCellEditor(new ButtonColumnEditor(tabela_produto, user, this)); // Passa 'this' para a tela home
            
            // Opcional: Ajustar largura da coluna de ação
            colunaAcao.setPreferredWidth(80); // Um bom tamanho para um botão/ícone
            colunaAcao.setMaxWidth(100);
            colunaAcao.setMinWidth(60);
        }
    }
    // método para mudar o ícone da tela na execução
    private void iconeTela(){
        try {
            Image iconeTitulo = ImageIO.read(getClass().getResource("/imagens/logoGeral.png"));
            this.setIconImage(iconeTitulo);
          
        }catch(IOException ex){
            System.out.println("Erro ao importar icone: " + ex.getMessage());
        }
    }// fim do coneTela()

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela_produto = new javax.swing.JTable();
        campoPesquisa = new javax.swing.JTextField();
        labelTitulo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));

        tabela_produto.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tabela_produto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Identificação", "Nome do Produto", "Codigo do Produto", "Unidade de medida", "Estoque Atual", "Categoria", "Acessar Info."
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabela_produto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Tabela_Produto(evt);
            }
        });
        jScrollPane1.setViewportView(tabela_produto);

        campoPesquisa.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        campoPesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoPesquisaActionPerformed(evt);
            }
        });

        labelTitulo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        labelTitulo.setText("Digite o nome ou código do produto: ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(44, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 910, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelTitulo)
                            .addComponent(campoPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 599, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(212, 212, 212))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(81, Short.MAX_VALUE)
                .addComponent(labelTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(-20, 0, 980, 540));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Tabela_Produto(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tabela_Produto
     /**   if (evt.getClickCount() == 2) { // Detecta clique duplo na linha
            int linhaSelecionada = tabela_produto.getSelectedRow();
            if (linhaSelecionada != -1) { // Verifica se alguma linha foi selecionada
                // Obter o ID do produto da coluna 0 (que agora contém o ID)
                int produtoId = (int) tabela_produto.getModel().getValueAt(linhaSelecionada, 0);

                // Abre a tela de detalhes do produto, passando o ID e o usuário logado
                // e uma referência para a tela home para que ela possa ser atualizada
                ProdutosView detailView = new ProdutosView(produtoId, user, this);
                detailView.setVisible(true);
            }
        }**/
    }//GEN-LAST:event_Tabela_Produto

    
    private void campoPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoPesquisaActionPerformed
        // TODO add your handling code here
    }//GEN-LAST:event_campoPesquisaActionPerformed
    
    private void filtrarEAtualizarTabela(String textoPesquisa) {
        List<Produtos> produtosFiltrados = produtosController.listarProdutosNome(textoPesquisa);
        atualizarTabela(produtosFiltrados);
    }
    
    private void atualizarTabela(List<Produtos> produtos) {
        Collections.sort(produtos, Comparator.comparing(Produtos::getTipoProduto));

        DefaultTableModel model = (DefaultTableModel) tabela_produto.getModel();
        model.setRowCount(0); // Limpa a tabela antes de adicionar os novos dados
        for (Produtos p : produtos) {
            model.addRow(new Object[]{
                p.getProdutoID(),
                p.getTipoProduto(),
                p.getCodigo(),
                p.getUnidadeVenda(),
                p.getEstoqueAtual(),
                p.getCategoria(),
                "Ver Detalhes" // Adicione o valor para a nova coluna de ação
            });
        }
        configurarColunasTabela(); // Chame para reconfigurar as colunas (ocultar ID e botão)
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Tela_home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Tela_home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Tela_home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Tela_home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Usuarios user = null;
                new Tela_home(user).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField campoPesquisa;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JTable tabela_produto;
    // End of variables declaration//GEN-END:variables
}
