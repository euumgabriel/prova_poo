import java.io.IOException;
import java.util.Scanner;

class Usuario {
    private String nome, email, senha;
    private int id;

    public Usuario(String nome, String email, String senha) {
        setNome(nome);
        setEmail(email);
        setSenha(senha);
        setId();
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setId() {
        try {
            String[] usuariosStr = ManipuladorArquivo.ler(Main.usuariosFilePath);
            String ultimoUsuario = usuariosStr[usuariosStr.length - 1];
            int idUltimoUsuario = Integer.parseInt(ultimoUsuario.split(";")[0]);
            this.id = idUltimoUsuario + 1;
        } catch (IOException erro) {
            System.out.println("Erro ao trabalhar com arquivo!");
        }
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Nome: " + this.nome + "\nEmail: " + this.email;
    }

    public void cadastrarOcorrencia(Scanner scannerStr) {
        String titulo = InputUsuario.inputString("Título: ", scannerStr);
        String descricao = InputUsuario.inputString("Descrição: ", scannerStr);

        new Ocorrencia(titulo, descricao, this).salvar();

        System.out.println("Ocorrência cadastrada com sucesso!\n");
    }
}