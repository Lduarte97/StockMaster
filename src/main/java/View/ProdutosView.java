/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Controller.ProdutosController;
import Model.Produtos;
import Model.Usuarios;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.filechooser.FileNameExtensionFilter;


public class ProdutosView extends javax.swing.JFrame {

    private Produtos produtoAtual;
    private ProdutosController produtosController;
    private Usuarios usuarioLogado;
    private Tela_home telaHomeReferencia; // Renomeado para clareza

    // Construtor padrão (pode ser útil para testes de UI, mas não para o fluxo principal)
    public ProdutosView() {
        initComponents();
        produtosController = new ProdutosController(); // Inicializa o controller aqui
        iconeTela();
        // Os listeners devem ser adicionados APÓS initComponents
        addActionEventListeners();
    }

    public ProdutosView(int produtoId, Usuarios usuario, Tela_home telaHome) {
       this.usuarioLogado = usuario;
        this.telaHomeReferencia = telaHome; // Armazena a referência da Tela_home
        this.produtosController = new ProdutosController(); // Inicializa o controller

        initComponents(); // Inicializa os componentes gráficos ANTES de usá-lo
        // Adiciona os ActionListeners aos botões APÓS a inicialização dos componentes
        addActionEventListeners(); 
        // Carrega os dados do produto e verifica as permissões
        carregarProduto(produtoId);
        verificarPermissaoAdmin();
        iconeTela();
    }
 // Método auxiliar para adicionar ActionListeners, tornando o construtor mais limpo
    private void addActionEventListeners() {
        // Agora usando os nomes que o NetBeans gerou para seus botões
        botaoAtualizar1.addActionListener(new ActionListener() { // Este é o seu botão "Atualizar"
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarProduto();
            }
        });

        boataoExcluir.addActionListener(new ActionListener() { // Este é o seu botão "Excluir"
            @Override
            public void actionPerformed(ActionEvent e) {
                excluirProduto();
            }
        });

