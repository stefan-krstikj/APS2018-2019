import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ZigZagSequence {

    static int najdiNajdolgaCikCak(int a[]) {
        // Vasiot kod tuka


        int najDolzina = 0;
        int currDolzina = 1;
        int trueIfPreviousPos;
        if(a[0] > 0) {
            trueIfPreviousPos = 1;
        }
        else if(a[0] < 0){
            trueIfPreviousPos = 0;
        }
        else {
            trueIfPreviousPos = -1;
        }
        for(int i = 0; i < a.length-1; i++){
            if((a[i] > 0&&a[i+1] < 0) || (a[i] < 0 && a[i+1] > 0)) {
                currDolzina++;
                if(currDolzina >= najDolzina)
                    najDolzina = currDolzina;
            }
            else {
                if(currDolzina >= najDolzina)
                    najDolzina = currDolzina;
                currDolzina = 1;
            }

            if(i == a.length-2) {
                break;
            }
        }
        return najDolzina;
    }

    public static void main(String[] args) throws Exception {
        int i,j,k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int a[] = new int[N];
        for (i=0;i<N;i++)
            a[i] = Integer.parseInt(br.readLine());

        int rez = najdiNajdolgaCikCak(a);
        System.out.println(rez);

        br.close();

    }

}
