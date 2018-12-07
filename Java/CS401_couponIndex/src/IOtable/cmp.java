package IOtable;

import java.util.Comparator;

public class cmp implements Comparator<Coupon>{

	@Override
	public int compare(Coupon o1, Coupon o2) {
		if(o1.act_price!=o2.act_price) {
			return (int) (o1.act_price-o2.act_price);
		}
		else {
		return o2.item_name.compareTo(o1.item_name);
		}
	}
}

