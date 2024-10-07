import socket

def send_file(conn, filename):
    conn.sendall("send_file".encode())
    conn.sendall(filename.encode())
    with open(filename, 'rb') as f:
        print(f'Enviando archivo: {filename}')
        while True:
            data = f.read(1024)
            if not data:
                break
            conn.sendall(data)
    print(f'Archivo {filename} enviado.')

def send_text(conn, message):
    conn.sendall("send_text".encode())
    conn.sendall(message.encode())
    print(f'Enviando texto: {message}')

def main():
    client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    client_socket.connect(('localhost', 65432))  # Cambia 'localhost' por la IP del servidor

    while True:
        option = input("Elige una opción (1: Enviar archivo, 2: Enviar texto, 3: Salir): ")
        if option == '1':
            filename = input("Introduce el nombre del archivo a enviar: ")
            send_file(client_socket, filename)
        elif option == '2':
            message = input("Introduce el texto a enviar: ")
            send_text(client_socket, message)
        elif option == '3':
            client_socket.sendall("exit".encode())
            break
        else:
            print("Opción no válida.")

    client_socket.close()

if __name__ == "__main__":
    main()
