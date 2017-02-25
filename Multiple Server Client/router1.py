import socket
import pickle

sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
host = 'afsaccess1.njit.edu'
#portNum = input("Server port? ")
#p = int(portNum)
#port = p
sock.connect((host,10101))
while True:
    data = {0:1,1:0,2:1,3:'x'}
    sock.send(pickle.dumps(data))
    response = pickle.loads(sock.recv(4096))
    print ("response: ", response)