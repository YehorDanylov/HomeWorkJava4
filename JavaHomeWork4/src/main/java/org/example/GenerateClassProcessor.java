package org.example;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.Writer;
import java.util.Set;

@SupportedAnnotationTypes("org.example.GenerateClass")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class GenerateClassProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element element : roundEnv.getElementsAnnotatedWith(GenerateClass.class)) {
            GenerateClass annotation = element.getAnnotation(GenerateClass.class);
            String className = annotation.className();
            String methodName = annotation.methodName();

            try {
                JavaFileObject file = processingEnv.getFiler().createSourceFile("org.example." + className);
                try (Writer writer = file.openWriter()) {
                    writer.write("package org.example;\n");
                    writer.write("public class " + className + " {\n");
                    writer.write("    public void " + methodName + "() {\n");
                    writer.write("        System.out.println(\"" + methodName + " called\");\n");
                    writer.write("    }\n");
                    writer.write("}\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
