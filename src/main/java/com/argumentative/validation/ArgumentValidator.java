package com.argumentative.validation;

public class ArgumentValidator {

  public static boolean isValidKey(String key) {
    return key.startsWith("-");
  }

}
