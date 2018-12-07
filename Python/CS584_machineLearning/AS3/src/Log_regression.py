# -*- coding:utf-8 -*-
import numpy as np
import matplotlib.pyplot as plt
from sklearn.model_selection import train_test_split



def load_data_set():
    """
    read in data set
    """
    data_arr = []
    label_arr = []
    f = open('/Users/michael/Codes/bitbucket/cs584-s18-kaiyue-ma/AS3/data/TestSet.txt', 'r')
    for line in f.readlines():
        line_arr = line.strip().split()

        data_arr.append([1.0, np.float(line_arr[0]), np.float(line_arr[1])])
        label_arr.append(float(line_arr[2]))

    # print(data_arr)
    # x1_2 = data_arr[:, 1]
    # print(x1_2)
    # print('.....')
    # print(label_arr)

    data_arr, test_arr, label_arr, test_label = train_test_split(data_arr, label_arr, test_size=0.2, random_state=42)

    return data_arr, test_arr, label_arr, test_label


def mapFeature(X1, X2):
    """
    adding 1 column [1, x1, x2]
    """
    degree = 1;
    out = np.ones((X1.shape[0], 1))

    for i in np.arange(1, degree + 1):
        for j in range(i + 1):
            temp = X1 ** (i - j) * (X2 ** j)
            out = np.hstack((out, temp.reshape(-1, 1)))
    return out

def mapFeature2(X1, X2):
    """
    mapping 2D to 6D: [1, x1, x2, x1^2, x1*x2, x2^2]
    """
    degree = 2;
    out = np.ones((X1.shape[0], 1))

    for i in np.arange(1, degree + 1):
        for j in range(i + 1):
            temp = X1 ** (i - j) * (X2 ** j)
            out = np.hstack((out, temp.reshape(-1, 1)))
    return out


def sigmoid(x):
    return 1.0 / (1 + np.exp(-x))


def grad_ascent(data_arr, class_labels):
    """
    maximize log likelihood

    """
    # turn the data_arr to numpy matrix
    data_mat = np.mat(data_arr)
    label_mat = np.mat(class_labels).transpose()
    m, n = np.shape(data_mat)

    alpha = 0.001
    max_cycles = 500

    weights = np.ones((n, 1))
    for k in range(max_cycles):
        h = sigmoid(data_mat * weights)

        error = label_mat - h
        weights = weights + alpha * data_mat.transpose() * error
    return weights

def grad_ascent_nonlinear(data_arr, class_labels):
    """
    applying non-linear method
    maximize log likelihood
    """
    # turn the data_arr to numpy matrix
    data_mat = (np.mat(data_arr)).getA()
    # print(data_mat)
    data_mat = mapFeature2(data_mat[:, 1], data_mat[:, 2])
    # print(data_mat)
    data_mat = np.mat(data_mat)
    # print(np.shape(data_mat))
    # x1_2 = np.power(data_mat[:, 1], 2)
    # x2_2 = np.power(data_mat[:, 2], 2)
    # x1x2 = np.multiply(data_mat[:, 1], data_mat[:, 2])
    # data_mat = np.c_[data_mat, x1_2]
    # data_mat = np.c_[data_mat, x2_2]
    # data_mat = np.c_[data_mat, x1x2]
    # print(data_mat)
    label_mat = np.mat(class_labels).transpose()
    m, n = np.shape(data_mat)

    alpha = 0.001
    max_cycles = 500

    weights = np.ones((n, 1))
    # print(weights)
    # print(data_mat)
    # gx = np.dot(data_mat, weights)
    # print(gx)
    # print('；；；；；；')
    # print(data_mat * weights)
    for k in range(max_cycles):
        h = sigmoid(data_mat * weights)
        # print(h)

        error = label_mat - h
        weights = weights + alpha * data_mat.transpose() * error
    return weights


def stoc_grad_ascent0(data_mat, class_labels):
    """
    SGD-using one example update weight
    """
    m, n = np.shape(data_mat)
    alpha = 0.01
    weights = np.ones(n)
    for i in range(m):
        h = sigmoid(sum(data_mat[i] * weights))
        error = class_labels[i] - h
        weights = weights + alpha * error * data_mat[i]
    return weights


def stoc_grad_ascent1(data_mat, class_labels, num_iter=150):
    """
    SGD-using random one example update weight
    """
    m, n = np.shape(data_mat)
    weights = np.ones(n)
    for j in range(num_iter):

        data_index = list(range(m))
        for i in range(m):

            alpha = 4 / (1.0 + j + i) + 0.01

            rand_index = int(np.random.uniform(0, len(data_index)))
            h = sigmoid(np.sum(data_mat[data_index[rand_index]] * weights))
            error = class_labels[data_index[rand_index]] - h
            weights = weights + alpha * error * data_mat[data_index[rand_index]]
            del(data_index[rand_index])
    return weights


