package org.example;

import java.lang.reflect.Method;

public class Validator {

    public static void validate(Object obj) throws Exception {
        Class<?> clazz = obj.getClass();
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(ValidateAge.class)) {
                ValidateAge annotation = method.getAnnotation(ValidateAge.class);
                int min = annotation.min();
                int max = annotation.max();

                method.setAccessible(true);
                int age = (int) method.invoke(obj);

                if (age < min || age > max) {
                    throw new RuntimeException("Age " + age + " is out of range (" + min + ", " + max + ")");
                }
            }
        }
    }
}
