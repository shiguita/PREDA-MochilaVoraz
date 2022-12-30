import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ResultData {
    List<Item> items = new ArrayList<Item>();

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void getResult(int maxW, boolean traza, String fileName){
        if (traza) {
            System.out.println(
                "\n****************** Traza del algoritmo ******************\n"
            );
            System.out.println(
                "Ordenamos los objetos en relacion al valor por unidad de" +
                " peso de forma descendiente."
            );
        }
        orderItems(items, 0, items.size() - 1);
        int pesoAcumulado = 0;
        float[] fraccionObjeto = new float[items.size()];
        float[] beneficioFraccion = new float[items.size()];
        float beneficioTotal = 0f;
        int contadorObj = 0;
        if (traza) {
            System.out.println(
                "Vamos añadiendo objetos a la mochila hasta llenarla:"
            );
        }
        while (pesoAcumulado < maxW && contadorObj < items.size()) {
            if (pesoAcumulado + items.get(contadorObj).peso <= maxW) {
                if (traza) {
                    System.out.println(
                        "\n* El peso del objeto evaluado es: " +
                        items.get(contadorObj).peso +
                        ", podemos meterlo entero y nos queda espacio en la mochila"
                    );
                    System.out.println(
                        "  Añadimos su valor al valor total dentro de la mochila"
                    );
                }
                fraccionObjeto[contadorObj]  = 1f;
                beneficioFraccion[contadorObj]  = items.get(contadorObj).valor;
                pesoAcumulado += items.get(contadorObj).peso;
                beneficioTotal += beneficioFraccion[contadorObj] ;
                contadorObj++;
            } else {
                fraccionObjeto[contadorObj]  = (float) (maxW - pesoAcumulado) /
                items.get(contadorObj).peso;
                beneficioFraccion[contadorObj]  = items.get(contadorObj).valor *
                fraccionObjeto[contadorObj];
                pesoAcumulado = maxW;
                beneficioTotal += beneficioFraccion[contadorObj] ;
                if (traza) {
                    System.out.println(
                        "\n* El peso del objeto evaluado es: " +
                        items.get(contadorObj).peso +
                        ", no podemos meterlo entero por tanto introducimos una fraccion."
                    );
                    System.out.println(
                        "  Con " + fraccionObjeto[contadorObj] + 
                        " parte del objeto llenamos la mochila, añadimos el valor equivalente " +
                        "a esa fraccion: \"" + beneficioFraccion[contadorObj] +
                        "\".\n  Alcanzamos el maximo " +
                        "valor que se puede introducir en la mochila: \"" + beneficioTotal +
                        "\" y resolvemos el problema."
                    );
                }
                contadorObj++;
            }
            if (traza) {
                System.out.println(
                    "\n- Peso de la mochila en la iteracion \"" +
                    contadorObj + "\": " + pesoAcumulado
                );
            }
        }
        if (fileName == "") {
            if (traza) System.out.println("\nImprimimos los resultados en consola:");
            printResult(fraccionObjeto, beneficioFraccion, beneficioTotal, contadorObj);
        } else {
            if (traza) System.out.println("\nIntroducimos los resultados en el archivo" +
                                    fileName);
            resultToFile(fileName, fraccionObjeto, beneficioFraccion, beneficioTotal, contadorObj);
        }
    }

    private void printResult(float[] frac, float[] bfrac, float btotal, int limit) {
        System.out.println("\nResultado:");
        for (int i = 0; i < limit; i++) {
            System.out.println(items.get(i).peso + " " + frac[i] + " " + bfrac[i]);
        }
        System.out.println(btotal);
    }

    private void resultToFile(String name, float[] frac, float[] bfrac, float btotal, int limit) {
        try {
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter(name));
            for (int i = 0; i < limit; i++) {
                writer.write(
                    items.get(i).peso + " " + String.valueOf(frac[i]) +
                    " " + String.valueOf(bfrac[i]) + "\n"
                );
            }
            writer.write(String.valueOf(btotal));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void orderItems(List<Item> items, int lowInd, int hiInd) {

        if (lowInd >= hiInd) return ;
        float pivot = items.get(hiInd).ratio;
        int lpointer = lowInd;
        int rpointer = hiInd;
        Item temp;
        while (lpointer < rpointer) {
            while (items.get(lpointer).ratio <= pivot && lpointer < rpointer) lpointer++;
            while (items.get(rpointer).ratio >= pivot && lpointer < rpointer) rpointer--;
            temp = items.get(lpointer);
            items.set(lpointer, items.get(rpointer));
            items.set(rpointer, temp);
        }
        temp = items.get(lpointer);
        items.set(lpointer, items.get(hiInd));
        items.set(hiInd, temp);

        orderItems(items, lowInd, lpointer-1);
        orderItems(items, lpointer+1, hiInd);
    }
}
