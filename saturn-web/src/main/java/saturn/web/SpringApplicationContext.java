package saturn.web;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

//@Component
public class SpringApplicationContext implements ApplicationContextAware {

    private static ApplicationContext appContext;

    private SpringApplicationContext() {}

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        appContext = applicationContext;
    }

    public static <T> T getBean(Class<T> bean) {
        return appContext.getBean(bean);
    }

}
