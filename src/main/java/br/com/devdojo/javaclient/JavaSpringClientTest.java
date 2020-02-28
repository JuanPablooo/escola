package br.com.devdojo.javaclient;

import br.com.devdojo.model.Student;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class JavaSpringClientTest {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplateBuilder()
                .rootUri("http://localhost:8080/v1/protected/students")
                .basicAuthorization("juan_user", "123")
                .build();
        Student student = restTemplate.getForObject("/{id}", Student.class, 1);
        ResponseEntity<Student> studentEntity= restTemplate.getForEntity("/{id}", Student.class, 1);
        System.out.println(student);
        System.out.println(student.getName());
        System.out.println("-=-=----=-=--=-=-=");
        System.out.println(studentEntity);
        System.out.println("-=-=----=-=--=-=-=");
        System.out.println(studentEntity.getClass());
    }
}
