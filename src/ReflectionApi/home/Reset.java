package ReflectionApi.home;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class Reset {

    public static void resetObjectWithStatics(Object obj) throws IllegalAccessException {
        if (obj == null) return;

        Class<?> clazz = obj.getClass();
        while (clazz != null && clazz != Object.class) {
            Field[] fields = clazz.getDeclaredFields();

            for (Field field : fields) {
                if (Modifier.isFinal(field.getModifiers())) {
                    continue;
                }

                field.setAccessible(true);

                if (!field.getType().isPrimitive()) {
                    if (Modifier.isStatic(field.getModifiers())) {
                        field.set(null, null);
                    } else {
                        field.set(obj, null);
                    }
                }
            }
            clazz = clazz.getSuperclass();
        }
    }
}
