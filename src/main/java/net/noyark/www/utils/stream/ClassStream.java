package net.noyark.www.utils.stream;

import java.util.ArrayList;
import java.util.List;

/**
 * class对象处理流
 */

public class ClassStream {

    private List<Class<?>> allClasses;

    public ClassStream(List<Class<?>> allClasses) {
        this.allClasses = allClasses;
    }

    public ClassStream filter(CompareTo compareTo){
        List<Class<?>> getClasses = new ArrayList<>();
        for(Class<?> clz:allClasses) {
            boolean isIt = compareTo.compare(clz);
            if(isIt){
                getClasses.add(clz);
            }
        }
        return new ClassStream(getClasses);
    }
    public ClassStream mustInstanceOf(Class<?> compare){
        List<Class<?>> getClasses = new ArrayList<>();
        try{
            for(Class<?> clz:allClasses) {
                if(compare.isInstance(clz.newInstance())){
                    getClasses.add(clz);
                }
            }
        }catch (Exception e){
         e.printStackTrace();
        }
        return new ClassStream(getClasses);
    }

    public List<Class<?>> asList(){
        return allClasses;
    }
}
