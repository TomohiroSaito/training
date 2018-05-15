package sample.di.dataaccess;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import sample.di.business.service.ProductDao;
import sample.di.business.valueobject.Product;

@Component
@Scope("singleton")
public class ProductDaoImpl implements ProductDao {
	public Product findProduct(String name) {
		return new Product(name, 100);
	}
}
