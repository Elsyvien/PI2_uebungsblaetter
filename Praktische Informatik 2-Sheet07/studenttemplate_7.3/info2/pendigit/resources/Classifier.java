package info2.pendigit.resources;

public interface Classifier {
    public int getInputSize();
    public int getOutputSize();
    public double[] predict(double[] input);
}
