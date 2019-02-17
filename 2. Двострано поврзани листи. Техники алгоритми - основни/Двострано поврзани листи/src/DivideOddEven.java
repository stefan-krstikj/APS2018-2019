import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class DLLNode<E>{
    public E element;
    protected DLLNode<E> pred, succ;

    public DLLNode(E elem, DLLNode<E> pred, DLLNode<E> succ){
        this.element = elem;
        this.pred = pred;
        this.succ = succ;
    }

}

class DLL<E>{
    private DLLNode<E> first, last;
    public DLL(){
        this.first = null;
        this.last = null;
    }

    public void insertFirst(E o){
        DLLNode<E> ins = new DLLNode<E>(o, null, first);
        if(first == null){
            last = ins;
        }else{
            first.pred = ins;
        }
        first = ins;
    }

    public void insertLast(E o){
        if(first == null){
            insertFirst(o);
        }
        else{
            DLLNode<E> insert = new DLLNode<E>(o, last, null);
            last.succ = insert;
            last = insert;
        }
    }

    public void insertAfter(E o, DLLNode<E> after){
        if(after==last){
            insertLast(o);
            return;
        }

        DLLNode<E> ins = new DLLNode(o, after, after.succ);
        after.succ.pred = ins;
        after.succ = ins;
    }

    public DLLNode<E> getFirst(){ return first; }
}


public class DivideOddEven {

    public static void podeliListaIPecati(DLL<Integer> niza){
        DLL<Integer> parna = new DLL<Integer>();
        DLL<Integer> neparna = new DLL<Integer>();

        DLLNode<Integer> tmp = niza.getFirst();
        while(tmp!=null){
            if(tmp.element % 2 == 0){
                parna.insertLast(tmp.element);
            }else{
                neparna.insertLast(tmp.element);
            }
            tmp = tmp.succ;
        }

        boolean printEmpty = false;
        tmp = neparna.getFirst();
        while(tmp!=null){
            if(tmp.succ == null){
                System.out.print(tmp.element);
                break;
            }
            System.out.print(tmp.element + " ");
            tmp = tmp.succ;
            printEmpty = true;
        }

        if(printEmpty)
            System.out.println();

        tmp = parna.getFirst();
        while(tmp!=null){
            if(tmp.succ == null){
                System.out.print(tmp.element);
                break;
            }
            System.out.print(tmp.element + " ");
            tmp = tmp.succ;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        String s = stdin.readLine();
        int N = Integer.parseInt(s);
        s = stdin.readLine();
        String[] pomniza = s.split(" ");
        DLL<Integer> lista = new DLL<Integer>();
        for (int i = 0; i < N; i++) {
            lista.insertLast(Integer.parseInt(pomniza[i]));
        }

        podeliListaIPecati(lista);
    }
}
