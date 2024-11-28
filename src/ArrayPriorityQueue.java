import java.util.ArrayList;

public class ArrayPriorityQueue  implements PriorityQueue{
    private ArrayList<Pair> queue;

    public ArrayPriorityQueue() {
        queue = new ArrayList<>();
    }

    @Override
    public void insert(int x, int p) {
        Pair newElement = new Pair(x, p);
        int index = 0;
        while (index < queue.size() && queue.get(index).getKey() >= p) {
            index++;
        }
        queue.add(index, newElement);
    }

    @Override
    public Pair extractMax() {
        if (queue.size() == 1) {
            return null;
        }
        Pair maxElement = queue.get(queue.size() - 1);
        queue.remove(queue.size() - 1);
        return maxElement;
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }
}