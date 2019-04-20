Intentionally blank directory.

Sometimes we encounter some domain logic that involves different entities, of the same type or not, and we feel that that domain logic does not belong in the entities themselves, we feel that that logic is not their direct responsibility.

So our first reaction might be to place that logic outside the entities, in an Application Service. However, this means that that domain logic will not be reusable in other use cases: domain logic should stay out of the application layer!

The solution is to create a Domain Service, which has the role of receiving a set of entities and performing some business logic on them. A Domain Service belongs to the Domain Layer, and therefore it knows nothing about the classes in the Application Layer, like the Application Services or the Repositories. In the other hand, it can use other Domain Services and, of course, the Domain Model objects.

Characterstics of a domain service are:
* Domain services encapsulate domain logic and concepts that are not naturally modeled as value objects or entities in your model.
* Domain services represent domain concepts; they are part of the ubiquitous language.
* Domain services have no identity or state; their responsibility is to orchestrate business logic using entities and value objects.
* Too many domain services can lead to an anemic domain model that does not align well with the problem domain.
* Too few domain services can lead to logic being incorrectly located on entities or value objects. This causes distinct concepts to be mixed up, which reduces clarity.
