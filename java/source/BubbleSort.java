package source;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * Standard Bubble Sort
 * 
 * @author jlefler
 *
 */
public class BubbleSort extends Thread {
	private long[] bestCaseTimes;
	private long[] averageCaseTimes;
	private long[] worstCaseTimes;
	
	private int[][] bestCasesInt;
	private int[][] averageCasesInt;
	private int[][] worstCasesInt;
	
	private int dataSetSize;
	private int sampleSize;
	private int dataSize;
	
	public BubbleSort(int dataSetSIZE, int dataSIZE, int sampleSIZE, int[][] bestCaseINT, int[][] worstCaseINT, int[][] averageCasesINT) {
		this.bestCaseTimes = new long[dataSetSIZE];
		this.averageCaseTimes = new long[dataSetSIZE];
		this.worstCaseTimes = new long[dataSetSIZE];
		
		this.bestCasesInt = bestCaseINT;
		this.worstCasesInt = worstCaseINT;
		this.averageCasesInt = averageCasesINT;
		
		this.dataSetSize = dataSetSIZE;
		this.dataSize = dataSIZE;
		this.sampleSize = sampleSIZE;
	}
	
	public void run() {
		File filePath = new File("../data/java/" + (String)(this.getClass().getSimpleName()) +".csv");
		try(PrintWriter out =
				new PrintWriter(
						new BufferedWriter(
								new FileWriter(filePath, true)))) {
			
			for(int i = 0; i < this.dataSetSize; i++) {
				long bestCaseAverage = 0;
				long averageCaseAverage = 0;
				long worstCaseAverage = 0;
				for(int j = 0; j < this.sampleSize; j++) {
					bestCaseAverage += this.sort(bestCasesInt[i], "");
					worstCaseAverage += this.sort(worstCasesInt[i], "");
					long currentAverageCaseAverage = 0;
					for(int c = 0; c < 3; c++) {
						currentAverageCaseAverage += this.sort(averageCasesInt[i], "");
					}
					averageCaseAverage += currentAverageCaseAverage / 3;
				}
				this.bestCaseTimes[i] = bestCaseAverage / this.sampleSize;
				this.averageCaseTimes[i] = averageCaseAverage / this.sampleSize;
				this.worstCaseTimes[i] = worstCaseAverage / this.sampleSize;
				out.println((i + 1) * this.dataSize + "," + this.bestCaseTimes[i]
						+ "," + this.averageCaseTimes[i] + "," + this.worstCaseTimes[i]);
				out.flush();
			}
		}catch (Exception e) {
			System.out.println(this.getName() + ": " + e);
		}
	}
	
    /**
     * Standard Bubble Sort
     * 
     * @param data - type int[]
     * @param CASE - type String
     */
	private long sort(int data[], String CASE){
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
