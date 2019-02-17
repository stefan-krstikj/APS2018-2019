import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

class GraphNode<E> {
    private int index;//index (reden broj) na temeto vo grafot
    public E info;
    private LinkedList<GraphNode<E>> neighbors;

    public GraphNode(int index, E info) {
        this.index = index;
        this.info = info;
        neighbors = new LinkedList<GraphNode<E>>();
    }

    boolean containsNeighbor(GraphNode<E> o){
        return neighbors.contains(o);
    }

    void addNeighbor(GraphNode<E> o){
        neighbors.add(o);
    }


    void removeNeighbor(GraphNode<E> o){
        if(neighbors.contains(o))
            neighbors.remove(o);
    }


    @Override
    public String toString() {
        String ret = info + "";
       // for(int i=0;i<neighbors.size();i++)
       //     ret+=neighbors.get(i).info+" ";
        return ret;

    }

    @Override
    public boolean equals(Object obj) {
        @SuppressWarnings("unchecked")
        GraphNode<E> pom = (GraphNode<E>)obj;
        return (pom.info.equals(this.info));
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public E getInfo() {
        return info;
    }

    public void setInfo(E info) {
        this.info = info;
    }

    public LinkedList<GraphNode<E>> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(LinkedList<GraphNode<E>> neighbors) {
        this.neighbors = neighbors;
    }



}

class Graph<E> {

    int num_nodes;
    GraphNode<E> adjList[];

    @SuppressWarnings("unchecked")
    public Graph(int num_nodes, E[] list) {
        this.num_nodes = num_nodes;
        adjList = (GraphNode<E>[]) new GraphNode[num_nodes];
        for (int i = 0; i < num_nodes; i++)
            adjList[i] = new GraphNode<E>(i, list[i]);
    }

    @SuppressWarnings("unchecked")
    public Graph(int num_nodes) {
        this.num_nodes = num_nodes;
        adjList = (GraphNode<E>[]) new GraphNode[num_nodes];
        for (int i = 0; i < num_nodes; i++)
            adjList[i] = new GraphNode<E>(i, null);
    }

    int adjacent(int x, int y) {
        // proveruva dali ima vrska od jazelot so
        // indeks x do jazelot so indeks y
        return (adjList[x].containsNeighbor(adjList[y])) ? 1 : 0;
    }

    void addEdge(int x, int y) {
        // dodava vrska od jazelot so indeks x do jazelot so indeks y
        if (!adjList[x].containsNeighbor(adjList[y])) {
            adjList[x].addNeighbor(adjList[y]);
        }
    }

    void deleteEdge(int x, int y) {
        adjList[x].removeNeighbor(adjList[y]);
    }

    /************************TOPOLOGICAL SORT*******************************************************************/

    void dfsVisit(Stack<Integer> s, int i, boolean[] visited){
        if(!visited[i]){
            visited[i] = true;
            Iterator<GraphNode<E>> it = adjList[i].getNeighbors().iterator();
            //System.out.println("dfsVisit: "+i+" Stack="+s + " entry: " + adjList[i].getInfo());
            while(it.hasNext()){
                dfsVisit(s, it.next().getIndex(), visited);
            }
            s.push(i);
           // System.out.println("--dfsVisit: "+i+" Stack="+s);
        }
    }

    void topological_sort_dfs(E last){
        ArrayList<GraphNode<E>> niza = new ArrayList<>();
        boolean visited[] = new boolean[num_nodes];
        for(int i=0;i<num_nodes;i++){
            visited[i] = false;
        }

        Stack<Integer> s = new Stack<Integer>();

        for(int i=0;i<num_nodes;i++){
            dfsVisit(s,i,visited);
        }
        //System.out.println("----Stack="+s);

        while(!s.pop().equals(getNodeIndex(last))){
            //s.pop();

        }
        System.out.println(adjList[s.peek()]);
    }

    int getNodeIndex(E info){
        for(GraphNode node: adjList){
            if(node.getInfo().equals(info)) return node.getIndex();
        }
        return -1;
    }
    /***********************************************************************************************************/

    @Override
    public String toString() {
        String ret = new String();
        for (int i = 0; i < this.num_nodes; i++)
            ret += i + ": " + adjList[i] + "\n";
        return ret;
    }


}

public class IzborPredmet {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        Graph<String> graph = new Graph<>(n);

        for(int i = 0; i < n; i++){
            graph.adjList[i].setInfo(br.readLine());
        }

        ArrayList<String> inputRedosled = new ArrayList<>();

        int m = Integer.parseInt(br.readLine());
        for(int i = 0; i < m; i++){
            String[] in = br.readLine().split(" ");
            inputRedosled.add(in[0]);
            int x = 0;
            for(int j = 0; j < graph.adjList.length; j++){
                if(graph.adjList[j].getInfo().equals(in[0])){
                    x = graph.adjList[j].getIndex();

                }
            }
            int lengthInput = in.length - 1;
            for(int k = 0; k < lengthInput; k++){
                int tmp = 0;
                for(int j = 0; j < graph.adjList.length; j++){
                    if(graph.adjList[j].getInfo().equals(in[k])){
                        tmp = graph.adjList[j].getIndex();

                    }
                }
                graph.addEdge(x, tmp);
            }

        }
        graph.topological_sort_dfs(br.readLine());
    }
}
