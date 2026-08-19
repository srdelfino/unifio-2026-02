# Tutorial — Criando um GitHub Codespace para o projeto `ecommerce-backend`

## Objetivo

Neste tutorial você aprenderá a criar um **GitHub Codespace** a partir do repositório `ecommerce-backend` criado anteriormente.

O Codespace permitirá desenvolver o projeto diretamente pelo navegador, utilizando uma versão do **Visual Studio Code** integrada ao GitHub.

Neste momento, não é necessário instalar Git, Java, Maven ou Visual Studio Code no computador.

---

## 1. Acessar o GitHub

Acesse:

https://github.com/

Faça login com sua conta.

---

## 2. Abrir o repositório

Depois de entrar no GitHub, acesse o repositório criado anteriormente:

```text
ecommerce-backend
```

Na página do repositório, você deverá encontrar os arquivos:

```text
ecommerce-backend
│
├── .gitignore
├── LICENSE
└── README.md
```

---

## 3. Verificar a branch

Na página principal do repositório, verifique se a branch selecionada é:

```text
main
```

É a partir dessa branch que vamos criar o Codespace.

O GitHub permite criar um Codespace associado a uma branch específica do repositório. Neste caso, utilizaremos a `main`.

---

## 4. Abrir o menu Code

Na página do repositório, localize o botão verde:

**Code**

Clique nele.

Será aberta uma janela com algumas opções.

Selecione:

**Codespaces**

---

## 5. Criar o Codespace

Na área de Codespaces, clique em:

**Create codespace on main**

O GitHub começará a preparar o ambiente de desenvolvimento.

A criação pode levar alguns minutos, principalmente na primeira utilização.

O GitHub também permite criar Codespaces usando opções avançadas, mas para este momento utilizaremos as configurações padrão.

---

## 6. Aguardar a criação do ambiente

Durante esse processo, o GitHub irá preparar automaticamente o ambiente necessário para trabalhar com o repositório.

O Codespace disponibiliza um ambiente de desenvolvimento na nuvem e permite trabalhar com o Visual Studio Code diretamente pelo navegador.

Quando a criação terminar, o **Visual Studio Code será aberto no navegador**.

---

## 7. Conhecendo o Visual Studio Code

A tela do Codespace é muito parecida com o Visual Studio Code instalado no computador.

Você encontrará:

- barra de atividades à esquerda;
- Explorer;
- área de edição;
- terminal;
- barra de status;
- Controle de Código-Fonte.

No Explorer, deverão aparecer os arquivos do repositório:

```text
ecommerce-backend
│
├── .gitignore
├── LICENSE
└── README.md
```

---

## 8. Entendendo o Codespace

É importante entender onde cada elemento está.

```text
┌─────────────────────────────────────────────┐
│                  GitHub                     │
│                                             │
│           ecommerce-backend                 │
│                  │                          │
│                  ▼                          │
│           GitHub Codespace                  │
│                  │                          │
│          ┌───────┴────────┐                 │
│          │                │                 │
│      VS Code Web       Terminal             │
│          │                                │
│          ▼                                │
│    Arquivos do projeto                    │
└─────────────────────────────────────────────┘
```

O **repositório** continua armazenado no GitHub.

O **Codespace** é o ambiente onde vamos trabalhar no projeto.

O **Visual Studio Code** é o editor utilizado dentro desse ambiente.

---

## 9. Abrir o arquivo README.md

No Explorer, clique em:

```text
README.md
```

O arquivo será aberto na área central do Visual Studio Code.

---

## 10. Fazer uma pequena alteração

Para testar o ambiente, acrescente ao final do README:

```markdown
## Projeto

Backend de um sistema de e-commerce desenvolvido em Java.
```

Salve o arquivo.

Neste momento, a alteração está no ambiente do Codespace.

---

## 11. Abrir o Controle de Código-Fonte

Na barra lateral esquerda do Visual Studio Code, localize o ícone:

**Controle de Código-Fonte**

Ele representa o sistema de controle de versão Git.

