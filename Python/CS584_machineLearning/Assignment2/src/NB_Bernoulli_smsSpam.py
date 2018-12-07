import numpy as np
from numpy import *
import pandas as pd
import re
import matplotlib as plt
import matplotlib.pyplot as plt
from sklearn.model_selection import train_test_split
# -*- coding: UTF-8 -*-


def loaddataset():

    sms = pd.read_csv('/Users/michael/Downloads/spam.csv', encoding = "cp1252")
    #sms = sms.sample(frac=1.0)
    # print(sms)

    dummy = pd.get_dummies(sms['v1'])
    sms = pd.concat([sms, dummy], axis=1)
    # print(sms)
    orgdata = np.array(sms.iloc[:, 1]).reshape(len(sms), -1)
    print(orgdata)
    orglabel = np.array(sms['spam']).reshape(len(sms), 1)
    print(orglabel)
    x_train, x_test, y_train, y_test = train_test_split(orgdata, orglabel, test_size=0.2)
    return x_train, x_test, y_train, y_test

def createVocabList(dataSet):
    vocabSet = set([])
    print('dataset')
    print(dataSet)
    for document in dataSet:
        # 求并集
        vocabSet = vocabSet | set(document)
        print(vocabSet)
    return list(vocabSet)

def setOfWords2Vec(vocabList, inputSet):

    returnVec = [0] * len(vocabList)
    for word in inputSet:
        if word in vocabList:
            returnVec[vocabList.index(word)] = 1
        else:
            print("word not in data" % word)
    return returnVec


def trainNB0(trainMatrix, trainCategory):

    numTrainDocs = len(trainMatrix)
    numWords = len(trainMatrix[0])
    pAbusive = sum(trainCategory) / float(numTrainDocs)

    p0Num = np.ones(numWords)
    p1Num = np.ones(numWords)
    p0Denom = 2
    p1Denom = 2
    for i in range(numTrainDocs):
        if trainCategory[i] == 1:
            p1Num += trainMatrix[i]
            p1Denom += sum(trainMatrix[i])
        else:
            p0Num += trainMatrix[i]
            p0Denom += sum(trainMatrix[i])

    p1Vect = np.log(p1Num / p1Denom)
    p0Vect = np.log(p0Num / p0Denom)
    return p0Vect, p1Vect, pAbusive

def classifyNB(vec2Classify,p0Vec,p1Vec,pClass1):

    p1 = sum(vec2Classify*p1Vec)+np.log(pClass1)
    p0 = sum(vec2Classify*p0Vec)+np.log(1-pClass1)
    if p1>p0:
        return 1
    else:
        return 0

def testingNB():

        dataTRain, dataTest, labelTRain, labelTest = loaddataset()
        # print(dataTRain)
        # print(dataTest)
        myVocabList = createVocabList(dataTRain)
        print('after')
        trainMat = []
        for postinDoc in dataTRain:
            trainMat.append(setOfWords2Vec(myVocabList, postinDoc))
        print(trainMat)
        p0V, p1V, pAb = trainNB0(np.array(trainMat), np.array(labelTRain))
        testMat = []
        for postinDoc in dataTest:
            testMat.append(setOfWords2Vec(myVocabList, postinDoc))
        testEntry = testMat
        thisDoc = np.array(setOfWords2Vec(myVocabList, testEntry))
        print(testEntry, 'classified as: ', classifyNB(thisDoc, p0V, p1V, pAb))
        testEntry = ['stupid', 'garbage']
        thisDoc = np.array(setOfWords2Vec(myVocabList, testEntry))
        print(testEntry, 'classified as: ', classifyNB(thisDoc, p0V, p1V, pAb))

if __name__ == '__main__':
    testingNB()