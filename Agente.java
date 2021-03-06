import java.io.IOException;
import java.util.Scanner;

class Agente extends Usuario {
    public Agente(String nome, String email, String senha) {
        super(nome, email, senha);
    }

    public void verOcorrencias() {
        try {
            String[] ocorrenciasCSV = ManipuladorArquivo.ler(Main.ocorrenciasFilePath);
            int quantidadeOcorrenciasAgente = 0;

            if (ocorrenciasCSV.length > 0) {
                System.out.println("Ocorrências cadastradas:\n");

                for (int i = 0; i < ocorrenciasCSV.length; i++) {
                    String[] ocorrenciaCSV = ocorrenciasCSV[i].split(";");

                    if (Integer.parseInt(ocorrenciaCSV[3]) == this.getId()) {
                        System.out.println(new Ocorrencia(ocorrenciaCSV[1], ocorrenciaCSV[2], this) + "\n");
                        quantidadeOcorrenciasAgente++;
                    }
                }

                if (quantidadeOcorrenciasAgente == 0) {
                    System.out.println("Não há ocorrências cadastradas!\n");

                }
            }
        } catch (IOException erro) {
            System.out.println("Erro ao trabalhar com arquivo!\n");
        }
    }

    public void menu(Scanner scanner, Scanner scannerStr) {
        String[] opcoes = { "Ver ocorrências cadastradas", "Cadastrar nova ocorrência", "Sair" };

        while (true) {
            switch (InputUsuario.selecionaOpcao(opcoes, "O que você deseja fazer?\n\n", scanner)) {
                case "Ver ocorrências cadastradas":
                    this.verOcorrencias();
                    break;
                case "Cadastrar nova ocorrência":
                    this.cadastrarOcorrencia(scannerStr);
                    break;
                case "Sair":
                    return;
            }
        }
    }
}