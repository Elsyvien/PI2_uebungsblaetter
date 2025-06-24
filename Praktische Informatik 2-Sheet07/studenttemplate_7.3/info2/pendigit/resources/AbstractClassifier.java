package info2.pendigit.resources;

public abstract class AbstractClassifier implements Classifier {
    private int inputSize;
    private int outputSize;

    public AbstractClassifier(int inputSize, int outputSize) {
        this.inputSize = inputSize;
        this.outputSize = outputSize;
    }

    @Override
    public int getInputSize() {
        return inputSize;
    }
    
    @Override
    public int getOutputSize() {
        return outputSize;
    }
}