package com.hoffmann.songifyclient.response;

import com.hoffmann.songifyclient.model.SongEntity;

public record GetSingleSongResponseDto(SongEntity song) {
}