import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ObjectData {
    int num;
    List<Item> items = new ArrayList<Item>();
    int maxW;

    public void setFromFile(String name) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(name));
            num = Integer.parseInt(reader.readLine());
            String[] line;
            int value;
            int weight;
            for (int i = 0; i < num; i++) {
                line = reader.readLine().split("\\s");
                weight = Integer.parseInt(line[0]);
                value = Integer.parseInt(line[1]);
                items.add(new Item(weight, value));
            }
            maxW = Integer.parseInt(reader.readLine());
            reader.close();
        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    public void setFromInputs() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduczca el numero de objetos: ");
        num = sc.nextInt();
        int value;
        int weight;
        System.out.println(
                "\nAhora introduzca el peso y el valor de cada objeto por separado");
        for (int i = 0; i < num; i++) {
            System.out.print("peso " + (i + 1) + ": ");
            weight = sc.nextInt();
            System.out.print("valor " + (i + 1) + ": ");
            value = sc.nextInt();
            items.add(new Item(weight, value));
        }
        System.out.print("Por ultimo introduzca el peso maximo de la mochila: ");
        maxW = sc.nextInt();
        sc.close();
    }

    public List<Item> getItems() {
        return items;
    }

    public int getMaxW() {
        return maxW;
    }

}
