package com.hoffmann.songifyclient;

import com.hoffmann.songifyclient.request.CreateSongRequestDto;
import com.hoffmann.songifyclient.request.UpdateSongRequestDto;
import com.hoffmann.songifyclient.songifyproxy.SongifyClient;
import feign.FeignException;
import feign.RetryableException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@EnableFeignClients
@Log4j2
public class SongifyClientApplication {

    private final SongifyClient songifyClient;

    @Autowired
    public SongifyClientApplication(SongifyClient songifyClient) {
        this.songifyClient = songifyClient;
    }

    public static void main(String[] args) {
        SpringApplication.run(SongifyClientApplication.class, args);
    }

    @EventListener(ApplicationStartedEvent.class)
    public void makeRequest() {
        try {
            log.info(songifyClient.makeGetRequest());
            log.info(songifyClient.makeGetByIdRequest(2));
            log.info(songifyClient.makeGetByIdRequest(3));
            log.info(songifyClient.makeDeleteSongByIdRequest(2));
            log.info(songifyClient.makeGetRequest());

            log.info(songifyClient.makePutRequest(3, new UpdateSongRequestDto("llaall", "nirvana")));
            log.info(songifyClient.makeGetRequest());

            log.info(songifyClient.makePostRequest(new CreateSongRequestDto("hello", "bebe")));
            log.info(songifyClient.makeGetRequest());


        } catch (FeignException.FeignClientException feignException) {
            log.error("client exception: " + feignException.status());
        } catch (FeignException.FeignServerException feignException) {
            System.out.println("server exception: " + feignException.status());
        } catch (RetryableException retryableException) {
            System.out.println("retryable exception " + retryableException.getMessage());
        } catch (FeignException feignException) {
            System.out.println(feignException.getMessage());
            System.out.println(feignException.status());
        }
    }
}