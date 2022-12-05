cd build/libs
for /f %%i in ('dir /b/a-d/od/t:c') do set LAST=%%i
echo %LAST%
java -jar %LAST%
