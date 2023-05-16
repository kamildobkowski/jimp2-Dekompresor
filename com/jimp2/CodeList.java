package com.jimp2;
import java.util.*;
public class CodeList {
	private Map<Vector<Boolean>, Code> mapa;

	//used to create a Tree object and to print all the values
	private List<Code> lista;

	private int length = 0;
	public int getLength() {
		return length;
	}
	CodeList() {
		mapa = new HashMap<>();
		lista = new LinkedList<>();
	}
	public void add(Code c) {
		this.mapa.put(c.getKod(), c);
		this.lista.add(c);
		this.length++;
	}
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("Key\tValue\n");
		for(Code c : this.lista) {
			s.append(c.toString());
			s.append("\n");
		}
		return s.toString();
	}
	public Code seek(Vector <Boolean> v) {
		return this.mapa.get(v);
	}
	public List<Code> getLista() {
		return this.lista;
	}
}
