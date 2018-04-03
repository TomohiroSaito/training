class Calc {
	public static void main(String args[]) {
		if(args.length < 3) {
			System.out.println("引数が足りません。");
		} else if(args.length > 3) {
			System.out.println("引数が多すぎます。");
		} else {
			int flag = 1;
			double number1 = 0;
			double number2 = 0;
			try {
				number1 = Double.parseDouble(args[0]);
				flag++;
				number2 = Double.parseDouble(args[1]);
				String operator = args[2];
				double answer = 0;
				if(operator.equals("+")) {
					answer = number1 + number2;
					System.out.println("計算結果は、" + answer + "です。");
				} else if(operator.equals("-")) {
					answer = number1 - number2;
					System.out.println("計算結果は、" + answer + "です。");
				} else if(operator.equals("*")) {
					answer = number1 * number2;
					System.out.println("計算結果は、" + answer + "です。");
				} else if(operator.equals("/")) {
					answer = number1 / number2;
					System.out.println("計算結果は、" + answer + "です。");
				} else if(operator.equals("%")) {
					answer = number1 % number2;
					System.out.println("計算結果は、" + answer + "です。");
				} else {
					System.out.println("入力できる記号は、「+」、「-」、「*」、「/」、「%」です。");
				}
			} catch(NumberFormatException e) {
				System.out.println(flag + "番目の引数は数値で入力してください。");
			}
		}
	}
}
