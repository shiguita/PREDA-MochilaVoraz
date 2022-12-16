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
                result.maxBenefit(data, false);
                result.printResult();
                break;
            case 1:
                if (args[0].equals("-h")) {
                    System.out.println(msg);
                } else {
                    if (new File(args[0]).exists()) {
                        data.setFromFile(args[0]);
                        result.maxBenefit(data, false);
                        result.printResult();
                    } else {
                        data.setFromInputs();
                        result.maxBenefit(data, false);
                        result.resultToFile(args[0]);
                    }
                }
                break;
            case 2:
                data.setFromFile(args[0]);
                result.maxBenefit(data, false);
                result.resultToFile(args[1]);
                break;
            case 3:
                data.setFromFile(args[1]);
                result.maxBenefit(data, true);
                result.resultToFile(args[2]);
                break;

            default:
                System.err.println(
                    "Utilice -h para ver como ejecutar el programa"
                );
                break;
        }
    }
}