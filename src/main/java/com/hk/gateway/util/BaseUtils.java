package com.hk.gateway.util;

import java.io.File;
import java.io.IOException;

import com.hk.gateway.util.md5.MD5;

public class BaseUtils {
	public static String getMD5Checksum(File file) {
		String md5 = null;
		if (file == null || !file.exists()) {
			return md5;
		}
		try {
			md5 = MD5.asHex(MD5.getHash(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return md5;
	}

	public static String getRandomString(int length) {
		char[] pw = new char[length];
		int c = 'A';
		int r1 = 0;
		for (int i = 0; i < length; i++) {
			r1 = (int) (Math.random() * 3);
			switch (r1) {
			case 0:
				c = '0' + (int) (Math.random() * 10);
				break;
			case 1:
				c = 'a' + (int) (Math.random() * 26);
				break;
			case 2:
				c = 'A' + (int) (Math.random() * 26);
				break;
			}
			pw[i] = (char) c;
		}
		return new String(pw);
	}
}
