class Calc2 {
	public static void main(String args[]) {

		lengthCheck(args);

		double number1 = 0;
		double number2 = 0;
		int flag = 0;

		try {
			number1 = Double.parseDouble(args[0]);
		} catch(NumberFormatException e) {
			System.out.println("1番目の引数は数値で入力してください。");
			flag = 1;
		}

		try{
			number2 = Double.parseDouble(args[1]);
		} catch(NumberFormatException e) {
			System.out.println("2番目の引数は数値で入力してください。");
			flag = 1;
		}

		String operator = operatorCheck(args[2]);

		if(flag != 0) {
			System.exit(-1);
		}

		String message = calculation(number1, number2, operator);

		System.out.println(message);
	}

	static void lengthCheck(String[] arguments) {
		if(arguments.length < 3) {
			System.out.println("引数が足りません。");
			System.exit(-1);
		}
		if(arguments.length > 3) {
			System.out.println("引数が多すぎます。");
			System.exit(-1);
		}
	}

	static String operatorCheck(String operator) {
		if(!(operator.equals("+") || operator.equals("-") || operator.equals("*") || operator.equals("/") || operator.equals("%"))) {
			System.out.println("入力できる記号は、「+」、「-」、「*」、「/」、「%」です。");
			System.exit(-1);
		}
		return operator;
	}

	static String calculation(double target1, double target2, String operator) {
		String message ;
		double answer;
		if(operator.equals("+")) {
			answer = target1 + target2;
			message = String.format("計算結果は、%.2fです。", round(answer));
		} else if(operator.equals("-")) {
			answer = target1 - target2;
			message = String.format("計算結果は、%.2fです。", round(answer));
		} else if(operator.equals("*")) {
			answer = target1 * target2;
			message = String.format("計算結果は、%.2fです。", round(answer));
		} else if(operator.equals("/")) {
			answer = target1 / target2;
			message = String.format("計算結果は、%.2fです。", round(answer));
		} else {
			answer = target1 % target2;
			message = String.format("計算結果は、%.2fです。", round(answer));
		}
		return message;
	}

	static double round(double target) {
		double temp = target * 1000;
		temp = Math.round(temp);
		temp = temp / 1000;
		return temp;
	}

}
