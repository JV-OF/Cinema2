
package Entidades;

/**
 *
 * @author joao
 */
public class Pessoa {
    private String Nome;
    private int Id;

    public Pessoa(String Nome, int Id) {
        this.Nome = Nome;
        this.Id = Id;
    }
    

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        this.Nome = nome;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }
    
    @Override
    public String toString(){
       return "Nome: " + this.getNome() + ", Id: " + this.getId();
    }
    
}
