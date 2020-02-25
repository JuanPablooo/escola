package br.com.devdojo.endpoint;


import br.com.devdojo.error.CostumErrorType;
import br.com.devdojo.error.ResourceNotFoundException;
import br.com.devdojo.model.Student;
import br.com.devdojo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;

import sun.net.www.http.HttpClient;

import java.util.List;

import static java.util.Arrays.asList;

// o RestController adiciona o responsybody para todos os metodos
@RestController
@RequestMapping("students")
public class StudentEndPoint {
    private final StudentRepository studentDAO;

    @Autowired
    public StudentEndPoint(StudentRepository studentDAO) {
        this.studentDAO = studentDAO;
    }

    // @RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public ResponseEntity<?> listAll(){//sdsds
        //System.out.println(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return new ResponseEntity<>(studentDAO.findAll(), HttpStatus.OK);
    }

    //@RequestMapping(method = RequestMethod.GET, path = "/{id}")
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable("id") Long id){
        verifyIfStudentExists(id);
        Student student = studentDAO.findOne(id);
        return  new  ResponseEntity<>(student, HttpStatus.OK);
    }
    @GetMapping(path = "findByName/{name}")
    public ResponseEntity<?> findByName(@PathVariable String name){
        return new ResponseEntity<>(studentDAO.findByNameIgnoreCaseContaining(name), HttpStatus.OK);
    }

    //@RequestMapping(method = RequestMethod.POST)
    @PostMapping
    public ResponseEntity<?> save(@RequestBody Student student ){
        //Student studentWithId = studentDAO.save(student); //estudante salvo no banco
        return new ResponseEntity<> (studentDAO.save(student), HttpStatus.CREATED);

    }


    //@RequestMapping(method = RequestMethod.DELETE)
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        verifyIfStudentExists(id);
        studentDAO.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    //@RequestMapping(method = RequestMethod.PUT)
    @PutMapping
    public ResponseEntity<?> update(@RequestBody Student student){
        verifyIfStudentExists(student.getId());
        studentDAO.save(student);
        //se atualizou nao preciso retornar o estudante, pois ele que enviou o estudante
        return new ResponseEntity<> (HttpStatus.OK);
    }

    private void verifyIfStudentExists(Long id){
        if( studentDAO.findOne(id) == null ) {
            throw new ResourceNotFoundException("Student not found for id: "+id);
        }
    }

}
