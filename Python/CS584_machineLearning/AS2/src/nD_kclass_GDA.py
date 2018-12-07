import math
import numpy as np
import pandas as pd
from sklearn.metrics import confusion_matrix
from sklearn.metrics import accuracy_score
from sklearn.metrics import precision_recall_fscore_support
from sklearn.cross_validation import KFold

iris = pd.read_csv('/Users/michael/Codes/bitbucket/cs584-s18-kaiyue-ma/AS2/data/iris.csv', encoding="gbk")
iris = iris.sample(frac=1.0)
dummy = pd.get_dummies(iris['Species'])
iris = pd.concat([iris, dummy], axis=1)


org_x = np.array(iris.iloc[:, 1:4])
org_y1 = np.array(iris['setosa']).reshape(len(iris), 1)
org_y2 = np.array(iris['versicolor']).reshape(len(iris), 1)
org_y3 = np.array(iris['virginica']).reshape(len(iris), 1)

iris_train = iris.sample(frac=0.8)
iris_test = iris.sample(frac=0.2)

X = iris_train.iloc[:, 1:4]
Y1 = np.array(iris_train['setosa']).reshape(len(iris_train), 1)
Y2 = np.array(iris_train['versicolor']).reshape(len(iris_train), 1)
Y3 = np.array(iris_train['virginica']).reshape(len(iris_train), 1)

X_t = iris_test.iloc[:, 1:4]
ntY3 = np.array(iris_test['setosa']).reshape(len(iris_test), 1)
ntY4 = np.array(iris_test['versicolor']).reshape(len(iris_test), 1)
ntY5 = np.array(iris_test['virginica']).reshape(len(iris_test), 1)

ntX_1 = X[Y1 == 1]
ntX_0 = X[Y1 == 0]
ntX_11 = X[Y2 == 1]
ntX_10 = X[Y2 == 0]
ntX_21 = X[Y3 == 1]
ntX_20 = X[Y3 == 0]
ntX1_1 = X_t[ntY3 == 1]
ntX1_0 = X_t[ntY3 == 0]
ntX1_11 = X_t[ntY4 == 1]
ntX1_10 = X_t[ntY4 == 0]
ntX1_21 = X_t[ntY5 == 1]
ntX1_20 = X_t[ntY5 == 0]


def getSigma(X, mu):
    vs = []
    mu = np.array([mu])
    for x in X:
        x = np.array([x])
        v = np.dot((x - mu).T, (x - mu))
        vs.append(v)
    sigma = np.mean(vs, axis=0)
    return sigma




def twongetParameters(tX, tY):
    theta1 = Y1.mean()
    theta2 = Y2.mean()
    theta3 = Y3.mean()
    mu1 = ntX_1.mean()
    mu2 = ntX_11.mean()
    mu3 = ntX_21.mean()
    var1 = ntX_1.var()
    var2 = ntX_11.var()
    var3 = ntX_21.var()

    sigma1 = getSigma(ntX_1.get_values(), mu1)
    sigma2 = getSigma(ntX_11.get_values(), mu2)
    sigma3 = getSigma(ntX_21.get_values(), mu3)
    return theta1, theta2, theta3, mu1, mu2, mu3, var1, var2, var3, sigma1, sigma2, sigma3
    print("sigma1,2,3", sigma1, sigma2, sigma3)


def g(mu, sigma, theta, x):
    n = len(x)
    det = np.linalg.det(sigma) ** 0.5
    c = 1 / ((2 * math.pi) ** (n / 2) * det)
    b = np.dot(np.dot((x - mu), np.linalg.inv(sigma)), (x - mu).T)
    d = math.exp(-1 / 2 * b)
    e = c * d

    return math.log(e) + math.log(theta)





def twonClassify(X, Y, test):
    theta1, theta2, theta3, mu1, mu2, mu3, var1, var2, var3, sigma1, sigma2, sigma3 = twongetParameters(X, Y)
    # print("twoClass", mu0, mu1, sigma0, sigma1, theta0, theta1)
    tdx = []
    for t in test:
        g2 = g(mu2, sigma2, theta2, t)
        g1 = g(mu1, sigma1, theta1, t)
        d = g1 - g2
        # print("g0-g1", g0, g1, d)
        tdx.append(d)
    return tdx




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




def tncrossValidate(X, Y):
    n = len(X)
    kf = KFold(n, n_folds=5)

    for trainIndex, testIndex in kf:
        trainY, testY = Y[trainIndex], Y[testIndex]
        trainX, testX = X[trainIndex], X[testIndex]

        dx = twonClassify(trainX, trainY, testX)

        matrix, accuracy, error, prf = tevaluate(dx, testX, testY)

        print("matrix", matrix, "accuracy", accuracy, "error", error, "prf", prf)


tncrossValidate(org_x, org_y2)

