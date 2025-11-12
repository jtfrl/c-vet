package model;

import exception.DadosObrigatoriosException;

public class Pessoa {

    private String nome;
    private final String cpf;
    private String endereco;
    private String telefone;

    public Pessoa(String nome, String cpf, String endereco, String telefone) throws DadosObrigatoriosException {
        //validação
        if (nome == null || nome.trim().isEmpty()
                || cpf == null || cpf.trim().isEmpty()
                || endereco == null || endereco.trim().isEmpty()
                || telefone == null || telefone.trim().isEmpty()) {

            //lança a exceção se algum dado obrigatório estiver faltando
            throw new DadosObrigatoriosException("Todos os campos (nome, cpf, endereco, telefone) são obrigatórios.");
        }

        //se a validação passar, o código continua
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    //se for null, continua o mesmo, não precisa lançar exceção
    public void atualizarDados(String nome, String endereco, String telefone) {
        if (nome != null || !nome.trim().isEmpty()) {
            this.nome = nome;
        }
        if (endereco != null || !endereco.trim().isEmpty()) {
            this.endereco = endereco;
        }
        if (telefone != null || !telefone.trim().isEmpty()) {
            this.telefone = telefone;
        }

    }

    public void exibirDados() {
        final String BLUE = "\u001B[34m";
        final String RESET = "\u001B[0m";
        System.out.println(BLUE + "[ Nome: " + nome + " | CPF: " + cpf + " | Endereço: " + endereco + " | Telefone: " + telefone + " ]" + RESET);
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEndereco() {
        return endereco;
    }
}
