package com.example.config;

import org.springframework.boot.context.annotation.ImportCandidates;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.StreamSupport;

public class MyAutoConfigImportSelector implements DeferredImportSelector {

    private final ClassLoader classLoader;

    public MyAutoConfigImportSelector(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {

        // 자동 구성 대상 후보들을 읽어온다.
        // 우리가 어떤 어플리케이션의 클래스 패스에서 resource, file들을 읽어올때 classLoader를 사용한다.
        // 파일에다가 설정해둘 configuration 목록들이 들어가 있을 것이다.
        // Iterable<String> candidates = ImportCandidates.load(MyAutoConfiguration.class, classLoader);
        // return StreamSupport.stream(candidates.spliterator(), false).toArray(String[]::new);

        // 변경1
        List<String> autoConfigs = new ArrayList<>();
        /*for (String candidate : ImportCandidates.load(MyAutoConfiguration.class, classLoader)) {
            autoConfigs.add(candidate);
        }*/

        // 변경2
        /*ImportCandidates.load(MyAutoConfiguration.class, classLoader).forEach(candidate ->
                autoConfigs.add(candidate)
        );*/

        // 변경 3
        ImportCandidates.load(MyAutoConfiguration.class, classLoader).forEach(autoConfigs::add);

        return autoConfigs.toArray(new String[0]);
//        return autoConfigs.toArray(String[]::new);
//        return Arrays.copyOf(autoConfigs.toArray(), autoConfigs.size(), String[].class)
        // 이중에서 어떤 것이 사용되게 할 것인지는 스마트한 방법으로 결정을 할 것이다.

    }
}
