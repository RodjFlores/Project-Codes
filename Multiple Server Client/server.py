#!/usr/bin/python           # This is server.py file
import pickle
import socket               # Import socket module

s = socket.socket()         # Create a socket object
host = ''
port = 10500              # Reserve a port for your service.
s.bind((host, port)) 
print('Host: ',socket.gethostname())
print('Port: ',port)



s.listen(5)  
print('Listening for Connections...') 


  
while True:
   c, addr = s.accept()     
   print ('Got connection from---', addr)
   
   r2={1:1,2:0,3:1,4:'x'}
   c.send(pickle.dumps(r2))
   
   r1 = pickle.loads(c.recv(4096))
   print('Recieve Link Costs---',r1)   
   
   
   c.send(('Thank you for connecting').encode())   
   c.close()
   
                  