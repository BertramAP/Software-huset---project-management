package com.example;
// Written by BAP
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.SNIPPET_TYPE_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("com/example") // Points to src/test/resources/com/example
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.example") // Points to your step definitions
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty, html:target/cucumber-reports.html") // Replaces plugin = {...}
@ConfigurationParameter(key = SNIPPET_TYPE_PROPERTY_NAME, value = "camelcase") // Forces camelCase snippets
public class RunCucumberTest {
    // This class must remain empty.
}