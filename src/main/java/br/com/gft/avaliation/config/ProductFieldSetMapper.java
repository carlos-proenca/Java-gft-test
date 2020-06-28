//package br.com.gft.avaliation.config;
//
//import org.springframework.batch.item.file.mapping.FieldSetMapper;
//import org.springframework.batch.item.file.transform.FieldSet;
//import org.springframework.stereotype.Component;
//
//import br.com.gft.avaliation.model.Product;
//
//@Component
//public class ProductFieldSetMapper implements FieldSetMapper<Product> {
//
//    @Override
//    public Product mapFieldSet(FieldSet fieldSet) {
//    	
//    	System.out.println("the field mapper executed" + fieldSet);
//    	System.out.println("the product name is "+fieldSet.readString("name"));
//        final Product product = new Product();
//
//        product.setName(fieldSet.readString("name"));
//        product.setPrice(fieldSet.readBigDecimal("price"));
//        product.setQuantity(fieldSet.readInt("time"));
//        
//        return product;
//
//    }
//}
