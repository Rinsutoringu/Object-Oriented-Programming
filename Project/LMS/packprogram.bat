@echo off
REM filepath: d:\dev\Object-Oriented-Programming\Project\LMS\packprogram.bat

set SRC_DIR=src
set BIN_DIR=bin
set JAR_NAME=LMS.jar
set MANIFEST_FILE=MANIFEST.MF
set LIB_DIR=lib
set RESOURCES_DIR=src\resources

if exist %BIN_DIR% (
    echo clear old bin path...
    rmdir /s /q %BIN_DIR%
)

echo create new bin...
mkdir %BIN_DIR%

echo compile Java source files...
for /r %SRC_DIR% %%f in (*.java) do (
    javac -d %BIN_DIR% -source 8 -target 8 -cp "%SRC_DIR%;%BIN_DIR%;%LIB_DIR%\*" "%%f"
)

if %errorlevel% neq 0 (
    echo compile failed, please check your code.
    exit /b %errorlevel%
)

echo copy resources...
xcopy /s /y "%RESOURCES_DIR%" "%BIN_DIR%\resources"

echo pack into JAR file...
jar cfm %JAR_NAME% %MANIFEST_FILE% -C %BIN_DIR% .

if %errorlevel% neq 0 (
    echo pack failed, please check your configuration.
    exit /b %errorlevel%
)

echo pack completed: %JAR_NAME%
pause