/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dynamicproxiesapp;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Jagadheesan Balu
 */
public class TimingInvocationHandler implements InvocationHandler {

    private static Logger LOG = LoggerFactory.getLogger(TimingInvocationHandler.class);
    
    private Object target;
    
    private final Map<String,Method> methods = new HashMap<String,Method>();
    
    public TimingInvocationHandler(Object target) {
        
        this.target = target;
        
        for(Method method : target.getClass().getDeclaredMethods()) {
            this.methods.put(method.getName(), method);
        }
        
    }
    
    
    @Override
    public Object invoke( Object proxy, Method method , Object[] args ) 
            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        
        long start = System.nanoTime();
        
        Object result = methods.get(method.getName()).invoke(target, args);
        
        long elapsed = System.nanoTime() - start;
        
        LOG.info("Executing {} finished in {} ns ", method.getName(), elapsed);
        
        
        return result;
        
    }
    
}
