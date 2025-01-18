package com.spring_boot.spring_jpa.domain;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

@Data
@Entity
@Table(name = "author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "author_id_generator")
    @SequenceGenerator(name = "author_id_generator", sequenceName = "author_id_seq", allocationSize = 1)
    @ColumnDefault("nextval('author_id_seq')")
    private Long id;

    @Column(unique=true, length = 100, nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer age;

    public Author(Long id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
