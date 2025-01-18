package com.spring_boot.spring_jpa;


import com.spring_boot.spring_jpa.domain.Author;
import com.spring_boot.spring_jpa.domain.Book;

public class TestDataUtil {
    private TestDataUtil(){
    }

    public static Author createTestAuthorA() {
        return new Author(1L,"Abigail Rose",80);
    }

    public static Author createTestAuthorB() {
        return new Author(2L,"Thomas Cronin",44);
    }

    public static Author createTestAuthorC() {
        return new Author(3L,"Jesse A Casey",24);
    }

    public static Book createTestBookA() {
        return new Book("978-1-2345-6789-0","The Shadow in the Attic",1L);
    }

    public static Book createTestBookB() {
        return new Book("978-1-2345-6789-1","Beyond the Horizon",1L);
    }

    public static Book createTestBookC() {
        return new Book("978-1-2345-6789-2","The Last Ember",1L);
    }
}
