package model;

import exception.AnimalInexistenteException;
import exception.ClienteInexistenteException;
import java.util.ArrayList;

public class Clinica {

    private ArrayList<Veterinario> veterinariosDaClinica = new ArrayList<>();
    private ArrayList<Funcionario> funcionariosDaClinica = new ArrayList<>();
    private ArrayList<Cliente> clientesDaClinica = new ArrayList<>();
    private ArrayList<Consulta> consultasDaClinica = new ArrayList<>();

    // talvez aqui fosse melhor um map para mostrar cada cliente associado a um produto/serviço
    private ArrayList<Produto> produtosDaClinica = new ArrayList<>();
    private ArrayList<Servico> servicosDoCliente_Cons = new ArrayList<>();

    public Clinica() {
    }

    // create Veterinário
    public void adicionarVeterinário(Veterinario veterinario) {
        veterinariosDaClinica.add(veterinario);
        System.out.println("Veterinário adicionado com sucesso!");
    }

    // remove Veterinário
    public void removerVeterinário(Veterinario veterinario) {
        veterinariosDaClinica.remove(veterinario);
        System.out.println("Veterinário removido com sucesso!");
    }

    // update Veterinário
    public void atualizarVeterinário(Veterinario veterinario, String nome, String endereco, String telefone, float salario) {
        Veterinario veterinario1 = veterinariosDaClinica.stream()
                .filter(veterinarioAntigo -> veterinarioAntigo.getCRMV().equals(veterinario.getCRMV())).findFirst().orElse(null);

        if (veterinario1 != null) {
            veterinario1.atualizarDados(nome, endereco, telefone, salario);

        }
    }

    // read Veterinario
    public void listarVeterinarios() {
        for (Veterinario veterinario : veterinariosDaClinica) {
            veterinario.exibirDados(true);
        }
    }

    // get list de veterinarios
    public ArrayList<Veterinario> getTotalVeterinariosDaClinica() {
        return this.veterinariosDaClinica;
    }

    // get lista particionada de veterinarios
    public ArrayList<Veterinario> getParteVeterinariosDaClinica(int[] numeros) {
        ArrayList<Veterinario> veterinarios = new ArrayList<>();
        for (int i = 0; i < numeros.length; i++) {
            veterinarios.add(veterinariosDaClinica.get(numeros[i] - 1));
        }
        return veterinarios;
    }

    // create funcionário
    public void adicionarFuncionario(Funcionario funcionario) {
        funcionariosDaClinica.add(funcionario);
        System.out.println("Funcionário adicionado com sucesso!");
    }

    // remove funcionário
    public void removerFuncionario(Funcionario funcionario) {
        funcionariosDaClinica.remove(funcionario);
        System.out.println("Funcionário removido com sucesso!");
    }

    // update funcionario
    public void atualizarFuncionario(Funcionario funcionario, String nome, String endereco, String telefone, float salario, Cargo cargo) {
        Funcionario funcionarioClinica = funcionariosDaClinica.stream()
                .filter(funcionarioAntigo -> funcionarioAntigo.getIdentificadorCarteiraTrabalho().equals(funcionario.getIdentificadorCarteiraTrabalho())).findFirst().orElse(null);

        if (funcionarioClinica != null) {
            funcionarioClinica.atualizarDados(nome, endereco, telefone, salario, cargo);
        }
    }

    // listar funcionario
    public void listarFuncionarios() {
        for (Funcionario funcionario : funcionariosDaClinica) {
            funcionario.exibirDados(true);
        }
    }

    // get lista funcionarios
    public ArrayList<Funcionario> getFuncionariosDaClinica() {
        return funcionariosDaClinica;
    }

    // create cliente
    public void adicionarCliente(Cliente cliente) {
        clientesDaClinica.add(cliente);
        System.out.println("Cliente adicionado com sucesso!");
    }

    // listar clientes
    public void listarClientes() {
        System.out.println("Lista de Clientes da Clínica:");
        for (Cliente cliente : clientesDaClinica) {
            cliente.exibirDados(true);
        }
    }

    // get cliente
    public Cliente getCliente(String cpf) {
        Cliente clienteEncontrado = clientesDaClinica.stream().filter(cliente -> cliente.getCpf().equals(cpf))
                .findFirst().orElse(null);

        if (clienteEncontrado != null) {
            return clienteEncontrado;

        } else {
            throw new ClienteInexistenteException("Cliente não encontrado");

        }
    }

    // update cliente
    public void atualizarCliente(Cliente cliente, String nome, String endereco, String telefone) {
        Cliente clienteClinica = clientesDaClinica.stream()
                .filter(clienteAntigo -> clienteAntigo.getCpf().equals(cliente.getCpf())).findFirst().orElse(null);

        if (clienteClinica != null) {
            clienteClinica.atualizarDados(nome, endereco, telefone);

        } else {
            throw new ClienteInexistenteException("Cliente não encontrado");

        }
    }

