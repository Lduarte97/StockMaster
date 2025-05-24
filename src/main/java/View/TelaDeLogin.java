/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Controller.LoginController;
import Model.Usuarios;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


/**
 *
 * @author devmat
 */
public class TelaDeLogin extends javax.swing.JFrame {

    /**
     * Creates new form TelaDeLogin
     */
    public TelaDeLogin() {
        initComponents();
        setLocationRelativeTo(null); // Centraliza a tela
        iconeTela();
        carregarDadosSalvos();
    }
    // método que muda o ícone da tela na hora da execução
    private void iconeTela(){
        try {
            Image iconeTitulo = ImageIO.read(getClass().getResource("/imagens/logoGeral.png"));
            this.setIconImage(iconeTitulo);
          
        }catch(IOException ex){
            System.out.println("Erro ao importar icone: " + ex.getMessage());
        }
    }// fim do iconeTela()
    
    // método para capturar os dados do usuário logado e salvar
     private void carregarDadosSalvos() {
        String[] dadosSalvos = MantenhaConectado.carregarLogin();
        if (dadosSalvos != null) {
            campoLogin.setText(dadosSalvos[0]);
            campoSenha.setText(dadosSalvos[1]);
            mantenhaConc.setSelected(true);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Painel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        BotaoEntrar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        campoLogin = new javax.swing.JTextField();
        campoSenha = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        jLabelTitulo = new javax.swing.JLabel();
        mantenhaConc = new javax.swing.JCheckBox();

        Painel2.setBackground(new java.awt.Color(0, 0, 0));
        Painel2.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout Painel2Layout = new javax.swing.GroupLayout(Painel2);
        Painel2.setLayout(Painel2Layout);
        Painel2Layout.setHorizontalGroup(
            Painel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 330, Short.MAX_VALUE)
        );
        Painel2Layout.setVerticalGroup(
            Painel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 370, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(640, 580));

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/logo.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 400, 360));

        BotaoEntrar.setBackground(new java.awt.Color(0, 0, 255));
        BotaoEntrar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        BotaoEntrar.setForeground(new java.awt.Color(255, 255, 255));
        BotaoEntrar.setText("Entrar");
        BotaoEntrar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BotaoEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoEntrarActionPerformed(evt);
            }
        });

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Usuario:");

        campoLogin.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        campoLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoLoginActionPerformed(evt);
            }
        });

        campoSenha.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        campoSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoSenhaActionPerformed(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setText("Senha:");

        jLabelTitulo.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabelTitulo.setText("Bem vindo! ");

        mantenhaConc.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        mantenhaConc.setText("Mantenha-me conectado");
        mantenhaConc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mantenhaConcActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(mantenhaConc)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campoLogin, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                            .addComponent(campoSenha)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(129, 129, 129)
                        .addComponent(jLabelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(80, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(BotaoEntrar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(142, 142, 142))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabelTitulo)
                .addGap(49, 49, 49)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(campoLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(mantenhaConc)
                .addGap(39, 39, 39)
                .addComponent(BotaoEntrar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 0, 400, 380));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void campoLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoLoginActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoLoginActionPerformed

    private void campoSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoSenhaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoSenhaActionPerformed

    private void BotaoEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoEntrarActionPerformed
        // TODO add your handling code here:
         // TODO add your handling code here:
    String user = campoLogin.getText();
    String password = new String(campoSenha.getPassword());
    LoginController controller = new LoginController();
    Usuarios logou = controller.authenticate(user, password);

    // Lógica do "Manter-me Conectado"
    if (logou != null) {
        if (mantenhaConc.isSelected()) {
            MantenhaConectado.salvarLogin(user, password);
        } else {
            MantenhaConectado.limparLogin();
        }
    }

    ImageIcon iconSucesso = new ImageIcon(getClass().getResource("/imagens/sucesso.png"));
    ImageIcon iconNegacao = new ImageIcon(getClass().getResource("/imagens/delete.png"));

    if (logou != null && logou.getIsAdmin() == 1) {
        String mensagem = String.format("Usuário logado com sucesso!\n  Bem-vindo, %s", logou.getNomeDeUsuario());
        JOptionPane.showMessageDialog(null, mensagem, "Login Bem-Sucedido", JOptionPane.INFORMATION_MESSAGE, iconSucesso);
        // chamar tela do admin
        Tela_home telahome = new Tela_home(logou);
        // deixando a tela visivel
        telahome.setVisible(true);
        dispose();
    } else if (logou != null && logou.getIsAdmin() == 0) {
        String mensagem = String.format("Usuário logado com sucesso!\n  Bem-vindo, %s", logou.getNomeDeUsuario());
        JOptionPane.showMessageDialog(null, mensagem, "Login Bem-Sucedido", JOptionPane.INFORMATION_MESSAGE, iconSucesso);
        // chamar tela usuario super admim
        Tela_home telahome = new Tela_home(logou);
        // deixando a tela visivel
        telahome.setVisible(true);
        dispose();
    } else {
        JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos!", "Erro de Login", JOptionPane.ERROR_MESSAGE, iconNegacao);
    }
    }//GEN-LAST:event_BotaoEntrarActionPerformed

    private void mantenhaConcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mantenhaConcActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mantenhaConcActionPerformed

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
            java.util.logging.Logger.getLogger(TelaDeLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaDeLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaDeLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaDeLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaDeLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotaoEntrar;
    private javax.swing.JPanel Painel2;
    private javax.swing.JTextField campoLogin;
    private javax.swing.JPasswordField campoSenha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JCheckBox mantenhaConc;
    // End of variables declaration//GEN-END:variables

    
}
