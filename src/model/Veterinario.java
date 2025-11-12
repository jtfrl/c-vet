package model;

import exception.DadosObrigatoriosException;
import exception.SalarioInvalidoException;

import java.util.ArrayList;

public class Veterinario extends Pessoa {

    private float salario;
    private final String CRMV;
    private ArrayList<Consulta> listaDeConsultas;

    public Veterinario(String nome, String cpf, String endereco, String telefone, float salario, String CRMV) throws DadosObrigatoriosException {
        super(nome, cpf, endereco, telefone);
        if (CRMV == null) {
            throw new DadosObrigatoriosException("Um veterinário deverá ter pelo menos um animal.");
        }
        if (salario <= 0) {
            throw new SalarioInvalidoException("Um veterinário deverá ter um salário positivo e pelo menos maior que zero.");
        }
        this.salario = salario;
        this.CRMV = CRMV;
        this.listaDeConsultas = new ArrayList<>();
    }

    public void registrarConsulta(Consulta consulta) {
        this.listaDeConsultas.add(consulta);
    }

    public void mostrarConsultas() {
        String RED = "\u001B[31m";
        String RESET = "\u001B[0m";

        System.out.println("-- Consultas");

        if (this.listaDeConsultas.isEmpty()) {
            System.out.println(RED + "-- Nenhuma consulta registrada. " + RESET);
            return;
        }

        for (Consulta consulta : this.listaDeConsultas) {
            System.out.println(RED + "[Consulta ID: " + consulta.getId() + ", Descrição: " + consulta.getDescricao() + ", Data: " + consulta.getData() + ", Animal: " + consulta.getAnimal().getNome() + "| Status: " + consulta.getStatus() + "]" + RESET);
        }
    }

    public void atualizarDados(String nome, String endereco, String telefone, float salario) {
        if (salario <= 0) {
            throw new SalarioInvalidoException("Um veterinário deverá ter um salário positivo e pelo menos maior que zero.");
        }
        this.salario = salario;
        super.atualizarDados(nome, endereco, telefone); //sobrecarga do pai
    }

    public void exibirDados(Boolean mostrarDetalhes) {
        String BLUE = "\u001B[34m";
        String RESET = "\u001B[0m";
        if (!mostrarDetalhes) {
            System.out.println(BLUE + "[" + this.getNome() + "|" + this.getCRMV() + "]" + RESET);
            return;
        }

        System.out.println(BLUE + "[" + this.getNome() + "|" + this.getCRMV() + "|" + this.getCpf() + "|" + this.getEndereco() + "|" + this.getTelefone() + "]" + RESET);
        mostrarConsultas();
    }

    public String getCRMV() {
        return CRMV;
    }

    public float getSalario() {
        return salario;
    }
}
