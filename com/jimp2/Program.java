package com.jimp2;
import java.util.Vector;
public class Program {
	public static void main(String[] args) {
		InputFile in = null;
		OutputFile out = null;
		if(args.length>0)
			in = new InputFile(args[0]);
		else error(1);
		if(args.length>1)
			out = new OutputFile(args[1]);
		else {
			out = new OutputFile(args[0] + ".comp");
		}
		decompress(in, out);



	}
	static void decompress(InputFile in, OutputFile out) {
		char buf;
		int longBuf, tempLongBuf;
		int compressionLvl, endBitFilling, dictionarySize, codedWordLength;

		//flaga wejsciowa
		buf = in.get();
		if(buf!='+') error(2);
		buf = in.get();
		if(buf!=13) error(2);
		compressionLvl = in.get();
		compressionLvl >>>= 6;
		//if(compressionLvl!=2) error(3);
		//dlugosc slownika
		dictionarySize = in.get();
		dictionarySize <<=8;
		dictionarySize += in.get();
		//dlugosc litery w slowniku
		codedWordLength = in.get();
		codedWordLength <<=8;
		codedWordLength += in.get();

		CodeList dictionary = new CodeList();
		int mostSigBit=0, tempMostSigBit=0;
		buf=0;
		longBuf=0;
		System.out.println(dictionarySize);
		System.out.println(codedWordLength);
		Code tmpCode = new Code();
		for(int i = 0; i<dictionarySize; i++) {
			tmpCode = new Code();
			while(mostSigBit<8) {
				buf=in.get();
				//if(in.isEOF()) error(5);
				mostSigBit+=8;
				longBuf<<=8;
				longBuf+=buf;
			}
			tmpCode.setBajt((char) (longBuf>>>(mostSigBit-8)));
			longBuf &= (1<<(mostSigBit-8))-1;
			mostSigBit-=8;
			while(mostSigBit<codedWordLength) {
				buf = in.get();
				//if(in.isEOF())
				longBuf<<=8;
				longBuf+=buf;
				mostSigBit+=8;
			}
			tempLongBuf=longBuf>>>(mostSigBit-codedWordLength);
			tempMostSigBit=0;
			while(tempLongBuf%2==0) {
				tempLongBuf>>>=1;
				tempMostSigBit++;
			}
			tempLongBuf>>>=1;
			tempMostSigBit++;
			tmpCode.setKod(Code.intToVec(tempLongBuf, codedWordLength-tempMostSigBit));

			longBuf &= (1<<(mostSigBit-codedWordLength))-1;
			mostSigBit-=codedWordLength;

			dictionary.add(tmpCode);
		}
		System.out.println(dictionary.toString());

		Vector<Boolean> tempVec = new Vector<>();
		while(!in.isEOF(1)) {
			if(mostSigBit==0) {
				buf = in.get();
				mostSigBit+=8;
				longBuf<<=8;
				longBuf+=buf;
			}
			tempVec.add((longBuf>>>(mostSigBit-1))==1);
			longBuf &= ((1<<mostSigBit-1)-1);
			mostSigBit--;
			if(dictionary.seek(tempVec)!=null) {
				out.Write(dictionary.seek(tempVec).getBajt());
				tempVec=new Vector<>();
			}
		}

	}
	static void error(int code) {
		/*
		Error Code Sheet
		0 - unknown error
		1 - file error
		2 - file not compressed
		3 - cannot decompress this file
		4-
		5 - unexpected end of file
		*/
		String [] errorMsg = new String[10];
		errorMsg[0]="unknown error";
		errorMsg[1] = "file error";
		errorMsg[2] = "file not compressed";
		errorMsg[3] = "cannot decompress this file";
		errorMsg[5] = "unexpected end of file";
		System.out.println(errorMsg[code]);
		System.exit(code);
	}
}