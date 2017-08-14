package com.inalkar.tools.agile.notes;

import com.inalkar.tools.agile.notes.main.MainWindowConfig;
import com.inalkar.tools.agile.notes.spring.config.AppConfig;
import com.inalkar.tools.agile.notes.util.dialog.ErrorDialogsUtil;
import javafx.application.Preloader;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Application extends javafx.application.Application {

    private final static Logger LOG = LogManager.getLogger(Application.class);
    
    public final static String MAIN_STYLE = 
            Application.class.getClassLoader().getResource("styles/darcula.css").toExternalForm();
    
    @Override
    public void start(Stage stage) {
        ApplicationContext appContext = new AnnotationConfigApplicationContext(AppConfig.class);
        try {
            List<String> params = getParameters().getUnnamed();
            MainWindowConfig window = appContext.getBean(MainWindowConfig.class);
            window.setPrimaryStage(stage);
            window.showMainWindow(params);
            notifyPreloader(new Preloader.StateChangeNotification(Preloader.StateChangeNotification.Type.BEFORE_START));
        } catch (Exception ex) {
            new ErrorDialogsUtil().exceptionDialog(ex);
            LOG.error(ex.getLocalizedMessage(), ex);
            System.exit(1);
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
