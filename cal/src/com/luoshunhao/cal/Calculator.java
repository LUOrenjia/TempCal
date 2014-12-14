package com.luoshunhao.cal;

import java.math.BigDecimal;
import java.util.EmptyStackException;
import java.util.Stack;

public class Calculator {
	//用户输入的表达式
	private static String expression;
	//逆波兰表达式
	private String suffix;
	
	final static char ADD = '+';
	final static char SUB = '-';
	final static char MUL = '*';
	final static char DIV = '/';
	//运算符高优先级
	private static final int HIGH_PRIO = 2;
	//运算符低优先级
	private static final int LOW_PRIO = 1;

	public Calculator(String expression){
		this.expression = expression;
		createSuffix();
	}
	//
	public String getSuffix() {
		return suffix;
	}
	
	private void createSuffix(){
		Stack<String> stack = new Stack<String>();
		stack.clear();
		String exp = expression.trim();
		String suf = "";
		int i = 0;
		char c;
		while(i<exp.length()){
			c = exp.charAt(i);
			if(isNum(c)){
				String num = "";
				while(i<exp.length() && isNum(exp.charAt(i))){
					num += (exp.charAt(i)+"");
					i++;
				}
				suf += (num+" "); 
				i--;
			}else if(c == ADD || c == SUB || c == MUL || c == DIV){
				while(true){
					if(stack.isEmpty()){
						break;
					}
					if(compare(stack.peek().charAt(0), c)){
						break;
					}
					suf += (stack.pop()+" "); 
				}
				stack.push(c+"");
			}
			i++;
		}
		while(!stack.isEmpty()){
			suf += (stack.pop()+" "); 
		}
		this.suffix = suf;
	}
	private boolean isNum(char c){
		if(c >= '0' && c <='9'){
			return true;
		}
		return false;
	}
	private boolean compare(char Ope_Stack , char Ope_Next){
		int Priority_Stack = getPriority(Ope_Stack);
		int Priority_Next = getPriority(Ope_Next);
		if(Priority_Stack < Priority_Next){
			return true;
		}
		return false;
	}
	private int getPriority(char ope_Stack) {
		// TODO Auto-generated method stub
		if(ope_Stack == '*' || ope_Stack == '/'){
			return HIGH_PRIO;
		}
		if(ope_Stack == '+' || ope_Stack == '-'){
			return LOW_PRIO;
		}
		return 0;
	}
	public String GetResult(){
		String[]  str = suffix.split(" ");
		Stack<String> stack = new Stack<String>();
		for(int i = 0 ; i < str.length ; i++){
			if(str[i].equals(ADD+"")||str[i].equals(SUB+"")
					||str[i].equals(MUL+"")||str[i].equals(DIV+"")){
				try{
					String RightNum = stack.pop();
					String LiftNum = stack.pop();
					String result = Calc(LiftNum,RightNum,str[i]);
					stack.push(result);
				}catch(EmptyStackException e){
					return "输入表达式有误";
				}
			}else{
				stack.push(str[i]);
			}
		}
		if(!stack.isEmpty()){
			return stack.pop();
		}
		return "结果栈为空";
	}
	private String Calc(String liftNum, String rightNum, String Ope) {
		// TODO Auto-generated method stub
		BigDecimal BigLiftNum = null;
		BigDecimal BigRightNum = null;
		try{
			BigLiftNum = new BigDecimal(liftNum);
			BigRightNum = new BigDecimal(rightNum);
		}catch (Exception e) {
			// TODO: handle exception
			return "算数异常";
		}
		switch (Ope.charAt(0)) {
		case ADD: return BigLiftNum.add(BigRightNum).toString();
		case SUB: return BigLiftNum.subtract(BigRightNum).toString();
		case MUL: return BigLiftNum.multiply(BigRightNum).toString();
		case DIV:{
			if(BigRightNum.doubleValue() == 0){
				return "除数为零";
			}
			String result = BigLiftNum.divide(BigRightNum, 20, BigDecimal.ROUND_DOWN).toString();
			int mark = 0;
			if((mark = result.indexOf('.'))!=-1){
				for(int i = mark ; i < result.length() ; i++){
					if(result.charAt(i)!='0'){
						mark = i;
					}
				}
				if(result.charAt(mark)=='.'){
					mark--;
				}
				return result.substring(0, mark+1);
			}
			return result;
		}
		default:
			break;
		}
		return "";
	}
	
	
}
