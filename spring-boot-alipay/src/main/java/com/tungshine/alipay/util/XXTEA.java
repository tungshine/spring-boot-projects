package com.tungshine.alipay.util;

public class XXTEA {
	/**
	 * Encrypt data with key.
	 * 
	 * @param data
	 * @param key
	 * @return
	 */
	public static byte[] encrypt(byte[] data, byte[] key) {
		if (data.length == 0) {
			return data;
		}
		return toByteArray(
				encrypt(toIntArray(data, false), toIntArray(key, false)), false);
	}

	/**
	 * Decrypt data with key.
	 * 
	 * @param data
	 * @param key
	 * @return
	 */
	public static byte[] decrypt(byte[] data, byte[] key) {
		if (data.length == 0) {
			return data;
		}
		return toByteArray(
				decrypt(toIntArray(data, false), toIntArray(key, false)), false);
	}

	/**
	 * Encrypt data with key.
	 * 
	 * @param v
	 * @param k
	 * @return
	 */
	public static int[] encrypt(int[] v, int[] k) {
		int n = v.length;

		int y;
		int p;
		int rounds = 6 + 52 / n;
		int sum = 0;
		int z = v[n - 1];
		int delta = 0x9E3779B9;
		do {
			sum += delta;
			int e = (sum >>> 2) & 3;
			for (p = 0; p < n - 1; p++) {
				y = v[p + 1];
				z = v[p] += (z >>> 5 ^ y << 2) + (y >>> 3 ^ z << 4) ^ (sum ^ y)
						+ (k[p & 3 ^ e] ^ z);
			}
			y = v[0];
			z = v[n - 1] += (z >>> 5 ^ y << 2) + (y >>> 3 ^ z << 4) ^ (sum ^ y)
					+ (k[p & 3 ^ e] ^ z);
		} while (--rounds > 0);

		return v;
	}

	/**
	 * Decrypt data with key.
	 * 
	 * @param v
	 * @param k
	 * @return
	 */
	public static int[] decrypt(int[] v, int[] k) {
		int n = v.length;
		int z = v[n - 1], y = v[0], delta = 0x9E3779B9, sum, e;
		int p;
		int rounds = 6 + 52 / n;
		sum = rounds * delta;
		y = v[0];
		do {
			e = (sum >>> 2) & 3;
			for (p = n - 1; p > 0; p--) {
				z = v[p - 1];
				y = v[p] -= (z >>> 5 ^ y << 2) + (y >>> 3 ^ z << 4) ^ (sum ^ y)
						+ (k[p & 3 ^ e] ^ z);
			}
			z = v[n - 1];
			y = v[0] -= (z >>> 5 ^ y << 2) + (y >>> 3 ^ z << 4) ^ (sum ^ y)
					+ (k[p & 3 ^ e] ^ z);
		} while ((sum -= delta) != 0);
		return v;
	}

	/**
	 * Convert byte array to int array.
	 * 
	 * @param data
	 * @param includeLength
	 * @return
	 */
	private static int[] toIntArray(byte[] data, boolean includeLength) {
		int n = (((data.length & 3) == 0) ? (data.length >>> 2)
				: ((data.length >>> 2) + 1));
		int[] result;

		if (includeLength) {
			result = new int[n + 1];
			result[n] = data.length;
		} else {
			result = new int[n];
		}
		n = data.length;
		for (int i = 0; i < n; i++) {
			result[i >>> 2] |= (0x000000ff & data[i]) << ((i & 3) << 3);
		}
		return result;
	}

	/**
	 * Convert int array to byte array.
	 * 
	 * @param data
	 * @param includeLength
	 * @return
	 */
	private static byte[] toByteArray(int[] data, boolean includeLength) {
		int n = data.length << 2;

		;
		if (includeLength) {
			int m = data[data.length - 1];

			if (m > n) {
				return null;
			} else {
				n = m;
			}
		}
		byte[] result = new byte[n];

		for (int i = 0; i < n; i++) {
			result[i] = (byte) ((data[i >>> 2] >>> ((i & 3) << 3)) & 0xff);
		}
		return result;
	}

}
