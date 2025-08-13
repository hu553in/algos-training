import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

// https://leetcode.com/problems/insert-delete-getrandom-o1/
class RandomizedSet {
    private Map<Integer, Integer> valIndex = new HashMap<>();
    private List<Integer> vals = new ArrayList<>();
    private Random r = new Random();

    public boolean insert(int val) {
        if (valIndex.putIfAbsent(val, vals.size()) != null) return false;
        vals.add(val);
        return true;
    }

    public boolean remove(int val) {
        var index = valIndex.get(val);
        if (index == null) return false;

        int last = vals.get(vals.size() - 1);
        vals.set(index, last);
        valIndex.put(last, index);

        vals.remove(vals.size() - 1);
        valIndex.remove(val);
        return true;
    }

    public int getRandom() {
        return vals.get(r.nextInt(vals.size()));
    }
}
