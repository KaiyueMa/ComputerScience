# -*- coding:utf-8 -*-
import numpy as np
import matplotlib.pyplot as plt
import pandas as pd
# ------使用 Logistic 回归在简单数据集上的分类-----------


def load_data_set():



    # data_arr = []
    # label_arr = []
    # f = open('/Users/michael/Codes/github/Clone/MachineLearning/input/5.Logistic/TestSet.txt', 'r')
    # for line in f.readlines():
    #     line_arr = line.strip().split()
    #     # 为了方便计算，我们将 X0 的值设为 1.0 ，也就是在每一行的开头添加一个 1.0 作为 X0
    #     data_arr.append([1.0, np.float(line_arr[0]), np.float(line_arr[1])])
    #     label_arr.append(float(line_arr[2]))

    iris = pd.read_csv('/Users/michael/Codes/bitbucket/cs584-s18-kaiyue-ma/AS2/data/iris.csv', encoding="gbk")
    iris = iris.sample(frac=1.0)

    dummy = pd.get_dummies(iris['Species'])
    iris = pd.concat([iris, dummy], axis=1)

    org_x = np.array(iris.iloc[:, 1:5])
    # org_y = np.array(iris['setosa']).reshape(len(iris), 1)
    # org_y = np.array(iris['setosa'])

    org_y1 = np.array(iris['setosa'])
    org_y2 = np.array(iris['versicolor'])
    org_y3 = np.array(iris['virginica'])

    x = org_x.tolist()
    y1 = org_y1.tolist()
    y2 = org_y2.tolist()
    y3 = org_y3.tolist()
    x = np.mat(x)
    one = np.ones(len(x))
    x = np.column_stack((one, x))
    x = x.tolist()



    # print(data_arr)
    # x1_2 = data_arr[:, 1]
    # print(x1_2)
    # print('.....')
    # print(label_arr)
    return x, y1, y2, y3


def mapFeature(X1, X2):
    degree = 1;  # 映射的最高次方
    out = np.ones((X1.shape[0], 1))  # 映射后的结果数组（取代X）

    for i in np.arange(1, degree + 1):
        for j in range(i + 1):
            temp = X1 ** (i - j) * (X2 ** j)  # 矩阵直接乘相当于matlab中的点乘.*
            out = np.hstack((out, temp.reshape(-1, 1)))
    return out

def mapFeature2(X1, X2):
    degree = 2;  # 映射的最高次方
    out = np.ones((X1.shape[0], 1))  # 映射后的结果数组（取代X）
    '''
    这里以degree=2为例，映射为1,x1,x2,x1^2,x1,x2,x2^2
    '''
    for i in np.arange(1, degree + 1):
        for j in range(i + 1):
            temp = X1 ** (i - j) * (X2 ** j)  # 矩阵直接乘相当于matlab中的点乘.*
            out = np.hstack((out, temp.reshape(-1, 1)))
    return out


# def nonlinear_data(data_arr):
#     # 将数据[1,x1,x2]变为[1,x1,x2,x1^2,x2^2,x1x2]
#     x1_2 = data_arr[1]^2
#     print(x1_2)
#     highD_data_arr = 0
#     return highD_data_arr

def sigmoid(x):
    # 这里其实非常有必要解释一下，会出现的错误 RuntimeWarning: overflow encountered in exp
    # 这个错误在学习阶段虽然可以忽略，但是我们至少应该知道为什么
    # 这里是因为我们输入的有的 x 实在是太小了，比如 -6000之类的，那么计算一个数字 np.exp(6000)这个结果太大了，没法表示，所以就溢出了
    # 如果是计算 np.exp（-6000），这样虽然也会溢出，但是这是下溢，就是表示成零
    # 去网上搜了很多方法，比如 使用bigfloat这个库（我竟然没有安装成功，就不尝试了，反正应该是有用的
    return 1.0 / (1 + np.exp(-x))


