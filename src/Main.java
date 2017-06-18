import java.util.Scanner;

public class Main {	
	
 public static int[] multiply(int[] f1, int[] f2) {
		int[] product = new int[2 * f1.length];
		
		//base case
		if (f1.length<=8) {
			for(int i=0;i<f1.length;i++){
				for(int j=0;j<f2.length;j++){
					product[i+j]+=f1[i]*f2[j];
				}
			}
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
		for (int i = 0; i < halfLength; i++) {
			f1Low[i] = f1[i];
			f1High[i] = f1[i + halfLength];
			f1Con[i] = f1Low[i] + f1High[i];
			
			f2Low[i] = f2[i];
			f2High[i] = f2[i + halfLength];
			f2con[i] = f2Low[i] + f2High[i];
		}
		
		//Divide
		int[] p1 = multiply(f1Low, f2Low);
		int[] p3 = multiply(f1High, f2High);
		int[] p2 = multiply(f1Con, f2con);
		
		//Conquer & build up 
		int[] productMiddle = new int[f1.length];
		for (int i = 0; i < f1.length; i++) {
			productMiddle[i] = p2[i] - p1[i] - p3[i];
		}
		for (int i = 0; i < f1.length; i++) {
			product[i] += p1[i];
			product[i + f1.length] += p3[i];
			product[i + halfLength] += productMiddle[i];
		}
		return product;
	}	
	
 public static void main(String[] args) throws Exception{
	 int[] c1,c2,c3;
	 Scanner s = new Scanner(System.in);
	 
	 String l=s.nextLine();
	 int index=Integer.parseInt(l);
	 c1=new int[index+1];
	 c2=new int[index+1];
	 
	 l=s.nextLine();
	 for(int i=0;i<l.length();i+=2){
		 int d=Integer.parseInt(l.substring(i, i+1));
		 c1[i/2]=d;
	 }
	 l=s.nextLine();
	 for(int i=0;i<l.length();i+=2){
		 int d=Integer.parseInt(l.substring(i, i+1));
		 c2[i/2]=d;
	 }
	 
	 c3=multiply(c1, c2);
	 for(int i=0;i<c3.length-1;i++)
		 System.out.print(c3[i]+" ");
 }
}
