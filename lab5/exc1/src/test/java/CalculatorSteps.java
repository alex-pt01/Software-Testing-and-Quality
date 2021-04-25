import static org.junit.jupiter.api.Assertions.*;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CalculatorSteps {
    private Calculator calc;

    @Given("^a calculator I just turned on$")
    public void setup() {
        calc = new Calculator();
    }

    @When("I power {int} to {int}")
    public void i_power_to(Integer arg1, Integer arg2) {
        calc.push(arg1);
        calc.push(arg2);
        calc.push("^");
    }

    @When("^I add (\\d+) and (\\d+)$")
    public void add(int arg1, int arg2) {
        calc.push(arg1);
        calc.push(arg2);
        calc.push("+");
    }

    @When("^I substract (\\d+) to (\\d+)$")
    public void substract(int arg1, int arg2) {
        calc.push(arg1);
        calc.push(arg2);
        calc.push("-");
    }

    @Then("^the result is (\\d+)$")
    public void the_result_is(double expected) {
        assertEquals(expected, calc.value());
    }

}