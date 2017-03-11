package pl.movie.rental.config;

import java.util.Properties;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@EnableJpaRepositories("pl.movie.rental.repository")
public class PersistanceConfiguration {
	@Bean
	public DriverManagerDataSource dataSource() {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setDriverClassName("org.hsqldb.jdbcDriver");
		// driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
		// driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/movie_rental");
		driverManagerDataSource.setUrl("jdbc:hsqldb:mem:movie-rental");
		// driverManagerDataSource.setUrl("jdbc:hsqldb:file:spring-hibernate");
		driverManagerDataSource.setUsername("SA");
		driverManagerDataSource.setPassword("");
		return driverManagerDataSource;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource());
		entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
		entityManagerFactoryBean.setPackagesToScan("pl.movie.rental.model");
		entityManagerFactoryBean.setJpaProperties(hibProperties());
		return entityManagerFactoryBean;
	}

	private Properties hibProperties() {
		Properties props = new Properties();
		props.put("hibernate.show_sql", true);
		props.put("hibernate.hbm2ddl.auto", "create-drop"); // or update

		// MySQL
		// props.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");

		// HSQL
		props.put("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
		props.put("hibernate.hbm2ddl.import_files", "data/data.sql");
		return props;
	}

	@Bean
	public JpaTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return transactionManager;
	}

}
