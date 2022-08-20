@cd /d "%~dp0"
taskkill /IM python.exe /F
python recognizeFoodRecoServer.py
pause