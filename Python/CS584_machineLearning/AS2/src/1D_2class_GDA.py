import math
import numpy as np
import pandas as pd
from numpy import dot
from sklearn.metrics import confusion_matrix
from sklearn.metrics import accuracy_score
from sklearn.metrics import precision_recall_fscore_support
from sklearn.cross_validation import KFold

iris = pd.read_csv('/Users/michael/Codes/bitbucket/cs584-s18-kaiyue-ma/AS2/data/iris.csv', encoding="gbk")
iris = iris.sample(frac=1.0)

dummy = pd.get_dummies(iris['Species'])
iris = pd.concat([iris, dummy], axis=1)

org_x = np.array(iris.iloc[:, 1:2])
org_y = np.array(iris['setosa']).reshape(len(iris), 1)

iris_train = iris.sample(frac=0.8)
iris_test = iris.sample(frac=0.2)
X = iris_train.iloc[:, 1:2]
Y = np.array(iris_train['setosa']).reshape(len(iris_train), 1)
X_t = iris_test.iloc[:, 1:2]
Y_t = np.array(iris_test['setosa']).reshape(len(iris_test), 1)
X_1 = X[Y == 1]
X_0 = X[Y == 0]
Xt_1 = X_t[Y_t == 1]
Xt_0 = X_t[Y_t == 0]


num = len(org_x)
kf = KFold(num, n_folds=3)
for trainIndex, testIndex in kf:
    trainY, testY = org_y[trainIndex], org_y[testIndex]
    trainX, testX = org_x[trainIndex], org_x[testIndex]

    theta1 = trainY.mean()
    theta0 = 1 - trainY.mean()
    mu1 = trainX[trainY == 1].mean()
    mu0 = trainX[trainY == 0].mean()
    var1 = trainX[trainY == 1].var()
    var0 = trainX[trainY == 0].var()
    sigma1 = (1 / len(trainY[trainY == 1])) * dot(X_1 - mu1, (X_1 - mu1).T)
    sigma0 = (1 / len(trainY[trainY == 0])) * dot(X_0 - mu1, (X_0 - mu1).T)
    gx1 = (-math.log(var1)) - (1 / (np.square(var1) * 2)) * (np.square(testX - mu1)) + (math.log(theta1))
    gx0 = (-math.log(var0)) - (1 / (np.square(var0) * 2)) * (np.square(testX - mu0)) + (math.log(theta0))
    dx = gx1 - gx0

    Yt1 = []
    for y in dx:
        if y[0] > 0:
            Yt1.append(1)
        else:
            Yt1.append(0)
    Yt1 = np.array(Yt1)

    matrix = confusion_matrix(testY, Yt1)
    accuracy = accuracy_score(testY, Yt1)
    error = 1 - accuracy
    prf = precision_recall_fscore_support(testY, Yt1)
    print("-result-")
    print('confusion matrix')
    print(matrix)
    print('accuracy is ：')
    print(accuracy)
    print('pcision is：')
    print(matrix[0, 0]/(matrix[0, 0]+matrix[0, 1]))
    print('recall is：')
    print(matrix[0, 0] / (matrix[0, 0] + matrix[1, 0]))
    print('F-measure is：')
    print((2*matrix[0, 0]) / ((2*matrix[0, 0]) + matrix[0, 1]+ matrix[1, 0]))


