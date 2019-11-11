[![Build Status](https://travis-ci.org/dustinsand/hex-arch-kotlin-spring-boot.svg?branch=master)](https://travis-ci.org/dustinsand/hex-arch-kotlin-spring-boot) 

# Hexagonal Architecture (aka Ports and Adapters) / Clean Architecture / DDD

“Create your application to work without either a UI or a database so you can run automated regression-tests against the application, work when the database becomes unavailable, and link applications together without any user involvement.”  –Alistair Cockburn

Personally I've used hexagonal architectures with DDD for years because it aligns well with how I reason about designing applications.  However, I'm not aware of a "standard" project structure for JVM microservices so this is my interpretation.  If a canonical project structure exists, please let me know.  I've seen a different interpretation of project structures from each engineer I've learned this architecture from.

Thank you to all the engineers who have shared their knowledge on this topic.  If you are unfamiliar with this architecture, please review the references first.  This project is my interpretation of how to design and implement a hexagonal architecture for the JVM.

# Project Description

Used to explain the project structure for a microservice using a hexagonal architecture with Kotlin and Spring Boot.  The sample code is intentionally simple in order to focus on how to structure the packages for a hexagonal architecture.  

# Objective Of This Architecture

* Platform Agnostic. Architecture can be used for applications developed for modern programming platforms such as the JVM, Go, .Net, Python, Node, etc.  
* Maintainable. Consistently structured software which is easy to understand, navigate and reason about.
* Encourage a separation of concerns through layers of responsibility.
* Evolutionary design. Easy to change, loosely coupled.
* Independent of Frameworks. The architecture does not depend on the existence of some library of feature laden software. This allows you to use such frameworks as tools, rather than having to cram your system into their limited constraints.
* Independent of UI. The UI can change easily, without changing the rest of the system. A Web UI could be replaced with a console UI, for example, without changing the business rules.
* Independent of Database. You can swap out Oracle or SQL Server, for Mongo, BigTable, CouchDB, or something else. Your business rules are not bound to the database.
* Independent of any external agency. In fact your business rules simply don’t know anything at all about the outside world.
* Modular. Minimizes tangled dependencies by decoupling the business logic from the technical code. Reduces the friction of extracting core domain objects to another context. Sometimes it makes design sense to start as more of a monolith and extract later to another context when there are strong reasons to do so.
* Testable. The business rules can be tested without the UI, Database, Web Server, or any other external element.

# How it works
Inputs and outputs are at the outermost layer of the hexagon, the edge of the application.  The domain layer of the hexagon, the core, is insulated from the concerns at the edge of the application.  The inputs and outputs at the edge allow the design to be switched with other technologies without impacting the core of the application. 

Outer layers depend on inner layers. Inner layers expose interfaces that outer layers must adapt to and implement. This form of dependency inversion protects the integrity of the domain and application layers. Outside of the application layer, we have ports (Interfaces) and adapters (Implementations) that handle messages from the outside world and map them to the appropriate application services handled in the domain layer. The resulting message from the application services are then passed back through the adapter as an appropriate response. 

![voter_hex_diagram](https://user-images.githubusercontent.com/5289/63655608-29921600-c758-11e9-8fb0-94b934edcdf8.png)

# Package Structure
## domain
Isolated from technical complexities of clients, frameworks and adapter (infrastructure) concerns. Contains the business models. Dependencies face inward so it does not depend on any layers outside of it. Why is this important?  Adapters (Infrastructure) can be changed without changes to the domain.

The objects in this layer contain the data and the logic pertaining to the business. Independent of the business processes that trigger that logic, they are independent and completely unaware of the Application Layer.

The business objects that represent something in the domain. Examples of these objects are, first of all, Entities but also Value Objects, Enums and any objects used in the Domain Model.

The Domain Model is also where Domain Events “live”. These events are triggered when a specific set of data changes and they carry those changes with them. In other words, when an entity changes, a Domain Event is triggered and it carries the changed properties new values. These events are perfect, for example, to be used in Event Sourcing.
### model
Using DDD this is where you define the Entities, Aggregates, Value Objects and Domain Events to model your domain. 

[Refer to the DDD tactical patterns for descriptions](http://dddsample.sourceforge.net/patterns-reference.html)

### service
Sometimes we encounter some domain logic that involves different entities, of the same type or not, and we feel that that domain logic does not belong in the entities themselves, we feel that that logic is not their direct responsibility.

So our first reaction might be to place that logic outside the entities, in an Application Service. However, this means that that domain logic will not be reusable in other use cases: domain logic should stay out of the application layer!

The solution is to create a Domain Service, which has the role of receiving a set of entities and performing some business logic on them. A Domain Service belongs to the Domain Layer, and therefore it knows nothing about the classes in the Application Layer, like the Application Services or the Repositories. In the other hand, it can use other Domain Services and, of course, the Domain Model objects.

Characteristics of a domain service are:
* Domain services encapsulate domain logic and concepts that are not naturally modeled as value objects or entities in your model.
* Domain services represent domain concepts; they are part of the ubiquitous language.
* Domain services have no identity or state; their responsibility is to orchestrate business logic using entities and value objects.
* Too many domain services can lead to an anemic domain model that does not align well with the problem domain.
* Too few domain services can lead to logic being incorrectly located on entities or value objects. This causes distinct concepts to be mixed up, which reduces clarity.

## application layer

### port

#### input
This layer defines use case interfaces of the application.

#### output
This layer defines infrastructure interfaces implemented by the adapters so there is no coupling between domain layer and technical code.

### service
The API representing the business use cases of the application. Dependent on the domain layer to orchestrate the business use cases and acts as a facade to the domain.  The domain model can continue to evolve without impacting clients. This layer is also responsible for coordinating notifications to other systems when significant events occur within the domain. 

Clients must adapt to the input defined by the API and also transform the output from the API into their own format. The application layer then acts as an anti-corruption layer, ensuring the domain layer stays unaffected by external technical details.

Example role of an Application Service to fulfill a use case:

1. use a repository to find one or several entities
2. tell those entities to do some domain logic
3. and use the repository to persist the entities again

## adapter layer
The adapter layer provides the technical capabilities of the application to be consumed, such as a UI, web services, messaging endpoints. It also provides the  application the ability to consume external services such as databases, 3rd party services, logging, security and other bounded contexts.  These are all technical details that should not directly affect the use case exposed and the domain logic of an application. Typically hexagonal architectures diagram the left side (see "in" below) for the the clients which use the domain.  The right side (see "out" below) of the diagram are the services used by the domain.  

### input (preferred 'in' for the name, but 'in' is a reserved word in Kotlin)
The entry point (left side of diagram below in green) of clients to use the application layer. The inbound adapter translates whatever comes from a client into a method call in the application layer.

### output (preferred 'out' for the name, but 'out' is a reserved word in Kotlin)
These are the outbound adapter technical details that the application uses (right side of diagram below in tan), for example, a database, a 3rd party APIs.  These are needed to support the domain use cases.

# Communication Across Layers
When communicating across layers, to prevent exposing the details of the domain model to the outside world, you don’t pass domain objects across boundaries. For the same reasons, you don’t send raw, unchecked user input straight into the domain layer. Instead, you use simple data transfer objects (DTOs), presentation models, and application event objects to communicate changes or actions in the domain.

# Testing in Isolation
Separating the concerns in the application and ensuring the domain logic is not dependent on any technical details allows you to test domain and application logic in isolation independent of any adapter frameworks.

The application layer is more appropriate for integration testing and the domain layer is more appropriate for unit testing with mocks.

# Enforcing the Architecture

The architecture is at risk of eroding over time if boundaries between layers are not maintained. [ArchUnit](https://www.archunit.org/) is used to check if the code follows the architecture. ArchUnit does so by analyzing the  given Java bytecode, importing all classes into a Java code structure. ArchUnit’s main focus is to automatically test architecture and coding rules, using any plain Java unit testing framework. See src/test/kotlin/.../HexagonalArchitectureTest.kt for the rules checked.  

# TL;DR Show me a diagram

The references have great example diagrams to visually explain how the components fit together so no point reinventing the wheel here.  [Herberto Graca](https://herbertograca.com/2017/11/16/explicit-architecture-01-ddd-hexagonal-onion-clean-cqrs-how-i-put-it-all-together/) has a diagram which is one of my favorites showing all the components together.

![herbertograca_hex_diagram](https://user-images.githubusercontent.com/5289/50517936-e70d2200-0a80-11e9-9382-d124912c27f3.png)

# References
* http://wiki.c2.com/?HexagonalArchitecture
* http://domainlanguage.com/ddd/
* http://www.wrox.com/WileyCDA/WroxTitle/Patterns-Principles-and-Practices-of-Domain-Driven-Design.productCd-1118714709.html
* https://www.youtube.com/watch?v=0wAvVcrbVK4&t=1513s 
* https://github.com/lievendoclo/devoxx-2017
* https://herbertograca.com/2017/11/16/explicit-architecture-01-ddd-hexagonal-onion-clean-cqrs-how-i-put-it-all-together/
* http://fideloper.com/hexagonal-architecture
* https://8thlight.com/blog/uncle-bob/2012/08/13/the-clean-architecture.html
* https://dzone.com/articles/hexagonal-architecture-is-powerful
* https://www.culttt.com/2014/12/31/hexagonal-architecture/
* https://skillsmatter.com/skillscasts/5744-decoupling-from-asp-net-hexagonal-architectures-in-net
* https://beyondxscratch.com/2017/08/19/decoupling-your-technical-code-from-your-business-logic-with-the-hexagonal-architecture-hexarch/
* https://softwarecampament.wordpress.com/portsadapters/
* https://www.youtube.com/watch?v=cPH5AiqLQTo
* https://leanpub.com/get-your-hands-dirty-on-clean-architecture
