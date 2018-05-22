

import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;

public class GoodsController {
	public static void main(String args[]) {
		Visitor visitor = new Visitor("四次元太郎", "京都府京都市中京区御池通間之町東入高宮町206 御池ビル6階");
		//商品の情報を作成
		Goods goods = new Goods("三段収納BOX", 2980);
		try {
			//Velocityの初期化
			Velocity.init();
			//Velocityコンテキストに値を設定
			VelocityContext context = new VelocityContext();
			context.put("visitor", visitor);
			context.put("goods", goods);

			StringWriter sw = new StringWriter();
			//テンプレートの作成
			Template template = Velocity.getTemplate("src/main/java/order.vm");
			//テンプレートとマージ
			template.merge(context, sw);
			//マージしたデータはWriterオブジェクトであるswが持っているのでそれを文字列として出力
			System.out.println(sw.toString());
			sw.flush();

		} catch(ResourceNotFoundException e) {
			System.out.println("テンプレートが見つかりません");
		} catch(ParseErrorException e) {
			System.out.println("構文にエラーがあります");
		} catch(MethodInvocationException e) {
			System.out.println("テンプレートのどこかにエラーがあります");
		} catch(Exception e) {
			System.out.println("その他のエラーです");
		}
	}
}
