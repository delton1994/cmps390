package code_assignments;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class node{
	public int data;
	public node next;
}
class nameNode{
	public String name;
	public char nameCode;
	public int weight;
	public nameNode next;
}
public class LinkList {
	static Scanner input = new Scanner(System.in);
	static int counter = 0;
	static class linkMyName{
		 nameNode front;
		void init() { //initializing
			front = null;
		}
		nameNode makeNameNode(String name) {
			nameNode newOne; //instantiate new name node
			newOne = new nameNode();
			newOne.name = name;
			newOne.next = null;
			int w = 0;
			for(int j= 0; j<name.length();j++) {
				char c = name.charAt(j);
				if(j == 0) {
					newOne.nameCode = name.charAt(0);
				}
				w += c;	
			}
			newOne.weight = w;
			return newOne;
		}
		nameNode findTail() {
			nameNode current;
			current = front;
			while(current.next!=null) {
				current = current.next;
			}
			return current;
		}
		nameNode findThatName(String name) {
			nameNode curr, prev;
			curr = front;
			prev = curr;
			while(!curr.name.equals(name)) {
				prev = curr;
				curr = curr.next;
			}
			return prev;
		}
		void findThatSection(char nameCode) {
			nameNode curr;
			curr = front;
			int count = 0;
			while(curr!=null) {
				if(curr.name.charAt(0)==nameCode) {
					System.out.println(curr.name);
					count++;
				}
				curr = curr.next;
			}
			if(count==0) {
				System.out.println("No matches found");
			}
		}
		int findSectionTotal(char nameCode) {
			nameNode curr;
			int i = 0;
			curr = front;
			while(curr!=null) {
				if(curr.name.charAt(0)==nameCode) {
					i++;
				}
				curr = curr.next;
			}
			return i;
		}
		void insertAfter(nameNode p, String name) {
			nameNode temp;
			nameNode nextNode;
			nextNode = p.next;
			temp = makeNameNode(name);
			p.next = temp;
			temp.next = nextNode;
		}
		void insert(String name) {// three cases: front interior end
			nameNode curr, prev,temp;
			temp = makeNameNode(name);
			boolean sending;
			if(front == null) {// if empty list create one
				front = temp;
			}
			else if(name.compareTo(front.name)<0) { // insert before front
				temp.next = front;
				front = temp;
			}
			else {
				curr = front;
				prev = curr;
				sending = true;
				while(sending) {
					if(curr.name.equals(name)) {// check for duplicate
						sending = false;
					}
					else if(curr.name.compareTo(temp.name)<0) { // curr.name is a lower alphabet
						if(curr.next == null) {//end of list
							curr.next = temp;
							sending = false;
						}
						prev = curr;
						curr = curr.next;
					}
					else if(curr.name.compareTo(temp.name)>0) { //insert before
						temp.next = curr;
						prev.next = temp;
						sending = false;
					}
				}
			}
		}
		void showList() {
			nameNode current;
			current = front;
			while(current!=null) {
				System.out.print(current.name  + " ");
				current = current.next;
			}
			System.out.println();
		}
		 void deleteName(nameNode p) {
			 if(p ==front) {
				 
			 }
			 nameNode next;
			 next = p.next;
			 p.next = next.next;
		 }
	}
	static class linkMe{
		 node front;
		void init() { //initializing
			front = null;
		}
		node makeNode(int num) {
			node newOne; //instantiate new node
			newOne = new node();
			newOne.data = num;
			newOne.next = null;
			return newOne;
		}
		node findTail() {
			node current;
			current = front;
			while(current.next!=null) {
				current = current.next;
			}
			return current;
		}
		node findThatSpot(int num) {
			node curr, prev;
			curr = front;
			prev = curr;
			while(curr.data<num) {
				prev = curr;
				curr = curr.next;
			}
			return prev;
		}
		void insertAfter(node p, int num) {
			node temp;
			node nextNode;
			nextNode = p.next;
			temp = makeNode(num);
			p.next = temp;
			temp.next = nextNode;
		}
		void insert(int num) {// three cases: front interior end
			node curr, prev,temp;
			boolean sending;
			if(front == null) {// if empty list create one
				front = makeNode(num);
			}
			else if(num<front.data) { // insert before front
				temp = makeNode(num);
				temp.next = front;
				front = temp;
			}
			else {
				curr = front;
				prev = curr;
				sending = true;
				while(sending) {
					if(curr.data ==num) {// check for duplicate
						sending = false;
					}
					else if(curr.data <num) { // insert after
						if(curr.next == null) {
							curr.next = makeNode(num);
							sending = false;
						}
						prev = curr;
						curr = curr.next;
					}
					else if(curr.data>num) { //insert before
						temp = makeNode(num);
						temp.next = curr;
						prev.next = temp;
						sending = false;
					}
				}
			}
		}
		void simplelinkList(int m) {
			int j;
			init();
			node tail;
			for(j=0;j<m;j++) {
				if(j==0) {
					front = makeNode(j);		
				}
				else {
					tail = findTail();
					tail.next = makeNode(j);
				}
			}
		}
		void showList() {
			node current;
			current = front;
			while(current!=null) {
				System.out.print(current.data  + " ");
				current = current.next;
			}
			System.out.println();
		}		 
		 void deleteNode(node p) {
			 node next;
			 next = p.next;
			 p.next = next.next;
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
	private static void InsertorDelete(linkMe link) {
		char c = 0;
		while(c!='Q') {
		System.out.println("Do you want to Insert a node or delete a node? \n"
				+ "I for insert. D for delete. Q to exit");
		c = input.next().charAt(0);
		switch(c) {
		case 'I':
			System.out.print("Enter the node to insert:");
			int I = input.nextInt();
			link.insert(I);
			System.out.println("New list with " +I+ " inserted");
			link.showList();
			break;
		case 'D':
			System.out.println("Enter a node to delete:");
			int D = input.nextInt();
			node deleteMe = link.findThatSpot(D);
			link.deleteNode(deleteMe);
			System.out.println("New list with node " +D+ " deleted");
			link.showList();
			break;
		default:
			break;
		}
		}
	}
	private static void insertAfterorDelete() {
		linkMe link = new linkMe();
		link.init();
		System.out.println("LinkList with numbers 0-9");
		link.simplelinkList(10);
		link.showList();
		System.out.println();
		System.out.println("Enter the amount of nodes you want to create: ");
		int nList = input.nextInt();
		link.simplelinkList(nList);
		System.out.println("New list with " +nList+ " nodes");
		link.showList();
		System.out.println();
		char c = 0;
		while(c!='Q') {
		System.out.println("Do you want to insert a value after a node or delete a node? \n"
				+ "I for insert. D for delete. Q to exit");
		c = input.next().charAt(0);
		switch(c) {
		case 'I':
			System.out.print("Enter a node to add a value after:");
			int foundNode = input.nextInt();
			node spot = link.findThatSpot(foundNode);
			System.out.println("Enter the value to add after the node: ");
			int value = input.nextInt();
			link.insertAfter(spot, value);
			System.out.println("Value " +value+ " added after node " +foundNode+ ". New list: " );
			link.showList();
			System.out.println();
			break;
		case 'D':
			System.out.println("Enter a node to delete:");
			int D = input.nextInt();
			node deleteMe = link.findThatSpot(D-1);
			link.deleteNode(deleteMe);
			System.out.println("New list with node " +D+ " deleted");
			link.showList();
			System.out.println();
			break;
		default:
			break;
		}
		}
	}
	private static void InsertDeleteDisplay(linkMyName link) {
			char c = 0;
			while(c!='Q') {
			System.out.println("Do you want to Insert a name Delete a name or Show the list? \n"
					+ "I for insert. D for delete. S to show list Q to exit");
			c = input.next().charAt(0);
			switch(c) {
			case 'I':
				System.out.print("Enter the name to insert:");
				String name = input.next();
				link.insert(name);
				counter++;
				System.out.println("New list with " +name+ " inserted");
				link.showList();
				System.out.println();
				break;
			case 'D':
				System.out.println("Enter a name to delete:");
				String Dname = input.next();
				nameNode deleteMe = link.findThatName(Dname);
				link.deleteName(deleteMe);
				counter--;
				System.out.println("New list with name " +Dname+ " deleted");
				link.showList();
				System.out.println();
				break;
			case 'S':
				System.out.println("Current list");
				link.showList();
				System.out.println();
				break;
			default:
				break;
			}
			}
		}
	private static void sectionFinder(linkMyName link) {
		char c = 0;
		while(c!='Q') {
			System.out.println("\n Select an option: p for print section. n for number of items in section."
					+ " Q to exit");
			c = input.next().charAt(0);
			switch(c) {
			case 'p':
				System.out.print("Enter the first letter of the section you want to view:");
				char sect= input.next().charAt(0);
				link.findThatSection(sect);
				System.out.println();
				break;
			case 'n':
				System.out.print("Enter the first letter of the section you want the total for.");
				char t = input.next().charAt(0);
				int tName =link.findSectionTotal(t);
				System.out.println("total names in section " +t+ " is " +tName);
			default:
				break;
			}
		}
	}
	public static void main(String[] args) {		
		insertAfterorDelete();
		linkMe numLink = new linkMe();
		numLink.init();
		String content = readFile("data.txt");
		String[] contentArray = content.split(",");
		for(int i = 0; i<contentArray.length; i++ ) {
			String numtoParse = contentArray[i];
			int num = Integer.parseInt(numtoParse);
			numLink.insert(num);
		}
		System.out.println("Ordered list from data.txt");
		numLink.showList();
		InsertorDelete(numLink);
		
		linkMyName nameList = new linkMyName();
		nameList.init();
		String Ncontent = readFile("Name.txt");
		String[] nameArray = Ncontent.split(",");
		for(int i = 0; i<nameArray.length;i++) {
			String name = nameArray[i];
			nameList.insert(name);
			counter+=1;
		}
		System.out.println("Ordered name list from Name.txt ");
		nameList.showList();
		InsertDeleteDisplay(nameList);
		sectionFinder(nameList);
		
	}
}
