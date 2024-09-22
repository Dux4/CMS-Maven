# CMS (Content Management System)

Este projeto é um simples Sistema de Gerenciamento de Conteúdo (CMS) implementado em Java, com a funcionalidade de selecionar o tipo de persistência (em memória ou banco de dados HSQLDB). O CMS permite gerenciar conteúdo e usuários, criando, listando, atualizando e removendo entradas.

## Funcionalidades

- Gerenciamento de conteúdo:
  - Criar, listar, atualizar e remover conteúdo.
  
- Gerenciamento de usuários:
  - Criar, listar, atualizar e remover usuários.
  - Validação de login.
  - Alteração de senha.
  
- Persistência de dados:
  - Suporte para persistência em memória ou HSQLDB.


## Como Executar

1. **Seleção de persistência**: Ao iniciar a aplicação, o usuário pode escolher entre persistência em memória ou no banco de dados HSQLDB.

2. **Usuário padrão**: Quando a persistência é selecionada, um usuário padrão com o nome de usuário `admin` e senha `admin` é criado automaticamente.
   
3. **Operações de conteúdo**: O usuário pode criar, listar, atualizar e remover conteúdos de acordo com o tipo de persistência escolhido.

4. **Operações de usuário**: O CMS oferece funcionalidades de CRUD para gerenciar usuários, além de uma opção para alterar a senha do usuário logado.


## Estrutura do Projeto

### Classes e Interfaces

#### `Content.java`
Representa uma entidade de conteúdo no sistema, com atributos como título e descrição. Cada instância de `Content` contém os dados a serem gerenciados no CMS.

#### `HSQLContent.java`
Implementa a interface `Persistencia<Content>`, que define a persistência de conteúdo no banco de dados HSQLDB. Ele executa operações de CRUD (Criar, Ler, Atualizar e Deletar) no banco.

#### `HSQLUser.java`
Implementa a interface `UserPersistencia` para a persistência de usuários no banco de dados HSQLDB. Executa operações de CRUD de usuários diretamente no banco, além de garantir que um usuário padrão "admin" seja criado automaticamente.

#### `Main.java`
O ponto de entrada da aplicação. Ele inicializa a interface do usuário (`TextUserInterface`) e permite a seleção do tipo de persistência (memória ou HSQLDB) para o conteúdo e usuários.

#### `MemoryContent.java`
Uma implementação da interface `Persistencia<Content>` para manter o conteúdo em memória. Útil para testes ou quando a persistência em banco de dados não é necessária.

#### `Persistencia.java`
Uma interface genérica que define as operações básicas de persistência para conteúdo. Implementações desta interface (como `MemoryContent` e `HSQLContent`) fornecem diferentes mecanismos de armazenamento.

#### `ServiceContent.java`
É a classe de serviço para manipulação de conteúdo. Ela delega as operações de CRUD para a implementação de persistência fornecida (seja `MemoryContent` ou `HSQLContent`).

#### `TextUserInterface.java`
A interface de texto que o usuário interage para utilizar o CMS. Apresenta o menu inicial e as opções de seleção de persistência para conteúdo e usuários. Além disso, oferece menus para operações de CRUD em conteúdo e usuários.

#### `User.java`
Representa uma entidade de usuário com atributos `username` e `password`. Usado para autenticação e gerenciamento de usuários no sistema.

#### `UserMemory.java`
Uma implementação da interface `UserPersistencia` para manter os usuários em memória. Executa operações de CRUD e login sem a necessidade de um banco de dados.

#### `UserPersistencia.java`
Uma interface que define as operações de persistência de usuários (criação, listagem, atualização e remoção de usuários). Pode ter diferentes implementações, como `UserMemory` ou `HSQLUser`.

#### `UserService.java`
Responsável pelo gerenciamento dos usuários. Utiliza uma implementação de `UserPersistencia` (como `HSQLUser` ou `UserMemory`) para realizar as operações de CRUD e login de usuários.


## Tecnologias Utilizadas

- **Java 21**: Linguagem de programação.
- **HSQLDB**: Banco de dados relacional embutido para persistência.
- **Maven**: Ferramenta de gerenciamento de dependências e build.

## Dependências

Este projeto usa as seguintes dependências, configuradas no `pom.xml`:
