package com.idea.tag.util;

import java.util.ArrayList;

import org.apache.hadoop.io.Text;

/**
 * @author haoming@126.com
 */

public class UDFDomain{
		
	protected static ArrayList<String> nTLDsArray= new ArrayList<String>();	//national top-level domainnames
	protected static ArrayList<String> iTDsArray= new ArrayList<String>();	//international top-level domain names
	static{
		nTLDsArray.add("cn");
		nTLDsArray.add("ad");
		nTLDsArray.add("ae");
		nTLDsArray.add("af");
		nTLDsArray.add("ag");
		nTLDsArray.add("ai");
		nTLDsArray.add("al");
		nTLDsArray.add("am");
		nTLDsArray.add("an");
		nTLDsArray.add("ao");
		nTLDsArray.add("aq");
		nTLDsArray.add("ar");
		nTLDsArray.add("as");
		nTLDsArray.add("at");
		nTLDsArray.add("au");
		nTLDsArray.add("aw");
		nTLDsArray.add("az");
		nTLDsArray.add("ba");
		nTLDsArray.add("bb");
		nTLDsArray.add("bd");
		nTLDsArray.add("be");
		nTLDsArray.add("bf");
		nTLDsArray.add("bg");
		nTLDsArray.add("bh");
		nTLDsArray.add("bi");
		nTLDsArray.add("bj");
		nTLDsArray.add("bm");
		nTLDsArray.add("bn");
		nTLDsArray.add("bo");
		nTLDsArray.add("br");
		nTLDsArray.add("bs");
		nTLDsArray.add("bt");
		nTLDsArray.add("bv");
		nTLDsArray.add("bw");
		nTLDsArray.add("by");
		nTLDsArray.add("bz");
		nTLDsArray.add("ca");
		nTLDsArray.add("cc");
		nTLDsArray.add("cf");
		nTLDsArray.add("cg");
		nTLDsArray.add("ch");
		nTLDsArray.add("ci");
		nTLDsArray.add("ck");
		nTLDsArray.add("cl");
		nTLDsArray.add("cm");
		nTLDsArray.add("co");
		nTLDsArray.add("cq");
		nTLDsArray.add("cr");
		nTLDsArray.add("cu");
		nTLDsArray.add("cv");
		nTLDsArray.add("cx");
		nTLDsArray.add("cy");
		nTLDsArray.add("cz");
		nTLDsArray.add("de");
		nTLDsArray.add("dj");
		nTLDsArray.add("dk");
		nTLDsArray.add("dm");
		nTLDsArray.add("do");
		nTLDsArray.add("dz");
		nTLDsArray.add("ec");
		nTLDsArray.add("ee");
		nTLDsArray.add("eg");
		nTLDsArray.add("eh");
		nTLDsArray.add("es");
		nTLDsArray.add("et");
		nTLDsArray.add("ev");
		nTLDsArray.add("fi");
		nTLDsArray.add("fj");
		nTLDsArray.add("fk");
		nTLDsArray.add("fm");
		nTLDsArray.add("fo");
		nTLDsArray.add("fr");
		nTLDsArray.add("ga");
		nTLDsArray.add("gb");
		nTLDsArray.add("gd");
		nTLDsArray.add("ge");
		nTLDsArray.add("gf");
		nTLDsArray.add("gh");
		nTLDsArray.add("gi");
		nTLDsArray.add("gl");
		nTLDsArray.add("gm");
		nTLDsArray.add("gn");
		nTLDsArray.add("gp");
		nTLDsArray.add("gr");
		nTLDsArray.add("gt");
		nTLDsArray.add("gu");
		nTLDsArray.add("gw");
		nTLDsArray.add("gy");
		nTLDsArray.add("hk");
		nTLDsArray.add("hm");
		nTLDsArray.add("hn");
		nTLDsArray.add("hr");
		nTLDsArray.add("ht");
		nTLDsArray.add("hu");
		nTLDsArray.add("id");
		nTLDsArray.add("ie");
		nTLDsArray.add("il");
		nTLDsArray.add("in");
		nTLDsArray.add("io");
		nTLDsArray.add("iq");
		nTLDsArray.add("ir");
		nTLDsArray.add("is");
		nTLDsArray.add("it");
		nTLDsArray.add("jm");
		nTLDsArray.add("jo");
		nTLDsArray.add("jp");
		nTLDsArray.add("ke");
		nTLDsArray.add("kg");
		nTLDsArray.add("kh");
		nTLDsArray.add("ki");
		nTLDsArray.add("km");
		nTLDsArray.add("kn");
		nTLDsArray.add("kp");
		nTLDsArray.add("kr");
		nTLDsArray.add("kw");
		nTLDsArray.add("ky");
		nTLDsArray.add("kz");
		nTLDsArray.add("la");
		nTLDsArray.add("lb");
		nTLDsArray.add("lc");
		nTLDsArray.add("li");
		nTLDsArray.add("lk");
		nTLDsArray.add("lr");
		nTLDsArray.add("ls");
		nTLDsArray.add("lt");
		nTLDsArray.add("lu");
		nTLDsArray.add("lv");
		nTLDsArray.add("ly");
		nTLDsArray.add("ma");
		nTLDsArray.add("mc");
		nTLDsArray.add("md");
		nTLDsArray.add("mg");
		nTLDsArray.add("mh");
		nTLDsArray.add("ml");
		nTLDsArray.add("mm");
		nTLDsArray.add("mn");
		nTLDsArray.add("mo");
		nTLDsArray.add("mp");
		nTLDsArray.add("mq");
		nTLDsArray.add("mr");
		nTLDsArray.add("ms");
		nTLDsArray.add("mt");
		nTLDsArray.add("mv");
		nTLDsArray.add("mw");
		nTLDsArray.add("mx");
		nTLDsArray.add("my");
		nTLDsArray.add("mz");
		nTLDsArray.add("na");
		nTLDsArray.add("nc");
		nTLDsArray.add("ne");
		nTLDsArray.add("nf");
		nTLDsArray.add("ng");
		nTLDsArray.add("ni");
		nTLDsArray.add("nl");
		nTLDsArray.add("no");
		nTLDsArray.add("np");
		nTLDsArray.add("nr");
		nTLDsArray.add("nt");
		nTLDsArray.add("nu");
		nTLDsArray.add("nz");
		nTLDsArray.add("om");
		nTLDsArray.add("pa");
		nTLDsArray.add("pe");
		nTLDsArray.add("pf");
		nTLDsArray.add("pg");
		nTLDsArray.add("ph");
		nTLDsArray.add("pk");
		nTLDsArray.add("pl");
		nTLDsArray.add("pm");
		nTLDsArray.add("pn");
		nTLDsArray.add("pr");
		nTLDsArray.add("pt");
		nTLDsArray.add("pw");
		nTLDsArray.add("py");
		nTLDsArray.add("qa");
		nTLDsArray.add("re");
		nTLDsArray.add("ro");
		nTLDsArray.add("ru");
		nTLDsArray.add("rw");
		nTLDsArray.add("sa");
		nTLDsArray.add("sb");
		nTLDsArray.add("sc");
		nTLDsArray.add("sd");
		nTLDsArray.add("se");
		nTLDsArray.add("sg");
		nTLDsArray.add("sh");
		nTLDsArray.add("si");
		nTLDsArray.add("sj");
		nTLDsArray.add("sk");
		nTLDsArray.add("sl");
		nTLDsArray.add("sm");
		nTLDsArray.add("sn");
		nTLDsArray.add("so");
		nTLDsArray.add("sr");
		nTLDsArray.add("st");
		nTLDsArray.add("su");
		nTLDsArray.add("sy");
		nTLDsArray.add("sz");
		nTLDsArray.add("tc");
		nTLDsArray.add("td");
		nTLDsArray.add("tf");
		nTLDsArray.add("tg");
		nTLDsArray.add("th");
		nTLDsArray.add("tj");
		nTLDsArray.add("tk");
		nTLDsArray.add("tm");
		nTLDsArray.add("tn");
		nTLDsArray.add("to");
		nTLDsArray.add("tp");
		nTLDsArray.add("tr");
		nTLDsArray.add("tt");
		nTLDsArray.add("tv");
		nTLDsArray.add("tw");
		nTLDsArray.add("tz");
		nTLDsArray.add("ua");
		nTLDsArray.add("ug");
		nTLDsArray.add("uk");
		nTLDsArray.add("us");
		nTLDsArray.add("uy");
		nTLDsArray.add("va");
		nTLDsArray.add("vcst");
		nTLDsArray.add("ve");
		nTLDsArray.add("vg");
		nTLDsArray.add("vn");
		nTLDsArray.add("vu");
		nTLDsArray.add("wf");
		nTLDsArray.add("ws");
		nTLDsArray.add("ye");
		nTLDsArray.add("yu");
		nTLDsArray.add("za");
		nTLDsArray.add("zm");
		nTLDsArray.add("zr");
		nTLDsArray.add("zw");
		
	}
	static{
		iTDsArray.add("com");
		iTDsArray.add("net");
		iTDsArray.add("org");
		iTDsArray.add("gov");
		iTDsArray.add("edu");
		iTDsArray.add("mil");
		iTDsArray.add("name");
		iTDsArray.add("info");
		iTDsArray.add("mobi");
		iTDsArray.add("pro");
		iTDsArray.add("travel");
		iTDsArray.add("museum");
		iTDsArray.add("int");
		iTDsArray.add("aero");
		iTDsArray.add("post");
		iTDsArray.add("rec");
		iTDsArray.add("asia");
		iTDsArray.add("co");
		iTDsArray.add("xxx");
		iTDsArray.add("cc");
		iTDsArray.add("tv");
		iTDsArray.add("ac");
		iTDsArray.add("biz");
		iTDsArray.add("int");
	}

