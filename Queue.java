import java.util.*;

class MyQueue<T>{
    public Object[] arr;
    int front,rear;
    int curr_size;
    //Constructor to initialize a queue
    public MyQueue(int size) {
        arr=new Object[size];
        curr_size=0;
        front=-1;
        rear=-1;
    }
    //method to get the current size of the queue
    public int getCurr_size() {
        return curr_size;
    }
    //method to get the element of a given index
    public Object getElement(int i){
        if(i<0 || i>=curr_size){
            throw new ArrayIndexOutOfBoundsException("Error");
        }
        int index=(front+i)%arr.length;
        return arr[index];
    }
    //the method to enqueue an element into the queue
    public void enqueue(T element){
        if (curr_size == arr.length) {
            throw new ArrayIndexOutOfBoundsException("Overflow");   //if current size is same as the length of the array size then throw an error
        }
        rear = (rear + 1) % arr.length;  //set the rear as to like this so that if it exceeds the last index then it can return to the 0th index to make the circular queue
        arr[rear] = element;
        if (curr_size == 0) {
            front = rear; // if the queue is empty then just initialize the front and rear to 0th index
        }
        curr_size++;//on each addition increase the current size variable
    }
    // method to dequeue an element of the queue and return it
    public T dequeue(){
        if(getCurr_size()==0){
            throw new ArrayIndexOutOfBoundsException("Underflow");     // before popping check if the current size is 0 or not, if 0 then throw an exception
        }
        T temp=(T) arr[front];  //store the front index variable into a temp var to return it afterward
        if(curr_size==1){
            front=rear=-1;          //destory the queue if current size is 1 and move the front and rear to -1
            curr_size--;
        }
        else{
            front=(front+1)%arr.length;//same as rear ,while popping do the same with front to maintain a circular queue
            curr_size--;
        }
        return temp;
    }
    //method to print the queue
    public void print() {
        if (curr_size == 0) {
            System.out.println("Queue is empty");
            return;
        }
        System.out.print("Queue elements:");
        for (int i = 0; i < curr_size; i++) {
            int index = (front + i) % arr.length;
            System.out.print(" " + arr[index]);
        }
        System.out.println();
    }
    //method to check whether two queues contains similar elements or not
    public static <T> boolean similarity(MyQueue<T> q1,MyQueue<T> q2){
        if (q1.getCurr_size() != q2.getCurr_size()) return false;//if no values are  there int the queue then return false

        HashSet<Object> hs1=new HashSet<>();
        HashSet<Object> hs2=new HashSet<>();
        for(int i=0;i<q1.getCurr_size();i++){
            hs1.add(q1.getElement(i));
        }
        for(int j=0;j<q2.getCurr_size();j++){
            hs2.add(q2.getElement(j));
        }
        return hs1.equals(hs2);
    }
}
public class Program1 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        MyQueue<Object> q1 = new MyQueue<>(4);
        MyQueue<Object> q2 = new MyQueue<>(5);

        while (true){
            System.out.println("want to add element ? yes or no");
            String choice= sc.next().toLowerCase();
            if(choice.equals("no")){
                break;
            }
            else{
                System.out.println("in which queue you want to add?");
                int q_num=sc.nextInt();
                if(q_num==1){
                    System.out.println("enter the element you want to add");
                    if(sc.hasNextInt()) q1.enqueue(sc.nextInt());
                    else if(sc.hasNextDouble()) q1.enqueue(sc.nextDouble());
                    else q1.enqueue(sc.next());
                    q1.print();

                } else if (q_num==2) {
                    System.out.println("enter the element you want to add");
                    if(sc.hasNextInt()) q2.enqueue(sc.nextInt());
                    else if(sc.hasNextDouble()) q2.enqueue(sc.nextDouble());
                    else q2.enqueue(sc.next());
                    q2.print();
                }
            }
        }
        System.out.println("q1 elements are");
        q1.print();
        System.out.println("q2 elements are");
        q2.print();

        if(MyQueue.similarity(q1,q2)){
            System.out.println("Both the queues have same elements");
        }
        else{
            System.out.println("The queues does not have the same elements");
        }

    }
}
