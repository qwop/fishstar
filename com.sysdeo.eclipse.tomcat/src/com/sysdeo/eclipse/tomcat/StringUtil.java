package com.sysdeo.eclipse.tomcat;

/*
 * (c) Copyright Sysdeo SA 2001, 2002.
 * All Rights Reserved.
 */

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class StringUtil {

	/**
	 * Concat two arrays of Strings,
	 * part2 is appended to part1
	 */
	public static String[] concat(String[] part1, String[] part2) {
		String[] full = new String[part1.length + part2.length];
		System.arraycopy(part1, 0, full, 0, part1.length);
		System.arraycopy(part2, 0, full, part1.length, part2.length);
		return full;
	}

	/**
	 * Concat two arrays of Strings, and prevent that duplicate
	 * Strings are present in result
	 * part2 is appended to part1
	 */
	public static String[] concatUniq(String[] part1, String[] part2) {
		ArrayList nlist = new ArrayList();

		for (int i = 0; i < part1.length; i++) {
			if (! nlist.contains(part1[i]))
				nlist.add(part1[i]);
		}

		for (int i = 0; i < part2.length; i++) {
			if (! nlist.contains(part2[i]))
				nlist.add(part2[i]);
		}

		return (String[])nlist.toArray(new String[0]);
	}


	/**
	 * See StringTokenizer for delim parameter format
	 */
	public static String[] cutString(String str, String delim) {
		ArrayList strings = new ArrayList();
		StringTokenizer tokenizer = new StringTokenizer(str, delim);
		while (tokenizer.hasMoreTokens()) {
			strings.add( URLDecoder.decode( tokenizer.nextToken()) );
		}

		return (String[])strings.toArray(new String[0]);
	}

	/**
	 * 过滤字符串. 类似 String.replace("replaceMent", "content");
	 *
	 * @param value
	 *            .
	 * @param replaceMent
	 *            .
	 * @param content
	 *            .
	 * @return
	 */
	public static String replace(String value, String replaceMent,
			String content) {
		if (isEmpty(value)) {
			return "";
		}
		int idx = value.indexOf(replaceMent);
		if (idx >= 0) {
			return value.substring(0, idx)
					+ value.substring(idx + replaceMent.length());
		}
		return "";
	}


	/**
	 * 判断字符串是否为空.
	 *
	 * @param v
	 * @return
	 */
	public final static boolean isEmpty( final String v ) {
		return v == null || v.trim().length() == 0;
	}
}
