CREATE DATABASE StockMaster;

USE StockMaster;

-- Tabela de Usuarios
CREATE TABLE Usuarios (
    IdUsuario INT PRIMARY KEY IDENTITY(1,1),
    Nome VARCHAR(100) NOT NULL,
    Email VARCHAR(150) NOT NULL UNIQUE,
    NomeDeUsuario VARCHAR(15) NOT NULL UNIQUE,
    Senha VARCHAR(50) NOT NULL,
    IsAdmin int, -- 0 para usußrio comum, 1 para administrador
	CodigoUsuario VARCHAR(4)
);

select * from Usuarios;

CREATE TABLE Produtos (
    ProdutoID INT IDENTITY(1,1) PRIMARY KEY,  -- Identificador ·nico                                                                                                                                                                                                                                                                                                                                                                                                                                                                      RY KEY,  -- Identificador único
    TipoProduto NVARCHAR(50),                 -- Tipo de Produto (Ex: Caminhões)
    MarcaProduto NVARCHAR(50),                -- Marca do Produto
    Codigo NVARCHAR(50),                      -- Código do Produto
    CodigoFornecedor NVARCHAR(50),            -- Código do Fornecedor
    UnidadeVenda NVARCHAR(50),                -- Unidade de Venda
    DataCadastro VARCHAR(12),                        -- Data de Cadastro
    PrecoCusto DECIMAL(18, 2),                -- Preço de Custo
    PrecoAtacado DECIMAL(18, 2),              -- Preço de Atacado
    PrecoVarejo DECIMAL(18, 2),               -- Preço de Varejo
    PrecoDistribuidora DECIMAL(18, 2),        -- Preço da Distribuidora
    EstoqueMaximo INT,                        -- Estoque Máximo
    EstoqueMinimo INT,                        -- Estoque Mínimo
    EstoqueAtual INT,                         -- Estoque Atual
    FotoProduto  VARCHAR(50),             -- Foto do Produto (armazenada em binário)
	Categoria NVARCHAR(50)

);
select * from Produtos;

-- Tabela de Movimentações de Estoque
CREATE TABLE MovimentacoesEstoque (
    IdMovimentacao INT PRIMARY KEY IDENTITY(1,1),
    IdProduto INT NOT NULL,
    TipoMovimentacao INT NOT NULL, -- "Entrada" ou "Saída"
	CodigoSolicitante VARCHAR(50) NOT NULL,
	CdodigoProduto varchar(50),
    Quantidade INT NOT NULL,
    IdUsuario INT NOT NULL, -- Usuário que realizou a movimentação
    FOREIGN KEY (IdProduto) REFERENCES Produtos(ProdutoID),
    FOREIGN KEY (IdUsuario) REFERENCES Usuarios(IdUsuario)
);

EXEC sp_rename 'MovimentacoesEstoque.CdodigoProduto', 'CodigoProduto', 'COLUMN';
select * from MovimentacoesEstoque;
-- Tabela de Requisições
CREATE TABLE Requisicoes (
    IdRequisicao INT PRIMARY KEY IDENTITY(1,1),
    IdUsuario INT NOT NULL,
	CodigoSolicitante VARCHAR(4) NOT NULL,
    DataRequisicao VARCHAR(15),
    StatusReq VARCHAR(50) NOT NULL DEFAULT 'Pendente', -- Ex: "Pendente", "Aprovada", "Rejeitada"
    FOREIGN KEY (IdUsuario) REFERENCES Usuarios(IdUsuario)
);

select * from Requisicoes;

-- Tabela de Itens da Requisição
CREATE TABLE ItensRequisicao (
    IdItemRequisicao INT PRIMARY KEY IDENTITY(1,1),
    IdRequisicao INT NOT NULL,
    IdProduto INT NOT NULL,
    Quantidade INT NOT NULL,
    FOREIGN KEY (IdRequisicao) REFERENCES Requisicoes(IdRequisicao),
    FOREIGN KEY (IdProduto) REFERENCES Produtos(IdProduto)
);
select * from Produtos;

-- Primeiro produto: Caminhão Volvo
INSERT INTO Produtos (
    TipoProduto, MarcaProduto, Codigo, CodigoFornecedor, UnidadeVenda, 
    DataCadastro, PrecoCusto, PrecoAtacado, PrecoVarejo, PrecoDistribuidora, 
    EstoqueMaximo, EstoqueMinimo, EstoqueAtual, FotoProduto, Categoria
)
VALUES (
    'Caminhão', 'Volvo', 'CAM1234', 'FOR9876', 'Unidade', 
    '2025-02-10', 250000.00, 240000.00, 260000.00, 245000.00, 
    10, 2, 5, 'volvo_caminhao.jpg', 'Veículos Pesados'
);

-- Segundo produto: Empilhadeira Toyota
INSERT INTO Produtos (
    TipoProduto, MarcaProduto, Codigo, CodigoFornecedor, UnidadeVenda, 
    DataCadastro, PrecoCusto, PrecoAtacado, PrecoVarejo, PrecoDistribuidora, 
    EstoqueMaximo, EstoqueMinimo, EstoqueAtual, FotoProduto, Categoria
)
VALUES (
    'Empilhadeira', 'Toyota', 'EMP5678', 'FOR1234', 'Unidade', 
    '2025-02-09', 80000.00, 77000.00, 85000.00, 78000.00, 
    15, 3, 7, 'toyota_empilhadeira.jpg', 'Equipamentos Industriais'
);

-- Trigger que é disparada na execução da opção movimentar estoque para adicionar ou remover a quantidade da coluna EstoqueAtual da tabela Produtos
CREATE TRIGGER trg_AtualizaEstoque
ON MovimentacoesEstoque
AFTER INSERT
AS
BEGIN
    SET NOCOUNT ON;

    -- Atualiza o estoque com base no tipo de movimentação
    UPDATE p
    SET p.EstoqueAtual = 
        CASE 
            WHEN i.TipoMovimentacao = 1 THEN p.EstoqueAtual + i.Quantidade  -- Entrada: Soma a quantidade
            WHEN i.TipoMovimentacao = 2 AND p.EstoqueAtual >= i.Quantidade THEN p.EstoqueAtual - i.Quantidade  -- Saída: Subtrai a quantidade (se houver estoque suficiente)
            ELSE p.EstoqueAtual  -- Caso não tenha estoque suficiente, mantém o valor atual
        END
    FROM Produtos p
    INNER JOIN inserted i ON p.ProdutoID = i.IdProduto;
    
    -- Bloqueia a movimentação caso a saída deixe o estoque negativo
    IF EXISTS (
        SELECT 1 
        FROM Produtos p
        INNER JOIN inserted i ON p.ProdutoID = i.IdProduto
        WHERE i.TipoMovimentacao = 2 AND p.EstoqueAtual < 0
    )
    BEGIN
        -- Reverte a atualização e exibe um erro
        RAISERROR ('Não é possível realizar a saída. Estoque insuficiente.', 16, 1);
        ROLLBACK TRANSACTION;
    END
END;
