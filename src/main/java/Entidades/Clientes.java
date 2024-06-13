
package Entidades;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author joao
 */
public class Clientes extends Pessoa{
    //Atributos para clientes do sistema
    private String CPF;
    private String Email;
    private int Telefone;
    private String Endereco;

    @JsonCreator
    public Clientes(@JsonProperty("nome")String Nome, @JsonProperty("id") int Id, @JsonProperty("cpf") String CPF, @JsonProperty("email") String Email, @JsonProperty("telefone") int Telefone, @JsonProperty("endereco") String Endereco) {
        super(Nome, Id);
        this.CPF = CPF;
        this.Email = Email;
        this.Telefone = Telefone;
        this.Endereco = Endereco;
    }

    public Clientes(String Nome, int Id) {
        super(Nome, Id);
    }
    
    

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public int getTelefone() {
        return Telefone;
    }

    public void setTelefone(int Telefone) {
        this.Telefone = Telefone;
    }

    public String getEndereco() {
        return Endereco;
    }

    public void setEndereco(String Endereco) {
        this.Endereco = Endereco;
    }
    
    @Override
    public String toString(){
       return "Nome: " + this.getNome() + ", Id: " + this.getId() + ", CPF: " + this.getCPF()
               + ", Email: " + this.getEmail() + ", Telefone: " + this.getTelefone() + ", Endereço:" + this.getEndereco();
    }
    
}
