import random
import string

def encryption(msg):
    # Definir el conjunto de caracteres
    chars = " " + string.punctuation + string.digits + string.ascii_letters
    char = list(chars)  # Convertir a una lista para poder modificar
    key = char.copy()   # Hacer una copia de la lista para la clave

    # Mezclar la clave
    random.shuffle(key)

    # Pedir al usuario un mensaje para cifrar
    plain_text = msg
    cipher_text = ""

    # Cifrar el mensaje
    for letter in plain_text:
        index = chars.index(letter)  # Buscar la posición del carácter en la lista original
        cipher_text += key[index]    # Agregar el carácter cifrado correspondiente

    # Mostrar resultados
    print(f"Encrypted message: {cipher_text}")