package mamans.maman03.src.q1;

import java.util.HashMap;

public class DupCount<E> {
    private HashMap<E, Integer> dcHashMap;


    public HashMap<E, Integer> getDcHashMap() {
        return this.dcHashMap;
    }

    public DupCount() {
        this.dcHashMap = new HashMap<>();
    }

    public void add(E element) {

        if (!dcHashMap.containsKey(element)) {
            dcHashMap.put(element, 0);
        }

        dcHashMap.put(element, dcHashMap.get(element) + 1);
    }

    public int remove(E element) {

        if (!dcHashMap.containsKey(element)) {
            return 0;
        }

        int updatedElemCount = dcHashMap.get(element) - 1;

        dcHashMap.put(element,updatedElemCount);

        if (updatedElemCount == 0) {
            dcHashMap.remove(element);
        }

        return updatedElemCount;
    }

    public boolean isEmpty() {
        return this.dcHashMap.isEmpty();
    }

    public E getMaxDup() {
        if (dcHashMap.isEmpty()) {
            return null;
        }

        int maxDupCount = 0;
        E maxDupElement = null;
        int elemValue;

        for (E elemKey: dcHashMap.keySet()) {
            elemValue = dcHashMap.get(elemKey);

            if (maxDupCount < elemValue) {
                maxDupCount = elemValue;
                maxDupElement = elemKey;
            }
        }

        return maxDupElement;

    }

}
