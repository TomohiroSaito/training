class Calc2 {
	public static void main(String args[]) {

		lengthCheck(args);

		double number1 = 0;
		double number2 = 0;
		boolean hasError = false;

		try {
			number1 = numberCheck(args[0]);
		} catch(NumberFormatException e) {
			System.out.println("1番目の引数は数値で入力してください。");
			hasError = true;
		}

		try{
			number2 = numberCheck(args[1]);
		} catch(NumberFormatException e) {
			System.out.println("2番目の引数は数値で入力してください。");
			hasError = true;
		}

		String operator = operatorCheck(args[2]);

		if(hasError == true) {
			return;
		}

		String message = createMessage(number1, number2, operator);

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

	static double numberCheck(String target) {
		double number;
		number = Double.parseDouble(target);
		return number;
	}

	static String operatorCheck(String operator) {
		if(!(operator.matches("\\+") || operator.matches("\\-") || operator.matches("\\*") || operator.matches("\\/") || operator.matches("%"))) {
			System.out.println("入力できる記号は、「+」、「-」、「*」、「/」、「%」です。");
			System.exit(-1);
		}
		return operator;
	}

	static String createMessage(double target1, double target2, String operator) {
		double answer = calculation(target1, target2, operator);
		String message = String.format("計算結果は、%.2fです。", round(answer));
		return message;
	}

	static double calculation(double target1, double target2, String operator) {
		double answer;
		if(operator.equals("+")) {
			answer = target1 + target2;
		} else if(operator.equals("-")) {
			answer = target1 - target2;
		} else if(operator.equals("*")) {
			answer = target1 * target2;
		} else if(operator.equals("/")) {
			answer = target1 / target2;
		} else {
			answer = target1 % target2;
		}
		return answer;
	}

	static double round(double target) {
		double temp = target * 1000;
		temp = Math.round(temp);
		temp = temp / 1000;
		return temp;
	}

}
