package com.argumentative;

public record Argument(
    String shortKey,
    String longKey,
    String value
) {
  public boolean hasKey(String key) {
    return shortKey.equals(key) ||
        longKey.equals(key);
  }
}
