/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Controller.UsuarioController;
import Model.Usuarios;
import com.itextpdf.text.Document;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import controller.MovimentacaoController;
import java.awt.Desktop;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.MovimentacaoEstoque;
import java.util.ArrayList;
import java.time.Month; // Se você já não tiver
import java.time.format.TextStyle; // Se você já não tiver
import java.util.Locale; // Se você já não tiver
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


public class TelaRequisicoes extends javax.swing.JFrame {
    
Usuarios user;
 private List<MovimentacaoEstoque> listaMovimentacoes; // Lista para armazenar as movimentações
 private MovimentacaoTableModel tableModel;
 private MovimentacaoController movimentacaoController; // Para buscar as movimentações
 private UsuarioController usuarioController; // Para buscar os usuários
 
    public TelaRequisicoes(Usuarios u) {
        initComponents();
        user =u;
        iconeTela();
        // Chamada do menu superior
         Menu_adm_outras_telas menu = new Menu_adm_outras_telas();
         setJMenuBar(menu.getMenuBarInstance());
         setJMenuBar(MenuSuperior.criarMenu(this, user));
         
        // Inicializar o Controller
        movimentacaoController = new MovimentacaoController();
        // Inicializar o controller
        usuarioController = new UsuarioController();

        // Inicializar a lista de movimentações e o TableModel
        listaMovimentacoes = movimentacaoController.listarMovimentacoes(); // Método para buscar as movimentações
        //tableModel = new MovimentacaoTableModel(listaMovimentacoes, movimentacaoController); // Passando o controller
        tableModel = new MovimentacaoTableModel(new ArrayList<>(), movimentacaoController); 
        // Configurar a tabela de requisições com o TableModel
        tabelaRequisicoes.setModel(tableModel);
        // Chamada do método que lista os usuários no combobox
        popularComboUsuarios();
        //Chamada do método que lista de data
        popularCombosFiltroData(); 
        
        // Ocultar todos os elementos de filtro inicialmente
        esconderTodosOsFiltros();
        btnFiltrar.setVisible(false);
        // já abre a tela com a opção "todos" selecionada
        comboFiltros.setSelectedItem("Todos");
        comboFiltrosActionPerformed(null);
                                               
        // Agrupar os radio buttons
        buttonGroup1.add(radioEntrada);
        buttonGroup1.add(radioSaida);

        // Carregar os nomes de usuário no ComboBox de Usuários
        carregarUsuariosNoComboBox();
        
        // ADICIONE ESTE CÓDIGO PARA O FILTRO DINÂMICO
        campoPesquisa.getDocument().addDocumentListener(new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent e) {
            aplicarFiltro(); // Chama o método de filtro quando o texto é inserido
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            aplicarFiltro(); // Chama o método de filtro quando o texto é removido
        }
   
        @Override
        public void changedUpdate(DocumentEvent e) {
            // Este método é para mudanças de atributos (raramente usado para campos de texto simples)
            }
        });

    }// fim do campoPesquisa.getDocument()
    
    // Método que esconde todos os elementos dos filtros para só serem chamados depois
    private void esconderTodosOsFiltros() {
        labelNomeOuCodigo.setVisible(false);
        campoPesquisa.setVisible(false);
        comboNomeDeUsuario.setVisible(false);
        radioEntrada.setVisible(false);
        radioSaida.setVisible(false);
        comboMes.setVisible(false); // Esconde o combo de mês
        comboAno.setVisible(false); // Esconde o combo de ano
        // Opcional: Se você tiver labels para Mês e Ano, esconda-as também
        labelMes.setVisible(false);
        labelAno.setVisible(false);
    }// fim do esconderTodosOsFiltros()
    
    //Carrega os usuários no combobox
    private void carregarUsuariosNoComboBox() {
        comboNomeDeUsuario.removeAllItems(); // Limpa itens existentes
        comboNomeDeUsuario.addItem("Selecione um Usuário"); // Adiciona uma opção padrão

        List<Usuarios> usuarios = usuarioController.listarUsuarios(); // Supondo que você tem este método
        if (usuarios != null) {
            for (Usuarios usuario : usuarios) {
                comboNomeDeUsuario.addItem(usuario.getNomeDeUsuario()); // Adiciona o nome de usuário
            }//fim do for
        }//fim do if
    }//fim do carregarUsuariosNoComboBox()
    
        // método que Popula o combo de meses com os nomes
        private void popularCombosFiltroData() {
            comboMes.removeAllItems();
            comboMes.addItem("Todos"); // Opção para não filtrar por mês
            // Usando java.time.Month para obter os nomes dos meses
            for (java.time.Month month : java.time.Month.values()) {
                // Obter o nome do mês em português (locale Brazil)
                comboMes.addItem(month.getDisplayName(java.time.format.TextStyle.FULL, new java.util.Locale("pt", "BR")));
            }//fim do for

            // Popula o combo de anos (2025 a 2060) - Mantém como estava
            comboAno.removeAllItems();
            comboAno.addItem("Todos"); // Opção para não filtrar por ano
            for (int i = 2025; i <= 2060; i++) { // Anos de 2025 a 2060
                comboAno.addItem(String.valueOf(i));
            }//fim do for
        }// fim do osFiltroData()
        
        // MÉTODO popularComboUsuarios() NA TELA REQUISIÇÕES
        private void popularComboUsuarios() {
            comboNomeDeUsuario.removeAllItems(); // Limpa itens anteriores
            comboNomeDeUsuario.addItem("Todos"); // Adiciona a opção "Todos"
            // Busca a lista de usuários usando o UsuarioController
            List<Usuarios> usuarios = usuarioController.listarUsuarios(); 

            if (usuarios != null) {
                for (Usuarios usuario : usuarios) {
                    comboNomeDeUsuario.addItem(usuario.getNomeDeUsuario()); // Adiciona o nome de usuário ao ComboBox
                }//fim do for
            }//fim do if
        }//fim do popularComboUsuarios()
        
            // classe responsável por fazer o preenchimento da tabela com as movimentações
            private class MovimentacaoTableModel extends DefaultTableModel {
            private String[] colunas = {"Nº da Movimentação", "Nome do Produto", "Nome de Usuário", "Código do Usuário", "Tipo de Mov.", "Quantidade", "Data e hora"}; // Nova ordem das colunas
            private List<MovimentacaoEstoque> movimentacoes;
            private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            private MovimentacaoController movimentacaoController;

            public MovimentacaoTableModel(List<MovimentacaoEstoque> movimentacoes, MovimentacaoController movimentacaoController) {
                this.movimentacoes = movimentacoes;
                this.movimentacaoController = movimentacaoController;
            }

            @Override
            public int getRowCount() {
                return movimentacoes == null ? 0 : movimentacoes.size();
            }

            @Override
            public int getColumnCount() {
                return colunas.length;
            }

            @Override
            public String getColumnName(int column) {
                return colunas[column];
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
            if (movimentacoes == null || movimentacoes.isEmpty() || rowIndex < 0 || rowIndex >= movimentacoes.size()) {
                return null;
            }
        MovimentacaoEstoque movimentacao = movimentacoes.get(rowIndex);
        switch (columnIndex) {
            case 0:
                int idMovimentacao = movimentacao.getIdMovimentacao();
                if (idMovimentacao < 10) {
                    return String.format("000%d", idMovimentacao);
                } else if (idMovimentacao < 100) {
                    return String.format("00%d", idMovimentacao);
                } else if (idMovimentacao < 1000) {
                    return String.format("0%d", idMovimentacao);
                } else {
                    return String.valueOf(idMovimentacao);
                }
                case 1: return movimentacao.getNomeProduto();
                case 2: // Nome de Usuário agora é a terceira coluna
                    int idUsuario = movimentacao.getIdUsuario();
                    System.out.println(idUsuario);
                    String nomeUsuario = movimentacaoController.buscarNomeUsuario(idUsuario);
                    return nomeUsuario;
                case 3: return movimentacao.getCodigoSolicitante(); // Código do Solicitante agora é a quarta coluna
                case 4: return movimentacao.getTipoMovimentacao() == 1 ? "Entrada" : "Saída"; // Tipo de Movimentação agora é a quinta coluna
                case 5: return movimentacao.getQuantidade(); // Quantidade agora é a sexta coluna
                case 6: // Data agora é a sétima coluna
                    LocalDateTime dataMov = movimentacao.getDataMov();
                    return dataMov != null ? dateFormatter.format(dataMov) : "";
                default: return null;
            }
        }

    // Método para atualizar os dados da tabela
    public void atualizarDados(List<MovimentacaoEstoque> novasMovimentacoes) {
        this.movimentacoes = novasMovimentacoes;
        fireTableDataChanged();
    }//fim do atualizarDados
    
}//fim do MovimentacaoTableModel
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        demoBundle1 = new datechooser.demo.locale.DemoBundle();
        jCalendar1 = new com.toedter.calendar.JCalendar();
        demoBundle2 = new datechooser.demo.locale.DemoBundle();
        show4Permanent1 = new datechooser.demo.steps.Show4Permanent();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaRequisicoes = new javax.swing.JTable();
        botaoGerarPdf = new javax.swing.JButton();
        jLabelTítulo = new javax.swing.JLabel();
        campoPesquisa = new javax.swing.JTextField();
        comboFiltros = new javax.swing.JComboBox<>();
        radioSaida = new javax.swing.JRadioButton();
        radioEntrada = new javax.swing.JRadioButton();
        labelNomeOuCodigo = new javax.swing.JLabel();
        comboNomeDeUsuario = new javax.swing.JComboBox<>();
        comboMes = new javax.swing.JComboBox<>();
        comboAno = new javax.swing.JComboBox<>();
        labelMes = new javax.swing.JLabel();
        labelAno = new javax.swing.JLabel();
        labelFiltros = new javax.swing.JLabel();
        btnFiltrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(940, 650));

        jPanel1.setMaximumSize(new java.awt.Dimension(940, 650));
        jPanel1.setMinimumSize(new java.awt.Dimension(940, 650));

        tabelaRequisicoes.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tabelaRequisicoes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Nº da requição", "Produto", "Cód. do solicitante", "Quantidade", "Tipo de mov.", "Data"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
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
        jLabelTítulo.setText("Relatório de Movimentações");

        campoPesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoPesquisaActionPerformed(evt);
            }
        });

        comboFiltros.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        comboFiltros.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Nome do produto", "Usuário", "Tipo de mov.", "Data", " " }));
        comboFiltros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboFiltrosActionPerformed(evt);
            }
        });

        radioSaida.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        radioSaida.setText("saída");
        radioSaida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioSaidaActionPerformed(evt);
            }
        });

        radioEntrada.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        radioEntrada.setText("Entrada");
        radioEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioEntradaActionPerformed(evt);
            }
        });

        labelNomeOuCodigo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        labelNomeOuCodigo.setText("Informe o nome  do produto:");

        comboNomeDeUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboNomeDeUsuarioActionPerformed(evt);
            }
        });

        comboMes.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        comboMes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboMesActionPerformed(evt);
            }
        });

        comboAno.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        comboAno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboAnoActionPerformed(evt);
            }
        });

        labelMes.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelMes.setText("Mês:");

        labelAno.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelAno.setText("Ano:");

        labelFiltros.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        labelFiltros.setText("Filtrar:");

        btnFiltrar.setBackground(new java.awt.Color(51, 51, 255));
        btnFiltrar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnFiltrar.setForeground(new java.awt.Color(255, 255, 255));
        btnFiltrar.setText("Filtrar");
        btnFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 854, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(765, 765, 765)
                            .addComponent(botaoGerarPdf, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btnFiltrar)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(39, 39, 39)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(campoPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(labelNomeOuCodigo))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(comboMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(labelMes, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(26, 26, 26)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(comboAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(labelAno, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(radioSaida)
                                            .addGap(31, 31, 31)
                                            .addComponent(radioEntrada))
                                        .addComponent(comboFiltros, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(comboNomeDeUsuario, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGap(54, 54, 54))))
                .addContainerGap(62, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabelTítulo)
                .addGap(106, 106, 106)
                .addComponent(labelFiltros, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(205, 205, 205))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelFiltros)
                            .addComponent(jLabelTítulo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboFiltros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(radioSaida)
                                    .addComponent(radioEntrada))
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(labelAno)
                                    .addComponent(labelMes))
                                .addGap(10, 10, 10)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboNomeDeUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addComponent(btnFiltrar))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(119, 119, 119)
                        .addComponent(labelNomeOuCodigo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(campoPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(botaoGerarPdf)
                .addContainerGap(67, Short.MAX_VALUE))
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
    }//GEN-LAST:event_botaoGerarPdfActionPerformed
