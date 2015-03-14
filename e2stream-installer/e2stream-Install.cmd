@echo off
echo __________________________________
echo.
echo E2Stream Installer Bootstrap
echo Copyright (C)2014 Pembo.co.uk
echo __________________________________
echo.
::get javaw.exe from the latest properly installed jre
for /f tokens^=2^ delims^=^" %%i in ('reg query HKEY_CLASSES_ROOT\jarfile\shell\open\command /ve') do (
set JAVA_PATH=%%i
)

::if reg entry is not found, java is not installed
if "%JAVA_PATH%"=="" goto NotFound

::then strip "\javaw.exe" from the JAVAW_PATH obtained above
set JAVA_HOME=%JAVA_PATH:\bin\javaw.exe=%


set str1="%JAVA_HOME%"

:v18
rem -----------------------------
rem check for v1.8
rem -----------------------------
echo Looking for JREv1.8
if not x%str1:jre1.8=%==x%str1% GOTO FoundJVM

:v17
rem -----------------------------
rem check for v1.7
rem -----------------------------
echo Looking for JREv1.7
if not x%str1:jre1.7=%==x%str1% GOTO FoundJVM

:NotFound
echo.
echo +--------------------------------------------------------------------+
echo ^| !WARNING! Unable to locate a compatible JRE.                       ^|
echo +--------------------------------------------------------------------+
echo ^| Please download a JRE (v1.8 or v1.7) from                          ^|
echo ^| http://www.oracle.com/technetwork/java/javase/downloads/index.html ^|
echo ^| to be able to continue with this instllation if the installer      ^|
echo ^| does not start!                                                    ^|
echo +--------------------------------------------------------------------+
start /WAIT java -jar E2Stream-installer.jar %1 %2
GOTO complete

:FoundJVM
echo %JAVA_HOME%
Echo Found Compatible JVM at: %JAVA_HOME%
Echo Starting Installer Using discovered JVM
"%JAVA_HOME%\bin\java.exe" -jar E2Stream-installer.jar %1 %2

:complete
Echo Bootstrap Terminated
Echo Press any key to close
pause > nul