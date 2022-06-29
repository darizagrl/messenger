Feature: The application throws exception when at least one placeholder value is not provided at runtime

  Scenario: take single value from console and throw exception
    When the user enters in console a value "test"
    Then application throws exception "Input shouldn't be empty"

  Scenario: take empty value from console and throw exception
    When the user enters in console a value ""
    Then application throws exception "Input shouldn't be empty"

  Scenario: take empty value from console and throw exception
    When the user enters in console a value " "
    Then application throws exception "Input shouldn't be empty"