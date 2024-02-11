package com.argumentative.match;

public record ArgumentMatcher(
    String shortKey,
    String longKey,
    String description
) {
  public boolean isMatch(String key) {
    return shortKey.equals(key) ||
        longKey.equals(key);
  }
}
