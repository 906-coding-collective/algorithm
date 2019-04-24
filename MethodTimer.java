/*Jonathon Lefler
The purpose of this project is to compare different sorting algorithms
against eachother for various data types. Eventually, it will create a 
usefullness graph for each. I'll also compare the program against different
languages to check perfermance between them. I suspect the higher level langauges
will be slower.
*/


import java.util.Scanner;

public class MethodTimer {
    /**
     * Runs a given sorting algorithm and prints out times for
     * different array sizes. For the average case, we make 
     * {sampleSize} unique arrays to get an average.
     * Best case: already in the correct order
     * Worst case: in reverse order
     * 
     * Cmd list is in for debugging and development
     */
    public static void main(String[] args){
        MethodTimer timer = new MethodTimer();
        Scanner input = new Scanner(System.in);
        InsertionSort insertionSort = new InsertionSort();
        BubbleSort bubbleSort = new BubbleSort();
        int dataSize, timesIndex = 0, sampleSize = 100;

        while(true){
            int cmd = timer.showCmd(input);

            if(cmd == 0){
                break;
            }
            else{
                for(dataSize = 10000; dataSize <= 20000; dataSize += 1000){
                    System.out.print("Working on datasize: " + dataSize);
                    long startTime = System.nanoTime();
                    //Best Case
                    int[] bestCaseInt = new int[dataSize];
                    //Worst Case
                    int[] worstCaseInt = new int[dataSize];

                    for(int i = 0; i < dataSize; i++){
                        bestCaseInt[i] = 1000000 + i;
                        worstCaseInt[i] = 1000000 - i;
                    }

                    if(cmd == 1){
                        long bestCaseTimeBS = bubbleSort.bubbleSort(bestCaseInt, "best case int");
                        long worstCaseTimeBS = bubbleSort.bubbleSort(worstCaseInt, "worst case int");

                        long averageTimeTakenBS = 0;
                        for(int i = 0; i < sampleSize; i++){
                            averageTimeTakenBS += bubbleSort.bubbleSort(timer.generateArray(dataSize), "average case int");
                            //averageTimeTakenBIS += insertionSort.binarySort(timer.generateArray(dataSize), "average case int");
                        }
                        averageTimeTakenBS = averageTimeTakenBS / sampleSize;
                        long endTime = System.nanoTime();
                        System.out.print(" | It took " + ((endTime - startTime) / 1000000) + "ms\r\n");
                        System.out.println("Bubble Sort: Best Case: " + bestCaseTimeBS + "ns | Average Case: " + averageTimeTakenBS + "ns | Worst Case: " + worstCaseTimeBS + "ns");
                        System.out.println("////////////////////////////////////////////////////////////////////");
                    }
                    else if(cmd == 2){}
                    else if(cmd == 3){
                        long bestCaseTimeIS = insertionSort.sortA(bestCaseInt, "best case int");
                        long worstCaseTimeIS = insertionSort.sortA(worstCaseInt, "worst case int");
    
                        long bestCaseTimeBIS = insertionSort.binarySort(bestCaseInt, "best case int");
                        long worstCaseTimeBIS = insertionSort.binarySort(worstCaseInt, "worst case int");
    
                        long averageTimeTakenIS = 0, averageTimeTakenBIS = 0;
                        for(int i = 0; i < sampleSize; i++){
                            averageTimeTakenIS += insertionSort.sortA(timer.generateArray(dataSize), "average case int");
                            averageTimeTakenBIS += insertionSort.binarySort(timer.generateArray(dataSize), "average case int");
                        }
                        long averageCaseTimeIS = averageTimeTakenIS / sampleSize;
                        long averageCaseTimeBIS = averageTimeTakenBIS / sampleSize;
                        timesIndex++;
                        long endTime = System.nanoTime();
                        System.out.print(" | It took " + ((endTime - startTime) / 1000000) + "ms\r\n");
                        System.out.println("       Insertion Sort: Best Case: " + bestCaseTimeIS + "ns | Average Case: " + averageCaseTimeIS + "ns | Worst Case: " + worstCaseTimeIS + "ns");
                        System.out.println("Binary Insertion Sort: Best Case: " + bestCaseTimeBIS + "ns | Average Case: " + averageCaseTimeBIS + "ns | Worst Case: " + worstCaseTimeBIS + "ns");
                        System.out.println("////////////////////////////////////////////////////////////////////");
                    }
                }
            }
        }
    }

    /**
     * Shows command list and gets input, validating it
     * 
     * @param input - type Scanner
     * @return cmd - type int
     */
    private int showCmd(Scanner input){
        int cmd = -1;
        do{
            System.out.println("(0)EXIT\r\n(1)Bubble Sort\r\n(2)Selection Sort\r\n(3)Insertion Sort\r\n");
            while(!input.hasNextInt()){
                System.out.println("Invalid command");
                input.next();
            }
            cmd = input.nextInt();
        } while(cmd < 0 && cmd > 3);
        return cmd;
    }

    /**
     * returns a generated array of size dataSize
     * 
     * @param dataSize - type int
     * @return - type int[]
     */
    private int[] generateArray(int dataSize){
        int[] averageCaseInt = new int[dataSize];
        for(int i = 0; i < dataSize; i++){
            int intNum = (int)(Math.random() * 1000000), j = 0;
            while(j < i){
                if(averageCaseInt[j] == intNum){
                    intNum = (int)(Math.random() * 1000000);
                    j = 0;
                    continue;
                }
                j++;
            }
            averageCaseInt[i] = intNum;
        }
        return averageCaseInt;
    }

    /**
     * Prints given array
     * 
     * @param array - int[]
     */
    private void printArray(int array[]){
        for(int i = 0; i < array.length; i++){
            System.out.print("|" + array[i]);
        }
        System.out.print("|");
        System.out.println();
    }

    /**
     * Prints given array
     * 
     * @param array - long[]
     */
    private void printArray(long array[]){
        for(int i = 0; i < array.length; i++){
            System.out.print("|" + array[i]);
        }
        System.out.print("|");
        System.out.println();
    }

    /**
     * Prints given array
     * 
     * @param array - float[]
     */
    private void printArray(float array[]){
        for(int i = 0; i < array.length; i++){
            System.out.print("|" + array[i]);
        }
        System.out.print("|");
        System.out.println();
    }

    /**
     * Prints given array
     * 
     * @param array - String[]
     */
    private void printArray(String array[]){
        for(int i = 0; i < array.length; i++){
            System.out.print("|" + array[i]);
        }
        System.out.print("|");
        System.out.println();
    }
}