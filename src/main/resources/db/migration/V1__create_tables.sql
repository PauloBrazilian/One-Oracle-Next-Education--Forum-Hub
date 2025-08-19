CREATE TABLE perfil (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE usuario (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL
);

CREATE TABLE usuario_perfis (
    usuario_id BIGINT NOT NULL,
    perfis_id BIGINT NOT NULL,
    PRIMARY KEY (usuario_id, perfis_id),
    FOREIGN KEY (usuario_id) REFERENCES usuario(id),
    FOREIGN KEY (perfis_id) REFERENCES perfil(id)
);

CREATE TABLE curso (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    categoria VARCHAR(100) NOT NULL
);

CREATE TABLE topico (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(100) NOT NULL,
    mensagem TEXT NOT NULL,
    data_criacao DATETIME NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'NAO_RESPONDIDO',
    autor_id BIGINT NOT NULL,
    curso_id BIGINT NOT NULL,
    FOREIGN KEY (autor_id) REFERENCES usuario(id),
    FOREIGN KEY (curso_id) REFERENCES curso(id)
);

CREATE TABLE resposta (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    mensagem TEXT NOT NULL,
    data_criacao DATETIME NOT NULL,
    autor_id BIGINT NOT NULL,
    topico_id BIGINT NOT NULL,
    solucao BOOLEAN NOT NULL DEFAULT FALSE,
    FOREIGN KEY (autor_id) REFERENCES usuario(id),
    FOREIGN KEY (topico_id) REFERENCES topico(id)
);

INSERT INTO perfil (nome) VALUES ('ROLE_ALUNO'), ('ROLE_MODERADOR'), ('ROLE_ADMIN');

INSERT INTO usuario (nome, email, senha) VALUES
('Admin', 'admin@forumhub.com', '$2a$10$Y50UaMFOxteibQEYLrwuHeehHYfcoafCopUazP12.rqB41bsolF5.'),
('João Silva', 'joao@email.com', '$2a$10$Y50UaMFOxteibQEYLrwuHeehHYfcoafCopUazP12.rqB41bsolF5.'),
('Maria Santos', 'maria@email.com', '$2a$10$Y50UaMFOxteibQEYLrwuHeehHYfcoafCopUazP12.rqB41bsolF5.');

INSERT INTO usuario_perfis (usuario_id, perfis_id) VALUES
(1, 3),
(2, 1),
(3, 2);

INSERT INTO curso (nome, categoria) VALUES
('Spring Boot', 'Programação'),
('HTML e CSS', 'Front-end'),
('JavaScript', 'Front-end'),
('React', 'Front-end'),
('SQL', 'Banco de Dados');