Clique nesse ícone.

Será aberta uma nova área mostrando as alterações realizadas no projeto.

---

## 12. Conferir a alteração

Na área de Controle de Código-Fonte deverá aparecer algo semelhante a:

```text
ALTERAÇÕES

M README.md
```

A letra:

```text
M
```

significa:

**Modified**

ou seja:

**Modificado**.

Isso significa que o Git identificou que o arquivo `README.md` foi alterado.

---

## 13. Preparar a alteração

Ao lado do arquivo `README.md`, clique no botão:

**+**

A alteração será adicionada à área de preparação.

Ela deverá passar de:

```text
ALTERAÇÕES
```

para:

```text
ALTERAÇÕES PREPARADAS
```

Essa etapa é conhecida como **staging**.

---

## 14. Fazer o commit

Na parte superior do Controle de Código-Fonte existe um campo para informar a mensagem do commit.

Digite:

```text
Atualiza README
```

Depois clique em:

**Commit**

O commit registra a alteração realizada no projeto.

---

## 15. Sincronizar com o GitHub

Depois de realizar o commit, precisamos sincronizar a alteração com o GitHub.

Na área de Controle de Código-Fonte, procure a opção:

**Sincronizar Alterações**

ou:

**Sync Changes**

Clique nessa opção.

Se aparecer uma confirmação, confirme a operação.

A alteração será enviada para o repositório no GitHub.

---

## 16. Entendendo o processo

O processo realizado foi:

```text
Editar README
      ↓
Controle de Código-Fonte
      ↓
+ Preparar alteração
      ↓
Commit
      ↓
Sincronizar Alterações
      ↓
GitHub
```

Isso permite visualizar o fluxo completo entre a alteração realizada no projeto e sua atualização no repositório do GitHub.

---

## 17. Conferir a alteração no GitHub

Volte para a página do repositório no GitHub:

```text
ecommerce-backend
```

Abra o arquivo:

```text
README.md
```

Você deverá encontrar o conteúdo que acabou de adicionar:

```markdown
## Projeto

Backend de um sistema de e-commerce desenvolvido em Java.
```

Isso confirma que a alteração realizada no Codespace foi enviada para o repositório.

---

## 18. Encerrar o trabalho

Quando terminar de trabalhar, você pode simplesmente fechar a aba do navegador.

O Codespace ficará associado à sua conta do GitHub.

Para visualizar seus Codespaces posteriormente, você pode acessar:

**GitHub → Code → Codespaces**

Ali estarão listados os ambientes criados para sua conta.

Um Codespace existente pode ser reaberto posteriormente para continuar o desenvolvimento.

---

# Resultado esperado

Ao final desta atividade, você terá:

```text
GitHub
│
└── ecommerce-backend
    │
    ├── .gitignore
    ├── LICENSE
    └── README.md
             │
             ▼
       GitHub Codespace
             │
             └── Visual Studio Code
                    │
                    ├── Explorer
                    ├── Editor
                    ├── Terminal
                    └── Controle de Código-Fonte
```

E terá realizado todo o processo diretamente pelo navegador.

---

# O que você aprendeu

Ao finalizar esta etapa, você aprendeu a:

1. Abrir um repositório no GitHub.
2. Criar um Codespace a partir da branch `main`.
3. Utilizar o Visual Studio Code pelo navegador.
4. Acessar os arquivos do projeto.
5. Fazer uma alteração no projeto.
6. Utilizar o **Controle de Código-Fonte**.
7. Preparar uma alteração (*staging*).
8. Criar um commit.
9. Sincronizar as alterações com o GitHub.
10. Conferir a alteração diretamente no repositório.

---

## Links oficiais

- GitHub: https://github.com/
- GitHub Codespaces — documentação: https://docs.github.com/pt/codespaces
- Como criar um Codespace para um repositório: https://docs.github.com/pt/codespaces/developing-in-a-codespace/creating-a-codespace-for-a-repository
- Guia rápido do GitHub Codespaces: https://docs.github.com/pt/codespaces/quickstart
