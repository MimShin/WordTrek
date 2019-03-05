import util.Dict;
import wordtrek.WordTrek;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Welcome to eClipse :-)");
		
		String dictFile = "/usr/share/dict/words";
		Dict dict = new Dict(dictFile);
	
		int[] wordLengths = {3, 6, 7, 9};
		String table = "mgeee oarpf resaf ntpwo ninec"; 

		//int[] wordLengths = {5, 4, 9, 7};
		//String table = "mgeee oarpf resaf ntpwo ninec"; 
		
		//int[] wordLengths = {7, 4, 8, 3, 3};
		//String table = "brigp eibfe ogaba lcbec cotea"; 

		WordTrek wt = new WordTrek(table, wordLengths, dict);
		
		wt.solve();
		wt.print();
	}

}
