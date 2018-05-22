


public class Goods {
	private String goodsName="";
	private int price = 0;

	public Goods() {}
	public Goods(String goodsName, int price){
		this.goodsName = goodsName;
		this.price = price;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public int getPrice() {
		return price;
	}
}
