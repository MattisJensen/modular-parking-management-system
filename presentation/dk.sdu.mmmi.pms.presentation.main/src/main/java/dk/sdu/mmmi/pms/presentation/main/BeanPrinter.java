package dk.sdu.mmmi.pms.presentation.main;

import org.springframework.context.ApplicationContext;

import java.util.Arrays;

public class BeanPrinter {
    /**
     * Prints all beans in the application context that belong to the specified base packages.
     *
     * @param context      The Spring application context.
     * @param basePackages The base packages to filter beans by.
     */
    public static void printPackageSpecificBeans(ApplicationContext context, String... basePackages) {
        System.out.println("\n=== Beans of PMS Modules ===");
        String[] beanNames = context.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        int pmsBeanCount = 0;

        for (String beanName : beanNames) {
            try {
                Class<?> type = context.getType(beanName);
                for (String basePackage : basePackages) {
                    if (type != null && type.getName().startsWith(basePackage)) {
                        System.out.println(beanName + " - " + type.getName());
                        pmsBeanCount++;
                        break;
                    }
                }
            } catch (Exception e) {
                System.out.println("Error getting type for bean: " + beanName);
            }
        }
    }
}
