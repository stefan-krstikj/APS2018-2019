import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class SLLNode<E>{
    E element;
    protected SLLNode<E> next;

    public SLLNode(E element, SLLNode<E> next){
        this.element = element;
        this.next = next;
    }
}

class SLL<E>{
    private SLLNode<E> first;
    public SLL(){
        this.first = null;
    }

    public void insertFirst(E el){
        SLLNode<E> insert = new SLLNode<E>(el, first);
        first = insert;
    }

    public void insertAfter(E el, SLLNode<E> node){
        if(node!=null){
            SLLNode<E> insert = new SLLNode<E>(el, node.next);
            node.next = insert;
        }
    }

    public void insertBefore(E el, SLLNode<E> before){
        if(first!=null){
            SLLNode<E> tmp = first;
            if(first == before){
                this.insertFirst(el);
                return;
            }
            while(tmp.next != null){
                tmp = tmp.next;
                if(tmp.next == before){
                    SLLNode<E> insert = new SLLNode<E>(el, before);
                    tmp.next = insert;
                }
            }
        }
    }

    public void insertLast(E el){
        if(first!=null){
            SLLNode<E> tmp = first;
            while(tmp.next != null){
                tmp = tmp.next;
            }
            SLLNode<E> insert = new SLLNode<E>(el, null);
            tmp.next = insert;
        }
        else{
            insertFirst(el);
            return;
        }
    }

    public SLLNode<E> getFirst(){ return first; }

    public String toString()
    {
        String s = new String();
        SLLNode<E> pointer = first;
        while(pointer!=null)
        {
            s = s+pointer.element+" ";
            pointer=pointer.next;
        }
        return s;
    }
}


public class SpecialSLLJoin {

    private static SLL<Integer> specialJoin(SLL<Integer> lista1, SLL<Integer> lista2){
        SLL<Integer> newList = new SLL<>();
        SLLNode<Integer> currentPointer1 = lista1.getFirst();
        SLLNode<Integer> currentPointer2 = lista2.getFirst();

        int currentP = 0;
        int currentP2 = 0;
        while(currentPointer1 != null || currentPointer2 != null){
            currentP = 0;
            currentP2 = 0;
            while(currentPointer1!=null&&currentP != 2){
                newList.insertLast(currentPointer1.element);
                currentPointer1 = currentPointer1.next;
                currentP++;
            }

            while(currentPointer2!=null&&currentP2 != 2){
                newList.insertLast(currentPointer2.element);
                currentPointer2 = currentPointer2.next;
                currentP2++;
            }
        }
        return newList;
    }

    public static void main(String[] args) throws IOException{

        SLL<Integer> lista1 = new SLL<>();
        SLL<Integer> lista2 = new SLL<>();

        BufferedReader stdin = new BufferedReader(new InputStreamReader(
                System.in));
        String s = stdin.readLine();
        int N = Integer.parseInt(s);
        s = stdin.readLine();
        String[] pomniza = s.split(" ");
        for (int i = 0; i < N; i++) {
            lista1.insertLast(Integer.parseInt(pomniza[i]));
        }

        s = stdin.readLine();
        N = Integer.parseInt(s);
        s = stdin.readLine();
        pomniza = s.split(" ");
        for (int i = 0; i < N; i++) {
            lista2.insertLast(Integer.parseInt(pomniza[i]));
        }

        SLL<Integer> spoeni = specialJoin(lista1,lista2);
        System.out.println(spoeni);

    }
}
