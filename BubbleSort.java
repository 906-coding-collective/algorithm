

public class BubbleSort {

    /**
     * 
     * Constructor
     */
    public BubbleSort(){}

    /**
     * Standard Bubble Sort
     * 
     * @param data - type int[]
     * @param CASE - type String
     */
    public long bubbleSort(int data[], String CASE){
        int[] array = new int[data.length];
        copyArray(data, array);
        long startTime = System.nanoTime();
        int arrayLength = array.length;
        for(int i = 0; i < arrayLength - 1; i++){
            for(int j = 0; j < arrayLength - 1; j++){
                if(array[j] > array[j + 1]){
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        long endTime = System.nanoTime();
        //System.out.println(CASE + ": " + (endTime - startTime) / 1000000 + "ms: " + checkAnswer(array));
        return endTime - startTime;
    }

    /**
     * Standard Bubble Sort with a "short circuit" boolean
     * to stop redundent passes after the array has been
     * sorted
     * 
     * @param data - type int[]
     * @param CASE - type String
     */
    public void bubbleSortO(int data[], String CASE){
        int[] array = new int[data.length];
        copyArray(data, array);
        long startTime = System.nanoTime();
        int arrayLength = array.length;
        boolean swapped;
        for(int i = 0; i < arrayLength - 1; i++){
            swapped = false;
            for(int j = 0; j < arrayLength - 1; j++){
                if(array[j] > array[j + 1]){
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }
            if(swapped == false){
                break;
            }
        }
        long endTime = System.nanoTime();
        System.out.println(CASE + ": " + (endTime - startTime) / 1000000 + "ms: " + checkAnswer(array));
    }

    /**
     * Checks array to see if it is the correct order
     * lowest to highest
     * 
     * @param array - type int[]
     * @return - type boolean
     */
    public boolean checkAnswer(int[] array){
        for(int i = 0; i < array.length - 1; i++){
            if(array[i] > array[i + 1]){
                return false;
            }
        }
        return true;
    }

    /**
     * Prints given array
     * 
     * @param array - type int[]
     */
    public void printArray(int array[]){
        for(int i = 0; i < array.length; i++){
            System.out.print("|" + array[i]);
        }
        System.out.print("|");
        System.out.println();
    }

    /**
     * Copy array into newArray
     * 
     * @param array - type int[]
     * @param newArray - type int[]
     */
    private void copyArray(int[] array, int[] newArray){
        for(int i = 0; i < array.length; i++){
            newArray[i] = array[i];
        }
    }
}