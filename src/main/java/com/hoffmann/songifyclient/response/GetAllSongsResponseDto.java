package com.hoffmann.songifyclient;

import java.util.Map;

public record AllSongsResponse(Map<String, Song> songs) {
}
