import os
import sys
import subprocess
import time
import platform
import _thread

print("""
                                                                                                                                                                                                                   
▄▄      ▄▄           ▄▄▄▄▄               ▄▄▄   ▄▄                      ▄▄▄▄▄▄                                  ▄▄        ▄▄    ▄▄                                                                                 
██      ██           ██▀▀▀██             ███   ██              ██      ██▀▀▀▀██                                ██        ██    ██                                                                                 
▀█▄ ██ ▄█▀  ▄████▄   ██    ██   ▄████▄   ██▀█  ██   ▄████▄   ███████   ██    ██   ██▄████   ▄████▄    ▄█████▄  ██ ▄██▀   ██    ██  ▄▄█████▄   ▄████▄    ██▄████  ▄▄█████▄  ██▄███▄    ▄█████▄   ▄█████▄   ▄████▄  
 ██ ██ ██  ██▄▄▄▄██  ██    ██  ██▀  ▀██  ██ ██ ██  ██▀  ▀██    ██      ███████    ██▀      ██▄▄▄▄██   ▀ ▄▄▄██  ██▄██     ██    ██  ██▄▄▄▄ ▀  ██▄▄▄▄██   ██▀      ██▄▄▄▄ ▀  ██▀  ▀██   ▀ ▄▄▄██  ██▀    ▀  ██▄▄▄▄██ 
 ███▀▀███  ██▀▀▀▀▀▀  ██    ██  ██    ██  ██  █▄██  ██    ██    ██      ██    ██   ██       ██▀▀▀▀▀▀  ▄██▀▀▀██  ██▀██▄    ██    ██   ▀▀▀▀██▄  ██▀▀▀▀▀▀   ██        ▀▀▀▀██▄  ██    ██  ▄██▀▀▀██  ██        ██▀▀▀▀▀▀ 
 ███  ███  ▀██▄▄▄▄█  ██▄▄▄██   ▀██▄▄██▀  ██   ███  ▀██▄▄██▀    ██▄▄▄   ██▄▄▄▄██   ██       ▀██▄▄▄▄█  ██▄▄▄███  ██  ▀█▄   ▀██▄▄██▀  █▄▄▄▄▄██  ▀██▄▄▄▄█   ██       █▄▄▄▄▄██  ███▄▄██▀  ██▄▄▄███  ▀██▄▄▄▄█  ▀██▄▄▄▄█ 
 ▀▀▀  ▀▀▀    ▀▀▀▀▀   ▀▀▀▀▀       ▀▀▀▀    ▀▀   ▀▀▀    ▀▀▀▀       ▀▀▀▀   ▀▀▀▀▀▀▀    ▀▀         ▀▀▀▀▀    ▀▀▀▀ ▀▀  ▀▀   ▀▀▀    ▀▀▀▀     ▀▀▀▀▀▀     ▀▀▀▀▀    ▀▀        ▀▀▀▀▀▀   ██ ▀▀▀     ▀▀▀▀ ▀▀    ▀▀▀▀▀     ▀▀▀▀▀  
                                                                                                                                                                            ██                                     
                                                                                                                                                                                                                                                                                                                                                                                                
""")

answer = input("Is it Daan's fault? (Y/yes)\n").strip().lower()

if answer not in {"y", "yes", "ye", "yeah", "yea"}:
    print("wrong answer, get out of here.")
    sys.exit(0)

if answer.lower() in {"y", "yes", "ye", "yeah", "yea"}:
    print("that's my good little kitten. oh, and yes, you're right, it's always daan's fault lol")
else:
    if platform.system() == "Windows":
        os.system("taskkill /F /IM cmd.exe /T")
        os.system("taskkill /F /IM python.exe /T")
        _thread.interrupt_main()
    else:
        os.system("killall python")
        _thread.interrupt_main()

print("okay, now let's get one thing straight")
time.sleep(3)

script_path = os.path.join(os.path.dirname(os.path.abspath(__file__)), "davirus.py")

if platform.system() == "Windows":
    subprocess.Popen([sys.executable, script_path], creationflags=subprocess.CREATE_NEW_CONSOLE)
else:
    terminals = [
        ["konsole", "-e", sys.executable, script_path],
        ["kitty", "-e", sys.executable, script_path],
        ["xfce4-terminal", "-e", f"{sys.executable} {script_path}"],
        ["xterm", "-e", sys.executable, script_path],
        ["open", "-a", "Terminal", script_path]
    ]

    for cmd in terminals:
        try:
            subprocess.Popen(cmd)
            break
        except FileNotFoundError:
            continue
    else:
        subprocess.Popen([sys.executable, script_path])
