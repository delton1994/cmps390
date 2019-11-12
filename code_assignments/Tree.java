package code_assignments;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

class treeNode{
	int data;
	treeNode left,right;
	public static int duplicateCount = 0;
}
public class Tree {
	public static int compareWrite = 0;
	public static int compareCreate = 0;
	public static int bruteCompare = 0;
	static void bubbleMe(int list[]){
		int i,j,temp;
		boolean swapping = true;
		while(swapping) {
			swapping = false;
			for(j=1;j<list.length;j++) {
				bruteCompare++;
				if(list[j-1]>list[j]) {
					temp = list[j];
					list[j] = list[j-1];
					list[j-1] = temp;
					swapping = true;
				}
			}	
		}
		for(i = 0;i<list.length;i++) {
			System.out.print(list[i] + " ");
		}
	}
	static class stackMe{
		int top;
		void init() {
			top =-1;
		}
		treeNode[] s = new treeNode[1000];
		void push(treeNode x) {
		top++;
		s[top] = x;
		
		}
		treeNode pop() {
			if(top ==-1) {
				return null;
			}
			else{
				treeNode x = s[top];
				top--;
				return x;
			}
		}
		boolean isItEmpty(){
			boolean isAnswer = false;
			if(top == -1) {
				isAnswer = true;
			}return isAnswer;
		}
		
	}
	static class treeMe{
		treeNode root;
	void init(){
		root = null;
	}
	treeNode makeMe(int num){
		treeNode t =new treeNode();
		t.data = num;
		t.left = null;
		t.right = null;
		compareCreate++;
		return t;
	}
	void buildTree(int list[]){
		init();
		int j,num;
		treeNode t;
		boolean searching;
		for(j=0;j<list.length;j++){
		num = list[j];
		if(root == null){
		  root = makeMe(num);
		}
		else{
		t = root;
		searching = true;
		while(searching){
			if(num<t.data){
				compareWrite++;
				if(t.left==null){
					t.left = makeMe(num);
					searching = false;
				  }
				else{
				  t = t.left;
				  }
			}
			else if(num>t.data){
				compareWrite++;
				if(t.right == null){
				  t.right = makeMe(num);
				  searching = false;
				}
				else{
					t = t.right;
				}
		      }
			else if(num == t.data) {
				compareWrite++;
				treeNode.duplicateCount++;
				searching = false;
			}
		     }
		    }
		   }
		  }
	void inOrder(treeNode t) {
		if (t.left != null) {
			inOrder(t.left);  
		} 
		System.out.print(t.data + " "); 
		if (t.right != null){ 
			inOrder(t.right); 
		}
	}
	void inOrderI(treeNode t) {
		treeNode curr;
		stackMe s = new stackMe();
		s.init();
		s.push(t);
		curr = t;
		while(!s.isItEmpty()|| curr!= null) {
			while(curr!=null) {
				s.push(curr);
				curr = curr.left;
			}
			if(!s.isItEmpty()) {
				curr = s.pop();
				System.out.println("Visiting " + curr.data);
				curr = curr.right;
			}
		}
		
	}
}
	private static String readFile(String fileName) {
		String content = "";
	      try {
	        File file = new File(fileName);
	        Scanner scanner = new Scanner(file);
	        while (scanner.hasNextLine()) {
	         content += (scanner.nextLine() + ",") ;
	        }
	        scanner.close();
	      } 
	      catch (FileNotFoundException e) {
	        e.printStackTrace();
	      }
		return content;
	    }  
	
	public static void writeFile(int compC, int compW, long Btime, long Ttime) {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter a name for the file: ");
		String name = input.next();
		try {
			FileWriter writer = new FileWriter("C:\\Users\\delto\\eclipse-workspace\\390_prac" + "\\"+name);
			writer.write("Number of duplicates: " + treeNode.duplicateCount +"\n");
			writer.write("Number of Compares made creating data: " + compC +"\n");
			writer.write("Number of Compares made writing data: " + compW+ "\n");
			writer.write("Number of Bubble sort compares: " + bruteCompare+ "\n");
			writer.write("Time in nanoseconds for tree sort: " + Ttime + "\n");
			writer.write("Time in nanoseconds for bubble sort: " + Btime);
			writer.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		input.close();
	}
		
	
	public static void main(String[] args) {
		String content = readFile("numbers.txt");
		String[] contentArray = content.split(",");
		int intA[] = new int[contentArray.length];
		for(int i = 0; i<contentArray.length; i++ ) {
			String numtoParse = contentArray[i];
			int num = Integer.parseInt(numtoParse);
			intA[i] = num;
		}
		treeMe groot = new treeMe();
		long TstartTime = System.nanoTime();
		groot.buildTree(intA);
		long TendTime = System.nanoTime();
		long Tduration = TendTime-TstartTime;
		
		System.out.println("Numbers sort using Recusion");
		groot.inOrder(groot.root);
		System.out.println("\n\nNumbers sort using iterations");
		groot.inOrderI(groot.root);
		
		System.out.println("\nNuumbers sort using bubble sort ");
		long BstartTime = System.nanoTime();
		bubbleMe(intA);
		long BendTime = System.nanoTime();
		long Bduration = BendTime-BstartTime;
		System.out.println();
		writeFile(compareCreate,compareWrite,Bduration,Tduration );
	}
}
