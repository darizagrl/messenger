package com.epam.ld.module2.testing.bddcucumber;

import com.epam.ld.module2.testing.Main;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ExceptionStepDefinition {

    @When("the user enters in console a value {string}")
    public void theUserEntersInConsoleAValue(String value) {
        InputStream input = new ByteArrayInputStream((value).getBytes());
        System.setIn(input);
    }

    @Then("application throws exception {string}")
    public void applicationThrowsException(String exception) throws IllegalArgumentException {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
                () -> Main.main(new String[]{}));
        assertEquals(exception, thrown.getMessage());
    }

}
