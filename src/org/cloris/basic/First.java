package org.cloris.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class First {
	static int[] arr = new int[31];

	public static void main(String[] args) {
		// int[] nums = { 2, 4, 5, 6, 8, 19, 25, 39 };
		// System.out.println(binarySearch(nums, nums.length, 39));
		// int[] nums = { 1, 2 };
		// System.out.println(containsNearbyDuplicate(nums, 2));
		// String s = "()";
		// System.out.println(s.length());
		// System.out.println();

		// for (int i = 0; i < arr.length; i++) {
		// arr[i] = -1;
		// }
		//
		// System.out.println(fun(4));
		// System.out.println(counts);
		// System.out.println(climbStairs(3));
		// System.out.println(integerBreak(10));

		// int[] nums = { 10, 9, 2, 5, 3, 7, 101, 18 };
		// System.out.println(rob(nums));
		// System.out.println(lengthOfLIS(nums));
		// Map<Character, Integer> map = new HashMap<>();
		// map.put('s', 1);
		// map.put('s', 1);
		// System.out.println(map.get('s'));
		// System.out.println(map.size());
//		System.out.println();
		lengthOfLongestSubstring_1("abcabcbb");

	}

	public static int binarySearch(int[] nums, int length, int target) {
		int left = 0, right = length - 1; // 闭区间

		while (left <= right) {
			// 注意整型溢出
			// int mid = (left + right) / 2;
			int mid = left + (right - left) / 2;

			if (target == nums[mid]) {
				return mid;
			} else if (target < nums[mid]) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}

		return -1;
	}

	public static int minSubArrayLen(int[] nums, int target) {

		int length = nums.length;
		int left = 0, right = -1; // [left, right] 为滑动窗口
		int sum = 0;
		int minLen = length + 1;

		while (left < length) {
			if (sum < target && right < length - 1) {
				right++;
				sum += nums[right];
			} else {
				sum -= nums[left];
				left++;
			}

			if (sum >= target) {
				minLen = Math.min(minLen, right - left + 1);
			}

		}

		if (minLen == length + 1) {
			return 0;
		}

		return minLen;
	}

	public static int lengthOfLongestSubstring(String s) {
		int length = s.length();
		int left = 0, right = -1;
		int maxLen = 0;
		int[] freq = new int[256];

		while (left < length) {
			if (freq[s.charAt(right + 1)] == 0 && right < length - 1) {
				right++;
				freq[s.charAt(right)]++;
			} else {
				freq[s.charAt(left)]--;
				left++;
			}

			maxLen = Math.max(maxLen, right - left + 1);
		}

		return maxLen;
	}

	public static boolean containsNearbyDuplicate(int[] nums, int k) {
		/*
		 * for (int i = 0; i < nums.length; i++) { for (int j = i + 1; j < nums.length;
		 * j++) { if (nums[i] == nums[j] && j - i <= k) { return true; } } } return
		 * false;
		 */
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();

		for (int i = 0; i < nums.length; i++) {
			if (map.containsKey(nums[i])) {
				if (i - map.get(nums[i]) <= k) {
					return true;
				}
			}
			map.put(nums[i], i);
		}

		return false;

	}

	public boolean isValid(String s) {
		Stack<Character> stack = new Stack<Character>();

		for (char c : s.toCharArray()) {
			if (c == '(')
				stack.push(')');
			else if (c == '{')
				stack.push('}');
			else if (c == '[')
				stack.push(']');
			else if (stack.isEmpty() || stack.pop() != c)
				return false;
		}

		return stack.isEmpty();
	}

	// 层序遍历 -- 队列
	public List<List<Integer>> levelOrder(TreeNode root) {
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		List<List<Integer>> res = new LinkedList<List<Integer>>();

		if (root == null)
			return res;

		q.offer(root);
		while (!q.isEmpty()) {
			int level = q.size(); //
			List<Integer> subList = new LinkedList<Integer>();

			for (int i = 0; i < level; i++) {
				if (q.peek().left != null) {
					q.offer(q.peek().left);
				}

				if (q.peek().right != null) {
					q.offer(q.peek().right);
				}

				subList.add(q.poll().val);
			}

			res.add(subList);
		}
		return res;
	}

	/*
	 * 二叉树
	 */
	public boolean hasPathSum(TreeNode root, int sum) {
		if (root == null) {
			return false;
		}

		if (root.left == null && root.right == null) {
			return root.val == sum;
		}

		return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
	}

	public int sumOfLeftLeaves(TreeNode root) {
		if (root == null) {
			return 0;
		}

		int sum = 0;

		if (root.left != null) {
			TreeNode left = root.left;
			if (left.left == null && left.right == null) {
				sum += left.val;
			} else {
				sum += sumOfLeftLeaves(left);
			}
		}

		if (root.right != null) {
			TreeNode right = root.right;
			sum += sumOfLeftLeaves(right);
		}

		return sum;

	}

	public List<String> binaryTreePaths(TreeNode root) {
		List<String> res = new ArrayList<>();

		if (root == null) {
			return res;
		}

		helper(root, String.valueOf(root.val), res);
		return res;

	}

	private void helper(TreeNode root, String path, List<String> res) {
		if (root.left == null && root.right == null) { // 递归终止条件
			res.add(path);
			return;
		}
		if (root.left != null) {
			helper(root.left, path + "->" + String.valueOf(root.left.val), res);
		}
		if (root.right != null) {
			helper(root.right, path + "->" + String.valueOf(root.right.val), res);
		}
	}

	public List<List<Integer>> pathSum(TreeNode root, int sum) {
		List<List<Integer>> res = new ArrayList<>();
		List<Integer> list = new ArrayList<>();
		if (root == null) {
			return res;
		}

		if (root.left == null && root.right == null) {
			if (sum == root.val) {
				list.add(root.val);
				res.add(list);
				return res;
			}
		}

		if (root.left != null) {
			list.add(root.val);
			pathSum(root.left, sum - root.val);
		}
		if (root.right != null) {
			list.add(root.val);
			pathSum(root.right, sum - root.val);
		}

		res.add(list);
		return res;
	}

	static int counts = 0;

	public static int fun(int n) {
		counts++;
		if (n == 0) {
			return 0;
		}
		if (n == 1) {
			return 1;
		}
		// if (arr[n] == -1) {
		// arr[n] = fun(n - 1) + fun(n - 2);
		// }
		//
		// return arr[n];
		return fun(n - 1) + fun(n - 2);

	}

	static int[] memo = null;

	public static int climbStairs(int n) {
		memo = new int[n + 1];
		for (int i = 0; i <= n; i++) {
			memo[i] = -1;
		}
		return climbStairsHelper(n);
	}

	private static int climbStairsHelper(int n) {
		if (n == 0) {
			return 0;
		}
		if (n == 1) {
			return 1;
		}

		if (memo[n] == -1) {
			memo[n] = climbStairsHelper(n - 1) + climbStairsHelper(n - 2);
		}
		return memo[n];
	}

	public int minimumTotal(List<List<Integer>> triangle) {
		int sum = 0;
		for (int i = 0; i < triangle.size(); i++) {
			triangle.get(i).sort(null);
			System.out.println(triangle.get(i));
		}
		for (int i = 0; i < triangle.size(); i++) {
			sum += triangle.get(i).get(0);
		}
		return sum;

	}

	// 343. 整数拆分
	static int[] memo2 = null;

	public static int integerBreak(int n) {
		memo2 = new int[n + 1];
		for (int i = 0; i <= n; i++) {
			memo2[i] = -1;
		}
		return helper(n);

	}

	private static int helper(int n) {
		int res = -1;

		if (n == 1) {
			return 1;
		}

		if (memo2[n] != -1) {
			return memo2[n];
		}

		for (int i = 1; i <= n - 1; i++) {
			// 分割 i + (n-i)
			res = max3(res, i * (n - i), i * helper(n - i));
		}
		memo2[n] = res;

		return res;
	}

	private static int max3(int res, int i, int j) {
		return Math.max(res, Math.max(i, j));
	}

	// 198. 打家劫舍

	static int[] memo3 = null;

	public static int rob(int[] nums) {
		memo3 = new int[nums.length];
		for (int i = 0; i < nums.length; i++) {
			memo3[i] = -1;
		}

		return tryRob(nums, 0);

	}

	// 考虑抢劫 nums[index, nums.size()] 范围内的所有房子
	private static int tryRob(int[] nums, int index) {
		int res = 0;

		if (index >= nums.length) {
			return 0;
		}
		if (memo3[index] != -1) {
			return memo3[index];
		}

		for (int i = index; i < nums.length; i++) {
			res = Math.max(res, nums[i] + tryRob(nums, i + 2));
		}

		memo3[index] = res;
		return res;
	}

	public static int lengthOfLIS(int[] nums) {
		if (nums.length == 0) {
			return 0;
		}

		int res = 1;

		for (int i = 0; i < nums.length; i++) {
			int counts = 1;
			int temp = nums[i];

			for (int j = i + 1; j < nums.length; j++) {

				if (nums[j] > temp) {
					temp = nums[j];
					counts++;
				}
			}
			res = Math.max(res, counts);
			// System.out.println(res);
		}

		return res;
	}

	public char firstUniqChar(String str) {
		Map<Character, Integer> map = new HashMap<>();
		for (int i = 0; i < str.length(); i++) {
			int counts = 1;
			if (map.containsKey(str.charAt(i))) {
				counts++;
				map.put(str.charAt(i), counts);
			} else {
				map.put(str.charAt(i), counts);
			}

		}

		for (int i = 0; i < str.length(); i++) {
			if (map.get(str.charAt(i)) == 1) {
				return str.charAt(i);
			}
		}

		return ' ';

	}

	public int findContentChildren(int[] g, int[] s) {
		Arrays.sort(g);
		Arrays.sort(s);
		int i = 0, j = 0;
		while (i < g.length && j < s.length) {
			if (g[i] < s[j]) {
				i++;
			}
			j++;
		}
		return i;

	}

	// 长度最小的子数组
	public int minSubArrayLen(int s, int[] nums) {
		int len = nums.length;
		int left = 0, right = -1;
		int sum = 0;
		int minLen = nums.length + 1;

		while (left < len) {
			if (sum < s && right < len - 1) {
				right++;
				sum += nums[right];
			} else {
				sum -= nums[left];
				left++;
			}
			if (sum >= s) {
				minLen = Math.min(minLen, right - left + 1);
			}

		}

		if (minLen == nums.length + 1) {
			return -1;
		}

		return minLen;
	}

	// 3. 无重复字符的最长子串
	public static int lengthOfLongestSubstring_1(String s) {
		char[] sc = s.toCharArray();
		int l = 0, r = -1;
		int maxLen = 0;
		int[] freq = new int[256];

		while (l < s.length()) {
			if (freq[sc[++r]] == 0 && r < s.length() - 1) {
				freq[sc[r]]++;
			} else {
				freq[sc[l++]]--;
			}

			maxLen = Math.max(maxLen, r - l + 1);

		}

		return maxLen;
	}
}
