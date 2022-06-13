import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class ManipuladorArquivo {
    public static String[] ler(String path) throws IOException {
        BufferedReader buffRead = new BufferedReader(new FileReader(path));
        String[] linhas;

        buffRead.readLine(); // Pula cabeçalho csv

        linhas = new String[contarLinhas(path)];

        for (int i = 0; i < linhas.length; i++) {
            linhas[i] = buffRead.readLine();
        }

        buffRead.close();

        return linhas;
    }

    public static void escrever(String path, String texto) throws IOException {
        BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path, true));
        buffWrite.append(texto + "\n");
        buffWrite.close();
    }

    private static int contarLinhas(String path) throws IOException {
        BufferedReader buffRead = new BufferedReader(new FileReader(path));
        String linha = "";
        int quantidadeLinhas = 0;

        linha = buffRead.readLine(); // Pula cabeçalho csv

        while (linha != null) {
            quantidadeLinhas++;
            linha = buffRead.readLine();
        }

        buffRead.close();

        return quantidadeLinhas - 1;
    }
}