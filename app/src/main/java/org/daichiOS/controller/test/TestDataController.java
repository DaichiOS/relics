package org.daichiOS.controller.test;

import java.time.Duration;
import java.time.LocalDateTime;
import org.daichiOS.model.book.Book;
import org.daichiOS.model.user.User;
import org.daichiOS.service.BookService;
import org.daichiOS.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/createTestData")
public class TestDataController {
  private final UserService userService;
  private final BookService bookService;

  @Autowired
  public TestDataController(UserService userService, BookService bookService) {
    this.userService = userService;
    this.bookService = bookService;
  }

  @PostMapping
  public ResponseEntity<Void> createTestData() {
    // Create test users
    User testUser1 = new User();
    testUser1.setUsername("daisyKam");
    testUser1.setPassword("123daisy");
    testUser1.setEmail("daisyKam@gmail.com");
    userService.registerUser(testUser1);

    // Create test books
    Book testBook1 = new Book();
    testBook1.setName("How to annoy Snowy 101");
    testBook1.setAuthor("Jessica Kam");
    testBook1.setDescription("A book about how to annoy Snowy");
    testBook1.setCategory("Self-Help");
    testBook1.setLanguage("English");
    testBook1.setPublisher("DaichiOS");
    testBook1.setPublishedDate(LocalDateTime.now());
    testBook1.setPageCount(100);
    testBook1.setIsbn("1234567890");
    testBook1.setReadTime(Duration.ofHours(1));
    bookService.createBook(testBook1);

    return ResponseEntity.ok().build();
  }
}
