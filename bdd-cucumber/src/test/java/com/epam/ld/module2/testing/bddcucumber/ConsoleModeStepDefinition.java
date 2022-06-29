package com.epam.ld.module2.testing.bddcucumber;

import com.epam.ld.module2.testing.Main;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import java.io.*;

public class ConsoleModeStepDefinition {

    private OutputStream outputStream;


    @Given("the user enters in console {string} and {string} as a values for placeholders")
    public void theUserEntersInConsoleAndAsAValuesForPlaceholders(String subject, String body) {
        InputStream input = new ByteArrayInputStream((subject + "\n" + body).getBytes());
        System.setIn(input);
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @When("the application takes values from console")
    public void theApplicationTakesValuesFromConsole() throws IOException {
        Main.main(new String[]{});
    }

    @Then("output is {string}")
    public void outputIs(String output) {
        Assertions.assertEquals("Enter subject: Enter body: " + output, outputStream.toString());
    }
}
