// 代码生成时间: 2025-10-08 02:03:24
import groovy.transform.TypeChecked

/**
 * Service class to provide sorting functionality.
 * @author Your Name
 * @since 1.0
 */
@TypeChecked
class SortingService {

    /**
     * Sorts an array of integers using the bubble sort algorithm.
     * @param array The array of integers to sort.
     * @return The sorted array.
     */
    List<Integer> bubbleSort(List<Integer> array) {
        if (array == null) {
            throw new IllegalArgumentException('The array cannot be null.')
        }

        int n = array.size()
        boolean swapped
        for (int i = 0; i < n - 1; i++) {
            swapped = false
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    // swap temp and array[i]
                    Integer temp = array[j]
                    array[j] = array[j + 1]
                    array[j + 1] = temp
                    swapped = true
                }
            }
            // IF no two elements were swapped by inner loop, break
            if (!swapped)
                break
        }
        return array
    }

    /**
     * Sorts an array of integers using the quick sort algorithm.
     * @param array The array of integers to sort.
     * @return The sorted array.
     */
    List<Integer> quickSort(List<Integer> array) {
        if (array == null) {
            throw new IllegalArgumentException('The array cannot be null.')
        }

        return quickSortHelper(array, 0, array.size() - 1)
    }

    private List<Integer> quickSortHelper(List<Integer> array, int low, int high) {
        if (low < high) {
            int pi = partition(array, low, high)
            quickSortHelper(array, low, pi - 1)
            quickSortHelper(array, pi + 1, high)
        }
        return array
    }

    private int partition(List<Integer> array, int low, int high) {
        Integer pivot = array[high]
        int i = (low - 1)
        for (int j = low; j < high; j++) {
            if (array[j] < pivot) {
                i++
                Integer temp = array[i]
                array[i] = array[j]
                array[j] = temp
            }
        }
        Integer temp = array[i + 1]
        array[i + 1] = array[high]
        array[high] = temp
        return i + 1
    }
}
