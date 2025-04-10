import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class ApiClient {
    public static double converterMoeda(String base, String moedaDestino, double valorMoeda) throws InterruptedException, IOException {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://v6.exchangerate-api.com/v6/d451956996393aee62bbe2b7/latest/" + base))
                .build();
        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        String json = response.body();

        Gson gson = new Gson();
        ApiResponse meuResponse = gson.fromJson(json, ApiResponse.class);
        Map<String, Double> taxas = meuResponse.getConversion_rates();
        if (taxas == null) {
            throw new IllegalArgumentException("Uma ou ambas as moedas inseridas são inválidas.");
        }

        Double taxaDestino = taxas.get(moedaDestino);
        if (taxaDestino != null) {
            return valorMoeda * taxaDestino;
        } else {
            throw new IllegalArgumentException("Uma ou ambas as moedas inseridas são inválidas.");
        }
    }
}

