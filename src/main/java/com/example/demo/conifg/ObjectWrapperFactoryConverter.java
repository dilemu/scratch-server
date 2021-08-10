package com.example.demo.conifg;


import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author 20022436
 * @Description
 * @date 2020/9/14 9:02
 */

@Component
@ConfigurationPropertiesBinding
class ObjectWrapperFactoryConverter implements Converter<String, ObjectWrapperFactory> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ObjectWrapperFactoryConverter.class);

    @Override
    public ObjectWrapperFactory convert(String source) {
        try {
            return (ObjectWrapperFactory) Class.forName(source).newInstance();
        } catch (InstantiationException e) {
            LOGGER.error("InstantiationException:{}", e);
        } catch (IllegalAccessException e) {
            LOGGER.error("IllegalAccessException:{}", e);
        } catch (ClassNotFoundException e) {
            LOGGER.error("ClassNotFoundException:{}", e);
        }
        return null;
    }

}

