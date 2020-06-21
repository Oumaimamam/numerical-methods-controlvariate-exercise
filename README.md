# numerical-methods-controlvariate-exercise

In this exercise you should implement a Monte-Carlo control variate to improve
the convergence of the Monte-Carlo integration by reducing the variance.

A control variate is (usually) product and model dependent. This is a clear
disadvantage. Nevertheless, it can achieve impressive improvements.

In this exercise we consider a Black-Scholes model (as model) and an Asian option
(as product).

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

### Test data

You may test you program with the following data.

	// Model properties
	private final double	initialValue   = 1.0;
	private final double	riskFreeRate   = 0.05;
	private final double	volatility     = 0.30;

	// Process discretization properties
	private final int		numberOfPaths		= 200000;
	private final int		numberOfTimeSteps	= 20;
	private final double	deltaT			= 0.5;

	// Product properties
	private final int		assetIndex = 0;
	private final double	maturity = 10.0;
	private final double	strike = 1.05;
	private final TimeDiscretization timesForAveraging = new TimeDiscretizationFromArray(5.0, 6.0, 7.0, 8.0, 9.0, 10.0);

For this model and product the value of the product is approximately &mu; = 0.3724.
The Monte-Carlo standard deviation is approximately &sigma; = 0.082.
Using 20,000,000 paths, the standard error then is &epsilon; = 0.000183.

### Working in Eclipse

Import this git repository into Eclipse and start working.

- Click on the link to your repository (the link starts with qntlb/numerical-methods… )
- Click on “Clone or download” and copy the URL to your clipboard.
- Go to Eclipse and select File -> Import -> Git -> Projects from Git.
- Select “Clone URI” and paste the GitHub URL from step 2.
- Select “master”, then Next -> Next
- In the Wizard for Project Import select “Import existing Eclipse projects”, then Next -> Finish

