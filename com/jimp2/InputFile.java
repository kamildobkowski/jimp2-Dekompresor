package com.jimp2;
import java.io.*;
import java.util.*;
public class InputFile{
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

	byte get() throws RuntimeException{
		try{
			bytesGone++;
			if(bytesGone==size) throw new IOException();
			return (byte) input.read();
		}
		catch(IOException e) {
			throw new RuntimeException(e);
		}
	}

	boolean isEOF(int bytesToEnd) {
		return bytesGone==size-bytesToEnd;
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
