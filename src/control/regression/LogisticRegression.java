package control.regression;

import org.jblas.DoubleMatrix;

import control.GradientDescent;
import entity.util.HypothesisLogisticRegression;

public class LogisticRegression {

	private HypothesisLogisticRegression hypothesis;
	private DoubleMatrix theta;
	private double alpha;
	private int iterations;

	public LogisticRegression(double alpha, int iterations) {
		this.alpha = alpha;
		this.iterations = iterations;
		this.hypothesis = new HypothesisLogisticRegression();
	}

	public void train(DoubleMatrix X, DoubleMatrix Y) {
		X = DoubleMatrix.concatHorizontally(DoubleMatrix.ones(X.rows, 1), X);
		theta = DoubleMatrix.zeros(X.columns, 1);
		theta = GradientDescent.compute(X, Y, theta, alpha, iterations, hypothesis);
	}

	public DoubleMatrix predict(DoubleMatrix X) {
		X = DoubleMatrix.concatHorizontally(DoubleMatrix.ones(X.rows, 1), X);
		DoubleMatrix Y = new DoubleMatrix(X.rows, 1);
		for (int i = 0; i < X.rows; i++) {
			Y.put(i, hypothesis.compute(X.getRow(i), theta));
		}
		return Y;
	}
}