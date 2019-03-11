package com.techinterview.onlinestore.formatter;

import java.util.Formatter;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;

import com.techinterview.onlinestore.domain.Product;
import com.techinterview.onlinestore.exception.ProductStringFormattingException;

/**
 * This custom formatter for objects of type Product formats objects in the
 * following format. Product name (product GUID), product attribute 1
 * name:product attribute1 value; ... product attribute n name:product attribute
 * value; Example format : Cool Backpack (111-222-333), maxContentWeight: 15.0,
 * color: "Black", capacity: 20.0 Numeric values will retain its type during
 * formatting i.e double will be printed with decimal and integer without
 * decimal
 */
public class ProductStringFormatter implements IProductFormatter<String> {
	public static final String EMPTY_SPACE = " ";

	public String format(Product product) throws ProductStringFormattingException {
		String formattedOutput = null;
		if (product == null)
			return formattedOutput;

		StringBuffer stringBuffer = new StringBuffer();
		Formatter formatter = new Formatter(stringBuffer);
		try {
			// propertyMap is created using apache PropertyUtils and contains all properties
			// of the object
			// It also contains the class name property
			Map<String, Object> propertyMap = PropertyUtils.describe(product);

			// name and guid are static formatting.
			stringBuffer.append(propertyMap.get("name") + " (" + propertyMap.get("guid") + "),");

			// remove static properties and the class name property prior to formatting
			// dynamic properties.
			propertyMap.remove("name");
			propertyMap.remove("guid");
			propertyMap.remove("class");

			// iterate through all other properties available in object and format output
			// using property name and property value
			Iterator<?> it = propertyMap.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<String, Object> pair = (Map.Entry<String, Object>) it.next();
				String propertyName = pair.getKey();
				Object propertyValue = pair.getValue();

				// for property value of type String , enclose with double quotes
				if (propertyValue != null && propertyValue instanceof String) {
					propertyValue = "\"" + (String) propertyValue + "\"";
				}

				formatter.format(" %1$1s: %2$1s,", propertyName, propertyValue);
			}
			// remove the last comma delimiter
			formattedOutput = StringUtils.chop(stringBuffer.toString());

		} catch (Exception e) {
			throw new ProductStringFormattingException(e.getMessage());
		} finally {
			formatter.close();
		}
		return formattedOutput;
	}

}
