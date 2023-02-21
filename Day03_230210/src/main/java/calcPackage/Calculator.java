// 7-1 : 액션 종합 예제 - 계산기 구현
package calcPackage;
public class Calculator {
	private int n1, n2;
	private String op;
	
	public double calc() {
		double result = 0;
		switch(op) {
		case "+": 
			result = n1 + n2;
			break;
		case "-": 
			result = n1 - n2;
			break;
		case "*": 
			result = n1 * n2;
			break;
		case "/": 
			result = (double)n1 / n2;
			break;
		}
		return result;
	}

	public int getN1() {
		return n1;
	}

	public void setN1(int n1) {
		this.n1 = n1;
	}

	public int getN2() {
		return n2;
	}

	public void setN2(int n2) {
		this.n2 = n2;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}
}
