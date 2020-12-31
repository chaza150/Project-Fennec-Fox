package Helper;

import java.lang.reflect.Array;
import java.util.Set;

public class Helper {

    public static <T extends Object> T[] deepCopyArray(T[] array){
        T[] tArray = (T[])Array.newInstance(array.getClass().getComponentType(), array.length);
        for(int i = 0; i < array.length; i++){
            tArray[i] = array[i];
        }

        return tArray;
    }

    public static void printSet(Set<Integer> set){
        for(Integer i : set){
            System.out.println(i);
        }
    }
}
