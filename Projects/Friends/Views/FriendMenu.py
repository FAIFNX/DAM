# friend_menu.py

from menu import Menu
from option import Option

class FriendMenu(Menu):
    def __init__(self):
        super().__init__("Friends Manager main menu")
        self.add_option(Option("Exit", "exit"))
        self.add_option(Option("List All friends", "list_all_friends"))
        self.add_option(Option("Find friend by phone", "search_friend_by_phone"))
        self.add_option(Option("Find friends by name", "search_friends_by_name"))
        self.add_option(Option("Add friend", "friend_form_add"))
        self.add_option(Option("Modify friend", "friend_form_modify"))
        self.add_option(Option("Remove friend", "friend_form_remove"))
