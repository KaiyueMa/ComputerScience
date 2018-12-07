import numpy as np
from numpy import *
import matplotlib.pyplot as plt
from sklearn.model_selection import train_test_split
from sklearn import linear_model

# read data from file
origin_data = np.loadtxt('/Users/michael/Codes/Python/AS1/data/svar-set1.txt')
arr = np.reshape(origin_data, (200, 2))
# print(arr)
# plot scatter graph
plt.scatter(arr[:, 0], arr[:, 1], color='black')
plt.show()

# separate data into training and test

X_train, X_test, y_train, y_test = train_test_split(arr[:, 0], arr[:, 1],
                                                    test_size=0.75, random_state=0)
xMat_train = mat(np.reshape(X_train, (-1, 1)))
yMat_train = mat(np.reshape(y_train, (-1, 1)))
xMat_test = mat(np.reshape(X_test,(-1,1)))
yMat_test = mat(np.reshape(y_test,(-1,1)))

# print(xMat_train)
a1 = np.ones((50,1))

xMat_train = np.column_stack((a1, xMat_train))
# print(xMat_train)
KX = (xMat_train.T * xMat_train)
# if np.lianlg.det(KX)==0 :
#     print('error')
theta = KX.I * (xMat_train.T * yMat_train)
print('theta = %s' %theta)

# training error
train_error = xMat_train * theta - yMat_train
print('training error is : %s' % train_error)
avg_train_error = np.mean(train_error, axis=0)
print('average error of training set is : %s' %avg_train_error)
# testing error
a1 = np.ones((150,1))
xMat_test = np.column_stack((a1, xMat_test))
test_error = xMat_test * theta - yMat_test
print('testing error is : %s' % test_error)
avg_test_error = np.mean(test_error, axis=0)
print('average error of testing set is : %s' %avg_test_error)


fig = plt.figure()
ax = fig.add_subplot(111)
ax.scatter(X_train, y_train, color='m', label='data', marker='.')

xCopy = xMat_train.copy()
xCopy.sort(0)
yPred=xCopy*theta
ax.plot(xCopy[:, 1],yPred, color='y', linewidth=2, label='linear model')
plt.legend()
plt.show()

# Python function
model = linear_model.LinearRegression()
model.fit(X_train.reshape(-1,1), y_train)
plt.scatter(X_train, y_train, color='m', label='data', marker='.')
plt.plot(X_train, model.predict(X_train.reshape(-1,1)), color='g',
         linewidth=1,label='Python function')
plt.plot(xCopy[:, 1],yPred, color='y', linewidth=1, label='linear model')
plt.legend()
plt.show()

# poly
fig = plt.figure()
ax = fig.add_subplot(111)

#order
order = 2

x = X_train
y = y_train

xa = []
ya = []
for idx, xx in enumerate(x):
    yy = y[idx]
    ax.plot(x, y, color='m', linestyle='', marker='.')
    xa.append(xx)
    ya.append(yy)

bigMat = []
for j in range(0,2*order+1):
    sum = 0
    for i in range(0,len(xa)):
        sum += (xa[i]**j)
    bigMat.append(sum)

#calculate the coefficient matrix
matA = []
for rowNum in range(0,order+1):
    row = bigMat[rowNum:rowNum+order+1]
    matA.append(row)

matA = np.array(matA)

matB=[]
for i in range(0,order+1):
    ty = 0.0
    for k in range(0,len(xa)):
        ty += ya[k]*(xa[k]**i)
    matB.append(ty)

matB = np.array(matB)
matAA = np.linalg.solve(matA,matB)

#plot curve
xxa = np.arange(np.min(X_train),np.max(X_train),0.01)
yya = []
for i in range(0,len(xxa)):
    yy=0.0
    for j in range(0,order+1):
        dy = (xxa[i]**j)
        dy *= matAA[j]
        yy += dy
    yya.append(yy)
ax.plot(xxa,yya,color='g',linestyle='-',marker='', label= 'poly model')

plt.plot(xCopy[:, 1],yPred, color='y', linewidth=1, label='linear model')

ax.legend()
plt.title('poly method order is %s'%(order))
plt.show()

#test error
if order == 2:
    xMat_test = np.delete(xMat_test, 0, axis=1)


    xMat_test2 = np.power(xMat_test,2)
    x1t = xMat_test.T
    x2t = xMat_test2.T
    a1 = np.ones((150,1))
    poly_mat = np.row_stack((a1.T, x1t, x2t))

    result = np.dot(poly_mat.T, matAA)
    error_poly = result - y_test
    avg_poly_error = np.mean(mat(error_poly), axis=0)
    print('poly method order is %s' % (order))
    print('error is : %s' % avg_poly_error)

# elif order == 3:
#     xMat_test2 = np.power(xMat_test, 2)
#     xMat_test3 = np.power(xMat_test, 3)
#     x1t = xMat_test.T
#     x2t = xMat_test2.T
#     x3t = xMat_test3.T
#     a1 = np.ones((40, 1))
#     poly_mat = np.row_stack((a1.T, x1t, x2t, x3t))
#
#     result = np.dot(poly_mat.T, matAA)
#     print('poly method order is %s' % (order))
#     print((result - y_test))

else: print('error')


