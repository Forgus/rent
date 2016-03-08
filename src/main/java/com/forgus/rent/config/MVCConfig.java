package com.forgus.rent.config;

import com.forgus.rent.util.ArrayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

import java.util.Arrays;
import java.util.List;

/**
 * Created by cwb on 2016/3/8.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {
        "com.forgus.rent"
})
@Import(ServiceConfig.class)
public class MVCConfig extends WebMvcConfigurerAdapter {

    private final String UTF8 = "UTF-8";

    /**
     * 静态资源处理,加在这里
     */
    private static String[] STATIC_RESOURCE_PATH = {
            "assets",
            "views"
    };

    @Autowired
    private Environment environment;
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private ViewResolver htmlViewResolver;
    @Autowired
    private ViewResolver javascriptViewResolver;

    /**
     * 静态资源设置
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);
        for (String resourcePath : MVCConfig.STATIC_RESOURCE_PATH) {
            registry.addResourceHandler("/" + resourcePath + "/**").addResourceLocations("/" + resourcePath + "/");
        }
    }

    /**
     * 注册视图解析器
     *
     * @param registry
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.viewResolver(htmlViewResolver);
        registry.viewResolver(javascriptViewResolver);
    }

    /**
     * html解析器
     *
     * @return
     */
    @Bean
    public ViewResolver htmlViewResolver() {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine(htmlTemplateResolver()));
        resolver.setOrder(1);
        resolver.setContentType("text/html;charset=utf-8");
        resolver.setCharacterEncoding(UTF8);
        return resolver;
    }

    /**
     * javascript 解析器
     * @return
     */
    @Bean
    public ViewResolver javascriptViewResolver() {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine(javascriptTemplateResolver()));
        resolver.setContentType("application/javascript");
        resolver.setCharacterEncoding(UTF8);
        resolver.setViewNames(ArrayUtil.array("*.js"));
        return resolver;
    }
    private ITemplateEngine templateEngine(ITemplateResolver templateResolver) {
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.setTemplateResolver(templateResolver);
        return engine;
    }
    private ITemplateResolver htmlTemplateResolver() {
        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setPrefix("/views/");
        resolver.setSuffix(".html");
        resolver.setApplicationContext(applicationContext);
        resolver.setCharacterEncoding(UTF8);
        //设置缓存
        if (environment.acceptsProfiles("development")) {
            resolver.setCacheable(false);
        }
        return resolver;
    }
    private ITemplateResolver javascriptTemplateResolver() {
        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setApplicationContext(applicationContext);
        resolver.setCharacterEncoding(UTF8);
        resolver.setTemplateMode(TemplateMode.JAVASCRIPT);
        //设置缓存
        if (environment.acceptsProfiles("development")) {
            resolver.setCacheable(false);
        }
        return resolver;
    }
    /**
     * for upload
     */
    @Bean
    public CommonsMultipartResolver multipartResolver() {
        return new CommonsMultipartResolver();
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();

        converter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON));
        converters.add(converter);
    }

}
