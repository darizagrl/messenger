Feature: In file mode the application takes expression from file and prints result to file.

  Scenario: take files name from console and print result to file.
    Given the input file with name "input.txt" exists and contains template "Subject: #{subject} Body: #{body}"
    And the user enters in console "testSubject" and "testBody" as a values for placeholders
    When the user enters in console input "input.txt" and output "output.txt" file names
    Then output file name is "output.txt"
    And output content is "Subject: testSubject Body: testBody"