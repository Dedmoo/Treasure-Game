package Game;

public class LinkedList<T>{
    Node<T> head;

    public boolean isEmpty(){
        return head == null;
    }

    public void addNode(T data, int maxDoors){
        Node<T> newNode = new Node<>(data, maxDoors);
        if (isEmpty()) {
            
            head = newNode;
        } else {
            
            Node<T> temp = head;
            while (temp.next != null){
                temp = temp.next;
            }
            temp.next = newNode;
        }
    }

    public Node<T> getNode(T data){
        Node<T> temp = head;
        while (temp != null){
            if (temp.data.equals(data)){
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }
}
