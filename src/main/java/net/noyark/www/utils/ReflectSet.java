/*Culesberry technolegy Co. Ltd. (c) 2019-2020
 * 
 * Stating that the software,the software belongs Gulesberry
 * noyark open source group,noyark has all the power to interpret
 * and copyright information for the software prohibit organizations
 * and individuals conduct their business practices and illegal practices,
 * projects of: magiclu,Chinese name *Changcun Lu*.The software has nothing
 * to do with current politics,free software is the purpose of noyark
 * 
 * noyark-system info:
 * 	****************************************************
 * 											www.noyark.net
 *		 ****************************************************
 * 
 */
package net.noyark.www.utils;


import net.noyark.www.utils.encode.SimpleClassCoder;
import net.noyark.www.utils.encode.Util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * This class is used to extensively load all Config
 * annotated classes and create corresponding configuration
 * file objects,then load the Root or Node annotations,load
 * them into hierarchy and key-value pairs,and various data types
 * to achieve the recommended configuration.
 * <BR>How to load using this class
 * <BR>if the config class in the a.b.c.d package,the packageFile
 * can be a or a.b or a.b.c or a.b.c.d
 * <BR>&nbsp;new ReflectSet("the parent package",this).loadAnnotation();
 * @author magiclu550
 * @since JDK1.8
 *
 */

public class ReflectSet {

	private static ReflectSet reflectSet;

	static {
		reflectSet = new ReflectSet();
	}

	/**
	 * Used to load all the classes with Config annotation 
	 * under the specified package ,and parse it into a 
	 * configuration file,and generate a configuration file object
	 */
	
	public List<Class<?>> load(SimpleClassCoder classCoder,String keyFile,boolean isDecode,String mainClass) {
		try {
			return scanPackage(classCoder,keyFile,isDecode,mainClass);
		} catch (IllegalArgumentException | IllegalAccessException | ClassNotFoundException | InstantiationException
				| IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Its role is to further parse
	 * the obtained class and resolve it to
	 * an instance; 
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 */
	private List<Class<?>> scanPackage( SimpleClassCoder classCoder,String keyFile,boolean decode,String mainClass) throws IllegalArgumentException, IllegalAccessException, IOException, ClassNotFoundException, InstantiationException {
		List<String> list = new ArrayList<>();
		List<Class<?>> classes = new ArrayList<>();
		File file = new File(Util.getClassPath(""));
		List<File> fileName = loadClass(file);
		for(File fn:fileName) {
			File[] fs = fn.listFiles();
			if(fs!=null) {
				for(File f1:fs) {
					if(f1.getName().endsWith(".class")) {
						String classpath = f1.getPath();
						classpath = classpath.substring(classpath.indexOf("classes")+"classes".length()+1,classpath.indexOf(".class")).replaceAll("/",".");
						list.add(classpath);
					}
				}
			}
		}
		String[] clzs = list.toArray(new String[list.size()]);
		if(!decode){
			classCoder.encode(keyFile,clzs);
		}else{
			for(String cname:clzs){
				Class<?> clazz;
				if(cname.replaceAll("/|\\\\",".").equals(mainClass)){
					clazz = classCoder.decode(keyFile,cname,true);
				}else{
					clazz = classCoder.decode(keyFile,cname);
				}
				classes.add(clazz);
			}
		}
		return classes;
	}

	
	/**
	 * It can get Package and Classes next the root package
	 * @param file the classpath file
	 * @return the file about classes and package
	 */
	
	private List<File> loadClass(File file) {
		List<File> allFiles = new ArrayList<File>();
		File[] files = file.listFiles();
		if(files!=null) {
			for(File f:files) {
				if(f.getName().endsWith(".class")) {
					allFiles.add(f);
				}
			}
		}
		searchFile(files, allFiles);
		return allFiles;
	}
	
	/**
	 * It can get all of the file about package
	 * @param files the file objects
	 * @param allFiles the package file
	 */
	
	private void searchFile(File[] files,List<File> allFiles) {
		if(files!=null) {
			for(File f:files) {
				if(f.isDirectory()){
					allFiles.add(f);
					File[] files2 = f.listFiles();
					searchFile(files2, allFiles);
				}
			}
		}
	}

	public static ReflectSet getReflectSet() {
		return reflectSet;
	}
}
