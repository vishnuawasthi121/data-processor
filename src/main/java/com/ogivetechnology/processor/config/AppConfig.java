/*
 * package com.ogivetechnology.processor.config;
 * 
 * import java.io.File; import java.time.Duration;
 * 
 * import javax.annotation.PreDestroy;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.beans.factory.annotation.Value; import
 * org.springframework.boot.devtools.filewatch.FileSystemWatcher; import
 * org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.Configuration;
 * 
 * import com.ogivetechnology.processor.service.WatcherService;
 * //https://betterprogramming.pub/3-methods-for-monitoring-log-file-changes-in-
 * java-e434b96046df
 * 
 * @Configuration public class AppConfig {
 * 
 * @Value("${QRcode.file.location}") private String fileLocation;
 * 
 * @Autowired private WatcherService watcherService;
 * 
 * @Bean public FileSystemWatcher fileSystemWatcher() { // @param pollInterval
 * the amount of time to wait between checking for changes // @param quietPeriod
 * the amount of time required after a change has been // detected to
 * FileSystemWatcher fileSystemWatcher = new FileSystemWatcher(false,
 * Duration.ofMillis(10000L), Duration.ofMillis(5000L));
 * 
 * fileSystemWatcher.addSourceDirectory(new File(fileLocation));
 * fileSystemWatcher.addListener(watcherService); fileSystemWatcher.start();
 * System.out.println("started fileSystemWatcher"); return fileSystemWatcher; }
 * 
 * @PreDestroy public void onDestroy() throws Exception {
 * System.out.println("AppConfig    -> onDestroy");
 * //fileSystemWatcher().stop(); } }
 */