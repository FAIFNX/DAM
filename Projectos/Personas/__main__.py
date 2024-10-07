import json
import os
from persona import Persona  # Asegúrate de que el archivo persona.py contenga la clase Persona.

def Creacion(archivo):
    # Verificación si existe el archivo
    if not os.path.exists(archivo):
        print("El archivo no existe.")
        # Creación del nuevo archivo
        archivo = input('Nombre del archivo a crear (.json): ')
        with open(archivo, 'w') as nuevo_archivo:
            json.dump({"mensaje": "Este es un nuevo objeto.", "personas": []}, nuevo_archivo)  # Crear un JSON con mensaje y lista vacía
        print(f"{archivo} creado exitosamente.")
    else:
        print(f"{archivo} ya existe.")

def lectura(archivo):
    # Verificar si el archivo existe antes de leer
    if os.path.exists(archivo):
        with open(archivo, 'r') as archive:
            datos = json.load(archive)
        print("Contenido del archivo:", datos)
        return datos
    else:
        print("El archivo no se puede leer porque no existe.")
        return None

def escrituraDePersona(archivo, datos):
    # Crear un nuevo objeto Persona con los inputs del usuario
    nombre = input("Introduce el nombre: ")
    edad = int(input("Introduce la edad: "))
    
    # Crear el objeto Persona
    persona = Persona(nombre, edad)
    
    # Inicializa datos si no existe
    if datos is None:
        datos = {"mensaje": "Este es un nuevo objeto.", "personas": []}
    
    # Añadir la nueva persona a la lista de personas
    datos['personas'].append(persona.to_dict())
    
    # Escribir los datos actualizados en el archivo (sin sobrescribir, solo agregando)
    with open(archivo, 'w') as archive:
        json.dump(datos, archive, indent=4)  # Usar indent para formato legible
    
    print(f"Persona añadida y datos guardados en {archivo} exitosamente.")

def modificarPersona(archivo, datos):
    if datos is None or 'personas' not in datos or not datos['personas']:
        print("No hay personas para modificar.")
        return

    nombre_a_modificar = input("Introduce el nombre de la persona que deseas modificar: ")
    
    # Buscar la persona en la lista
    for persona in datos['personas']:
        if persona['nombre'] == nombre_a_modificar:
            print(f"Datos actuales: Nombre: {persona['nombre']}, Edad: {persona['edad']}")
            # Preguntar qué desea modificar
            opcion = input("¿Deseas modificar el nombre (N) o la edad (E)? (Introduce N/E): ").upper()
            if opcion == 'N':
                nuevo_nombre = input("Introduce el nuevo nombre: ")
                persona['nombre'] = nuevo_nombre
            elif opcion == 'E':
                nueva_edad = int(input("Introduce la nueva edad: "))
                persona['edad'] = nueva_edad
            else:
                print("Opción no válida.")
                return
            
            # Escribir los datos actualizados en el archivo
            with open(archivo, 'w') as archive:
                json.dump(datos, archive, indent=4)
            print("Persona modificada exitosamente.")
            return
    
    print("No se encontró a la persona.")

def Main():
    archivo = input('Nombre del archivo que deseas abrir: ')
    
    # Verificar si el archivo existe o crearlo
    Creacion(archivo)
    
    # Leer el contenido del archivo
    datos = lectura(archivo)
    
    while True:  # Ciclo infinito para continuar interactuando con el usuario
        opcion = input('¿Quieres añadir una persona? (Si/No): ').strip().lower()
        if opcion not in ["si", "no"]:
            print("Opción inválida, por favor ingresa 'Si' o 'No'.")
            continue
        
        if opcion == 'si':
            escrituraDePersona(archivo, datos)
            datos = lectura(archivo)  # Leer los datos después de agregar una nueva persona
        else:
            print("Se acaba el programa.")
            break  # Salir del bucle principal
    
    while True:  # Ciclo para modificar personas
        opcion = input("¿Quieres modificar algún dato? (Si/No): ").strip().lower()
        if opcion not in ["si", "no"]:
            print("Opción inválida, por favor ingresa 'Si' o 'No'.")
            continue
        
        if opcion == 'si':
            modificarPersona(archivo, datos)
            datos = lectura(archivo)  # Leer los datos después de modificar una persona
        else:
            print("Se acaba el programa.")
            break  # Salir del bucle de modificación

Main()
