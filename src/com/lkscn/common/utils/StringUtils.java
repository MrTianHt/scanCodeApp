package com.lkscn.common.utils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串操作工具类
 * @author Mr Tian
 */
public class StringUtils {
	private final static int[] li_SecPosValue = { 1601, 1637, 1833, 2078, 2274,  
        2302, 2433, 2594, 2787, 3106, 3212, 3472, 3635, 3722, 3730, 3858,  
        4027, 4086, 4390, 4558, 4684, 4925, 5249, 5590 };  
	private final static String[] lc_FirstLetter = { "a", "b", "c", "d", "e",  
        "f", "g", "h", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",  
        "t", "w", "x", "y", "z" }; 

	/**
	 * 判断是否为空
	 * @param str 输入字符串参数
	 */
	public static  boolean  isEmpty(String str){
		if(str == null || "".equals(str.trim()) || "null".equalsIgnoreCase(str))
			return true;
		else return false;
	}
	
	
	/**
     * 半角转全角
     * @param input 输入字符串参数
     * @return 全角字符串.
     */
    public static String convert2DoubleByte(String input) {
         char c[] = input.toCharArray();
         for(int i = 0; i < c.length; i++) {
              if (c[i] == ' ')  c[i] = '\u3000';
              else if (c[i] < '\177') 
                 c[i] = (char) (c[i] + 65248);
         }
         return new String(c);
    }

