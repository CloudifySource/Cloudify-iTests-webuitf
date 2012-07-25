echo on

set CURRENT_DIR=%CD%
set SGTEST_DIR=%CURRENT_DIR%\..\SGTest
set SGTEST_WEBUI_LIB=%SGTEST_DIR%\lib\webui
set JAR_TARGET_PATH=%CURRENT_DIR%\target\webuitf.jar
set SRC_TARGET_PATH=%CURRENT_DIR%\target\webuitf-sources.jar

call mvn package

if exist %SGTEST_WEBUI_LIB%\webuitf.jar del %SGTEST_WEBUI_LIB%\webuitf.jar
if exist %SGTEST_WEBUI_LIB%\webuitf-sources.jar del %SGTEST_WEBUI_LIB%\webuitf-sources.jar
copy %JAR_TARGET_PATH% %SGTEST_WEBUI_LIB%

copy %SRC_TARGET_PATH% %SGTEST_WEBUI_LIB%



