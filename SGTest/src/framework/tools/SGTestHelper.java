package framework.tools;

import java.io.File;

import org.apache.commons.lang.StringUtils;

import framework.utils.ScriptUtils;

public class SGTestHelper {

	/**
	 * This is a very cool method which returns jar or directory from where the supplied class has loaded.
	 * 
	 * @param claz the class to get location.
	 * @return jar/path location of supplied class.
	 */
	public static String getClassLocation(Class claz)
	{
		return claz.getProtectionDomain().getCodeSource().getLocation().toString().substring(5);
	}

	public static boolean isDevMode() {
		boolean isDevMode;
		if (System.getenv().containsKey("DEV_ENV")) {
			boolean value = Boolean.getBoolean(System.getenv("DEV_ENV"));
			return value;
		} else if (System.getProperties().contains("DEV_ENV")) {
			boolean value = Boolean.getBoolean(System.getProperty("DEV_ENV"));
			return value;
		}
		

		if (ScriptUtils.isWindows()) {
			isDevMode = !System.getenv("USERNAME").equals("ca");
		}
		else {
			isDevMode = !System.getProperty("user.name").equals("tgrid");
		}
		return isDevMode;
	}

	public static String getSGTestSrcDir() {
		String sgtestSrcDir;
		if (isDevMode()) {
			sgtestSrcDir = getSGTestRootDir() + "/src";
		}
		else {
			sgtestSrcDir = getSGTestRootDir() + "/tmp";
		}
		return sgtestSrcDir;

	}
	
	
	public static String getSuiteName(){
		return System.getProperty("sgtest.suiteName", "sgtest");
	}
	
	public static String getSuiteId() {
		return System.getProperty("sgtest.suiteId", "0");
	}
	
	//each suite has it's own work dir.
	public static String getWorkDirName() {
		String suiteDir = getSuiteName();
		String suiteId = getSuiteId();
		if (StringUtils.isEmpty(suiteDir) || StringUtils.isEmpty(suiteId)) {
			return "work";
		} else {
			return suiteDir + suiteId + "_work";
		}
	}

	/** @return SGTest root directory */
	public static String getSGTestRootDir()
	{
		String tmp;
		String sgTestJarPath = getClassLocation(SGTestHelper.class);
		String sgTestRootDir = new File(sgTestJarPath).getParent();
		tmp = sgTestRootDir.toLowerCase();
		if (!tmp.endsWith("sgtest") && !tmp.endsWith("sgtest-xap") && !tmp.endsWith("sgtest-cloudify" ) && !tmp.endsWith("sgtest_cloudify")){
			sgTestRootDir = new File(sgTestJarPath).getParentFile().getParent();
		}
		return sgTestRootDir;
	}

	public static String getBuildDir() {
		return ScriptUtils.getBuildPath();
	}
}
