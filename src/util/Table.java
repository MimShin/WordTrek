package util;

public class Table {
	char[][] table = null;
	int rows = 0, cols = 0;
	
	public Table(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		
		clear();
	}
	
	public Table(char[][] table) {
		rows = table.length;
		cols = table[0].length;

		this.table = clone(table);
	}

	public Table(String s) {
		char[] ca = s.toCharArray();
		rows = s.length() - s.replace(" ", "").length() + 1;
		cols = s.length() / rows;

		clear();

		for (int r=0; r<rows; r++) {
			for (int c=0; c<cols; c++) {
				table[r][c] = ca[r*(cols+1)+c]; //+1 is for row separator
			}
		}
	}
	
	public String toStr() {
		return toStr(table);
	}

	public static String toStr(char[][] table) {
		String s = "";
		for (int r=0; r<table.length; r++)
				s +=  String.copyValueOf(table[r]);
		return s;
	}
	
	public void clear() {
		table = new char[rows][cols];

		for (int r=0; r<rows; r++)
			for (int c=0; c<cols; c++) 
				table[r][c] = '.';
	}

	public char getChar(int r, int c) {
		if (r >= rows || c >= cols || table == null)
			return '.';
		return table[r][c];
	}

	public void setChar(int r, int c, char ch) {
		if (r >= 0 && c >= 0 && r < rows && c < cols && table != null)
			table[r][c] = ch;
	}
	
	public int getRows() {
		return rows;
	}
	
	public int getCols() {
		return cols;
	}
	
	public static void print(char[][] table) {
		int rows = table.length;
		int cols = table[0].length;

		System.out.println("--------");
		for (int i=0; i<rows; i++) {
			for (int j=0; j<cols; j++) {
				System.out.printf(" %c", table[i][j]);
			}
			System.out.println();
		}
		System.out.println("--------");
	}
	
	public static char[][] clone(char[][] srcTable) {
		int rows = srcTable.length;
		int cols = srcTable[0].length;

		char[][] t = new char[rows][cols];
		for (int i=0; i<rows; i++) {
			for (int j=0; j<cols; j++) {
				t[i][j] = srcTable[i][j];
			}
		}
		return t;
	}	
	
	public static void dropDown(char[][] table) {
		int rows = table.length;
		int cols = table[0].length;
		for (int c=0; c<cols; c++) 
			for (int i=rows-1; i>0; i--)
				if (table[i][c] == '.')
					for (int j=i-1; j>=0; j--)
						if (table[j][c] != '.') {
							table[i][c] = table[j][c];
							table[j][c] = '.';
							break;
						}	
	}
	
	public void print() {
		print(table);
	}
	
	public char[][] clone() {
		return clone(table);
	}
	
	public void dropDown() {
		dropDown(table);
	}
	
}