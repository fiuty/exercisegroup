package leetcode;

import java.util.Arrays;

/**
 * 滑动窗口最大值
 * @author zhengdayue
 */
public class Main_239 {

    public static void main(String[] args) {
        int[] nums = {4, -2};
        System.out.println(Arrays.toString(maxSlidingWindow(nums, 2)));
    }

    //超时了，时间复杂度为k*n
    public static int[] maxSlidingWindow(int[] nums, int k) {
        TempNode head = new TempNode();
        TempNode tail = new TempNode();
        int [] back = new int[nums.length - k + 1];
        int firstNum = Integer.MIN_VALUE;
        for (int i = 0; i < k; i++) {
            if (nums[i] > firstNum) {
                firstNum = nums[i];
            }
            if (i == 0) {
                head.next = new TempNode(nums[i]);
                tail.next = head.next;
                continue;
            }
            tail.next.next = new TempNode(nums[i]);
            tail.next = tail.next.next;
        }
        int backIndex = 0;
        back[backIndex] = firstNum;
        int currentMax = firstNum;
        for (int i = k; i < nums.length; i++) {
            tail.next.next = new TempNode(nums[i]);
            tail.next = tail.next.next;
            if (head.next.value == currentMax) {
                currentMax = Integer.MIN_VALUE;
                head.next = head.next.next;
                TempNode currentNode = head.next;
                while (currentNode != null) {
                    if (currentMax < currentNode.value) {
                        currentMax = currentNode.value;
                    }
                    currentNode = currentNode.next;
                }
            } else {
                head.next = head.next.next;
                if (currentMax < tail.next.value) {
                    currentMax = tail.next.value;
                }
            }
            back[++backIndex] = currentMax;
        }
        return back;
    }
}

class TempNode{
    public TempNode next;
    public int value;

    TempNode(int value) {
        this.value = value;
    }

    TempNode() {

    }
}
