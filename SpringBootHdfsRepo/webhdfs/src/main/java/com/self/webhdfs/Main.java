package com.self.webhdfs;

import java.net.URI;
import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hdfs.web.WebHdfsFileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class Main implements CommandLineRunner {

	@Autowired
	private Configuration configuration;

	@Override
	public void run(String... strings) throws Exception {
		Path readPath = new Path("/geeks/README.txt");
		WebHdfsFileSystem webhdfs = new WebHdfsFileSystem();
		webhdfs.initialize(new URI("webhdfs://namenode:50070"), configuration);
		log.debug("WebHdfsFileSystem file system init");
		webhdfs.createNewFile(readPath);
		webhdfs.copyFromLocalFile(new Path("/home/admin/JavaBinaries/newFile.txt"), readPath);
		FSDataInputStream inputStream = webhdfs.open(readPath);
		String out = IOUtils.toString(inputStream, "UTF-8");
		webhdfs.close();
		log.debug("Read file {}", out);
	}

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}
}