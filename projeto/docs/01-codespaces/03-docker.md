# Tutorial — Personalizando o GitHub Codespaces com Docker e Dev Containers

## Objetivo

Neste tutorial vamos personalizar o ambiente de desenvolvimento do **GitHub Codespaces** utilizando **Docker** e **Dev Containers**.

A situação que motivou essa configuração foi simples: o container criado inicialmente pelo Codespaces não possuía todas as ferramentas necessárias para o desenvolvimento do projeto.

Para resolver esse problema, vamos criar uma configuração personalizada dentro da pasta:

```text
.devcontainer
```

Ao final, o Codespace será reconstruído utilizando essa nova configuração.

---

# 1. Por que personalizar o container?

Quando criamos um Codespace, o GitHub prepara automaticamente um ambiente de desenvolvimento.

Esse ambiente atende a muitos projetos, mas pode não possuir todas as ferramentas necessárias para uma aplicação específica.

No nosso projeto, precisamos de um ambiente preparado para desenvolvimento de uma aplicação **Java com Spring Boot e Maven**, além de um banco de dados **MySQL**.

Por isso, vamos criar nosso próprio ambiente utilizando Docker.

O resultado será:

```text
GitHub Codespace
       │
       ▼
Configuração .devcontainer
       │
       ▼
Docker
       │
       ▼
Container personalizado
       │
       ├── Java 21
       ├── Maven 3.9
       ├── MySQL
       ├── Git
       ├── Git LFS
       ├── curl
       ├── zip
       └── unzip
```

---

# 2. O que é Docker?

O **Docker** permite criar e executar ambientes isolados chamados **containers**.

Um container pode possuir todas as ferramentas necessárias para executar uma aplicação.

Por exemplo:

```text
┌──────────────────────────────┐
│          Container           │
│                              │
│       Ubuntu                 │
│       Java                   │
│       Maven                  │
│       MySQL                  │
│       Git                    │
│       outras ferramentas     │
│                              │
└──────────────────────────────┘
```

A grande vantagem é poder definir esse ambiente em arquivos e reproduzi-lo sempre que necessário.

---

# 3. O que é um Dev Container?

Um **Dev Container** é um ambiente de desenvolvimento baseado em container.

O Visual Studio Code e o GitHub Codespaces conseguem utilizar uma configuração chamada:

```text
.devcontainer
```

Essa configuração informa como o ambiente de desenvolvimento deve ser criado.

Neste projeto utilizaremos um **Dockerfile**.

---

# 4. Estrutura da configuração

Nossa configuração possui três arquivos:

```text
.devcontainer
│
├── devcontainer.json
├── Dockerfile
└── init-mysql.sh
```

Cada arquivo possui uma responsabilidade:

| Arquivo | Responsabilidade |
|---|---|
| `devcontainer.json` | Configura o ambiente utilizado pelo VS Code/Codespaces |
| `Dockerfile` | Define como o container será construído |
| `init-mysql.sh` | Inicializa e configura o banco de dados MySQL |

---

# 5. O arquivo `Dockerfile`

O primeiro arquivo que vamos analisar é:

```text
.devcontainer/Dockerfile
```

Esse arquivo define a imagem que será utilizada para construir o nosso container.

## Conteúdo

```dockerfile
FROM maven:3.9-eclipse-temurin-21-noble

ENV DEBIAN_FRONTEND=noninteractive

RUN apt-get update && apt-get install -y \
    mysql-server-8.0 \
    git \
    git-lfs \
    curl \
    unzip \
    zip \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/*
```

---

# 6. Entendendo o `FROM`

A primeira instrução é:

```dockerfile
FROM maven:3.9-eclipse-temurin-21-noble
```

O `FROM` define a imagem que será utilizada como ponto de partida.

Nesse caso estamos utilizando uma imagem que já possui:

- Maven 3.9;
- Java 21;
- Eclipse Temurin;
- Ubuntu 24.04 como base.

Portanto, não precisamos instalar Java e Maven manualmente.

A imagem já fornece essas ferramentas.

Podemos representar:

```text
Imagem Maven + Java 21
           │
           ▼
     Nossa imagem
           │
           ├── MySQL
           ├── Git
           ├── Git LFS
           ├── curl
           ├── zip
           └── unzip
```

---

# 7. Configurando o ambiente de instalação

Temos também:

```dockerfile
ENV DEBIAN_FRONTEND=noninteractive
```

Essa configuração evita que alguns comandos de instalação do Linux interrompam o processo esperando respostas do usuário.

