package com.techinterview.onlinestore.formatter;

import com.techinterview.onlinestore.domain.Product;

public interface IProductFormatter<T> {
	public T format(Product product) throws Exception;
}
