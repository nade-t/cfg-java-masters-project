package com.cfgmasters.project.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "table-name") // change this once we have the DB code
@Entity
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id") // change once we have DB code
    private Long id;

    @Column(name = "title", length = 50, nullable = false) // change once we have DB code
    private String title;

    @Column(name = "author") // change once we have DB code
    private String author;

    @Column(name = "price") // change once we have DB code
    private double price;

    @Column(name = "copies_available") // change once we have DB code
    private int copiesAvailable;
}
