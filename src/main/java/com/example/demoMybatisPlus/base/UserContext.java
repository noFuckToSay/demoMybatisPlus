package com.example.demoMybatisPlus.base;

import com.example.demoMybatisPlus.user.entity.User;

import java.util.Optional;

public class UserContext {

  private UserContext() {
    throw new IllegalAccessError("Utility class");
  }

  private static ThreadLocal<User> local = new ThreadLocal<>();

  public static final User currentUser(){
    return Optional.ofNullable(local.get()).orElse(new User());
  }

  public static final Optional<User> currentUserOpt(){
    return Optional.ofNullable(local.get());
  }

  public static final void set(User userInfo) {
    local.set(userInfo);
  }
  public static final void remove() {
    local.remove();
  }
}