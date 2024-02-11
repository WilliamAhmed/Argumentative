package com.argumentative;

import com.argumentative.exception.InvalidArgumentException;
import com.argumentative.match.ArgumentParser;

public class Main {

  public static void main(String[] args) throws InvalidArgumentException {
    ArgumentSet argumentSet = new ArgumentParser()
        .withMatcher("-p", "-print", "Printing")
        .withMatcher("-l", "-long", "Long key")
        .parse(args);
  }

}
