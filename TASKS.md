# Tasks

Tasks and goals that still need to be implemented. Each task should have an associated issue, which should be closed with the commit that finishes the task.

## Interfaces

Interfaces that need to be designed and written.

### Rect

A Rect should represent a two-dimensional rectangle bounding area, along with the position of the bounding area in a 2D space. The core functionality of a Rect needs to be able to determine if it is overlapping / colliding with another Rect (likely a method such as `boolean collides(Rect)` ). A Rect may also expose attributes such as width, height, and origin, either as individual numbers or packaged into a Vector.

### Item

An Item should represent a conceptual in-game object, such as a Consumable (Food, etc) or Weapon. Another interpretation is that an Item is something that can exist inside an Inventory. The core functionality of an Item needs to be able to determine whether it is equal to another Item (i.e. through a `.equals(Item)` method), likely by comparing a name or ID. An Item may also expose attributes such as name and ID (which would be intended to be unique in the game, and may be a simpler data type than the name). More functionality of an Item will likely either be delegated to implementations or added when the game design is more developed (e.g. sell price).

### Inventory

An Inventory should represent a collection of Items, potentially ordered. The core functionality of an Inventory needs to be able to allow adding Item's to the Inventory and track how many copies of each Item has been added. An Inventory does not need to expose how it internally stores Item's, only provide functionality to check the count of an Item. Items should be considered identical (and counted with the same count) if they are equal by their `.equals(Item)` method. An Inventory may also expose attributes such as the total number of Items it contains, the number of different Items it contains, etc.

### Loot

A Loot should represent a combination of an Entity and an Inventory, i.e. an Inventory that has a real-world presence in the game map. The core functionality of a Loot needs to allow access to its Inventory (either directly or through specific methods), especially to be able to read all items in its Inventory, and have a physical presence on the game world. Having a physical presence could be achieved by extending Entity, but also may provide a perfect opportunity to rework Jerra's architecture so that Entity and Loot both contain a common attribute (e.g. Physical) that gives them their physical presence.

### Effect

An Effect should represent a set of mutations (e.g. damage, acceleration, etc) that could be applied to a conceptual entity (which might be an Entity). At this point, the core functionality of an Effect should include a damage attribute. An Effect should also implement a combine method, which combines the effect with another (e.g. `Effect combine(Effect)`).
