class Calc {
	public static void main(String args[]) {
		if(args.length < 3) {
			System.out.println("引数が足りません。");
		} else if(args.length > 3) {
			System.out.println("引数が多すぎます。");
		} else {
			int flg = 1;
			double num1 = 0;
			double num2 = 0;
			try {
				num1 = Double.parseDouble(args[0]);
				flg++;
				num2 = Double.parseDouble(args[1]);
				String ope = args[2];
				double ans = 0;
				if(ope.equals("+")) {
					ans = num1 + num2;
					System.out.println("計算結果は、" + ans + "です。");
				} else if(ope.equals("-")) {
					ans = num1 - num2;
					System.out.println("計算結果は、" + ans + "です。");
				} else if(ope.equals("*")) {
					ans = num1 * num2;
					System.out.println("計算結果は、" + ans + "です。");
				} else if(ope.equals("/")) {
					ans = num1 / num2;
					System.out.println("計算結果は、" + ans + "です。");
				} else if(ope.equals("%")) {
					ans = num1 % num2;
					System.out.println("計算結果は、" + ans + "です。");
				} else {
					System.out.println("入力できる記号は、「+」、「-」、「*」、「/」、「%」です。");
				}
			} catch(NumberFormatException e) {
				System.out.println(flg + "番目の引数は数値で入力してください。");
			}
		}
	}
}
