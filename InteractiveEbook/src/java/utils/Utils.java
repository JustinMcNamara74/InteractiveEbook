/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Random;

/**
 *
 * @author carbo_000
 */
public class Utils {
    
    private static String charSet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static Random random = new Random();
    
    public static String generateRandomString(int length) {
        StringBuilder s = new StringBuilder();
        
        for(int i=0; i < length; i++) {
            s.append(charSet.charAt(random.nextInt(charSet.length())));
        }
        
        return s.toString();
    }
}
