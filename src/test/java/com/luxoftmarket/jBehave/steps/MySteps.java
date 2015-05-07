package com.luxoftmarket.jBehave.steps;

import com.luxoftmarket.controller.UserController;
import org.jbehave.core.annotations.Alias;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import static org.junit.Assert.assertEquals;

public class MySteps {

        UserController userController;

        String returnedValue;

        @Given("Add new user Request method get")
        public void given() {
                this.userController = new UserController();
        }



        @When("Call method addUser")
        public void whenIRunThisAsExecutableJar() {
                this.returnedValue = userController.addUser();
        }

        @Then("Returne value addUser")
        public void thenThisStoryShouldRun() {
             assertEquals("addUser", returnedValue);
        }
}
