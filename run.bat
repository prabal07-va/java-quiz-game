@echo off
setlocal
title Java Quiz Game Server

echo ========================================================
echo         Java Quiz Game - Building and Starting
echo ========================================================
echo.

if not exist "bin" (
    mkdir bin
)

echo [1/2] Compiling Java source files...
javac -d bin src\com\quiz\*.java
if %ERRORLEVEL% NEQ 0 (
    echo.
    echo [ERROR] Compilation failed! Please check your JDK installation.
    pause
    exit /b %ERRORLEVEL%
)

echo [OK] Compilation successful!
echo.
echo [2/2] Launching Java Web Server on port 8080...
echo.
echo Open your browser at: http://localhost:8080
echo.

java -cp bin com.quiz.Main 8080

pause
