import numpy as np
import matplotlib.pyplot as plt  # 画图工具
import pandas as pd
from sklearn import datasets
from sklearn.model_selection import train_test_split
from cvxopt import matrix, solvers
from heapq import nlargest

# define SVM
def train_G(x, y):
    P, q, G, h, A, b = bulid_matrix_kernel_G(x, y)
    # A = A.transpose()
    alpha = QP_alpha(P, q, G, h, A, b)
    # print(alpha)
    w = cal_w(x, y, alpha)

    #find the support_vector
    sv_x, sv_y = find_sv(x, y, alpha)
    sv_x = np.squeeze(np.asarray(sv_x))
    sv_y = np.squeeze(np.asarray(sv_y))

    w0 = cal_w0(sv_x, sv_y, w)

    return w, w0, sv_x, sv_y

def train_poly(x, y):
    P, q, G, h, A, b = bulid_matrix_kernel_poly(x, y)
    alpha = QP_alpha(P, q, G, h, A, b)
    w = cal_w(x, y, alpha)

    #find the support_vector
    sv_x, sv_y = find_sv(x, y, alpha)
    sv_x = np.squeeze(np.asarray(sv_x))
    sv_y = np.squeeze(np.asarray(sv_y))

    w0 = cal_w0(sv_x, sv_y, w)

    return w, w0, sv_x, sv_y

# test funciton

def test(x, w ,w0):
    w = np.mat(w)
    w0 = np.mat(w0)
    pred = np.dot(x, w.T) + w0
    for i in range(len(pred)):
        if pred[i] > 0:
            pred[i] = 1
        else:
            pred[i]= -1
    # Y = np.array([[1 if p > 0 else -1] for p in pred])
    # return Y
    return pred

# using the most biggest 15 alpha to find the support vector
def find_sv(x, y, alpha):
    sv_x = []
    sv_y = []
    alpha6 = nlargest(6, alpha[:, 0])
    for i in range(len(x)):
        for j in range(6):
            if alpha[i] == alpha6[j]:
                sv_x.append(x[i])
                sv_y.append(y[i])
    # for i in range(len(x)):
    #     if alpha[i] > 0.01:
    #             sv_x.append(x[i])
    #             sv_y.append(y[i])

    return sv_x, sv_y

#------------------------------------------------------------------

def cal_w0(svX, svY, w):
    # for i in range(len(svX)):
    #     sum = svY[i] - np.dot(svX[i], w)
    #
    # w0 = sum/len(svX)
    svX = np.mat(svX)
    svY = np.mat(svY)
    w = np.mat(w)
    result = np.dot(svX, w.T)
    # print(result)
    w0 = np.mean(svY.T - result)
    # print('wo %s' %w0)
    # return np.mean([svY[i] - np.dot(w, svX[i]) for i in range(len(svX))])
    return w0

def cal_w(x, y, alpha):
    # sum = 0
    # for i in range(len(x)):
    #     sum += alpha[i] * y[i] * x[i]
    return np.sum([alpha[i] * y[i] * x[i] for i in range(len(x))], axis=0)

def G_kernel(x, sigma):
    gram_k = np.empty((len(x), len(x)))
    for i in range(len(x)):
        for j in range(len(x)):
            # gram_k[i][j] = Gaussian_g(x[i], x[j], 0.5)
            gram_k[i][j] = np.exp((-(np.linalg.norm(x[i]-x[j])**2))/(2*sigma**2))
    return gram_k

def poly_kernel(x):
    gram_k = np.empty((len(x), len(x)))
    for i in range(len(x)):
        for j in range(len(x)):
            # gram_k[i][j] = polynomial_g(x[i], x[j])
            gram_k[i][j] = np.dot(x[i], x[j].T)+1
    print(gram_k)
    return gram_k

# def Gaussian_g(x,z,sigma):
#     return np.exp((-(np.linalg.norm(x-z)**2))/(2*sigma**2))

# def polynomial_g(x,y):
#     return np.dot(x,y.T)+1

#----------------------------------------------------------------------------
# using QP solver find alpha
def QP_alpha(P, q, G, h, A, b):
    P = matrix(P)
    q = matrix(q)
    G = matrix(G)
    h = matrix(h)
    A = matrix(A)
    # A = A.T
    b = matrix(b)
    aplha = solvers.qp(P, q, G, h, A, b)

    return np.array(aplha["x"])


# bulid the matrix which will use in QP
def bulid_matrix_kernel_G(x, y):

    # gram =  np.dot(x, x.transpose())
    kernel_gram = G_kernel(x, 2)
    # print(kernel_gram)
    # y1 = np.mat(y)
    # inter_y = np.dot(y1,y1.transpose())
    # inter_y = np.squeeze(np.asarray(inter_y))
    inter_y = np.dot(y, y.T)

    P = (np.multiply(inter_y,  kernel_gram)).astype(float)
    q = -np.ones((len(x), 1))
    A = (y.T).astype(float)

    b = matrix(0.)
    G = -np.eye(len(x)).astype(float)
    h = np.zeros((len(x), 1)).astype(float)

    return P, q, G, h, A, b

