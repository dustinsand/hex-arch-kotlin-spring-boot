# Hexagonal Architecture (aka Ports and Adapters) and DDD

“Create your application to work without either a UI or a database so you can run automated regression-tests against the application, work when the database becomes unavailable, and link applications together without any user involvement.”  –Alistair Cockburn

Personally I've used hexagonal architectures with DDD for years because it matches how I reason about designing applications.  However, I'm not aware of a "standard" project structure for JVM micro services which other engineers are also familiar with so this is my interpretation.  If a canonical project structure exists, please let me know.  I've seen a different interpretation of project structures from each engineer I've learned this architecture from.

Thank you to all the engineers who have shared their knowledge on this topic.  This project is a derivative of their work mentioned in the references. If you are not familiar with this architecture, please review the references first.

# Project Description

Used to explain the project structure for a micro service using a hexagonal architecture with Kotlin and Spring Boot. The sample code is intentionally simple in order to focus on how to structure the packages for a hexagonal architecture. 

# Flow
Outer layers depend on inner layers. Inner layers expose interfaces that outer layers must adapt to and implement. This form of dependency inversion protects the integrity of the domain and application layers.

# Package Structure
## core
Isolated from technical complexities of clients, frameworks and infrastructure concerns. Dependencies face inward so it does not depend on any layers outside of it.
### application layer
The API representing the business use cases of the application. Dependent on the domain layer to orchestrate the business use cases and acts as a facade to the domain.  The domain model can continue to evolve without impacting clients. This layer is also responsible for coordinating notifications to other systems when significant events occur within the domain. 

Clients must adapt to the input defined by the API and also transform the output from the API into their own format. The application layer then acts as an anti-corruption layer, ensuring the domain layer stays unaffected by external technical details.

This layer defines repository interfaces implemented by the infrastructure so there is no coupling between domain layer and technical code.

Example role of an Application Service to fulfill a use case:

1. use a repository to find one or several entities
2. tell those entities to do some domain logic
3. and use the repository to persist the entities again
### domain layer
The objects in this layer contain the data and the logic pertaining to the business. Independent of the business processes that trigger that logic, they are independent and completely unaware of the Application Layer.

The business objects that represent something in the domain. Examples of these objects are, first of all, Entities but also Value Objects, Enums and any objects used in the Domain Model.

The Domain Model is also where Domain Events “live”. These events are triggered when a specific set of data changes and they carry those changes with them. In other words, when an entity changes, a Domain Event is triggered and it carries the changed properties new values. These events are perfect, for example, to be used in Event Sourcing.
#### model
An object that is identified by its consistent thread of continuity, as opposed to traditional objects, which are defined by their attributes.

Repository for the domain model that encapsulates access to an external system or resource.

### service
Sometimes we encounter some domain logic that involves different entities, of the same type or not, and we feel that that domain logic does not belong in the entities themselves, we feel that that logic is not their direct responsibility.

So our first reaction might be to place that logic outside the entities, in an Application Service. However, this means that that domain logic will not be reusable in other use cases: domain logic should stay out of the application layer!

The solution is to create a Domain Service, which has the role of receiving a set of entities and performing some business logic on them. A Domain Service belongs to the Domain Layer, and therefore it knows nothing about the classes in the Application Layer, like the Application Services or the Repositories. In the other hand, it can use other Domain Services and, of course, the Domain Model objects.

## infrastructure layer
The infrastructure layer provides the technical capabilities of the application to be consumed, such as a UI, web services, messaging endpoints. It also provides the  application the ability to consume external services such as databases, 3rd party services, logging, security and other bounded contexts.  These are all technical details that should not dirctly affect the use case exposed and the domain logic of an application. 
### in
The entry point (inbound) of clients to use the application layer. The inbound infrastructure translates whatever comes from a client into a method call in the application layer.
### out
These are the outbound infrastructure technical details that the application uses, for example, a database, a 3rd party APIs.  These are needed to support the use cases.

# Communication Across Layers
When communicating across layers, to prevent exposing the details of the domain model to the outside world, you don’t pass domain objects across boundaries. For the same reasons, you don’t send raw unsolicited data or user input straight into the domain layer. Instead, you use simple data transfer objects (DTOs), presentation models, and application event objects to communicate changes or actions in the domain.

# Testing in Isolation
Separating the concerns in the application and ensuring the domain logic is not dependent on any technical details allows you to test domain and application logic in isolation independent of any infrastructure frameworks.

The application layer is more appropriate for integration testing and the domain layer is more appropriate for unit testing with mocks.
# References
* http://wiki.c2.com/?HexagonalArchitecture
* http://www.wrox.com/WileyCDA/WroxTitle/Patterns-Principles-and-Practices-of-Domain-Driven-Design.productCd-1118714709.html
* https://www.youtube.com/watch?v=0wAvVcrbVK4&t=1513s 
* https://github.com/lievendoclo/devoxx-2017
* https://herbertograca.com/2017/11/16/explicit-architecture-01-ddd-hexagonal-onion-clean-cqrs-how-i-put-it-all-together/
* http://fideloper.com/hexagonal-architecture
* https://8thlight.com/blog/uncle-bob/2012/08/13/the-clean-architecture.html
* https://dzone.com/articles/hexagonal-architecture-is-powerful
* https://www.culttt.com/2014/12/31/hexagonal-architecture/
* https://skillsmatter.com/skillscasts/5744-decoupling-from-asp-net-hexagonal-architectures-in-net