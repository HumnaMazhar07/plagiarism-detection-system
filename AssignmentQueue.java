package application;

public class AssignmentQueue {

    AssignmentNode front, rear;
    public static int count = 0;  

    public void enqueue(Assignment a) {

        if (count == 3) {
            return; 
        }

        AssignmentNode n = new AssignmentNode(a);

        if (front == null) {
            front = rear = n;
        } else {
            rear.next = n;
            rear = n;
        }
        count++;
    }

  
    public Assignment[] toArray() {
        Assignment[] arr = new Assignment[count];
        AssignmentNode temp = front;
        int i = 0;
        while (temp != null) {
            arr[i++] = temp.data;
            temp = temp.next;
        }
        return arr;
    }
}
