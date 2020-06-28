//package br.com.gft.avaliation.config;
//
//import java.math.BigDecimal;
//
//import org.springframework.batch.item.ItemProcessor;
//
//import br.com.gft.avaliation.model.Product;
//
//public class ProductProcessor implements ItemProcessor<Product, Product>{
//
//    @Override
//    public Product process(final Product Product) {
//        final BigDecimal volt = Product.getVolt();
//        final double time = Product.getTime();
//
//        final Product processedProduct = new Product();
//        processedProduct.setVolt(volt);
//        processedProduct.setTime(time);
//        return processedProduct;
//    }
//}