    // get lista particionada de veterinarios
    public ArrayList<Cliente> getParteClientesDaClinica(int[] numeros) {
        ArrayList<Cliente> clientes = new ArrayList<>();
        for (int i = 0; i < numeros.length; i++) {
            clientes.add(clientesDaClinica.get(numeros[i] - 1));
        }
        return clientes;
    }

    // remove cliente
    public void removerCliente(Cliente cliente) {
        clientesDaClinica.remove(cliente);
    }

    // get lista de clientes
    public ArrayList<Cliente> getClientesDaClinica() {
        return this.clientesDaClinica;
    }

    // update animal do cliente
    public void atualizarAnimal(Animal animal, String nome, Especie especie, String raca, java.util.Date dataNascimento) {
        if (animal == null) {
            throw new AnimalInexistenteException("Animal não encontrado");
        }

        animal.atualizarDados(nome, especie, raca, dataNascimento);
    }

    // remover animal do cliente
    public void removerAnimal(Animal animal, Cliente cliente) {
        if (animal == null) {
            throw new AnimalInexistenteException("Animal não encontrado");
        }
        if (cliente == null) {
            throw new ClienteInexistenteException("Cliente não encontrado");
        }
        cliente.removerAnimal(animal.getId());
    }

    // adicionar animal ao cliente
    public void adicionarAnimal(Animal animal, Cliente cliente) {
        if (animal == null) {
            throw new AnimalInexistenteException("Animal não encontrado");
        }
        if (cliente == null) {
            throw new ClienteInexistenteException("Cliente não encontrado");
        }

        cliente.adicionarAnimal(animal);
    }

    public void listarAnimaisDoCliente(Cliente cliente) {
        if (cliente == null) {
            throw new ClienteInexistenteException("Cliente não encontrado");
        }

        cliente.mostrarListaDeAnimais();

    }

    public void adicionarConsulta(Consulta consulta) {
        consultasDaClinica.add(consulta);
        System.out.println("Consulta agendada com sucesso!");
    }

// READ - Listar consultas
    public void listarConsultas() {
        for (Consulta consulta : consultasDaClinica) {
            consulta.exibirDados(true);
        }
    }

    // DELETE - Remover consulta
    public void removerConsulta(Consulta consulta) {
        consultasDaClinica.remove(consulta);
        System.out.println("Consulta removida com sucesso!");
    }

    // GET - Obter lista de consultas
    public ArrayList<Consulta> getConsultasDaClinica() {
        return consultasDaClinica;
    }

    // CREATE - Cria o serviço e adciona no Array
    public void AddServico(Servico serv) {
        servicosDoCliente_Cons.add(serv);
        System.out.println("Serviço adicionado: " + serv.getNome());
    }

    // REMOVE - Remove o serviço 
    public void RemvServico(Servico serv) {
        servicosDoCliente_Cons.remove(serv);
        System.out.println("Serviço removido: " + serv.getNome());
    }

    // READ - leitura do nome do serviço
    public void lerServico(String sv) {
        for (Servico s : servicosDoCliente_Cons) {
            if (s.getNome().equals(sv)) {
                s.imprimirDados();
            }
        }
    }

    // UPDATE - atualiza o serviço
    public void atualizaServ(int id, String nome, String desc, float preco, Animal animal,
            ArrayList<Funcionario> listaDeFuncionarios) {
        for (Servico s : servicosDoCliente_Cons) {
            if (s.getID() == id) {
                s.atualizarDados(id, nome, desc, preco, animal, listaDeFuncionarios);
            }
        }

    }

    //CREATE - produto
    public void adicionarProduto(Produto produto) {
        produtosDaClinica.add(produto);
        System.out.println("Produto adicionado: " + produto.getNome());
    }

    //REMOVE - produto
    public void removerProduto(Produto produto) {
        produtosDaClinica.remove(produto);
    }

    //return list
    public ArrayList<Produto> getProdutosDaClinica() {
        return this.produtosDaClinica;
    }

    //READ - leitura do nome do produto para verificar no container
    public void LerProds(int limite) {
        if (limite > 0) {
            System.out.println("Lista de produtos limitada em " + limite);
            for (int i = 0; i < limite; i++) {
                produtosDaClinica.get(i).imprimirDados();
            }
        } else {
            for (Produto produto : produtosDaClinica) {
                produto.imprimirDados();
            }
        }
    }

    //UPGRADE - atualizar dados 
    public void atualizaProd(Produto produto, String nome, String desc, float preco, int etq) {
        Produto produtoClinica = produtosDaClinica.stream()
                .filter(produtoAntigo -> produtoAntigo.getId() == produto.getId())
                .findFirst()
                .orElse(null);

        if (produtoClinica != null) {
            produtoClinica.atualizarDados(nome, desc, preco, etq);

        }
    }

}
