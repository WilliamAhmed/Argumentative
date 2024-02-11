package com.argumentative;

import java.util.Set;

public class ArgumentSet {

  private final Set<Argument> arguments;

  public ArgumentSet(Set<Argument> arguments) {
    this.arguments = Set.copyOf(arguments);
  }

  public String getValue(String key) {
    return arguments.stream()
        .filter(argument -> argument.hasKey(key))
        .findFirst()
        .map(Argument::value)
        .orElseThrow();
  }
}
