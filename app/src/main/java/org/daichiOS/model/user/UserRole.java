package org.daichiOS.model.user;

public enum UserRole {
  USER,
  ADMIN;

  public String getAuthority() {
    return "ROLE_" + name();
  }
}
