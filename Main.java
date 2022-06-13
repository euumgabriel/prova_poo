class Main {
    public static String ocorrenciasFilePath = "data/ocorrencias.csv", usuariosFilePath = "data/usuarios.csv";

    public static void main(String[] args) {
        System.out.println(new Ocorrencia("Teste", "Testando", new Usuario("n", "n", "n")));
    }
}