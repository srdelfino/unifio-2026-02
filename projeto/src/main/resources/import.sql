-- Categorias
INSERT INTO categorias (nome, descricao) VALUES ('Informática', 'Produtos de informática');

INSERT INTO categorias (nome, descricao) VALUES ('Periféricos', 'Acessórios para computadores');

INSERT INTO categorias (nome, descricao) VALUES ('Livros', 'Livros técnicos');

INSERT INTO categorias (nome, descricao) VALUES ('Eletrônicos', 'Aparelhos eletrônicos');

INSERT INTO categorias (nome, descricao) VALUES ('Escritório', 'Materiais de escritório');

-- Produtos
INSERT INTO produtos (nome, descricao, preco, estoque, categoria_id) VALUES ('Notebook Dell', 'Notebook Core i7', 5200.00, 15, 1);

INSERT INTO produtos (nome, descricao, preco, estoque, categoria_id) VALUES ('Mouse Logitech', 'Mouse sem fio', 150.00, 80, 2);

INSERT INTO produtos (nome, descricao, preco, estoque, categoria_id) VALUES ('Teclado Mecânico', 'RGB Switch Blue', 350.00, 40, 2);

INSERT INTO produtos (nome, descricao, preco, estoque, categoria_id) VALUES ('Clean Code', 'Livro de Robert C. Martin', 120.00, 25, 3);

INSERT INTO produtos (nome, descricao, preco, estoque, categoria_id) VALUES ('Monitor Samsung', 'Monitor 27" 4K', 1800.00, 20, 4);

-- Clientes
INSERT INTO clientes (nome, email, telefone) VALUES ('João da Silva', 'joao.silva@email.com', '(11) 99999-0001');

INSERT INTO clientes (nome, email, telefone) VALUES ('Maria Oliveira', 'maria.oliveira@email.com', '(11) 99999-0002');

INSERT INTO clientes (nome, email, telefone) VALUES ('Pedro Santos', 'pedro.santos@email.com', '(11) 99999-0003');

INSERT INTO clientes (nome, email, telefone) VALUES ('Ana Souza', 'ana.souza@email.com', '(11) 99999-0004');

INSERT INTO clientes (nome, email, telefone) VALUES ('Carlos Pereira', 'carlos.pereira@email.com', '(11) 99999-0005');

-- Pedidos
INSERT INTO pedidos (data, status, valor_total, cliente_id) VALUES ('2026-08-01 10:30:00', 'PAGO', 5350.00, 1);

INSERT INTO pedidos (data, status, valor_total, cliente_id) VALUES ('2026-08-03 14:00:00', 'PENDENTE', 850.00, 2);

INSERT INTO pedidos (data, status, valor_total, cliente_id) VALUES ('2026-08-05 09:15:00', 'ENVIADO', 2150.00, 1);

INSERT INTO pedidos (data, status, valor_total, cliente_id) VALUES ('2026-08-08 16:45:00', 'ENTREGUE', 120.00, 3);

INSERT INTO pedidos (data, status, valor_total, cliente_id) VALUES ('2026-08-10 11:20:00', 'CANCELADO', 360.00, 4);

-- Itens do pedido
INSERT INTO itens_pedido (quantidade, valor_unitario, pedido_id, produto_id) VALUES (1, 5200.00, 1, 1);

INSERT INTO itens_pedido (quantidade, valor_unitario, pedido_id, produto_id) VALUES (1, 150.00, 1, 2);

INSERT INTO itens_pedido (quantidade, valor_unitario, pedido_id, produto_id) VALUES (2, 350.00, 2, 3);

INSERT INTO itens_pedido (quantidade, valor_unitario, pedido_id, produto_id) VALUES (1, 150.00, 2, 2);

INSERT INTO itens_pedido (quantidade, valor_unitario, pedido_id, produto_id) VALUES (1, 1800.00, 3, 5);

INSERT INTO itens_pedido (quantidade, valor_unitario, pedido_id, produto_id) VALUES (1, 350.00, 3, 3);

INSERT INTO itens_pedido (quantidade, valor_unitario, pedido_id, produto_id) VALUES (1, 120.00, 4, 4);

INSERT INTO itens_pedido (quantidade, valor_unitario, pedido_id, produto_id) VALUES (3, 120.00, 5, 4);

-- Pagamentos
INSERT INTO pagamentos (valor, data, status, tipo, pedido_id) VALUES (5350.00, '2026-08-01 10:35:00', 'PAGO', 'PIX', 1);

INSERT INTO pagamentos (valor, data, status, tipo, pedido_id) VALUES (850.00, '2026-08-03 14:05:00', 'PENDENTE', 'CARTAO_CREDITO', 2);

INSERT INTO pagamentos (valor, data, status, tipo, pedido_id) VALUES (2150.00, '2026-08-05 09:20:00', 'PAGO', 'CARTAO_DEBITO', 3);

INSERT INTO pagamentos (valor, data, status, tipo, pedido_id) VALUES (120.00, '2026-08-08 16:50:00', 'PAGO', 'BOLETO', 4);

INSERT INTO pagamentos (valor, data, status, tipo, pedido_id) VALUES (360.00, '2026-08-10 11:25:00', 'CANCELADO', 'PIX', 5);
