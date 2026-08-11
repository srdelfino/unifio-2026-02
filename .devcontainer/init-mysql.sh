#!/bin/bash

set -e

echo "Iniciando MySQL..."
service mysql start

echo "Aguardando MySQL ficar pronto..."
for tentativa in $(seq 1 30); do
    if mysqladmin ping --silent; then
        echo "MySQL pronto."
        break
    fi
    sleep 1
done

if ! mysqladmin ping --silent; then
    echo "Erro: MySQL não respondeu a tempo." >&2
    exit 1
fi

echo "Configurando banco..."

mysql <<EOF
CREATE DATABASE IF NOT EXISTS projeto
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

CREATE USER IF NOT EXISTS 'usuario'@'%' IDENTIFIED BY '123@Mudar';

ALTER USER 'usuario'@'%' IDENTIFIED BY '123@Mudar';

GRANT ALL PRIVILEGES ON projeto.* TO 'usuario'@'%';

FLUSH PRIVILEGES;
EOF

echo "MySQL configurado com sucesso!"