# friend_view.py

from friend_menu import FriendMenu
from friend import Friend

class FriendView:
    def __init__(self, control, model):
        self.control = control
        self.model = model
        self.menu = FriendMenu()

    def show_input_dialog(self, message):
        return input(message)

    def show_message(self, message):
        print(message)

    def display(self):
        while True:
            self.menu.show()
            action = self.menu.get_selected_option_action_command()
            self.process_action(action)

    def process_action(self, action):
        if action:
            self.control.process_request(action)

    def show_friend_table(self, data):
        for elem in data:
            print(elem)
        print(f"{len(data)} elements found.")

    def friend_form(self, input_friend=None):
        if input_friend:
            print(input_friend)
        
        try:
            phone = input("Input phone: ")
            name = input("Input name: ")
            age = int(input("Input age: "))
            return Friend(phone, name, age)
        except (ValueError, TypeError):
            return None
