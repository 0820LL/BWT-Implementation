package edu.bsu.cs224;

public class Main {
	public static void main(String[] args){
		//Example string that I used in class
		String testString = "AGATCCTG";
		
		//Object that deals with the transform logic
		BWT transform = new BWT(testString);
		
		//Prints the transformed string and the table
		transform.printTransform();
		
		String transformedString = transform.getTransform();
		System.out.println("Transformed String: "+transformedString+"\n");
		int rowNumberOfOriginalString = transform.getRowOfOriginal();
		
		
		//Prints the table and original string using only the transformed string and 
		//the row number of the original string in the sorted table
		transform.printTableOriginalStringFromOutput(transformedString, rowNumberOfOriginalString);
	}	
}


//perhaps it would be better to have a 1d array of strings?