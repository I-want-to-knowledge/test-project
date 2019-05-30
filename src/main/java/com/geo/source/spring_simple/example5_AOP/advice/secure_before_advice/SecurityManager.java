package com.geo.source.spring_simple.example5_AOP.advice.secure_before_advice;

/**
 * 安全管理员
 *
 * @author YanZhen
 * @since 2019-05-29 15:58
 */
public class SecurityManager {
  private static ThreadLocal<UserInfo> threadLocal = new ThreadLocal<>();

  void login(String name, String pwd) {
    threadLocal.set(new UserInfo(name, pwd));
  }

  void logout() {
    threadLocal.set(null);
  }

  UserInfo getLoggedOnUser() {
    return threadLocal.get();
  }
}
