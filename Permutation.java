import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;


public class Permutation {
    public static void main(String[] args) {
        RandomizedQueue<String> randQ = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            randQ.enqueue(item);
        }
        final int K = Integer.parseInt(args[0]);
        for (int i = 0; i < K; i++) StdOut.println(randQ.dequeue());
    }
}
