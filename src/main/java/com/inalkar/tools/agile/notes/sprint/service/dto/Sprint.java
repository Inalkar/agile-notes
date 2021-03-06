package com.inalkar.tools.agile.notes.sprint.service.dto;

import java.util.Date;
import java.util.UUID;

public class Sprint {

    public UUID id;
    public String title;
    public Date startTime;
    public Date endTime;
    public Date rrfDue;
    public Date stageRelease;
    public Date prodRelease;
    public boolean archived;

    @Override
    public String toString() {
        return "Sprint{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", rrfDue=" + rrfDue +
                ", stageRelease=" + stageRelease +
                ", prodRelease=" + prodRelease +
                ", archived=" + archived +
                '}';
    }
}
