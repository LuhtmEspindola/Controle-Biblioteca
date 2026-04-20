# Sistema de Controle de Biblioteca

Sistema web para gerenciamento de biblioteca desenvolvido com Spring Boot, MongoDB e Thymeleaf, seguindo o padrão MVC com camada de serviços.

## Arquitetura

```
src/main/java/com/biblioteca/
├── model/          Entidades de domínio (Model)
│   ├── Usuario.java
│   ├── Livro.java
│   ├── Emprestimo.java
│   ├── Reserva.java
│   └── Multa.java
├── repository/     Camada de acesso a dados (Spring Data MongoDB)
│   ├── UsuarioRepository.java
│   ├── LivroRepository.java
│   ├── EmprestimoRepository.java
│   ├── ReservaRepository.java
│   └── MultaRepository.java
├── service/        Camada de regras de negócio
│   ├── UsuarioService.java
│   ├── LivroService.java
│   ├── EmprestimoService.java
│   └── ReservaService.java
├── controller/     Camada de controle HTTP
│   ├── HomeController.java
│   ├── LivroController.java
│   ├── UsuarioController.java
│   ├── EmprestimoController.java
│   ├── MultaController.java
│   └── ReservaController.java
├── config/
│   ├── DataInitializer.java
│   ├── WebConfig.java
│   └── GlobalExceptionHandler.java
└── BibliotecaApplication.java

src/main/resources/
├── templates/      Views Thymeleaf
│   ├── index.html
│   ├── livros/
│   ├── usuarios/
│   ├── emprestimos/
│   └── reservas/
├── static/css/
│   └── style.css
└── application.properties
```

`WebConfig` configura os handlers de recursos estáticos (CSS, JS, imagens). `GlobalExceptionHandler` intercepta exceções lançadas pelos services (`IllegalStateException`, `IllegalArgumentException`) e redireciona para a view de erro com a mensagem adequada.

## Funcionalidades

| Módulo      | Operações                                                              |
|-------------|------------------------------------------------------------------------|
| Livros      | Listar, buscar por título/autor/categoria, adicionar, editar, remover  |
| Usuários    | Listar, adicionar por tipo (Aluno, Professor, Funcionário), editar, remover |
| Empréstimos | Registrar empréstimo, devolver livro, listar ativos e histórico        |
| Reservas    | Criar reserva, cancelar, listar ativas                                 |
| Multas      | Geradas automaticamente na devolução com atraso (R$ 2,50/dia)         |

## Regras de negócio

Empréstimo só é permitido se o livro tiver quantidade disponível maior que zero. O prazo padrão de devolução é de 14 dias a partir da data de retirada. Ao devolver com atraso, uma multa é gerada automaticamente com valor de R$ 2,50 por dia de atraso. A quantidade do livro é decrementada ao emprestar e incrementada ao devolver.

Reservas têm validade de 7 dias a partir da data de solicitação e podem ser canceladas manualmente, alterando o status de ATIVA para CANCELADA.

## Como rodar localmente

**1. Clonar o repositório**
```bash
git clone https://github.com/SEU_USUARIO/sistema-biblioteca.git
cd sistema-biblioteca/sistema-biblioteca-luiza/novo-layout/biblioteca
```

**2. Configurar o MongoDB**

O projeto está configurado para usar MongoDB Atlas. Edite o arquivo `src/main/resources/application.properties` com a sua connection string:

```properties
spring.data.mongodb.uri=mongodb+srv://usuario:senha@cluster.mongodb.net/biblioteca
spring.data.mongodb.database=biblioteca
```

Para rodar com MongoDB local, substitua pela URI local:

```properties
spring.data.mongodb.uri=mongodb://localhost:27017/biblioteca
```

**3. Executar**
```bash
mvn spring-boot:run
```

Acesse em: http://localhost:8081

Na primeira execução, o `DataInitializer` insere automaticamente dados de exemplo: 5 livros (Dom Quixote, O Senhor dos Anéis, 1984, Clean Code, O Alquimista) e 4 usuários (2 alunos, 1 professor, 1 funcionário).

## Gerar JAR

```bash
mvn clean package -DskipTests
java -jar target/sistema-biblioteca-1.0.0.jar
```

## Stack tecnológica

| Tecnologia          | Versão | Função                       |
|---------------------|--------|------------------------------|
| Java                | 17     | Linguagem                    |
| Spring Boot         | 3.2.0  | Framework principal          |
| Spring Data MongoDB | 3.2.0  | Acesso ao banco de dados     |
| Thymeleaf           | 3.1    | Template engine              |
| MongoDB             | 6+     | Banco de dados NoSQL         |
| Lombok              | atual  | Redução de boilerplate       |
| Maven               | 3.8+   | Gerenciamento de build       |

## Autor

Desenvolvido como projeto acadêmico por Luiza — Sistemas de Informação.  
Atividade: Passo 4 — Aplicação Web com Banco de Dados.
