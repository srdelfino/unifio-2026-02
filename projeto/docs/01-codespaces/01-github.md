# Passo a passo — Criando o repositório `ecommerce-backend` no GitHub

Este tutorial mostra como criar o repositório **somente pelo portal do GitHub**, configurando:

- README
- License
- `.gitignore` para Java
- Complementação do `.gitignore` com os templates oficiais de Maven, Linux e Visual Studio Code

> **Importante:** o GitHub permite selecionar apenas um template de `.gitignore` durante a criação do repositório. Por isso, vamos selecionar **Java** na criação e depois complementar o arquivo usando os templates oficiais do repositório `github/gitignore`.

---

## 1. Acessar o GitHub

Acesse:

https://github.com/

Faça login com sua conta.

Depois de entrar, no canto superior direito, clique no botão **+** e selecione:

**New repository**

---

## 2. Informar o nome do repositório

Na tela **Create a new repository**, localize o campo:

**Repository name**

Digite exatamente:

```text
ecommerce-backend
```

No campo **Description**, você pode colocar:

```text
Backend de um sistema de e-commerce desenvolvido em Java
```

---

## 3. Escolher a visibilidade

Em **Choose a visibility**, selecione:

**Public**

Assim, o professor poderá acessar o repositório para corrigir a atividade.

> Caso a atividade determine que os repositórios sejam privados, siga a orientação do professor.

---

## 4. Configurar o README

Na seção **Initialize this repository with**, marque:

**Add a README file**

Deixe:

```text
☑ Add a README file
```

O README é o arquivo que normalmente aparece na página inicial do repositório e serve para apresentar e documentar o projeto.

---

## 5. Configurar o `.gitignore`

No campo:

**Add .gitignore**

selecione:

```text
Java
```

Neste momento, não será possível selecionar Java + Maven + Linux + Visual Studio Code ao mesmo tempo.

O GitHub disponibiliza esses templates separadamente no repositório oficial `github/gitignore`.

Portanto, vamos selecionar **Java agora** e complementar os demais posteriormente.

Deixe:

```text
Add .gitignore: Java
```

---

## 6. Configurar a licença

No campo:

**Choose a license**

selecione:

```text
MIT License
```

Isso fará com que o GitHub crie automaticamente um arquivo:

```text
LICENSE
```

---

## 7. Conferir antes de criar

A configuração deve ficar aproximadamente assim:

| Configuração | Valor |
|---|---|
| Owner | Sua conta |
| Repository name | `ecommerce-backend` |
| Description | Backend de um sistema de e-commerce desenvolvido em Java |
| Visibility | Public |
| README | Add a README file |
| `.gitignore` | Java |
| License | MIT License |

Depois disso, clique em:

**Create repository**

---

## 8. Conferir os arquivos criados

Depois que o repositório for criado, na página principal você deverá encontrar algo semelhante a:

```text
ecommerce-backend
│
├── .gitignore
├── LICENSE
└── README.md
```

O arquivo `.gitignore` criado pelo template **Java** já possui regras para arquivos como `.class`, `.jar`, `.war`, `.ear`, arquivos de log e outros artefatos relacionados ao desenvolvimento Java.

Agora precisamos complementá-lo.

---

# 9. Acessar os templates oficiais do GitHub

Abra o repositório oficial:

https://github.com/github/gitignore

Esse é o repositório mantido pelo GitHub que reúne os templates de `.gitignore`.

Vamos utilizar quatro templates:

### Java

https://github.com/github/gitignore/blob/main/Java.gitignore

### Maven

https://github.com/github/gitignore/blob/main/Maven.gitignore

### Linux

https://github.com/github/gitignore/blob/main/Global/Linux.gitignore

### Visual Studio Code

https://github.com/github/gitignore/blob/main/Global/VisualStudioCode.gitignore

---

# 10. Por que vamos complementar o `.gitignore`?

O template **Java** não é exatamente a soma dos templates Java + Maven + Linux + VS Code.

Por exemplo, o template Maven possui regras específicas para:

```text
target/
pom.xml.tag
pom.xml.releaseBackup
pom.xml.versionsBackup
release.properties
.mvn/timing.properties
```

Já o template Linux possui regras para arquivos temporários e arquivos criados pelo sistema operacional, como:

```text
*~
.fuse_hidden*
.directory
.Trash-*
.nfs*
nohup.out
```

O template do Visual Studio Code possui suas próprias regras para arquivos e configurações do editor.

Portanto, para a atividade, vamos **combinar os quatro templates em um único `.gitignore`**.

---

# 11. Abrir o `.gitignore` do projeto

Volte para o repositório:

```text
ecommerce-backend
```

Na lista de arquivos, clique em:

```text
.gitignore
```

Depois clique no botão:

**Edit this file**

ou no ícone de lápis.

Isso permitirá editar o arquivo diretamente pelo navegador.

