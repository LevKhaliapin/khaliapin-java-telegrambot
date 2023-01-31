package org.telbot.telran.info.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telbot.telran.info.model.Statistic;
import org.telbot.telran.info.repository.StatisticRepository;

import java.util.List;

@Service
public class StatisticServiceImpl implements StatisticService {
    @Autowired
    private StatisticRepository statisticRepository;

    @Override
    public Statistic createStatistic(Statistic statistic) {
        return statisticRepository.save(statistic);
    }

    @Override
    public List<Statistic> getStatistic() {
        return statisticRepository.findAll();
    }
}
