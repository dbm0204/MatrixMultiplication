public class Strassen
{	public static void printArray(int [][] arr){
		for(int i=0;i<arr.length;i++){
			for(int j=0;j<arr[i].length;j++){
				System.out.print(arr[i][j]);
			}
			System.out.println();
		}
		System.out.println("\n");
	}
	public static void randArray(int [][] arr){
		for(int i=0;i<arr.length;i++){
			for(int j=0;j<arr[i].length;j++){
				arr[i][j]= (int)Math.random()*20+1;
			}
		}
	}
	public static int[][] sub(int [][]a, int[][]b)
	{
		int n = a.length;
		int [][]c= new int[n][n];
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				c[i][j]= a[i][j]-b[i][j];
			}
		}
		return c;
	}
	public static int[][] add(int [][]a, int[][]b){
		int n= a.length;
		int [][] c = new int[n][n];
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				c[i][j]= a[i][j]+b[i][j];
			}
		}
		return c;
	}
	public static void split(int [][] p, int [][]c, int iB, int jB)
	{
		for(int i1 = 0, i2 = iB; i1 < c.length; i1++, i2++){ 
			for(int j1 = 0, j2 = jB; j1 < c.length; j1++, j2++){ 
				c[i1][j1] = p[i2][j2]; 
			}
		}
	}
	 public static void join(int[][] c, int[][] p, int iB, int jB) 
	{ 
		for(int i1 = 0, i2 = iB; i1 < c.length; i1++, i2++){
			 for(int j1 = 0, j2 = jB; j1 < c.length; j1++, j2++){
				 p[i2][j2] = c[i1][j1]; 
			}
		}
	} 	 
	public static int[][] multiply(int [][] a, int  [][] b)
	{
		int n= a.length;
		int [][] result = new int [n][n];
		if(n==1)
			result[0][0]= a[0][0]*b[0][0];
		else {
			int [][] a11 = new int [n/2][n/2];
			int [][] a12 = new int [n/2][n/2];
			int [][] a21 = new int [n/2][n/2];
			int [][] a22 = new int [n/2][n/2];
			int [][] b11 = new int [n/2][n/2];
			int [][] b12 = new int [n/2][n/2];
			int [][] b21 = new int [n/2][n/2];
			int [][] b22 = new int [n/2][n/2];	
			// Split the matrix A into 4 halves
			split(a,a11,0,0);
			split(a,a12,0,n/2);
			split(a,a21,n/2,0);
			split(a,a22,n/2,n/2);
			// Split the matrix B into 4 Halves
			split(b, b11, 0,0);
			split(b, b12,0,n/2);
		 	split(b, b21,n/2,0);
			split(b, b22, n/2,n/2);
			
			int [][] m1 = multiply(add(a11,a22), add(b11,b22));	
			int [][] m2 = multiply(add(a21, a22), b11);
			int [][] m3 = multiply(a11,sub(b21,b11));
			int [][] m4 = multiply(a22,sub(b21,b11));
			int [][] m5 = multiply(add(a11,a12), b22);
			int [][] m6 = multiply(sub(a21,a11), add(b11,b21));
			int [][] m7 = multiply(sub(a12,a22), add(b21,b22));
			int [][] c11 = add(sub(add(m1,m4),m5),m7);
			int [][] c12 = add(m3, m5);
			int [][] c21 = add(m2,m4);
			int [][] c22 = add(sub(add(m1,m3),m2),m6);
			
            // Join the 4 halves into one resultant Matrix
            join(c11,result,0,0);
			join(c12,result,0,n/2);
			join(c21,result,n/2,0);
			join(c22,result,n/2,n/2);
		}
		return result;
	}
	public static void main(String args[])
	{
		int num = Integer.parseInt(args[0]);
		int [][] arr1 = new int [num][num];
		int [][] arr2 = new int [num][num];
		int [][] result = new int [num][num];
		randArray(arr1);
		randArray(arr2);
		//System.out.println("The Input Matrices are:");
		//printArray(arr1);
		//printArray(arr2);
		Strassen matrix = new Strassen();
		long StartTime = System.nanoTime();
		result= matrix.multiply(arr1, arr2);
		long EndTime = System.nanoTime();
		long time = EndTime - StartTime;
		System.out.println();
		//System.out.println("Output Matrices:");
		//printArray(result);
		System.out.println("Execution Time:"+time);
	}
}
