package com.xcompany.xproject.auth.server.controller;

public final class DeviceAlreadyExistException extends RuntimeException {

    private static final long serialVersionUID = 5861310537366287163L;

    public DeviceAlreadyExistException() {
        super();
    }

    public DeviceAlreadyExistException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public DeviceAlreadyExistException(final String message) {
        super(message);
    }

    public DeviceAlreadyExistException(final Throwable cause) {
        super(cause);
    }

}
