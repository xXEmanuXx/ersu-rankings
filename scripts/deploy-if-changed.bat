@echo off

REM Get the current date and time in UTC
FOR /F "tokens=1-4 delims=:.," %%a IN ('powershell -Command "Get-Date -Format 'yyyy-MM-ddTHH:mm:ssZ'"') DO SET TIMESTAMP=%%a:%%b:%%c.%%dZ

REM Fetch the remote repository
echo %TIMESTAMP%: Fetching remote repository...
git fetch

REM Set the upstream branch
SET UPSTREAM=%1
IF "%UPSTREAM%"=="" SET UPSTREAM=@{u}

REM Get local, remote, and base commit hashes
FOR /F %%i IN ('git rev-parse @') DO SET LOCAL=%%i
FOR /F %%i IN ('git rev-parse %UPSTREAM%') DO SET REMOTE=%%i
FOR /F %%i IN ('git merge-base @ %UPSTREAM%') DO SET BASE=%%i

REM Check the relationship between local, remote, and base
IF "%LOCAL%"=="%REMOTE%" (
    echo %TIMESTAMP%: No changes detected in git
) ELSE IF "%LOCAL%"=="%BASE%" (
    echo %TIMESTAMP%: Changes detected, deploying new version: %BUILD_VERSION%
    CALL deploy.bat
) ELSE IF "%REMOTE%"=="%BASE%" (
    echo %TIMESTAMP%: Local changes detected, stashing
    git stash
    CALL deploy.bat
) ELSE (
    echo %TIMESTAMP%: Git is diverged, this is unexpected
)