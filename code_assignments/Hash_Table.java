package code_assignments;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Hash_Table {
	
static class Hash{
	private String readFile() {
		int hashIndex =0;
		String content = "";
		try {
			File file = new File("Name.txt");
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String name = scanner.nextLine();
				hashIndex = hashFun(name);
				content += (name + " "+ hashIndex + ",");
			}
			scanner.close();
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return content;
	}
		String hashTable[];
		int tabLen;
		int numSmashes;
		void HashmyTable(int hashTableLen) {
			int j;
			tabLen = hashTableLen;
			numSmashes = 0;
			hashTable = new String[tabLen];
			
			for(j=0;j<tabLen;j++) {
				hashTable[j] = "#";
			}
			showHashTable();
		}
	    void showHashTable(){
	    	String content = readFile();
	    	String ConArray[] = content.split(",");
	    	for(int j = 0; j<ConArray.length;j++) {
	    		String num = ConArray[j].replaceAll("[a-z]", "");
	    		num = num.trim();
	    		int index =Integer.parseInt(num);
	    		hashTable[index] = ConArray[j];
	    	}
	    	int j;
	    	for(j=0;j<tabLen;j++){
	    		if(hashTable[j] != "#"){
	    			System.out.println(hashTable[j]);
	    		}
	    	}
	    	System.out.println("Number of Smashes: " + numSmashes);
	    	System.out.println("Hash Table size: " + tabLen);
	    	System.out.println("\n\n");
	    }
	    int letterVal(char letter){
	    	return letter-'a';
	    }
	     int hashFun(String name){
	    	int hashVal = 0;
	    	
	    	hashVal = (letterVal(name.charAt(0))*(26*26) 
	    			+ letterVal(name.charAt(1))*26 + letterVal(name.charAt(2)));
	    	if(tabLen == 200) {
	    		hashVal = hashVal/92;
	    	}
	    	if(tabLen == 400) {
	    		hashVal = hashVal/46;
	    	}
	    	if(tabLen == 700) {
	    		hashVal =hashVal/26;
	    	}
	    	while(hashTable[hashVal]!="#") {
	    		hashVal = hashVal +1;
	    		numSmashes++;
	    	}
	    	hashTable[hashVal] = name;
	    	return hashVal;   
	}
}
	public static void main(String[] args) {
		Hash h = new Hash();
		h.HashmyTable(200);
		h.HashmyTable(400);
		h.HashmyTable(700);
	}
}