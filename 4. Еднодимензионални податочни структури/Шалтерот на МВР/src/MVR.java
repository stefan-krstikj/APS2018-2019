import java.util.NoSuchElementException;
import java.util.Scanner;

class ArrayQueue<E>{
    private E[] elems;
    int length, front, rear;

    public ArrayQueue(int maxLength) {
        elems = (E[]) new Object[maxLength];
        clear();
    }

    public void clear() {
        front = rear = length = 0;
    }

    public boolean isEmpty() {
        return (length == 0);
    }

    public int size() {
        return length;
    }

    public E peek() {
        if(length == 0)
            throw new NoSuchElementException();
        return elems[front];
    }

    public void enqueue(E x) {
        if(length == elems.length)
            return;
        elems[rear++] = x;
        if(rear == elems.length)
            rear = 0;
        length++;
    }

    public E dequeue() {
        if(length == 0)
            throw new NoSuchElementException();
        E frontmost = elems[front];
        elems[front++] = null;
        if(front == elems.length)
            front = 0;
        length--;
        return frontmost;
    }
}

class Gragjanin{
    private String imePrezime;
    int lKarta, pasos, vozacka;
    public Gragjanin(String imePrezime, int lKarta, int pasos, int vozacka) {
        super();
        this.imePrezime = imePrezime;
        this.lKarta = lKarta;
        this.pasos = pasos;
        this.vozacka = vozacka;
    }

    public int getPriority() {
        int priority = 0;
        if(lKarta == 1)
            priority+= 1;
        if(pasos == 1)
            priority+= 2;
        if(vozacka == 1)
            priority+=3;
        return priority;
    }

    public String getName() {
        return imePrezime;
    }
}

public class MVR {

    public static void main(String[] args) {

        Scanner br = new Scanner(System.in);

        ArrayQueue<Gragjanin> array = new ArrayQueue<Gragjanin>(50);

        int N = Integer.parseInt(br.nextLine());
        for(int i=1;i<=N;i++){
            String imePrezime = br.nextLine();
            int lKarta = Integer.parseInt(br.nextLine());
            int pasos = Integer.parseInt(br.nextLine());
            int vozacka = Integer.parseInt(br.nextLine());
            Gragjanin covek = new Gragjanin(imePrezime,lKarta,pasos,vozacka);

            array.enqueue(covek);
        }
        for(int currPr = 1; currPr <= 6; currPr++) {
            ArrayQueue<Gragjanin> tmpArr = array;
            for(int i = array.front; i < array.rear; i++) {
                Gragjanin pr = (Gragjanin) tmpArr.dequeue();
                if(pr.getPriority() == currPr)
                    System.out.println(pr.getName());
            }
        }

    }
}