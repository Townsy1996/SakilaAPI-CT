package com.example.sakiladb.Steps;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources", // Adjust this to point to your feature files
        glue = {"com.example.sakiladb.Steps"} // Adjust to match the package structure of your step definitions
)
public class CucumberTestRunner {
}