        botaoCancelar.addActionListener(new ActionListener() { // Este é o seu botão "Cancelar"
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fecha a janela
            }
        });
        
        // Se você tiver um botão específico para "Alterar Imagem", adicione o listener aqui
        alterarFoto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alterarFotoProduto(); // Crie este método para a lógica de alterar foto
            }
        });
        
        // Se você tiver um botão específico para "Excluir Imagem", adicione o listener aqui
        excluirFoto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluirFotoProduto(); // Crie este método para a lógica de excluir foto
            }
        });
    }

    private void carregarProduto(int produtoId) {
        produtoAtual = produtosController.buscarProdutoPorId(produtoId);
        if (produtoAtual != null) {
            // Preencher os campos com os dados do produto
            tipoDeProduto1.setText(produtoAtual.getTipoProduto()); // Nome do Produto
            textMarcaProduto.setText(produtoAtual.getMarcaProduto());
            textCodigo1.setText(produtoAtual.getCodigo()); // Cód. do produto
            textCodigoFornecedor1.setText(produtoAtual.getCodigoFornecedor()); // Cód. Fornecedor
            textUnidadeVenda.setText(produtoAtual.getUnidadeVenda());
            textDataCadastro.setText(produtoAtual.getDataCadastro());
            textPrecoCusto.setText(String.valueOf(produtoAtual.getPrecoCusto()));
            textPrecoAtacado.setText(String.valueOf(produtoAtual.getPrecoAtacado()));
            textPrecoVarejo.setText(String.valueOf(produtoAtual.getPrecoVarejo()));
            textPrecoDistribuidora.setText(String.valueOf(produtoAtual.getPrecoDistribuidora()));
            textEstoqueMaximo.setText(String.valueOf(produtoAtual.getEstoqueMaximo()));
            textEstoqueMinimo.setText(String.valueOf(produtoAtual.getEstoqueMinimo()));
            textEstoqueAtual.setText(String.valueOf(produtoAtual.getEstoqueAtual()));
            // textFotoProduto é um JButton, não um JTextField, então não podemos usar setText nele para o caminho da foto.
            // Se você quer exibir o caminho da foto, use um JLabel ou JTextField para isso.
            // Para exibir a imagem real, você precisará carregar a imagem no ImageIcon do JButton ou JLabel.
            // textFotoProduto.setText(produtoAtual.getFotoProduto()); // Remover ou ajustar
            if (produtoAtual.getFotoProduto() != null && !produtoAtual.getFotoProduto().isEmpty()) {
                try {
                    ImageIcon imageIcon = new ImageIcon(produtoAtual.getFotoProduto());
                    Image image = imageIcon.getImage().getScaledInstance(textFotoProduto.getWidth(), textFotoProduto.getHeight(), Image.SCALE_SMOOTH);
                    textFotoProduto.setIcon(new ImageIcon(image));
                    textFotoProduto.setText(""); // Limpa o texto se tiver imagem
                } catch (Exception e) {
                    textFotoProduto.setText("Erro ao carregar imagem");
                    System.err.println("Erro ao carregar imagem: " + e.getMessage());
                }
            } else {
                textFotoProduto.setIcon(null); // Remove qualquer imagem anterior
                textFotoProduto.setText("Sem Imagem"); // Ou um texto padrão
            }

            textCategoria.setText(produtoAtual.getCategoria());
            //descricao.setText(produtoAtual.getDescricao()); // Adicionei o campo de descrição
        } else {
            JOptionPane.showMessageDialog(this, "Produto não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
            dispose(); // Fecha a janela se o produto não for encontrado
        }
    }// fim do método carregar produto

    private void salvarProduto() {
        if (produtoAtual != null) {
            // Atualizar o objeto produtoAtual com os dados dos campos
            // Ajustado os nomes dos JTextFields
            produtoAtual.setTipoProduto(tipoDeProduto1.getText()); // Nome do Produto
            produtoAtual.setMarcaProduto(textMarcaProduto.getText());
            produtoAtual.setCodigo(textCodigo1.getText()); // Cód. do produto
            produtoAtual.setCodigoFornecedor(textCodigoFornecedor1.getText()); // Cód. Fornecedor
            produtoAtual.setUnidadeVenda(textUnidadeVenda.getText());
            produtoAtual.setDataCadastro(textDataCadastro.getText());
            try {
                produtoAtual.setPrecoCusto(Double.parseDouble(textPrecoCusto.getText()));
                produtoAtual.setPrecoAtacado(Double.parseDouble(textPrecoAtacado.getText()));
                produtoAtual.setPrecoVarejo(Double.parseDouble(textPrecoVarejo.getText()));
                produtoAtual.setPrecoDistribuidora(Double.parseDouble(textPrecoDistribuidora.getText()));
                produtoAtual.setEstoqueMaximo(Integer.parseInt(textEstoqueMaximo.getText()));
                produtoAtual.setEstoqueMinimo(Integer.parseInt(textEstoqueMinimo.getText()));
                produtoAtual.setEstoqueAtual(Integer.parseInt(textEstoqueAtual.getText()));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Erro de formato numérico nos campos de preço ou estoque. Verifique se digitou apenas números.", "Erro de Entrada", JOptionPane.ERROR_MESSAGE);
                return;
            }
            // produtoAtual.setFotoProduto(textFotoProduto.getText()); // textFotoProduto é um JButton, não um JTextField
            produtoAtual.setCategoria(textCategoria.getText());
            //produtoAtual.setDescricao(descricao.getText()); // Adicionei a descrição

            // Chamar o método de atualização no controller
            if (produtosController.atualizarProduto(produtoAtual)) {
                JOptionPane.showMessageDialog(this, "Produto atualizado com sucesso!");
                if (telaHomeReferencia != null) {
                    telaHomeReferencia.listarprodutostabela(); // Atualiza a tabela na tela home
                }
                dispose(); // Fecha a janela de detalhes
            } else {
                JOptionPane.showMessageDialog(this, "Falha ao atualizar o produto.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }// fim do salvarProduto()

    private void excluirProduto() {
        if (produtoAtual != null) {
            int confirm = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja excluir este produto?", "Confirmação de Exclusão", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                if (produtosController.excluirProduto(produtoAtual.getProdutoID())) {
                    JOptionPane.showMessageDialog(this, "Produto excluído com sucesso!");
                    if (telaHomeReferencia != null) {
                        telaHomeReferencia.listarprodutostabela(); // Atualiza a tabela na tela home
                    }
                    dispose(); // Fecha a janela de detalhes
                } else {
                    JOptionPane.showMessageDialog(this, "Falha ao excluir o produto.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }// fim do excluirProduto()

    private void verificarPermissaoAdmin() {
        // O valor 1 para isAdmin significa que é um usuário administrador
        boolean isAdmin = (usuarioLogado != null && usuarioLogado.getIsAdmin() == 1);
        
        // Habilita/desabilita os botões de ação
        botaoAtualizar1.setEnabled(isAdmin);
        boataoExcluir.setEnabled(isAdmin);
        alterarFoto.setEnabled(isAdmin); // Botão alterar foto
        excluirFoto.setEnabled(isAdmin); // Botão excluir foto

        // Desabilitar campos de edição para usuários não-admin
        tipoDeProduto1.setEditable(isAdmin); // Nome do Produto
        textMarcaProduto.setEditable(isAdmin);
        textCodigo1.setEditable(isAdmin); // Cód. do produto
        textCodigoFornecedor1.setEditable(isAdmin); // Cód. Fornecedor
        textUnidadeVenda.setEditable(isAdmin);
        textDataCadastro.setEditable(isAdmin);
        textPrecoCusto.setEditable(isAdmin);
        textPrecoAtacado.setEditable(isAdmin);
        textPrecoVarejo.setEditable(isAdmin);
        textPrecoDistribuidora.setEditable(isAdmin);
        textEstoqueMaximo.setEditable(isAdmin);
        textEstoqueMinimo.setEditable(isAdmin);
        textEstoqueAtual.setEditable(isAdmin);
        // textFotoProduto é um JButton, não setEditable diretamente
        textCategoria.setEditable(isAdmin);
        descricao.setEditable(isAdmin); // Campo de descrição

    }// fim do verificarPermissaoAdmin()
    
    // Método para lidar com a alteração da foto do produto
    private void alterarFotoProduto() {
    // Verifica se há um produto carregado para ser alterado
    if (produtoAtual == null) {
        JOptionPane.showMessageDialog(this, "Nenhum produto selecionado para alterar a foto.", "Erro", JOptionPane.ERROR_MESSAGE);
        return;
    }
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Escolha uma imagem");
    fileChooser.setAcceptAllFileFilterUsed(false);
    fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Imagens JPG & PNG", "jpg", "png"));

    int returnValue = fileChooser.showOpenDialog(this); // 'this' refere-se à sua ProdutosView

    if (returnValue == JFileChooser.APPROVE_OPTION) {
        File selectedFile = fileChooser.getSelectedFile();
        String novoCaminhoFoto = selectedFile.getAbsolutePath(); // Captura o caminho do arquivo

        try {
            // Carregar a imagem selecionada
            Image image = ImageIO.read(selectedFile);
            // Ajusta o tamanho da imagem para o seu JButton 'textFotoProduto'
            // Use as dimensões reais do seu JButton para melhor ajuste
            ImageIcon icon = new ImageIcon(image.getScaledInstance(
                textFotoProduto.getWidth(),
                textFotoProduto.getHeight(),
                Image.SCALE_SMOOTH
            ));
            // 1. Atualiza o ImageIcon no seu JButton 'textFotoProduto'
            textFotoProduto.setIcon(icon);
            textFotoProduto.setText(""); // Limpa qualquer texto que possa estar no botão se a imagem for exibida

            // 2. **MUUITO IMPORTANTE:** Atualiza o caminho da foto no objeto produtoAtual
            produtoAtual.setFotoProduto(novoCaminhoFoto);

            JOptionPane.showMessageDialog(this, "Imagem selecionada com sucesso! Clique em 'Atualizar' para salvar as alterações.");

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar a imagem selecionada: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            System.err.println("Erro ao carregar imagem: " + ex.getMessage());
            ex.printStackTrace(); // Para depuração
            // Opcional: Se der erro, você pode querer reverter o ícone para o original ou para 'Sem Imagem'
            textFotoProduto.setIcon(null);
            textFotoProduto.setText("Erro");
        }
    }
}// fim do alterarFotoProduto() 
    
    // Método para lidar com a exclusão da foto do produto
    private void excluirFotoProduto() {
        int confirm = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja remover a foto do produto?", "Confirmação", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            if (produtoAtual != null) {
                produtoAtual.setFotoProduto(null); // Define o caminho da foto como nulo
                textFotoProduto.setIcon(null); // Remove a imagem do botão
                textFotoProduto.setText("Sem Imagem"); // Define um texto padrão
                JOptionPane.showMessageDialog(this, "Foto removida. Lembre-se de 'Salvar Alterações' para persistir.");
            }
        }
    }// fim do excluirFotoProduto()

    private void iconeTela(){
        try {
            // Certifique-se de que o caminho da imagem está correto
            // Se "logoGeral.png" está dentro de uma pasta "imagens" na raiz do seu projeto (dentro de src)
            Image iconeTitulo = ImageIO.read(getClass().getResource("/imagens/logoGeral.png"));
            this.setIconImage(iconeTitulo);
        }catch(IOException ex){
            System.out.println("Erro ao importar icone: " + ex.getMessage());
            // ex.printStackTrace(); // Para depurar, descomente esta linha
        }
    }// fim do iconeTela()
    
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
        alterarFoto = new javax.swing.JButton();
        textFotoProduto = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        excluirFoto = new javax.swing.JButton();
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
        boataoExcluir = new javax.swing.JButton();
        textCategoria = new javax.swing.JTextField();
        tipoDeProduto1 = new javax.swing.JTextField();
        textCodigoFornecedor1 = new javax.swing.JTextField();
        textCodigo1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabelTitulo = new javax.swing.JLabel();
        botaoCancelar = new javax.swing.JButton();
        botaoAtualizar1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(940, 650));

        jPanel1.setMaximumSize(new java.awt.Dimension(940, 650));
        jPanel1.setMinimumSize(new java.awt.Dimension(940, 650));
        jPanel1.setPreferredSize(new java.awt.Dimension(940, 650));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setText("Categoria :");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 200, 90, -1));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setText("Cód. do produto:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 200, 130, -1));

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setText("Cód. Fornecedor:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 200, -1, -1));

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setText("Preço da Dist :");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 280, 110, -1));
        jPanel1.add(textDataCadastro, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 220, 110, 30));

        jLabel7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel7.setText("Data do Cad. :");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 200, 110, -1));
        jPanel1.add(textMarcaProduto, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 140, 150, 30));

        textPrecoDistribuidora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textPrecoDistribuidoraActionPerformed(evt);
            }
        });
        jPanel1.add(textPrecoDistribuidora, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 300, 120, 30));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("R$ ");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 300, 20, -1));

        jLabel9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel9.setText("Estoque Atual :*");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 360, 120, -1));

        descricao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                descricaoActionPerformed(evt);
            }
        });
        jPanel1.add(descricao, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 450, 550, 130));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
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

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setText("R$ ");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 300, 20, -1));

        alterarFoto.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        alterarFoto.setText("Alterar Imagem");
        alterarFoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alterarFotoActionPerformed(evt);
            }
        });
        jPanel1.add(alterarFoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 360, -1, -1));

        textFotoProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFotoProdutoActionPerformed(evt);
            }
        });
        jPanel1.add(textFotoProduto, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 130, 250, 200));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setText("R$ ");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 300, 20, -1));

        excluirFoto.setBackground(new java.awt.Color(153, 0, 0));
        excluirFoto.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        excluirFoto.setForeground(new java.awt.Color(255, 255, 255));
        excluirFoto.setText("Excluir Imagem");
        excluirFoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                excluirFotoActionPerformed(evt);
            }
        });
        jPanel1.add(excluirFoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 360, -1, -1));

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
        jLabel16.setText("Preço de Custo:");
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
        tipoDeproduto.setText("Nome do Produto:");
        jPanel1.add(tipoDeproduto, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, 140, -1));
        jPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 10, 30, 20));

        jLabel22.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel22.setText("Und de Venda:");
        jPanel1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 120, 110, -1));
        jPanel1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 161, 110, -1));

        textUnidadeVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textUnidadeVendaActionPerformed(evt);
            }
        });
        jPanel1.add(textUnidadeVenda, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 140, 110, 30));

        boataoExcluir.setBackground(new java.awt.Color(153, 0, 0));
        boataoExcluir.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        boataoExcluir.setForeground(new java.awt.Color(255, 255, 255));
        boataoExcluir.setText("Excluir");
        boataoExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boataoExcluirActionPerformed(evt);
            }
        });
        jPanel1.add(boataoExcluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 460, -1, 30));
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
        jLabelTitulo.setText("Informações do produto");
        jPanel1.add(jLabelTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 40, 220, 20));

        botaoCancelar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        botaoCancelar.setText("Cancelar");
        botaoCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(botaoCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 550, -1, 30));

        botaoAtualizar1.setBackground(new java.awt.Color(0, 0, 255));
        botaoAtualizar1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        botaoAtualizar1.setForeground(new java.awt.Color(255, 255, 255));
        botaoAtualizar1.setText("Atualizar");
        botaoAtualizar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoAtualizar1ActionPerformed(evt);
            }
        });
        jPanel1.add(botaoAtualizar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 460, -1, 30));

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

    private void textPrecoDistribuidoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textPrecoDistribuidoraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textPrecoDistribuidoraActionPerformed

    private void descricaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_descricaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_descricaoActionPerformed

    private void textPrecoAtacadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textPrecoAtacadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textPrecoAtacadoActionPerformed

    private void textFotoProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFotoProdutoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFotoProdutoActionPerformed

    private void excluirFotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_excluirFotoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_excluirFotoActionPerformed

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

    private void textUnidadeVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textUnidadeVendaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textUnidadeVendaActionPerformed

    private void boataoExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boataoExcluirActionPerformed
       // e chama o método excluirProduto().
                          
    }//GEN-LAST:event_boataoExcluirActionPerformed

    private void textCodigoFornecedor1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textCodigoFornecedor1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textCodigoFornecedor1ActionPerformed

    private void textCodigo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textCodigo1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textCodigo1ActionPerformed

    private void botaoCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCancelarActionPerformed
        // TODO add your handling code here:
        // e chama dispose().
        dispose();
    }//GEN-LAST:event_botaoCancelarActionPerformed

    private void botaoAtualizar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAtualizar1ActionPerformed
        // TODO add your handling code here:
        // e chama o método salvarProduto().
       
    }//GEN-LAST:event_botaoAtualizar1ActionPerformed

    private void alterarFotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alterarFotoActionPerformed
        // TODO add your handling code here:
        // e chama o método alterarFotoProduto().
        alterarFotoProduto();
    }//GEN-LAST:event_alterarFotoActionPerformed

    
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            new ProdutosView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton alterarFoto;
    private javax.swing.JButton boataoExcluir;
    private javax.swing.JButton botaoAtualizar1;
    private javax.swing.JButton botaoCancelar;
    private javax.swing.JTextField descricao;
    private javax.swing.JButton excluirFoto;
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