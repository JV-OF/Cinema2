/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author joao
 */
public class Adm extends Funcionario{

    public Adm(@JsonProperty("salario") double Salario,
               @JsonProperty("cargo") String Cargo,
               @JsonProperty("nome") String Nome,
               @JsonProperty("id") int Id) {
        super(Salario, Cargo, Nome, Id);
    }
    
}
