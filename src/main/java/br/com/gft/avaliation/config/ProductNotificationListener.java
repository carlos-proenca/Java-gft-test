//package br.com.gft.avaliation.config;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.batch.core.BatchStatus;
//import org.springframework.batch.core.JobExecution;
//import org.springframework.batch.core.listener.JobExecutionListenerSupport;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import br.com.gft.avaliation.service.ProductService;
//
//
//@Component
//public class ProductNotificationListener extends JobExecutionListenerSupport{
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(ProductNotificationListener.class);
//
//    @Autowired
//    private ProductService productService;
//    
//    @Override
//    public void afterJob(final JobExecution jobExecution) {
//        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
//            LOGGER.info("!!! JOB FINISHED! Time to verify the results");
//            productService.searchProducts().forEach(product -> LOGGER.info("Found <" + product + "> in the database."));
//        }
//    }
//}
