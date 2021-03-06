import java.util.*;

public class Sorting {
    private static void quickSort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }
    
    private static void quickSort(int[] array, int left, int right) {
        if (left >= right) {
            return;
        }

        int[] partitionIndices = partition(array, left, right);
        quickSort(array, left, partitionIndices[0]);
        quickSort(array, partitionIndices[1], right);
    }

    private static void randomizePivot(int[] array, int left, int right) {
        int randomIndex = (int)(Math.random() * (right - left + 1) + left);
        
        int temp = array[left];
        array[left] = array[randomIndex];
        array[randomIndex] = temp;
    }
    
    private static int[] partition(int[] array, int left, int right) {
        randomizePivot(array, left, right);
        
        int pivot = array[left];
        int k = right;
        int j = left + 1;
        int temp;

        for (int i = left + 1; i <= k; i++) {
            if (array[i] < pivot) {
                temp = array[j];
                array[j] = array[i];
                array[i] = temp;
                j++;
            }
            else if (array[i] > pivot) {
                temp = array[k];
                array[k] = array[i];
                array[i] = temp;
                k--;
                i--;
            }
        }
        temp = array[left];
        array[left] = array[j - 1];
        array[j - 1] = temp;
        
        int[] partitionIndices = {0, 0};
        partitionIndices[0] = Math.max(0, j - 2); // rightmost index of the less-than partition
        partitionIndices[1] = Math.min(right, k + 1); // leftmost index of the greater-than partition
        return partitionIndices;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] array = new int[n];

        for (int i = 0; i < n; i++) {
            array[i] = input.nextInt();
        }
        
        quickSort(array);
        
        for (int i = 0; i < n; i++) {
            System.out.print(array[i] + " ");
        }
    }
}