import random
import math
import pandas as pd
import numpy as np


class NeuralNetwork:

    LEARNING_RATE = 0.5


    def __init__(self, num_inputs, num_hidden, num_outputs, hidden_layer_weights=None, hidden_layer_bias=None,
                 output_layer_weights=None, output_layer_bias=None):

        self.num_inputs = num_inputs

        self.hidden_layer = NeuronLayer(num_hidden, hidden_layer_bias)
        self.output_layer = NeuronLayer(num_outputs, output_layer_bias)

        self.init_weights_from_inputs_to_hidden_layer_neurons(hidden_layer_weights)
        self.init_weights_from_hidden_layer_neurons_to_output_layer_neurons(output_layer_weights)
        # self.inspect()

    def init_weights_from_inputs_to_hidden_layer_neurons(self, hidden_layer_weights):
        weight_num = 0
        for h in range(len(self.hidden_layer.neurons)):
            for i in range(self.num_inputs):
                if not hidden_layer_weights:

                    self.hidden_layer.neurons[h].weights.append(random.random())
                else:
                    self.hidden_layer.neurons[h].weights.append(hidden_layer_weights[weight_num])
                weight_num += 1

    def init_weights_from_hidden_layer_neurons_to_output_layer_neurons(self, output_layer_weights):
        weight_num = 0
        for o in range(len(self.output_layer.neurons)):
            for h in range(len(self.hidden_layer.neurons)):
                if not output_layer_weights:
                    self.output_layer.neurons[o].weights.append(random.random())
                else:
                    self.output_layer.neurons[o].weights.append(output_layer_weights[weight_num])
                weight_num += 1

    def inspect(self):
        print('------')
        print('* Inputs: {}'.format(self.num_inputs))
        print('------')
        print('Hidden Layer')
        self.hidden_layer.inspect()
        print('------')
        print('* Output Layer')
        self.output_layer.inspect()
        print('------')




    def feed_forward(self, inputs):
        hidden_layer_outputs = self.hidden_layer.feed_forward(inputs)
        return self.output_layer.feed_forward(hidden_layer_outputs)

    #
    def train(self, training_inputs, training_outputs):
        self.feed_forward(training_inputs)
        # BP
        # 1. Output neuron
        pd_errors_wrt_output_neuron_total_net_input = [0]*len(self.output_layer.neurons)
        for o in range(len(self.output_layer.neurons)):
            # ∂E/∂zⱼ=∂E/∂a*∂a/∂z=cost'(target_output)*sigma'(z)
            pd_errors_wrt_output_neuron_total_net_input[o] = self.output_layer.neurons[
                o].calculate_pd_error_wrt_total_net_input(training_outputs[o])

        # 2. Hidden neuron
        pd_errors_wrt_hidden_neuron_total_net_input = [0]*len(self.hidden_layer.neurons)
        for h in range(len(self.hidden_layer.neurons)):
            # dE/dyⱼ = Σ ∂E/∂zⱼ * ∂z/∂yⱼ = Σ ∂E/∂zⱼ * wᵢⱼ
            d_error_wrt_hidden_neuron_output = 0
            for o in range(len(self.output_layer.neurons)):
                d_error_wrt_hidden_neuron_output += pd_errors_wrt_output_neuron_total_net_input[o]* \
                                                    self.output_layer.neurons[o].weights[h]

            # ∂E/∂zⱼ = dE/dyⱼ * ∂zⱼ/∂
            pd_errors_wrt_hidden_neuron_total_net_input[h] = d_error_wrt_hidden_neuron_output*self.hidden_layer.neurons[
                h].calculate_pd_total_net_input_wrt_input()

        # 3. Update output neuron weights
        for o in range(len(self.output_layer.neurons)):
            for w_ho in range(len(self.output_layer.neurons[o].weights)):
                # ∂Eⱼ/∂wᵢⱼ = ∂E/∂zⱼ * ∂zⱼ/∂wᵢⱼ
                pd_error_wrt_weight = pd_errors_wrt_output_neuron_total_net_input[o]*self.output_layer.neurons[
                    o].calculate_pd_total_net_input_wrt_weight(w_ho)

                # Δw = α * ∂Eⱼ/∂wᵢ
                self.output_layer.neurons[o].weights[w_ho] -= self.LEARNING_RATE*pd_error_wrt_weight

        # 4. Update hidden neuron weights
        for h in range(len(self.hidden_layer.neurons)):
            for w_ih in range(len(self.hidden_layer.neurons[h].weights)):
                # ∂Eⱼ/∂wᵢ = ∂E/∂zⱼ * ∂zⱼ/∂wᵢ
                pd_error_wrt_weight = pd_errors_wrt_hidden_neuron_total_net_input[h]*self.hidden_layer.neurons[
                    h].calculate_pd_total_net_input_wrt_weight(w_ih)

                # Δw = α * ∂Eⱼ/∂wᵢ
                self.hidden_layer.neurons[h].weights[w_ih] -= self.LEARNING_RATE*pd_error_wrt_weight

    def calculate_total_error(self, training_sets):

        total_error1 = 0
        total_error2 = 0
        total_error3 = 0
        total_error = 0
        for t in range(len(training_sets)):
            training_inputs, training_outputs = training_sets[t]
            result = self.feed_forward(training_inputs)
            # print('result is %s' % result)
            # for o in range(len(training_outputs)):
            #     # total_error1 += self.output_layer.neurons[o].calculate_error(training_outputs[o][0])
            #     # total_error2 += self.output_layer.neurons[o].calculate_error(training_outputs[o][1])
            #     # total_error3 += self.output_layer.neurons[o].calculate_error(training_outputs[o][2])
            #     # total_error = (total_error1+total_error2+total_error3)/3
            #     total_error += self.output_layer.neurons[o].calculate_error(training_outputs[o])
        return result


