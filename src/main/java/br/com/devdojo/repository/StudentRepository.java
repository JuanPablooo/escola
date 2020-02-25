package br.com.devdojo.repository;


import br.com.devdojo.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface StudentRepository extends CrudRepository<Student, Long> {
    //o spring sozinho monta a query ignorando case e com o like (podendo buscar pedacos de nomes)
    List<Student> findByNameIgnoreCaseContaining(String name);
}
