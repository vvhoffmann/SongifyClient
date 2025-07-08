package com.hoffmann.songifyclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@FeignClient(value = "songify-client", url = "${songify-server.service.url}")
public interface SongifyClient {

    @RequestMapping(method = RequestMethod.GET, value = "/songs")
    AllSongsResponse makeGetRequest();

    @RequestMapping(method = RequestMethod.GET, value = "/songs/{id}")
    SingleSongResponse makeGetByIdRequest(@PathVariable Integer id);

    @PostMapping(path = "/songs")
    SingleSongResponse makePostRequest(@RequestBody Song song) ;

    @DeleteMapping(path = "/songs/{id}")
    SingleSongResponse makeDeleteRequest(@PathVariable Integer id);

    @PutMapping(path = "/songs/{id}")
    SingleSongResponse makePutRequest(@PathVariable Integer id, @RequestBody Song song);

    @PatchMapping(path = "/songs/{id}")
    SingleSongResponse makePatchRequest(@PathVariable Integer id, @RequestBody Song song);
}