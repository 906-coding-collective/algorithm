#!/bin/python3
import sys
from numpy import genfromtxt
from os import walk, system


def getDataFilePaths():
    dataDirs = []
    for dirs in next(walk("data"))[1]:
        for files in next(walk("data/" + dirs))[2]:
            dataDirs.append("data/" + dirs + "/" + files)

    return dataDirs


def getCSV(path):
    java_data = genfromtxt(path, delimiter=',', dtype=(int, int), autostrip=True)
    return java_data


def startProgressTracker(dataSetSize, dataFilePaths):
    system('setterm -cursor off')
    for path in dataFilePaths:
        dataSoFar = getCSV(path)
        datashape = dataSoFar.shape[-1]
        percentage = str(round(((dataSoFar.size // datashape) / dataSetSize) * 100))
        print("[" + "+" * (dataSoFar.size // datashape) + "-" *
              (dataSetSize - (dataSoFar.size // datashape)) + "]->" + percentage + "%->" + path)
    
    while True:
        print("\033[F" * len(dataFilePaths), end='')
        for path in dataFilePaths:
            dataSoFar = getCSV(path)
            percentage = str(round(((dataSoFar.size // datashape) / dataSetSize) * 100))
            print("[" + "+" * (dataSoFar.size // datashape) + "-" *
                  (dataSetSize - (dataSoFar.size // datashape)) + "]->" + percentage + "%->" + path)


def main():
    dataSetSize = 30
    dataFilePaths = getDataFilePaths()
    startProgressTracker(dataSetSize, dataFilePaths)


if __name__ == "__main__":
    try:
        main()
        system('setterm -cursor off')
    except KeyboardInterrupt:
        system('setterm -cursor off')
        sys.exit(0)
