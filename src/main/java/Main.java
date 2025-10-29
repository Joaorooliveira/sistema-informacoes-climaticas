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
}
