import sys

sys.path.append('C:\\Python35\\Lib\site-packages')

import os
from glob import glob

import socket
import sys

import sys
from scipy.io import arff
from sklearn.svm import SVC
import pandas as pd
import numpy as np
from sklearn.svm import SVC
from sklearn.metrics import classification_report, confusion_matrix
import filehelper
import librosa
from train_food_dataset import *

HOST = ''  # Symbolic name, meaning all available interfaces
PORT = 7813  # Arbitrary non-privileged port

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
print('Socket created')
svclassifier = filehelper.read_object("./svm.model")
data="5.0,0.5,0.5,0.0,0.5,0.5,0.0,1.5,0.5,1.0,3.5,1.0,0.0,0.0,0.5,1.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,1.0,0.0,0.0,0.0,0.0,0.0,0.0,2.0,0.0,0.5,4.5,1.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,1.0,0.0,0.0,3.0,1.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,1.0,1.0,0.0,0.0,0.5,0.0,0.0,3.0,0.5,1.0,6.5,1.5,0.5,0.0,0.0,0.5,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,1.0,0.0,0.0,0.0,0.0,0.0,0.0,1.0,0.0,1.0,4.0,1.0,0.0,0.0,0.0,1.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,1.0,0.0,0.0,3.0,1.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,1.0,0.0,0.0,0.0,0.0,0.0,0.0,1.0,0.0,1.0,3.0,1.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0"
audio_path = data.strip()
lst_int = [float(x) for x in audio_path.split(",")]
print(lst_int)
#print(lst_int.shape)
reshape2d=np.reshape(lst_int,(1,168))
print(reshape2d)
data = svclassifier.predict(reshape2d)
print(data)
try:
    s.bind((HOST, PORT))
except socket.error as msg:
    print('Bind failed. Error Code : ' + str(msg[0]) + ' Message ' + msg[1])
    sys.exit()

print('Socket bind complete')

# Start listening on socket
s.listen(10)
print('Socket now listening')

# now keep talking with the client
while 1:
    # wait to accept a connection - blocking call
    conn, addr = s.accept()
    print('Connected with ' + addr[0] + ':' + str(addr[1]))
    try:
        data = conn.recv(1024).decode()
        print(data)
        audio_path = data.strip()
        if (audio_path == "1"):
            #r = str(classesIndexMapRev)
            r=""
            print(r)
            conn.send(r.encode())
            conn.close()
            continue
        elif (audio_path == "2"):
            s2=trainNetwork()
            conn.send(s2.encode())
            conn.close()
            continue
        else:
            lst_int = [float(x) for x in audio_path.split(",")]
            print(lst_int)
            #print(lst_int.shape)
            reshape2d=np.reshape(lst_int,(1,168))
            print(reshape2d)
            data = svclassifier.predict(reshape2d)
            print(data)
            r = str(data[0]) 
            print(r)
            conn.send(r.encode())
    except Exception as e:
        print("An exception occurred")
        print(e.message)
    conn.close()
s.close()
