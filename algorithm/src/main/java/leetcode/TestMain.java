package leetcode;


import datastructure.linkedlist.ListNode;

/**
 * @author zhengdayue
 * @date: 2021-08-26
 */
import java.util.*;
public class TestMain {

    public static void main(String[] args) {
        //System.out.println(reverseLeftWords("abcdefg",2));
        //System.out.println(reverseLeftWords("lrloseumgh", 6));

        //int []nums = {3,3};
        //int target = 6;
        //int[] ints = twoSum(nums, target);
        //System.out.println(ints[0]+":"+ ints[1]);

//        TreeNode treeNode = new TreeNode(4);
//        TreeNode treeNode1 = new TreeNode(2);
//        TreeNode treeNode2 = new TreeNode(7);
//        TreeNode treeNode3 = new TreeNode(1);
//        TreeNode treeNode4 = new TreeNode(3);
//        TreeNode treeNode5 = new TreeNode(6);
//        TreeNode treeNode6 = new TreeNode(9);
//        treeNode1.left = treeNode3;
//        treeNode1.right = treeNode4;
//        treeNode2.left = treeNode5;
//        treeNode2.right = treeNode6;
//        treeNode.left = treeNode1;
//        treeNode.right = treeNode2;
//        TreeNode result = mirrorTree(treeNode);

//        ListNode treeNode = new ListNode(2);
//        ListNode treeNode1 = new ListNode(4);
//        ListNode treeNode2 = new ListNode(3);
//        treeNode1.next = treeNode2;
//        treeNode.next = treeNode1;
//
//        ListNode treeNode3 = new ListNode(5);
//        ListNode treeNode4 = new ListNode(6);
//        ListNode treeNode5 = new ListNode(4);
//        treeNode4.next = treeNode5;
//        treeNode3.next = treeNode4;
//
//        ListNode listNode = addTwoNumbers(treeNode, treeNode3);

        //System.out.println(lengthOfLongestSubstring(" "));

//        int [] nums1 = {2};
//        int [] nums2 = {};
//        System.out.println(findMedianSortedArrays(nums1, nums2));

        //System.out.println(isAnagram("rat","car"));


//        ListNode treeNode1 = new ListNode(1);
//        ListNode treeNode2 = new ListNode(2);
//        ListNode treeNode3 = new ListNode(3);
//        ListNode treeNode4 = new ListNode(4);
//        ListNode treeNode5 = new ListNode(5);
//        treeNode1.next = treeNode2;
//        treeNode2.next = treeNode3;
//        treeNode3.next = treeNode4;
//        treeNode4.next = treeNode5;
        Scanner scanner = new Scanner(System.in);
        String next = scanner.next();
        int[] array = new int[128];
        for (int i = 0; i < next.length(); i++) {
            char c = next.charAt(i);
            if (c > 127) {
                continue;
            }
            array[c] = 1;
        }
        int num = 0;
        for (int i = 0; i < array.length; i++) {
            num += array[i];
        }
        System.out.println(num);
    }

    //	#25 K 个一组翻转链表

    //  #24 两两交换链表中的节点

    //	#206 反转链表
    public static ListNode reverseList(ListNode head) {
        ListNode reverseHead = new ListNode();
        ListNode current = head;
        while (current != null) {
            ListNode next = current.next;
            current.next = reverseHead.next;
            reverseHead.next = current;
            current = next;
        }
        return reverseHead.next;
    }

