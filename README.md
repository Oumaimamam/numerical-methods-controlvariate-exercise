# numerical-methods-controlvariate-exercise

In this exercise you should implement a Monte-Carlo control variate to improve
the convergence of the Monte-Carlo integration by reducing the variance.

## Implement an Asian Option valuation with a Control Variate.

Implement a class with the following properties:

- It implements the interface `net.finmath.montecarlo.assetderivativevaluation.products.AssetMonteCarloProduct`.
- It has a constructor taking the argument list `(final double maturity, final double strike, final TimeDiscretization timesForAveraging)`
or `(final Double maturity, final Double strike, final TimeDiscretization timesForAveraging)`.
- The `getValue` method returns the value of the corresponding Asian option using a control variate
for the Black-Scholes model.

The payoff of the Asian option is max(1/n * sum S(T_i)-K,0) paid in T, where T_i are the times in `timesForAveraging`, n is the number of times in `timesForAveraging`, T is `maturity` and K is `strike`.

And most importantly

- The Monte-Carlo valuation uses a control variate to improve the accuracy of the valuation
(in probability, i.e. for most cases) is a Black-Scholes model is used.


## Hints

The valuation method `getValue` takes as argument a model implementing `AssetModelMonteCarloSimulationModel`.
This interface is comparably parsimonious as it only allow get the value of the asset process
and the numeraire.

In order to construct a control variate it may be necessary to get more information about
the `ProcessModel` used to construct the stochastic process.

We will call the `getValue` with a `MonteCarloBlackScholesModel` and you can rely on this. Hence, you can
get the model properties via the following code:

	net.finmath.montecarlo.assetderivativevaluation.models.BlackScholesModel processModel = (BlackScholesModel) ((MonteCarloAssetModel)model).getModel();
	double initialValueOfStock = model.getAssetValue(0, 0).doubleValue();
	double riskFreeRate = processModel.getRiskFreeRate().doubleValue();
	double volatility = processModel.getVolatility().doubleValue();