def grad_ascent(data_arr, class_labels):
    """
    梯度上升法，其实就是因为使用了极大似然估计，这个大家有必要去看推导，只看代码感觉不太够
    :param data_arr: 传入的就是一个普通的数组，当然你传入一个二维的ndarray也行
    :param class_labels: class_labels 是类别标签，它是一个 1*100 的行向量。
                    为了便于矩阵计算，需要将该行向量转换为列向量，做法是将原向量转置，再将它赋值给label_mat
    :return:
    """
    # 注意一下，我把原来 data_mat_in 改成data_arr,因为传进来的是一个数组，用这个比较不容易搞混
    # turn the data_arr to numpy matrix
    data_mat = np.mat(data_arr)
    # 变成矩阵之后进行转置
    label_mat = np.mat(class_labels).transpose()
    # label_mat = np.mat(class_labels)
    # m->数据量，样本数 n->特征数
    m, n = np.shape(data_mat)
    # 学习率，learning rate
    alpha = 0.001
    # 最大迭代次数，假装迭代这么多次就能收敛2333
    max_cycles = 500
    # 生成一个长度和特征数相同的矩阵，此处n为3 -> [[1],[1],[1]]
    # weights 代表回归系数， 此处的 ones((n,1)) 创建一个长度和特征数相同的矩阵，其中的数全部都是 1
    weights = np.ones((n, 1))
    for k in range(max_cycles):
        # 这里是点乘  m x 3 dot 3 x 1
        h = sigmoid(data_mat * weights)
        error = label_mat - h
        # 这里比较建议看一下推导，为什么这么做可以，这里已经是求导之后的
        weights = weights + alpha * data_mat.transpose() * error
    return weights

def grad_ascent_nonlinear(data_arr, class_labels):
    """
    梯度上升法，其实就是因为使用了极大似然估计，这个大家有必要去看推导，只看代码感觉不太够
    :param data_arr: 传入的就是一个普通的数组，当然你传入一个二维的ndarray也行
    :param class_labels: class_labels 是类别标签，它是一个 1*100 的行向量。
                    为了便于矩阵计算，需要将该行向量转换为列向量，做法是将原向量转置，再将它赋值给label_mat
    :return:
    """
    # 注意一下，我把原来 data_mat_in 改成data_arr,因为传进来的是一个数组，用这个比较不容易搞混
    # turn the data_arr to numpy matrix
    data_mat = (np.mat(data_arr)).getA()
    # print(data_mat)
    data_mat = mapFeature2(data_mat[:, 1], data_mat[:, 2])
    # print(data_mat)
    data_mat = np.mat(data_mat)
    # print(np.shape(data_mat))
    # 提升原数组维度，至6D——————————X的维度
    # x1_2 = np.power(data_mat[:, 1], 2)
    # x2_2 = np.power(data_mat[:, 2], 2)
    # x1x2 = np.multiply(data_mat[:, 1], data_mat[:, 2])
    # data_mat = np.c_[data_mat, x1_2]
    # data_mat = np.c_[data_mat, x2_2]
    # data_mat = np.c_[data_mat, x1x2]
    # print(data_mat)
    # 变成矩阵之后进行转置
    label_mat = np.mat(class_labels).transpose()
    # m->数据量，样本数 n->特征数
    m, n = np.shape(data_mat)
    # print(n)
    # 学习率，learning rate
    alpha = 0.001
    # 最大迭代次数，假装迭代这么多次就能收敛2333
    max_cycles = 500
    # 生成一个长度和特征数相同的矩阵，此处n为6 -> [[1],[1],[1],[1],[1],[1]]
    # weights 代表回归系数， 此处的 ones((n,1)) 创建一个长度和特征数相同的矩阵，其中的数全部都是 1
    weights = np.ones((n, 1))
    # print(weights)
    # print(data_mat)
    # gx = np.dot(data_mat, weights)
    # print(gx)
    # print('；；；；；；')
    # print(data_mat * weights)
    for k in range(max_cycles):
        # 这里是点乘  m x 3 dot 3 x 1

        h = sigmoid(data_mat * weights)
        # print(h)
        error = label_mat - h
        # 这里比较建议看一下推导，为什么这么做可以，这里已经是求导之后的
        weights = weights + alpha * data_mat.transpose() * error
    return weights


