/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;


import Controller.ProdutosController;
import Model.Produtos;
import Model.Usuarios;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author devmat
 */
public final class TelaDeCadastroDeProdutos extends javax.swing.JFrame {
   
  

    /**
     * Creates new form TelaDeCadastroDeProdutos
     */Usuarios user;
    public TelaDeCadastroDeProdutos(Usuarios u) {
        user = u;
        initComponents();
        capturarimagem();
        iconeTela();
        //Chamada do menu superior
         Menu_adm_outras_telas menu = new Menu_adm_outras_telas();
          setJMenuBar(MenuSuperior.criarMenu(this, user));
    }
    // método que permite ao usuário adicionar uma imagem no cadastro de produtos
     public void capturarimagem() {
    buscarFoto.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Escolha uma imagem");
            fileChooser.setAcceptAllFileFilterUsed(false);
            fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Imagens JPG & PNG", "jpg", "png"));

            int returnValue = fileChooser.showOpenDialog(TelaDeCadastroDeProdutos.this);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                try {
                    // Carregar a imagem selecionada
                    Image image = ImageIO.read(selectedFile);
                    ImageIcon icon = new ImageIcon(image.getScaledInstance(200, 200, Image.SCALE_SMOOTH)); // Ajustando tamanho da imagem
                    
                    // Atualizando o JLabel IMAGEM para exibir a imagem
                    textFotoProduto.setIcon(icon);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }// fim do try/Catrch
            }// fim do if
        }// fim do actionPerformed
    });

    // Criando o botão para remover a foto
    limparFoto.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            // Remove a imagem do JLabel
            textFotoProduto.setIcon(null);
        }
    });
}// fim do capturarimagem()
 
     // método que muda o ícone da tela na hora da execução
     private void iconeTela(){  
        try {

        Image iconeTitulo = ImageIO.read(getClass().getResource("/imagens/logoGeral.png"));
       
            this.setIconImage(iconeTitulo);
           
        } catch(IOException ex) {
          System.out.println("Erro ao importar icone: " + ex.getMessage());
        }        
    }//fim do iconeTela()

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        textDataCadastro = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        textMarcaProduto = new javax.swing.JTextField();
        textPrecoDistribuidora = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        descricao = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        textPrecoAtacado = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        limparFoto = new javax.swing.JButton();
        textFotoProduto = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        buscarFoto = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        textPrecoVarejo = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        textEstoqueAtual = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        textPrecoCusto = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        textEstoqueMaximo = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        textEstoqueMinimo = new javax.swing.JTextField();
        tipoDeproduto = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        textUnidadeVenda = new javax.swing.JTextField();
        cadastrarProdutos = new javax.swing.JButton();
        textCategoria = new javax.swing.JTextField();
        tipoDeProduto1 = new javax.swing.JTextField();
        textCodigoFornecedor1 = new javax.swing.JTextField();
        textCodigo1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabelTitulo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(940, 580));
        setMinimumSize(new java.awt.Dimension(940, 580));

        jPanel1.setMaximumSize(new java.awt.Dimension(940, 580));
        jPanel1.setMinimumSize(new java.awt.Dimension(940, 580));
        jPanel1.setPreferredSize(new java.awt.Dimension(940, 580));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setText("Categoria :");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 200, 90, -1));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setText("Cód. do produto:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 200, 120, -1));

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setText("Cód. Fornecedor :");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 200, -1, -1));

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setText("Preço da Dist :");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 280, 110, -1));
        jPanel1.add(textDataCadastro, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 220, 110, 30));

        jLabel7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel7.setText("Data do Cad. :");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 200, 100, -1));
        jPanel1.add(textMarcaProduto, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 140, 150, 30));

        textPrecoDistribuidora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textPrecoDistribuidoraActionPerformed(evt);
            }
        });
        jPanel1.add(textPrecoDistribuidora, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 300, 120, 30));

        jLabel8.setText("R$ ");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 300, 20, -1));

        jLabel9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel9.setText("Estoque Atual  :");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 360, 120, -1));

        descricao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                descricaoActionPerformed(evt);
            }
        });
        jPanel1.add(descricao, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 450, 550, 100));

        jLabel10.setText("R$ ");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, 20, -1));

        jLabel11.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel11.setText("Preço de Atac :");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 280, 110, -1));

        textPrecoAtacado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textPrecoAtacadoActionPerformed(evt);
            }
        });
        jPanel1.add(textPrecoAtacado, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 300, 110, 30));

        jLabel12.setText("R$ ");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 300, 20, -1));

        limparFoto.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        limparFoto.setText("Limpar Foto");
        jPanel1.add(limparFoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 360, -1, -1));

        textFotoProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFotoProdutoActionPerformed(evt);
            }
        });
        jPanel1.add(textFotoProduto, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 130, 250, 200));

        jLabel13.setText("R$ ");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 300, 20, -1));

        buscarFoto.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        buscarFoto.setText("Buscar Foto ");
        buscarFoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarFotoActionPerformed(evt);
            }
        });
        jPanel1.add(buscarFoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 360, -1, -1));

        jLabel14.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel14.setText("Preço de Varj:");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 280, 110, -1));

        textPrecoVarejo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textPrecoVarejoActionPerformed(evt);
            }
        });
        jPanel1.add(textPrecoVarejo, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 300, 110, 30));

        jLabel16.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel16.setText("Preço de Custo :");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 280, 130, -1));

        textEstoqueAtual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textEstoqueAtualActionPerformed(evt);
            }
        });
        jPanel1.add(textEstoqueAtual, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 380, 120, 30));

        jLabel17.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel17.setText("Descrições :");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 430, 110, -1));

        textPrecoCusto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textPrecoCustoActionPerformed(evt);
            }
        });
        jPanel1.add(textPrecoCusto, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 300, 120, 30));

        jLabel18.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel18.setText("Estoque Max:");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 360, 100, -1));

        textEstoqueMaximo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textEstoqueMaximoActionPerformed(evt);
            }
        });
        jPanel1.add(textEstoqueMaximo, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 380, 110, 30));

        jLabel19.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel19.setText("Estoque Min :");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 360, 100, -1));

        textEstoqueMinimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textEstoqueMinimoActionPerformed(evt);
            }
        });
        jPanel1.add(textEstoqueMinimo, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 380, 120, 30));

        tipoDeproduto.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tipoDeproduto.setText("Nome do Produto :");
        jPanel1.add(tipoDeproduto, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, 140, -1));
        jPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 10, 30, 20));

        jLabel22.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel22.setText("Und de Venda :");
        jPanel1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 120, 110, -1));
        jPanel1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 161, 110, -1));

        textUnidadeVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textUnidadeVendaActionPerformed(evt);
            }
        });
        jPanel1.add(textUnidadeVenda, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 140, 110, 30));

        cadastrarProdutos.setBackground(new java.awt.Color(0, 102, 51));
        cadastrarProdutos.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        cadastrarProdutos.setText("Cadastrar");
        cadastrarProdutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cadastrarProdutosActionPerformed(evt);
            }
        });
        jPanel1.add(cadastrarProdutos, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 470, -1, 30));
        jPanel1.add(textCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 220, 140, 30));
        jPanel1.add(tipoDeProduto1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, 270, 30));

        textCodigoFornecedor1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textCodigoFornecedor1ActionPerformed(evt);
            }
        });
        jPanel1.add(textCodigoFornecedor1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 220, 130, 30));

        textCodigo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textCodigo1ActionPerformed(evt);
            }
        });
        jPanel1.add(textCodigo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, 120, 30));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setText("Marca do Produto: ");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 120, -1, -1));

        jLabelTitulo.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabelTitulo.setText("Cadastro de produto");
        jPanel1.add(jLabelTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 70, 200, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void textPrecoDistribuidoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textPrecoDistribuidoraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textPrecoDistribuidoraActionPerformed

    private void descricaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_descricaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_descricaoActionPerformed

    private void textPrecoAtacadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textPrecoAtacadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textPrecoAtacadoActionPerformed

    private void buscarFotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarFotoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buscarFotoActionPerformed

    private void textPrecoVarejoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textPrecoVarejoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textPrecoVarejoActionPerformed

    private void textEstoqueAtualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textEstoqueAtualActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textEstoqueAtualActionPerformed

    private void textPrecoCustoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textPrecoCustoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textPrecoCustoActionPerformed

    private void textEstoqueMaximoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textEstoqueMaximoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textEstoqueMaximoActionPerformed

    private void textEstoqueMinimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textEstoqueMinimoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textEstoqueMinimoActionPerformed

    private void textFotoProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFotoProdutoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFotoProdutoActionPerformed

    private void cadastrarProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cadastrarProdutosActionPerformed
        ProdutosController controller = new ProdutosController();
try {
    // Obtendo valores dos campos de texto e convertendo-os conforme necessário
    String tipoProduto = tipoDeProduto1.getText();
    String marcaProduto = textMarcaProduto.getText();
    String codigo = textCodigo1.getText();
    String codigoFornecedor = textCodigoFornecedor1.getText();
    String unidadeVenda = textUnidadeVenda.getText();
    String dataCadastro = textDataCadastro.getText();
    double precoCusto = Double.parseDouble(textPrecoCusto.getText());
    double precoAtacado = Double.parseDouble(textPrecoAtacado.getText());
    double precoVarejo = Double.parseDouble(textPrecoVarejo.getText());
    double precoDistribuidora = Double.parseDouble(textPrecoDistribuidora.getText());
    int estoqueMaximo = Integer.parseInt(textEstoqueMaximo.getText());
    int estoqueMinimo = Integer.parseInt(textEstoqueMinimo.getText());
    int estoqueAtual = Integer.parseInt(textEstoqueAtual.getText());
    String fotoProduto = textFotoProduto.getText();
    String categoria = textCategoria.getText(); // Obtendo valor da Categoria

    // Criando um objeto Produto e configurando seus atributos
    Produtos produto = new Produtos();
    produto.setTipoProduto(tipoProduto);
    produto.setMarcaProduto(marcaProduto);
    produto.setCodigo(codigo);
    produto.setCodigoFornecedor(codigoFornecedor);
    produto.setUnidadeVenda(unidadeVenda);
    produto.setDataCadastro(dataCadastro);
    produto.setPrecoCusto(precoCusto);
    produto.setPrecoAtacado(precoAtacado);
    produto.setPrecoVarejo(precoVarejo);
    produto.setPrecoDistribuidora(precoDistribuidora);
    produto.setEstoqueMaximo(estoqueMaximo);
    produto.setEstoqueMinimo(estoqueMinimo);
    produto.setEstoqueAtual(estoqueAtual);
    produto.setFotoProduto(fotoProduto);
    produto.setCategoria(categoria); // Adicionando Categoria

    // Mandando os dados do produto para o método de cadastro
    boolean cadastrou = controller.cadastrandoProdutos(produto);
    if (cadastrou) {
        JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
    } else {
        JOptionPane.showMessageDialog(null, "Produto não cadastrado.");
    }// fim do if else

} catch (Exception e) {
    System.err.print("Erro ao cadastrar: " + e);
}// fimd o try/catch


    }//GEN-LAST:event_cadastrarProdutosActionPerformed

    private void textUnidadeVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textUnidadeVendaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textUnidadeVendaActionPerformed

    private void textCodigoFornecedor1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textCodigoFornecedor1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textCodigoFornecedor1ActionPerformed

    private void textCodigo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textCodigo1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textCodigo1ActionPerformed

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
            java.util.logging.Logger.getLogger(TelaDeCadastroDeProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaDeCadastroDeProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaDeCadastroDeProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaDeCadastroDeProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Usuarios user = null;
                new TelaDeCadastroDeProdutos(user).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buscarFoto;
    private javax.swing.JButton cadastrarProdutos;
    private javax.swing.JTextField descricao;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton limparFoto;
    private javax.swing.JTextField textCategoria;
    private javax.swing.JTextField textCodigo1;
    private javax.swing.JTextField textCodigoFornecedor1;
    private javax.swing.JTextField textDataCadastro;
    private javax.swing.JTextField textEstoqueAtual;
    private javax.swing.JTextField textEstoqueMaximo;
    private javax.swing.JTextField textEstoqueMinimo;
    private javax.swing.JButton textFotoProduto;
    private javax.swing.JTextField textMarcaProduto;
    private javax.swing.JTextField textPrecoAtacado;
    private javax.swing.JTextField textPrecoCusto;
    private javax.swing.JTextField textPrecoDistribuidora;
    private javax.swing.JTextField textPrecoVarejo;
    private javax.swing.JTextField textUnidadeVenda;
    private javax.swing.JTextField tipoDeProduto1;
    private javax.swing.JLabel tipoDeproduto;
    // End of variables declaration//GEN-END:variables
}
