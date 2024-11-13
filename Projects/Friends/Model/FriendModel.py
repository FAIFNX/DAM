from typing import List, Optional

class FriendsModel:
    def __init__(self):
        pass
    
    def find(self, entity: 'Friend') -> Optional['Friend']:
        return None
    
    def add(self, entity: 'Friend') -> int:
        return 0
    
    def modify(self, old_entity: 'Friend', new_entity: 'Friend') -> int:
        return 0
    
    def remove(self, entity: 'Friend') -> int:
        return 0
    
    def findAll(self) -> List['Friend']:
        return []
    
    def findFriendsByName(self, name) -> List['Friend']:
        return []
    
    def __str__(self) -> str:
        return ""