def stoc_grad_ascent0(data_mat, class_labels):
    """
    随机梯度上升，只使用一个样本点来更新回归系数
    :param data_mat: 输入数据的数据特征（除去最后一列）,ndarray
    :param class_labels: 输入数据的类别标签（最后一列数据）
    :return: 得到的最佳回归系数
    """
    m, n = np.shape(data_mat)
    alpha = 0.01
    weights = np.ones(n)
    for i in range(m):
        # sum(data_mat[i]*weights)为了求 f(x)的值， f(x)=a1*x1+b2*x2+..+nn*xn,
        # 此处求出的 h 是一个具体的数值，而不是一个矩阵
        h = sigmoid(sum(data_mat[i] * weights))
        error = class_labels[i] - h
        # 还是和上面一样，这个先去看推导，再写程序
        weights = weights + alpha * error * data_mat[i]
    return weights


def stoc_grad_ascent1(data_mat, class_labels, num_iter=150):
    """
    改进版的随机梯度上升，使用随机的一个样本来更新回归系数
    :param data_mat: 输入数据的数据特征（除去最后一列）,ndarray
    :param class_labels: 输入数据的类别标签（最后一列数据
    :param num_iter: 迭代次数
    :return: 得到的最佳回归系数
    """
    m, n = np.shape(data_mat)
    weights = np.ones(n)
    for j in range(num_iter):
        # 这里必须要用list，不然后面的del没法使用
        data_index = list(range(m))
        for i in range(m):
            # i和j的不断增大，导致alpha的值不断减少，但是不为0
            alpha = 4 / (1.0 + j + i) + 0.01
            # 随机产生一个 0～len()之间的一个值
            # random.uniform(x, y) 方法将随机生成下一个实数，它在[x,y]范围内,x是这个范围内的最小值，y是这个范围内的最大值。
            rand_index = int(np.random.uniform(0, len(data_index)))
            h = sigmoid(np.sum(data_mat[data_index[rand_index]] * weights))
            error = class_labels[data_index[rand_index]] - h
            weights = weights + alpha * error * data_mat[data_index[rand_index]]
            del(data_index[rand_index])
    return weights


def plot_best_fit(weights1, weights2, weights3):
    """
    可视化
    :param weights:
    :return:
    """
    import matplotlib.pyplot as plt
    # data_mat, label_mat = load_data_set()
    data_mat, label_mat1, label_mat2, label_mat3 = load_data_set()
    data_arr = np.array(data_mat)
    n = np.shape(data_mat)[0]
    x_cord1 = []
    y_cord1 = []
    x_cord2 = []
    y_cord2 = []
    x_cord3 = []
    y_cord3 = []
    # print(label_mat)
    for i in range(n):
        if int(label_mat1[i]) == 1:
            x_cord1.append(data_arr[i, 1])
            y_cord1.append(data_arr[i, 2])
        elif int(label_mat2[i]) == 1:
            x_cord2.append(data_arr[i, 1])
            y_cord2.append(data_arr[i, 2])
        else:
            x_cord3.append(data_arr[i, 1])
            y_cord3.append(data_arr[i, 2])
    fig = plt.figure()
    ax = fig.add_subplot(111)
    ax.scatter(x_cord1, y_cord1, s=30, color='k', marker='s')
    ax.scatter(x_cord2, y_cord2, s=30, color='red', marker='s')
    ax.scatter(x_cord3, y_cord3, s=30, color='blue', marker='s')
    x1 = np.arange(3.0, 8.0, 0.1)
    y1 = (-weights1[0] - weights1[1] * x1) / weights1[2]
    x2 = np.arange(3.0, 8.0, 0.1)
    y2 = (-weights2[0] - weights2[1] * x1) / weights2[2]
    x3 = np.arange(3.0, 8.0, 0.1)
    y3 = (-weights3[0] - weights3[1] * x1) / weights3[2]
    """
    y的由来，卧槽，是不是没看懂？
    首先理论上是这个样子的。
    dataMat.append([1.0, float(lineArr[0]), float(lineArr[1])])
    w0*x0+w1*x1+w2*x2=f(x)
    x0最开始就设置为1叻， x2就是我们画图的y值，而f(x)被我们磨合误差给算到w0,w1,w2身上去了
    所以： w0+w1*x+w2*y=0 => y = (-w0-w1*x)/w2   
    """
    ax.plot(x1, y1)
    plt.xlabel('x1')
    plt.ylabel('y1')
    ax.plot(x2, y2)
    plt.xlabel('x2')
    plt.ylabel('y2')
    ax.plot(x3, y3)
    plt.xlabel('x3')
    plt.ylabel('y3')
    plt.show()

