package com.spring_boot.spring_jpa.repositories;

import com.spring_boot.spring_jpa.domain.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, String> {
}
