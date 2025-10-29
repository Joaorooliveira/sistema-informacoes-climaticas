# 🌍 Sistema de Informações Climáticas

Um projeto de console em Java 21 que consulta e exibe informações meteorológicas em tempo real de uma cidade fornecida pelo usuário.

Este projeto demonstra o consumo de APIs REST em Java, o parsing de JSON e o gerenciamento seguro de chaves de API através de variáveis de ambiente, utilizando a [WeatherAPI.com](httpss://www.weatherapi.com/).

---
<img width="453" height="235" alt="brasilia" src="https://github.com/user-attachments/assets/61cd141b-476a-489b-ab98-defbb82bf7c9" />


## 🚀 Funcionalidades

* 📍 **Consulta por Cidade:** Permite ao usuário digitar o nome de qualquer cidade.
* 🌡️ **Dados Detalhados:** Exibe informações como temperatura, sensação térmica, umidade, vento, etc.
* 🔒 **Segurança:** A chave de API não é exposta no código. O projeto lê a chave de uma variável de ambiente (`WEATHER_API_KEY`).
* ⚠️ **Tratamento de Erros:** Informa ao usuário caso a localização digitada não seja encontrada.

## 💻 Tecnologias Utilizadas

* **Java 21:** Versão mais recente da linguagem.
* **Maven:** Gerenciamento de dependências do projeto.
* **Java HttpClient:** Cliente nativo do Java (desde a versão 11) para fazer as requisições HTTP.
* **org.json:** Biblioteca simples e leve para manipulação de dados JSON.

---

## 🔑 Configuração Obrigatória (API Key)

Para que este projeto funcione, você **precisa** de uma chave de API do WeatherAPI.

1.  **Obtenha sua Chave:**
    * Acesse [https://www.weatherapi.com/](httpss://www.weatherapi.com/) e crie uma conta gratuita.
    * No seu painel (dashboard), copie sua chave de API (API Key).

2.  **Configure a Variável de Ambiente:**
    * O projeto lê a chave da variável de ambiente `WEATHER_API_KEY`.
    * A forma mais fácil de configurar é na sua IDE.

    **No IntelliJ IDEA (Recomendado):**
    1.  Vá em **Run** → **Edit Configurations...** (onde você criou a configuração "Main").
    2.  Encontre o campo **"Environment variables"**.
    3.  Clique no ícone 📑 ao lado e adicione (clicando no **+**):
        * **Name:** `WEATHER_API_KEY`
        * **Value:** `SUA_CHAVE_DE_API_COPIADA_AQUI`
    4.  Clique em "OK" e "Apply".

---

## ▶️ Como Executar

Após clonar o repositório e configurar a API Key, você pode rodar o projeto.

### 1. Pela sua IDE (IntelliJ)
* Abra o projeto e espere o Maven carregar as dependências.
* Clique no botão "Run" (▶️) ao lado da sua configuração "Main".

### 2. Pelo Terminal (usando Maven)

```bash
# Compila o projeto
mvn compile

# Executa a classe Main
mvn exec:java -Dexec.mainClass="Main"
