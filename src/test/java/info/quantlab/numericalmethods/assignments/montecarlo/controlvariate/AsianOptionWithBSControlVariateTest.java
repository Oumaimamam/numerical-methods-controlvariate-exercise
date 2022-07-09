package info.quantlab.numericalmethods.assignments.montecarlo.controlvariate;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import info.quantlab.numericalmethods.assignments.montecarlo.check.AsianOptionWithBSControlVariateAssignment;
import info.quantlab.numericalmethods.assignments.montecarlo.check.AsianOptionWithBSControlVariateChecker;
import info.quantlab.numericalmethods.assignments.montecarlo.check.AsianOptionWithBSControlVariateChecker.CHECK;

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
		if(!AsianOptionWithBSControlVariateChecker.check(new AsianOptionWithBSControlVariateSolution(), CHECK.BASIC)) fail();
	}

	@Test
	void testVarianceReductionWeak() {
		if(!AsianOptionWithBSControlVariateChecker.check(new AsianOptionWithBSControlVariateSolution(), CHECK.WEAK)) fail();
	}

	@Test
	void testVarianceReductionStrong() {
		if(!AsianOptionWithBSControlVariateChecker.check(new AsianOptionWithBSControlVariateSolution(), CHECK.STRONG)) fail();
	}
	
	@Test
	void testVarianceReductionStronger() {
		if(!AsianOptionWithBSControlVariateChecker.check(new AsianOptionWithBSControlVariateSolution(), CHECK.STRONGER)) fail();
	}
	
	@Test
	void testVarianceReductionStrongest() {
		if(!AsianOptionWithBSControlVariateChecker.check(new AsianOptionWithBSControlVariateSolution(), CHECK.STRONGEST)) fail();
	}
}
