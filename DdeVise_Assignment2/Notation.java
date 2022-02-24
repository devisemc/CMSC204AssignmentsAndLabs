/**
 * 
 * @author Donovan deVise
 *
 */
import java.util.ArrayList;
import java.util.Arrays;

public class Notation {
	public static String convertInfixToPostfix(String infix) throws InvalidNotationFormatException
	{
		// Setup Vars
		char temp;
		boolean recheck;
		String postfix = "";
		MyQueue queue = new MyQueue(infix.length());
		MyStack stack = new MyStack(infix.length());
		queue.fill(new ArrayList<String>(Arrays.asList(infix.split(""))));
		// Run through infix
		for (int i = 0; i < infix.length(); i++)
		{
			try {
				recheck = true;
				// Take out item
				char item = infix.charAt(i);
				// Evaluate
				if (Character.isDigit(item))
				{
					postfix += item;
					continue;
				}
				else if (stack.isEmpty() || (char)stack.top() == '(' || item == '(')
				{
					stack.push(item);
					continue;
				}
				else if (item == ')')
				{
					temp = (char)stack.pop();
					while (temp != '(')
					{
						postfix += temp;
						temp = (char)stack.pop();
					}
					continue;
				}
				while (recheck)
				{
					switch (precedence(item, (char)stack.top()))
					{
					case 1:
						stack.push(item);
						recheck = false;
						break;
					case -1:
						postfix += stack.pop();
						break;
					case 0:
						postfix += stack.pop();
						stack.push(item);
						recheck = false;
					}
				}
			} catch (Exception e) {
				throw new InvalidNotationFormatException();
			}
		}
		// pop and print all ops at end of expression
		for (int i = 0; i < stack.size(); i++)
		{
			try {
				postfix += stack.pop();
			} catch (Exception e) {
				throw new InvalidNotationFormatException();
			}
		}
		return postfix;
	}
	public static String convertPostfixToInfix(String postfix) throws InvalidNotationFormatException
	{
		MyStack stack = new MyStack(postfix.length());
		String temp;
		String expression;
		try {
		for (int i = 0; i < postfix.length(); i++){
			char item = postfix.charAt(i);
			if (Character.isDigit(item))
				stack.push(Character.toString(item));
			else
			{
				temp = (String)stack.pop();
				stack.push("(" + (String)stack.pop() + item + temp + ")");
				
			}
		}
		return (String) stack.top();}
		catch (Exception e)
		{
			throw new InvalidNotationFormatException();
		}
	}
	public static double evaluatePostfixExpression(String postfixExpr) throws InvalidNotationFormatException
	{
		MyStack stack = new MyStack(postfixExpr.length());
		char item;
		Double temp, result;
		try
		{
			for (int i = 0; i < postfixExpr.length(); i++)
			{
				item = postfixExpr.charAt(i);
				if (Character.isDigit(item) || item == '(')
				{
					stack.push(Character.toString(item));
				}
				else if (postfixExpr.charAt(i) == '+' || postfixExpr.charAt(i) == '-' || postfixExpr.charAt(i) == '/'
						|| postfixExpr.charAt(i) == '*') {
					temp = Double.parseDouble((String) stack.pop());

					switch (postfixExpr.charAt(i)) {
					case '/':
						result = Double.parseDouble((String) stack.pop()) / temp;
						stack.push(result.toString());
						break;
					case '*':
						result = Double.parseDouble((String) stack.pop()) * temp;
						stack.push(result.toString());
						break;
					case '+':
						result = Double.parseDouble((String) stack.pop()) + temp;
						stack.push(result.toString());
						break;
					case '-':
						result = Double.parseDouble((String) stack.pop()) - temp;
						stack.push(result.toString());
						break;
					}
				}
			}
		}catch (Exception e)
		{
			throw new InvalidNotationFormatException();
		}
		return Double.parseDouble((String)stack.pop());
	}
	/**
	 * 
	 * @param o1
	 * @param o2
	 * @return 1 if o1 is higher precedence, -1 if o2 is higher, 0 if equal
	 */
	public static int precedence(char o1, char o2)
	{
		char[][] prec = {{'(',')'},{'^'},{'*','/'},{'+','-'}};
		int i1,i2;
		i1 = i2 = -1;
		for (int i = 0; i < prec.length; i++)
		{
			for (int j = 0; j < prec[i].length; j++)
			{
				if (prec[i][j] == o1)
					i1 = i;
				if (prec[i][j] == o2)
					i2 = i;
			}
		}
		if (i1 > i2)
			return 1;
		else if (i1 < i2)
			return -1;
		else return 0;
	}
}
