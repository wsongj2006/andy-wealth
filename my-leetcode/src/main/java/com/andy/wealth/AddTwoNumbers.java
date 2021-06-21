package com.andy.wealth;

/**
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 * <p>
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * <p>
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[7,0,8]
 * 解释：342 + 465 = 807.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class AddTwoNumbers {


    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode sumNode = null;
        ListNode head = null;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int value1 = l1 != null ? l1.val : 0;
            int value2 = l2 != null ? l2.val : 0;
            int sum = value1 + value2 + carry;
            carry = sum / 10;
            sum = sum % 10;
            if (sumNode == null) {
                head = new ListNode(sum);
                sumNode = head;
            } else {
                ListNode newNode = new ListNode(sum);
                sumNode.next = newNode;
                sumNode = sumNode.next;
            }


            if (l1 != null) {
                l1 = l1.next;
            }

            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (carry > 0) {
            ListNode newNode = new ListNode(carry);
            sumNode.next = newNode;
        }

        return head;
    }



    public static void printListNode(ListNode listNode) {
        System.out.print("[");
        while (listNode != null) {
            System.out.print(listNode.val + " ");
            if (listNode != null) {
                listNode = listNode.next;
            }
        }

        System.out.print("]");
    }

    private static ListNode initialListNode(int[] input) {
        ListNode head = null;
        ListNode listNode = null;
        for(int value: input) {
            if (listNode == null) {
                head = new ListNode(value);
                listNode = head;
            }else {
                ListNode tempNode = new ListNode(value);
                listNode.next = tempNode;
                listNode = listNode.next;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        int[] input1 = {2, 4, 6};
        ListNode l1 = initialListNode(input1);
        printListNode((l1));

        int[] input2 = {3, 6, 9};
        ListNode l2 = initialListNode(input2);
        printListNode((l2));

        printListNode(addTwoNumbers(l1, l2));
    }


    public static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        public void setNext(ListNode next) {
            this.next = next;
        }

        public void setVal(int val) {
            this.val = val;
        }
    }
}


