package com.idea.tag.util;

import com.idea.tag.model.CDPI;

public class ModelUtil {
	private final static char CNCCHAR='+';

	public static CDPI toCDPI(String record, String delimiter,
			boolean needBase64) {
		String[] arr = record.split(delimiter);
		// 是否需要base64
		if (arr.length == 0 || arr == null)
			return null;

		CDPI dpi = null;
		if (arr.length == 38) {
			dpi = new CDPI();
			// 3G数据
			dpi.setMdn(arr[1].trim());
			dpi.setMeid(arr[2].trim());

			if (needBase64) {
				// dpi.setUserAgent(Base64Util.base64Decode(arr[26].trim()));
				// dpi.setDomainName(Base64Util.base64Decode(arr[28].trim()));
				// dpi.setRefer(Base64Util.base64Decode(arr[33].trim()));
				dpi.setUrl(Base64Util.base64Decode(arr[27].trim()));
			} else {
				// dpi.setUserAgent(arr[26].trim());
				// dpi.setDomainName(arr[28].trim());
				// dpi.setRefer(arr[33].trim());
				dpi.setUrl(arr[27].trim());
			}

		} else if (arr.length == 40) {
			dpi = new CDPI();
			// 4G数据
			dpi.setMdn(arr[1].trim());
			dpi.setMeid(arr[2].trim());
			if (needBase64) {
				// dpi.setUserAgent(Base64Util.base64Decode(arr[28].trim()));
				// dpi.setDomainName(Base64Util.base64Decode(arr[30].trim()));
				// dpi.setRefer(Base64Util.base64Decode(arr[35].trim()));
				dpi.setUrl(Base64Util.base64Decode(arr[29].trim()));
			} else {
				// dpi.setUserAgent(arr[28].trim());
				// dpi.setDomainName(arr[30].trim());
				// dpi.setRefer(arr[35].trim());
				dpi.setUrl(arr[29].trim());
			}
		} else if (arr.length == 12) {
			dpi = new CDPI();
//			dpi.setMdn(arr[0].trim());
			//修改固网 mdn=UserAccount+UserAgent
			dpi.setMdn(arr[0].trim()+CNCCHAR+arr[9].trim());
			if (needBase64) {
				// dpi.setUserAgent(Base64Util.base64Decode(arr[9].trim()));
				// dpi.setRefer(Base64Util.base64Decode(arr[8].trim()));
				// dpi.setDomainName(Base64Util.base64Decode(arr[6].trim()));
				dpi.setUrl(Base64Util.base64Decode(arr[7].trim()));
			} else {
				// dpi.setUserAgent(arr[9].trim());
				// dpi.setRefer(arr[8].trim());
				dpi.setUrl(arr[7].trim());
				// dpi.setDomainName(arr[6].trim());
			}
		}
		return dpi;
	}
}
