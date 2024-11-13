from typing import List, Optional
from option import Option

"""
Menu.py
Class Menu encapsulates the creation and use of a generic menu.
Author: Jose Moreno
"""

class Menu:

    def __init__(self, title: Optional[str] = None):
        self.title = title
        self.options: List[Option] = []

    def get_title(self) -> Optional[str]:
        return self.title

    def set_title(self, title: str):
        self.title = title

    def get_option(self, index: int) -> Option:
        return self.options[index]

    def add_option(self, option: Option) -> bool:
        self.options.append(option)
        return True

    def remove_option(self, option: Option) -> bool:
        try:
            self.options.remove(option)
            return True
        except ValueError:
            return False

    def remove_option_by_index(self, index: int) -> Optional[Option]:
        if 0 <= index < len(self.options):
            return self.options.pop(index)
        return None

    def __str__(self) -> str:
        return f"Menu [title={self.title}, options={self.options}]"

    def show(self):
        print(f"============ {self.title} ============")
        for id_option, option in enumerate(self.options):
            print(f"[{id_option}] {option.get_text()}")

    def get_selected_option(self) -> int:
        try:
            opt = int(input("Select an option: "))
            if 0 <= opt < len(self.options):
                return opt
        except ValueError:
            pass
        return -1

    def get_selected_option_action_command(self) -> Optional[str]:
        option_number = self.get_selected_option()
        if 0 <= option_number < len(self.options):
            return self.options[option_number].get_action_command()
        return None