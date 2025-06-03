
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

-- Populate the Book table.

 INSERT INTO books (title, author, price, copies_available)
    VALUES 	("The Shadows Quest for Power", "Dave Wood", 17.99, 10),
			("Crimson Blade Chronicles", "Chris Price", 9.99, 8),
            ("The Sapphire Storm Prophecy", "Eleanor Matthews", 7.99, 12),
            ("The Emerald Enigma Strikes Back", "Eleanor Matthews", 7.99, 15),
            ("Haunting of Hollow Hill", "Michael Harris", 21.99, 12),
            ("The Curse of Crimson Manor", "Michael Harris", 16.99, 7),
            ("Shadowy Secrets of Whispering Woods", "Barry Cook", 20.50, 3),
            ("The Terror beneath Blackwater Bay", "Max Robertson", 5.50, 4),
			("Unlocking the Secrets to a Happier Life", "Dan James", 12.00, 13),
            ("The Joyful Journey: Embracing Everyday Happiness", "Dan James", 14.99, 8),
            ("Inner Peace: Finding Serenity in a Chaotic World", "Emily Davies", 16.99, 6),
            ("The Bright Side of Life: Cultivating Optimism", "Dennis Martin", 9.99, 11);