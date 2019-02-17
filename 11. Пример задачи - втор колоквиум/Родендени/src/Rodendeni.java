import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Rodendeni {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Map<Integer, ArrayList<String>> hash = new HashMap<>(12);

        for(int i = 0; i < n; i++){
            String[] in = br.readLine().split(" ");
            String[] date = in[1].split("\\.");
            int dateInsert = Integer.parseInt(date[1]);
            String nameInsert = in[0];

            ArrayList<String> tmpSet = hash.get(dateInsert);
            if(tmpSet == null)
                tmpSet = new ArrayList<>();
            if(tmpSet.contains(nameInsert))
                continue;
            tmpSet.add(nameInsert);
            hash.put(dateInsert, tmpSet);

        }

        int m = Integer.parseInt(br.readLine());
        ArrayList<String> namesToPrint = hash.get(m);
        if(namesToPrint == null){
            System.out.println("Nema");

        }
        else {
            for (String s : namesToPrint) {
                System.out.print(s + " ");
            }
        }
    }
}
