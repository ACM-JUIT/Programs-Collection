import java.util.Arrays;

public class twoNumberSum {
    
    public static void main(String[] args) {
        
        int[] array = new int[] {3, 5, -4, 8, 11, 1, -1, 6};
        int targetSum = 10;
        int[] sum = twoNumSum(array, targetSum);
        System.out.println(sum);
    }

    public static int[] twoNumSum(int[] numArray, int target) {

        Arrays.sort(numArray);
        int left = 0;
        int right = numArray.length - 1;
        while (left < right) {
            int currentSum = numArray[left] + numArray[right];
            if (currentSum == target) {
                return new int[] {numArray[left], numArray[right]};
            } else if (currentSum < target) {
                left++;
            } else if (currentSum > target) {
                right--;
            }
        }
        return new int[0];
    }
}
