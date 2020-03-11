package br.com.devdojo.endpoint;


import br.com.devdojo.error.CostumErrorType;
import br.com.devdojo.error.ResourceNotFoundException;
import br.com.devdojo.model.Student;
import br.com.devdojo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;

import sun.net.www.http.HttpClient;

import javax.validation.Valid;
import java.util.List;

import static java.util.Arrays.asList;

// o RestController adiciona o responsybody para todos os metodos
@RestController
@RequestMapping("v1")
public class StudentEndPoint {
    private final StudentRepository studentDAO;

    @Autowired
    public StudentEndPoint(StudentRepository studentDAO) {
        this.studentDAO = studentDAO;
    }

    // @RequestMapping(method = RequestMethod.GET)
    @GetMapping(path = "protected/students")
    // public ResponseEntity<?> listAll(Pageable pageable){
    public ResponseEntity<?> listAll(Pageable pageable){
        //System.out.println(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return new ResponseEntity<>(studentDAO.findAll(pageable), HttpStatus.OK);
    }

    //@RequestMapping(method = RequestMethod.GET, path = "/{id}")
    @GetMapping(path = "protected/students/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable("id") Long id,
                                            @AuthenticationPrincipal UserDetails userDetails){
        System.out.println(userDetails);
        verifyIfStudentExists(id);
        Student student = studentDAO.findOne(id);
        return  new  ResponseEntity<>(student, HttpStatus.OK);
    }
    @GetMapping(path = "protected/students/findByName/{name}")
    public ResponseEntity<?> findByName(@PathVariable String name){
        return new ResponseEntity<>(studentDAO.findByNameIgnoreCaseContaining(name), HttpStatus.OK);
    }

    //@RequestMapping(method = RequestMethod.POST)
    @PostMapping(path = "admin/students")
    public ResponseEntity<?> save(@Valid @RequestBody Student student ){
        //Student studentWithId = studentDAO.save(student); //estudante salvo no banco
        return new ResponseEntity<> (studentDAO.save(student), HttpStatus.CREATED);

    }


    //@RequestMapping(method = RequestMethod.DELETE)
    @DeleteMapping(path = "admin/students/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        verifyIfStudentExists(id);
        studentDAO.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    //@RequestMapping(method = RequestMethod.PUT)
    @PutMapping(path = "admin/students")
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
