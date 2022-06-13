import java.io.IOException;
import java.util.Scanner;

class Main {
    public static String ocorrenciasFilePath = "data/ocorrencias.csv", usuariosFilePath = "data/usuarios.csv";

    public static void main(String[] args) {
        Scanner scannerStr = new Scanner(System.in), scanner = new Scanner(System.in);
        Usuario usuarioLogado = null;
        boolean continuar = true;

        while (continuar) {
            System.out.println("\nFazer login\n");

            do {
                usuarioLogado = fazerLogin(scannerStr);
            } while (usuarioLogado == null);

            System.out.println("\nOlá, " + usuarioLogado.getNome() + "!\n");

            if (usuarioLogado instanceof Administrador) {
                menuAdmin(usuarioLogado, scanner, scannerStr);
            } else {
                menuAgente(usuarioLogado, scanner, scannerStr);
            }

            continuar = InputUsuario.inputString("Fazer login novamente? s/n: ", scannerStr).toLowerCase()
                    .charAt(0) == 's';
        }

        scanner.close();
        scannerStr.close();
    }

    public static Usuario fazerLogin(Scanner scannerStr) {
        Usuario usuario = null;

        String email = InputUsuario.inputString("Email: ", scannerStr);
        String senha = InputUsuario.inputString("Senha: ", scannerStr);

        try {
            String[] usuariosCSV = ManipuladorArquivo.ler(usuariosFilePath);

            for (int i = 0; i < usuariosCSV.length; i++) {
                String[] usuarioCSV = usuariosCSV[i].split(";");

                if (email.equals(usuarioCSV[2]) && senha.equals(usuarioCSV[3])) {
                    int id = Integer.parseInt(usuarioCSV[0]);

                    if (id == 1) { // Usuário é admin.
                        usuario = new Administrador(usuarioCSV[1], usuarioCSV[2], usuarioCSV[3]);
                        usuario.setId(1);
                    } else {
                        usuario = new Agente(usuarioCSV[1], usuarioCSV[2], usuarioCSV[3]);
                        usuario.setId(id);
                    }
                }
            }
        } catch (IOException erro) {
            System.out.println("Erro ao trabalhar com arquivo!");
        }

        if (usuario == null) {
            System.out.println("Email ou senha incorretos!\n");
        }

        return usuario;
    }

    public static void menuAgente(Usuario usuario, Scanner scanner, Scanner scannerStr) {
        Agente agente = (Agente) usuario;
        String[] opcoes = { "Ver ocorrências cadastradas", "Cadastrar nova ocorrência", "Sair" };

        while (true) {
            switch (InputUsuario.selecionaOpcao(opcoes, "O que você deseja fazer?\n\n", scanner)) {
                case "Ver ocorrências cadastradas":
                    agente.verOcorrencias();
                    break;
                case "Cadastrar nova ocorrência":
                    agente.cadastrarOcorrencia(scannerStr);
                    break;
                case "Sair":
                    return;
            }
        }
    }

    public static void menuAdmin(Usuario usuario, Scanner scanner, Scanner scannerStr) {
        Administrador admin = (Administrador) usuario;
        String[] opcoes = { "Ver ocorrências cadastradas", "Cadastrar nova ocorrência", "Ver agentes cadastrados",
                "Cadastrar novo agente", "Sair" };

        while (true) {
            switch (InputUsuario.selecionaOpcao(opcoes, "O que você deseja fazer?\n\n", scanner)) {
                case "Ver ocorrências cadastradas":
                    admin.verOcorrencias();
                    break;
                case "Cadastrar nova ocorrência":
                    admin.cadastrarOcorrencia(scannerStr);
                    break;
                case "Ver agentes cadastrados":
                    admin.verUsuarios();
                    break;
                case "Cadastrar novo agente":
                    admin.cadastrarUsuario(scannerStr);
                    break;
                case "Sair":
                    return;
            }
        }
    }
}