def plotDecisionBoundary2(theta, X, y):
    # print(y)
    # y = np.array(y)
    pos = np.where(y == 1)  # 找到y==1的坐标位置
    neg = np.where(y == 0)  # 找到y==0的坐标位置

    # print(X)

    # 作图
    plt.figure(figsize=(15, 12))
    plt.plot(X[pos, 1], X[pos, 2], 'ro')  # red o
    plt.plot(X[neg, 1], X[neg, 2], 'bo')  # blue o
    plt.title("决策边界")

    # u = np.linspace(30,100,100)
    # v = np.linspace(30,100,100)

    u = np.linspace(-3, 3, 50)  # 根据具体的数据，这里需要调整
    v = np.linspace(-5, 20, 50)

    z = np.zeros((len(u), len(v)))
    for i in range(len(u)):
        for j in range(len(v)):
            z[i, j] = np.dot(mapFeature2(u[i].reshape(1, -1), v[j].reshape(1, -1)), theta)  # 计算对应的值，需要map

    z = np.transpose(z)
    plt.contour(u, v, z, [0, 0.01], linewidth=2.0)  # 画等高线，范围在[0,0.01]，即近似为决策边界
    # plt.legend()
    plt.show()

def plotDecisionBoundary(theta, X, y1, y2, y3):



    pos1 = np.where(y1 == 1)  # 找到y1==1的坐标位置
    pos2 = np.where(y2 == 1)  # 找到y2==1的坐标位置
    pos3 = np.where(y3 == 1)
    # print(pos)

    # 作图
    plt.figure(figsize=(15, 12))
    plt.plot(X[pos1, 1], X[pos1, 2], X[pos1, 3], X[pos1, 4], 'ro')  # red o
    plt.plot(X[pos2, 1], X[pos2, 2], X[pos2, 3], X[pos2, 4], 'black')
    plt.plot(X[pos3, 1], X[pos3, 2], X[pos3, 3], X[pos3, 4], 'bo')  # blue o
    plt.title("决策边界")
    plt.show()


    u = np.linspace(30,100,100)
    v = np.linspace(30,100,100)

    u = np.linspace(3, 8, 50)
    v = np.linspace(1, 6, 50)

    z = np.zeros((len(u), len(v)))
    for i in range(len(u)):
        for j in range(len(v)):
            z[i, j] = np.dot(mapFeature(u[i].reshape(1, -1), v[j].reshape(1, -1)), theta)  # 计算对应的值，需要map

    z = np.transpose(z)
    plt.contour(u, v, z, [0, 0.01], linewidth=2.0)  # 画等高线，范围在[0,0.01]，即近似为决策边界
    # plt.legend()
    plt.show()



