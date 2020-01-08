package no.plasmid.nyhende.migration;

import no.plasmid.nyhende.orientdb.OrientDBTransactionlessWrapper;
import no.plasmid.nyhende.orientdb.service.TransactionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
public class MigrationRunner {

    private final static Logger LOG = LoggerFactory.getLogger(MigrationRunner.class);

    @Autowired
    TransactionFactory transactionFactory;

    public void upgrade() {
        LOG.info("Begin upgrade");
        OrientDBTransactionlessWrapper transactionless = transactionFactory.getTransactionless();
        try {
            runInitialMigration();

            applyMigrationClasses(sortMigrationClasses(findMigrationClasses()));

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            transactionless.finish();
        }
        System.out.println("Finished upgrade");
        LOG.info("Finished upgrade");
    }

    private void runInitialMigration() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        LOG.debug("Initial migration");

        if (!shouldRun(InitialMigration.class.getSimpleName())) { return; }

        applyClass(InitialMigration.class);
    }

    private boolean shouldRun(String className) {
        return !(OrientDBTransactionlessWrapper.getInstance().findVertexInstances(FinishedMigration.class, "name", className).iterator().hasNext());
    }

    @SuppressWarnings("unchecked")
    private List<Class<? extends Migration>> findMigrationClasses() throws ClassNotFoundException {
        List<Class<? extends Migration>> classes = new ArrayList<>();

        ClassPathScanningCandidateComponentProvider scanner =
                new ClassPathScanningCandidateComponentProvider(false);

        scanner.addIncludeFilter(new AssignableTypeFilter(Migration.class));

        for (BeanDefinition bd : scanner.findCandidateComponents("no.plasmid.nyhende")) {
            classes.add((Class<? extends Migration>) Class.forName(bd.getBeanClassName()));
        }

        return classes;
    }

    private List<Class<? extends Migration>> sortMigrationClasses(List<Class<? extends Migration>> classes) {
        /**
         * Sorts migrations by class.getSimpleName() (which amounts to ascending by name).
         */
        classes.sort(Comparator.comparing(Class::getSimpleName));

        return classes;
    }

    private void applyMigrationClasses(List<Class<? extends Migration>> classes) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        for (Class<? extends Migration> theClass : classes) {
            if (ExampleDataMigration.class.isAssignableFrom(theClass) || !shouldRun(theClass.getSimpleName())) { continue; }
            applyClass(theClass);
        }
    }

    private void applyClass(Class<? extends Migration> theClass) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        //Instantiate and run the migration
        Migration migration = theClass.getConstructor(TransactionFactory.class).newInstance(transactionFactory);
        LOG.debug(migration.getDescription());
        System.out.println(migration.getDescription());
        migration.upgrade();
        //Register the migration as run
        new FinishedMigration(OrientDBTransactionlessWrapper.getInstance().createVertex(FinishedMigration.class, theClass.getSimpleName()));
    }


}
