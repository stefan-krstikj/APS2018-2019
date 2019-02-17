import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;

class ArrayStack<E>{
    private E[] elem;
    private int depth;

    public ArrayStack(int maxDepth){
        elem = (E[]) new Object[maxDepth];
        depth = 0;
    }

    public boolean isEmpty() {
        return depth==0;
    }

    public void push(E x) {
        elem[depth++] = x;
    }

    public E pop() {
        if(depth == 0)
            throw new NoSuchElementException();
        E topmost = elem[--depth];
        elem[depth] = null;
        return topmost;
    }

    public E peek() {
        if(depth == 0)
            throw new NoSuchElementException();
        return elem[depth-1];
    }
}

public class CheckXML {

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int n = Integer.parseInt(s);
        String [] redovi = new String[n];

        for(int i=0;i<n;i++)
            redovi[i] = br.readLine();

        int valid = 1;

        // Vasiot kod tuka
        ArrayStack<String> stack = new ArrayStack<String>(100);
        stack.push(redovi[0]);
        for(int i = 1; i < redovi.length; i++) {
            if(redovi[i].charAt(0) != '[')
                continue;
            if(redovi[i].charAt(1) == '/') {
                // brisi go prethodniot ako e ist, ako ne, break i 0 vrati
                if(stack.peek().substring(1, stack.peek().length()-1)
                        .equals(redovi[i].substring(2, redovi[i].length()-1))) {
                    stack.pop();
                }
                else {
                    valid = 0;
                    break;
                }
            }
            else {
                // dodaj vo stack
                stack.push(redovi[i]);
            }
        }


        // Moze da koristite dopolnitelni funkcii ako vi se potrebni

        System.out.println(valid);

        br.close();
    }
}