package ma.s.gcm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BureauEtudeApplication {
	public static void main(String[] args) {
		SpringApplication.run(BureauEtudeApplication.class, args);
	}
	
	
	/*@Scheduled(fixedDelay = 1000)
	public void delayTask() throws InterruptedException {
        System.out.println("Second  " +  new Date().getSeconds());
        Thread.sleep(2000);
    }
*/
}
