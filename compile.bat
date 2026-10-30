@echo off
setlocal
title Compile Java Quiz Game

echo Compiling Java Quiz Game sources...
if not exist "bin" mkdir bin

javac -d bin src\com\quiz\*.java
if %ERRORLEVEL% EQU 0 (
    echo [OK] Compilation successful! Class files are in the 'bin' directory.
) else (
    echo [ERROR] Compilation failed with error code %ERRORLEVEL%.
)

pause
