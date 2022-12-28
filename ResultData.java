import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ResultData {
    Item[] items;

    public void setItems(int[] values, int[] weights) {
        items = new Item[values.length];
        for (int i = 0; i < items.length; i++) {
            items[i] = new Item(weights[i], values[i]);
        }
    }
    
    public void getResult(int maxW, boolean traza, String fileName){
        if (traza) {
            System.out.println("\n****************** Traza del algoritmo ******************\n");
            System.out.println("Ordenamos los objetos en relacion al valor por unidad de" +
                            " peso de forma descendiente.");
        }
        orderItems(items, 0, items.length - 1);
        int pesoAcumulado = 0;
        float[] fraccionObjeto = new float[items.length];
        float[] beneficioFraccion = new float[items.length];
        float beneficioTotal = 0f;
        int contadorObj = 0;
        if (traza) System.out.println("Vamos añadiendo objetos a la mochila hasta llenarla:");
        while (pesoAcumulado < maxW && contadorObj < items.length) {
            if (pesoAcumulado + items[contadorObj].peso <= maxW) {
                if (traza) {
                    System.out.println("\n* El peso del objeto evaluado es: " + items[contadorObj].peso + 
                    ", podemos meterlo entero y nos queda espacio en la mochila");
                    System.out.println("  Añadimos su valor al valor total dentro de la mochila");
                }
                fraccionObjeto[contadorObj]  = 1f;
                beneficioFraccion[contadorObj]  = items[contadorObj].valor;
                pesoAcumulado += items[contadorObj].peso;
                beneficioTotal += beneficioFraccion[contadorObj] ;
                contadorObj++;
            } else {
                fraccionObjeto[contadorObj]  = (float) (maxW - pesoAcumulado) / items[contadorObj].peso;
                beneficioFraccion[contadorObj]  = items[contadorObj].valor * fraccionObjeto[contadorObj] ;
                pesoAcumulado = maxW;
                beneficioTotal += beneficioFraccion[contadorObj] ;
                if (traza) {
                    System.out.println("\n* El peso del objeto evaluado es: " + items[contadorObj].peso +
                    ", no podemos meterlo entero por tanto introducimos una fraccion.");
                    System.out.println("  Con " + fraccionObjeto[contadorObj] + 
                    " parte del objeto llenamos la mochila, añadimos el valor equivalente " +
                    "a esa fraccion: \"" +beneficioFraccion[contadorObj] + "\".\n  Alcanzamos el maximo " +
                    "valor que se puede introducir en la mochila: \"" + beneficioTotal +
                    "\" y resolvemos el problema.");
                }
                contadorObj++;
            }
            if (traza) System.out.println("\n- Peso de la mochila en la iteracion \"" +
                                    contadorObj + "\": " + pesoAcumulado);
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
            System.out.println(items[i].peso + " " + frac[i] + " " + bfrac[i]);
        }
        System.out.println(btotal);
    }

    private void resultToFile(String name, float[] frac, float[] bfrac, float btotal, int limit) {
        try {
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter(name));
            for (int i = 0; i < limit; i++) {
                writer.write(items[i].peso + " " +
                        String.valueOf(frac[i]) +
                        " " + String.valueOf(bfrac[i]) + "\n");
            }
            writer.write(String.valueOf(btotal));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void orderItems(Item[] array, int lowInd, int hiInd) {

        if (lowInd >= hiInd) return ;
        float pivot = array[hiInd].ratio;
        int lpointer = lowInd;
        int rpointer = hiInd;
        Item temp;
        while (lpointer < rpointer) {
            while (array[lpointer].ratio <= pivot && lpointer < rpointer) lpointer++;
            while (array[rpointer].ratio >= pivot && lpointer < rpointer) rpointer--;
            temp = array[lpointer];
            array[lpointer] = array[rpointer];
            array[rpointer] = temp;
        }
        temp = array[lpointer];
        array[lpointer] = array[hiInd];
        array[hiInd] = temp;

        orderItems(array, lowInd, lpointer-1);
        orderItems(array, lpointer+1, hiInd);
    }
}