def plot_best_fit(weights):
    """
    plot the training data
    """
    import matplotlib.pyplot as plt
    data_mat, test_mat, label_mat, test_label = load_data_set()
    data_arr = np.array(data_mat)
    n = np.shape(data_mat)[0]
    x_cord1 = []
    y_cord1 = []
    x_cord2 = []
    y_cord2 = []
    for i in range(n):
        if int(label_mat[i]) == 1:
            x_cord1.append(data_arr[i, 1])
            y_cord1.append(data_arr[i, 2])
        else:
            x_cord2.append(data_arr[i, 1])
            y_cord2.append(data_arr[i, 2])
    fig = plt.figure()
    ax = fig.add_subplot(111)
    ax.scatter(x_cord1, y_cord1, s=30, color='k', marker='^')
    ax.scatter(x_cord2, y_cord2, s=30, color='red', marker='s')
    x = np.arange(-3.0, 3.0, 0.1)
    y = (-weights[0] - weights[1] * x) / weights[2]

    ax.plot(x, y)
    plt.xlabel('x1')
    plt.ylabel('y1')
    plt.show()


def plotDecisionBoundary(theta, X, y):
    """
    plot testing data 3D (with 1st column all 1s)
    """
    y = y[0]

    pos = np.where(y == 1)
    neg = np.where(y == 0)
    # print(pos)

    plt.figure(figsize=(15, 12))
    plt.plot(X[pos, 1], X[pos, 2], 'ro')
    plt.plot(X[neg, 1], X[neg, 2], 'bo')
    plt.title("decision boundary_test")

    u = np.linspace(-5, 12, 50)
    v = np.linspace(-5, 12, 50)

    z = np.zeros((len(u), len(v)))
    for i in range(len(u)):
        for j in range(len(v)):
            z[i, j] = np.dot(mapFeature(u[i].reshape(1, -1), v[j].reshape(1, -1)), theta)  # 计算对应的值，需要map

    z = np.transpose(z)
    plt.contour(u, v, z, [0, 0.01], linewidth=2.0)
    # plt.legend()
    plt.show()



def plotDecisionBoundary2(theta, X, y):
    """
        plot testing data 6D (with 1st column all 1s)
    """
    # print(y)
    # y = np.array(y)
    pos = np.where(y == 1)
    neg = np.where(y == 0)

    # print(X)

    plt.figure(figsize=(15, 12))
    plt.plot(X[pos, 1], X[pos, 2], 'ro')
    plt.plot(X[neg, 1], X[neg, 2], 'bo')
    plt.title("decision boundary_test")

    u = np.linspace(-3, 3, 50)
    v = np.linspace(-5, 20, 50)

    z = np.zeros((len(u), len(v)))
    for i in range(len(u)):
        for j in range(len(v)):
            z[i, j] = np.dot(mapFeature2(u[i].reshape(1, -1), v[j].reshape(1, -1)), theta)

    z = np.transpose(z)
    plt.contour(u, v, z, [0, 0.01], linewidth=2.0)
    # plt.legend()
    plt.show()





def test():
    """
    test dataset
    """
    data_arr, test_arr, class_labels, test_labels= load_data_set()

    # print(class_labels)

    weights1 = grad_ascent(data_arr, class_labels).getA()
    print('linear function theta: %s' %weights1)
    # weights2 = stoc_grad_ascent0(np.array(data_arr), class_labels)
    # weights3 = stoc_grad_ascent1(np.array(data_arr), class_labels)
    plot_best_fit(weights1)
    # plot_best_fit(weights2)
    # plot_best_fit(weights3)
    test_arr = (np.mat(test_arr)).getA()
    test_labels = (np.mat(test_labels)).getA()
    p = predict(test_arr, weights1)
    print('linear function on testing set accuracy: %f%%' % np.mean(np.float64(p == test_labels.transpose()) * 100))
    plotDecisionBoundary(weights1, test_arr, test_labels)



    weights_nonlinear = grad_ascent_nonlinear(data_arr, class_labels).getA()
    print('non-linear function theta: %s' % weights_nonlinear)
    test_mat = (np.mat(test_arr)).getA()
    # print(data_mat)
    test_arr_hd = mapFeature2(test_mat[:, 1], test_mat[:, 2])
    # print(data_arr_hd)
    pn = predict(test_arr_hd, weights_nonlinear)
    print('non-linear function on testing set accuracy:%f%%' % np.mean(np.float64(pn == test_labels.transpose()) * 100))
    #
    # # print(weights_nonlinear)
    # # plot_best_fit(weights_nonlinear)
    #
    #
    plotDecisionBoundary2(weights_nonlinear, test_arr_hd, test_labels)
    # plotDecisionBoundary(result, X, y)