// fim do botão de gerar pdf
    
    private void comboFiltrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboFiltrosActionPerformed

        // Esconder todos os componentes de filtro e o botão Filtrar inicialmente
        esconderTodosOsFiltros();
        // Oculta o botão Filtrar por padrão, ele só será exibido em casos específicos.
        btnFiltrar.setVisible(false);
        String filtroSelecionado = (String) comboFiltros.getSelectedItem();
        switch (filtroSelecionado) {
            case "Todos":
                aplicarFiltro(); // Chama para carregar todos os registros
                break;
            case "Nome do produto": // Atenção à capitalização, deve ser exatamente como no design
                labelNomeOuCodigo.setVisible(true);
                campoPesquisa.setVisible(true);
                // O botão Filtrar permanece oculto para este filtro, pois ele é dinâmico (via DocumentListener).
                aplicarFiltro(); // Chama para filtrar dinamicamente conforme a digitação
                break;
            case "Usuário":
                comboNomeDeUsuario.setVisible(true);
                btnFiltrar.setVisible(true); // Exibe o botão Filtrar para este tipo de filtro.
                break;
            case "Tipo de mov.":
                radioEntrada.setVisible(true);
                radioSaida.setVisible(true);
                btnFiltrar.setVisible(true); // Exibe o botão Filtrar para este tipo de filtro.
                break;
            case "Data":
                comboMes.setVisible(true);
                comboAno.setVisible(true);
                labelMes.setVisible(true);
                labelAno.setVisible(true);
                btnFiltrar.setVisible(true); // Exibe o botão Filtrar para este tipo de filtro.
                break;
            default:
                // Por segurança, vamos pedir para mostrar todos os registros.
                aplicarFiltro();
                break;
        }//fim do switch case filtroselecionado()
    }//GEN-LAST:event_comboFiltrosActionPerformed
    //fim do combofiltros
    
    private void campoPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoPesquisaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoPesquisaActionPerformed

    private void radioEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioEntradaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radioEntradaActionPerformed

    private void radioSaidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioSaidaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radioSaidaActionPerformed

    private void comboNomeDeUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboNomeDeUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboNomeDeUsuarioActionPerformed

    private void comboAnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboAnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboAnoActionPerformed

    private void comboMesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboMesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboMesActionPerformed

    private void btnFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarActionPerformed
        // TODO add your handling code here:
         aplicarFiltro();
    }//GEN-LAST:event_btnFiltrarActionPerformed
  
    // Método que aplica os filtros de listagem
       private void aplicarFiltro() {
    List<MovimentacaoEstoque> movimentacoesFiltradas = new ArrayList<>();
    // Obter qual filtro principal está selecionado no comboFiltros
    String filtroPrincipalAtivo = (String) comboFiltros.getSelectedItem();
    // Se a opção "Todos" estiver selecionada, liste todas as movimentações e saia.
    if ("Todos".equals(filtroPrincipalAtivo)) {
        movimentacoesFiltradas = movimentacaoController.listarMovimentacoes();
        tableModel.atualizarDados(movimentacoesFiltradas);
        return; // Sai do método, pois não há outros filtros para aplicar.
    }
    // Obter valores de todos os filtros (para uso posterior, dependendo do filtro principal)
    String termoProduto = campoPesquisa.getText().trim();
    String nomeUsuarioSelecionado = (String) comboNomeDeUsuario.getSelectedItem();
    int tipoMovimentacaoSelecionado = 0;
    if (radioEntrada.isSelected()) {
        tipoMovimentacaoSelecionado = 1;
    } else if (radioSaida.isSelected()) {
        tipoMovimentacaoSelecionado = 2;
    }
    String mesSelecionadoStr = (String) comboMes.getSelectedItem();
    String anoSelecionadoStr = (String) comboAno.getSelectedItem();
    // 1. Começa sempre com todas as movimentações para então aplicar o filtro principal.
    movimentacoesFiltradas = movimentacaoController.listarMovimentacoes();
    // 2. Aplicar filtro com base na seleção do filtro principal (comboFiltros)
    switch (filtroPrincipalAtivo) {
        case "Nome do produto": // Atenção à capitalização, deve ser exatamente como no design
            if (!termoProduto.isEmpty()) {
                movimentacoesFiltradas = movimentacaoController.filtrarPorNomeOuCodigoProduto(termoProduto);
            }
            break;

        case "Usuário":
            if (nomeUsuarioSelecionado != null && !"Todos".equals(nomeUsuarioSelecionado)) {
                Usuarios usuario = usuarioController.buscarUsuarioPorNomeDeUsuario(nomeUsuarioSelecionado);
                if (usuario != null) {
                    // Como você optou por não usar o método combinado, filtramos em memória
                    List<MovimentacaoEstoque> subFiltro = new ArrayList<>();
                    for (MovimentacaoEstoque mov : movimentacoesFiltradas) {
                        if (mov.getIdUsuario() == usuario.getIdUsuario()) {
                            subFiltro.add(mov);
                        }
                    }
                    movimentacoesFiltradas = subFiltro;
                }
            }
            break;

        case "Tipo de mov.":
            if (tipoMovimentacaoSelecionado > 0) {
                // Como você optou por não usar o método combinado, filtramos em memória
                List<MovimentacaoEstoque> subFiltro = new ArrayList<>();
                for (MovimentacaoEstoque mov : movimentacoesFiltradas) {
                    if (mov.getTipoMovimentacao() == tipoMovimentacaoSelecionado) {
                        subFiltro.add(mov);
                    }
                }
                movimentacoesFiltradas = subFiltro;
            }
            break;

        case "Data":
            boolean temFiltroMes = (mesSelecionadoStr != null && !"Todos".equals(mesSelecionadoStr));
            boolean temFiltroAno = (anoSelecionadoStr != null && !"Todos".equals(anoSelecionadoStr));

            if (temFiltroMes && temFiltroAno) {
                int mesNumero = 0;
                // Converte o nome do mês em português para número
                for (Month m : Month.values()) {
                    if (m.getDisplayName(TextStyle.FULL, new Locale("pt", "BR")).equalsIgnoreCase(mesSelecionadoStr)) {
                        mesNumero = m.getValue();
                        break;
                    }
                }
                int ano = Integer.parseInt(anoSelecionadoStr);

                LocalDateTime dataAtual = LocalDateTime.now();
                LocalDateTime inicioMesAtual = dataAtual.withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
                LocalDateTime dataDesejada = LocalDateTime.of(ano, mesNumero, 1, 0, 0);

                // Validação de data futura
                if (dataDesejada.isAfter(inicioMesAtual)) {
                    JOptionPane.showMessageDialog(this, "Não é possível filtrar por data futura.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    comboMes.setSelectedItem("Todos");
                    comboAno.setSelectedItem("Todos");
                    // Se a data for inválida, limpa a lista de movimentações para não exibir resultados incorretos.
                    movimentacoesFiltradas = new ArrayList<>();
                } else {
                    movimentacoesFiltradas = movimentacaoController.filtrarPorMesEAno(mesNumero, ano);
                }
            } else { // Caso apenas mês ou ano seja selecionado no filtro "Data"
                JOptionPane.showMessageDialog(this, "Para filtrar por data, selecione tanto o mês quanto o ano.", "Aviso", JOptionPane.WARNING_MESSAGE);
                comboMes.setSelectedItem("Todos");
                comboAno.setSelectedItem("Todos");
                movimentacoesFiltradas = new ArrayList<>(); // Limpa a tabela se a seleção for inválida
            }
            break;

        default:
            // Caso caia aqui por algum motivo inesperado, lista tudo
            movimentacoesFiltradas = movimentacaoController.listarMovimentacoes();
            break;
    }
    // Atualizar a tabela com os resultados filtrados
    tableModel.atualizarDados(movimentacoesFiltradas);
}
  
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
    private javax.swing.JButton btnFiltrar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JTextField campoPesquisa;
    private javax.swing.JComboBox<String> comboAno;
    private javax.swing.JComboBox<String> comboFiltros;
    private javax.swing.JComboBox<String> comboMes;
    private javax.swing.JComboBox<String> comboNomeDeUsuario;
    private datechooser.demo.locale.DemoBundle demoBundle1;
    private datechooser.demo.locale.DemoBundle demoBundle2;
    private com.toedter.calendar.JCalendar jCalendar1;
    private javax.swing.JLabel jLabelTítulo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelAno;
    private javax.swing.JLabel labelFiltros;
    private javax.swing.JLabel labelMes;
    private javax.swing.JLabel labelNomeOuCodigo;
    private javax.swing.JRadioButton radioEntrada;
    private javax.swing.JRadioButton radioSaida;
    private datechooser.demo.steps.Show4Permanent show4Permanent1;
    private javax.swing.JTable tabelaRequisicoes;
    // End of variables declaration//GEN-END:variables
}//fim da classe TelaRequisicoes
