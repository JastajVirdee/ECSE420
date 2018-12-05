package ca.mcgill.ecse420.a3;

public class MatrixVectorMultTest {

	private static final int NUMBER_THREADS = 3;
	private static final int MATRIX_SIZE = 2000;
	
	public static void main(String[] args) {
		double[][] matrix = generateRandomMatrix(MATRIX_SIZE, MATRIX_SIZE);
		double[] vector = generateRandomVector(MATRIX_SIZE);
		
		SeqMatrixVectorMult seq = new SeqMatrixVectorMult(matrix, vector, MATRIX_SIZE);
		double start = System.currentTimeMillis();
		seq.sequentialMultiplication();
		double end = System.currentTimeMillis();
		System.out.println("Sequential: " + (end-start)/1000 + "s");
		
		ParMatrixVectorMult par = new ParMatrixVectorMult(matrix, vector, MATRIX_SIZE, NUMBER_THREADS);
		start = System.currentTimeMillis();
		par.parallelMultiplication();
		end = System.currentTimeMillis();
		System.out.println("Parallel: " + (end-start)/1000 + "s");
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
    
    /**
     * Populates a vector of given size with randomly generated integers between 0-10.
     * @param numCols number of cols
     * @return vector
     */
    private static double[] generateRandomVector (int numCols) {
        double vector[] = new double[numCols];
        for (int col = 0 ; col < numCols ; col++ ) {
            vector[col] = (double) ((int) (Math.random() * 10.0));
        }
        return vector;
    }
}
