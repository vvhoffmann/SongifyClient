package com.hoffmann.songifyclient.songifyproxy;

import com.hoffmann.songifyclient.request.CreateSongRequestDto;
import com.hoffmann.songifyclient.request.PartiallyUpdateSongRequestDto;
import com.hoffmann.songifyclient.request.UpdateSongRequestDto;
import com.hoffmann.songifyclient.response.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@FeignClient(value = "songify-client", url = "${songify-server.service.url}")
public interface SongifyClient {

    @RequestMapping(method = RequestMethod.GET, value = "/songs")
    GetAllSongsResponseDto makeGetRequest();

    @RequestMapping(method = RequestMethod.GET, value = "/songs/{id}")
    GetSingleSongResponseDto makeGetByIdRequest(@PathVariable Integer id);

    @PostMapping(path = "/songs")
    CreatedSongResponseDto makePostRequest(@RequestBody CreateSongRequestDto song) ;

    @DeleteMapping(path = "/songs/{id}")
    DeleteSongResponseDto makeDeleteSongByIdRequest(@PathVariable Integer id);

    @PutMapping(path = "/songs/{id}")
    UpdatedSongResponseDto makePutRequest(@PathVariable Integer id, @RequestBody UpdateSongRequestDto song);

    @PatchMapping(path = "/songs/{id}")
    UpdatedSongResponseDto makePatchRequest(@PathVariable Integer id, @RequestBody PartiallyUpdateSongRequestDto song);
}