package info.quantlab.numericalmethods.assignments.montecarlo.controlvariate;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import info.quantlab.numericalmethods.assignments.montecarlo.check.AsianOptionWithBSControlVariateAssignment;
import info.quantlab.numericalmethods.assignments.montecarlo.check.AsianOptionWithBSControlVariateChecker;
import info.quantlab.numericalmethods.assignments.montecarlo.check.AsianOptionWithBSControlVariateChecker.Check;

/**
 * This class tests the implementation of your class.
 * The actual test code is not part of this project.
 * 
 * @author Christian Fries
 */
public class AsianOptionWithBSControlVariateTest {

	@Test
	void testBasicFunctionality() {
		if(!AsianOptionWithBSControlVariateChecker.check(new AsianOptionWithBSControlVariateSolution(), Check.BASIC)) fail();
	}

	@Test
	void testVarianceReductionWeak() {
		if(!AsianOptionWithBSControlVariateChecker.check(new AsianOptionWithBSControlVariateSolution(), Check.WEAK)) fail();
	}

	@Test
	void testVarianceReductionStrong() {
		if(!AsianOptionWithBSControlVariateChecker.check(new AsianOptionWithBSControlVariateSolution(), Check.STRONG)) fail();
	}
	
	@Test
	void testVarianceReductionStronger() {
		if(!AsianOptionWithBSControlVariateChecker.check(new AsianOptionWithBSControlVariateSolution(), Check.STRONGER)) fail();
	}
	
	@Test
	void testVarianceReductionStrongest() {
		if(!AsianOptionWithBSControlVariateChecker.check(new AsianOptionWithBSControlVariateSolution(), Check.STRONGEST)) fail();
	}
}
