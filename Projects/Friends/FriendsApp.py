
from Friends.Controllers.FriendController import FriendController
from Friends.Model.FriendModel import FriendModel

class FriendsApp:
    @staticmethod
    def main():
        model = FriendModel()
        control = FriendController(model)

if __name__ == "__main__":
    FriendsApp.main()