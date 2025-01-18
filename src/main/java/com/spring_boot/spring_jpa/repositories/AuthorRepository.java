package com.spring_boot.spring_jpa.repositories;

import com.spring_boot.spring_jpa.domain.Author;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {

    Iterable<Author> ageLessThan(Integer age); // JPA can create a query based on the name of the method

    @Query("select a from Author a where a.age > ?1") // Custom query using HQL
    Iterable<Author> findAuthorWithAgeGreaterThan(Integer age);
}
