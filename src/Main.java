import util.Dict;
import wordtrek.WordTrek;

public class Main {

	public static void main(String[] args) {
		
		String dictFile = "/usr/share/dict/words";
		Dict dict = new Dict(dictFile);
	
		//int[] wordLengths = {4, 6, 6}; 
		//String table = "wses ogau rlnr bcef";

		//int[] wordLengths = {3, 6, 7, 9};
		//String table = "mgeee oarpf resaf ntpwo ninec"; 

		int[] wordLengths = {5, 4, 9, 7};
		String table = "adlgb nedtn oiawi giuqa rbsat"; 
		
		//int[] wordLengths = {7, 4, 8, 3, 3};
		//String table = "brigp eibfe ogaba lcbec cotea"; 
		
		//int[] wordLengths = {4, 3, 4, 3, 4, 3, 4};
		//String table = "xflga oadtk vblbo oooob glbol"; 

		WordTrek wt = new WordTrek(table, wordLengths, dict);
		wt.solveSingleThread();
		wt.print();

		wt = new WordTrek(table, wordLengths, dict);
		wt.solveMultiThread();
		wt.print();
	}

}
