import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        int opcao;
        Scanner leitura = new Scanner(System.in);

        do {
            System.out.println("\n====== CONVERSOR DE MOEDAS ======");
            System.out.println("Moedas disponíveis: ARS | BOB | BRL | CLP | COP | USD");
            System.out.println("1 - Converter moeda");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Digite o código da moeda de ORIGEM: ");
                    String moedaOrigem = leitura.nextLine().toUpperCase();

                    System.out.print("Digite o código da moeda de DESTINO: ");
                    String destino = leitura.nextLine().toUpperCase();

                    System.out.print("Digite o valor a ser convertido: ");
                    double valor = leitura.nextDouble();

                    try {
                        double resultado = ApiClient.converterMoeda(moedaOrigem, destino, valor);
                        System.out.printf("Valor convertido: %.2f %s%n", resultado, destino);
                    } catch (Exception e) {
                        System.out.println("Erro ao converter moeda: " + e.getMessage());
                    }
                    break;

                case 0:
                    System.out.println("Saindo do programa...");
                    break;

                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        } while (opcao != 0);
    }
}




