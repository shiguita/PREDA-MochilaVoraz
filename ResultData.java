import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ResultData {
    int numElements = 2;
    int[] peso = {15, 10};
    float[] fraccion = {1f, 0.5f};
    float[] fracBeneficio = {24f, 7.5f};
    float totalValue = 31.5f;

    public void maxBenefit(ObjectData data, boolean traza) {

    }

    public void printResult() {
        for (int i = 0; i < this.numElements; i++) {
            System.out.println(
                    this.peso[i] + " " + this.fraccion[i] +
                            " " + this.fracBeneficio[i]);
            System.out.println(this.totalValue);
        }
    }

    public void resultToFile(String name) {
        try {
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter(name));
            for (int i = 0; i < numElements; i++) {
                writer.write(this.peso[i] + " " +
                        String.valueOf(this.fraccion[i]) +
                        " " + String.valueOf(this.fracBeneficio[i]) + "\n");
            }
            writer.write(String.valueOf(totalValue));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
