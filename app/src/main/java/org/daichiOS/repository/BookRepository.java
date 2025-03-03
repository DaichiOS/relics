package org.daichiOS.repository;

import java.util.List;
import org.daichiOS.model.book.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {
  List<Book> findByAuthor(String author);

  List<Book> findByCategory(String category);

  List<Book> findByNameContaining(String name);

  List<Book> findByNameContainingAndAuthor(String name, String author);

  List<Book> findByNameContainingAndCategory(String name, String category);

  List<Book> findByAuthorAndCategory(String author, String category);

  List<Book> findByNameContainingAndAuthorAndCategory(String name, String author, String category);
}
