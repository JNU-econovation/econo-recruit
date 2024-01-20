package com.econovation.recruitcommon.utils;

import com.econovation.recruitcommon.exception.GlobalErrorCode;
import com.econovation.recruitcommon.exception.RecruitCodeException;
import io.vavr.control.Either;
import java.util.Optional;

public class FpUtils {
    public static <T> Either<RecruitCodeException, T> toEither(Optional<T> optional) {
        return optional.map(Either::<RecruitCodeException, T>right).orElseGet(() -> Either.left(new RecruitCodeException(
            GlobalErrorCode.INTERNAL_SERVER_ERROR)));
    }
}
