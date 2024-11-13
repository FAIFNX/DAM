
from friend import Friend
from friend_model import FriendModel
from friend_controller import FriendController
from friend_view import FriendView

def main():
    # Crear el modelo de datos
    model = FriendModel()

    # Crear el controlador
    control = FriendController(model)

    # Crear la vista y pasarle el controlador y el modelo
    view = FriendView(control, model)

    # Mostrar la vista
    view.display()

if __name__ == "__main__":
    main()