package wordtrek;

import java.util.HashMap;
import java.util.ArrayList;
import util.Dict;
import util.Table;

public class FindWord extends Thread {

	WordtrekNode wtn;
	HashMap<String, WordtrekNode> nodes;
	Dict dict;
	int rows, cols;
	int index;

	public FindWord(WordtrekNode wtn, int index, HashMap<String, WordtrekNode> nodes, Dict dict) {
		this.nodes = nodes;
		this.wtn = wtn;
		this.index = index;
		this.dict = dict;
		this.rows = wtn.table.getRows();
		this.cols = wtn.table.getCols();
	}
	
	public void run() {
		findWord(wtn, index);
	}

	public void findWord(WordtrekNode wtn, int index) {
		for (int r=0; r<wtn.table.getRows(); r++)
			for (int c=0; c<wtn.table.getCols(); c++)
				findWordAtRC(r,  c, wtn, "", wtn.wordLengths[index]);
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
