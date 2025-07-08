package com.hoffmann.songifyclient.response;

import org.springframework.http.HttpStatus;

public record DeleteSongResponseDto (String message, HttpStatus status) {
}