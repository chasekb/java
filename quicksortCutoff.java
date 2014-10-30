package quicksortCutoff;

import java.util.Arrays;
import java.util.Random;

public class quicksortCutoff
{
	public static boolean quick;
	public static boolean insert;
	private static Random rand = new Random();
	
	public static void quicksortCutoff(int arr[], int cutoff, int left, int right)
	{
		quick = false;
		insert = false;
		
		if (arr.length > cutoff)
		{
			quickSort(arr, left, right);
			quick = true;
		}
		else
		{
			insertionSort(arr);
			insert = true;
		}
	}
	
	private static int partition(int arr[], int left, int right)
	{
	      int i = left, j = right;
	      int tmp;
	      int pivot = arr[(left + right) / 2];
	     
	      while (i <= j)
	      {
	            while (arr[i] < pivot)
	                  i++;
	            while (arr[j] > pivot)
	                  j--;
	            if (i <= j)
	            {
	                  tmp = arr[i];
	                  arr[i] = arr[j];
	                  arr[j] = tmp;
	                  i++;
	                  j--;
	            }
	      };
	     
	      return i;
	}
	
	private static int randomPartition(int arr[], int left, int right)
	{
		int i = rand.nextInt((right - left) + 1) + left;
		int tmp;
		
		tmp = arr[right];
		arr[right] = arr[i];
		arr[i] = tmp;
		return partition(arr, left, right);
	}
	 
	private static void quickSort(int arr[], int left, int right)
	{
	    int index = randomPartition(arr, left, right);
	    if (left < index - 1)
	    	quickSort(arr, left, index - 1);
	    if (index < right)
	    	quickSort(arr, index, right);
	}
	
	private static void insertionSort(int[] arr)
	{
	      int i, j, newValue;
	      for (i = 1; i < arr.length; i++)
	      {
	            newValue = arr[i];
	            j = i;
	            while (j > 0 && arr[j - 1] > newValue)
	            {
	                  arr[j] = arr[j - 1];
	                  j--;
	            }
	            arr[j] = newValue;
	      }
	}

	public static void main(String[] args)
	{
		// check quicksort
		Random random = new Random();
		int maxArrayLength = 100;
		int cutOff = 10;
		int array[] = new int[maxArrayLength];
		
		// generate array of maxArrayLength
		// filled with ints
		for (int i = 0; i < maxArrayLength; i++)
			array[i] = random.nextInt(array.length);

		System.out.println(Arrays.toString(array));
		
		// call quicksortCutoff on array
		quicksortCutoff(array, cutOff, 0, array.length - 1);
		System.out.println(Arrays.toString(array));
		System.out.println("array length:   " + array.length);
		System.out.println("cutoff:         " + cutOff);
		System.out.println("quicksort:      " + quicksortCutoff.quick);
		System.out.println("insertion sort: " + quicksortCutoff.insert);

		System.out.println("\n");
		
		// check insertion sort
		Random random2 = new Random();
		int maxArrayLength2 = 100;
		int cutOff2 = 1000;
		int array2[] = new int[maxArrayLength2];
		
		// generate array of maxArrayLength
		// filled with ints
		for (int i = 0; i < maxArrayLength2; i++)
			array2[i] = random2.nextInt(array2.length);

		System.out.println(Arrays.toString(array2));
		
		// call quicksortCutoff on array
		quicksortCutoff(array2, cutOff2, 0, array.length - 1);
		System.out.println(Arrays.toString(array2));
		System.out.println("array length:   " + array2.length);
		System.out.println("cutoff:         " + cutOff2);
		System.out.println("quicksort:      " + quicksortCutoff.quick);
		System.out.println("insertion sort: " + quicksortCutoff.insert);
	}
}
