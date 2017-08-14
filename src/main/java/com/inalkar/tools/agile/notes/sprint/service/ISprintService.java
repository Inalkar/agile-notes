package com.inalkar.tools.agile.notes.sprint.service;

import com.inalkar.tools.agile.notes.sprint.dto.Sprint;

import java.util.List;
import java.util.stream.Stream;

public interface ISprintService {

    void addSprint(Sprint sprint) throws Exception;

    Stream<Sprint> getSprintsAsStream() throws Exception;

}
