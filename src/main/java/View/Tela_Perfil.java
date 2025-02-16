/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Controller.UsuarioController;
import Model.Usuarios;
import Controller.UsuarioController;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author devmat
 */
public class Tela_Perfil extends javax.swing.JFrame {

    /**
     * Creates new form Tela_Perfil
     */
    int idUsuario;
    Usuarios user;
    public Tela_Perfil(Usuarios u) {
        initComponents();
        user =u;
         Menu_adm_outras_telas menu = new Menu_adm_outras_telas();
         setJMenuBar(menu.getMenuBarInstance());
         setJMenuBar(MenuSuperior.criarMenu(this, user));
        Listagem();
        limparCampos();
    }
    private void limparCampos() {
            campoNome.setText("");         // Substitua pelos IDs dos seus campos
            campoNomeDeUsuario.setText("");
            campoEmail.setText("");
            campoSenha.setText("");
            campoNome.requestFocus();//quando limpa , o curso fica dentro do campo nome 
        }



    public void Listagem() {
    // Chamando o controller de usuários
    UsuarioController controller = new UsuarioController();
    
    // Capturando a lista de usuários que vem do banco de dados
    List<Usuarios> listaUsuarios = controller.listarUsuarioNome(""); // Você pode passar um nome de usuário aqui, ou deixar vazio para listar todos os usuários
    
    // Obtendo o modelo da tabela
    DefaultTableModel modeloTabela = (DefaultTableModel) TabelaUsuario.getModel();
    
    // Limpando a tabela antes de adicionar novos dados
    modeloTabela.setRowCount(0);
    
    // Verificando se a lista de usuários não está vazia
    if (listaUsuarios != null && !listaUsuarios.isEmpty()) {
        // Adicionando os dados na tabela
        for (Usuarios usuario : listaUsuarios) {
            // Criando uma nova linha para a tabela
            Object[] linha = {
                usuario.getIdUsuario(),
                usuario.getNome(), 
                usuario.getNomeDeUsuario(), // nome de usuário// nome do usuário
                usuario.getEmail(),       // email do usuário 
                usuario.getSenha()        // senha do usuário
            };
            // Adicionando a linha ao modelo da tabela
            modeloTabela.addRow(linha);
        }
    } else {
        JOptionPane.showMessageDialog(this, "Nenhum nome encontrado.");
    }
}//

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        ExcluirBotao = new javax.swing.JButton();
        botaoAtualizar = new javax.swing.JButton();
        campoNome = new javax.swing.JTextField();
        campoSenha = new javax.swing.JTextField();
        campoEmail = new javax.swing.JTextField();
        campoNomeDeUsuario = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        TabelaUsuario = new javax.swing.JTable();
        cadastrar_usuario = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setPreferredSize(new java.awt.Dimension(980, 540));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Tipo de Usuario:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 270, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Nome Completo:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 140, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Email:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 140, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Nome de Usuario:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 220, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Senha:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 210, -1, -1));

        jRadioButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jRadioButton1.setText("Comum");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jRadioButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 270, -1, -1));

        jRadioButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jRadioButton2.setText("Admin");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jRadioButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 270, -1, -1));

        ExcluirBotao.setBackground(new java.awt.Color(204, 0, 0));
        ExcluirBotao.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ExcluirBotao.setForeground(new java.awt.Color(255, 255, 255));
        ExcluirBotao.setText("Excluir conta");
        ExcluirBotao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExcluirBotaoActionPerformed(evt);
            }
        });
        jPanel1.add(ExcluirBotao, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 480, -1, 40));

        botaoAtualizar.setBackground(new java.awt.Color(102, 102, 102));
        botaoAtualizar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        botaoAtualizar.setForeground(new java.awt.Color(255, 255, 255));
        botaoAtualizar.setText("Atualizar");
        botaoAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoAtualizarActionPerformed(evt);
            }
        });
        jPanel1.add(botaoAtualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 480, 120, 40));
        jPanel1.add(campoNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 130, 220, 40));
        jPanel1.add(campoSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 200, 210, 40));
        jPanel1.add(campoEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 130, 210, 40));
        jPanel1.add(campoNomeDeUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 200, 220, 40));

        TabelaUsuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Id", "Nome Completo", "Nome de Usuario", "Email", "Senha"
            }
        ));
        TabelaUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Tabela_De_Listagem(evt);
            }
        });
        jScrollPane1.setViewportView(TabelaUsuario);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 310, 930, 140));

        cadastrar_usuario.setBackground(new java.awt.Color(0, 102, 51));
        cadastrar_usuario.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cadastrar_usuario.setForeground(new java.awt.Color(255, 255, 255));
        cadastrar_usuario.setText("Cadastrar usuario ");
        cadastrar_usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cadastrar_usuarioActionPerformed(evt);
            }
        });
        jPanel1.add(cadastrar_usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 480, -1, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void Tabela_De_Listagem(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tabela_De_Listagem
         int linhaSelecionada = TabelaUsuario.getSelectedRow();
    
    // Verifica se uma linha foi realmente selecionada
    if (linhaSelecionada >= 0) {  
        DefaultTableModel modeloTabela = (DefaultTableModel) TabelaUsuario.getModel();
        
        // Verifica se a célula do ID não é nula antes de converter
        Object idObj = modeloTabela.getValueAt(linhaSelecionada, 0);
        if (idObj != null) {
            idUsuario = Integer.parseInt(idObj.toString());
        }
        
        // Exibe o ID para depuração
        System.out.println("ID do usuário selecionado: " + idUsuario);
        
        // Preenche os campos de texto com os dados da tabela
        campoNome.setText(modeloTabela.getValueAt(linhaSelecionada, 1).toString());
        campoNomeDeUsuario.setText(modeloTabela.getValueAt(linhaSelecionada, 2).toString());
        campoEmail.setText(modeloTabela.getValueAt(linhaSelecionada, 3).toString());
        campoSenha.setText(modeloTabela.getValueAt(linhaSelecionada, 4).toString());
    }
    }//GEN-LAST:event_Tabela_De_Listagem

    private void botaoAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAtualizarActionPerformed
      if (idUsuario <= 0) {
        JOptionPane.showMessageDialog(null, "Selecione um usuário para atualizar.");
        return;
    }
    
    System.out.println("ID do usuário antes da atualização: " + this.idUsuario);
    
    UsuarioController controller = new UsuarioController();
    
    try {
        Usuarios usuario = new Usuarios();
        usuario.setIdUsuario(this.idUsuario);  // Usando o ID já atribuído
        usuario.setNome(campoNome.getText());
        usuario.setNomeDeUsuario(campoNomeDeUsuario.getText());
        usuario.setEmail(campoEmail.getText());
        usuario.setSenha(campoSenha.getText());

        // Debug: Mostra os valores antes de enviar
        System.out.println("Atualizando usuário com ID: " + usuario.getIdUsuario());
        System.out.println("Nome: " + usuario.getNome());
        System.out.println("Nome de Usuário: " + usuario.getNomeDeUsuario());
        System.out.println("Email: " + usuario.getEmail());
        System.out.println("Senha: " + usuario.getSenha());

        boolean atualizou = controller.editarUsuarios(usuario);

        if (atualizou) {
            JOptionPane.showMessageDialog(null, "Usuário atualizado com sucesso!");
            Listagem();
            limparCampos();
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar usuário!");
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao atualizar usuário: " + e.getMessage());
        e.printStackTrace();
    }
    }//GEN-LAST:event_botaoAtualizarActionPerformed

    private void ExcluirBotaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExcluirBotaoActionPerformed
        // TODO add your handling code here:
         // TODO add your handling code here:
        UsuarioController controller = new UsuarioController();
        try{
            controller.excluirUsuario(this.idUsuario);
            JOptionPane.showConfirmDialog(null," Excluido com sucesso!");
            Listagem();
            limparCampos();
            
        }catch(Exception e){
            JOptionPane.showConfirmDialog(null,"erro ao excluir "+ e);
        }    
    }//GEN-LAST:event_ExcluirBotaoActionPerformed

    private void cadastrar_usuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cadastrar_usuarioActionPerformed
      // Chamando o controller de produtos
    UsuarioController controller = new UsuarioController();
    
    try {
        // Objeto do tipo Produtos
        Usuarios usuario = new Usuarios();
        usuario.setIdUsuario(this.idUsuario);  // Usando o ID já atribuído
        usuario.setNome(campoNome.getText());
        usuario.setNomeDeUsuario(campoNomeDeUsuario.getText());
        usuario.setEmail(campoEmail.getText());
        usuario.setSenha(campoSenha.getText());
        
        // Mandando os dados do produto para o metodo de cadastro
        Usuarios usuarioCadastrado = controller.cadastrarUsuario(usuario);  // Aqui recebe o objeto 'Usuarios'
        
        if (usuarioCadastrado != null) {
            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
            Listagem(); // listar produtos novos
            limparCampos();
        } else {
            JOptionPane.showMessageDialog(null, "Não cadastrado!");
        }

    } catch (Exception e) {
        System.err.print("Erro ao cadastrar: " + e);
    }
    }//GEN-LAST:event_cadastrar_usuarioActionPerformed

    /**private void Tabela_De_Listagem(java.awt.event.MouseEvent evt) {                                    
    // Obtém a linha selecionada
    int linhaSelecionada = TabelaUsuario.getSelectedRow();
    
    // Corrige a condição para permitir a seleção da primeira linha (índice 0)
    if (linhaSelecionada >= 0) {  
        DefaultTableModel modeloTabela = (DefaultTableModel) TabelaUsuario.getModel();

        // Verifica se a célula do ID não é nula antes de converter
        Object idObj = modeloTabela.getValueAt(linhaSelecionada, 0);
        if (idObj != null) {
            idUsuario = Integer.parseInt(idObj.toString());
        }

        // Preenche os campos de texto
        campoNome.setText(modeloTabela.getValueAt(linhaSelecionada, 1).toString());
        campoEmail.setText(modeloTabela.getValueAt(linhaSelecionada, 2).toString());

        // Preenche o campoNomeDeUsuario (JComboBox ou JTextField)
        Object nomeUsuarioObj = modeloTabela.getValueAt(linhaSelecionada, 3);
        if (campoNomeDeUsuario instanceof JComboBox) {
            campoNomeDeUsuario.setSelectedItem(nomeUsuarioObj);
        } else {
            campoNomeDeUsuario.setText(nomeUsuarioObj.toString());
        }

        // Preenche o campoSenha (JComboBox ou JTextField)
        Object senhaObj = modeloTabela.getValueAt(linhaSelecionada, 4);
        if (campoSenha instanceof JComboBox) {
            campoSenha.setSelectedItem(senhaObj);
        } else {
            campoSenha.setText(senhaObj.toString());
        }
    }
}

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
            java.util.logging.Logger.getLogger(Tela_Perfil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Tela_Perfil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Tela_Perfil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Tela_Perfil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Usuarios user =null;
                new Tela_Perfil(user).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ExcluirBotao;
    private javax.swing.JTable TabelaUsuario;
    private javax.swing.JButton botaoAtualizar;
    private javax.swing.JButton cadastrar_usuario;
    private javax.swing.JTextField campoEmail;
    private javax.swing.JTextField campoNome;
    private javax.swing.JTextField campoNomeDeUsuario;
    private javax.swing.JTextField campoSenha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
