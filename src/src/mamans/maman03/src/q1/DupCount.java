/**
 * <h3> DupCount </h3>
 *
 * <p>
 *     this is a generic class that save collections of elements in a compact way,
 *     so n appearances of the same element will be represented by one copy of this
 *     element, plus its n number of duplications. under the hood, it is technically implemented
 *     by a hashmap, where key = element, value = number-of-duplications (count)
 * </p>
 * <br>
 * <p> Note: HTML tags helps to improve comments readability in editors like IntelliJ that support it </p>
 *
 * @maman   03
 * @question    1
 * @author  Omer Shraibshtein (205984271)
 * @email   omershreib@gmail.com
 * @since   2026-06-02
 * */

package mamans.maman03.src.q1;
import java.util.HashMap;

public class DupCount<E> {
    private final HashMap<E, Integer> dcHashMap;

    /* dcHashMap getter */
    public HashMap<E, Integer> getDcHashMap() {
        return this.dcHashMap;
    }


    /* DupCount empty constructor (initiates an empty dcHashMap) */
    public DupCount() {
        this.dcHashMap = new HashMap<>();
    }


    /** Add element into DupCount
     * <p>
     *  logic explained:
     *  <br>
     *  - if the element does not exist, then add it to dcHashMap with countValue = 0
     *  <br>
     *  - anyway, increase element countValue by one
     * <br><br>
     * (honestly, I used this logic A LOT because it allows treat new element increment as existing one)
     * <p/>
     *
     * @param element some element
     * */
    public void add(E element) {

        if (!dcHashMap.containsKey(element)) {
            dcHashMap.put(element, 0);
        }

        dcHashMap.put(element, dcHashMap.get(element) + 1);
    }

    /** Remove element from DupCount
     * <br><br>
     * after the removal, if this element valueCount equals to zero, then remove this element from dcHashMap.
     *
     * @param element some element
     * @return the updated valueCount of element (after this removal). if the element does not exist in the first
     * place, then return 0.
     * */
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

    /** check if DupCount is empty
     *
     * A helpful wrapper method that check if DupCount is empty or not. (cannot do this check directly on
     * dcHashMap since it is private)
     *
     * @return true if dcHashMap is empty, otherwise false
     * */
    public boolean isEmpty() {
        return this.dcHashMap.isEmpty();
    }


    /** Get max duplicate element
     *
     * return the most popular element exists in DupCount. in the case of several elements with the same
     * max valueCount, return the first element this method found. if DupCount is empty then return null.
     *
     * @return most popular element in DupCount
     * */
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
