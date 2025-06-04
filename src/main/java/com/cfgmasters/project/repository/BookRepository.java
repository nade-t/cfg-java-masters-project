package com.cfgmasters.project.repository;

import com.cfgmasters.project.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
