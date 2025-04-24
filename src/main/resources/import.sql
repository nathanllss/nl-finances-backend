-- Inserindo usuários
INSERT INTO tb_user (id, created_at, updated_at, active, name, email_address, phone_number, username, password) VALUES ('123e4567-e89b-12d3-a456-426614174000', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, true, 'João Silva', 'joao@email.com', '11999887766', 'joaosilva', '$2a$10$DMMNATLforB1IC98EydDROXvjlZV4HcDrQ.nXS0as16mT.BhAxHjy');
INSERT INTO tb_user (id, created_at, updated_at, active, name, email_address, phone_number, username, password) VALUES ('223e4567-e89b-12d3-a456-426614174001', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, true, 'Maria Santos', 'maria@email.com', '11998765432', 'mariasantos', '$2a$10$DMMNATLforB1IC98EydDROXvjlZV4HcDrQ.nXS0as16mT.BhAxHjy');
INSERT INTO tb_user (id, created_at, updated_at, active, name, email_address, phone_number, username, password) VALUES ('323e4567-e89b-12d3-a456-426614174002', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, true, 'Pedro Oliveira', 'pedro@email.com', '11977889900', 'pedrooliveira', '$2a$10$DMMNATLforB1IC98EydDROXvjlZV4HcDrQ.nXS0as16mT.BhAxHjy');
INSERT INTO tb_user (id, created_at, updated_at, active, name, email_address, phone_number, username, password) VALUES ('423e4567-e89b-12d3-a456-426614174003', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, true, 'Ana Souza', 'ana@email.com', '11966778899', 'anasouza', '$2a$10$DMMNATLforB1IC98EydDROXvjlZV4HcDrQ.nXS0as16mT.BhAxHjy');
INSERT INTO tb_user (id, created_at, updated_at, active, name, email_address, phone_number, username, password) VALUES ('523e4567-e89b-12d3-a456-426614174004', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, true, 'Carlos Mendes', 'carlos@email.com', '11955667788', 'carlosmendes', '$2a$10$DMMNATLforB1IC98EydDROXvjlZV4HcDrQ.nXS0as16mT.BhAxHjy');

INSERT INTO tb_role (authority) VALUES ('ROLE_USER');
INSERT INTO tb_role (authority) VALUES ('ROLE_ADMIN');

