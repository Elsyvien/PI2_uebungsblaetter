package info2.pendigit;

import info2.pendigit.gui.PenDigitRecognitionFrame;
import info2.pendigit.resources.LSTMClassifier;
import info2.pendigit.resources.SequenceClassifier;

/**
 * Starting class for this exercise. It creates an instance
 * of LSTMClassifier and hands it over to the handwriting recognition
 * GUI. 
 * 
 * @author Sebastian Otte
 */
public class PenDigitRecognition {
    
    public static void main(String[] args) {

        final SequenceClassifier classifier = new LSTMClassifier();
        final PenDigitRecognitionFrame frame = new PenDigitRecognitionFrame(classifier);
        frame.setVisible(true);
    }       

}