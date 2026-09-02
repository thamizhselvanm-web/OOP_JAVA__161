# PowerShell Script to Compile and Run DocuVerify Application

$ErrorActionPreference = "Stop"
$ScriptDir = Split-Path -Path $MyInvocation.MyCommand.Definition -Parent
Set-Location $ScriptDir

Write-Host "=========================================" -ForegroundColor Cyan
Write-Host " Building DocuVerify JavaFX Application   " -ForegroundColor Cyan
Write-Host "=========================================" -ForegroundColor Cyan

# 1. Ensure bin directory exists and is clear
if (Test-Path "bin") {
    Remove-Item -Recurse -Force "bin"
}
New-Item -ItemType Directory -Force -Path "bin" | Out-Null

# 2. Get all Java source files
$javaFiles = Get-ChildItem -Recurse -Filter "*.java" -Path "src" | ForEach-Object { $_.FullName }

if ($javaFiles.Count -eq 0) {
    Write-Error "No Java source files found in src directory!"
    exit 1
}

Write-Host "Compiling $($javaFiles.Count) Java source files..." -ForegroundColor Yellow

# 3. Compile Java files
& javac -cp "lib/*" -d bin $javaFiles

if ($LASTEXITCODE -ne 0) {
    Write-Error "Compilation failed with exit code $LASTEXITCODE"
    exit $LASTEXITCODE
}

Write-Host "Compilation successful!" -ForegroundColor Green

# 4. Copy resources into bin directory
Write-Host "Copying UI resources (FXML & CSS)..." -ForegroundColor Yellow
Copy-Item -Path "resources\*" -Destination "bin" -Recurse -Force

Write-Host "Resources copied successfully!" -ForegroundColor Green
Write-Host "=========================================" -ForegroundColor Cyan
Write-Host " Launching DocuVerify...                 " -ForegroundColor Cyan
Write-Host "=========================================" -ForegroundColor Cyan

# 5. Run DocuVerify application
& java -cp "bin;lib/*" --module-path lib --add-modules javafx.controls,javafx.fxml com.docuverify.Main
