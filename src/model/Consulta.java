package model;

import exception.DataInvalidaException;
import exception.EstadoInvalidoException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class Consulta extends Servico {

    private int id;
    private LocalDate data;
    private LocalTime hora;
    private String motivo;
    private String status;
    private ArrayList<Veterinario> listaDeVeterinarios;

    public Consulta(int id, String nome, String descricao, float preco,
            LocalDate data, LocalTime hora, String motivo,
            ArrayList<Veterinario> listaDeVeterinarios, Animal animal) throws DataInvalidaException {

        super(id, nome, descricao, preco, animal, null);

        LocalDateTime dataHoraConsulta = LocalDateTime.of(data, hora);
        LocalDateTime agora = LocalDateTime.now();

        if (dataHoraConsulta.isBefore(agora)) {
            throw new DataInvalidaException("Data e hora da consulta não podem ser no passado.");
        }

        this.id = id;
        this.data = data;
        this.hora = hora;
        this.motivo = motivo;
        this.status = "AGENDADA";
        this.listaDeVeterinarios = listaDeVeterinarios;

        // Registrar a consulta para cada veterinário envolvido
        for (Veterinario vet : listaDeVeterinarios) {
            vet.registrarConsulta(this);
        }
    }

    @Override
    public int getId() {
        return id;
    }

    public LocalDate getData() {
        return data;
    }

    public LocalTime getHora() {
        return hora;
    }

    public String getMotivo() {
        return motivo;
    }

    public String getStatus() {
        return status;
    }

    public ArrayList<Veterinario> getVeterinarios() {
        return listaDeVeterinarios;
    }

    public void iniciarConsulta() {
        if (!this.status.equals("AGENDADA")) {
            throw new EstadoInvalidoException("Não é possível iniciar consulta com status: " + this.status);
        }

        this.status = "EM ANDAMENTO";
        System.out.println("Consulta iniciada");
    }

    public void finalizarConsulta() {
        if (!this.status.equals("EM ANDAMENTO")) {
            throw new EstadoInvalidoException("Não é possível finalizar consulta com status: " + this.status);
        }

        this.status = "FINALIZADA";
        System.out.println("Consulta finalizada");
    }

    public void cancelarConsulta() {
        if (!this.status.equals("AGENDADA")) {
            throw new EstadoInvalidoException("Não é possível cancelar consulta com status: " + this.status);
        }
        this.status = "CANCELADA";
        System.out.println("Consulta cancelada");
    }

    public void exibirDados(Boolean mostrarDetalhes) {

        String BLUE = "\u001B[34m";
        String RESET = "\u001B[0m";
        if (!mostrarDetalhes) {
            System.out.println(BLUE + "[ Consulta ID: " + this.getId()
                    + " | Data: " + this.getData()
                    + " | Hora: " + this.getHora()
                    + " | Status: " + this.getStatus()
                    + " ]" + RESET
            );
            return;
        }

        System.out.println(BLUE + "[ ID: " + this.getId()
                + " | Data: " + this.getData()
                + " | Hora: " + this.getHora()
                + " | Status: " + this.getStatus()
                + " ]" + RESET
        );

        String RED = "\u001B[31m";
        String PURPLE = "\u001B[35m";

        System.out.println("-- Veterinários envolvidos na consulta:");

        for (Veterinario vet : this.getVeterinarios()) {
            System.out.println(RED + "-- [ Nome: " + vet.getNome() + " | CRMV: " + vet.getCRMV() + " ]" + RESET);
        }

        System.out.println("-- Animal envolvido na consulta:");
        System.out.println(PURPLE + "-- [ Nome: " + this.getAnimal().getNome()
                + " | Espécie: " + this.getAnimal().getEspecie()
                + " | Raça: " + this.getAnimal().getRaca() + " ]");
        System.out.println();

    }
}
