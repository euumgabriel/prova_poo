import java.io.IOException;
import java.util.Scanner;

class Administrador extends Usuario {
    public Administrador(String nome, String email, String senha) {
        super(nome, email, senha);
    }

    public void verOcorrencias() {
        try {
            String[] ocorrenciasCSV = ManipuladorArquivo.ler(Main.ocorrenciasFilePath);
            String[] usuariosCSV = ManipuladorArquivo.ler(Main.usuariosFilePath);

            if (ocorrenciasCSV.length > 0) {
                System.out.println("Ocorrências cadastradas:\n");

                for (int i = 0; i < ocorrenciasCSV.length; i++) {
                    String[] ocorrenciaCSV = ocorrenciasCSV[i].split(";");
                    Usuario criador = null;

                    if (Integer.parseInt(ocorrenciaCSV[3]) == this.getId()) {
                        criador = this;
                    } else {
                        for (int j = 0; j < usuariosCSV.length; j++) {
                            String[] usuarioCSV = usuariosCSV[j].split(";");

                            if ((ocorrenciaCSV[3]).equals(usuarioCSV[0])) {
                                criador = new Usuario(usuarioCSV[1], usuarioCSV[2], usuarioCSV[3]);
                            }
                        }
                    }

                    System.out.println(new Ocorrencia(ocorrenciaCSV[1], ocorrenciaCSV[2], criador) + "\n");
                }
            } else {
                System.out.println("Não há ocorrências cadastradas!\n");
            }
        } catch (IOException erro) {
            System.out.println("Erro ao trabalhar com arquivo!");
        }
    }

    public void verUsuarios() {
        try {
            String[] usuariosCSV = ManipuladorArquivo.ler(Main.usuariosFilePath);

            if (usuariosCSV.length > 1) {
                System.out.println("Agentes cadastrados: \n");
                for (int i = 1; i < usuariosCSV.length; i++) {
                    String[] usuarioCSV = usuariosCSV[i].split(";");
                    System.out.println(new Usuario(usuarioCSV[1], usuarioCSV[2], usuarioCSV[3]) + "\n");
                }
            } else {
                System.out.println("Você ainda não cadastrou nenhum agente!\n");
            }

        } catch (IOException erro) {
            System.out.println("Erro ao trabalhar com arquivo!");
        }
    }

    public void cadastrarUsuario(Scanner scannerStr) {
        String nome = InputUsuario.inputString("Nome: ", scannerStr);
        String email = InputUsuario.inputString("Email: ", scannerStr);
        String senha = InputUsuario.inputString("Senha: ", scannerStr);

        Agente agente = new Agente(nome, email, senha);
        this.salvarNovoAgente(agente);

        System.out.println("Agente cadastrado com sucesso!\n");
    }

    private void salvarNovoAgente(Agente agente) {
        String agenteCSV = agente.getId() + ";" + agente.getNome() + ";" + agente.getEmail() + ";" + agente.getSenha();

        try {
            ManipuladorArquivo.escrever(Main.usuariosFilePath, agenteCSV);
        } catch (IOException erro) {
            System.out.println("Erro ao trabalhar com arquivo!");
        }
    }
}