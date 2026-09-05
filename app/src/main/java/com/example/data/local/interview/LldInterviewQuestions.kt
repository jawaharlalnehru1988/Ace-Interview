package com.example.data.local.interview

import com.example.domain.model.InterviewQuestion

/**
 * 165 Low-Level Design (LLD), Object-Oriented Design & Clean Architecture Interview Questions.
 * Split across 9 private part methods to remain well under the 64KB JVM method bytecode limit.
 */
object LldInterviewQuestions {

    fun getQuestions(): List<InterviewQuestion> =
        part1() + part2() + part3() + part4() + part5() + part6() + part7() + part8() + part9()

    private fun part1(): List<InterviewQuestion> = listOf(
        InterviewQuestion(
            id = "iq_lld_001",
            trackId = "lld_interview",
            conceptId = "lld_solid_clean_code",
            conceptName = "SOLID Principles & Clean Code Architecture",
            title = "Liskov Substitution Principle (LSP) and the Rectangle-Square Problem",
            question = "Explain the Liskov Substitution Principle (LSP). Why does having a `Square` inherit from a `Rectangle` violate LSP, and how do you resolve it in clean object-oriented design?",
            shortAnswer = "LSP states that objects of a superclass should be replaceable with objects of its subclasses without altering program correctness or breaking client expectations. A Square inheriting from Rectangle violates LSP: if Rectangle has independent `setWidth(w)` and `setHeight(h)` methods, overriding them in Square to keep both dimensions equal breaks the caller's invariant expectation that modifying width leaves height untouched. Solution: Square and Rectangle should not have an inheritance relationship. Both can implement a common `Shape` interface with an `area()` method, or use immutability where dimensions are set at construction.",
            keyPoints = listOf(
                "Subtypes must be substitutable for their base types without altering expected program behavior",
                "Subclasses cannot strengthen preconditions or weaken postconditions established by the parent",
                "Square extending Rectangle breaks the fundamental invariant of independent width and height mutations",
                "Throwing UnsupportedOperationException in a subclass method is a hallmark symptom of an LSP violation",
                "Favor composition or shared abstract interfaces (e.g. Shape) over incorrect taxonomic inheritance hierarchies"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_lld_002",
            trackId = "lld_interview",
            conceptId = "lld_solid_clean_code",
            conceptName = "SOLID Principles & Clean Code Architecture",
            title = "Dependency Inversion Principle (DIP) vs Dependency Injection (DI)",
            question = "What is the Dependency Inversion Principle (DIP), and how does it fundamentally differ from Dependency Injection (DI) and Inversion of Control (IoC)?",
            shortAnswer = "DIP is a high-level architectural principle stating: (1) High-level modules should not depend on low-level modules; both should depend on abstractions. (2) Abstractions should not depend on details; details should depend on abstractions. Inversion of Control (IoC) is a broader design paradigm where the control flow of a program is inverted (e.g. frameworks calling application code). Dependency Injection (DI) is a specific creational design pattern that realizes DIP by passing dependencies into a class via constructors, setters, or interfaces rather than having the class instantiate them via `new`.",
            keyPoints = listOf(
                "DIP dictates that business logic modules must depend on abstract interfaces, never on concrete classes",
                "Inverts traditional dependency graphs: domain cores define interfaces that infrastructure adapters implement",
                "Dependency Injection (DI) is the structural mechanism used to supply implementations to dependent classes",
                "Inversion of Control (IoC) is the overarching architectural pattern shifting lifecycle control to a container",
                "Enables comprehensive unit testing by allowing mock or stub implementations to be injected effortlessly"
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_lld_003",
            trackId = "lld_interview",
            conceptId = "lld_solid_clean_code",
            conceptName = "SOLID Principles & Clean Code Architecture",
            title = "Single Responsibility Principle (SRP) and Cohesion vs Coupling",
            question = "How do you define the Single Responsibility Principle (SRP)? What is Robert C. Martin's definition regarding 'actors', and how does high cohesion relate to loose coupling?",
            shortAnswer = "SRP states that a class or module should have one, and only one, reason to change. Uncle Bob defines it as: 'A module should be responsible to one, and only one, actor' (stakeholder or user group). For example, combining report financial calculations (CFO actor), employee hours tracking (COO actor), and database saving (CTO actor) in one `Employee` class violates SRP. Cohesion measures how closely related and focused the responsibilities within a single class are; coupling measures inter-module dependencies. High cohesion within classes naturally drives loose coupling between classes.",
            keyPoints = listOf(
                "SRP defines a responsibility as a single reason to change, tied to a specific business actor or stakeholder",
                "Violating SRP couples disparate business requirements, causing unrelated features to break during edits",
                "High cohesion ensures all methods and fields in a class directly serve a single focused domain purpose",
                "Loose coupling minimizes inter-class dependencies, allowing components to be modified independently",
                "Refactor God objects by extracting distinct collaborators (e.g. EmployeeRepository, PayrollCalculator, TimeTracker)"
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_lld_004",
            trackId = "lld_interview",
            conceptId = "lld_solid_clean_code",
            conceptName = "SOLID Principles & Clean Code Architecture",
            title = "Open-Closed Principle (OCP) and Replace Conditional with Polymorphism",
            question = "How do you enforce the Open-Closed Principle (OCP) in production code? How does the 'Replace Conditional with Polymorphism' refactoring eliminate branching code smells?",
            shortAnswer = "OCP mandates that software entities should be open for extension, but closed for modification. When new requirements arise, developers should write new code rather than modifying tested existing code. A classic OCP violation is a massive `switch(shapeType)` or `if-else` chain that draws shapes or calculates discounts. The 'Replace Conditional with Polymorphism' refactoring introduces an abstract interface (`Shape` or `DiscountStrategy`) with an abstract method. Each condition becomes a distinct concrete class (`Circle`, `Square`, `SeniorDiscount`). Adding a new type requires adding a new class without modifying existing classes.",
            keyPoints = listOf(
                "Software entities should be open for extension (new behavior) but closed for modification (existing code)",
                "Switch statements checking type enums indicate an OCP violation and code smell across call sites",
                "Replace Conditional with Polymorphism extracts branches into separate classes implementing a shared interface",
                "Strategy and Factory patterns are the primary object-oriented design mechanisms used to satisfy OCP",
                "Protects existing, battle-tested code from regression bugs when introducing new business variations"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_lld_005",
            trackId = "lld_interview",
            conceptId = "lld_solid_clean_code",
            conceptName = "SOLID Principles & Clean Code Architecture",
            title = "Interface Segregation Principle (ISP) and Fat Interfaces",
            question = "What is the Interface Segregation Principle (ISP)? How do 'Fat Interfaces' force unwanted dependencies on clients, and how do you decompose them into Role Interfaces?",
            shortAnswer = "ISP states that clients should not be forced to depend upon interfaces that they do not use. A 'Fat Interface' bundles dozens of unrelated methods into a single interface (e.g. `MultiFunctionPrinter` with `print()`, `scan()`, `fax()`, `staple()`). A simple `BasicPrinter` forced to implement this must provide dummy implementations or throw `UnsupportedOperationException` for scanning and faxing. ISP decomposes fat interfaces into cohesive Role Interfaces (`Printer`, `Scanner`, `Fax`). Classes implement only the role interfaces they support, and clients depend only on the specific role methods they require.",
            keyPoints = listOf(
                "Clients should never be forced to implement or depend upon interface methods they do not utilize",
                "Fat or monolithic interfaces create artificial coupling, forcing dummy empty or exception-throwing implementations",
                "Role Interfaces decompose capabilities into small, client-specific contracts (e.g. Printable, Closeable, Serializable)",
                "Promotes multiple interface implementation: complex classes compose multiple small role interfaces cleanly",
                "Reduces client re-compilation and re-testing overhead when unrelated interface methods are modified"
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_lld_006",
            trackId = "lld_interview",
            conceptId = "lld_solid_clean_code",
            conceptName = "SOLID Principles & Clean Code Architecture",
            title = "Composition over Inheritance and the Fragile Base Class Problem",
            question = "Why is 'Composition over Inheritance' a cornerstone of robust low-level design? What is the Fragile Base Class Problem, and how does composition prevent it?",
            shortAnswer = "Inheritance creates the tightest coupling in OOP: subclasses depend on the private implementation details of parent classes. The Fragile Base Class problem occurs when a modification to a parent class inadvertently breaks subclass invariants (e.g. Java's `HashSet` `addAll` internally invoking `add`, breaking a counting subclass that overrides both). Inheritance also causes class explosion and lacks runtime flexibility. Composition delegates responsibilities to collaborator objects held as private references (`has-a` instead of `is-a`), allowing behaviors to be swapped at runtime and keeping internal state encapsulated.",
            keyPoints = listOf(
                "Inheritance breaks encapsulation because subclasses are intimately exposed to superclass implementation details",
                "Fragile Base Class problem: changing a parent class's internal method calls silently breaks subclass behavior",
                "Composition models 'has-a' relationships, allowing collaborator implementations to be replaced dynamically at runtime",
                "Prevents combinatorial class explosion that occurs when trying to extend multiple orthogonal dimensions via inheritance",
                "Decorator and Strategy patterns rely directly on composition to extend class capabilities without subclassing"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_lld_007",
            trackId = "lld_interview",
            conceptId = "lld_solid_clean_code",
            conceptName = "SOLID Principles & Clean Code Architecture",
            title = "Law of Demeter (Principle of Least Knowledge)",
            question = "What is the Law of Demeter (LoD)? Why are 'train wrecks' (`a.getB().getC().getD().doSomething()`) an anti-pattern, and how do you refactor them using 'Tell, Don't Ask'?",
            shortAnswer = "The Law of Demeter states that a method \$M\$ of an object \$O\$ should invoke only methods of: (1) \$O\$ itself, (2) parameters passed to \$M\$, (3) objects created within \$M\$, or (4) direct instance fields of \$O\$. 'Train wrecks' (chains of getters like `order.getCustomer().getAddress().getCity()`) violate LoD by coupling the caller to the internal object graph structure of multiple collaborators; if the schema of `Address` changes, unrelated callers break. LoD is resolved using 'Tell, Don't Ask': move the behavior to the owning object (e.g. `order.getDeliveryCity()` or `customer.deliverTo(package)`).",
            keyPoints = listOf(
                "Law of Demeter limits object interactions to immediate neighbors, preventing knowledge of deep object graphs",
                "Train wreck getter chains tightly couple callers to the structural traversal path of intermediate objects",
                "Violates encapsulation by asking for internal state to make decisions rather than telling objects what to do",
                "'Tell, Don't Ask' principle moves business logic into the entity that actually holds the necessary data",
                "Fluent builders and streams are legitimate exceptions to LoD because they return representations, not internal structural objects"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_lld_008",
            trackId = "lld_interview",
            conceptId = "lld_solid_clean_code",
            conceptName = "SOLID Principles & Clean Code Architecture",
            title = "Code Smells: Feature Envy vs Shotgun Surgery",
            question = "Compare the 'Feature Envy' and 'Shotgun Surgery' code smells. How do you detect them during code reviews, and what specific refactoring patterns eliminate them?",
            shortAnswer = "1) Feature Envy: Occurs when a method in Class A accesses the getters and data fields of Class B more than its own data (e.g. `OrderService` calculating tax by repeatedly pulling 8 fields from `Address`). Detection: Heavy getter usage on an external collaborator. Fix: 'Move Method' or 'Extract Method' to relocate the logic directly into Class B where the data lives. 2) Shotgun Surgery: The opposite of Divergent Change; occurs when making a single small conceptual business change forces small edits across 10 different files. Detection: High commit file count for minor feature tweaks. Fix: 'Move Method' and 'Move Field' to consolidate the scattered responsibilities into a single cohesive class.",
            keyPoints = listOf(
                "Feature Envy: a method seems more interested in the data of another class than the class it actually resides in",
                "Feature Envy is resolved using Move Method or Extract Method, placing behavior where the underlying data lives",
                "Shotgun Surgery: a single business change requires touching and editing dozens of scattered classes across the project",
                "Shotgun Surgery indicates poor encapsulation and is resolved by consolidating related logic into a single cohesive module",
                "Divergent Change (one class altered by multiple unrelated reasons) is the complementary opposite of Shotgun Surgery"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_lld_009",
            trackId = "lld_interview",
            conceptId = "lld_solid_clean_code",
            conceptName = "SOLID Principles & Clean Code Architecture",
            title = "Primitive Obsession and Value Objects in Clean Architecture",
            question = "What is the 'Primitive Obsession' code smell? Why should domain concepts like `EmailAddress`, `Money`, and `PhoneNumber` be modeled as immutable Value Objects rather than raw strings or floats?",
            shortAnswer = "Primitive Obsession is the anti-pattern of using basic language primitives (strings, integers, floats) to model rich domain concepts. Problems: (1) Lack of validation: A string variable `email` can contain `'invalid-text'`, requiring validation checks scattered everywhere. (2) Accidental parameter transposition: A method `createCustomer(String firstName, String lastName, String email)` allows passing email as the first name without compiler warnings. (3) Float rounding bugs: Using float for currency causes precision loss. Value Objects encapsulate validation at construction, guarantee immutability, enforce type safety, and co-locate domain operations (e.g. `money.add(otherMoney)`).",
            keyPoints = listOf(
                "Primitive Obsession models rich domain concepts using raw primitives (String, int, double) without domain behavior",
                "Causes duplicated validation logic scattered across services, controllers, and database mappers",
                "Value Objects enforce domain invariants and validation rules at instantiation (guaranteed valid state)",
                "Value Objects are defined by their attributes (equality based on value, not identity) and are strictly immutable",
                "Enhances compile-time type safety: prevents passing an OrderId into a method expecting a CustomerId"
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_lld_010",
            trackId = "lld_interview",
            conceptId = "lld_solid_clean_code",
            conceptName = "SOLID Principles & Clean Code Architecture",
            title = "Anemic Domain Model vs Rich Domain Model",
            question = "What is an Anemic Domain Model (Martin Fowler anti-pattern)? Contrast it with a Rich Domain Model, and explain how anemic models violate object-oriented design principles.",
            shortAnswer = "An Anemic Domain Model consists of entity classes that contain only data fields and public getters/setters with zero business logic. All business rules, validation, and calculations are placed into external procedural service classes (`OrderService`). Fowler considers this an anti-pattern because it reduces OOP to procedural C-style programming: entities become passive data bags, invariants cannot be protected (any caller can call `setStatus(INVALID)`), and business logic duplicates across multiple services. A Rich Domain Model co-locates business behavior, validation, and state mutations inside the entity itself, keeping setters private.",
            keyPoints = listOf(
                "Anemic Domain Model features entity classes with only getters/setters and zero encapsulated business logic",
                "Relegates object-oriented programming to procedural programming: active services operating on passive data structures",
                "Fails to protect domain invariants: public setters allow external code to mutate entities into invalid states",
                "Rich Domain Models encapsulate behavior and state together, exposing expressive domain methods (e.g. order.cancel())",
                "Domain Services should only be used for operations that naturally span multiple independent Aggregates"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_lld_011",
            trackId = "lld_interview",
            conceptId = "lld_solid_clean_code",
            conceptName = "SOLID Principles & Clean Code Architecture",
            title = "Defensive Programming vs Fail-Fast Principle",
            question = "When should you apply Defensive Programming vs the Fail-Fast Principle? What are the hazards of over-defensive programming (e.g. returning nulls or swallowing exceptions)?",
            shortAnswer = "Defensive Programming protects code at system boundaries (public APIs, user inputs, external network payloads) by sanitizing inputs and validating preconditions. However, within internal core domain boundaries, Over-Defensive Programming is dangerous: catching exceptions, logging, and returning `null` or empty strings masks severe bugs, allowing corrupted state to propagate until a cryptic crash occurs deep in the call stack. The Fail-Fast Principle states that internal system bugs, illegal arguments, and invariant violations should immediately throw runtime exceptions (`IllegalArgumentException`, `IllegalStateException`) as close to the source as possible, making bugs visible instantly.",
            keyPoints = listOf(
                "Defensive programming is mandatory at external public boundaries (user input, HTTP controllers, third-party APIs)",
                "Fail-Fast principle mandates throwing explicit exceptions immediately upon detecting invalid internal states",
                "Swallowing exceptions or returning null defaults hides bugs and allows corrupted data to propagate silently",
                "Null-checks scattered deep inside internal private methods indicate missing validation contracts at public boundaries",
                "Fail-fast behavior accelerates debugging and unit test discovery by localizing failure causes immediately"
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_lld_012",
            trackId = "lld_interview",
            conceptId = "lld_solid_clean_code",
            conceptName = "SOLID Principles & Clean Code Architecture",
            title = "Immutability and Pure Functions in Object-Oriented Design",
            question = "How does designing immutable objects improve thread safety, simplify reasoning, and eliminate side effects in multi-threaded application design?",
            shortAnswer = "An immutable object's state cannot be modified after construction. Key techniques: (1) Declare all fields `private final`, (2) Do not provide setters, (3) Deep-copy mutable collaborator arguments in constructors and defensive-copy return values, (4) Prevent subclassing (`final class`). Benefits: (a) Free Thread-Safety: Immutable objects can be shared across multiple threads without locks, synchronization, or volatile keywords. (b) Safe Hash Keys: State cannot change, guaranteeing consistent `hashCode()` in HashMaps. (c) Eliminates Temporal Coupling and Side Effects: Callers cannot mutate passed arguments behind the scenes.",
            keyPoints = listOf(
                "Immutable objects cannot be modified after construction: private final fields and defensive copying",
                "Inherently thread-safe: eliminates race conditions, deadlocks, and synchronization overhead across concurrent threads",
                "Guarantees safe usage as keys in HashMaps and elements in HashSets by preserving hashcode stability",
                "Eliminates temporal coupling where method execution order dictates object state validity",
                "Modern languages provide native immutable primitives (Java records, Kotlin data classes with val)"
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_lld_013",
            trackId = "lld_interview",
            conceptId = "lld_solid_clean_code",
            conceptName = "SOLID Principles & Clean Code Architecture",
            title = "Coupling Metrics: Afferent vs Efferent Coupling and Instability",
            question = "How do you calculate Afferent Coupling (\$Ca\$), Efferent Coupling (\$Ce\$), and Instability (\$I\$) of a software package? How does the Stable Abstractions Principle guide clean package architecture?",
            shortAnswer = "1) Afferent Coupling (\$Ca\$): Number of external classes that depend ON this package (incoming dependencies/responsibility). 2) Efferent Coupling (\$Ce\$): Number of external classes this package depends UPON (outgoing dependencies). 3) Instability Index (\$I\$): \$I = Ce / (Ca + Ce)\$. \$I = 0\$ means maximally stable (many depend on it, it depends on nothing, e.g. `java.lang`). \$I = 1\$ means maximally unstable (it depends on many, none depend on it). 4) Stable Abstractions Principle (SAP): A package's abstractness should increase with its stability: stable packages must be abstract (interfaces) to remain extensible; unstable packages should be concrete.",
            keyPoints = listOf(
                "Afferent Coupling (Ca) measures incoming dependencies: how many external packages rely on this package",
                "Efferent Coupling (Ce) measures outgoing dependencies: how many external packages this package relies upon",
                "Instability Index I = Ce / (Ca + Ce): ranges from 0 (maximally stable) to 1 (maximally volatile/unstable)",
                "Stable Dependencies Principle: dependencies should point in the direction of stability (towards lower I)",
                "Stable Abstractions Principle: stable packages should consist primarily of abstract interfaces to preserve extensibility"
            ),
            difficulty = "Staff / Principal"
        ),
        InterviewQuestion(
            id = "iq_lld_014",
            trackId = "lld_interview",
            conceptId = "lld_solid_clean_code",
            conceptName = "SOLID Principles & Clean Code Architecture",
            title = "Refactoring: Replace Type Code with State/Strategy Pattern",
            question = "How do you execute Martin Fowler's 'Replace Type Code with State/Strategy' refactoring? When should you choose State vs Strategy?",
            shortAnswer = "When a class has an integer or enum field controlling behavior via conditional branches (e.g. `Order.status` determining shipping logic), it violates OCP. Refactoring steps: (1) Create an abstract class or interface (`OrderState` or `ShippingStrategy`), (2) Create concrete subclasses for each type code value, (3) Move the conditional branch logic into the respective subclass methods, (4) In the context class (`Order`), replace the type code with a reference to the abstract state/strategy, delegating the call. Choose Strategy when the algorithm is selected by the client at initialization; choose State when the object's internal state transitions automatically during its lifecycle.",
            keyPoints = listOf(
                "Type codes with conditional switch statements create rigid code that breaks OCP during additions",
                "Creates an abstract class/interface and concrete subclasses for each enum/integer type code value",
                "Delegates domain method execution from the context class to the polymorphic state or strategy object",
                "Strategy pattern is preferred when algorithms are selected upfront and remain relatively stable during a task",
                "State pattern is preferred when an entity transitions through a defined lifecycle of states during execution"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_lld_015",
            trackId = "lld_interview",
            conceptId = "lld_solid_clean_code",
            conceptName = "SOLID Principles & Clean Code Architecture",
            title = "Null Object Pattern vs Optional<T>",
            question = "Compare the Null Object Pattern with Java's `Optional<T>`. When should you implement a Null Object instead of returning an empty `Optional`?",
            shortAnswer = "1) Optional<T>: A container type representing value presence or absence. Ideal for method return types where 'no result' is a valid business outcome (e.g. `findUserById()`), forcing callers to explicitly handle the empty case via `.map()`, `.orElse()`. Anti-pattern: using `Optional` as method parameters or class fields (serializability and memory overhead). 2) Null Object Pattern: A concrete subclass implementing an interface with no-op default behaviors (e.g. `NullLogger` doing nothing, `GuestUser` returning default guest permissions). Ideal when you want to eliminate null checks entirely from client code by providing safe do-nothing polymorphic behavior.",
            keyPoints = listOf(
                "Optional<T> forces callers to explicitly handle presence or absence, eliminating accidental NullPointerExceptions",
                "Optional should be used primarily as a method return type, never as class fields or method arguments",
                "Null Object pattern implements the interface with neutral do-nothing behavior (e.g. NullLogger, NullDiscount)",
                "Null Objects eliminate null checks completely by allowing callers to invoke methods polymorphically without branching",
                "Null Objects must be stateless and are commonly implemented as Singletons to conserve memory"
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_lld_016",
            trackId = "lld_interview",
            conceptId = "lld_solid_clean_code",
            conceptName = "SOLID Principles & Clean Code Architecture",
            title = "Fluent Interface Design and Method Chaining Hazards",
            question = "How do you design a clean Fluent Interface in Java? What are the potential architectural hazards of method chaining regarding debugging and error localization?",
            shortAnswer = "A Fluent Interface (Martin Fowler) provides readable, readable code by returning `this` from mutator methods: `order.withCustomer(c).withItem(i).build()`. Design rules: (1) Methods should return `this` or the next builder step interface, (2) Method names use domain prepositions (`with`, `and`, `to`, `for`). Hazards: (a) Obscured Stack Traces: Chaining 10 calls on a single line causes compiler stack traces to report a single line number; if a `NullPointerException` occurs, identifying which specific chained method produced null requires bytecode inspection. (b) Law of Demeter violations if chains traverse across unrelated objects.",
            keyPoints = listOf(
                "Fluent interfaces use method chaining returning 'this' to construct expressive, readable domain sentences",
                "Step Builder pattern can enforce mandatory parameters sequentially at compile-time using staged interfaces",
                "Debugging hazard: chaining multiple methods on a single source line obscures exact NPE failure line numbers",
                "Must be distinguished from train-wreck getter chains: fluent chaining on the same builder is valid; traversing graphs is not",
                "Ensure immutability where appropriate: fluent mutators on Value Objects should return fresh new instances"
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_lld_017",
            trackId = "lld_interview",
            conceptId = "lld_solid_clean_code",
            conceptName = "SOLID Principles & Clean Code Architecture",
            title = "Design by Contract: Preconditions, Postconditions, and Class Invariants",
            question = "What is Bertrand Meyer's Design by Contract (DbC) principle? How do Preconditions, Postconditions, and Class Invariants guarantee software correctness?",
            shortAnswer = "Design by Contract models software component collaboration as a binding legal contract: (1) Preconditions: Obligations that the caller must satisfy before invoking a method (e.g. `amount > 0`). If violated, the method refuses to execute. (2) Postconditions: Guarantees that the method promises to deliver upon successful completion (e.g. `balance == old_balance + amount`). (3) Class Invariants: Invariant assertions that must hold true for an object throughout its entire lifecycle, verified after constructor and public method calls (e.g. `accountNumber != null && balance >= overdraftLimit`).",
            keyPoints = listOf(
                "Preconditions define caller requirements: must be true before method execution (validated via assert or require)",
                "Postconditions define method guarantees: promised state transitions that hold true upon return",
                "Class Invariants define permanent truths about an object that must hold true throughout its active lifecycle",
                "Subclasses under LSP can weaken preconditions (accept more) but cannot strengthen them",
                "Subclasses under LSP can strengthen postconditions (guarantee more) but cannot weaken them"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_lld_018",
            trackId = "lld_interview",
            conceptId = "lld_solid_clean_code",
            conceptName = "SOLID Principles & Clean Code Architecture",
            title = "Command-Query Separation (CQS) at the Method Level",
            question = "What is Bertrand Meyer's Command-Query Separation (CQS) principle? Why should a method never perform a state mutation and return a value simultaneously?",
            shortAnswer = "CQS states that every method should either be a Command (performs an action and mutates state, returning `void`) or a Query (returns data to the caller without producing any observable side effects). A method must not do both: 'Asking a question should not change the answer.' For example, a method `getUser(id)` should never increment a login counter. Violating CQS makes code unpredictable, complicates testing, and creates hidden side effects. Exception: Atomic operations in concurrent programming (e.g. `Queue.poll()`, `AtomicInteger.incrementAndGet()`) where atomicity mandates returning the mutated value.",
            keyPoints = listOf(
                "Commands mutate system state and return void; Queries return data and produce zero observable side effects",
                "Asking a question should never modify the answer: calling a query multiple times must be idempotent",
                "Violating CQS creates hidden side effects that make debugging and unit testing erratic and difficult",
                "Separates write concerns from read projections, serving as the micro-level precursor to system-level CQRS",
                "Legitimate exceptions exist in concurrent data structures (e.g. Queue.poll()) where atomicity requires atomic read-modify"
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_lld_019",
            trackId = "lld_interview",
            conceptId = "lld_solid_clean_code",
            conceptName = "SOLID Principles & Clean Code Architecture",
            title = "YAGNI, KISS, and Over-Engineering Anti-Patterns in LLD Interviews",
            question = "How do candidates commonly over-engineer during Low-Level Design interviews? How do YAGNI (You Aren't Gonna Need It) and KISS keep object-oriented designs pragmatic?",
            shortAnswer = "Common interview over-engineering traps: (1) Forcing design patterns where simple composition suffices (e.g. using Abstract Factory + Bridge + Visitor for a simple 3-class problem). (2) Speculative generality: Adding generic abstractions, interfaces, and reflection hooks for theoretical future features that were never requested. YAGNI dictates implementing features only when actually needed, never in anticipation. KISS (Keep It Simple, Stupid) prioritizes readability and straightforward logic over complex meta-programming. Rule: Build the simplest working design that satisfies all functional and non-functional requirements, with clean extension points for real foreseeable changes.",
            keyPoints = listOf(
                "Over-engineering trap: applying complex design patterns preemptively where simple classes and methods suffice",
                "YAGNI (You Aren't Gonna Need It) eliminates speculative abstractions built for non-existent future requirements",
                "KISS (Keep It Simple, Stupid) prioritizes cognitive clarity and maintainability over clever architectural wizardry",
                "Interfaces should be introduced when multiple concrete implementations exist or unit test mocking requires them",
                "Refactor towards patterns as requirements evolve, rather than forcing patterns upfront into initial designs"
            ),
            difficulty = "Senior"
        )
    )
    private fun part2(): List<InterviewQuestion> = listOf(
        InterviewQuestion(
            id = "iq_lld_020",
            trackId = "lld_interview",
            conceptId = "lld_solid_clean_code",
            conceptName = "SOLID Principles & Clean Code Architecture",
            title = "Refactoring: Extract Class vs Inline Class",
            question = "When should you apply the 'Extract Class' refactoring vs the 'Inline Class' refactoring? How do you recognize when a class has grown too large or become a useless middleman?",
            shortAnswer = "1) Extract Class: Apply when a single class has grown too large and violates SRP by handling two distinct conceptual abstractions. Indicators: A subset of data fields and a subset of methods always operate together (e.g. `Person` containing `officeAreaCode`, `officeNumber`, `formatPhoneNumber()`). Solution: Extract a new `TelephoneNumber` class and hold a reference in `Person`. 2) Inline Class: The exact opposite. Apply when a class is no longer earning its keep: it does almost nothing, delegates all calls to another class (Lazy Class / Middleman code smell), or past refactorings have stripped away its responsibilities. Solution: Move all its features into the absorbing class and delete the middleman.",
            keyPoints = listOf(
                "Extract Class splits a bloated class when a distinct subset of fields and methods operate cohesively together",
                "Identifies co-occurring data clumps and extracts them into dedicated, self-contained collaborator classes",
                "Inline Class absorbs an underutilized Lazy Class whose responsibilities have diminished over time",
                "Eliminates useless Middleman classes that serve merely as pass-through forwarding wrappers",
                "Continuous refactoring oscillates between extracting and inlining to keep the object model balanced and minimal"
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_lld_021",
            trackId = "lld_interview",
            conceptId = "lld_creational_patterns",
            conceptName = "Creational Design Patterns & Object Construction",
            title = "Singleton Pattern: Double-Checked Locking and the volatile Keyword",
            question = "How do you implement a thread-safe Singleton using Double-Checked Locking in Java? Why is the `volatile` keyword strictly mandatory?",
            shortAnswer = "Double-Checked Locking checks if the instance is null before acquiring a synchronized lock, and checks again inside the lock before instantiating. The `volatile` keyword on the private static instance field is mandatory to prevent CPU/compiler instruction reordering. Object creation involves three bytecode steps: (1) allocate memory, (2) execute constructor, (3) assign memory address to reference. Without `volatile`, the JVM may reorder step 3 before step 2. Another concurrent thread checking `instance == null` outside the lock can observe a non-null reference that is only partially initialized, causing fatal runtime crashes when accessing its fields.",
            keyPoints = listOf(
                "First null check avoids expensive synchronization locking once the instance has been created",
                "Synchronized block ensures only one thread can execute the instantiation code at a time",
                "Second null check inside the synchronized block prevents waiting threads from creating duplicate instances",
                "volatile keyword creates a memory barrier preventing CPU instruction reordering during construction",
                "Without volatile, concurrent threads can observe a non-null but partially initialized object reference"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_lld_022",
            trackId = "lld_interview",
            conceptId = "lld_creational_patterns",
            conceptName = "Creational Design Patterns & Object Construction",
            title = "Bill Pugh Singleton vs Enum Singleton",
            question = "Compare the Bill Pugh Singleton (Initialization-on-Demand Holder) with Joshua Bloch's Enum Singleton. How do they defend against reflection, serialization, and class-loader attacks?",
            shortAnswer = "1) Bill Pugh Singleton: Uses a private static inner holder class: `private static class Holder { static final Singleton INSTANCE = new Singleton(); }`. The JVM guarantees lazy initialization: the inner class is not loaded into memory until `getInstance()` is called, achieving thread safety via the JVM class-loading mechanism without synchronization locks. However, it can be broken via Java Reflection (`setAccessible(true)`) or Serialization (unless implementing `readResolve()`). 2) Enum Singleton: `public enum Singleton { INSTANCE; }`. Recommended by Joshua Bloch as the best singleton. The JVM inherently prevents reflection instantiation (throws `IllegalArgumentException`), guarantees 100% serialization safety out-of-the-box, and provides thread-safe initialization.",
            keyPoints = listOf(
                "Bill Pugh pattern achieves lazy, lock-free thread safety utilizing the JVM inner class loading specification",
                "Bill Pugh singletons can be compromised by Java reflection (setAccessible) and deserialization",
                "Enum singleton is inherently immune to reflection attacks; the JVM explicitly blocks reflective enum construction",
                "Enum singletons provide automated serialization guarantees, preventing deserialization from creating duplicate instances",
                "Trade-off: Enum singletons cannot extend an abstract class (enums can only implement interfaces)"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_lld_023",
            trackId = "lld_interview",
            conceptId = "lld_creational_patterns",
            conceptName = "Creational Design Patterns & Object Construction",
            title = "Factory Method Pattern vs Simple Factory",
            question = "What is the difference between a Simple Factory and the GoF Factory Method Pattern? How does Factory Method honor the Open-Closed Principle?",
            shortAnswer = "1) Simple Factory: A single concrete class with a static method containing an `if-else` or `switch` statement that instantiates and returns concrete products: `PizzaFactory.createPizza(type)`. It violates OCP: adding a new product requires modifying the existing factory class. 2) Factory Method Pattern (GoF): Defines an abstract creator interface or abstract class with an abstract method: `abstract Product createProduct()`. Instead of a switch statement, object creation is deferred to polymorphic subclasses: `NYPizzaStore` creates `NYStylePizza`, while `ChicagoPizzaStore` creates `ChicagoStylePizza`. Adding a new product type requires creating a new creator subclass without modifying existing code.",
            keyPoints = listOf(
                "Simple Factory is a programming idiom using a central class with switch statements, violating OCP on additions",
                "Factory Method is a true GoF design pattern that delegates instantiation to polymorphic subclasses",
                "Defines an interface for creating an object, but lets subclasses decide which class to instantiate",
                "Honors OCP: adding a new product variation requires creating a new subclass without modifying existing factories",
                "Inverts dependency: high-level creator logic operates against product abstractions, not concrete classes"
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_lld_024",
            trackId = "lld_interview",
            conceptId = "lld_creational_patterns",
            conceptName = "Creational Design Patterns & Object Construction",
            title = "Abstract Factory Pattern: Creating Families of Related Products",
            question = "How does the Abstract Factory pattern differ from the Factory Method pattern? How does it enforce product family compatibility in multi-platform UI libraries?",
            shortAnswer = "Factory Method creates a single product; Abstract Factory creates families of related or dependent products without specifying their concrete classes. In a cross-platform UI toolkit, an `AbstractUIFactory` defines methods `createButton()`, `createCheckbox()`, and `createScrollBar()`. Concrete implementations (`MacUIFactory`, `WindowsUIFactory`) instantiate platform-specific suites (`MacButton`, `MacCheckbox`). This guarantees that a client using `MacUIFactory` can never accidentally combine a `MacButton` with a `WindowsCheckbox`, strictly enforcing aesthetic and behavioral compatibility across product families.",
            keyPoints = listOf(
                "Abstract Factory defines an interface for creating families of related or dependent objects",
                "Enforces product consistency: prevents accidental mixing of incompatible products across different families",
                "Abstract Factory classes frequently implement Factory Methods internally for individual product creation",
                "Decouples client code completely from concrete product implementations, depending only on abstract interfaces",
                "Drawback: adding a new product category requires altering the Abstract Factory interface and all concrete factories"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_lld_025",
            trackId = "lld_interview",
            conceptId = "lld_creational_patterns",
            conceptName = "Creational Design Patterns & Object Construction",
            title = "Builder Pattern vs Telescoping Constructor Anti-Pattern",
            question = "How does the Builder pattern eliminate the Telescoping Constructor anti-pattern? How do you ensure required fields are validated and target objects are immutable?",
            shortAnswer = "The Telescoping Constructor anti-pattern occurs when a class has 6+ constructors of increasing length (`Car(make)`, `Car(make, model)`, `Car(make, model, year, color, ...)`); it is error-prone, hard to read, and prone to parameter swapping bugs. Using JavaBeans with setters introduces mutability and exposes partially constructed objects. The Builder pattern separates object construction from representation. The static `Builder` class collects optional attributes via fluent methods returning `this`. The terminal `.build()` method validates required fields, verifies business invariants, and calls the private constructor of the target class, producing a fully initialized, immutable object.",
            keyPoints = listOf(
                "Eliminates telescoping constructors with long, confusing lists of identical-type arguments",
                "Avoids JavaBean setter mutability by allowing objects to be fully constructed before exposure",
                "Terminal build() method executes centralized business validation on required attributes and invariant combinations",
                "Target class constructor is private, guaranteeing that construction occurs exclusively through the Builder",
                "Produces strictly immutable objects with final fields and zero public setters"
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_lld_026",
            trackId = "lld_interview",
            conceptId = "lld_creational_patterns",
            conceptName = "Creational Design Patterns & Object Construction",
            title = "Step Builder Pattern: Compile-Time Enforced Construction Sequences",
            question = "What is the Step Builder pattern? How does it use staged interfaces to enforce mandatory attributes in a strict sequence at compile-time before allowing optional fields?",
            shortAnswer = "Standard builders cannot prevent a developer from forgetting a mandatory field until runtime validation in `.build()`. The Step Builder pattern enforces mandatory fields at compile-time using chained inner interfaces. For example, building a User requires Name, then Email, before optional fields: `interface NameStep { EmailStep withName(String name); }`, `interface EmailStep { BuildStep withEmail(String email); }`, `interface BuildStep { BuildStep withPhone(String phone); User build(); }`. The IDE autocomplete guides the developer sequentially through each mandatory step, and `.build()` is only accessible on the final interface, making it impossible to construct an incomplete object.",
            keyPoints = listOf(
                "Uses a sequence of nested interfaces representing construction steps to guide object assembly",
                "Enforces mandatory attributes at compile-time: compiler rejects calls out of sequence",
                "Eliminates runtime NullPointerExceptions and missing-attribute validation failures",
                "Terminal build() method is hidden and only exposed on the final step interface after mandatory inputs exist",
                "Provides an intuitive IDE autocomplete experience for complex domain entity construction"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_lld_027",
            trackId = "lld_interview",
            conceptId = "lld_creational_patterns",
            conceptName = "Creational Design Patterns & Object Construction",
            title = "Builder with Inheritance and Recursive Generics (CRTP)",
            question = "Why does standard Builder pattern break when subclasses extend a base class? How do Recursive Generics (Curiously Recurring Template Pattern - CRTP) solve builder inheritance?",
            shortAnswer = "If `EmployeeBuilder` extends `PersonBuilder`, calling `new EmployeeBuilder().withName(\"Alice\")` invokes a method on `PersonBuilder` that returns `PersonBuilder`, losing access to subclass methods like `.withSalary(100)`. Solution: Recursive Generics (CRTP): `abstract class PersonBuilder<T extends PersonBuilder<T>> { public T withName(String name) { this.name = name; return self(); } protected abstract T self(); }`. The subclass defines: `class EmployeeBuilder extends PersonBuilder<EmployeeBuilder> { protected EmployeeBuilder self() { return this; } public EmployeeBuilder withSalary(double s) { ... return this; } }`. Calling inherited methods now returns the typed subclass builder.",
            keyPoints = listOf(
                "Standard builder inheritance fails because base class builder methods return the base builder type",
                "Recursive generics (T extends Builder<T>) bind the fluent return type to the concrete subclass builder",
                "The self() method provides a type-safe cast mechanism returning 'this' as the generic parameter T",
                "Allows subclasses to seamlessly chain base class builder methods with subclass builder methods",
                "Standard pattern used in advanced libraries like Lombok (@SuperBuilder) and AssertJ assertions"
            ),
            difficulty = "Staff / Principal"
        ),
        InterviewQuestion(
            id = "iq_lld_028",
            trackId = "lld_interview",
            conceptId = "lld_creational_patterns",
            conceptName = "Creational Design Patterns & Object Construction",
            title = "Prototype Pattern: Deep Copy vs Shallow Copy and Cloneable Flaws",
            question = "What is the Prototype design pattern? Why is Java's native `Cloneable` interface widely considered broken, and why are Copy Constructors preferred for deep copying?",
            shortAnswer = "The Prototype pattern creates new objects by cloning an existing prototype instance, avoiding expensive construction overhead (e.g. database loading or complex calculations). Java's `Cloneable` is fundamentally flawed: (1) It contains no methods (it is a marker interface); `clone()` is declared protected in `Object`. (2) `super.clone()` does not invoke constructors, bypassing initialization. (3) By default, it performs a Shallow Copy (cloning primitive values, but copying memory references of mutable objects, causing shared-state bugs). Joshua Bloch strongly recommends Copy Constructors (`public Car(Car other)`) or Copy Static Factories for explicit, safe deep cloning.",
            keyPoints = listOf(
                "Prototype pattern instantiates new objects by copying an existing configured template instance",
                "Shallow copy duplicates primitive fields but shares references to mutable collaborator objects",
                "Deep copy recursively clones all referenced mutable objects, ensuring complete state independence",
                "Java's Cloneable interface is broken: protected method in Object, bypasses constructors, shallow by default",
                "Copy Constructors and static copy factories provide safe, explicit deep cloning without reflection magic"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_lld_029",
            trackId = "lld_interview",
            conceptId = "lld_creational_patterns",
            conceptName = "Creational Design Patterns & Object Construction",
            title = "Object Pool Pattern: Resource Lifecycle and Thread-Safe Checkin/Checkout",
            question = "How do you design a thread-safe Object Pool (e.g. for database connections or socket threads)? How do you prevent resource leaks when clients fail to return objects?",
            shortAnswer = "An Object Pool pre-allocates a set of expensive resources. Key design: (1) Thread-Safe Storage: Uses a `BlockingQueue` (e.g. `ArrayBlockingQueue` or `LinkedBlockingQueue`) to store available idle objects. (2) Checkout: `borrowObject()` pulls from the queue with a timeout. If the queue is empty and pool < max, it creates a new instance. (3) Checkin: `returnObject()` validates object health and re-inserts it into the queue. (4) Preventing Leaks: Wrap checked-out objects in an `AutoCloseable` proxy or smart wrapper so clients can use Java's `try-with-resources`. Background watchdog threads track checkout timestamps and reclaim abandoned objects exceeding a timeout threshold.",
            keyPoints = listOf(
                "Object Pool pre-allocates expensive-to-create resources (database connections, parser threads, native buffers)",
                "Thread-safe BlockingQueue coordinates concurrent checkout and checkin operations with configurable timeouts",
                "Implements AutoCloseable on wrapper references to integrate seamlessly with try-with-resources syntax",
                "Health-check validation verifies resource vitality before dispensing objects to callers",
                "Watchdog scavenger threads monitor borrow timestamps to detect and recover leaked unreturned resources"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_lld_030",
            trackId = "lld_interview",
            conceptId = "lld_creational_patterns",
            conceptName = "Creational Design Patterns & Object Construction",
            title = "Service Locator Anti-Pattern vs Dependency Injection",
            question = "Why is the Service Locator pattern considered an anti-pattern in modern enterprise architecture, and how does Constructor Dependency Injection resolve its flaws?",
            shortAnswer = "A Service Locator is a central registry where classes query for dependencies: `class OrderService { OrderService() { this.repo = ServiceLocator.get(Repo.class); } }`. Flaws: (1) Hidden Dependencies: Looking at the constructor signature `OrderService()`, it appears to have no dependencies, but crashes with `NullPointerException` at runtime if the locator registry is unconfigured. (2) Tight Coupling: Couples the domain class to the Service Locator API. (3) Brittle Unit Testing: Testing requires mocking the global static Service Locator. Constructor Injection makes dependencies explicit, enforces immutability (`final` fields), and allows mocks to be passed cleanly without container dependencies.",
            keyPoints = listOf(
                "Service Locator hides class dependencies inside method bodies rather than declaring them publicly in constructors",
                "Tightly couples business logic classes to a global singleton registry mechanism",
                "Makes unit testing brittle by requiring complex global static state setup before executing isolated tests",
                "Constructor Injection makes all dependencies explicit, fail-fast, and verifiable at compile time",
                "Enables dependencies to be declared as final fields, ensuring immutability after construction"
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_lld_031",
            trackId = "lld_interview",
            conceptId = "lld_creational_patterns",
            conceptName = "Creational Design Patterns & Object Construction",
            title = "Static Factory Methods: Advantages over Public Constructors",
            question = "In 'Effective Java', Joshua Bloch recommends: 'Consider static factory methods instead of constructors'. Detail the 4 major advantages of static factory methods.",
            shortAnswer = "Advantages: (1) They have meaningful names: Unlike constructors (which must share the class name), static factories can describe their behavior (e.g. `BigInteger.probablePrime()` vs `new BigInteger(...)`). (2) They are not required to create a new object each time: Can return cached immutable instances (e.g. `Boolean.valueOf(true)`, `Integer.valueOf(127)` interning), slashing memory allocations. (3) They can return any subtype of their return type: Gives APIs tremendous flexibility to return private interface implementations (e.g. `Collections.unmodifiableList()`). (4) The return type can vary based on input arguments (e.g. `EnumSet.noneOf()` returns `RegularEnumSet` for \$\\le 64\$ elements, `JumboEnumSet` otherwise).",
            keyPoints = listOf(
                "Static factory methods have descriptive names that clearly communicate object creation semantics",
                "Instance-controlled: can return cached, interned singletons (e.g. Boolean.valueOf) to prevent duplicate allocations",
                "Can return any polymorphic subtype of the declared return type, hiding internal implementation classes",
                "Can dynamically vary the returned concrete class based on the parameters passed at runtime",
                "Encapsulates constructor access, making future architectural refactorings transparent to clients"
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_lld_032",
            trackId = "lld_interview",
            conceptId = "lld_creational_patterns",
            conceptName = "Creational Design Patterns & Object Construction",
            title = "Multiton Pattern (Registry of Singletons)",
            question = "What is the Multiton (Registry) pattern? How does it extend the Singleton pattern to manage keyed instances (e.g. database connections per tenant or loggers per class)?",
            shortAnswer = "The Multiton pattern generalizes the Singleton pattern by maintaining a centralized registry of named/keyed instances. Instead of a single global instance, `Multiton.getInstance(key)` checks an internal `ConcurrentHashMap<Key, Multiton>`. If an instance exists for the key, it is returned; otherwise, it is created thread-safely via `computeIfAbsent()`. Classic examples: `LoggerFactory.getLogger(Class)` in SLF4J, or managing multi-tenant database connection pools where each tenant has exactly one isolated singleton instance.",
            keyPoints = listOf(
                "Maintains a centralized map-based registry of distinct singleton instances keyed by identifier",
                "Thread-safe retrieval and instantiation using ConcurrentHashMap.computeIfAbsent()",
                "Standard implementation pattern for multi-tenant database pools and logger registries (SLF4J)",
                "Private constructors guarantee that instances cannot be instantiated outside the registry",
                "Memory management: weak references or eviction policies should be considered if keys are dynamic to avoid leaks"
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_lld_033",
            trackId = "lld_interview",
            conceptId = "lld_creational_patterns",
            conceptName = "Creational Design Patterns & Object Construction",
            title = "ThreadLocal Storage as Per-Thread Singletons",
            question = "How does `ThreadLocal<T>` act as a per-thread singleton? What are the memory leak hazards of ThreadLocal in pooled application server threads (Tomcat / Netty)?",
            shortAnswer = "`ThreadLocal` allocates a separate, isolated instance of an object for each individual thread accessing it (e.g. storing a per-thread `SimpleDateFormat` or Security Context). Memory Leak Hazard: Application servers (Tomcat) use thread pools where worker threads are reused across requests. Each `Thread` has a `ThreadLocalMap` field where keys are weak references to `ThreadLocal`, but values are strong references. If an application sets a `ThreadLocal` value and fails to call `threadLocal.remove()` before the request completes: (1) The value object is retained indefinitely in the pooled worker thread, leaking memory; (2) Future requests reusing that thread see stale tenant data.",
            keyPoints = listOf(
                "ThreadLocal provides thread-isolated instances without requiring locks or synchronization",
                "Worker thread pooling causes ThreadLocal objects to persist across requests if not explicitly cleared",
                "Failure to invoke threadLocal.remove() causes severe memory leaks and cross-request data pollution",
                "Standard hygiene requires wrapping ThreadLocal usage in try-finally blocks to guarantee cleanup",
                "Modern Java 21 introduces Scoped Values as a safer, immutable, performant alternative to ThreadLocal"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_lld_034",
            trackId = "lld_interview",
            conceptId = "lld_creational_patterns",
            conceptName = "Creational Design Patterns & Object Construction",
            title = "Monostate Pattern (Borg Pattern) vs Singleton",
            question = "What is the Monostate (Borg) pattern? How does it achieve singleton behavior by sharing static state while allowing clients to instantiate standard objects?",
            shortAnswer = "The Monostate pattern achieves singleton-like behavior through behavioral transparency: all instances share the same state. Instead of hiding the constructor and using a static `getInstance()` method, Monostate provides a standard public constructor. However, all internal fields are declared `static`: `class Monostate { private static int count; public void setCount(int c) { count = c; } public int getCount() { return count; } }`. Clients can call `new Monostate()` anywhere, yet all instances operate on the identical underlying state. Advantage: transparent polymorphic behavior and standard inheritance. Disadvantage: hidden shared state that can surprise developers expecting distinct instances.",
            keyPoints = listOf(
                "Monostate allows multiple instances to be created normally via 'new' while sharing identical static state",
                "Singleton enforces structural uniqueness (one instance); Monostate enforces behavioral uniqueness (shared state)",
                "Supports standard inheritance and polymorphism, unlike traditional static singletons",
                "Disadvantage: non-obvious shared state creates confusing side effects for developers unaware of the pattern",
                "Thread safety requires synchronizing access to shared static fields across instances"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_lld_035",
            trackId = "lld_interview",
            conceptId = "lld_creational_patterns",
            conceptName = "Creational Design Patterns & Object Construction",
            title = "Java Service Provider Interface (SPI) as a Dynamic Factory",
            question = "How does Java's Service Provider Interface (`ServiceLoader`) decouple client code from concrete implementations? How do frameworks like JDBC driver loading work via SPI?",
            shortAnswer = "Java SPI is a built-in pluggable architecture mechanism. 1) Interface: A library defines a service interface (e.g. `java.sql.Driver`). 2) Provider Implementation: A vendor (e.g. PostgreSQL) implements the interface (`org.postgresql.Driver`) and creates a provider configuration file in `META-INF/services/java.sql.Driver` containing the fully qualified class name. 3) Discovery: Client code invokes `ServiceLoader.load(Driver.class)`. The ServiceLoader reads the classpath, instantiates the discovered provider classes via reflection, and registers them. This enables complete decoupling: client code compiles against Java's standard interface, and concrete driver jars can be dropped into the classpath dynamically at runtime.",
            keyPoints = listOf(
                "Java ServiceLoader provides a standardized discovery mechanism for runtime plugin implementations",
                "Provider configuration file in META-INF/services/ registers the fully qualified provider class name",
                "Decouples core application interfaces from vendor implementations (e.g. JDBC drivers, SLF4J loggers)",
                "Instantiates discovered classes lazily as the ServiceLoader iterator traverses the provider list",
                "Modern Java module systems (JPMS) declare service providers natively via 'provides ... with ...' in module-info"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_lld_036",
            trackId = "lld_interview",
            conceptId = "lld_creational_patterns",
            conceptName = "Creational Design Patterns & Object Construction",
            title = "Safe Publication of Objects in Concurrent Java",
            question = "What constitutes 'Safe Publication' in the Java Memory Model (JMM)? Why can sharing a newly created object reference across threads result in reading corrupt state?",
            shortAnswer = "In the Java Memory Model, allocating memory, setting fields, and writing the object reference can be reordered by the CPU. If an object is published unsafely (e.g. assigning to a plain non-volatile static field), another thread can observe the reference before the fields are flushed from the CPU write buffer to main memory, observing uninitialized values. Safe Publication idioms: (1) Initializing an object in a static initializer block, (2) Storing the reference in a `volatile` field or `AtomicReference`, (3) Storing the reference in a `final` field of a properly constructed object, (4) Storing the reference in a field guarded by a lock or thread-safe collection (e.g. `ConcurrentHashMap`).",
            keyPoints = listOf(
                "Unsafe publication allows concurrent threads to observe an object reference before its fields are initialized",
                "Properly constructed immutable objects with final fields are guaranteed to be safely published without locks",
                "Volatile fields enforce memory barriers (happens-before relationship) ensuring writes are visible immediately",
                "Static initializers (JVM class loading) provide automatic thread-safe publication guaranteed by the JMM",
                "Concurrent collections (ConcurrentHashMap, BlockingQueue) internally establish safe publication boundaries"
            ),
            difficulty = "Staff / Principal"
        ),
        InterviewQuestion(
            id = "iq_lld_037",
            trackId = "lld_interview",
            conceptId = "lld_creational_patterns",
            conceptName = "Creational Design Patterns & Object Construction",
            title = "Lazy Initialization with Holder Class vs Double-Checked Locking",
            question = "Why is the Initialization-on-Demand Holder idiom preferred over Double-Checked Locking in standard Java singletons, and what are its memory model guarantees?",
            shortAnswer = "Initialization-on-Demand Holder idiom is cleaner, faster, and less error-prone than Double-Checked Locking. Implementation: `public class Resource { private Resource() {} private static class Holder { static final Resource INSTANCE = new Resource(); } public static Resource getInstance() { return Holder.INSTANCE; } }`. Guarantees: The Java Language Specification (JLS §12.4.2) mandates that class initialization is strictly thread-safe: the JVM locks during class loading. `Holder` is not initialized when `Resource` is loaded; it is initialized ONLY when `Holder.INSTANCE` is first referenced in `getInstance()`. It achieves lazy, lock-free, zero-overhead thread safety without needing `volatile` or `synchronized` blocks.",
            keyPoints = listOf(
                "Relies on the Java Language Specification's guarantee that class initialization is atomic and thread-safe",
                "Zero synchronization overhead during normal execution; requires no volatile keywords or memory barriers",
                "Holder class is loaded lazily on first access to Holder.INSTANCE, achieving true lazy initialization",
                "Far less susceptible to subtle concurrency reordering bugs than Double-Checked Locking implementations",
                "Cannot be used if initialization requires passing dynamic runtime parameters (constructor takes arguments)"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_lld_038",
            trackId = "lld_interview",
            conceptId = "lld_creational_patterns",
            conceptName = "Creational Design Patterns & Object Construction",
            title = "Clean Disposal and Lifecycle Management of Singletons",
            question = "How do you design a Singleton that manages expensive native resources (threads, sockets) and guarantees clean graceful shutdown during JVM termination?",
            shortAnswer = "Singletons holding native resources (thread pools, open file descriptors, network sockets) must provide a cleanup lifecycle. Design: (1) Implement `AutoCloseable` with an idempotent `close()` method that shuts down thread pools, flushes buffers, and releases sockets. (2) JVM Shutdown Hook: In the constructor or factory initialization, register a JVM shutdown hook: `Runtime.getRuntime().addShutdownHook(new Thread(() -> singleton.close()))`. (3) State Flag: An atomic boolean `isClosed` prevents new operations and ensures `close()` executes exactly once even if invoked multiple times during application shutdown.",
            keyPoints = listOf(
                "Singletons with native resources must implement AutoCloseable with an idempotent close() lifecycle method",
                "JVM Shutdown Hooks (Runtime.addShutdownHook) ensure resources are released during graceful JVM termination",
                "AtomicBoolean state flag guards against double-closing and rejects new operations after disposal starts",
                "Avoid long-running tasks in shutdown hooks: OS signals (SIGTERM) enforce hard kill timeouts (e.g. 30s)",
                "Dependency injection containers (Spring @PreDestroy) should manage lifecycle where available instead of static hooks"
            ),
            difficulty = "Senior"
        )
    )
    private fun part3(): List<InterviewQuestion> = listOf(
        InterviewQuestion(
            id = "iq_lld_039",
            trackId = "lld_interview",
            conceptId = "lld_creational_patterns",
            conceptName = "Creational Design Patterns & Object Construction",
            title = "Copy-on-Write Pattern for Thread-Safe Read-Heavy Collections",
            question = "How does the Copy-on-Write (COW) pattern work in `CopyOnWriteArrayList`? Why is it exceptionally fast for read-heavy operations, and why does it degrade on writes?",
            shortAnswer = "Copy-on-Write makes collections thread-safe by treating the underlying array as immutable. 1) Reads: Read operations (`get()`, iteration) execute directly on the current volatile array reference without any locks or synchronization. Iterators iterate over a stable snapshot that never throws `ConcurrentModificationException`. 2) Writes: Any mutating operation (`add()`, `set()`, `remove()`) acquires a lock, makes a complete shallow copy of the entire array, applies the modification to the copy, and updates the volatile array reference. Trades write performance and memory allocation for ultra-fast, lock-free reads. Ideal for observer lists and event listener registries.",
            keyPoints = listOf(
                "Read operations execute completely lock-free on an underlying volatile array reference",
                "Iterators traverse an immutable snapshot of the array and never throw ConcurrentModificationException",
                "Write operations acquire an internal ReentrantLock and clone the entire array to apply mutations",
                "Write throughput degrades exponentially on large arrays due to repeated O(N) array copy allocations",
                "Ideal for event listener and observer registries where reads outnumber writes by 100:1"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_lld_040",
            trackId = "lld_interview",
            conceptId = "lld_creational_patterns",
            conceptName = "Creational Design Patterns & Object Construction",
            title = "Factory vs Builder Decision Matrix in Machine Coding",
            question = "What specific criteria dictate whether you should choose a Factory Pattern vs a Builder Pattern during an object-oriented Low-Level Design interview?",
            shortAnswer = "Decision criteria: (1) Number of Parameters: Use Factory when construction involves 1-3 arguments. Use Builder when a class has 4+ parameters, particularly when multiple parameters are of the same type (eliminates parameter transposition bugs). (2) Optional vs Mandatory: Use Factory when all parameters are required and the object is created in a single atomic step. Use Builder when many parameters are optional or have sensible defaults. (3) Polymorphic Variations: Use Factory (Factory Method/Abstract Factory) when the exact concrete subtype being instantiated is determined by input type or configuration. (4) Construction Process: Use Builder when construction is multi-step or requires immutable validation before instantiation.",
            keyPoints = listOf(
                "Use Factory when instantiation is single-step and focuses on selecting the correct polymorphic subclass",
                "Use Builder when an entity contains numerous optional fields and complex multi-parameter combinations",
                "Factory hides the concrete implementation class; Builder constructs a known complex entity incrementally",
                "Builder guarantees immutability by validating combinations of fields atomically in the build() method",
                "Both patterns can be combined: an Abstract Factory returning a specific Builder instance"
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_lld_041",
            trackId = "lld_interview",
            conceptId = "lld_structural_patterns",
            conceptName = "Structural Design Patterns & Composition",
            title = "Adapter Pattern: Class Adapter vs Object Adapter",
            question = "What is the Adapter design pattern? Contrast Class Adapter (using multiple inheritance) with Object Adapter (using composition). Why is Object Adapter preferred in Java?",
            shortAnswer = "The Adapter pattern converts the interface of a class into another interface clients expect, allowing classes with incompatible interfaces to collaborate. (1) Class Adapter: Inherits from both the target interface and the adaptee class. In languages like Java that disallow multiple class inheritance, Class Adapters can only adapt a single specific concrete class, cannot adapt its subclasses, and violate composition principles. (2) Object Adapter: Holds an instance reference to the Adaptee (composition) while implementing the Target interface. Object Adapter is strongly preferred in Java because it can adapt the Adaptee and all of its subclasses polymorphically, respecting 'Composition over Inheritance'.",
            keyPoints = listOf(
                "Adapter bridges incompatible interfaces without altering existing source code of target or adaptee",
                "Class Adapter relies on multiple inheritance, binding the adapter rigidly to a single concrete class",
                "Object Adapter uses composition, allowing a single adapter to adapt an entire family of adaptee subclasses",
                "Target interface represents the domain contract expected by client business services",
                "Standard examples include Arrays.asList() adapting arrays to Collections and InputStreamReader adapting byte streams"
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_lld_042",
            trackId = "lld_interview",
            conceptId = "lld_structural_patterns",
            conceptName = "Structural Design Patterns & Composition",
            title = "Decorator Pattern vs Subclass Explosion: Java I/O Streams",
            question = "How does the Decorator pattern eliminate the 'Subclass Explosion' problem? How does the Java I/O Streams architecture (`BufferedInputStream`, `GZIPInputStream`) implement Decorator?",
            shortAnswer = "If a class needs combinations of features (e.g. Encryption, Compression, Buffering), using inheritance requires creating a subclass for every permutation (\$2^N\$ subclasses: `EncryptedFileStream`, `CompressedFileStream`, `EncryptedAndCompressedFileStream`). The Decorator pattern dynamically attaches additional responsibilities to an object at runtime using composition. A Decorator implements the component interface and wraps a component instance: `InputStream in = new GZIPInputStream(new BufferedInputStream(new FileInputStream(\"data.gz\")));`. Each wrapper executes its behavior (decompression, buffering) and delegates to the inner wrapped stream, achieving infinite combinations with just \$N\$ decorator classes.",
            keyPoints = listOf(
                "Decorator attaches responsibilities dynamically at runtime, avoiding combinatorial subclass explosion",
                "Decorators implement the component interface AND hold a reference to an inner component instance",
                "Transparent to clients: because the decorator implements the target interface, clients treat it like the base object",
                "Java I/O library is the canonical implementation: BufferedInputStream wrapping FileInputStream wrapping files",
                "Drawback: can create architectures with many tiny, layered objects that are difficult to inspect and debug"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_lld_043",
            trackId = "lld_interview",
            conceptId = "lld_structural_patterns",
            conceptName = "Structural Design Patterns & Composition",
            title = "Proxy Pattern: Virtual, Protection, and Caching Proxies",
            question = "Explain the Proxy design pattern. Contrast Virtual Proxy (lazy loading), Protection Proxy (access control), and Caching Proxy with concrete architectural examples.",
            shortAnswer = "A Proxy provides a surrogate or placeholder for another object to control access to it. (1) Virtual Proxy: Defers the creation and loading of an expensive object until it is actually accessed (e.g. an `ImageProxy` that displays a lightweight placeholder icon while downloading a 50MB high-res image from disk in background). (2) Protection Proxy: Enforces security and role permissions before delegating to the real subject (e.g. checking if the current user has `ADMIN` role before forwarding calls to `PayrollService`). (3) Caching Proxy: Intercepts expensive queries, returning cached results if present and delegating to the real database/service only on cache misses.",
            keyPoints = listOf(
                "Proxy controls and manages access to a real subject while exposing the exact same interface to clients",
                "Virtual Proxy defers expensive initialization of heavy resources until first method invocation (lazy loading)",
                "Protection Proxy verifies user credentials and permissions before delegating to sensitive business operations",
                "Caching Proxy intercepts requests to return cached results, shielding expensive downstream network or database calls",
                "Unlike Decorator (which adds new behavior), Proxy primarily controls lifecycle, access, or performance"
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_lld_044",
            trackId = "lld_interview",
            conceptId = "lld_structural_patterns",
            conceptName = "Structural Design Patterns & Composition",
            title = "JDK Dynamic Proxies vs CGLIB / ByteBuddy Bytecode Generation",
            question = "How do JDK Dynamic Proxies (`java.lang.reflect.Proxy`) work? Why do they require interfaces, and how do CGLIB / ByteBuddy create proxies for concrete classes in Spring AOP?",
            shortAnswer = "1) JDK Dynamic Proxy: Creates proxy classes dynamically at runtime using `Proxy.newProxyInstance(loader, interfaces, InvocationHandler)`. It requires interfaces because the generated proxy class extends `java.lang.reflect.Proxy`; since Java does not support multiple class inheritance, it cannot extend any other concrete class, only implement interfaces. 2) CGLIB / ByteBuddy: Generates dynamic bytecode subclasses of concrete classes at runtime. They do NOT require interfaces. CGLIB overrides methods to inject cross-cutting logic (e.g. `@Transactional`). Limitation: Cannot proxy `final` classes or `final` methods, because final elements cannot be subclassed or overridden in Java.",
            keyPoints = listOf(
                "JDK Dynamic Proxies generate proxy classes at runtime that implement one or more public interfaces",
                "JDK proxies strictly require interfaces because the generated bytecode already extends java.lang.reflect.Proxy",
                "InvocationHandler.invoke() intercepts all method calls, enabling cross-cutting logic before/after execution",
                "CGLIB and ByteBuddy dynamically generate subclasses of concrete classes without requiring interfaces",
                "CGLIB cannot proxy final classes or final methods because Java prohibits subclassing final types"
            ),
            difficulty = "Staff / Principal"
        ),
        InterviewQuestion(
            id = "iq_lld_045",
            trackId = "lld_interview",
            conceptId = "lld_structural_patterns",
            conceptName = "Structural Design Patterns & Composition",
            title = "Composite Pattern: Tree Structures and Uniform Treatment",
            question = "How does the Composite pattern treat individual objects and compositions of objects uniformly? Detail the design of a File System (Files and Folders) or UI Component tree.",
            shortAnswer = "The Composite pattern composes objects into tree structures to represent part-whole hierarchies, allowing clients to treat individual objects (Leaves) and compositions of objects (Nodes) identically through a common interface: `FileSystemComponent` with `getSize()` and `display()`. (1) Leaf (`File`): Implements `getSize()` by returning its own byte size. (2) Composite (`Directory`): Contains a `List<FileSystemComponent>` of children. Its `getSize()` iterates through all children and sums their sizes recursively. Clients invoke `component.getSize()` uniformly without writing `if (isFolder)` type checks.",
            keyPoints = listOf(
                "Composite pattern structures part-whole hierarchies into recursive tree structures",
                "Uniformity: clients treat individual leaf nodes and branch composite nodes through an identical interface",
                "Leaf nodes represent primitive elements; Composite nodes manage child components and delegate operations recursively",
                "Eliminates conditional branching: clients do not check whether a node is a leaf or a collection before invoking operations",
                "Design trade-off: transparency (declaring add/remove on the component interface) vs safety (declaring only on Composite)"
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_lld_046",
            trackId = "lld_interview",
            conceptId = "lld_structural_patterns",
            conceptName = "Structural Design Patterns & Composition",
            title = "Facade Pattern vs God Object Anti-Pattern",
            question = "What is the Facade pattern? How does a Facade provide a simplified interface to a complex subsystem without deteriorating into an unmaintainable God Object?",
            shortAnswer = "A Facade provides a unified, higher-level interface that makes a complex subsystem easier to use (e.g. `VideoConverterFacade.convert(file, format)` orchestrating audio extractors, video codecs, bit-rate scalers, and file writers). To prevent a Facade from degenerating into a bloated God Object: (1) Keep the Facade thin: It should contain zero business logic, acting strictly as a router or coordinator of calls. (2) Subsystems remain directly accessible: Facade does not encapsulate or hide subsystem classes; clients needing fine-grained control can bypass the Facade and use subsystems directly. (3) Decompose into domain-specific facades rather than one monolithic application facade.",
            keyPoints = listOf(
                "Facade provides a clean, simplified entry-point interface to a complex subsystem of interdependent classes",
                "Loose coupling: shields client code from knowing the complex internal wiring of multiple subsystem modules",
                "Does not encapsulate or hide the subsystem: advanced clients can still access low-level subsystem classes directly",
                "Avoids God Object anti-pattern by remaining thin: delegates coordination without hoarding core business logic",
                "Multiple domain facades should be created to manage separate subsystems rather than one global megaclass"
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_lld_047",
            trackId = "lld_interview",
            conceptId = "lld_structural_patterns",
            conceptName = "Structural Design Patterns & Composition",
            title = "Bridge Pattern: Decoupling Abstraction from Implementation",
            question = "What is the GoF Bridge pattern? How does it decouple an abstraction from its implementation so that the two can vary independently across orthogonal dimensions?",
            shortAnswer = "The Bridge pattern favors composition over inheritance when a domain varies across two orthogonal dimensions (e.g. `Shape` [Circle, Square] and `RenderingAPI` [DirectX, OpenGL, Metal]). If solved via inheritance, it causes combinatorial class explosion (\$2 \\times 3 = 6\$ subclasses: `DirectXCircle`, `OpenGLCircle`, ...). The Bridge pattern splits the hierarchy into two independent trees: Abstraction (`Shape` holding a reference to `Renderer`) and Implementor (`Renderer` interface with `drawCircle()`, `drawSquare()`). Now new shapes can be added without modifying renderers, and new renderers can be added without modifying shapes, scaling linearly (\$N + M\$ instead of \$N \\times M\$).",
            keyPoints = listOf(
                "Decouples an abstraction from its implementation so that both can vary independently without cross-coupling",
                "Replaces combinatorial N x M subclass explosion with linear N + M component composition",
                "The Abstraction tree defines high-level domain operations, holding an internal reference to the Implementor",
                "The Implementor interface defines low-level primitive operations consumed by the Abstraction",
                "Classic real-world examples: cross-platform graphics drivers, database drivers (JDBC), and OS window managers"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_lld_048",
            trackId = "lld_interview",
            conceptId = "lld_structural_patterns",
            conceptName = "Structural Design Patterns & Composition",
            title = "Flyweight Pattern: Intrinsic vs Extrinsic State",
            question = "How does the Flyweight pattern drastically reduce memory footprint when rendering millions of fine-grained objects (e.g. text editor characters or game forest trees)?",
            shortAnswer = "The Flyweight pattern shares common, immutable state among multiple objects to minimize memory footprint. State is divided into: (1) Intrinsic State: Constant, immutable, context-independent data stored inside the shared Flyweight instance (e.g. a Tree's 3D mesh, texture image, leaf color). Shared across 1,000,000 trees. (2) Extrinsic State: Context-dependent, unique data that changes per instance (e.g. a Tree's \$X, Y, Z\$ coordinates and scale). The extrinsic state is NOT stored in the flyweight; it is stored in compact arrays or passed into the flyweight's methods by the caller: `treeModel.render(x, y, scale)`. Storing only coordinates per tree saves 95% of RAM.",
            keyPoints = listOf(
                "Flyweight shares common immutable state across thousands or millions of fine-grained objects to save memory",
                "Intrinsic state is invariant, context-free, and stored permanently inside the shared Flyweight object",
                "Extrinsic state is variable, context-dependent, and passed into Flyweight methods from client calling contexts",
                "Flyweight Factory manages a pool of existing shared Flyweights, returning cached instances on request",
                "Canonical examples: String interning in Java, character glyph rendering in word processors, particle engines in games"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_lld_049",
            trackId = "lld_interview",
            conceptId = "lld_structural_patterns",
            conceptName = "Structural Design Patterns & Composition",
            title = "Decorator vs Proxy vs Adapter vs Facade: Structural Pattern Matrix",
            question = "Candidates frequently confuse Decorator, Proxy, Adapter, and Facade. Provide a clear architectural comparison matrix detailing their intent, interface relationships, and use cases.",
            shortAnswer = "Comparison Matrix: (1) Adapter: Intent is interface translation. Converts an incompatible interface to match client expectations. Interface changes; behavior is unchanged. (2) Decorator: Intent is dynamic behavior extension. Implements the same interface as the wrapped object to add responsibilities without modifying original code. Interface remains identical; behavior is enriched. (3) Proxy: Intent is access control and lifecycle management (lazy loading, auth, caching). Implements the same interface as the real subject. Interface remains identical; access/lifecycle is controlled. (4) Facade: Intent is simplification. Creates a brand new, higher-level interface over a complex subsystem of multiple classes.",
            keyPoints = listOf(
                "Adapter changes the interface of an existing object to match what a client expects without changing behavior",
                "Decorator retains the exact same interface while dynamically augmenting or enhancing behavioral responsibilities",
                "Proxy retains the exact same interface while controlling access, caching, or deferring initialization of the real subject",
                "Facade introduces a brand new simplified interface that coordinates multiple complex subsystem classes",
                "Key mnemonic: Adapter = translation; Decorator = enhancement; Proxy = access control; Facade = simplification"
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_lld_050",
            trackId = "lld_interview",
            conceptId = "lld_structural_patterns",
            conceptName = "Structural Design Patterns & Composition",
            title = "Cycle Detection and Safety in Deep Composite Trees",
            question = "How do you prevent infinite recursion and stack overflow errors when building hierarchical tree structures with the Composite pattern? Implement safe child addition with cycle detection.",
            shortAnswer = "In a Composite tree (e.g. folders and shortcuts), accidentally adding an ancestor directory as a child of its own descendant creates a cyclic graph: `Folder A -> Folder B -> Folder A`. Invoking recursive operations like `getSize()` or `print()` enters an infinite loop, crashing with `StackOverflowError`. Prevention: When `composite.addChild(component)` is invoked, the composite must execute Cycle Detection before inserting: traverse upwards from `this` composite to the root; if the component being added matches `this` or any ancestor in the parent chain, reject the addition with `IllegalArgumentException(\"Cyclic dependency detected\")`.",
            keyPoints = listOf(
                "Cyclic references in Composite hierarchies cause infinite recursion and fatal StackOverflowError crashes",
                "Cycle validation must be enforced synchronously during addChild() before updating internal pointers",
                "Cycle detection traverses the parent chain upwards: child cannot be an ancestor of the composite node",
                "Alternative graph traversal algorithms (DFS with visited sets) safely traverse complex graphs with cycles",
                "Parent pointers should be maintained cleanly so components know their containing composite context"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_lld_051",
            trackId = "lld_interview",
            conceptId = "lld_structural_patterns",
            conceptName = "Structural Design Patterns & Composition",
            title = "Private Class Data Pattern",
            question = "What is the Private Class Data pattern? How does it protect class attributes from unauthorized mutation even within the class itself, enforcing write-once initialization?",
            shortAnswer = "The Private Class Data pattern separates an object's mutable methods from its initialization data by moving the data attributes into a separate, private data class. The main class holds a single private final reference to the Data object. The Data class exposes only getters (no setters) and initializes values via constructor. Even methods within the main class cannot accidentally alter the private data fields after construction because the data class is immutable. This reduces cognitive load during maintenance: developers working on complex methods know that internal state cannot be inadvertently modified.",
            keyPoints = listOf(
                "Encapsulates initialization attributes in a dedicated private data class to prevent internal accidental mutation",
                "The main class holds an immutable reference to the data class, which exposes read-only getters",
                "Protects class invariants even from methods residing within the same enclosing class",
                "Simplifies class maintenance by establishing immutable baseline state after constructor execution",
                "Precursor to modern immutable record data structures in Java"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_lld_052",
            trackId = "lld_interview",
            conceptId = "lld_structural_patterns",
            conceptName = "Structural Design Patterns & Composition",
            title = "Spring AOP and @Transactional: Proxy Mechanics and Self-Invocation Pitfalls",
            question = "How does Spring use Dynamic Proxies to implement `@Transactional` and `@Cacheable`? Why does calling a `@Transactional` method from within the same class (self-invocation) fail to start a transaction?",
            shortAnswer = "Spring wraps beans in a Dynamic Proxy (CGLIB or JDK). When an external caller invokes a `@Transactional` method, the call hits the Proxy first. The Proxy's interceptor opens a database transaction, invokes the target method on the real bean, and commits/rolls back based on exceptions. Self-Invocation Trap: If method `A()` calls `@Transactional` method `B()` within the same class (`this.B()`), the call is executed via the raw `this` reference, completely bypassing the Spring Proxy interceptor. The transaction never starts! Fix: (1) Refactor `B()` into a separate collaborator bean, (2) Inject the proxy into itself (`@Lazy`), or (3) Use AspectJ compile-time weaving.",
            keyPoints = listOf(
                "Spring creates CGLIB or JDK dynamic proxies around annotated beans to intercept method invocations",
                "The proxy interceptor manages cross-cutting concerns (opening DB transactions, security, caching) around target calls",
                "Self-invocation (this.method()) bypasses the outer proxy wrapper, causing annotations to be silently ignored",
                "To trigger proxy interception, invocations must originate from an external collaborator bean",
                "Solutions: extract method into a separate service, inject self-proxy via @Lazy, or use AspectJ bytecode weaving"
            ),
            difficulty = "Staff / Principal"
        ),
        InterviewQuestion(
            id = "iq_lld_053",
            trackId = "lld_interview",
            conceptId = "lld_structural_patterns",
            conceptName = "Structural Design Patterns & Composition",
            title = "Transparent Caching with the Decorator / Proxy Pattern",
            question = "How do you design a transparent caching layer over an expensive third-party client using the Decorator pattern? How does it preserve Open-Closed Principle and keep caching out of business services?",
            shortAnswer = "1) Interface: Both the real service and the caching layer implement a common interface: `public interface WeatherClient { WeatherData getWeather(String city); }`. 2) Real Service: `HttpWeatherClient` executes raw HTTP network calls. 3) Caching Decorator: `CachingWeatherClient implements WeatherClient` wraps an inner `WeatherClient` and holds a `Cache<String, WeatherData>`. When `getWeather(city)` is called: checks cache; on hit, returns cached data; on miss, invokes `delegate.getWeather(city)`, stores in cache with TTL, and returns. 4) Clean Architecture: Upstream domain services inject `WeatherClient`; they remain 100% oblivious to whether responses come from network or cache.",
            keyPoints = listOf(
                "Both concrete client and caching wrapper implement the shared domain interface transparently",
                "Domain services depend on the interface, remaining completely decoupled from caching infrastructure",
                "Honors OCP: caching capability is added without modifying or polluting the HTTP networking client code",
                "Easily composable: can layer RetryDecorator, LoggingDecorator, and CachingDecorator cleanly",
                "Enables isolated unit testing of networking logic and caching eviction policies independently"
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_lld_054",
            trackId = "lld_interview",
            conceptId = "lld_structural_patterns",
            conceptName = "Structural Design Patterns & Composition",
            title = "Circuit Breaker Pattern as a Structural Proxy / Decorator",
            question = "How do you design a Circuit Breaker (Resilience4j pattern) as a structural decorator around remote service calls? Detail the State transitions (Closed, Open, Half-Open).",
            shortAnswer = "A Circuit Breaker decorator wraps external remote calls to prevent cascading system failure. State Machine: (1) CLOSED: Normal operation. Requests pass through to remote service. Failure counter tracks errors in a rolling window. If failure rate > threshold (e.g. 50%), transitions to OPEN. (2) OPEN: Circuit is tripped. All requests fail fast immediately, throwing `CallNotPermittedException` or returning fallback responses without making network calls. A sleep timer (e.g. 30s) starts. (3) HALF-OPEN: When timer expires, allows a limited trial probe of \$N\$ requests. If all succeed, transitions back to CLOSED; if any fail, resets back to OPEN.",
            keyPoints = listOf(
                "Circuit Breaker decorator wraps remote collaborator calls to shield systems from cascading latency and failure",
                "Closed state processes requests normally while tracking rolling failure rates and slow call percentages",
                "Open state fails fast immediately, returning fallbacks without wasting thread pools on dead dependencies",
                "Half-Open state allows trial canary requests to probe dependency recovery after a cooldown timeout",
                "Decouples resilience logic from business workflows by packaging it as a reusable method decorator"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_lld_055",
            trackId = "lld_interview",
            conceptId = "lld_structural_patterns",
            conceptName = "Structural Design Patterns & Composition",
            title = "Pipeline / Filter Pattern for Data Transformation",
            question = "How do you design a flexible, composable Pipeline (Pipes and Filters) architecture in Java? How does it allow dynamic re-ordering and addition of processing stages?",
            shortAnswer = "A Pipeline processes data through a sequence of discrete, reusable stages (Filters). Design: (1) Stage Interface: `public interface PipelineStep<I, O> { O execute(I input); }`. (2) Pipeline Container: Chains steps using composition: `public class Pipeline<I, O> { private final Function<I, O> pipeline; public <R> Pipeline<I, R> addStep(PipelineStep<O, R> step) { ... } public O execute(I input) { return pipeline.apply(input); } }`. Each step performs one isolated transformation (e.g. sanitize, validate, enrich, calculate tax, format). Steps are decoupled from adjacent stages, enabling dynamic reordering, selective execution, and independent unit testing.",
            keyPoints = listOf(
                "Pipes and Filters decomposes complex data processing into a chain of independent, composable stages",
                "Each pipeline stage (filter) implements a shared interface, receiving input and emitting transformed output",
                "Stages can be reordered, added, or removed dynamically without modifying existing filter implementations",
                "Promotes Single Responsibility Principle: each filter is focused on a single isolated data transformation",
                "Standard architectural pattern for compiler stages, audio/video pipelines, and request-response filter chains"
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_lld_056",
            trackId = "lld_interview",
            conceptId = "lld_structural_patterns",
            conceptName = "Structural Design Patterns & Composition",
            title = "Two-Way Adapter Pattern",
            question = "What is a Two-Way Adapter? When is it necessary for an adapter to satisfy both client interfaces simultaneously across legacy and modern systems?",
            shortAnswer = "A Two-Way Adapter is an adapter that implements two different interfaces simultaneously, allowing an object to be used transparently by two incompatible systems. For example, adapting an existing `LegacyPaymentGateway` and a new `ModernPaymentGateway`. The `TwoWayPaymentAdapter` implements both `LegacyPayment` and `ModernPayment`. If passed to a legacy client, it responds to `makePayment()` and translates calls to the modern engine; if passed to a modern client, it responds to `process()` and translates calls to the legacy engine. This enables seamless, progressive two-way interoperability during large-scale enterprise system migrations.",
            keyPoints = listOf(
                "Two-Way Adapter implements both incompatible interfaces simultaneously to provide bi-directional compatibility",
                "Allows modern and legacy clients to interact with the exact same adapter instance without code changes",
                "Essential architectural pattern during long-term phased system migrations and parallel runs",
                "Maintains dual translation logic: mapping legacy calls to modern semantics and vice-versa",
                "Prevents creating two separate, one-way adapter classes that might fall out of sync"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_lld_057",
            trackId = "lld_interview",
            conceptId = "lld_structural_patterns",
            conceptName = "Structural Design Patterns & Composition",
            title = "Marker Interface Pattern vs Java Annotations",
            question = "What was the historical purpose of the Marker Interface pattern (`Serializable`, `Cloneable`) in Java? Why have modern Java Annotations largely superseded marker interfaces?",
            shortAnswer = "A Marker Interface is an empty interface with no methods used to associate metadata with a class, signaling to the compiler or runtime that the class possesses special capabilities (e.g. `Serializable` tells the JVM runtime that fields may be serialized). Flaws: Marker interfaces pollute the type system: a class can never 'un-implement' an interface it inherited from a parent class. Modern Java Annotations (`@Entity`, `@Component`, `@Audited`) have superseded marker interfaces because: (1) They allow attaching rich parameters and metadata, (2) They do not pollute the inheritance hierarchy or polymorphic type system, and (3) They can target classes, methods, or individual fields with fine-grained retention policies.",
            keyPoints = listOf(
                "Marker interface contains no method declarations; acts as a compile/runtime type tag (e.g. Serializable)",
                "Polymorphic pollution: subclasses irrevocably inherit marker interfaces from parent classes",
                "Java Annotations provide rich parameterized metadata without polluting the object type hierarchy",
                "Annotations support targeted application at class, method, parameter, and field levels",
                "Marker interfaces remain useful ONLY when compile-time type-safety (passing MarkerInterface to a method) is required"
            ),
            difficulty = "Mid-Level"
        )
    )
    private fun part4(): List<InterviewQuestion> = listOf(
        InterviewQuestion(
            id = "iq_lld_058",
            trackId = "lld_interview",
            conceptId = "lld_structural_patterns",
            conceptName = "Structural Design Patterns & Composition",
            title = "Extension Object Pattern (Interface Extensions)",
            question = "What is the Extension Object pattern? How does it allow an existing class hierarchy to be extended with new interfaces dynamically without modifying the classes?",
            shortAnswer = "The Extension Object pattern allows an object to provide dynamic extensions without class modification or inheritance explosion. The subject implements an interface: `public interface Extensible { <T> T getExtension(Class<T> extensionType); void addExtension(Class<?> type, Object extension); }`. The subject holds an internal `Map<Class<?>, Object>`. New capabilities (e.g. `Exportable`, `Printable`, `Auditable`) are implemented as separate extension classes and registered dynamically. When client code needs a capability: `Exportable exp = document.getExtension(Exportable.class); if (exp != null) exp.export();`. Widely used in extensible IDEs (Eclipse IAdaptable pattern) and CAD frameworks.",
            keyPoints = listOf(
                "Dynamically attaches optional, modular capabilities to objects without altering original class hierarchies",
                "Decouples core entities from specialized, domain-specific features (exporting, printing, auditing)",
                "Uses a registry mapping extension interfaces to concrete extension instances via getExtension(Class<T>)",
                "Foundation of extensible plugin frameworks (Eclipse IAdaptable architecture)",
                "Alternative to Visitor pattern when new operations must be added dynamically without fixed visitor interfaces"
            ),
            difficulty = "Staff / Principal"
        ),
        InterviewQuestion(
            id = "iq_lld_059",
            trackId = "lld_interview",
            conceptId = "lld_structural_patterns",
            conceptName = "Structural Design Patterns & Composition",
            title = "Package-Private Encapsulation and Modular Architecture",
            question = "Why is marking every class and method `public` a destructive design habit? How does package-private (default) visibility enforce structural boundaries within modules?",
            shortAnswer = "Making classes public exposes internal implementation details to the entire application, destroying encapsulation. Once a class is public, other developers will couple to it, making future refactoring impossible without breaking changes. Package-private visibility (default in Java, no modifier) restricts access strictly to classes within the same package. Clean architectural design exposes only 1-2 public interfaces and factories per package, keeping all concrete implementation classes, helper strategies, and adapters package-private. This creates a solid encapsulation firewall: clients interact exclusively through public contracts, while internal wiring remains free to refactor.",
            keyPoints = listOf(
                "Over-using public visibility exposes internal implementation mechanics, creating widespread tight coupling",
                "Package-private visibility acts as an encapsulation boundary, restricting usage to classes inside the package",
                "Clean module pattern exposes only public interfaces and factories, keeping concrete classes package-private",
                "Enables fearless refactoring: developers can alter package-private classes without breaking external clients",
                "Java Module System (JPMS module-info.java) extends package encapsulation to the JVM jar boundary level"
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_lld_060",
            trackId = "lld_interview",
            conceptId = "lld_structural_patterns",
            conceptName = "Structural Design Patterns & Composition",
            title = "Composite with Visitor Integration for Complex Operations",
            question = "How do you combine the Composite pattern with the Visitor pattern to execute complex operations (e.g. calculating total cost, rendering HTML, exporting JSON) over tree hierarchies?",
            shortAnswer = "In a pure Composite pattern, adding new operations (e.g. `exportToXml()`, `calculateTax()`, `findDuplicates()`) requires modifying the base `Component` interface and every Leaf/Composite class, violating OCP. Combining with Visitor: (1) Composite nodes implement a single stable method: `void accept(Visitor visitor)`. (2) `Visitor` interface defines visit methods for each node type: `visit(File f)`, `visit(Directory d)`. (3) In Composite: `accept(visitor)` calls `visitor.visit(this)` and recursively calls `child.accept(visitor)` on children. (4) New operations are implemented as new Visitor classes (`XmlExportVisitor`, `TaxCalculatorVisitor`) without touching the Composite tree classes.",
            keyPoints = listOf(
                "Combining Composite with Visitor separates tree structure representation from operational algorithms",
                "Avoids polluting Composite and Leaf classes with dozens of unrelated operation methods",
                "New operations are implemented as new Visitor classes without modifying existing tree nodes (satisfies OCP)",
                "Double Dispatch mechanism ensures the correct polymorphic visit method executes based on node runtime type",
                "Visitor state maintains running calculation totals during recursive tree traversal without mutating nodes"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_lld_061",
            trackId = "lld_interview",
            conceptId = "lld_behavioral_patterns",
            conceptName = "Behavioral Design Patterns & Event Handling",
            title = "Strategy Pattern: Runtime Algorithm Swapping and Lambda Modernization",
            question = "How does the Strategy pattern decouple algorithms from their host context? How do Java functional interfaces and Lambdas streamline Strategy implementations?",
            shortAnswer = "The Strategy pattern defines a family of algorithms, encapsulates each one, and makes them interchangeable at runtime. The Context class holds a reference to a `Strategy` interface and delegates execution to it: `paymentContext.executePayment(amount)`. Clients can inject different concrete strategies (`CreditCardPayment`, `PayPalPayment`, `CryptoPayment`) dynamically without altering the Context class. In modern Java, because Strategy interfaces typically declare a single method (e.g. `Comparator`, `Predicate`), they are Functional Interfaces (`@FunctionalInterface`). Clients can pass stateless lambdas (`(a, b) -> a - b`) or method references directly, eliminating dozens of boilerplate concrete classes.",
            keyPoints = listOf(
                "Strategy encapsulates interchangeable algorithms behind a common interface, decoupling them from clients",
                "Satisfies the Open-Closed Principle: new algorithms can be introduced without modifying the context class",
                "Replaces complex conditional if-else or switch statements with polymorphic delegation",
                "Single-method strategies operate as Java Functional Interfaces, implemented concisely via lambdas",
                "Context classes can provide a default fallback strategy if none is explicitly configured by the client"
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_lld_062",
            trackId = "lld_interview",
            conceptId = "lld_behavioral_patterns",
            conceptName = "Behavioral Design Patterns & Event Handling",
            title = "Observer Pattern: Push vs Pull Models and the Lapsed Listener Memory Leak",
            question = "Explain the Observer pattern. Contrast the Push model with the Pull model of event notification, and explain how the 'Lapsed Listener' problem causes severe memory leaks.",
            shortAnswer = "The Observer pattern defines a one-to-many dependency where when the Subject changes state, all registered Observers are notified automatically. (1) Push Model: Subject sends detailed event state directly inside the notification method (`update(price, volume)`). Simplifies observers, but tightly couples the payload. (2) Pull Model: Subject passes only a reference to itself (`update(this)`). Observers pull only the specific data fields they care about, maximizing flexibility. (3) Lapsed Listener Leak: When an observer registers with a long-lived subject (`subject.addListener(this)`), the subject holds a strong reference to the observer. If the observer is discarded by the application without calling `removeListener()`, the subject's reference prevents garbage collection, leaking memory. Fix: Use `WeakReference` in the subject's listener list.",
            keyPoints = listOf(
                "Observer establishes a decoupled one-to-many publish-subscribe relationship between subject and listeners",
                "Push model transmits complete data payloads in the notification; Pull model passes subject reference for on-demand reads",
                "Lapsed Listener problem: long-lived subjects retain strong references to short-lived observers, causing memory leaks",
                "WeakReference observer lists allow unreferenced observers to be garbage collected automatically",
                "Thread safety: iterating observer lists during notification requires thread-safe collections (CopyOnWriteArrayList)"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_lld_063",
            trackId = "lld_interview",
            conceptId = "lld_behavioral_patterns",
            conceptName = "Behavioral Design Patterns & Event Handling",
            title = "State Pattern: Modeling Finite State Machines without Switch Statements",
            question = "How does the State pattern model Finite State Machines (FSMs) like a Vending Machine or Order Lifecycle? How does it eliminate massive switch-case statements across states?",
            shortAnswer = "In a naive FSM, every method (`insertCoin()`, `pressButton()`, `dispense()`) contains a massive `switch(currentState)` statement. Adding a new state requires editing every single method, creating brittle code that violates OCP. The State pattern encapsulates state-specific behavior in concrete classes implementing a common `State` interface. The Context class (`VendingMachine`) holds a reference to the current `State` object and delegates calls to it: `currentState.insertCoin()`. State transitions are handled polymorphically: the current state object executes business validation and updates the context's state reference: `context.setState(new HasMoneyState(context))`.",
            keyPoints = listOf(
                "State pattern encapsulates state-specific behaviors into distinct polymorphic classes",
                "Eliminates monolithic switch statements scattered across every entity method",
                "Context delegates incoming actions to its current state object, which executes domain transitions",
                "State transitions can be controlled by the concrete state classes or coordinated centrally by the context",
                "State classes that hold no mutable data can be shared across multiple context entities as flyweight singletons"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_lld_064",
            trackId = "lld_interview",
            conceptId = "lld_behavioral_patterns",
            conceptName = "Behavioral Design Patterns & Event Handling",
            title = "Command Pattern: Undo/Redo Architecture and Transactional Execution",
            question = "How does the Command pattern encapsulate requests as objects? Detail the implementation of a reversible Undo/Redo stack and Macro Commands (Batching).",
            shortAnswer = "The Command pattern encapsulates a request as an object with an `execute()` and `undo()` method, decoupling the invoker from the receiver that performs the work: (1) Command Interface: `public interface Command { void execute(); void undo(); }`. (2) Concrete Command: `PasteCommand` holds references to the `Document` (Receiver) and captures the previous state/text. (3) Undo/Redo Engine: Maintains two `Deque<Command>` stacks (`undoStack` and `redoStack`). When a command executes, it is pushed to `undoStack` and `redoStack.clear()`. When Undo is called: pop from `undoStack`, call `command.undo()`, push to `redoStack`. (4) Macro Command: Holds a `List<Command>`, iterating and executing all commands sequentially as a batch.",
            keyPoints = listOf(
                "Encapsulates a request as a standalone object containing all information required to execute the action",
                "Decouples the invoker (UI button, scheduler) from the receiver (domain business entity) performing the operation",
                "Undo/Redo is implemented using twin stacks (undoStack and redoStack) managing reversible command objects",
                "Macro Commands (Composite pattern integration) batch multiple commands into a single transactional sequence",
                "Commands can be serialized, queued, logged to disk, and executed asynchronously across thread boundaries"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_lld_065",
            trackId = "lld_interview",
            conceptId = "lld_behavioral_patterns",
            conceptName = "Behavioral Design Patterns & Event Handling",
            title = "Chain of Responsibility Pattern: Middleware Pipelines and Interceptors",
            question = "What is the Chain of Responsibility pattern? How do web middleware frameworks (Servlet Filters, Spring Interceptors, Express.js) use this pattern to process HTTP requests?",
            shortAnswer = "Chain of Responsibility decouples the sender of a request from its receivers by giving more than one object a chance to handle the request. Handlers are chained sequentially in a linked list. Each handler implements: `public abstract class Handler { protected Handler next; public void handle(Request req) { if (canHandle(req)) process(req); if (next != null) next.handle(req); } }`. In web frameworks (Servlet Filters): (1) Request enters the chain (`AuthenticationFilter -> RateLimitFilter -> LoggingFilter -> Controller`). (2) Short-Circuiting: If authentication fails, `AuthenticationFilter` returns `401 Unauthorized` and halts the chain without invoking `next.doFilter()`, protecting downstream handlers.",
            keyPoints = listOf(
                "Passes a request along a dynamic chain of handlers until an object handles it or the chain completes",
                "Decouples request senders from individual receivers, allowing handlers to be reordered or added dynamically",
                "Supports short-circuiting: a handler can terminate execution and return a response immediately (e.g. auth failure)",
                "Servlet Filter chain (FilterChain.doFilter) and Express.js middleware represent standard industry implementations",
                "Can be configured as a Pure Chain (exactly one handler handles it) or an Interceptor Chain (all handlers process it)"
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_lld_066",
            trackId = "lld_interview",
            conceptId = "lld_behavioral_patterns",
            conceptName = "Behavioral Design Patterns & Event Handling",
            title = "Template Method Pattern and the Hollywood Principle",
            question = "What is the Template Method pattern? How does it enforce the 'Hollywood Principle' ('Don't call us, we'll call you'), and how does it differ from the Strategy pattern?",
            shortAnswer = "Template Method defines the skeleton of an algorithm in a base class method marked `final`, deferring specific steps to subclasses without changing the algorithm's structure. The base method calls primitive abstract methods and optional 'hook' methods: `public final void processOrder() { validate(); debitPayment(); packageItems(); ship(); }`. The Hollywood Principle: high-level base classes call low-level subclass methods, inverting traditional control. Difference from Strategy: Template Method uses class inheritance to alter parts of an algorithm at compile time; Strategy uses object composition to replace an entire algorithm at runtime.",
            keyPoints = listOf(
                "Defines the invariant skeleton of an algorithm in a final superclass method, deferring steps to subclasses",
                "Enforces the Hollywood Principle ('Don't call us, we'll call you'): superclass orchestrates subclass method calls",
                "Hook methods provide default no-op behavior that subclasses can optionally override for extension points",
                "Template Method relies on compile-time class inheritance; Strategy relies on runtime object composition",
                "Canonical example: AbstractList in Java Collections and Spring Framework's JdbcTemplate"
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_lld_067",
            trackId = "lld_interview",
            conceptId = "lld_behavioral_patterns",
            conceptName = "Behavioral Design Patterns & Event Handling",
            title = "Iterator Pattern: Fail-Fast vs Fail-Safe Mechanics",
            question = "What is the Iterator pattern? What causes a `ConcurrentModificationException` in Java collections, and how do Fail-Safe iterators traverse collections without locking?",
            shortAnswer = "The Iterator pattern provides a way to access elements of an aggregate object sequentially without exposing its underlying representation. (1) Fail-Fast Iterators (`ArrayList`, `HashMap`): Maintain an internal counter `modCount` tracking structural modifications (add/remove). When an iterator is created, it records `expectedModCount = modCount`. On every `next()` call, if `modCount != expectedModCount`, it immediately throws `ConcurrentModificationException`. (2) Fail-Safe / Weakly Consistent Iterators (`CopyOnWriteArrayList`, `ConcurrentHashMap`): Operate on a cloned snapshot of the collection or traverse bucket nodes safely using volatile reads, allowing concurrent mutations without throwing exceptions or acquiring exclusive locks.",
            keyPoints = listOf(
                "Iterator encapsulates traversal logic, decoupling client algorithms from underlying collection data structures",
                "Fail-Fast iterators detect concurrent structural modifications via a modCount check, throwing ConcurrentModificationException",
                "Mutating a collection directly (e.g. list.remove()) during standard foreach iteration triggers a fail-fast crash",
                "Fail-Safe iterators traverse an immutable snapshot or use lock-free volatile pointers (ConcurrentHashMap)",
                "Java Iterable interface enables language-level enhanced for-loop syntax (for (Item item : list))"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_lld_068",
            trackId = "lld_interview",
            conceptId = "lld_behavioral_patterns",
            conceptName = "Behavioral Design Patterns & Event Handling",
            title = "Mediator Pattern: Decoupling Many-to-Many Relationships",
            question = "What problem does the Mediator pattern solve? Contrast an unmediated system (\$O(N^2)\$ connections) with a mediated system (\$O(N)\$ connections) using an Air Traffic Controller example.",
            shortAnswer = "When multiple objects communicate directly with each other, they form a tight web of \$O(N^2)\$ interdependencies (spaghetti architecture). Modifying one class breaks several others. The Mediator pattern encapsulates how a set of objects interact by forcing all communication through a central Mediator object, reducing coupling to \$O(N)\$ connections. Air Traffic Control (ATC) example: Airplanes do NOT communicate radio coordinates directly with 50 other airplanes in the sky (\$N^2\$ chaos). Instead, all airplanes send status to the central ATC Tower (Mediator). The Tower evaluates runway safety and broadcasts flight clearances back to individual planes.",
            keyPoints = listOf(
                "Mediator centralizes complex communications between disparate objects, reducing O(N^2) couplings to O(N)",
                "Colleague objects communicate exclusively with the mediator rather than referencing each other directly",
                "Promotes loose coupling: colleagues can be added, modified, or reused independently of other colleagues",
                "Classic use case: UI dialog window coordinating interdependencies between buttons, dropdowns, and text fields",
                "Trade-off: the Mediator itself can become a bloated God Object if it absorbs excessive domain business logic"
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_lld_069",
            trackId = "lld_interview",
            conceptId = "lld_behavioral_patterns",
            conceptName = "Behavioral Design Patterns & Event Handling",
            title = "Memento Pattern: Encapsulation-Preserving State Snapshots",
            question = "How does the Memento pattern capture and restore an object's internal state without violating encapsulation? Detail the roles of Originator, Memento, and Caretaker.",
            shortAnswer = "The Memento pattern captures an object's internal state so it can be restored later (rollback/undo), without exposing private fields. Roles: (1) Originator: The object whose state is being snapshot (`Editor`). It creates a Memento containing its private fields and restores state from a passed Memento. (2) Memento: Immutable state container. Exposes a wide interface (getters) to the Originator, but a narrow interface (empty/opaque) to the outside world. (3) Caretaker: Manages Memento history (`HistoryManager`). It stores mementos (e.g. in a stack), but NEVER inspects, reads, or modifies their internal contents. Encapsulation remains 100% intact.",
            keyPoints = listOf(
                "Captures and externalizes an object's internal state without exposing private fields or violating encapsulation",
                "Originator creates memento snapshots of its own private state and restores itself from historical mementos",
                "Memento represents an immutable state snapshot; restricts internal state visibility to the Originator",
                "Caretaker manages the lifecycle and storage of mementos (undo stack) without inspecting their contents",
                "Can be memory-intensive if full object snapshots are captured frequently; consider delta-based mementos"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_lld_070",
            trackId = "lld_interview",
            conceptId = "lld_behavioral_patterns",
            conceptName = "Behavioral Design Patterns & Event Handling",
            title = "Visitor Pattern and Double Dispatch Mechanics",
            question = "How does the Visitor pattern implement 'Double Dispatch' in single-dispatch languages like Java? How does it allow adding new operations to class hierarchies without modifying them?",
            shortAnswer = "Java is a single-dispatch language: virtual method invocation resolves dynamically based ONLY on the runtime type of the receiver object (`obj.method()`), while overloaded method parameters resolve statically at compile time. The Visitor pattern achieves Double Dispatch through two polymorphic calls: (1) Call 1: Client calls `element.accept(visitor)`. The runtime type of `element` dynamically dispatches to the concrete element's method. (2) Call 2: Inside `concreteElement.accept()`, it executes `visitor.visit(this)`. Because `this` is statically typed to the concrete element, the compiler binds to the exact overloaded `visit(ConcreteElement)` method. This allows adding new operations (new visitors) without touching element classes.",
            keyPoints = listOf(
                "Visitor pattern separates algorithms and operations from the complex object structures on which they operate",
                "Double Dispatch mechanism uses two dynamic method calls to resolve both the element and visitor types at runtime",
                "Adding a new operation requires creating a new Visitor class without modifying existing element classes (satisfies OCP)",
                "Disadvantage: adding a new Element class requires updating the Visitor interface and all concrete visitor classes",
                "Heavily utilized in compiler abstract syntax tree (AST) traversal, document exporters, and report generators"
            ),
            difficulty = "Staff / Principal"
        ),
        InterviewQuestion(
            id = "iq_lld_071",
            trackId = "lld_interview",
            conceptId = "lld_behavioral_patterns",
            conceptName = "Behavioral Design Patterns & Event Handling",
            title = "Interpreter Pattern: Domain-Specific Languages (DSL) and Expression Trees",
            question = "What is the Interpreter pattern? How do you design an Abstract Syntax Tree (AST) evaluator for boolean expressions (e.g. `(A AND B) OR (NOT C)`)?",
            shortAnswer = "The Interpreter pattern defines a grammatical representation for a language and an interpreter to evaluate sentences in the language. Design: (1) Abstract Expression: `interface Expression { boolean interpret(Context ctx); }`. (2) Terminal Expressions: Leaf nodes representing variables or constants: `class VariableExpression implements Expression { public boolean interpret(Context ctx) { return ctx.get(varName); } }`. (3) Non-Terminal Expressions: Composite nodes combining expressions with operators: `class AndExpression implements Expression { Expression left, right; public boolean interpret(Context ctx) { return left.interpret(ctx) && right.interpret(ctx); } }`. Expressions compose into an AST evaluated recursively.",
            keyPoints = listOf(
                "Defines a grammar for a language and an interpreter tree to evaluate sentences in that grammar",
                "Terminal Expressions represent atomic leaf tokens (variables, literals, numbers)",
                "Non-Terminal Expressions represent composite grammatical rules and operators (AND, OR, NOT, ADD)",
                "Constructs an Abstract Syntax Tree (AST) evaluated recursively by calling interpret(Context)",
                "Best suited for simple, stable grammars; complex languages should use dedicated parser generators (ANTLR)"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_lld_072",
            trackId = "lld_interview",
            conceptId = "lld_behavioral_patterns",
            conceptName = "Behavioral Design Patterns & Event Handling",
            title = "State vs Strategy: Architectural Comparison Matrix",
            question = "State and Strategy patterns have identical UML class diagrams (Context delegating to an abstract interface). What is their fundamental difference in intent, coupling, and lifecycle?",
            shortAnswer = "Differences: (1) Intent: Strategy encapsulates interchangeable algorithms chosen by the client; State encapsulates states and lifecycle transitions that change object behavior dynamically. (2) Who Initiates Change: In Strategy, the client typically configures the strategy once at startup or explicitly swaps it. In State, state transitions are driven automatically by internal events or by the concrete state classes themselves. (3) Knowledge of Siblings: Concrete Strategies are completely independent and oblivious to the existence of other strategies. Concrete States often know about each other because they actively transition the context to the next state (`context.setState(new ShippedState())`).",
            keyPoints = listOf(
                "Identical structural UML diagrams but fundamentally different design intents and coupling semantics",
                "Strategy patterns encapsulate algorithms; State patterns encapsulate lifecycle phases and transition rules",
                "Strategies are selected externally by client callers; States transition automatically based on internal events",
                "Strategy classes are decoupled and unaware of siblings; State classes frequently know and transition to sibling states",
                "Context behavior changes dynamically in State as lifecycle advances; Strategy behavior is generally stable per task"
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_lld_073",
            trackId = "lld_interview",
            conceptId = "lld_behavioral_patterns",
            conceptName = "Behavioral Design Patterns & Event Handling",
            title = "Observer vs Mediator: Communication Decoupling Trade-offs",
            question = "Compare the Observer pattern with the Mediator pattern. When should you use a distributed pub/sub Observer vs a centralized Mediator coordinator?",
            shortAnswer = "1) Observer: Decentralized, one-to-many communication. A Subject broadcasts events blindly to any number of registered observers. The Subject doesn't know who is listening, and observers don't interact with each other; communication flows unidirectionally from Subject to Observers. Ideal for reactive event streams and UI model-view bindings. 2) Mediator: Centralized, many-to-many communication. Colleague objects communicate through the Mediator, which actively coordinates complex, multidirectional interactions between colleagues. The Mediator encapsulates workflow logic and state coordination. Ideal for complex form dialogues where widget interactions require centralized coordination.",
            keyPoints = listOf(
                "Observer provides decentralized, one-to-many broadcast communication (Subject to Observers)",
                "Mediator provides centralized, many-to-many coordination among disparate Colleague components",
                "Communication flows unidirectionally in Observer; communication flows multidirectionally through Mediator",
                "Observer subjects are oblivious to observer identities; Mediators contain explicit domain coordination logic",
                "Both patterns can be combined: Colleagues can observe a Mediator using the Observer pattern"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_lld_074",
            trackId = "lld_interview",
            conceptId = "lld_behavioral_patterns",
            conceptName = "Behavioral Design Patterns & Event Handling",
            title = "Rules Engine Pattern (Specification Pattern)",
            question = "How do you architect a composable Rules Engine using the Specification pattern? How do you combine discrete business rules using boolean logic (`and`, `or`, `not`)?",
            shortAnswer = "The Specification pattern encapsulates business rules into reusable, composable boolean predicates. Design: (1) Specification Interface: `public interface Specification<T> { boolean isSatisfiedBy(T candidate); default Specification<T> and(Specification<T> other) { return candidate -> isSatisfiedBy(candidate) && other.isSatisfiedBy(candidate); } default Specification<T> or(Specification<T> other) { ... } default Specification<T> not() { ... } }`. (2) Concrete Rules: `AgeAbove18Spec`, `CreditScoreAbove700Spec`, `HasActiveAccountSpec`. (3) Composition: Rules compose fluently: `Specification<User> loanApproval = ageSpec.and(creditSpec).or(vipSpec);`. If `loanApproval.isSatisfiedBy(user)` passes, loan is approved.",
            keyPoints = listOf(
                "Specification pattern encapsulates a business rule into an atomic, reusable boolean evaluation predicate",
                "Fluent combinator methods (and, or, not) allow complex domain policies to be composed from simple rules",
                "Eliminates deeply nested conditional if-statements scattered across domain services",
                "Rules can be evaluated in memory against domain entities or translated to database query predicates (SQL/JPA Criteria)",
                "Enables isolated unit testing of each business rule specification in complete independence"
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_lld_075",
            trackId = "lld_interview",
            conceptId = "lld_behavioral_patterns",
            conceptName = "Behavioral Design Patterns & Event Handling",
            title = "Event Aggregator / Event Bus Pattern in Low-Level Design",
            question = "What is an in-memory Event Bus (Guava EventBus / Spring ApplicationEventPublisher)? How does it decouple publisher and subscriber lifecycles without \$O(N^2)\$ registrations?",
            shortAnswer = "An in-memory Event Bus acts as a central pub/sub hub within a single JVM process. Instead of every subscriber registering individually with 50 different event producers (which creates an \$O(N \\times M)\$ registration web), all producers publish events to the central Event Bus (`eventBus.post(new OrderCompletedEvent(order))`). Subscribers register with the Event Bus using annotations (`@Subscribe public void onOrder(OrderCompletedEvent e)`). The Event Bus inspects the event's class type and dispatches it to all subscribed handler methods via reflection or compiled method handles, achieving total decoupling between publishers and consumers.",
            keyPoints = listOf(
                "Centralizes in-process event dispatching, eliminating direct producer-subscriber registration coupling",
                "Publishers post strongly typed domain event objects without knowing who or how many consumers exist",
                "Annotation-driven handlers (@Subscribe) automatically receive events matching their parameter type",
                "Supports synchronous in-thread dispatch or asynchronous thread-pool dispatch for non-blocking handlers",
                "Disadvantage: can make code harder to trace and debug because control flow is completely implicit"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_lld_076",
            trackId = "lld_interview",
            conceptId = "lld_behavioral_patterns",
            conceptName = "Behavioral Design Patterns & Event Handling",
            title = "Command vs Strategy Pattern: Distinguishing Action from Algorithm",
            question = "Both Command and Strategy encapsulate behavior in an object. What is the fundamental conceptual difference between an 'Action' (Command) and an 'Algorithm' (Strategy)?",
            shortAnswer = "Differences: (1) Strategy encapsulates HOW to do something (an algorithm: how to sort, how to calculate tax, how to compress). The client intends to do the action now and uses the strategy to perform it. Strategies are typically stateless and reused across many invocations. (2) Command encapsulates WHAT needs to be done (an intent to execute an action: paste text, delete file, transfer \$100). Commands carry their own state (parameters, target receiver, timestamps) and are created for single execution. Commands can be stored in queues, scheduled for delayed execution, serialized over network, or held in stacks for Undo operations.",
            keyPoints = listOf(
                "Strategy represents an interchangeable Algorithm (how to perform an operation)",
                "Command represents an encapsulated Action or Request (what operation to perform and its parameters)",
                "Strategies are typically stateless and long-lived; Commands are stateful and frequently single-use",
                "Commands can be queued, delayed, logged to disk, and undone; Strategies are executed immediately in-flow",
                "A Command may internally use a Strategy to execute its underlying business calculation"
            ),
            difficulty = "Mid-Level"
        )
    )
    private fun part5(): List<InterviewQuestion> = listOf(
        InterviewQuestion(
            id = "iq_lld_077",
            trackId = "lld_interview",
            conceptId = "lld_behavioral_patterns",
            conceptName = "Behavioral Design Patterns & Event Handling",
            title = "Implementing Undo/Redo by Combining Command and Memento",
            question = "How do you combine the Command pattern with the Memento pattern to build a bulletproof Undo/Redo architecture in an interactive text or graphic editor?",
            shortAnswer = "Architecture: (1) Receiver (`Canvas` / `TextBuffer`): Contains complex mutable state. (2) Memento: Immutable snapshot of canvas state at a specific instant. (3) Command: Concrete commands (`DrawShapeCommand`, `EraseCommand`) hold a reference to the Receiver. Before modifying state in `execute()`, the command asks the receiver for a memento: `this.backup = receiver.createMemento()`, then applies the mutation. (4) Reversal: When `undo()` is called on the command, it simply calls `receiver.restore(this.backup)`. The command history stack manages the commands, while Memento guarantees state restoration without violating encapsulation.",
            keyPoints = listOf(
                "Command pattern coordinates the execution flow and history stack ordering",
                "Memento pattern captures and restores the private state snapshot of the receiver entity",
                "Before mutating state in execute(), the Command captures a Memento snapshot from the Receiver",
                "Undo() simply passes the captured Memento back to the Receiver for instantaneous state restoration",
                "Combines clean separation of concerns: commands manage workflow; mementos preserve state integrity"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_lld_078",
            trackId = "lld_interview",
            conceptId = "lld_behavioral_patterns",
            conceptName = "Behavioral Design Patterns & Event Handling",
            title = "Functional Programming Impact on GoF Behavioral Patterns",
            question = "How has the introduction of First-Class Functions and Lambdas in modern Java/Kotlin made several classic GoF Behavioral patterns obsolete or trivial?",
            shortAnswer = "In original OOP, behaviors had to be wrapped in classes because functions were not first-class citizens. Modern FP impacts: (1) Strategy: Obsolete as boilerplate classes; replaced directly by lambda functions (`Function<T, R>`, `Comparator`). (2) Command: Replaced by `Runnable`, `Callable`, or `Consumer<T>`. (3) Template Method: Replaced by passing lambda higher-order functions to execute custom steps without subclassing. (4) Iterator: Replaced by declarative Stream APIs (`map`, `filter`, `reduce`). However, complex patterns like State (state machines), Memento (snapshots), and Visitor (double dispatch over ASTs) remain highly relevant.",
            keyPoints = listOf(
                "First-class functions eliminate boilerplate classes for single-method patterns (Strategy, Command)",
                "Higher-order functions replace Template Method inheritance with composition of lambda steps",
                "Java Stream API and Kotlin sequences make external imperative iterators largely redundant",
                "Complex stateful patterns (State, Memento, Visitor) remain essential and cannot be reduced to simple lambdas",
                "Combines the best of OOP (rich domain models) with the conciseness of Functional Programming"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_lld_079",
            trackId = "lld_interview",
            conceptId = "lld_behavioral_patterns",
            conceptName = "Behavioral Design Patterns & Event Handling",
            title = "Template Method vs Strategy Decision Guidelines",
            question = "When refactoring legacy code, what factors indicate you should use Strategy rather than Template Method, and vice-versa?",
            shortAnswer = "Guidelines: (1) Choose Template Method when: The algorithm's workflow steps are strictly invariant and must never change, and only 1-2 individual internal steps need customization by subclasses. It is best when code reuse among closely related internal classes within the same framework is the primary goal. (2) Choose Strategy when: The entire algorithm needs to be swappable dynamically at runtime, or when you want to adhere to 'Composition over Inheritance' to avoid fragile base classes. Strategy is superior when algorithms might be written by third-party clients or when algorithms have completely different execution strategies.",
            keyPoints = listOf(
                "Use Template Method when algorithm workflow steps are strictly fixed and only individual steps vary",
                "Template Method tightly couples subclasses to superclass step execution order via class inheritance",
                "Use Strategy when the entire algorithm needs to be swappable dynamically at runtime",
                "Strategy favors composition over inheritance, keeping algorithms independent of calling context classes",
                "Strategy avoids class explosion when multiple algorithm dimensions vary independently"
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_lld_080",
            trackId = "lld_interview",
            conceptId = "lld_behavioral_patterns",
            conceptName = "Behavioral Design Patterns & Event Handling",
            title = "Null Object Pattern Implementation for Behavioral Fallbacks",
            question = "How do you implement the Null Object pattern to provide safe, silent default behavior in an event processing pipeline without scattering `if (handler != null)` checks?",
            shortAnswer = "Design: (1) Interface: `public interface AuditLogger { void log(String message); }`. (2) Real Implementation: `DatabaseAuditLogger` writes to database. (3) Null Implementation: `public class NullAuditLogger implements AuditLogger { public static final NullAuditLogger INSTANCE = new NullAuditLogger(); private NullAuditLogger() {} public void log(String msg) { /* do nothing */ } }`. (4) Context: Context initializes its logger to `NullAuditLogger.INSTANCE` by default. When an event occurs, it calls `logger.log(msg)` directly without null checks. If a real logger is injected, it uses it; otherwise, the null object safely no-ops, eliminating defensive branching.",
            keyPoints = listOf(
                "Null Object provides a polymorphic, do-nothing implementation of a domain interface",
                "Completely eliminates defensive null-checking branches (if (listener != null)) across call sites",
                "Typically implemented as an immutable, thread-safe Singleton to conserve memory allocations",
                "Ensures client code can invoke methods safely without fear of NullPointerExceptions",
                "Ideal for optional logging, metrics collectors, fallback discounts, and no-op event listeners"
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_lld_081",
            trackId = "lld_interview",
            conceptId = "lld_concurrency_patterns",
            conceptName = "Concurrency, Thread Safety & Async Design",
            title = "Producer-Consumer Pattern: Bounded Queue and Condition Variables",
            question = "How do you implement a thread-safe Bounded Blocking Queue from scratch in Java using `ReentrantLock` and `Condition` variables? Why is `while` mandatory over `if` when awaiting conditions?",
            shortAnswer = "Implementation: Uses a `ReentrantLock` with two condition variables: `notFull = lock.newCondition()` and `notEmpty = lock.newCondition()`. (1) `put(item)`: Acquires lock. If queue is full, calls `notFull.await()`. Inserts item, calls `notEmpty.signal()`, and releases lock. (2) `take()`: Acquires lock. If queue is empty, calls `notEmpty.await()`. Extracts item, calls `notFull.signal()`, and releases lock. `while` loop is strictly mandatory (never `if`) because of: (a) Spurious Wakeups: The OS thread scheduler may wake waiting threads without any signal. (b) Race Conditions: Multiple waiting threads wake up simultaneously; only one can claim the slot, so remaining threads must re-check condition.",
            keyPoints = listOf(
                "ReentrantLock paired with separate notFull and notEmpty Condition variables coordinates buffer state",
                "while loop is mandatory around condition.await() to defend against spurious wakeups and multi-thread race conditions",
                "notEmpty.signal() wakes waiting consumer threads when a producer adds an item",
                "notFull.signal() wakes waiting producer threads when a consumer extracts an item",
                "Poison Pill pattern (inserting a sentinel termination object) signals consumers to gracefully shut down"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_lld_082",
            trackId = "lld_interview",
            conceptId = "lld_concurrency_patterns",
            conceptName = "Concurrency, Thread Safety & Async Design",
            title = "Read-Write Lock Pattern: ReentrantReadWriteLock vs StampedLock Optimistic Reads",
            question = "How does the Read-Write Lock pattern improve concurrency for read-heavy workloads? How does Java 8's `StampedLock` eliminate write starvation using Optimistic Reads?",
            shortAnswer = "1) ReentrantReadWriteLock: Allows multiple concurrent readers (Shared Lock) or exactly one writer (Exclusive Lock). Problem: Write Starvation: If there is a continuous stream of incoming readers, writers starve and wait indefinitely. 2) StampedLock Optimistic Reads: StampedLock provides an Optimistic Read mode that acquires NO locks: `long stamp = lock.tryOptimisticRead()`. The reader reads fields, then calls `lock.validate(stamp)`. If no write occurred while reading, the read is valid without having acquired any lock or written to memory barriers! If a write intervened (`validate` returns false), the reader falls back to acquiring a standard pessimistic read lock. Writers never starve.",
            keyPoints = listOf(
                "Read-Write Lock allows concurrent reads while granting exclusive access to single writers",
                "ReentrantReadWriteLock can cause write starvation when reader threads continuously hold shared locks",
                "StampedLock tryOptimisticRead() reads fields completely lock-free without blocking incoming writers",
                "lock.validate(stamp) verifies whether an exclusive write intervened during the optimistic read phase",
                "StampedLock is not reentrant; attempting to acquire the same lock twice on the same thread deadlocks"
            ),
            difficulty = "Staff / Principal"
        ),
        InterviewQuestion(
            id = "iq_lld_083",
            trackId = "lld_interview",
            conceptId = "lld_concurrency_patterns",
            conceptName = "Concurrency, Thread Safety & Async Design",
            title = "ThreadPoolExecutor Architecture and Saturation Rejection Policies",
            question = "Explain the internal task scheduling algorithm of Java's `ThreadPoolExecutor`. Detail the 4 standard RejectedExecutionHandler policies and when each should be used.",
            shortAnswer = "Scheduling Algorithm: When a task submits: (1) If active threads < `corePoolSize`, create a new worker thread. (2) If core threads are busy, queue task in `workQueue`. (3) If queue is FULL and threads < `maximumPoolSize`, spawn a new thread. (4) If queue is FULL and threads == `maximumPoolSize`, invoke `RejectedExecutionHandler`. Rejection Policies: (a) `AbortPolicy` (default): Throws `RejectedExecutionException`. Good for fail-fast systems. (b) `CallerRunsPolicy`: The thread calling `submit()` executes the task itself! Slows down the producer, providing natural backpressure. (c) `DiscardPolicy`: Silently drops the task. Good for non-critical telemetry. (d) `DiscardOldestPolicy`: Drops the oldest unhandled task in the queue and retries.",
            keyPoints = listOf(
                "Tasks populate the workQueue BEFORE the thread pool expands beyond corePoolSize to maximumPoolSize",
                "AbortPolicy (default) throws RejectedExecutionException to alert callers immediately of pool saturation",
                "CallerRunsPolicy forces the submitting producer thread to execute the task, creating natural backpressure",
                "DiscardPolicy silently drops the rejected task; DiscardOldestPolicy drops the oldest waiting task in the queue",
                "Unbounded queues (LinkedBlockingQueue with default capacity) cause pool to never expand beyond corePoolSize and risk OOM"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_lld_084",
            trackId = "lld_interview",
            conceptId = "lld_concurrency_patterns",
            conceptName = "Concurrency, Thread Safety & Async Design",
            title = "CompletableFuture Async Pipeline Composition and Error Handling",
            question = "How do you construct non-blocking asynchronous processing pipelines using `CompletableFuture`? Contrast `thenApply`, `thenCompose`, and `thenCombine`, and explain exception recovery.",
            shortAnswer = "1) Methods: (a) `thenApply(fn)`: Transforms value synchronously (\$T \\to U\$), similar to `map()`. (b) `thenCompose(fn)`: Flattens nested futures (\$T \\to \\text{CompletableFuture}<U>\$), similar to `flatMap()`, used for dependent async chaining (Call B after Call A finishes). (c) `thenCombine(other, fn)`: Executes two independent futures concurrently in parallel and combines their results when both complete. 2) Non-blocking Threading: Append `Async` (`thenApplyAsync`) to execute the stage on a custom `Executor` pool rather than the completing thread. 3) Error Handling: `.exceptionally(ex -> fallbackValue)` catches errors and returns a fallback; `.handle((res, ex) -> ...)` processes both success and error simultaneously.",
            keyPoints = listOf(
                "CompletableFuture enables non-blocking, event-driven asynchronous programming pipelines in Java",
                "thenApply transforms values synchronously; thenCompose flattens and chains dependent asynchronous operations",
                "thenCombine executes two independent asynchronous stages concurrently and merges their results",
                "Async suffix methods (thenApplyAsync) offload stage execution to a designated custom thread pool executor",
                "exceptionally() and handle() provide resilient error catching and graceful fallback recovery in pipelines"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_lld_085",
            trackId = "lld_interview",
            conceptId = "lld_concurrency_patterns",
            conceptName = "Concurrency, Thread Safety & Async Design",
            title = "Semaphore Pattern: Concurrency Throttling vs Mutex",
            question = "What is the Semaphore pattern? How does a counting Semaphore differ from a Mutex lock, and how do you implement a concurrency rate limiter for external API calls?",
            shortAnswer = "A Counting Semaphore maintains a set of permits. Threads call `acquire()` to take a permit (blocking if none available) and `release()` to return a permit. Differences: (1) Capacity: A Mutex is binary (capacity 1); a Semaphore has \$N\$ permits. (2) Ownership: A Mutex has an owner: only the thread that acquired the mutex can unlock it. A Semaphore has NO concept of thread ownership: Thread A can acquire a permit, and Thread B can release it. Rate Limiting Example: To restrict concurrent outbound calls to a third-party payment API to max 10 concurrent requests, initialize `Semaphore(10, true)`. Worker threads must acquire a permit in a `try-finally` block around the HTTP request.",
            keyPoints = listOf(
                "Counting Semaphore manages a fixed number of permits, controlling concurrent access to a shared resource",
                "Mutex is binary and enforces thread ownership: only the acquiring thread can legally release the lock",
                "Semaphore permits can be released by a different thread than the one that acquired them (no ownership)",
                "Fairness flag (new Semaphore(N, true)) grants permits in FIFO arrival order to prevent thread starvation",
                "try-finally block is strictly mandatory to ensure semaphore permits are released during unexpected exceptions"
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_lld_086",
            trackId = "lld_interview",
            conceptId = "lld_concurrency_patterns",
            conceptName = "Concurrency, Thread Safety & Async Design",
            title = "CountDownLatch vs CyclicBarrier vs Phaser",
            question = "Compare Java's synchronization barriers: `CountDownLatch`, `CyclicBarrier`, and `Phaser`. When should each be chosen for coordinating multi-threaded workflows?",
            shortAnswer = "1) CountDownLatch: One-time coordination tool. One or more threads wait (`await()`) until a counter decrements to zero (`countDown()`). Cannot be reset or reused once the latch opens. Ideal for starting a race or waiting for \$N\$ startup services to initialize. 2) CyclicBarrier: Reusable barrier where \$N\$ worker threads wait for each other at a synchronization point (`barrier.await()`). Once all \$N\$ threads arrive, an optional barrier action runs, and the barrier resets automatically for the next cycle. Ideal for iterative parallel simulations (e.g. multi-step matrix calculations). 3) Phaser: Flexible, dynamic barrier where the number of registered parties can register and deregister dynamically at runtime across multiple phases.",
            keyPoints = listOf(
                "CountDownLatch is non-reusable; counts down to zero to unblock waiting threads (one-shot coordination)",
                "CyclicBarrier is reusable; synchronizes N threads at a common barrier point before advancing together in cycles",
                "CyclicBarrier can execute a designated Runnable barrier action when all participating threads arrive",
                "Phaser supports dynamic registration and deregistration of participating parties across multi-phase workflows",
                "CountDownLatch tracks event counts; CyclicBarrier tracks waiting thread counts"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_lld_087",
            trackId = "lld_interview",
            conceptId = "lld_concurrency_patterns",
            conceptName = "Concurrency, Thread Safety & Async Design",
            title = "Lock-Free Patterns: Compare-And-Swap (CAS) and the ABA Problem",
            question = "How do lock-free data structures achieve thread safety using Compare-And-Swap (CAS)? What is the ABA problem, and how does `AtomicStampedReference` eliminate it?",
            shortAnswer = "CAS is a hardware-supported atomic CPU instruction (`cmpxchg`) that compares memory location \$V\$ against expected value \$A\$; if matching, it swaps \$V\$ to new value \$B\$ atomically in a single clock cycle. If it fails, the thread retries in a loop without sleeping or context switching. The ABA Problem: Thread 1 reads value \$A\$. Thread 2 changes \$A \\to B\$, and then changes \$B \\to A\$. When Thread 1 executes CAS, the value is \$A\$, so CAS succeeds! In pointer-based data structures (lock-free stacks/pools), this causes severe node recycling memory corruption because the state changed despite value equality. Fix: Pair references with a monotonic version stamp using `AtomicStampedReference<T>`: CAS checks both `(reference, stamp)`.",
            keyPoints = listOf(
                "Compare-And-Swap (CAS) provides atomic, non-blocking hardware instruction updates without OS thread locks",
                "Optimistic retry loops (while (!cas())) achieve extreme throughput under low-to-moderate thread contention",
                "ABA problem occurs when a memory location transitions from A to B and back to A, deceiving naive CAS checks",
                "In lock-free linked stacks, the ABA problem leads to severed node pointers and catastrophic memory corruption",
                "AtomicStampedReference associates an integer version stamp with the pointer, neutralizing the ABA hazard"
            ),
            difficulty = "Staff / Principal"
        ),
        InterviewQuestion(
            id = "iq_lld_088",
            trackId = "lld_interview",
            conceptId = "lld_concurrency_patterns",
            conceptName = "Concurrency, Thread Safety & Async Design",
            title = "LMAX Disruptor: Mechanical Sympathy and Ring Buffer Architecture",
            question = "Why is the LMAX Disruptor 10x faster than `ArrayBlockingQueue`? Explain False Sharing, CPU cache-line padding, and the lock-free circular Ring Buffer.",
            shortAnswer = "1) Why Queues are Slow: `ArrayBlockingQueue` uses locks/conditions and causes lock contention, context switches, and cache misses. Both head and tail pointers reside on the same 64-byte CPU cache line, causing False Sharing (CPU cores continuously invalidate each other's L1/L2 caches). 2) LMAX Disruptor: (a) Pre-allocated Ring Buffer: An array with size power-of-two (fast bitwise modulo `index & (size - 1)`). Pre-allocated objects eliminate GC. (b) Sequence Pointers: Producers and consumers claim sequence numbers using atomic CAS. (c) Cache-Line Padding: Sequences are padded with 7 unused `long` variables (56 bytes) to ensure each sequence sits exclusively on its own 64-byte CPU cache line, completely eliminating False Sharing.",
            keyPoints = listOf(
                "Pre-allocated circular ring buffer eliminates runtime memory allocations and garbage collection pauses",
                "Power-of-two buffer sizing replaces expensive division modulo operations with single-cycle bitwise AND indexing",
                "False Sharing occurs when unrelated variables share the same 64-byte CPU cache line, triggering cache invalidations",
                "Cache line padding pads sequence counters with dummy variables to isolate them onto dedicated cache lines",
                "Single-producer or multi-producer CAS sequencing delivers tens of millions of operations per second per node"
            ),
            difficulty = "Staff / Principal"
        ),
        InterviewQuestion(
            id = "iq_lld_089",
            trackId = "lld_interview",
            conceptId = "lld_concurrency_patterns",
            conceptName = "Concurrency, Thread Safety & Async Design",
            title = "Half-Sync / Half-Async Pattern: Netty / Reactor I/O Architecture",
            question = "How does the Half-Sync/Half-Async pattern decouple asynchronous I/O demultiplexing from synchronous business logic execution in network engines like Netty?",
            shortAnswer = "The Half-Sync/Half-Async pattern balances asynchronous I/O performance with synchronous programming simplicity. Architecture: (1) Async Service Layer (Event Loop / Selector): A small number of non-blocking I/O threads (e.g. 1 per CPU core) handle socket readiness (`epoll`/`kqueue`). They never perform blocking operations (no DB calls, no heavy computation). (2) Sync Service Layer (Worker Thread Pool): Business logic often requires blocking calls (JDBC database queries, third-party REST APIs). (3) Queueing Layer: The async event loop reads incoming socket bytes, packages them into a request object, and enqueues them into an in-memory queue. Synchronous worker threads pull from the queue, execute blocking business logic, and hand results back.",
            keyPoints = listOf(
                "Decomposes system into an asynchronous non-blocking I/O event layer and a synchronous worker processing layer",
                "Async event loop threads handle socket epoll multiplexing without ever executing blocking operations",
                "Synchronous worker thread pool absorbs blocking database queries and long-running business computation",
                "Bounded blocking queue mediates communication between the async layer and the synchronous worker pool",
                "Core architectural foundation of high-performance networking engines (Netty, Node.js libuv, Java NIO)"
            ),
            difficulty = "Staff / Principal"
        ),
        InterviewQuestion(
            id = "iq_lld_090",
            trackId = "lld_interview",
            conceptId = "lld_concurrency_patterns",
            conceptName = "Concurrency, Thread Safety & Async Design",
            title = "Active Object Pattern: Decoupling Method Invocation from Execution",
            question = "What is the Active Object design pattern? How does it decouple method invocation on an object from its execution, running methods on a private thread?",
            shortAnswer = "The Active Object pattern allows an object to execute method invocations asynchronously on its own private thread of control, making method calls thread-safe without explicit caller locking. Components: (1) Proxy: Exposes the public API; when a method is called, it constructs a `MethodRequest` object and enqueues it. It returns a `Future` immediately to the caller. (2) Activation List: An internal thread-safe queue holding pending method requests. (3) Scheduler: A dedicated single background thread pulling requests from the queue and executing them sequentially on the (4) Servant (the real implementation object). Because the servant is accessed only by the scheduler thread, it requires no internal locks.",
            keyPoints = listOf(
                "Decouples client method invocation from actual method execution onto an autonomous private thread",
                "Client receives an immediate Future/Promise while the method request enqueues in an activation list",
                "Dedicated scheduler thread executes queued method requests sequentially against the servant object",
                "The servant object is strictly single-threaded, eliminating internal synchronization and race conditions",
                "Precursor to the modern Actor Model (Akka, Erlang) where actors process messages from an internal mailbox"
            ),
            difficulty = "Staff / Principal"
        ),
        InterviewQuestion(
            id = "iq_lld_091",
            trackId = "lld_interview",
            conceptId = "lld_concurrency_patterns",
            conceptName = "Concurrency, Thread Safety & Async Design",
            title = "Deadlock Detection and Prevention in Low-Level Design",
            question = "What are Coffman's 4 conditions for Deadlock? How do you guarantee deadlock prevention using Resource Ordering, and how does `tryLock()` provide deadlock recovery?",
            shortAnswer = "Coffman's 4 Conditions: (1) Mutual Exclusion, (2) Hold and Wait, (3) No Preemption, (4) Circular Wait. Breaking any one condition prevents deadlock. (a) Resource Ordering (Eliminates Circular Wait): Assign a global canonical order to all locks (e.g. by account ID hash or ID). When transferring money between Account A and B, always acquire lock on `min(A.id, B.id)` first, then `max(A.id, B.id)`. Two threads transferring \$A \\to B\$ and \$B \\to A\$ will acquire locks in the exact same order, eliminating circular wait. (b) Timed Try-Lock (Eliminates Hold and Wait): Use `lock.tryLock(timeout)`. If the second lock cannot be acquired within 100ms, release the first lock, back off randomly, and retry.",
            keyPoints = listOf(
                "Deadlock requires 4 Coffman conditions: Mutual Exclusion, Hold & Wait, No Preemption, and Circular Wait",
                "Global Resource Ordering eliminates Circular Wait by enforcing that all threads acquire locks in identical sequential order",
                "ReentrantLock tryLock(timeout) eliminates indefinite blocking, allowing threads to release acquired locks and back off",
                "Wait-For Graph cycle detection algorithms identify deadlocks dynamically by checking for directed graph cycles",
                "Thread dumps (jstack) analyze thread states (BLOCKED) to diagnose production deadlocks and identify offending lock addresses"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_lld_092",
            trackId = "lld_interview",
            conceptId = "lld_concurrency_patterns",
            conceptName = "Concurrency, Thread Safety & Async Design",
            title = "Graceful Shutdown of ThreadPoolExecutor: shutdown vs shutdownNow",
            question = "How do you implement a graceful shutdown procedure for a `ThreadPoolExecutor`? Contrast `shutdown()` with `shutdownNow()`, and explain the role of thread interrupts.",
            shortAnswer = "Recommended two-phase shutdown idiom (Oracle standard): (1) Call `pool.shutdown()`: Rejects new incoming tasks, but allows currently executing tasks and already queued tasks to finish processing. (2) Wait for completion: Call `pool.awaitTermination(30, TimeUnit.SECONDS)`. (3) Force termination if hung: If timeout expires, call `pool.shutdownNow()`: Cancels queued tasks (returns them as a `List<Runnable>`) and sends `Thread.interrupt()` to all actively executing worker threads. (4) Wait again: Call `awaitTermination(30, SECONDS)`. Tasks must be designed to be interruptible: long loops or blocking calls must check `Thread.currentThread().isInterrupted()` and exit cleanly.",
            keyPoints = listOf(
                "shutdown() stops accepting new tasks while allowing executing and queued tasks to finish gracefully",
                "shutdownNow() halts queued tasks and transmits thread interrupts to actively executing worker threads",
                "Tasks must handle thread interrupts cleanly by checking Thread.currentThread().isInterrupted()",
                "awaitTermination() blocks the coordinator thread until all workers complete or a timeout threshold elapses",
                "Two-phase shutdown idiom combines shutdown() with fallback shutdownNow() on timeout for robust termination"
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_lld_093",
            trackId = "lld_interview",
            conceptId = "lld_concurrency_patterns",
            conceptName = "Concurrency, Thread Safety & Async Design",
            title = "Virtual Threads (Java 21 Project Loom) Impact on Concurrency Patterns",
            question = "How do Virtual Threads (Project Loom) in Java 21 change concurrent low-level design? Why does thread pooling become an anti-pattern for virtual threads, and what is 'Thread Pinning'?",
            shortAnswer = "1) Virtual Threads: Lightweight user-mode threads managed by the JVM rather than OS kernel threads. A single JVM can run 1,000,000 virtual threads concurrently. When a virtual thread executes blocking I/O (socket read, file read, `Thread.sleep`), the JVM unmounts it from the underlying carrier OS thread and parks it in memory, allowing the carrier thread to run other virtual threads. 2) Thread Pooling Anti-Pattern: Pooling was invented because OS threads are heavy (1MB stack, slow creation). Virtual threads are cheap (1KB, instant creation); pooling them is an anti-pattern! Use `Executors.newVirtualThreadPerTaskExecutor()`, spawning a new virtual thread per request. 3) Thread Pinning: Occurs when a virtual thread blocks inside a `synchronized` block or native method, pinning the carrier OS thread and preventing other virtual threads from running. Fix: Replace `synchronized` with `ReentrantLock`.",
            keyPoints = listOf(
                "Virtual threads are cheap, user-space threads managed by the JVM runtime rather than 1:1 OS kernel threads",
                "Thread pooling is an anti-pattern for virtual threads; create fresh virtual threads per task and let them terminate",
                "Blocking I/O unmounts virtual threads from carrier OS threads automatically without consuming OS thread capacity",
                "Thread Pinning occurs when blocking operations execute inside 'synchronized' blocks, blocking the carrier OS thread",
                "Replace synchronized blocks with ReentrantLock to prevent thread pinning and maintain high concurrency throughput"
            ),
            difficulty = "Staff / Principal"
        ),
        InterviewQuestion(
            id = "iq_lld_094",
            trackId = "lld_interview",
            conceptId = "lld_concurrency_patterns",
            conceptName = "Concurrency, Thread Safety & Async Design",
            title = "Thread-Safe Cache with TTL Eviction using DelayQueue",
            question = "How do you design a thread-safe in-memory cache supporting Time-To-Live (TTL) expiration using `ConcurrentHashMap` and `DelayQueue` without spinning CPU threads?",
            shortAnswer = "Design: (1) Storage: `ConcurrentHashMap<K, V> map` stores active key-value pairs. (2) Expiration Queue: `DelayQueue<DelayedKey<K>> queue` stores keys wrapped in a `Delayed` object implementing `getDelay(unit)` and `compareTo()`. (3) Write: `put(key, val, ttl)` puts `(key, val)` in the map and enqueues `new DelayedKey(key, System.currentTimeMillis() + ttl)` into the `DelayQueue`. (4) Read: `get(key)` returns from map in \$O(1)\$ time. (5) Expiration Cleaner: A single background daemon thread executes: `while (!stopped) { DelayedKey dk = queue.take(); map.remove(dk.getKey()); }`. `DelayQueue.take()` sleeps cleanly until the earliest item's TTL expires, consuming 0% CPU while idle.",
            keyPoints = listOf(
                "ConcurrentHashMap provides O(1) lock-free thread-safe reads and concurrent writes for active cached values",
                "DelayQueue orders expiration keys by remaining TTL, blocking cleaner threads until the next item expires",
                "Background cleaner thread consumes expired keys from DelayQueue.take() without burning CPU in busy-wait polling",
                "Delayed interface requires implementing getDelay(TimeUnit) and compareTo() to sequence expiration timestamps",
                "Overwriting an existing key requires invalidating or superseding its previous DelayQueue entry to prevent early eviction"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_lld_095",
            trackId = "lld_interview",
            conceptId = "lld_concurrency_patterns",
            conceptName = "Concurrency, Thread Safety & Async Design",
            title = "Dining Philosophers Problem: Resource Hierarchy Solution",
            question = "How does Dijkstra's Resource Hierarchy solution resolve the Dining Philosophers synchronization problem, and how does it translate to locking multiple entities in banking transactions?",
            shortAnswer = "Problem: 5 philosophers sit around a table with 5 chopsticks; each needs both left and right chopsticks to eat. If all philosophers pick up their left chopstick simultaneously, everyone holds one and waits for the right, creating a circular deadlock. Dijkstra's Resource Hierarchy: Number the chopsticks 1 to 5. Rule: Philosophers must always pick up the lower-numbered chopstick first, then the higher-numbered chopstick. Philosopher 5 (between chopstick 5 and 1) must pick up chopstick 1 first, competing with Philosopher 1. This breaks the circular wait condition, guaranteeing at least one philosopher eats. In banking transfers: Always lock accounts by sorting their IDs: `lock(min(fromId, toId))` then `lock(max(fromId, toId))`, completely preventing deadlocks.",
            keyPoints = listOf(
                "Classic synchronization problem illustrating deadlocks arising from circular resource dependencies",
                "Dijkstra's Resource Hierarchy assigns a strict total ordering to all lockable resources in the system",
                "Threads must acquire locks strictly in ascending order of resource IDs, eliminating circular wait",
                "Applied to bank fund transfers: sorting account IDs before acquiring locks guarantees deadlock-free transfers",
                "Alternative solutions: Chandy-Misra drinking philosophers algorithm or waiter mediator arbitrator"
            ),
            difficulty = "Mid-Level"
        )
    )
    private fun part6(): List<InterviewQuestion> = listOf(
        InterviewQuestion(
            id = "iq_lld_096",
            trackId = "lld_interview",
            conceptId = "lld_concurrency_patterns",
            conceptName = "Concurrency, Thread Safety & Async Design",
            title = "Token Bucket Rate Limiter Implementation using Atomic Primitives",
            question = "How do you implement a thread-safe, lock-free in-memory Token Bucket rate limiter in Java using `AtomicLong` and `AtomicReference`?",
            shortAnswer = "Instead of running a background thread to add tokens every millisecond (which wastes CPU), calculate token refills lazily on demand upon each `tryAcquire()` request. State class: `class State { final long tokens; final long lastRefillTimestamp; }`. Logic in `tryAcquire(requested)`: In a CAS loop: (1) Read current state. (2) Calculate newly generated tokens: `newTokens = min(capacity, current.tokens + (now - current.lastRefillTimestamp) * refillRatePerMs)`. (3) If `newTokens < requested`, return false (rate limited). (4) New state = `new State(newTokens - requested, now)`. (5) Attempt `stateRef.compareAndSet(current, newState)`. If CAS succeeds, return true; if failed, retry loop.",
            keyPoints = listOf(
                "Lazy token calculation computes newly accumulated tokens upon request arrival, eliminating background refill threads",
                "Immutable State wrapper object pairs available token count with the last refill millisecond timestamp",
                "AtomicReference with CAS loop updates token count and timestamp atomically without mutual exclusion locks",
                "Burst capacity allows handling short traffic spikes up to maximum bucket capacity while enforcing sustained rate limits",
                "Non-blocking implementation delivers microsecond execution latency suitable for high-throughput API gateways"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_lld_097",
            trackId = "lld_interview",
            conceptId = "lld_concurrency_patterns",
            conceptName = "Concurrency, Thread Safety & Async Design",
            title = "Actor Model Concurrency in OOP (Akka / Erlang Mailbox Mechanics)",
            question = "How does the Actor Model eliminate shared mutable state and lock-based synchronization? How do Actors communicate via asynchronous Mailboxes?",
            shortAnswer = "In standard multi-threaded OOP, threads share mutable objects, requiring locks, volatile variables, and defensive copying. The Actor Model treats 'Actors' as autonomous computational entities. Principles: (1) Zero Shared State: An actor holds private state that cannot be accessed or mutated by any other object directly. (2) Asynchronous Mailboxes: To interact with an actor, callers send an immutable message. The message lands in the actor's FIFO Mailbox (concurrent queue). (3) Single-Threaded Processing: The actor's private thread (or event loop) processes messages from its mailbox one at a time sequentially. Because state is mutated exclusively by a single thread, NO locks, synchronization, or volatile keywords are ever needed inside the actor.",
            keyPoints = listOf(
                "Eliminates shared mutable memory by encapsulating state strictly within autonomous Actor boundaries",
                "All communication between actors occurs via asynchronous, immutable message passing",
                "Each actor maintains a private FIFO mailbox queue buffering incoming messages",
                "Actor processes messages sequentially one at a time, eliminating internal lock contention and race conditions",
                "Supervision hierarchies provide fault tolerance: parent actors supervise and restart crashed child actors"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_lld_098",
            trackId = "lld_interview",
            conceptId = "lld_concurrency_patterns",
            conceptName = "Concurrency, Thread Safety & Async Design",
            title = "Thread-Safe Lazy Initialization with VarHandle and Memory Barriers",
            question = "How do modern Java libraries (Netty, JDK internals) use `VarHandle` to achieve high-performance lazy initialization with plain memory reads and acquire/release semantics?",
            shortAnswer = "Standard volatile fields enforce full memory barriers on every read, incurring CPU cache synchronization overhead even after initialization is complete. `VarHandle` (Java 9+) provides fine-grained control over memory fence operations. In lazy initialization: The instance field is declared as a plain (non-volatile) reference. In `getInstance()`: First check uses a plain, free memory read. If null, use `VarHandle.getAcquire()` and `VarHandle.compareAndSet()` (release fence) inside synchronization. Once initialized, subsequent reads execute as plain reads with zero CPU cache fence overhead, yielding 2x-3x higher read throughput on multi-core ARM/x86 architectures than standard volatile reads.",
            keyPoints = listOf(
                "VarHandle provides fine-grained memory access modes (plain, opaque, acquire/release, full volatile)",
                "Acquire/Release semantics provide necessary visibility ordering guarantees without full bidirectional memory fence overhead",
                "Allows plain, zero-overhead memory reads once an object has been successfully initialized",
                "Replaces legacy, unsafe sun.misc.Unsafe direct memory manipulation with type-safe APIs",
                "Used extensively in modern high-performance Java frameworks (Netty, Caffeine Cache, JDK CompletableFuture)"
            ),
            difficulty = "Staff / Principal"
        ),
        InterviewQuestion(
            id = "iq_lld_099",
            trackId = "lld_interview",
            conceptId = "lld_concurrency_patterns",
            conceptName = "Concurrency, Thread Safety & Async Design",
            title = "Asynchronous Event Loop Pattern (Reactor Pattern in Java NIO)",
            question = "How does the Reactor pattern manage 100,000 concurrent network connections on a single thread using non-blocking I/O multiplexing (`Selector`)?",
            shortAnswer = "Traditional I/O assigns 1 thread per connection; 100k threads crash memory (100GB stack space) and destroy CPU throughput via context switching. The Reactor pattern uses an Event Loop running on a single thread. Components: (1) Demultiplexer (`Selector`): Registers thousands of non-blocking channels (`SocketChannel`) for interest events (`OP_READ`, `OP_WRITE`, `OP_ACCEPT`). (2) The Event Loop: Calls `selector.select()` (delegating to OS `epoll`/`kqueue`), which blocks until at least one channel is ready. (3) Event Dispatcher: Iterates through the ready `SelectedKeys` and dispatches each event to its registered non-blocking Handler. The handler processes bytes immediately and returns control to the loop in microseconds.",
            keyPoints = listOf(
                "Replaces thread-per-connection architecture with an event-driven non-blocking I/O multiplexing loop",
                "OS-level I/O demultiplexers (Linux epoll, BSD kqueue) notify the Java Selector when channels have readable bytes",
                "Single event loop thread handles thousands of active connections with minimal memory overhead",
                "Handlers must never perform blocking operations on the event loop thread to prevent freezing the entire server",
                "Foundation of reactive servers including Netty, Node.js, and Spring WebFlux"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_lld_100",
            trackId = "lld_interview",
            conceptId = "lld_concurrency_patterns",
            conceptName = "Concurrency, Thread Safety & Async Design",
            title = "Software Transactional Memory (STM) Concepts in Low-Level Design",
            question = "What is Software Transactional Memory (STM)? How does it bring database-like ACID transactions (`atomic`, `commit`, `rollback`) to in-memory object mutations?",
            shortAnswer = "STM (popularized by Clojure and Haskell) treats blocks of memory like a database. Instead of manual lock management (which causes deadlocks, race conditions, and priority inversions), code executes inside an `atomic` block: `atomic { accountA.balance -= 100; accountB.balance += 100; }`. The STM runtime tracks all reads and writes in a private transaction transaction log using Optimistic Concurrency Control (OCC). At the end of the block, STM attempts to commit: if no other thread modified the referenced memory locations during execution, the commit succeeds atomically. If a conflict is detected, the transaction aborts, rolls back memory mutations, and retries automatically.",
            keyPoints = listOf(
                "Brings database transaction semantics (Atomic, Consistent, Isolated) to in-memory object state mutations",
                "Eliminates manual lock orchestration, deadlock hazards, and race conditions from application code",
                "Uses Optimistic Concurrency Control (OCC) with private transaction logs tracking speculative memory reads and writes",
                "Conflicts trigger automatic transaction aborts, rollback of speculative memory changes, and transparent retries",
                "Excels in read-heavy and low-to-moderate contention workloads, but can experience retry thrashing under extreme write contention"
            ),
            difficulty = "Staff / Principal"
        ),
        InterviewQuestion(
            id = "iq_lld_101",
            trackId = "lld_interview",
            conceptId = "lld_machine_coding_games",
            conceptName = "Real-World Machine Coding: Games & Classic Systems",
            title = "Design a Multi-Floor Parking Lot System",
            question = "How do you design a low-level object-oriented Multi-Floor Parking Lot? Detail class relationships, spot allocation strategies (nearest to entrance vs floor capacity), and fee calculation.",
            shortAnswer = "1) Entities & Enums: `VehicleType` (MOTORCYCLE, CAR, TRUCK), `ParkingSpotType` (COMPACT, REGULAR, LARGE). Spot capacity matches vehicle sizes. 2) Parking Lot Hierarchy: `ParkingLot` has multiple `ParkingFloor`s; each floor has a list of `ParkingSpot`s. 3) Spot Allocation Strategy: Strategy pattern (`ParkingAssignmentStrategy`): (a) `NearestToEntranceStrategy`: Allocates the spot closest to entry gate using a Min-Heap (PriorityQueue) of available spots ordered by distance. (b) `BestFitStrategy`: Matches vehicle size to the smallest available compatible spot. 4) Concurrency: Spot assignment synchronizes on the selected spot or uses atomic compare-and-swap (`isOccupied.compareAndSet(false, true)`) to prevent two cars claiming the same spot. 5) Fee Calculation: Strategy pattern (`FeeStrategy`: Flat rate, Hourly, Dynamic peak hours).",
            keyPoints = listOf(
                "Hierarchy: ParkingLot -> ParkingFloor -> ParkingSpot; VehicleType maps to compatible ParkingSpotType",
                "Strategy pattern decouples spot allocation algorithms (Nearest to entrance, Lowest floor, Best fit)",
                "Min-Heap / PriorityQueue data structures maintain available spots ordered by entrance proximity in O(log N)",
                "Thread safety: atomic state flags or synchronized spot reservations prevent double-booking race conditions",
                "Strategy pattern encapsulates fee calculation models (hourly rate, weekend pricing, vehicle-type multipliers)"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_lld_102",
            trackId = "lld_interview",
            conceptId = "lld_machine_coding_games",
            conceptName = "Real-World Machine Coding: Games & Classic Systems",
            title = "Design an Elevator Control System with Multiple Cars",
            question = "How do you architect an elevator system managing multiple elevator cars across 50 floors? Detail internal car requests, external hall calls, and the LOOK/SCAN scheduling algorithm.",
            shortAnswer = "1) Core Entities: `ElevatorCar`, `Direction` (UP, DOWN, IDLE), `DoorState` (OPEN, CLOSED), `Button` (InternalButton, ExternalHallButton). 2) Scheduling Algorithm (LOOK / Elevator Algorithm): An elevator maintains two sorted sets of floor targets (UP queue in ascending order, DOWN queue in descending order). The car travels in the current direction servicing all requests until the queue is empty, then reverses direction, eliminating starvation. 3) Elevator Controller (Dispatcher): Manages all cars. When a user presses 'UP' on Floor 15: The Dispatcher evaluates which car has the lowest cost/distance to service the call (evaluating car direction, current floor, and pending stops). 4) Safety Invariants: Doors cannot open while car is moving; motor cannot engage while doors are open.",
            keyPoints = listOf(
                "Elevator car state machine manages Direction (UP, DOWN, IDLE) and DoorState (OPEN, CLOSING, CLOSED)",
                "LOOK/SCAN scheduling algorithm processes requests in the current direction before reversing, eliminating starvation",
                "Two sorted collections (TreeSet) maintain pending UP stops (ascending) and DOWN stops (descending)",
                "Central Dispatcher evaluates candidate cars, assigning external hall calls to the car with the minimal estimated arrival cost",
                "Hardware safety invariants enforced: movement methods strictly verify door closure before motor engagement"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_lld_103",
            trackId = "lld_interview",
            conceptId = "lld_machine_coding_games",
            conceptName = "Real-World Machine Coding: Games & Classic Systems",
            title = "Design a Vending Machine using the State Pattern",
            question = "How do you implement a classic Vending Machine using the State pattern? Detail the states (NoMoney, HasMoney, Dispensing, SoldOut) and coin change calculation.",
            shortAnswer = "1) State Pattern Architecture: `VendingMachine` (Context) holds `State` interface reference. Concrete states: `NoMoneyState`, `HasMoneyState`, `DispensingState`, `SoldOutState`. (a) `NoMoneyState`: Rejects item selection; accepts coin and transitions to `HasMoneyState`. (b) `HasMoneyState`: Allows item selection. If inserted money >= item price, transitions to `DispensingState`. If user presses 'Cancel', refunds money and transitions to `NoMoneyState`. (c) `DispensingState`: Deducts inventory slot, dispenses item, computes change, and returns to `NoMoneyState` (or `SoldOutState` if inventory empty). 2) Change Dispensing: Greedy algorithm / Coin change dynamic programming using available coin inventory denominations.",
            keyPoints = listOf(
                "State pattern encapsulates vending machine lifecycle transitions without monolithic switch-case statements",
                "Concrete states (NoMoney, HasMoney, Dispensing, SoldOut) validate and handle operations polymorphically",
                "Context (VendingMachine) maintains inventory slots and accumulated balance, delegating actions to active state",
                "Change calculation algorithm verifies available coin denomination inventory before finalizing transactions",
                "Refund cancellation transitions cleanly return inserted money and reset the machine state back to idle"
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_lld_104",
            trackId = "lld_interview",
            conceptId = "lld_machine_coding_games",
            conceptName = "Real-World Machine Coding: Games & Classic Systems",
            title = "Design a Movie Ticket / Concert Booking System with Seat Locking",
            question = "Design a low-level seat reservation engine for a cinema hall. How do you implement temporary 10-minute seat holds with automatic expiration and concurrency control?",
            shortAnswer = "1) Entities: `CinemaHall`, `Show`, `Seat` (Row, Col, SeatType: REGULAR, VIP), `SeatStatus` (AVAILABLE, HELD, BOOKED), `Booking`. 2) Seat Hold Architecture: In `ShowSeat`: holds `status`, `heldByUserId`, and `lockExpiryTimestamp`. 3) Concurrency & Atomic Locking: When user selects seats: acquire lock on `ShowSeat`. If `status == AVAILABLE` or (`status == HELD && now > lockExpiryTimestamp`), update `status = HELD`, `lockExpiryTimestamp = now + 10m`. In-memory implementation uses `ConcurrentHashMap` with atomic `compute()` or `ReentrantLock` per seat. 4) Expiration Engine: Scheduled executor runs every 30s to transition expired holds back to `AVAILABLE`. 5) Booking: On payment success, atomically update from `HELD` to `BOOKED`.",
            keyPoints = listOf(
                "SeatStatus state machine: AVAILABLE -> HELD -> BOOKED, with automatic rollback from HELD to AVAILABLE on timeout",
                "ShowSeat encapsulates seat pricing, seat tier, hold expiration timestamps, and reservation ownership",
                "Atomic seat locking using concurrent collections (ConcurrentHashMap.compute) prevents double-booking race conditions",
                "Scheduled background worker or lazy validation releases expired seat holds back to the public booking pool",
                "Final booking transaction verifies active hold ownership before confirming payment and issuing tickets"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_lld_105",
            trackId = "lld_interview",
            conceptId = "lld_machine_coding_games",
            conceptName = "Real-World Machine Coding: Games & Classic Systems",
            title = "Design a Complete Chess Game",
            question = "How do you model a complete Object-Oriented Chess Game? Detail Board representation, Piece movement validation, Check/Checkmate detection, and Special Moves (Castling, En Passant).",
            shortAnswer = "1) Model: `Board` (8x8 array of `Spot`), `Piece` (abstract class with `Color` and `abstract boolean canMove(Board, Spot start, Spot end)`). Concrete pieces: `King`, `Queen`, `Rook`, `Bishop`, `Knight`, `Pawn`. 2) Movement & Path Validation: Piece overrides `canMove()` to validate its geometric movement rules. Path clearing: Rook/Bishop/Queen verify all intermediate squares are empty. 3) King Safety & Check Detection: After any prospective move, verify the player's King is not under attack by any opposing piece. If in check, the move is invalid. 4) Special Moves: `Pawn` tracks first-move double-step to enable En Passant on the immediate next turn. `King` and `Rook` maintain `hasMoved` flags to validate Castling. 5) Game History & Undo: Command pattern captures `Move` records in a history stack.",
            keyPoints = listOf(
                "Polymorphic Piece hierarchy (King, Queen, Rook, Bishop, Knight, Pawn) encapsulates specific geometric movement rules",
                "Board represents an 8x8 grid of Spots, tracking piece positions and verifying unobstructed line-of-sight paths",
                "Check validation tests if any opposing piece can legally target the King's current spot coordinates",
                "State tracking supports special moves: boolean hasMoved flags for Castling and turn counters for En Passant",
                "Command pattern encapsulates moves into a game history stack to enable Undo, Redo, and PGN notation export"
            ),
            difficulty = "Staff / Principal"
        ),
        InterviewQuestion(
            id = "iq_lld_106",
            trackId = "lld_interview",
            conceptId = "lld_machine_coding_games",
            conceptName = "Real-World Machine Coding: Games & Classic Systems",
            title = "Design a Generalized NxN Tic-Tac-Toe Game with O(1) Win Checking",
            question = "Design an \$N \\times N\$ Tic-Tac-Toe game supporting \$M\$ players. How do you determine whether a winning move has occurred in \$O(1)\$ time complexity instead of scanning the \$N \\times N\$ grid?",
            shortAnswer = "1) Model: `Board` of size \$N\$, `Player` (with `PieceType`: X, O, etc.). 2) O(1) Win Checking Optimization: Scanning the board after every move takes \$O(N)\$ or \$O(N^2)\$. Optimization: Maintain count arrays for rows, columns, main diagonal, and anti-diagonal. For Player 1, add \$+1\$; for Player 2, add \$-1\$. When Player 1 places a piece at \$(r, c)\$: `rows[r]++`, `cols[c]++`. If \$r == c\$, `diagonal++`. If \$r + c == N - 1\$, `antiDiagonal++`. A winning move occurs IF `rows[r] == N` or `cols[c] == N` or `diagonal == N` or `antiDiagonal == N`. The win evaluation runs in \$O(1)\$ constant time.",
            keyPoints = listOf(
                "Row and column counter arrays enable O(1) win verification without scanning the N x N grid",
                "Diagonal and anti-diagonal integer accumulators track cross-board alignment in constant time",
                "Supports generalization to N x N board dimensions and M players with unique piece tokens",
                "Move validation verifies coordinate bounds and ensures the target square is currently unoccupied",
                "Strategy pattern enables pluggable AI player implementations (e.g. Minimax algorithm, Random move bot)"
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_lld_107",
            trackId = "lld_interview",
            conceptId = "lld_machine_coding_games",
            conceptName = "Real-World Machine Coding: Games & Classic Systems",
            title = "Design a Snake and Ladder Game",
            question = "How do you model a Snake and Ladder game for arbitrary board sizes and dynamic numbers of players? Detail Board generation, Dice rolling, Jump resolution, and Game Loop.",
            shortAnswer = "1) Entities: `Board` (size \$N\$, holds map of `Jumper`s: Snake or Ladder), `Player` (id, currentPosition), `Dice` (handles rolling \$1 \\dots 6\$), `Game` (coordinates game loop). 2) Jumper Representation: `Jumper` (or abstract class with `Snake` and `Ladder` subclasses) has `startPosition` and `endPosition`. For Snake: `start > end`; for Ladder: `start < end`. The board stores `Map<Integer, Integer> jumpers`. 3) Turn Execution: Player rolls dice: `newPos = player.pos + roll`. If `newPos > board.size`, move is forfeited. If `jumpers.containsKey(newPos)`, `newPos = jumpers.get(newPos)`. Player position updates. 4) Player Turn Queue: Uses a `Queue<Player>` (FIFO). Player takes turn and re-enqueues at the back. If player reaches exactly `board.size`, they win.",
            keyPoints = listOf(
                "Unified Jumper abstraction models both Snakes (start > end) and Ladders (start < end) cleanly",
                "Map<Integer, Integer> on the Board provides O(1) lookup to resolve snake bites and ladder climbs",
                "Queue<Player> manages round-robin turn progression among players in circular FIFO order",
                "Movement validation handles exact-landing winning rules (cannot overshoot the final winning square)",
                "Pluggable Dice strategy supports single die, multiple dice, or custom biased dice for testing"
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_lld_108",
            trackId = "lld_interview",
            conceptId = "lld_machine_coding_games",
            conceptName = "Real-World Machine Coding: Games & Classic Systems",
            title = "Design an Automated Teller Machine (ATM) System",
            question = "How do you design an ATM system using the State pattern and Chain of Responsibility? Detail the cash dispenser denominations algorithm and card transaction state machine.",
            shortAnswer = "1) State Pattern (ATM Lifecycle): `ATM` (Context) transitions through: `IdleState`, `CardInsertedState`, `PinAuthenticatedState`, `DispensingState`. Each state validates inputs (e.g. `ejectCard()` only allowed before dispensing starts; PIN verification allows max 3 attempts). 2) Chain of Responsibility (Cash Dispenser): The ATM holds cash in discrete bill denominations (\$100, \$50, \$20, \$10). Cash dispensing uses Chain of Responsibility: `Dollar100Dispenser -> Dollar50Dispenser -> Dollar20Dispenser -> Dollar10Dispenser`. When \$380 is requested: \$100 dispenser dispenses 3 bills (\$300) and passes remainder (\$80) to \$50 dispenser (dispenses 1 bill, passes \$30), and so on. If remainder cannot be fulfilled with available cash, the transaction aborts atomically.",
            keyPoints = listOf(
                "State pattern encapsulates ATM hardware lifecycle (Idle, Card Inserted, PIN Validated, Dispensing)",
                "Chain of Responsibility coordinates cash dispensing across physical currency bill denominations (\$100, \$50, \$20)",
                "Dispenser chain verifies physical note inventory before committing transactions to prevent partial dispensing failures",
                "Security constraints: 3-attempt PIN retry limit triggers card seizure transition to protect account security",
                "Two-phase hardware transaction: bank account debits only after physical cash shutter successfully opens"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_lld_109",
            trackId = "lld_interview",
            conceptId = "lld_machine_coding_games",
            conceptName = "Real-World Machine Coding: Games & Classic Systems",
            title = "Design a Library Management System",
            question = "Design a Library Management System managing book cataloging, member borrowing limits, reservation queues, and automated overdue fine calculations.",
            shortAnswer = "1) Book vs BookItem: Core distinction: `Book` represents the intellectual title metadata (ISBN, Title, Authors, Subject). `BookItem` represents a physical, individual copy with a unique `barcode` and `status` (AVAILABLE, RESERVED, LOANED, LOST). 2) Member & Lending Rules: `Member` has borrowing limits (e.g. max 5 book items for max 14 days). 3) Borrowing Service: When a member borrows a `BookItem`: verifies member active status, active loan count < 5, and creates a `BookLending` record with `dueDate`. 4) Overdue Fine Strategy: `FineStrategy` computes fines on return based on days overdue and member type (student vs faculty). 5) Reservation Queue: If all copies of a `Book` are loaned, members join a FIFO `ReservationQueue` for that ISBN.",
            keyPoints = listOf(
                "Separates abstract intellectual Book title metadata from physical, barcoded BookItem inventory copies",
                "BookItem status state machine tracks availability: AVAILABLE, LOANED, RESERVED, LOST",
                "Reservation queue manages FIFO waitlists per book title when all physical copies are checked out",
                "Strategy pattern encapsulates overdue fine calculation algorithms based on days late and membership tier",
                "Search catalog supports multi-attribute querying (by title, author, subject, or ISBN) using inverted index maps"
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_lld_110",
            trackId = "lld_interview",
            conceptId = "lld_machine_coding_games",
            conceptName = "Real-World Machine Coding: Games & Classic Systems",
            title = "Design an Online Blackjack Card Game",
            question = "How do you model an Object-Oriented Blackjack game? Detail Hand calculation where Aces can count as 1 or 11, and Dealer automated hit/stand rules.",
            shortAnswer = "1) Model: `Card` (Suit, Rank), `Deck` (52 cards, shuffled), `Hand` (list of cards), `BlackjackPlayer`, `BlackjackDealer`. 2) Dynamic Hand Scoring (Aces): A hand calculates score: sum all non-ace cards. Count Aces initially as 11. If `score > 21` (bust), iteratively downgrade Aces from 11 to 1 until `score <= 21` or all Aces count as 1. Hand returns two scores or flags 'Soft' vs 'Hard' hands. 3) Dealer Rules: Encapsulates standard casino rule: `while (dealer.getScore() < 17) { dealer.addCard(deck.deal()); }`. 4) Game Engine: Coordinates rounds: deal initial 2 cards, check natural Blackjack (21), player decision loop (Hit, Stand, Double Down, Split), dealer reveal, and win/loss resolution.",
            keyPoints = listOf(
                "Card and Deck entities model standard playing cards, with Deck supporting shuffling and dealing mechanics",
                "Hand scoring algorithm dynamically adjusts Ace values (1 or 11) to maximize score without busting over 21",
                "Dealer logic strictly encapsulates casino rules (dealer must hit until reaching soft/hard 17 or higher)",
                "State machine tracks player round options: Hit, Stand, Double Down, and Split based on initial card pairs",
                "Payout calculation compares final non-busted player scores against the dealer score to settle bets"
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_lld_111",
            trackId = "lld_interview",
            conceptId = "lld_machine_coding_games",
            conceptName = "Real-World Machine Coding: Games & Classic Systems",
            title = "Design a Smart Traffic Light Intersection Controller",
            question = "Design an automated traffic light controller for a 4-way intersection. How do you implement the State pattern for signal transitions and handle emergency vehicle sensor overrides?",
            shortAnswer = "1) Model: `TrafficSignal` (LightColor: RED, YELLOW, GREEN), `Direction` (NORTH_SOUTH, EAST_WEST), `IntersectionController`. 2) State Pattern: States represent active traffic phases: `NorthSouthGreenState`, `NorthSouthYellowState`, `EastWestGreenState`, `EastWestYellowState`. Safety Invariant: When NS is GREEN or YELLOW, EW MUST be RED, and vice-versa, making orthogonal collisions physically impossible. 3) Emergency Vehicle Override (Sensor Integration): Emergency vehicles broadcast an optical/radio strobe. The controller registers an `EmergencySensorListener`. When an emergency vehicle approaches from NORTH: The controller immediately interrupts standard timers, transitions NS to GREEN, sets EW to RED, and locks the state until the vehicle passes.",
            keyPoints = listOf(
                "State pattern encapsulates intersection traffic phases (NS Green, NS Yellow, EW Green, EW Yellow)",
                "Safety invariant: one directional corridor is strictly locked in RED whenever the cross-street is GREEN or YELLOW",
                "Timer-based state transitions advance light cycles based on configurable morning/evening peak durations",
                "Observer pattern hooks road inductive loop sensors and pedestrian crossing buttons to adapt cycle durations",
                "Emergency vehicle pre-emption interrupts standard cycles to force an immediate green corridor for first responders"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_lld_112",
            trackId = "lld_interview",
            conceptId = "lld_machine_coding_games",
            conceptName = "Real-World Machine Coding: Games & Classic Systems",
            title = "Design a Coffee Vending Machine / Customizer using the Decorator Pattern",
            question = "How do you model a coffee machine beverage customizer using the Decorator pattern? How do you calculate cost and prepare recipes with condiments (Milk, Sugar, Whip)?",
            shortAnswer = "1) Decorator Pattern Architecture: (a) Base Component: `public interface Beverage { String getDescription(); double getCost(); List<Ingredient> getIngredients(); }`. (b) Concrete Components: `Espresso`, `DarkRoast`, `HouseBlend`. (c) Abstract Decorator: `abstract class CondimentDecorator implements Beverage { protected final Beverage beverage; }`. (d) Concrete Decorators: `Milk`, `Mocha`, `Soy`, `Whip`. Each decorator adds to the description (`beverage.getDescription() + \", Milk\"`) and sums the cost (`beverage.getCost() + 0.50`). 2) Coffee Machine: Dispenses customized beverage: `Beverage myDrink = new Whip(new Mocha(new Espresso()));`. The machine checks inventory for all required ingredients and dispenses.",
            keyPoints = listOf(
                "Decorator pattern allows arbitrary condiment combinations to wrap base beverages dynamically at runtime",
                "Eliminates combinatorial subclass explosion (DarkRoastWithMilkAndWhip, DarkRoastWithMocha)",
                "Cost calculation recurses through wrapped decorators, accumulating additive ingredient pricing",
                "Inventory manager inspects the aggregated ingredient list of the customized drink before brewing",
                "Drink preparation engine executes sequential physical dispenser steps based on the decorated recipe"
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_lld_113",
            trackId = "lld_interview",
            conceptId = "lld_machine_coding_games",
            conceptName = "Real-World Machine Coding: Games & Classic Systems",
            title = "Design a Bowling Alley Scoring System",
            question = "How do you design an automated scoring engine for a 10-frame bowling game? Detail the calculation of Strikes (bonus of next 2 rolls), Spares (bonus of next 1 roll), and 10th frame bonus rolls.",
            shortAnswer = "1) Model: `BowlingGame`, `Frame`, `Roll`. A game consists of 10 `Frame`s. Frames 1-9 have max 2 rolls. Frame 10 can have up to 3 rolls if a strike or spare occurs. 2) Scoring Algorithm: Score calculation cannot always be computed immediately because strikes and spares depend on FUTURE rolls: (a) Strike: 10 + next 2 rolls. (b) Spare: 10 + next 1 roll. (c) Open Frame: sum of pins in the 2 rolls. 3) Implementation: A `Game` records all rolls in a flat `int[] rolls` array. `score()` iterates through 10 frames tracking a `rollIndex`: `if (isStrike(rollIndex)) { score += 10 + rolls[rollIndex + 1] + rolls[rollIndex + 2]; rollIndex += 1; } else if (isSpare(rollIndex)) { score += 10 + rolls[rollIndex + 2]; rollIndex += 2; } else { score += rolls[rollIndex] + rolls[rollIndex + 1]; rollIndex += 2; }`.",
            keyPoints = listOf(
                "Flat roll array representation simplifies forward-looking bonus roll lookups for strikes and spares",
                "Strike calculation adds 10 pins plus the score of the subsequent 2 rolls",
                "Spare calculation adds 10 pins plus the score of the immediately following roll",
                "10th frame special handling permits a 3rd bonus roll if the player scores a strike or spare",
                "Clean separation between roll event capture (real-time bowling pin sensors) and cumulative scorecard evaluation"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_lld_114",
            trackId = "lld_interview",
            conceptId = "lld_machine_coding_games",
            conceptName = "Real-World Machine Coding: Games & Classic Systems",
            title = "Design an Amazon Locker Delivery and Pickup System",
            question = "How do you architect an automated package locker hub? Detail compartment size allocation (Small, Medium, Large), secure PIN generation, package deposit, and customer pickup.",
            shortAnswer = "1) Entities: `LockerHub`, `LockerCompartment` (id, size: SMALL, MEDIUM, LARGE, state: VACANT, OCCUPIED, DEFECTIVE), `Package` (dimensions, trackingId), `LockerDelivery` (accessCode, expiryTime). 2) Compartment Allocation: Best-Fit Algorithm: When a package arrives, find the smallest vacant compartment whose dimensions exceed the package dimensions. Lock compartment and generate a cryptographically secure 6-digit PIN and QR code token with a 3-day TTL. 3) Delivery Flow: Delivery driver scans package barcode; the assigned locker door pops open via hardware relay; driver inserts package and closes door. State updates to `OCCUPIED`. 4) Customer Pickup: Customer enters PIN or scans QR code. Door pops open. Customer takes package and closes door. State transitions back to `VACANT`.",
            keyPoints = listOf(
                "Best-fit compartment allocation matches package physical dimensions to the smallest compatible vacant locker",
                "Compartment state machine tracks hardware readiness: VACANT, RESERVED, OCCUPIED, DEFECTIVE",
                "Cryptographic 6-digit PIN / QR code token generated with a 3-day pickup expiration TTL",
                "Hardware relay abstraction decouples low-level door solenoid locks from high-level software services",
                "Automatic timeout handling flags abandoned packages after 72 hours for driver return-to-warehouse pickup"
            ),
            difficulty = "Senior"
        )
    )
    private fun part7(): List<InterviewQuestion> = listOf(
        InterviewQuestion(
            id = "iq_lld_115",
            trackId = "lld_interview",
            conceptId = "lld_machine_coding_games",
            conceptName = "Real-World Machine Coding: Games & Classic Systems",
            title = "Design a Hotel Room Reservation & Keycard System",
            question = "Design a hotel room booking and digital keycard generation system. Detail room type inventory, stay date ranges, and RFID keycard validation with cryptographic expiration.",
            shortAnswer = "1) Entities: `Hotel`, `Room` (roomNumber, RoomType: STANDARD, DELUXE, SUITE, status: CLEAN, DIRTY, MAINTENANCE), `Reservation` (startDate, endDate, guest, room). 2) Date Range Availability: The reservation service verifies that for the selected `RoomType`, there is at least one room without overlapping reservations: `existing.start < requested.end && existing.end > requested.start`. 3) Digital Keycard Issuance: When guest checks in, keycard encoder generates an encrypted payload written to the RFID card or mobile Bluetooth token: `AES_Encrypt(hotelId + roomId + checkinEpoch + checkoutEpoch)`. 4) Door Lock Validation: Offline door lock hardware decrypts the RFID payload using the hotel's master key: verifies room matches and current timestamp is between check-in and checkout, unlocking the door without needing internet access.",
            keyPoints = listOf(
                "Room inventory availability checks verify non-overlapping date ranges for requested room categories",
                "Housekeeping state tracking (CLEAN, DIRTY, INSPECTION) prevents checking guests into unprepared rooms",
                "Offline digital keycard architecture encrypts room ID and valid date boundaries onto RFID / mobile tokens",
                "Door lock hardware verifies cryptographically signed keycards locally without requiring online Wi-Fi connectivity",
                "Strategy pattern accommodates variable pricing models (seasonal rates, weekend surcharges, loyalty discounts)"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_lld_116",
            trackId = "lld_interview",
            conceptId = "lld_machine_coding_games",
            conceptName = "Real-World Machine Coding: Games & Classic Systems",
            title = "Design a Car Rental Management System (Hertz / Enterprise)",
            question = "How do you model an automated Car Rental system? Detail vehicle category fleet tracking, reservation holds, vehicle return condition inspection, and dynamic damage surcharge billing.",
            shortAnswer = "1) Model: `Vehicle` (vin, make, model, category: COMPACT, SUV, LUXURY, mileage, status: AVAILABLE, RENTED, MAINTENANCE), `RentalLocation`, `Reservation`, `RentalAgreement`. 2) Reservation Flow: User reserves a vehicle category for a date window at Location A with return at Location B. Inventory availability checks confirm fleet capacity. 3) Pickup & Agreement: User presents license; system assigns a specific physical `Vehicle` matching the reserved category, records initial mileage and fuel level, and places a pre-authorization hold on credit card. 4) Return & Inspection: On return, staff records return mileage, fuel level, and logs new damage photos. Pricing engine computes base fee + excess mileage fee + refueling surcharge + damage penalties, and captures the final charge.",
            keyPoints = listOf(
                "Fleet inventory state machine tracks vehicle availability, active rentals, servicing, and inter-branch transfers",
                "Reservation binds to a vehicle category (SUV, Sedan) rather than a specific VIN until physical pickup",
                "Return condition inspection pipeline calculates incremental charges for fuel differentials and excess mileage",
                "Credit card pre-authorization hold pattern protects against vehicle damage or non-return before final settlement",
                "Drop-off branch routing handles one-way cross-city rentals, adjusting localized fleet capacity counts"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_lld_117",
            trackId = "lld_interview",
            conceptId = "lld_machine_coding_games",
            conceptName = "Real-World Machine Coding: Games & Classic Systems",
            title = "Design a Restaurant Table Reservation System (OpenTable)",
            question = "How do you design a low-level table reservation engine for a restaurant? Detail table capacity matching, dining time slot durations, and party size optimization.",
            shortAnswer = "1) Model: `Restaurant`, `Table` (tableId, capacity: 2, 4, 6, 8, status), `TimeSlot` (startTime, duration e.g. 90 minutes), `Reservation`. 2) Table Matching Strategy: When a party of 3 requests 7:00 PM: (a) Find available tables for the 7:00-8:30 PM slot. (b) Filter tables where `table.capacity >= partySize`. (c) Best-Fit optimization: Assign the smallest viable table (a 4-top table instead of an 8-top table) to maximize restaurant seating efficiency. 3) Table Combination: If no single table fits a party of 10, an optimization algorithm evaluates adjacent combinable tables (e.g. joining two 6-top tables). 4) Concurrency: Atomic reservations prevent two parties claiming the same table time slot.",
            keyPoints = listOf(
                "Best-fit table allocation assigns the smallest capable table matching party size to maximize revenue seating",
                "Reservation duration windows (e.g. 90-minute dining slots) model realistic dining table turnover cycles",
                "Table combination algorithms identify adjacent joinable tables for large group event dining",
                "Thread-safe reservation checks prevent double-booking identical table time slots across simultaneous requests",
                "Waitlist management system queues walk-in customers and sends SMS notifications when tables become available"
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_lld_118",
            trackId = "lld_interview",
            conceptId = "lld_machine_coding_games",
            conceptName = "Real-World Machine Coding: Games & Classic Systems",
            title = "Design a Digital Stopwatch & Lap Timer using State & Observer Patterns",
            question = "Design a digital stopwatch with Millisecond accuracy. How do you implement the State pattern (Stopped, Running, Paused) and Observer pattern for UI display and Lap recording?",
            shortAnswer = "1) State Pattern (Stopwatch Lifecycle): States: `StoppedState`, `RunningState`, `PausedState`. (a) `StoppedState`: `start()` records start timestamp, transitions to `Running`. `lap()` and `stop()` are disabled. (b) `RunningState`: `stop()` records elapsed time, transitions to `Stopped`. `pause()` records accumulated elapsed time, transitions to `Paused`. `lap()` captures current split time and saves a `Lap` record. (c) `PausedState`: `resume()` restarts timer, transitions to `Running`. `reset()` clears time, transitions to `Stopped`. 2) Observer Pattern: `Stopwatch` is a Subject; UI display components implement `StopwatchObserver`. A background timer thread fires updates at 60Hz, notifying observers to refresh the UI display canvas with the latest split.",
            keyPoints = listOf(
                "State pattern models stopwatch lifecycle: Stopped, Running, and Paused, enforcing valid user transitions",
                "Time calculation measures delta from System.currentTimeMillis() rather than counting ticks to prevent drift",
                "Lap recording captures immutable split timestamps and delta intervals from the preceding recorded lap",
                "Observer pattern decouples internal high-precision timing logic from decoupled UI rendering displays",
                "Thread synchronization protects elapsed time accumulators during start, pause, and lap transitions"
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_lld_119",
            trackId = "lld_interview",
            conceptId = "lld_machine_coding_games",
            conceptName = "Real-World Machine Coding: Games & Classic Systems",
            title = "Design an Online Snooker / Billiards Score Tracker",
            question = "How do you model a snooker match score tracker? Detail ball potting point values, foul penalty rules, break calculation, and maximum 147 break tracking.",
            shortAnswer = "1) Model: `Match`, `Frame`, `Player`, `Ball` (enum with point values: RED=1, YELLOW=2, GREEN=3, BROWN=4, BLUE=5, PINK=6, BLACK=7), `Break` (sequence of potted balls in a turn). 2) Ball Sequencing Rules: Player must alternately pot a Red ball (1 pt), followed by a Color ball (2-7 pts), until all 15 Reds are potted. Once Reds are cleared, the 6 Colors must be potted in strict ascending point order (Yellow through Black). 3) Break Tracking: Tracks consecutive potted balls by the active player in a single visit to the table (Maximum possible break = 147: 15 Reds + 15 Blacks + 6 Colors). 4) Foul Engine: Evaluates foul penalties (min 4 points to opponent; up to 7 points if Black is involved), resetting the break and transferring turn to opponent.",
            keyPoints = listOf(
                "Ball enum models official snooker points (Red=1, Yellow=2, Green=3, Brown=4, Blue=5, Pink=6, Black=7)",
                "State machine enforces official potting sequence: alternating Red-Color until reds clear, then ascending Colors",
                "Break accumulator tracks active player consecutive scores, detecting maximum 147 break achievements",
                "Foul calculation engine awards penalty points (4-7 pts) to opponent based on the highest-value ball involved in the foul",
                "Frame win condition evaluates remaining points on the table vs score differential to determine mathematical concessions"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_lld_120",
            trackId = "lld_interview",
            conceptId = "lld_machine_coding_games",
            conceptName = "Real-World Machine Coding: Games & Classic Systems",
            title = "Design a Distributed Airline Flight Seat Reservation Engine",
            question = "How do you design an airline flight seat booking engine? Detail aircraft cabin seat maps (First, Business, Economy), seat pitch/legroom attributes, and concurrent seat hold locks.",
            shortAnswer = "1) Entities: `Aircraft`, `FlightInstance`, `CabinClass` (FIRST, BUSINESS, PREMIUM_ECONOMY, ECONOMY), `Seat` (seatNumber e.g. '12B', cabinClass, attributes: AISLE, WINDOW, EMERGENCY_EXIT, EXTRA_LEGROOM), `SeatStatus` (AVAILABLE, HELD, CONFIRMED). 2) Seat Map Pricing: Seat price = base fare + cabin class multiplier + seat attribute premium (e.g. +\$50 for Emergency Exit extra legroom). 3) Concurrent Seat Selection: When user picks seat '14A': In-memory seat registry uses atomic check-and-set. A temporary 15-minute hold lock is assigned to `passengerId`. 4) Passenger Manifest Generation: When booking completes, updates seat to `CONFIRMED`, linking passenger passport details and generating an IATA-compliant boarding pass barcode.",
            keyPoints = listOf(
                "Cabin seat hierarchy models multi-tier classes (First, Business, Economy) with granular seat attributes (Aisle, Window, Exit)",
                "Dynamic seat pricing combines base tariff class with add-on premiums for extra legroom and priority zones",
                "Atomic seat hold reservation with 15-minute expiration lock prevents concurrent seat collisions during checkout",
                "Emergency exit row safety validation verifies passenger physical eligibility before permitting seat selection",
                "Passenger manifest generation compiles confirmed travelers with passport and ticketing records for airline operations"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_lld_121",
            trackId = "lld_interview",
            conceptId = "lld_machine_coding_realworld",
            conceptName = "Real-World Machine Coding: Platforms, Apps & Tools",
            title = "In-Memory File System (mkdir, ls, cat, touch)",
            question = "Design an in-memory hierarchical file system supporting mkdir, ls, addContentToFile (touch/cat), and readContentFromFile.",
            shortAnswer = "Model using Composite pattern: abstract Node (name, parent, creationTime, isDirectory) with DirectoryNode containing Map<String, Node> children and FileNode containing StringBuilder/byte[] content. Path navigation splits path by '/' resolving nodes sequentially with edge cases for root '/' and relative paths.",
            keyPoints = listOf(
                "Composite pattern with abstract Node, DirectoryNode (holding map of child nodes), and FileNode (holding string content)",
                "Path tokenizer resolving absolute paths by splitting on '/' and traversing step-by-step from root",
                "ls operation returns sorted names of immediate children for directories or file name itself if pointing to a file",
                "mkdir -p semantics creates all non-existent ancestor directories recursively along target path",
                "Thread safety via ReadWriteLock on directory nodes to allow concurrent reads while serializing writes"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_lld_122",
            trackId = "lld_interview",
            conceptId = "lld_machine_coding_realworld",
            conceptName = "Real-World Machine Coding: Platforms, Apps & Tools",
            title = "Splitwise / Expense Sharing System",
            question = "Design an expense sharing application like Splitwise supporting equal, exact, and percentage splits, balance sheets, and debt simplification.",
            shortAnswer = "Define User, Group, Expense, and Split hierarchy (EqualSplit, ExactSplit, PercentSplit) validated by ExpenseValidator. Maintain UserBalanceSheet mapping borrower to net debt. For debt simplification, compute net balance per user and resolve using Greedy two-pointer approach (max creditor pairs with max debtor) or min-flow graph algorithm.",
            keyPoints = listOf(
                "Split hierarchy (EqualSplit, ExactSplit, PercentSplit) inheriting from abstract Split with validation strategy",
                "ExpenseFactory decoupling creation and calculation of splits based on ExpenseType enum",
                "UserBalanceSheet maintaining directed net owes/owes-by amounts per user pair",
                "Debt simplification algorithm: compute net balances per user, separating creditors and debtors, matching greedily in O(N log N)",
                "Auditable ledger: immutable Expense entries with rollback or settlement transaction records"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_lld_123",
            trackId = "lld_interview",
            conceptId = "lld_machine_coding_realworld",
            conceptName = "Real-World Machine Coding: Platforms, Apps & Tools",
            title = "Low-Level Rate Limiter Library",
            question = "Design an extensible in-memory Rate Limiter library supporting multiple algorithms (Token Bucket, Leaky Bucket, Sliding Window Counter) configurable per user or API key.",
            shortAnswer = "Define RateLimiter interface with allowRequest(key, permits). Implement Strategy pattern for algorithms: TokenBucket (capacity, refillRate, atomic tokens using lastRefillTimestamp CAS), SlidingWindowCounter (circular array or deque of sub-minute window buckets). Encapsulate behind RateLimiterManager using ConcurrentHashMap.",
            keyPoints = listOf(
                "RateLimiter interface with boolean isAllowed(String clientId) and configurable permit counts",
                "Token Bucket implementation using lazy refill math (currentTime - lastRefillTime) * rate to eliminate background refill threads",
                "Sliding Window Log / Counter implementation using concurrent deque or bucketed ring counter for sub-second precision",
                "Strategy pattern allowing runtime swappable algorithms (TokenBucket, SlidingWindow, FixedWindow)",
                "Concurrency control using AtomicLong/CAS or StampedLock avoiding synchronized lock contention on hot user keys"
            ),
            difficulty = "Staff / Principal"
        ),
        InterviewQuestion(
            id = "iq_lld_124",
            trackId = "lld_interview",
            conceptId = "lld_machine_coding_realworld",
            conceptName = "Real-World Machine Coding: Platforms, Apps & Tools",
            title = "In-Memory Key-Value Store with Transactions",
            question = "Design an in-memory transactional Key-Value store supporting GET, PUT, DELETE, BEGIN, COMMIT, and ROLLBACK with nested transactions.",
            shortAnswer = "Maintain global primary Map<String, String>. Maintain Stack<Transaction> where Transaction holds local mutable map of staged changes and deletedKeys set. GET checks current transaction stack from top to bottom before global map. ROLLBACK discards top transaction frame. COMMIT merges top transaction frame into parent or global store.",
            keyPoints = listOf(
                "Global storage backing map alongside Stack<TransactionContext> for nested transactional scopes",
                "TransactionContext tracking stageWrites (Map<String, String>) and stageDeletes (Set<String>)",
                "GET resolution: search downward from top of transaction stack; if absent, read from root global map",
                "ROLLBACK pops current TransactionContext without touching global map, restoring previous state in O(1)",
                "COMMIT flattens top transaction into immediate parent transaction (or global map if at root depth)"
            ),
            difficulty = "Staff / Principal"
        ),
        InterviewQuestion(
            id = "iq_lld_125",
            trackId = "lld_interview",
            conceptId = "lld_machine_coding_realworld",
            conceptName = "Real-World Machine Coding: Platforms, Apps & Tools",
            title = "Extensible Multi-Channel Notification Service",
            question = "Design an extensible notification service supporting SMS, Email, Push notifications, retry policies, template rendering, and rate limits.",
            shortAnswer = "Use Strategy pattern for NotificationChannel (EmailSender, SmsSender, PushSender) registered in ChannelRegistry. TemplateEngine renders message content from templates. Decorator pattern applies RateLimitingDecorator and RetryDecorator with exponential backoff around channels. An asynchronous worker queue processes notification requests via thread pool.",
            keyPoints = listOf(
                "NotificationChannel interface with EmailChannel, SmsChannel, PushChannel implementations (Strategy pattern)",
                "TemplateEngine rendering Mustache/Thymeleaf templates with dynamic context variables",
                "Decorator pattern for cross-cutting concerns: LoggingDecorator, RetryDecorator, and ThrottlingDecorator",
                "User preference manager filtering channels based on opt-outs, quiet hours, and priority levels",
                "Async worker pool with dead-letter queue (DLQ) for failed deliveries after max retries"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_lld_126",
            trackId = "lld_interview",
            conceptId = "lld_machine_coding_realworld",
            conceptName = "Real-World Machine Coding: Platforms, Apps & Tools",
            title = "Logging Framework (like Log4j / Logback)",
            question = "Design a low-level logging framework supporting log levels (DEBUG, INFO, ERROR), multiple appenders (Console, File, Socket), layouts/formatters, and asynchronous non-blocking logging.",
            shortAnswer = "Define Logger hierarchy (RootLogger with child dot-separated namespaces), LogLevel enum, and LogMessage. Logger holds List<Appender> (ConsoleAppender, FileAppender). Use Chain of Responsibility / Filter pattern for level filtering. For async logging, use an LMAX Disruptor or ArrayBlockingQueue with a background consumer thread writing to appenders.",
            keyPoints = listOf(
                "Logger hierarchy using dot-separated package namespaces with level inheritance from root logger",
                "Appender interface (ConsoleAppender, RollingFileAppender) decoupling log generation from physical output",
                "Formatter/Layout interface (PatternLayout, JsonLayout) transforming LogEvent into formatted string",
                "AsyncAppender using bounded lock-free ring buffer or blocking queue decoupling caller threads from I/O latency",
                "Thread-safe Mapped Diagnostic Context (MDC) using ThreadLocal for tracing request IDs across logs"
            ),
            difficulty = "Staff / Principal"
        ),
        InterviewQuestion(
            id = "iq_lld_127",
            trackId = "lld_interview",
            conceptId = "lld_machine_coding_realworld",
            conceptName = "Real-World Machine Coding: Platforms, Apps & Tools",
            title = "Online Stock Brokerage / Order Matching Engine",
            question = "Design a low-level limit order book and matching engine for an exchange (stocks/crypto) matching BUY and SELL orders by Price-Time Priority.",
            shortAnswer = "Model Order (id, symbol, side, price, quantity, timestamp). Maintain OrderBook with TreeMap<Double, LinkedList<Order>> for bids (reverse sorted) and asks (natural sorted). When new order arrives, match against opposite book: if bid price >= lowest ask, execute trade, reduce quantities, remove filled orders. Unfilled remainder inserted into book.",
            keyPoints = listOf(
                "OrderBook maintaining two priority queues/TreeMaps: Bids (descending price) and Asks (ascending price)",
                "Price-Time priority: same price level orders stored in FIFO LinkedList for deterministic execution",
                "Matching engine loop: iteratively matching incoming buy order against ask top if buyPrice >= askPrice",
                "Partial fill mechanics: generating TradeExecution events while decrementing order remaining quantities",
                "Deterministic single-threaded or Disruptor-based event processing guaranteeing zero lock contention on hot order books"
            ),
            difficulty = "Staff / Principal"
        ),
        InterviewQuestion(
            id = "iq_lld_128",
            trackId = "lld_interview",
            conceptId = "lld_machine_coding_realworld",
            conceptName = "Real-World Machine Coding: Platforms, Apps & Tools",
            title = "Trello / Kanban Task Management System",
            question = "Design a Trello-like Kanban system supporting Boards, Lists, Cards, activity logs, labels, and card movement with re-ordering.",
            shortAnswer = "Model Board containing List<TaskList>, TaskList containing List<Card>. For card ordering, use fractional indexing (rank numbers between adjacent items e.g., Lexorank) to allow drag-and-drop reordering in O(1) without re-indexing the entire list. Observer pattern triggers ActivityLog entries whenever cards are moved, assigned, or updated.",
            keyPoints = listOf(
                "Domain model: Board, TaskList, Card, ChecklistItem, User, ActivityAuditLog",
                "Fractional indexing / Lexorank ordering: calculating floating/lexical position between adjacent cards avoiding bulk updates",
                "Observer pattern publishing CardMovedEvent, CardAssignedEvent to notify members and record activity feeds",
                "Composite/Command pattern for batch card operations, archival, and undo capabilities",
                "Fine-grained permissions: BoardRole (ADMIN, MEMBER, VIEWER) enforced before mutating lists or cards"
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_lld_129",
            trackId = "lld_interview",
            conceptId = "lld_machine_coding_realworld",
            conceptName = "Real-World Machine Coding: Platforms, Apps & Tools",
            title = "Carpool / Ride-Sharing Dispatch Engine",
            question = "Design an in-memory ride-sharing dispatch system like Uber/Lyft matching rider requests to nearby drivers based on distance, rating, and vehicle type.",
            shortAnswer = "Model Rider, Driver, Location (lat, long), Ride (pickup, dropoff, status). DriverManager tracks driver states (AVAILABLE, ON_TRIP, OFFLINE). Spatial index (QuadTree, Geohash, or R-Tree) queries drivers within radius R. Strategy pattern defines MatchingStrategy (ShortestDistanceStrategy, HighestRatedStrategy) and PricingStrategy (SurgePricingStrategy).",
            keyPoints = listOf(
                "Spatial indexing: Geohash or QuadTree grid mapping geographic coordinates for fast nearest-neighbor lookups",
                "State pattern managing Ride lifecycle: REQUESTED, DRIVER_ASSIGNED, IN_PROGRESS, COMPLETED, CANCELLED",
                "Strategy pattern for DriverMatching (NearestFirst, TopRatedFirst) and Pricing (NormalPricing, SurgePricing)",
                "Optimistic concurrency control with driver assignment to prevent two riders claiming the same driver simultaneously",
                "Observer pattern notifying rider and driver of real-time status transitions and ETA updates"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_lld_130",
            trackId = "lld_interview",
            conceptId = "lld_machine_coding_realworld",
            conceptName = "Real-World Machine Coding: Platforms, Apps & Tools",
            title = "In-Memory Pub/Sub Message Broker (Kafka/RabbitMQ lite)",
            question = "Design an in-memory Pub/Sub message broker supporting Topics, Partitions, Consumer Groups, and offset tracking.",
            shortAnswer = "Broker contains ConcurrentMap<String, Topic>. Topic contains List<Partition>. Partition stores List<Message> (append-only array). ConsumerGroup contains Map<String, Integer> tracking committed offsets per partition. Assign partitions to group consumers evenly. Pull-based consumer model: consumer requests messages starting from its current committed offset.",
            keyPoints = listOf(
                "Topic composed of multiple Partitions storing append-only sequential Message logs with unique incremental offsets",
                "ConsumerGroup tracking current committed offsets per partition independently of other groups",
                "Rebalance protocol: assigning partitions deterministically among active consumers in a consumer group",
                "Pull model vs Push model: consumers poll for batches specifying maxBatchSize and timeout",
                "Retention policy manager: background thread truncating partition messages older than TTL or exceeding max size"
            ),
            difficulty = "Staff / Principal"
        ),
        InterviewQuestion(
            id = "iq_lld_131",
            trackId = "lld_interview",
            conceptId = "lld_machine_coding_realworld",
            conceptName = "Real-World Machine Coding: Platforms, Apps & Tools",
            title = "E-Commerce Shopping Cart & Checkout Pipeline",
            question = "Design an e-commerce shopping cart and checkout pipeline supporting discounts, promo codes, tax calculation, inventory reservation, and payment processing.",
            shortAnswer = "Model Cart, CartItem, Product, Order. Use Pipeline / Chain of Responsibility pattern for CheckoutPipeline: (1) CartValidationStep, (2) CouponDiscountStep (Strategy for percentage vs flat coupons), (3) TaxCalculationStep, (4) InventoryHoldStep (with TTL), (5) PaymentGatewayStep, (6) OrderCreationStep. If any step fails, execute compensating rollback steps.",
            keyPoints = listOf(
                "Pipeline / Chain of Responsibility pattern orchestrating validation, coupons, taxes, inventory hold, and payment",
                "Strategy pattern for discount engines (CouponStrategy, PercentageDiscount, BuyXGetYFree, CategoryDiscount)",
                "Two-phase inventory reservation: temporary hold with expiry timer before hard stock deduction upon payment capture",
                "Command pattern with rollback/compensating transactions if payment fails after inventory reservation",
                "Idempotency token attached to checkout request preventing duplicate order creation on network retries"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_lld_132",
            trackId = "lld_interview",
            conceptId = "lld_machine_coding_realworld",
            conceptName = "Real-World Machine Coding: Platforms, Apps & Tools",
            title = "Food Delivery Ordering & Cart System (Swiggy / DoorDash)",
            question = "Design the food ordering and cart system for an app like Swiggy supporting restaurant-specific menus, item customization/add-ons, dynamic delivery charges, and surge fees.",
            shortAnswer = "Model Restaurant, MenuItem, AddonGroup, AddonItem. Enforce single-restaurant cart rule with ClearCartConfirmation prompt. Use Decorator pattern to calculate customized item price (BaseItem + ExtraCheese + LargeSize). Use Strategy pattern for DeliveryFeeCalculation based on distance, weather, and peak order surge.",
            keyPoints = listOf(
                "Single-restaurant constraint enforcement: clearing cart or rejecting items from conflicting restaurant IDs",
                "Decorator or Composite pattern for MenuItem customization (BaseFoodItem + Addons with price adjustments)",
                "Strategy pattern for dynamic delivery charge computation (DistanceStrategy, SurgeFeeStrategy, FreeDeliveryOverThreshold)",
                "State pattern modeling Order lifecycle: PLACED, RESTAURANT_ACCEPTED, PREPARING, OUT_FOR_DELIVERY, DELIVERED",
                "Locking menu prices at time of cart checkout to guard against real-time menu price modifications"
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_lld_133",
            trackId = "lld_interview",
            conceptId = "lld_machine_coding_realworld",
            conceptName = "Real-World Machine Coding: Platforms, Apps & Tools",
            title = "SaaS Subscription & Billing Engine",
            question = "Design a recurring subscription billing engine supporting tier plans (FREE, PRO, ENTERPRISE), monthly/annual billing cycles, usage-based metered billing, prorated upgrades, and payment grace periods.",
            shortAnswer = "Model Subscription (User, Plan, BillingCycle, startDate, renewalDate, status). Plan contains Price and QuotaLimits. State pattern manages SubscriptionStatus (TRIAL, ACTIVE, PAST_DUE, CANCELLED). ProrationCalculator computes credit for unused days of current plan when upgrading to higher plan mid-cycle. Scheduled billing job fires invoice generation.",
            keyPoints = listOf(
                "State pattern for Subscription lifecycle: TRIAL, ACTIVE, GRACE_PERIOD, PAST_DUE, CANCELLED, EXPIRED",
                "Proration calculation engine: computing daily fractional credits on unused days during mid-cycle plan switches",
                "Metered billing accumulator: recording consumption events against quota limits with overage rate calculation",
                "Dunning process: scheduled retry sequence and customer notification workflow upon payment failure",
                "Strategy pattern for invoicing: PrepaidFlatRate vs PostpaidMetered vs Hybrid plans"
            ),
            difficulty = "Senior"
        )
    )
    private fun part8(): List<InterviewQuestion> = listOf(
        InterviewQuestion(
            id = "iq_lld_134",
            trackId = "lld_interview",
            conceptId = "lld_machine_coding_realworld",
            conceptName = "Real-World Machine Coding: Platforms, Apps & Tools",
            title = "Configurable Cache Eviction Engine (LRU, LFU, FIFO)",
            question = "Design a generic, thread-safe in-memory cache supporting configurable eviction policies (LRU, LFU, FIFO) and TTL-based item expiration.",
            shortAnswer = "Define Cache<K, V> interface. For LRU, combine ConcurrentHashMap with doubly linked list (O(1) get and put). For LFU, maintain Map<K, Node> and TreeMap<Integer, DoublyLinkedList> of frequencies. Encapsulate eviction algorithm behind EvictionPolicy<K> interface (Strategy pattern). For TTL, use a DelayQueue or background cleaner thread.",
            keyPoints = listOf(
                "Strategy pattern decoupling cache storage from EvictionPolicy (LruEvictionPolicy, LfuEvictionPolicy, FifoEvictionPolicy)",
                "LRU implementation: HashMap + custom DoublyLinkedList moving accessed nodes to head in O(1)",
                "LFU implementation: frequency map + min-frequency pointer to achieve O(1) eviction under high loads",
                "Passive eviction (checking expiry on get) combined with active periodic cleanup of expired keys",
                "Thread safety via ReadWriteLock or segmented bucket locks minimizing lock contention across key ranges"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_lld_135",
            trackId = "lld_interview",
            conceptId = "lld_machine_coding_realworld",
            conceptName = "Real-World Machine Coding: Platforms, Apps & Tools",
            title = "Social Media Feed Generator (Fan-out on Write vs Read)",
            question = "Design a low-level social media feed engine supporting Follow/Unfollow, Post creation, and User Timeline vs Home Feed generation.",
            shortAnswer = "Model User, Post, Feed. Implement FeedStrategy: PushStrategy (fan-out on write: when user posts, append postId to all followers' feeds) and PullStrategy (fan-out on read: when user opens feed, fetch posts from all followed users and merge-sort). Use hybrid strategy: push for normal users, pull for celebrity users with millions of followers.",
            keyPoints = listOf(
                "Domain model: User, Post, FollowGraph, Feed, ActivityEvent",
                "Fan-out on write (Push model): fast O(1) reads for users by updating pre-computed in-memory inbox queues",
                "Fan-out on read (Pull model): prevents write amplification for celebrity accounts with millions of followers",
                "Hybrid routing strategy: checking author follower count threshold to route between Push and Pull mechanics",
                "Feed pagination using cursor-based timestamps (lastPostTimestamp, limit) avoiding offset pagination performance cliffs"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_lld_136",
            trackId = "lld_interview",
            conceptId = "lld_machine_coding_realworld",
            conceptName = "Real-World Machine Coding: Platforms, Apps & Tools",
            title = "Multi-Threaded Web Crawler",
            question = "Design a multi-threaded web crawler with URL deduplication, politeness policies, depth limits, and HTML parsing.",
            shortAnswer = "Model CrawlTask (url, depth). Use ConcurrentSkipListSet or BloomFilter + ConcurrentHashMap for visited URLs. PriorityBlockingQueue serves as FrontierQueue (ordered by priority or domain delay). Worker threads poll frontier, fetch HTML via HttpClient, extract links, filter via robots.txt parser, and enqueue new CrawlTasks if depth < maxDepth.",
            keyPoints = listOf(
                "Frontier Queue: thread-safe priority queue holding URLs to crawl, prioritized by importance or recency",
                "Deduplication filter: BloomFilter backed by ConcurrentHashMap to eliminate already-visited URLs in O(1)",
                "Politeness manager: per-domain delay queue honoring robots.txt crawl-delay to avoid hammering host servers",
                "Worker thread pool with bounded execution: parsing HTML, extracting links, and enforcing depth boundaries",
                "Graceful termination: tracking active thread tasks + empty queue condition using atomic counters"
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_lld_137",
            trackId = "lld_interview",
            conceptId = "lld_machine_coding_realworld",
            conceptName = "Real-World Machine Coding: Platforms, Apps & Tools",
            title = "Meeting Room Scheduler (Google Calendar Lite)",
            question = "Design a meeting room scheduler supporting room booking, recurring meetings, finding available slots for multiple participants, and conflict resolution.",
            shortAnswer = "Model MeetingRoom (capacity, facilities), Meeting (id, title, Interval, participants, room), Interval (startTime, endTime). Scheduler holds List<MeetingRoom>. Check room availability using Interval overlap check (start1 < end2 && start2 < end1). Finding free slots across multiple participants intersects free intervals using two-pointer sweep-line algorithm.",
            keyPoints = listOf(
                "Interval domain class with immutable timestamps and overlap detection method: (start < other.end && end > other.start)",
                "Room calendar storing non-overlapping interval bookings using TreeMap or Segment Tree for fast range queries",
                "Free slot finder: finding intersection of free intervals among N participants using sweep-line algorithm",
                "Optimistic locking / synchronized room booking to prevent race conditions when two users book the same slot simultaneously",
                "Recurring meeting generator: expanding recurrence rules (Daily, Weekly) into discrete meeting occurrences"
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_lld_138",
            trackId = "lld_interview",
            conceptId = "lld_machine_coding_realworld",
            conceptName = "Real-World Machine Coding: Platforms, Apps & Tools",
            title = "Payment Gateway Aggregator / Router",
            question = "Design a payment gateway routing engine like Razorpay/Stripe routing transactions across multiple third-party PSPs (Stripe, PayPal, Adyen) based on success rate and fees.",
            shortAnswer = "Define PaymentGatewayAdapter interface with processPayment(PaymentRequest). Create concrete adapters for StripeAdapter, PaypalAdapter. Use Strategy pattern for GatewayRoutingStrategy (LowestCostStrategy, HighestSuccessRateStrategy, FailoverStrategy). If primary gateway returns transient 5xx error, failover router automatically attempts secondary gateway.",
            keyPoints = listOf(
                "Adapter pattern wrapping proprietary third-party payment APIs into uniform PaymentGatewayAdapter interface",
                "Routing Strategy pattern dynamically selecting gateway based on card type, geography, transaction fee, and health metrics",
                "Circuit Breaker pattern wrapping gateway calls to fast-fail traffic away from degraded PSPs",
                "Automated fallback / retry mechanism: idempotently retrying failed transactions against secondary gateways",
                "Audit ledger recording immutable PaymentAttempt logs with third-party reference IDs and error codes"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_lld_139",
            trackId = "lld_interview",
            conceptId = "lld_machine_coding_realworld",
            conceptName = "Real-World Machine Coding: Platforms, Apps & Tools",
            title = "Dynamic Survey / Questionnaire System",
            question = "Design a survey engine supporting diverse question types (SingleChoice, MultiChoice, Text, Rating), conditional branching logic (skip to question X if answer is Y), and response evaluation.",
            shortAnswer = "Model Survey, Question (abstract), Answer, SurveyResponse. Question types inherit from Question. Conditional branching logic uses Rules Engine / Specification pattern: BranchRule evaluates previous Question answers against predicate to determine next Question ID. Composite pattern allows nesting questions into Sections/Pages.",
            keyPoints = listOf(
                "Polymorphic Question hierarchy: SingleSelectQuestion, MultiSelectQuestion, TextQuestion, RatingQuestion",
                "Conditional branching engine: Rule-based directed graph directing survey flow based on user's selected answers",
                "Composite pattern grouping Questions into SurveySections and Pages with custom validation rules",
                "Response validator validating required fields, regex constraints, and min/max selection bounds before progression",
                "SurveyResponse aggregate root encapsulating submission state (IN_PROGRESS, SUBMITTED) and immutable answers"
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_lld_140",
            trackId = "lld_interview",
            conceptId = "lld_machine_coding_realworld",
            conceptName = "Real-World Machine Coding: Platforms, Apps & Tools",
            title = "Git Diff & Code Review Engine",
            question = "Design a low-level file diffing and code review commenting system supporting line-by-line diff generation (Myers algorithm), side-by-side view, inline comments, and suggestions.",
            shortAnswer = "Model FileVersion, DiffHunk (oldStart, oldLines, newStart, newLines, lines), DiffLine (type: ADDED, DELETED, UNMODIFIED, text). Implement Myers Diff algorithm to compute shortest edit script. Model ReviewComment attached to specific DiffHunk and line number, supporting threaded replies and suggestion code blocks.",
            keyPoints = listOf(
                "Diff data model: DiffResult composed of DiffHunk segments containing ADDED, DELETED, and UNCHANGED DiffLines",
                "Myers Diff or LCS (Longest Common Subsequence) algorithm computing minimal edit distance between file versions",
                "Inline comment anchoring: attaching comments to stable line identifiers (hunk header + relative offset)",
                "Threaded discussion model: Comment containing recursive list of reply comments and resolution status",
                "Code suggestion patch applicator: validating and staging proposed inline diffs directly onto target branch"
            ),
            difficulty = "Staff / Principal"
        ),
        InterviewQuestion(
            id = "iq_lld_141",
            trackId = "lld_interview",
            conceptId = "lld_ddd_clean_arch",
            conceptName = "Domain-Driven Design, Clean Architecture & Testing",
            title = "Ubiquitous Language & Bounded Contexts",
            question = "How do Ubiquitous Language and Bounded Contexts guide object-oriented design and class modeling in complex domain applications?",
            shortAnswer = "Ubiquitous Language ensures domain terminology is used identically by domain experts and in source code (class, method, and variable names). A Bounded Context sets explicit boundaries where a specific domain model applies. The same real-world entity (e.g., 'User') is modeled differently across contexts (e.g., 'Customer' in Sales, 'Recipient' in Shipping, 'Claimant' in Support) avoiding monolithic god classes.",
            keyPoints = listOf(
                "Ubiquitous Language binds code naming directly to business domain vocabulary with zero technical translation loss",
                "Bounded Context defines linguistic and conceptual boundaries within which a domain model remains pure and consistent",
                "Separation of polysemic models: identical real-world entities are split into distinct, context-specific classes",
                "Context Mapping documents relationships between bounded contexts (Shared Kernel, Customer-Supplier, Anti-Corruption Layer)",
                "Prevents giant monolithic domain models that attempt to satisfy mutually conflicting cross-departmental requirements"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_lld_142",
            trackId = "lld_interview",
            conceptId = "lld_ddd_clean_arch",
            conceptName = "Domain-Driven Design, Clean Architecture & Testing",
            title = "Entity vs Value Object vs Aggregate",
            question = "Differentiate between an Entity, a Value Object, and an Aggregate in Domain-Driven Design with concrete code examples.",
            shortAnswer = "An Entity has a continuous identity (UUID/ID) that persists through state mutations (e.g., User, Order). A Value Object is immutable, has no conceptual identity, and equality is defined strictly by all its attributes (e.g., Money(amount, currency), Address). An Aggregate is a cluster of associated Entities and Value Objects treated as a single cohesive unit for data changes with one designated Aggregate Root.",
            keyPoints = listOf(
                "Entity: defined by unique identity (UUID) surviving state modifications; equality checks ID only (equals/hashCode)",
                "Value Object: immutable object defined entirely by its attribute values with structural equality and zero lifecycle tracking",
                "Aggregate: cluster of domain objects with strict consistency boundary managed exclusively through an Aggregate Root",
                "Side-effect-free methods: Value Objects produce new instances on transformations (e.g., money.add(other))",
                "Persistence independence: domain entities and value objects remain completely decoupled from database ORM annotations"
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_lld_143",
            trackId = "lld_interview",
            conceptId = "lld_ddd_clean_arch",
            conceptName = "Domain-Driven Design, Clean Architecture & Testing",
            title = "Aggregate Root as Transaction Boundary",
            question = "Why must external objects only hold references to the Aggregate Root, and how does it enforce domain invariants within a single transaction?",
            shortAnswer = "The Aggregate Root is the sole gatekeeper for all internal entities within the aggregate. External code cannot directly mutate child entities (e.g., modifying OrderLine without going through Order). By forcing all operations through the Aggregate Root, domain invariants (e.g., max order total, non-empty lines) are strictly enforced in-memory, and only one aggregate is updated per database transaction.",
            keyPoints = listOf(
                "Single point of entry: external components can only invoke public methods on the Aggregate Root",
                "Encapsulation of internal entities: child collections are returned as unmodifiable lists or value snapshots",
                "Invariant enforcement: business rules (e.g., total discount cannot exceed 30%) are guaranteed before committing mutations",
                "One aggregate per transaction rule: prevents distributed transactions and locks across multiple large database tables",
                "Reference by identity: aggregates reference other aggregates strictly by their unique ID, not direct object pointers"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_lld_144",
            trackId = "lld_interview",
            conceptId = "lld_ddd_clean_arch",
            conceptName = "Domain-Driven Design, Clean Architecture & Testing",
            title = "Domain Events & Event-Driven Decoupling",
            question = "How are Domain Events captured, recorded, and dispatched in Clean Architecture to decouple core business logic from side effects?",
            shortAnswer = "Aggregates record Domain Events (e.g., OrderPlacedEvent, PaymentFailedEvent) internally during state transitions into an in-memory event list (registerEvent). When the aggregate is saved by the Application Service / Repository, these events are dispatched synchronously or asynchronously to registered DomainEventHandlers via an EventPublisher, executing side effects without polluting aggregate logic.",
            keyPoints = listOf(
                "Domain Event represents an immutable record of something significant that has already occurred in the domain",
                "Aggregate internal accumulation: aggregate collects domain events in a private list during method execution",
                "Post-commit dispatching: Application Service publishes accumulated events only after database transaction succeeds",
                "Decoupling cross-cutting side effects: email sending, analytics, and notification logic moved completely out of the domain model",
                "Event sourcing foundation: domain events can serve as the primary source of truth for entity state history"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_lld_145",
            trackId = "lld_interview",
            conceptId = "lld_ddd_clean_arch",
            conceptName = "Domain-Driven Design, Clean Architecture & Testing",
            title = "Domain Services vs Application Services vs Infrastructure Services",
            question = "Distinguish between Domain Services, Application Services, and Infrastructure Services with specific responsibilities.",
            shortAnswer = "A Domain Service contains pure business logic involving multiple aggregates or domain calculations that do not naturally belong to a single entity (e.g., CurrencyConversionService, TransferMoneyService). An Application Service orchestrates use cases, manages transactions, loads aggregates, and triggers security/notifications without business rules. An Infrastructure Service implements technical mechanisms (e.g., SendGridEmailSender, S3StorageService).",
            keyPoints = listOf(
                "Domain Service: pure, stateless business logic spanning multiple aggregates with zero framework or I/O dependencies",
                "Application Service: use case orchestrator loading domain aggregates, managing transactions, and calling domain services",
                "Infrastructure Service: concrete adapters implementing ports for database access, external HTTP APIs, and messaging systems",
                "Dependency direction: Application Services depend on Domain Services; Infrastructure implements domain/application interfaces",
                "Anti-pattern avoidance: keeping Application Services thin by rejecting business validation logic into Domain models or Domain Services"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_lld_146",
            trackId = "lld_interview",
            conceptId = "lld_ddd_clean_arch",
            conceptName = "Domain-Driven Design, Clean Architecture & Testing",
            title = "Repository Pattern vs Data Access Object (DAO)",
            question = "How does the DDD Repository pattern differ fundamentally from the traditional Data Access Object (DAO) pattern?",
            shortAnswer = "A DAO is a data-centric abstraction closely mirroring database tables and CRUD operations (findBySql, insert, update). A DDD Repository is a domain-centric abstraction providing an in-memory collection illusion for entire Aggregates (add, remove, findById). A Repository operates strictly at the Aggregate Root level, returning rich domain models rather than database rows or DTOs.",
            keyPoints = listOf(
                "Collection illusion: Repository mimics an in-memory Set<AggregateRoot> (e.g., repository.add(order), repository.getById(id))",
                "Granularity: Repositories exist only for Aggregate Roots; DAOs exist per individual database table",
                "Domain model purity: Repository interfaces reside in the domain layer, keeping core logic agnostic of SQL/NoSQL frameworks",
                "DAO focus: DAO focuses on database CRUD queries, joins, and row-to-DTO mapping without aggregate invariant concerns",
                "Implementation placement: concrete Repository implementations reside in the infrastructure layer using DAOs or ORMs internally"
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_lld_147",
            trackId = "lld_interview",
            conceptId = "lld_ddd_clean_arch",
            conceptName = "Domain-Driven Design, Clean Architecture & Testing",
            title = "Hexagonal Architecture (Ports and Adapters)",
            question = "Explain the Ports and Adapters (Hexagonal Architecture) pattern. How does it isolate core business logic from frameworks, UI, and databases?",
            shortAnswer = "Hexagonal Architecture places the domain logic at the center inside an application hexagon. Core logic defines 'Ports' (interfaces): Inbound/Driving Ports (Use Cases invoked by UI/REST controllers) and Outbound/Driven Ports (Repository/Notification interfaces implemented by DB/External services). 'Adapters' surround the hexagon, translating external protocols to and from domain ports.",
            keyPoints = listOf(
                "Central Core: Domain entities and use cases contain zero references to external frameworks, libraries, or database drivers",
                "Driving / Inbound Ports: interfaces defining use case operations invoked by primary adapters (REST, CLI, GraphQL)",
                "Driven / Outbound Ports: interfaces defined by the core for secondary adapters (PostgreSQL, Kafka, Stripe) to implement",
                "Dependency Inversion: all dependencies point inward toward the core domain logic (The Dependency Rule)",
                "Testability benefit: enables testing core business logic at lightning speed using in-memory mock adapters without spinning up databases"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_lld_148",
            trackId = "lld_interview",
            conceptId = "lld_ddd_clean_arch",
            conceptName = "Domain-Driven Design, Clean Architecture & Testing",
            title = "The Clean Architecture Dependency Rule",
            question = "Explain Uncle Bob's Clean Architecture Dependency Rule and the concentric layers (Entities, Use Cases, Interface Adapters, Frameworks).",
            shortAnswer = "The Dependency Rule states that source code dependencies can only point inward toward higher-level policies. The layers from inside out are: (1) Enterprise Business Rules (Entities), (2) Application Business Rules (Use Cases), (3) Interface Adapters (Controllers, Presenters, Gateways), and (4) Frameworks & Drivers (Web, DB, UI). Nothing in an inner circle can know anything about an outer circle.",
            keyPoints = listOf(
                "The Dependency Rule: inner layers know nothing about outer layers; all arrows point strictly inward",
                "Entities: encapsulate enterprise-wide critical business rules and data structures, completely decoupled from application flows",
                "Use Cases: orchestrate data flow between entities and direct them to achieve application-specific goals",
                "Interface Adapters: convert data from format convenient for use cases into format convenient for external agents (DB, Web)",
                "Crossing boundaries: dynamic polymorphism / dependency inversion allows control flow to cross boundaries without violating rule"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_lld_149",
            trackId = "lld_interview",
            conceptId = "lld_ddd_clean_arch",
            conceptName = "Domain-Driven Design, Clean Architecture & Testing",
            title = "CQRS at Class & Package Level",
            question = "How do you implement Command Query Responsibility Segregation (CQRS) at the class and package level without requiring a microservice architecture?",
            shortAnswer = "Separate the codebase into Command (Write) and Query (Read) models. Commands (e.g., CreateOrderCommand, CancelOrderCommand) mutate state through CommandHandlers executing domain aggregates and transaction boundaries. Queries (e.g., GetOrderDetailQuery) bypass the domain aggregate layer entirely, reading directly from optimized read models or SQL projections via lightweight QueryHandlers.",
            keyPoints = listOf(
                "Command side: Command objects encapsulating intent, dispatched to CommandHandlers operating on rich Domain Aggregates",
                "Query side: Query objects requesting data, dispatched to QueryHandlers reading directly from read-optimized DTO projections",
                "Bypassing aggregate overhead on reads: queries do not load complex aggregate trees, improving read throughput dramatically",
                "Distinct data models: Write model optimized for domain validation; Read model optimized for UI presentation and search",
                "Package separation: explicit `commands` and `queries` packages with dedicated interfaces (CommandHandler<C, R>, QueryHandler<Q, R>)"
            ),
            difficulty = "Staff / Principal"
        ),
        InterviewQuestion(
            id = "iq_lld_150",
            trackId = "lld_interview",
            conceptId = "lld_ddd_clean_arch",
            conceptName = "Domain-Driven Design, Clean Architecture & Testing",
            title = "Event Sourcing Mechanics in Class Design",
            question = "Design an Event-Sourced Aggregate class. How does it handle command execution, event generation, and state rehydration from event streams?",
            shortAnswer = "An Event-Sourced Aggregate maintains a sequence of past domain events rather than mutable state columns. When a command arrives, the aggregate validates invariants against its current state and produces an event (e.g., OrderCreatedEvent). It applies the event immediately to mutate internal state (apply method). To reconstruct state from DB, it instantiates an empty aggregate and replays all historical events sequentially.",
            keyPoints = listOf(
                "Append-only event stream: state is derived as a pure function of historical events: State = fold(Events, InitialState)",
                "Two-step mutation: business logic validates command and raises event; `apply(Event)` method mutates fields without validation",
                "Rehydration: loading aggregate creates empty instance and invokes `apply` across chronological list of stored events",
                "Snapshots: saving periodic state snapshots (e.g., every 100 events) to accelerate rehydration of long-lived aggregates",
                "Auditability & temporal queries: enables reconstructing exact domain state at any historic point in time"
            ),
            difficulty = "Staff / Principal"
        ),
        InterviewQuestion(
            id = "iq_lld_151",
            trackId = "lld_interview",
            conceptId = "lld_ddd_clean_arch",
            conceptName = "Domain-Driven Design, Clean Architecture & Testing",
            title = "Specification Pattern for Reusable Business Invariants",
            question = "Implement the Specification Pattern in OOP to encapsulate domain validation rules, query criteria, and composite boolean logic.",
            shortAnswer = "Define Specification<T> interface with boolean isSatisfiedBy(T candidate) and combinators and(), or(), not(). Implement concrete specifications (e.g., CustomerEligibleForLoanSpec, PremiumAccountSpec). Combine specs dynamically using composite objects. Allows reusing identical business predicates across in-memory validation, repository query construction, and UI rule rendering.",
            keyPoints = listOf(
                "Specification<T> interface with `isSatisfiedBy(T entity): boolean` encapsulating domain predicate logic",
                "Composite boolean combinators: `and(Specification)`, `or(Specification)`, and `not()` returning chained specifications",
                "Single Responsibility: removes complex validation if-else ladders from entity classes into discrete testable rules",
                "Dual usage: evaluated in-memory for entity validation, or translated to SQL Criteria / JPA Predicates for database querying",
                "Declarative domain language: enables writing business policies expressively (e.g., `isEligible = isAdult.and(hasGoodCredit)`)"
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_lld_152",
            trackId = "lld_interview",
            conceptId = "lld_ddd_clean_arch",
            conceptName = "Domain-Driven Design, Clean Architecture & Testing",
            title = "Unit of Work Pattern in Clean Architecture",
            question = "How does the Unit of Work pattern maintain a list of business transactions and coordinate writing out changes to resolve concurrency and integrity?",
            shortAnswer = "Unit of Work maintains lists of objects affected by a business transaction: new (registerNew), dirty (registerDirty), and removed (registerClean/registerRemoved). When committing, the Unit of Work flushes all accumulated inserts, updates, and deletes within a single database transaction, optimizing SQL batching and preventing partial state commits.",
            keyPoints = listOf(
                "Change tracking: keeps track of all dirty, new, and deleted domain entities during a single business use case",
                "Transaction coordination: ensures all registered aggregate mutations commit atomically within one database transaction",
                "SQL optimization: groups operations into batches (batch inserts, batch updates) reducing network database roundtrips",
                "Identity Map collaboration: ensures the same database row is loaded as a single unique object reference within the unit of work",
                "Concurrency conflict detection: checks entity version numbers at commit time to fail fast on optimistic lock conflicts"
            ),
            difficulty = "Senior"
        )
    )
    private fun part9(): List<InterviewQuestion> = listOf(
        InterviewQuestion(
            id = "iq_lld_153",
            trackId = "lld_interview",
            conceptId = "lld_ddd_clean_arch",
            conceptName = "Domain-Driven Design, Clean Architecture & Testing",
            title = "Test Doubles: Dummy vs Stub vs Spy vs Mock vs Fake",
            question = "Clarify the precise differences between Dummy, Stub, Spy, Mock, and Fake (Gerard Meszaros test double taxonomy) with testing examples.",
            shortAnswer = "Dummy: passed around but never actually used (e.g., filling parameter list). Stub: provides canned answers to calls made during test (e.g., when(repo.findById()).thenReturn(user)). Spy: wraps real object recording calls and parameters. Mock: pre-programmed with expectations of calls it should receive (interaction verification). Fake: working implementation not suitable for production (e.g., InMemoryUserRepository using HashMap).",
            keyPoints = listOf(
                "Dummy: placeholder object passed to satisfy compiler/method signature requirements but never invoked",
                "Stub: provides pre-configured hardcoded answers to queries with zero call verification",
                "Spy: acts as a spy proxying a real implementation while recording invocations and arguments for later assertion",
                "Mock: pre-programmed with expected method calls and parameters; test verifies that interactions occurred as expected",
                "Fake: fully functional, lightweight in-memory substitute for a complex infrastructure dependency (e.g., H2 DB, InMemoryQueue)"
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_lld_154",
            trackId = "lld_interview",
            conceptId = "lld_ddd_clean_arch",
            conceptName = "Domain-Driven Design, Clean Architecture & Testing",
            title = "London School (Mockist) vs Chicago School (Classicist) TDD",
            question = "Contrast the London (Mockist / Outside-In) and Chicago (Classicist / Detroit / Inside-Out) approaches to Test-Driven Development.",
            shortAnswer = "Chicago School (Inside-Out) emphasizes state verification: start with domain entities, write tests using real collaborating objects, verify return values and final state, minimizing mocks. London School (Outside-In) emphasizes behavior verification: start with entry points (Controllers/Use cases), mock collaborators using interfaces, verify interactions, discovering required collaborator contracts downwards.",
            keyPoints = listOf(
                "Chicago / Classicist: verifies state outcomes using real objects; tests are resilient to internal code refactoring",
                "London / Mockist: verifies interactions and message passing between objects using mocks at every boundary",
                "Direction of design: Chicago starts with domain entities building outward; London starts at API boundary drilling downward",
                "Refactoring coupling: London tests can be brittle when internal collaborator interactions change without altering behavior",
                "Isolation: London isolates the system under test to a single class; Chicago allows cohesive units of multiple cooperating classes"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_lld_155",
            trackId = "lld_interview",
            conceptId = "lld_ddd_clean_arch",
            conceptName = "Domain-Driven Design, Clean Architecture & Testing",
            title = "Testing Private Methods: Anti-Pattern & Refactoring",
            question = "Why is testing private methods directly considered a design code smell, and what OOP refactoring techniques should be used instead?",
            shortAnswer = "Testing private methods breaks encapsulation, couples tests to internal implementation details, and makes refactoring brittle. A private method that warrants complex unit testing is violating the Single Responsibility Principle. Refactor it by: (1) Extracting the logic into a separate collaborator class where the method becomes public, or (2) Testing it indirectly through public interface behavior.",
            keyPoints = listOf(
                "Encapsulation violation: tests should verify contract behavior through public API, not private implementation details",
                "Refactoring resistance: testing private methods binds tests to internal details, causing false test failures during refactoring",
                "SRP violation indicator: complex private methods usually represent hidden, unextracted domain responsibilities",
                "Extract Class refactoring: extract private logic into a package-private or public Strategy/Value Object class with unit tests",
                "Indirect coverage: well-designed public method unit tests naturally execute all branches of internal helper methods"
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_lld_156",
            trackId = "lld_interview",
            conceptId = "lld_ddd_clean_arch",
            conceptName = "Domain-Driven Design, Clean Architecture & Testing",
            title = "Consumer-Driven Contract Testing (Pact)",
            question = "How does Consumer-Driven Contract Testing work at the low level, and how does it prevent integration regressions without end-to-end environments?",
            shortAnswer = "The API consumer defines a contract (Pact file) specifying expected HTTP requests and mock responses. The consumer tests its own client code against a local Pact mock server. The generated Pact JSON is published to a Pact Broker. The provider service runs provider verification tests against the contract, replaying consumer requests against its real controllers to ensure schema and behavioral compliance.",
            keyPoints = listOf(
                "Consumer writes unit tests generating a Pact contract file capturing request expectations and response schemas",
                "Pact Broker acts as central repository versioning and sharing contract JSON files between teams",
                "Provider verification executes consumer expectations against real provider controllers during CI pipeline",
                "Eliminates flaky, expensive end-to-end staging environments while guaranteeing zero breaking API changes",
                "Can-I-Deploy tool: checks compatibility matrix between consumer and provider versions before production deployment"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_lld_157",
            trackId = "lld_interview",
            conceptId = "lld_ddd_clean_arch",
            conceptName = "Domain-Driven Design, Clean Architecture & Testing",
            title = "Mutation Testing & Killing Mutants (PITest)",
            question = "Explain Mutation Testing. Why is high code coverage insufficient, and how do mutation tools (like PITest) measure test suite effectiveness?",
            shortAnswer = "Code coverage only measures executed lines, not asserted assertions (a test can achieve 100% line coverage with zero asserts). Mutation testing modifies source code bytecode intentionally (mutants: flipping booleans, changing `<` to `<=`, deleting calls). The test suite is run against each mutant. If a test fails, the mutant is 'Killed'; if all tests pass, the mutant 'Survived', exposing weak test assertions.",
            keyPoints = listOf(
                "Coverage illusion: 100% line coverage does not prove tests actually verify business logic or edge case behavior",
                "Mutator operators: conditional boundary mutator (`<` to `<=`), math mutator (`+` to `-`), return value mutator (returning null/0)",
                "Killed Mutant: at least one test fails when the mutant bytecode is executed (healthy, robust test suite)",
                "Survived Mutant: all tests pass despite deliberate logic alteration, pinpointing missing test assertions or dead code",
                "Mutation Score: percentage of killed mutants over total mutants, serving as the gold standard of test suite efficacy"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_lld_158",
            trackId = "lld_interview",
            conceptId = "lld_ddd_clean_arch",
            conceptName = "Domain-Driven Design, Clean Architecture & Testing",
            title = "Architecture Unit Testing with ArchUnit",
            question = "How do you enforce Clean Architecture dependency rules, layer isolation, and naming conventions as automated unit tests using ArchUnit?",
            shortAnswer = "ArchUnit analyzes compiled Java/Kotlin bytecode via reflection and class loading. Developers write standard JUnit test cases asserting architectural rules (e.g., `classes().that().resideInAPackage('..domain..').should().onlyDependOnClassesThat().resideInAnyPackage('..domain..', 'java..')`). Running in CI, it fails the build immediately if someone introduces an illegal dependency from domain to infrastructure.",
            keyPoints = listOf(
                "Automated architecture governance: converts architectural design rules into automated, executable unit tests",
                "Layer violation prevention: ensures domain layer never imports Spring, Hibernate, or infrastructure packages",
                "Cyclic dependency detection: asserts that packages are free of cyclic package dependencies (`slices().should().beFreeOfCycles()`)",
                "Naming & annotation conventions: verifies that `@Repository` classes reside in repository packages and end with 'Repository'",
                "Zero runtime overhead: executes entirely during standard unit testing phase in under a few seconds"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_lld_159",
            trackId = "lld_interview",
            conceptId = "lld_ddd_clean_arch",
            conceptName = "Domain-Driven Design, Clean Architecture & Testing",
            title = "Anti-Corruption Layer (ACL) Pattern",
            question = "How does an Anti-Corruption Layer (ACL) protect a Clean Architecture domain model when integrating with a messy legacy system?",
            shortAnswer = "An Anti-Corruption Layer consists of Adapters, Translators/Mappers, and Façades positioned between the clean domain and an external legacy system. It translates incoming legacy data structures into pure Domain Value Objects and Entities, and translates domain commands into legacy API requests, ensuring legacy schemas, quirks, and bad naming never contaminate the clean domain model.",
            keyPoints = listOf(
                "Domain isolation: prevents legacy models, terminology, and bad data structures from leaking into the new domain",
                "Translator / Mapper: converts legacy payloads and database rows into strongly typed domain Entities and Value Objects",
                "Adapter: implements clean domain Driven Port while internally making legacy SOAP/RPC/Database calls",
                "Façade: simplifies a chatty, complex legacy interface into a clean, cohesive domain-friendly contract",
                "Facilitates Strangler Fig migration: allows rewriting subsystems cleanly while maintaining legacy interoperability"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_lld_160",
            trackId = "lld_interview",
            conceptId = "lld_ddd_clean_arch",
            conceptName = "Domain-Driven Design, Clean Architecture & Testing",
            title = "Transactional Outbox Pattern at Class Level",
            question = "Design the class-level components for the Transactional Outbox pattern to reliably publish domain events without distributed two-phase commits.",
            shortAnswer = "When mutating an Aggregate, both the entity state changes and OutboxMessage records (containing serialized event JSON, aggregateId, status: PENDING) are written to the database within the SAME local database transaction. An asynchronous OutboxPublisher background worker polls PENDING outbox messages, publishes them to Kafka/RabbitMQ, and marks them as PUBLISHED.",
            keyPoints = listOf(
                "Atomic commit: domain state mutation and outbox table record write occur inside the exact same local ACID transaction",
                "OutboxMessage model: id, aggregateType, aggregateId, eventType, payload (JSON), createdAt, status (PENDING, PUBLISHED)",
                "Eliminates dual-write failure: guarantees event publication even if the message broker is temporarily unreachable during commit",
                "OutboxPoller / CDC: poller thread with row locks (SELECT FOR UPDATE SKIP LOCKED) or Debezium CDC streaming table WAL",
                "At-least-once delivery: consumers must implement idempotent processing to handle potential redeliveries"
            ),
            difficulty = "Staff / Principal"
        ),
        InterviewQuestion(
            id = "iq_lld_161",
            trackId = "lld_interview",
            conceptId = "lld_ddd_clean_arch",
            conceptName = "Domain-Driven Design, Clean Architecture & Testing",
            title = "Idempotent Command Processing at Class Level",
            question = "How do you design an idempotent command execution pipeline at the class level to guard against duplicate network requests and message retries?",
            shortAnswer = "Every incoming Command carries a unique idempotencyKey. Use Decorator pattern: IdempotencyCommandHandlerDecorator wraps CommandHandler. It checks IdempotencyStore (Redis/DB). If key exists and status is COMPLETED, return cached response. If IN_PROGRESS, reject with 409 ConcurrentRequest. If new, save key with IN_PROGRESS, execute delegate handler, and save response atomically.",
            keyPoints = listOf(
                "IdempotencyKey: unique client-generated UUID or hash of command parameters identifying the operation uniquely",
                "IdempotencyRecord model: idempotencyKey, status (PENDING, COMPLETED, FAILED), responsePayload, createdAt, ttl",
                "Decorator / Interceptor pattern: intercepts commands before reaching domain handlers to verify idempotency state",
                "Handling concurrent duplicate requests: acquiring short-lived lock or unique constraint on key, returning 409 or waiting",
                "Atomic result caching: caching final business response alongside idempotency key to return identical response on retry"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_lld_162",
            trackId = "lld_interview",
            conceptId = "lld_ddd_clean_arch",
            conceptName = "Domain-Driven Design, Clean Architecture & Testing",
            title = "Pessimistic vs Optimistic Locking on Aggregate Roots",
            question = "When should an Aggregate Root employ Optimistic Locking versus Pessimistic Locking, and how is it implemented in class design?",
            shortAnswer = "Optimistic Locking uses a `@Version` attribute incremented on each update. If version in DB != in-memory version, OptimisticLockException is thrown. Best for read-heavy systems with low collision probability. Pessimistic Locking issues `SELECT FOR UPDATE` at the database level, holding row lock until transaction completion. Best for high-contention operations (e.g., concert ticket flash sales).",
            keyPoints = listOf(
                "Optimistic Locking: Aggregate Root holds `version: Long`; DB update includes `WHERE id = ? AND version = ?`",
                "Retry mechanism: optimistic lock failures caught by Application Service and retried with fresh aggregate re-fetch",
                "Pessimistic Write Lock: locks database row exclusively; prevents concurrent reads or writes until lock release",
                "Trade-off matrix: Optimistic offers higher throughput and zero deadlocks; Pessimistic prevents wasted work under intense write contention",
                "In-memory alternative: StampedLock or ReentrantLock per Aggregate ID for single-instance in-memory architectures"
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_lld_163",
            trackId = "lld_interview",
            conceptId = "lld_ddd_clean_arch",
            conceptName = "Domain-Driven Design, Clean Architecture & Testing",
            title = "Factory Pattern within DDD Aggregates",
            question = "When should an Aggregate Root act as a Factory to create another Aggregate or child entity, rather than calling a public constructor?",
            shortAnswer = "An Aggregate Root acts as a Factory when creating a child or related entity requires enforcing parent domain invariants or internal state. For example, `order.createInvoice()` or `customer.openAccount()`. The parent aggregate checks its current business state (e.g., customer KYC verified, order not cancelled) before instantiating the new entity, guaranteeing invalid entities cannot be born.",
            keyPoints = listOf(
                "Enforcing pre-creation invariants: parent aggregate ensures prerequisites are satisfied before spawning new domain objects",
                "Encapsulating construction complexity: hides complex child constructor parameter wiring and ID generation from callers",
                "Encourages rich domain language: methods like `order.createShipment()` read like natural domain workflows",
                "Publishing creation domain events: factory method can record associated creation events (e.g., `AccountOpenedEvent`)",
                "Constructor privacy: child constructor can be package-private to ensure creation only through the authorized Aggregate factory"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_lld_164",
            trackId = "lld_interview",
            conceptId = "lld_ddd_clean_arch",
            conceptName = "Domain-Driven Design, Clean Architecture & Testing",
            title = "Clean Architecture Packaging Strategies",
            question = "Compare Package-by-Layer versus Package-by-Feature versus Package-by-Component in the context of Clean Architecture and encapsulation.",
            shortAnswer = "Package-by-Layer groups by technical concerns (`controllers`, `services`, `repositories`), forcing all classes to be public and obscuring domain capabilities. Package-by-Feature groups by user stories. Package-by-Component (Uncle Bob / Simon Brown) groups all classes belonging to a coarse-grained component inside one package, making only the interface public and keeping implementation classes package-private.",
            keyPoints = listOf(
                "Package-by-Layer flaw: violates encapsulation by forcing all repository and service implementations to be declared public",
                "Screaming Architecture: packages should reveal domain capabilities (e.g., `orders`, `billing`) rather than framework technologies",
                "Package-by-Component: component interface is public; all domain entities, services, and repositories are package-private",
                "Compiler-enforced encapsulation: package-private visibility prevents developers from illegally coupling across module boundaries",
                "Microservices readiness: package-by-component structures can be cleanly extracted into independent microservices with minimal friction"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_lld_165",
            trackId = "lld_interview",
            conceptId = "lld_ddd_clean_arch",
            conceptName = "Domain-Driven Design, Clean Architecture & Testing",
            title = "Machine Coding Interview Execution Framework",
            question = "What is the systematic step-by-step framework to successfully execute a 45-90 minute Machine Coding / LLD interview?",
            shortAnswer = "Execute in 5 disciplined phases: (1) Requirements & Scope Clarification (10m: clarify functional/non-functional, edge cases, define APIs). (2) Class Diagram & Schema Design (10m: identify Entities, Enums, Design Patterns). (3) Core Skeleton & Interfaces (10m: write interfaces, models, contracts). (4) Business Logic & Concurrency Implementation (30m: write clean, modular, working code). (5) Driver/Demo & Unit Tests (15m: verify test cases, demonstrate extensibility).",
            keyPoints = listOf(
                "Phase 1: Clarify ambiguity, list hard assumptions, define input/output contracts and MVP scope explicitly with the interviewer",
                "Phase 2: Identify core entities, value objects, enums, and applicable GoF design patterns before typing business logic",
                "Phase 3: Write clean interfaces and domain models first; adhere to SOLID principles and package-private encapsulation",
                "Phase 4: Implement core business logic incrementally; handle concurrency (ConcurrentHashMap, locks) and edge cases",
                "Phase 5: Implement Driver class with runnable main method demonstrating all required use cases and write automated unit tests"
            ),
            difficulty = "Senior"
        )
    )
}
