package com.inalkar.tools.agile.notes.sprint.service;

import com.inalkar.tools.agile.notes.sprint.service.dto.Sprint;

import java.util.stream.Stream;

public interface ISprintService {

    void addSprint(Sprint sprint) throws Exception;

    Stream<Sprint> getSprintsAsStream() throws Exception;

}
