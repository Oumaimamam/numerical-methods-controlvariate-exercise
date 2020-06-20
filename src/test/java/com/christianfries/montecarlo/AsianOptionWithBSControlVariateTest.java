package com.christianfries.montecarlo;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import info.quantlab.numericalmethods.lecture.montecarlo.check.AsianOptionWithBSControlVariateChecker;

/**
 * This class tests the implementation of your class.
 * The actual test code is not part of this project.
 * 
 * @author Christian Fries
 */
public class AsianOptionWithBSControlVariateTest {

	@Test
	void testBasicFunctionality() {
		Class<?> classToTest = AsianOptionWithBSControlVariate.class;
		
		System.out.println("Testing " + classToTest.getCanonicalName());
		
		boolean success = AsianOptionWithBSControlVariateChecker.check(classToTest, "basic");

		if(!success) {
			System.out.println("Sorry, the test failed.");
		}
		else {
			System.out.println("Congratulation! You solved the exercise.");
		}

		System.out.println("_".repeat(79));

		if(!success) fail();
	}

	@Test
	void testAccuracy() {
		Class<?> classToTest = AsianOptionWithBSControlVariate.class;
		
		System.out.println("Testing " + classToTest.getCanonicalName());
		
		boolean success = AsianOptionWithBSControlVariateChecker.check(classToTest, "accuracy");

		if(!success) {
			System.out.println("Sorry, the test failed.");
		}
		else {
			System.out.println("Congratulation! You solved the exercise.");
		}

		System.out.println("_".repeat(79));

		if(!success) fail();
	}

	@Test
	void testControlVariate() {
		Class<?> classToTest = AsianOptionWithBSControlVariate.class;
		
		System.out.println("Testing " + classToTest.getCanonicalName());
		
		boolean success = AsianOptionWithBSControlVariateChecker.check(classToTest, "valuation");

		if(!success) {
			System.out.println("Sorry, the test failed.");
		}
		else {
			System.out.println("Congratulation! You solved the exercise.");
		}

		System.out.println("_".repeat(79));

		if(!success) fail();
	}
}
