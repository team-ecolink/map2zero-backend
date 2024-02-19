package com.ecolink.core.product.util;

import java.text.DecimalFormat;

public class PriceUtil {

	private PriceUtil() {
	}

	private static final DecimalFormat df = new DecimalFormat("###,###");

	public static String formatPrice(int originalPrice) {
		return df.format(originalPrice) + "원";
	}

}
