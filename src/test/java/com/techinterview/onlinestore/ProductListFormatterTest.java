package com.techinterview.onlinestore;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.techinterview.onlinestore.domain.BackPack;
import com.techinterview.onlinestore.domain.Product;
import com.techinterview.onlinestore.domain.SmartPhone;
import com.techinterview.onlinestore.service.ProductListProcessor;

public class ProductListFormatterTest {

	// Test case for empty product list
	@Test
	public void testEmptyProductListFormatting() {
		List<Product> productList = new ArrayList<Product>();

		ProductListProcessor productListProcessor = new ProductListProcessor();
		String actual = productListProcessor.productListToString(productList);
		String expected = null;

		assertEquals(expected, actual);
	}

	// Test case for null product list
	@Test
	public void testNullProductListFormatting() {
		List<Product> productList = null;

		ProductListProcessor productListProcessor = new ProductListProcessor();
		String actual = productListProcessor.productListToString(productList);
		String expected = null;

		assertEquals(expected, actual);
	}

	// Test case for product list with Backpack
	@Test
	public void testBackPackProductListFormatting() {
		List<Product> productList = new ArrayList<Product>();

		BackPack backpack = new BackPack("111-222-333", "Cool Backpack");
		backpack.setCapacity(20);
		backpack.setColor("Black");
		backpack.setMaxContentWeight(15);
		productList.add(backpack);

		backpack = new BackPack("111-222-444", "New Backpack");
		backpack.setCapacity(52);
		backpack.setColor("Blue");
		backpack.setMaxContentWeight(25);
		productList.add(backpack);

		ProductListProcessor productListProcessor = new ProductListProcessor();
		String actual = productListProcessor.productListToString(productList);

		StringBuffer sb = new StringBuffer();
		sb.append("product 1 Cool Backpack (111-222-333), maxContentWeight: 15.0, color: \"Black\", capacity: 20.0\n");
		sb.append("product 2 New Backpack (111-222-444), maxContentWeight: 25.0, color: \"Blue\", capacity: 52.0\n");
		String expected = sb.toString();

		assertEquals(expected, actual);
	}

	// Test case for product list with Backpack having partial data
	@Test
	public void testBackPackProductWithPartialDataListFormatting() {
		List<Product> productList = new ArrayList<Product>();

		BackPack backpack = new BackPack("111-222-333", "Cool Backpack");
		backpack.setCapacity(20);
		productList.add(backpack);

		backpack = new BackPack("111-222-444", "New Backpack");
		backpack.setCapacity(52);
		backpack.setColor("Blue");
		backpack.setMaxContentWeight(25);
		productList.add(backpack);

		ProductListProcessor productListProcessor = new ProductListProcessor();
		String actual = productListProcessor.productListToString(productList);

		StringBuffer sb = new StringBuffer();
		sb.append("product 1 Cool Backpack (111-222-333), maxContentWeight: 0.0, color: null, capacity: 20.0\n");
		sb.append("product 2 New Backpack (111-222-444), maxContentWeight: 25.0, color: \"Blue\", capacity: 52.0\n");
		String expected = sb.toString();

		assertEquals(expected, actual);
	}

	// Test case for product list with SmartPhone
	@Test
	public void testSmartPhoneProductListFormatting() {
		List<Product> productList = new ArrayList<Product>();

		SmartPhone smartPhone = new SmartPhone("111-222-333", "IPhone 10");
		smartPhone.setColor("Black");
		smartPhone.setManufacturer("Apple");
		smartPhone.setNumberOfCPUs(3);
		smartPhone.setRamSize(1024);
		smartPhone.setScreenResolution("10x10");
		productList.add(smartPhone);

		smartPhone = new SmartPhone("111-222-444", "IPhone 7");
		smartPhone.setColor("Red");
		smartPhone.setManufacturer("Apple");
		smartPhone.setNumberOfCPUs(3);
		smartPhone.setRamSize(1024);
		smartPhone.setScreenResolution("10x10");
		productList.add(smartPhone);

		ProductListProcessor productListProcessor = new ProductListProcessor();
		String actual = productListProcessor.productListToString(productList);

		StringBuffer sb = new StringBuffer();
		sb.append(
				"product 1 IPhone 10 (111-222-333), numberOfCPUs: 3, color: \"Black\", ramSize: 1024.0, screenResolution: \"10x10\", manufacturer: \"Apple\"\n");
		sb.append(
				"product 2 IPhone 7 (111-222-444), numberOfCPUs: 3, color: \"Red\", ramSize: 1024.0, screenResolution: \"10x10\", manufacturer: \"Apple\"\n");
		String expected = sb.toString();

		assertEquals(expected, actual);
	}

	// Test case for product list with both Backpack and SmartPhone
	@Test
	public void testAllProductListFormatting() {
		List<Product> productList = new ArrayList<Product>();

		BackPack backpack = new BackPack("111-222-333", "Cool Backpack");
		backpack.setCapacity(20);
		backpack.setColor("Black");
		backpack.setMaxContentWeight(15);
		productList.add(backpack);

		SmartPhone smartPhone = new SmartPhone("111-222-333", "IPhone 10");
		smartPhone.setColor("Black");
		smartPhone.setManufacturer("Apple");
		smartPhone.setNumberOfCPUs(3);
		smartPhone.setRamSize(1024);
		smartPhone.setScreenResolution("10x10");
		productList.add(smartPhone);

		ProductListProcessor productListProcessor = new ProductListProcessor();
		String actual = productListProcessor.productListToString(productList);

		StringBuffer sb = new StringBuffer();
		sb.append("product 1 Cool Backpack (111-222-333), maxContentWeight: 15.0, color: \"Black\", capacity: 20.0\n");
		sb.append(
				"product 2 IPhone 10 (111-222-333), numberOfCPUs: 3, color: \"Black\", ramSize: 1024.0, screenResolution: \"10x10\", manufacturer: \"Apple\"\n");
		String expected = sb.toString();

		assertEquals(expected, actual);
	}
}
