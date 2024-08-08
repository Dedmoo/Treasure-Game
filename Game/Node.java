package Game;

public class Node<T>{
    public T data;
    public Node<T> next;
    public int[] doors;
    public boolean treasure;
    public boolean trap;

    public Node(T data, int maxDoors){
        this.data = data;
        this.doors = new int[maxDoors];
        this.treasure = false;
        this.trap = false;
    }

    @Override
    public String toString(){
        return "Node with data: " + data.toString();
    }
}
