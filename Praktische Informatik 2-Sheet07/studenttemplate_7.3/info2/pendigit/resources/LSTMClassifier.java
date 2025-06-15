package info2.pendigit.resources;

import de.jannlab.Net;
import de.jannlab.generator.GenerateNetworks;

import info2.pendigit.misc.LSTMTools;

public final class LSTMClassifier extends AbstractClassifier implements SequenceClassifier {

    private final Net net;

    public LSTMClassifier() {
        super(3, 10);

        final String descriptor = LSTMTools.var("LSTM-#1-tanh32-softmax#2", 3, 10);
        this.net = GenerateNetworks.generateNet(descriptor);

        double[] weights = LSTMTools.loadWeights("info2/pendigit/resources/" + descriptor + ".weights.gz");
        this.net.writeWeights(weights, 0);
    }

    @Override
    public double[] predict(double[] input) {
        if (input.length != getInputSize()) {
            return new double[getOutputSize()];
        }
        double[] output = new double[getOutputSize()];
        net.input(input, 0);
        net.compute();
        net.output(output, 0);
        return output;
    }

    @Override
    public void reset() {
        net.reset();
    }

    public Net getNet() {
        return this.net;
    }
}