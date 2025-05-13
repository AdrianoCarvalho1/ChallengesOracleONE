import java.net.URI;
import java.net.http.*;
import java.util.Scanner;
import org.json.JSONObject;

public class ConversorMoedas {
    public static void main(String[] args) throws Exception {
        // Sua chave da API
        String apiKey = "b414ef87db4f57305685b85b";
        String baseUrl = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/";

        // Scanner para entrada do usuário
        Scanner scanner = new Scanner(System.in);

        // Menu de opções
        System.out.println("Conversor de Moedas");
        System.out.println("1. USD para BRL");
        System.out.println("2. BRL para USD");
        System.out.println("3. USD para EUR");
        System.out.println("4. EUR para USD");
        System.out.println("5. BRL para EUR");
        System.out.println("6. EUR para BRL");
        System.out.print("Escolha uma opção (1-6): ");
        int opcao = scanner.nextInt();

        // Determinando as moedas de base e alvo com base na escolha
        String base = "USD";
        String target = "BRL";

        switch (opcao) {
            case 1:
                base = "USD";
                target = "BRL";
                break;
            case 2:
                base = "BRL";
                target = "USD";
                break;
            case 3:
                base = "USD";
                target = "EUR";
                break;
            case 4:
                base = "EUR";
                target = "USD";
                break;
            case 5:
                base = "BRL";
                target = "EUR";
                break;
            case 6:
                base = "EUR";
                target = "BRL";
                break;
            default:
                System.out.println("Opção inválida.");
                return;
        }

        // Fazendo a requisição à API
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + base))
                .build();

        // Enviando a requisição e recebendo a resposta
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Analisando a resposta JSON
        JSONObject json = new JSONObject(response.body());
        double taxa = json.getJSONObject("conversion_rates").getDouble(target);

        // Exibindo o resultado da conversão
        System.out.print("Digite o valor a converter: ");
        double valor = scanner.nextDouble();
        double resultado = valor * taxa;

        System.out.printf("Resultado: %.2f %s%n", resultado, target);
    }
}
