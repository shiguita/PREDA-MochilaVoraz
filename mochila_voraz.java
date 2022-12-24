import java.io.File;

/**
 * mochila_voraz
 */
public class mochila_voraz {
    static String msg = "SINTAXIS: mochila-voraz [-t][-h] [fichero entrada]" +
            "\n-t                               Traza del algoritmo" +
            "\n-h                               Muestra este mensaje" +
            "\n[fichero de entrada]         nombre del fichero de entrada" +
            "\n[fichero de salida]          nombre del fichero de salida";

    public static void main(String[] args) {

        ObjectData data = new ObjectData();
        ResultData result = new ResultData();

        switch (args.length) {
            case 0:
                data.setFromInputs();
                result.setItems(data.values, data.weights);
                result.getResult(data.maxW, false, "");
                break;
            case 1:
                if (args[0].equals("-h")) System.out.println(msg);
                if (args[0].equals("-t")) {
                    data.setFromInputs();
                    result.setItems(data.values, data.weights);
                    result.getResult(data.maxW, true, "");
                } else {
                    if (new File(args[0]).exists()) {
                        data.setFromFile(args[0]);
                        result.setItems(data.values, data.weights);
                        result.getResult(data.maxW, false, "");
                    } else {
                        data.setFromInputs();
                        result.setItems(data.values, data.weights);
                        result.getResult(data.maxW, false, args[0]);
                    }
                }
                break;
            case 2:
                if (args[0].equals("-t")) {
                    if (new File(args[1]).exists()) {
                        data.setFromFile(args[1]);
                        result.setItems(data.values, data.weights);
                        result.getResult(data.maxW, true, "");
                    } else {
                        data.setFromInputs();
                        result.setItems(data.values, data.weights);
                        result.getResult(data.maxW, true, args[1]);
                    }
                } else {
                    data.setFromFile(args[0]);
                    result.setItems(data.values, data.weights);
                    result.getResult(data.maxW, false, args[1]);
                }
                break;
            case 3:
                data.setFromFile(args[1]);
                result.setItems(data.values, data.weights);
                result.getResult(data.maxW, true, args[2]);
                break;

            default:
                System.err.println(
                        "Utilice -h para ver como ejecutar el programa");
                break;
        }
    }
}