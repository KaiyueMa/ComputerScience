import random
import math
import pandas as pd
import numpy as np
from sklearn.model_selection import train_test_split


class NeuralNetwork:
    # 3 layer network ----input----hidden----output
    LEARNING_RATE = 0.5
    # initialize NN
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
            for i in range(self.num_inputs):  # input layer
                if not hidden_layer_weights:
                    # if no hidden_layer_weight---randomly pick a value
                    self.hidden_layer.neurons[h].weights.append(random.random())
                else:
                    self.hidden_layer.neurons[h].weights.append(hidden_layer_weights[weight_num])
                weight_num += 1

    def init_weights_from_hidden_layer_neurons_to_output_layer_neurons(self, output_layer_weights):
        weight_num = 0
        for o in range(len(self.output_layer.neurons)):
            for h in range(len(self.hidden_layer.neurons)):  # hidden layer
                if not output_layer_weights:
                    #  if no output_layer_weight---randomly pick a value
                    self.output_layer.neurons[o].weights.append(random.random())
                else:
                    self.output_layer.neurons[o].weights.append(output_layer_weights[weight_num])
                weight_num += 1

    def inspect(self):  # print NN info
        print('------')
        print('* Inputs: {}'.format(self.num_inputs))
        print('------')
        print('Hidden Layer')
        self.hidden_layer.inspect()
        print('------')
        print('* Output Layer')
        self.output_layer.inspect()
        print('------')




    def feed_forward(self, inputs):  # FP output
        hidden_layer_outputs = self.hidden_layer.feed_forward(inputs) # hidden layer output-- to output layer
        return self.output_layer.feed_forward(hidden_layer_outputs) # output layer output


    def train(self, training_inputs, training_outputs):
        self.feed_forward(training_inputs)
        # BP
        # 1. Output layer deltas Δ
        pd_errors_wrt_output_neuron_total_net_input = [0]*len(self.output_layer.neurons)
        for o in range(len(self.output_layer.neurons)): #to every neuron in output layer
            # ∂E/∂zⱼ=∂E/∂a*∂a/∂z=cost'(target_output)*sigma'(z)
            pd_errors_wrt_output_neuron_total_net_input[o] = self.output_layer.neurons[
                o].calculate_pd_error_wrt_total_net_input(training_outputs[o])

        # 2. Hidden layer Δ
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
        #  calculate error
        total_error = 0
        for t in range(len(training_sets)):
            training_inputs, training_outputs = training_sets[t]
            result = self.feed_forward(training_inputs)
            # print('result is %s' % result)
            for o in range(len(training_outputs)):
                total_error += self.output_layer.neurons[o].calculate_error(training_outputs[o])
                # total_error += self.output_layer.neurons[o].calculate_error(training_outputs[o])
        return total_error


class NeuronLayer:
    # layer
    def __init__(self, num_neurons, bias):

        # neuron in a layer have the same bias
        self.bias = bias if bias else random.random()

        self.neurons = []
        for i in range(num_neurons):
            self.neurons.append(Neuron(self.bias))
        # print layer info
        self.inspect()

    def inspect(self):
        # print layer info
        print('Neurons:', len(self.neurons))
        for n in range(len(self.neurons)):
            print(' Neuron', n)
            for w in range(len(self.neurons[n].weights)):
                print('  Weight:', self.neurons[n].weights[w])
            print('  Bias:', self.bias)

    def feed_forward(self, inputs):
        # feed forward output, every neuron output (using the sigmoid)
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
    # single neuron
    def __init__(self, bias):
        self.bias = bias
        self.weights = []

    def calculate_output(self, inputs): #single neuron output
        self.inputs = inputs
        self.output = self.squash(self.calculate_total_net_input())

        return self.output

    def calculate_total_net_input(self):
        # z=W(n)x+b
        total = 0
        for i in range(len(self.inputs)):
            total += self.inputs[i]*self.weights[i]
        return total + self.bias



    # Use the sigmoid function as the activition function, which is the definition of the sigmoid function.
    def squash(self, total_net_input):
        return 1/(1 + math.exp(-total_net_input))


    # δ = ∂E/∂zⱼ = ∂E/∂yⱼ * dyⱼ/dzⱼ 关键key

    def calculate_pd_error_wrt_total_net_input(self, target_output):
        return self.calculate_pd_error_wrt_output(target_output)*self.calculate_pd_total_net_input_wrt_input()

    # The error for each neuron is calculated by the Mean Square Error method:
    def calculate_error(self, target_output):
        return 0.5*(target_output - self.output) ** 2


    # = ∂E/∂yⱼ = -(tⱼ - yⱼ)
    def calculate_pd_error_wrt_output(self, target_output):
        return -(target_output - self.output)


    # dyⱼ/dzⱼ = yⱼ * (1 - yⱼ)这是sigmoid函数的导数表现形式.
    def calculate_pd_total_net_input_wrt_input(self):
        return self.output*(1 - self.output)


    # = ∂zⱼ/∂wᵢ = some constant + 1 * xᵢw₁^(1-0) + some constant ... = xᵢ
    def calculate_pd_total_net_input_wrt_weight(self, index):
        return self.inputs[index]

def load_data_set():
    """
    read in data 5D add all 1s column
    """

    iris = pd.read_csv('/Users/michael/Codes/bitbucket/cs584-s18-kaiyue-ma/AS2/data/iris.csv', encoding="gbk")
    iris = iris.sample(frac=1.0)

    dummy = pd.get_dummies(iris['Species'])
    iris = pd.concat([iris, dummy], axis=1)

    org_x = np.array(iris.iloc[:, 1:5])
    # org_y = np.array(iris['setosa']).reshape(len(iris), 1)
    org_y = np.array(iris['setosa'])

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
    y = org_y.tolist()
    x = np.mat(x)
    one = np.ones(len(x))
    x = np.column_stack((one, x))
    x = x.tolist()

    # print(data_arr)
    # x1_2 = data_arr[:, 1]
    # print(x1_2)
    # print('.....')
    # print(label_arr)
    # return data_arr, test_arr, label_arr, test_label
    return x, y


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
def data_trans():
    train = []
    x, y = load_data_set() # load dataset
    # separate data into data and label
    for idx,feature in enumerate(x):
        train.append([feature,[y[idx]]])
    print('done')

    # separate data randomly into test and train
    # xtrain, xtest, ytrain, ytest = load_data_set()
    trainingset = random.sample(train,105)
    testset = [f for f in train if f not in trainingset]

    return trainingset , testset ,train

def test():

    trainingset, testset, train = data_trans()
    epoch = 80
    nn = NeuralNetwork(len(train[0][0]), 10, len(train[0][1]))
    for i in range(epoch):
        for idx , trainfeatures in enumerate(trainingset):
            randomtuple = random.choice(train)
            training_inputs, training_outputs = randomtuple[0],randomtuple[1]
            nn.train(training_inputs, training_outputs)
            # print('epoch %d:, step:%d----error:%f'%(i, idx, nn.calculate_total_error([trainfeatures])))
            # print(i, nn.calculate_total_error(train))
        totaltesterror=0
        for idx, testfeature in enumerate(testset):
            singlerror = nn.calculate_total_error([testfeature])
            # print('test error%f'%singlerror)
            totaltesterror+=singlerror
        print('epoch:%d, test error %f'%(i, totaltesterror/(idx+1)))

if __name__=="__main__":
    test()