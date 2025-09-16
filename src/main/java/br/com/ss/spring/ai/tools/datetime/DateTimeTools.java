package br.com.ss.spring.ai.tools.datetime;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.context.i18n.LocaleContextHolder;

import java.time.LocalDateTime;

public class DateTimeTools {

    @Tool(description = "Get the currente date time in the user's timezone")
    public String getCurrentDateTime(){
        return LocalDateTime.now().atZone(LocaleContextHolder.getTimeZone().toZoneId()).toString();
    }
}
