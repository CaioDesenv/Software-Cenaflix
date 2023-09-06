
package data;

public class Cadastro {
    
    public Cadastro(){
        
    }
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDatalancamento() {
        return datalancamento;
    }

    public void setDatalancamento(String datalancamento) {
        this.datalancamento = datalancamento;
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getData() {
        return datalancamento;
    }

    public void setData(String datalancamento) {
        this.datalancamento = datalancamento;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    private String nome;

    public void Cadastro (String nome) {
        this.nome = nome;
    }
    private String datalancamento;

    public void Data (String datalancamento) {
        this.datalancamento = datalancamento;
    }
    private String categoria;

    public void Categoria (String categoria) {
        this.categoria = categoria;
    }
    
}
