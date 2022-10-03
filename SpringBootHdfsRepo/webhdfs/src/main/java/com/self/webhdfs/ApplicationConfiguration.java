package com.self.webhdfs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class ApplicationConfiguration {
    @Value("${com.domain.app.hadoop.fs-uri}")
    private String hdfsUri;

    @Value("${com.domain.app.hadoop.user}")
    private String user;

    @Value("${com.domain.app.hadoop.hive.jdbc-uri}")
    private String hiveUri;


    @Bean
    public org.apache.hadoop.conf.Configuration hadoopConfiguration() {
    	org.apache.hadoop.conf.Configuration configuration = new org.apache.hadoop.conf.Configuration();
    	configuration.setInt("dfs.replication",2);
        configuration.set("fs.defaultFS", hdfsUri);
        configuration.set("fs.hdfs.impl",org.apache.hadoop.hdfs.DistributedFileSystem.class.getName());
        configuration.set("fs.file.impl",org.apache.hadoop.fs.LocalFileSystem.class.getName());

        return configuration;
    }


	/*
	 * @Bean public HiveTemplate hiveTemplate() { return new HiveTemplate(() -> {
	 * final SimpleDriverDataSource dataSource = new SimpleDriverDataSource(new
	 * HiveDriver(), hiveUri); return new HiveClient(dataSource); }); }
	 */
}
