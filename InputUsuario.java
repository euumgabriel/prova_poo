import java.util.Scanner;

class InputUsuario {
    static String inputString(String prompt) {
        Scanner scanner = new Scanner(System.in);
        String resposta;

        System.out.print(prompt);
        resposta = scanner.nextLine();

        scanner.close();

        return resposta;
    }

    static int inputInt(String prompt) {
        Scanner scanner = new Scanner(System.in);
        int resposta;

        System.out.print(prompt);
        resposta = scanner.nextInt();

        scanner.close();

        return resposta;
    }

    static String selecionaOpcao(String[] opcoes, String prompt) {
        int opcaoSelecionada;
        System.out.print(prompt);

        for (int i = 0; i < opcoes.length; i++) {
            System.out.printf("(%d) %s\n", i + 1, opcoes[i]);
        }

        do {
            opcaoSelecionada = inputInt("Escolha: ");
        } while (opcaoSelecionada < 1 || opcaoSelecionada > opcoes.length);

        return opcoes[opcaoSelecionada - 1];
    }
}