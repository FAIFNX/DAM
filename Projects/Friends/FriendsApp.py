from Friends.FriendController import FriendController
from Model.FriendModel import FriendModel

def main():
    # Instanciamos el modelo y el controlador
    model = FriendModel()
    control = FriendController(model)

if __name__ == "__main__":
    main()
