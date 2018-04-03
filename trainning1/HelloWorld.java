import java.io.*;

class HelloWorld {
	public static void main(String args[]) {
		try{
				int ans = 0;
				for(int i=0; i<args.length; i++) {
					ans += Integer.parseInt(args[i]);
				}
				System.out.println("入力された値の合計は" + ans + "です。");
		} catch(NumberFormatException e) {
			System.out.println("数字で入力してください。");
		}
	}
}
