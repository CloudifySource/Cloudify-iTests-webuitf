package test.cli.cloudify;

import framework.utils.ScriptUtils;
import groovy.util.ConfigObject;
import groovy.util.ConfigSlurper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NoaTest {
	
	private static final String APPLICATION_RELATIVE_PATH = "/examples/petclinic";
	
	public static void main(String [] args) {

		String applicationDir = ScriptUtils.getBuildPath()
				+ APPLICATION_RELATIVE_PATH;
		List<String> services = new ArrayList<String>();
		services.add("tomcat");
		services.add("mongos");
		services.add("mongo-cfg");

		try {
			// for each service, read the properties file and empty the
			// destination directory
			ConfigObject configObject = null;
			for (String serviceName : services) {
				String propertiesFile = applicationDir + "/" + serviceName + "/" + serviceName + ".properties";
				configObject = new ConfigSlurper().parse(new File(propertiesFile).toURL());
				Map<Object, Object> propertiesMap = configObject.flatten();
				Object installDir = propertiesMap.get("installDir");
				Object zipFile = propertiesMap.get("zipName");
				if (zipFile == null)
					zipFile = CommandTestUtils.isWindows() ? propertiesMap.get("win32.zipName") : propertiesMap.get("unix.zipName");
				String fileToDelete = installDir + "/" + zipFile;
				deleteFile(fileToDelete, false);
			}

			// handle mongod - hard coded since the configSlurper can't parse it
			String mongodFileToDelete = System.getProperty("user.home") + "/.cloudify/mongod1/mongodb-win32-i386-2.0.2.zip";
			deleteFile(mongodFileToDelete, false);
			mongodFileToDelete = System.getProperty("user.home") + "/.cloudify/mongod2/mongodb-win32-i386-2.0.2.zip";
			deleteFile(mongodFileToDelete, false);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	private static void deleteFile(String fileName, boolean failIfFileNotFound) {

		try {
			// A File object to represent the filename
			File f = new File(fileName);

			// Make sure the file or directory exists and isn't write protected
			if (f.exists()) {
				if (!f.canWrite())
					throw new IllegalArgumentException("file is write protected and cannot be deleted: " + fileName);

				// If it is a directory, make sure it is empty
				if (f.isDirectory()) {
					throw new IllegalArgumentException(fileName + " is a directory, not a file");
				}

				// Attempt to delete it
				if (!f.delete())
					throw new IllegalArgumentException("Unknown error");	
			}
			//file not found
			else {
				if (failIfFileNotFound)
					throw new IllegalArgumentException("No such file or directory: " + fileName);
			}
		} catch (Exception ex) {
			System.out.println("Failed to delete file: " + fileName + ", exception: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
}
