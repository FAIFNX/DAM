from gestor_archivos import GestorDeArchivos

class MenuGestorDeArchivos:
    def __init__(self):
        self.gestor = GestorDeArchivos()

    def mostrar_menu(self):
        while True:
            # Mostramos las opciones del menú.
            print("\nOpciones del menú:")
            print("1- Crear un archivo")
            print("2- Leer el contenido de un archivo")
            print("3- Escribir en un archivo")
            print("4- Eliminar un archivo")
            print("5- Salir")

            opcion = input("Selecciona una opción (1-5): ")

            if opcion == "1":
                nombre_archivo = input("Introduce el nombre del archivo a crear: ")
                self.gestor.crear_archivo(nombre_archivo)
            elif opcion == "2":
                nombre_archivo = input("Introduce el nombre del archivo a leer: ")
                self.gestor.leer_archivo(nombre_archivo)
            elif opcion == "3":
                nombre_archivo = input("Introduce el nombre del archivo a escribir: ")
                texto = input("Escribe el texto que deseas agregar: ")
                self.gestor.escribir_en_archivo(nombre_archivo, texto)
            elif opcion == "4":
                nombre_archivo = input("Introduce el nombre del archivo a eliminar: ")
                self.gestor.eliminar_archivo(nombre_archivo)
            elif opcion == "5":
                print("Saliendo del programa.")
                break
            else:
                print("Opción no válida. Por favor, selecciona una opción correcta.")
