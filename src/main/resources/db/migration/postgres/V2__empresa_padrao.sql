INSERT INTO empresa (cod_filial,razao_social,nome_fantasia,cnpjcpf, data_criacao , data_atualizacao ) VALUES 
(nextval('empresa_seq'),'JOYSTICK LOJA LTDA','JOYSTICK LOJA','55.581.722/0001-79', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO funcionario (id, cpf, data_atualizacao, data_criacao, email, nome, perfil, qtd_horas_almoco, qtd_horas_trabalho_dia, senha, valor_hora, empresa_cod_filial) 
VALUES (1, '10862823455', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin@afigueredo.com', 'ADMIN', 'ROLE_ADMIN', NULL, NULL, '$2a$06$xIvBeNRfS65L1N17I7JzgefzxEuLAL0Xk0wFAgIkoNqu9WD6rmp4m', NULL, 
5);
