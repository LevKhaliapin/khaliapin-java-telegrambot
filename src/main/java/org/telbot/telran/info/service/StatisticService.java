package org.telbot.telran.info.service;

import org.telbot.telran.info.model.Statistic;

import java.util.List;

public interface StatisticService {

    Statistic createStatistic(Statistic statistic);

    List<Statistic> getStatistic();
}
