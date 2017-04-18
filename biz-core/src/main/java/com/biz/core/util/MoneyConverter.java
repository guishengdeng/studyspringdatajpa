package com.biz.core.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import org.springframework.core.NamedThreadLocal;

/**
 * @author yanweijin
 * @date 2017/2/10
 */
public class MoneyConverter {

	public final static MoneyConverter instance = new MoneyConverter();

	private final static ThreadLocal<DecimalFormat> tl = new NamedThreadLocal<>("money format thread local");

	private MoneyConverter(){}

	public String fen2yuan(Integer fen){
		BigDecimal _100 = BigDecimal.valueOf(100);
		return getFormat().format(BigDecimal.valueOf(fen).divide(_100));
	}

	private static DecimalFormat getFormat(){
		DecimalFormat df = tl.get();
		if(df==null){
			df = new DecimalFormat("0.00");
			tl.set(df);
		}
		return df;
	}

}
