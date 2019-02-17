import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;

interface Stack<E>{
    public boolean isEmpty();
    public E peek();
    public void clear();
    public void push(E ex);
    public E pop();
}

class ArrayStack<E> implements Stack<E>{
    private E[] elems;
    private int depth;

    public ArrayStack(int maxDepth) {
        depth = 0;
        elems = (E[]) new Object[maxDepth];
    }

    public boolean isEmpty() {
        return(depth == 0);
    }

    public E peek() {
        if(depth == 0)
            throw new NoSuchElementException();
        return elems[depth-1];
    }

    public void clear() {
        for(int i = 0; i < depth; i++)
            elems[i] = null;
        depth = 0;
    }

    public void push(E x) {
        elems[depth++] = x;
    }

    public E pop() {
        if(depth == 0)
            throw new NoSuchElementException();
        E topmost = elems[--depth];
        elems[depth] = null;
        return topmost;
    }
}
public class ExpressionEvaluator {

    public static int evaluateExpression(String expression){
        // Vasiot kod tuka
        ArrayStack<Integer> array = new ArrayStack<Integer>(50);
        char[] exp = expression.toCharArray();

        for(int i = 0; i < exp.length; i++) {
            if(Character.isDigit(exp[i])) {
                StringBuilder sb = new StringBuilder();
                while(Character.isDigit(exp[i])) {
                    sb.append(exp[i]);
                    i++;
                }
                String s = sb.toString();
                array.push(Integer.parseInt(s));
            }
            if(exp[i] == '+') {
                int br1 = array.pop();
                int br2 = array.pop();
                array.push(br1+br2);
            }
            else if(exp[i] == '*') {
                int br1 = array.pop();
                int br2 = array.pop();
                array.push(br1 * br2);
            }
        }

        return array.pop();

    }
    public static void main(String[] args) throws IOException {
        BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
        System.out.println(evaluateExpression(input.readLine()));
    }

}