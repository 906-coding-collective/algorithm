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
public class MergeSort extends Thread {
	private long[] bestCaseTimes;
	private long[] averageCaseTimes;
	private long[] worstCaseTimes;
	
	private int[][] bestCasesInt;
	private int[][] averageCasesInt;
	private int[][] worstCasesInt;
	
	private int dataSetSize;
	private int sampleSize;
	private int dataSize;
	
	public MergeSort(int dataSetSIZE, int dataSIZE, int sampleSIZE, int[][] bestCaseINT, int[][] worstCaseINT, int[][] averageCasesINT) {
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
					bestCaseAverage += this.sort(this.bestCasesInt[i], 0, this.bestCasesInt.length - 1);
					worstCaseAverage += this.sort(this.worstCasesInt[i], 0, this.worstCasesInt.length - 1);
					long currentAverageCaseAverage = 0;
					for(int c = 0; c < 3; c++) {
						currentAverageCaseAverage += this.sort(averageCasesInt[i], 0, this.averageCasesInt.length - 1);
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
	 * 
	 * @param data
	 * @param left
	 * @param right
	 * @return
	 */
	private long sort(int[] data, int left, int right){
        int[] array = new int[data.length];
        copyArray(data, array);
        long startTime = System.nanoTime();
        //int arrayLength = array.length;
        if(left < right) {
        	int middle = (left + right) / 2;
        	this.sort(array, left, middle);
        	this.sort(array, middle + 1, right);
        	this.merge(array, left, middle, right);
        }
        
        long endTime = System.nanoTime();
        //for(int i )
        return endTime - startTime;
    }
	
	/**
	 * 
	 * @param array
	 * @param left
	 * @param middle
	 * @param right
	 */
	private void merge(int[] array, int left, int middle, int right) {
		int n1 = middle - left + 1;
		int n2 = right - middle;
		
		int[] tempLeft = new int[n1];
		int[] tempRight = new int[n2];
		
		for(int i = 0; i < n1; i++) {
			tempLeft[i] = array[left + i];
		}
		for(int i = 0; i < n2; i++) {
			tempRight[i] = array[middle + 1 + i];
		}
		
		int i = 0, j = 0, k = left;
		while(i < n1 && j < n2) {
			if(tempLeft[i] <= tempRight[j]) {
				array[k] = tempLeft[i];
				i++;
			}
			else {
				array[k] = tempRight[j];
				j++;
			}
			k++;
		}
		while(i < n1) {
			array[k] = tempLeft[i];
			i++;
			k++;
		}
		while(j < n2) {
			array[k] = tempRight[j];
			j++;
			k++;
		}
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