class NeuronLayer:
    # 神经层类
    def __init__(self, num_neurons, bias):

        # Every neuron in a layer shares the same bias
        self.bias = bias if bias else random.random()

        self.neurons = []
        for i in range(num_neurons):
            self.neurons.append(Neuron(self.bias))
        self.inspect()

    def inspect(self):
        # print该层神经元的信息
        print('Neurons:', len(self.neurons))
        for n in range(len(self.neurons)):
            print(' Neuron', n)
            for w in range(len(self.neurons[n].weights)):
                print('  Weight:', self.neurons[n].weights[w])
            print('  Bias:', self.bias)

    def feed_forward(self, inputs):
        outputs = []
        for neuron in self.neurons:
            outputs.append(neuron.calculate_output(inputs))
        return outputs

    def get_outputs(self):
        outputs = []
        for neuron in self.neurons:
            outputs.append(neuron.output)
        return outputs


class Neuron:

    def __init__(self, bias):
        self.bias = bias
        self.weights = []

    def calculate_output(self, inputs):
        self.inputs = inputs
        self.output = self.squash(self.calculate_total_net_input())

        return self.output

    def calculate_total_net_input(self):

        total = 0
        for i in range(len(self.inputs)):
            total += self.inputs[i]*self.weights[i]
        return total + self.bias


    def squash(self, total_net_input):
        return 1/(1 + math.exp(-total_net_input))

    # δ = ∂E/∂zⱼ = ∂E/∂yⱼ * dyⱼ/dzⱼ
    def calculate_pd_error_wrt_total_net_input(self, target_output):
        return self.calculate_pd_error_wrt_output(target_output)*self.calculate_pd_total_net_input_wrt_input()


    def calculate_error(self, target_output):
        return 0.5*(target_output - self.output) ** 2

    # = ∂E/∂yⱼ = -(tⱼ - yⱼ)
    def calculate_pd_error_wrt_output(self, target_output):
        return -(target_output - self.output)

    # dyⱼ/dzⱼ = yⱼ * (1 - yⱼ)
    def calculate_pd_total_net_input_wrt_input(self):
        return self.output*(1 - self.output)

    # = ∂zⱼ/∂wᵢ = some constant + 1 * xᵢw₁^(1-0) +  ... = xᵢ
    def calculate_pd_total_net_input_wrt_weight(self, index):
        return self.inputs[index]

