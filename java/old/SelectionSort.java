//Jonathon Lefler

public class SelectionSort{
    public SelectionSort(){}

    /**
     * Inline Selection Sort
     * 
     * @param data - type int[]
     * @param CASE - type String
     */
    public void sortInline(int[] data, String CASE){
        int[] array = new int[data.length];
        copyArray(data, array);
        long startTime = System.nanoTime();
        int arrayLength = array.length;
        for(int i = 0; i < arrayLength - 1; i++){
            int minIndex = i;
            for(int j = i + 1; j < arrayLength; j++){
                if(array[j] < array[minIndex]){
                    minIndex = j;
                }
            }
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
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
    private void printArray(int array[]){
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