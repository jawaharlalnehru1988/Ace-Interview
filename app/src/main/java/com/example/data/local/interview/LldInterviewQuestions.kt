package com.example.data.local.interview

import com.example.domain.model.InterviewQuestion

object LldInterviewQuestions {

    fun getQuestions(): List<InterviewQuestion> = listOf(
        // --- Concept 1: SOLID Principles ---
        InterviewQuestion(
            id = "iq_lld_001",
            trackId = "lld_interview",
            conceptId = "lld_solid",
            conceptName = "SOLID Principles & Clean Code",
            title = "Liskov Substitution Principle (LSP) in Practice",
            question = "Explain the Liskov Substitution Principle (LSP). What common design violation occurs with the classic Rectangle-Square problem?",
            shortAnswer = "LSP states that objects of a superclass should be replaceable with objects of its subclasses without altering the correctness or expected behavior of the program. The Rectangle-Square problem violates LSP: if Square inherits from Rectangle and overrides setWidth(w) to set both width and height to w, any client code expecting a Rectangle (where setting width does not alter height) will break invariants. In clean OOP, an entity should only inherit if it completely satisfies the behavioral contract of the parent.",
            keyPoints = listOf(
                "Subtypes must be substitutable for base types without breaking client expectations",
                "Subclasses cannot strengthen preconditions or weaken postconditions",
                "Square extending Rectangle breaks the independent dimension invariant",
                "Favor composition or separate Shape abstractions over improper inheritance",
                "Throws UnsupportedOperationException in subclass is a classic LSP violation symptom"
            ),
            difficulty = "Mid-Level"
        ),
        InterviewQuestion(
            id = "iq_lld_002",
            trackId = "lld_interview",
            conceptId = "lld_solid",
            conceptName = "SOLID Principles & Clean Code",
            title = "Dependency Inversion Principle (DIP)",
            question = "What is the Dependency Inversion Principle (DIP), and how does it differ from Dependency Injection (DI)?",
            shortAnswer = "DIP is an architectural principle stating: 1) High-level modules should not depend on low-level modules; both should depend on abstractions. 2) Abstractions should not depend on details; details should depend on abstractions. Dependency Injection (DI) is a specific design pattern and implementation technique to achieve DIP, where dependencies are passed (injected) into a class via constructor or setter rather than instantiated internally via new().",
            keyPoints = listOf(
                "High-level policy logic decoupled from low-level implementation details",
                "Both high and low levels depend on shared interfaces/abstractions",
                "DIP is the architectural goal; DI is the mechanism to supply concrete implementations",
                "Inverts traditional control: domain services own the interfaces that infrastructure implements",
                "Enables seamless unit testing via mock/stub injection"
            ),
            difficulty = "Mid-Level"
        ),

        // --- Concept 2: Creational & Structural Patterns ---
        InterviewQuestion(
            id = "iq_lld_003",
            trackId = "lld_interview",
            conceptId = "lld_patterns",
            conceptName = "Design Patterns in Production",
            title = "Thread-Safe Singleton with Double-Checked Locking",
            question = "How do you implement a thread-safe Singleton using Double-Checked Locking in Java? Why is the volatile keyword mandatory?",
            shortAnswer = "Double-Checked Locking checks if the instance is null before acquiring a synchronized lock, and checks again inside the lock before instantiating. The volatile keyword on the private static instance field is mandatory to prevent CPU instruction reordering. Without volatile, the JVM might allocate memory, assign the memory reference to the variable, and then invoke the constructor out of order. Another thread could observe a non-null instance that is partially initialized, causing erratic crashes.",
            keyPoints = listOf(
                "First check avoids synchronization overhead once instance is created",
                "Synchronized block guarantees only one thread initializes the instance",
                "Second null check inside lock ensures waiting threads do not create duplicates",
                "volatile prevents instruction reordering (prevents exposing partially initialized objects)",
                "Alternative: Bill Pugh Singleton (static inner holder class) provides lazy thread safety without locking"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_lld_004",
            trackId = "lld_interview",
            conceptId = "lld_patterns",
            conceptName = "Design Patterns in Production",
            title = "Strategy Pattern vs State Pattern",
            question = "Compare the Strategy Pattern and the State Pattern. Both use composition with interfaces; what is their fundamental difference?",
            shortAnswer = "While their UML structure is virtually identical (Context delegates to an Interface), their intent and state transitions differ. In the Strategy Pattern, the client explicitly selects or configures an algorithm/behavior (e.g. PaymentStrategy: CreditCard vs PayPal) which typically remains fixed for the task lifecycle. In the State Pattern, the Context changes its internal behavior dynamically as its internal state changes (e.g. VendingMachine state transitions from NoCoin -> HasCoin -> Dispensing), and State objects often trigger state transitions on the Context.",
            keyPoints = listOf(
                "Strategy: interchangeable algorithms chosen by client code",
                "State: internal state changes dynamically as the context executes",
                "In State pattern, concrete states often know about and trigger transitions to next states",
                "In Strategy pattern, strategies are independent and unaware of each other",
                "Both eliminate complex if-else / switch conditional branching"
            ),
            difficulty = "Senior"
        ),

        // --- Concept 3: Object-Oriented Modeling ---
        InterviewQuestion(
            id = "iq_lld_005",
            trackId = "lld_interview",
            conceptId = "lld_modeling",
            conceptName = "Object-Oriented System Modeling",
            title = "Design a Parking Lot System (LLD)",
            question = "Walk through the low-level object model for a multi-floor Parking Lot supporting different vehicle types (Motorcycle, Car, Bus) and spot allocation.",
            shortAnswer = "Entities: 1) Vehicle: abstract class with licensePlate and VehicleType (MOTORCYCLE, COMPACT, LARGE). 2) ParkingSpot: has spotId, floorNumber, ParkingSpotType, and isAvailable flag; methods: parkVehicle(v), vacate(). 3) ParkingLot: Singleton managing list of ParkingFloors. 4) ParkingStrategy interface: implements spot allocation logic (e.g. NearestToEntranceStrategy, RandomStrategy). 5) ParkingTicket: stores ticketId, entryTime, vehicle, and assigned spot. 6) PaymentService: calculates fees based on VehicleType, duration, and pricing strategy.",
            keyPoints = listOf(
                "Vehicle hierarchy with Polymorphism (Motorcycle, Car, Bus)",
                "ParkingSpot encapsulates spot size matching and availability state",
                "ParkingFloor manages collections of spots with thread-safe atomic allocation",
                "Strategy pattern decouples spot selection algorithm from parking lot management",
                "Factory pattern instantiates parking tickets and pricing rules"
            ),
            difficulty = "Senior"
        )
    )
}
