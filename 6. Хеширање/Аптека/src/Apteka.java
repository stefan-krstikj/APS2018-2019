import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class MapEntry<K extends Comparable<K>,E> implements Comparable<K> {

    // Each MapEntry object is a pair consisting of a key (a Comparable
    // object) and a value (an arbitrary object).
    K key;
    E value;

    public MapEntry (K key, E val) {
        this.key = key;
        this.value = val;
    }

    public int compareTo (K that) {
        // Compare this map entry to that map entry.
        @SuppressWarnings("unchecked")
        MapEntry<K,E> other = (MapEntry<K,E>) that;
        return this.key.compareTo(other.key);
    }

    public String toString () {
        return "<" + key + "," + value + ">";
    }
}

class SLLNode<E> {
    protected E element;
    protected SLLNode<E> succ;

    public SLLNode(E elem, SLLNode<E> succ) {
        this.element = elem;
        this.succ = succ;
    }

    @Override
    public String toString() {
        return element.toString();
    }
}

class CBHT<K extends Comparable<K>, E> {

    // An object of class CBHT is a closed-bucket hash table, containing
    // entries of class MapEntry.
    private SLLNode<MapEntry<K,E>>[] buckets;

    @SuppressWarnings("unchecked")
    public CBHT(int m) {
        // Construct an empty CBHT with m buckets.
        buckets = (SLLNode<MapEntry<K,E>>[]) new SLLNode[m];
    }

    private int hash(K key) {
        // Translate key to an index of the array buckets.
        String s = key.toString();
        s.toUpperCase();

       // int a = (29 * (29 * (29 * 0+(int)(s.charAt(0)) + (int)  (s.charAt(1))+(int)(s.charAt(2)))))%102780;
        int b = ((28 * (29 * (20 * 0 + (int)(s.charAt(0))) + (int)(s.charAt(1))) + (int)s.charAt(2)) % 102780);
        return b;
    }

    public SLLNode<MapEntry<K,E>> search(K targetKey) {
        // Find which if any node of this CBHT contains an entry whose key is
        // equal
        // to targetKey. Return a link to that node (or null if there is none).
        int b = hash(targetKey);
        for (SLLNode<MapEntry<K,E>> curr = buckets[b]; curr != null; curr = curr.succ) {
            if (targetKey.equals(((MapEntry<K, E>) curr.element).key))
                return curr;
        }
        return null;
    }

    public void insert(K key, E val) {		// Insert the entry <key, val> into this CBHT.
        MapEntry<K, E> newEntry = new MapEntry<K, E>(key, val);
        int b = hash(key);
        for (SLLNode<MapEntry<K,E>> curr = buckets[b]; curr != null; curr = curr.succ) {
            if (key.equals(((MapEntry<K, E>) curr.element).key)) {
                // Make newEntry replace the existing entry ...
                curr.element = newEntry;
                return;
            }
        }
        // Insert newEntry at the front of the 1WLL in bucket b ...
        buckets[b] = new SLLNode<MapEntry<K,E>>(newEntry, buckets[b]);
    }

    public void delete(K key) {
        // Delete the entry (if any) whose key is equal to key from this CBHT.
        int b = hash(key);
        for (SLLNode<MapEntry<K,E>> pred = null, curr = buckets[b]; curr != null; pred = curr, curr = curr.succ) {
            if (key.equals(((MapEntry<K,E>) curr.element).key)) {
                if (pred == null)
                    buckets[b] = curr.succ;
                else
                    pred.succ = curr.succ;
                return;
            }
        }
    }

    public String toString() {
        String temp = "";
        for (int i = 0; i < buckets.length; i++) {
            temp += i + ":";
            for (SLLNode<MapEntry<K,E>> curr = buckets[i]; curr != null; curr = curr.succ) {
                temp += curr.element.toString() + " ";
            }
            temp += "\n";
        }
        return temp;
    }

}

class Lek{
    public String imeLek;
    public int pozLista;
    public int cena;
    public int broj;

    public Lek(String imeLek, int pozLista, int cena, int broj) {
        this.imeLek = imeLek;
        this.pozLista = pozLista;
        this.cena = cena;
        this.broj = broj;
    }

    @Override
    public String toString() {
        String s = imeLek.toUpperCase()+ "\n";
        if(pozLista == 1)
            s += "POZ" + "\n";
        else
            s += "NEG" + "\n";
        s+=cena + "\n" + broj;
        return s;
    }
}

public class Apteka {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        CBHT<String, Lek> table = new CBHT<String, Lek>(102780);

        for(int i = 0; i < n; i++) {
            String[] pom = br.readLine().split(" ");
            Lek lek = new Lek(pom[0], Integer.parseInt(pom[1]) , Integer.parseInt(pom[2]), Integer.parseInt(pom[3]));
            table.insert(pom[0], lek);
        }

        String reader;
        while(!(reader = br.readLine()).equals("KRAJ")){
            String findLekName = reader.toUpperCase();
            int findLekQuantity = Integer.parseInt(br.readLine());

            SLLNode<MapEntry<String, Lek>> tmp = table.search(findLekName);
            SLLNode<MapEntry<String, Lek>> tmp2 = table.search(findLekName.toLowerCase());
            if(tmp!= null && tmp.element.value.broj >= findLekQuantity){

                System.out.println(tmp.element.value);
                tmp.element.value.broj -= findLekQuantity;
                System.out.println("Napravena naracka");
            }
            else if(tmp!= null && tmp.element.value.broj < findLekQuantity){
                System.out.println(tmp.element.value);
                System.out.println("Nema dovolno lekovi");
            }
            else{
                if(tmp2!= null && tmp2.element.value.broj >= findLekQuantity){

                    System.out.println(tmp2.element.value);
                    tmp.element.value.broj -= findLekQuantity;
                    System.out.println("Napravena naracka");
                }
                else if(tmp2!= null && tmp2.element.value.broj < findLekQuantity){
                    System.out.println(tmp2.element.value);
                    System.out.println("Nema dovolno lekovi");
                }
                else{
                    System.out.println("Nema takov lek");
                }

            }
        }

    }
}
