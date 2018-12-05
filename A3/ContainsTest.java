public class ContainsTest{
    public static FineGrainedContains test = new FineGrainedContains<Object>();

    public static void main(String args[]){

        // Create the linkedList structure
        Node c = new Node();
        c.item = 3;
        c.key = c.item.hashCode();
        c.next = test.tail;

        Node b = new Node();
        b.item = 2;
        b.key = b.item.hashCode();
        b.next = c;


        Node a = new Node();
        a.item = 1;
        a.key = a.item.hashCode();
        a.next = b;

        test.head.next = a;

        // setup and run the tester threads
        Tester[] testers = new Tester[5];
        
        for(int i = 0; i < testers.length; i++)
        {
            testers[i] = new Tester();
            testers[i].start();
        }
    }

    /**
     * Class that will run the contains method for a thread
     */
    private static class Tester extends Thread {
        public void run() {
            System.out.println("Thread with id = " + this.getId() + " Searching for value = 3.");
            
            boolean found = test.contains(3);

            if (found)
            {
                System.out.println("Thread with id = " + this.getId() + " found value");
            }
            else
            {
                System.out.println("Thread with id = " + this.getId() + " did not find value");
            }
        }
    }

}