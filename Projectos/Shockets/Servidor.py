import socket
import os

def start_server(host, port):
    server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    server_socket.bind((host, port))
    server_socket.listen(1)
    print(f'Servidor escuchando en {host}:{port}')

    conn, addr = server_socket.accept()
    print(f'Conexión desde {addr}')

    while True:
        # Recibir tipo de operación
        operation = conn.recv(1024).decode()
        if operation == "send_file":
            filename = conn.recv(1024).decode()
            # Guardar el archivo en el mismo directorio donde está el script
            with open(os.path.join(os.getcwd(), filename), 'wb') as f:
                print(f'Recibiendo archivo: {filename}')
                while True:
                    data = conn.recv(1024)
                    if not data:
                        break
                    f.write(data)
            print(f'Archivo {filename} recibido.')

        elif operation == "send_text":
            message = conn.recv(1024).decode()
            print(f'Recibido texto: {message}')

        elif operation == "exit":
            print('Cerrando conexión.')
            break

    conn.close()
    server_socket.close()

if __name__ == "__main__":
    start_server('localhost', 65432)  # Cambia 'localhost' por la IP de tu servidor