> **Não é necessário clonar o projeto, instalar Git ou utilizar o terminal.**

---

# 12. Complementar o `.gitignore`

Mantenha o conteúdo Java que já foi criado pelo GitHub e acrescente as regras dos outros templates.

Uma forma organizada de fazer isso é deixar o arquivo dividido em seções:

```gitignore
# Java
# Compiled class file
*.class

# Log file
*.log

# BlueJ files
*.ctxt

# Mobile Tools for Java (J2ME)
.mtj.tmp/

# Package Files #
*.jar
*.war
*.nar
*.ear
*.zip
*.tar.gz
*.rar

# virtual machine crash logs
hs_err_pid*
replay_pid*

# Maven
target/
pom.xml.tag
pom.xml.releaseBackup
pom.xml.versionsBackup
pom.xml.next
release.properties
dependency-reduced-pom.xml
buildNumber.properties
.mvn/timing.properties
.mvn/wrapper/maven-wrapper.jar

# Eclipse
.project
.classpath

# Linux
*~
.fuse_hidden*
.directory
.Trash-*
.nfs*
nohup.out

# Visual Studio Code
.vscode/*
!.vscode/settings.json
!.vscode/tasks.json
!.vscode/launch.json
!.vscode/extensions.json
!.vscode/*.code-snippets

# Local history
.history/
```

> **Importante:** como o conteúdo dos templates oficiais pode mudar ao longo do tempo, em uma aula recomenda-se que os alunos copiem os conteúdos diretamente dos quatro templates oficiais, em vez de utilizar uma versão fixa deste tutorial.

---

# 13. Como copiar os templates

Para evitar erros, faça assim:

## Template Java

Abra:

https://github.com/github/gitignore/blob/main/Java.gitignore

Clique em **Raw** e copie o conteúdo.

Como o repositório já foi criado com Java, **não precisa duplicar as regras Java**.

---

## Template Maven

Abra:

https://github.com/github/gitignore/blob/main/Maven.gitignore

Copie as regras do arquivo e acrescente ao `.gitignore` do projeto.

O template Maven oficial possui regras específicas para `target/`, arquivos de backup do `pom.xml`, propriedades de release, arquivos do Maven Wrapper e arquivos gerados pelo Eclipse.

---

## Template Linux

Abra:

https://github.com/github/gitignore/blob/main/Global/Linux.gitignore

Copie as regras e acrescente ao `.gitignore`.

O template Linux está dentro da pasta `Global` do repositório oficial.

---

## Template Visual Studio Code

Abra:

https://github.com/github/gitignore/blob/main/Global/VisualStudioCode.gitignore

Copie as regras e acrescente ao `.gitignore`.

Esse template também está dentro da pasta `Global`.

---

# 14. Fazer o commit da alteração

Depois de complementar o `.gitignore`, role até o final da página.

Na seção:

**Commit changes**

coloque uma mensagem como:

```text
Complementa o gitignore para Maven, Linux e VS Code
```

Mantenha selecionado:

```text
Commit directly to the main branch
```

Depois clique em:

**Commit changes**

---

# 15. Conferir o resultado

Volte para a página principal do repositório.

Agora você deverá ter pelo menos:

```text
ecommerce-backend
│
├── .gitignore
├── LICENSE
└── README.md
```

E o `.gitignore` deverá contemplar os quatro ambientes:

```text
Java
 ├── arquivos compilados
 ├── logs
 └── pacotes Java

Maven
 ├── target/
 ├── arquivos temporários do Maven
 └── arquivos de release

Linux
 ├── arquivos temporários
 ├── lixeira
 └── arquivos do sistema

Visual Studio Code
 ├── configurações locais
 └── arquivos específicos do editor
```

---

# Resultado esperado

Ao final da atividade, o aluno terá criado **somente pelo portal do GitHub** um repositório chamado:

```text
ecommerce-backend
```

contendo:

```text
ecommerce-backend
│
├── .gitignore    ← Java + Maven + Linux + VS Code
├── LICENSE       ← MIT License
└── README.md     ← criado pelo GitHub
```

A ideia principal da atividade é mostrar que o `.gitignore` não precisa ficar limitado ao template escolhido durante a criação do repositório. O GitHub fornece templates prontos, mas eles podem ser combinados e complementados conforme as tecnologias utilizadas no projeto.

---

## Links oficiais utilizados

- GitHub: https://github.com/
- Repositório oficial de templates: https://github.com/github/gitignore
- Java: https://github.com/github/gitignore/blob/main/Java.gitignore
- Maven: https://github.com/github/gitignore/blob/main/Maven.gitignore
- Linux: https://github.com/github/gitignore/blob/main/Global/Linux.gitignore
- Visual Studio Code: https://github.com/github/gitignore/blob/main/Global/VisualStudioCode.gitignore
