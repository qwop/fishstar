@echo off
REM create by qwop 76430850 2013.07.26
SET PATH_12306=GIT_SRC
SET OLD_PATH=%CD%
CD ../12306_ticket_helper.git
SET GIT_PATH=%CD%
svn update
CD %OLD_PATH%
xcopy /E /C /Y %GIT_PATH%\trunk\src %PATH_12306%
12306 %PATH_12306%
pause