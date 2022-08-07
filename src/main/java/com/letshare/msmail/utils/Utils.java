package com.letshare.msmail.utils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/**
 * @author Joao Victor
 * @version 1.0
 * @since 07/08/2022
 */
public class Utils {

    public static List<Long> longStream(Long start, Long end) {
        return LongStream.rangeClosed(start, end)
                .boxed().collect(Collectors.toList());
    }

}
