Sistema de Informações Climáticas

Um projeto de console em Java 21 que consulta e exibe informações meteorológicas em tempo real de uma cidade fornecida pelo usuário, utilizando a API do WeatherAPI.com.

Este projeto demonstra o consumo de APIs REST em Java, o parsing de JSON e o gerenciamento seguro de chaves de API através de variáveis de ambiente.

Funcionalidades

Consulta por Cidade: Permite ao usuário digitar o nome de qualquer cidade.

Dados Detalhados: Exibe informações como temperatura atual, sensação térmica, umidade, velocidade do vento e condição do tempo.

Segurança: A chave de API não é armazenada no código-fonte. O projeto é configurado para ler a chave de uma variável de ambiente (WEATHER_API_KEY), protegendo suas credenciais.

Tratamento de Erros: Verifica se a localização foi encontrada e informa ao usuário caso a cidade não exista.

Tecnologias Utilizadas

Java 21

Maven (para gerenciamento de dependências)

Java 11+ HttpClient (para fazer as requisições HTTP)

org.json (para fazer o parsing da resposta JSON da API)

Configuração Obrigatória (API Key)

Para que este projeto funcione, você precisa de uma chave de API do WeatherAPI.

Obtenha sua Chave:

Acesse https://www.weatherapi.com/ e crie uma conta gratuita.

Acesse seu painel (dashboard) e copie sua chave de API (API Key).

Configure a Variável de Ambiente:

Este projeto não armazena a chave no código. Ele a lê da variável de ambiente chamada WEATHER_API_KEY.

A forma mais fácil de configurar isso para desenvolvimento é na sua IDE.

No IntelliJ IDEA (Recomendado):

No canto superior direito, clique na configuração de execução (provavelmente "Main") e escolha "Edit Configurations...".

Na janela que abrir, encontre o campo "Environment variables" (Variáveis de Ambiente).

Clique no ícone 📑 ao lado e adicione (clicando no +):

Name: WEATHER_API_KEY

Value: SUA_CHAVE_DE_API_COPIADA_AQUI

Clique em "OK" e "Apply".

Como Executar

Após clonar o repositório e configurar a variável de ambiente (passo anterior), você pode executar o projeto de duas formas:

1. Pela sua IDE (IntelliJ)

Abra o projeto.

Certifique-se de que o Maven importou as dependências (se ele perguntar, clique em "Load Maven Project").

Clique no botão "Run" (▶️) ao lado da sua configuração "Main".

2. Pelo Terminal (usando Maven)

Você pode compilar e executar o projeto diretamente com o Maven:

# Compila o projeto
mvn compile

# Executa a classe Main
mvn exec:java -Dexec.mainClass="Main"


Exemplo de Saída

Digite o nome da cidade: Londres
-------------------------------------
Informacoes Metereologicas para Londres, Reino Unido
Data e hora: 2025-10-29 19:30
Temperatura atual: 12.0C
Sensacao Termica: 10.5C
Condicao do tempo: Parcialmente nublado
Umidade: 81%
Velocidade do Vento: 15.0 km/h
Pressao Atmosferica: 1009.0 mb
-------------------------------------
