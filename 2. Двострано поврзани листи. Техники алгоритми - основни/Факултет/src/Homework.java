import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Homework {

    static int minBrojKazneni(int a[]) {
        for(int i = 0; i < a.length-1; i++){
            for(int j = i+1; j < a.length; j++){
                if(a[j] > a[i]){
                    int tmp = a[j];
                    a[j] = a[i];
                    a[i] = tmp;
                }
            }
        }

        int sum = 0;
        for(int i = 0; i < a.length-1; i++){
            sum+=a[i];
            for(int j = i+1; j < a.length; j++){
                sum+=a[j];
            }
        }

        return sum + a[a.length - 1];
    }

    public static void main(String[] args) throws Exception {
        int i;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int a[] = new int[N];

        for (i=0;i<N;i++)
            a[i] = Integer.parseInt(br.readLine());

        int rez = minBrojKazneni(a);

        System.out.println(rez);

        br.close();
    }

}
