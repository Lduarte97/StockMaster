/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */ 
package View;

import Controller.ProdutosController;
import Model.Produtos;
import Model.Usuarios;
import controller.MovimentacaoController;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.time.LocalDateTime;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import model.MovimentacaoEstoque;


public class TelaEntradaSaida extends javax.swing.JFrame {
   private ButtonGroup group;
   private int userType;
   private int idproduto = -1;
   Usuarios user;
    
   ProdutosController produtosController = new ProdutosController();
    
    
    public TelaEntradaSaida(Usuarios u) {
        initComponents();
        user = u; 
        // Chamada do menu superior
        setJMenuBar(MenuSuperior.criarMenu(this, user));
        produtosController = new ProdutosController();
        selecionarApenasumBotao();
        listarprodutostabela();
        iconeTela();
        Menu_adm_outras_telas menu = new Menu_adm_outras_telas();
        setJMenuBar(menu.getMenuBarInstance());
        
        // Exemplo: Atribuindo uma ação diretamente ao campo de pesquisa
         // Adiciona o DocumentListener ao campo de pesquisa
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
    }// fim do TelaEntradaSaida
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        campoPesquisa = new javax.swing.JTextField();
        campoImagemProduto = new javax.swing.JLabel();
        textoCampoPesquisa = new javax.swing.JLabel();
        campoProduto = new javax.swing.JTextField();
        campoCodigo = new javax.swing.JTextField();
        campoUnidademedida = new javax.swing.JTextField();
        campoCategoria = new javax.swing.JTextField();
        labelNome = new javax.swing.JLabel();
        labelCodigo = new javax.swing.JLabel();
        labelUndmedida = new javax.swing.JLabel();
        labelCategoria = new javax.swing.JLabel();
        labelQuantidade = new javax.swing.JLabel();
        botaoMovimentarEstoque = new javax.swing.JButton();
        quantidade = new javax.swing.JComboBox<>();
        tituloPágina = new javax.swing.JLabel();
        radioButtonSaida = new javax.swing.JRadioButton();
        radioButtonEntrada = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela_produto = new javax.swing.JTable();
        botaoLimpar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(940, 650));
        setMinimumSize(new java.awt.Dimension(940, 650));
        setPreferredSize(new java.awt.Dimension(940, 650));

        jPanel1.setMaximumSize(new java.awt.Dimension(940, 650));
        jPanel1.setMinimumSize(new java.awt.Dimension(940, 650));
        jPanel1.setPreferredSize(new java.awt.Dimension(940, 650));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        campoPesquisa.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        campoPesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoPesquisaActionPerformed(evt);
            }
        });
        jPanel1.add(campoPesquisa, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 390, 30));

        campoImagemProduto.setBackground(new java.awt.Color(51, 0, 204));
        campoImagemProduto.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jPanel1.add(campoImagemProduto, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 390, 130));

        textoCampoPesquisa.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        textoCampoPesquisa.setText("Digite o código ou nome do produto:");
        jPanel1.add(textoCampoPesquisa, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 270, -1));

        campoProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoProdutoActionPerformed(evt);
            }
        });
        jPanel1.add(campoProduto, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 180, 400, 30));

        campoCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoCodigoActionPerformed(evt);
            }
        });
        jPanel1.add(campoCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 240, 200, 30));
        jPanel1.add(campoUnidademedida, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 240, 180, 30));

        campoCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoCategoriaActionPerformed(evt);
            }
        });
        jPanel1.add(campoCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 310, 290, 30));

        labelNome.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        labelNome.setText("Nome:");
        jPanel1.add(labelNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 160, 50, -1));

        labelCodigo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        labelCodigo.setText("Código:");
        jPanel1.add(labelCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 220, 70, -1));

        labelUndmedida.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        labelUndmedida.setText("Und Medida:");
        jPanel1.add(labelUndmedida, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 220, 90, 20));

        labelCategoria.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        labelCategoria.setText("Categoria:");
        jPanel1.add(labelCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 290, 110, -1));

        labelQuantidade.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        labelQuantidade.setText("Qtd:");
        jPanel1.add(labelQuantidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 290, 40, -1));

        botaoMovimentarEstoque.setBackground(new java.awt.Color(0, 153, 102));
        botaoMovimentarEstoque.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        botaoMovimentarEstoque.setText("Mov. Estoque");
        botaoMovimentarEstoque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoMovimentarEstoqueActionPerformed(evt);
            }
        });
        jPanel1.add(botaoMovimentarEstoque, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 450, 120, 30));

        quantidade.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        quantidade.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "61", "62", "63", "64", "65", "66", "67", "68", "69", "70", "71", "72", "73", "74", "75", "76", "77", "78", "79", "80", "81", "82", "83", "84", "85", "86", "87", "88", "89", "90", "91", "92", "93", "94", "95", "96", "97", "98", "99", "100" }));
        quantidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quantidadeActionPerformed(evt);
            }
        });
        jPanel1.add(quantidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 310, 80, 30));

        tituloPágina.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        tituloPágina.setText("Entrada e saída de estoque");
        jPanel1.add(tituloPágina, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 40, 250, 30));

        radioButtonSaida.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        radioButtonSaida.setText("Saída");
        radioButtonSaida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButtonSaidaActionPerformed(evt);
            }
        });
        jPanel1.add(radioButtonSaida, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 100, -1, -1));

        radioButtonEntrada.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        radioButtonEntrada.setText("Entrada");
        radioButtonEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButtonEntradaActionPerformed(evt);
            }
        });
        jPanel1.add(radioButtonEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 100, -1, -1));

        tabela_produto.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tabela_produto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Identificação", "Nome do produto", "Código do produto", "Unidade de medida", "Estoque Atual", "Categoria"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabela_produto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabela_produtoTabela_Produto(evt);
            }
        });
        jScrollPane1.setViewportView(tabela_produto);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 370, 700, 220));

        botaoLimpar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        botaoLimpar.setText("Limpar Campos");
        botaoLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoLimparActionPerformed(evt);
            }
        });
        jPanel1.add(botaoLimpar, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 530, 120, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Método para chamar o ícone da logo na t5ela  
    private void iconeTela(){  
                try {

                Image iconeTitulo = ImageIO.read(getClass().getResource("/imagens/logoGeral.png"));

                    this.setIconImage(iconeTitulo);

                } catch(IOException ex) {
                  System.out.println("Erro ao importar icone: " + ex.getMessage());
                }        

            }// fim do iconeTela()
    
        // método que permite ao usuáriomarcar apenas uma opção, ou entrada ou saída
        public void selecionarApenasumBotao(){
        
        group = new ButtonGroup();
        
        // Adiciona os botões ao grupo
        group.add(radioButtonSaida);
        group.add(radioButtonEntrada); 
        
        radioButtonSaida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userType = 0; 
            }
        });
        radioButtonEntrada.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userType = 1;    
            }
        });
    }// fim do selecionarApenasumBotao()
        
    // método para listar os produtos na tabela
    public void listarprodutostabela(){
      // Limpar a tabela antes de adicionar novos dados
     // Chamar o método listarProdutos do controller
        List<Produtos> listaProdutos = produtosController.listarProdutos();

        // Ordenar a lista de produtos por nome (TipoProduto)
        Collections.sort(listaProdutos, Comparator.comparing(Produtos::getTipoProduto));

        // Limpar a tabela antes de adicionar novos dados
        DefaultTableModel modeloTabela = (DefaultTableModel) tabela_produto.getModel();
        modeloTabela.setRowCount(0); // Limpa as linhas da tabela

        // Preencher a tabela com os dados dos produtos (incluindo o ID, mas sem exibi-lo)
        for (Produtos produto : listaProdutos) {
         modeloTabela.addRow(new Object[]{
          produto.getProdutoID(),        // Adiciona o ID do produto (índice 0 agora)
          produto.getTipoProduto(),        // Nome do Produto (era a coluna 1, agora índice 1)
          produto.getCodigo(),          // Código do Produto (era a coluna 2, agora índice 2)
          produto.getUnidadeVenda(),      // Unidade de Medida (era a coluna 3, agora índice 3)
          produto.getEstoqueAtual(),      // Estoque Atual (era a coluna 4, agora índice 4)
          produto.getCategoria()          // Categoria (era a coluna 5, agora índice 5)
         });
         // Chamada do método que oculta a coluna id
         ocultarColunaID();
        }

    }//fim do listarprodutostabela()
    
    /** método para ocultar a coluna IdProduto para deixar uma experiência
     visualmente melhor para o usuário */
    
    private void ocultarColunaID() {
        TableColumnModel tcm = tabela_produto.getColumnModel();
        if (tcm.getColumnCount() > 0) { // Verifica se há colunas na tabela
            // Ajusta o tamanho da coluna 0 (ID) para 0, ocultando-a
            tcm.getColumn(0).setMinWidth(0);
            tcm.getColumn(0).setMaxWidth(0);
            tcm.getColumn(0).setPreferredWidth(0);
            tcm.getColumn(0).setResizable(false); // Opcional: impede que o usuário redimensione
        }
    }
    private void campoProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoProdutoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoProdutoActionPerformed

    private void campoCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoCategoriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoCategoriaActionPerformed

    private void campoCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoCodigoActionPerformed
    // variável para receber a opção definida pelo radiobutton
    private int movimentacao ;
    private void radioButtonEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButtonEntradaActionPerformed
        // TODO add your handling code here:
        movimentacao = 1;
    }//GEN-LAST:event_radioButtonEntradaActionPerformed

    private void campoPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoPesquisaActionPerformed
        // TODO add your handling code here:
            String pesquisa = campoPesquisa.getText().trim();
    }//GEN-LAST:event_campoPesquisaActionPerformed
    private void filtrarEAtualizarTabela(String textoPesquisa) {
        List<Produtos> produtosFiltrados = produtosController.listarProdutosNome(textoPesquisa);
        atualizarTabela(produtosFiltrados);
    }

    private void atualizarTabela(List<Produtos> produtos) {
 
    DefaultTableModel model = (DefaultTableModel) tabela_produto.getModel();
    model.setRowCount(0); // Limpa a tabela antes de adicionar os novos dados
    for (Produtos p : produtos) {
        model.addRow(new Object[]{
            p.getProdutoID(),
            p.getTipoProduto(),     // Nome do Produto (será a coluna 0 após remover "Id")
            p.getCodigo(),           // Código do Produto (será a coluna 1 após remover "Id")
            p.getUnidadeVenda(),     // Unidade de Medida (será a coluna 2 após remover "Id")
            p.getEstoqueAtual(),     // Estoque Atual (será a coluna 3 após remover "Id")
            p.getCategoria()        // Categoria (será a coluna 4 após remover "Id")
        });
    }
}
    private void quantidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quantidadeActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_quantidadeActionPerformed

    private void radioButtonSaidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButtonSaidaActionPerformed
        // TODO add your handling code here:
       movimentacao = 2;
    }//GEN-LAST:event_radioButtonSaidaActionPerformed

    private void botaoMovimentarEstoqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoMovimentarEstoqueActionPerformed
    // Verifica se um produto foi selecionado na tabela
    if (idproduto == -1) {
        JOptionPane.showMessageDialog(null, "Selecione um produto na tabela!", "Erro", JOptionPane.ERROR_MESSAGE);
        return; // Impede a movimentação se nenhum produto for selecionado
    }
    // Verificar se o usuário está nulo
    else if (user == null) {
        JOptionPane.showMessageDialog(null, "Usuário não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
        return; // Retorna para evitar o processo de movimentação
    } else {

    String nomeProduto = campoProduto.getText();
    String codigoProduto = campoCodigo.getText();
    int quantidadeItem = Integer.parseInt(quantidade.getSelectedItem().toString());

    // Captura a data e hora atuais
    LocalDateTime dataHoraAtual = LocalDateTime.now();

    // Verificar se os valores são válidos
    System.out.println("ID Produto: " + idproduto);
    System.out.println("Tipo Movimentação: " + movimentacao);
    System.out.println("Código Solicitante: " + user.getCodigoUsuario());
    System.out.println("Código Produto: " + codigoProduto);
    System.out.println("Quantidade: " + quantidadeItem);
    System.out.println("ID Usuário: " + user.getIdUsuario());
    System.out.println("NomeProduto: " + nomeProduto);
    System.out.println("DataMov: " + dataHoraAtual); // Imprime a data capturada

    MovimentacaoController movimentacaoController = new MovimentacaoController();

    MovimentacaoEstoque movimentacaoEst = new MovimentacaoEstoque();
    movimentacaoEst.setIdProduto(idproduto);
    movimentacaoEst.setTipoMovimentacao(movimentacao);
    movimentacaoEst.setCodigoSolicitante(user.getCodigoUsuario());
    movimentacaoEst.setCodigoProduto(codigoProduto);
    movimentacaoEst.setQuantidade(quantidadeItem);
    movimentacaoEst.setIdUsuario(user.getIdUsuario());
    movimentacaoEst.setNomeProduto(nomeProduto);
    movimentacaoEst.setDataMov(dataHoraAtual); // Atribui a data capturada ao objeto

    // Tentativa de registrar a movimentação
    movimentacaoController.registrarMovimentacao(movimentacaoEst);
    // Limpa os campos após registrar a movimentação
    campoProduto.setText("");
    campoCodigo.setText("");
    campoUnidademedida.setText("");
    campoCategoria.setText("");
    group.clearSelection();
   }// fim do else
    
    // Faz atualização da tabela no estoque atual imediatamente
    listarprodutostabela();
    }//GEN-LAST:event_botaoMovimentarEstoqueActionPerformed

    private void tabela_produtoTabela_Produto(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabela_produtoTabela_Produto

        //Captura a linha selecionada da tabela
        int linhaSelecionada = tabela_produto.getSelectedRow();

        // Verifica se a linha selecionada é válida
        if (linhaSelecionada != -1) {
         // Recupera o ID do produto da primeira coluna (índice 0)
         idproduto = (int) tabela_produto.getValueAt(linhaSelecionada, 0);

         // Recupera os outros dados da linha selecionada (os índices mudaram!)
         String nomeProduto = tabela_produto.getValueAt(linhaSelecionada, 1).toString();
         String codigoProduto = tabela_produto.getValueAt(linhaSelecionada, 2).toString();
         String unidadeVenda = tabela_produto.getValueAt(linhaSelecionada, 3).toString();
         String estoqueAtual = tabela_produto.getValueAt(linhaSelecionada, 4).toString();
         String categoria = tabela_produto.getValueAt(linhaSelecionada, 5).toString();

         // Preenche os campos de texto com os dados da linha
         campoProduto.setText(nomeProduto);
         campoCodigo.setText(codigoProduto);
         campoUnidademedida.setText(unidadeVenda);
         campoCategoria.setText(categoria);
        }// fim do if

    }//GEN-LAST:event_tabela_produtoTabela_Produto

    private void botaoLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoLimparActionPerformed
        // TODO add your handling code here:
        campoProduto.setText("");
        campoCodigo.setText("");
        campoUnidademedida.setText("");
        campoCategoria.setText("");
        group.clearSelection();
    }//GEN-LAST:event_botaoLimparActionPerformed

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
            java.util.logging.Logger.getLogger(TelaEntradaSaida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaEntradaSaida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaEntradaSaida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaEntradaSaida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Usuarios user = null;
                new TelaEntradaSaida(user).setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoLimpar;
    private javax.swing.JButton botaoMovimentarEstoque;
    private javax.swing.JTextField campoCategoria;
    private javax.swing.JTextField campoCodigo;
    private javax.swing.JLabel campoImagemProduto;
    private javax.swing.JTextField campoPesquisa;
    private javax.swing.JTextField campoProduto;
    private javax.swing.JTextField campoUnidademedida;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelCategoria;
    private javax.swing.JLabel labelCodigo;
    private javax.swing.JLabel labelNome;
    private javax.swing.JLabel labelQuantidade;
    private javax.swing.JLabel labelUndmedida;
    private javax.swing.JComboBox<String> quantidade;
    private javax.swing.JRadioButton radioButtonEntrada;
    private javax.swing.JRadioButton radioButtonSaida;
    private javax.swing.JTable tabela_produto;
    private javax.swing.JLabel textoCampoPesquisa;
    private javax.swing.JLabel tituloPágina;
    // End of variables declaration//GEN-END:variables
}