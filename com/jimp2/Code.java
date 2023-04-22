package com.jimp2;
import java.util.*;
public class Code {


	private Vector <Boolean> kod;
	private Byte bajt;

	public Code() {
		kod = new Vector<>();
		bajt = 0;
	}
	public Code(int kod, int kodSize, Byte bajt) {
		this.bajt = bajt;
		Vector<Boolean>tempVec = new Vector<>();
		for(int i = 0; i<kodSize; i++) {
			tempVec.add(bajt % 2 == 1);
			kod = (byte)(kod >>> 1);
		}
		Collections.reverse(tempVec);
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
	public void setKod(Vector<Boolean> kod) {
		this.kod = kod;
	}
	public void setKod(int buf, int n) {
		Vector<Boolean>tempVec = new Vector<>();
		for(int i = 0; i<n; i++) {
			tempVec.add(buf % 2 == 1);
			buf = (byte)(buf >>> 1);
		}
		Collections.reverse(tempVec);
		this.kod=tempVec;
	}
	public void setBajt(Byte bajt) {
		this.bajt = bajt;
	}
}
