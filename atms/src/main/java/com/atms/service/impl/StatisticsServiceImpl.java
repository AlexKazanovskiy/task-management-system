package com.atms.service.impl;

import com.atms.model.Developer;
import com.atms.model.DeveloperEffectiveness;
import com.atms.model.Task;
import com.atms.model.TaskKeyword;
import com.atms.repository.DeveloperRepository;
import com.atms.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Alex Kazanovskiy.
 */
@Service
public class StatisticsServiceImpl implements StatisticsService {

    private final DeveloperRepository developerRepository;

    @Autowired
    public StatisticsServiceImpl(DeveloperRepository developerRepository) {
        this.developerRepository = developerRepository;
    }

    @Override
    public Developer getSuitableDeveloper(Task task) {
        List<Developer> developerList = developerRepository.findAll();
        developerList = developerList.stream().filter(developer -> developer.getDeveloperEffectiveness().size() > 0).collect(Collectors.toList());
        int[][] a = new int[2][developerList.size() + 1];
        for (int i = 1; i <= developerList.size(); i++)
            a[1][i] = getCoefficient(developerList.get(i - 1), task);
        return developerList.get(hungurian(a, 1, developerList.size())[0]);
    }

    @Override
    public Map<Integer, Integer> getSuitableMap(List<Developer> developers, List<Task> tasks) {
        int[][] a = new int[tasks.size()][developers.size()];
        for (int i = 1; i <= tasks.size(); i++) {
            for (int j = 1; j <= developers.size(); j++)
                a[i][j] = getCoefficient(developers.get(j), tasks.get(i));
        }
        int[] suitable = hungurian(a, tasks.size() + 1, developers.size() + 1);
        Map<Integer, Integer> suitableMap = new HashMap<>();
        for (int i = 1; i <= suitable.length; i++)
            suitableMap.put(tasks.get(i).getTaskId(), developers.get(suitable[i]).getDeveloperId());
        return suitableMap;
    }

    private int getCoefficient(Developer developer, Task task) {
        int coefficient = (int) (1 / developer.getDeveloperLoad());
        coefficient *= task.getEstimationTime() * avgDeviation(developer, task);
        return coefficient;
    }

    @Override
    public double avgDeviation(Developer developer, Task task) {
        double avgDeviation = 0;
        for (TaskKeyword keyword : task.getKeywords()) {
            List<DeveloperEffectiveness> developerEffectiveness = developer.getDeveloperEffectiveness().stream().filter(developerEffectiveness1 -> developerEffectiveness1.getKeyword().equals(keyword)).collect(Collectors.toList());
            if (developerEffectiveness.size() == 0)
                continue;
            avgDeviation += keyword.getImportance() *
                    developerEffectiveness.get(0).getDeviation();
        }
        avgDeviation /= task.getKeywords().size();
        return avgDeviation;
    }

    private int[] hungurian(int[][] a, int n, int m) {
        int u[] = new int[n];
        int v[] = new int[m];
        int p[] = new int[m];
        int[] way = new int[m];
        int INF = Integer.MAX_VALUE;
        for (int i = 1; i < n; ++i) {
            p[0] = i;
            int j0 = 0;
            int[] minv = new int[m];
            Arrays.fill(minv, INF);
            boolean[] used = new boolean[m];
            Arrays.fill(used, false);
            do {
                used[j0] = true;
                int i0 = p[j0], delta = INF, j1 = 0;
                for (int j = 1; j < m; ++j)
                    if (!used[j]) {
                        int cur = a[i0][j] - u[i0] - v[j];
                        if (cur < minv[j]) {
                            minv[j] = cur;
                            way[j] = j0;
                        }
                        if (minv[j] < delta) {
                            delta = minv[j];
                            j1 = j;
                        }
                    }
                for (int j = 0; j < m; ++j)
                    if (used[j]) {
                        u[p[j]] += delta;
                        v[j] -= delta;
                    } else
                        minv[j] -= delta;
                j0 = j1;
            } while (p[j0] != 0);
            do {
                int j1 = way[j0];
                p[j0] = p[j1];
                j0 = j1;
            } while (j0 != 0);
        }
        int[] ans = new int[n];
        for (int j = 1; j < m; ++j)
            ans[p[j]] = j;
        return ans;
    }
}
