class Calc2 {
	public static void main(String args[]) {
		if(inputCheck(args)) {
			return;
		}
		double answer = calculation(args);
		outMessage(answer);
	}

	static boolean inputCheck(String[] arguments) {
		lengthCheck(arguments);
		boolean hasError = false;
		if(!numberCheck(arguments[0])) {
			System.out.println("1番目の引数は数値で入力してください。");
			hasError = true;
		}
		if(!numberCheck(arguments[1])) {
			System.out.println("2番目の引数は数値で入力してください。");
			if(!hasError) {
				hasError = true;
			}
		}
		if(!operatorCheck(arguments[2])) {
			System.out.println("入力できる記号は、「+」、「-」、「*」、「/」、「%」です。");
			if(!hasError) {
				hasError = true;
			}
		}
		return hasError;
	}

	static double calculation(String[] arguments) {
		double answer;
		double target1 = Double.parseDouble(arguments[0]);
		double target2 = Double.parseDouble(arguments[1]);
		String operator = arguments[2];
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

	static void outMessage(double answer) {
		String message = createMessage(answer);
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

	static boolean numberCheck(String target) {
		return target.matches("^([1-9]\\d*|0)(\\.\\d+)?$");
	}

	static boolean operatorCheck(String operator) {
		return operator.matches("\\+|\\-|\\*|\\/|%");
	}

	static String createMessage(double answer) {
		return String.format("計算結果は、%.2fです。", round(answer));
	}

	static double round(double target) {
		double temp = target * 1000;
		temp = Math.round(temp);
		temp = temp / 1000;
		return temp;
	}

}
