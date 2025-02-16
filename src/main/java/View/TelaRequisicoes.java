/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Model.Usuarios;
import com.itextpdf.text.Document;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Requisicoes;

/**
 *
 * @author lldua
 */
public class TelaRequisicoes extends javax.swing.JFrame {
    
Usuarios user;
    public TelaRequisicoes(Usuarios u) {
        initComponents();
        user =u;
        iconeTela();
        // Chamada do menu superior
         Menu_adm_outras_telas menu = new Menu_adm_outras_telas();
         setJMenuBar(menu.getMenuBarInstance());
         setJMenuBar(MenuSuperior.criarMenu(this, user));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaRequisicoes = new javax.swing.JTable();
        botaoGerarPdf = new javax.swing.JButton();
        jLabelTítulo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(920, 580));

        jPanel1.setMinimumSize(new java.awt.Dimension(920, 580));

        tabelaRequisicoes.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tabelaRequisicoes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Nº da requição", "Produto", "Cód. do solicitante", "Quantidade", "Tipo", "Data", "Situação"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabelaRequisicoes);

        botaoGerarPdf.setBackground(new java.awt.Color(0, 153, 102));
        botaoGerarPdf.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        botaoGerarPdf.setText("Gerar PDF");
        botaoGerarPdf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoGerarPdfActionPerformed(evt);
            }
        });

        jLabelTítulo.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabelTítulo.setText("Relatório de requisições");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 854, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(348, 348, 348)
                        .addComponent(jLabelTítulo)))
                .addContainerGap(46, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(botaoGerarPdf, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(84, 84, 84))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(102, 102, 102)
                .addComponent(jLabelTítulo)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addComponent(botaoGerarPdf)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // método para mudar o ícone da tela na hora da execução
    private void iconeTela(){  
        try {

        Image iconeTitulo = ImageIO.read(getClass().getResource("/imagens/logoGeral.png"));
       
            this.setIconImage(iconeTitulo);
           
        } catch(IOException ex) {
          System.out.println("Erro ao importar icone: " + ex.getMessage());
        }        
   
    }// fim do iconeTela()
    
    // método para gerar um relatório em pdf dentro do botãoGerarPDF
    private void botaoGerarPdfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoGerarPdfActionPerformed
         String pdfPath = "Requisições.pdf";
        try{
            
            // criar o documento pdf
            Document documento = new Document();
            PdfWriter.getInstance(documento, new java.io.FileOutputStream(pdfPath));
           // abrir pdf 
           documento.open();
           
           // criando tabela no pdf
           // capturando a quantidade de colunas da minha tabela
           int colunas = tabelaRequisicoes.getColumnCount();
           // definindo a nossa tabela dentro do pdf
           PdfPTable tabela = new PdfPTable(colunas);
           // definindo a excala da tabela 100%, 80%, ou 50%
           tabela.setWidthPercentage(100);
           
           // adicionando o nome das colunas da tabela do pdf
           for(int i=0;i<colunas;i++){
               tabela.addCell(new Phrase(tabelaRequisicoes.getColumnName(i)));
           }//fim do for
           
           // adicionando os dados na tabela pdf
           DefaultTableModel modeloTabela = (DefaultTableModel) tabelaRequisicoes.getModel();
           
           // usando laço de repetição para inserir os dados
           for(int ln=0;ln<modeloTabela.getRowCount(); ln++){
               for(int c=0;c<modeloTabela.getColumnCount();c++){
                    // adicionando os dados dentro da tabela
                    Object valorcelula = modeloTabela.getValueAt(ln,c);
                    
                    tabela.addCell(valorcelula !=null ? valorcelula.toString():"");
                }// fim do 2º for
           }// fim do 1º for
           
           // adicionando a tabela dentro do pdf
           documento.add(tabela);
           
           // fechando o documento 
           documento.close();
           JOptionPane.showMessageDialog(this, "PDF GERADO COM SUCESSO");
           // abrir o pdf automaticamente
           
           File pdfFile = new File(pdfPath);
           if(pdfFile.exists()){
               if(Desktop.isDesktopSupported()){
                   
                   Desktop.getDesktop().open(pdfFile);
                   
               }else{
                   JOptionPane.showConfirmDialog(this,"Nenhum aplicativo suporta o PDF!");
                           
               }// fim do else
               
           }// fim do primeiro if
           
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Não foi possível PDF!");
        }// fim do try catch
        
       /** public void preencherTabelaRequisicoes() {
        DefaultTableModel modeloTabela = (DefaultTableModel) tabelaRequisicoes.getModel();
        modeloTabela.setRowCount(0); // Limpa a tabela para evitar dados duplicados

        // AQUI você deve fazer uma consulta para obter os dados da tabela de requisições
        // Vou criar uma lista de exemplo para representar os dados que você pode usar

        List<Requisicoes> requisicoes = obterRequisicoes(); // Exemplo de método que retorna uma lista de requisições

        // Preenchendo a tabela com os dados obtidos
        for (Requisicoes requisicao : requisicoes) {
            Object[] dados = {
                requisicao.getNumeroRequisicao(),
                requisicao.getProduto(),
                requisicao.getCodigoSolicitante(),
                requisicao.getQuantidade(),
                requisicao.getTipo(),
                requisicao.getData(),
                requisicao.getSituacao()
        };
        modeloTabela.addRow(dados);
    }
    }//GEN-LAST:event_botaoGerarPdfActionPerformed

    
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
            java.util.logging.Logger.getLogger(TelaRequisicoes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaRequisicoes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaRequisicoes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaRequisicoes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Usuarios user = null;
                new TelaRequisicoes(user).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoGerarPdf;
    private javax.swing.JLabel jLabelTítulo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelaRequisicoes;
    // End of variables declaration//GEN-END:variables
}
