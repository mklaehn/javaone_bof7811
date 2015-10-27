package org.annotationprocessing.proc;

import java.io.IOException;
import java.io.Writer;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.FileObject;
import javax.tools.StandardLocation;
import org.annotationprocessing.defintion.MyName;

@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes("org.annotationprocessing.defintion.MyName")
public class MyNameProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        int i = 0;
        for (final Element elementsAnnotatedWith : roundEnv.getElementsAnnotatedWith(MyName.class)) {
            processingEnv.getMessager().printMessage(Diagnostic.Kind.WARNING, elementsAnnotatedWith.toString());
            MyName myName = elementsAnnotatedWith.getAnnotation(MyName.class);
            processingEnv.getMessager().printMessage(Diagnostic.Kind.WARNING, myName.toString());

            try {
                FileObject fo = processingEnv.getFiler().createResource(
                        StandardLocation.SOURCE_OUTPUT,
                        "data.myname",
                        String.format("%04d.txt", i++),
                        elementsAnnotatedWith);

                try (Writer writer = fo.openWriter()) {
                    writer.append("lastName: " + myName.lastName());
                    writer.append("\n");

                    if (null != myName.firstName()) {
                        writer.append("fastName: " + myName.firstName());
                        writer.append("\n");
                    }
                }
            } catch (IOException ex) {
                processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, ex.toString());
            }
        }

        return false;
    }
}
