import java.util.*;

public class PointsAndSegments {
	private static class Pair {
		private int point, label, count;

		private Pair(int point, int label) {
			this.point = point;
			this.label = label;
		}

		private Pair(int point, int label, int count) {
			this.point = point;
			this.label = label;
			this.count = count;
		}
	}

	private static Pair[] labelPoints(int[] starts, int[] ends, int[] points) {
		int s = starts.length;
		int p = points.length;
		Pair[] labeledPoints = new Pair[2 * s + p];
		int j = 0;

		for (int i = 0; i < s; i++) {
			labeledPoints[j] = new Pair(starts[i], 1);
			j++;
			labeledPoints[j] = new Pair(ends[i], 3);
			j++;
		}

		for (int i = 0; i < p; i++) {
			labeledPoints[j] = new Pair(points[i], 2);
			j++;
		}
		return labeledPoints;
	}

	private static Pair[] getCounts(Pair[] labeledPoints, int numPoints) {
		Pair[] counts = new Pair[numPoints];
		int j = 0;
		int startCount = 0;
		int endCount = 0;

		for (int i = 0; i < labeledPoints.length; i++) {
			if (labeledPoints[i].label == 1) {
				startCount++;
			}
			else if (labeledPoints[i].label == 3) {
				endCount++;
			}
			else if (labeledPoints[i].label == 2) {
				counts[j] = new Pair(labeledPoints[i].point, 0, startCount - endCount);
				j++;
			}
		}
		return counts;
	}

	private static void printResult(Pair[] counts, int[] points) {
		for (int i = 0; i < points.length; i++) {
			for (int j = 0; j < points.length; j++) {
				if (points[i] == counts[j].point) {
					System.out.print(counts[j].count + " ");
					break;
				}
			}
		}
	}

	private static void quickSort(Pair[] arr, int left, int right) {
		if (left >= right) {
			return;
		}
		int[] partitionIndices = partition(arr, left, right);
		quickSort(arr, left, partitionIndices[0]);
		quickSort(arr, partitionIndices[1], right);
	}

	private static void randomizePivot(Pair[] array, int left, int right) {
        int randomIndex = (int)(Math.random() * (right - left + 1) + left);
        
        Pair temp = array[left];
        array[left] = array[randomIndex];
        array[randomIndex] = temp;
    }

	private static int[] partition(Pair[] arr, int left, int right) {
		randomizePivot(arr, left, right);

		Pair pivot = arr[left];
		int k = right;
		int j = left + 1;
		Pair temp;

		for (int i = left + 1; i <= k; i++) {
			if (arr[i].point < pivot.point) {
				temp = arr[j];
				arr[j] = arr[i];
				arr[i] = temp;
				j++;
			}
			else if (arr[i].point == pivot.point) {
				if (arr[i].label < pivot.label) {
					temp = arr[j];
					arr[j] = arr[i];
					arr[i] = temp;
					j++;
				}
				else if (arr[i].label > pivot.label) {
					temp = arr[k];
					arr[k] = arr[i];
					arr[i] = temp;
					k--;
					i--;
				}
			}
			else if (arr[i].point > pivot.point) {
				temp = arr[k];
				arr[k] = arr[i];
				arr[i] = temp;
				k--;
				i--;
			}
		}
		temp = arr[left];
		arr[left] = arr[j - 1];
		arr[j - 1] = temp;

		int[] partitionIndices = {0, 0};
		partitionIndices[0] = Math.max(0, j - 2); // rightmost index of the less-than partition
		partitionIndices[1] = Math.min(right, k + 1); // leftmost index of the greater-than partition
		return partitionIndices;
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int s = input.nextInt();
		int p = input.nextInt();

		int[] starts = new int[s];
		int[] ends = new int[s];
		for (int i = 0; i < s; i++) {
			starts[i] = input.nextInt();
			ends[i] = input.nextInt();
		}

		int[] points = new int[p];
		for (int i = 0; i < p; i++) {
			points[i] = input.nextInt();
		}

		Pair[] labeledPoints = labelPoints(starts, ends, points);
		quickSort(labeledPoints, 0, labeledPoints.length - 1);
		Pair[] counts = getCounts(labeledPoints, points.length);
		printResult(counts, points);
	}
}
