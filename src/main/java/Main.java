import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import org.json.JSONObject;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o nome da cidade: ");
        String cidade = sc.nextLine();

        try {
            String dadosClimaticos = getDadosClimaticos(cidade); //retorna um JSON

            // Codigo 1006 significa localizacao nao encontrada
            if (dadosClimaticos.contains("\"code\":1006")) { //"\"code\":1006" representa "code": 1006
                System.out.println("Localizacao nao encontrada. Por favor, tente novamente.");
            } else {
                imprimirDadosClimaticos(dadosClimaticos);
            }
        } catch (Exception e) {
            System.out.println("Ocorreu um erro: " + e.getMessage());
            e.printStackTrace();
        } finally {
            sc.close();
        }
    }

    public static String getDadosClimaticos(String cidade) throws Exception {
        String apiKey = System.getenv("WEATHER_API_KEY");

        if (apiKey == null || apiKey.trim().isEmpty()) {
            throw new Exception("Variável de ambiente 'WEATHER_API_KEY' não foi configurada.\n" +
                    "Configure-a no seu sistema ou na sua IDE (Run Configuration).");
        }

        String formataNomeCidade = URLEncoder.encode(cidade, StandardCharsets.UTF_8);
        String apiUrl = "http://api.weatherapi.com/v1/current.json?key=" + apiKey + "&q=" + formataNomeCidade;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .build();
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

    //Metodo para imprimir os dados metereologicos de forma organizada
    public static void imprimirDadosClimaticos(String dados) {
        //System.out.println("Dados originais (JSON) obtidos no site metereologico"+dados);

        JSONObject dadosJson = new JSONObject(dados);

        //Extrai os dados da localizacao
        String cidade = dadosJson.getJSONObject("location").getString("name");
        String pais = dadosJson.getJSONObject("location").getString("country");

        // Pega o objeto "current" de "dadosJson"
        JSONObject informacoesMetereologicas = dadosJson.getJSONObject("current");

        //Extrai os dados adicionais
        String condicaoTempo = informacoesMetereologicas.getJSONObject("condition").getString("text");
        int umidade = informacoesMetereologicas.getInt("humidity");
        double velocidadeVento = informacoesMetereologicas.getDouble("wind_kph");
        double pressaoAtmosferica = informacoesMetereologicas.getDouble("pressure_mb");
        double sensacaoTermica = informacoesMetereologicas.getDouble("feelslike_c");
        double temperaturaAtual = informacoesMetereologicas.getDouble("temp_c");

        //Extrai a data e hora da string retornada pela API
        String dataHoraString = informacoesMetereologicas.getString("last_updated");

        //Imprime as informacoes atuais
        System.out.println("-------------------------------------");
        System.out.println("Informacoes Metereologicas para " + cidade + ", " + pais);
        System.out.println("Data e hora: " + dataHoraString);
        System.out.println("Temperatura atual: " + temperaturaAtual + "C");
        System.out.println("Sensacao Termica: " + sensacaoTermica + "C");
        System.out.println("Condicao do tempo: " + condicaoTempo);
        System.out.println("Umidade: " + umidade + "%");
        System.out.println("Velocidade do Vento: " + velocidadeVento + " km/h");
        System.out.println("Pressao Atmosferica: " + pressaoAtmosferica + " mb"); // Unidade é mb
        System.out.println("-------------------------------------");
    }
}
