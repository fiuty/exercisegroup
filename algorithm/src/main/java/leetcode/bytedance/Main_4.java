package leetcode.bytedance;

/**
 * @author zhengdayue
 */
public class Main_4 {

    public static void main(String[] args) {
        int [] nums1 = {2};
        int [] nums2 = {};
        System.out.println(findMedianSortedArrays(nums1, nums2));
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int size = nums1.length+nums2.length;
        int [] all = new int [size];
        int n1 = 0;
        int n2 = 0;
        int index = 0;
        while(n1<nums1.length || n2<nums2.length){
            if(n1<nums1.length && n2<nums2.length){
                if(nums1[n1]<=nums2[n2]){
                    all[index++] = nums1[n1++];
                }else{
                    all[index++] = nums2[n2++];
                }
            }else if (n1 == nums1.length){
                all[index++] = nums2[n2++];
            }else{
                all[index++] = nums1[n1++];
            }
        }
        if(size%2 == 0){
            int mid = size/2;
            return ((double)all[mid-1]+(double)all[mid])/2;
        }else{
            return (double)all[size/2];
        }
    }
}
