package aplicativos;

import java.io.IOException;
import java.util.Scanner;

public class AppPilotos {
    public static void main(String[] args) throws InterruptedException, IOException {
        int MAX_ELEMENTOS = 20;
        int opcao, qtdCadastrados = 0;
        Pessoa[] pilotos = new Pessoa[MAX_ELEMENTOS];
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("\n****\nMENU\n****\n");
            System.out.println("1 - Cadastrar piloto");
            System.out.println("2 - Listar pilotos cadastrados");
            System.out.println("3 - Localizar piloto pelo CPF");
            System.out.println("4 - Aumentar espaço de armazenamento");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine(); // Tira o ENTER que ficou na entrada na instrução anterior

            if (opcao == 1) {
                // Se não tem mais espaço no vetor, caio fora
                if (qtdCadastrados == MAX_ELEMENTOS) {
                    System.out.println("\nNão há espaço para cadastrar novos pilotos.");
                    voltarMenu(scanner);
                    continue;
                }

                // Cadastre seu piloto aqui
                Pessoa piloto = new Pessoa();

                System.out.print("Informe o nome do piloto: ");
                piloto.setNome(scanner.nextLine());

                System.out.print("Informe o CPF do piloto: ");

                piloto.setCpf(scanner.nextLine());

                pilotos[qtdCadastrados] = piloto;
                qtdCadastrados++;

                System.out.println("\nPiloto cadastrado com sucesso.");
                voltarMenu(scanner);

            } else if (opcao == 2) {
                // Se não tem ninguém cadastrado no vetor, caio fora
                if (qtdCadastrados == 0) {
                    System.out.println("\nNão há pilotos cadastrados para exibir.");
                    voltarMenu(scanner);
                    continue;
                }

                // Exiba os pilotos aqui
                for (int i = 0; i < qtdCadastrados; i++) {
                    System.out.println("=======Piloto " + (i + 1) + "=======");
                    System.out.println("Nome: " + pilotos[i].getNome() + "\nCPF: "
                            + pilotos[i].getCpf());
                    System.out.println("===================");

                }
                voltarMenu(scanner);
            } else if (opcao == 3) {
                System.out.print("Digite o CPF do piloto: \n");
                String buscaCpf = scanner.nextLine();

                for (int i = 0; i < qtdCadastrados; i++) {
                    if (pilotos[i].getCpf().equals(buscaCpf)) {
                        System.out.println("=======Piloto=======");
                        System.out.println("Nome: " + pilotos[i].getNome());
                        System.out.println("===================");
                    }
                }
                voltarMenu(scanner);
            } else if (opcao == 4) {

                System.out.printf("O tamanho atual é " + MAX_ELEMENTOS + ",informe um novo tamanho : ");
                int novoTamanho = scanner.nextInt();
                scanner.nextLine();

                if (novoTamanho <= MAX_ELEMENTOS) {
                    System.out.println("O novo tamanho deve ser maior que o atual.");
                    voltarMenu(scanner);

                    continue;
                }

                Pessoa[] novoVetor = new Pessoa[novoTamanho];
                for (int i = 0; i < qtdCadastrados; i++) {
                    novoVetor[i] = pilotos[i];
                }

                pilotos = novoVetor;
                MAX_ELEMENTOS = novoTamanho;

                System.out.println("Tamanho do vetor alterado com sucesso !!!");

                voltarMenu(scanner);
            } else if (opcao != 0) {
                System.out.println("\nOpção inválida!");
            }
        } while (opcao != 0);

        System.out.println("Fim do programa!");

        scanner.close();
    }

    private static void voltarMenu(Scanner scanner) throws InterruptedException, IOException {
        System.out.println("\nPressione ENTER para voltar ao menu.");
        scanner.nextLine();

        // Limpa toda a tela, deixando novamente apenas o menu
        if (System.getProperty("os.name").contains("Windows"))
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        else
            System.out.print("\033[H\033[2J");

        System.out.flush();
    }
}