package com.example; // Make sure this matches your actual package name

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/com/example", // Where your .feature files live
    glue = "com.example",                        // Where your Java step definitions will live
    plugin = {"pretty", "html:target/cucumber-reports.html"} // Generates a nice HTML report
)
public class RunCucumberTest {
    // This class will be empty. The annotations above will take care of everything.
}