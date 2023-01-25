//generated with help from chatgpt
public class XORNeuralNetwork
{
    private double[][] input = {{0, 0}, {0, 1}, {1, 0}, {1, 1}};
    private double[] output = {0, 1, 1, 0};
    private double[][] weights1 = new double[2][2];
    private double[] weights2 = new double[2];
    private double[] bias1 = new double[2];
    private double bias2;
    private double learningRate = 0.1;
    private int numEpochs = 100000;

    public static void main(String[] args)
    {
        XORNeuralNetwork nn = new XORNeuralNetwork();
        nn.initWeights();
        nn.train();
        nn.predict();
    }

    private void initWeights()
    {
        for (int i = 0; i < 2; i++)
        {
            for (int j = 0; j < 2; j++)
            {
                weights1[i][j] = Math.random() - 1;
            }
        }
        for (int i = 0; i < 2; i++)
        {
            weights2[i] = Math.random() - 1;
        }
        bias1[0] = Math.random() - 1;
        bias1[1] = Math.random() - 1;
        bias2 = Math.random() - 1;
    }

    private double sigmoid(double x)
    {
        return 1.0 / (1.0 + Math.exp(-x));
    }

    private double sigmoidDerivative(double x)
    {
        return x * (1.0 - x);
    }

    private void train()
    {
        for (int i = 0; i < numEpochs; i++)
        {
            double[] hidden = new double[2];
            double prediction = 0;
            for (int j = 0; j < input.length; j++)
            {
// feedforward
                for (int k = 0; k < 2; k++)
                {
                    hidden[k] = sigmoid(input[j][0] * weights1[0][k] + input[j][1] * weights1[1][k] + bias1[k]);
                }
                prediction = sigmoid(hidden[0] * weights2[0] + hidden[1] * weights2[1] + bias2);
                // backpropagation
                double error = output[j] - prediction;
                double errorGradient = sigmoidDerivative(error);
                double[] hiddenError = new double[2];
                for (int k = 0; k < 2; k++)
                {
                    hiddenError[k] = hidden[k] * (1 - hidden[k]) * errorGradient * weights2[k];
                }
                for (int k = 0; k < 2; k++)
                {
                    weights2[k] += learningRate * errorGradient * hidden[k];
                }
                bias2 += learningRate * errorGradient;
                for (int k = 0; k < 2; k++)
                {
                    for (int l = 0; l < 2; l++)
                    {
                        weights1[k][l] += learningRate * hiddenError[l] * input[j][k];
                    }
                    bias1[k] += learningRate * hiddenError[k];
                }
            }
        }
    }

    private void predict()
    {
        for (int i = 0; i < input.length; i++)
        {
            double[] hidden = new double[2];
            double prediction = 0;
            for (int j = 0; j < 2; j++)
            {
                hidden[j] = sigmoid(input[i][0] * weights1[0][j] + input[i][1] * weights1[1][j] + bias1[j]);
            }
            prediction = sigmoid(hidden[0] * weights2[0] + hidden[1] * weights2[1] + bias2);
            System.out.println("Expected: " + output[i] + ", Prediction: " + prediction);
        }
    }
}