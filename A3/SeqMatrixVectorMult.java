package ca.mcgill.ecse420.a1;
public class SeqMatrixVectorMult {

	public static double[][] matrix;
	public static double[] vector;
	public static  int MATRIX_SIZE;
	
	public SeqMatrixVectorMult(double[][] matrix, double[] vector, int MATRIX_SIZE){
		this.matrix = matrix;
		this.vector = vector;
		this.MATRIX_SIZE = MATRIX_SIZE;
	}
	   
    /**
	 * @return the result of the multiplication
	 * */
	public static double[] sequentialMultiplication() {
		double[] c = new double[MATRIX_SIZE];
		for (int i = 0; i < MATRIX_SIZE; i++) {
			for (int j = 0; j < MATRIX_SIZE; j++) {
				c[i] = c[i] + matrix[i][j] * vector[j];
			}
		}
    	/*
		// if you want to print the result
		for (int i = 0; i < MATRIX_SIZE; i++) {
			System.out.print(c[i] + " ");
		}
		System.out.println("");
		*/
		return c;
	}
}
