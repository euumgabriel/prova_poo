import java.util.Scanner;

class InputUsuario {
    static String inputString(String prompt, Scanner scanner) {
        String resposta;

        System.out.print(prompt);
        resposta = scanner.nextLine();

        return resposta;
    }

    static int inputInt(String prompt, Scanner scanner) {
        int resposta;

        System.out.print(prompt);
        resposta = scanner.nextInt();

        return resposta;
    }

    static String selecionaOpcao(String[] opcoes, String prompt, Scanner scanner) {
        int opcaoSelecionada;
        System.out.print(prompt);

        for (int i = 0; i < opcoes.length; i++) {
            System.out.printf("(%d) %s\n", i + 1, opcoes[i]);
        }

        do {
            opcaoSelecionada = inputInt("\nEscolha: ", scanner);
        } while (opcaoSelecionada < 1 || opcaoSelecionada > opcoes.length);

        System.out.println();

        return opcoes[opcaoSelecionada - 1];
    }
}