def load_data_set():
    """
    加载数据集
    :return:返回两个数组，普通数组
        data_arr -- 原始数据的特征
        label_arr -- 原始数据的标签，也就是每条样本对应的类别
    """


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
    # print(iris)
    org_x = np.array(iris.iloc[:, 1:5])
    # org_y = np.array(iris['setosa']).reshape(len(iris), 1)
    org_y = np.array(iris['setosa'])

    org_y1 = np.array(iris['setosa'])
    org_y2 = np.array(iris['versicolor'])
    org_y3 = np.array(iris['virginica'])



    # data_arr, test_arr, label_arr, test_label = train_test_split(org_x, org_y, test_size=0.2, random_state=42)
    #
    # data_arr = data_arr.tolist()
    # test_arr = test_arr.tolist()
    # label_arr = label_arr.tolist()
    # test_label = test_label.tolist()
    #
    # data_arr = np.dot(data_arr)
    # one = np.ones(len(data_arr))
    # data_arr = np.column_stack(one , data_arr)
    # data_arr = data_arr.tolist()
    #
    # test_arr = np.dot(test_arr)
    # one = np.ones(len(test_arr))
    # test_arr = np.column_stack(one, test_arr)
    # test_arr = test_arr.tolist()

    x = org_x.tolist()
    y1 = org_y1.tolist()
    y2 = org_y2.tolist()
    y3 = org_y3.tolist()

    x = np.mat(x)
    one = np.ones(len(x))
    x = np.column_stack((one, x))

    y1 = (np.mat(y1)).transpose()
    y2 = (np.mat(y2)).transpose()
    y3 = (np.mat(y3)).transpose()
    y = np.column_stack((y1, y2, y3))

    x = x.tolist()
    y = y.tolist()
    # print(data_arr)
    # x1_2 = data_arr[:, 1]
    # print(x1_2)
    # print('.....')
    # print(label_arr)
    # return data_arr, test_arr, label_arr, test_label
    return x, y

# Blog post example:

# nn = NeuralNetwork(2, 2, 1, hidden_layer_weights=[0.15, 0.2, 0.25, 0.3], hidden_layer_bias=0.35,
#                    output_layer_weights=[0.4, 0.45], output_layer_bias=0.6)

# for i in range(10000):
#     nn.train([0.05, 0.95], [1])
#     print(i, round(nn.calculate_total_error([[[0.05, 0.1], [1]]]), 9))  # 截断处理只保留小数点后9位
# print(round(nn.calculate_total_error([[[0.05, 0.1], [1]]]), 9))
# print(nn.feed_forward([0, 0]))
# XOR example:

# training_sets = [
#     [[0, 0, 0], [0]],
#     [[0, 0, 1], [0]],
#     [[0, 1, 0], [0]],
#     [[1, 0, 0], [0]],
#     [[0, 1, 1], [1]],
#     [[1, 0, 1], [1]],
#     [[1, 1, 0], [1]],
#     [[1, 1, 1], [1]],
# ]
# training_sets = [
    # [[0, 0], [0]],
    # [[0, 1], [1]],
    # [[1, 0], [1]],
    # [[1, 1], [0]]
# ]
train = []
x, y = load_data_set()
for idx,feature in enumerate(x):
    train.append([feature,[y[idx]]])
print('done')

# xtrain, xtest, ytrain, ytest = load_data_set()
trainingset = random.sample(train,105)
testset = [f for f in train if f not in trainingset]
# train = x[0].append(y[0])
# x = np.mat(x)
# y = np.mat(y)
epoch = 80
result = []
nn = NeuralNetwork(len(train[0][0]), 6, len(train[0][1][0]))
for i in range(epoch):
    for idx , trainfeatures in enumerate(trainingset):
        randomtuple = random.choice(train)
        training_inputs, training_outputs = randomtuple[0],randomtuple[1][0]
        nn.train(training_inputs, training_outputs)
        # print('epoch %d:, step:%d----error:%f'%(i, idx, nn.calculate_total_error([trainfeatures])))
        # print(i, nn.calculate_total_error(train))
    totaltesterror=0
    result = []
    for idx, testfeature in enumerate(testset):
        output = nn.calculate_total_error([testfeature])
        index = output.index(max(output))
        mod_out =[]
        # for j in range(len(train[0][1][0])):
        if index == 0:
            mod_out = [1, 0, 0]
        elif index == 1:
            mod_out = [0, 1, 0]
        elif index == 2:
            mod_out = [0, 0, 1]
        else:
            print('error')
        result.append(mod_out)

        # if gt label is ground truth
        correct=0
        wrong = 0
    for idx,res in enumerate(result):
        if res == testset[idx][1][0]:
            correct+=1
        else:
            wrong+=1

        # singlerror = nn.calculate_total_error([testfeature])
        # print('test error%f'%singlerror)
        # totaltesterror+=singlerror
    print('epoch:%d, accuracy %f'%(i, correct/( wrong +correct)))
