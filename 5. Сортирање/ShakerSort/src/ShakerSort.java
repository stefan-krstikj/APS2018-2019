    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;

    public class ShakerSort {

        static void swap(int a, int b){
            int tmp = a;
            a = b;
            b = a;
        }

        static void shakerSort(int a[], int n)
        {
            // Vasiot kod tuka
            int levo = 0, desno = 0;
            for(int i = 0; i < n-1; i++){
                for(int j =  n-i-1; j > i; j--) {
                    if(a[j] < a[i]){
                        // swap
                        int tmp = a[j];
                        a[j] = a[i];
                        a[i] = tmp;
                    }
                }


                for(int j = 0; j < n; j++){
                    System.out.print(a[j] + " ");
                }
                System.out.println();
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
            shakerSort(a,n);
        }
    }