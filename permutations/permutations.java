import java.util.Arrays;

public class permutations {
  public static void main(String[] args) {
	int[] test = {1,2};
	permute(test, 0, test.length - 1);
 }

  
  private static void permute(int[] array, int start, int end) {
  	if (start == end) {
  		System.out.println(Arrays.toString(array));
  	} else {
  		for (int i = start; i <= end; i++) {
  			swap(array, start, i);
  			permute(array, start + 1, end);
  			swap(array, start, i);
  		}
  	}
  }
  
  private static void swap(int[] array, int a, int b) {
 	int temp = array[a];
 	array[a] = array[b];
 	array[b] = temp;
  }
}

class permuteS {
	

}