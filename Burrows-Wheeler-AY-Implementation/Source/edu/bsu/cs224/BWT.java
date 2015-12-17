package edu.bsu.cs224;

import java.util.*;

public class BWT {

	private String[] transformTable;
	private String input, output="";
	private int sideLength, rowOfOriginal;
	
	public BWT(String data){
		this.input = data;
		this.sideLength = data.length();
		this.transformTable = new String[sideLength];
		fillTable();
	}
	
	private void fillTable(){
		String string = this.input;
		for(int row=0; row<sideLength; row++){
			this.transformTable[row] = "";
			for(int column=0; column<sideLength; column++){
				this.transformTable[row] += (string.charAt(column));
			}
			string = shiftString(string);
		}
	}
	
	public void printTransform(){
		System.out.print("Original Unsorted Table:");
		printTable(this.transformTable);
		findOutput();
		System.out.print("Original Sorted Table:");
		printTable(this.transformTable);
	}
	
	private void findOutput(){
		Arrays.sort(this.transformTable);
		this.rowOfOriginal = findRowOfOriginal();
		for(int row=0; row<sideLength; row++){
			this.output += getLastStrChar(this.transformTable[row]);
		}
	}
	
	public String getTransform(){
		return this.output;
	}
	
	private void printTable(String[] table){
		printLineAcross();
		for(int row=0; row<sideLength; row++){
			for(int column=0; column<sideLength; column++){
				System.out.print(" "+table[row].charAt(column) + " |");
			}
			printLineAcross();
		}
	}
	
	private String getLastStrChar(String string){
		String firstStrChar = string.charAt(this.sideLength-1)+"";
		return firstStrChar;
	}
	
	private void printLineAcross(){
		System.out.println();
		for(int i=0; i<sideLength; i++){
			System.out.print("____");
		}
		System.out.println();
	}
	
	private String shiftString(String string){
		String newStart = string.substring(1), firstLetter = string.substring(0, 1);
		return newStart += firstLetter;
	}
	
	//This method does not use any of the class variables, recreating the table from the string passed in
	public void printTableOriginalStringFromOutput(String output, int row){
		int width = output.length();
		String[] recreatedTable = new String[width];
		for(int i=0; i<width; i++){
			for(int n=0; n<width; n++){
				if(recreatedTable[n]==null){
					recreatedTable[n] = "";
				}
				recreatedTable[n] = prependCharToString(output.charAt(n), recreatedTable[n]);
			}
			Arrays.sort(recreatedTable);
		}
		System.out.print("Recreated Sorted Table:");
		printTable(recreatedTable);
		System.out.println("Original String: "+recreatedTable[row]+" found in row "+row);
	}
	
	private int findRowOfOriginal(){
		for(int row=0; row<this.sideLength; row++){
			if(this.transformTable[row].equals(this.input)){
				return row;
			}
		}
		return -1;
	}
	
	public int getRowOfOriginal(){
		return this.rowOfOriginal;
	}
	
	private String prependCharToString(char acter, String str){
		str = String.valueOf(acter)+str;
		return str;
	}
}
