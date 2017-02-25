import socket
import threading
import pickle
import time

class ThreadedServer(object):

    r1={0:0,1:1,2:3,3:7}
    

    def __init__(self, host, port):
        self.host = host
        self.port = port
        self.sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.sock.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
        self.sock.bind((self.host, self.port))
        
        print("\nListening for Connections...\n")

        
        print('Router 0 Links---',ThreadedServer.r1)

    def listen(self):
        self.sock.listen(5)
        while True:
            client, address = self.sock.accept()
            client.settimeout(120)
            threading.Thread(target = self.listenToClient,args = (client,address)).start()

    def listenToClient(self, client, address):

        print("\nNew Connection with: ", address)
        size = 4096
        while True:            

            try:
                data = client.recv(size)
                if data:                   
                    # Set the response to echo back the recieved data 
                    client.send(data)
                    r2 = pickle.loads(data)
                    rr= ThreadedServer.r1

                    if (r2[0]+r2[2])<(rr[1]+rr[2]):
                        ThreadedServer.r1[2]=2
                        ThreadedServer.r1[3]=5

                    elif r2[3] is 'x':
                            continue
                    elif(r2[0]+r2[3]<rr[3]):
                        ThreadedServer.r1[3]=4
                           
                  
                    print(r2, "----- FROM ----- ",address)
                    
                    print("\nUpdated Router 0 ----- ",ThreadedServer.r1)

                else:
                    raise error('Client disconnected')
            except:
                client.close()
                return False

            time.sleep(30)

if __name__ == "__main__":    
    ThreadedServer('',10101).listen()