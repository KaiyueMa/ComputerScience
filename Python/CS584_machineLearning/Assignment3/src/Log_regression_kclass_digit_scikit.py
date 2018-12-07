# -*- coding: utf-8 -*-
from scipy import io as spio
import numpy as np
from sklearn import svm
from sklearn.linear_model import LogisticRegression


def logisticRegression_oneVsAll():
    data = loadmat_data('/Users/michael/Codes/bitbucket/cs584-s18-kaiyue-ma/AS3/data/data_digits.mat')
    X = data['X']
    y = data['y']
    y = np.ravel(y)  # sklearn trans to (5000,)

    model = LogisticRegression()
    model.fit(X, y)  # fitting

    predict = model.predict(X)  # predit

    print("Using scikit-learn predict accuracy is ：%f%%" % np.mean(np.float64(predict == y) * 100))


# 加载mat文件
def loadmat_data(fileName):
    return spio.loadmat(fileName)


if __name__ == "__main__":
    logisticRegression_oneVsAll()
