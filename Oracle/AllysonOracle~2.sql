CREATE TABLE SYSTEM.usuarios (
    id INT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    idade INT,
    credito DECIMAL(10,2)
);
-----

select * from system.usuarios
CREATE TABLE SYSTEM.produtos_novo (
    id INT PRIMARY KEY,
    nome VARCHAR2(100),
    categoria VARCHAR2(100),
    preco NUMBER(10, 2),
    quantidade INT
);

CREATE TABLE SYSTEM.fornecedores (
    id INT PRIMARY KEY,
    nome VARCHAR2(100) NOT NULL,
    contato VARCHAR2(100), 
    endereco VARCHAR2(200), 
    tipo VARCHAR2(50), 
    cnpj VARCHAR2(18) UNIQUE,
    saldo_devedor NUMBER(10,2) 
);

CREATE TABLE SYSTEM.transacoes (
    id INT PRIMARY KEY,
    data DATE NOT NULL,
    descricao VARCHAR2(200),
    tipo VARCHAR2(10), 
    valor NUMBER(10,2) NOT NULL,
    usuario_id INT, 
    fornecedor_id INT, 
    CONSTRAINT fk_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
    CONSTRAINT fk_fornecedor FOREIGN KEY (fornecedor_id) REFERENCES fornecedores(id)
);


SELECT * FROM SYSTEM.usuarios;
SELECT * FROM SYSTEM.produtos_novo

DROP TABLE SYSTEM.produtos_novo;
 
 SELECT * FROM SYSTEM.usuarios;