import java.time.*;
import java.util.*;

public class QuickSorter{
	public static <E extends Comparable<E>> Duration timedQuickSort(ArrayList<E> list, PivotStrategy strategy) throws NullPointerException{
		if(list.isEmpty() || strategy == null){
			throw new NullPointerException("invalid argument");
		}
		long startTime = System.nanoTime();
		if(strategy == PivotStrategy.FIRST_ELEMENT){
		sortFirst(list, 0, list.size() - 1);
		}
		else if(strategy == PivotStrategy.RANDOM_ELEMENT){
		sortRandom(list, 0, list.size() - 1);
		}
		else if(strategy == PivotStrategy.MEDIAN_OF_THREE_RANDOM_ELEMENTS){
		sortMedianOfRandomThree(list, 0, list.size() - 1);
		}
		else if(strategy == PivotStrategy.MEDIAN_OF_THREE_ELEMENTS){
		sortMedianOfThree(list, 0, list.size() - 1);
		}
		long finishTime = System.nanoTime();
		Duration elapsedTime = Duration.ofNanos(finishTime - startTime);
		return elapsedTime;
		}

	private static <E extends Comparable<E>> void sortFirst(ArrayList<E> list, int low, int high){
		if(low >= high){
			return;
		}
		swap(list, low, high);
		int pivot = quickSort(list, low, high);
		sortFirst(list, low, pivot - 1);
		sortFirst(list, pivot + 1, high);
	}

	private static <E extends Comparable<E>> void sortRandom(ArrayList<E> list, int low, int high){
		if(low >= high){
			return;
		}
		int randomPivot = rand(low, high);
		swap(list, randomPivot, high);
		int pivot = quickSort(list, low, high);
		sortRandom(list, low, pivot - 1);
		sortRandom(list, pivot + 1, high);
	}

	private static <E extends Comparable<E>> void sortMedianOfRandomThree(ArrayList<E> list, int low, int high){
		if(low >= high){
			return;
		}
		int randX = rand(low, high);
		int randY = rand(low, high);
		int randZ = rand(low, high);
		int medianRandomPivot = median(list, randX, randY, randZ);
		if(medianRandomPivot != high){
			swap(list, medianRandomPivot, high);
		}
		int pivot = quickSort(list, low, high);
		sortMedianOfRandomThree(list, low, pivot - 1);
		sortMedianOfRandomThree(list, pivot + 1, high);
	}

	private static <E extends Comparable<E>> void sortMedianOfThree(ArrayList<E> list, int low, int high){
		if(low >= high){
			return;
		}
		int medianThreePivot = median(list, low, high, (low + high) / 2);
		if(medianThreePivot != high){
			swap(list, medianThreePivot, high);
		}
		int pivot = quickSort(list, low, high);
		sortMedianOfThree(list, low, pivot - 1);
		sortMedianOfThree(list, pivot + 1, high);
	}

	private static int rand(int min, int max) throws IllegalArgumentException{
		if(min > max || (max - min + 1 > Integer.MAX_VALUE)){
			throw new IllegalArgumentException("Invalid range");
		}

		return new Random().nextInt(max - min + 1) + min;
	}

	private static <E extends Comparable<E>> int median(ArrayList<E> list, int x, int y, int z){
		if(list.get(x).compareTo(list.get(y)) > 0){
			if(list.get(y).compareTo(list.get(z)) > 0){
				return y;
			}
			else if(list.get(x).compareTo(list.get(z)) > 0){
				return z;
			}
			else{
				return x;
			}
		}
		else{
			if(list.get(x).compareTo(list.get(z)) > 0){
				return x;
			}
			else if(list.get(y).compareTo(list.get(z)) > 0){
				return z;
			}
			else{
				return y;
			}
		}
	}

	public static ArrayList<Integer> generateRandomList(int size){
		ArrayList<Integer> list = new ArrayList<Integer>(size);
		Random random = new Random();
		for(int i = 0; i < size; i++){
			int num = random.nextInt();
			list.add(num);
		}
		return list;
	}

	private static <E extends Comparable<E>> int quickSort(ArrayList<E> list, int low, int high){
		E pivotValue = list.get(high);
		int i = low - 1;
		for(int j = low; j < high; j++){
			if(list.get(j).compareTo(pivotValue) <= 0){
				i++;
				swap(list, i, j);
			}
		}
		swap(list, i + 1, high);
		return i + 1;
	}

	private static <E extends Comparable<E>> void swap(ArrayList<E> list, int i, int j){
		E temp = list.get(i);
		list.set(i, list.get(j));
		list.set(j, temp);
	}

	public static enum PivotStrategy{
		FIRST_ELEMENT,
		RANDOM_ELEMENT,
		MEDIAN_OF_THREE_RANDOM_ELEMENTS,
		MEDIAN_OF_THREE_ELEMENTS
	}

}