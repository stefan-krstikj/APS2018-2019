import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Scanner;

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


class MapEntry<K extends Comparable<K>, E> implements Comparable<K>{
    K key;
    E value;

    public MapEntry(K key, E val){
        this.key = key;
        this.value = val;
    }

    public int compareTo(K that){
        @SuppressWarnings("unchecked")
        MapEntry<K, E> other = (MapEntry<K, E>) that;
        return this.key.compareTo(other.key);
    }

    public String toString(){
        return "<" + key + "," + value + ">";
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
        return Math.abs(key.hashCode()) % buckets.length;
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
public class RoutingHashJava{
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        CBHT<String, String[]> table = new CBHT<String, String[]>(n*2);
        for(int i = 0; i < n; i++){
            String key = br.readLine();
            String val = br.readLine();
            String[] allVals = val.split(",");
            // pravime Key so soodvetna niza na vrednosti allVals. T.e za sekoj key imame niza od values sto moze da pripagaat
            table.insert(key, allVals);
        }

        n = Integer.parseInt(br.readLine());
        for(int i = 0; i < n; i++) {
            String addToCheck = br.readLine();
            String rangeToCheck = br.readLine();

            SLLNode<MapEntry<String, String[]>> tmp = table.search(addToCheck);

            if (tmp == null) {
                System.out.println("ne postoi");
                continue;
            }
            String[] pomToCheck = rangeToCheck.split("\\.");
            String[] pomInTable = tmp.element.value;
            boolean flag = false; // se ispolnuva vo true koga barem eden od nizata pomInTable e ednakov
            for (int j = 0; j < pomInTable.length; j++) {
                String[] localPom = pomInTable[j].split("\\.");
                if (pomToCheck[0].equals(localPom[0]) && pomToCheck[1].equals(localPom[1]) && pomToCheck[2].equals(localPom[2])) {
                    flag = true; // ako barem eden e tocen, namesti flag True i break za da ne gubis vreme
                    break;
                }
            }
            if (flag == true) {
                System.out.println("postoi");
            } else {
                System.out.println("ne postoi");
            }
        }
    }
}