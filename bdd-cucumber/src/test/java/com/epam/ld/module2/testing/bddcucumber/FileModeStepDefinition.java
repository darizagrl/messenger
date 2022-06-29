package com.epam.ld.module2.testing.bddcucumber;

import com.epam.ld.module2.testing.Main;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class FileModeStepDefinition {

    @Given("the input file with name {string} exists and contains template {string}")
    public void theInputFileWithNameExistsAndContainsTemplate(String inputFile, String content) throws IOException {
        FileWriter fileWriter = new FileWriter(inputFile);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.print(content);
        printWriter.close();

    }

    @When("the user enters in console input {string} and output {string} file names")
    public void theUserEntersInConsoleInputAndOutputFileNames(String inputFile, String outputFile) throws IOException {
        Main.main(new String[]{inputFile, outputFile});
    }

    @Then("output file name is {string}")
    public void outputFileNameIs(String outputFile) {
        Path p = Paths.get(outputFile);
        String file = p.getFileName().toString();
        assertAll(
                () -> assertTrue(Files.exists(p)),
                () -> assertEquals(outputFile, file)
        );
    }

    @And("output file {string} content is {string}")
    public void outputFileContentIs(String outputFile, String content) throws IOException {
        Path p = Paths.get(outputFile);
        String lines = Files.readAllLines(p).toString();
        assertAll(
                () -> Assertions.assertNotNull(lines),
                () -> assertEquals(content, lines)
        );
    }
}
