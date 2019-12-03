//Jonathon Lefler

public class InsertionSort{
    public InsertionSort(){}

    /**
     * Inline selection sort
     * 
     * @param data - type int[]
     * @param CASE - type String
     */
    public void sort(int[] data, String CASE){
        int[] array = new int[data.length];
        copyArray(data, array);
        long startTime = System.nanoTime();
        int arrayLength = array.length;
        for(int i = 1; i < arrayLength; i++){
            int current = array[i], prev = i - 1;
            while(prev >= 0 && array[prev] > current){
                array[prev + 1] = array[prev];
                prev--;
            }
            array[prev + 1] = current;
        }
        long endTime = System.nanoTime();
        System.out.println(CASE + ": " + (endTime - startTime) / 1000000 + "ms: " + checkAnswer(array));
    }

    /**
     * Inline selection sort
     * 
     * @param data - type int[]
     * @param CASE - type String
     * @return - type int
     */
    public long sortA(int[] data, String CASE){
        int[] array = new int[data.length];
        copyArray(data, array);
        long startTime = System.nanoTime();
        int arrayLength = array.length;
        for(int i = 1; i < arrayLength; i++){
            int current = array[i], prev = i - 1;
            while(prev >= 0 && array[prev] > current){
                array[prev + 1] = array[prev];
                prev--;
            }
            array[prev + 1] = current;
        }
        long endTime = System.nanoTime();
        return (endTime - startTime); // 1000000;
        //System.out.println(CASE + ": " + (endTime - startTime) / 1000000 + "ms: " + checkAnswer(array));
    }

    public long binarySort(int[] data, String CASE){
        int[] array = new int[data.length];
        copyArray(data, array);
        long startTime = System.nanoTime();
        int arrayLength = array.length;
        int target;
        for(int i = 1; i < arrayLength; i++){
            int targetIndex = binarySearch(array, array[i], i - 1);
            target = array[i];
            for(int j = i; j > targetIndex; j--){
                array[j] = array[j - 1];
            }
            array[targetIndex] = target;
        }
        long endTime = System.nanoTime();
        if(checkAnswer(array)){
            return (endTime - startTime);
        }
        else{
            return -1;
        }
        //System.out.println(CASE + ": " + (endTime - startTime) / 1000000 + "ms: " + checkAnswer(array));
    }

    /**
     * Binary search that returns the postion of
     * where key would go in order of least to greatest
     * in array using high as the index upper bound
     * Will still return postion of key if it's in array
     * 
     * @param array - type int[]
     * @param key - type int
     * @param high - type int
     * @return - type int
     */
    public int binarySearch(int[] array, int key, int high){
        //System.out.println("key: " + key);
        int low = 0, mid = 0;
        while(low <= high){
            mid = (low + high) / 2;
            if(key < array[mid]){
                high = mid - 1;
            }
            else if(key > array[mid]){
                low = mid + 1;
            }
            else{
                return mid;
            }
        }
        return low;
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
}

