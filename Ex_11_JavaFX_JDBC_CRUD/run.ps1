# PowerShell script to compile and run Student Management Application

param(
    [string]$Password = ""
)

$ScriptDir = Split-Path -Path $MyInvocation.MyCommand.Definition -Parent
Set-Location $ScriptDir

Write-Host "Compiling StudentManagementApp.java..." -ForegroundColor Yellow
& javac --module-path lib --add-modules javafx.controls,javafx.fxml -cp "lib/*" StudentManagementApp.java

if ($LASTEXITCODE -ne 0) {
    Write-Error "Compilation failed!"
    exit $LASTEXITCODE
}

Write-Host "Launching Student Management App..." -ForegroundColor Green
if ($Password) {
    & java --module-path lib --add-modules javafx.controls,javafx.fxml -cp ".;lib/*" StudentManagementApp --db.password=$Password
} else {
    & java --module-path lib --add-modules javafx.controls,javafx.fxml -cp ".;lib/*" StudentManagementApp @args
}
