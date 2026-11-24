@echo off
rem Builds FreeMyTV.apk on Windows using Git Bash.
rem Set ANDROID_HOME and JAVA_HOME below if your SDK/JDK live elsewhere,
rem or define them in your environment before running this file.
setlocal

if "%ANDROID_HOME%"=="" set "ANDROID_HOME=C:\Android\Sdk"
if "%JAVA_HOME%"=="" set "JAVA_HOME=C:\Java\jdk-17"

set "GITBASH=C:\Program Files\Git\usr\bin\bash.exe"
if not exist "%GITBASH%" set "GITBASH=bash"

"%GITBASH%" -lc "cd \"$(cygpath '%CD%')\" && ANDROID_HOME=\"$(cygpath '%ANDROID_HOME%')\" JAVA_HOME=\"$(cygpath '%JAVA_HOME%')\" ./build.sh"
if errorlevel 1 (
  echo.
  echo Build failed. Check ANDROID_HOME and JAVA_HOME above.
  exit /b 1
)
