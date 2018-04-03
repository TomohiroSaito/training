class Calc {
	public static void main(String args[]) {
		try {
			double answer = 0;
			for(int i=0; i<args.length; i++) {
				answer += Double.parseDouble(args[i]);
			}
			String stringAnswer = String.format("入力された値の合計は%fです。", answer);
			System.out.println(stringAnswer);
		} catch (NumberFormatException e) {
			System.out.println("数字で入力してください。");
		}
	}
}
