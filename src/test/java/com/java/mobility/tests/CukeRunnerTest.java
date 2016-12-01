package com.java.mobility.tests;

/**
 *
 * @author Ramandeep <RamandeepSingh@QAInfoTech.com>
 */
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"progress",
            "json:target/cucumber-report.json",
            "html:target/cucumber-report.html"
        },
        features="features/"
)
public class CukeRunnerTest {
}