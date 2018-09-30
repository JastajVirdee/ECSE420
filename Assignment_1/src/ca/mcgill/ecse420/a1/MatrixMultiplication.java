package ca.mcgill.ecse420.a1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MatrixMultiplication {
	
	private static final int NUMBER_THREADS = 1;
	private static final int MATRIX_SIZE = 1000;

    public static void main(String[] args) {
		// Generate two random matrices, same size
		double[][] a = generateRandomMatrix(MATRIX_SIZE, MATRIX_SIZE);
		double[][] b = generateRandomMatrix(MATRIX_SIZE, MATRIX_SIZE);
		/*
		// 2 small arrays to test
		double[][] y = {{1, 2}, {3, 4}};
		double[][] z = {{1, 2}, {3, 4}};
		*/
		sequentialMultiplyMatrix(a, b);
		parallelMultiplyMatrix(a, b);
	}
	
	/**
	 * Returns the result of a sequential matrix multiplication
	 * The two matrices are randomly generated
	 * @param a is the first matrix
	 * @param b is the second matrix
	 * @return the result of the multiplication
	 * */
	public static double[][] sequentialMultiplyMatrix(double[][] a, double[][] b) {
		double[][] c = new double[MATRIX_SIZE][MATRIX_SIZE];
		for (int i = 0; i < MATRIX_SIZE; i++) {
			for (int j = 0; j < MATRIX_SIZE; j++) {
				for (int k = 0; k < MATRIX_SIZE; k++) {
					c[i][j] = c[i][j] + a[i][k] * b[k][j];
				}				
			}
		}
		/*
		for (int i = 0; i < MATRIX_SIZE; i++) {
			for (int j = 0; j < MATRIX_SIZE; j++) {
				System.out.print(c[i][j] + " ");
			}
			System.out.println("");
		}
		*/
		return c;
	}
	
	/**
	 * Returns the result of a concurrent matrix multiplication
	 * The two matrices are randomly generated
	 * @param a is the first matrix
	 * @param b is the second matrix
	 * @return the result of the multiplication
	 * */
    public static double[][] parallelMultiplyMatrix(double[][] a, double[][] b) {
    	double[][] c = new double[MATRIX_SIZE][MATRIX_SIZE];
    	ExecutorService executor = Executors.newFixedThreadPool(NUMBER_THREADS);
    	for (int i = 0; i < MATRIX_SIZE; i++) {
    		executor.execute(new OneRowMultiplication(a, b, c, i, MATRIX_SIZE));
    	}
    	executor.shutdown();
    	/*
    	for (int i = 0; i < MATRIX_SIZE; i++) {
			for (int j = 0; j < MATRIX_SIZE; j++) {
				System.out.print(c[i][j] + " ");
			}
			System.out.println("");
		}
		*/
    	return c;		
	}
        
    /**
     * Populates a matrix of given size with randomly generated integers between 0-10.
     * @param numRows number of rows
     * @param numCols number of cols
     * @return matrix
     */
    private static double[][] generateRandomMatrix (int numRows, int numCols) {
        double matrix[][] = new double[numRows][numCols];
        for (int row = 0 ; row < numRows ; row++ ) {
            for (int col = 0 ; col < numCols ; col++ ) {
                matrix[row][col] = (double) ((int) (Math.random() * 10.0));
            }
        }
        return matrix;
    }
	
}

class OneRowMultiplication implements Runnable {
	private double[][] a, b, c;
	private int i, MATRIX_SIZE;
	public OneRowMultiplication(double[][] m1, double[][] m2, double[][] m3, 
			int index, int m_size) {
		a = m1; b = m2; c = m3;
		i = index; MATRIX_SIZE = m_size;
	}
	@Override
	public void run() {
		for (int j = 0; j < MATRIX_SIZE; j++) {
			for (int k = 0; k < MATRIX_SIZE; k++) {
				c[i][j] = c[i][j] + a[i][k] * b[k][j];
			}				
		}		
	}
}
