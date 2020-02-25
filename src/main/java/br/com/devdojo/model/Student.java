package br.com.devdojo.model;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity //mapea a classe para uma tabela do banco
public class Student extends AbstractEntity {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
