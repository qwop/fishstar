@echo off
for /l %%i in (1,1,10) do wget http://code.jquery.com/jquery-1.%%i.js
pause