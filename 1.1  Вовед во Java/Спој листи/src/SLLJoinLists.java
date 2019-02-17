import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.NoSuchElementException;

class SLLNode<E>{
    protected E element;
    protected SLLNode<E> succ;

    public SLLNode(E element, SLLNode<E> succ){
        this.element = element;
        this.succ = succ;
    }
}

class SLL<E extends Comparable>{
    public SLLNode<E> first;
    public SLL(){
        this.first=null;
    }

    public void insertFirst(E el){
        SLLNode<E> insert = new SLLNode<E> (el, first);
        first = insert;
    }

    public void insertAfter(E el, SLLNode<E> after){
        if(after!=null){
            SLLNode<E> insert = new SLLNode<E> (el, after.succ);
            after.succ = insert;
        }
    }

    public void insertLast(E el){
        if(first!=null){
            SLLNode<E> tmp = first;
            while(tmp.succ != null)
                tmp=tmp.succ;
            SLLNode<E> insert = new SLLNode<E> (el, null);
            tmp.succ = insert;
        }
        else{
            insertFirst(el);
        }
    }


    public SLL<E> joinLists(SLL<E> list2){
        SLL<E> finalString = new SLL<>();
        SLLNode<E> pointer1 = this.first;
        SLLNode<E> pointer2 = list2.first;
        int previous = -1;

        while(pointer1 != null&&pointer2 != null){
            if(pointer1.element.compareTo(pointer2.element)<0){
                if((Integer) pointer1.element != previous){
                    finalString.insertLast(pointer1.element);
                    previous = (Integer) pointer1.element;
                }
                pointer1 = pointer1.succ;
            }
            else{
                if((Integer) pointer2.element != previous){
                    finalString.insertLast(pointer2.element);
                    previous = (Integer) pointer2.element;
                }
                pointer2=pointer2.succ;
            }
        }
        if(pointer1!=null){
            while(pointer1!=null){
                if((Integer) pointer1.element != previous){
                    finalString.insertLast(pointer1.element);
                    previous = (Integer)pointer1.element;
                }
                pointer1 = pointer1.succ;
            }
        }
        if(pointer2!=null){
            while(pointer2!=null){
                if((Integer) pointer2.element != previous){
                    finalString.insertLast(pointer2.element);
                    previous = (Integer)pointer2.element;
                }
                pointer2 = pointer2.succ;
            }
        }
        return finalString;
    }

    public Iterator<E> iterator () {
// vraka iterator koj gi posetuva site elementi na listata od levo na desno
        return new LRIterator<E>();
    }
    // //////////Inner class ////////////
    private class LRIterator<E> implements Iterator<E> {
        private SLLNode<E> place, prev, curr;
        private LRIterator() {
            place = (SLLNode<E>) first;
            curr = prev = null;
        }
        public boolean hasNext() {
            return (place != null);
        }
        public E next() {
            if (place == null)
                throw new NoSuchElementException();
            E nextElem = place.element;
            prev = curr;
            curr = place;
            place = place.succ;
            return nextElem;
        }
        public void remove() {
//Not implemented
        }
    }
}


public class SLLJoinLists {
    public static void main(String[] args) throws IOException {

        SLL<Integer> lista1 = new SLL<>();
        SLL<Integer> lista2 = new SLL<>();

        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
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

        SLL<Integer> spoeni = lista1.joinLists(lista2);
        Iterator<Integer> it = spoeni.iterator();
        while (it.hasNext()) {
            System.out.print(it.next());
            if(it.hasNext())
                System.out.print(" ");
        }

        System.out.println();
    }
}

