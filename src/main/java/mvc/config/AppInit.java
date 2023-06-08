package mvc.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class AppInit extends AbstractAnnotationConfigDispatcherServletInitializer {

    // Метод, указывающий на класс конфигурации
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    // Добавление конфигурации, в которой инициализируем ViewResolver, для корректного отображения jsp.
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{
                WebConfig.class
        };
    }

    /* Данный метод указывает url, на котором будет базироваться приложение */
    @Override
    @SuppressWarnings("NullableProblems")
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    /* Включение кодировки UTF-8 */
    @Override
    @SuppressWarnings("NullableProblems")
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
        FilterRegistration.Dynamic filterRegistration = servletContext.addFilter("characterEncodingFilter",
                new CharacterEncodingFilter("UTF-8", true, true));
        filterRegistration.addMappingForUrlPatterns(null, false, "/*");
        filterRegistration = servletContext.addFilter("hiddenHttpMethodFilter", new HiddenHttpMethodFilter() );
        filterRegistration.addMappingForUrlPatterns(null, false, "/*");

    }
}