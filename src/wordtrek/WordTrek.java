package wordtrek;

import util.Table;
import util.Dict;
import java.util.HashMap;
import java.util.ArrayList;

public class WordTrek {
	HashMap<String, WordtrekNode> nodes;
	Dict dict;
	int nWords;
	int rows, cols;

	public WordTrek(String table, int[] wordLengths, Dict dict) {
		nodes = new HashMap<String, WordtrekNode>();
		this.dict = dict;
		nWords = wordLengths.length;

		WordtrekNode wtn = new WordtrekNode(table, wordLengths);
		nodes.put(wtn.getKey(), wtn);
		rows = wtn.table.getRows();
		cols = wtn.table.getCols();
		
	}
	
	public void solve() {
		long startTime = System.currentTimeMillis();
		if (nodes == null || nodes.isEmpty()) 
			return;

		ArrayList<WordtrekNode> worklist = new ArrayList<WordtrekNode>();
	
		for (int i=0; i<nWords; i++) {
			worklist.clear();
			for (WordtrekNode wtn : nodes.values()) {
				if (wtn.words.size() == i)
					worklist.add(wtn);
			}
		
			for (WordtrekNode wtn : worklist)
				findWord(wtn, i);
		}
		long finishTime = System.currentTimeMillis();
		System.out.printf("Elapsed time: %d milliseconds\n", finishTime - startTime);
	}

	public void print() {
		if (nodes.isEmpty()) {
			System.out.println("Nothing to print!");
			return;
		}

		for (WordtrekNode wtn : nodes.values()) {
			if (wtn.words.size() == nWords)
				wtn.printWords();
		}	
	}
	
	public void findWord(WordtrekNode wtn, int i) {
		for (int r=0; r<wtn.table.getRows(); r++)
			for (int c=0; c<wtn.table.getCols(); c++)
				findWordAtRC(r,  c, wtn, "", wtn.wordLengths[i]);
	}

	public void findWordAtRC(int r, int c, WordtrekNode wtn, String prefix, int len) {

		//System.out.printf("\"%s\" %d,%d %d\n", prefix, r, c, len);
		
		if (len == 0) {
			if (dict.contains(prefix)) {
				ArrayList<String> newWords = new ArrayList<String>(wtn.words);
				newWords.add(prefix);
				WordtrekNode newWtn = new WordtrekNode(wtn.table.clone(), wtn.wordLengths, newWords);
				nodes.putIfAbsent(newWtn.getKey(), newWtn);
			}
			return;
		}
		
		Table table = wtn.table;
		char ch = table.getChar(r, c);

		if (ch != '.') {
			table.setChar(r, c, '.');
			if (len == 1) {
				findWordAtRC(-1, -1, wtn, prefix + Character.toString(ch), 0);
			} else {
				for (int dr=-1; dr<=1; dr++) {

					int rr = r+dr;
					if (rr < 0 || rr > rows) continue; // out of bound

					for (int dc=-1; dc<=1; dc++) {

						int cc = c+dc;
						if (cc < 0 || cc > cols || table.getChar(rr, cc) == '.') continue; //out of bound or empty

						findWordAtRC(rr, cc, wtn, prefix + Character.toString(ch), len - 1);
					}
				}
			}
			table.setChar(r, c, ch);
		}	
	}
}

class WordtrekNode {
	Table table;
	ArrayList<String> words;
	int[] wordLengths;
	String key;
	
	public WordtrekNode(String table, int[] wordLengths) {
		this.table = new Table(table);
		this.table.dropDown();
		this.wordLengths = wordLengths;
		words = new ArrayList<String>();
		setKey();
	}

	public WordtrekNode(String table, int[] wordLengths, ArrayList<String> words) {
		this(table, wordLengths);
		this.words = words;
		setKey();
	}

	public WordtrekNode(char[][] table, int[] wordLengths, ArrayList<String> words) {
		this.table = new Table(table);
		this.table.dropDown();
		this.wordLengths = wordLengths;
		this.words = words;
		setKey();
	}
	
	public void print() {
		table.print();
		printWordLengths();
		printWords();
		System.out.println("Key: " + key);
	}

	public void printWordLengths() {
		System.out.print("WordLenghts: [");
		for (int wl: wordLengths)
			System.out.printf("%2d", wl);
		System.out.println(" ]\n--------");
	}
	
	public void printWords() {
		System.out.print("Words: [");
		for (String w: words)
			System.out.printf(" %s", w);
//		System.out.print(" key: " + key);
		System.out.println(" ]\n--------");
	}
	
	public String getKey() {
		return this.key;
	}
	
	void setKey() {
		key = table.toStr(); 
		for (String w: words) {
			key += w;
		}
	}
}