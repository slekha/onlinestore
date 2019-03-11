package com.techinterview.onlinestore.service;

import com.techinterview.onlinestore.domain.BackPack;
import com.techinterview.onlinestore.domain.Product;
import com.techinterview.onlinestore.domain.SmartPhone;
import com.techinterview.onlinestore.formatter.IProductFormatter;
import com.techinterview.onlinestore.formatter.ProductStringFormatter;

import java.util.ArrayList;
import java.util.List;

/**
 * Implement the function productListToString that does conversion of provided
 * list of products to text representation of this list in this way. - Result
 * string is \n - separated string. E.g.
 *
 * product 1 details product 2 details ... product 3 details
 *
 * - Each line contains details of one product - Product description line should
 * look like this: Product name (product GUID), product attribute 1 name:product
 * attribute1 value; ... product attribute n name:product attributen value; For
 * example. List contains BackPack definition: BackPack { guid: 111-222-333
 * name: Cool Backpack maxContentWeight: 15 color: "Black" capacity: 20 } This
 * becka pakc description string should look like this: Cool Backpack
 * (111-222-333), maxContentWeight: 15, color: "Black", capacity: 20
 *
 * Keep in mind these requirements: - String reorientation can be modified in
 * future. - There is a possibility to support multiply ways to convert list of
 * products to string. E.g. it it is possible that in future you will need to
 * implement support of different formats of outpust string (e.g. json instead
 * of \n-separated string). The basic idea is to make your current
 * implementation flexible and modifiable in future.
 *
 * You can use any build system to build the sources (maven, gralde, ant). You
 * can use any 3rd party libs in your application. Any java version (>=8). Code
 * must be tested (framework is up to you).
 * 
 * PLEASE KEEP IN MIND THAT IT CAN BE THOUSANDS OF PRODUCT IMPLEMENTATIONS, NOT
 * JUST 2!!! INSTANCE OF SOLUTION IS BAD AND IS NOT GOING TO WORK IN A REAL
 * WORLD!
 * 
 * Product classes (base class and implementations) can be changed to resolve
 * the task, Any new methods, properties or anything else can be added there.
 * 
 * If you are familiar with Git, please do work in a separate branch and create
 * a pull request with your changes.
 */
public class ProductListProcessor {

	/**
	 * Make String representation of provided product list.
	 * 
	 * @param products list of the products that needs to be converted to String
	 * @return String representation of the provided list.
	 */
	public String productListToString(List<Product> products) {

		if (products == null || products.isEmpty())
			return null;

		IProductFormatter<String> formatter = new ProductStringFormatter();

		StringBuffer sb = new StringBuffer();
		int count = 0;
		for (Product product : products) {
			String formattedDetail = null;

			try {
				formattedDetail = formatter.format(product);
			} catch (Exception e) {
				System.out.println(" Exception when formatting product : " + product.getGuid() + e.getMessage());
			}

			if (formattedDetail != null) {
				count++;
				sb.append("product").append(ProductStringFormatter.EMPTY_SPACE).append(count)
						.append(ProductStringFormatter.EMPTY_SPACE).append(formattedDetail).append("\n");
			}
		}

		return sb.toString();
	}

	public static void main(String[] args) {
		List<Product> productList = new ArrayList<Product>();

		BackPack bp1 = new BackPack("111-222-333", "BP1");

		bp1.setCapacity(20);
		bp1.setColor("blue");
		bp1.setMaxContentWeight(100);
		productList.add(bp1);

		SmartPhone sp1 = new SmartPhone("000-999-888", "SP1");
		productList.add(sp1);

		ProductListProcessor pl = new ProductListProcessor();
		pl.productListToString(productList);

		String formattedProductList = pl.productListToString(productList);
		System.out.println(formattedProductList);
	}
}
