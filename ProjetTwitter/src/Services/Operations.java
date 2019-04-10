package Services;

public class Operations {

	public static double calcul(int a, int b, String op) {
		double res = 0;
		if(op.equals("mult")) {
			res=multiplication(a,b);
		}
		if(op.equals("div")) {
			res=division(a,b);
		}
		if(op.equals("plus")) {
			res=addition(a,b);
		}
		return res;
	}

	private static double addition(int a, int b) {
		return a+b;
	}

	private static double division(int a, int b) {
		// TODO Auto-generated method stub
		return a/b;
	}

	private static double multiplication(int a, int b) {
		// TODO Auto-generated method stub
		return a*b;
	}

}
