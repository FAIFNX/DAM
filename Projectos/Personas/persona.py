class Persona:
    def __init__(self, nombre, edad):
        self.nombre = nombre
        self.edad = edad

    def to_dict(self):
        return{
        "nombre" : self.nombre,
        "edad" : self.edad
        }