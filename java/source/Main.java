package source;

import java.util.Arrays;

public class Main {
	public static void main(String[] args){
		int dataSetSize = Integer.parseInt(args[0]);
		int dataSize = 1000;
		int sampleSize = 100;
		Main main = new Main();
		int[][] bestCaseInt = new int[dataSetSize][];
		int[][] worstCaseInt = new int[dataSetSize][];
		int[][] averageCasesInt = new int[dataSetSize][];
		
		int dataSizeTemp = dataSize;
        for(int i = 0; i < dataSetSize; i++, dataSizeTemp += 1000) {
        	averageCasesInt[i] = new int[dataSizeTemp];
        	bestCaseInt[i] = new int[dataSizeTemp];
        	worstCaseInt[i] = new int[dataSizeTemp];
        	
        	for(int j = 0; j < dataSizeTemp; j++) {
                bestCaseInt[i][j] = 1000000 + i;
                worstCaseInt[i][j] = 1000000 - i;
        	}
            averageCasesInt[i] = main.generateArray(dataSizeTemp);
        }
		
        Thread[] threadList = new Thread[2];
		threadList[0] = new BubbleSort(dataSetSize, dataSize, sampleSize, bestCaseInt, worstCaseInt, averageCasesInt);
		threadList[1] = new InsertionSort(dataSetSize, dataSize, sampleSize, bestCaseInt, worstCaseInt, averageCasesInt);
		//threadList[2] = new MergeSort(dataSetSize, dataSize, sampleSize, bestCaseInt, worstCaseInt, averageCasesInt);

		
		main.threadProcessing(threadList);
	}
	
	/**
	 * Process threads to keep them under a safe limit.
	 * @param threadList Array of threads
	 */
	private void threadProcessing(Thread[] threadList) {
		int currentActiveThreads = 0;
		//boolean go = true;
		while(true) {
			for(int i = 0; i < threadList.length; i++) {
				if(threadList[i].getState() == Thread.State.NEW
						&& currentActiveThreads < 7){
					threadList[i].start();
					currentActiveThreads++;
				}
			}
			if(Arrays.stream(threadList).allMatch(thread -> thread.getState() == Thread.State.TERMINATED)) {
				System.out.println("Done!");
				break;
			}
		}
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
}
