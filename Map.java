package model;

public class Map {
	static public int[][] location = new int[601][501];
	public Map() {
		for(int i=0; i<=600; i++) {
			for(int j=0; j<=500; j++) {
				location[i][j]=0;
			}
		}
	}
}
