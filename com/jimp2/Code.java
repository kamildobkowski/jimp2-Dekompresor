package com.jimp2;
import java.util.*;
public class Code {

	private final Vector <Boolean> kod;
	private final Byte bajt;

	public Code() {
		kod = new Vector<>();
		bajt = 0;
	}
	public Code(Byte kod, Byte bajt) {
		this.bajt = bajt;
		Vector<Boolean>tempVec = new Vector<>();
		for(int i = 0; i<8; i++) {
			tempVec.add(bajt % 2 == 1);
			kod = (byte)(kod >>> 1);
		}
		this.kod=tempVec;
	}
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		for(Boolean kod : this.kod) {
			s.append(kod);
		}
		return s.toString();
	}
	@Override
	public boolean equals(Object a) {
		if(this.getClass() != a.getClass()) return false;
		return this.kod.equals(((Code) a).kod);
	}
	public Vector<Boolean> getKod() {
		return kod;
	}
	public Byte getBajt() {
		return bajt;
	}

}
