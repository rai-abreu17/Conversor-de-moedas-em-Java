import java.io.IOException;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner leitura = new Scanner(System.in);
        System.out.println("");
        int menu = 0;
        while (menu != 7) {
            System.out.println("""
                    ******************************
                    Menu do Conversor de Moedas
                    [1] Converter USD para EUR
                    [2] Converter EUR para USD
                    [3] Converter USD para BRL
                    [4] Converter BRL para USD
                    [5] Converter EUR para BRL
                    [6] Converter BRL para EUR
                    [7] Sair
                    ******************************
                    """);
            System.out.println("Digite uma opção: ");
            menu = leitura.nextInt();

            if (menu >= 1 && menu <= 6) {
                System.out.println("Digite o valor a ser convertido: ");
                double valor = leitura.nextDouble();
                String fromCurrency = "";
                String toCurrency = "";

                switch (menu) {
                    case 1:
                        fromCurrency = "USD";
                        toCurrency = "EUR";
                        break;
                    case 2:
                        fromCurrency = "EUR";
                        toCurrency = "USD";
                        break;
                    case 3:
                        fromCurrency = "USD";
                        toCurrency = "BRL";
                        break;
                    case 4:
                        fromCurrency = "BRL";
                        toCurrency = "USD";
                        break;
                    case 5:
                        fromCurrency = "EUR";
                        toCurrency = "BRL";
                        break;
                    case 6:
                        fromCurrency = "BRL";
                        toCurrency = "EUR";
                        break;
                }

                try {
                   
                    double taxaConversao = ConverteMoeda.obterTaxaConversao(fromCurrency, toCurrency);
                   
                    double valorConvertido = valor * taxaConversao;
                    System.out.printf("Valor convertido: %.2f %s%n", valorConvertido, toCurrency);
                } catch (Exception e) {
                    System.out.println("Erro: " + e.getMessage());
                }

            } else if (menu > 7) {
                System.out.println("Opção inválida. Tente novamente.");
            }
        }
        System.out.println("Programa encerrado.");
        leitura.close();
    }
}
