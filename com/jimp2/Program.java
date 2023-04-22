package com.jimp2;

import java.io.*;
import java.util.*;

import static java.lang.System.exit;
import static java.lang.System.out;
public class Program {
	public static void main(String [] args) {
		InputFile inFile = null;
		OutputFile outFile = null;
		switch(args.length){
			case 0:
				error(0);
				break;
			case 1:
				inFile = new InputFile(args[0]);
				outFile = new OutputFile(args[0] + ".comp");
				break;
			case 2:
				inFile = new InputFile(args[0]);
				outFile = new OutputFile(args[1]);
				break;
			default:
				error(1);
		}
		if(inFile == null || outFile == null) {
			return;
		}
		try{
			decompress(inFile, outFile);
		}
		catch(RuntimeException ex) {

		}
	}
	static void error(int errorCode) {
		switch(errorCode) {
			case 0:
				System.out.println("Nie podano nazwy pliku do dekompresji");
				exit(0);
				break;
			case 1:
				System.out.println("Podano zbyt wiele argumentów!");
				exit(1);
				break;
			default:
				System.out.println("Wystąpił błąd!");
				exit(-1);
		}
	}
	static void decompress(InputFile in, OutputFile out) throws RuntimeException{
		byte temp = 0;
		int buf = 0, bufCounter = 0, tmpBuf = 0, tmpBufCounter = 0;
		int dictionarySize = 0, codedWordLength = 0;
		CodeList dictionary= new CodeList();
		in.skip(3);
		temp=in.get();
		dictionarySize+=temp;
		dictionarySize<<=8;
		temp=in.get();
		dictionarySize+=temp;
		temp=in.get();
		codedWordLength+=temp;
		codedWordLength<<=8;

		for(int i = 0; i<dictionarySize; i++) {
			Code newCode = new Code();
			while(bufCounter<8) {
				temp=in.get();
				buf<<=8;
				buf+=temp;
				bufCounter+=8;
			}
			newCode.setBajt((byte)(buf>>> bufCounter-8));
			bufCounter-=8;
			while(bufCounter<codedWordLength) {
				temp=in.get();
				buf<<=8;
				buf+=temp;
				bufCounter+=8;
			}
			tmpBuf=buf>>>bufCounter-codedWordLength;
			tmpBufCounter=bufCounter-codedWordLength;
			while(tmpBuf%2!=1 && tmpBufCounter!=0) {
				tmpBuf>>>=1;
				tmpBufCounter--;
			}
			tmpBuf>>>=1;
			tmpBufCounter--;
			newCode.setKod(tmpBuf, tmpBufCounter);
			dictionary.add(newCode);
			buf>>>=codedWordLength;
			bufCounter-=codedWordLength;
		}
		Code tempCode;
		while(!in.isEOF(2) || bufCounter!=0) {
			if(bufCounter==0 && !in.isEOF(2)) {
				temp = in.get();
				buf<<=8;
				buf+=temp;
			}
			tempCode=dictionary.seekKod(new Code(buf, bufCounter, (byte)0));
			if(tempCode!=null) out.Write(tempCode.getBajt());
		}
	}
}
