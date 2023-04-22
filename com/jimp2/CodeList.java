package com.jimp2;
import java.util.*;
public class CodeList {
	private Vector <Code> list;
	CodeList() {
		list = new Vector<Code>();
	}
	void add(Code c) {
		list.add(c);
	}
	Code seekKod(Code c) {
		for(Code i : list) {
			if(i.equals(c)) {
				return i;
			}
		}
		return null;
	}
}
