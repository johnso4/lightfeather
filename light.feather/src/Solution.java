// This class includes functions that are able to sort a list both in
// ascending and descending order and can also display various information
// such as frequency of numbers or number of unique values.
public class Solution implements SortChallenge{

    // simpleSort uses quickSort as it is generally the fastest sorting algorithm.
    public int[] simpleSort(int[] list, boolean ascending) {
        return quickSort(list, 0, list.length - 1, ascending);
    }

    // simpleSort defaults to ascending.
    public int[] simpleSort(int[] list)
    {
        return simpleSort(list, true);
    }

    // sorts a list by choosing a pivot point, moving everything
    // to the left that is less than it, to the right that is greater
    // than it(or vise versa for descending) and then breaking up the
    // array into subarrays(the arrays to the left and right of the pivot point)
    // and sorting those the same way recursively.
    int[]  quickSort(int[] list, int low, int high, boolean ascending) {
        if(low < high) {
            int partitionIndex = partition(list, low, high, ascending);

            quickSort(list, low,partitionIndex - 1, ascending);
            quickSort(list, partitionIndex + 1, high, ascending);
        }
        return list;
    }

    // this function uses the last index of the list as a pivot. It then moves
    // elements of the list relative to the pivot depending on if its greater
    // or less than the pivot and if its ascending or descending.
    int partition(int[] list, int low, int high, boolean ascending) {
        int pivot = list[high];
        int index = low - 1;

        if(ascending) {
            for (int j = low; j < high; j++) {
                if (list[j] < pivot) {
                    index++;

                    // swap list[index] with list[j]
                    int temp = list[index];
                    list[index] = list[j];
                    list[j] = temp;

                }
            }
        }

        else {
            for (int j = low; j < high; j++) {
                if (list[j] > pivot) {
                    index++;

                    // swap list[index] and list[j]
                    int temp = list[index];
                    list[index] = list[j];
                    list[j] = temp;
                }
            }
        }

        // swap list[index + 1] with list[high]
        int temp = list[index + 1];
        list[index + 1] = list[high];
        list[high] = temp;

        return index + 1;
    }

    // prints out how many times each number in the list appears.
    // Each number is placed on a separate line in ascending order.
    public void printSortedFrequency(int[] list) {
        int[] sortedList = simpleSort(list, true);
        int currentNumCount = 1;
        for (int i = 0; i < sortedList.length; ++i) {
            while(i < sortedList.length - 1 && sortedList[i] == sortedList[i + 1]) {
                currentNumCount++;
                i++;
            }
            if (currentNumCount > 1) {
                System.out.println(list[i] + " appears " + currentNumCount + " times");
            } else {
                System.out.println(list[i] + " appears " + currentNumCount + " time");
            }
            currentNumCount = 1;
        }
    }

    // Returns how many unique values there are in a list.
    public int numberOfUniqueValues(int[] list) {
        int uniqueValues = 0;
        int[] sortedList = simpleSort(list, true);
        for(int i = 0; i < sortedList.length; i++) {
            while(i < sortedList.length - 1 && sortedList[i] == sortedList[i + 1]) {
                i++;
            }
            uniqueValues++;
        }
        return uniqueValues;
    }

    // Prints a list of ints on one line to standard output.
    void printList(int[] list) {
        String listOutput = "";
        for(int i = 0; i < list.length; i++) {
            listOutput += list[i] + " ";
        }
        System.out.println(listOutput);
    }

    // Test the class by creating a list and using the various
    // list sorting functions on it and printing out the results
    // to standard output.
    public static void main(String args[])
    {
        int testList[] = {10, 7, 8, 3, 3, 2, 4, 1, 9, 1, 5};
        Solution solution = new Solution();

        System.out.println("----------------------------------------------");
        System.out.println("Testing with the list below");
        solution.printList(testList);
        System.out.println("----------------------------------------------");
        System.out.println("Testing print sorted frequency with the test list");
        solution.printSortedFrequency(testList);
        System.out.println("----------------------------------------------");
        System.out.println("Now testing sorting it in a ascending order");
        int testList2[] = solution.simpleSort(testList, true);
        solution.printList(testList2);
        System.out.println("----------------------------------------------");
        System.out.println("Now testing sorting it in a descending order");
        int testList3[] = solution.simpleSort(testList, false);
        solution.printList(testList3);
        System.out.println("----------------------------------------------");
        System.out.println("Now testing number of unique values");
        System.out.println("There are " + solution.numberOfUniqueValues(testList) + " unique values in the test list");
        System.out.println("----------------------------------------------");
    }
}