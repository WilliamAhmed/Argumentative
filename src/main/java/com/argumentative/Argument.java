package com.argumentative;

public class Argument {

  private final String shortKey;
  private final String longKey;
  private final String value;

  public Argument(String shortKey, String longKey, String value) {
    this.shortKey = shortKey;
    this.longKey = longKey;
    this.value = value;
  }

  public String getShortKey() {
    return shortKey;
  }

  public String getLongKey() {
    return longKey;
  }

  public String getValue() {
    return value;
  }

  public boolean hasKey(String key) {
    return shortKey.equals(key) ||
        longKey.equals(key);
  }
}