def test():
    data_arr, class_labels1, class_labels2, class_labels3 = load_data_set()
    # print(class_labels)

    weights1 = grad_ascent(data_arr, class_labels1).getA()
    weights2 = grad_ascent(data_arr, class_labels2).getA()
    weights3 = grad_ascent(data_arr, class_labels3).getA()
    print('线性方法的theta1:%s' % weights1)
    print('线性方法的theta2:%s' % weights2)
    print('线性方法的theta3:%s' % weights3)
    # weights2 = stoc_grad_ascent0(np.array(data_arr), class_labels)
    # weights3 = stoc_grad_ascent1(np.array(data_arr), class_labels)
    # plot_best_fit(weights1, weights2, weights3)
    # plot_best_fit(weights2)
    # plot_best_fit(weights3)
    data_arr = (np.mat(data_arr)).getA()
    class_labels1 = np.mat(class_labels1)
    class_labels1 = class_labels1.transpose()
    class_labels2 = np.mat(class_labels2)
    class_labels2 = class_labels2.transpose()
    # print(class_labels1)
    p1 = predict(data_arr, weights1)
    # print(p1 - class_labels2)
    # print(np.float64(p1 == class_labels.transpose()) * 100)
    # print(class_labels1)
    print('linear function on testing set accuracy %f%%' % np.mean(np.float64(p1 == 1) * 100))
    # plotDecisionBoundary(weights1, data_arr, class_labels1, class_labels2, class_labels3)



    # weights_nonlinear = grad_ascent_nonlinear(data_arr, class_labels).getA()
    # print('非线性方法的theta%s' % weights_nonlinear)
    # data_mat = (np.mat(data_arr)).getA()
    # # print(data_mat)
    # data_arr_hd = mapFeature2(data_mat[:, 1], data_mat[:, 2])
    # # print(data_arr_hd)
    # pn = predict(data_arr_hd, weights_nonlinear)
    # print('NON-linear在训练集上的准确度为%f%%' % np.mean(np.float64(pn == class_labels.transpose()) * 100))
    #
    # # print(weights_nonlinear)
    # # plot_best_fit(weights_nonlinear)
    #
    #
    # plotDecisionBoundary2(weights_nonlinear, data_arr_hd, class_labels)
    # plotDecisionBoundary(result, X, y)





def colic_test():

    f_train = open('/Users/michael/Codes/github/Clone/MachineLearning/input/5.Logistic/HorseColicTraining.txt', 'r')
    f_test = open('/Users/michael/Codes/github/Clone/MachineLearning/input/5.Logistic/HorseColicTest.txt', 'r')
    training_set = []
    training_labels = []

    for line in f_train.readlines():
        curr_line = line.strip().split('\t')
        if len(curr_line) == 1:
            continue
        line_arr = [float(curr_line[i]) for i in range(21)]
        training_set.append(line_arr)
        training_labels.append(float(curr_line[21]))

    train_weights = stoc_grad_ascent1(np.array(training_set), training_labels, 500)
    error_count = 0
    num_test_vec = 0.0

    for line in f_test.readlines():
        num_test_vec += 1
        curr_line = line.strip().split('\t')
        if len(curr_line) == 1:
            continue
        line_arr = [float(curr_line[i]) for i in range(21)]
        if int(classify_vector(np.array(line_arr), train_weights)) != int(curr_line[21]):
            error_count += 1
    error_rate = error_count / num_test_vec
    print('the error rate is {}'.format(error_rate))
    return error_rate


def multi_test():

    num_tests = 10
    error_sum = 0
    for k in range(num_tests):
        error_sum += colic_test()
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
    p = sigmoid(np.dot(X, theta))

    for i in range(m):
        if p[i] > 0.5:
            p[i] = 1
        else:
            p[i] = 0
    return p


if __name__ == '__main__':

    test()
    # colic_test()
    # multi_test()

