class Calc {
	public static void main(String args[]) {
		try {
			double ans = 0;
			for(int i=0; i<args.length; i++) {
				ans += Double.parseDouble(args[i]);
			}
			String strans = String.format("入力された値の合計は%fです。", ans);
			System.out.println(strans);
		} catch (NumberFormatException e) {
			System.out.println("数字で入力してください。");
		}
	}
}
