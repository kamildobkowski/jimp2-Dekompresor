package com.jimp2;
import java.util.*;
import java.io.*;
public class OutputFile {
	File outputFile;
	FileOutputStream output;

	OutputFile(String name) {
		try {
			outputFile = new File(name);
			output = new FileOutputStream(outputFile);
		}
		catch (IOException e) {
			System.out.println("Output file error");
			throw new RuntimeException(e);
		}
	}

	void Write(char bajt) throws RuntimeException{
		try {
			output.write(bajt);
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
