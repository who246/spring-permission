
package com.demo.common.data.jpa.utlis;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * 
 * 查询辅助类
 * 
 * 
 * 池超凡
 * 
 * 2015年5月19日 下午4:39:22
 * 
 * @version 1.0.0
 *
 */

public class SearchFilter {

	public enum Operator {
		EQ, LIKE, GT, LT, GTE, LTE
	}
	public static final DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	public static final DateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
	public static final String  ALIAS = ".";
	public String fieldName;
	public Object value;
	public Operator operator;
	public String alias;
	public SearchFilter(String fieldName, Operator operator, Object value,String alias) {
		this.fieldName = fieldName;
		setPropertyValue(value.toString());
		this.operator = operator;
		this.alias = alias;
	}
   
	
   

    
    public void setValue(Object value) {
        this.value = value;
    }

 

    /**
	 * searchParams中key的格式为OPERATOR_FIELDNAME
	 */
	public static Map<String, SearchFilter> parse(Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = new HashMap<String, SearchFilter>();

		for (Entry<String, Object> entry : searchParams.entrySet()) {
			// 过滤掉空值
			String key = entry.getKey();
			Object value = entry.getValue();
			if (StringUtils.isBlank((String) value)) {
				continue;
			}

			// 拆分operator与filedAttribute
			String[] names = StringUtils.split(key, "_");
			if (names.length != 2) {
				throw new IllegalArgumentException(key + " is not a valid search filter name");
			}
			String filedName = names[1];
			Operator operator = Operator.valueOf(names[0]);
			String alias = null;
			//如 user.username user表示别名
			if(filedName.indexOf(ALIAS)!= -1){
			    alias = StringUtils.substring(filedName, 0,filedName.indexOf(ALIAS));
			    filedName  = StringUtils.substring(filedName, filedName.indexOf(ALIAS)+1,filedName.length());
			}
			// 创建searchFilter
			SearchFilter filter = new SearchFilter(filedName, operator, value,alias);
			filters.put(key, filter);
		}

		return filters;
	}
	//判断数据类型
	public void setPropertyValue(String value) {
        if(value==null){
            setValue(null);
        }else if (value.contains("-")) {
            try {
                setValue(format2.parse(value));
            } catch (ParseException e) {
               setValue(value);
              
            }
        } 
        else if (value.contains("-") && value.contains(":")) {
            try {
               
                setValue(format1.parse(value));
            } catch (ParseException e) {
                setValue(value);
                
            }
        }
        else if (value.contains(".")) {
            try {
               
                setValue(Double.parseDouble(value));
            } catch (NumberFormatException e) {
                setValue(value);
                
            }
        } else if (value.contains("$")) {
           setValue(value.replace("$", ""));
        } else if (value.contains("true") || value.contains("false")) {
             setValue(value.contains("true") ? Boolean.TRUE : Boolean.FALSE);
        } else {
            try {
                setValue(Integer.parseInt(value));
            } catch (NumberFormatException e) {
                setValue(value);
            }
        }
    }

}
