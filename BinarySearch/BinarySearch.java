package BinarySearch;

public class BinarySearch {
    static boolean binarySearch(int[] arr, int target) {
        int l = 0;
        int r = arr.length - 1;
        int i;
        while (l <= r) {
            i = (r + l) / 2;
            if (arr[i] == target)
                return true;
            if (arr[i] < target)
                l = i + 1;
            else
                r = i - 1;
        }
        return false;
    }

    public static void main(String args[]) {
        int[] arr = { 1, 4, 5, 6, 12 };
        System.out.println(binarySearch(arr, 3));
        System.out.println(binarySearch(arr, 6));
    }
}
