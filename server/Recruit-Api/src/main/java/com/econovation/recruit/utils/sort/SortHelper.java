package com.econovation.recruit.utils.sort;

import com.econovation.recruit.utils.sort.strategy.NameAscendingSortingStrategy;
import com.econovation.recruit.utils.sort.strategy.NewestSortingStrategy;
import com.econovation.recruit.utils.sort.strategy.ObjectiveSortingStrategy;
import com.econovation.recruit.utils.sort.strategy.SortStrategy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class SortHelper<T> {
    SortStrategy<T> sortStrategy;
    Map<String, SortStrategy<T>> sortingStrategies;

    public SortHelper() {
        this.sortingStrategies = new HashMap<>();
        init();
    }

    private void init() {
        sortingStrategies = new HashMap<>();
        addSortingStrategy("Name", new NameAscendingSortingStrategy());
        addSortingStrategy("Newest", new NewestSortingStrategy());
        addSortingStrategy("Objective", new ObjectiveSortingStrategy());
    }

    public void addSortingStrategy(String strategyName, SortStrategy<?> comparator) {
        sortingStrategies.put(strategyName, (SortStrategy<T>) comparator);
    }

    public void setSortStrategy(String sort) {
        sortStrategy = sortingStrategies.get(sort);
    }
    // 지원서 정렬 데이터
    int compare(T obj1, T obj2) {
        return sortStrategy.compare(obj1, obj2);
    }

    public void sort(List<T> data, String sortType) {
        if (sortType == null || sortType.isEmpty() || !sortingStrategies.containsKey(sortType)) {
            sortType = "Newest";
        }
        init();
        setSortStrategy(sortType);
        data.sort(this::compare);
    }
}
