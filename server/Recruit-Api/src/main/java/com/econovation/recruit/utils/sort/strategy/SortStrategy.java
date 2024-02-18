package com.econovation.recruit.utils.sort.strategy;

import java.util.Comparator;

public interface SortStrategy<T> extends Comparator<T> {
    int compare(T obj1, T obj2);
}
