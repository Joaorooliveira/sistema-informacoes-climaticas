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
}
