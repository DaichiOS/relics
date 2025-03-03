package org.daichiOS.controller.book;

import java.util.List;
import org.daichiOS.model.book.Book;
import org.daichiOS.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController {
  private final BookService bookService;

  @Autowired
  public BookController(BookService bookService) {
    this.bookService = bookService;
  }

  @GetMapping
  public ResponseEntity<List<Book>> getAllBooks() {
    return ResponseEntity.ok(bookService.getAllBooks());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Book> getBookById(@PathVariable String id) {
    return bookService
        .getBookById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<Book> createBook(@RequestBody Book book) {
    return ResponseEntity.status(HttpStatus.CREATED).body(bookService.createBook(book));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Book> updateBook(@PathVariable String id, @RequestBody Book book) {
    book.setId(id);
    return bookService
        .updateBook(book.getId(), book)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteBook(@PathVariable String id) {
    if (bookService.deleteBook(id)) {
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.notFound().build();
  }

  @GetMapping("/search")
  public ResponseEntity<List<Book>> searchBooks(
      @RequestParam(required = false) String name,
      @RequestParam(required = false) String author,
      @RequestParam(required = false) String category) {
    return ResponseEntity.ok(bookService.searchBooks(name, author, category));
  }
}
