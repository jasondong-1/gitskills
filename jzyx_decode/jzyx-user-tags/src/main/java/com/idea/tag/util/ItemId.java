package com.idea.tag.util;

public class ItemId {
	public static String evaluate(String stringBuffer) {
		
		if(null == stringBuffer){
			return new String();
		}
		String url = stringBuffer.toString() ;
		String urlid = "" ; 
		if(url.contains("%3D")){
			url = url.replaceAll("%3D", "=");
		}
		if(url.contains("%3F")) {
			url = url.replaceAll("%3F", "?");
		}
		if(url.contains("%2F")) {
			url = url.replaceAll("%2F", "/");
		}
		if(url.contains("%26")) {
			url = url.replaceAll("%26", "&");
		}
		//京东
		if(url.contains("item.jd.com")){
			urlid = url.substring(url.indexOf("item.jd.com")+11);
			if(urlid.contains(".html")){
				urlid = urlid.substring(0, urlid.indexOf(".html"));
			}
			if(urlid.contains("%2F")){
				urlid = urlid.replaceAll("%2F", "");
			}else if(urlid.contains("/")){
				urlid = urlid.replaceAll("/", "");
			}
			urlid = "JD" + urlid ; 
		}
		//淘宝
		else if(url.contains("item.taobao.com") || url.contains("detail.taobao.com")){
			if(url.contains("?id=")){
				urlid = url.substring(url.indexOf("?id=")+4);
			}else if(url.contains("&id=")){
				urlid = url.substring(url.indexOf("&id=")+4);
			}			
			if(urlid.contains("&")){
				urlid = urlid.substring(0, urlid.indexOf("&"));
			}
			if(url.contains("itemid=")){
				urlid = url.substring(url.indexOf("itemid=")+7);
				if(urlid.contains("&catid=")){
					urlid = urlid.substring(0,urlid.indexOf("&catid="));
				}
			}
			if(urlid.contains("?url")){
				urlid = urlid.substring(0,urlid.indexOf("?url"));
			}
			if(urlid.contains("http")){
				urlid = urlid.substring(0,urlid.indexOf("http"));
			}
			if(urlid.contains("?spm")){
				urlid = urlid.substring(0,urlid.indexOf("?spm"));
			}
			if(urlid.contains("%")){
				urlid = urlid.substring(0,urlid.indexOf("%"));
			}
			if(urlid.contains("?")){
				urlid = urlid.substring(0,urlid.indexOf("?"));
			}
							
			urlid ="TAOBAO"+ urlid; 
		}
		//天猫
		else if(url.contains("detail.tmall.com") || url.contains("item.tmall.com")  ){
			if(url.contains("?id=")){
				urlid = url.substring(url.indexOf("?id=")+4);
			}else if(url.contains("&id=")){
				urlid = url.substring(url.indexOf("&id=")+4);
			}
			
			if(urlid.contains("&")){
				urlid = urlid.substring(0, urlid.indexOf("&"));
			}
			if(urlid.contains("?url")){
				urlid = urlid.substring(0,urlid.indexOf("?url"));
			}
			if(urlid.contains("http")){
				urlid = urlid.substring(0,urlid.indexOf("http"));
			}
			if(urlid.contains("?spm")){
				urlid = urlid.substring(0,urlid.indexOf("?spm"));
			}
			if(urlid.contains("%")){
				urlid = urlid.substring(0,urlid.indexOf("%"));
			}
			if(urlid.contains("?")){
				urlid = urlid.substring(0,urlid.indexOf("?"));
			}
			urlid= "TMALL"+urlid;
		}
		//一号店
		else if(url.contains("item.yhd.com")){
			if(url.contains("index.do")){
				return null;
			}
			urlid = url.substring(url.indexOf("item.yhd.com")+ 12);
			if(urlid.contains("item")){
				urlid = urlid.substring(urlid.indexOf("item")+4);
			}
			if(urlid.contains("lp")){
				urlid = urlid.substring(urlid.indexOf("lp")+2);
			}
			if(urlid.startsWith("/")){
				urlid = urlid.replace("/", "");
			}
			if(urlid.contains("?")){
				urlid=urlid.substring(0, urlid.indexOf("?"));
			}
			if(urlid.contains("&")){
				urlid=urlid.substring(0, urlid.indexOf("&"));
			}
			if(!urlid.contains(".do")){
				urlid = urlid; 
			}
			urlid = "YHD"+ urlid; 
		}
		//当当
		else if(url.contains("product.dangdang.com")){
			urlid = url.substring(url.indexOf("product.dangdang.com")+20);
			if(urlid.contains(".html")){
				urlid = urlid.substring(0, urlid.indexOf(".html"));
			if(urlid.contains("%2F")){
				urlid = urlid.replaceAll("%2F", "");
			}else if(urlid.contains("/")){
				urlid = urlid.replaceAll("/", "");
			}
			urlid = urlid; 
			}
			urlid = "DD" + urlid;
		}
		//易迅
		else if(url.contains("item.yixun.com")){
			urlid = url.substring(url.indexOf("item.yixun.com")+14);
			if(urlid.contains(".html")){
				urlid = urlid.substring(0, urlid.indexOf(".html"));
			}
			if(urlid.contains("item-")){
				urlid = urlid.substring(urlid.indexOf("item-")+5);
			}
			urlid = "YX"+ urlid;
		}
		//苏宁
		else if(url.contains("http://product.suning.com/")){
			urlid = url.substring(url.indexOf("product.suning.com/")+19);
			if(urlid.contains(".html")){
				urlid = urlid.substring(0, urlid.indexOf(".html"));
			}
			if(urlid.contains("/")){
				urlid = urlid.substring(urlid.indexOf("/")+1);
			}
			urlid = "SUNING"+urlid;
		}
		//国美
		else if(url.contains("www.gome.com.cn")){
			urlid = url.substring(url.indexOf("www.gome.com.cn")+15);
			if(urlid.contains(".html")){
				urlid = urlid.substring(0,urlid.indexOf(".html"));
			}
			if(urlid.contains("product")){
				urlid = urlid.substring(urlid.indexOf("product")+7);
			}
			if(urlid.contains("/")){
				urlid = urlid.replaceAll("/", "");
			}
			if(urlid.contains("-")){
				urlid = urlid.substring(0,urlid.indexOf("-"));
			}
			if(urlid.contains("-pop")){
				urlid = urlid.substring(0,urlid.indexOf("-pop"));
			}
			if(urlid.contains("pop")){
				urlid = urlid.substring(0,urlid.indexOf("pop"));
			}
			urlid = "GOME"+urlid;
		}
		//亚马逊
		else if(url.contains("amazon.cn")){
			urlid = url.substring(url.indexOf("www.amazon.cn")+13);
			if(urlid.contains("gp/registry/huc/add-item-ajax")){
				urlid = urlid.substring(urlid.indexOf("add-item-ajax")+13);
				if(urlid.contains("&ASIN")){
					urlid = urlid.substring(urlid.indexOf("&ASIN=")+6);
				}
				if(urlid.contains("&offerListingID")){
					urlid = urlid.substring(0,urlid.indexOf("&offerListingID"));
				}
				urlid = urlid;
			}else if(urlid.contains("dp")){
				urlid = urlid.substring(urlid.indexOf("/dp")+3);
				if(urlid.contains("/ref")){
					urlid = urlid.substring(0,urlid.indexOf("/ref"));
				}
				if(urlid.contains("?ref")){
					urlid = urlid.substring(0,urlid.indexOf("?ref"));
				}
				if(urlid.contains("product")){
					urlid = urlid.substring(urlid.indexOf("product")+7);
				}
				if(urlid.contains("ASIN")){
					urlid = urlid.substring(urlid.indexOf("ASIN")+4);
				}
				if(urlid.contains("?camp")){
					urlid = urlid.substring(0,urlid.indexOf("?camp"));
				}
				if(urlid.contains("?smid")){
					urlid = urlid.substring(0,urlid.indexOf("?smid"));
				}
				if(urlid.contains("?tag")){
					urlid = urlid.substring(0,urlid.indexOf("?tag"));
				}
				if(urlid.contains("?m")){
					urlid = urlid.substring(0,urlid.indexOf("?m"));
				}
				if(urlid.contains("?t")){
					urlid = urlid.substring(0,urlid.indexOf("?t"));
				}
				if(urlid.contains("?ie")){
					urlid = urlid.substring(0,urlid.indexOf("?ie"));
				}
				if(urlid.contains("?select")){
					urlid = urlid.substring(0,urlid.indexOf("?select"));
				}
				if(urlid.contains("?*")){
					urlid = urlid.substring(0,urlid.indexOf("?*"));
				}
				if(urlid.contains("/sr")){
					urlid = urlid.substring(0,urlid.indexOf("/sr"));
				}
				if(urlid.contains("/")){
					urlid = urlid.replaceAll("/","");
				}
				if(urlid.contains("?psc")){
					urlid = urlid.substring(0,urlid.indexOf("?psc"));
				}
				urlid = urlid ;
			}else if(urlid.contains("gp")){
				urlid = urlid.substring(urlid.indexOf("/gp")+3);
				if(urlid.contains("/ref")){
					urlid = urlid.substring(0,urlid.indexOf("/ref"));
				}
				if(urlid.contains("?ref")){
					urlid = urlid.substring(0,urlid.indexOf("?ref"));
				}
				if(urlid.contains("product")){
					urlid = urlid.substring(urlid.indexOf("product")+7);
				}
				if(urlid.contains("ASIN")){
					urlid = urlid.substring(urlid.indexOf("ASIN")+4);
				}
				if(urlid.contains("?camp")){
					urlid = urlid.substring(0,urlid.indexOf("?camp"));
				}
				if(urlid.contains("?smid")){
					urlid = urlid.substring(0,urlid.indexOf("?smid"));
				}
				if(urlid.contains("?tag")){
					urlid = urlid.substring(0,urlid.indexOf("?tag"));
				}
				if(urlid.contains("?m")){
					urlid = urlid.substring(0,urlid.indexOf("?m"));
				}
				if(urlid.contains("?t")){
					urlid = urlid.substring(0,urlid.indexOf("?t"));
				}
				if(urlid.contains("?ie")){
					urlid = urlid.substring(0,urlid.indexOf("?ie"));
				}
				if(urlid.contains("?select")){
					urlid = urlid.substring(0,urlid.indexOf("?select"));
				}
				if(urlid.contains("?*")){
					urlid = urlid.substring(0,urlid.indexOf("?*"));
				}
				if(urlid.contains("/sr")){
					urlid = urlid.substring(0,urlid.indexOf("/sr"));
				}
				if(urlid.contains("?psc")){
					urlid = urlid.substring(0,urlid.indexOf("?psc"));
				}
				if(urlid.contains("?")){
					urlid = urlid.substring(0,urlid.indexOf("?"));
				}
				if(urlid.contains("&")){
					urlid = urlid.substring(0,urlid.indexOf("&"));
				}
				if(urlid.contains("=")){
					urlid = urlid.replaceAll("=", "");
				}
				if(urlid.contains("/")){
					urlid = urlid.replaceAll("/", "");
				}
				if(urlid.length()>10){
					urlid = urlid.substring(0,10);
				}
				
			}	
			urlid = "AMAZON" +urlid;
		}
		//唯品会
		else if(url.contains("www.vip.com")){
			urlid = url.substring(url.indexOf("www.vip.com")+11);
			if(urlid.contains(".html")){
				urlid = urlid.substring(0,urlid.indexOf(".html"));
			}
			if(urlid.contains("detail-")){
				urlid = urlid.substring(urlid.indexOf("detail-")+7);
			}
			urlid = "VIP"+ urlid;
		}
		//飞牛
		else if(url.contains("www.feiniu.com")){
			urlid = url.substring(url.indexOf("feiniu.com")+10);
			if(urlid.contains("/item")){
				urlid = urlid.substring(urlid.indexOf("item/")+5);
			}
			if(urlid.contains("?utm")){
				urlid = urlid.substring(0,urlid.indexOf("?utm"));
			}
			urlid = "FeiNiu" + urlid;
		}
		//天天
		else if(url.contains("tiantian.com")){
			urlid = url.substring(url.indexOf("tiantian.com")+12);
			if(urlid.contains(".html")){
				urlid = urlid.substring(0,urlid.indexOf(".html"));
			}
			if(urlid.contains("cosmetic")){
				urlid = urlid.substring(urlid.indexOf("cosmetic/")+9);
			}
			urlid = "TianTian" + urlid;
		}
		//聚美
		else if(url.contains("jumei.com")){
			urlid = url.substring(url.indexOf("jumei.com")+9);
			if(urlid.contains(".html")){
				urlid = urlid.substring(0,urlid.indexOf(".html"));
			}
			if(urlid.contains("product")){
				urlid = urlid.substring(urlid.indexOf("product_")+8);
			}
			if(urlid.contains("deal")){
				urlid = urlid.substring(urlid.indexOf("deal/")+5);
			}
			if(urlid.contains("p")){
				urlid = urlid.substring(urlid.indexOf("p")+1);
			}
			urlid = "JUMEI" + urlid;
		}
		//聚美global
		else if(url.contains("jumeiglobal.com")){
			urlid = url.substring(url.indexOf("jumeiglobal.com")+15);
			if(urlid.contains(".html")){
				urlid = urlid.substring(0,urlid.indexOf(".html"));
			}
			if(urlid.contains("deal")){
				urlid = urlid.substring(urlid.indexOf("deal/")+5);
			}
			if(urlid.contains("p")){
				urlid = urlid.substring(urlid.indexOf("p")+1);
			}
			if(urlid.contains("t1")){
				urlid = urlid.substring(0,urlid.indexOf("t1"));
			}
			urlid = "JUMEI" + urlid;
		}
		//母婴之家
		else if(url.contains("item.muyingzhijia.com")){
			urlid = url.substring(url.indexOf("muyingzhijia.com/")+17);
			if(urlid.contains(".html")){
				urlid = urlid.substring(0,urlid.indexOf(".html"));
			}
			urlid = "MYZJ" + urlid;
		}
		//爱婴岛
		else if(url.contains("baby.com.cn")){
			urlid = url.substring(url.indexOf("baby.com.cn/")+12);
			if(urlid.contains(".html")){
				urlid = urlid.substring(0,urlid.indexOf(".html"));
			}
			if(urlid.contains("/")){
				urlid = urlid.substring(0,urlid.indexOf("/"));
			}
			if(urlid.contains("product")){
				urlid = urlid.substring(urlid.indexOf("product-")+8);
			}
			urlid = "AYD"  + urlid ;
		}
		//麦乐购
		else if(url.contains("m6go.com")){
			urlid = url.substring(url.indexOf("m6go.com/")+8);
			if(urlid.contains(".html")){
				urlid = urlid.substring(0,urlid.indexOf(".html"));
			}
			if(urlid.contains("product")){
				urlid = urlid.substring(urlid.indexOf("product_")+8);
			}
			if(urlid.contains("_")){
				urlid = urlid.substring(0,urlid.indexOf("_"));
			}
			urlid = "MLG" + urlid ;
		}
		//亲亲宝贝
		else if(url.contains("qinqinbaby.com")){
			urlid = url.substring(url.indexOf("qinqinbaby.com/")+15);
			if(urlid.contains("goods")){
				urlid = urlid.substring(urlid.indexOf("goods/")+6);
			}
			urlid = "QQBB" + urlid ;
		}
		//优一宝贝
		else if(url.contains("u1baby.com")){
			urlid = url.substring(url.indexOf("u1baby.com/")+11);
			if(urlid.contains(".html")){
				urlid = urlid.substring(0,urlid.indexOf(".html"));
			}
			if(urlid.contains("goods")){
				urlid = urlid.substring(urlid.indexOf("goods-")+6);
			}
			urlid = "YYBB" + urlid ;
		}
		//乐蜂网
		else if(url.contains("lefeng.com")){
			urlid = url.substring(url.indexOf("lefeng.com/")+10);
			if(urlid.contains(".html")){
				urlid = urlid.substring(0,urlid.indexOf(".html"));
			}
			if(urlid.contains("product")){
				urlid = urlid.substring(urlid.indexOf("product/")+8);
			}
			urlid = "LF" + urlid ;
		}
		//111
		else if(url.contains("111.com.cn")){
			urlid = url.substring(url.indexOf("111.com.cn")+10);
			if(urlid.contains(".html")){
				urlid = urlid.substring(0,urlid.indexOf(".html"));
			}
			if(urlid.contains("product")){
				urlid = urlid.substring(urlid.indexOf("product/")+8);
			}
			urlid = "YYW" + urlid ;
		}
		//百联
		else if(url.contains("blemall.com")){
			urlid = url.substring(url.indexOf("blemall.com/")+12);
			if(urlid.contains(".html")){
				urlid  = urlid.substring(0,urlid.indexOf(".html"));
			}
			urlid  = "BLW" + urlid ;
		}
		//我买
		else if(url.contains("womai.com")){
			urlid = url.substring(url.indexOf("womai.com")+9);
			if(urlid.contains(".htm")){
				urlid = urlid.substring(0,urlid.indexOf(".htm"));
			}
			if(urlid.contains("Product")){
				urlid = urlid.substring(urlid.indexOf("Product-")+8);
			}
			urlid = "WMW" + urlid ;
		}
		//顺丰
		else if(url.contains("sfbest.com")){
			urlid = url.substring(url.indexOf("sfbest.com")+10);
			if(urlid.contains(".html")){
				urlid =urlid.substring(0,urlid.indexOf(".html"));
			}
			if(urlid.contains("products")){
				urlid = urlid.substring(urlid.indexOf("products/")+9);
			}
			if(urlid.contains("/")){
				urlid = urlid.substring(urlid.indexOf("/")+1);
			}
			urlid = "SF" + urlid;
		}
		//美味77
		else if(url.contains("yummy77.com")){
			urlid = url.substring(url.indexOf("yummy77.com")+11);
			if(urlid.contains(".html")){
				urlid =urlid.substring(0,urlid.indexOf(".html"));
			}
			if(urlid.contains("product")){
				urlid = urlid.substring(urlid.indexOf("product/")+8);
			}
			urlid = "MW" +urlid ;
		}
		//酒仙
		else if(url.contains("jiuxian.com")){
			urlid = url.substring(url.indexOf("jiuxian.com")+11);
			if(urlid.contains(".html")){
				urlid =urlid.substring(0,urlid.indexOf(".html"));
			}
			if(urlid.contains("goods")){
				urlid = urlid.substring(urlid.indexOf("goods-")+6);
			}
			urlid = "JXW" + urlid ;
		}
		//yesmywine
		else if(url.contains("yesmywine.com")){
			urlid = url.substring(url.indexOf("yesmywine.com")+13);
			if(urlid.contains(".html")){
				urlid =urlid.substring(0,urlid.indexOf(".html"));
			}
			if(urlid.contains("goods")){
				urlid = urlid.substring(urlid.indexOf("goods/")+6);
			}
			urlid = "YMJ" + urlid;
		}
		return new String(urlid.trim());

	}
	
	
	public static void main(String[] args) throws Exception{
		System.out.println(evaluate("http://item.jd.com/1310003889.html"));
		System.out.println(evaluate("http://item.m.jd.com/product/1324600.html"));
//		ItemId ii = new ItemId();
//		BufferedReader read = new BufferedReader(new FileReader(new File("E://11.txt")));
//        StringBuffer htmlstr = new StringBuffer();
//		String line = "";
//		while((line = read.readLine()) != null){
//			System.out.println(ii.evaluate(new String(line)));
//		}
	}

}
