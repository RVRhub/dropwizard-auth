package com.test.auth;

import com.test.auth.utilities.LoggingUtility;

import com.test.auth.special.Function;

public abstract class AbstractController<E> {

    public final static class Result<T> {
        public enum Status {
            OK,
            ERROR
        }

        public final String message;
        public final Status status;
        public final Long duration;
        public final T payload;

        public static <T> Result<T> success(final T payload, final Long duration) {
            return new Result<>(Status.OK, null, payload, duration);
        }

        public static <T> Result<T> error(final String message, final Long duration) {
            return new Result<>(Status.ERROR, message, null, duration);
        }

        public static <T> Result<T> error(final String message) {
            return error(message, null);
        }

        private Result(final Status status, final String msg, final T payload, final Long duration) {
            this.status   = status;
            this.message  = msg;
            this.payload  = payload;
            this.duration = duration;
        }
    }

    protected final <T> Result<T> run(final Function<T> function) {
        try {
            final Long timeStarted = System.currentTimeMillis();
            final T result = function.run();
            final Long timeFinished = System.currentTimeMillis();

            return Result.success(result, timeFinished - timeStarted);
        } catch (final Throwable throwable) {
            LoggingUtility.exception("Exception", throwable);
            return Result.error(getReason(throwable));
        }
    }

    private String getReason(final Throwable exception) {
        Throwable reason = exception;
        while (reason.getCause() != null)
            reason = reason.getCause();

        return reason.getMessage();
    }
}
