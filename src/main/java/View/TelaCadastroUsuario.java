/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;


import Controller.UsuarioController;
import Model.Usuarios;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;


/**
 *
 * @author devmat
 */
public class TelaCadastroUsuario extends javax.swing.JFrame {
    Usuarios user;
private ButtonGroup group;
private int userType;
    /**
     * Creates new form TelaCadastroUsuario
     */
        
    public TelaCadastroUsuario(Usuarios u) {
        initComponents();
        user = u;
        botaoOcultarSenha.setIcon(new ImageIcon("D:/ProjetoStockMaster/imagens/ocultar.png"));
        selecionarApenasumBotao();
        iconeTela();
        
     // Chamada do menu na parte superior da página
     Menu_adm_outras_telas menu = new Menu_adm_outras_telas();
         setJMenuBar(MenuSuperior.criarMenu(this, user));
    }
    // método que permite ao usuário marcar apenas uma opção de usuário ou admin ou comum
    public void selecionarApenasumBotao(){
        
        group = new ButtonGroup();
        
        // Adiciona os botões ao grupo
        group.add(radiobtncomum);
        group.add(radionbtnadmim); 
        
        
        radiobtncomum.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userType = 0; // Usuário comum
                
            }
        });

        radionbtnadmim.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userType = 1; // Administrador
                
            }
        });
        
    }//fim do selecionarApenasumBotao()

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        campoNome = new javax.swing.JTextField();
        campoEmail = new javax.swing.JTextField();
        campoNomeDeUsuario = new javax.swing.JTextField();
        campoSenha = new javax.swing.JPasswordField();
        labelNome = new javax.swing.JLabel();
        labelEmail = new javax.swing.JLabel();
        labelNomeDeUsuario = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        labelSenha = new javax.swing.JLabel();
        botaoOcultarSenha = new javax.swing.JButton();
        botaoLimparCampos = new javax.swing.JButton();
        botaoCadastrar = new javax.swing.JButton();
        radionbtnadmim = new javax.swing.JRadioButton();
        radiobtncomum = new javax.swing.JRadioButton();
        tituloPágina = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(920, 580));

        jPanel1.setMaximumSize(new java.awt.Dimension(980, 540));
        jPanel1.setMinimumSize(new java.awt.Dimension(980, 540));
        jPanel1.setPreferredSize(new java.awt.Dimension(980, 540));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        campoNome.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jPanel2.add(campoNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 130, 378, 30));

        campoEmail.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jPanel2.add(campoEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 200, 378, 30));

        campoNomeDeUsuario.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jPanel2.add(campoNomeDeUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 270, 378, 30));

        campoSenha.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        campoSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoSenhaActionPerformed(evt);
            }
        });
        jPanel2.add(campoSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 340, 330, 31));

        labelNome.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        labelNome.setText("Nome Completo:");
        jPanel2.add(labelNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 130, -1, 30));

        labelEmail.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        labelEmail.setText("Email:");
        jPanel2.add(labelEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 200, -1, 30));

        labelNomeDeUsuario.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        labelNomeDeUsuario.setText("Nome de Usuário:");
        jPanel2.add(labelNomeDeUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 270, -1, 30));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setText("Tipo de Usuário:");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 390, -1, 30));

        labelSenha.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        labelSenha.setText("Senha:");
        jPanel2.add(labelSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 340, -1, 30));

        botaoOcultarSenha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/ocultar.png"))); // NOI18N
        botaoOcultarSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoOcultarSenhaActionPerformed(evt);
            }
        });
        jPanel2.add(botaoOcultarSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 340, 42, 31));

        botaoLimparCampos.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        botaoLimparCampos.setText("Limpar campos");
        botaoLimparCampos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoLimparCamposActionPerformed(evt);
            }
        });
        jPanel2.add(botaoLimparCampos, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 460, -1, 29));

        botaoCadastrar.setBackground(new java.awt.Color(0, 153, 153));
        botaoCadastrar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        botaoCadastrar.setText("Cadastrar");
        botaoCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCadastrarActionPerformed(evt);
            }
        });
        jPanel2.add(botaoCadastrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 460, -1, 29));

        radionbtnadmim.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        radionbtnadmim.setText("Admin");
        jPanel2.add(radionbtnadmim, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 400, -1, -1));

        radiobtncomum.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        radiobtncomum.setText("Comum");
        jPanel2.add(radiobtncomum, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 400, -1, -1));

        tituloPágina.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        tituloPágina.setText("Cadastro de Usuário");
        jPanel2.add(tituloPágina, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 70, 220, 30));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 980, 540));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // método que muda o ícone da tela na hora da execução
    private void iconeTela(){  
        try {

        Image iconeTitulo = ImageIO.read(getClass().getResource("/imagens/logoGeral.png"));
       
            this.setIconImage(iconeTitulo);
           
        } catch(IOException ex) {
          System.out.println("Erro ao importar icone: " + ex.getMessage());
        }        
   
    }// fim do iconeTela()
    private void botaoCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCadastrarActionPerformed
        // TODO add your handling code here:
         try{
        UsuarioController controller = new UsuarioController();
        // Recebe os dados informados e manda para o insert através do método CadastrarUsuário da Classe UsuarioController
        Usuarios usuario = new Usuarios(
                campoNome.getText(), 
                campoEmail.getText(),
                campoNomeDeUsuario.getText(),
                new String (campoSenha.getPassword()),
                userType 
        );
        Usuarios userCadastrado = controller.cadastrarUsuario(usuario);
        JOptionPane.showMessageDialog (null,"Usuário Cadastrado com Sucesso!"+userCadastrado.getCodigoUsuario());
        dispose();
        
        }catch(Exception e){
          JOptionPane.showMessageDialog(null,"Usuário Não Cadastrado!"+e);
        }//fim do catch
    }//GEN-LAST:event_botaoCadastrarActionPerformed

    private void botaoLimparCamposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoLimparCamposActionPerformed
        // TODO add your handling code here:
        // limpa os campos 
        botaoLimparCampos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoNome.setText("");
                campoEmail.setText("");
                campoNomeDeUsuario.setText("");
                campoSenha.setText("");
               // usuarioAdm.setSelected(false);
                //usuarioComum.setSelected(false);
            }
        });
    }//GEN-LAST:event_botaoLimparCamposActionPerformed
    boolean oculto=true;
    private void botaoOcultarSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoOcultarSenhaActionPerformed
        // TODO add your handling code here:
        // se senha estiver oculta
        if(oculto==true){
            campoSenha.setEchoChar((char)0);// desocultar senha
            oculto=false;// senha não está mais oculta
            botaoOcultarSenha.setIcon(new ImageIcon("D:/ProjetoStockMaster/imagens/mostrar.png"));
        }else{// se a senha não estiver oculta
            campoSenha.setEchoChar('\u2022');// ocultar senha
            oculto=true;// senha oculta novamente
            botaoOcultarSenha.setIcon(new ImageIcon("D:/ProjetoStockMaster/imagens/ocultar.png"));
        }// fim do else
    }//GEN-LAST:event_botaoOcultarSenhaActionPerformed

    private void campoSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoSenhaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoSenhaActionPerformed
    
   
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
            java.util.logging.Logger.getLogger(TelaCadastroUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Usuarios user = null;
                new TelaCadastroUsuario(user).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoCadastrar;
    private javax.swing.JButton botaoLimparCampos;
    private javax.swing.JButton botaoOcultarSenha;
    private javax.swing.JTextField campoEmail;
    private javax.swing.JTextField campoNome;
    private javax.swing.JTextField campoNomeDeUsuario;
    private javax.swing.JPasswordField campoSenha;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel labelEmail;
    private javax.swing.JLabel labelNome;
    private javax.swing.JLabel labelNomeDeUsuario;
    private javax.swing.JLabel labelSenha;
    private javax.swing.JRadioButton radiobtncomum;
    private javax.swing.JRadioButton radionbtnadmim;
    private javax.swing.JLabel tituloPágina;
    // End of variables declaration//GEN-END:variables
}
