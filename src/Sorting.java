import java.util.ArrayList;



public class Sorting {
	public static void main(String[] args) {
		int[] arr = {8,35,98,	13,	25,	73,	15,	60,	34,	43};
		selectionSort(arr);
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
		int[] arr2 = {1	,27	,77	,30,	99,	53,	11,	52,	48,	31};
		partitionList(arr2, 0, 0);
		for (int i = 0; i < arr2.length; i++) {
			System.out.println(arr2[i]);
		}
	}
	
	
	public static void selectionSort(int[] arr) {
		int n = arr.length; 
		/* TODO ADD CODE HERE FOLLOWING PSEUDOCODE
		 * 
		 * Use a simple for loop to find the smallest value in the desired range
		 * 
		 * To compare two values in dataSet, use something like:
		 *    if (cmp.compare(dataSet.get(j), dataSet.get(sm)) < 0)
		 * To swap, use something like:
		 *    SortingUtils.swapListElements(dataSet, sm, i); 
		 *    assuming that sm and i are indexes within dataSet.
		 */

	    for (int i = 0; i < 5; i++) {
	      int minPos = i;
	      for (int j = i + 1; j < n; j++) {
	        if (arr[j] < arr[minPos]) {
	          minPos = j;
	        }
	      }
	      swapListElements(arr, i, minPos);
	    }
	}


	private static void swapListElements(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	private static void partitionList(int[] data, int first, int last) { 
		int pivot = data[last]; 
		int left = first, right = last-1; 
		
		while (left <= right) {
			while (left <= right && data[left] <= data[pivot])
				left++; 
			while (left <= right && data[left] > data[pivot])
				right--; 
			if (left < right)
				swapListElements(data, left++, right--); 
		}
		swapListElements(data, left, last); 

	}
}
