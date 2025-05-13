package util;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiClient {
    private final Gson gson = new Gson();

    public double buscarTaxa(String from, String to) {
        String endpoint = "https://api.frankfurter.app/latest?from=" + from + "&to=" + to;

        try {
            URL url = new URL(endpoint);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("GET");

            if (conexao.getResponseCode() == 200) {
                InputStreamReader reader = new InputStreamReader(conexao.getInputStream());
                JsonObject json = gson.fromJson(reader, JsonObject.class);
                return json.getAsJsonObject("rates").get(to).getAsDouble();
            } else {
                System.out.println("Erro ao acessar a API: " + conexao.getResponseCode());
                return -1;
            }

        } catch (Exception e) {
            System.out.println("Erro na requisição: " + e.getMessage());
            return -1;
        }
    }
}
