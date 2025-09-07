// 代码生成时间: 2025-09-07 09:26:05
package com.example

import groovy.transform.CompileStatic

@CompileStatic
class SortAlgorithmGrails {

    /**
     * Sorts an array of integers using a simple bubble sort algorithm.
     *
     * @param numbers The array of integers to sort.
     * @return The sorted array of integers.
     */
    Integer[] bubbleSort(Integer[] numbers) {
        if (numbers == null || numbers.length == 0) {
            throw new IllegalArgumentException('Array must not be null or empty.')
        }

        boolean swapped
        do {
            swapped = false
            for (int i = 1; i < numbers.length; i++) {
                if (numbers[i - 1] > numbers[i]) {
                    swap(numbers, i - 1, i)
                    swapped = true
                }
            }
        } while (swapped)

        return numbers
    }

    /**
     * Helper method to swap two elements in an array.
     *
     * @param array The array where elements will be swapped.
     * @param i The index of the first element.
     * @param j The index of the second element.
     */
    private void swap(Integer[] array, int i, int j) {
        int temp = array[i]
        array[i] = array[j]
        array[j] = temp
    }

    /**
     * Main application method to demonstrate the bubble sort functionality.
     *
     * @param args Command line arguments (not used in this example).
     */
    static void main(String[] args) {
        SortAlgorithmGrails sortAlgorithm = new SortAlgorithmGrails()

        Integer[] numbersToSort = [5, 3, 8, 4, 2]
        println 'Original array: ' + numbersToSort

        Integer[] sortedNumbers = sortAlgorithm.bubbleSort(numbersToSort)
        println 'Sorted array: ' + sortedNumbers
    }
}
