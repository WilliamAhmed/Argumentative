package com.argumentative.validation;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ArgumentValidatorTest {

  @ParameterizedTest
  @ValueSource(strings = {"@s", "$s", "s", "|S", "!s"})
  void willReturnFalseForInvalidArgumentDefinitions(String key) {
    assertThat(ArgumentValidator.isValidKey(key)).isFalse();
  }

  @ParameterizedTest
  @ValueSource(strings = {"-s", "--s", "-s-something", "--s-something"})
  void willReturnTrueForValidArgumentDefinitions(String key) {
    assertThat(ArgumentValidator.isValidKey(key)).isTrue();
  }

}