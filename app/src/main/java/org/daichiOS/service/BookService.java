package org.daichiOS.service;

import java.util.List;
import java.util.Optional;
import org.daichiOS.model.book.Book;
import org.daichiOS.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

  private final BookRepository bookRepository;

  @Autowired
  public BookService(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  public List<Book> getAllBooks() {
    return bookRepository.findAll();
  }

  public Optional<Book> getBookById(String id) {
    return bookRepository.findById(id);
  }

  public List<Book> getBooksByAuthor(String author) {
    return bookRepository.findByAuthor(author);
  }

  public List<Book> getBooksByCategory(String category) {
    return bookRepository.findByCategory(category);
  }

  public List<Book> getBooksByNameContaining(String name) {
    return bookRepository.findByNameContaining(name);
  }

  public Book createBook(Book book) {
    return bookRepository.save(book);
  }

  public boolean deleteBook(String id) {
    if (!bookRepository.existsById(id)) {
      return false;
    }
    bookRepository.deleteById(id);
    return true;
  }

  public List<Book> searchBooks(String name, String author, String category) {
    // If all parameters are null, return all books
    if (name == null && author == null && category == null) {
      return bookRepository.findAll();
    }

    // If only name is provided
    if (name != null && author == null && category == null) {
      return bookRepository.findByNameContaining(name);
    }

    // If only author is provided
    if (name == null && author != null && category == null) {
      return bookRepository.findByAuthor(author);
    }

    // If only category is provided
    if (name == null && author == null && category != null) {
      return bookRepository.findByCategory(category);
    }

    // Name and author provided
    if (name != null && author != null && category == null) {
      return bookRepository.findByNameContainingAndAuthor(name, author);
    }

    // Name and category provided
    if (name != null && author == null && category != null) {
      return bookRepository.findByNameContainingAndCategory(name, category);
    }

    // Author and category provided
    if (name == null && author != null && category != null) {
      return bookRepository.findByAuthorAndCategory(author, category);
    }

    // All three parameters provided
    return bookRepository.findByNameContainingAndAuthorAndCategory(name, author, category);
  }

  public Optional<Book> updateBook(String id, Book book) {
    if (bookRepository.existsById(id)) {
      book.setId(id);
      return Optional.of(bookRepository.save(book));
    }
    return Optional.empty();
  }
}
