# numerical-methods-controlvariate-exercise

In this exercise you should implement a Monte-Carlo control variate to improve
the convergence of the Monte-Carlo integration by reducing the variance.

A control variate is (usually) product and model dependent. This is a clear
disadvantage of the method. Nevertheless, it can achieve impressive improvements.

In this exercise we consider a Black-Scholes model (as model) and an Asian option
(as product).

## Implement an Asian Option valuation under the Black-Scholes Model with a Control Variate.

Implement a class with the following properties:

- It implements the interface `net.finmath.montecarlo.assetderivativevaluation.products.AssetMonteCarloProduct`.
- It has a constructor taking the argument list `(final double maturity, final double strike, final TimeDiscretization timesForAveraging)`
or `(final Double maturity, final Double strike, final TimeDiscretization timesForAveraging)`.
- The `getValue` method returns the value of the corresponding Asian option using a control variate
for the Black-Scholes model.

The payoff of the Asian option is max(1/n * sum S(T_i)-K,0) paid in T, where T_i are the times in `timesForAveraging`, n is the number of times in `timesForAveraging`, T is `maturity` and K is `strike`.

And most importantly

- The Monte-Carlo valuation uses a control variate to improve the accuracy of the valuation
(in probability, i.e. for most cases) if a Black-Scholes model is used.

## Submission of the Solution

You may just complete the stub implementation provided in the repository in

```
info.quantlab.numericalmethods.assignments.montecarlo.controlvariate.AsianOptionWithBSControlVariate
```

Alternatively, if you provide your own implementation of a class implementing `AssetMonteCarloProduct` to value an Asian option, you may just return an object of your class in the method `getAsianOption` of `AsianOptionWithBSControlVariateSolution`. Remark: Our unit test will call this method to test your implementation.

## Hints

### Getting the parameters of the underlying Black-Scholes model (the `ProcessModel`)

The valuation method `getValue` takes as argument a model implementing `AssetModelMonteCarloSimulationModel`.
This interface is comparably parsimonious as it only allows to get the value of the asset process S
and the numeraire N (and some information on thes simulation time discretization).
At this point the model of S may be almost anything.

In order to construct a control variate it may be necessary to get more information about
the `ProcessModel` used to construct the stochastic process.

When we test your implementation, we will call the `getValue` with a `MonteCarloAssetModel` and calling `getModel()` on this object will return a `BlackScholesModel`. You can rely on this to obtain the model parameters we used in the test (but you could
implement an Exception handling if we don't do it). Hence, you can get the model properties via the following code:

```
	net.finmath.montecarlo.assetderivativevaluation.models.BlackScholesModel processModel = (BlackScholesModel) ((MonteCarloAssetModel)model).getModel();
	double initialValueOfStock = model.getAssetValue(0, 0).doubleValue();
	double riskFreeRate = processModel.getRiskFreeRate().doubleValue();
	double volatility = processModel.getVolatility().doubleValue();
```

Note (technical detail): Since the library allows to create object implementing `AssetModelMonteCarloSimulationModel` in different ways, a slightly more robust way of getting the underlying model is to use the utility function

```
info.quantlab.numericalmethods.lecture.montecarlo.models.Utils.getBlackScholesModelFromMonteCarloModel
```

So you may get the underlying `BlackScholesModel` via

```
	// Get model parameters - making strong assumption on the model
	net.finmath.montecarlo.assetderivativevaluation.models.BlackScholesModel processModel = info.quantlab.numericalmethods.lecture.montecarlo.models.Utils.getBlackScholesModelFromMonteCarloModel(model);
```

### Test data

You may test you program with the following data.

```
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
```

For this model and product the value of the product is approximately &mu; = 0.372.
The Monte-Carlo standard deviation is approximately &sigma; = 0.74.
Using 200,000 paths, the standard error then is &epsilon; = 0.00165.

Using control variates it is possible to bring the standard error below 0.0009 (comparably easy) and even below 0.0001 (a bit more difficult). This would correspond to using 200-times more Monte-Carlo simulation times (requiring 200-times the computation time).

## Unit Tests and GitHub Autograding

The project comes with a unit test that runs four test

- basic: (5 Points) Passes if the valuation of the Asian option appears to be OK (no variance reduction required).
- weak: (5 Points) Passes if at least some variance reduction is performed.
- strong: (5 Points) Passes if good variance reduction is performed.
- stronger: (5 Points) Passes if very good variance reduction is performed.
- strongest: (2 Points) Passes if extremely good variance reduction is performed.

*Note: You may consider the exercise solved if you achieve 15 or 20 points, since this is already a good variance reduction. However, the autograding will show a failure unless you reach the full 22 points.*

## Working in Eclipse

Import this git repository into Eclipse and start working.

- Click on the link to your repository (the link starts with qntlb/numerical-methods… )
- Click on “Clone or download” and copy the URL to your clipboard.
- Go to Eclipse and select File -> Import -> Git -> Projects from Git.
- Select “Clone URI” and paste the GitHub URL from step 2.
- Select “master”, then Next -> Next
- In the Wizard for Project Import select “Import existing Eclipse projects”, then Next -> Finish

