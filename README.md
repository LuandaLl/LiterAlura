# 📚 LiterAlura - Catálogo de Livros

A aplicação permite buscar livros, salvar no banco de dados, listar autores e filtrar obras por idioma, tudo através de uma interface interativa via console.

---

## 🚀 Funcionalidades

* **Busca de livros por título:** Consulta a API Gutendex e registra o livro e seu autor no banco de dados.
* **Listagem de livros:** Exibe todos os livros salvos localmente.
* **Listagem de autores:** Mostra todos os autores registrados e suas respectivas obras.
* **Filtro de autores vivos:** Busca autores que estavam vivos em um determinado ano informado pelo usuário.
* **Filtro por idioma:** Lista livros de acordo com a sigla do idioma (ex: `en`, `pt`, `es`, `fr`).
* **Estatísticas:** Exibe a contagem de livros por idioma registrado.

---

## 🛠️ Tecnologias Utilizadas

* **Java 17+**
* **Spring Boot 3.2.3**
* **Spring Data JPA**
* **PostgreSQL** (Banco de dados relacional)
* **Jackson** (Manipulação de JSON)
* **HttpClient** (Consumo de API externa)

---

## 📋 Pré-requisitos e Configuração

### 1. Banco de Dados
Certifique-se de ter o PostgreSQL instalado e crie um banco de dados chamado `literalura_db`.

### 2. Variáveis de Ambiente
Para segurança, o projeto utiliza um arquivo `.env` na raiz para gerenciar as credenciais. Crie o arquivo e adicione:

```env
DB_NAME=literalura_db
DB_USER=seu_usuario
DB_PASSWORD=sua_senha
