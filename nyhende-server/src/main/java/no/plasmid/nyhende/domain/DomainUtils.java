package no.plasmid.nyhende.domain;

import com.tinkerpop.blueprints.impls.orient.OrientEdge;
import com.tinkerpop.blueprints.impls.orient.OrientVertex;
import no.plasmid.nyhende.domain.domainobject.DomainObject;
import no.plasmid.nyhende.domain.domainrelation.DomainRelation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AssignableTypeFilter;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class DomainUtils {

    private final static Logger LOG = LoggerFactory.getLogger(DomainUtils.class);

    private static Map<String, Class<? extends DomainObject<?>>> domainObjectClasses;

    private static Map<String, Class<? extends DomainRelation<?>>> domainRelationClasses;

    static {
        domainObjectClasses = new HashMap<>();
        domainRelationClasses = new HashMap<>();

        loadClassInfo();
    }

    @SuppressWarnings("unchecked")
    public static <T extends DomainObject<?>> T createDomainObjectInstance(OrientVertex ov) {
        T rc = null;
        Class<? extends DomainObject<?>> domainObjectClass = resolveDomainObjectClassName(ov.getProperty(DomainObject.PROPERTY_CLASS_NAME));

        try {
            rc = (T)domainObjectClass.getConstructor(OrientVertex.class).newInstance(ov);
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
            LOG.error(e.getMessage(), e);
        }

        return rc;
    }

    @SuppressWarnings("unchecked")
    public static <T extends DomainRelation<?>> T createDomainRelationInstance(OrientEdge oe) {
        T rc = null;
        Class<? extends DomainRelation<?>> domainRelationClass = resolveDomainRelationClassName(oe.getProperty(DomainRelation.PROPERTY_CLASS_NAME));

        try {
            rc = (T)domainRelationClass.getConstructor(OrientEdge.class).newInstance(oe);
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
            LOG.error(e.getMessage(), e);
        }

        return rc;
    }

    private static Class<? extends DomainObject<?>> resolveDomainObjectClassName(String className) {
        return domainObjectClasses.get(className);
    }

    private static Class<? extends DomainRelation<?>> resolveDomainRelationClassName(String graphClassName) {
        return domainRelationClasses.get(graphClassName);
    }

    private static void loadClassInfo() {
        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
        scanner.addIncludeFilter(new AssignableTypeFilter(DomainObject.class));
        for (BeanDefinition bd : scanner.findCandidateComponents("no.plasmid.nyhende")) {
            try {
                @SuppressWarnings("unchecked")
                Class<? extends DomainObject<?>> clazz = (Class<? extends DomainObject<?>>) Class.forName(bd.getBeanClassName());
                domainObjectClasses.put(clazz.getName(), clazz);
            } catch (ClassNotFoundException e) {
                LOG.error(e.getMessage(), e);
            }
        }

        scanner = new ClassPathScanningCandidateComponentProvider(false);
        scanner.addIncludeFilter(new AssignableTypeFilter(DomainRelation.class));
        for (BeanDefinition bd : scanner.findCandidateComponents("no.plasmid.nyhende")) {
            try {
                @SuppressWarnings("unchecked")
                Class<? extends DomainRelation<?>> clazz = (Class<? extends DomainRelation<?>>) Class.forName(bd.getBeanClassName());
                domainRelationClasses.put(clazz.getName(), clazz);
            } catch (ClassNotFoundException e) {
                LOG.error(e.getMessage(), e);
            }
        }
    }

}
