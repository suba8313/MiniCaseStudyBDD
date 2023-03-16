package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features="src//test//resources//features//Items.feature",
		glue={"stepDefs"},
		plugin= {"pretty",
				"html:target//Reports//HTMLReport.html",}
		)

 public class Pagerunner extends AbstractTestNGCucumberTests {


 }




