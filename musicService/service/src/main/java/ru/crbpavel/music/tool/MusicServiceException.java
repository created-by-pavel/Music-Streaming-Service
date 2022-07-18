package ru.crbpavel.music.tool;

public class MusicServiceException extends RuntimeException {

    public MusicServiceException() {

    }

    public MusicServiceException(String message) {
        super(message);
    }

    public MusicServiceException(String message, Exception exception) {
        super(message, exception);
    }

}
