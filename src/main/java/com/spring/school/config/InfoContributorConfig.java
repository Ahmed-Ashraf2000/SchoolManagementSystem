package com.spring.school.config;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class InfoContributorConfig implements InfoContributor {

    @Override
    public void contribute(Info.Builder builder) {
        Map<String, String> map = new HashMap<>();
        map.put("App Name", "PrimeSchool");
        map.put("App Description", "Prime School Web Application for Students and Admin");
        map.put("App Version", "1.0.0");
        map.put("Contact Email", "info@primeschool.com");
        map.put("Contact Mobile", "+1(21) 673 4587");
        builder.withDetail("primeschool-info", map);
    }
}
