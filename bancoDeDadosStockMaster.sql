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

-- Tabela de Movimentações de Estoque
CREATE TABLE MovimentacoesEstoque (
    IdMovimentacao INT PRIMARY KEY IDENTITY(1,1),
    IdProduto INT NOT NULL,
    TipoMovimentacao INT NOT NULL, -- "Entrada" ou "Saída"
    CodigoSolicitante VARCHAR(50) NOT NULL,
    CodigoProduto varchar(50),
    Quantidade INT NOT NULL,
    IdUsuario INT NOT NULL, -- Usuário que realizou a movimentação
    FOREIGN KEY (IdProduto) REFERENCES Produtos(ProdutoID),
    FOREIGN KEY (IdUsuario) REFERENCES Usuarios(IdUsuario)
);

alter table MovimentacoesEstoque add NomeProduto varchar(50);
alter table MovimentacoesEstoque add DataMov DATETIME2;

select * from Usuarios;
select * from Produtos;
select * from  MovimentacoesEstoque;