Isso é importante porque o container precisa ser construído automaticamente.

---

# 8. Instalando ferramentas

A principal instalação está neste trecho:

```dockerfile
RUN apt-get update && apt-get install -y \
    mysql-server-8.0 \
    git \
    git-lfs \
    curl \
    unzip \
    zip \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/*
```

O comando instala:

### MySQL

```text
mysql-server-8.0
```

Será utilizado como banco de dados da aplicação.

### Git

```text
git
```

Permite trabalhar com o controle de versão.

### Git LFS

```text
git-lfs
```

É utilizado para trabalhar com arquivos grandes dentro de repositórios Git.

### curl

```text
curl
```

Pode ser utilizado para realizar requisições HTTP.

Por exemplo:

```bash
curl http://localhost:8080
```

### unzip e zip

São ferramentas utilizadas para compactar e descompactar arquivos.

---

# 9. Por que limpar o cache?

No final do comando temos:

```dockerfile
apt-get clean
```

e:

```dockerfile
rm -rf /var/lib/apt/lists/*
```

Esses comandos removem arquivos temporários utilizados durante a instalação.

Isso ajuda a evitar que arquivos desnecessários permaneçam na imagem final.

---

# 10. O arquivo `devcontainer.json`

Agora vamos analisar:

```text
.devcontainer/devcontainer.json
```

Esse arquivo informa ao Visual Studio Code e ao GitHub Codespaces como utilizar o container.

A configuração utilizada no projeto é:

```json
{
    "name": "Projeto",

    "build": {
        "dockerfile": "Dockerfile"
    },

    "forwardPorts": [8080, 3306],

    "customizations": {
        "vscode": {
            "extensions": [
                "ms-azuretools.vscode-docker",
                "vscjava.vscode-java-pack",
                "vmware.vscode-spring-boot",
                "vscjava.vscode-spring-boot-dashboard",
                "cweijan.vscode-mysql-client2",
                "vscode-spring-initializr"
            ],

            "settings": {
                "workbench.colorTheme": "Dark Modern",
                "editor.fontSize": 14,
                "terminal.integrated.fontSize": 14,
                "java.server.launchMode": "Standard"
            }
        }
    },

    "postCreateCommand": "/bin/bash .devcontainer/init-mysql.sh",

    "postStartCommand": "service mysql start"
}
```

---

# 11. Nome do ambiente

A propriedade:

```json
"name": "Projeto"
```

define o nome do ambiente de desenvolvimento.

Esse nome pode aparecer em opções relacionadas aos Dev Containers.

---

# 12. Informando o Dockerfile

Temos:

```json
"build": {
    "dockerfile": "Dockerfile"
}
```

Aqui estamos dizendo:

> Para construir o ambiente, utilize o arquivo `Dockerfile`.

Como o `Dockerfile` está dentro da mesma pasta do `devcontainer.json`, podemos utilizar:

```text
Dockerfile
```

---

# 13. Encaminhando portas

A configuração:

```json
"forwardPorts": [8080, 3306]
```

define as portas que queremos disponibilizar.

Temos:

```text
8080
```

para a aplicação Spring Boot.

E:

```text
3306
```

para o MySQL.

Podemos representar:

```text
Container
│
├── 8080 → Spring Boot
│
└── 3306 → MySQL
```

O Codespaces consegue disponibilizar essas portas para acesso durante o desenvolvimento.

---

# 14. Instalando extensões do Visual Studio Code

Na configuração temos:

```json
"customizations": {
    "vscode": {
        "extensions": [
            "ms-azuretools.vscode-docker",
            "vscjava.vscode-java-pack",
            "vmware.vscode-spring-boot",
            "vscjava.vscode-spring-boot-dashboard",
            "cweijan.vscode-mysql-client2",
            "vscode-spring-initializr"
        ]
    }
}
```

Isso permite que as extensões sejam instaladas automaticamente no ambiente.

As extensões utilizadas são:

| Extensão | Finalidade |
|---|---|
| Docker | Gerenciamento de containers e imagens |
| Java Extension Pack | Suporte ao desenvolvimento Java |
| Spring Boot | Recursos para desenvolvimento Spring Boot |
| Spring Boot Dashboard | Visualização das aplicações Spring Boot |
| MySQL Client | Acesso ao banco pelo VS Code |
| Spring Initializr | Auxilia na criação de projetos Spring Boot |

---

# 15. Configurações do Visual Studio Code

Também definimos algumas configurações:

