-- Categorias
INSERT INTO categorias (nome, descricao) VALUES ('Informática', 'Produtos de informática');

INSERT INTO categorias (nome, descricao) VALUES ('Periféricos', 'Acessórios para computadores');

INSERT INTO categorias (nome, descricao) VALUES ('Livros', 'Livros técnicos');

-- Produtos
INSERT INTO produtos (nome, descricao, preco, estoque, categoria_id) VALUES ('Notebook Dell', 'Notebook Core i7', 5200.00, 15, 1);

INSERT INTO produtos (nome, descricao, preco, estoque, categoria_id) VALUES ('Mouse Logitech', 'Mouse sem fio', 150.00, 80, 2);

INSERT INTO produtos (nome, descricao, preco, estoque, categoria_id) VALUES ('Teclado Mecânico', 'RGB Switch Blue', 350.00, 40, 2);

INSERT INTO produtos (nome, descricao, preco, estoque, categoria_id) VALUES ('Clean Code', 'Livro de Robert C. Martin', 120.00, 25, 3);