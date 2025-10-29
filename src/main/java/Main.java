import com.fasterxml.jackson.databind.util.JSONPObject;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o nome da cidade: ");
        String cidade = sc.nextLine();

        try{
            String dadosClimaticos = getDadosClimaticos(cidade); //retorna um JSON

            // Codigo 1006 significa localizacao nao encontrada
            if(dadosClimaticos.contains("\"code\":1006")){ //"\"code\":1006" representa "code": 1006
                System.out.println("Localizacao nao encontrada .Por favor, tente novamente.");

            }else
                imprimirDadosClimaticos(dadosClimaticos);
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static String getDadosClimaticos(String cidade) throws Exception{
        String apiKey = Files.readString(Paths.get("/resources/api-Key.txt")).trim();

        String formataNomeCidade = URLEncoder.encode(cidade, "UTF-8");
        String apiUrl = "http://api.weatherapi.com/v1/current.json?key="+ apiKey+"&q="+formataNomeCidade;
        HttpRequest request = HttpRequest.newBuilder() // Comeca a construcao de uma nova solicitacao HTTP
                .uri(URI.create(apiUrl))
                .build(); //Finaliza a construcao da solicitacao HTTP.
        //Criar objeto enviar solicitacoes HTTP e receber respostas HTTP, para acessar o site da WeatherAPI
        HttpClient client = HttpClient.newHttpClient();

        //Agora vamos enviar requisicoes HTTP e recebber respostas HTTP, comunicar com o site da API Metereologica
        HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());
        return response.body(); // retorna os dados metereologicos obtidos no site API (WeatherAPI)
    }

    //Metodo para imprimir os dados metereologicos de forma organizada
    public static void imprimirDadosClimaticos(String dados){
        //System.out.println("Dados originais (JSON) obtidos no site metereologico"+dados);

        JSONPObject dadosJson = new JSONPObject(dados);
        JSONPObject informacoesMetereologicas = dadosJson.getJSONObject("current");

        //Extrai os dados da localizacao
        String cidade = dadosJson.getJSONObject("location").getString("name");
        String pais = dadosJson.getJSONObject("location").getString("country");

        //Extrai os dados adicionais
        String condicaoTempo = informacoesMetereologicas.getJSONObject("condition").getString("text");
        int umidade = informacoesMetereologicas.getInt("humidity");
        float velocidadeVento = informacoesMetereologicas.getFloat("wind_kph");
        float pressaoAtmosferica = informacoesMetereologicas.getFloat("pressure_mb");
        float sensacaoTermica = informacoesMetereologicas.getFloat("feelslike_c");
        float temperaturaAtual = informacoesMetereologicas.getFloat("temp_c");

        //Extrai a data e hora da string retornada pela API
        String dataHoraString = informacoesMetereologicas.getString("last_update");

        //Imprime as informacoes atuais
        System.out.println("Informacoes Metereologicas para "+ cidade + ", "+ pais);
        System.out.println("Data e hora : " + dataHoraString);
        System.out.println("Temperatura atual : " + temperaturaAtual+"C");
        System.out.println("Sensacao Termica : " + sensacaoTermica+"C");
        System.out.println("Condicao do tempo: " + condicaoTempo);
        System.out.println("Umidade: "+umidade+"%");
        System.out.println("Velocidade do Vento: " + velocidadeVento+" km/h");
        System.out.println("Pressao Atmosferica: " + pressaoAtmosferica);

    }
}
