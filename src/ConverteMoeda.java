import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConverteMoeda {
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/8455c43dea12d9e5c512f71d/latest/";

    public static double obterTaxaConversao(String fromCurrency, String toCurrency) throws IOException, InterruptedException {
        String url = API_URL + fromCurrency;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        try {
            HttpResponse<String> response = HttpClient
                    .newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());
            String json = response.body();
            JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();

            // Verifica se o campo "conversion_rates" está presente
            if (jsonObject.has("conversion_rates")) {
                JsonObject rates = jsonObject.getAsJsonObject("conversion_rates");
                if (rates.has(toCurrency)) {
                    return rates.get(toCurrency).getAsDouble();
                } else {
                    throw new IllegalArgumentException("Taxa de conversão não encontrada para " + toCurrency);
                }
            } else {
                throw new IllegalArgumentException("Resposta da API inválida: campo 'conversion_rates' não encontrado.");
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Erro ao obter a taxa de conversão: " + e.getMessage(), e);
        }
    }
}
