package cinematic.cinemafuncional;

import Entidades.*;
import RegistroPessoa.PessoaDAO;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author joao
 */
public class CinemaFuncional {

     public static void main(String[] args) throws Exception {
         Scanner scanner = new Scanner(System.in);
        // Create instances of PessoaDAO classes for each person type
        PessoaDAO<Clientes> clienteDAO = new PessoaDAO<>("clientes.json", new TypeReference<List<Clientes>>() {});
        PessoaDAO<Funcionario> funcionarioDAO = new PessoaDAO<>("funcionarios.json", new TypeReference<List<Funcionario>>() {});
        PessoaDAO<Adm> admDAO = new PessoaDAO<>("adm.json", new TypeReference<List<Adm>>() {});
        // Create an instance of Navigation class and pass the DAOs
        MenuPessoas menuPessoa = new MenuPessoas(scanner, clienteDAO, funcionarioDAO, admDAO);
        
        // Start the navigation flow
        menuPessoa.startNavigation();
        
        
     }
}