package com.argumentative.match;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.argumentative.ArgumentSet;
import com.argumentative.exception.InvalidArgumentException;
import java.util.function.Consumer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ArgumentParserTest {

  private ArgumentParser underTest;

  @BeforeEach
  void setUp() {
    underTest = new ArgumentParser();
  }

  @Test
  void canAddArgumentMatcher() {
    underTest
        .withMatcher("-x", "-x-x", "X argument code");

    assertThat(underTest.getArgumentMatcher("-x"))
        .hasValueSatisfying(assertArgumentMatcher("-x", "-x-x", "X argument code"));
  }

  @Test
  void canHandleMultipleMatchers() {
    underTest
        .withMatcher("-x", "-x-x", "X argument code")
        .withMatcher("-y", "-y-y", "Y argument code");

    assertThat(underTest.getArgumentMatcher("-x"))
        .hasValueSatisfying(assertArgumentMatcher("-x", "-x-x", "X argument code"));

    assertThat(underTest.getArgumentMatcher("-y"))
        .hasValueSatisfying(assertArgumentMatcher("-y", "-y-y", "Y argument code"));
  }

  @Test
  void willReturnEmptyWhenNoMatcherWithKey() {
    underTest
        .withMatcher("-x", "-x-x", "X argument code");

    assertThat(underTest.getArgumentMatcher("-y")).isEmpty();
  }

  @Test
  void willReturnEmptyIfKeyDoesNotMatchCase() {
    underTest
        .withMatcher("-x", "-x-x", "X argument code");

    assertThat(underTest.getArgumentMatcher("-X")).isEmpty();
  }

  @Test
  void canParseArgumentInput() throws InvalidArgumentException {
    String[] args = new String[]{"-x", "some value"};

    ArgumentSet argumentSet = underTest
        .withMatcher("-x", "-x-x", "X argument code")
        .parse(args);

    assertThat(argumentSet.getValue("-x")).isEqualTo("some value");
  }

  @Test
  void willThrowInvalidArgumentExceptionWhenInvalidInput() throws InvalidArgumentException {
    String[] args = new String[]{"x", "some value"};

    assertThatThrownBy(() -> underTest
        .withMatcher("-x", "-x-x", "X argument code")
        .parse(args))
        .isInstanceOf(InvalidArgumentException.class);
  }

  @Test
  void willThrowInvalidArgumentExceptionIfExpectedArgumentIsMissing() {
    String[] args = new String[]{"-y", "some value"};

    assertThatThrownBy(() -> underTest
        .withMatcher("-x", "-x-x", "X argument code")
        .parse(args))
        .isInstanceOf(InvalidArgumentException.class);
  }

  private Consumer<ArgumentMatcher> assertArgumentMatcher(String shortCode, String longCode, String description) {
    return argumentMatcher -> {
      assertThat(argumentMatcher.shortKey()).isEqualTo(shortCode);
      assertThat(argumentMatcher.longKey()).isEqualTo(longCode);
      assertThat(argumentMatcher.description()).isEqualTo(description);
    };
  }
}