# Hexagonal Architecture (aka Ports and Adapters) and DDD

“Create your application to work without either a UI or a database so you can run automated regression-tests against the application, work when the database becomes unavailable, and link applications together without any user involvement.”  –Alistair Cockburn

Personally I've used hexagonal architectures with DDD for years because it matches how I reason about designing applications.  However, I'm not aware of a "standard" project structure for JVM micro services which other engineers are also familiar with so I've created my own interpretation.  If a canonical project structure exists, please let me know.  I've seen a different interpretation of project structures from each engineer I've learned this architecture from.

Thank you to all the engineers who have shared their knowledge on this topic.  This project is a derivative of their work mentioned in the references. If you are not familiar with this architecture, please review the references first.

# Project Description

Used to explain the project structure for a micro service using a hexagonal architecture with Kotlin and Spring Boot. The sample code is intentionally simple in order to focus on how to structure the packages for a hexagonal architecture. 

# Flow
The flow of control goes from the entry port into the Application Core, over to the infrastructure tools, back to the Application Core and finally back to the user.

# Package Structure
## core
### application
Orchestrates the work in the domain model and used by the ports.

Example role of an Application Service to fulfill a use case:

1. use a gateway to find one or several entities
2. tell those entities to do some domain logic
3. and use the gateway to persist the entities again
### domain
The objects in this layer contain the data and the logic to manipulate that data, that is specific to the Domain itself and it’s independent of the business processes that trigger that logic, they are independent and completely unaware of the Application Layer.

The business objects that represent something in the domain. Examples of these objects are, first of all, Entities but also Value Objects, Enums and any objects used in the Domain Model.

The Domain Model is also where Domain Events “live”. These events are triggered when a specific set of data changes and they carry those changes with them. In other words, when an entity changes, a Domain Event is triggered and it carries the changed properties new values. These events are perfect, for example, to be used in Event Sourcing.
#### model
An object that is identified by its consistent thread of continuity, as opposed to traditional objects, which are defined by their attributes.

Repository for the domain model that encapsulates access to an external system or resource.

### service
Sometimes we encounter some domain logic that involves different entities, of the same type or not, and we feel that that domain logic does not belong in the entities themselves, we feel that that logic is not their direct responsibility.

So our first reaction might be to place that logic outside the entities, in an Application Service. However, this means that that domain logic will not be reusable in other use cases: domain logic should stay out of the application layer!

The solution is to create a Domain Service, which has the role of receiving a set of entities and performing some business logic on them. A Domain Service belongs to the Domain Layer, and therefore it knows nothing about the classes in the Application Layer, like the Application Services or the Repositories. In the other hand, it can use other Domain Services and, of course, the Domain Model objects.

## port
A port is a consumer agnostic entry and exit point to/from the application.
### in
The entry point and used it to tell the Application Core what to do. They translate whatever comes from a delivery mechanism into a method call in the Application Core.
### out
The infrastructure tools that the application uses, for example, a database, a 3rd party APIs.  These ports are needed to support the use cases.

It contains code that your application uses but it is not actually your application.  

# References
* http://wiki.c2.com/?HexagonalArchitecture
* https://www.youtube.com/watch?v=0wAvVcrbVK4&t=1513s 
* https://github.com/lievendoclo/devoxx-2017
* https://herbertograca.com/2017/11/16/explicit-architecture-01-ddd-hexagonal-onion-clean-cqrs-how-i-put-it-all-together/
* http://fideloper.com/hexagonal-architecture
* https://8thlight.com/blog/uncle-bob/2012/08/13/the-clean-architecture.html
* https://dzone.com/articles/hexagonal-architecture-is-powerful
* https://www.culttt.com/2014/12/31/hexagonal-architecture/
* https://skillsmatter.com/skillscasts/5744-decoupling-from-asp-net-hexagonal-architectures-in-net