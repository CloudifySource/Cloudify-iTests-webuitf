package com.gigaspaces.webuitf.util;

public class SharedContextConstants {

	// namespace constants from the web UI

	private static final String DELIMITER = ".";

	private static final String KEY_GS_WEB_UI = "GsWebUi";
	private static final String KEY_FLAGS = "Flags";
	private static final String KEY_OBJECTS = "Objects";
	private static final String KEY_FLAG_IS_UNDER_TEST = "isUnderTest";
	private static final String KEY_OBJECT_CODE_MIRROR_QUERIES = "codeMirrorQueries";
	private static final String KEY_OBJECT_CODE_MIRROR_RECIPES = "codeMirrorRecipes";

	private static final String NS_OBJECTS = KEY_GS_WEB_UI + DELIMITER + KEY_OBJECTS;
	private static final String NS_FLAGS = KEY_GS_WEB_UI + DELIMITER + KEY_FLAGS;

	public static final String NS_CODE_MIRROR_QUERIES = NS_OBJECTS + DELIMITER + KEY_OBJECT_CODE_MIRROR_QUERIES;
	public static final String NS_CODE_MIRROR_RECIPES = NS_OBJECTS + DELIMITER + KEY_OBJECT_CODE_MIRROR_RECIPES;
	public static final String NS_IS_UNDER_TEST = NS_FLAGS + DELIMITER + KEY_FLAG_IS_UNDER_TEST;
	
}
