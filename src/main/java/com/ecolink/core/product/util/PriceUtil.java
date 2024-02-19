package com.ecolink.core.product.util;

import java.text.DecimalFormat;

public class PriceUtil {

	public static String formatPrice(int originalPrice) {
		DecimalFormat df = new DecimalFormat("###,###");
		return df.format(originalPrice) + "원";
	}
}
