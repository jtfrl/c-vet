package model;

import exception.AnimalInexistenteException;
import exception.DadosObrigatoriosException;

import java.util.ArrayList;

public class Cliente extends Pessoa {

    private ArrayList<Animal> listaDeAnimais;
    private ArrayList<Produto> listaDeProdutos;

    public Cliente(String nome, String cpf, String endereco, String telefone, ArrayList<Animal> listaDeAnimais) throws DadosObrigatoriosException {
        super(nome, cpf, endereco, telefone);
        if (listaDeAnimais == null) {
            throw new DadosObrigatoriosException("Um cliente deverá ter pelo menos um animal");
        }
        this.listaDeAnimais = listaDeAnimais;
        this.listaDeProdutos = new ArrayList<>();
    }

    public void agendarConsulta() {
        // Implementar
    }

    public void atualizarDados(String nome, String endereco, String telefone) {
        if (listaDeAnimais == null) {
            throw new DadosObrigatoriosException("Um cliente deverá ter pelo menos um animal");
        }
        super.atualizarDados(nome, endereco, telefone); //sobrecarga do pai
    }

    public void exibirDados(Boolean mostrarDetalhes) {
        if (!mostrarDetalhes) {
            super.exibirDados();
        } else if (mostrarDetalhes) {
            // Dados do cliente
            super.exibirDados();
            // Dados dos animais
            mostrarListaDeAnimais();
            // Dados dos produtos
            mostrarProdutos();
        }

    }

    public ArrayList<Animal> getAnimais() {
        return this.listaDeAnimais;
    }

    public ArrayList<Produto> getProdutos() {
        return this.listaDeProdutos;
    }

    public void removerProduto(Produto produto) {
        listaDeProdutos.remove(produto);
    }

    public void atualizarProduto(Produto produto) {
        //acha de listaDeProdutos
        Produto produtoParaAtualizar = this.listaDeProdutos.stream().filter(produtoAntigo -> produtoAntigo.getId() == produto.getId()).findFirst().orElse(null);

        if (produtoParaAtualizar != null) {
            for (Produto p : this.listaDeProdutos) {
                if (p.getId() == produtoParaAtualizar.getId()) {
                    p.atualizarDados(produto.getNome(), produto.getDescricao(), produto.getPreco(), produto.getEstoque());
                }
            }
        }
    }

    // CRUD de animais do cliente
    public void adicionarAnimal(Animal animal) {
        this.listaDeAnimais.add(animal);
    }

    public void removerAnimal(int id) {
        this.listaDeAnimais.removeIf(animal -> animal.getId() == id);
    }

    public void atualizarAnimal(Animal animal) {
        Animal animalParaAtualizar = this.listaDeAnimais.stream().filter(animalAntigo -> animalAntigo.getId() == animal.getId()).findFirst().orElse(null);

        if (animalParaAtualizar != null) {
            animalParaAtualizar.atualizarDados(animal.getNome(), animal.getEspecie(), animal.getRaca(), animal.getDataNascimento());
        } else {
            throw new AnimalInexistenteException("Animal de ID: " + animal.getId() + " , não foi encontrado");
        }
    }

    public void mostrarListaDeAnimais() {
        final String RED = "\u001B[31m";
        final String RESET = "\u001B[0m";

        if (this.listaDeAnimais.isEmpty()) {
            System.out.println(RED + "-- Nenhum animal cadastrado. " + RESET);
            return;
        }

        System.out.println("-- Lista de Animais:");
        for (Animal animal : this.listaDeAnimais) {
            System.out.println(RED + "-- [ ID: " + animal.getId()
                    + " | Nome: " + animal.getNome()
                    + " | Espécie: " + animal.getEspecie()
                    + " | Raça: " + animal.getRaca() + " ]" + RESET);
        }
    }

    public void registrarPesoAnimal(int id, float peso) {
        Animal animalParaAtualizar = this.listaDeAnimais.stream().filter(animal -> animal.getId() == id).findFirst().orElse(null);

        if (animalParaAtualizar != null) {
            animalParaAtualizar.registrarPeso(peso);
        } else {
            throw new AnimalInexistenteException("Animal de ID: " + id + " , não foi encontrado");
        }
    }

    public void registrarObservacaoAnimal(int id, String observacao) {
        Animal animalParaAtualizar = this.listaDeAnimais.stream().filter(animal -> animal.getId() == id).findFirst().orElse(null);

        if (animalParaAtualizar != null) {
            animalParaAtualizar.registrarObservacao(observacao);
        } else {
            throw new AnimalInexistenteException("Animal de ID: " + id + " , não foi encontrado");
        }
    }

    public void adicionarProduto(Produto produto) {
        listaDeProdutos.add(produto);
    }

    public void mostrarProdutos() {
        final String PURPLE = "\u001B[35m";
        final String RESET = "\u001B[0m";
        if (this.listaDeProdutos.isEmpty()) {
            System.out.println(PURPLE + "-- Nenhum produto comprado. " + RESET);
        } else {
            System.out.println("-- Lista de Produtos Comprados:");
            for (Produto produto : this.listaDeProdutos) {
                System.out.println(PURPLE + "-- [ ID: " + produto.getId()
                        + " | Nome: " + produto.getNome()
                        + " | Preço: " + produto.getPreco()
                        + " | Descrição: " + produto.getDescricao() + " ]" + RESET);
            }
        }
    }

}
