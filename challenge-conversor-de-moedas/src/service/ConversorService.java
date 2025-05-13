package service;

import util.ApiClient;

import java.util.Scanner;

public class ConversorService {
    private final Scanner scanner = new Scanner(System.in);
    private final ApiClient apiClient = new ApiClient();

    public void executar() {
        while (true) {
            System.out.println("\n=== CONVERSOR DE MOEDAS ===");
            System.out.println("1. USD → BRL");
            System.out.println("2. BRL → USD");
            System.out.println("3. EUR → BRL");
            System.out.println("4. BRL → EUR");
            System.out.println("5. USD → EUR");
            System.out.println("6. EUR → USD");
            System.out.println("7. Sair");

            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();

            if (opcao == 7) break;

            System.out.print("Digite o valor a ser convertido: ");
            double valor = scanner.nextDouble();

            String from = "", to = "";
            switch (opcao) {
                case 1 -> { from = "USD"; to = "BRL"; }
                case 2 -> { from = "BRL"; to = "USD"; }
                case 3 -> { from = "EUR"; to = "BRL"; }
                case 4 -> { from = "BRL"; to = "EUR"; }
                case 5 -> { from = "USD"; to = "EUR"; }
                case 6 -> { from = "EUR"; to = "USD"; }
                default -> {
                    System.out.println("Opção inválida.");
                    continue;
                }
            }

            double taxa = apiClient.buscarTaxa(from, to);
            if (taxa != -1) {
                double convertido = valor * taxa;
                System.out.printf("Valor convertido: %.2f %s = %.2f %s%n", valor, from, convertido, to);
            }
        }

        System.out.println("Programa encerrado.");
    }
}
