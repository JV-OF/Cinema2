
package RegistroPessoa;
import Entidades.*;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;


/**
 *
 * @author joao
 */
public class PessoaDAO <T extends Pessoa> {
    private final String filePath;
    private final ObjectMapper mapper;
    private final TypeReference<List<T>> typeReference;

    public PessoaDAO(String filePath, TypeReference<List<T>> typeReference) {
        this.filePath = filePath;
        this.mapper = new ObjectMapper();
        this.typeReference = typeReference;
    }

    public void save(T pessoa) throws Exception {
        File arquivo = new File(filePath);
        
        
            if (!arquivo.exists()) {
            System.out.println("File does not exist, creating a new file: " + filePath);
            boolean created = arquivo.createNewFile();
            if (created) {
                System.out.println("New file created successfully: " + filePath);
                // Create an empty ArrayList
                List<T> initialList = new ArrayList<>();
                
                // Convert the empty list to JSON
                String initialListJson = mapper.writeValueAsString(initialList);

                // Write the empty JSON to the file
                FileWriter writer = new FileWriter(filePath);
                writer.write(initialListJson);
                writer.close();
            } else {
                System.out.println("Failed to create a new file: " + filePath);
                throw new IOException("Failed to create a new file: " + filePath);
            }
        }
            
        List<T> pessoas = getAll();
        pessoas.add(pessoa);
        
        // Converte a lista pessoas para uma string JSON 
        String pessoasJson = mapper.writeValueAsString(pessoas);
        
         // Escreve a string JSON em um arquivo
        FileWriter writer = new FileWriter(filePath);
        writer.write(pessoasJson);
        writer.close();
    }

    public List<T> getAll() throws Exception {
        File arquivo = new File(filePath);
        if (!arquivo.exists()) {
            return new ArrayList<>();
        }
        return mapper.readValue(arquivo, typeReference);
    }
    
    public void imprime() throws IOException{
        FileReader reader = new FileReader(filePath);
        List<T> pessoas = mapper.readValue(reader, typeReference);
        
         // Enable pretty print for ObjectMapper
        String prettyJsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(pessoas);
        System.out.println(prettyJsonString);
        
        reader.close();  // Manual resource closing recommended
    }
    
    public boolean delete(int id) throws Exception {
        List<T> pessoas = getAll();
        boolean removed = pessoas.removeIf(pessoa -> pessoa.getId() == id);
        
        if (removed) {
            // Convert the updated list to a JSON string
            String pessoasJson = mapper.writeValueAsString(pessoas);

            // Write the JSON string to the file
            FileWriter writer = new FileWriter(filePath);
            writer.write(pessoasJson);
            writer.close();
        }

        return removed;
    }
    
    public boolean editarPessoa(int id, T updatedPessoa) throws Exception {
        List<T> pessoas = getAll();
        Optional<T> existingPessoaOpt = pessoas.stream().filter(pessoa -> pessoa.getId() == id).findFirst();

        if (existingPessoaOpt.isPresent()) {
            T existingPessoa = existingPessoaOpt.get();
            pessoas.remove(existingPessoa);
            pessoas.add(updatedPessoa);

            // Convert the updated list to a JSON string
            String pessoasJson = mapper.writeValueAsString(pessoas);

            // Write the JSON string to the file
            FileWriter writer = new FileWriter(filePath);
            writer.write(pessoasJson);
            writer.close();

            return true;
        } else {
            return false;
        }
    }
    
}

