
package cinematic.cinemafuncional;

import Entidades.*;
import RegistroPessoa.PessoaDAO;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author joao
 */
public class MenuPessoas {
    private Scanner scanner = new Scanner(System.in);
    private PessoaDAO<Clientes> clienteDAO;
    private PessoaDAO<Funcionario> funcionarioDAO;
    private PessoaDAO<Adm> admDAO;

    public MenuPessoas(Scanner scanner, PessoaDAO<Clientes> clienteDAO, PessoaDAO<Funcionario> funcionarioDAO, PessoaDAO<Adm> admDAO) {
        this.scanner = scanner;
        this.clienteDAO = clienteDAO;
        this.funcionarioDAO = funcionarioDAO;
        this.admDAO = admDAO;
    }
    

    public void startNavigation() throws Exception {
        while (true) {
            System.out.println("Escolha o tipo de operação a ser realizada:");
            System.out.println("1 - Cadastrada cliente");
            System.out.println("2 - Cadastrada funcionario");
            System.out.println("3 - Cadastrada administrador");
            System.out.println("4 - Imprimir lista de pessoas");
            System.out.println("5 - Deletar uma pessoa");
            System.out.println("6 - Editar uma pessoa");
            System.out.println("0 - Sair");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 0) {
                break;
            }

            handleChoice(choice);
        }
    }

    private void handleChoice(int choice) throws Exception {
        Pessoa pessoa = null;
        switch (choice) {
            case 1:
                pessoa = createCliente(scanner);
                if (pessoa != null) {
                    clienteDAO.save((Clientes) pessoa);
                    System.out.println("Pessoa cadastrada com sucesso!");
                }
                break;
            case 2:
                pessoa = createFuncionario(scanner);
                if (pessoa != null) {
                    funcionarioDAO.save((Funcionario) pessoa);
                    System.out.println("Pessoa cadastrada com sucesso!");
                }
                break;
            case 3:
                pessoa = createAdm(scanner);
                if (pessoa != null) {
                    admDAO.save((Adm) pessoa);
                    System.out.println("Pessoa cadastrada com sucesso!");
                }
                break;
            case 4:
                printAllPessoas(scanner);
                break;
            case 5:
                deletePerson(scanner, clienteDAO, funcionarioDAO, admDAO);
                break;
            case 6:
                editPerson(scanner, clienteDAO, funcionarioDAO, admDAO);
                break;
        }
        if (pessoa != null) {
                System.out.println("Pessoa cadastrada com sucesso!");
        }
    }

    //Metodos parar criar novas pessoas
    private static Clientes createCliente(Scanner scanner) {
        System.out.println("Digite o nome:");
        String Nome = scanner.nextLine();

        System.out.println("Digite o ID:");
        int Id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Digite o CPF:");
        String CPF = scanner.nextLine();

        System.out.println("Digite o email:");
        String Email = scanner.nextLine();

        System.out.println("Digite o telefone:");
        int Telefone = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Digite o nome da rua:");
        String Endereco = scanner.nextLine();

        

    return new Clientes(Nome, Id, CPF, Email, Telefone, Endereco);
    }
    
    private static Funcionario createFuncionario(Scanner scanner) {
        System.out.println("Digite o nome:");
        String Nome = scanner.nextLine();

        System.out.println("Digite o ID:");
        int Id = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        System.out.println("Digite o salário:");
        double Salario = scanner.nextDouble();
        scanner.nextLine(); // Consume newline character

        System.out.println("Digite o cargo:");
        String Cargo = scanner.nextLine();

        return new Funcionario(Salario, Cargo, Nome, Id);
    }
    
    private static Adm createAdm(Scanner scanner) {
        System.out.println("Digite o nome:");
    String Nome = scanner.nextLine();

    System.out.println("Digite o ID:");
    int Id = scanner.nextInt();
    scanner.nextLine(); // Consume newline character

    System.out.println("Digite o salário:");
    double Salario = scanner.nextDouble();
    scanner.nextLine(); // Consume newline character

    System.out.println("Digite o cargo:");
    String Cargo = scanner.nextLine();

    
        return new Adm(Salario, Cargo, Nome, Id);
    }

    private void printAllPessoas(Scanner scanner) throws IOException {
    System.out.println("Escolha qual lista a ser impressa:");
    System.out.println("1 - Clientes");
    System.out.println("2 - Funcionarios");
    System.out.println("3 - Adms");
    System.out.println("4 - Imprimir todos");

    int choice = scanner.nextInt();
    scanner.nextLine(); // Consume newline character

    switch (choice) {
        case 1:
            clienteDAO.imprime();
            break;
        case 2:
            funcionarioDAO.imprime();
            break;
        case 3:
            admDAO.imprime();
            break;
        case 4:
            System.out.println("Clientes:");
            clienteDAO.imprime();
            System.out.println("Funcionarios:");
            funcionarioDAO.imprime();
            System.out.println("Adms:");
            admDAO.imprime();
            break;
        default:
            System.out.println("Opção inválida!");
            break;
    }
}

    private static void deletePerson(Scanner scanner, PessoaDAO<Clientes> clienteDAO, PessoaDAO<Funcionario> funcionarioDAO, PessoaDAO<Adm> admDAO) throws Exception {
        System.out.println("Escolha o tipo de pessoa a ser deletada:");
        System.out.println("1 - Cliente");
        System.out.println("2 - Funcionario");
        System.out.println("3 - Adm");

        int choice = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Digite o ID da pessoa a ser deletada:");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean deleted = false;
        switch (choice) {
            case 1:
                deleted = clienteDAO.delete(id);
                break;
            case 2:
                deleted = funcionarioDAO.delete(id);
                break;
            case 3:
                deleted = admDAO.delete(id);
                break;
            default:
                System.out.println("Opção inválida!");
                break;
        }

        if (deleted) {
            System.out.println("Pessoa deletada com sucesso!");
        } else {
            System.out.println("Pessoa não encontrada!");
        }
    }

    private static void editPerson(Scanner scanner, PessoaDAO<Clientes> clienteDAO, PessoaDAO<Funcionario> funcionarioDAO, PessoaDAO<Adm> admDAO) throws Exception {
        System.out.println("Escolha o tipo de pessoa a ser editada:");
        System.out.println("1 - Cliente");
        System.out.println("2 - Funcionario");
        System.out.println("3 - Adm");

        int choice = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Digite o ID da pessoa a ser editada:");
        int id = scanner.nextInt();
        scanner.nextLine();

        Pessoa updatedPessoa = null;
        switch (choice) {
            case 1:
                updatedPessoa = createCliente(scanner);
                break;
            case 2:
                updatedPessoa = createFuncionario(scanner);
                break;
            case 3:
                updatedPessoa = createAdm(scanner);
                break;
            default:
                System.out.println("Opção inválida!");
                return;
        }

        boolean edited = false;
        switch (choice) {
            case 1:
                edited = clienteDAO.editarPessoa(id, (Clientes) updatedPessoa);
                break;
            case 2:
                edited = funcionarioDAO.editarPessoa(id, (Funcionario) updatedPessoa);
                break;
            case 3:
                edited = admDAO.editarPessoa(id, (Adm) updatedPessoa);
                break;
        }

        if (edited) {
            System.out.println("Pessoa editada com sucesso!");
        } else {
            System.out.println("Pessoa não encontrada!");
        }
    }
}
