/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dynamicproxiesapp;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Jagadheesan Balu
 */
public class DynamicProxiesApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Map proxyInstance = (Map) Proxy.newProxyInstance(
                Map.class.getClassLoader(),
                new Class[] {Map.class},
                new TimingInvocationHandler(new HashMap<String,String>()));
        
        proxyInstance.put("hello", "world");
        
        System.out.println(proxyInstance.get("hello"));
    }
}
