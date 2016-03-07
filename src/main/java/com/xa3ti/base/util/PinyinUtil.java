package com.xa3ti.base.util;

import net.sourceforge.pinyin4j.PinyinHelper;

public class PinyinUtil {
	public static String toPinyin(String chinese){
		String pinyin = "";
		for(int i = 0 ; i<chinese.length();i++){
			String[] s = PinyinHelper.toHanyuPinyinStringArray(chinese.charAt(i));
			if(s!=null&&s.length!=0){
				pinyin +=s[0].substring(0,s[0].length()-1); 
			}else{
				pinyin += chinese.charAt(i);
			}
		}
		return pinyin;
	}
}
