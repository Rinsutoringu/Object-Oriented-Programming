@echo off
REM filepath: d:\dev\Object-Oriented-Programming\Project\LMS\packprogram.bat

set SRC_DIR=src
set BIN_DIR=bin
set JAR_NAME=LMS.jar
set MANIFEST_FILE=MANIFEST.MF

if exist %BIN_DIR% (
    echo clear old bin path...
    rmdir /s /q %BIN_DIR%
)

echo create new bin...
mkdir %BIN_DIR%

echo compile Java source files...
for /r %SRC_DIR% %%f in (*.java) do (
    javac -d %BIN_DIR% -cp %BIN_DIR% "%%f"
)

if %errorlevel% neq 0 (
    echo compile failed, please check your code.
    exit /b %errorlevel%
)

echo pack into JAR file...
jar cfm %JAR_NAME% %MANIFEST_FILE% -C %BIN_DIR% .

if %errorlevel% neq 0 (
    echo pack failed, please check your configuration.
    exit /b %errorlevel%
)

echo pack completed: %JAR_NAME%
pause