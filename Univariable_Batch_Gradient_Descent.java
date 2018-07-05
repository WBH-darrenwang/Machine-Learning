/*
 * Finding the linear regression of one set of X and Y data using gradient descent (No user input)
 * @author wang
 * Credits to Andrew Ng
 * Link: https://see.stanford.edu/materials/aimlcs229/cs229-notes1.pdf
 */
public class Univariable_Batch_Gradient_Descent{
	
	public static void main(String[] args) {
		
		//X set of data
		double[] datax = { 1,2,3,4,5 };
		//Y set of data
		double[] datay = { 0.3,2.6,3.1,3.85,5.72 };
		//A training set would be (x,y) -> corresponding x with corresponding y -> e.g (1,0.3),(2,2.6)
		
		/*
		 * In a linear function, the standard formula of a line is y=mx+b where m and b are called weights or parameters
		 * 		-> m is the slope of the line (rise over run)
		 * 		-> b is the y-intercept (where the line intersect the y-axis) 
		 * 		-> x is the x value that you input form the data
		 * 		-> y is the value that comes out after multiplying x by m and adding b to it
		 * There can be an infinite amount of x categories. For example:
		 * 		-> If you are trying to find the price of a house given its living area, x1 would be the living area and y
		 * 		   would be the price. However, you can add other factors that may affect the price including # of bedrooms (x2),
		 * 		   whether each house has a fireplace (x3), etc. 
		 * 		-> In a more general format, the equation for the linear line of multiple x's is the summation from 0 to 
		 * 		   the number of # of x's where the i'th theta is multiplied to the i'th x.
		 * 
		 * However, for simplicity, this code is only for when there is one set of X data.
		 * theta0 correlates to b
		 * theta1 correlates to m
		 * 
		 * theta0 and theta1 both are set to 0 but can be set to any number
		 * y = 0x + 0
		 * The computer starts off with a random guess with no knowledge of the data and, in this case, draws a 
		 * horizontal line at the x-axis.
		 */
		
		double theta0 = 0.0, theta1 = 0.0;
		
		/*
		 * After the line y = 0x + 0 is drawn, the error needs to be measured and this is done by taking the difference 
		 * of the predicted y and the actual y value. The predicted y value can be found by plugging the x data into the 
		 * y = mx + b with the current weights. The difference is then squared and divided by 2. This is done to every single 
		 * training set and the sum of all these are taken.
		 * 
		 * The LMS algorithm allows us to update the thetas in order to find the line of best fit. It states that 
		 * the current weight is updated by taking the difference of itself and the derivative of the corresponding cost 
		 * function.
		 * 
		 * By using partial derivatives, it turns out that the derivative of the cost function in respect to b (in y = mx + b)
		 * is (predicted y - actual y). The derivative allows us to focus on the slope of the cost function. By using the chain
		 * rule and power rule, the derivative can be found.
		 *
		 * The derivative of the cost function in respect to m is (predicted y - actual y) * corresponding x data.  
		 * 
		 * sum0 and sum1 correlates to the sum when inputed into the derivative of the cost function
		 * 
		 */
		
		double sum0, sum1;
		
		/*
		 * The learning rate is the rate that you take in gradient descent
		 * Greater the learning rate, the bigger steps you take in the J(0), or the cost function.
		 * 		-> Too big of a step can overshoot the minimum and cause failure to converge to the global minimum
		 * Smaller  the learning rate, the smaller steps you take in the cost function
		 * 		_> Too small of a step can result in a slow convergence to global minimum (more iterations) and in companies
		 * 		   may cost more money 
		 * 
		 * There are formulas to find the best fit learning rate however, for simplicity and the goal of learning
		 * gradient descent, we are not going to implement it.
		 * 
		 * One way to see if the learning rate is efficient is to graph it with x-axis the # of iterations and the y-axis
		 * the cost. An ideal learning rate would create a graph where it looks like something like y = 1/x
		 * 
		 * For now, this can be manually changed. If the result weights are way larger than your data, you should lower the
		 * learning rate. If you feel the learning rate is low enough and the results don't look correct, you should 
		 * increase the number of iterations.
		 */
		double learning_rate = 0.01;
		
		//# of training sets
		int size = datay.length;
		
		//The first for loop controls the number of iterations
		for (int j = 0; j < 1000; j++) {
			sum0 = 0;
			sum1 = 0;
			//If you want, you can implement your learning rate formula here
			
			//Using the formula derived by taking the partial derivative of the cost function
			for(int i = 0; i < size; i++) {
				sum0 += linear_regression(datax[i],theta0,theta1) - datay[i];
				sum1 += (linear_regression(datax[i],theta0,theta1) - datay[i]) * datax[i];
			}
			//Applying the LMS algorithm
			theta0 = theta0 - (learning_rate * sum0 );
			theta1 = theta1 - (learning_rate * sum1 );
			
			//Prints out the answer to each iteration
			System.out.println("y = " + theta0 + " + " + theta1 + "x");
		}
		
		//Rounding to nearest thousandth place
		double round_theta0 = Math.round(theta0 * 1000.0) / 1000.0;
		double round_theta1 = Math.round(theta1 * 1000.0) / 1000.0;
		
		//Prints out the final answer
		System.out.println("Final: y = " + round_theta0 + " + " + round_theta1 + "x");
	}
	
	// y = mx +b 
	private static double linear_regression(double x, double theta0, double theta1) {
		return (theta1 * x) + theta0;
	}
}
