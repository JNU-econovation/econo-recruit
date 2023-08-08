import java.util.function.Consumer;

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
}
