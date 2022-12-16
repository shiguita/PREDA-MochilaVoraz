import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ObjectData {
    int num;
    int[] values;
    int[] weights;
    int maxW;

    public void setFromFile(String name) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(name));
            this.num = Integer.parseInt(reader.readLine());
            String[] line;
            for (int i = 0; i < num; i++) {
                line = reader.readLine().split("\\s");
                this.weights[i] = Integer.parseInt(line[0]);
                this.values[i] = Integer.parseInt(line[1]);
            }
            this.maxW = Integer.parseInt(reader.readLine());
            reader.close();
        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    public void setFromInputs() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduczca el numero de objetos: ");
        this.num = sc.nextInt();
        values = new int [num];
        weights = new int [num];
        System.out.println(
                "\nAhora introduzca el peso y el valor de cada objeto por separado");
        for (int i = 0; i < this.num; i++) {
            System.out.print("peso " + (i + 1) + ": ");
            this.weights[i] = sc.nextInt();
            System.out.print("valor " + (i + 1) + ": ");
            this.values[i] = sc.nextInt();
        }
        System.out.print("Por ultimo introduzca el peso maximo de la mochila: ");
        this.maxW = sc.nextInt();
        sc.close();
    }
}