	public static String getDomain(String host) {
		String domain="";
		String []arrayDomain=null;
		
		if(null==host) {
			return "";
		}else{
			host = host.toLowerCase();
			//判断host是否为ip地址
			if(isIp(host)){
				return host;
			}
			//
			arrayDomain = host.split("\\.");

			int arrayNum = arrayDomain.length-1;
			if(arrayNum > 0) {
				if(nTLDsArray.contains(arrayDomain[arrayNum])){
					if(iTDsArray.contains(arrayDomain[arrayNum-1])){
						if(arrayNum < 2){
							return host;
						}
						domain=arrayDomain[arrayNum-2]+"."+arrayDomain[arrayNum-1]+"."+arrayDomain[arrayNum];
					}else{
						domain=arrayDomain[arrayNum-1]+"."+arrayDomain[arrayNum];
					}
				} else{
					domain=arrayDomain[arrayNum-1]+"."+arrayDomain[arrayNum];
				}
			}
		}
		return domain;
	}
	
	private static boolean isIp(String host) {
		return host.matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}");
	}

	public Text evaluate(Text url_text) {
		
		if (null == url_text) {
			return new Text();
		}

		return new Text(getDomain(UDFHost.getHost(url_text.toString())));
	}
	
//	public static void main(String[] args) {
//		System.out.println(getDomain("sadasda3.qq.com"));
//	}
}