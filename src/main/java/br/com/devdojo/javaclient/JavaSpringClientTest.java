package br.com.devdojo.javaclient;

import br.com.devdojo.model.Student;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;

public class JavaSpringClientTest {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplateBuilder()
                .rootUri("http://localhost:8080/v1/protected/students")
                .basicAuthorization("juan_user", "123")
                .build();
        Student student = restTemplate.getForObject("/{id}", Student.class, 1);
        //pegando em um response entity
        ResponseEntity<Student> studentEntity = restTemplate.getForEntity("/{id}", Student.class, 1);
        System.out.println(student);
        System.out.println(student.getName());
        System.out.println("-=-=----=-=--=-=-=");
        System.out.println(studentEntity.getBody());
        System.out.println("-=-=----=-=--=-=-=");

    //        ResponseEntity<List<Student>> studentList =
    //                restTemplate.exchange("/",
    //                HttpMethod.GET,
    //                null,
    //                new ParameterizedTypeReference<List<Student>>() {});
    //        System.out.println("lista de estudantes");
        

//        RestTemplate restTemplate = new RestTemplateBuilder()
//                .rootUri("http://localhost:8080/v1/protected/students")
//                .basicAuthorization("juan_user", "123")
//                .build();
//        Student student = restTemplate.getForObject("/{id}", Student.class, 1);
//        //pegando em um response entity
//        ResponseEntity<Student> studentEntity= restTemplate.getForEntity("/{id}", Student.class, 1);
//        System.out.println(student);
//        System.out.println(student.getName());
//        System.out.println("-=-=----=-=--=-=-=");
//        System.out.println(studentEntity);
//        System.out.println("-=-=----=-=--=-=-=");
//        //pegando e transformando em um objeto
//        Student[]  students = restTemplate.getForObject("/", Student[].class);
//        System.out.println(Arrays.toString(students));
//        /*
//        estamos declarando um responseEntity e o seu tipo e uma lista de estudantes
//            assim podemos pegar uma lista de objetos
//         */
//        ResponseEntity<List<Student>> studentList =
//                restTemplate.exchange("/",
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<List<Student>>() {});
//        System.out.println("lista de estudantes");
//        System.out.println(studentList);
//        System.out.println("corpo");
//        System.out.println(studentList.getBody());
    }
}