```json
"settings": {
    "workbench.colorTheme": "Dark Modern",
    "editor.fontSize": 14,
    "terminal.integrated.fontSize": 14,
    "java.server.launchMode": "Standard"
}
```

Essas configurações permitem padronizar algumas características do ambiente.

Por exemplo:

```text
Tema: Dark Modern
Fonte do editor: 14
Fonte do terminal: 14
```

A configuração:

```text
java.server.launchMode
```

define o modo de inicialização do servidor de linguagem Java.

---

# 16. O arquivo `init-mysql.sh`

O terceiro arquivo é:

```text
.devcontainer/init-mysql.sh
```

Esse arquivo é um **script Bash**.

Sua função é preparar o banco de dados MySQL automaticamente.

O conteúdo utilizado no projeto é baseado na seguinte estrutura:

```bash
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
CREATE DATABASE IF NOT EXISTS ecommerce
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

CREATE USER IF NOT EXISTS 'aluno'@'%' IDENTIFIED BY 'SENHA_DO_BANCO';

GRANT ALL PRIVILEGES ON ecommerce.* TO 'aluno'@'%';

FLUSH PRIVILEGES;
EOF

echo "MySQL configurado com sucesso!"
```

> **Atenção:** a senha apresentada aqui é apenas um exemplo. Nunca coloque senhas reais ou credenciais pessoais diretamente no material didático ou em um repositório público.

---

# 17. Iniciando o MySQL

O script começa executando:

```bash
service mysql start
```

Esse comando inicia o servidor MySQL dentro do container.

---

# 18. Aguardando o MySQL

O MySQL pode precisar de alguns segundos para começar completamente.

Por isso, o script verifica se o servidor já está respondendo:

```bash
mysqladmin ping --silent
```

O script tenta até 30 vezes.

Entre cada tentativa existe:

```bash
sleep 1
```

Ou seja, ele espera um segundo antes de tentar novamente.

O processo pode ser representado assim:

```text
Inicia MySQL
    ↓
MySQL está pronto?
    │
 ┌──┴──┐
Não    Sim
 │      │
 ▼      ▼
Espera  Continua
 1s
 │
 └──→ tenta novamente
```

---

# 19. Criando o banco de dados

Depois que o MySQL estiver disponível, o script executa:

```sql
CREATE DATABASE IF NOT EXISTS ecommerce
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;
```

Isso cria o banco:

```text
ecommerce
```

O `IF NOT EXISTS` evita erro caso o banco já exista.

---

# 20. Criando o usuário

O script também cria um usuário para a aplicação:

```sql
CREATE USER IF NOT EXISTS 'aluno'@'%' IDENTIFIED BY 'SENHA_DO_BANCO';
```

Nesse exemplo:

```text
Usuário: aluno
Senha: SENHA_DO_BANCO
```

Em um projeto real, credenciais não devem ficar expostas diretamente dessa maneira.

Para fins didáticos, estamos utilizando uma configuração simples para facilitar a criação automática do ambiente.

---

# 21. Concedendo permissões

Depois de criar o usuário, temos:

```sql
GRANT ALL PRIVILEGES ON ecommerce.* TO 'aluno'@'%';
```

Isso concede ao usuário permissões sobre o banco:

```text
ecommerce
```

Por fim:

```sql
FLUSH PRIVILEGES;
```

solicita ao MySQL que atualize as permissões.

---

# 22. Quando o script é executado?

Agora precisamos entender como o `devcontainer.json` utiliza o script.

Temos:

```json
"postCreateCommand": "/bin/bash .devcontainer/init-mysql.sh"
```

Isso significa que o script será executado depois que o container for criado.

O fluxo é:

```text
Container criado
      ↓
postCreateCommand
      ↓
init-mysql.sh
      ↓
Inicia MySQL
      ↓
Cria banco ecommerce
      ↓
Cria usuário
      ↓
Configura permissões
```

---

# 23. O que acontece quando o container é iniciado novamente?

Também temos:

```json
"postStartCommand": "service mysql start"
```

Esse comando é executado quando o container é iniciado.

Assim, o MySQL é iniciado novamente sempre que o ambiente for iniciado.

O fluxo fica:

```text
Container inicia
      ↓
postStartCommand
      ↓
service mysql start
      ↓
MySQL disponível
```

---

# 24. Como tudo funciona junto?

Agora podemos visualizar os três arquivos trabalhando em conjunto:

