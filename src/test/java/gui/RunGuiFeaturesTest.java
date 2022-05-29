package gui;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = "gui/steps",
        plugin = {"pretty"},
        features = "src/test/resources/features",
        publish = true)
public class RunGuiFeaturesTest {
}
