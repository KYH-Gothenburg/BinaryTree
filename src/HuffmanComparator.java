import java.util.Comparator;

public class HuffmanComparator implements Comparator<HuffmanNode> {
    @Override
    public int compare(HuffmanNode hn1, HuffmanNode hn2) {
        return hn1.getFrequency() - hn2.getFrequency();
    }
}
