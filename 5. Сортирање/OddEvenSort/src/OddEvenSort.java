import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class OddEvenSort {

    static void oddEvenSort(int a[], int n)
    {
        // Vasiot kod tuka

        int numbEven = 0, numbOdd = 0;

        for(int i = 0; i < a.length; i++){
            if(a[i] % 2 == 0)
                numbEven++;
            else{
                numbOdd++;
            }
        }
        List<Integer> evensList = new ArrayList<Integer>(numbEven);
        List<Integer> oddsList = new ArrayList<Integer>(numbOdd);

        for(int i = 0; i < a.length; i++){
            if(a[i] % 2 == 0) {
                evensList.add(a[i]);
            }
            else{
                oddsList.add(a[i]);
            }
        }
        Collections.sort(evensList, Collections.reverseOrder());
        Collections.sort(oddsList);

        //a = new int[numbEven+numbOdd];
        for(int i = 0; i < numbOdd; i++){
            a[i] = oddsList.get(i);
        }
        int marker = numbOdd;
        for(int i = 0; i < numbEven; i++){
            a[marker++] = evensList.get(i);
        }
    }

    public static void main(String[] args) throws IOException{
        int i;
        BufferedReader stdin = new BufferedReader( new InputStreamReader(System.in));
        String s = stdin.readLine();
        int n = Integer.parseInt(s);

        s = stdin.readLine();
        String [] pom = s.split(" ");
        int [] a = new int[n];
        for(i=0;i<n;i++)
            a[i]=Integer.parseInt(pom[i]);
        oddEvenSort(a,n);
        for(i=0;i<n-1;i++)
            System.out.print(a[i]+" ");
        System.out.print(a[i]);
    }
}