    /**
     * 全角转半角
     * @param input 输入字符串参数
     * @return 半角字符串
     */
    public static String convertSingleByte(String input) {
		if (StringUtils.isEmpty(input))
			return "";
		char c[] = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == '\u3000')
				c[i] = ' ';
			else if (c[i] > '\uFF00' && c[i] < '\uFF5F')
				c[i] = (char) (c[i] - 65248);
		}
		return new String(c);
    }
    
    /**
     * 是否包含标点符号
     * @return
     */
    public static boolean contanisPunctuation(String sourceStr){
    	if(isEmpty(sourceStr)) return false;
    	return (sourceStr.indexOf("，") !=-1 || sourceStr.indexOf("、") !=-1 
    			|| sourceStr.indexOf("；") !=-1 || sourceStr.indexOf("。") !=-1);
    }
    
    /**
     * 判定字符是英文字符
     * @param str
     * @return
     */
    public static boolean charIsEn(String str){
    	if(StringUtils.isEmpty(str)) return false;
    	char curChar = str.charAt(0);
		if (!(curChar >= 'A' && curChar <= 'Z')
				&& !(curChar >= 'a' && curChar <= 'z')) {
			return false;
		}
		return true;
    }
    
    /**
     * 字符是中文字符
     * @param str
     * @return
     */
    public static boolean charIsChinese(String str){
    	if(StringUtils.isEmpty(str)) return false;
    	char curChar = str.charAt(0);
    	if ((curChar >= 0x4e00) && (curChar <= 0x9fbb)) {
			return true;
		}
    	return false;
    }
    
    public static boolean charIsNumeric(String str){
    	if(str.length()>4){
    		char firstChar = str.charAt(0);
    		for (int i = 0;i<4;i++){ 
    			if(!Character.isDigit(firstChar)) return false; 
    		}
    	}else{
    		if(StringUtils.containsChinese(str)) return false;
    	}
    	return true;
    } 
    
    /**
     * 首字母英文大写字母
     * @param sourceStr
     * @return
     */
    public static boolean charStartWithEn(String sourceStr){
    	if(StringUtils.isEmpty(sourceStr)) return false;
    	char startChar = sourceStr.charAt(0);
    	if(startChar>='A'  &&  startChar<='Z'){
    		return true;
    	}
    	return false;
    }
    /**
     * 是否为节
     * @param sourceStr
     * @return
     */
    public static boolean charStartWithNum(String sourceStr){
    	if(StringUtils.isEmpty(sourceStr)) return false;
    	char startChar = sourceStr.charAt(0);
    	if(startChar>'0'  &&  startChar<='9'){
    		return true;
    	}
    	return false;
    }
    
    /*public static String preprocessPagecontent(String sourceContent){
    	if(StringUtils.isEmpty(sourceContent)) return "";
    	return replaceFontChar(sourceContent);
    }
    */
    
    
    
    //判断表示是否全为英文
	public static boolean strIsEnglish(String word) {
		for (int i = 0; i < word.length(); i++) {
			if (!(word.charAt(i) >= 'A' && word.charAt(i) <= 'Z')
					&& !(word.charAt(i) >= 'a' && word.charAt(i) <= 'z')) {
				return false;
			}
		}
		return true;
	}

	/**
     * 去除字符串中空格
     * @param substring
     * @return
     */
	public static String removeSpace(String sourceStr) {
		if(StringUtils.isEmpty(sourceStr)) return "";
		String afterStr = sourceStr.replaceAll(" ", "").replaceAll(" ", "");//去除普通空格和非换行空格，第二个为非换行空格
		String lastStr = afterStr.replaceAll("　", "").replaceAll("","");
		return lastStr;
	}
	
	/**
	 * 把字符串中的非换行空格替换为普通空格
	 * @param sourceStr
	 * @return
	 */
	public static String replaceNonLineSpace(String sourceStr){
		if(StringUtils.isEmpty(sourceStr)) return "";
		String afterStr = sourceStr.replaceAll(" ", " ");//将非换行空格转换为普通空格
		return afterStr;
	}
	
	/**
	 * 判断字符串中是否包含全角字符
	 * @param sourceStr 源字符串
	 */
	public static boolean hasDoubleByte(String sourceStr){
		String afterStr = removeSpace(sourceStr);
		boolean hasDouble = false;
		 char c[] = afterStr.toCharArray();
         for(int i = 0; i < c.length; i++) {
             if (c[i] > '\uFF00' && c[i] < '\uFF5F'){
            	 hasDouble = true; break;
             }
          }
		return hasDouble;
	}
	
	/**
	 * 字符串中包含指定字符数
	 * @param sourceStr 源字符串
	 * @param c 包含的字符
	 */
	public static int containsChar(String sourceStr,char c){
		char[] cArray = sourceStr.toCharArray();
		int containNum = 0;
		for(int i=0;i<cArray.length;i++){
			if(cArray[i] == c) containNum ++;
		}
		return containNum;
	}
	
	/**
	 * 替换内容中包含map中key的值为value
	 * @param sourceStr
	 * @param map
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String replaceModifyString(String sourceStr,Map map){
		if(StringUtils.isEmpty(sourceStr)) return "";
		if(map == null || (map != null && map.isEmpty())) return sourceStr;
		String afterStr = sourceStr;
		java.util.Iterator it = map.entrySet().iterator();
		while(it.hasNext()){
			java.util.Map.Entry entry = (java.util.Map.Entry)it.next();
//			afterStr = afterStr.replaceAll(entry.getKey().toString(),entry.getValue().toString());
			afterStr = afterStr.replace(entry.getKey().toString(),entry.getValue().toString());
		}
		return afterStr;
	}
	
	/**
	 * 判断字符串中是否含有日期格式形式字符串
	 * @param resourceStr
	 */
	public static boolean strContainsDate(String resourceStr){
		if(StringUtils.isEmpty(resourceStr)) return false;
		String dateRegex = ".*((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|" +
				"(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|" +
				"(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|" +
				"(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|" +
				"([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|" +
				"([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|" +
				"(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8])))))).*";
		 Pattern pat = Pattern.compile(dateRegex);
         Matcher mat = pat.matcher(resourceStr);
         boolean dateType = mat.matches();
         return dateType;  
	}
	
	/** 字符串为日期格式 **/
	public static boolean isDateStr(String resourceStr){
		if(StringUtils.isEmpty(resourceStr)) return false;
		String dateRegex = "((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|" +
				"(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|" +
				"(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|" +
				"(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|" +
				"([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|" +
				"([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|" +
				"(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";  
		 Pattern pat = Pattern.compile(dateRegex);    
         Matcher mat = pat.matcher(resourceStr);    
         boolean dateType = mat.matches();  
         return dateType;  
	}
	
	
	
	/**
	 * 替换内容中包含的全角字符及特殊字母“犌犅”等
	 * @param sourceStr
	 * @return
	 */
	public static String replaceFontChar(String sourceStr){
		if(StringUtils.isEmpty(sourceStr)) return "";
		String afterStr = sourceStr.replaceAll("犪","a").replaceAll("犃","A").replaceAll("犫","b").replaceAll("犅","B")
				.replaceAll("犮","c").replaceAll("犆","C").replace("犇","D").replaceAll("犱","d").replaceAll("犲","e").replaceAll("犈","E")
				.replaceAll("犳","f").replaceAll("犉","F").replaceAll("犵","g").replaceAll("犌","G").replaceAll("犺","h").replaceAll("犎","H")
				.replaceAll("犻","i").replaceAll("犐","I").replaceAll("犼","j").replaceAll("犑","J").replaceAll("犽","k").replaceAll("犓","K").replaceAll("犾","l").replaceAll("犔","L")
				.replaceAll("犿","m").replaceAll("犕","M").replaceAll("狀","n").replaceAll("犖","N").replaceAll("狅","o").replaceAll("犗","O")
				.replaceAll("狆","p").replaceAll("犘","P").replaceAll("狇","q").replaceAll("犙", "Q").replaceAll("狉","r").replaceAll("犚","R").replaceAll("狊","s").replaceAll("犛","S")
				.replaceAll("狋","t").replaceAll("犜","T").replaceAll("赝", "/T").replaceAll("狌","u").replaceAll("犝","U").replaceAll("狏","v").replaceAll("犞","V")
				.replaceAll("犠","W").replaceAll("狑","w").replaceAll("犡","X").replace("狓","x").replaceAll("狔","y").replaceAll("犢","Y")
				.replaceAll("狕","z").replaceAll("犣","Z").replace("—","-");
		return afterStr;
	}
	
	public static boolean containsChinese(String sourceStr){
		if(StringUtils.isEmpty(sourceStr)) return false;
		char[] charArray = sourceStr.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
		     if ((charArray[i] >= 0x4e00) && (charArray[i] <= 0x9fbb)) {
			  //Java判断一个字符串是否有中文是利用Unicode编码来判断，
			  //  因为中文的编码区间为：0x4e00--0x9fbb
			    return true;
			 }
		}
		return false;
	}
	
	public static String autoCompeleteVal(String _sourceVal){
		if(StringUtils.isEmpty(_sourceVal)) return "0000";
		while (_sourceVal.length() < 4)
			_sourceVal = "0" + _sourceVal;
	    return _sourceVal;
	}
	
	public static String[] splitSepctor(String sourceStr){
		if(StringUtils.isEmpty(sourceStr)) return null;
		String[] splitCharArray = new String[]{"、",",",";"};
		String afterStr = sourceStr;
		for(String splitChar:splitCharArray){
			afterStr = afterStr.replaceAll(splitChar,"_"); }
		String[] nameStrArray = afterStr.split("_");
		return nameStrArray;
	}
	
	
	/**
	 * 去除字符串中包含的html标签
	 * @param sourceStr
	 */
	public static String removeHtmlTag(String sourceStr){
		if(StringUtils.isEmpty(sourceStr)) return "";
		String htmlTagReg = "<[^>]+>"; //HTML标签的正则表达式
		Pattern htmlTagPattern = Pattern.compile(htmlTagReg,Pattern.CASE_INSENSITIVE);      
        Matcher htmlTagMatcher = htmlTagPattern.matcher(sourceStr);      
        sourceStr = htmlTagMatcher.replaceAll(""); // 过滤html标签     
		return sourceStr;
	}
	
	/**
	 * 标准号格式化
	 */
	public static String standardNoFormat(String standardNo){
		//强制标准   GB 18565-2001
		//推荐标准   GB_T 31351-2008   GB_Z 17624.2-2013
		//有确认年代号的标准   GB 50325-2010(2013)
		//组织机构代号里有括号的，括号用半角括号：    GB 50325-2010(2013)
		//组织机构代号里有括号的，括号用半角括号     JJF(纺织) 016-2010
		//其他说明    SJ/T 234/1-2013 -->SJ_T 234.1-2013       ISO 123-2-2013-->ISO 123.2-2013
		
		
		//年代号    -2008    -2010(2013)
		String dateNumber=standardNo.substring(standardNo.lastIndexOf("-"));
		//不含年代号    GB_T 31351    JJF(纺织) 016
		String containsNoDateNumber=standardNo.substring(0,standardNo.lastIndexOf("-"));
		//标准类型  GB  GB/T  SJ/T  JJF(纺织)
		String standardType=containsNoDateNumber.substring(0,containsNoDateNumber.indexOf(" "));
		//去掉标准类型和年代号部分   50325   234/1
		String containsNostandardType=containsNoDateNumber.substring(containsNoDateNumber.indexOf(" "));
		standardType=standardType.replaceAll("_", "/");
		containsNostandardType=containsNostandardType.replaceAll("/", ".").replaceAll("-", ".");
		standardNo=standardType+containsNostandardType+dateNumber;
		return standardNo;
	}
	
	
	/**
	 * 计算字符串的长度，然后均匀的填充空格。（结构化加工的封面发布单位用到）
	 * @return 计算出填充空格的像素
	 */
	public static String strLenFillSpace(String str){
		StringBuilder sb=new StringBuilder();
		/** 文字的个数 **/
        int chineseCount = 0;
        for (int i = 0; i < str.length(); i++) {  
//            char c = str.charAt(i);  
            chineseCount++;  
        }
        /** 空格的个数 **/
        int spaceCount=chineseCount-1;
        /** 计算出所有文字所占的像素大小     
         * 25是静态文件中css设置的文字大小**/
        int chinesePX=chineseCount*25;
        /** 计算出div中去掉中文所占大小剩余部分像素
         * 700是静态文件中css设置的div大小**/
        int surplusSpacePX=650-chinesePX;
        /** 每一个空格的大小，可能为小数**/
        double everySpaceSize=surplusSpacePX/spaceCount;
        for (int i = 0; i < str.length(); i++) {  
            char c = str.charAt(i);
            if(i==0){
            	sb.append(c);
            }else{
            	sb.append("<span style=\"margin-left:"+everySpaceSize+"px;\">"+c+"</span>");
            }
        }
        return sb.toString();
	}
	
	public static String extractKeywords(String contentData){
		if(StringUtils.isEmpty(contentData)) return "";
		List<String> keywordList = new ArrayList<String>();//LkscnAnalyzer.getInStance().extractNameWord(contentData);
		Set<String> keywordSet = new LinkedHashSet<String>(keywordList);
		StringBuilder keywordBuilder = new StringBuilder();
		if(keywordList !=null && keywordList.size()>0){
			for(String keyword:keywordSet){
				if(keyword.length()<=2) continue;
				keywordBuilder.append(keyword).append(";");
			}
		}
		return keywordBuilder.toString();
	}
	
	 /** 
     * 取得给定汉字的首字母,即声母 
     * @param chinese 给定的汉字 
     * @return 给定汉字的声母 
     */ 
	public static String getFirstLetter(String chinese) {  
        if (chinese == null || chinese.trim().length() == 0) {  
            return "";  
        }  
        chinese = conversionStr(chinese, "GB2312", "ISO8859-1");  
  
        if (chinese.length() > 1) // 判断是不是汉字  
        {  
            int li_SectorCode = (int) chinese.charAt(0); // 汉字区码  
            int li_PositionCode = (int) chinese.charAt(1); // 汉字位码  
            li_SectorCode = li_SectorCode - 160;  
            li_PositionCode = li_PositionCode - 160;  
            int li_SecPosCode = li_SectorCode * 100 + li_PositionCode; // 汉字区位码  
            if (li_SecPosCode > 1600 && li_SecPosCode < 5590) {  
                for (int i = 0; i < 23; i++) {  
                    if (li_SecPosCode >= li_SecPosValue[i]  
                            && li_SecPosCode < li_SecPosValue[i + 1]) {  
                        chinese = lc_FirstLetter[i];  
                        break;  
                    }  
                }  
            } else // 非汉字字符,如图形符号或ASCII码  
            {  
                chinese = conversionStr(chinese, "ISO8859-1", "GB2312");  
                chinese = chinese.substring(0, 1);  
            }  
        }  
        return chinese;  
    } 
	/** 
     * 取得给定汉字串的首字母串,即声母串 
     * @param str 给定汉字串 
     * @return 声母串 
     */  
    public static String getAllFirstLetter(String str) {  
        if (str == null || str.trim().length() == 0) {  
            return "";  
        }  
  
        String _str = "";  
        for (int i = 0; i < str.length(); i++) {  
            _str = _str + getFirstLetter(str.substring(i, i + 1));  
        }  
  
        return _str;  
    } 
	 /** 
     * 字符串编码转换 
     * @param str 要转换编码的字符串 
     * @param charsetName 原来的编码 
     * @param toCharsetName 转换后的编码 
     * @return 经过编码转换后的字符串 
     */  
    private static String conversionStr(String str, String charsetName,String toCharsetName) {  
        try {  
            str = new String(str.getBytes(charsetName), toCharsetName);  
        } catch (UnsupportedEncodingException ex) {  
            System.out.println("字符串编码转换异常：" + ex.getMessage());  
        }  
        return str;  
    }
    
    /** 
     * 取得给定字符串的第一个字母的位置 
     * @param str 给定字符串
     * @return 位置标号
     */
    public static int getPlaceByFirstLetter(String str) {
        if (str == null || str.trim().length() == 0) {
            return 0;
        }  
  
        char[] chars = str.toCharArray();
        int _str = 0;
		for(int i=0; i<chars.length; i++) {
            if((chars[i] >= 'a' && chars[i] <= 'z') || (chars[i] >= 'A' && chars[i] <= 'Z')){
            	_str = i;
            	break;
            }
        }
  
        return _str;
    }
    
    /**
     * 判断字符串是否与TreeMap中的值存在模糊匹配
     * @param catalogTreeM
     * @param sourceStr
     * @return
     */
    @SuppressWarnings("rawtypes")
	public static boolean treeMapValStartWithStr(TreeMap catalogTreeM,String sourceStr){
    	if(StringUtils.isEmpty(sourceStr) || catalogTreeM == null || (catalogTreeM != null && catalogTreeM.isEmpty())) return false;
    	
    	Iterator testPrints = catalogTreeM.entrySet().iterator();
  		 while(testPrints.hasNext()){
  			java.util.Map.Entry nextObj = (java.util.Map.Entry)testPrints.next();
  			if(nextObj.getValue().toString().startsWith(sourceStr)){
  				return true;
  			}
  		 }
    	return false;
    }
    
    /** 
     * 取得给定字符串中的某个字符的个数 
     * @param str 给定字符串
     * @param chr 指定的字符
     * @return 指定字符的个数
     */
    public static int getCountByLetter(String str,String chr) {
        if (str == null || str.trim().length() == 0) {
            return 0;
        }  
  
        int cnt = 0;
        int offset = 0;
        while((offset = str.indexOf(chr, offset)) != -1){
            offset = offset + chr.length();
            cnt++;
        }
  
        return cnt;
    }
    
    public static String strArr2str(String[] arr){
    	StringBuffer sb = new StringBuffer();
    	for(int i = 0; i < arr.length; i++){
    		sb.append(",").append(arr[i]);
    	}
    	return sb.substring(1).toString();
    }
    
    public static String floatArr2str(float[] arr){
    	StringBuffer sb = new StringBuffer();
    	for(int i = 0; i < arr.length; i++){
    		sb.append(",").append(arr[i]);
    	}
    	return sb.substring(1).toString();
    }
    
    public static String intArr2str(int[] arr){
    	StringBuffer sb = new StringBuffer();
    	for(int i = 0; i < arr.length; i++){
    		sb.append(",").append(arr[i]);
    	}
    	return sb.substring(1).toString();
    }
    
    public static String byteArr2str(byte[] arr){
    	StringBuffer sb = new StringBuffer();
    	for(int i = 0; i < arr.length; i++){
    		sb.append(",").append(arr[i]);
    	}
    	return sb.substring(1).toString();
    }
    
    /**
     * 根据坐标获取页码
     * @param lineContentPos		坐标
     * @return	pageNum
     */
    public static int getPageNumByPos(String lineContentPos){
    	if(!StringUtils.isEmpty(lineContentPos))
    		return Integer.parseInt(lineContentPos.split(",")[0].toString());
    	else
    		return 0;
    }
    /**
     * 根据坐标获取Y坐标
     * @param lineContentPos		坐标
     * @return	yAxis
     */
    public static double getYAxisPos(String lineContentPos){
    	double yAxis = 0.0;
    	if(!StringUtils.isEmpty(lineContentPos))
    		yAxis = Double.parseDouble(lineContentPos.split(",")[1].toString());
    	return yAxis;
    }
    
    public static float getYAxisPosFloat(String lineContentPos){
    	float yAxis = 0.0f;
    	if(!StringUtils.isEmpty(lineContentPos))
    		yAxis = Float.parseFloat(lineContentPos.split(",")[1].toString());
    	return yAxis;
    }
    /**
     * 根据坐标获取X坐标
     * @param lineContentPos		坐标
     * @return	xAxis
     */
    public static double getXAxisPos(String lineContentPos){
    	double xAxis = 0.0;
    	if(!StringUtils.isEmpty(lineContentPos))
    		xAxis = Double.parseDouble(lineContentPos.split(",")[2].toString());
    	return xAxis;
    }

    /**
     * 根据传入的章节号判断该章节号是章、节、附录章还是附录节
     * @param serialNum		如  5			  5.3		   附录A			A.1
     * @return				chapter		section		addendum	addendumSection
     */
    public static String getChapterTypeBySerialNum(String serialNum){
    	String chapterType = "";
    	String tempSerialNumStr = serialNum.replaceAll("[.]", "");
		if (serialNum.startsWith("附录")) { // 附录章中包含的表
			chapterType = "addendum";
		} else if (tempSerialNumStr.matches("^[0-9]*$") && serialNum.indexOf(".") != -1) {
			chapterType = "section";
		} else if (tempSerialNumStr.matches("^[0-9]*$") && serialNum.indexOf(".") == -1) { // 章包含的表
			chapterType = "chapter";
		} else if (StringUtils.charStartWithEn(tempSerialNumStr)
				&& (serialNum.indexOf(".") != -1 || serialNum.length() > 1)) { // 附录节包含的表
			chapterType = "addendumSection";
		}
		return chapterType;
    }
    
    /**
     * 字符串长度不够前边补零
     * @param str	字符串	1
     * @param chart 补充字符串    0
     * @param strLength 返回字符串长度	4
     * @return	返回字符串		0001
     */
    public static String addZeroForLeftNum(String str,String chart, int strLength) {
        int strLen = str.length();
        StringBuffer sb = null;
         while (strLen < strLength) {
               sb = new StringBuffer();
               sb.append(chart).append(str);// 左补0
               str = sb.toString();
               strLen = str.length();
         }
        return str;
    }
    /**
     * 字符串长度不够后边补零
     * @param str	字符串	1
     * @param chart 补充字符串    0
     * @param strLength 返回字符串长度	4
     * @return	返回字符串		0001
     */
    public static String addZeroForRightNum(String str,String chart, int strLength) {
        int strLen = str.length();
        StringBuffer sb = null;
         while (strLen < strLength) {
               sb = new StringBuffer();
             sb.append(str).append(chart);//右补0
               str = sb.toString();
               strLen = str.length();
         }
        return str;
    }
	
	/**
	 * 
	 * 相似度比较
	 * @param strA
	 * @param strB
	 * @return
	 */
	public static double SimilarDegree(String strA, String strB) {
		String newStrA = removeSign(strA);
		String newStrB = removeSign(strB);
		int temp = Math.max(newStrA.length(), newStrB.length());
		int temp2 = longestCommonSubstring(newStrA, newStrB).length();
		return temp2 * 1.0 / temp;

	}

	private static String removeSign(String str) {
		StringBuffer sb = new StringBuffer();
		for (char item : str.toCharArray())
			if (charReg(item)) {
				// System.out.println("--"+item);
				sb.append(item);
			}
		return sb.toString();

	}

	private static boolean charReg(char charValue) {
		return (charValue >= 0x4E00 && charValue <= 0X9FA5)
		|| (charValue >= 'a' && charValue <= 'z')
		|| (charValue >= 'A' && charValue <= 'Z')
		|| (charValue >= '0' && charValue <= '9');
	}

	private static String longestCommonSubstring(String strA, String strB) {
		char[] chars_strA = strA.toCharArray();
		char[] chars_strB = strB.toCharArray();
		int m = chars_strA.length;
		int n = chars_strB.length;
		int[][] matrix = new int[m + 1][n + 1];
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (chars_strA[i - 1] == chars_strB[j - 1])
					matrix[i][j] = matrix[i - 1][j - 1] + 1;
				else
					matrix[i][j] = Math.max(matrix[i][j - 1], matrix[i - 1][j]);
			}
		}
		char[] result = new char[matrix[m][n]];
		int currentIndex = result.length - 1;
		while (matrix[m][n] != 0) {
			if (matrix[m][n] == matrix[m][n - 1])
				n--;
			else if (matrix[m][n] == matrix[m - 1][n])
				m--;
			else {
				result[currentIndex] = chars_strA[m - 1];
				currentIndex--;
				n--;
				m--;
			}
		}
		return new String(result);
	}
	
	public static String filterSpecialCharOfXml(String txt){
    	String res = "";
    	for(int i = 0; i < txt.length(); ++i){
    		char ch = txt.charAt(i);
    		if(	Character.isDefined(ch) &&
    			ch!= '&' && ch != '<' && ch != '>' && 
    			!Character.isHighSurrogate(ch) &&
    			!Character.isISOControl(ch) &&
    			!Character.isLowSurrogate(ch)
    		   ){
    			res = res + ch;
    		}
    	}
    	return res;
    }
	
	public static String replaceSpecialCharOfXml(String txt){
    	String res = "";
    	for(int i = 0; i < txt.length(); ++i){
    		char ch = txt.charAt(i);
    		if(	Character.isDefined(ch) && ch== '&' ){
    			res = res + "&amp;";
    		}else if(Character.isDefined(ch) && ch== '>' ){
    			res = res + "&gt";
    		}else if(Character.isDefined(ch) && ch== '<' ){
    			res = res + "&lt;";
    		}else{
    			res = res + ch;
    		}
    	}
    	return res;
    }

	public static String removeSpecialChar(String standardNo) {
		standardNo = convertSingleByte(standardNo);
		char[] chars = standardNo.toCharArray();
		String _str = "";
		for (int i = 0; i < chars.length; i++) {
//			System.out.println(chars[i]);
			if ((chars[i] >= 'a' && chars[i] <= 'z') || (chars[i] >= 'A' && chars[i] <= 'Z'))
				_str += chars[i];
			if(Character.isDigit(chars[i]))
				_str += chars[i];
			if(chars[i] == '-' || chars[i] == '_' || chars[i] == '.' || chars[i] == '/' || chars[i] =='(' || chars[i] == ')')
				_str += chars[i];
			if((chars[i] >= 0x4e00) && (chars[i] <= 0x9fbb)){
				_str += chars[i];
			}
		}
		return _str;
	}
	
	/**
	 * 获取指标聚类列的内容
	 * @param clusterContent		横向吸液高度(成品层)≥
	 * @return	横向吸液高度
	 */
	public static String getClusterContent(String clusterContent){
		String clusterName = "";
		if(!isEmpty(clusterContent)){
			clusterName = clusterContent.replace("≥", "").replace("≤", "").replace("＞", "").replace("＞", "").replace("=", "");
			clusterName = clusterName.replace("（", "(").replace("）", ")");
			clusterName = clusterName.replace("/%", "");
			if(clusterName.indexOf("(") != -1)
				clusterName = clusterName.substring(0,clusterName.indexOf("("));
		}
		return clusterName;
	}
	
	/** 
     * 根据Map中的value获取key值 
     * @param map 章节目录树  005.001.001.000.000.000.000,5.1.1XXX.....
     * @param str 5.1.1XXX
     * @return map中value为str的key值。用于从章节目录TreeMap中获取排序码。 005.001.001.000.000.000.000
     */
    @SuppressWarnings({ "rawtypes" })
    public static String getKeyByValueForMap(Map map,String str){
    	if(isEmpty(str))
			return "";
		String retStr = "";
		if(map == null || (map != null && map.isEmpty())) return retStr;
		
		java.util.Iterator it = null;			
		if(map != null && map.size()>0){
			it = map.entrySet().iterator();
			while(it.hasNext()){
				java.util.Map.Entry entry = (java.util.Map.Entry)it.next();
				if(str.equals(entry.getValue().toString())){
					retStr = entry.getKey().toString();
					break;
				}
			}
		}
		if(it != null){
			it.remove();
			it = null;
		}
		return retStr;
	}
    
    /** 
     * 根据章节目录排序码获取章节号 
     * @param str 005.001.001.000.000.000.000
     * @return 转换后的章节号。  5.1.1
     */
    public static String getSerialNumByOrderNum(String str){
    	if(isEmpty(str))
			return "";
		String retStr = "";
		
		String[] tempStr = str.split("\\.");
		boolean flag = false;
		for(int i=tempStr.length-1;i>=0;i--){
			if(i==0){
				try {
					if(!flag){
						retStr = String.valueOf(Integer.parseInt(tempStr[i]));
					}else{
						retStr = String.valueOf(Integer.parseInt(tempStr[i])) + "." + retStr;
					}
				} catch (NumberFormatException e) {
					if(!flag){
						retStr = tempStr[i].replace("0", "");
					}else{
						retStr = tempStr[i].replace("0", "") + "." + retStr;
					}
				}
				continue;
			}
			if(Integer.parseInt(tempStr[i])==0 && !flag){
				continue;
			}else{
				if(!flag){
					retStr = String.valueOf(Integer.parseInt(tempStr[i]));
					flag = true;
				}else{
					retStr = String.valueOf(Integer.parseInt(tempStr[i])) + "." + retStr;
				}
			}
		}
		return retStr;
	}
}
