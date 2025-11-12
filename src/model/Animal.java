package model;

import java.util.ArrayList;
import java.util.Date;

public class Animal {

    static public int animalCount = 0;

    private final int id;

    private Cliente cliente;

    private String nome;
    private Especie especie;
    private String raca;
    private Date dataNascimento;
    private Float peso;
    private String observacao;

    private ArrayList<Servico> listaDeServicos = new ArrayList<Servico>();
    private ArrayList<Consulta> listaDeConsultas = new ArrayList<Consulta>();

    public Animal(String nome, Especie especie, String raca, Date dataNascimento, Cliente cliente) {
        this.nome = nome;
        this.especie = especie;
        this.raca = raca;
        this.dataNascimento = dataNascimento;
        this.id = animalCount++;
        this.cliente = cliente;
    }

    void registrarPeso(Float peso) {
        this.peso = peso;
    }

    void registrarObservacao(String observacao) {
        this.observacao = observacao;
    }

    void atualizarDados(String nome, Especie especie, String raca, Date dataNascimento) {
        this.nome = nome;
        this.especie = especie;
        this.raca = raca;
        this.dataNascimento = dataNascimento;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Especie getEspecie() {
        return especie;
    }

    public String getRaca() {
        return raca;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    void exibirAnimal() {

        final String BLUE = "\u001B[34m";
        final String RESET = "\u001B[0m";
        System.out.println(BLUE + "[ Id: " + this.getId() + " | Nome: " + nome + " | Especie: " + especie + " | Raça: " + raca + " | Data de Nascimento: " + dataNascimento + " | Peso: " + peso + " | Observação: " + observacao + " ]" + RESET);
    }

    // Servico methods
    void adicionarServico(Servico servico) {
        this.listaDeServicos.add(servico);
    }

    void removerServico(Servico servico) {
        this.listaDeServicos.remove(servico);
    }

    void exibirServicos() {
        System.out.println("Serviços associados ao animal " + this.nome + ":");
        for (Servico servico : listaDeServicos) {
            System.out.println("Serviço ID: 123");
        }
    }

    // Consulta methods
    void adicionarConsulta(Consulta consulta) {
        this.listaDeConsultas.add(consulta);
    }

    void removerConsulta(Consulta consulta) {
        this.listaDeConsultas.remove(consulta);
    }

    void exibirConsultas() {
        System.out.println("Consultas associadas ao animal " + this.nome + ":");
        for (Consulta consulta : listaDeConsultas) {
            System.out.println("Consulta ID: 123");
        }
    }
}
