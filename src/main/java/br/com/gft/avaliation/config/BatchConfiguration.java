package br.com.gft.avaliation.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.DuplicateKeyException;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.gft.avaliation.model.Product;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

	private static final String SAVE_PRODUCT = "INSERT INTO product (\"name\", price, quantity, industry, origin, \"type\")"
			+ "VALUES(:name, :price, :quantity, :industry, :origin, :type)";

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	private DataSource dataSource;

	@Bean
	public FlatFileItemReader<Product> reader() {
		return new FlatFileItemReaderBuilder<Product>().name("dataItemReader")
				.resource(new ClassPathResource("data/data_1.json")).lineMapper(lineMapper())
				.fieldSetMapper(new BeanWrapperFieldSetMapper<Product>() {
					{
						setTargetType(Product.class);
					}
				}).build();
	}

	@Bean
	public LineMapper<Product> lineMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		LineMapper<Product> mapper = new LineMapper<Product>() {

			@Override
			public Product mapLine(String line, int lineNumber) throws Exception {
				System.out.println("the line issss " + line);
				line = line.replace("{\"data\":[", "");
				line = line.replace("$", "");
				Product product = objectMapper.readValue(line, Product.class);
				return product;
			}
		};

		return mapper;

//    	final DefaultLineMapper<Product> defaultLineMapper = new DefaultLineMapper<>();
//        defaultLineMapper.setFieldSetMapper(new ProductFieldSetMapper());
//        return defaultLineMapper;
	}

	@Bean
	public JdbcBatchItemWriter<Product> writer() {
		return new JdbcBatchItemWriterBuilder<Product>()
				.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>()).sql(SAVE_PRODUCT)
				.dataSource(this.dataSource).build();
	}

	@Bean
	public Job importProductJob() {
		return jobBuilderFactory.get("importProductJob")
				// .listener(listener)
				.start(this.importProductsStep()).build();
	}

	@Bean
	public Step importProductsStep() {
		return stepBuilderFactory.get("importProductsStep").<Product, Product>chunk(10).reader(reader())
				.writer(writer()).faultTolerant().skipLimit(12000).skip(DuplicateKeyException.class).build();
	}

//	private PlatformTransactionManager transactionManager() {
//		return new ResourcelessTransactionManager();
//	}
//
//	private JobRepository createJobRepository() throws Exception {
//		JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
//		factory.setDataSource(dataSource);
//		factory.setTransactionManager(transactionManager());
//		factory.setIsolationLevelForCreate("ISOLATION_READ_COMMITTED");
//		factory.setTablePrefix("SOMETHING.BATCH_");
//		factory.setDatabaseType("POSTGRES");
//		factory.setMaxVarCharLength(1000);
//		return factory.getObject();
//	}

//	@Bean
//	public JobLauncher jobLauncher() throws Exception {
//		SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
//		jobLauncher.setTaskExecutor(new SimpleAsyncTaskExecutor());
////		jobLauncher.setJobRepository(createJobRepository());
//		return jobLauncher;
//	}
}