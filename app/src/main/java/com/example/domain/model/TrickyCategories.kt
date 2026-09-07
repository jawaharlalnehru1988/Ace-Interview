package com.example.domain.model

data class TrickyCategory(
    val id: String,
    val trackId: String, // "java_tricky" or "js_tricky"
    val name: String,
    val subtitle: String,
    val questionCount: Int = 0,
    val keywords: List<String> = emptyList()
)

object TrickyCategoryCatalog {

    val javaCategories = listOf(
        TrickyCategory(
            id = "java_tricky_all",
            trackId = "java_tricky",
            name = "All Java Tricky",
            subtitle = "Complete 155 JVM Drills",
            keywords = emptyList()
        ),
        TrickyCategory(
            id = "java_tricky_syntax",
            trackId = "java_tricky",
            name = "Syntax Nuances",
            subtitle = "Integer cache, literals, bitwise & casts",
            keywords = listOf(
                "syntax", "integer", "cache", "autoboxing", "string", "literal", "intern",
                "unicode", "escape", "octal", "prefix", "bitwise", "cast", "compound",
                "precision", "char", "binary", "floatingpoint", "postincrement", "operators",
                "math", "overflow", "byte", "modulo", "division"
            )
        ),
        TrickyCategory(
            id = "java_tricky_control_flow",
            trackId = "java_tricky",
            name = "Control Flow",
            subtitle = "Finally overrides, switch & loops",
            keywords = listOf(
                "controlflow", "control", "flow", "switch", "fallthrough", "finally",
                "try", "catch", "exception", "operandstack", "unboxing", "ternary",
                "shortcircuit", "loop", "conditional"
            )
        ),
        TrickyCategory(
            id = "java_tricky_collections",
            trackId = "java_tricky",
            name = "Collections & Generics",
            subtitle = "Type erasure, mutability & HashMaps",
            keywords = listOf(
                "collection", "collections", "generic", "generics", "list", "arraysaslist",
                "immutable", "immutablelist", "hashmap", "set", "map", "concurrentmodification",
                "typeerasure", "erasure", "arrays", "queue"
            )
        ),
        TrickyCategory(
            id = "java_tricky_oop",
            trackId = "java_tricky",
            name = "OOP & Inheritance",
            subtitle = "Method hiding, shadowing & init order",
            keywords = listOf(
                "oop", "overload", "overloading", "override", "staticmethodhiding",
                "fieldshadowing", "initializationorder", "inheritance", "polymorphism",
                "lambda", "effectivelyfinal", "interface", "default method", "covariant",
                "static initializer", "array covariance", "constructor", "shadow"
            )
        )
    )

    val jsCategories = listOf(
        TrickyCategory(
            id = "js_tricky_all",
            trackId = "js_tricky",
            name = "All JS Tricky",
            subtitle = "Complete 155 JS Quirks",
            keywords = emptyList()
        ),
        TrickyCategory(
            id = "js_tricky_coercion",
            trackId = "js_tricky",
            name = "Type Coercion",
            subtitle = "Loose equality, NaN & primitives",
            keywords = listOf(
                "coercion", "type", "typeof", "nan", "loose", "strict", "equality",
                "object.is", "primitive", "toprimitive", "unary", "plus", "string primitive",
                "wrapper", "falsy", "boolean", "null", "number.min_value"
            )
        ),
        TrickyCategory(
            id = "js_tricky_scoping",
            trackId = "js_tricky",
            name = "Scoping & TDZ",
            subtitle = "Temporal dead zone, closures & hoisting",
            keywords = listOf(
                "scoping", "scope", "tdz", "temporal dead zone", "hoisting", "closure",
                "let", "var", "const", "block", "for-loop", "parameter", "arguments", "lexical"
            )
        ),
        TrickyCategory(
            id = "js_tricky_prototypes",
            trackId = "js_tricky",
            name = "This & Prototypes",
            subtitle = "Object prototypes, bind & proxy traps",
            keywords = listOf(
                "this", "prototype", "object", "bind", "call", "apply", "arrow",
                "freeze", "seal", "preventextensions", "create", "class", "super",
                "proxy", "reflect", "symbol", "destructuring", "spread", "delete"
            )
        ),
        TrickyCategory(
            id = "js_tricky_event_loop",
            trackId = "js_tricky",
            name = "Event Loop",
            subtitle = "Microtasks, macrotasks & promises",
            keywords = listOf(
                "event loop", "microtask", "macrotask", "promise", "async", "await",
                "generator", "yield", "settimeout", "allsettled", "then", "queue"
            )
        )
    )

    fun findCategory(categoryId: String): TrickyCategory? {
        return (javaCategories + jsCategories).find { it.id == categoryId }
    }

    fun getCategoriesForTrack(trackId: String): List<TrickyCategory> {
        return when (trackId) {
            "java_tricky" -> javaCategories
            "js_tricky" -> jsCategories
            else -> emptyList()
        }
    }

    fun matchesCategory(category: TrickyCategory, title: String, prompt: String, explanation: String, tags: List<String>): Boolean {
        if (category.id.endsWith("_all") || category.keywords.isEmpty()) return true
        val searchBlob = (title + " " + prompt + " " + explanation + " " + tags.joinToString(" ")).lowercase()
        return category.keywords.any { kw -> searchBlob.contains(kw) }
    }
}
