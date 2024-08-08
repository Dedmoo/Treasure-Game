package Game;

public class ArrayStack<T>{
    private T[] array;
    private int top;

    public ArrayStack(int capacity){
        array = (T[]) new Object[capacity];
        top = -1;
    }
    

    public void push(T element){
        if (!isFull()) {
            top++;              
            array[top] = element; 
        } else {
            System.out.println("Stack overflow");
        }
    }


    public T pop(){
        if (!isEmpty()) {
            return array[top--];
        }
        
        System.out.println(" Stack overflow ");
        return null;
    }

    public T peek(){
        if (!isEmpty()) {
            return array[top];
        }
        
        System.out.println(" Stack overflow ");
        return null;
    }

    public boolean isFull(){
        return top == array.length - 1;
    }

    public boolean isEmpty(){
        return top == -1;
    }

    public Object[] getAllElements(){
        Object[] result = new Object[top + 1];
        for (int i = 0; i < top + 1; i++) {
            result[i] = array[i];
        }
        return result;
    }

    @Override
    public String toString(){
        String s = "Stack: [";
        for (int i = 0; i <= top; i++) {
            s += array[i].toString();
            if (i < top) {
                s += ", ";
            }
        }
        s += "]";
        return s;
    }
}
