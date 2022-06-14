import java.io.IOException;

class Ocorrencia {
    private String titulo, descricao;
    private int id;
    Usuario criador;

    public Ocorrencia(String titulo, String descricao, Usuario criador) {
        setTitulo(titulo);
        setDescricao(descricao);
        setId();
        setCriador(criador);
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setId() {
        try {
            String[] ocorrenciasStr = ManipuladorArquivo.ler(Main.ocorrenciasFilePath);

            if (ocorrenciasStr.length > 0) {
                String ultimaOcorrencia = ocorrenciasStr[ocorrenciasStr.length - 1];
                int idUltimaOcorrencia = Integer.parseInt(ultimaOcorrencia.split(";")[0]);
                this.id = idUltimaOcorrencia + 1;
            } else {
                this.id = 1;
            }
        } catch (IOException erro) {
            System.out.println("Erro ao trabalhar com arquivo!\n");
        }
    }

    public void setCriador(Usuario criador) {
        this.criador = criador;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getId() {
        return id;
    }

    public Usuario getCriador() {
        return criador;
    }

    @Override
    public String toString() {
        return "Título: " + this.titulo + "\nDescrição: " + this.descricao + "\nCriador: " + this.criador.getNome();
    }

    public void salvar() {
        String ocorrenciaCSV = this.id + ";" + this.titulo + ";" + this.descricao + ";" + this.criador.getId();

        try {
            ManipuladorArquivo.escrever(Main.ocorrenciasFilePath, ocorrenciaCSV);
        } catch (IOException erro) {
            System.out.println("Erro ao trabalhar com arquivo!\n");
        }
    }
}