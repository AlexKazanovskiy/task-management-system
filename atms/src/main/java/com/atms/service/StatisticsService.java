package com.atms.service;

import com.atms.model.Developer;
import com.atms.model.Task;

import java.util.List;
import java.util.Map;

/**
 * @author Alex Kazanovskiy.
 */
public interface StatisticsService {

    Developer getSuitableDeveloper(Task task);

    Map<Integer, Integer> getSuitableMap(List<Developer> developers, List<Task> tasks);

    double avgDeviation(Developer developer, Task task);
}
