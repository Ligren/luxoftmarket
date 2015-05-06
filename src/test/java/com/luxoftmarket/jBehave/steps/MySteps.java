package com.luxoftmarket.jBehave.steps;

import org.jbehave.core.annotations.Alias;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

public class MySteps {

        @When("I run this as executable jar") //When I run this as executable jar -- jar
        @Alias("this") //When this -- jar2
        public void whenIRunThisAsExecutableJar() {
                System.out.println("we are in the whenIRunThisAsExecutableJar");
            // we don't do anything
        }

        @Then("this story should run") // Then this story should run
        @Alias("that") // Then that -- jar2
        public void thenThisStoryShouldRun() {
                System.out.println("we are in the thenThisStoryShouldRun");
            // we don't do anything
        }
}
