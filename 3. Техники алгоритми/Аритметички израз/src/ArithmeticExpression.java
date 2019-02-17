import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ArithmeticExpression {

    // funkcija za presmetuvanje na izrazot pocnuvajki
    // od indeks l, zavrsuvajki vo indeks r
    static int presmetaj(char c[], int l, int r) {
        // Vasiot kod tuka
        if(r-l == 4){ // sme stignale do forma (1+2)
            int a = Character.getNumericValue(c[l+1]);
            int b = Character.getNumericValue(c[r-1]);
            if(c[l+2] == '+'){
                return a+b;
            }
            else{
                return a-b;
            }
        }

        int zag = 0;
        for(int i = l+1; i < r; i++){
            if(c[i] == '(')
                zag++;
            if(c[i] == ')')
                zag--;
            if(zag == 0){
                if(c[i+1] == '+'){
                    return presmetaj(c, l+1, i) + presmetaj(c, i+2, r-1);
                }
                else{
                    return presmetaj(c, l+1, i) - presmetaj(c, i+2, r-1);
                }
            }
        }
        return 50;
    }

    public static void main(String[] args) throws Exception {
        int i,j,k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String expression = br.readLine();
        char exp[] = expression.toCharArray();

        int rez = presmetaj(exp, 0, exp.length-1);
        System.out.println(rez);

        br.close();

    }

}
