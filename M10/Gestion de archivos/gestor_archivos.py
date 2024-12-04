import os

class GestorDeArchivos:
    def crear_archivo(self, nombre_archivo):
        try:
            # Intentamos crear el archivo en modo 'x', que falla si el archivo ya existe.
            with open(nombre_archivo, 'x') as archivo:
                print(f"El archivo '{nombre_archivo}' ha sido creado correctamente.")
        except FileExistsError:
            print(f"El archivo '{nombre_archivo}' ya existe.")

    def leer_archivo(self, nombre_archivo):
        try:
            # Abrimos el archivo en modo lectura y mostramos su contenido.
            with open(nombre_archivo, 'r') as archivo:
                contenido = archivo.read()
                print(f"Contenido de '{nombre_archivo}':\n{contenido}")
        except FileNotFoundError:
            print(f"El archivo '{nombre_archivo}' no existe.")

    def escribir_en_archivo(self, nombre_archivo, texto):
        try:
            # Escribimos el texto al final del archivo usando modo 'a' (append).
            with open(nombre_archivo, 'a') as archivo:
                archivo.write(texto + '\n')
                print(f"Se ha escrito en el archivo '{nombre_archivo}' correctamente.")
        except FileNotFoundError:
            print(f"El archivo '{nombre_archivo}' no existe.")

    def eliminar_archivo(self, nombre_archivo):
        try:
            # Eliminamos el archivo especificado.
            os.remove(nombre_archivo)
            print(f"El archivo '{nombre_archivo}' ha sido eliminado correctamente.")
        except FileNotFoundError:
            print(f"El archivo '{nombre_archivo}' no existe.")