    //	#242 有效的字母异位词
    public static boolean isAnagram(String s, String t) {
        String letters = "qwertyuiopasdfghjklzxcvbnm";
        int[] nums = new int[letters.length()];
        for (int i = 0; i < s.length(); i++) {
            String temp = s.charAt(i) + "";
            int num = letters.indexOf(temp);
            ++nums[num];
        }
        for (int i = 0; i < t.length(); i++) {
            String temp = t.charAt(i) + "";
            int num = letters.indexOf(temp);
            --nums[num];
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                return false;
            }
        }
        return true;
    }

    //	#4 寻找两个正序数组的中位数(注意复用好计算的变量如size，length，避免多余计算。)
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int i = 0;
        int j = 0;
        int [] allnums = new int[nums1.length + nums2.length];
        int index = 0;
        while (i <= nums1.length - 1 || j <= nums2.length - 1) {
            //i,j同时存在
            if (i <= nums1.length - 1 && j <= nums2.length - 1) {
                int num1 = nums1[i];
                int num2 = nums2[j];
                if (num1 < num2) {
                    allnums[index++] = num1;
                    i++;
                } else {
                    allnums[index++] = num2;
                    j++;
                }
                //i存在，j不存在
            } else if (i <= nums1.length - 1 && j > nums2.length - 1) {
                allnums[index++] = nums1[i++];
            }else {
                //j存在，i不存在
                allnums[index++] = nums2[j++];
            }
        }
        for (int pr = 0; pr < allnums.length; pr++) {
            System.out.print(allnums[pr]);
        }
        System.out.println("-------");
        if (allnums.length % 2 != 0) {
            return allnums[(allnums.length - 1) / 2];
        } else {
            return (allnums[allnums.length / 2] + allnums[(allnums.length / 2) - 1]) / 2.0;
        }
    }


    //  #3 无重复字符的最长子串(方式不太行，不简洁)
    public static int lengthOfLongestSubstring(String s) {
        String temp = "";
        int maxSize = 0;
        if (s.length() == 1) {
            maxSize = 1;
        }
        for (int i = 0; i < s.length()-1; i++) {
            temp = s.charAt(i) + "";
            for (int j = i + 1; j < s.length(); j++) {
                if (contains(temp, s.charAt(j))) {
                    if (temp.length() > maxSize) {
                        maxSize = temp.length();
                    }
                    break;
                } else {
                    temp = temp + s.charAt(j);
                    if (temp.length() > maxSize) {
                        maxSize = temp.length();
                    }
                }
            }
        }
        return maxSize;
    }

    public static boolean contains(String s, char target) {
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == target) {
                return true;
            }
        }
        return false;
    }

    //	#2 两数相加(看题不仔细)
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode temp1 = l1;
        ListNode temp2 = l2;
        int tempValue = 0;
        StringBuilder stringBuilder = new StringBuilder("");
        while (temp1 != null || temp2 != null) {
            int num1 = 0;
            if (temp1 != null) {
                num1 = temp1.val;
                temp1 = temp1.next;
            }
            int num2 = 0;
            if (temp2 != null) {
                num2 = temp2.val;
                temp2 = temp2.next;
            }
            int value = num1 + num2 + tempValue;
            if (value >= 10) {
                tempValue = 1;
                value = value - 10;
            } else {
                tempValue = 0;
            }
            stringBuilder.append(value);
        }
        if (tempValue != 0) {
            stringBuilder.append(tempValue);
        }
        String reversValue = stringBuilder.toString();
        ListNode first = new ListNode();
        ListNode temp = first;
        for (int i = 0; i < reversValue.length(); i++) {
            String num = reversValue.charAt(i) + "";
            temp.next = new ListNode(Integer.parseInt(num));
            temp = temp.next;
        }
        return first.next;
    }

    //	#剑指 Offer 27 二叉树的镜像
    public static TreeNode mirrorTree(TreeNode root) {
        if (root.left == null || root.right == null) {
            return root;
        }
        TreeNode left = root.left;
        TreeNode right = root.right;
        root.left = right;
        root.right = left;
        mirrorTree(left);
        mirrorTree(right);
        return root;
    }

    //  #1 两数之和
    public static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            int num2 = target - nums[i];
            for (int j = 0; j < nums.length; j++) {
                if (num2 == nums[j] && i != j) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    //#剑指 Offer 58 - II 左旋转字符串
    public static String reverseLeftWords(String s, int n) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = n; i < s.length(); i++) {
            stringBuilder.append(s.charAt(i));
        }
        for (int i = 0; i < n; i++) {
            stringBuilder.append(s.charAt(i));
        }
        return stringBuilder.toString();
    }
}
