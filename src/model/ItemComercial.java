package model;

abstract class ItemComercial {

    private int id;
    private String nome;
    private String descricao;
    private float preco;

    ItemComercial(int id, String nome, String desc, float preco) {
        this.id = id;
        this.nome = nome;
        this.descricao = desc;
        this.preco = preco;
    }

    public String getNome() {
        return this.nome;
    }

    public int getId() {
        return this.id;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public String getPrecoFormatado() {
        return String.format("%.2f", this.preco);
    }

    public float getPreco() {
        return this.preco;
    }

    public void setPreco(float p){
        if(p>0) this.preco=p;
    }

    public int getID(){
        return id;
    }

    public void setNome(String nome){
        if(nome.length()<=1) System.err.println("Nome inválido\n");
        this.nome=nome;
    } 

    public void setDesc(String desc){
        if(desc.length()<=10) System.err.println("Desc. inválida. Insira mais informações\n");
        this.descricao=desc;
    }

    public void setID(int id){
        if(id<=0) System.err.println("Informe um número válido de identificador.");
        this.id=id;
    }

    public abstract boolean aplicarDesconto(Double v);

    public abstract void imprimirDados();
}
