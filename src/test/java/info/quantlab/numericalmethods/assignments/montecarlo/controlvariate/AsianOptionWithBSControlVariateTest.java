package info.quantlab.numericalmethods.assignments.montecarlo.controlvariate;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import info.quantlab.numericalmethods.assignments.montecarlo.check.AsianOptionWithBSControlVariateAssignment;
import info.quantlab.numericalmethods.assignments.montecarlo.check.AsianOptionWithBSControlVariateChecker;

/**
 * This class tests the implementation of your class.
 * The actual test code is not part of this project.
 * 
 * @author Christian Fries
 */
public class AsianOptionWithBSControlVariateTest {

	private static final AsianOptionWithBSControlVariateAssignment solution = new AsianOptionWithBSControlVariateSolution();;

	@Test
	void testBasicFunctionality() {
		System.out.println("Testing basic fuctionality of " + solution.getClass().getCanonicalName());
		
		boolean success = AsianOptionWithBSControlVariateChecker.check(solution, "basic");

		if(!success) {
			System.out.println("Sorry, the test failed.");
		}
		else {
			System.out.println("Congratulation! You solved this part of the exercise.");
		}

		System.out.println("_".repeat(79));

		if(!success) fail();
	}

	@Test
	void testVarianceReductionWeak() {
		System.out.println("Testing accuracy (weak) of " + solution.getClass().getCanonicalName());
		
		boolean success = AsianOptionWithBSControlVariateChecker.check(solution, "weak");

		if(!success) {
			System.out.println("Sorry, the test failed.");
		}
		else {
			System.out.println("Congratulation! You solved this part of the exercise.");
		}

		System.out.println("_".repeat(79));

		if(!success) fail();
	}

	@Test
	void testVarianceReductionStrong() {
		System.out.println("Testing accuracy (strong) of " + solution.getClass().getCanonicalName());
		
		boolean success = AsianOptionWithBSControlVariateChecker.check(solution, "strong");

		if(!success) {
			System.out.println("Sorry, the test failed.");
		}
		else {
			System.out.println("Congratulation! You solved this part of the exercise.");
		}

		System.out.println("_".repeat(79));

		if(!success) fail();
	}
}
