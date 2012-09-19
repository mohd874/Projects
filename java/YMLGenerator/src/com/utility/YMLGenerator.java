/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author m.alhabshi
 * 
 * Parts of the code were taken from the following site:
 * <a href="http://www.javapractices.com/topic/TopicAction.do?Id=42">http://www.javapractices.com/topic/TopicAction.do?Id=42</a>
 */
public class YMLGenerator {

    public static boolean GenerateYML(String strFile) throws FileNotFoundException, Exception{

//        String filePath = YMLGenerator.class.getResource(strFile).getPath();
//        log("File Path: " + filePath);
//
//        String className = strFile.substring(0, strFile.indexOf("."));
//        log("Class name: " + className);

        File file = new File(strFile);
        
        Scanner scanner = new Scanner(file, "UTF-8");
        String NL = System.getProperty("line.separator");
        
        String[] header = readHeaders(scanner);
        
        String text = createData(scanner, file.getName(), NL, header);
        
//        writeToFile(filePath, text);
        writeToFile(file.getAbsolutePath(), text);
        
        return true;
    }

    private static String[] readHeaders(Scanner scanner) throws Exception {
        StringTokenizer st;
        String[] header;
        if (scanner.hasNextLine()) {
            st = new StringTokenizer(scanner.nextLine(), ":");
            header = new String[st.countTokens()];
            int i = 0;
            while (st.hasMoreTokens()) {
                header[i] = st.nextToken();
                i++;
            }

            for (int j = 0; j < header.length; j++) {
                log("Header " + j + ": " + header[j]);
            }
        } else {
            throw new Exception("The file is empty");
        }
        return header;
    }
    
    private static String createData(Scanner scanner, String className, String NL, String[] header) {
        StringBuilder text = new StringBuilder();
        StringTokenizer st;
        try {
            int i = 0;
            while (scanner.hasNextLine()) {
                st = new StringTokenizer(scanner.nextLine(), ":");
                text.append(className)
                    .append("(")
                    .append(i)
                    .append(")")
                    .append(NL);

                for (int j = 0; j < header.length; j++) {
                    String h = header[j];
                    text.append("  ")
                        .append(h)
                        .append(":")
                        .append(st.nextToken())
                        .append(NL);
                }

                i++;
            }
        } finally {
            scanner.close();
        }
        
        return text.toString();
    }

    private static void writeToFile(String filePath, String text) throws UnsupportedEncodingException, FileNotFoundException, IOException {
        log(text);
        Writer out = new OutputStreamWriter(new FileOutputStream(filePath.replaceFirst(".txt", ".yml")), "UTF-8");
        try {
            out.write(text.toString());
        } finally {
            out.close();
        }
    }
    
    private static void log(Object obj) {
        System.out.println(obj);
    }

    public static void main(String[] args) {
        String file;
        if(args != null && args[0] != null){
            file = args[0];
        }else{
            log("No file were spacified!");
            return;
        }
        try {
            YMLGenerator.GenerateYML(file);
        } catch (FileNotFoundException ex) {
//            Logger.getLogger(YMLGenerator.class.getName()).log(Level.SEVERE, null, ex);
            log("\nFile not found...program will exit now!\n");
        } catch (Exception ex) {
            Logger.getLogger(YMLGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
