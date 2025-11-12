package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

@SuppressWarnings("UseSpecificCatch")
public class Menu {

    public static void iniciarMenu(Clinica clinica) {
        while (true) {
            System.out.println("[Menu Principal]");
            System.out.println("[1] Gerenciar clientes");
            System.out.println("[2] Gerenciar funcionarios");
            System.out.println("[3] Gerenciar veterinarios");
            System.out.println("[4] Gerenciar consultas");
            System.out.println("[5] Gerenciar produtos");
            System.out.println("[6] Gerenciar animais de um cliente");
            System.out.println("[7] Sair");

            Scanner scanner = new Scanner(System.in);
            int escolha = scanner.nextInt();

            switch (escolha) {
                case 1 ->
                    MenuClientes(clinica);
                case 2 ->
                    MenuFuncionarios(clinica);
                case 3 ->
                    MenuVeterinarios(clinica);
                case 4 ->
                    MenuConsultas(clinica);
                case 5 ->
                    MenuProdutos(clinica);
                case 6 ->
                    MenuAnimais(clinica);
                case 7 -> {
                    System.out.println("Saindo do sistema...");
                    return;
                }
                default ->
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    public static void MenuClientes(Clinica clinica) {
        while (true) {
            System.out.println("[Menu Cliente]");
            System.out.println("[1 - C] Adicionar cliente");
            System.out.println("[2 - R] Listar clientes");
            System.out.println("[3 - U] Atualizar clientes");
            System.out.println("[4 - D] Deletar clientes");
            System.out.println("[5] Voltar para o menu principal");

            Scanner scanner = new Scanner(System.in);
            int escolha = scanner.nextInt();
            scanner.nextLine();

            switch (escolha) {
                case 1 -> {
                    System.out.println("Adicionando cliente...");
                    while (true) {
                        try {
                            System.out.print("Nome do cliente: ");
                            String nome = scanner.nextLine();

                            System.out.print("CPF do cliente: ");
                            String cpf = scanner.nextLine();

                            System.out.print("Endereço do cliente: ");
                            String endereco = scanner.nextLine();

                            System.out.print("Telefone do cliente: ");
                            String telefone = scanner.nextLine();

                            System.out.println("Nome do animal:");
                            String nomeAnimal = scanner.nextLine();

                            System.out.println("Escolha uma especie:");
                            System.out.println("[1] Cachorro");
                            System.out.println("[2] Gato");
                            System.out.println("[3] Cobra");
                            System.out.println("[4] Papagaio");
                            System.out.println("[5] Tatu");
                            int especieEscolhida = Integer.parseInt(scanner.nextLine());
                            Especie especieAnimal = switch (especieEscolhida) {
                                case 1 ->
                                    Especie.CACHORRO;
                                case 2 ->
                                    Especie.GATO;
                                case 3 ->
                                    Especie.COBRA;
                                case 4 ->
                                    Especie.PAPAGAIO;
                                case 5 ->
                                    Especie.TATU;
                                default ->
                                    throw new IllegalArgumentException("Espécie inválida");
                            };

                            System.out.println("Raça do animal:");
                            String racaAnimal = scanner.nextLine();

                            ArrayList<Animal> animais = new ArrayList<>();
                            Cliente cliente = new Cliente(nome, cpf, endereco, telefone, animais);
                            animais.add(new Animal(nomeAnimal, especieAnimal, racaAnimal, new Date(), cliente));

                            clinica.adicionarCliente(cliente);
                            break;
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            System.out.println("Por favor, tente novamente.");
                        }
                    }
                }
                case 2 -> {
                    System.out.println("Listando clientes...");
                    clinica.listarClientes();
                }
                case 3 -> {
                    System.out.println("Atualizando cliente...");
                    while (true) {
                        try {
                            for (Cliente cliente : clinica.getClientesDaClinica()) {
                                System.out.print("[" + (clinica.getClientesDaClinica().indexOf(cliente) + 1) + "]");
                                cliente.exibirDados(false);
                            }

                            System.out.println("Escolha o cliente ou digite '0' para cancelar:");
                            int indice = Integer.parseInt(scanner.nextLine());

                            if (indice == 0) {
                                System.out.println("Operação cancelada. Voltando ao menu de clientes...");
                                break;
                            }

                            Cliente cliente = clinica.getClientesDaClinica().get(indice - 1);

                            System.out.println("Atualizando cliente...");
                            System.out.print("Nome do cliente: ");
                            String nome = scanner.nextLine();

                            System.out.print("Endereço do cliente: ");
                            String endereco = scanner.nextLine();

                            System.out.print("Telefone do cliente: ");
                            String telefone = scanner.nextLine();

                            clinica.atualizarCliente(cliente, nome, endereco, telefone);
                            break;

                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            System.out.println("Por favor, tente novamente.");
                        }
                    }
                }
                case 4 -> {
                    System.out.println("Deletando cliente...");
                    while (true) {
                        try {
                            for (Cliente cliente : clinica.getClientesDaClinica()) {
                                System.out.print("[" + (clinica.getClientesDaClinica().indexOf(cliente) + 1) + "]");
                                cliente.exibirDados(false);
                            }

                            System.out.println("Escolha o cliente ou digite '0' para cancelar:");
                            int indice = Integer.parseInt(scanner.nextLine());

                            if (indice == 0) {
                                System.out.println("Operação cancelada. Voltando ao menu de clientes...");
                                break;
                            }

                            Cliente cliente = clinica.getClientesDaClinica().get(indice - 1);
                            clinica.removerCliente(cliente);
                            System.out.println("Cliente deletado com sucesso.");
                            break;

                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            System.out.println("Por favor, tente novamente.");
                        }
                    }
                }
                case 5 -> {
                    System.out.println("Voltando para o menu principal...");
                    return;
                }
                default ->
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    public static void MenuFuncionarios(Clinica clinica) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("[Menu Funcionarios]");
                System.out.println("[1 - C] Adicionar funcionario");
                System.out.println("[2 - R] Listar funcionarios");
                System.out.println("[3 - U] Atualizar funcionarios");
                System.out.println("[4 - D] Deletar funcionarios");
                System.out.println("[5] Voltar para o menu principal");

                int escolha = Integer.parseInt(scanner.nextLine());

                switch (escolha) {
                    case 1 -> {
                        System.out.println("Adicionando funcionario...");
                        System.out.print("Nome do funcionario: ");
                        String nome = scanner.nextLine();

                        System.out.print("CPF do funcionario: ");
                        String cpf = scanner.nextLine();

                        System.out.print("Endereço do funcionario: ");
                        String endereco = scanner.nextLine();

                        System.out.print("Telefone do funcionario: ");
                        String telefone = scanner.nextLine();

                        System.out.print("Salario do funcionario: ");
                        float salario = Float.parseFloat(scanner.nextLine());

                        System.out.print("Identificador da carteira de trabalho do funcionario: ");
                        String identificadorCarteiraTrabalho = scanner.nextLine();

                        System.out.println("Escolha um cargo:");
                        System.out.println("[1] Auxiliar Tosador");
                        System.out.println("[2] Auxiliar Veterinario");
                        System.out.println("[3] Biomedico");
                        System.out.println("[4] Motorista Animal");
                        System.out.println("[5] Tosador");

                        int cargoEscolhido = Integer.parseInt(scanner.nextLine());
                        Cargo cargo = switch (cargoEscolhido) {
                            case 1 ->
                                Cargo.AUXILIAR_TOSADOR;
                            case 2 ->
                                Cargo.AUXILIAR_VETERINARIO;
                            case 3 ->
                                Cargo.BIOMEDICO;
                            case 4 ->
                                Cargo.MOTORISTA_ANIMAL;
                            case 5 ->
                                Cargo.TOSADOR;
                            default ->
                                throw new IllegalArgumentException("Cargo inválido");
                        };

                        Funcionario funcionario = new Funcionario(nome, cpf, endereco, telefone, salario, identificadorCarteiraTrabalho, cargo);
                        clinica.adicionarFuncionario(funcionario);
                    }
                    case 2 -> {
                        System.out.println("Listando funcionarios...");
                        clinica.listarFuncionarios();
                    }
                    case 3 -> {
                        System.out.println("Atualizando funcionario...");
                        try {
                            for (Funcionario funcionario : clinica.getFuncionariosDaClinica()) {
                                System.out.print("[" + (clinica.getFuncionariosDaClinica().indexOf(funcionario) + 1) + "]");
                                funcionario.exibirDados();
                            }

                            System.out.println("Escolha o funcionario ou digite '0' para cancelar:");
                            int indice = Integer.parseInt(scanner.nextLine());

                            if (indice == 0) {
                                System.out.println("Operação cancelada. Voltando ao menu de clientes...");
                                break;
                            }

                            Funcionario funcionario = clinica.getFuncionariosDaClinica().get(indice - 1);

                            System.out.println("Atualizando cliente...");
                            System.out.print("Nome do cliente: ");
                            String nome = scanner.nextLine();

                            System.out.print("Endereço do cliente: ");
                            String endereco = scanner.nextLine();

                            System.out.print("Telefone do cliente: ");
                            String telefone = scanner.nextLine();

                            System.out.print("Salario do funcionario: ");
                            float salario = Float.parseFloat(scanner.nextLine());

                            System.out.println("Escolha um cargo:");
                            System.out.println("[1] Auxiliar Tosador");
                            System.out.println("[2] Auxiliar Veterinario");
                            System.out.println("[3] Biomedico");
                            System.out.println("[4] Motorista Animal");
                            System.out.println("[5] Tosador");

                            int cargoEscolhido = Integer.parseInt(scanner.nextLine());
                            Cargo cargo = switch (cargoEscolhido) {
                                case 1 ->
                                    Cargo.AUXILIAR_TOSADOR;
                                case 2 ->
                                    Cargo.AUXILIAR_VETERINARIO;
                                case 3 ->
                                    Cargo.BIOMEDICO;
                                case 4 ->
                                    Cargo.MOTORISTA_ANIMAL;
                                case 5 ->
                                    Cargo.TOSADOR;
                                default ->
                                    throw new IllegalArgumentException("Cargo inválido");
                            };

                            clinica.atualizarFuncionario(funcionario, nome, endereco, telefone, salario, cargo);
                            break;

                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            System.out.println("Por favor, tente novamente.");
                        }
                    }
                    case 4 -> {
                        System.out.println("Deletando funcionario...");
                        try {
                            for (Funcionario funcionario : clinica.getFuncionariosDaClinica()) {
                                System.out.print("[" + (clinica.getFuncionariosDaClinica().indexOf(funcionario) + 1) + "]");
                                funcionario.exibirDados();
                            }

                            System.out.println("Escolha o funcionario ou digite '0' para cancelar:");
                            int indice = Integer.parseInt(scanner.nextLine());

                            if (indice == 0) {
                                System.out.println("Operação cancelada. Voltando ao menu de funcionarios...");
                                break;
                            }

                            Funcionario func = clinica.getFuncionariosDaClinica().get(indice - 1);
                            clinica.removerFuncionario(func);
                            System.out.println("Funcionario deletado com sucesso.");
                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }
                    }
                    case 5 -> {
                        System.out.println("Voltando para o menu principal...");
                        return;
                    }
                    default ->
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Por favor, tente novamente.");
            }
        }
    }

    public static void MenuVeterinarios(Clinica clinica) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("[Menu Veterinarios]");
                System.out.println("[1 - C] Adicionar veterinarios");
                System.out.println("[2 - R] Listar veterinarios");
                System.out.println("[3 - U] Atualizar veterinarios");
                System.out.println("[4 - D] Deletar veterinarios");
                System.out.println("[5] Voltar para o menu principal");

                int escolha = Integer.parseInt(scanner.nextLine());

                switch (escolha) {
                    case 1 -> {
                        System.out.println("Adicionando veterinario...");
                        System.out.print("Nome do veterinario: ");
                        String nome = scanner.nextLine();

                        System.out.print("CPF do veterinario: ");
                        String cpf = scanner.nextLine();

                        System.out.print("Endereço do veterinario: ");
                        String endereco = scanner.nextLine();

                        System.out.print("Telefone do veterinario: ");
                        String telefone = scanner.nextLine();

                        System.out.print("Salario do veterinario: ");
                        float salario = Float.parseFloat(scanner.nextLine());

                        System.out.print("CRMV do veterinario: ");
                        String CRMV = scanner.nextLine();

                        Veterinario veterinario = new Veterinario(nome, cpf, endereco, telefone, salario, CRMV);
                        clinica.adicionarVeterinário(veterinario);
                    }
                    case 2 -> {
                        System.out.println("Listando veterinarios...");
                        clinica.listarVeterinarios();
                    }
                    case 3 -> {
                        System.out.println("Atualizando veterinario...");
                        while (true) {
                            try {
                                for (Veterinario veterinario : clinica.getTotalVeterinariosDaClinica()) {
                                    System.out.print("[" + (clinica.getTotalVeterinariosDaClinica().indexOf(veterinario) + 1) + "] ");
                                    veterinario.exibirDados();
                                }

                                System.out.println("Escolha o veterinario ou digite '0' para cancelar:");
                                int indice = Integer.parseInt(scanner.nextLine());

                                if (indice == 0) {
                                    System.out.println("Operação cancelada. Voltando ao menu de veterinarios...");
                                    break;
                                }

                                Veterinario veterinario = clinica.getTotalVeterinariosDaClinica().get(indice - 1);

                                System.out.println("Atualizando veterinario...");
                                System.out.print("Nome do veterinario: ");
                                String nome = scanner.nextLine();

                                System.out.print("Endereço do veterinario: ");
                                String endereco = scanner.nextLine();

                                System.out.print("Telefone do veterinario: ");
                                String telefone = scanner.nextLine();

                                System.out.print("Salario do veterinario: ");
                                String salarioEntrada = scanner.nextLine();
                                int salario = Integer.parseInt(salarioEntrada);

                                clinica.atualizarVeterinário(veterinario, nome, endereco, telefone, salario);
                                break;

                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                                System.out.println("Por favor, tente novamente.");
                            }
                        }
                    }
                    case 4 -> {
                        System.out.println("Deletando veterinario...");
                        try {
                            for (Veterinario veterinario : clinica.getTotalVeterinariosDaClinica()) {
                                System.out.print("[" + (clinica.getTotalVeterinariosDaClinica().indexOf(veterinario) + 1) + "]");
                                veterinario.exibirDados();
                            }

                            System.out.println("Escolha o veterinario (ou digite '0' para cancelar):");
                            int indice = Integer.parseInt(scanner.nextLine());

                            if (indice == 0) {
                                System.out.println("Operação cancelada. Voltando ao menu de funcionarios...");
                                break;
                            }

                            Veterinario vet = clinica.getTotalVeterinariosDaClinica().get(indice - 1);

                            clinica.removerVeterinário(vet);
                            System.out.println("Veterinario deletado com sucesso.");

                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }
                    }
                    case 5 -> {
                        System.out.println("Voltando para o menu principal...");
                        return;
                    }
                    default ->
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Por favor, tente novamente.");
            }
        }
    }

    public static void MenuConsultas(Clinica clinica) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("[Menu Consultas]");
            System.out.println("[1 - C] Agendar consulta");
            System.out.println("[2 - R] Listar consultas");
            System.out.println("[3 - U] Atualizar status da consulta");
            System.out.println("[4 - D] Cancelar consulta");
            System.out.println("[5] Voltar para o menu principal");

            int escolha = Integer.parseInt(scanner.nextLine());

            switch (escolha) {
                case 1 -> {
                    System.out.println("Agendando consulta...");
                    try {

                        if (clinica.getClientesDaClinica().isEmpty()) {
                            System.out.println("Não há clientes cadastrados! Cadastre um cliente primeiro.");
                            break;
                        }

                        System.out.println("Lista de clientes e seus animais:");
                        for (Cliente cliente : clinica.getClientesDaClinica()) {

                            System.out.println("[" + (clinica.getClientesDaClinica().indexOf(cliente) + 1) + "]");
                            cliente.exibirDados();

                            for (Animal animal : cliente.getAnimais()) {
                                String RED = "\u001B[31m";
                                String RESET = "\u001B[0m";
                                System.out.println(RED + "-- [" + (cliente.getAnimais().indexOf(animal) + 1) + "] " + animal.getNome() + " (" + animal.getEspecie() + " - " + animal.getRaca() + ")" + RESET);
                            }
                        }

                        System.out.println("\nEscolha o número do cliente (ou 0 para cancelar):");
                        int indiceCliente = Integer.parseInt(scanner.nextLine()) - 1;

                        if (indiceCliente == -1) {
                            System.out.println("Operação cancelada.");
                            break;
                        }

                        Cliente clienteSelecionado = clinica.getClientesDaClinica().get(indiceCliente);

                        if (clienteSelecionado.getAnimais().isEmpty()) {
                            System.out.println("Este cliente não possui animais cadastrados!");
                            break;
                        }

                        System.out.println("\nEscolha o número do animal:");
                        int indiceAnimal = Integer.parseInt(scanner.nextLine()) - 1;
                        Animal animalSelecionado = clienteSelecionado.getAnimais().get(indiceAnimal);

                        System.out.println("Veterinários disponíveis:");

                        for (Veterinario veterinario : clinica.getTotalVeterinariosDaClinica()) {
                            System.out.println("[" + (clinica.getTotalVeterinariosDaClinica().indexOf(veterinario) + 1) + "]");
                            veterinario.exibirDados();
                        }

                        System.out.println("\nEscolha o(s) número(s) dos veterinários (ex: 1,2,3,4) ou 0 para cancelar:");
                        String entrada = scanner.nextLine();
                        String[] partes = entrada.split(",");

                        if (partes[0].equals("0")) {
                            System.out.println("Operação cancelada.");
                            break;
                        }
                        int[] numeros = new int[partes.length];
                        for (int i = 0; i < partes.length; i++) {
                            numeros[i] = Integer.parseInt(partes[i].trim());
                        }

                        ArrayList<Veterinario> listaVeterinarios = clinica.getParteVeterinariosDaClinica(numeros);

                        System.out.println("\nData da consulta (formato: dd/MM/yyyy):");
                        String dataStr = scanner.nextLine();
                        String[] partesData = dataStr.split("/");
                        LocalDate data = LocalDate.of(
                                Integer.parseInt(partesData[2]),
                                Integer.parseInt(partesData[1]),
                                Integer.parseInt(partesData[0])
                        );

                        System.out.println("Hora da consulta (formato: HH:mm):");
                        String horaStr = scanner.nextLine();
                        String[] partesHora = horaStr.split(":");
                        LocalTime hora = LocalTime.of(
                                Integer.parseInt(partesHora[0]),
                                Integer.parseInt(partesHora[1])
                        );

                        System.out.println("Motivo da consulta:");
                        String motivo = scanner.nextLine();

                        System.out.println("Nome da consulta (ex: Consulta de Rotina):");
                        String nome = scanner.nextLine();

                        System.out.println("Descrição:");
                        String descricao = scanner.nextLine();

                        System.out.println("Preço da consulta:");
                        float preco = Float.parseFloat(scanner.nextLine());

                        int id = clinica.getConsultasDaClinica().size() + 1;

                        Consulta novaConsulta = new Consulta(id, nome, descricao, preco,
                                data, hora, motivo,
                                listaVeterinarios, animalSelecionado);

                        clinica.adicionarConsulta(novaConsulta);

                        System.out.println("\n✓ Consulta agendada com sucesso!");

                        novaConsulta.exibirDados(true);

                    } catch (Exception e) {
                        System.out.println("Erro ao agendar consulta: " + e.getMessage());
                        System.out.println("Por favor, tente novamente.");
                    }
                }
                case 2 -> {
                    System.out.println("Listando consultas...");
                    clinica.listarConsultas();
                }
                case 3 -> {
                    System.out.println("Atualizando status da consulta...");
                    try {

                        for (Consulta consulta : clinica.getConsultasDaClinica()) {
                            System.out.print("[" + (clinica.getConsultasDaClinica().indexOf(consulta) + 1) + "]");
                            consulta.exibirDados(false);
                        }

                        System.out.println("Escolha a consulta ou digite '0' para cancelar:");
                        int indice = Integer.parseInt(scanner.nextLine());

                        if (indice == 0) {
                            System.out.println("Operação cancelada.");
                            break;
                        }

                        Consulta consulta = clinica.getConsultasDaClinica().get(indice - 1);

                        System.out.println("Escolha a ação:");
                        System.out.println("[1] Iniciar consulta");
                        System.out.println("[2] Finalizar consulta");
                        int acao = Integer.parseInt(scanner.nextLine());

                        if (acao == 1) {
                            consulta.iniciarConsulta();
                            System.out.println("Atualizando consulta no veterinario...");
                        } else if (acao == 2) {
                            consulta.finalizarConsulta();
                            System.out.println("Atualizando consulta no veterinario...");
                        }

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 4 -> {
                    System.out.println("Cancelando consulta...");
                    try {

                        for (Consulta consulta : clinica.getConsultasDaClinica()) {
                            System.out.print("[" + (clinica.getConsultasDaClinica().indexOf(consulta) + 1) + "]");
                            consulta.exibirDados(false);
                        }

                        System.out.println("Escolha a consulta ou digite '0' para cancelar:");
                        int indice = Integer.parseInt(scanner.nextLine());

                        if (indice == 0) {
                            System.out.println("Operação cancelada.");
                            break;
                        }

                        Consulta consulta = clinica.getConsultasDaClinica().get(indice - 1);
                        consulta.cancelarConsulta();

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 5 -> {
                    System.out.println("Voltando para o menu principal...");
                    return;
                }
                default ->
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    public static void MenuAnimais(Clinica clinica) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("[Menu Animais de Cliente]");
            System.out.println("[1 - C] Adicionar animal a um cliente");
            System.out.println("[2 - R] Listar animais de um cliente");
            System.out.println("[3 - U] Atualizar animal");
            System.out.println("[4 - D] Remover animal");
            System.out.println("[5] Voltar para o menu principal");

            int escolha = Integer.parseInt(scanner.nextLine());

            switch (escolha) {
                case 1 -> {
                    System.out.println("Adicionando animal a um cliente...");
                    try {

                        if (clinica.getClientesDaClinica().isEmpty()) {
                            System.out.println("Não há clientes cadastrados! Cadastre um cliente primeiro.");
                            break;
                        }

                        System.out.println("=== CLIENTES ===");
                        for (int i = 0; i < clinica.getClientesDaClinica().size(); i++) {
                            Cliente cliente = clinica.getClientesDaClinica().get(i);
                            System.out.println("[" + (i + 1) + "] " + cliente.getNome() + " (CPF: " + cliente.getCpf() + ")");
                        }

                        System.out.println("\nEscolha o número do cliente (ou 0 para cancelar):");
                        int indiceCliente = Integer.parseInt(scanner.nextLine()) - 1;

                        if (indiceCliente == -1) {
                            System.out.println("Operação cancelada.");
                            break;
                        }

                        Cliente clienteSelecionado = clinica.getClientesDaClinica().get(indiceCliente);

                        System.out.println("\n=== DADOS DO ANIMAL ===");
                        System.out.print("Nome do animal: ");
                        String nomeAnimal = scanner.nextLine();

                        System.out.println("\nEscolha a espécie:");
                        System.out.println("[1] Cachorro");
                        System.out.println("[2] Gato");
                        System.out.println("[3] Cobra");
                        System.out.println("[4] Papagaio");
                        System.out.println("[5] Tatu");
                        int especieEscolhida = Integer.parseInt(scanner.nextLine());

                        Especie especieAnimal = switch (especieEscolhida) {
                            case 1 ->
                                Especie.CACHORRO;
                            case 2 ->
                                Especie.GATO;
                            case 3 ->
                                Especie.COBRA;
                            case 4 ->
                                Especie.PAPAGAIO;
                            case 5 ->
                                Especie.TATU;
                            default ->
                                throw new IllegalArgumentException("Espécie inválida");
                        };

                        System.out.print("Raça do animal: ");
                        String racaAnimal = scanner.nextLine();

                        Animal novoAnimal = new Animal(nomeAnimal, especieAnimal, racaAnimal, new Date(), clienteSelecionado);

                        clinica.adicionarAnimal(novoAnimal, clienteSelecionado);

                        System.out.println("\n✓ Animal adicionado com sucesso!");
                        System.out.println("ID: " + novoAnimal.getId());
                        System.out.println("Nome: " + novoAnimal.getNome());
                        System.out.println("Cliente: " + clienteSelecionado.getNome());

                    } catch (Exception e) {
                        System.out.println("Erro: " + e.getMessage());
                        System.out.println("Por favor, tente novamente.");
                    }
                }
                case 2 -> {
                    System.out.println("Listando animais de um cliente...");
                    try {

                        if (clinica.getClientesDaClinica().isEmpty()) {
                            System.out.println("Não há clientes cadastrados!");
                            break;
                        }

                        System.out.println("=== CLIENTES ===");
                        for (int i = 0; i < clinica.getClientesDaClinica().size(); i++) {
                            Cliente cliente = clinica.getClientesDaClinica().get(i);
                            System.out.println("[" + (i + 1) + "] " + cliente.getNome() + " (CPF: " + cliente.getCpf() + ")");
                        }

                        System.out.println("\nEscolha o número do cliente (ou 0 para cancelar):");
                        int indice = Integer.parseInt(scanner.nextLine()) - 1;

                        if (indice == -1) {
                            System.out.println("Operação cancelada.");
                            break;
                        }

                        Cliente clienteSelecionado = clinica.getClientesDaClinica().get(indice);

                        System.out.println("\n=== ANIMAIS DE " + clienteSelecionado.getNome().toUpperCase() + " ===");
                        clinica.listarAnimaisDoCliente(clienteSelecionado);

                    } catch (Exception e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                }
                case 3 -> {
                    System.out.println("Atualizando animal...");
                    try {

                        if (clinica.getClientesDaClinica().isEmpty()) {
                            System.out.println("Não há clientes cadastrados!");
                            break;
                        }

                        System.out.println("=== CLIENTES ===");
                        for (int i = 0; i < clinica.getClientesDaClinica().size(); i++) {
                            Cliente cliente = clinica.getClientesDaClinica().get(i);
                            System.out.println("[" + (i + 1) + "] " + cliente.getNome() + " (CPF: " + cliente.getCpf() + ")");
                        }

                        System.out.println("\nEscolha o número do cliente (ou 0 para cancelar):");
                        int indiceCliente = Integer.parseInt(scanner.nextLine()) - 1;

                        if (indiceCliente == -1) {
                            System.out.println("Operação cancelada.");
                            break;
                        }

                        Cliente clienteSelecionado = clinica.getClientesDaClinica().get(indiceCliente);

                        if (clienteSelecionado.getAnimais().isEmpty()) {
                            System.out.println("Este cliente não possui animais cadastrados!");
                            break;
                        }

                        System.out.println("\n=== ANIMAIS DE " + clienteSelecionado.getNome().toUpperCase() + " ===");
                        ArrayList<Animal> animais = clienteSelecionado.getAnimais();
                        for (int i = 0; i < animais.size(); i++) {
                            Animal animal = animais.get(i);
                            System.out.println("[" + (i + 1) + "] ID: " + animal.getId()
                                    + " | " + animal.getNome()
                                    + " (" + animal.getEspecie() + " - " + animal.getRaca() + ")");
                        }

                        System.out.println("\nEscolha o número do animal (ou 0 para cancelar):");
                        int indiceAnimal = Integer.parseInt(scanner.nextLine()) - 1;

                        if (indiceAnimal == -1) {
                            System.out.println("Operação cancelada.");
                            break;
                        }

                        Animal animalSelecionado = animais.get(indiceAnimal);

                        System.out.println("\n=== NOVOS DADOS DO ANIMAL ===");
                        System.out.print("Novo nome (atual: " + animalSelecionado.getNome() + "): ");
                        String novoNome = scanner.nextLine();

                        System.out.println("\nNova espécie (atual: " + animalSelecionado.getEspecie() + "):");
                        System.out.println("[1] Cachorro");
                        System.out.println("[2] Gato");
                        System.out.println("[3] Cobra");
                        System.out.println("[4] Papagaio");
                        System.out.println("[5] Tatu");
                        int especieEscolhida = Integer.parseInt(scanner.nextLine());

                        Especie novaEspecie = switch (especieEscolhida) {
                            case 1 ->
                                Especie.CACHORRO;
                            case 2 ->
                                Especie.GATO;
                            case 3 ->
                                Especie.COBRA;
                            case 4 ->
                                Especie.PAPAGAIO;
                            case 5 ->
                                Especie.TATU;
                            default ->
                                throw new IllegalArgumentException("Espécie inválida");
                        };

                        System.out.print("Nova raça (atual: " + animalSelecionado.getRaca() + "): ");
                        String novaRaca = scanner.nextLine();

                        Date dataNascimento = animalSelecionado.getDataNascimento();

                        clinica.atualizarAnimal(animalSelecionado, novoNome, novaEspecie, novaRaca, dataNascimento);

                        System.out.println("\n✓ Animal atualizado com sucesso!");

                    } catch (Exception e) {
                        System.out.println("Erro: " + e.getMessage());
                        System.out.println("Por favor, tente novamente.");
                    }
                }
                case 4 -> {
                    System.out.println("Removendo animal...");
                    try {

                        if (clinica.getClientesDaClinica().isEmpty()) {
                            System.out.println("Não há clientes cadastrados!");
                            break;
                        }

                        System.out.println("=== CLIENTES ===");
                        for (int i = 0; i < clinica.getClientesDaClinica().size(); i++) {
                            Cliente cliente = clinica.getClientesDaClinica().get(i);
                            System.out.println("[" + (i + 1) + "] " + cliente.getNome() + " (CPF: " + cliente.getCpf() + ")");
                        }

                        System.out.println("\nEscolha o número do cliente (ou 0 para cancelar):");
                        int indiceCliente = Integer.parseInt(scanner.nextLine()) - 1;

                        if (indiceCliente == -1) {
                            System.out.println("Operação cancelada.");
                            break;
                        }

                        Cliente clienteSelecionado = clinica.getClientesDaClinica().get(indiceCliente);

                        if (clienteSelecionado.getAnimais().isEmpty()) {
                            System.out.println("Este cliente não possui animais cadastrados!");
                            break;
                        }

                        System.out.println("\n=== ANIMAIS DE " + clienteSelecionado.getNome().toUpperCase() + " ===");
                        ArrayList<Animal> animais = clienteSelecionado.getAnimais();
                        for (int i = 0; i < animais.size(); i++) {
                            Animal animal = animais.get(i);
                            System.out.println("[" + (i + 1) + "] ID: " + animal.getId()
                                    + " | " + animal.getNome()
                                    + " (" + animal.getEspecie() + " - " + animal.getRaca() + ")");
                        }

                        System.out.println("\nEscolha o número do animal (ou 0 para cancelar):");
                        int indiceAnimal = Integer.parseInt(scanner.nextLine()) - 1;

                        if (indiceAnimal == -1) {
                            System.out.println("Operação cancelada.");
                            break;
                        }

                        Animal animalSelecionado = animais.get(indiceAnimal);

                        clinica.removerAnimal(animalSelecionado, clienteSelecionado);
                        System.out.println("✓ Animal removido com sucesso!");

                    } catch (Exception e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                }
                case 5 -> {
                    System.out.println("Voltando para o menu principal...");
                    return;
                }
                default ->
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    public static void MenuProdutos(Clinica clinica) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("[Menu Produtos]");
                System.out.println("[1 - C] Adicionar produto");
                System.out.println("[2 - R] Listar produtos");
                System.out.println("[3 - U] Atualizar produtos");
                System.out.println("[4 - D] Deletar produtos");
                System.out.println("[5 - V] Vender produtos");
                System.out.println("[6] Voltar para o menu principal");

                int escolha = Integer.parseInt(scanner.nextLine());

                switch (escolha) {
                    case 1 -> {
                        System.out.println("Adicionando produto...");

                        System.out.print("Nome do produto: ");
                        String nome = scanner.nextLine();

                        System.out.print("Descrição do produto: ");
                        String desc = scanner.nextLine();

                        System.out.print("Preço do produto: ");
                        float preco = Float.parseFloat(scanner.nextLine());

                        System.out.print("Estoque do produto: ");
                        int estoque = Integer.parseInt(scanner.nextLine());

                        int id = 0;

                        if (!clinica.getProdutosDaClinica().isEmpty()) {
                            id = clinica.getProdutosDaClinica().size();
                        }

                        Produto produto = new Produto(id, nome, desc, preco, estoque);

                        clinica.adicionarProduto(produto);
                    }
                    case 2 -> {
                        System.out.println("Listando produtos...");
                        System.out.print("Digite o limite de produtos que deseja ver: ");

                        int limite = Integer.parseInt(scanner.nextLine());

                        clinica.LerProds(limite);
                    }
                    case 3 -> {
                        System.out.println("Atualizando produto...");

                        for (int i = 0; i < clinica.getProdutosDaClinica().size(); i++) {
                            System.out.println("[" + (i + 1) + "] [" + clinica.getProdutosDaClinica().get(i).getNome() + "]");
                            clinica.getProdutosDaClinica().get(i).imprimirDados();
                        }

                        System.out.println("Escolha o produto ou digite '0' para cancelar:");
                        int indice = Integer.parseInt(scanner.nextLine());

                        if (indice == 0) {
                            System.out.println("Operação cancelada. Voltando ao menu de produtos...");
                            break;
                        }

                        Produto produto = clinica.getProdutosDaClinica().get(indice - 1);

                        System.out.print("Nome do produto: ");
                        String nome = scanner.nextLine();

                        System.out.print("Descrição do produto: ");
                        String desc = scanner.nextLine();

                        System.out.print("Preço do produto: ");
                        float preco = Float.parseFloat(scanner.nextLine());

                        System.out.print("Estoque do produto: ");
                        int estoque = Integer.parseInt(scanner.nextLine());

                        System.out.println("Atualizando produto no cliente...");
                        clinica.atualizaProd(produto, nome, desc, preco, estoque);

                        produto.atualizarDados(nome, desc, preco, estoque);

                    }
                    case 4 -> {
                        System.out.println("Deletando produto...");

                        for (int i = 0; i < clinica.getProdutosDaClinica().size(); i++) {
                            System.out.println("[" + (i + 1) + "] [" + clinica.getProdutosDaClinica().get(i).getNome() + "]");
                        }

                        System.out.println("Escolha o produto (ou digite '0' para cancelar):");
                        int indice = Integer.parseInt(scanner.nextLine());

                        if (indice == 0) {
                            System.out.println("Operação cancelada. Voltando ao menu de funcionarios...");
                            break;
                        }

                        System.out.println("Removendo produto dos clientes...");

                        Produto produto = clinica.getProdutosDaClinica().get(indice - 1);

                        clinica.removerProduto(produto);

                        System.out.println("Produto deletado com sucesso.");

                    }
                    case 5 -> {
                        System.out.println("Vendendo um produto para clientes...");

                        System.out.println("Produtos disponíveis para venda:");

                        for (Produto produto : clinica.getProdutosDaClinica()) {
                            System.out.println("[" + (clinica.getProdutosDaClinica().indexOf(produto) + 1) + "] [" + produto.getNome() + "]");
                            produto.imprimirDados();
                        }

                        System.out.println("Escolha o produto ou digite '0' para cancelar:");

                        int indice = Integer.parseInt(scanner.nextLine());

                        if (indice == 0) {
                            System.out.println("Operação cancelada. Voltando ao menu de produtos...");
                            break;
                        }

                        Produto produto = clinica.getProdutosDaClinica().get(indice - 1);

                        System.out.println("Clientes disponíveis para comprar o produto:");

                        for (Cliente cliente : clinica.getClientesDaClinica()) {
                            System.out.println("[" + (clinica.getClientesDaClinica().indexOf(cliente) + 1) + "] " + cliente.getNome() + " (CPF: " + cliente.getCpf() + ")");
                        }

                        System.out.println("\nEscolha o(s) número(s) dos clientes (ex: 1,2,3,4) ou 0 para cancelar:");

                        String entrada = scanner.nextLine();
                        String[] partes = entrada.split(",");

                        if (partes[0].equals("0")) {
                            System.out.println("Operação cancelada.");
                            break;
                        }

                        int[] numeros = new int[partes.length];

                        for (int i = 0; i < partes.length; i++) {
                            numeros[i] = Integer.parseInt(partes[i].trim());
                        }

                        System.out.println("Vendendo o produto para os clientes...");

                        ArrayList<Cliente> listaClientes = clinica.getParteClientesDaClinica(numeros);

                        for (Cliente cliente : listaClientes) {
                            System.out.println("[Vendendo para o cliente: " + cliente.getNome() + "| CPF: " + cliente.getCpf() + "]");
                            produto.vender(produto, cliente);
                        }

                    }
                    case 6 -> {
                        System.out.println("Voltando para o menu principal...");
                        return;
                    }
                    default ->
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Por favor, tente novamente.");
            }
        }
    }
}
