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

org_x = np.array(iris.iloc[:, 1:4])
org_y = np.array(iris['setosa']).reshape(len(iris), 1)

iris_train = iris.sample(frac=0.8)
iris_test = iris.sample(frac=0.2)
X = iris_train.iloc[:, 1:4]
Y = np.array(iris_train['setosa']).reshape(len(iris_train), 1)
X_t = iris_test.iloc[:, 1:4]
Y_t = np.array(iris_test['setosa']).reshape(len(iris_test), 1)
X_1 = X[Y == 1]
X_0 = X[Y == 0]
Xt_1 = X_t[Y_t == 1]
Xt_0 = X_t[Y_t == 0]

def g(mu, sigma, theta, x):
    n = len(x)
    det = np.linalg.det(sigma) ** 0.5
    c = 1 / ((2 * math.pi) ** (n / 2) * det)
    b = np.dot(np.dot((x - mu), np.linalg.inv(sigma)), (x - mu).T)
    d = math.exp(-1 / 2 * b)
    e = c * d

    return math.log(e) + math.log(theta)


def tevaluate(dx, testX, testY):
    Yt1 = []
    for y in dx:
        if y > 0:
            Yt1.append(1)
        else:
            Yt1.append(0)
    Yt1 = np.array(Yt1)

    matrix = confusion_matrix(testY, Yt1)

    accuracy = accuracy_score(testY, Yt1)
    error = 1 - accuracy
    prf = precision_recall_fscore_support(testY, Yt1)
    return matrix, accuracy, error, prf


def tcrossValidate(X, Y):
    n = len(X)
    kf = KFold(n, n_folds=5)

    for trainIndex, testIndex in kf:
        trainY, testY = Y[trainIndex], Y[testIndex]
        trainX, testX = X[trainIndex], X[testIndex]


        theta1 = Y.mean()
        theta0 = 1 - Y.mean()
        mu1 = X[Y == 1].mean()
        mu0 = X[Y == 0].mean()
        var1 = X[Y == 1].var()
        var0 = X[Y == 0].var()

        vs = []
        mu = np.array([np.array([mu0])])
        for x in X_0.get_values():
            x = np.array([x])
            v = np.dot((x - np.array([mu0])).T, (x - np.array([mu0])))
            vs.append(v)
        sigma0 = np.mean(vs, axis=0)

        vs = []
        mu = np.array([np.array([mu1])])
        for x in X_1.get_values():
            x = np.array([x])
            v = np.dot((x - np.array([mu1])).T, (x - np.array([mu1])))
            vs.append(v)
        sigma1 = np.mean(vs, axis=0)


        dx = []
        for t in test:
            g0 = g(mu0, sigma0, theta0, t)
            g1 = g(mu1, sigma1, theta1, t)
            d = g1 - g0
            # print("g0-g1", g0, g1, d)
            dx.append(d)
        return dx


        matrix, accuracy, error, prf = tevaluate(dx, testX, testY)

        print(matrix, accuracy, error, prf)



tcrossValidate(org_x, org_y)


