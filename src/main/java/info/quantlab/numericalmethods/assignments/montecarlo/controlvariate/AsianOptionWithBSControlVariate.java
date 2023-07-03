package info.quantlab.numericalmethods.assignments.montecarlo.controlvariate;

import net.finmath.exception.CalculationException;
import net.finmath.montecarlo.assetderivativevaluation.AssetModelMonteCarloSimulationModel;
import net.finmath.montecarlo.assetderivativevaluation.products.AssetMonteCarloProduct;
import net.finmath.stochastic.RandomVariable;
import net.finmath.time.TimeDiscretization;

/**
 * Implements the valuation of an Asian option.
 *
 * Given a model for an asset <i>S</i>, the Asian option with strike <i>K</i>, maturity <i>T</i>
 * and averaging points <i>T<sub>i</sub></i> for <i>i = 1,...,n</i> pays
 * <br>
 * 	<i>max(sign (A(T) - K) , 0)</i> in <i>T</i>
 * <br>
 * where
 * <br>
 * 	<i>A(T) = 1/n (S(T<sub>1</sub>)+ ... + S(T<sub>n</sub>))</i>
 * <br>
 * and sign is (usually) either +1 or -1. For +1 this is a call option max(A(T)-K,0). For -1 this is a put option with payoff max(K-A(T),0).
 *
 * @author Christian Fries
 * @version 1.2
 */
public class AsianOptionWithBSControlVariate implements AssetMonteCarloProduct {

	private final double maturity;
	private final double strike;
	private final TimeDiscretization timesForAveraging;
	private final Integer underlyingIndex;
	private final Double callOrPutSign;


	/**
	 * Construct a product representing an Asian option on an asset S (where S the asset with index 0 from the model - single asset case).
	 * A(T) = 1/n sum_{i=1,...,n} S(t_i), where t_i are given observation times.
	 *
	 * @param strike The strike K in the option payoff max( sign * (A(T)-K), 0)
	 * @param maturity The maturity T in the option payoff max( sign * (A(T)-K), 0)
	 * @param timesForAveraging The times t_i used in the calculation of A(T) = 1/n sum_{i=1,...,n} S(t_i).
	 * @param underlyingIndex The index of the asset S to be fetched from the model
	 * @param callOrPutSign The parameter sign in the payoff max( sign * (A(T)-K), 0) (usually either +1 or -1).
	 */
	public AsianOptionWithBSControlVariate(final Double maturity, final Double strike, final TimeDiscretization timesForAveraging, final Integer underlyingIndex, Double callOrPutSign) {
		super();
		this.maturity = maturity;
		this.strike = strike;
		this.timesForAveraging = timesForAveraging;
		this.underlyingIndex = underlyingIndex;
		this.callOrPutSign = callOrPutSign;
	}

	/**
	 * Construct a product representing an Asian option on an asset S (where S the asset with index 0 from the model - single asset case).
	 * A(T) = 1/n sum_{i=1,...,n} S(t_i), where t_i are given observation times.
	 *
	 * @param strike The strike K in the option payoff max( sign * (A(T)-K), 0)
	 * @param maturity The maturity T in the option payoff max( sign * (A(T)-K), 0)
	 * @param timesForAveraging The times t_i used in the calculation of A(T) = 1/n sum_{i=1,...,n} S(t_i).
	 * @param callOrPutSign The parameter sign in the payoff max( sign * (A(T)-K), 0) (usually either +1 or -1).
	 */
	public AsianOptionWithBSControlVariate(final Double maturity, final Double strike, final TimeDiscretization timesForAveraging, Double callOrPutSign) {
		this(maturity, strike, timesForAveraging, 0, callOrPutSign);
	}

	/**
	 * This method returns the value random variable of the product within the specified model, evaluated at a given evalutationTime.
	 * Note: For a lattice this is often the value conditional to evalutationTime, for a Monte-Carlo simulation this is the (sum of) value discounted to evaluation time.
	 * Cashflows prior evaluationTime are not considered.
	 *
	 * @param evaluationTime The time on which this products value should be observed.
	 * @param model The model used to price the product.
	 * @return The random variable representing the value of the product discounted to evaluation time
	 * @throws net.finmath.exception.CalculationException Thrown if the valuation fails, specific cause may be available via the <code>cause()</code> method.
	 */
	@Override
	public RandomVariable getValue(final double evaluationTime, final AssetModelMonteCarloSimulationModel model) throws CalculationException {

		// Inital values to zero
		RandomVariable values = model.getRandomVariableForConstant(0.0);

		

		// Part 1: add code for the valuation of the Asian option here

		
		

		// Discounting...
		final RandomVariable numeraireAtMaturity	= model.getNumeraire(maturity);
		final RandomVariable monteCarloWeights		= model.getMonteCarloWeights(maturity);
		values = values.div(numeraireAtMaturity).mult(monteCarloWeights);

		// ...to evaluation time.
		final RandomVariable	numeraireAtEvalTime					= model.getNumeraire(evaluationTime);
		final RandomVariable	monteCarloProbabilitiesAtEvalTime	= model.getMonteCarloWeights(evaluationTime);
		values = values.mult(numeraireAtEvalTime).div(monteCarloProbabilitiesAtEvalTime);
		
		/*
		 * At this point, the RandomVariable values should
		 * contain the path-wise discounted values (the payoff multiplied
		 * with the numeraire ratio N(t)/N(T)), such that taking the expectation
		 * would give you the value of the option.
		 * 
		 * But don't take the expectation here
		 * (it will be done outside of the method).
		 * 
		 * Now work on the second part.
		 * 
		 * Part 2: add a control variate of the Asian option here
		 */
		
		RandomVariable valuesControled = values;
		
		
		return valuesControled;
	}
}

