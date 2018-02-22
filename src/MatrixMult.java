public class MatrixMult
{
	public static void printArray(int arr[][])
	{
		for(int i=0;i<arr.length;i++){
			for(int j=0;j<arr[0].length;j++){
				System.out.print(arr[i][j]+"  ");
			}
			System.out.println();
		}
	}
	public static void RandArray(int arr[][])
	{
		for(int i=0;i<arr.length;i++){
			for(int j=0;j<arr[0].length;j++){
				arr[i][j]= ((int) Math.random()*10+1);
			}
		}
	}
	public void multiply(int a[][], int b[][], int c[][]){
		for(int i=0;i<a.length;i++){
			for(int j=0;j<a.length;j++){
				c[i][j]=0;
				for(int k=0;k<a.length;k++){
					c[i][j]+=a[i][k]*b[k][j];
				}
			}
		}
	}
	public static void main(String args[]){
		int num = Integer.parseInt(args[0]);
		int arr1[][]= new int [num][num];
		int arr2[][]= new int [num][num];
		int arr3[][]= new int [num][num];
		MatrixMult matrix = new MatrixMult();
		RandArray(arr1);
		RandArray(arr2);
		//System.out.println("Input Matrices:");
		//printArray(arr1);
		//System.out.println();
		//printArray(arr2);
		long StartTime = System.nanoTime();
		matrix.multiply(arr1,arr2,arr3);
		//System.out.println("Resultant Matrix After Multiplication:"+"\n");
		//printArray(arr3);
		long EndTime = System.nanoTime();
		long time = EndTime- StartTime;
		System.out.println("Execution Time:"+time+" ns"+"\n");
	}
}
