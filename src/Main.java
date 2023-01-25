public class Main
{
    public static void main(String[] args)
    {
        Neuron nodes[] = new Neuron[5];
        for(int i = 0; i < 5; i++)
            nodes[i] = new Neuron();
        nodes[2].inputNodes = new Neuron[]{nodes[0], nodes[1]};
        nodes[3].inputNodes = new Neuron[]{nodes[0], nodes[1]};
        nodes[4].inputNodes = new Neuron[]{nodes[2], nodes[3]};
        double data[][] = {{0, 0, 0}};
    }
}

class Neuron
{
    double[] weights;
    double bias;
    Neuron[] inputNodes;

    double getOutput(double[] inputs)
    {
        double sum = bias;
        int i = 0;
        for (double d : inputs)
            sum += d * weights[i++];
        return sum;
    }

    public Neuron() {
        randomize(2);
    }
    void randomize(int number)
    {
        weights = new double[number];
        for(int i = 0; i < number; i++)
            weights[i] = Math.random();
        bias = Math.random();
    }
}

class Train
{
    private static double sigmoid(double x)
    {
        return 1 / (1 + Math.exp(-x));
    }
}