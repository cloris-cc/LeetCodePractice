package org.cloris.basic;

/**
 * LeetCode 算法视频第二遍复习
 * 
 * @author cloris
 *
 */
public class Second {
	public static void main(String[] args) {

		int[] nums = { 2, 7, 9, 3, 1, 19, 4, 14, 533, 2222, 13, 3 };
		System.out.println(rob1(nums));
		System.out.println(rob2(nums));
		System.out.println(rob3(nums));
	}

	/*
	 * 第 9 章 - 动态规划基础
	 */

	// 1. 斐波纳契数列

	// 方法一：普通递归实现 (自顶向下) - O(2^n)
	public static int fib1(int n) {
		if (n == 0) {
			return 0;
		}
		if (n == 1) {
			return 1;
		}
		return fib1(n - 1) + fib1(n - 2);
	}

	// 方法二：记忆化搜索 - O(n)
	static int[] memo = null;

	public static int fib2(int n) {
		memo = new int[n + 1];
		for (int i = 0; i < n + 1; i++) {
			memo[i] = -1;
		}
		return helper(n);
	}

	public static int helper(int n) {
		if (n == 0) {
			return 0;
		}
		if (n == 1) {
			return 1;
		}

		// if (memo[n] == -1) {
		// memo[n] = helper(n - 1) + helper(n - 2);
		// }
		// return memo[n];

		return memo[n] == -1 ? memo[n] = helper(n - 1) + helper(n - 2) : memo[n];
	}

	// 方法三：动态规划 (自底向上) - O(n)
	// 动态规划：将原问题拆解成多个子问题，同时保存子问题的答案 (用 memo[] 保存)，使得每个子问题只求解一次，最终获得原问题的答案。
	public static int fib3(int n) {
		memo[0] = 0;
		memo[1] = 1;

		for (int i = 2; i <= n; i++) {
			memo[i] = memo[i - 1] + memo[i - 2];
		}

		return memo[n];
	}

	// 2. 爬楼梯
	// 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
	// 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？

	// 3. 整数拆分(343)
	// 给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。

	// 方法一：递归 - O(2^n)
	public static int integerBreak1(int n) {
		if (n == 1) {
			return 1;
		}

		int res = -1;

		for (int i = 1; i < n; i++) {
			res = max3(res, i * (n - i), i * integerBreak1(n - i));
		}

		return res;
	}

	private static int max3(int a, int b, int c) {
		return Math.max(a, Math.max(b, c));
	}

	// 方法二：记忆化搜索 - O(n^2)
	public static int integerBreak2(int n) {
		memo = new int[n + 1];
		for (int i = 0; i < n + 1; i++) {
			memo[i] = -1;
		}

		return helper2(n);
	}

	private static int helper2(int n) {
		if (n == 1) {
			return 1;
		}
		int res = -1;

		if (memo[n] != -1) {
			return memo[n];
		}

		// memo[n] 是存取本次递归的结果的，不应该放在代码块中。
		for (int i = 1; i < n; i++) {
			res = max3(res, i * (n - i), i * integerBreak1(n - i));
		}
		// 保存本次递归调用的结果
		memo[n] = res;

		return memo[n];
	}

	// 方法三：动态规划(自底向上) - O()
	public static int integerBreak3(int n) {
		memo = new int[n + 1];
		for (int i = 0; i < n + 1; i++) {
			memo[i] = -1;
		}

		memo[1] = 1;
		for (int i = 2; i <= n; i++) {
			// 求解 memo[i]
			for (int j = 1; j <= i - 1; j++) {
				// j + (i - j)
				memo[i] = max3(memo[i], j * (i - j), j * memo[i - j]);
			}
		}

		return memo[n];
	}

	// 4. 打家劫舍
	// 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
	// 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。

	// 方法一：递归 - O(n * 2^n)
	public static int rob1(int[] nums) {
		return robHelper1(nums, 0);
	}

	private static int robHelper1(int[] nums, int index) {
		if (nums.length <= index) {
			return 0;
		}

		int res = -1;
		for (int i = index; i < nums.length; i++) {
			res = Math.max(res, nums[i] + robHelper1(nums, i + 2));
		}
		return res;
	}

	// 方法二： 记忆化搜索 - O(n^2)
	public static int rob2(int[] nums) {
		memo = new int[nums.length + 1];
		for (int i = 0; i < nums.length + 1; i++) {
			memo[i] = -1;
		}
		return robHelper2(nums, 0);
	}

	private static int robHelper2(int[] nums, int index) {
		if (nums.length <= index) {
			return 0;
		}
		if (memo[index] != -1) {
			return memo[index];
		}

		int res = -1;
		for (int i = index; i < nums.length; i++) {
			res = Math.max(res, nums[i] + robHelper2(nums, i + 2));
		}
		memo[index] = res;

		return memo[index];
	}

	// 方法三：动态规划
	public static int rob3(int[] nums) {
		int len = nums.length;
		memo = new int[len]; // nums 的区间不包括nums.length
		for (int i = 0; i < len; i++) {
			memo[i] = -1;
		}
		
		memo[len - 1] = nums[len - 1];

		for (int i = len - 2; i >= 0; i--) {
			for (int j = i; j < len; j++) {
				memo[i] = Math.max(memo[i], nums[j] + (j + 2 < len ? memo[j + 2] : 0));
			}
		}

		return memo[0];
	}

}
