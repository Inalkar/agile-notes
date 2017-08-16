package com.inalkar.tools.agile.notes.settings.extension.sprint;

import com.inalkar.tools.agile.notes.settings.extension.ISettingExtension;
import com.inalkar.tools.agile.notes.settings.extension.sprint.view.SprintSettingsLayout;
import javafx.scene.layout.AnchorPane;
import org.springframework.stereotype.Component;

@Component("Sprint")
public class SprintSettingExtension implements ISettingExtension {

    @Override
    public String getName() {
        return "Sprint";
    }

    @Override
    public AnchorPane getLayout() {
        return new SprintSettingsLayout();
    }

}
