package dk.sdu.mmmi.pms.presentation.main.spring;

import org.springframework.context.ApplicationContext;

import java.util.Arrays;

/**
 * Utility class for printing Spring beans filtered by specific base packages.
 * This class provides a method to list all beans in the {@link ApplicationContext}
 * that belong to the specified base packages.
 */
public class BeanPrinter {

    /**
     * Prints all beans in the {@link ApplicationContext} that belong to the specified base packages.
     * The method retrieves all bean definitions, filters them by the provided base packages
     * and prints their names along with their fully qualified class names.
     *
     * @param context      the {@link ApplicationContext} containing the bean definitions
     * @param basePackages the base packages to filter beans by
     */
    public static void printPackageSpecificBeans(ApplicationContext context, String... basePackages) {
        System.out.println("\n=== Beans of PMS Modules ===");
        String[] beanNames = context.getBeanDefinitionNames();
        Arrays.sort(beanNames);

        for (String beanName : beanNames) {
            try {
                Class<?> type = context.getType(beanName);
                for (String basePackage : basePackages) {
                    if (type != null && type.getName().startsWith(basePackage)) {
                        System.out.println(beanName + " - " + type.getName());
                    }
                }
            } catch (Exception e) {
                System.out.println("Error getting type for bean: " + beanName);
            }
        }

        System.out.println("\n");
    }
}