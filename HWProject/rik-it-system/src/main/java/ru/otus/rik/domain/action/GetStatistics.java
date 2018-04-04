package ru.otus.rik.domain.action;

import com.opensymphony.xwork2.ActionSupport;
import lombok.Getter;
import lombok.Setter;
import ru.otus.rik.domain.StatisticsEntity;
import ru.otus.rik.service.helpers.RemoteStatisticsServiceHolder;

import java.util.List;

@Getter
@Setter
public class GetStatistics extends ActionSupport {

    private List<StatisticsEntity> statistics;

    @Override
    public String execute() throws Exception {
        statistics = RemoteStatisticsServiceHolder.getStatisticsService().getAllStatistics();
        if (statistics != null) {
            return SUCCESS;
        }
        return ERROR;
    }
}
