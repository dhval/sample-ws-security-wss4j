package wss4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WSS4JDemo {

    static {
        System.setProperty("javax.net.debug", "none");
    }

    private static final Logger LOG = LoggerFactory.getLogger(WSS4JDemo.class);

    public static void main(String[] args) {
        SpringApplication.run(WSS4JDemo.class);
    }

    @Autowired
    WSClient client;

    @Value("${client.ws.srcXML}")
    String srcXML;

    @Bean
    CommandLineRunner lookup(@Autowired WSClient wsclient) {
        return args -> {
            String response = client.doPost(srcXML);
            LOG.info("\n\n" + response + "\n");
        };
    }

}
