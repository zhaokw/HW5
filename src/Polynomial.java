public class Polynomial {

	public int[] coefficients;
	
	public Polynomial(int[] coefficients) {
	
		this.coefficients = coefficients;
	}
	
	public int[] multiply(int[] f1, int[] f2) {
		int[] product = new int[2 * f1.length];
		
		//base case
		if (f1.length == 1) {
			product[0] = f1[0] * f2[0];
			return product;
		}
		
		int halfLength = f1.length / 2;

		//Cut two arrays into four
		int[] f1Low = new int[halfLength];
		int[] f1High = new int[halfLength];
		int[] f2Low = new int[halfLength];
		int[] f2High = new int[halfLength];
		int[] f1Con = new int[halfLength];
		int[] f2con = new int[halfLength];
		for (int i = 0; i < halfLength; ++i) {
			f1Low[i] = f1[i];
			f1High[i] = f1[i + halfLength];
			f1Con[i] = f1Low[i] + f1High[i];
			
			f2Low[i] = f2[i];
			f2High[i] = f2[i + halfLength];
			f2con[i] = f2Low[i] + f2High[i];
		}
		
		//Divide
		int[] p1 = multiply(f1Low, f2Low);
		int[] p2 = multiply(f1Con, f2con);
		int[] p3 = multiply(f1High, f2High);
		
		//Conquer & build up 
		int[] productMiddle = new int[f1.length];
		for (int i = 0; i < f1.length; ++i) {
			productMiddle[i] = p2[i] - p1[i] - p3[i];
		}
		for (int i = 0; i < f1.length; ++i) {
			product[i] += p1[i];
			product[i + f1.length] += p3[i];
			product[i + halfLength] += productMiddle[i];
		}
		return product;
	}
	

	
}