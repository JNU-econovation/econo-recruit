package com.econovation.recruitcommon.utils;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Result<T> {
    private final T value;
    private final Throwable error;

    private Result(T value, Throwable error) {
        this.value = value;
        this.error = error;
    }

    public static <T> Result<T> success(T value) {
        return new Result<>(value, null);
    }

    public static <T> Result<T> failure(Throwable error) {
        return new Result<>(null, error);
    }

    public boolean isSuccess() {
        return value != null;
    }

    public boolean isFailure() {
        return error != null;
    }

    public T getValue() {
        return value;
    }

    public Throwable getError() {
        return error;
    }

    public void onSuccess(Consumer<T> consumer) {
        if (isSuccess()) {
            consumer.accept(value);
        }
    }

    public void onFailure(Consumer<Throwable> consumer) {
        if (isFailure()) {
            consumer.accept(error);
        }
    }

    public <R> Result<R> map(Function<T, R> mapper) {
        if (isSuccess()) {
            try {
                return Result.success(mapper.apply(value));
            } catch (Exception e) {
                return Result.failure(e);
            }
        } else {
            return Result.failure(error);
        }
    }

    public Result<T> filter(Predicate<T> predicate) {
        if (isSuccess() && predicate.test(value)) {
            return this;
        } else {
            return null;
        }
    }

    public static <T> Result<T> of(T value) {
        return value == null
                ? Result.failure(new IllegalArgumentException("value is null"))
                : Result.success(value);
    }

    public T orElseThrow(Throwable error) {
        if (isSuccess()) {
            return value;
        } else {
            throw new RuntimeException(error);
        }
    }
}