```text
                 .devcontainer
                       │
          ┌────────────┼────────────┐
          │            │            │
          ▼            ▼            ▼
   devcontainer.json  Dockerfile  init-mysql.sh
          │            │            │
          │            ▼            │
          │       Constrói imagem   │
          │            │            │
          └────────────┼────────────┘
                       ▼
                   Container
                       │
                       ├── Java 21
                       ├── Maven
                       ├── Git
                       ├── MySQL
                       └── Ferramentas
                       │
                       ▼
                GitHub Codespace
```

O `Dockerfile` define **o que existe dentro do container**.

O `devcontainer.json` define **como o VS Code/Codespaces deve utilizar esse container**.

O `init-mysql.sh` executa **a configuração inicial do banco de dados**.

---

# 25. Adicionando os arquivos ao projeto

No seu repositório `ecommerce-backend`, crie a pasta:

```text
.devcontainer
```

Dentro dela, crie:

```text
.devcontainer
│
├── devcontainer.json
├── Dockerfile
└── init-mysql.sh
```

O projeto ficará:

```text
ecommerce-backend
│
├── .devcontainer
│   ├── devcontainer.json
│   ├── Dockerfile
│   └── init-mysql.sh
│
├── .gitignore
├── LICENSE
└── README.md
```

---

# 26. Fazer o Rebuild Container

Depois que os três arquivos estiverem no projeto, precisamos solicitar ao Codespaces que reconstrua o ambiente.

No Visual Studio Code, abra a:

**Paleta de Comandos**

Utilize:

```text
Ctrl + Shift + P
```

Digite:

```text
Codespaces: Rebuild Container
```

Selecione:

**Codespaces: Rebuild Container**

---

# 27. O que acontece durante o Rebuild?

O Codespaces irá ler a configuração:

```text
.devcontainer/devcontainer.json
```

O `devcontainer.json` informará que deve utilizar:

```text
Dockerfile
```

O Docker então construirá uma nova imagem.

Podemos representar:

```text
devcontainer.json
       │
       ▼
   Dockerfile
       │
       ▼
Construção da imagem
       │
       ▼
Novo container
       │
       ▼
postCreateCommand
       │
       ▼
init-mysql.sh
       │
       ▼
MySQL configurado
```

---

# 28. Aguardar a reconstrução

O processo pode levar alguns minutos.

Durante esse período, o Codespaces poderá apresentar mensagens relacionadas à construção e inicialização do container.

Aguarde até que o Visual Studio Code seja carregado novamente.

---

# 29. Verificar o ambiente

Depois que o Rebuild terminar, verifique:

- se o projeto está aberto;
- se o Java está funcionando;
- se o Maven está disponível;
- se o MySQL foi iniciado;
- se as extensões foram instaladas;
- se o terminal está funcionando.

O ambiente agora deverá estar preparado para o desenvolvimento do projeto.

---

# 30. Resultado final

Ao terminar, teremos:

```text
GitHub
   │
   ▼
ecommerce-backend
   │
   ├── .devcontainer
   │      ├── devcontainer.json
   │      ├── Dockerfile
   │      └── init-mysql.sh
   │
   ├── .gitignore
   ├── LICENSE
   └── README.md
   │
   ▼
GitHub Codespace
   │
   ▼
Container personalizado
   │
   ├── Java 21
   ├── Maven 3.9
   ├── MySQL
   ├── Git
   ├── Git LFS
   ├── curl
   ├── zip
   └── unzip
```

---

# O que você aprendeu

Ao finalizar esta atividade, você aprendeu:

1. Por que o ambiente padrão de um Codespace pode precisar de personalização.
2. O que é Docker.
3. O que é um container.
4. O que é um Dev Container.
5. Para que serve a pasta `.devcontainer`.
6. Como utilizar um `Dockerfile`.
7. Como configurar o ambiente por meio do `devcontainer.json`.
8. Como utilizar um script Bash para configurar o MySQL.
9. Como automatizar a configuração do banco de dados.
10. Como executar um **Rebuild Container** no GitHub Codespaces.
11. Como criar um ambiente de desenvolvimento padronizado para um projeto.

---

# Resumo

A ideia principal desta atividade é que o ambiente de desenvolvimento também pode fazer parte do projeto.

Em vez de cada desenvolvedor precisar configurar manualmente todas as ferramentas, podemos descrever essa configuração em arquivos:

```text
.devcontainer
     │
     ├── devcontainer.json
     ├── Dockerfile
     └── init-mysql.sh
```

O GitHub Codespaces utiliza esses arquivos para construir um ambiente de desenvolvimento personalizado e reproduzível.
