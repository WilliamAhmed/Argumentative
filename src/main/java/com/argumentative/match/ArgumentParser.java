package com.argumentative.match;

import com.argumentative.Argument;
import com.argumentative.ArgumentSet;
import com.argumentative.exception.InvalidArgumentException;
import com.argumentative.validation.ArgumentValidator;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class ArgumentParser {

  private final Set<ArgumentMatcher> argumentMatchers = new HashSet<>();

  private static ArgumentParser instance;

  private ArgumentParser() {}

  public static ArgumentParser get() {
    if (instance == null) {
      instance = new ArgumentParser();
    }
    return instance;
  }

  public ArgumentParser withMatcher(String shortCode, String longCode, String description) {
    argumentMatchers.add(new ArgumentMatcher(shortCode, longCode, description));
    return this;
  }

  public ArgumentParser withMatcher(String shortCode, String longCode) {
    argumentMatchers.add(new ArgumentMatcher(shortCode, longCode));
    return this;
  }

  public ArgumentSet parse(String[] args) throws InvalidArgumentException {
    Set<Argument> arguments = new HashSet<>();

    for (int i = 0; i < args.length - 1; i = i + 2) {
      String key = args[i];
      if (!ArgumentValidator.isValidKey(key)) {
        throw new InvalidArgumentException(String.format("Argument key %s is an invalid format", key));
      }

      ArgumentMatcher associatedMatcher = getArgumentMatcher(key)
          .orElseThrow(() -> new InvalidArgumentException(String.format("Argument key %s was not found", key)));

      arguments.add(new Argument(associatedMatcher.getShortKey(), associatedMatcher.getLongKey(), args[i+1]));
    }

    return new ArgumentSet(arguments);
  }

  public Optional<ArgumentMatcher> getArgumentMatcher(String key) {
    return argumentMatchers.stream()
        .filter(matcher -> matcher.isMatch(key))
        .findFirst();
  }
}
