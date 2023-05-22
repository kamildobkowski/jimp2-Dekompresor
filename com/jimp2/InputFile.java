package com.jimp2;

import java.io.*;
public class InputFile {
	File inputFile;
	FileInputStream input;
	private final long size;
	private int bytesGone = 0;
	InputFile(String name) throws RuntimeException{
		try {
			inputFile = new File(name);
			input = new FileInputStream(inputFile);
			size = inputFile.length();
		} catch(IOException e) {
			throw new RuntimeException(e);
		}
	}

	char get() throws RuntimeException{
		try{
			bytesGone++;
			if(bytesGone==size) throw new IOException();
			return (char) input.read();
		}
		catch(IOException e) {
			throw new RuntimeException(e);
		}
	}
	String get(int n) {
		try{
			StringBuilder s = new StringBuilder();
			for(int i = 0; i<n; i++) {
				bytesGone++;
				if(bytesGone==size) throw new IOException();
				s.append(input.read());
			}
			return s.toString();
		}
		catch(IOException e) {
			throw new RuntimeException(e);
		}
	}

	boolean isEOF(int bytesToEnd) {
		return bytesGone==size-bytesToEnd;
	}
	boolean isEOF() {
		return this.bytesGone==this.size;
	}
	void skip(int n) {
		try {
			this.input.skip(n);
		}
		catch(IOException e) {
			throw new RuntimeException(e);
		}
	}

}
