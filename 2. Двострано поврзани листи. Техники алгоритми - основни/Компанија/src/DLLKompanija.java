import java.util.Scanner;

class DLLNode<E>{
    protected int id;
    protected int plata;
    protected DLLNode<E> pred;
    protected DLLNode<E> succ;
    public DLLNode(int id, int plata, DLLNode<E> pred, DLLNode<E> succ) {
        this.plata = plata;
        this.id = id;
        this.pred = pred;
        this.succ = succ;
    }

    public int getId() {
        return id;
    }

    public int getPlata() {
        return plata;
    }

    public void swapIdAndElement(int newId, int newPlata){
        this.id = newId;
        this.plata = newPlata;
    }
}

class DLL<E>{
    private DLLNode<E> first;
    private DLLNode<E> last;
    public DLL() {
        this.first = null;
        this.last = null;
    }

    public void insertFirst(int id, int plata) {
        DLLNode<E> insert = new DLLNode<E>(id, plata, null, first);
        if(first == null) {
            last = insert;
        }
        else {
            first.pred = insert;
        }
        first = insert;
    }

    public void insertLast(int id, int plata) {
        if(first == null)
            insertFirst(id, plata);
        else {
            DLLNode<E> insert = new DLLNode<E>(id, plata, last, null);
            last.succ = insert;
            last = insert;
        }
    }

    public void insertAfter(int id, int plata, DLLNode<E> after) {
        if(after == last)
            insertLast(id, plata);
        else {
            DLLNode<E> insert = new DLLNode<E>(id, plata, after, after.succ);
            after.succ.pred = insert;
            after.succ = insert;
        }
    }

    public int deleteFirst() {
        if (first != null){
            DLLNode<E> tmp = first;
            first = first.succ;
            if (first == null)
                last = null;
            else{
                first.pred = null;
            }
            return tmp.plata;
        }
        return 0;

    }

    public int deleteLast() {
        if(first != null) {
            if(first.succ == null) {
                return deleteFirst();
            }
            else {
                DLLNode<E> tmp = last;
                last = last.pred;
                last.succ = null;
                return tmp.plata;
            }
        }
        return 0;
    }

    public int delete(DLLNode<E> node) {
        if(node == first) {
            deleteFirst();
            return node.plata;
        }
        if(node == last) {
            deleteLast();
            return node.plata;
        }

        node.pred.succ = node.succ;
        node.succ.pred = node.pred;
        return node.plata;
    }

    public DLLNode<E> getFirst() {
        return first;
    }

    public void sortDll() {
        if(first == null)
            return;
        DLLNode<E> tmp = first;
        while(tmp.succ!=null) {
            DLLNode<E> tmp2 = tmp.succ;
            while(tmp2 != null) {
                if(tmp2.getId() > tmp.getId()) {
                    int swapperId = tmp.getId();
                    int swapperEl = tmp.getPlata();

                    tmp.swapIdAndElement(tmp2.getId(), tmp2.getPlata());
                    tmp2.swapIdAndElement(swapperId, swapperEl);
                }
                tmp2 = tmp2.succ;
            }
            tmp = tmp.succ;
        }
    }
}

public class DLLKompanija {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        DLL<Integer> inputDll = new DLL<Integer>();
        for(int i = 0; i < n; i++) {
            int insertId = sc.nextInt();
            int insertPlata = sc.nextInt();
            inputDll.insertLast(insertId, insertPlata);
        }
        int iznosOtstrana = sc.nextInt();

        DLL<Integer> filteredDll = new DLL<Integer>();

        DLLNode<Integer> tmp = inputDll.getFirst();
        boolean daliImaFlag = false;
        while(tmp != null) {
            if(tmp.getPlata() >= iznosOtstrana) {
                filteredDll.insertLast(tmp.getId(), tmp.getPlata());
                daliImaFlag = true;
            }
            tmp = tmp.succ;
        }

        if(!daliImaFlag){
            System.out.print("nema");
            return;
        }

        filteredDll.sortDll();

        tmp = filteredDll.getFirst();
        while(tmp != null) {
            String s = tmp.getId() + " " + tmp.getPlata() + "\n";
            System.out.print(s);
            tmp = tmp.succ;
        }
    }

}
