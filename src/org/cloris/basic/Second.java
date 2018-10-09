package org.cloris.basic;

/**
 * LeetCode �㷨��Ƶ�ڶ��鸴ϰ
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
	 * �� 9 �� - ��̬�滮����
	 */

	// 1. 쳲���������

	// ����һ����ͨ�ݹ�ʵ�� (�Զ�����) - O(2^n)
	public static int fib1(int n) {
		if (n == 0) {
			return 0;
		}
		if (n == 1) {
			return 1;
		}
		return fib1(n - 1) + fib1(n - 2);
	}

	// �����������仯���� - O(n)
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

	// ����������̬�滮 (�Ե�����) - O(n)
	// ��̬�滮����ԭ������ɶ�������⣬ͬʱ����������Ĵ� (�� memo[] ����)��ʹ��ÿ��������ֻ���һ�Σ����ջ��ԭ����Ĵ𰸡�
	public static int fib3(int n) {
		memo[0] = 0;
		memo[1] = 1;

		for (int i = 2; i <= n; i++) {
			memo[i] = memo[i - 1] + memo[i - 2];
		}

		return memo[n];
	}

	// 2. ��¥��
	// ������������¥�ݡ���Ҫ n ������ܵ���¥����
	// ÿ��������� 1 �� 2 ��̨�ס����ж����ֲ�ͬ�ķ�����������¥���أ�

	// 3. �������(343)
	// ����һ�������� n��������Ϊ���������������ĺͣ���ʹ��Щ�����ĳ˻���󻯡� ��������Ի�õ����˻���

	// ����һ���ݹ� - O(2^n)
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

	// �����������仯���� - O(n^2)
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

		// memo[n] �Ǵ�ȡ���εݹ�Ľ���ģ���Ӧ�÷��ڴ�����С�
		for (int i = 1; i < n; i++) {
			res = max3(res, i * (n - i), i * integerBreak1(n - i));
		}
		// ���汾�εݹ���õĽ��
		memo[n] = res;

		return memo[n];
	}

	// ����������̬�滮(�Ե�����) - O()
	public static int integerBreak3(int n) {
		memo = new int[n + 1];
		for (int i = 0; i < n + 1; i++) {
			memo[i] = -1;
		}

		memo[1] = 1;
		for (int i = 2; i <= n; i++) {
			// ��� memo[i]
			for (int j = 1; j <= i - 1; j++) {
				// j + (i - j)
				memo[i] = max3(memo[i], j * (i - j), j * memo[i - j]);
			}
		}

		return memo[n];
	}

	// 4. ��ҽ���
	// ����һ������ÿ�����ݴ�Ž��ķǸ��������飬�������ڲ���������װ�õ�����£��ܹ�͵�Ե�����߽�
	// ����������ڵķ�����ͬһ���ϱ�С͵���룬ϵͳ���Զ�������

	// ����һ���ݹ� - O(n * 2^n)
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

	// �������� ���仯���� - O(n^2)
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

	// ����������̬�滮
	public static int rob3(int[] nums) {
		int len = nums.length;
		memo = new int[len]; // nums �����䲻����nums.length
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
