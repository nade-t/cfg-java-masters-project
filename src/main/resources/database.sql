
-- Create the database
CREATE DATABASE bookshop_db;
use bookshop_db;

-- Create the table for books
CREATE TABLE books (
	book_id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(50) NOT NULL,
    author VARCHAR(30) NOT NULL,
    price DECIMAL(4, 2) NOT NULL,
    copies_available INT(3)
);