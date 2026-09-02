@echo off
echo =========================================
echo  Building and Running DocuVerify App
echo =========================================

if not exist bin mkdir bin

echo Finding Java source files...
dir /b /s src\*.java > sources.txt

echo Compiling Java source files...
javac -cp "lib/*" -d bin @sources.txt
del sources.txt

if %ERRORLEVEL% NEQ 0 (
    echo Compilation failed!
    pause
    exit /b %ERRORLEVEL%
)

echo Copying resources...
xcopy /E /Y /I resources bin\

echo Launching DocuVerify...
java -cp "bin;lib/*" --module-path lib --add-modules javafx.controls,javafx.fxml com.docuverify.Main
