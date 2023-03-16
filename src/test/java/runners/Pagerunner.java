package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features="src//test//resources//features//Items.feature",
		glue={"stepDefs"},
		monochrome = true,
		dryRun = false,
		plugin= {"pretty",
				"html:target//reports//htmlreport"}
		)

 public class Pagerunner extends AbstractTestNGCucumberTests {


 }




