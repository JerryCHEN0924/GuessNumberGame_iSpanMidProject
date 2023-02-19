package test;

import java.util.HashSet;
import java.util.Random;

public class CreateNewCards {

	public static int[] generateRandomArray() {
		
		Random rand = new Random();
		HashSet<Integer> set = new HashSet<>();
		
		while (set.size() < 5) {
            int num = rand.nextInt(10);
            set.add(num);
        }
		
        int[] array = new int[5];
        int i = 0;
        for (int n : set) {
            array[i++] = n;
        }
        return array;
    }
		
		
		
		
		
		

}