INSERT INTO tb_user_role (user_id, role_id) VALUES ('123e4567-e89b-12d3-a456-426614174000', 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES ('223e4567-e89b-12d3-a456-426614174001', 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES ('323e4567-e89b-12d3-a456-426614174002', 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES ('423e4567-e89b-12d3-a456-426614174003', 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES ('523e4567-e89b-12d3-a456-426614174004', 2);

-- Inserindo contas
INSERT INTO tb_account (id, current_balance) VALUES ('623e4567-e89b-12d3-a456-426614174005', 5000.00);
INSERT INTO tb_account (id, current_balance) VALUES ('723e4567-e89b-12d3-a456-426614174006', 3500.00);
INSERT INTO tb_account (id, current_balance) VALUES ('823e4567-e89b-12d3-a456-426614174007', 7800.00);
INSERT INTO tb_account (id, current_balance) VALUES ('923e4567-e89b-12d3-a456-426614174008', 2300.00);
INSERT INTO tb_account (id, current_balance) VALUES ('a23e4567-e89b-12d3-a456-426614174009', 6200.00);

-- Linkando contas aos usuários
UPDATE tb_user SET account_id = '623e4567-e89b-12d3-a456-426614174005' WHERE id = '123e4567-e89b-12d3-a456-426614174000';
UPDATE tb_user SET account_id = '723e4567-e89b-12d3-a456-426614174006' WHERE id = '223e4567-e89b-12d3-a456-426614174001';
UPDATE tb_user SET account_id = '823e4567-e89b-12d3-a456-426614174007' WHERE id = '323e4567-e89b-12d3-a456-426614174002';
UPDATE tb_user SET account_id = '923e4567-e89b-12d3-a456-426614174008' WHERE id = '423e4567-e89b-12d3-a456-426614174003';
UPDATE tb_user SET account_id = 'a23e4567-e89b-12d3-a456-426614174009' WHERE id = '523e4567-e89b-12d3-a456-426614174004';

-- Inserindo categorias de receita
INSERT INTO tb_category (id, name, description, img_url, color_hex, type, account_id) VALUES (1, 'Salário', 'Rendimentos mensais', 'salary-icon.png', '#27AE60', 'INCOME', '623e4567-e89b-12d3-a456-426614174005');
INSERT INTO tb_category (id, name, description, img_url, color_hex, type, account_id) VALUES (2, 'Freelance', 'Trabalhos extras', 'freelance-icon.png', '#2ECC71', 'INCOME', '623e4567-e89b-12d3-a456-426614174005');
INSERT INTO tb_category (id, name, description, img_url, color_hex, type, account_id) VALUES (3, 'Investimentos', 'Rendimentos financeiros', 'investment-icon.png', '#3498DB', 'INCOME', '723e4567-e89b-12d3-a456-426614174006');
INSERT INTO tb_category (id, name, description, img_url, color_hex, type, account_id) VALUES (4, 'Vendas', 'Vendas online', 'sales-icon.png', '#F1C40F', 'INCOME', '823e4567-e89b-12d3-a456-426614174007');
INSERT INTO tb_category (id, name, description, img_url, color_hex, type, account_id) VALUES (5, 'Aluguel', 'Renda de aluguel', 'rent-icon.png', '#E67E22', 'INCOME', '923e4567-e89b-12d3-a456-426614174008');
INSERT INTO tb_category (id, name, description, img_url, color_hex, type, account_id) VALUES (6, 'Alimentação', 'Gastos com comida', 'food-icon.png', '#E74C3C', 'EXPENSE', '623e4567-e89b-12d3-a456-426614174005');
INSERT INTO tb_category (id, name, description, img_url, color_hex, type, account_id) VALUES (7, 'Transporte', 'Gastos com locomoção', 'transport-icon.png', '#9B59B6', 'EXPENSE', '723e4567-e89b-12d3-a456-426614174006');
INSERT INTO tb_category (id, name, description, img_url, color_hex, type, account_id) VALUES (8, 'Moradia', 'Despesas com casa', 'home-icon.png', '#34495E', 'EXPENSE', '823e4567-e89b-12d3-a456-426614174007');
INSERT INTO tb_category (id, name, description, img_url, color_hex, type, account_id) VALUES (9, 'Lazer', 'Entretenimento', 'leisure-icon.png', '#16A085', 'EXPENSE', '923e4567-e89b-12d3-a456-426614174008');
INSERT INTO tb_category (id, name, description, img_url, color_hex, type, account_id) VALUES (10, 'Saúde', 'Gastos médicos', 'health-icon.png', '#C0392B', 'EXPENSE', '623e4567-e89b-12d3-a456-426614174005');

-- Inserindo transacoes
INSERT INTO tb_transaction (id, title, description, category_id, type, moment, transaction_value, account_id, recurring) VALUES ('b23e4567-e89b-12d3-a456-426614174001', 'Salário Janeiro', 'Pagamento mensal', 1, 'INCOME', '2024-01-05 08:00:00-03:00', 5000.00, '623e4567-e89b-12d3-a456-426614174005', true);
INSERT INTO tb_transaction (id, title, description, category_id, type, moment, transaction_value, account_id, recurring) VALUES ('f23e4567-e89b-12d3-a456-426614174051', 'Freelance Website', 'Desenvolvimento frontend', 2, 'INCOME', '2024-04-01 09:00:00-03:00', 3500.00, '623e4567-e89b-12d3-a456-426614174005', false);
INSERT INTO tb_transaction (id, title, description, category_id, type, moment, transaction_value, account_id, recurring) VALUES ('f23e4567-e89b-12d3-a456-426614174052', 'Investimento FII', 'Dividendos mensais', 3, 'INCOME', '2024-04-02 10:30:00-03:00', 850.00, '723e4567-e89b-12d3-a456-426614174006', true);
INSERT INTO tb_transaction (id, title, description, category_id, type, moment, transaction_value, account_id, recurring) VALUES ('f23e4567-e89b-12d3-a456-426614174053', 'Venda Smartphone', 'Venda equipamento usado', 4, 'INCOME', '2024-04-03 14:15:00-03:00', 1200.00, '823e4567-e89b-12d3-a456-426614174007', false);
INSERT INTO tb_transaction (id, title, description, category_id, type, moment, transaction_value, account_id, recurring) VALUES ('f23e4567-e89b-12d3-a456-426614174054', 'Salário Abril', 'Pagamento mensal', 1, 'INCOME', '2024-04-05 08:00:00-03:00', 5000.00, '623e4567-e89b-12d3-a456-426614174005', true);
INSERT INTO tb_transaction (id, title, description, category_id, type, moment, transaction_value, account_id, recurring) VALUES ('f23e4567-e89b-12d3-a456-426614174055', 'Aluguel Comercial', 'Aluguel loja', 5, 'INCOME', '2024-04-05 11:00:00-03:00', 3000.00, '923e4567-e89b-12d3-a456-426614174008', true);
INSERT INTO tb_transaction (id, title, description, category_id, type, moment, transaction_value, account_id, recurring) VALUES ('f23e4567-e89b-12d3-a456-426614174056', 'Conta Luz Abril', 'Fatura mensal', 8, 'EXPENSE', '2024-04-06 13:45:00-03:00', 290.00, '623e4567-e89b-12d3-a456-426614174005', true);
INSERT INTO tb_transaction (id, title, description, category_id, type, moment, transaction_value, account_id, recurring) VALUES ('f23e4567-e89b-12d3-a456-426614174057', 'Supermercado', 'Compras semanais', 6, 'EXPENSE', '2024-04-07 16:20:00-03:00', 450.00, '723e4567-e89b-12d3-a456-426614174006', false);
INSERT INTO tb_transaction (id, title, description, category_id, type, moment, transaction_value, account_id, recurring) VALUES ('f23e4567-e89b-12d3-a456-426614174058', 'Plano Saúde', 'Mensalidade', 10, 'EXPENSE', '2024-04-08 09:30:00-03:00', 600.00, '823e4567-e89b-12d3-a456-426614174007', true);
INSERT INTO tb_transaction (id, title, description, category_id, type, moment, transaction_value, account_id, recurring) VALUES ('f23e4567-e89b-12d3-a456-426614174059', 'Uber', 'Transporte trabalho', 7, 'EXPENSE', '2024-04-09 08:15:00-03:00', 45.00, '923e4567-e89b-12d3-a456-426614174008', false);
INSERT INTO tb_transaction (id, title, description, category_id, type, moment, transaction_value, account_id, recurring) VALUES ('f23e4567-e89b-12d3-a456-426614174060', 'Cinema', 'Ingresso filme', 9, 'EXPENSE', '2024-04-10 20:00:00-03:00', 80.00, '623e4567-e89b-12d3-a456-426614174005', false);
INSERT INTO tb_transaction (id, title, description, category_id, type, moment, transaction_value, account_id, recurring) VALUES ('f23e4567-e89b-12d3-a456-426614174061', 'Curso Online', 'Curso programação', 8, 'EXPENSE', '2024-04-11 14:30:00-03:00', 997.00, '723e4567-e89b-12d3-a456-426614174006', false);
INSERT INTO tb_transaction (id, title, description, category_id, type, moment, transaction_value, account_id, recurring) VALUES ('f23e4567-e89b-12d3-a456-426614174062', 'Consultoria TI', 'Projeto extra', 2, 'INCOME', '2024-04-12 11:00:00-03:00', 2500.00, '823e4567-e89b-12d3-a456-426614174007', false);
INSERT INTO tb_transaction (id, title, description, category_id, type, moment, transaction_value, account_id, recurring) VALUES ('f23e4567-e89b-12d3-a456-426614174063', 'Farmácia', 'Medicamentos', 10, 'EXPENSE', '2024-04-13 15:45:00-03:00', 123.50, '923e4567-e89b-12d3-a456-426614174008', false);
INSERT INTO tb_transaction (id, title, description, category_id, type, moment, transaction_value, account_id, recurring) VALUES ('f23e4567-e89b-12d3-a456-426614174064', 'Internet', 'Mensalidade', 8, 'EXPENSE', '2024-04-14 10:00:00-03:00', 159.90, '623e4567-e89b-12d3-a456-426614174005', true);
INSERT INTO tb_transaction (id, title, description, category_id, type, moment, transaction_value, account_id, recurring) VALUES ('f23e4567-e89b-12d3-a456-426614174065', 'Rendimentos CDB', 'Investimento mensal', 3, 'INCOME', '2024-04-15 09:00:00-03:00', 450.00, '723e4567-e89b-12d3-a456-426614174006', true);
INSERT INTO tb_transaction (id, title, description, category_id, type, moment, transaction_value, account_id, recurring) VALUES ('f23e4567-e89b-12d3-a456-426614174066', 'Restaurante', 'Almoço negócios', 6, 'EXPENSE', '2024-04-16 13:30:00-03:00', 175.00, '823e4567-e89b-12d3-a456-426614174007', false);
INSERT INTO tb_transaction (id, title, description, category_id, type, moment, transaction_value, account_id, recurring) VALUES ('f23e4567-e89b-12d3-a456-426614174067', 'Manutenção Carro', 'Troca óleo', 7, 'EXPENSE', '2024-04-17 11:20:00-03:00', 380.00, '923e4567-e89b-12d3-a456-426614174008', false);
INSERT INTO tb_transaction (id, title, description, category_id, type, moment, transaction_value, account_id, recurring) VALUES ('f23e4567-e89b-12d3-a456-426614174068', 'Venda Curso', 'Curso online criado', 4, 'INCOME', '2024-04-18 16:45:00-03:00', 1500.00, '623e4567-e89b-12d3-a456-426614174005', false);
INSERT INTO tb_transaction (id, title, description, category_id, type, moment, transaction_value, account_id, recurring) VALUES ('f23e4567-e89b-12d3-a456-426614174069', 'Academia', 'Mensalidade', 9, 'EXPENSE', '2024-04-19 08:00:00-03:00', 120.00, '723e4567-e89b-12d3-a456-426614174006', true);
INSERT INTO tb_transaction (id, title, description, category_id, type, moment, transaction_value, account_id, recurring) VALUES ('f23e4567-e89b-12d3-a456-426614174070', 'Consultoria Design', 'Projeto logo', 2, 'INCOME', '2024-04-20 14:30:00-03:00', 800.00, '823e4567-e89b-12d3-a456-426614174007', false);
INSERT INTO tb_transaction (id, title, description, category_id, type, moment, transaction_value, account_id, recurring) VALUES ('550e8400-e29b-41d4-a716-446655440000', 'Salário', 'Salário mensal', 1, 'INCOME', '2024-03-01T08:00:00-03:00', 5000.00, 'a23e4567-e89b-12d3-a456-426614174009', true);
INSERT INTO tb_transaction (id, title, description, category_id, type, moment, transaction_value, account_id, recurring) VALUES ('550e8400-e29b-41d4-a716-446655440001', 'Aluguel', 'Pagamento do aluguel', 2, 'EXPENSE', '2024-03-05T10:00:00-03:00', 1200.00, 'a23e4567-e89b-12d3-a456-426614174009', true);
INSERT INTO tb_transaction (id, title, description, category_id, type, moment, transaction_value, account_id, recurring) VALUES ('550e8400-e29b-41d4-a716-446655440002', 'Supermercado', 'Compras mensais', 3, 'EXPENSE', '2024-03-10T15:30:00-03:00', 800.00, 'a23e4567-e89b-12d3-a456-426614174009', false);
INSERT INTO tb_transaction (id, title, description, category_id, type, moment, transaction_value, account_id, recurring) VALUES ('550e8400-e29b-41d4-a716-446655440003', 'Freelance', 'Projeto extra', 1, 'INCOME', '2024-03-15T14:00:00-03:00', 2500.00, 'a23e4567-e89b-12d3-a456-426614174009', false);
INSERT INTO tb_transaction (id, title, description, category_id, type, moment, transaction_value, account_id, recurring) VALUES ('550e8400-e29b-41d4-a716-446655440004', 'Energia', 'Conta de luz', 4, 'EXPENSE', '2024-03-07T09:00:00-03:00', 150.00, 'a23e4567-e89b-12d3-a456-426614174009', true);
INSERT INTO tb_transaction (id, title, description, category_id, type, moment, transaction_value, account_id, recurring) VALUES ('550e8400-e29b-41d4-a716-446655440005', 'Internet', 'Conta de internet', 4, 'EXPENSE', '2024-03-08T11:00:00-03:00', 120.00, 'a23e4567-e89b-12d3-a456-426614174009', true);
INSERT INTO tb_transaction (id, title, description, category_id, type, moment, transaction_value, account_id, recurring) VALUES ('550e8400-e29b-41d4-a716-446655440006', 'Academia', 'Mensalidade', 5, 'EXPENSE', '2024-03-05T08:00:00-03:00', 100.00, 'a23e4567-e89b-12d3-a456-426614174009', true);
INSERT INTO tb_transaction (id, title, description, category_id, type, moment, transaction_value, account_id, recurring) VALUES ('550e8400-e29b-41d4-a716-446655440007', 'Investimentos', 'Aporte mensal', 6, 'EXPENSE', '2024-03-01T10:00:00-03:00', 1000.00, 'a23e4567-e89b-12d3-a456-426614174009', true);
INSERT INTO tb_transaction (id, title, description, category_id, type, moment, transaction_value, account_id, recurring) VALUES ('550e8400-e29b-41d4-a716-446655440008', 'Venda item usado', 'Venda notebook antigo', 7, 'INCOME', '2024-03-20T16:00:00-03:00', 2000.00, 'a23e4567-e89b-12d3-a456-426614174009', false);
INSERT INTO tb_transaction (id, title, description, category_id, type, moment, transaction_value, account_id, recurring) VALUES ('550e8400-e29b-41d4-a716-446655440009', 'Restaurante', 'Almoço fora', 8, 'EXPENSE', '2024-03-12T13:00:00-03:00', 50.00, 'a23e4567-e89b-12d3-a456-426614174009', false);

INSERT INTO tb_budget (id, account_id, name, planned_amount, spent_amount, period, start_date, end_date) VALUES ('c23e4567-e89b-12d3-a456-426614174001', '623e4567-e89b-12d3-a456-426614174005', 'Orçamento Mensal', 6000.00, 4500.00, 'MONTHLY', '2024-03-01', '2024-03-31');
INSERT INTO tb_budget (id, account_id, name, planned_amount, spent_amount, period, start_date, end_date) VALUES ('c23e4567-e89b-12d3-a456-426614174002', '723e4567-e89b-12d3-a456-426614174006', 'Orçamento Trimestral', 15000.00, 12000.00, 'QUARTERLY', '2024-01-01', '2024-03-31');

-- Inserindo sumarios
INSERT INTO tb_transaction_summary (id, account_id, total_income, total_spent, expenses_by_category_json) VALUES ('d23e4567-e89b-12d3-a456-426614174001', '623e4567-e89b-12d3-a456-426614174005', 7500.00, 4500.00, '{"Alimentação": 800.00, "Saúde": 300.00}');
INSERT INTO tb_transaction_summary (id, account_id, total_income, total_spent, expenses_by_category_json) VALUES ('d23e4567-e89b-12d3-a456-426614174002', '723e4567-e89b-12d3-a456-426614174006', 5000.00, 3000.00, '{"Transporte": 250.00, "Moradia": 1500.00}');


-- Inserindo relações entre budgets e categorias para o orçamento mensal
INSERT INTO tb_budget_category (budget_id, category_id) VALUES ('c23e4567-e89b-12d3-a456-426614174001', 6);
INSERT INTO tb_budget_category (budget_id, category_id) VALUES ('c23e4567-e89b-12d3-a456-426614174001', 10);
INSERT INTO tb_budget_category (budget_id, category_id) VALUES ('c23e4567-e89b-12d3-a456-426614174001', 8);
INSERT INTO tb_budget_category (budget_id, category_id) VALUES ('c23e4567-e89b-12d3-a456-426614174001', 9);
INSERT INTO tb_budget_category (budget_id, category_id) VALUES ('c23e4567-e89b-12d3-a456-426614174001', 7);

-- Inserindo relações entre budgets e categorias para o orçamento trimestral
INSERT INTO tb_budget_category (budget_id, category_id) VALUES ('c23e4567-e89b-12d3-a456-426614174002', 6);
INSERT INTO tb_budget_category (budget_id, category_id) VALUES ('c23e4567-e89b-12d3-a456-426614174002', 8);
INSERT INTO tb_budget_category (budget_id, category_id) VALUES ('c23e4567-e89b-12d3-a456-426614174002', 7);
INSERT INTO tb_budget_category (budget_id, category_id) VALUES ('c23e4567-e89b-12d3-a456-426614174002', 10);
INSERT INTO tb_budget_category (budget_id, category_id) VALUES ('c23e4567-e89b-12d3-a456-426614174002', 9);
