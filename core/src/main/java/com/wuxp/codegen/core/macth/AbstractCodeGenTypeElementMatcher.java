package com.wuxp.codegen.core.macth;

import org.springframework.util.AntPathMatcher;
import org.springframework.util.Assert;
import org.springframework.util.PathMatcher;

import java.util.Set;

public class AbstractCodeGenTypeElementMatcher extends AbstractCodeGenElementMatcher<Class<?>> implements CodeGenTypeElementMatcher {

    private final PathMatcher pathMatcher;

    private final Set<String> antPatterns;

    protected AbstractCodeGenTypeElementMatcher(Set<String> matchPackages, Set<Class<?>> matchClasses) {
        super(matchClasses);
        Assert.notNull(matchPackages, "match packages not null");
        this.antPatterns = matchPackages;
        this.pathMatcher = new AntPathMatcher();
    }

    @Override
    public boolean matches(Class<?> clazz) {
        if (super.matches(clazz)) {
            return true;
        }
        return antPatterns.stream().anyMatch(name -> clazz.getName().startsWith(name) || pathMatcher.match(name, clazz.getName()));
    }
}
