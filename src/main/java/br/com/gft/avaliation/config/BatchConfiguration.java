package br.com.gft.avaliation.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
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
import org.springframework.core.task.TaskExecutor;
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
	
	@Autowired
	private TaskExecutor taskExecutor;
	
	@Bean
	public Job importProductJob() {
		return jobBuilderFactory.get("importProductJob")
				.flow(this.importProductsDatasource1Step())
				.next(this.importProductsDatasource2Step())
				.next(this.importProductsDatasource3Step())
				.next(this.importProductsDatasource4Step())
				.end()
				.build();
	}
	
	@Bean
	public Step importProductsDatasource1Step() {
		return stepBuilderFactory.get("importProductsDatasource1Step").<Product, Product>chunk(10)
				.reader(readerDatasource1())
				.writer(writer())
				.faultTolerant().skipLimit(120000).skip(DuplicateKeyException.class)
				.taskExecutor(taskExecutor)
				.build();
	}
	
	@Bean
	public Step importProductsDatasource2Step() {
		return stepBuilderFactory.get("importProductsDatasource2Step").<Product, Product>chunk(10)
				.reader(readerDatasource2())
				.writer(writer())
				.faultTolerant().skipLimit(120000).skip(DuplicateKeyException.class)
				.taskExecutor(taskExecutor)
				.build();
	}
	
	@Bean
	public Step importProductsDatasource3Step() {
		return stepBuilderFactory.get("importProductsDatasource3Step").<Product, Product>chunk(10)
				.reader(readerDatasource3())
				.writer(writer())
				.faultTolerant().skipLimit(120000).skip(DuplicateKeyException.class)
				.taskExecutor(taskExecutor)
				.build();
	}

	@Bean
	public Step importProductsDatasource4Step() {
		return stepBuilderFactory.get("importProductsDatasource4Step").<Product, Product>chunk(10)
				.reader(readerDatasource4())
				.writer(writer())
				.faultTolerant().skipLimit(120000).skip(DuplicateKeyException.class)
				.taskExecutor(taskExecutor)
				.build();
	}

	/**
	 * TODO open a ticket to improve JsonReader for contains named arrays as field
	 * 
	 * @param datasourcePath
	 * @return
	 */
	@Bean
	@StepScope
	public FlatFileItemReader<Product> readerDatasource1() {
		return createReader("data/data_1.json");
	}
	
	@Bean
	@StepScope
	public FlatFileItemReader<Product> readerDatasource2() {
		return createReader("data/data_2.json");
	}
	
	@Bean
	@StepScope
	public FlatFileItemReader<Product> readerDatasource3() {
		return createReader("data/data_3.json");
	}
	
	@Bean
	@StepScope
	public FlatFileItemReader<Product> readerDatasource4() {
		return createReader("data/data_4.json");
	}
	
	@Bean
	public LineMapper<Product> lineMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		LineMapper<Product> mapper = new LineMapper<Product>() {
			@Override
			public Product mapLine(String line, int lineNumber) throws Exception {
				System.out.println("the line issss " + line);
				return objectMapper.readValue(prepateLine(line), Product.class);
			}

			private String prepateLine(String line) {
				return line.replace("{\"data\":[", "").replace("$", "");
			}
		};

		return mapper;
	}

	@Bean
	public JdbcBatchItemWriter<Product> writer() {
		return new JdbcBatchItemWriterBuilder<Product>()
				.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>()).sql(SAVE_PRODUCT)
				.dataSource(this.dataSource).build();
	}
	
	private FlatFileItemReader<Product> createReader(String datasourcePath) {
		return new FlatFileItemReaderBuilder<Product>().name("dataItemReaderFor"+datasourcePath)
				.resource(new ClassPathResource(datasourcePath)).lineMapper(lineMapper())
				.fieldSetMapper(new BeanWrapperFieldSetMapper<Product>() {
					{
						setTargetType(Product.class);
					}
				}).build();
	}
}