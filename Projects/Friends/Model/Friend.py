class Friend:
    def __init__(self, phone: str = "", name: str = "", age: int = 0):
        self.phone = phone
        self.name = name
        self.age = age

    def get_phone(self) -> str:
        return self.phone

    def set_phone(self, phone: str):
        self.phone = phone

    def get_name(self) -> str:
        return self.name

    def set_name(self, name: str):
        self.name = name

    def get_age(self) -> int:
        return self.age

    def set_age(self, age: int):
        self.age = age

    def __hash__(self) -> int:
        return hash(self.phone)

    def __eq__(self, other: object) -> bool:
        if other is None:
            return False
        if self is other:
            return True
        if isinstance(other, Friend):
            return self.phone == other.phone
        return False

    def __str__(self) -> str:
        return f"Friend{{phone={self.phone};name={self.name};age={self.age}}}"