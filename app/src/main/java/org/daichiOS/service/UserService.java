package org.daichiOS.service;

import java.util.List;
import java.util.Optional;
import org.daichiOS.model.user.User;
import org.daichiOS.model.user.UserRole;
import org.daichiOS.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @Autowired
  public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  public Optional<User> getUserById(String id) {
    return userRepository.findById(id);
  }

  public Optional<User> updateUser(String id, User user) {
    if (userRepository.existsById(id)) {
      user.setId(id);
      return Optional.of(userRepository.save(user));
    }
    return Optional.empty();
  }

  public boolean deleteUser(String id) {
    if (!userRepository.existsById(id)) {
      return false;
    }
    userRepository.deleteById(id);
    return true;
  }

  public User registerUser(User user) {
    if (userRepository.existsByUsername(user.getUsername())) {
      throw new RuntimeException("Username already exists");
    }
    if (userRepository.existsByEmail(user.getEmail())) {
      throw new RuntimeException("Email already exists");
    }
    user.setPassword(passwordEncoder.encode(user.getPassword()));

    if (user.getRoles().isEmpty()) {
      user.getRoles().add(UserRole.USER);
    }
    return userRepository.save(user);
  }
}
