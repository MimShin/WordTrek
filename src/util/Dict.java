package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.HashSet;

public class Dict {
	
	String filepath;
	HashSet<String> words;
	
	public Dict(String filePath) {
		this.filepath = filePath;
		File f = new File(filePath);
		words = new HashSet<String>();

		try {
			Scanner s = new Scanner(f);
			while (s.hasNextLine()) {
				words.add(s.nextLine().toLowerCase());
			}
			s.close();
			System.out.println(words.size());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
		}
		
	}
	
	public void print() {
		for (String w : words) {
			System.out.println(w);
		}
	}
	
	public boolean contains(String word) {
		return words.contains(word);
	}
	
}
