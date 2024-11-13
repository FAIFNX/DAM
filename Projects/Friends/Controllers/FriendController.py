# friend_controller.py

from typing import List
from friend_model import FriendModel
from friend_view import FriendView
from friend import Friend

"""
FriendController.py
Class FriendController. Friend controller.
Uses the FriendModel class and FriendView class.
Author: ProvenSoft
"""

class FriendController:

    def __init__(self, model: FriendModel):
        self.model = model
        self.view = FriendView(self, model)
        self.view.display()

    def exit_application(self):
        exit(0)

    def process_request(self, action: str):
        action = action if action is not None else "wrong_action"
        if action == "exit":
            self.exit_application()
        elif action == "list_all_friends":
            self.list_all_friends()
        elif action == "search_friend_by_phone":
            self.search_friend_by_phone()
        elif action == "friend_form_remove":
            self.remove_friend_form()
        elif action == "friend_form_add":
            self.add_friend()
        elif action == "search_friends_by_name":
            self.search_friends_by_name()
        elif action == "friend_form_modify":
            self.modify_friend_form()
        else:
            self.view.show_message("Wrong option")

    def list_all_friends(self):
        data = self.model.find_all()
        if data is not None:
            self.view.show_friend_table(data)
        else:
            self.view.show_message("Error retrieving data")

    def search_friend_by_phone(self):
        phone = self.view.show_input_dialog("Input phone: ")
        if phone is not None:
            friend = Friend(phone=phone)
            found = self.model.find(friend)
            if found is not None:
                self.view.friend_form(found)
            else:
                self.view.show_message("Friend not found")
        else:
            self.view.show_message("Error in parameter phone")

    def search_friends_by_name(self):
        name = self.view.show_input_dialog("Input name: ")
        if name is not None:
            data = self.model.find_friends_by_name(name)
            if data is not None:
                self.view.show_friend_table(data)
            else:
                self.view.show_message("Error searching Friends")
        else:
            self.view.show_message("Error in parameter name")

    def add_friend(self):
        friend = self.view.friend_form(None)
        if friend is not None:
            result = self.model.add(friend)
            if result > 0:
                self.view.show_message("Friend successfully added")
            else:
                self.view.show_message("Friend has not been added")
        else:
            self.view.show_message("Error in parameters")

    def modify_friend(self, old_version: Friend, new_version: Friend):
        result = self.model.modify(old_version, new_version)
        if result > 0:
            self.view.show_message("Friend successfully modified")
        else:
            self.view.show_message("Friend has not been modified")

    def remove_friend(self, friend: Friend):
        result = self.model.remove(friend)
        if result > 0:
            self.view.show_message("Friend successfully removed")
        else:
            self.view.show_message("Friend has not been removed")

    def modify_friend_form(self):
        phone = self.view.show_input_dialog("Input phone: ")
        if phone is not None:
            friend = Friend(phone=phone)
            found = self.model.find(friend)
            if found is not None:
                new_friend = self.view.friend_form(found)
                self.modify_friend(friend, new_friend)
            else:
                self.view.show_message("Friend not found")

    def remove_friend_form(self):
        phone = self.view.show_input_dialog("Input phone: ")
        if phone is not None:
            friend = Friend(phone=phone)
            found = self.model.find(friend)
            if found is not None:
                self.remove_friend(friend)
            else:
                self.view.show_message("Friend not found")
