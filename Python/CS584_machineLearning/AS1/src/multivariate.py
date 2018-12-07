from numpy import *
import numpy as np
import matplotlib.pyplot as plt
from sklearn.model_selection import train_test_split
from sklearn import linear_model

# read data from file
origin_data = np.loadtxt('/Users/michael/Codes/Python/AS1/data/mvar-set1.txt')
arr = np.reshape(origin_data, (2500, 3))

train = arr[:, 0:2]
test = arr[:, 2]

# print(test)
x_train, x_test, y_train, y_test = train_test_split(train, test,
                                                    test_size=0.1, random_state=0)

xMat_train = mat(np.reshape(x_train, (-1, 2)))
yMat_train = mat(np.reshape(y_train, (-1, 1)))
xMat_test = mat(np.reshape(x_test,(-1,2)))
yMat_test = mat(np.reshape(y_test,(-1,1)))

# build high dimension train
xMat_train1_2 = np.power(xMat_train[:, 0], 2)
xMat_train2_2 = np.power(xMat_train[:, 1], 2)
x_train12 = x_train[:, 0] * x_train[:, 1]
xMat_train12 = mat(np.reshape(x_train12,(-1,1)))
a1 = np.ones((2250, 1))
map_mat_train = np.column_stack((a1, xMat_train[:, 0], xMat_train[:, 1], xMat_train12,
                                 xMat_train1_2, xMat_train2_2))
#bulid high dimension test
xMat_test1_2 = np.power(xMat_test[:, 0], 2)
xMat_test2_2 = np.power(xMat_test[:, 1], 2)
x_test12 = x_test[:, 0] * x_test[:, 1]
xMat_test12 = mat(np.reshape(x_test12,(-1,1)))
a1 = np.ones((250, 1))
map_mat_test = np.column_stack((a1, xMat_test[:, 0], xMat_test[:, 1], xMat_test12,
                                 xMat_test1_2, xMat_test2_2))

print('6D training set is : %s' %map_mat_train)
print('6D testing set is : %s' %map_mat_test)
# print(a1)
# print(xMat_train[:, 0])
# print(xMat_train[:, 1])
# print(xMat_train12)
# print(xMat_train1_2)
# print(xMat_train2_2)
# s1 = shape(xMat_train[:, 0])
# s2 = shape(xMat_train[:, 0])
# s3 = shape(xMat_train[:, 0])
# s4 = shape(xMat_train[:, 0])
# s5 = shape(xMat_train[:, 0])
# print(s1)

# Multivariate regression
Gram = np.dot(map_mat_train.T, map_mat_train)
theta = Gram.I * map_mat_train.T * yMat_train

# RSE multivariate
result_train = map_mat_train * theta
error_train = result_train - yMat_train
result_test = map_mat_test * theta
error_test = result_test - yMat_test
print('6D Multivariate regression testing error : %s' %error_test)
# err_size = len(error)
# total_err = sum(error)
# print(total_err/ err_size)
Rse_train = np.mean(error_train, axis=0)
print('6D Multivariate regression RSE of the training set is : %s' %Rse_train)
Rse_test = np.mean(error_test, axis=0)
print('6D Multivariate regression RSE of the testing set is : %s' %Rse_test)

# Linear Regression
Gram_l = xMat_train.T * xMat_train
theta_l = Gram_l.I * xMat_train.T * yMat_train

# RSE linear Regression
result_l_train = xMat_train * theta_l
error_l_train = result_l_train - yMat_train
Rse_l_train = np.mean(error_l_train, axis=0)
print('2D Multivariate regression RSE of the training set is : %s' %Rse_l_train)
result_l_test = xMat_test * theta_l
error_l_test = result_l_test - yMat_test
Rse_l_test = np.mean(error_l_test, axis=0)
print('2D Multivariate regression testing error : %s' %error_l_test)
print('2D Multivariate regression RSE of the testing set is : %s' %Rse_l_test)

print('explicit solution the theta is : %s'%theta_l.T)

def stochastic_gradient_descent(x,y,theta,alpha,m,max_iter):
    deviation = 1
    iter = 0
    flag = 0
    while True:
        for i in range(m):
            deviation = 0
            h = theta[0] * x[i][0] + theta[1] * x[i][1]
            theta[0] = theta[0] + alpha * (y[i] - h)*x[i][0]
            theta[1] = theta[1] + alpha * (y[i] - h)*x[i][1]

            iter = iter + 1
            for i in range(m):
                deviation = deviation + (y[i] - (theta[0] * x[i][0] + theta[1] * x[i][1])) ** 2
            if deviation <EPS or iter >max_iter:
                flag = 1
                break
        if flag == 1 :
            break
    return theta, iter


matrix_x = x_train
matrix_y = y_train
MAX_ITER = 20000
EPS = 0.0001
theta = [1,1]
ALPHA = 0.1

resultTheta,iters = stochastic_gradient_descent(matrix_x, matrix_y, theta, ALPHA, 5, MAX_ITER)
print('stochastic gradient descent(iterative solution) theta is: %s' %resultTheta)
print('# of iters is %s' % iters)
result_i_train = xMat_train * mat(resultTheta).T
error_i_train = result_i_train - yMat_train
Rse_i_train = np.mean(error_i_train, axis=0)
print('stochastic gradient descent(iterative solution) RSE of the'
      ' training set is : %s' %Rse_i_train)

result_i_test = xMat_test * mat(resultTheta).T
error_i_test = result_i_test - yMat_test
Rse_i_test = np.mean(error_i_test, axis=0)
print('stochastic gradient descent(iterative solution) RSE of the'
      ' testing set is : %s' %Rse_i_test)

def gaussian_2d_kernel(kernel_size = 3,sigma = 0):

    kernel = np.zeros([kernel_size,kernel_size])
    center = kernel_size//2

    if sigma == 0:
        sigma = ((kernel_size-1)*0.5 - 1)*0.3 + 0.8

    s = 2*(sigma**2)
    sum_val = 0
    for i in range(0,kernel_size):
        for j in range(0,kernel_size):
            x = i-center
            y = j-center
            kernel[i,j] = np.exp(-(x**2+y**2) / s)
            sum_val += kernel[i,j]
            #/(np.pi * s)
    sum_val = 1/sum_val
    return kernel*sum_val

# gaussian_2d_kernel(xMat_train,yMat_train)
#
# def gaussian_kernel_2d_opencv(kernel_size = 3,sigma = 0):
#     kx = cv2.getGaussianKernel(kernel_size,sigma)
#     ky = cv2.getGaussianKernel(kernel_size,sigma)
#     return np.multiply(kx,np.transpose(ky))