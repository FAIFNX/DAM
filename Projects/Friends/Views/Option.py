# option.py

class Option:
    """
    Option class representing an option with text and an action command.
    """

    def __init__(self, text: str, action_command: str):
        self.text = text
        self.action_command = action_command

    def get_text(self) -> str:
        return self.text

    def set_text(self, text: str):
        self.text = text

    def get_action_command(self) -> str:
        return self.action_command

    def set_action_command(self, action_command: str):
        self.action_command = action_command

    def __hash__(self) -> int:
        return hash((self.text, self.action_command))

    def __eq__(self, other) -> bool:
        if not isinstance(other, Option):
            return False
        return (self.text == other.text) and (self.action_command == other.action_command)

    def __str__(self) -> str:
        return f"Option [text={self.text}, actionCommand={self.action_command}]"
