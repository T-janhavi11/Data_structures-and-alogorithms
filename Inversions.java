import java.util.Scanner;

public class Inversions {
	private static class Data {
		private int[] array = {};
		private long count = 0;

		private Data() {}

		private Data(int arrayLength, long count) {
			this.array = new int[arrayLength];
			this.count = count;
		}
	}

	private static Data mergeSort(int[] arr) {
		return mergeSort(arr, 0, arr.length - 1);
	}

	private static Data mergeSort(int[] arr, int left, int right) {
		Data result = new Data();

		if (left == right) {
			result.array = new int[1];
			result.array[0] = arr[left];
			return result;
		}

		int mid = (right + left) / 2;

		Data leftHalf = mergeSort(arr, left, mid);
		int[] leftArr = leftHalf.array;
		result.count += leftHalf.count;

		Data rightHalf = mergeSort(arr, mid + 1, right);
		int[] rightArr = rightHalf.array;
		result.count += rightHalf.count;

		Data merged = merge(leftArr, rightArr);
		result.array = merged.array;
		result.count += merged.count;

		return result;
	}

	private static Data merge(int[] a, int[] b) {
		Data data = new Data(a.length + b.length, 0);

		int i = 0, j = 0, k = 0;
		while (true) {
			if (a[i] < b[j]) {
				data.array[k] = a[i];
				i++;
				k++;
			}
			else if (a[i] > b[j]) {
				data.count += a.length - i;
				data.array[k] = b[j];
				j++;
				k++;
			}
			else if (a[i] == b[j]) {
				data.array[k] = a[i];
				i++;
				k++;
			}

			if (i == a.length) {
				while (j < b.length) {
					data.array[k] = b[j];
					j++;
					k++;
				}
				return data;
			}

			if (j == b.length) {
				while (i < a.length) {
					data.array[k] = a[i];
					i++;
					k++;
				}
				return data;
			}
		}
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int[] array = new int[input.nextInt()];

		for (int i = 0; i < array.length; i++) {
			array[i] = input.nextInt();
		}
		System.out.println(mergeSort(array).count);
	}
}