package com.cfgmasters.project.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "books")
@Entity
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long id;

    @Column(name = "title", length = 50, nullable = false)
    private String title;

    @Column(name = "author", length = 30, nullable = false)
    private String author;

    @DecimalMin(value = "1.00", inclusive = true, message = ("Price must be £1 or more"))
    @Column(name = "price", precision = 4,scale = 2, nullable = false)
    private BigDecimal price;

    @Column(name = "copies_available", length = 3)
    private int copiesAvailable;
}
