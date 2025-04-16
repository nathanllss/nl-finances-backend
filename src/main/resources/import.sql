-- Inserindo usuários
INSERT INTO tb_user (id, created_at, updated_at, active, name, email_address, phone_number, username, password) VALUES ('123e4567-e89b-12d3-a456-426614174000', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, true, 'João Silva', 'joao@email.com', '11999887766', 'joaosilva', 'senha123');
INSERT INTO tb_user (id, created_at, updated_at, active, name, email_address, phone_number, username, password) VALUES ('223e4567-e89b-12d3-a456-426614174001', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, true, 'Maria Santos', 'maria@email.com', '11998765432', 'mariasantos', 'senha456');

-- Inserindo contas
INSERT INTO tb_account (id, current_balance, user_id) VALUES ('323e4567-e89b-12d3-a456-426614174002', 1000.00, '123e4567-e89b-12d3-a456-426614174000');
INSERT INTO tb_account (id, current_balance, user_id) VALUES ('423e4567-e89b-12d3-a456-426614174003', 2500.00, '223e4567-e89b-12d3-a456-426614174001');

-- Inserindo categorias
INSERT INTO tb_category (name, description, img_url, color_hex, type, account_id) VALUES ('Salário', 'Rendimentos mensais', 'salary-icon.png', '#27AE60', 'INCOME', '323e4567-e89b-12d3-a456-426614174002');
INSERT INTO tb_category (name, description, img_url, color_hex, type, account_id) VALUES ('Alimentação', 'Gastos com comida', 'food-icon.png', '#E74C3C', 'EXPENSE', '323e4567-e89b-12d3-a456-426614174002');
INSERT INTO tb_category (name, description, img_url, color_hex, type, account_id) VALUES ('Freelance', 'Trabalhos extras', 'freelance-icon.png', '#2ECC71', 'INCOME', '423e4567-e89b-12d3-a456-426614174003');
INSERT INTO tb_category (name, description, img_url, color_hex, type, account_id) VALUES ('Transporte', 'Gastos com locomoção', 'transport-icon.png', '#E67E22', 'EXPENSE', '423e4567-e89b-12d3-a456-426614174003');

-- Inserindo transações
INSERT INTO tb_transaction (id, title, description, category_id, type, moment, transaction_value, account_id, recurring) VALUES ('923e4567-e89b-12d3-a456-426614174008', 'Salário Mensal', 'Pagamento de salário', 1, 'INCOME', CURRENT_TIMESTAMP, 5000.00, '323e4567-e89b-12d3-a456-426614174002', true);
INSERT INTO tb_transaction (id, title, description, category_id, type, moment, transaction_value, account_id, recurring) VALUES ('a23e4567-e89b-12d3-a456-426614174009', 'Supermercado', 'Compras do mês', 2, 'EXPENSE', CURRENT_TIMESTAMP, 800.00, '323e4567-e89b-12d3-a456-426614174002', false);
INSERT INTO tb_transaction (id, title, description, category_id, type, moment, transaction_value, account_id, recurring) VALUES ('b23e4567-e89b-12d3-a456-426614174010', 'Projeto Web', 'Desenvolvimento de site', 3, 'INCOME', CURRENT_TIMESTAMP, 3000.00, '423e4567-e89b-12d3-a456-426614174003', false);
INSERT INTO tb_transaction (id, title, description, category_id, type, moment, transaction_value, account_id, recurring) VALUES ('c23e4567-e89b-12d3-a456-426614174011', 'Uber', 'Corridas do mês', 4, 'EXPENSE', CURRENT_TIMESTAMP, 200.00, '423e4567-e89b-12d3-a456-426614174003', true);