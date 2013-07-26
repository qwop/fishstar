REM create by qwop 76430850 2013.07.26
@echo off
SET PATH_12306=12306
del /f /s /q *.crx
echo 开始下载...
wget http://www.fishlee.net/Service/Download.ashx/44/63/12306_ticket_helper.crx
echo 开始解压...
rd /s /q %PATH_12306%
7z x *.crx -o%PATH_12306%
del /f /s /q *.crx
12306
pause