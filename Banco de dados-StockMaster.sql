CREATE DATABASE StockMaster;

USE StockMaster;

-- Tabela de Usu�rios
CREATE TABLE Usuarios (
    IdUsuario INT PRIMARY KEY IDENTITY(1,1),
    Nome VARCHAR(100) NOT NULL,
    Email VARCHAR(150) NOT NULL UNIQUE,
    NomeDeUsuario VARCHAR(15) NOT NULL UNIQUE,
    Senha VARCHAR(50) NOT NULL,
    IsAdmin int, -- 0 para usu�rio comum, 1 para administrador
	CodigoUsuario VARCHAR(4)
);

select * from Usuarios;

CREATE TABLE Produtos (
    ProdutoID INT IDENTITY(1,1) PRIMARY KEY,  -- Identificador �nico                                                                                                                                                                                                                                                                                                                                                                                                                                                                      RY KEY,  -- Identificador �nico
    TipoProduto NVARCHAR(50),                 -- Tipo de Produto (Ex: Caminh�es)
    MarcaProduto NVARCHAR(50),                -- Marca do Produto
    Codigo NVARCHAR(50),                      -- C�digo do Produto
    CodigoFornecedor NVARCHAR(50),            -- C�digo do Fornecedor
    UnidadeVenda NVARCHAR(50),                -- Unidade de Venda
    DataCadastro VARCHAR(12),                        -- Data de Cadastro
    PrecoCusto DECIMAL(18, 2),                -- Pre�o de Custo
    PrecoAtacado DECIMAL(18, 2),              -- Pre�o de Atacado
    PrecoVarejo DECIMAL(18, 2),               -- Pre�o de Varejo
    PrecoDistribuidora DECIMAL(18, 2),        -- Pre�o da Distribuidora
    EstoqueMaximo INT,                        -- Estoque M�ximo
    EstoqueMinimo INT,                        -- Estoque M�nimo
    EstoqueAtual INT,                         -- Estoque Atual
    FotoProduto  VARCHAR(50),             -- Foto do Produto (armazenada em bin�rio)
	Categoria NVARCHAR(50)

);
select * from Produtos;

-- Tabela de Movimenta��es de Estoque
CREATE TABLE MovimentacoesEstoque (
    IdMovimentacao INT PRIMARY KEY IDENTITY(1,1),
    IdProduto INT NOT NULL,
    TipoMovimentacao INT NOT NULL, -- "Entrada" ou "Sa�da"
	CodigoSolicitante VARCHAR(50) NOT NULL,
	CdodigoProduto varchar(50),
    Quantidade INT NOT NULL,
    IdUsuario INT NOT NULL, -- Usu�rio que realizou a movimenta��o
    FOREIGN KEY (IdProduto) REFERENCES Produtos(ProdutoID),
    FOREIGN KEY (IdUsuario) REFERENCES Usuarios(IdUsuario)
);

EXEC sp_rename 'MovimentacoesEstoque.CdodigoProduto', 'CodigoProduto', 'COLUMN';
select * from MovimentacoesEstoque;
-- Tabela de Requisi��es
CREATE TABLE Requisicoes (
    IdRequisicao INT PRIMARY KEY IDENTITY(1,1),
    IdUsuario INT NOT NULL,
	CodigoSolicitante VARCHAR(4) NOT NULL,
    DataRequisicao VARCHAR(15),
    StatusReq VARCHAR(50) NOT NULL DEFAULT 'Pendente', -- Ex: "Pendente", "Aprovada", "Rejeitada"
    FOREIGN KEY (IdUsuario) REFERENCES Usuarios(IdUsuario)
);

select * from Requisicoes;

-- Tabela de Itens da Requisi��o
CREATE TABLE ItensRequisicao (
    IdItemRequisicao INT PRIMARY KEY IDENTITY(1,1),
    IdRequisicao INT NOT NULL,
    IdProduto INT NOT NULL,
    Quantidade INT NOT NULL,
    FOREIGN KEY (IdRequisicao) REFERENCES Requisicoes(IdRequisicao),
    FOREIGN KEY (IdProduto) REFERENCES Produtos(IdProduto)
);
select * from Produtos;

-- Primeiro produto: Caminh�o Volvo
INSERT INTO Produtos (
    TipoProduto, MarcaProduto, Codigo, CodigoFornecedor, UnidadeVenda, 
    DataCadastro, PrecoCusto, PrecoAtacado, PrecoVarejo, PrecoDistribuidora, 
    EstoqueMaximo, EstoqueMinimo, EstoqueAtual, FotoProduto, Categoria
)
VALUES (
    'Caminh�o', 'Volvo', 'CAM1234', 'FOR9876', 'Unidade', 
    '2025-02-10', 250000.00, 240000.00, 260000.00, 245000.00, 
    10, 2, 5, 'volvo_caminhao.jpg', 'Ve�culos Pesados'
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


CREATE TRIGGER trg_AtualizaEstoque
ON MovimentacoesEstoque
AFTER INSERT
AS
BEGIN
    SET NOCOUNT ON;

    -- Atualiza o estoque com base no tipo de movimenta��o
    UPDATE p
    SET p.EstoqueAtual = 
        CASE 
            WHEN i.TipoMovimentacao = 1 THEN p.EstoqueAtual + i.Quantidade  -- Entrada: Soma a quantidade
            WHEN i.TipoMovimentacao = 2 AND p.EstoqueAtual >= i.Quantidade THEN p.EstoqueAtual - i.Quantidade  -- Sa�da: Subtrai a quantidade (se houver estoque suficiente)
            ELSE p.EstoqueAtual  -- Caso n�o tenha estoque suficiente, mant�m o valor atual
        END
    FROM Produtos p
    INNER JOIN inserted i ON p.ProdutoID = i.IdProduto;
    
    -- Bloqueia a movimenta��o caso a sa�da deixe o estoque negativo
    IF EXISTS (
        SELECT 1 
        FROM Produtos p
        INNER JOIN inserted i ON p.ProdutoID = i.IdProduto
        WHERE i.TipoMovimentacao = 2 AND p.EstoqueAtual < 0
    )
    BEGIN
        -- Reverte a atualiza��o e exibe um erro
        RAISERROR ('N�o � poss�vel realizar a sa�da. Estoque insuficiente.', 16, 1);
        ROLLBACK TRANSACTION;
    END
END;