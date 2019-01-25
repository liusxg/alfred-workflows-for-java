package com.liusxg.alfred.workflows;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class WorkflowsApplication {

    public static void main(String[] args) {
        List<String> argList = new ArrayList(Arrays.asList(args));
        try {
            if (argList.size() != 0) {
                Class needRunClass = Class.forName("com.liusxg.alfred.workflows." + argList.get(0));
                argList.remove(0);
                Object instance = needRunClass.getConstructor().newInstance();
                String json = ((AbstractWorkflow) instance).execute(argList);
                System.out.println(json);
            } else {
                System.out.println("没有要执行的类");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } finally {

        }
    }

}

