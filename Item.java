import java.lang.reflect.Constructor;

public class Item {
    int peso;
    int valor;
    float ratio;

    Item(int peso, int valor){
        this.peso = peso;
        this.valor = valor;
        ratio = (float) peso/valor;
    }
}
