package com.jimp2;
import java.util.Collections;
import java.util.Vector;
public class Code {
	private Vector <Boolean> kod;
	private char bajt;

	Code() {
		kod = new Vector<>();
		bajt=0;
	}
	Code(int kod, int n, char bajt) {
		this.kod = new Vector<>();
		for(int i = 0; i<n; i++) {
			if(kod % 2==1) this.kod.add(true);
			else this.kod.add(false);
		}
		Collections.reverse(this.kod);
		this.bajt = bajt;
	}
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		//s.append(this.kod.toString());
		s.append("[ ");
		for(Boolean b : this.kod) {
			if(b) s.append('1');
			else s.append('0');
			s.append(' ');
		}
		s.append("] ");
		/*for(int i = 7; i>=0; i--) {
			s.append(this.bajt & (1<<i));
		}*/
		s.append((int)this.bajt);
		return s.toString();
	}
	@Override
	public boolean equals(Object o) {
		if(o.getClass() != this.getClass()) return false;
		if(((Code) o).getBajt() != this.getBajt()) return false;
		if(!((Code) o).getKod().equals(this.getKod())) return false;
		return true;
	}

	public Vector<Boolean> getKod() {
		return kod;
	}
	public char getBajt() {
		return bajt;
	}
	public void setKod(Vector<Boolean> kod) {
		this.kod = kod;
	}
	public void setBajt(char bajt) {
		this.bajt = bajt;
	}
	public static Vector<Boolean> intToVec(int s, int n) {
		Vector<Boolean> v = new Vector<>();
		for(int i = 0; i<n; i++) {
			v.add(s%2!=0);
			s>>>=1;
		}
		Collections.reverse(v);
		return v;
	}
}
