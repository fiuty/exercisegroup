package nowcoder;

/**
 * NC88 寻找第K大
 * 采用快速排序排序好再返回第K大数
 * @author zhengdayue
 */
public class NC_88 {

    public static void main(String[] args) {
        int[] a = {10,10,9,9,8,7,5,6,4,3,4,2};
        System.out.println(findKth(a,12,3));
    }

    public static int findKth(int[] a, int n, int K) {
        quick(a, 0, n - 1);
        return a[n-K];
    }

    public static void quick(int[] a, int left, int right) {
        if (left < right) {
            //每一次排好一个元素的最终位置
            int mid = quickSort(a, left, right);
            quick(a, left, mid-1);
            quick(a, mid + 1, right);
        }
    }

    public static int quickSort(int[] a, int left, int right) {
        int mid = a[left];
        while (left < right) {
            //从右往左不断往后退，最后交换
            while (left < right && a[right] >= mid) {
                --right;
            }
            a[left] = a[right];
            a[right] = mid;
            //从左往右不断往前进，最后交换
            while (left < right && a[left] < mid) {
                ++left;
            }
            a[right] = a[left];
            a[left] = mid;
        }
        return left;
    }
}
