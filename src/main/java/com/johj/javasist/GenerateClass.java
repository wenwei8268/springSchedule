package com.johj.javasist;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.bytecode.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

/**
 * @author:wenwei
 * @date:2019/04/09
 * @description:
 */
public class GenerateClass {

    private static final String className = "Point";
    private static final String methodName = "toString";
//    private static final String methodBody = "java.lang.System.out.println(\"Hello world!\");";
    private static final String methodBody = "com.johh.javasist.Point.toString()";


    public static Class generateClass(String className, String methodName, String methodBody)
            throws CannotCompileException {
        ClassPool classPool = ClassPool.getDefault();
        CtClass ctClass = classPool.makeClass(className);

        StringBuffer method = new StringBuffer();
        method.append("public void ")
                .append(methodName)
                .append("() {")
                .append(methodBody)
                .append(";}");

        ctClass.addMethod(CtMethod.make(method.toString(), ctClass));
        return classPool.toClass(ctClass);
    }
    public static void compileClass() throws Exception{
        ClassPool classPool = ClassPool.getDefault();
        ClassFile cf = classPool.get("com.johj.javasisst.Point").getClassFile();
        MethodInfo minfo = cf.getMethod("toString");
        CodeAttribute ca = minfo.getCodeAttribute();
        CodeIterator ci = ca.iterator();

        // when
        List<String> operations = new LinkedList<>();
        while (ci.hasNext()) {
            int index = ci.next();
            int op = ci.byteAt(index);
            operations.add(Mnemonic.OPCODE[op]);
        }
        System.out.println(operations.toString());

    }
    public static void main(String[] args) {
        try {
            compileClass();
            // Use our static method to make a magic
            Class clazz = generateClass(className, methodName, methodBody);
            // Create a new instance of our newly generated class
            Object obj = clazz.newInstance();
            // Find our method in generated class
            Method method = clazz.getDeclaredMethod(methodName);
            // And finally invoke it on instance
            method.invoke(obj);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (CannotCompileException e) {
            e.printStackTrace();
        }catch(Exception e ){
            e.printStackTrace();
        }
    }

}