def colic_test():
    """
    using training set and testing set from UCI----use gradient descent
    """
    f_train = open('/Users/michael/Codes/bitbucket/cs584-s18-kaiyue-ma/AS3/data/HorseColicTraining.txt', 'r')
    f_test = open('/Users/michael/Codes/bitbucket/cs584-s18-kaiyue-ma/AS3/data/HorseColicTest.txt', 'r')
    training_set = []
    training_labels = []

    for line in f_train.readlines():
        curr_line = line.strip().split('\t')
        if len(curr_line) == 1:
            continue    # if empty element jump out this loop
        line_arr = [float(curr_line[i]) for i in range(21)]
        training_set.append(line_arr)
        training_labels.append(float(curr_line[21]))

    train_weights = grad_ascent(np.array(training_set), training_labels)
    error_count = 0
    num_test_vec = 0.0


    for line in f_test.readlines():
        num_test_vec += 1
        curr_line = line.strip().split('\t')
        if len(curr_line) == 1:
            continue    # if empty element jump out this loop
        line_arr = [float(curr_line[i]) for i in range(21)]
        if int(classify_vector(np.array(line_arr), train_weights)) != int(curr_line[21]):
            error_count += 1
    error_rate = error_count / num_test_vec
    print('the error rate is {}'.format(error_rate))
    return error_rate




def colic_test_SGD():
    """
    using training set and testing set from UCI----use stochastic gradient descent
    """
    f_train = open('/Users/michael/Codes/bitbucket/cs584-s18-kaiyue-ma/AS3/data/HorseColicTraining.txt', 'r')
    f_test = open('/Users/michael/Codes/bitbucket/cs584-s18-kaiyue-ma/AS3/data/HorseColicTest.txt', 'r')
    training_set = []
    training_labels = []

    for line in f_train.readlines():
        curr_line = line.strip().split('\t')
        if len(curr_line) == 1:
            continue    # if empty element jump out this loop
        line_arr = [float(curr_line[i]) for i in range(21)]
        training_set.append(line_arr)
        training_labels.append(float(curr_line[21]))

    # SGD
    train_weights = stoc_grad_ascent0(np.array(training_set), training_labels)
    error_count = 0
    num_test_vec = 0.0

    for line in f_test.readlines():
        num_test_vec += 1
        curr_line = line.strip().split('\t')
        if len(curr_line) == 1:
            continue    # if empty element jump out this loop
        line_arr = [float(curr_line[i]) for i in range(21)]
        if int(classify_vector(np.array(line_arr), train_weights)) != int(curr_line[21]):
            error_count += 1
    error_rate = error_count / num_test_vec
    print('the error rate is {}'.format(error_rate))
    return error_rate



def colic_test_randomSGD():
    """
    using training set and testing set from UCI----use random sthchastic gradient descent
    """
    f_train = open('/Users/michael/Codes/bitbucket/cs584-s18-kaiyue-ma/AS3/data/HorseColicTraining.txt', 'r')
    f_test = open('/Users/michael/Codes/bitbucket/cs584-s18-kaiyue-ma/AS3/data/HorseColicTest.txt', 'r')
    training_set = []
    training_labels = []

    for line in f_train.readlines():
        curr_line = line.strip().split('\t')
        if len(curr_line) == 1:
            continue    # if empty element jump out this loop
        line_arr = [float(curr_line[i]) for i in range(21)]
        training_set.append(line_arr)
        training_labels.append(float(curr_line[21]))

    # random SGD
    train_weights = stoc_grad_ascent1(np.array(training_set), training_labels, 500)
    error_count = 0
    num_test_vec = 0.0

    for line in f_test.readlines():
        num_test_vec += 1
        curr_line = line.strip().split('\t')
        if len(curr_line) == 1:
            continue    # if empty element jump out this loop
        line_arr = [float(curr_line[i]) for i in range(21)]
        if int(classify_vector(np.array(line_arr), train_weights)) != int(curr_line[21]):
            error_count += 1
    error_rate = error_count / num_test_vec
    print('the error rate is {}'.format(error_rate))
    return error_rate


def multi_test():
    """
    run 10 times and find the average
    could be used in every function
    """
    num_tests = 20
    error_sum = 0
    for k in range(num_tests):
        error_sum += colic_test_randomSGD()
    print('after {} iteration the average error rate is {}'.format(num_tests, error_sum / num_tests))


def classify_vector(in_x, weights):
    # print(np.sum(in_x * weights))
    prob = sigmoid(np.sum(in_x * weights))
    if prob > 0.5:
        return 1.0
    return 0.0

def predict(X, theta):
    m = X.shape[0]
    p = np.zeros((m, 1))
    p = sigmoid(np.dot(X, theta))  # calculate the possibility

    for i in range(m):
        if p[i] > 0.5:  # if>0.5==1 else 0
            p[i] = 1
        else:
            p[i] = 0
    return p


if __name__ == '__main__':

    test()
    print('using separate dataset on horse-colic.data AND horse-colic.test')
    print('GD:')
    colic_test()
    print('SGD:')
    colic_test_SGD()
    print('random SGD:')
    colic_test_randomSGD()
    print('multi SGD (20 times avg):')
    multi_test()

