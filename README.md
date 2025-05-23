# Projeto A3 - Gestao e Qualidade de Software

## 📋 Descrição
Este projeto foi desenvolvido como parte da UC de **Gestão e Qualidade de Software**, com o objetivo de aplicar os princípios do Clean Code por meio da refatoração de um código-fonte legado. O foco foi melhorar a legibilidade, manutenibilidade e eficiência do código sem alterar sua funcionalidade.

O projeto possui duas versões:
- **Projeto Java (Antigo)**: Implementação inicial utilizando a linguagem Java e banco de dados MySQL.
- **Projeto Python (Refatorizado)**: Versão aprimorada, utilizando Python com Flask, SQLite, HTML e CSS, aplicando princípios de desenvolvimento ágil, melhores práticas de codificação, testes e maior manutenibilidade.



## 🚀 Funcionalidades
Sistema de gerenciamento de biblioteca, com as seguintes funcionalidades principais:
- 📚 Cadastro de livros.
- 👥 Cadastro de usuários.
- 🔍 Consulta e pesquisa de livros disponíveis.
- 📖 Empréstimo de livros para usuários.
- 🔄 Devolução de livros.
- 📅 Gerenciamento de prazos de empréstimos.



## 🔧 Tecnologias Utilizadas

### 🧠 Projeto Java (Antigo):
- ☕ **Java**
- 🗄️ **MySQL** (banco de dados relacional)
- 🗂️ **JDBC** (conexão com banco)
- 🧰 **IDE:** Eclipse

### 🚀 Projeto Python (Refatorado):
- 🐍 **Python**
- 🌐 **Flask** (framework web leve)
- 🗄️ **SQLite** (banco de dados embarcado)
- 🎨 **HTML** e **CSS** (interface web)
- 🧰 **IDE:** VSCode



## 🏗️ Como Executar

### ✔️ Projeto em Java:
1. Navegue até a pasta `Projeto Java (Antigo)`.
2. Importe o projeto em uma IDE Java.
3. Configure a conexão com o banco MySQL:
   - Crie o banco e as tabelas conforme o script SQL.
4. Baixe o MySQL Connector/J (driver JDBC):
   - Link para download: https://dev.mysql.com/downloads/connector/j/
   - Após o download, adicione o arquivo `.jar` do connector nas dependências do projeto (Build Path da sua IDE).
5. Compile e execute a classe principal.


### ✔️ Projeto em Python (Refatorado):
1. Navegue até a pasta `Projeto Python (Refatorizado)`.
2. Crie e ative um ambiente virtual:
```bash
python -m venv venv
```
```bash
source venv/bin/activate  # Linux/macOS
venv\Scripts\activate     # Windows
```
