package com.argumentative.match;

import java.util.Objects;
import java.util.Optional;

public class ArgumentMatcher {

  private final String shortKey;
  private final String longKey;
  private final String description;

  public ArgumentMatcher(String shortKey, String longKey) {
    this(shortKey, longKey, "");
  }

  public ArgumentMatcher(String shortKey, String longKey, String description) {
    this.shortKey = shortKey;
    this.longKey = longKey;
    this.description = description;
  }

  public String getShortKey() {
    return shortKey;
  }

  public String getLongKey() {
    return longKey;
  }

  public Optional<String> getDescription() {
    return Optional.ofNullable(description);
  }

  public boolean isMatch(String key) {
    return shortKey.equals(key) ||
        longKey.equals(key);
  }
}
