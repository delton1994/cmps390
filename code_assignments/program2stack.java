package code_assignments;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class program2stack {   
  static class Mystack{
        int top;
        char[] s = new char[100];
        void init(){
            top = -1;
        }
        void push(int x){
            top++;
            s[top] = (char) x;
        }
         char pop(){
        	 if(top == -1) {
        		 return 0;
        	 }
        	 else {	 
        		 char x = s[top];
        		 top--;
        		 return x;
        	 }
        } 
        boolean isStackEmpty(){
            Boolean isAnswer = false;
            if(top ==-1){
                isAnswer = true;
            }return isAnswer;
        }
        public void showStack(){
    		int j;	
    		System.out.println(" ");
    		System.out.println("Stack contents ...");
    		for(j=top;j>-1;j--) {
    			System.out.print(s[j]);
    		}
    		System.out.print(" ");
    	}
        boolean isMatch(char char1, char char2){
            if(char1 =='(' && char2 ==')'){
                return true;
            }
            else if(char1 =='[' && char2 ==']'){
            return true;
            }
            else if(char1 =='{' && char2 =='}'){
            return true;
            }
            else{
            return false;
            }
        }
  }
  static class Dstack{
	  int top;
      double[] s = new double[100];
      void init(){
          top = -1;
      }
      void push(double x){
          top++;
          s[top] = x;
      }
       double pop(){
      	 if(top == -1) {
      		 return 0;
      	 }
      	 else {	 
      		 double x = s[top];
      		 top--;
      		 return x;
      	 }
      } 
  }
 static boolean isItBal(String eq) {
	   Mystack stack = new Mystack();
       stack.init();
	   for(int i = 0; i<eq.length(); i++){
		   char c =  eq.charAt(i);
           if(c == '('|| c == '['|| c == '{'){
               stack.push(c);
           }
           else if(c == ')' || c ==']' || c=='}'){
               if(stack.isStackEmpty() == true){
               	return false;
               }
               else if(!stack.isMatch(stack.pop(), c)) {
               	return false;
               }
           }
       }
	   if(stack.isStackEmpty() == true) {
		   return true;
	   }
	   else return false;
   }
  static String IntoPost(String eq) {
	  Mystack s = new Mystack();s.init();
	  Mystack op = new Mystack();op.init();
	  Mystack stack = new Mystack();stack.init();
	  String answer = "";
	  for(int i = 0; i<eq.length();i++) {
		  char ch = eq.charAt(i);
		  if(Character.isDigit(ch)) {
			  s.push(ch);
		  }
		  else if(ch == '+' || ch == '-'|| ch =='*' || ch =='/') {
			  op.push(ch);
		  }
		  else if(ch == ')') {
			  char d = op.pop();
			  s.push(d);
		  }
		  if(i==eq.length()-1) {
		  char d =  op.pop();
		  s.push(d);
		  }
	  }
	  while(s.isStackEmpty()==false) {
		  char d = s.pop();
		  stack.push(d);
	  }
	  while(stack.isStackEmpty() == false) {
		  answer += stack.pop();
	  }
	return answer; 
  }   
  static double PostEval(String eq) {
	  Dstack stackme = new Dstack();
	  stackme.init(); 
	  for(int i = 0; i<eq.length();i++) {
		  char c = eq.charAt(i);
		  if(c>'0'&&c<='9') {
			  double num = c-'0';
			 stackme.push(num);
		  }
		  else if(c == '+' || c== '-' || c == '*' || c =='/') {
			 double x,y,z;
			  x = stackme.pop();
			  y = stackme.pop();
			  switch(c) {
			  case '+':
				z = x+y;
				stackme.push(z);
				break;
			  case '-':
				  z = y-x;
					stackme.push(z);
					break;
			  case '*':
				  z = x*y;
					stackme.push(z);
					break;
			  case '/':
				  z = x/y;
					stackme.push(z);
					break;
			  } 
		  }
	  } 
	  return stackme.pop();
  }
private static String readFile(String fileName) {
	String content = "";
      try {
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
         String str = scanner.nextLine();
         if(str.length()<=15) {
        	 content += str + ",";
         }
        }
        scanner.close();
      } 
      catch (FileNotFoundException e) {
        e.printStackTrace();
      }
	return content;
    } 
 public static void main(String args[]) {
	String content =readFile("buff.txt");
	String[] arr = content.split(",");
	for(int i = 0; i<arr.length;i++) {
		if(i<3) {
			if(isItBal(arr[i])) {
				System.out.println("Balanced");
			}
			else System.out.println("Not balanced");
		}
		else if(i>2 &&i<7) {
			System.out.println(PostEval(arr[i]));
		}
		else if(i>6 && i<11){
			System.out.println(IntoPost(arr[i]));
		}
		else if(i>10 &&i<15) {
			while(i<15) {
			if(isItBal(arr[i])) {
				System.out.println("Balanced");
			}
			else System.out.println("Not balanced");
				String s = IntoPost(arr[i]);
				System.out.println(s);
				System.out.println(PostEval(s));
				i++;
			}
		}
	}
 }
}