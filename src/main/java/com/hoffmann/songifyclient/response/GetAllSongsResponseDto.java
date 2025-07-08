package com.hoffmann.songifyclient.response;

import com.hoffmann.songifyclient.model.SongEntity;

import java.util.Map;

public record GetAllSongsResponseDto(Map<String, SongEntity> songs) {
}