def bulid_matrix_kernel_poly(x, y):

    # gram =  np.dot(x, x.transpose())
    kernel_gram = poly_kernel(x)
    # y1 = np.mat(y)
    # inter_y = np.dot(y1,y1.transpose())
    # inter_y = np.squeeze(np.asarray(inter_y))
    inter_y = np.dot(y, y.T)

    P = (np.multiply(inter_y, kernel_gram)).astype(float)
    q = -np.ones((len(x), 1))
    A = (y.T).astype(float)

    b = matrix(0.)
    G = -np.eye(len(x)).astype(float)
    # G = np.concatenate((G, np.eye(len(x))), axis=0)
    h = np.zeros((len(x), 1)).astype(float)
    # h = np.concatenate((h, np.array([[c]] * (len(x)))))

    return P, q, G, h, A, b


#-----------------------------------------------------------


def testsvm():

    # def train data
    # x_train = np.array([[1,5],[1,3],[2,4],[3,5],[2,0],[3,0],[4,1],[4,2]])
    # y_train = np.array([[-1],[-1],[-1],[-1],[1],[1],[1],[1]])
    # x_train = np.array([[1, 5], [1, 3], [4, 1], [4, 2]])
    # y_train = np.array([[-1], [-1], [1], [1]])
    # x_test = x_train
    # y_test = y_train

    # generate the dataset user could change the parameter of distribution to get a non separable data
    # x_train, x_test, y_train, y_test = generate_data()

    #read the data
    x_train, x_test, y_train, y_test = read_data_2()


    train4sca = y_train
    y_train = np.mat(y_train)
    y_train = np.asarray(y_train.T)
    plt.scatter(x_train[:, 0], x_train[:, 1], c=train4sca, marker='o')
    x_train_m = np.mat(x_train)
    x_test_m = np.mat(x_test)
    y_test_m = np.mat(y_test)
    y_train_m = np.mat(y_train)

    # poly kernel margin
    sw, sw0, ssvx, ssvy = train_poly(x_train_m, y_train_m)

    plt.scatter(ssvx[:, 0], ssvx[:, 1], marker= '*')

    s_pred_y = test(x_test_m, sw, sw0)
    correct = s_pred_y - y_test_m.T
    s_k = np.count_nonzero(correct)
    s_accuracy = (20-s_k)/20
    print('poly kernel accuracy is : %f' %s_accuracy)

    # Gaussian kernel margin
    w, w0, svx, svy = train_G(x_train_m, y_train_m)

    plt.scatter(svx[:, 0], svx[:, 1], marker='x')
    plt.show()

    pred_y = test(x_test_m, w, w0)
    correct = pred_y - y_test_m.T
    k = np.count_nonzero(correct)
    accuracy = (20 - k) / 20
    print('Gaussian kernel accuracy is : %f' % accuracy)

def generate_data():
    x, y = datasets.make_blobs(n_samples=100, centers=2, n_features=2, cluster_std=[5.0, 5.0])
    # x, y = datasets.make_blobs(n_samples=100, centers=2, n_features=2)
    for i in range(len(y)):
        if y[i] == 0:
            y[i] = -1

    x_train, x_test, y_train, y_test = train_test_split(x, y, test_size=0.2, random_state=42)

    return x_train, x_test, y_train, y_test

def read_data():
    iris = pd.read_csv('/Users/michael/Codes/bitbucket/cs584-s18-kaiyue-ma/AS4/data/iris.csv', encoding="gbk")
    iris = iris.sample(frac=1.0)

    dummy = pd.get_dummies(iris['Species'])
    iris = pd.concat([iris, dummy], axis=1)

    org_x = np.array(iris.iloc[:, 1:3])
    # org_y = np.array(iris['setosa']).reshape(len(iris), 1)
    org_y = np.array(iris['setosa']).astype(int)

    for i in range(len(org_y)):
        if org_y[i] == 0:
            org_y[i] = org_y[i]-1

    x_train, x_test, y_train, y_test = train_test_split(org_x, org_y, test_size=0.2, random_state=42)


    return x_train, x_test, y_train, y_test

def read_data_2():
    data_arr = []
    label_arr = []
    f = open('/Users/michael/Codes/bitbucket/cs584-s18-kaiyue-ma/AS3/data/TestSet.txt', 'r')
    for line in f.readlines():
        line_arr = line.strip().split()

        data_arr.append([np.float(line_arr[0]), np.float(line_arr[1])])
        label_arr.append(float(line_arr[2]))

    org_x = np.asarray(data_arr)
    org_y = np.asarray(label_arr)

    for i in range(len(org_y)):
        if org_y[i] == 0:
            org_y[i] = org_y[i]-1

    x_train, x_test, y_train, y_test = train_test_split(org_x, org_y, test_size=0.2, random_state=42)


    return x_train, x_test, y_train, y_test

if __name__ == '__main__':
    testsvm()