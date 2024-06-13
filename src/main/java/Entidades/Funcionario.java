
package Entidades;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author joao
 */
public class Funcionario extends Pessoa{
    private double salario;
    private String cargo;

    public Funcionario(String Nome, int Id) {
        super(Nome, Id);
    }
    

    public Funcionario(@JsonProperty("salario") double Salario,
                       @JsonProperty("cargo") String Cargo,
                       @JsonProperty("nome") String Nome,
                       @JsonProperty("id") int Id) {
        super(Nome, Id);
        this.salario = Salario;
        this.cargo = Cargo;
    }


    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    
    @Override
    public String toString(){
       return "Nome: " + this.getNome() + ", Id: " + this.getId()
               + ", Salario:" + this.getSalario() + ", Cargo: " + this.getSalario();
    }
}
