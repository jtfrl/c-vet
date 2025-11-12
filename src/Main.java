
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;
import model.*;

public class Main {

    public static void main(String[] args) {
        Clinica clinica = new Clinica();

        Funcionario funcionario_A = new Funcionario("Raquel Garcia", "111.111.111-11", "parco della veritá",
                "84 98860-6745", 100000, "2020202", Cargo.AUXILIAR_TOSADOR);
        Funcionario funcionario_B = new Funcionario("Angelo Melo", "000.000.000-00", "parco della veritá",
                "84 98860-6745",
                100000, "2020203", Cargo.TOSADOR);

        Veterinario vet_A = new Veterinario("Carlos Eduardo", "222.222.222-22", "avenida brasil",
                "84 98860-6745", 150000, "3030303");

        Veterinario vet_B = new Veterinario("Mariana Souza", "333.333.333-33", "rua das flores",
                "84 98860-6745", 150000, "9012300");
        clinica.adicionarVeterinário(vet_A);
        clinica.adicionarVeterinário(vet_B);

        clinica.adicionarFuncionario(funcionario_A);
        clinica.adicionarFuncionario(funcionario_B);

        ArrayList<Animal> animaisInicias_A = new ArrayList<>();
        ArrayList<Animal> animaisInicias_B = new ArrayList<>();

        Cliente cliente_A = new Cliente("Pedro Varela", "123.456.789-00", "Rua A, 123", "84 99999-9999",
                animaisInicias_A);
        Cliente cliente_B = new Cliente("Ana Silva", "987.654.321-00", "Rua B, 456", "84 98888-8888",
                animaisInicias_B);

        Animal animal_1 = new Animal("Rex", Especie.CACHORRO, "Labrador", new java.util.Date(), cliente_A);
        Animal animal_2 = new Animal("Miau", Especie.GATO, "Siamês", new java.util.Date(), cliente_B);
        Animal animal_3 = new Animal("Bolt", Especie.CACHORRO, "Vira-lata", new java.util.Date(), cliente_B);

        animaisInicias_A.add(animal_1);
        animaisInicias_B.add(animal_2);
        animaisInicias_B.add(animal_3);

        clinica.adicionarCliente(cliente_A);
        clinica.adicionarCliente(cliente_B);

        Produto produto_1 = new Produto(1, "Ração Premium", "Ração de alta qualidade para cães adultos", 150.0f, 10);
        Produto produto_2 = new Produto(2, "Brinquedo Interativo", "Brinquedo para estimular a mente dos pets", 80.0f, 30);

        produto_1.vender(produto_2, cliente_A);
        produto_2.vender(produto_1, cliente_B);
        produto_1.vender(produto_1, cliente_A);

        clinica.adicionarProduto(produto_1);
        clinica.adicionarProduto(produto_2);

        ArrayList<Veterinario> vets1 = new ArrayList<>();
        vets1.add(vet_A);
        Consulta consulta_1 = new Consulta(1, "Consulta-1", "Vacinação", 200.0f, LocalDate.of(2026, 12, 21), LocalTime.of(12, 0), "Vacinação anual", vets1, animal_1);

        ArrayList<Veterinario> vets2 = new ArrayList<>();
        vets2.add(vet_B);
        vets2.add(vet_A);
        Consulta consulta_2 = new Consulta(2, "Consulta-2", "Checkup", 150.0f, LocalDate.of(2026, 12, 22), LocalTime.of(14, 0), "Check-up geral", vets2, animal_2);

        clinica.adicionarConsulta(consulta_1);
        clinica.adicionarConsulta(consulta_2);

        Menu.iniciarMenu(clinica);

    }
}
