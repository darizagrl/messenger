Feature: In console mode the application takes expression from console and prints result to console.

  Scenario: take expression from console and print result to console.
    Given the user enters in console "testSubject" and "testBody" as a values for placeholders
    When the application takes values from console
    Then output is "Subject: testSubject Body: testBody"