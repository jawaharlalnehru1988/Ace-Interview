package com.example.data.local.interview

import com.example.domain.model.InterviewQuestion

/**
 * 165 Angular & Modern Frontend Interview Questions.
 * Split across 9 private part methods to remain well under the 64KB JVM method bytecode limit.
 */
object AngularInterviewQuestions {

    fun getQuestions(): List<InterviewQuestion> =
        part1() + part2() + part3() + part4() + part5() + part6() + part7() + part8() + part9()

    private fun part1(): List<InterviewQuestion> = listOf(
        InterviewQuestion(
            id = "iq_ng_001",
            trackId = "angular_interview",
            conceptId = "ng_core_architecture",
            conceptName = "Angular Core Architecture & Standalone Ecosystem",
            title = "Standalone Components Architecture vs NgModules",
            question = "Why did Angular introduce Standalone Components in Angular 14-17, and how do they fundamentally simplify architecture compared to legacy NgModules?",
            shortAnswer = "NgModules introduced heavy boilerplate, high cognitive load, and convoluted dependency graphs where components could not be used without being declared in a module. Standalone components (`standalone: true`, default in Angular 17+) declare their own dependencies (`imports: [CommonModule, ReactiveFormsModule, ChildComponent]`) directly in the `@Component` decorator. Benefits: 1) True component-level tree-shaking: unused directives and pipes are not bundled. 2) Direct file-level lazy loading: routes load component files directly (`loadComponent: () => import('./profile.component')`). 3) Simplified mental model: eliminates SharedModule anti-patterns. 4) Streamlined unit testing without complex module declarations.",
            keyPoints = listOf(
                "Standalone components declare dependencies directly in the `@Component` imports array",
                "Eliminates NgModules, SharedModule anti-patterns, and redundant boilerplate declarations",
                "Enables direct file-level lazy loading via `loadComponent` in the router",
                "Improves tree-shaking by allowing bundlers to eliminate unreferenced directives and pipes",
                "Simplifies unit testing by removing module orchestration from TestBed configuration"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_002",
            trackId = "angular_interview",
            conceptId = "ng_core_architecture",
            conceptName = "Angular Core Architecture & Standalone Ecosystem",
            title = "Modern Component Lifecycle Hooks: afterRender and afterNextRender",
            question = "Compare traditional lifecycle hooks (ngOnInit, ngAfterViewInit) with modern Angular 16+ hooks: `afterNextRender` and `afterRender`.",
            shortAnswer = "Traditional hooks run on both browser and server (SSR). `ngOnInit` runs after input properties are initialized. `ngAfterViewInit` runs after the component's view and child views are initialized; modifying view state inside it causes the infamous `ExpressionChangedAfterItHasBeenCheckedError`. Modern Hooks (`afterNextRender` and `afterRender`): 1) SSR-Safe: They ONLY execute in the browser after the DOM has been fully rendered, NEVER running during Server-Side Rendering. This eliminates brittle `isPlatformBrowser(platformId)` checks. 2) DOM Mutation Safety: `afterNextRender` runs once after the next change detection render cycle (ideal for third-party Chart.js/D3 chart initialization). 3) `afterRender` runs after EVERY change detection cycle across the entire application, providing read and write phases to prevent layout thrashing.",
            keyPoints = listOf(
                "ngOnInit runs after inputs resolve; ngAfterViewInit runs after DOM views initialize",
                "Modifying state in ngAfterViewInit triggers ExpressionChangedAfterItHasBeenCheckedError",
                "afterNextRender and afterRender execute strictly in browser environments, never during SSR",
                "afterNextRender runs once after initial DOM render; ideal for D3, Canvas, and DOM measurement",
                "Provides structured read/write phases to prevent browser layout recalculation thrashing"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_003",
            trackId = "angular_interview",
            conceptId = "ng_core_architecture",
            conceptName = "Angular Core Architecture & Standalone Ecosystem",
            title = "Signal Inputs (input() / input.required()) vs @Input Decorators",
            question = "How do Signal Inputs (`input()`, `input.required()`) in Angular 17.1+ improve upon the traditional `@Input()` decorator?",
            shortAnswer = "Traditional `@Input()` is a decorator on a plain property that requires `ngOnChanges` or custom getters/setters to react to changes, lacks compile-time required checks (unless configured via complex metadata), and does not participate in fine-grained reactivity. Signal Inputs: 1) True Signals: Declared as `data = input<string>()` or `id = input.required<number>()`. They return reactive `Signal<T>` getters. 2) Compile-Time Enforcement: `input.required()` produces a TypeScript compiler error if the parent template omits the binding. 3) Built-In Transforms: Supports automatic attribute parsing (`input(false, { transform: booleanAttribute })`). 4) Reactive Derivation: Can be directly consumed by `computed()` signals to derive state synchronously without `ngOnChanges`.",
            keyPoints = listOf(
                "Signal inputs return Signal<T> getters, integrating natively with reactive dependency graphs",
                "`input.required()` enforces required parent bindings at compile time without runtime checks",
                "Eliminates verbose ngOnChanges and getter/setter boilerplate for reacting to input changes",
                "Supports built-in coercion transform functions (booleanAttribute, numberAttribute)",
                "Allows computed() signals to derive values directly from inputs synchronously"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_004",
            trackId = "angular_interview",
            conceptId = "ng_core_architecture",
            conceptName = "Angular Core Architecture & Standalone Ecosystem",
            title = "The output() API and Functional Outputs vs @Output EventEmitter",
            question = "Explain the modern `output()` and `outputFromObservable()` API introduced in Angular 17.3. How does it eliminate RxJS EventEmitter overhead?",
            shortAnswer = "Legacy `@Output()` relied on `new EventEmitter<T>()`, which extended RxJS `Subject`. This forced components to pull in RxJS overhead even if they did not use reactive streams, and its internal implementation had leaky type contracts. Modern `output()` API: 1) Functional Declaration: `const selectItem = output<string>();`. 2) Direct Emission: Emits values via `this.selectItem.emit('item')`. 3) Lighter Footprint: Purely framework-native; does not instantiate an RxJS Subject, reducing bundle size. 4) Observable Interop: `outputFromObservable(this.stream\$)` automatically bridges any RxJS observable directly to a template event binding, completing the stream when the component is destroyed.",
            keyPoints = listOf(
                "Modern `output()` replaces `@Output() = new EventEmitter()` with a lightweight functional API",
                "Eliminates dependency on RxJS Subject under the hood, reducing component bundle size",
                "Provides clean syntax `myOutput = output<T>()` with identical `.emit()` ergonomics",
                "`outputFromObservable()` automatically bridges RxJS streams to template event listeners",
                "Automatically handles lifecycle teardown and subscription disposal on component destruction"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_005",
            trackId = "angular_interview",
            conceptId = "ng_core_architecture",
            conceptName = "Angular Core Architecture & Standalone Ecosystem",
            title = "Content Projection: Single-Slot, Multi-Slot, and ngProjectAs",
            question = "How does Content Projection (`<ng-content>`) work in Angular? Explain multi-slot projection with CSS selectors and the purpose of `ngProjectAs`.",
            shortAnswer = "Content projection allows parent components to insert arbitrary HTML/components into placeholder slots inside child component templates. 1) Single-Slot: Uses a plain `<ng-content></ng-content>` to project all children. 2) Multi-Slot: Uses the `select` attribute with CSS selectors: `<ng-content select=\"[card-header]\"></ng-content>`, `<ng-content select=\"[card-body]\"></ng-content>`. 3) The `ngProjectAs` Attribute: Required when content is wrapped inside a structural container (like `<ng-container *ngIf=\"show\">`). Because `ng-container` does not exist in the DOM, Angular's CSS selector matcher cannot match `[card-header]`. Adding `ngProjectAs=\"[card-header]\"` forces the compiler to treat the container as matching that projection slot.",
            keyPoints = listOf(
                "Content projection passes DOM subtrees from parent template into child placeholder slots",
                "Multi-slot projection uses `select=\"selector\"` matching attributes, elements, or CSS classes",
                "Projected content is evaluated in the parent component's context, not the child's",
                "`ngProjectAs` enables `<ng-container>` and synthetic wrappers to match multi-slot projection targets",
                "Projected nodes are initialized even if hidden; use `<ng-template>` for deferred instantiation"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_006",
            trackId = "angular_interview",
            conceptId = "ng_core_architecture",
            conceptName = "Angular Core Architecture & Standalone Ecosystem",
            title = "Dynamic Component Creation with ViewContainerRef",
            question = "How do you dynamically create and mount components at runtime in modern Angular using `ViewContainerRef.createComponent()` without ComponentFactoryResolver?",
            shortAnswer = "In Angular 13+, the legacy `ComponentFactoryResolver` was completely deprecated. Dynamic creation is now simple and direct: 1) Inject `ViewContainerRef` (or query it via `@ViewChild('container', { read: ViewContainerRef })`). 2) Call `viewContainerRef.createComponent(DynamicModalComponent)`. 3) The returned `ComponentRef<DynamicModalComponent>` provides: `componentRef.instance` (to set `@Input` properties or call methods), `componentRef.setInput('propName', value)` (for signal inputs), and `componentRef.changeDetectorRef.detectChanges()`. 4) Clean Up: Destroy the component when finished via `componentRef.destroy()` or `viewContainerRef.clear()` to prevent memory leaks.",
            keyPoints = listOf(
                "ComponentFactoryResolver is deprecated; use ViewContainerRef.createComponent directly",
                "createComponent takes the component class type directly without pre-compiling factories",
                "Returns a ComponentRef instance giving programmatic access to inputs, outputs, and instance methods",
                "`componentRef.setInput()` properly triggers input signals and change detection bindings",
                "Must explicitly call componentRef.destroy() or viewContainerRef.clear() to prevent memory leaks"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_007",
            trackId = "angular_interview",
            conceptId = "ng_core_architecture",
            conceptName = "Angular Core Architecture & Standalone Ecosystem",
            title = "Built-in Control Flow Syntax (@if, @for, @switch) vs *ngIf and *ngFor",
            question = "Explain Angular 17's built-in Control Flow syntax (`@if`, `@for`, `@switch`). Why is it faster and cleaner than legacy structural directives?",
            shortAnswer = "Built-in Control Flow replaces legacy micro-syntax directives (`*ngIf`, `*ngFor`): 1) Performance: Direct compiler transformation. Legacy `*ngFor` required importing `CommonModule`, creating synthetic `EmbeddedViewRef` wrappers, and evaluating an expensive default object identity check. Built-in `@for (item of items; track item.id)` MANDATES a `track` expression at compile-time, delivering up to 90% faster list diffing algorithms. 2) Clean Syntax: Supports native `@empty` blocks for zero-element lists, eliminating secondary `*ngIf=\"items.length === 0\"` wrappers. 3) `@switch` / `@case` / `@default` eliminates bloated `[ngSwitch]` and `*ngSwitchCase` template tags. 4) Zero Imports: Built into the compiler template grammar; requires zero module imports.",
            keyPoints = listOf(
                "Built-in control flow (@if, @for, @switch) is part of Angular's core template compiler grammar",
                "Requires zero imports (no CommonModule, no NgIf/NgFor directive dependencies)",
                "@for mandates a compile-time `track` expression, preventing catastrophic full-list DOM re-renders",
                "Includes native `@empty` block syntax to display fallback content for empty arrays",
                "Delivers significant rendering performance gains over legacy structural directive view wrappers"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_008",
            trackId = "angular_interview",
            conceptId = "ng_core_architecture",
            conceptName = "Angular Core Architecture & Standalone Ecosystem",
            title = "Deferred Loading with @defer and Trigger Strategies",
            question = "How does Angular 17+ `@defer` enable declarative lazy loading of component dependencies? Explain triggers: `on idle`, `on viewport`, `on interaction`, and `on hover`.",
            shortAnswer = "`@defer` automatically splits referenced standalone components, directives, and pipes into separate lazy-loaded JavaScript chunks, downloading and rendering them only when specified conditions are met. Auxiliary Blocks: `@placeholder` (shown before download), `@loading` (shown during fetch), and `@error` (shown on network failure). Trigger Types: 1) `on idle`: Downloads chunk when browser main thread is idle (`requestIdleCallback`). 2) `on viewport(triggerRef)`: Fetches and renders when an element scrolls into view via IntersectionObserver (ideal for below-the-fold comments/heavy charts). 3) `on interaction(buttonRef)`: Fetches when user clicks or presses a key on a trigger element. 4) `on hover(elementRef)`: Prefetches on mouseover. Supports prefetching: `@defer (on interaction; prefetch on hover)`.",
            keyPoints = listOf(
                "@defer automatically splits referenced dependencies into standalone lazy-loaded JS bundles",
                "Blocks: @placeholder displays initial layout, @loading shows skeleton, @error handles fetch failures",
                "`on viewport` uses IntersectionObserver to defer rendering until elements enter view",
                "`on idle` defers bundle loading until the browser main thread has completed initial work",
                "Supports decoupled prefetching (`prefetch on idle; on interaction`) to eliminate UI click latency"
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_ng_009",
            trackId = "angular_interview",
            conceptId = "ng_core_architecture",
            conceptName = "Angular Core Architecture & Standalone Ecosystem",
            title = "ViewChild vs ContentChild vs ViewChildren",
            question = "What are the structural differences between `@ViewChild`, `@ContentChild`, and their modern Signal counterparts (`viewChild()`, `contentChild()`)?",
            shortAnswer = "1) `@ViewChild`: Queries elements, components, or directives located WITHIN the component's own template. Available after `ngAfterViewInit`. 2) `@ContentChild`: Queries elements projected into the component from the parent via `<ng-content>`. Available earlier, after `ngAfterContentInit`. 3) Modern Signal Queries (`viewChild()`, `contentChild()` in Angular 17.2+): Instead of decorators that return undefined until lifecycle hooks fire, signal queries return a reactive `Signal<ElementRef | Component | undefined>`. Benefits: a) Type-safe and automatically updates if the queried element conditionally appears/disappears via `@if`. b) Can be consumed directly in `effect()` or `computed()` without lifecycle timing bugs. c) Required queries (`viewChild.required()`) guarantee non-null values.",
            keyPoints = listOf(
                "ViewChild queries internal template elements; ContentChild queries projected `<ng-content>` nodes",
                "Decorator queries require waiting for ngAfterViewInit / ngAfterContentInit lifecycle phases",
                "Modern `viewChild()` and `contentChild()` return reactive Signal getters",
                "Signal queries dynamically update when queried elements appear or disappear via @if",
                "`viewChild.required()` provides compile-time non-null assertions for guaranteed elements"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_010",
            trackId = "angular_interview",
            conceptId = "ng_core_architecture",
            conceptName = "Angular Core Architecture & Standalone Ecosystem",
            title = "HostBinding & HostListener vs Host Object in @Component",
            question = "Why has Angular shifted away from `@HostBinding()` and `@HostListener()` decorators toward the `host` property in `@Component` metadata?",
            shortAnswer = "Legacy `@HostBinding()` and `@HostListener()` decorators were scattered throughout the TypeScript class body, obscuring host element attributes and introducing inheritance ambiguities. Modern Best Practice: Use the `host` property inside `@Component({ host: { ... } })`: 1) Centralized Declaration: All host attributes, class bindings, style bindings, and event listeners are declared in ONE visible place: `host: { 'role': 'button', '[class.active]': 'isActive()', '(click)': 'onClick(\$event)' }`. 2) Signal Compatibility: Directly binds signal getters without extra getter decorators (`'[attr.aria-expanded]': 'isOpen()'`). 3) Superior Tooling & Linting: Easier for Angular compiler and ESLint rules to validate host bindings and prevent duplicate event listeners.",
            keyPoints = listOf(
                "HostBinding and HostListener decorators scatter host configuration throughout class methods",
                "Component `host` metadata property centralizes all host attributes, styles, and listeners",
                "Integrates cleanly with Signal getters (`'[class.open]': 'isOpen()'`)",
                "Improves template static analysis, compiler optimizations, and linting enforcement",
                "Reduces decorator boilerplate and eliminates subtle class inheritance binding conflicts"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_011",
            trackId = "angular_interview",
            conceptId = "ng_core_architecture",
            conceptName = "Angular Core Architecture & Standalone Ecosystem",
            title = "Pure vs Impure Pipes and Change Detection Cost",
            question = "How does Angular determine when to re-execute a Pipe? Contrast Pure Pipes with Impure Pipes and explain the performance hazards of impure pipes.",
            shortAnswer = "1) Pure Pipes (`pure: true`, default): Angular executes `transform()` ONLY when it detects a change in the input value. For primitives (strings, numbers), it compares by value; for objects/arrays, it compares by OBJECT REFERENCE (identity `===`). If an array element is mutated internally without creating a new array reference, a pure pipe does NOT re-execute. Extremely fast; cached results across template renders. 2) Impure Pipes (`pure: false`): Angular re-executes `transform()` on EVERY change detection cycle across the entire application (every mouse move, timer, HTTP response). Hazard: If an impure pipe performs an expensive calculation, sorting, or array filtering, it runs dozens of times per second, freezing the browser main thread. Use pure pipes with immutable data or compute signals instead.",
            keyPoints = listOf(
                "Pure pipes execute only when input primitive values or object references change (identity ===)",
                "Pure pipes cache results, skipping execution if inputs remain identical across change detection",
                "Impure pipes re-execute on every single change detection cycle regardless of input changes",
                "Impure pipes performing array filtering or complex formatting cause severe frame drops and jank",
                "Modern standard: Use pure pipes with immutable arrays, or derive state via computed() signals"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_012",
            trackId = "angular_interview",
            conceptId = "ng_core_architecture",
            conceptName = "Angular Core Architecture & Standalone Ecosystem",
            title = "Template-Driven Forms vs Reactive Forms Architecture",
            question = "Compare Template-Driven Forms (`[(ngModel)]`) with Reactive Forms (`FormGroup`, `FormControl`). What architectural criteria dictate choosing Reactive Forms?",
            shortAnswer = "1) Template-Driven Forms: Implicit, asynchronous, and template-centric. Angular builds the underlying `FormGroup` asynchronously using directives (`ngModel`, `ngForm`). Pros: Simple two-way binding for simple single-field forms. Cons: Difficult to unit test without DOM rendering; complex dynamic validation rules or conditional cross-field validations become messy template logic. 2) Reactive Forms: Explicit, synchronous, and programmatic. Form model (`FormGroup`, `FormControl`, `FormArray`) is defined explicitly in TypeScript. Pros: Synchronous access to form state and validity; native integration with RxJS streams (`valueChanges`, `statusChanges`); effortless unit testing without DOM rendering; dynamic runtime field addition/removal. Always choose Reactive Forms for enterprise applications, complex multi-step wizards, and dynamic validation requirements.",
            keyPoints = listOf(
                "Template-driven forms build form models asynchronously from template directives (ngModel)",
                "Reactive forms instantiate synchronous programmatic form hierarchies in TypeScript code",
                "Reactive forms expose valueChanges and statusChanges as first-class RxJS Observables",
                "Reactive forms can be unit tested purely in TypeScript without rendering component DOM",
                "Reactive forms excel in dynamic field addition (FormArray) and cross-field validation rules"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_013",
            trackId = "angular_interview",
            conceptId = "ng_core_architecture",
            conceptName = "Angular Core Architecture & Standalone Ecosystem",
            title = "Strictly Typed Reactive Forms in Modern Angular",
            question = "How did Angular 14+ introduce Strictly Typed Reactive Forms? How do you handle non-nullable form controls?",
            shortAnswer = "Prior to Angular 14, `FormGroup.value` returned `any`, causing runtime bugs on renamed or missing fields. Strictly Typed Forms infer or enforce strong types: 1) Type Inference: `new FormGroup({ email: new FormControl('') })` is typed as `FormGroup<{ email: FormControl<string | null> }>`. 2) Non-Nullable Controls: By default, calling `form.reset()` resets controls to `null`. To guarantee a primitive type without null, use `NonNullableFormBuilder` or `{ nonNullable: true }`: `new FormControl('default', { nonNullable: true })` creates `FormControl<string>` where reset restores the initial string value. 3) Disabling Controls: When a control is disabled, it is excluded from `form.value`, meaning fields can be `undefined`. Use `form.getRawValue()` to retrieve all fields including disabled ones with full type safety.",
            keyPoints = listOf(
                "Strictly Typed Forms enforce compile-time type validation across FormGroups and FormControls",
                "Default FormControls allow null because calling `.reset()` clears values to null",
                "`nonNullable: true` or NonNullableFormBuilder creates controls that reset to initial values",
                "`form.value` excludes disabled controls, while `form.getRawValue()` returns all fields typed",
                "Prevents silent runtime errors when refactoring backend DTO schemas and form controls"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_014",
            trackId = "angular_interview",
            conceptId = "ng_core_architecture",
            conceptName = "Angular Core Architecture & Standalone Ecosystem",
            title = "ViewEncapsulation: Emulated vs ShadowDom vs None",
            question = "Explain the 3 ViewEncapsulation modes in Angular: `Emulated`, `ShadowDom`, and `None`. How does Emulated encapsulation isolate styles under the hood?",
            shortAnswer = "1) `ViewEncapsulation.Emulated` (Default): Emulates Shadow DOM without actual browser Shadow DOM. During compilation, Angular appends a unique synthetic attribute (e.g. `_ngcontent-c42`) to all HTML elements in the component template, and rewrites the component's CSS selectors to include that attribute: `.btn` becomes `.btn[_ngcontent-c42]`. Styles apply strictly to this component, but global styles bleed in. 2) `ViewEncapsulation.ShadowDom`: Uses native browser Web Components Shadow DOM (`attachShadow({ mode: 'open' })`). Complete physical encapsulation: component styles cannot leak out, and global page styles CANNOT penetrate inside (except CSS variables). 3) `ViewEncapsulation.None`: No style scoping. Component styles are injected directly into the document `<head>` as global CSS, polluting the entire application.",
            keyPoints = listOf(
                "Emulated scopes styles by appending synthetic attributes (_ngcontent-xyz) to template elements and CSS",
                "Emulated prevents component styles from leaking out, but allows global styles to penetrate in",
                "ShadowDom uses native browser Shadow DOM boundaries, isolating styles in both directions",
                "None injects CSS directly into document head as global styles without isolation",
                "Use CSS custom properties (variables) to theme components across Shadow DOM boundaries"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_015",
            trackId = "angular_interview",
            conceptId = "ng_core_architecture",
            conceptName = "Angular Core Architecture & Standalone Ecosystem",
            title = "DOM Sanitization and DomSanitizer Security",
            question = "How does Angular prevent Cross-Site Scripting (XSS) when binding `[innerHTML]`? When and how should `DomSanitizer` be used safely?",
            shortAnswer = "By default, Angular treats all untrusted values as dangerous. When binding `[innerHTML]=\"userHtml\"`, the Angular sanitizer parses the HTML AST and strips dangerous elements (`<script>`, `<object>`, `<iframe>`) and attributes (`onclick`, `javascript:` URIs) before DOM insertion. Bypassing Sanitization: When rendering trusted rich text (e.g. trusted SVG icons or sanitised CMS markdown), inject `DomSanitizer` and call: `sanitizer.bypassSecurityTrustHtml(html)`, `bypassSecurityTrustResourceUrl(url)`, or `bypassSecurityTrustStyle(style)`. Security Warning: Never pass raw user-supplied input to `bypassSecurityTrust*` methods—doing so creates a direct XSS vulnerability. If user HTML must be displayed, sanitize it with a verified library like DOMPurify BEFORE calling `bypassSecurityTrustHtml`.",
            keyPoints = listOf(
                "Angular automatically sanitizes innerHTML, attributes, and styles against XSS injection",
                "Strips script tags, event handlers (onclick), and javascript: protocol URIs automatically",
                "DomSanitizer provides bypassSecurityTrust* methods to mark trusted values explicitly",
                "Passing unsanitized user inputs to bypass methods exposes direct XSS exploit vectors",
                "Pair user-generated HTML with client-side sanitization libraries (DOMPurify) before bypassing"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_016",
            trackId = "angular_interview",
            conceptId = "ng_core_architecture",
            conceptName = "Angular Core Architecture & Standalone Ecosystem",
            title = "DestroyRef and the takeUntilDestroyed Operator",
            question = "How does `DestroyRef` (Angular 16+) modernize lifecycle cleanup compared to `ngOnDestroy`? How does `takeUntilDestroyed()` eliminate unsubscribe boilerplate?",
            shortAnswer = "Legacy Cleanup: Required implementing `ngOnDestroy`, creating a `destroy\$ = new Subject<void>()`, calling `.pipe(takeUntil(this.destroy\$))` on every observable, and manually calling `destroy\$.next(); destroy\$.complete()`. Modern `DestroyRef`: 1) Can be injected anywhere in an injection context (services, directives, components, utility functions). 2) Register callbacks: `destroyRef.onDestroy(() => clearInterval(timerId))`. 3) `takeUntilDestroyed()`: An RxJS operator that automatically unsubscribes when the current injection context is destroyed: `api.getData().pipe(takeUntilDestroyed()).subscribe(...)`. If called within constructor/field initializer, it automatically detects the current `DestroyRef` without arguments.",
            keyPoints = listOf(
                "DestroyRef provides programmatic lifecycle destruction hooks without implementing ngOnDestroy",
                "Can be injected into standalone functions, services, and directives outside component classes",
                "`takeUntilDestroyed()` automatically unsubscribes from RxJS streams on context destruction",
                "Infers current DestroyRef automatically when used within constructor injection context",
                "Completely eliminates manual Subject-based unsubscribe boilerplate in components"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_017",
            trackId = "angular_interview",
            conceptId = "ng_core_architecture",
            conceptName = "Angular Core Architecture & Standalone Ecosystem",
            title = "ExpressionChangedAfterItHasBeenCheckedError Mechanics",
            question = "What causes `ExpressionChangedAfterItHasBeenCheckedError` in Angular development mode? Walk through the two-pass change detection cycle and proper fixes.",
            shortAnswer = "In development mode, Angular runs a two-pass change detection verification cycle: Pass 1 executes change detection, evaluates template bindings, and updates the DOM. Pass 2 immediately re-evaluates all template expressions WITHOUT updating the DOM. If any expression produces a different value than in Pass 1, Angular throws `ExpressionChangedAfterItHasBeenCheckedError`. Cause: A child component or hook (like `ngAfterViewInit`) modifies state that a parent or preceding component's template already rendered in Pass 1 (unidirectional data flow violation). Proper Fixes: 1) Refactor state flow: Move state updates into `ngOnInit` before rendering begins. 2) Use Signals: Signals update fine-grained dependencies synchronously. 3) Move to asynchronous microtask: `Promise.resolve().then(() => this.state = newValue)`. Anti-Pattern: Calling `cdr.detectChanges()` inside `ngAfterViewInit` masks design flaws.",
            keyPoints = listOf(
                "Dev mode runs a verification second pass to enforce unidirectional top-down data flow",
                "Error triggers when a template binding value changes between the first and second verification pass",
                "Common cause: Modifying parent state from child lifecycle hooks (ngAfterViewInit)",
                "Proper fix: Update state before rendering in ngOnInit or shift to reactive Signal derivations",
                "Promise.resolve().then() defers mutation to the next microtask as an intermediate workaround"
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_ng_018",
            trackId = "angular_interview",
            conceptId = "ng_core_architecture",
            conceptName = "Angular Core Architecture & Standalone Ecosystem",
            title = "Custom Structural Directives and TemplateRef / ViewContainerRef",
            question = "How do you build a custom structural directive (e.g. `*appHasRole=\"['ADMIN']\"`) using `TemplateRef` and `ViewContainerRef`?",
            shortAnswer = "Structural directives manipulate DOM layout by adding or removing host view containers. 1) Injection: Inject `TemplateRef<any>` (the blueprint of the template content inside the directive) and `ViewContainerRef` (the location in the DOM hierarchy). 2) Implementation: Listen to inputs or permissions. If authorized, instantiate the template: `this.vcr.createEmbeddedView(this.templateRef)`. If unauthorized, clear the container: `this.vcr.clear()`. 3) Tracking State: Maintain a boolean flag so `createEmbeddedView` is not called repeatedly on identical permissions. Structural asterisk syntax (`*appHasRole=\"'ADMIN'\"`) is syntactic sugar for `<ng-template [appHasRole]=\"'ADMIN'\">`.",
            keyPoints = listOf(
                "Structural directives modify DOM structure using TemplateRef and ViewContainerRef",
                "TemplateRef represents the embedded view blueprint; ViewContainerRef manages DOM attachment",
                "`viewContainerRef.createEmbeddedView(templateRef)` instantiates and renders the template",
                "`viewContainerRef.clear()` destroys and detaches the view from the DOM hierarchy",
                "The asterisk (*) syntax is syntactic sugar for wrapping elements inside `<ng-template>`"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_019",
            trackId = "angular_interview",
            conceptId = "ng_core_architecture",
            conceptName = "Angular Core Architecture & Standalone Ecosystem",
            title = "Component Inheritance vs Directive Composition API",
            question = "Why is class inheritance problematic in Angular components? How does the Directive Composition API (Angular 15+) enable scalable feature reuse?",
            shortAnswer = "Problems with Component Inheritance: Angular does NOT inherit template HTML or CSS styles—only TypeScript class logic. Inheriting components requires repeating parent constructors, leads to rigid hierarchies, and breaks single-responsibility principles. Directive Composition API (`hostDirectives` in Angular 15+): Allows components to compose multiple standalone directives onto their host element directly in the decorator: `@Component({ hostDirectives: [TooltipDirective, { directive: CdkMenu, inputs: ['cdkMenu: menu'] }] })`. Benefits: 1) Composition over inheritance: A component can adopt behavior from 5 different directives simultaneously. 2) Forwarding inputs/outputs: Explicitly maps and re-exposes directive inputs/outputs. 3) Clean separation of concerns: Keeps component logic focused.",
            keyPoints = listOf(
                "Class inheritance in Angular only inherits TypeScript methods, not templates or styles",
                "Directive Composition API (`hostDirectives`) enables true horizontal code reuse via composition",
                "Components can compose multiple standalone behavior directives onto their host element",
                "Enables aliasing and forwarding directive inputs and outputs to the component's public API",
                "Adheres to the Gang of Four principle: Favor object composition over class inheritance"
            ),
            difficulty = "Senior"
        )
    )
    private fun part2(): List<InterviewQuestion> = listOf(
        InterviewQuestion(
            id = "iq_ng_020",
            trackId = "angular_interview",
            conceptId = "ng_core_architecture",
            conceptName = "Angular Core Architecture & Standalone Ecosystem",
            title = "Angular CDK (Component Development Kit) Architecture",
            question = "What architectural role does the Angular CDK play in building enterprise design systems? Detail its Overlay, Virtual Scrolling, and A11y modules.",
            shortAnswer = "The Angular CDK (Component Development Kit) provides unstyled, accessible behavioral building blocks for complex UI widgets, allowing teams to build custom design systems without reinventing primitives: 1) `@angular/cdk/overlay`: Manages floating dynamic panels (modals, dropdowns, tooltips, toasts) outside standard DOM flow. Handles positioning strategies (connected to origin vs global viewport), backdrops, and click-outside dismissal. 2) `@angular/cdk/scrolling`: `CdkVirtualScrollViewport` renders only the 20 visible items in a 100,000-item list, maintaining constant 60 FPS scrolling and low DOM node count. 3) `@angular/cdk/a11y`: Manages keyboard focus trapping (`FocusTrap`), keyboard navigation lists (`ListKeyManager`), and screen reader live announcements (`LiveAnnouncer`).",
            keyPoints = listOf(
                "Angular CDK provides headless, unstyled architectural behavior primitives for design systems",
                "Overlay module manages floating panels, positioning strategies, backdrops, and modal stacking",
                "Virtual Scrolling renders only visible DOM viewport rows, scaling to 100,000+ items smoothly",
                "A11y module enforces accessibility: focus trapping, keyboard list managers, and screen reader announcements",
                "Decouples complex interaction behavior from visual CSS design and branding themes"
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_ng_021",
            trackId = "angular_interview",
            conceptId = "ng_signals_reactivity",
            conceptName = "Angular Signals, Reactivity & Zoneless Architecture",
            title = "Angular Signals Mental Model: Push-Pull Reactivity",
            question = "Explain the Push-Pull reactivity model of Angular Signals. How does it guarantee synchronous, glitch-free execution?",
            shortAnswer = "Traditional push-based reactive systems (RxJS, standard event emitters) push notifications through the entire graph immediately on mutation, which can lead to 'glitches' (intermediate inconsistent states where a derived value recalculates before all its inputs have updated). Angular Signals implement Push-Pull Reactivity: 1) Push Phase: When a writable signal changes, it pushes a lightweight dirty notification down its dependency graph, marking dependent computed signals as stale. NO derived calculations are performed yet! 2) Pull Phase: When a consumer (e.g. template or another signal read) requests the value, it pulls the value on demand. The computed signal checks if any upstream dependency changed; if so, it recalculates synchronously and caches the result. This guarantees that derived values are computed lazily, execute synchronously without glitches, and recalculate at most once per change.",
            keyPoints = listOf(
                "Signals implement Push-Pull reactivity combining dirty notifications with on-demand pulls",
                "Push phase marks downstream consumer nodes dirty without executing expensive computations",
                "Pull phase evaluates and recalculates derived values synchronously when read by consumers",
                "Completely eliminates reactive glitches and transient inconsistent intermediate states",
                "Guarantees that derived values are computed lazily and cached until dependencies mutate"
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_ng_022",
            trackId = "angular_interview",
            conceptId = "ng_signals_reactivity",
            conceptName = "Angular Signals, Reactivity & Zoneless Architecture",
            title = "signal(), computed(), and effect() Mechanics and Invariants",
            question = "Compare `signal()`, `computed()`, and `effect()`. What architectural invariants must be respected when using them?",
            shortAnswer = "1) `signal(initialValue)`: Creates a mutable reactive value. Updated via `.set(val)` (replacement) or `.update(fn)` (functional derivation). 2) `computed(() => fn)`: Creates a read-only signal that derives state from other signals. It is pure, memoized, lazy, and dynamically tracks dependencies based on which signals were read during execution. Invariant: `computed` must NEVER produce side effects. 3) `effect(() => fn)`: Runs an asynchronous side-effect function whenever any read signal changes. Executes after change detection and DOM updates. Invariants: By default, modifying signals inside an effect is forbidden (`allowSignalWrites: true` is an anti-pattern); effects must be declared in an injection context or explicitly passed an `Injector`.",
            keyPoints = listOf(
                "signal() defines mutable reactive state with .set() and .update() modifiers",
                "computed() defines pure, memoized, lazily evaluated derived read-only state",
                "computed() functions must remain pure and free of side effects or external mutations",
                "effect() runs side effects (logging, analytics, canvas/DOM sync) in response to signal changes",
                "Mutating signals inside effect() is restricted to prevent cascading infinite re-render loops"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_023",
            trackId = "angular_interview",
            conceptId = "ng_signals_reactivity",
            conceptName = "Angular Signals, Reactivity & Zoneless Architecture",
            title = "Dynamic Dependency Tracking in computed() Signals",
            question = "How does Angular dynamically track dependencies in `computed()` signals? What happens when conditional branching (`if/else`) changes the execution path?",
            shortAnswer = "Angular tracks dependencies dynamically at runtime during the actual execution of the `computed()` function, not statically: When `computed()` runs, it registers itself on an internal reactive context stack. As signals are read (`signalA()`), they register themselves as dependencies of the currently running computed node. Conditional Branching Example: `const result = computed(() => useA() ? signalA() : signalB())`. If `useA()` is true, Angular subscribes to `useA` and `signalA`. If `signalB` changes, `result` does NOT re-evaluate! If `useA` later becomes false, during recalculation Angular drops the dependency on `signalA` and subscribes to `signalB`. Subscriptions adjust dynamically based strictly on the branches taken.",
            keyPoints = listOf(
                "Dependencies are tracked dynamically during function execution via a reactive context stack",
                "Only signals actually read during the current execution path become reactive dependencies",
                "Conditional branches (if/else) dynamically subscribe to and drop dependencies on the fly",
                "Unread signals in unexecuted conditional branches do not trigger recomputation when updated",
                "Prevents redundant recomputations and avoids memory leaks from inactive dependency branches"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_024",
            trackId = "angular_interview",
            conceptId = "ng_signals_reactivity",
            conceptName = "Angular Signals, Reactivity & Zoneless Architecture",
            title = "Custom Equality Functions in Signals and Preventing Dirty Cascades",
            question = "How does Angular Signal equality checking work by default? How do custom equality functions (`equal`) prevent unnecessary change detection cascades?",
            shortAnswer = "By default, signals compare new and previous values using JavaScript's `Object.is()` identity comparison. If `.set(newValue)` is called with an identical primitive or identical object reference, Angular ignores the update and does NOT mark downstream consumers as dirty. Custom Equality Function: When working with objects, arrays, or domain entities where mutations produce new object references but unchanged semantic content: `const user = signal({ id: 1, name: 'Alice' }, { equal: (a, b) => a.id === b.id && a.name === b.name })`. If the API returns a fresh object reference with identical data, the custom equality function returns true. Downstream `computed()` signals and template DOM nodes are NOT marked dirty, preventing wasteful recalculations.",
            keyPoints = listOf(
                "Signals use Object.is() by default to determine if a value modification occurred",
                "Setting an identical value does not mark downstream dependent signals or templates dirty",
                "Custom equality functions (`equal: (prev, next) => boolean`) define semantic equivalence",
                "Prevents new object references with identical fields from triggering downstream change cascades",
                "Essential for optimizing complex array updates, collection diffs, and domain models"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_025",
            trackId = "angular_interview",
            conceptId = "ng_signals_reactivity",
            conceptName = "Angular Signals, Reactivity & Zoneless Architecture",
            title = "Reading Signals Without Subscribing: untracked()",
            question = "What is `untracked()`, and how do you use it inside `computed()` or `effect()` to read a signal without making it a reactive dependency?",
            shortAnswer = "When reading a signal inside `computed()` or `effect()`, Angular automatically registers that signal as a reactive dependency. Any change to that signal will re-trigger the computation. `untracked(() => signalValue())` temporarily suspends reactive dependency tracking. Scenario: You want an effect to log analytics whenever `userAction()` emits, but you also want to log the current `currentUser()`, without re-running the effect whenever `currentUser` changes: `effect(() => { const action = userAction(); const user = untracked(() => currentUser()); analytics.log(action, user); });`. The effect only runs when `userAction` changes. Reading `currentUser` inside `untracked()` fetches the latest value synchronously without creating a subscription edge.",
            keyPoints = listOf(
                "Reading signals inside computed or effect creates automatic reactive subscription dependencies",
                "`untracked(fn)` executes code while temporarily disabling dependency registration",
                "Allows reading the latest value of a signal without triggering recomputation when it mutates",
                "Prevents unwanted cyclic dependencies and infinite effect triggering loops",
                "Commonly used for logging, analytics tagging, and secondary non-reactive context lookups"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_026",
            trackId = "angular_interview",
            conceptId = "ng_signals_reactivity",
            conceptName = "Angular Signals, Reactivity & Zoneless Architecture",
            title = "Two-Way Binding with Signal model() Inputs in Angular 17.2+",
            question = "How does the modern `model()` input API in Angular 17.2+ replace the legacy `@Input()` / `@Output() ...Change` two-way binding pattern?",
            shortAnswer = "Legacy Two-Way Binding: Required declaring both an `@Input() count: number` and an `@Output() countChange = new EventEmitter<number>()` to support `[(count)]=\"total\"` ('banana-in-a-box' syntax). Modern `model()` API: Replaces both with a single functional declaration: `count = model(0)`. Features: 1) It creates a two-way signal. The parent can bind via two-way binding `[(count)]=\"parentValue\"` or standard input `[count]=\"parentValue\"`. 2) Writable Signal: Child component code can read it (`count()`) and mutate it directly (`count.set(5)` or `count.update(c => c + 1)`). 3) Automatic Output: Whenever the child mutates the model signal, Angular automatically emits the change to the parent and synchronizes the parent's state seamlessly.",
            keyPoints = listOf(
                "`model()` replaces paired `@Input()` and `@Output() Change` declarations with a single API",
                "Supports standard 'banana-in-a-box' two-way template binding syntax `[(property)]`",
                "Model inputs are writable signals; child components can directly call `.set()` and `.update()`",
                "Child signal mutations automatically emit and synchronize the value back to the parent",
                "Can be marked required via `model.required<T>()` for mandatory bidirectional properties"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_027",
            trackId = "angular_interview",
            conceptId = "ng_signals_reactivity",
            conceptName = "Angular Signals, Reactivity & Zoneless Architecture",
            title = "Signals and RxJS Interoperability: toSignal() and toObservable()",
            question = "How do `toSignal()` and `toObservable()` bridge Angular Signals with RxJS streams? What are the subscription timing and lifecycle rules?",
            shortAnswer = "1) `toSignal(observable\$, { initialValue })`: Converts an RxJS Observable into a Signal. Subscribes immediately upon invocation. Because Observables emit asynchronously while Signals must always have a synchronous value, you should provide `{ initialValue: T }` (or `requireSync: true` for BehaviorSubjects); otherwise, the signal returns `undefined` until the first emission. It automatically unsubscribes when the current `DestroyRef` injection context is destroyed. 2) `toObservable(signal)`: Converts a Signal into an RxJS Observable. Subscribes to the signal using an internal `effect()`. Crucial timing note: Because effects run asynchronously during change detection, `toObservable()` emits values asynchronously using microtasks, NOT synchronously. Rapid consecutive signal sets (`sig.set(1); sig.set(2)`) are coalesced and emit only the final value (`2`).",
            keyPoints = listOf(
                "`toSignal()` bridges RxJS streams to Signals, automatically managing unsubscription via DestroyRef",
                "Requires initialValue or requireSync to ensure the returned Signal has a synchronous value",
                "`toObservable()` wraps a Signal into an Observable using an internal effect() listener",
                "`toObservable()` emits asynchronously on change detection microtasks, coalescing intermediate sets",
                "Enables combining RxJS async operators (debounce, switchMap) with Signal template rendering"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_028",
            trackId = "angular_interview",
            conceptId = "ng_signals_reactivity",
            conceptName = "Angular Signals, Reactivity & Zoneless Architecture",
            title = "Zoneless Angular (Angular 18+): Architecture and Change Detection",
            question = "How does Angular 18+ achieve Zoneless Change Detection without `zone.js`? How does the framework know when to schedule DOM updates?",
            shortAnswer = "How Zone.js Worked: Monkey-patched all async browser APIs (`addEventListener`, `setTimeout`, `fetch`). Whenever ANY async event completed anywhere, Zone.js triggered a full top-down dirty check from the Root component down to every leaf node. How Zoneless Works: `zone.js` is completely removed from the bundle. Angular relies on explicit notifications scheduled via an internal `ChangeDetectionScheduler`: 1) Signal updates: Reading signals in templates registers template dependencies; mutating a signal notifies the scheduler. 2) RxJS `async` pipe: Automatically notifies the scheduler on emissions. 3) Template event listeners: Clicking a button in an Angular template notifies the scheduler. 4) `ChangeDetectorRef.markForCheck()`: Programmatic notification. The scheduler coalesces notifications and runs change detection only on marked component subtrees via microtasks.",
            keyPoints = listOf(
                "Zoneless removes Zone.js bundle overhead (~30KB) and eliminates monkey-patched browser APIs",
                "Replaces global top-down dirty checking with scheduler-driven targeted change detection",
                "Change detection is triggered by Signal mutations, async pipes, template events, and markForCheck()",
                "The ChangeDetectionScheduler coalesces multiple concurrent updates into a single microtask render",
                "Eliminates subtle Zone.js bugs with async/await, Web Workers, and third-party Web Components"
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_ng_029",
            trackId = "angular_interview",
            conceptId = "ng_signals_reactivity",
            conceptName = "Angular Signals, Reactivity & Zoneless Architecture",
            title = "Enabling Zoneless with provideExperimentalZonelessChangeDetection()",
            question = "How do you configure an Angular 18+ application for Zoneless execution? What third-party library compatibility pitfalls must you audit?",
            shortAnswer = "Configuration: 1) In `app.config.ts`, replace `provideZoneChangeDetection()` with `provideExperimentalZonelessChangeDetection()`. 2) In `angular.json`, remove `zone.js` from the `polyfills` array. Audit Pitfalls: 1) Legacy OnPush Components mutating mutable object state without calling `markForCheck()` or using Signals will STOP updating the DOM. 2) Third-party UI libraries (e.g. older datepickers) that rely on Zone.js to detect internal `setTimeout` or raw DOM event handlers will fail to update. 3) RxJS subscriptions inside `.subscribe(val => this.val = val)` without `markForCheck()` or converting to Signals will not re-render. Solution: Standardize components on `ChangeDetectionStrategy.OnPush`, migrate state to Signals, and use `toSignal()`.",
            keyPoints = listOf(
                "Enable via `provideExperimentalZonelessChangeDetection()` in application bootstrap providers",
                "Remove zone.js from angular.json polyfills array to eliminate bundle footprint",
                "Requires auditing legacy components that rely on Zone.js automatic top-down re-renders",
                "Raw RxJS `.subscribe()` assigning class properties must migrate to Signals or call markForCheck()",
                "Third-party libraries must be tested to ensure they properly interact with Angular's scheduler"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_030",
            trackId = "angular_interview",
            conceptId = "ng_signals_reactivity",
            conceptName = "Angular Signals, Reactivity & Zoneless Architecture",
            title = "ChangeDetectionStrategy.OnPush vs Default Change Detection",
            question = "How does `ChangeDetectionStrategy.OnPush` optimize Angular component tree rendering? Detail the exact criteria that trigger an OnPush component check.",
            shortAnswer = "Default Strategy: Traverses EVERY component in the entire component tree from top to bottom on every async event, evaluating all template expressions (wasteful CPU cycles in enterprise apps). OnPush Strategy: Instructs Angular to SKIP checking this component and its entire child subtree unless explicitly invalidated. Criteria Triggering OnPush Checks: 1) An `@Input()` property receives a new object reference (identity comparison `===`). 2) A DOM event listener declared in the component's OWN template fires (e.g. `(click)`). 3) An Observable bound via the `async` pipe emits. 4) A Signal read in the template changes. 5) `ChangeDetectorRef.markForCheck()` is called explicitly. Any parent-to-child data flow with mutable object mutations fails to re-render unless object references change or Signals are used.",
            keyPoints = listOf(
                "Default change detection visits every component in the entire application tree sequentially",
                "OnPush skips checking entire component subtrees unless explicit invalidation criteria are met",
                "Triggers on new @Input object references, template event listeners, async pipes, and Signal updates",
                "Requires immutable state discipline because input comparisons rely on reference equality (===)",
                "Drastically cuts CPU execution time and eliminates frame drops in complex data grids"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_031",
            trackId = "angular_interview",
            conceptId = "ng_signals_reactivity",
            conceptName = "Angular Signals, Reactivity & Zoneless Architecture",
            title = "ChangeDetectorRef API: markForCheck vs detectChanges vs detach",
            question = "Compare `markForCheck()`, `detectChanges()`, `detach()`, and `reattach()` on `ChangeDetectorRef`. When is each method appropriate?",
            shortAnswer = "1) `markForCheck()`: Marks the component and all its ancestor components up to the root as dirty. It does NOT run change detection immediately; it simply ensures that the component will be checked during the NEXT scheduled change detection cycle. Safe and recommended for OnPush and Zoneless. 2) `detectChanges()`: Runs change detection SYNCHRONOUSLY right now on this component and its children. Warning: Can trigger `ExpressionChangedAfterItHasBeenCheckedError` if called inappropriately; use sparingly for urgent DOM updates. 3) `detach()`: Detaches the component's change detector from the change detection tree entirely. Angular will NEVER check this component automatically. Ideal for components displaying thousands of static rows or high-frequency real-time financial ticks. 4) `reattach()`: Re-links a previously detached change detector.",
            keyPoints = listOf(
                "markForCheck() flags the component and its ancestors for checking during the next scheduled cycle",
                "detectChanges() executes change detection synchronously on the current subtree immediately",
                "detach() completely removes the component from change detection cycles to eliminate CPU overhead",
                "reattach() restores a detached component back into the active change detection hierarchy",
                "Detaching is ideal for high-throughput live data streaming widgets that update on manual intervals"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_032",
            trackId = "angular_interview",
            conceptId = "ng_signals_reactivity",
            conceptName = "Angular Signals, Reactivity & Zoneless Architecture",
            title = "NgZone Event Coalescing and Performance Optimization",
            question = "What are `ngZoneEventCoalescing` and `ngZoneRunCoalescing`? How do they reduce redundant change detection cycles in Zone.js apps?",
            shortAnswer = "By default in Zone.js, if a user event triggers multiple nested DOM listeners (e.g. click on a button fires button handler, container handler, and window handler), Zone.js triggers a separate change detection cycle for EVERY listener invocation. Similarly, multiple concurrent microtasks or timers trigger separate top-down checks. Solutions: 1) `ngZoneEventCoalescing: true`: Coalesces multiple event listener executions triggered by the same event bubble chain into a SINGLE change detection cycle. 2) `ngZoneRunCoalescing: true`: Coalesces multiple asynchronous tasks (e.g. 5 concurrent HTTP responses resolving within the same event loop turn) into a single change detection tick. Configured in `app.config.ts`: `provideZoneChangeDetection({ eventCoalescing: true, runCoalescing: true })`. Slashes CPU consumption by 40-70%.",
            keyPoints = listOf(
                "Default Zone.js triggers separate change detection passes for each bubbling event listener",
                "`ngZoneEventCoalescing` merges multiple event handlers from one event into a single render tick",
                "`ngZoneRunCoalescing` batches multiple concurrent async tasks resolving in the same event loop turn",
                "Configured via `provideZoneChangeDetection({ eventCoalescing: true, runCoalescing: true })`",
                "Significantly reduces redundant change detection cycles and boosts frame rendering rates"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_033",
            trackId = "angular_interview",
            conceptId = "ng_signals_reactivity",
            conceptName = "Angular Signals, Reactivity & Zoneless Architecture",
            title = "Running Code Outside Angular with NgZone.runOutsideAngular()",
            question = "Why do high-frequency DOM events (scroll, mousemove, requestAnimationFrame) cause UI lag in Angular? How does `NgZone.runOutsideAngular()` solve this?",
            shortAnswer = "Because Zone.js monkey-patches `addEventListener` and `requestAnimationFrame`, attaching a listener to `window.onscroll` or `mousemove` causes Zone.js to run change detection across the ENTIRE component tree on every single mouse pixel movement or scroll tick (60 to 120 times per second). The browser main thread chokes, causing severe UI jank. Solution: Inject `NgZone` and wrap the listener in `runOutsideAngular`: `this.ngZone.runOutsideAngular(() => { window.addEventListener('scroll', () => { this.updateScrollPosition(); if (this.thresholdReached) { this.ngZone.run(() => this.showFloatingButton = true); } }); });`. The scroll event executes in the outer JavaScript context without triggering Angular change detection. When an actual UI state update is required, `this.ngZone.run()` re-enters Angular to update the DOM.",
            keyPoints = listOf(
                "Zone.js triggers full change detection cycles on every native scroll, mousemove, or animation frame",
                "High-frequency events saturate the main thread, resulting in severe frame drops and jank",
                "`ngZone.runOutsideAngular()` attaches event listeners outside Zone.js execution boundaries",
                "Bypasses Angular change detection completely while high-frequency events are processing",
                "`ngZone.run()` selectively re-enters Angular change detection only when UI state must update"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_034",
            trackId = "angular_interview",
            conceptId = "ng_signals_reactivity",
            conceptName = "Angular Signals, Reactivity & Zoneless Architecture",
            title = "Memory Leaks with effect() and onCleanup Teardown",
            question = "How can `effect()` introduce memory leaks or race conditions? How does the `onCleanup` callback resolve them?",
            shortAnswer = "Hazards of `effect()`: 1) Dangling Timers / Event Listeners: If an effect starts an asynchronous timer (`setInterval`) or manual DOM listener whenever an input signal changes, re-running the effect creates multiple duplicate running timers, leaking memory. 2) Race Conditions: If an effect triggers an async fetch based on a search signal, slow older requests can resolve after faster newer requests. The `onCleanup` Callback: Angular provides an `onCleanup` registration hook inside effects that executes BEFORE the effect re-runs or when the effect is destroyed: `effect((onCleanup) => { const timerId = setInterval(() => console.log(counter()), 1000); const controller = new AbortController(); fetch('/api/data', { signal: controller.signal }); onCleanup(() => { clearInterval(timerId); controller.abort(); }); });`. Guarantees clean teardown of resources on every reactive cycle.",
            keyPoints = listOf(
                "Effects setting up async intervals or external subscriptions can leak resources across runs",
                "Asynchronous API fetches inside effects can cause out-of-order race conditions",
                "`onCleanup()` registers a teardown function executed before the effect re-evaluates or destroys",
                "Ideal for clearing intervals, closing WebSocket sockets, and aborting in-flight HTTP requests",
                "Ensures side-effecting operations maintain zero resource leaks across reactive signal updates"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_035",
            trackId = "angular_interview",
            conceptId = "ng_signals_reactivity",
            conceptName = "Angular Signals, Reactivity & Zoneless Architecture",
            title = "allowSignalWrites in effect() Anti-Pattern",
            question = "Why does Angular forbid writing to signals inside `effect()` by default? Why is setting `allowSignalWrites: true` considered an anti-pattern?",
            shortAnswer = "Angular deliberately throws an error (`NG0600: Writing to signals is not allowed in a computed or an effect by default`) if you call `.set()` on a signal inside an `effect()`. Reason: Writing to signals inside an effect creates circular reactive dependencies: Signal A triggers Effect B, which writes to Signal C, which triggers Effect D, which writes back to Signal A, causing an infinite change detection loop that crashes the browser. Why `allowSignalWrites: true` is an Anti-Pattern: It indicates a flawed mental model trying to synchronize state imperatively. If Signal C depends on Signal A, it should be declared as a `computed(() => ...)` signal! Deriving state synchronously via `computed()` is glitch-free, memoized, and immune to circular write loops.",
            keyPoints = listOf(
                "Angular disallows writing to signals inside effects to prevent circular infinite loops",
                "allowSignalWrites: true bypasses safety checks, often causing browser crashes from recursive writes",
                "Using effects to synchronize state is an imperative anti-pattern; state should be derived",
                "Derived state should always be modeled using pure, synchronous computed() signals",
                "Only legitimate use for allowSignalWrites is bridging external non-reactive imperative third-party events"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_036",
            trackId = "angular_interview",
            conceptId = "ng_signals_reactivity",
            conceptName = "Angular Signals, Reactivity & Zoneless Architecture",
            title = "Angular 19 linkedSignal() for Dependent Writable State",
            question = "What problem does Angular 19's `linkedSignal()` solve? Contrast it with `computed()` and manual `effect()` signal syncing.",
            shortAnswer = "The Problem: You have an input signal `options = input<string[]>()` and want a local selected option `selectedOption = signal<string>('')`. You want `selectedOption` to be WRITABLE by the user (clicking a dropdown), BUT whenever `options` changes from the parent, `selectedOption` must automatically reset to the first option (`options()[0]`). Before Angular 19: You had to use an `effect({ allowSignalWrites: true })` to watch `options` and call `selectedOption.set()`, which is messy and prone to glitches. `linkedSignal()` Solution: Creates a writable signal whose value is LINKED to a computation source: `selectedOption = linkedSignal(() => this.options()[0])`. Behavior: 1) It is fully writable: user can call `selectedOption.set('custom')`. 2) Whenever `options()` mutates, `linkedSignal` automatically resets its value to the computation result.",
            keyPoints = listOf(
                "Solves the challenge of maintaining local writable state that resets when upstream state changes",
                "Replaces messy `effect()` with `allowSignalWrites: true` with a clean declarative primitive",
                "`linkedSignal(() => computation)` derives its initial value from an upstream signal source",
                "Remains fully writable by local components via standard `.set()` and `.update()` methods",
                "Automatically recalculates and resets its value whenever the upstream source signal mutates"
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_ng_037",
            trackId = "angular_interview",
            conceptId = "ng_signals_reactivity",
            conceptName = "Angular Signals, Reactivity & Zoneless Architecture",
            title = "Signal Inputs with Attribute Coercion Transforms",
            question = "How do transform functions work in Signal Inputs (`input()`)? Explain `booleanAttribute` and `numberAttribute`.",
            shortAnswer = "In HTML templates, boolean attributes are often passed without values: `<app-button disabled></app-button>`. In standard JavaScript, the DOM attribute is the empty string `\"\"` (which is truthy, but not boolean `true`). If passed as `<app-card [elevation]=\"'5'\">`, the number is passed as a string. Signal Input Transforms solve this natively at the input boundary: `disabled = input(false, { transform: booleanAttribute }); elevation = input(0, { transform: numberAttribute });`. 1) `booleanAttribute`: Coerces `\"\"` (empty string) and `\"true\"` to boolean `true`, and `\"false\"` / `null` / `undefined` to `false`. 2) `numberAttribute`: Parses numeric strings to numbers (`'42'` -> `42`), with an optional fallback for `NaN`. 3) Custom Transforms: Any pure function `(value: T) => R` can be passed to normalize inputs before signal storage.",
            keyPoints = listOf(
                "Input transforms coerce and format incoming template attributes before signal storage",
                "`booleanAttribute` converts empty strings (e.g. `<tag disabled>`) and 'true' to boolean true",
                "`numberAttribute` converts string numbers to typed floats/integers with NaN fallback handling",
                "Supports custom transformation functions `transform: (val: string) => ParsedType`",
                "Eliminates manual getter/setter type coercion boilerplate in component TypeScript classes"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_038",
            trackId = "angular_interview",
            conceptId = "ng_signals_reactivity",
            conceptName = "Angular Signals, Reactivity & Zoneless Architecture",
            title = "Readonly Signals vs Writable Signals: asReadonly() Encapsulation",
            question = "Why is exposing raw writable signals directly from shared services an anti-pattern? How does `.asReadonly()` enforce encapsulation?",
            shortAnswer = "The Anti-Pattern: If an Angular service exposes `public users = signal<User[]>([])`, ANY component can call `service.users.set([])` or mutate state arbitrarily. This destroys unidirectional data flow, makes state changes untraceable, and scatters state mutation logic across components. Encapsulation Best Practice: Keep the writable signal private inside the service, and expose a read-only signal alongside explicit mutation methods: `class UserService { private _users = signal<User[]>([]); public readonly users = this._users.asReadonly(); public addUser(user: User) { this._users.update(list => [...list, user]); } }`. `.asReadonly()` returns a `Signal<T>` view that lacks `.set()` and `.update()`. Components can read and react to the signal in templates, but CANNOT mutate it directly.",
            keyPoints = listOf(
                "Exposing writable signals publicly violates encapsulation and makes state mutation untraceable",
                "Components should not call `.set()` or `.update()` directly on shared service state",
                "Keep internal writable signals private (`private _state = signal(...)`)",
                "Expose immutable read-only public signals using `state = this._state.asReadonly()`",
                "Provide explicit service methods to perform validated, deterministic state transitions"
            ),
            difficulty = "Senior"
        )
    )
    private fun part3(): List<InterviewQuestion> = listOf(
        InterviewQuestion(
            id = "iq_ng_039",
            trackId = "angular_interview",
            conceptId = "ng_signals_reactivity",
            conceptName = "Angular Signals, Reactivity & Zoneless Architecture",
            title = "Zone.js Monkey-Patching Overhead and Enterprise Pain Points",
            question = "Detail the technical pain points of Zone.js in large enterprise Angular applications that motivated the push to Zoneless.",
            shortAnswer = "Pain Points of Zone.js: 1) Massive Top-Down Checking: Every time ANY async event completes (even an unhandled mousemove in a third-party tooltip), Zone.js notifies Angular to check the ENTIRE component tree from root down. In an app with 5,000 components, this wastes enormous CPU. 2) Bundle Size & Startup Cost: Zone.js adds ~30-40KB of minified JavaScript to the initial critical bundle. 3) Native Async/Await Limitations: Zone.js cannot patch native ES2017 `async/await` syntax without downleveling to ES5/generators, adding compilation overhead. 4) Unpatchable APIs: Cannot patch Web Workers or custom Web Components natively. 5) Debugging Hell: Stack traces are deeply polluted with hundreds of Zone.js internal frames (`zone.runTask`, `drainMicroTaskQueue`), making root-cause debugging difficult.",
            keyPoints = listOf(
                "Zone.js triggers global top-down change detection on any async event, degrading enterprise performance",
                "Adds 30-40KB of critical bundle overhead and slows initial application boot time",
                "Prevents native ES2017+ async/await syntax, requiring slower transpiled generator code",
                "Incompatible with multi-threaded Web Workers and creates friction with Web Components",
                "Pollutes browser debugging stack traces with dozens of internal Zone monkey-patching frames"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_040",
            trackId = "angular_interview",
            conceptId = "ng_signals_reactivity",
            conceptName = "Angular Signals, Reactivity & Zoneless Architecture",
            title = "Signals vs Virtual DOM: Fine-Grained Template Updates",
            question = "Contrast Angular Signals' fine-grained template reactivity with React's Virtual DOM diffing. Why are Signals computationally more efficient?",
            shortAnswer = "Virtual DOM Model (React): When component state changes, React re-executes the ENTIRE component render function from scratch. It builds an in-memory Virtual DOM tree of JavaScript objects representing the entire component UI, diffs (reconciles) the new Virtual DOM tree against the old Virtual DOM tree, and patches the actual browser DOM. Drawback: High CPU overhead re-executing functions and allocating thousands of short-lived VDOM objects on every render. Angular Signals Model (Fine-Grained): Angular templates are compiled into static DOM creation instructions and discrete reactive update instructions. Signals establish direct fine-grained reactive edges between a Signal and the SPECIFIC DOM text node or attribute binding that reads it. When the signal changes, Angular bypasses component re-rendering entirely and updates that exact DOM node directly in O(1) time with zero Virtual DOM allocation and zero tree diffing.",
            keyPoints = listOf(
                "Virtual DOM re-executes entire component render functions, generating garbage-collected VDOM trees",
                "VDOM requires diffing old and new trees to calculate minimal DOM mutation patches",
                "Signals establish direct fine-grained dependencies between state and individual DOM nodes",
                "Mutating a signal updates target DOM text nodes or attributes directly in O(1) time",
                "Completely eliminates Virtual DOM memory allocation, component re-execution, and tree diffing"
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_ng_041",
            trackId = "angular_interview",
            conceptId = "ng_rxjs_streams",
            conceptName = "RxJS Streams & Reactive Programming",
            title = "Cold vs Hot Observables in Angular",
            question = "Explain the fundamental difference between Cold and Hot Observables in RxJS. Provide concrete examples in an Angular application.",
            shortAnswer = "1) Cold Observable: The data producer is created INSIDE the observable subscription. It does NOT produce values until a consumer calls `.subscribe()`. Every new subscriber gets an independent, separate execution of the producer. Example: Angular's `HttpClient.get('/api/users')`. If 3 components subscribe to `users\$`, 3 separate physical HTTP network requests are dispatched to the backend. 2) Hot Observable: The data producer exists OUTSIDE the observable and produces values regardless of whether subscribers exist. Subscribers share the same execution stream and receive values emitted AFTER they subscribe. Examples: DOM event streams (`fromEvent(button, 'click')`), WebSockets, and RxJS `Subject`. Multiple subscribers receive the exact same click event without re-triggering the click.",
            keyPoints = listOf(
                "Cold observables instantiate their data producer only when a consumer subscribes",
                "Each cold observable subscriber triggers an independent execution (e.g. separate HTTP requests)",
                "Hot observables emit values from an external producer regardless of active subscriber counts",
                "Hot observable subscribers share the active stream, receiving emissions from subscription time forward",
                "Use multicasting operators (shareReplay) to convert cold HTTP observables into hot shared streams"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_042",
            trackId = "angular_interview",
            conceptId = "ng_rxjs_streams",
            conceptName = "RxJS Streams & Reactive Programming",
            title = "Subject Hierarchy: Subject, BehaviorSubject, ReplaySubject, AsyncSubject",
            question = "Compare the 4 types of Subjects in RxJS: `Subject`, `BehaviorSubject`, `ReplaySubject`, and `AsyncSubject`. When do you choose each?",
            shortAnswer = "1) `Subject`: A basic hot observable and observer. Subscribers receive ONLY future emissions that occur AFTER they subscribe. No initial value or history. 2) `BehaviorSubject`: Holds a mandatory initial value. When a new consumer subscribes, it IMMEDIATELY receives the latest current value synchronously (`.getValue()`). Ideal for stateful services (e.g. current user, theme). 3) `ReplaySubject(bufferSize, windowTime)`: Buffers the last N emissions (or emissions within a time window). New subscribers receive all buffered values immediately, even if they missed them. Ideal for caching multiple historical events or auth tokens without needing an initial value. 4) `AsyncSubject`: Emits ONLY the final value, and ONLY when the stream completes (`.complete()`). Ideal for calculations that produce a single final result.",
            keyPoints = listOf(
                "Subject emits only future events; late subscribers miss earlier emissions",
                "BehaviorSubject requires an initial value and delivers the current value immediately to new subscribers",
                "BehaviorSubject provides synchronous state inspection via `.value` or `.getValue()`",
                "ReplaySubject replays a specified buffer of historical emissions to late subscribers",
                "AsyncSubject emits only the final emission upon stream completion (similar to a Promise)"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_043",
            trackId = "angular_interview",
            conceptId = "ng_rxjs_streams",
            conceptName = "RxJS Streams & Reactive Programming",
            title = "Higher-Order Flattening: switchMap vs mergeMap vs concatMap vs exhaustMap",
            question = "Compare the 4 higher-order flattening operators in RxJS: `switchMap`, `mergeMap`, `concatMap`, and `exhaustMap`. What concurrency strategy does each implement?",
            shortAnswer = "Higher-order observables emit inner observables that must be flattened into values: 1) `switchMap` (Switch/Cancel): Cancels (unsubscribes from) the previous in-flight inner observable as soon as a new outer emission arrives. Concurrency: 1 (always current). Ideal for search autocomplete. 2) `mergeMap` (Concurrent/Parallel): Subscribes to every inner observable concurrently as they arrive without cancelling or waiting. Concurrency: Unlimited (configurable). Ideal for independent fire-and-forget operations (e.g. uploading multiple files concurrently). 3) `concatMap` (Sequential/Queue): Queues incoming inner observables in order, subscribing to the next ONLY after the previous inner observable completes. Concurrency: 1 (FIFO queue). Ideal for sequential updates (e.g. chat messages, ordered DB writes). 4) `exhaustMap` (Ignore/Lock): Ignores and discards all incoming outer emissions while an inner observable is currently active. Concurrency: 1 (lockout). Ideal for submit/login buttons.",
            keyPoints = listOf(
                "switchMap cancels in-flight inner observables on new emissions; standard for search/filtering",
                "mergeMap handles all inner observables concurrently in parallel without ordering guarantees",
                "concatMap queues inner observables sequentially, preserving strict FIFO execution order",
                "exhaustMap drops incoming outer emissions while an inner operation is active, preventing duplicate triggers",
                "Selecting the wrong flattening operator causes race conditions, duplicate writes, or memory leaks"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_044",
            trackId = "angular_interview",
            conceptId = "ng_rxjs_streams",
            conceptName = "RxJS Streams & Reactive Programming",
            title = "exhaustMap for Double-Click and Duplicate Submission Defense",
            question = "How does `exhaustMap` prevent duplicate form submissions and double-charge payment bugs in Angular? Write an example.",
            shortAnswer = "Problem: A user clicks 'Pay Now' or 'Submit Order', the network lags for 2 seconds, and the impatient user clicks the button 4 more times. Using `mergeMap` sends 5 charges to the credit card API; using `switchMap` cancels the first charge mid-flight and starts a new one. `exhaustMap` Solution: `submitButtonClicks\$.pipe(exhaustMap(() => this.paymentService.processPayment(orderData))).subscribe(result => this.handleSuccess(result));`. Behavior: The first click triggers the inner payment observable. While the payment HTTP request is pending (in-flight), `exhaustMap` completely ignores, drops, and discards all subsequent clicks! Only after the payment completes (or errors) does it accept new emissions, providing client-side idempotent submission defense.",
            keyPoints = listOf(
                "Double-clicking payment or submit buttons triggers duplicate HTTP transactions under mergeMap",
                "switchMap cancels in-flight transactions, which can leave backend services in ambiguous states",
                "exhaustMap ignores all subsequent button clicks until the active HTTP observable completes",
                "Provides mathematical client-side prevention against duplicate transactional requests",
                "Button UI state should be disabled concurrently for visual feedback while exhaustMap enforces logic"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_045",
            trackId = "angular_interview",
            conceptId = "ng_rxjs_streams",
            conceptName = "RxJS Streams & Reactive Programming",
            title = "concatMap for Sequential Message Queues and Order Preservation",
            question = "When is `concatMap` strictly required over `mergeMap`? Provide a scenario where parallel execution causes database or state corruption.",
            shortAnswer = "Scenario: Chat messages or stock order placement where order of execution is critical. If a user rapidly sends Message 1 ('Hello') and Message 2 ('How are you?'), using `mergeMap` fires two concurrent HTTP requests. Due to variable network latency, Request 2 might reach the server before Request 1, corrupting conversation history. With `concatMap`: `messageQueue\$.pipe(concatMap(msg => this.chatService.sendMessage(msg))).subscribe();`. Behavior: Message 1 is sent. When Message 2 arrives, `concatMap` places it into an internal FIFO queue. It waits until Message 1's HTTP request completes with 200 OK before subscribing to Message 2's request, guaranteeing strict sequential execution and preserving chronological order.",
            keyPoints = listOf(
                "mergeMap executes inner observables concurrently; network jitter causes out-of-order resolution",
                "concatMap maintains an internal queue, executing inner streams strictly one after another",
                "Next inner observable starts only after the previous inner observable has emitted and completed",
                "Essential for sequential database mutations, ordered message pipelines, and step-by-step wizard sync",
                "Inner observables must complete; if an inner stream never completes, concatMap stalls forever"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_046",
            trackId = "angular_interview",
            conceptId = "ng_rxjs_streams",
            conceptName = "RxJS Streams & Reactive Programming",
            title = "Typeahead Autocomplete Pattern with switchMap and Race Condition Defense",
            question = "Write a complete reactive typeahead search pipeline in Angular. Explain why `switchMap` is necessary to eliminate out-of-order race conditions.",
            shortAnswer = "Pipeline: `searchControl.valueChanges.pipe(map(term => term?.trim() ?? ''), filter(term => term.length >= 2), debounceTime(300), distinctUntilChanged(), switchMap(term => this.searchService.search(term).pipe(catchError(err => of([])))), takeUntilDestroyed()).subscribe(results => this.results.set(results));`. Why `switchMap` is Mandatory: Suppose user types 'ang', triggering Request 1. User immediately types 'angular', triggering Request 2. Request 1 takes 800ms (slow network route); Request 2 takes 150ms. With `mergeMap`, Request 2 finishes first, showing 'angular' results. Then, Request 1 resolves at 800ms, overwriting the UI with STALE 'ang' results! `switchMap` automatically cancels (aborts) Request 1 the moment Request 2 is initiated, completely preventing out-of-order race conditions.",
            keyPoints = listOf(
                "Standard pipeline uses filter, debounceTime(300), and distinctUntilChanged for traffic shaping",
                "Variable network response latency can cause earlier search requests to resolve after later ones",
                "switchMap cancels the in-flight HTTP request when a new search query arrives",
                "Aborts stale network requests and prevents older responses from overwriting newer UI results",
                "catchError must be placed on the inner observable to prevent searchControl stream termination"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_047",
            trackId = "angular_interview",
            conceptId = "ng_rxjs_streams",
            conceptName = "RxJS Streams & Reactive Programming",
            title = "Combining Streams: forkJoin vs combineLatest vs zip vs withLatestFrom",
            question = "Compare `forkJoin`, `combineLatest`, `zip`, and `withLatestFrom`. Detail when each operator emits and when it completes.",
            shortAnswer = "1) `forkJoin([a\$, b\$])`: Similar to `Promise.all()`. Waits until ALL source observables COMPLETE, then emits a single array of their FINAL values. If any source errors, forkJoin errors. Ideal for parallel HTTP requests (e.g. loading user profile + permissions together on page load). 2) `combineLatest([a\$, b\$])`: Waits for every source to emit at least once. Then, whenever ANY source emits, emits an array of the LATEST values from each source. Never completes until all complete. Ideal for combining filter dropdowns. 3) `zip([a\$, b\$])`: Emits in pairwise lockstep (1st of A with 1st of B, 2nd of A with 2nd of B). 4) `a\$.pipe(withLatestFrom(b\$))`: `a\$` is the master trigger. Whenever `a\$` emits, it grabs the current latest value of `b\$` and emits `[a, b]`. Emissions from `b\$` alone do NOT trigger output.",
            keyPoints = listOf(
                "forkJoin waits for all sources to complete, emitting once with the last value of each stream",
                "forkJoin is the standard choice for firing independent parallel HTTP requests simultaneously",
                "combineLatest emits immediately whenever any source stream emits, combining all current values",
                "zip pairs emissions on a strict 1-to-1 index matching basis like a zipper",
                "withLatestFrom uses the source stream as an exclusive master trigger, sampling the secondary stream"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_048",
            trackId = "angular_interview",
            conceptId = "ng_rxjs_streams",
            conceptName = "RxJS Streams & Reactive Programming",
            title = "RxJS Error Handling: Inner vs Outer catchError() Stream Death",
            question = "Why does placing `catchError()` on the outer stream kill user input controls in Angular? How does inner observable error handling protect stream survivability?",
            shortAnswer = "The Observable Contract: When an Observable emits an `error` notification, the stream permanently terminates and tears down all subscriptions. It can NEVER emit again. The Outer Catch Trap: `searchControl.valueChanges.pipe(debounceTime(300), switchMap(q => api.search(q)), catchError(err => of([]))).subscribe();`. If an HTTP 500 error occurs, `catchError` catches it, emits `[]`, and the entire stream COMPLETES. The user types again in the input box, but change listeners are dead—zero queries fire! Inner Catch Fix: Place `catchError` INSIDE the `switchMap` projection: `searchControl.valueChanges.pipe(switchMap(q => api.search(q).pipe(catchError(err => of([]))))).subscribe();`. If the HTTP request fails, the inner observable errors and is caught, returning `[]` to the outer stream. The outer `valueChanges` stream remains healthy and continues listening for future user keystrokes.",
            keyPoints = listOf(
                "RxJS streams that emit an error notification terminate permanently per the Observable contract",
                "Placing catchError on the outer stream kills input event listeners on the first HTTP error",
                "Future user keystrokes or UI clicks fail to trigger because the outer subscription is completed",
                "Placing catchError inside the inner observable catches errors locally without killing the outer pipeline",
                "Inner error handlers can return fallback values (e.g. `of([])`) while keeping the main stream alive"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_049",
            trackId = "angular_interview",
            conceptId = "ng_rxjs_streams",
            conceptName = "RxJS Streams & Reactive Programming",
            title = "Resilient HTTP Retries with Exponential Backoff and Jitter",
            question = "How do you implement an exponential backoff retry strategy with randomized jitter in RxJS using `retry({ delay: ... })`?",
            shortAnswer = "In modern RxJS 7+, `retry` accepts a configuration object with a delay callback: `api.getData().pipe(retry({ count: 3, delay: (error, retryCount) => { if (error.status === 400 || error.status === 404) { return throwError(() => error); } const baseDelay = Math.pow(2, retryCount) * 1000; const jitter = Math.random() * 500; const totalDelay = baseDelay + jitter; return timer(totalDelay); } }));`. Mechanics: 1) Circuit Breaker: Fails fast on 4xx client errors (retrying a 404 is pointless). 2) Exponential Backoff: `2^retryCount * 1000ms` increases wait intervals (2s, 4s, 8s), allowing overloaded servers to recover. 3) Randomized Jitter: Adds random milliseconds (0-500ms) to decorrelate concurrent retries from thousands of clients, preventing a synchronized thundering herd retry storm on the backend.",
            keyPoints = listOf(
                "Modern `retry({ count, delay })` provides programmatic control over error filtering and delay intervals",
                "Immediate fail-fast checks filter out non-retriable 4xx client errors (400, 401, 404)",
                "Exponential backoff (`2^n * base`) spaces out retry attempts to allow backend recovery",
                "Randomized jitter adds timing entropy to prevent thundering herd retry spikes on servers",
                "Returns a `timer()` observable to pause execution until the calculated backoff period elapses"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_050",
            trackId = "angular_interview",
            conceptId = "ng_rxjs_streams",
            conceptName = "RxJS Streams & Reactive Programming",
            title = "Memory Leak Prevention: takeUntilDestroyed vs AsyncPipe",
            question = "What causes memory leaks with RxJS subscriptions in Angular components? Compare `takeUntilDestroyed()`, `take(1)`, and `AsyncPipe`.",
            shortAnswer = "Cause of Leak: When a component calls `stream\$.subscribe()`, the Observable retains a reference to the component instance via closure. If the user navigates away, the component is destroyed from the DOM, but its instance CANNOT be garbage collected because the subscription keeps it pinned in memory. Solutions: 1) `AsyncPipe` (Best Practice): `<div *ngIf=\"data\$ | async as data\">`. Subscribes automatically when the template mounts, and automatically calls `.unsubscribe()` when the component is destroyed. Zero manual subscription management. 2) `takeUntilDestroyed()` (Modern): Injects `DestroyRef` and automatically completes the stream when the component destroys. 3) `take(1)`: Completes the stream immediately after the first emission. Ideal for HTTP requests where you only need a single response.",
            keyPoints = listOf(
                "Manual subscriptions keep component instances pinned in memory after DOM destruction",
                "AsyncPipe manages subscriptions declaratively in templates, unsubscribing on component destruction",
                "`takeUntilDestroyed()` automatically completes subscriptions using Angular 16+ DestroyRef",
                "`take(1)` is ideal for single-value emissions (HTTP calls), completing the stream immediately",
                "Signals (`toSignal()`) automatically integrate with DestroyRef, eliminating unsubscription boilerplate"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_051",
            trackId = "angular_interview",
            conceptId = "ng_rxjs_streams",
            conceptName = "RxJS Streams & Reactive Programming",
            title = "Multicasting Optimization: share() vs shareReplay() with refCount",
            question = "What is the difference between `share()` and `shareReplay()`? Why is configuring `refCount: true` critical to avoid memory leaks?",
            shortAnswer = "1) `share()`: Converts a cold observable into a hot, multicasted stream using a Subject. When the first subscriber connects, it subscribes to the source. Future subscribers share the active stream. When subscriber count drops to zero (`refCount: 0`), it unsubscribes from the source. Late subscribers get only future values. 2) `shareReplay({ bufferSize: 1, refCount: true })`: Caches the last emission and replays it to late subscribers. Critical Bug of `shareReplay(1)` without `refCount`: Legacy `shareReplay(1)` defaults to `refCount: false`. Once subscribed to, it NEVER unsubscribes from the underlying source observable, even if all components destroy! If the source is an interval or infinite WebSocket, it leaks memory forever. Adding `refCount: true` ensures that when all subscribers disconnect, it cleans up the source subscription properly.",
            keyPoints = listOf(
                "`share()` multicasts a single execution across multiple subscribers, unsubscribing when count reaches zero",
                "`shareReplay(1)` caches the last emitted value and replays it to late subscribers",
                "Legacy `shareReplay(1)` with `refCount: false` never unsubscribes from source, causing permanent memory leaks",
                "`refCount: true` guarantees that the source observable is torn down when active subscribers drop to zero",
                "Essential for caching expensive HTTP GET responses across multiple components"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_052",
            trackId = "angular_interview",
            conceptId = "ng_rxjs_streams",
            conceptName = "RxJS Streams & Reactive Programming",
            title = "Rate Limiting: debounceTime vs throttleTime vs auditTime vs sampleTime",
            question = "Compare the 4 rate-limiting operators in RxJS: `debounceTime`, `throttleTime`, `auditTime`, and `sampleTime`. Graph their emission timings.",
            shortAnswer = "1) `debounceTime(t)`: Emits a value ONLY after a silence window of duration `t` has elapsed with no new emissions. Every new emission resets the timer. Ideal for search inputs (waits until user stops typing). 2) `throttleTime(t, { leading: true, trailing: false })`: Emits the FIRST value immediately (leading edge), then ignores all emissions for duration `t`. Ideal for preventing rapid button spam. 3) `auditTime(t)`: Starts a timer on emission, ignores intermediate values, and emits the LATEST value at the END of duration `t` (trailing edge). 4) `sampleTime(t)`: Runs a periodic clock every `t` ms. If any value was emitted during that interval, it emits the latest value at the clock tick. Ideal for telemetry and scroll progress tracking.",
            keyPoints = listOf(
                "debounceTime waits for a quiet silence window before emitting; resets timer on every new event",
                "throttleTime emits the initial event immediately and silences subsequent events for a duration window",
                "auditTime waits for duration window completion and emits the most recent trailing value",
                "sampleTime emits the latest value periodically at regular fixed clock intervals",
                "debounceTime is standard for user typing; throttleTime is standard for window resizing/scrolling"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_053",
            trackId = "angular_interview",
            conceptId = "ng_rxjs_streams",
            conceptName = "RxJS Streams & Reactive Programming",
            title = "distinctUntilChanged() with Custom Comparator Functions",
            question = "How does `distinctUntilChanged()` optimize stream emissions? How do you provide a custom comparator for complex object streams?",
            shortAnswer = "By default, `distinctUntilChanged()` filters out consecutive duplicate emissions using strict identity equality (`===`). If a stream emits `1, 1, 2, 2, 1`, it outputs `1, 2, 1`. Object Problem: When an upstream API or store emits object copies (`{ id: 1, name: 'A' }, { id: 1, name: 'A' }`), strict `===` fails because the memory references differ, allowing redundant emissions to pass through. Custom Comparator Solution: Pass a comparator function `(previous, current) => boolean`: `stream\$.pipe(distinctUntilChanged((prev, curr) => prev.id === curr.id && prev.name === curr.name));` or use a key selector: `distinctUntilChanged((prev, curr) => prev.id === curr.id)`. Returning `true` signals that values are identical, discarding the emission and preventing downstream processing.",
            keyPoints = listOf(
                "distinctUntilChanged suppresses consecutive identical emissions across the stream",
                "Default comparator uses strict equality (===), which fails on distinct object references with identical fields",
                "Accepts a custom comparator function `(prev, curr) => boolean` to define semantic equality",
                "Returning true marks the item as identical, filtering it out from downstream operators",
                "Eliminates redundant HTTP queries, store dispatches, and UI re-renders on duplicate data"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_054",
            trackId = "angular_interview",
            conceptId = "ng_rxjs_streams",
            conceptName = "RxJS Streams & Reactive Programming",
            title = "State Accumulation with the scan Operator",
            question = "How does the `scan` operator accumulate state over time in RxJS? How can it be used to build a mini-Redux state store without external libraries?",
            shortAnswer = "`scan((accumulator, current) => newAccumulator, seed)` works like `Array.prototype.reduce()`, but unlike `reduce` (which emits only ONCE when the stream completes), `scan` emits the updated accumulated state on EVERY source emission. Building Mini-Redux Store: `interface Action { type: string; payload?: any; } const actions\$ = new Subject<Action>(); const initialState = { count: 0 }; const store\$ = actions\$.pipe(scan((state, action) => { switch (action.type) { case 'INC': return { ...state, count: state.count + 1 }; case 'ADD': return { ...state, count: state.count + action.payload }; default: return state; } }, initialState), shareReplay(1));`. Allows dispatching actions (`actions\$.next({ type: 'INC' })`) and streaming immutable state updates without NgRx.",
            keyPoints = listOf(
                "scan accumulates values over time, emitting the updated accumulator on every source emission",
                "Differs from reduce() which waits for stream completion before emitting a single value",
                "Applies a pure reducer function `(state, action) => newState` over an immutable accumulator",
                "Enables building lightweight Redux-like state containers natively using RxJS streams",
                "Combine with shareReplay(1) to provide late subscribers with immediate access to current state"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_055",
            trackId = "angular_interview",
            conceptId = "ng_rxjs_streams",
            conceptName = "RxJS Streams & Reactive Programming",
            title = "Authoring Custom RxJS Operators using pipe()",
            question = "How do you write a custom, reusable RxJS operator function in TypeScript? Provide an example of a `filterNull()` operator.",
            shortAnswer = "An RxJS operator is a higher-order function that takes an `Observable<T>` input and returns an `Observable<R>`. Best practice is to compose existing operators using `pipe()` with TypeScript type guards: `export function filterNull<T>(): (source\$: Observable<T | null | undefined>) => Observable<T> { return (source\$) => source\$.pipe(filter((val): val is T => val !== null && val !== undefined)); }`. Usage: `user\$.pipe(filterNull()).subscribe(user => console.log(user.name));`. Benefits: 1) Full TypeScript type narrowing: downstream operators receive `Observable<T>` without null. 2) Reusable across all project services. 3) Tree-shakable pure function.",
            keyPoints = listOf(
                "Custom operators are higher-order functions taking `Observable<T>` and returning `Observable<R>`",
                "Composes existing RxJS operators (filter, map) inside source\$.pipe()",
                "Leverages TypeScript type predicates (`val is T`) to narrow types for downstream operators",
                "Encapsulates complex stream transformations into clean, domain-specific operator functions",
                "Maintains functional purity, testability, and full tree-shaking support"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_056",
            trackId = "angular_interview",
            conceptId = "ng_rxjs_streams",
            conceptName = "RxJS Streams & Reactive Programming",
            title = "RxJS Schedulers and Execution Timing",
            question = "What are RxJS Schedulers? Compare `queueScheduler`, `asapScheduler`, and `asyncScheduler` with the JavaScript event loop.",
            shortAnswer = "Schedulers control the execution timing and concurrency context of observable subscriptions and emissions: 1) `queueScheduler`: Synchronous execution on the current call stack. Emits immediately, but queues nested recursive calls to prevent stack overflow. 2) `asapScheduler`: Executes asynchronously on the microtask queue (`Promise.then` / `queueMicrotask`). Emits before the browser renders, immediately after the current synchronous script finishes. 3) `asyncScheduler`: Executes asynchronously on the macrotask queue using `setInterval` or `setTimeout(fn, 0)`. Emits on the next event loop turn after browser rendering. Schedulers can be passed to operators: `of(1, 2, 3, asapScheduler)` or `observeOn(asyncScheduler)` to decouple heavy tasks.",
            keyPoints = listOf(
                "Schedulers orchestrate the execution timing and dispatch context of observable streams",
                "queueScheduler executes synchronously, managing internal queueing to prevent call stack overflow",
                "asapScheduler executes on the microtask queue before browser DOM painting (like Promise.resolve)",
                "asyncScheduler executes on the macrotask queue via setTimeout, yielding to browser rendering",
                "Passed to creation operators or observeOn/subscribeOn to control concurrency scheduling"
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_ng_057",
            trackId = "angular_interview",
            conceptId = "ng_rxjs_streams",
            conceptName = "RxJS Streams & Reactive Programming",
            title = "Backpressure Handling in High-Velocity RxJS Streams",
            question = "What is backpressure in RxJS? Compare Lossy backpressure strategies (debounce, throttle) with Lossless strategies (buffer, window).",
            shortAnswer = "Backpressure occurs when a producer emits events at a higher velocity than a consumer can process (e.g. WebSocket sending 1,000 telemetry packets/sec to an Angular chart that can only render at 60 FPS). Unhandled backpressure exhausts browser memory and locks the main thread. 1) Lossy Strategies (Drops Data): Acceptable when intermediate events can be discarded. `throttleTime` (samples rate), `debounceTime` (waits for silence), `sampleTime` (periodic snapshot). Discards unneeded intermediate data packets. 2) Lossless Strategies (Preserves Data): Required when every event must be saved or processed. `bufferCount(100)` (batches 100 events into one array), `bufferTime(1000)` (collects all events arriving in 1s into a single batch array), `windowTime` (emits nested observables of buffered events). Allows components to process data in chunked bulk operations.",
            keyPoints = listOf(
                "Backpressure occurs when stream emission frequency exceeds consumer processing capacity",
                "Lossy strategies (throttleTime, debounceTime, sample) drop intermediate values to match consumer rate",
                "Lossless strategies preserve all data by batching events into arrays or nested stream windows",
                "`bufferTime(1000)` groups high-frequency events into single 1-second batch arrays for efficient bulk processing",
                "Prevents browser memory exhaustion and UI thread freezing during high-throughput WebSocket streaming"
            ),
            difficulty = "Staff"
        )
    )
    private fun part4(): List<InterviewQuestion> = listOf(
        InterviewQuestion(
            id = "iq_ng_058",
            trackId = "angular_interview",
            conceptId = "ng_rxjs_streams",
            conceptName = "RxJS Streams & Reactive Programming",
            title = "Marble Testing with RxJS TestScheduler",
            question = "How does Marble Testing with `TestScheduler` allow deterministic unit testing of asynchronous, time-dependent RxJS streams without real-time delays?",
            shortAnswer = "Real-time tests using `setTimeout` or `fakeAsync` are slow, flaky, and non-deterministic. Marble Testing uses a virtual time string syntax where 1 frame (dash `-`) represents 1 virtual millisecond. Diagram Syntax: `-` = 1 virtual frame; `a` = emission; `|` = complete; `#` = error; `()` = synchronous grouping. Implementation with `TestScheduler`: `testScheduler.run(({ cold, expectObservable }) => { const source\$ = cold('-a--b--c---|'); const expected = '---a--b--c-|'; const result\$ = source\$.pipe(delay(2)); expectObservable(result\$).toBe(expected); });`. Inside `run()`, the virtual clock advances thousands of milliseconds instantaneously, enabling millisecond-accurate testing of debouncing, retries, and intervals in under 5 milliseconds.",
            keyPoints = listOf(
                "Marble testing uses ASCII diagrams to model stream emissions and virtual time deterministically",
                "`-` represents a virtual time frame (1ms), alphanumeric characters represent emitted values",
                "`|` represents completion, `#` represents errors, and `()` groups synchronous emissions",
                "TestScheduler runs virtual time instantaneously without real-world setTimeout delays",
                "Enables precise assertion testing of time-dependent operators (debounceTime, delay, bufferTime)"
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_ng_059",
            trackId = "angular_interview",
            conceptId = "ng_rxjs_streams",
            conceptName = "RxJS Streams & Reactive Programming",
            title = "Bridging Native DOM Events with fromEvent()",
            question = "How does `fromEvent()` wrap native DOM events into Observables? Why must you configure `{ passive: true }` for scroll performance?",
            shortAnswer = "`fromEvent(target, eventName, options)` converts standard browser event listeners into reactive streams: `fromEvent(window, 'scroll', { passive: true }).pipe(throttleTime(100), map(() => window.scrollY), takeUntilDestroyed()).subscribe(y => this.scrollY = y);`. Passive Event Listeners: By default, browser scroll event listeners block the browser's compositor thread because the browser must wait to see if the listener calls `event.preventDefault()` before scrolling the page. Configuring `{ passive: true }` tells the browser in advance that the listener will NEVER call `preventDefault()`. The browser compositor scrolls immediately with smooth 60 FPS, completely decoupling scrolling performance from JavaScript execution time.",
            keyPoints = listOf(
                "fromEvent converts browser EventTarget objects into manageable reactive Observable streams",
                "Automatically attaches addEventListener on subscription and removeEventListener on unsubscription",
                "Default scroll listeners block the browser compositor thread to check for preventDefault()",
                "`{ passive: true }` guarantees preventDefault() will not be called, enabling smooth 60 FPS scrolling",
                "Must be paired with takeUntilDestroyed() or lifecycle operators to remove native event listeners"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_060",
            trackId = "angular_interview",
            conceptId = "ng_rxjs_streams",
            conceptName = "RxJS Streams & Reactive Programming",
            title = "Architectural Decision Matrix: Signals vs RxJS in Modern Angular",
            question = "Provide a definitive architectural decision matrix for choosing between Angular Signals and RxJS Observables in modern applications.",
            shortAnswer = "Decision Matrix: 1) Use Angular Signals for: Synchronous state management, UI component state, derived template values (`computed`), two-way component bindings (`model()`), component inputs (`input()`), and template rendering. Signals excel at state that has a 'current value' and requires simple, glitch-free synchronous consumption. 2) Use RxJS Observables for: Asynchronous events over time, network communication (`HttpClient`, WebSockets), user input rate limiting (`debounceTime`, `throttleTime`), complex orchestration (`switchMap`, `forkJoin`, `exhaustMap`), race condition handling, and event buses. The Golden Pattern: Use RxJS in services to manage async flows, and convert to Signals via `toSignal()` at the component boundary for clean template consumption.",
            keyPoints = listOf(
                "Signals represent synchronous values over time; ideal for UI state, inputs, and template bindings",
                "RxJS represents asynchronous events over time; ideal for HTTP streams, WebSockets, and event pipelines",
                "Use RxJS for complex stream operators (debounceTime, switchMap, retry, buffer, exhaustMap)",
                "Use Signals for fine-grained template updates, computed derivations, and zoneless rendering",
                "Modern architecture: Process asynchronous flows with RxJS in services, expose Signals to components"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_061",
            trackId = "angular_interview",
            conceptId = "ng_di_architecture",
            conceptName = "Dependency Injection & Hierarchical Injector Architecture",
            title = "Angular Hierarchical Injector Tree: Element vs Environment Injector",
            question = "Explain the dual-tree hierarchy of Angular's Dependency Injection system: the Element Injector tree vs the Environment Injector tree.",
            shortAnswer = "Angular's DI system consists of two parallel injector hierarchies: 1) Element Injector Tree: Created implicitly at every DOM element that declares a component or directive with a `providers` or `viewProviders` array. It follows the exact component DOM structure: child components search up the parent element injector chain. 2) Environment Injector Tree: Manages application-wide, platform, and route-scoped services. Hierarchy: Root Environment Injector (`providedIn: 'root'`) -> Platform Injector -> NullInjector. In modern Angular with routing, every lazy-loaded route creates a child Environment Injector scoped to that route. When a component requests a token, Angular searches the Element Injector tree first; if not found, it switches to the Environment Injector tree.",
            keyPoints = listOf(
                "Element Injector tree mirrors the component DOM element nesting structure",
                "Environment Injector tree hosts application, route-scoped, and platform singletons",
                "Components search the Element Injector hierarchy upwards before consulting Environment Injectors",
                "Lazy-loaded routes instantiate child Environment Injectors scoped to the route boundary",
                "NullInjector resides at the top of the hierarchy, throwing NullInjectorError if tokens are missing"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_062",
            trackId = "angular_interview",
            conceptId = "ng_di_architecture",
            conceptName = "Dependency Injection & Hierarchical Injector Architecture",
            title = "Dependency Resolution Algorithm and NullInjectorError",
            question = "Step through the exact sequence Angular follows when resolving a requested dependency. When and why does `NullInjectorError` occur?",
            shortAnswer = "Resolution Sequence: 1) Element Hierarchy: Angular checks the requesting component's own `ElementInjector`. If not found, it bubbles up through parent component/directive `ElementInjectors` to the root DOM element. 2) Environment Hierarchy: If not found in the Element tree, resolution jumps to the current route's `EnvironmentInjector`. It bubbles up through parent route injectors to the Root Environment Injector (`providedIn: 'root'`). 3) Platform Hierarchy: Checks the `PlatformInjector` (shared across multiple apps on the same page). 4) NullInjector: The ultimate root. It always throws `NullInjectorError: No provider for Token!`. Resolution modifiers like `@SkipSelf()` or `@Host()` alter where the search begins and terminates.",
            keyPoints = listOf(
                "Resolution checks local ElementInjector first, bubbling up parent component DOM elements",
                "Switches to the EnvironmentInjector tree, traversing route injectors up to the root",
                "Checks PlatformInjector before reaching the terminal NullInjector",
                "NullInjector throws NullInjectorError if no provider satisfies the requested token",
                "Resolution modifiers (@Self, @SkipSelf, @Optional, @Host) customize traversal boundaries"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_063",
            trackId = "angular_interview",
            conceptId = "ng_di_architecture",
            conceptName = "Dependency Injection & Hierarchical Injector Architecture",
            title = "InjectionToken for Primitives, Interfaces, and Configuration",
            question = "Why can't TypeScript interfaces be used as DI provider tokens in Angular? How does `InjectionToken<T>` solve this?",
            shortAnswer = "TypeScript interfaces exist ONLY at compile-time and are completely erased during compilation to JavaScript (type erasure). At runtime, an interface has zero JavaScript representation, so Angular cannot use an interface as a runtime Map key to look up providers in the injector. `InjectionToken<T>` creates a real runtime JavaScript object that acts as a unique lookup key while retaining compile-time TypeScript type safety: `export const API_CONFIG = new InjectionToken<ApiConfig>('api.config', { providedIn: 'root', factory: () => ({ baseUrl: 'https://api.example.com' }) });`. Usage: `inject(API_CONFIG)` or `@Inject(API_CONFIG) config: ApiConfig`. Ideal for API configurations, feature flags, and primitive values.",
            keyPoints = listOf(
                "TypeScript interfaces are erased at compile time and do not exist at runtime in JavaScript",
                "Angular DI requires runtime tokens (classes, functions, or objects) as lookup keys in its provider map",
                "InjectionToken<T> provides a runtime object instance that carries static TypeScript type definitions",
                "Supports tree-shakable default factories via `{ providedIn: 'root', factory: () => ... }`",
                "Essential for injecting string URLs, configuration objects, and interface contracts"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_064",
            trackId = "angular_interview",
            conceptId = "ng_di_architecture",
            conceptName = "Dependency Injection & Hierarchical Injector Architecture",
            title = "Provider Recipes: useClass vs useExisting vs useValue vs useFactory",
            question = "Compare the 4 provider recipes in Angular: `useClass`, `useExisting`, `useValue`, and `useFactory`. When is each pattern appropriate?",
            shortAnswer = "1) `useClass`: Instantiates a new instance of the specified class: `{ provide: LoggerService, useClass: ProductionLoggerService }`. 2) `useExisting`: Aliases an existing token to another already-registered token WITHOUT creating a second instance: `{ provide: MinimalLogger, useExisting: LoggerService }`. Both tokens resolve to the exact same singleton instance. 3) `useValue`: Injects a static runtime value, object, or primitive: `{ provide: API_URL, useValue: 'https://api.com' }`. Cannot execute runtime logic. 4) `useFactory`: Executes a factory function to compute the dependency dynamically, allowing conditional instantiation or dependency injection via `deps` array or `inject()`: `{ provide: AuthService, useFactory: (http: HttpClient) => isMock ? new MockAuth() : new RealAuth(http), deps: [HttpClient] }`.",
            keyPoints = listOf(
                "useClass instantiates a fresh instance of the specified implementation class",
                "useExisting creates an alias to an existing token without instantiating duplicate instances",
                "useValue injects static objects, configuration constants, or primitive values directly",
                "useFactory executes a dynamic function to construct dependencies based on runtime conditions",
                "useFactory accepts a `deps` array or leverages modern `inject()` for internal dependencies"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_065",
            trackId = "angular_interview",
            conceptId = "ng_di_architecture",
            conceptName = "Dependency Injection & Hierarchical Injector Architecture",
            title = "Tree-Shakable Providers: providedIn 'root' vs 'platform'",
            question = "How does `@Injectable({ providedIn: 'root' })` enable tree-shaking? What is `providedIn: 'platform'` used for?",
            shortAnswer = "Legacy providers were registered in an NgModule's `providers: [MyService]` array. Because the NgModule directly referenced the service class, build tools (Webpack) could never remove the service even if no component ever used it. Tree-Shakable Providers (`providedIn: 'root'`): The dependency points 'inwards' to the root rather than modules pointing to the service. If no component or service imports or calls `inject(MyService)`, bundlers detect zero references and completely tree-shake the service out of the production JavaScript bundle! `providedIn: 'platform'`: Instantiates the singleton in the `PlatformInjector` above the application root. This singleton is shared across MULTIPLE Angular micro-frontend applications running concurrently on the same browser page.",
            keyPoints = listOf(
                "providedIn: 'root' registers providers in the root injector without explicit module declarations",
                "Inverts dependency references, allowing bundlers to tree-shake unused services completely",
                "Guarantees a single application-wide singleton instance across all components and routes",
                "providedIn: 'platform' shares services across multiple independent Angular apps on a single page",
                "Platform services are ideal for shared cross-application singletons (e.g. shared tracking script)"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_066",
            trackId = "angular_interview",
            conceptId = "ng_di_architecture",
            conceptName = "Dependency Injection & Hierarchical Injector Architecture",
            title = "Component-Scoped Providers: providers vs viewProviders",
            question = "What is the critical distinction between `providers` and `viewProviders` in `@Component` metadata? How do they affect projected `<ng-content>`?",
            shortAnswer = "Both `providers` and `viewProviders` create a new instance of a service scoped to the component's `ElementInjector` (destroyed when the component destroys). The Difference lies in Content Projection: 1) `providers`: The provided service is visible to BOTH the component's internal view template AND any projected content passed into `<ng-content>`. 2) `viewProviders`: The provided service is visible ONLY to the component's own direct view template; it is completely HIDDEN from projected child components in `<ng-content>`. Use Case: When authoring a reusable UI component (like an accordion or form container) that provides a internal state service, use `viewProviders` to prevent projected external user components from accidentally injecting or overriding your component's internal state.",
            keyPoints = listOf(
                "Both create component-level ElementInjector singletons destroyed with the component",
                "providers makes services accessible to both internal template and projected `<ng-content>` children",
                "viewProviders restricts access strictly to the component's direct view template",
                "Projected content passed from parent templates cannot inject services declared in viewProviders",
                "viewProviders protects internal component state from accidental leakage to projected child nodes"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_067",
            trackId = "angular_interview",
            conceptId = "ng_di_architecture",
            conceptName = "Dependency Injection & Hierarchical Injector Architecture",
            title = "Resolution Modifiers: @Optional, @Self, @SkipSelf, and @Host",
            question = "Explain how the resolution modifiers `@Optional()`, `@Self()`, `@SkipSelf()`, and `@Host()` alter Angular's dependency resolution traversal.",
            shortAnswer = "1) `@Optional()`: If the dependency is not found anywhere, returns `null` instead of throwing `NullInjectorError`. 2) `@Self()`: Searches ONLY the requesting component's own immediate `ElementInjector`. If not provided on this exact element, throws an error immediately without bubbling up. 3) `@SkipSelf()`: Skips the requesting component's own `ElementInjector` and starts searching from the PARENT component upwards. Crucial when a component provides a service and also wants to inject its parent's instance of that same service (e.g. nested tree nodes). 4) `@Host()`: Restricts the search to the host component element (the boundary of the component's template). Directives on an element use `@Host()` to ensure a service is provided by their enclosing host component.",
            keyPoints = listOf(
                "@Optional prevents NullInjectorError by returning null when dependencies are missing",
                "@Self restricts search strictly to the local element's injector, forbidding parent traversal",
                "@SkipSelf skips the current element and begins resolution from the parent injector upwards",
                "@SkipSelf is essential for recursive structures (nested trees) injecting parent instances",
                "@Host bounds dependency resolution to the nearest enclosing component template boundary"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_068",
            trackId = "angular_interview",
            conceptId = "ng_di_architecture",
            conceptName = "Dependency Injection & Hierarchical Injector Architecture",
            title = "The Modern inject() Function vs Constructor Injection",
            question = "Why does modern Angular prefer the `inject()` function over constructor injection? How does it simplify class inheritance and functional composition?",
            shortAnswer = "Constructor Injection Limitations: If a base class has 5 injected services (`constructor(a, b, c, d, e)`), every extending child class must declare all 5 services and pass them through `super(a, b, c, d, e)`, creating massive boilerplate and fragile inheritance refactoring. Modern `inject()` API: 1) Clean Inheritance: Base classes inject directly in field initializers (`private http = inject(HttpClient)`). Child classes don't need constructors or `super()` forwarding! 2) Functional Composition: Enables writing reusable standalone utility functions that inject services (e.g. `const user\$ = injectCurrentUser()`). 3) Injection Context Requirement: `inject()` can ONLY be called during construction phases (constructor, field initializer, factory function) or inside `runInInjectionContext()`.",
            keyPoints = listOf(
                "inject() replaces constructor parameter injection with clean functional field initializers",
                "Completely eliminates tedious `super()` parameter forwarding in derived class hierarchies",
                "Enables functional composition helpers (e.g. custom observable factories injecting services)",
                "Must be executed within an active Injection Context (constructor or runInInjectionContext)",
                "Standardizes dependency injection ergonomics across functions, guards, and classes"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_069",
            trackId = "angular_interview",
            conceptId = "ng_di_architecture",
            conceptName = "Dependency Injection & Hierarchical Injector Architecture",
            title = "Multi-Providers (multi: true) and Plugin Architectures",
            question = "How do Multi-Providers (`multi: true`) work in Angular DI? How do they enable extensible plugin architectures like `HTTP_INTERCEPTORS`?",
            shortAnswer = "By default, providing a token a second time OVERWRITES the previous provider. When an `InjectionToken` is configured with `multi: true`, Angular instead creates an ARRAY of all provided values: `{ provide: VALIDATORS, useClass: CustomValidatorA, multi: true }, { provide: VALIDATORS, useClass: CustomValidatorB, multi: true }`. When injected via `inject(VALIDATORS)` or `@Inject(VALIDATORS)`, Angular injects an array `[CustomValidatorA, CustomValidatorB]`. Plugin Architecture: Core frameworks define an open hook token (like `HTTP_INTERCEPTORS` or `APP_INITIALIZER`). Independent feature modules or libraries provide their own implementations with `multi: true` without knowing about each other. The core engine simply iterates over the injected array to execute all registered plugins.",
            keyPoints = listOf(
                "Default providers overwrite previous registrations for identical injection tokens",
                "`multi: true` instructs Angular to collect all registrations into an aggregated array",
                "Injecting the token returns an array containing all registered provider instances in order",
                "Powers extensible framework hooks like HTTP_INTERCEPTORS, APP_INITIALIZER, and custom validators",
                "Enables decoupled modular plugin architectures where features register behaviors independently"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_070",
            trackId = "angular_interview",
            conceptId = "ng_di_architecture",
            conceptName = "Dependency Injection & Hierarchical Injector Architecture",
            title = "Factory Providers and Dynamic Dependency Resolution",
            question = "How do you configure a factory provider (`useFactory`) with runtime dependencies? Compare modern `inject()` inside factory functions with the legacy `deps` array.",
            shortAnswer = "Legacy Factory Pattern: Required declaring a `deps` array listing token dependencies in exact parameter order: `{ provide: DataService, useFactory: (http: HttpClient, config: AppConfig) => new DataService(http, config), deps: [HttpClient, APP_CONFIG] }`. Fragile: Renaming or reordering parameters in `deps` breaks runtime instantiation silently. Modern Functional Factory Pattern (Angular 14+): The factory function runs inside an active Injection Context, allowing direct calls to `inject()`: `{ provide: DataService, useFactory: () => { const http = inject(HttpClient); const config = inject(APP_CONFIG); return config.useMock ? new MockDataService() : new RealDataService(http); } }`. Cleaner, fully type-safe, eliminates the `deps` array entirely, and supports conditional injection.",
            keyPoints = listOf(
                "Factory providers construct complex dependencies dynamically based on runtime logic",
                "Legacy approach required maintaining a synchronized `deps` array of injection tokens",
                "Modern factory functions execute inside an active Injection Context, allowing direct `inject()` calls",
                "Eliminates parameter ordering errors and type mismatches associated with the deps array",
                "Allows conditional dependency injection and environment-based service swapping"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_071",
            trackId = "angular_interview",
            conceptId = "ng_di_architecture",
            conceptName = "Dependency Injection & Hierarchical Injector Architecture",
            title = "Circular Dependency Resolution with forwardRef()",
            question = "What causes circular dependency errors in Angular DI, and how does `forwardRef()` resolve them? Why should circular dependencies be refactored?",
            shortAnswer = "Cause: Class A imports and injects Class B, while Class B imports and injects Class A. At runtime, when JavaScript parses Class A, Class B is still `undefined` (not yet evaluated), causing `ReferenceError: Cannot access 'ClassB' before initialization`. How `forwardRef(() => ClassB)` Works: `forwardRef` takes a closure callback returning the class reference. JavaScript closures defer the evaluation of `ClassB` until Angular DI actually executes at runtime, by which time both classes have been fully loaded and defined. Architectural Debt: While `forwardRef()` fixes the runtime crash, circular dependencies indicate bad architectural coupling (violates Single Responsibility and Dependency Inversion). Proper Solution: Extract shared logic or interfaces into a third independent service (Class C) that both A and B inject.",
            keyPoints = listOf(
                "Circular dependencies occur when two classes mutually import and inject each other",
                "JavaScript module loading leaves the second class undefined during initial evaluation",
                "`forwardRef(() => Class)` defers class reference evaluation until runtime injection execution",
                "Using forwardRef masks architectural coupling and violates clean modular design principles",
                "Proper fix: Refactor shared dependencies into an intermediary service to break the circular graph"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_072",
            trackId = "angular_interview",
            conceptId = "ng_di_architecture",
            conceptName = "Dependency Injection & Hierarchical Injector Architecture",
            title = "Asynchronous App Bootstrapping: APP_INITIALIZER vs ENVIRONMENT_INITIALIZER",
            question = "Compare `APP_INITIALIZER` with `ENVIRONMENT_INITIALIZER` in modern standalone Angular applications. How do you block app startup until configs load?",
            shortAnswer = "1) `APP_INITIALIZER`: Executes a factory function during application bootstrap. If the factory returns a `Promise` or an `Observable`, Angular BLOCKS application initialization until the promise resolves or observable completes! Used to load essential runtime configurations (`config.json`), authenticate sessions, or pre-fetch translations before any component renders: `{ provide: APP_INITIALIZER, useFactory: (cfg: ConfigService) => () => cfg.loadSettings(), deps: [ConfigService], multi: true }`. 2) `ENVIRONMENT_INITIALIZER`: Runs when an `EnvironmentInjector` (either root or a lazy-loaded route) is instantiated. Does NOT block bootstrap with asynchronous promises; designed for synchronous setup logic (e.g. registering icons, initializing analytics listeners) inside standalone route boundaries.",
            keyPoints = listOf(
                "APP_INITIALIZER blocks application bootstrap until returned Promises or Observables resolve",
                "Guarantees critical runtime configuration (APIs, auth tokens) is loaded before rendering",
                "Multiple APP_INITIALIZER functions run concurrently using multi: true providers",
                "ENVIRONMENT_INITIALIZER executes synchronous setup logic upon EnvironmentInjector creation",
                "ENVIRONMENT_INITIALIZER is ideal for initializing standalone route-level libraries and icons"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_073",
            trackId = "angular_interview",
            conceptId = "ng_di_architecture",
            conceptName = "Dependency Injection & Hierarchical Injector Architecture",
            title = "Isolating State across Component Instances with Element Injector",
            question = "How do you build complex compound components (e.g. Tabs or Accordions) where each parent instance maintains isolated state for its children?",
            shortAnswer = "Architecture: Use the Element Injector to create an isolated state container per instance: 1) State Service: Create `AccordionStateService` (WITHOUT `providedIn: 'root'`). 2) Parent Provider: In `<app-accordion>`, declare `providers: [AccordionStateService]`. Because it is in the component's `providers`, Angular instantiates a NEW, separate instance of `AccordionStateService` for every `<app-accordion>` on the page. 3) Child Injection: Child components (`<app-accordion-item>`) inject `AccordionStateService`. Angular's hierarchical resolution bubbles up the DOM element tree and injects their specific parent accordion's service instance. Multiple accordions on the same page operate completely independently with zero shared state or cross-talk.",
            keyPoints = listOf(
                "Omitting `providedIn: 'root'` allows services to be scoped locally to component instances",
                "Declaring services in a component's `providers` array creates an isolated ElementInjector instance",
                "Child components automatically resolve their nearest parent component's service instance",
                "Allows multiple independent widget instances (tabs, dialogs, accordions) on a single screen",
                "Each service instance is automatically garbage collected when its parent component is destroyed"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_074",
            trackId = "angular_interview",
            conceptId = "ng_di_architecture",
            conceptName = "Dependency Injection & Hierarchical Injector Architecture",
            title = "Route-Scoped Providers in Modern Standalone Routing",
            question = "How do you scope a service singleton strictly to a feature route in modern Angular? What happens when navigating away from that route?",
            shortAnswer = "In standalone routing, declare providers in the route definition: `{ path: 'checkout', loadComponent: () => import('./checkout.component'), providers: [CheckoutService, CartPaymentService] }`. Mechanics: 1) Scoped Environment Injector: When the user navigates to `/checkout`, Angular creates a child `EnvironmentInjector` dedicated to this route branch, instantiating `CheckoutService` as a singleton shared among `checkout.component` and all its child routes. 2) Automatic Teardown: When the user navigates away from `/checkout`, Angular completely DESTROYS the route's child Environment Injector! `CheckoutService` is destroyed, its `DestroyRef.onDestroy` hooks fire, and memory is garbage collected. Re-entering the route creates a pristine new instance.",
            keyPoints = listOf(
                "Route definitions accept a `providers` array in standalone routing configurations",
                "Creates a child EnvironmentInjector scoped strictly to that route subtree",
                "Service instances are shared as singletons across all child routes and components within that branch",
                "Angular automatically destroys the route EnvironmentInjector when the user navigates away",
                "Cleans up service state, unsubscribes active streams, and frees memory automatically"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_075",
            trackId = "angular_interview",
            conceptId = "ng_di_architecture",
            conceptName = "Dependency Injection & Hierarchical Injector Architecture",
            title = "Platform Injector and Multi-App Bootstrapping",
            question = "What is the `PlatformInjector`? In what micro-frontend or multi-app architecture scenarios is it utilized?",
            shortAnswer = "The `PlatformInjector` is the parent of all Root Application Injectors. When calling `bootstrapApplication()`, Angular first initializes a single shared platform injector (`getPlatform()` or `platformBrowser()`). Multi-App Scenario: In micro-frontends or hybrid enterprise portals where multiple Angular applications are bootstrapped onto the same HTML document (e.g. HeaderApp and MainContentApp): 1) Each app has its own isolated `RootInjector` (separate state, router, and components). 2) Both apps share the SAME underlying `PlatformInjector`. Services configured with `@Injectable({ providedIn: 'platform' })` are instantiated ONCE in the platform injector and shared across all running applications on the page, enabling seamless cross-application token sharing, telemetry, or single sign-on synchronization.",
            keyPoints = listOf(
                "PlatformInjector sits above application-level RootInjectors in the DI hierarchy",
                "Initialized once when the Angular browser platform starts up",
                "Services declared with `providedIn: 'platform'` become singletons across all apps on the page",
                "Enables multi-app micro-frontend portals to share global singleton services (SSO, telemetry)",
                "Prevents duplicate script initialization and coordinates cross-application browser events"
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_ng_076",
            trackId = "angular_interview",
            conceptId = "ng_di_architecture",
            conceptName = "Dependency Injection & Hierarchical Injector Architecture",
            title = "Overriding Providers in Unit Tests with TestBed",
            question = "How do you mock or override dependencies in Angular unit tests using `TestBed.overrideComponent()` and `TestBed.overrideProvider()`?",
            shortAnswer = "1) Overriding Root Services: Provide a mock implementation directly in `TestBed.configureTestingModule({ providers: [{ provide: UserService, useValue: mockUserService }] })`. 2) Overriding Component-Level Providers: If a component declares `providers: [LocalService]` in its `@Component` decorator, testing module providers CANNOT override it because the component's ElementInjector takes precedence. Solution: Use `TestBed.overrideComponent`: `TestBed.overrideComponent(UserProfileComponent, { set: { providers: [{ provide: LocalService, useValue: mockLocalService }] } });`. 3) Modern Standalone Testing: In Angular 14+, you can pass overrides directly into `createComponent` or use `TestBed.inject()` to verify mock method calls cleanly.",
            keyPoints = listOf(
                "TestBed.configureTestingModule overrides root and module-level providers",
                "Component-level providers in ElementInjectors override TestBed providers by default",
                "`TestBed.overrideComponent()` replaces component-level providers with mock test doubles",
                "Supports replacing real implementations with lightweight Jasmine/Jest spies or mocks",
                "`TestBed.inject(Service)` retrieves typed references to verify spy assertions"
            ),
            difficulty = "Senior"
        )
    )
    private fun part5(): List<InterviewQuestion> = listOf(
        InterviewQuestion(
            id = "iq_ng_077",
            trackId = "angular_interview",
            conceptId = "ng_di_architecture",
            conceptName = "Dependency Injection & Hierarchical Injector Architecture",
            title = "White-Label Multi-Tenant Customization via DI Service Swapping",
            question = "How can an enterprise white-label application dynamically swap business logic or theme services for different enterprise clients using Angular DI?",
            shortAnswer = "Enterprise Pattern: Decouple client-specific business rules behind an abstract class or injection token: `export abstract class TaxCalculatorService { abstract calculate(total: number): number; }`. Standard components inject the abstract class: `private taxCalc = inject(TaxCalculatorService)`. Tenant Customization: In `app.config.ts`, conditionally provide tenant implementations based on domain or build configuration: `{ provide: TaxCalculatorService, useClass: environment.tenant === 'UK' ? UkTaxCalculatorService : UsTaxCalculatorService }`. Benefits: Core component code never changes; client-specific logic is isolated in dedicated classes; bundlers tree-shake unused tenant classes for client-specific builds.",
            keyPoints = listOf(
                "Abstract classes act as both compile-time interface contracts and runtime injection tokens",
                "Core components programmatically inject the abstract base class without concrete coupling",
                "Tenant-specific implementations are swapped at the bootstrap provider level via useClass",
                "Decouples enterprise tenant customizations from core application component codebases",
                "Enables clean white-label multi-tenant architectures and conditional feature deployments"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_078",
            trackId = "angular_interview",
            conceptId = "ng_di_architecture",
            conceptName = "Dependency Injection & Hierarchical Injector Architecture",
            title = "Memory Lifecycle of Root Singletons vs Component-Scoped Services",
            question = "Trace the memory lifecycle and garbage collection behavior of a service provided in `root` vs a service provided in a `@Component`'s `providers`.",
            shortAnswer = "1) Root Singleton (`providedIn: 'root'`): Instantiated lazily on first injection and retained in the Root `EnvironmentInjector`'s internal instances map. Because the root injector lives for the ENTIRE duration of the browser tab session, root services are NEVER garbage collected until the user closes or refreshes the tab. Warning: Storing large cache arrays in root services without eviction causes permanent memory bloat. 2) Component-Scoped Service (`@Component({ providers: [Service] })`): Instantiated when the component element is mounted into the DOM. Its instance is stored in that element's `ElementInjector`. When the component is removed from the DOM, Angular destroys its ElementInjector, dereferences the service, and triggers `DestroyRef.onDestroy` callbacks. The service instance becomes eligible for immediate browser garbage collection.",
            keyPoints = listOf(
                "Root singletons remain pinned in root injector memory for the lifetime of the application session",
                "Accumulating data in root services without eviction policies causes progressive memory leaks",
                "Component-scoped services are bound to the lifecycle of the component's DOM ElementInjector",
                "Destroying the component severs references to the service, triggering DestroyRef teardown",
                "Enables browser garbage collection to reclaim memory automatically upon route transitions"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_079",
            trackId = "angular_interview",
            conceptId = "ng_di_architecture",
            conceptName = "Dependency Injection & Hierarchical Injector Architecture",
            title = "Type-Safe Feature Configuration Pattern with Factory Providers",
            question = "How do you design a type-safe `provideFeature(config)` function in standalone Angular to configure third-party libraries cleanly?",
            shortAnswer = "Modern Angular libraries configure features via standalone provider functions (e.g. `provideRouter()`, `provideHttpClient()`). Pattern: 1) Config Token: `export const FEATURE_CONFIG = new InjectionToken<FeatureConfig>('FEATURE_CONFIG');`. 2) Provider Function: `export function provideMyFeature(config: Partial<FeatureConfig>): EnvironmentProviders { return makeEnvironmentProviders([ { provide: FEATURE_CONFIG, useValue: { ...defaultConfig, ...config } }, FeatureService ]); }`. 3) `makeEnvironmentProviders`: Wraps providers into an `EnvironmentProviders` type, ensuring the compiler prevents users from accidentally providing these services inside component-level `@Component.providers` (enforcing environment-level registration).",
            keyPoints = listOf(
                "Standardizes library configuration using functional `provideX(options)` conventions",
                "Merges user-provided options with default configuration objects inside a private InjectionToken",
                "`makeEnvironmentProviders()` encapsulates providers strictly for environment injectors",
                "Prevents developers from mistakenly registering environment-level services inside component providers",
                "Follows modern Angular 15+ architectural idioms established by provideRouter and provideHttpClient"
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_ng_080",
            trackId = "angular_interview",
            conceptId = "ng_di_architecture",
            conceptName = "Dependency Injection & Hierarchical Injector Architecture",
            title = "Service Anti-Patterns: State Pollution in Root Singletons",
            question = "Why is storing view-specific mutable state in `@Injectable({ providedIn: 'root' })` services a dangerous anti-pattern? How do you remediate it?",
            shortAnswer = "The Anti-Pattern: Storing transient view state (e.g. `selectedUserId`, `activeFilter`, `formState`) in a root singleton service. Consequences: 1) State Bleed: When User A visits `/edit-profile`, state is modified. If they navigate away to `/dashboard` and return to `/edit-profile`, the old state persists, showing stale, incorrect data. 2) Memory Leaks: Event subscriptions and cached data pinned to root services are never reclaimed. 3) Multi-Instance Bugs: If two modal windows open simultaneously, both share and corrupt the single root state. Remediation: 1) Move transient state into route-level `providers: [FeatureStateService]` or component-level `providers`. 2) If using root services, state must be tied to route parameters or explicitly reset on component unmount via `DestroyRef.onDestroy()`.",
            keyPoints = listOf(
                "Storing component-specific state in root singletons causes state pollution across navigation routes",
                "Returning to a screen displays stale data from previous sessions due to persistent singleton memory",
                "Multiple concurrent component instances overwrite and corrupt shared singleton variables",
                "Remedy: Scope state services to route boundaries or component ElementInjectors",
                "Enforces automatic state reset and resource cleanup when components leave the DOM"
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_081",
            trackId = "angular_interview",
            conceptId = "ng_state_management",
            conceptName = "State Management & SignalStore",
            title = "NgRx Global Store Architecture and Unidirectional Data Flow",
            question = "Explain the architectural layers of NgRx Store. How does the unidirectional data flow cycle operate from Action dispatch to View rerender, and what are the benefits of immutable state containers in enterprise Angular applications?",
            shortAnswer = "NgRx Store implements the Redux pattern tailored with RxJS for Angular. The cycle begins when a Component dispatches an Action (a plain object with a 'type' string and optional 'payload'). Reducers (pure functions) take the current state and action to compute a new immutable state tree. Selectors (memoized pure functions) extract and project slices of state to components as Observables or Signals. Side effects (API calls, route navigation) are isolated in NgRx Effects, which listen to dispatched Actions, perform async tasks, and dispatch result Actions. Benefits include deterministic state transitions, time-travel debugging via Redux DevTools, decoupled business logic, and high performance with OnPush change detection.",
            keyPoints = listOf(
                "Defines NgRx Action as a unique event descriptor with type and payload.",
                "Explains Reducers as pure, side-effect-free functions returning new immutable state.",
                "Describes Selectors as memoized query functions slicing and transforming state.",
                "Details NgRx Effects as RxJS stream listeners handling async tasks and side effects.",
                "Highlights enterprise advantages: predictability, debuggability, and OnPush integration."
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_082",
            trackId = "angular_interview",
            conceptId = "ng_state_management",
            conceptName = "State Management & SignalStore",
            title = "NgRx createAction, createReducer, and on Handlers",
            question = "How do modern NgRx creator functions (`createAction`, `createReducer`, `on`, `props`) enhance type safety and eliminate boilerplate compared to legacy switch-case action reducers?",
            shortAnswer = "Modern NgRx utilizes creator functions to enforce end-to-end TypeScript inference. `createAction('[Source] Event', props<{ id: string }>())` creates typed action creators where payload shapes are strictly typed without manual interface casting. `createReducer(initialState, on(myAction, (state, { id }) => ({ ...state, id })))` replaces error-prone switch-case statements with clean, mapped action handlers. TypeScript validates that every property destructured in `on()` matches the action's props and ensures the reducer's return value conforms to the defined State interface. This prevents silent runtime bugs caused by misspelled action constants or missing default branches.",
            keyPoints = listOf(
                "Contrasts modern creator functions with legacy string enums and switch-case blocks.",
                "Explains createAction and props<T>() for strict payload type checking.",
                "Explains createReducer with on() handlers for declarative state updates.",
                "Highlights automatic state and payload type inference in reducer handlers.",
                "Identifies prevention of common runtime errors like action name collision or missing breaks."
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_083",
            trackId = "angular_interview",
            conceptId = "ng_state_management",
            conceptName = "State Management & SignalStore",
            title = "NgRx Selectors: createSelector and Memoization Invariants",
            question = "How does `createSelector` implement memoization in NgRx? Under what conditions does a selector recalculate its projection function, and how can memoization be accidentally broken by reference mutations?",
            shortAnswer = "`createSelector` implements memoization by caching its last calculated result based on strict equality (===) comparison of its input selector arguments. When the store updates, if the slices returned by the input selectors have not changed references, the projection function is skipped and the cached reference is returned immediately. Memoization breaks if reducers mutate existing state objects in place instead of creating shallow clones (causing input selector references to appear unchanged despite mutations) or if projection functions return newly instantiated object/array literals when nothing changed, causing downstream dependent selectors to needlessly recompute and trigger view rerenders.",
            keyPoints = listOf(
                "Explains memoization mechanism relying on reference equality (===) of input arguments.",
                "Describes conditions for recalculation: only when at least one input selector produces a new reference.",
                "Explains the performance impact on Angular OnPush components when cached values are reused.",
                "Identifies the danger of in-place state mutation bypassing selector re-evaluation.",
                "Discusses parameterized selectors and selector factory caching implications."
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_ng_084",
            trackId = "angular_interview",
            conceptId = "ng_state_management",
            conceptName = "State Management & SignalStore",
            title = "NgRx Effects: Stream Lifecycle and Non-Dispatching Actions",
            question = "Explain how NgRx `createEffect` manages asynchronous pipelines. Why must an effect stream never error out uncaught, and how do you configure non-dispatching effects (`dispatch: false`)?",
            shortAnswer = "NgRx `createEffect` subscribes to the Actions stream, applies RxJS flattening operators (e.g., `switchMap`, `concatMap`) to invoke async services, and automatically dispatches the emitted action back to the store. If an error is thrown and not caught inside the inner flattening observable, the entire outer Actions stream terminates, permanently disabling the effect for future actions. Errors must always be handled with `catchError` inside the inner stream to return an error action (e.g., `loadFailed`). Non-dispatching effects are configured via `{ dispatch: false }` in `createEffect`, commonly used for side effects like navigation, toast logging, or analytics tracking.",
            keyPoints = listOf(
                "Explains the role of createEffect in handling async operations and side effects.",
                "Describes outer Actions stream vs inner observable execution pipeline.",
                "Explains why uncaught errors in the outer stream terminate the effect permanently.",
                "Details how inner catchError emitting a failure action preserves stream liveness.",
                "Explains { dispatch: false } configuration for non-state-dispatching operations like routing."
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_ng_085",
            trackId = "angular_interview",
            conceptId = "ng_state_management",
            conceptName = "State Management & SignalStore",
            title = "NgRx SignalStore: Architecture and signalStore Core Features",
            question = "What is the NgRx SignalStore introduced in recent Angular versions? How does it unify signal-based reactivity with declarative state management using `signalStore`, `withState`, and `withMethods`?",
            shortAnswer = "NgRx SignalStore is a lightweight, fully reactive state management library built natively on Angular Signals. Defined using `signalStore()`, it composes state, computed properties, and methods into a single typed service without RxJS boilerplate. `withState({ users: [], loading: false })` initializes reactive state slices as Signals. `withComputed(({ users }) => ({ count: computed(() => users().length) }))` derives reactive values. `withMethods((store, userService = inject(UserService)) => ({ loadUsers: rxMethod<void>(pipe(...)) }))` defines state mutators and async workflows. SignalStore supports local component scoping or root injection, eliminating actions/reducers while preserving strict unidirectional typing.",
            keyPoints = listOf(
                "Defines NgRx SignalStore as a native Signal-based state container without Redux boilerplate.",
                "Explains withState for declaring reactive signal properties with auto-generated getters.",
                "Explains withComputed for defining derived signal properties.",
                "Explains withMethods for defining synchronous updates and async operations.",
                "Highlights advantages: reduced boilerplate, zero-action simplicity, and flexible scoping."
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_086",
            trackId = "angular_interview",
            conceptId = "ng_state_management",
            conceptName = "State Management & SignalStore",
            title = "patchState in NgRx SignalStore and Deep Immutability",
            question = "How does `patchState` work in NgRx SignalStore? How does it handle partial updates, updater functions, and ensure immutability compared to direct signal assignment?",
            shortAnswer = "`patchState` is the primary state-mutation utility in NgRx SignalStore. It accepts the store instance followed by one or more partial state objects or updater functions: `patchState(store, (state) => ({ count: state.count + 1 }))` or `patchState(store, { loading: true })`. It shallowly merges the updates into the state tree and triggers fine-grained signal notifications. By enforcing updates through `patchState`, direct mutations to state references are prevented, ensuring that all dependent `computed()` signals and template bindings accurately register changes without stale state anomalies.",
            keyPoints = listOf(
                "Explains the signature and usage of patchState with partial objects and updater functions.",
                "Details how patchState performs atomic, sequential state transitions.",
                "Explains why direct assignment (e.g. store.users.set()) is discouraged or restricted.",
                "Describes how patchState triggers fine-grained signal dependency recalculations.",
                "Compares patchState convenience against traditional Redux reducer object spread patterns."
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_087",
            trackId = "angular_interview",
            conceptId = "ng_state_management",
            conceptName = "State Management & SignalStore",
            title = "Custom Features and withEntities in NgRx SignalStore",
            question = "How do you create reusable custom features in NgRx SignalStore using `signalStoreFeature`? How does `withEntities` provide out-of-the-box normalized CRUD collections?",
            shortAnswer = "`signalStoreFeature` enables composing reusable state slices, methods, and hooks across multiple stores. For example, a custom `withLoading()` feature can bundle `{ loading: signal(false) }` and `setLoading(val: boolean)` methods. Furthermore, `@ngrx/signals/entities` provides `withEntities<T>()`, which automatically normalizes collections into `{ ids: Signal<EntityId[]>, entityMap: Signal<Record<EntityId, T>>, entities: Signal<T[]> }`. It includes built-in entity updaters like `setAllEntities`, `addEntity`, `removeEntity`, and `updateEntity`, delivering normalized O(1) lookups and standard CRUD manipulation without hand-written dictionary logic.",
            keyPoints = listOf(
                "Explains signalStoreFeature for modular, composable state slice definitions.",
                "Describes withEntities for normalizing collections into ids array and entityMap record.",
                "Identifies entity updaters: setAllEntities, addEntity, removeEntity, and updateEntity.",
                "Explains O(1) entity lookup benefits over flat array iterations for large datasets.",
                "Demonstrates composing multiple features: signalStore(withEntities<User>(), withLoading())."
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_ng_088",
            trackId = "angular_interview",
            conceptId = "ng_state_management",
            conceptName = "State Management & SignalStore",
            title = "NgRx rxMethod: Bridging Reactive RxJS Streams into SignalStore",
            question = "What is `rxMethod` in NgRx SignalStore? How does it bridge RxJS observable streams (e.g., debounced search inputs, WebSocket feeds) into SignalStore methods while managing subscriptions?",
            shortAnswer = "`rxMethod<T>` is a utility in NgRx SignalStore that transforms an RxJS observable pipeline into a callable method that accepts static values, Observables, or Signals as arguments. When invoked, `rxMethod` dynamically pipes the incoming reactive values through an RxJS operator chain (e.g., `pipe(debounceTime(300), distinctUntilChanged(), switchMap(apiCall))`). Crucially, `rxMethod` is lifecycle-aware: it automatically binds to the current injection context and destroys its underlying subscriptions when the enclosing component or injector is destroyed, preventing memory leaks without manual `takeUntilDestroyed` boilerplate.",
            keyPoints = listOf(
                "Defines rxMethod as a reactive bridge converting RxJS pipelines into invocable functions.",
                "Explains that rxMethod can take static values, Observables, or Signals as arguments.",
                "Describes use cases: async API calls with switchMap, debounced auto-search, polling.",
                "Details automatic lifecycle subscription cleanup tied to the host InjectionContext.",
                "Compares rxMethod against traditional NgRx Effects for local and feature stores."
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_ng_089",
            trackId = "angular_interview",
            conceptId = "ng_state_management",
            conceptName = "State Management & SignalStore",
            title = "NgRx Entity: Normalization, EntityAdapter, and CRUD Operations",
            question = "Explain the architecture of `@ngrx/entity`. How does `createEntityAdapter` normalize relational data, and how do adapter methods like `upsertMany` and `selectId` optimize state operations?",
            shortAnswer = "`@ngrx/entity` provides an API to manage normalized collections in NgRx Global Store, storing entities as `{ ids: (string | number)[], entities: { [id: string]: T } }`. `createEntityAdapter<T>({ selectId: item => item.uuid, sortComparer: (a, b) => a.name.localeCompare(b.name) })` generates prebuilt reducer adapters and memoized selectors. Adapter methods like `addOne`, `setAll`, `removeMany`, and `upsertMany` handle immutable collection updates without manual object cloning loops. By maintaining an indexed dictionary (`entities`), lookups and targeted updates run in O(1) time complexity, avoiding expensive O(N) array scans.",
            keyPoints = listOf(
                "Explains normalized structure: ids array preserving order and entities lookup hashmap.",
                "Describes createEntityAdapter configuration with selectId and sortComparer.",
                "Lists key adapter reducer methods: addOne, upsertOne, removeOne, setAll, updateMany.",
                "Explains generated adapter selectors: selectAll, selectEntities, selectIds, selectTotal.",
                "Highlights O(1) access complexity and prevention of array scanning bottlenecks."
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_090",
            trackId = "angular_interview",
            conceptId = "ng_state_management",
            conceptName = "State Management & SignalStore",
            title = "NgRx ComponentStore vs Global Store: Scoping and Use Cases",
            question = "Compare NgRx ComponentStore with NgRx Global Store. When should an engineering team use ComponentStore or SignalStore over a centralized Global Store?",
            shortAnswer = "NgRx Global Store is a centralized singleton managing application-wide cross-cutting state (user session, global permissions, cart total) that outlives individual page navigations. It requires explicit actions, reducers, and global selectors. NgRx ComponentStore (and modern SignalStore) is a localized state management solution designed to be bound to a specific component subtree via its `providers` array. It is ideal for complex UI state (multi-step dialog wizards, paginated data grids, local filters). When the host component is destroyed, the ComponentStore/SignalStore instance is automatically garbage collected, preventing state pollution and eliminating global state bloat.",
            keyPoints = listOf(
                "Contrasts singleton application-wide global store with component-scoped local stores.",
                "Explains lifecycle tying: local stores are automatically garbage collected with host components.",
                "Delineates state types: shared server/session cache vs transient view/dialog/grid state.",
                "Highlights reduced boilerplate and localized encapsulation of ComponentStore/SignalStore.",
                "Provides a decision framework for selecting global vs local state containers."
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_091",
            trackId = "angular_interview",
            conceptId = "ng_state_management",
            conceptName = "State Management & SignalStore",
            title = "Optimistic UI Updates and Rollback Strategies in NgRx",
            question = "How do you implement optimistic UI updates in NgRx or SignalStore? How do you guarantee state rollback if the backend network mutation fails?",
            shortAnswer = "Optimistic UI updates immediately update local state before server confirmation to provide zero-latency user feedback. In NgRx, when an action like `deleteItem({ id })` is dispatched, the reducer immediately removes the item from state while saving the deleted item or previous state snapshot into an auxiliary holding property. The Effect triggers the backend HTTP DELETE. If the request succeeds, it dispatches `deleteItemSuccess` which clears the holding buffer. If the request fails, the Effect catches the error and dispatches `deleteItemFailure`, prompting the reducer to roll back by reinserting the item into state and triggering an error notification.",
            keyPoints = listOf(
                "Defines optimistic updating: mutating state immediately before network confirmation.",
                "Explains state backup mechanism: caching previous state or snapshot before mutation.",
                "Details reducer handling on failure: reverting state back to the original snapshot.",
                "Explains effect error handling: catching API errors and dispatching rollback actions.",
                "Discusses race condition handling when multiple optimistic mutations overlap."
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_ng_092",
            trackId = "angular_interview",
            conceptId = "ng_state_management",
            conceptName = "State Management & SignalStore",
            title = "NgRx Router Store and Navigation Serializer",
            question = "What is `@ngrx/router-store`? Why is a custom `RouterStateSerializer` essential to avoid circular reference serialization errors and performance degradation in Redux DevTools?",
            shortAnswer = "`@ngrx/router-store` synchronizes Angular Router navigation events into the NgRx state tree, enabling route parameters and query params to be selected directly within selectors and effects. By default, Angular's `RouterStateSnapshot` is a massive, deeply nested tree containing circular references to component instances and DOM elements. Attempting to store the raw snapshot crashes Redux DevTools serialization and causes massive memory bloat. A custom `CustomSerializer implements RouterStateSerializer<RouterStateUrl>` must be implemented to extract only lightweight primitives: `url`, `params`, and `queryParams`, ensuring clean, serializable store snapshots.",
            keyPoints = listOf(
                "Explains router-store purpose: syncing router navigation events with the NgRx store.",
                "Identifies issues with default RouterStateSnapshot: circular references, DOM links, memory bloat.",
                "Explains the implementation of custom RouterStateSerializer extracting url, params, queryParams.",
                "Describes benefits: time-travel debugging of route transitions in Redux DevTools.",
                "Explains how selectors use router state (e.g. selectRouteParam) to derive state."
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_ng_093",
            trackId = "angular_interview",
            conceptId = "ng_state_management",
            conceptName = "State Management & SignalStore",
            title = "NgRx Meta-Reducers: Logging, Hydration, and State Immutability Enforcement",
            question = "What are NgRx Meta-Reducers? How do they act as higher-order reducers, and how can they be used for localStorage state hydration and immutability verification?",
            shortAnswer = "Meta-reducers are higher-order functions that take a `ActionReducer<T>` and return a new `ActionReducer<T>`, wrapping the root reducer execution pipeline like middleware. When any action is dispatched, meta-reducers execute before and after slice reducers. Common use cases include: (1) State Hydration: reading persisted JSON from `localStorage` during app initialization to seed initial state and writing state updates back to storage; (2) Logging: outputting previous state, current action, and next state to the console in development; and (3) Immutability enforcement: deep-freezing state to catch runtime object mutation attempts (built into `@ngrx/store` runtime checks).",
            keyPoints = listOf(
                "Defines meta-reducer as a higher-order reducer intercepting action processing.",
                "Explains execution order relative to feature/root reducers.",
                "Demonstrates localStorage hydration meta-reducer for persistent offline state.",
                "Describes development-only meta-reducers (console logging, deep-freeze checks).",
                "Explains how meta-reducers are configured in provideStore(rootReducers, { metaReducers })."
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_ng_094",
            trackId = "angular_interview",
            conceptId = "ng_state_management",
            conceptName = "State Management & SignalStore",
            title = "NgRx Runtime Checks and Store Freeze Invariants",
            question = "What runtime checks does NgRx provide out of the box (`strictStateImmutability`, `strictActionImmutability`, `strictStateSerializability`), and why should they be active during development?",
            shortAnswer = "NgRx provides built-in development runtime checks configured in `provideStore`: `strictStateImmutability` deep-freezes the state tree to throw an immediate error if a developer attempts to mutate state directly; `strictActionImmutability` deep-freezes actions to ensure action payloads are never altered downstream; `strictStateSerializability` and `strictActionSerializability` verify that state and actions contain only JSON-serializable types (no functions, Promises, Observables, or class instances). Enabling these checks in development guarantees deterministic time-travel debugging, eliminates silent reference mutation bugs, and guarantees bug-free production runs where checks are stripped for speed.",
            keyPoints = listOf(
                "Lists key runtime checks: state immutability, action immutability, serializability.",
                "Explains strictStateImmutability using Object.freeze to detect mutation bugs early.",
                "Explains serializability checks ensuring state/actions can be cleanly JSON stringified.",
                "Identifies non-serializable anti-patterns: storing Observables, class instances, or functions.",
                "Clarifies why runtime checks are disabled in production builds to eliminate CPU overhead."
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_095",
            trackId = "angular_interview",
            conceptId = "ng_state_management",
            conceptName = "State Management & SignalStore",
            title = "Unit Testing NgRx Reducers, Selectors, and Effects",
            question = "How do you unit test NgRx Reducers, Selectors, and Effects in isolation using Jasmine/Jest and `provideMockActions`?",
            shortAnswer = "Reducers and Selectors are pure functions and require no Angular TestBed: (1) Reducer tests pass an initial state and action directly to `myReducer(state, action)` and assert the output equals expected state; (2) Selector tests use `mySelector.projector(mockSlice1, mockSlice2)` to verify the projection logic in isolation without setting up a full store. (3) Effects tests use `provideMockActions(() => actions\$)` with an RxJS `ReplaySubject` or marble testing via `TestScheduler`. The test emits an action onto `actions\$`, triggers the effect, and verifies that the effect emits the anticipated success/failure action while verifying mock service calls.",
            keyPoints = listOf(
                "Details testing reducers as pure functions without Angular TestBed overhead.",
                "Explains selector unit testing using the .projector() property for isolated testing.",
                "Describes setting up provideMockActions to supply a mock Actions observable.",
                "Explains asserting effect emissions using ReplaySubject or RxJS marble testing.",
                "Emphasizes mocking backend services injected into Effects to test success and failure paths."
            ),
            difficulty = "Staff"
        )
    )
    private fun part6(): List<InterviewQuestion> = listOf(
        InterviewQuestion(
            id = "iq_ng_096",
            trackId = "angular_interview",
            conceptId = "ng_state_management",
            conceptName = "State Management & SignalStore",
            title = "State Normalization vs Denormalization in Complex Frontends",
            question = "Why is relational state normalization critical in large-scale Angular applications? What data duplication anomalies occur when denormalized nested API payloads are stored directly in state?",
            shortAnswer = "APIs often return denormalized nested trees (e.g., an Order containing an Author and multiple Products, with each Product referencing the same Author). If stored directly in state: (1) Updating an author's name requires deeply traversing and mutating multiple nested arrays; (2) Inconsistencies arise if one nested copy is updated and another is missed; (3) Selectors re-evaluate unnecessarily because nested object references change. Normalization splits data into flat lookup tables indexed by ID (e.g., `entities: { [id]: Item }`) with relational foreign keys. This guarantees single-source-of-truth, O(1) mutations, and simple relational joins in memoized selectors.",
            keyPoints = listOf(
                "Explains the difference between nested denormalized payloads and normalized entity tables.",
                "Identifies update anomalies caused by redundant, duplicate data across nested branches.",
                "Describes normalized representation using id arrays and entity lookup dictionaries.",
                "Explains how memoized selectors perform efficient relational joins across tables.",
                "Demonstrates O(1) update efficiency vs expensive deep recursive clone operations."
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_ng_097",
            trackId = "angular_interview",
            conceptId = "ng_state_management",
            conceptName = "State Management & SignalStore",
            title = "NgRx Action Hygiene and Good Practices",
            question = "What is 'Action Hygiene' in NgRx? Why should actions be modeled as unique, descriptive events rather than generic setter commands, and why must actions not be reused across multiple components?",
            shortAnswer = "Action Hygiene is a set of best practices for authoring NgRx actions: (1) Actions as Events, not Commands: Name actions after what happened in the UI (e.g., `[Order Page] Submit Button Clicked`), not what the system should do (e.g., `[Order] Set Loading and Save Order`). (2) Unique Action Names: Every action must have a globally unique type including source context (`[Source] Event`), enabling accurate debugging in Redux DevTools; (3) Zero Action Reuse: Never reuse the same action across two different components; separate actions allow different effects/reducers to react independently to different UI triggers without breaking decoupling.",
            keyPoints = listOf(
                "Differentiates between event-driven actions and command-driven setter actions.",
                "Explains standard naming format: '[Category/Source] Specific Event Description'.",
                "Explains why reusing actions across different UI triggers hampers analytics and debugging.",
                "Describes how event-driven actions allow multiple reducers/effects to react simultaneously.",
                "Highlights how proper action hygiene improves Redux DevTools readability and traceability."
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_098",
            trackId = "angular_interview",
            conceptId = "ng_state_management",
            conceptName = "State Management & SignalStore",
            title = "Signals vs RxJS in State Management: When to Use Which",
            question = "With the introduction of Angular Signals and SignalStore, what criteria determine whether state should be modeled using Signals, RxJS Observables, or a combination of both?",
            shortAnswer = "Signals are synchronous, value-oriented primitives ideal for synchronous state representation, computed view projections, and fine-grained DOM updates. Use Signals for UI state, form control state, selected table rows, pagination indices, and local component state. RxJS is asynchronous, event-oriented, and operator-rich, ideal for complex event orchestration over time: debounced search typing, WebSocket real-time streams, polling, cancellation, race condition handling (`switchMap`), and coordinated retries. Modern architecture combines both: RxJS handles the async/event pipeline, and the final state is bridged into Signals via `toSignal()` or `rxMethod` for template rendering.",
            keyPoints = listOf(
                "Identifies Signals as synchronous, pull-based state containers for UI and derived values.",
                "Identifies RxJS as push-based event streams for async orchestration and temporal events.",
                "Specifies ideal Signal use cases: component UI state, computed data, template bindings.",
                "Specifies ideal RxJS use cases: debounce, throttling, cancellation, retries, WebSockets.",
                "Describes the hybrid pattern: RxJS for async event pipeline -> bridged to Signals for rendering."
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_ng_099",
            trackId = "angular_interview",
            conceptId = "ng_state_management",
            conceptName = "State Management & SignalStore",
            title = "Feature Stores and Lazy Loaded State Slices in Standalone Apps",
            question = "How do you register lazy-loaded feature state slices in standalone Angular applications using `provideState` and `provideEffects` within route configurations?",
            shortAnswer = "In modern standalone Angular applications without NgModules, feature states are registered lazily at the route level using `provideState` and `provideEffects`. Inside the route definition's `providers` array: `{ path: 'products', loadComponent: () => import('./product.component'), providers: [provideState(productsFeature), provideEffects(ProductEffects)] }`. When the user navigates to the 'products' route, Angular dynamically downloads the chunk, injects the feature slice into the global state tree, and initializes the feature's effects. When navigating away, the state persists in the store unless explicitly reset via route cleanup actions.",
            keyPoints = listOf(
                "Explains route-level state registration using provideState and provideEffects.",
                "Describes how standalone applications eliminate legacy StoreModule.forFeature.",
                "Explains dynamic chunk loading and runtime state tree slice injection.",
                "Discusses lifecycle: feature state persistence vs route deactivation cleanup.",
                "Highlights bundle size reduction by splitting store code per feature route."
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_100",
            trackId = "angular_interview",
            conceptId = "ng_state_management",
            conceptName = "State Management & SignalStore",
            title = "State Reset and Multi-Tenancy Cleanliness on Logout",
            question = "How do you cleanly reset the entire application state tree upon user logout in an NgRx application, preventing data leaks across multi-tenant sessions?",
            shortAnswer = "To prevent data leakage between user sessions, a root meta-reducer is configured to intercept the `logoutAction`. When dispatched: `export function clearStateMetaReducer(reducer: ActionReducer<any>): ActionReducer<any> { return (state, action) => { if (action.type === logoutAction.type) { return reducer(undefined, { type: '@INIT' }); } return reducer(state, action); }; }`. Passing `undefined` forces all feature reducers to re-initialize with their original `initialState` defaults. Additionally, any active IndexedDB/localStorage tokens are purged, and all open RxJS subscriptions or WebSockets are terminated to guarantee a completely sanitized application state before redirecting to the login view.",
            keyPoints = listOf(
                "Explains the security risk of retaining state in memory across user logouts.",
                "Demonstrates implementing a clearState meta-reducer intercepting logout actions.",
                "Explains how passing undefined state to child reducers resets state to default initial values.",
                "Details auxiliary cleanup tasks: localStorage/sessionStorage purge, token revocation.",
                "Discusses terminating active WebSocket connections and background polling intervals."
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_ng_101",
            trackId = "angular_interview",
            conceptId = "ng_routing_architecture",
            conceptName = "Routing & Micro-Frontends",
            title = "Modern Functional Route Guards in Angular",
            question = "How do modern functional route guards (`CanActivateFn`, `CanMatchFn`, `CanDeactivateFn`) work in standalone Angular, and why have class-based guards implementing interfaces been deprecated?",
            shortAnswer = "Modern Angular replaces class-based guards with functional guards defined directly as functions matching signatures like `const authGuard: CanActivateFn = (route, state) => { const auth = inject(AuthService); return auth.isLoggedIn() ? true : inject(Router).createUrlTree(['/login']); }`. Functional guards leverage `inject()` to access dependencies directly without constructor injection boilerplate. They are tree-shakable, composable via higher-order functions (e.g., `hasRole(['ADMIN'])`), and eliminate the need for class declarations and NgModule provider registrations. Class-based guards were deprecated to simplify the API and encourage functional, modular patterns.",
            keyPoints = listOf(
                "Contrasts functional guards with deprecated class-based guards implementing CanActivate.",
                "Explains using inject() inside guard functions for DI resolution.",
                "Demonstrates returning boolean, Observable<boolean>, or UrlTree for redirects.",
                "Explains composability using higher-order functions (e.g. role-checking factories).",
                "Highlights benefits: tree-shaking, zero boilerplate, and standalone route compatibility."
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_102",
            trackId = "angular_interview",
            conceptId = "ng_routing_architecture",
            conceptName = "Routing & Micro-Frontends",
            title = "CanMatch vs CanActivate: Bundle Protection and Feature Toggles",
            question = "What is the difference between `canMatch` and `canActivate` in the Angular Router? Why is `canMatch` superior for feature flagging and preventing unauthorized bundle downloads?",
            shortAnswer = "`canActivate` runs *after* a route has been matched and its lazy code chunk has already begun downloading; returning `false` cancels navigation, but the JavaScript bundle has already arrived at the client. `canMatch` runs *before* route matching and bundle fetching. If `canMatch` returns `false`, the router skips that route definition entirely and continues evaluating subsequent routes in the configuration. This enables: (1) True bundle protection (unauthorized users never download restricted code); and (2) Dynamic feature toggling/A-B testing, where multiple routes share the same path (e.g., `/dashboard`) but render different components depending on feature flags.",
            keyPoints = listOf(
                "Contrasts execution timing: canMatch executes before route matching and chunk downloading.",
                "Explains that canActivate runs after the route matches, downloading code even if blocked.",
                "Details how canMatch skips to the next matching route definition on false.",
                "Explains A/B testing and role-based route variations sharing the same path string.",
                "Highlights security and bandwidth benefits of preventing code chunk transmission."
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_ng_103",
            trackId = "angular_interview",
            conceptId = "ng_routing_architecture",
            conceptName = "Routing & Micro-Frontends",
            title = "withComponentInputBinding: Route Params and Query Params as Inputs",
            question = "What does `withComponentInputBinding()` enable in Angular 16+? How does it transform how components receive route parameters, query parameters, and resolved data via `@Input()` or `input()`?",
            shortAnswer = "Configured in `provideRouter(routes, withComponentInputBinding())`, this feature automatically binds path parameters, query parameters, matrix parameters, and route data/resolvers directly to component inputs matching the parameter names. For a route `path: 'user/:id'`, the component can directly declare `id = input.required<string>();` without injecting `ActivatedRoute`, subscribing to `paramMap`, or managing RxJS subscription cleanup. If a query param `?tab=profile` exists, a matching input `tab = input<string>('overview');` is automatically populated and reactively updated when the URL changes.",
            keyPoints = listOf(
                "Explains enabling withComponentInputBinding in provideRouter configuration.",
                "Demonstrates direct binding of path params, query params, and resolve data to component inputs.",
                "Shows integration with modern Signal inputs: input() and input.required().",
                "Contrasts with legacy boilerplate: injecting ActivatedRoute and subscribing to paramMap.",
                "Explains collision precedence: path params take precedence over query params."
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_104",
            trackId = "angular_interview",
            conceptId = "ng_routing_architecture",
            conceptName = "Routing & Micro-Frontends",
            title = "Route Resolvers vs Skeleton Loaders and Modern UX Patterns",
            question = "What are Route Resolvers in Angular? Why is the industry moving away from Route Resolvers in favor of immediate component navigation with skeleton loaders and deferred views?",
            shortAnswer = "Route Resolvers (`ResolveFn`) fetch required backend data *before* the router activates the destination component. While guaranteeing data is ready upon view render, resolvers block navigation: the user remains on the previous page with no visual feedback while network requests execute, creating perceived lag. Modern UX practices avoid resolvers for non-critical data. Instead, the router navigates immediately, instantly rendering skeleton loaders, while the target component fetches data asynchronously via Signals or RxJS, or uses `@defer` blocks. Resolvers should be reserved strictly for critical metadata where rendering without data causes severe layout breakage.",
            keyPoints = listOf(
                "Defines Route Resolvers (ResolveFn) as pre-navigation data fetchers.",
                "Identifies UX problem: frozen UI and perceived lag while resolvers wait for network responses.",
                "Contrasts with immediate navigation paired with skeleton loaders or spinners.",
                "Explains integration with Angular @defer blocks for progressive content loading.",
                "Identifies legitimate remaining resolver use cases (critical route preconditions, SEO metadata)."
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_105",
            trackId = "angular_interview",
            conceptId = "ng_routing_architecture",
            conceptName = "Routing & Micro-Frontends",
            title = "Preloading Strategies: PreloadAllModules, Custom, and Quicklink",
            question = "How do preloading strategies work in Angular Routing? Compare `PreloadAllModules`, `NoPreloading`, and intelligent heuristics like Quicklink or network-aware preloading.",
            shortAnswer = "Preloading downloads lazy-loaded JavaScript chunks in the background *after* the initial application has rendered, eliminating latency when users navigate: (1) `NoPreloading` (default) downloads chunks only on demand; (2) `PreloadAllModules` aggressively downloads all lazy chunks immediately after bootstrap, which wastes mobile bandwidth and battery; (3) Custom preloading strategies implement `PreloadingStrategy` to conditionally preload based on route data (e.g., `data: { preload: true }`), user roles, or network status (`navigator.connection.saveData`); (4) Quicklink monitors the viewport using `IntersectionObserver` and preloads chunks only for links currently visible on screen.",
            keyPoints = listOf(
                "Explains the concept of preloading lazy route chunks after initial app bootstrap.",
                "Compares NoPreloading and PreloadAllModules tradeoffs regarding data consumption.",
                "Explains implementing a custom PreloadingStrategy evaluating route data flags.",
                "Describes Quicklink strategy preloading links visible in the current viewport via IntersectionObserver.",
                "Mentions network-aware preloading checking navigator.connection for 2G/data-saver modes."
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_ng_106",
            trackId = "angular_interview",
            conceptId = "ng_routing_architecture",
            conceptName = "Routing & Micro-Frontends",
            title = "RouteReuseStrategy: Preserving Component Instances and Tabbed UI",
            question = "What is `RouteReuseStrategy` in Angular? How do you implement a custom strategy to cache component instances across route changes, such as in a multi-tab dashboard?",
            shortAnswer = "By default, Angular destroys the active component when navigating away and instantiates a new one upon returning. `RouteReuseStrategy` allows overriding this lifecycle by caching `DetachedRouteHandle` instances. It defines five methods: `shouldDetach` (determines if a route should be stored upon leaving), `store` (saves the detached handle in an internal cache map), `shouldAttach` (checks if a route being visited is cached), `retrieve` (retrieves the cached handle to reattach to the DOM), and `shouldReuseRoute` (determines if the current and future routes share the same config). This is critical for enterprise multi-tab dashboards, preserving scroll positions, form inputs, and state without re-fetching.",
            keyPoints = listOf(
                "Explains the purpose of RouteReuseStrategy: detaching and caching component DOM trees.",
                "Details the five core methods: shouldDetach, store, shouldAttach, retrieve, shouldReuseRoute.",
                "Describes using DetachedRouteHandle to reattach existing component instances.",
                "Outlines real-world use case: browser-like tabbed navigation preserving scroll and state.",
                "Discusses memory leak management and eviction policies when caching multiple route handles."
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_ng_107",
            trackId = "angular_interview",
            conceptId = "ng_routing_architecture",
            conceptName = "Routing & Micro-Frontends",
            title = "Micro-Frontends with Module Federation and Native Federation",
            question = "How do Micro-Frontends operate in Angular using Webpack Module Federation or Manfred Steyer's Native Federation? How are shared dependencies (Angular core, RxJS) negotiated at runtime?",
            shortAnswer = "Module Federation allows independent Angular applications (Remotes) to expose modules or standalone components dynamically to a Shell application (Host) at runtime. Native Federation achieves the same using standard browser ES Modules and import maps without Webpack lock-in (supporting esbuild and Vite). The Host route configuration loads the remote dynamically: `loadComponent: () => loadRemoteModule({ remoteEntry: 'http://remote:3000/remoteEntry.json', exposedModule: './Widget' }).then(m => m.WidgetComponent)`. Shared dependencies (like `@angular/core`, `rxjs`) are declared in the federation config with `singleton: true, strictVersion: true`, ensuring both host and remotes share a single runtime memory instance, preventing injector collisions.",
            keyPoints = listOf(
                "Defines Micro-Frontends and distinguishes Shell (Host) from Remote applications.",
                "Contrasts Webpack Module Federation with Native Federation (ESM and import maps).",
                "Explains dynamic remote component loading in route configurations via loadRemoteModule.",
                "Details shared dependency negotiation: singleton: true and requiredVersion constraints.",
                "Explains why duplicate @angular/core instances break DI and change detection."
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_ng_108",
            trackId = "angular_interview",
            conceptId = "ng_routing_architecture",
            conceptName = "Routing & Micro-Frontends",
            title = "Angular Elements: Exporting Angular Components as Web Components",
            question = "What is `@angular/elements`? How does `createCustomElement` bridge Angular components into native Custom Elements (Web Components) for framework-agnostic embedding?",
            shortAnswer = "`@angular/elements` packages Angular components as native Custom Elements conforming to the W3C Web Components standard. `createCustomElement(MyComponent, { injector })` wraps the Angular component, converting `@Input()` properties into HTML attributes/properties and `@Output()` event emitters into DOM CustomEvents. `customElements.define('my-element', customElement)` registers it with the browser. It allows Angular components to be embedded into React, Vue, or legacy static HTML sites. The custom element runs its own isolated change detection and handles its own lifecycle callbacks (`connectedCallback`, `disconnectedCallback`).",
            keyPoints = listOf(
                "Defines @angular/elements and its role in creating standards-compliant Custom Elements.",
                "Explains createCustomElement API and the requirement of passing an EnvironmentInjector.",
                "Describes bridging: @Input becomes DOM property/attribute, @Output becomes DOM CustomEvent.",
                "Explains registration using the browser-native customElements.define() API.",
                "Discusses use cases: micro-frontends, design systems embedded in legacy CMS or React apps."
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_109",
            trackId = "angular_interview",
            conceptId = "ng_routing_architecture",
            conceptName = "Routing & Micro-Frontends",
            title = "Navigation Cancellation and Router Event Lifecycle",
            question = "Describe the sequence of `RouterEvent` emissions during a navigation cycle in Angular. What happens when a user triggers a rapid secondary navigation while the first is in progress?",
            shortAnswer = "The Router event lifecycle emits in strict sequence: `NavigationStart` -> `RouteConfigLoadStart`/`RouteConfigLoadEnd` (if lazy loading) -> `RoutesRecognized` -> `GuardsCheckStart`/`GuardsCheckEnd` -> `ResolveStart`/`ResolveEnd` -> `ActivationStart`/`ActivationEnd` -> `NavigationEnd`. If an error occurs, `NavigationError` emits. If a guard returns `false` or redirects via `UrlTree`, `NavigationCancel` emits. If a user clicks a new link while navigation is in progress, the Router cancels the active navigation, emits `NavigationCancel` with reason `NavigationCancellationCode.SupersededByNewNavigation`, terminates pending guard/resolver observables, and begins a new navigation cycle for the latest URL.",
            keyPoints = listOf(
                "Lists the chronological sequence of Router events from NavigationStart to NavigationEnd.",
                "Identifies guard and resolver event phases (GuardsCheckStart/End, ResolveStart/End).",
                "Explains NavigationCancel triggers: guard returning false or redirecting with UrlTree.",
                "Explains superseded navigation: active navigation aborted when a new navigation starts.",
                "Discusses listening to router events for global loading progress bar indicators."
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_ng_110",
            trackId = "angular_interview",
            conceptId = "ng_routing_architecture",
            conceptName = "Routing & Micro-Frontends",
            title = "UrlTree, UrlSerializer, and Custom URL Matching",
            question = "How does Angular Router represent URLs internally as a `UrlTree`? When should you implement a custom `UrlMatcher` instead of standard path string matching?",
            shortAnswer = "Angular parses URLs into a `UrlTree`, a hierarchical data structure of `UrlSegmentGroup` and `UrlSegment` nodes representing primary and secondary outlets, path segments, and query parameters. `DefaultUrlSerializer` parses string URLs into `UrlTree` and serializes trees back to strings. A custom `UrlMatcher` (`(segments: UrlSegment[], group: UrlSegmentGroup, route: Route) => UrlMatchResult | null`) is used when static path strings or regexes are insufficient: e.g., matching dynamic slugs with custom file extensions (`photo/:id.jpg`), parsing localization prefixes (`/en/docs` vs `/fr/docs`), or matching complex variable segment patterns.",
            keyPoints = listOf(
                "Explains the hierarchical structure of UrlTree, UrlSegmentGroup, and UrlSegment.",
                "Describes UrlSerializer converting strings to UrlTree and vice-versa.",
                "Defines UrlMatcher function signature and UrlMatchResult return structure.",
                "Presents real-world use cases for custom UrlMatcher: dynamic extensions, locale prefixes.",
                "Contrasts UrlMatcher with standard path and pathMatch: 'prefix' | 'full' configurations."
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_ng_111",
            trackId = "angular_interview",
            conceptId = "ng_routing_architecture",
            conceptName = "Routing & Micro-Frontends",
            title = "Auxiliary Routes and Named Router Outlets",
            question = "What are named router outlets (auxiliary routes) in Angular? How do you configure and navigate to auxiliary outlets, and what does the resulting URL syntax look like?",
            shortAnswer = "Named router outlets allow multiple independent views to be rendered simultaneously on the same page (e.g., a floating chat sidebar or modal dialog alongside the main content). In template: `<router-outlet name='sidebar'></router-outlet>`. In route configuration: `{ path: 'chat', component: ChatComponent, outlet: 'sidebar' }`. Programmatic navigation uses: `router.navigate([{ outlets: { sidebar: ['chat'] } }])`. In the browser address bar, auxiliary routes appear inside parentheses: `/dashboard(sidebar:chat)`. Closing the outlet involves navigating with `outlets: { sidebar: null }`.",
            keyPoints = listOf(
                "Explains the purpose of named router outlets for multi-pane and auxiliary views.",
                "Shows template declaration: <router-outlet name='outletName'>.",
                "Demonstrates route configuration with outlet property.",
                "Demonstrates programmatic navigation using { outlets: { name: ['path'] } }.",
                "Explains browser URL syntax with parentheses: /path(outletName:subPath)."
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_112",
            trackId = "angular_interview",
            conceptId = "ng_routing_architecture",
            conceptName = "Routing & Micro-Frontends",
            title = "TitleStrategy: Dynamic Page Titles and SEO Breadcrumbs",
            question = "How does Angular's `TitleStrategy` API streamline document title management? How do you implement a custom `TitleStrategy` for dynamic, localized page titles?",
            shortAnswer = "Angular provides `TitleStrategy` to automatically manage the browser tab `<title>`. Static titles are defined on routes via `title: 'User Profile'`, while dynamic titles use title resolvers (`ResolveFn<string>`). By extending `TitleStrategy` and implementing `updateTitle(snapshot: RouterStateSnapshot)`, you can intercept title updates: `export class TemplatePageTitleStrategy extends TitleStrategy { override updateTitle(snapshot: RouterStateSnapshot) { const title = this.buildTitle(snapshot); if (title) { inject(Title).setTitle(`MyCorp | \${title}`); } } }`. Registering it via `{ provide: TitleStrategy, useClass: TemplatePageTitleStrategy }` standardizes branding and dynamic prefixes app-wide.",
            keyPoints = listOf(
                "Explains built-in route title property and title resolvers.",
                "Describes TitleStrategy base class and updateTitle() lifecycle hook.",
                "Demonstrates building title from snapshot using this.buildTitle(snapshot).",
                "Shows injecting Title service to apply formatted global prefixes or suffixes.",
                "Explains provider replacement in provideRouter or root application config."
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_113",
            trackId = "angular_interview",
            conceptId = "ng_routing_architecture",
            conceptName = "Routing & Micro-Frontends",
            title = "CanDeactivate: Unsaved Changes Protection with Reactive Dialogs",
            question = "How do you implement a `CanDeactivateFn` guard to prevent users from accidentally navigating away from a dirty form, integrating with an async confirmation dialog?",
            shortAnswer = "`CanDeactivateFn<T>` intercepts navigation away from the current route. It accepts the active component instance: `export const unsavedChangesGuard: CanDeactivateFn<FormComponent> = (component) => { if (component.form.dirty && !component.isSaved) { const dialog = inject(DialogService); return dialog.confirm('You have unsaved changes. Discard?'); } return true; };`. The guard can return a `boolean`, a `Promise<boolean>`, or an `Observable<boolean>`. If the user cancels the dialog (emitting `false`), the router halts navigation and preserves the user's form state. Note that browser tab closure or refresh requires handling the native `window.onbeforeunload` event separately.",
            keyPoints = listOf(
                "Defines CanDeactivateFn signature accepting component instance.",
                "Checks component form state (dirty/pristine) before permitting navigation.",
                "Integrates with async dialog services returning Observable<boolean> or Promise<boolean>.",
                "Explains how returning false cancels navigation and keeps user on the form.",
                "Distinguishes internal router navigation from native browser refresh/close (onbeforeunload)."
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_114",
            trackId = "angular_interview",
            conceptId = "ng_routing_architecture",
            conceptName = "Routing & Micro-Frontends",
            title = "Scroll Position Restoration and Anchor Scrolling",
            question = "How does `withInMemoryScrolling()` configure scroll restoration in Angular? Explain the difference between `scrollPositionRestoration: 'top'` and `'enabled'`.",
            shortAnswer = "Configured via `provideRouter(routes, withInMemoryScrolling({ scrollPositionRestoration: 'enabled', anchorScrolling: 'enabled' }))`: (1) `'top'` scrolls the window to `(0, 0)` on every navigation; (2) `'enabled'` intelligently scrolls to `(0, 0)` on forward navigation while restoring the exact previous X/Y scroll position when the user clicks browser Back/Forward buttons. (3) `anchorScrolling: 'enabled'` automatically scrolls to an element matching the URL fragment (`#section-id`). For fine-grained custom control (e.g., virtual scroll containers or multi-pane apps), developers can inject `ViewportScroller` and manage coordinates manually.",
            keyPoints = listOf(
                "Explains withInMemoryScrolling configuration in provideRouter.",
                "Contrasts scrollPositionRestoration: 'top' vs 'enabled' (history back/forward restoration).",
                "Explains anchorScrolling: 'enabled' for jumping to URL fragments (#hash).",
                "Identifies ViewportScroller service for programmatic scroll control.",
                "Discusses challenges with scroll restoration in lazy-loaded or async-rendered views."
            ),
            difficulty = "Senior"
        )
    )
    private fun part7(): List<InterviewQuestion> = listOf(
        InterviewQuestion(
            id = "iq_ng_115",
            trackId = "angular_interview",
            conceptId = "ng_routing_architecture",
            conceptName = "Routing & Micro-Frontends",
            title = "Standalone Routing Architecture and Nested Child Routes",
            question = "How do you organize nested child routes and lazy-loaded feature boundaries in modern standalone Angular applications without NgModules?",
            shortAnswer = "Standalone routing uses modular route files exporting route arrays. Feature boundaries are lazy-loaded via `loadChildren`: `{ path: 'admin', loadChildren: () => import('./admin/admin.routes').then(m => m.ADMIN_ROUTES) }`. Within `admin.routes.ts`, child routes are defined: `export const ADMIN_ROUTES: Routes = [{ path: '', component: AdminLayoutComponent, children: [{ path: 'users', component: UserListComponent }, { path: 'settings', component: SettingsComponent }] }]`. This splits bundles per feature, establishes hierarchical component layouts with nested `<router-outlet>`, and encapsulates route-scoped providers without any `NgModule` declarations.",
            keyPoints = listOf(
                "Demonstrates loadChildren importing modular route configuration files.",
                "Explains child route array structure with nested <router-outlet> in parent layout.",
                "Shows elimination of legacy RouterModule.forChild() in standalone architecture.",
                "Explains route-level providers encapsulation for lazy-loaded feature routes.",
                "Highlights automatic esbuild/Webpack code splitting per loadChildren boundary."
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_116",
            trackId = "angular_interview",
            conceptId = "ng_routing_architecture",
            conceptName = "Routing & Micro-Frontends",
            title = "Route Matching Order and Wildcard Catch-All Routing",
            question = "How does the Angular Router evaluate route matching precedence? Why must wildcard (`**`) routes always be placed last, and how does `pathMatch: 'full'` prevent prefix matching traps?",
            shortAnswer = "Angular Router evaluates routes linearly from top to bottom; the first route matching the requested URL wins. A wildcard route `{ path: '**', component: NotFoundComponent }` matches any URL; placing it before other routes immediately intercepts all subsequent paths, rendering them unreachable. Furthermore, empty paths (`path: ''`) default to `pathMatch: 'prefix'`, meaning they match any URL since every string starts with an empty prefix. Empty path redirects must specify `pathMatch: 'full'` (`{ path: '', redirectTo: '/home', pathMatch: 'full' }`) so the redirect fires only when the URL path is strictly empty.",
            keyPoints = listOf(
                "Explains first-match-wins linear route evaluation order.",
                "Explains why wildcard (**) must be the terminal route in the configuration array.",
                "Explains default pathMatch: 'prefix' behavior on route segments.",
                "Details why empty path redirects require pathMatch: 'full' to avoid matching all URLs.",
                "Discusses debugging route collision and unexpected 404 redirects."
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_117",
            trackId = "angular_interview",
            conceptId = "ng_routing_architecture",
            conceptName = "Routing & Micro-Frontends",
            title = "Testing Standalone Angular Routing with RouterTestingHarness",
            question = "How do you test routing in standalone Angular using `provideRouter` and `RouterTestingHarness` without brittle DOM navigation mocks?",
            shortAnswer = "`RouterTestingHarness` (Angular 15+) simplifies router integration testing. Configured via `TestBed.configureTestingModule({ providers: [provideRouter(routes)] })`, the test initializes the harness: `const harness = await RouterTestingHarness.create(); const component = await harness.navigateByUrl('/users/42', UserComponent);`. The harness manages router event cycles, waits for microtasks, and returns the instantiated component instance. Developers can assert `component.id()` matches the route param, verify active route URLs via `harness.routeNativeElement`, and test navigation transitions declaratively without mocking `Router` or `ActivatedRoute`.",
            keyPoints = listOf(
                "Explains RouterTestingHarness introduction in modern Angular testing.",
                "Demonstrates TestBed setup with provideRouter(routes).",
                "Shows creating harness and using navigateByUrl() returning component instance.",
                "Explains automatic handling of async router navigation cycles.",
                "Contrasts with legacy manual mocking of Router and ActivatedRoute."
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_ng_118",
            trackId = "angular_interview",
            conceptId = "ng_routing_architecture",
            conceptName = "Routing & Micro-Frontends",
            title = "View Transitions API Integration in Angular Routing",
            question = "What is `withViewTransitions()` in Angular 17+? How does it integrate the browser-native View Transitions API into route navigations for seamless animated transitions?",
            shortAnswer = "Configured via `provideRouter(routes, withViewTransitions())`, this feature hooks Angular route changes into the browser's native View Transitions API (`document.startViewTransition`). When navigating between pages, Angular captures a snapshot of the current DOM, updates the DOM with the incoming component, and lets the browser animate the cross-fade or custom morphing transition. Developers can define CSS `view-transition-name` properties on shared elements (e.g., product card images transitioning to hero images on detail pages) to achieve smooth, hardware-accelerated animations without complex JavaScript animation libraries.",
            keyPoints = listOf(
                "Explains withViewTransitions() feature flag in provideRouter.",
                "Describes browser-native View Transitions API (document.startViewTransition).",
                "Explains default cross-fade animation between outgoing and incoming routes.",
                "Shows using CSS view-transition-name to animate shared elements across routes.",
                "Discusses fallback behavior for browsers that do not yet support View Transitions."
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_ng_119",
            trackId = "angular_interview",
            conceptId = "ng_routing_architecture",
            conceptName = "Routing & Micro-Frontends",
            title = "Dynamic and Programmatic Route Creation at Runtime",
            question = "Can Angular routes be modified dynamically at runtime? How do you use `Router.resetConfig()` to inject routes dynamically after user authentication or permission checks?",
            shortAnswer = "Yes, Angular routes can be modified dynamically via `Router.resetConfig(newRoutes)`. During application startup or immediately following login, an application can fetch user permissions, filter or construct a custom route tree, and invoke `router.resetConfig([...baseRoutes, ...dynamicAdminRoutes])`. This ensures unauthorized routes do not even exist in the router table for standard users. However, in modern Angular, `canMatch` functional guards are generally preferred over `resetConfig()` because `canMatch` cleanly intercepts and delegates route matching declaratively without imperatively mutating global router configuration.",
            keyPoints = listOf(
                "Explains Router.resetConfig() for dynamically replacing the active route configuration.",
                "Describes use case: injecting admin or tenant-specific routes after auth.",
                "Explains potential race conditions if navigation occurs while resetting configuration.",
                "Compares resetConfig() with declarative canMatch guards.",
                "Explains why canMatch is the modern industry-recommended approach."
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_ng_120",
            trackId = "angular_interview",
            conceptId = "ng_routing_architecture",
            conceptName = "Routing & Micro-Frontends",
            title = "State Transfer Across Routes via NavigationExtras",
            question = "How do you pass transient state between routes using `NavigationExtras.state`? How does `Router.getCurrentNavigation()?.extras.state` differ from `history.state`?",
            shortAnswer = "Transient state can be passed during navigation: `router.navigate(['/checkout'], { state: { orderTotal: 150, promoCode: 'SAVE20' } })`. During navigation (e.g., inside route guards or constructors), this state is accessed via `router.getCurrentNavigation()?.extras.state`. Once navigation completes, `getCurrentNavigation()` returns `null`, and the state is retrieved via `history.state` or `location.getState()`. Unlike query parameters, `state` is not visible in the browser address bar and does not pollute URLs. However, state persists across browser page refreshes because it is stored in the browser's session history entry.",
            keyPoints = listOf(
                "Demonstrates passing state object via NavigationExtras: router.navigate(path, { state }).",
                "Explains accessing state during navigation via router.getCurrentNavigation()?.extras.state.",
                "Explains accessing state after navigation completion via history.state.",
                "Contrasts route state with query params: clean URL without exposing sensitive data.",
                "Notes that browser refresh retains history.state tied to the session history entry."
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_121",
            trackId = "angular_interview",
            conceptId = "ng_http_networking",
            conceptName = "HttpClient, Interceptors & Networking",
            title = "Modern provideHttpClient and Functional Interceptors",
            question = "How do modern functional interceptors (`HttpInterceptorFn`) work with `provideHttpClient(withInterceptors([...]))` in standalone Angular? Why are they preferred over class-based `HttpInterceptor`?",
            shortAnswer = "Modern Angular configures HTTP via `provideHttpClient(withInterceptors([authInterceptor, loggingInterceptor]))`. Interceptors are defined as pure functions matching `HttpInterceptorFn`: `(req, next) => next(req)`. They access dependencies directly via `inject()` without constructor boilerplate and class declarations. Functional interceptors are fully tree-shakable, simple to compose, and execute in the strict linear order specified in the `withInterceptors` array. Class-based interceptors using `HTTP_INTERCEPTORS` multi-provider tokens were deprecated because they required complex module ceremony and prevented fine-grained tree-shaking.",
            keyPoints = listOf(
                "Explains provideHttpClient and withInterceptors configuration.",
                "Defines HttpInterceptorFn signature: (req: HttpRequest<unknown>, next: HttpHandlerFn).",
                "Demonstrates using inject() inside interceptor functions for DI resolution.",
                "Explains strict execution ordering: request traverses in order, response in reverse.",
                "Highlights advantages over legacy HTTP_INTERCEPTORS: tree-shaking, no classes/modules."
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_122",
            trackId = "angular_interview",
            conceptId = "ng_http_networking",
            conceptName = "HttpClient, Interceptors & Networking",
            title = "JWT Authentication and 401 Refresh Token Queue Implementation",
            question = "How do you implement an HTTP interceptor that attaches JWT Bearer tokens and handles 401 Unauthorized responses with an asynchronous refresh token queue?",
            shortAnswer = "The interceptor clones outgoing requests to append `Authorization: Bearer \${token}`. If a 401 error occurs, it catches the error and checks a mutex/flag `isRefreshing`. If false, it sets `isRefreshing = true`, initiates a refresh token API call, and passes new tokens through a `BehaviorSubject<string | null>(null)`. If `isRefreshing` is already true (concurrent requests arriving), subsequent requests wait by piping the `BehaviorSubject`, filtering out `null`, taking 1, and cloning with the new token before retrying. Once refreshed, `isRefreshing` is reset to false, and the queued requests replay seamlessly. If refresh fails, it clears tokens and redirects to login.",
            keyPoints = listOf(
                "Demonstrates attaching Authorization Bearer header via req.clone().",
                "Catches 401 HttpErrorResponse using catchError operator.",
                "Implements refresh locking mechanism (isRefreshing flag) to prevent duplicate refresh calls.",
                "Uses BehaviorSubject to queue concurrent incoming requests until new token arrives.",
                "Replays original failed requests with new token or redirects to login upon refresh failure."
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_ng_123",
            trackId = "angular_interview",
            conceptId = "ng_http_networking",
            conceptName = "HttpClient, Interceptors & Networking",
            title = "HttpContext and HttpContextToken for Request-Level Metadata",
            question = "What are `HttpContext` and `HttpContextToken` in Angular? How do you pass type-safe metadata to interceptors, such as bypassing authentication or caching?",
            shortAnswer = "`HttpContext` allows attaching type-safe, non-HTTP metadata to an `HttpRequest`. A token is declared: `export const IS_PUBLIC_API = new HttpContextToken<boolean>(() => false);`. A request passes the context: `httpClient.get('/public-feed', { context: new HttpContext().set(IS_PUBLIC_API, true) })`. Inside the interceptor, the value is retrieved: `if (req.context.get(IS_PUBLIC_API)) return next(req);`. This provides clean, type-safe communication between caller and interceptors without polluting HTTP headers or query parameters with internal control flags.",
            keyPoints = listOf(
                "Defines HttpContext and HttpContextToken for request-scoped metadata.",
                "Shows declaration of HttpContextToken with default factory function.",
                "Demonstrates attaching context when executing HttpClient get/post calls.",
                "Demonstrates reading context inside an HttpInterceptorFn.",
                "Presents real-world use cases: bypass auth, bypass cache, custom retry count."
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_124",
            trackId = "angular_interview",
            conceptId = "ng_http_networking",
            conceptName = "HttpClient, Interceptors & Networking",
            title = "HTTP Caching Interceptor with TTL and Cache Invalidation",
            question = "How do you design an in-memory HTTP caching interceptor in Angular? How do you handle cache expiration (TTL), cache invalidation on mutations (POST/PUT/DELETE), and concurrency?",
            shortAnswer = "A caching interceptor checks if `req.method === 'GET'`. It consults a Map caching `{ response: HttpResponse<any>, expiry: number }`. If a valid unexpired entry exists, it returns `of(cached.response)`. Otherwise, it invokes `next(req)` and taps the response, storing successful 200 responses in the map with `Date.now() + TTL`. For non-GET mutations (`POST`, `PUT`, `PATCH`, `DELETE`), the interceptor intercepts the request and purges relevant cached entries matching the URL or clears the entire cache. Requests can also pass `new HttpContext().set(BYPASS_CACHE, true)` to force fresh network fetches.",
            keyPoints = listOf(
                "Explains checking GET method and caching Map lookup using URL key.",
                "Demonstrates returning cached response via of(cachedResponse) to skip network.",
                "Explains TTL calculation and purging expired cache entries.",
                "Explains cache invalidation strategy on write operations (POST/PUT/DELETE).",
                "Uses HttpContext to allow callers to selectively bypass caching."
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_ng_125",
            trackId = "angular_interview",
            conceptId = "ng_http_networking",
            conceptName = "HttpClient, Interceptors & Networking",
            title = "Global Error Handling vs HTTP Interceptor Error Catching",
            question = "Compare Angular's global `ErrorHandler` class with `HttpInterceptorFn` error catching. Where should network errors be handled vs uncaught JavaScript exceptions?",
            shortAnswer = "Angular's global `ErrorHandler` (`provideErrorHandler(CustomErrorHandler)`) catches all unhandled runtime exceptions thrown in component templates, lifecycle hooks, and asynchronous microtasks. However, RxJS observable errors caught by subscribers (or uncaught in streams) don't always propagate cleanly to `ErrorHandler`. HTTP interceptors handle network-specific communication failures (e.g., status 400, 401, 403, 500, timeouts) centrally: logging to monitoring services (e.g., Sentry), showing toast alerts, or refreshing tokens. `ErrorHandler` is the ultimate fallback for fatal JS crashes, while HTTP interceptors normalize and recover from operational API failures.",
            keyPoints = listOf(
                "Explains ErrorHandler class role catching uncaught application runtime exceptions.",
                "Explains HttpInterceptor role intercepting HttpErrorResponse instances.",
                "Clarifies why network errors must be caught in RxJS pipelines before reaching ErrorHandler.",
                "Describes responsibilities: HTTP interceptor for status toasts/auth; ErrorHandler for Sentry crash logs.",
                "Explains error re-throwing (throwError) so calling components can display local validation errors."
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_126",
            trackId = "angular_interview",
            conceptId = "ng_http_networking",
            conceptName = "HttpClient, Interceptors & Networking",
            title = "withFetch: Modern Fetch API Integration in Angular HttpClient",
            question = "What is `withFetch()` in Angular 17+? Why does switching from `XMLHttpRequest` to the native Fetch API matter for Server-Side Rendering (SSR) and HTTP streaming?",
            shortAnswer = "Configured via `provideHttpClient(withFetch())`, Angular replaces the legacy browser `XMLHttpRequest` engine with the native browser and Node.js `fetch()` API. Benefits: (1) In SSR (Node.js runtime), Fetch is natively supported without needing bulky polyfills like `xhr2`; (2) Enables HTTP response streaming, allowing server-sent events or streaming LLM completions to be processed chunk-by-chunk via readable streams; (3) Improved performance and alignment with modern web standards, service workers, and Cloudflare Workers/edge computing runtimes.",
            keyPoints = listOf(
                "Explains withFetch() feature configuration in provideHttpClient.",
                "Contrasts native Fetch API with legacy XMLHttpRequest (XHR) implementation.",
                "Explains benefits for Angular SSR: native Node/edge runtime execution without XHR polyfills.",
                "Describes streaming capabilities for chunked transfer encoding and SSE.",
                "Notes compatibility considerations with legacy XHR-specific upload progress listeners."
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_127",
            trackId = "angular_interview",
            conceptId = "ng_http_networking",
            conceptName = "HttpClient, Interceptors & Networking",
            title = "Upload and Download Progress Tracking with HttpClient",
            question = "How do you track file upload and download progress percentages in Angular using `reportProgress: true` and `observe: 'events'`?",
            shortAnswer = "To track progress, configure `httpClient.request(new HttpRequest('POST', '/upload', formData, { reportProgress: true }))` or `httpClient.get('/file', { reportProgress: true, observe: 'events', responseType: 'blob' })`. The returned observable emits multiple `HttpEvent` instances over time: `HttpEventType.Sent`, `HttpEventType.UploadProgress`, `HttpEventType.DownloadProgress`, and `HttpEventType.Response`. Progress percentage is computed as `Math.round((100 * event.loaded) / (event.total || 1))` during progress events. Components map these events into reactive signals to drive visual progress bars.",
            keyPoints = listOf(
                "Shows configuration with reportProgress: true and observe: 'events'.",
                "Identifies HttpEventType enum values: UploadProgress, DownloadProgress, Response.",
                "Explains computing progress percentage using event.loaded and event.total.",
                "Demonstrates handling FormData for multi-part file uploads.",
                "Explains filtering events to emit clean progress percentages to UI signals."
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_128",
            trackId = "angular_interview",
            conceptId = "ng_http_networking",
            conceptName = "HttpClient, Interceptors & Networking",
            title = "Runtime Schema Validation with Zod in Angular Services",
            question = "Why is TypeScript type assertion (`httpClient.get<User>('/api/user')`) insufficient for API responses? How do you integrate Zod or Valibot for runtime schema validation and sanitization?",
            shortAnswer = "TypeScript generics (`<User>`) are erased at compile time; if the backend changes a schema, returns null, or sends an unexpected type, runtime JavaScript receives corrupt data without compiler warnings, leading to downstream `Cannot read properties of undefined` crashes. Integrating Zod introduces runtime validation: `const UserSchema = z.object({ id: z.string(), email: z.string().email() }); type User = z.infer<typeof UserSchema>;`. In the service: `return this.http.get('/api/user').pipe(map(data => UserSchema.parse(data)));`. If the response is invalid, Zod throws a descriptive validation error immediately at the API boundary before corrupted state infects the store.",
            keyPoints = listOf(
                "Explains that TypeScript generic types in HttpClient are compile-time type assertions only.",
                "Identifies the risk of silent runtime crashes when backend API schemas drift.",
                "Demonstrates defining schema and inferring TypeScript types using Zod (or Valibot).",
                "Shows piping API response through map(res => schema.parse(res)).",
                "Explains fail-fast benefits: catches bad API contracts directly at the network boundary."
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_ng_129",
            trackId = "angular_interview",
            conceptId = "ng_http_networking",
            conceptName = "HttpClient, Interceptors & Networking",
            title = "CSRF / XSRF Protection: withXsrfConfiguration in Angular",
            question = "How does Angular defend against Cross-Site Request Forgery (CSRF/XSRF) in HttpClient? How do you customize cookie and header names using `withXsrfConfiguration`?",
            shortAnswer = "Angular implements the Double-Submit Cookie pattern for CSRF defense. By default, `HttpClient` looks for a cookie named `XSRF-TOKEN` and, for all mutating requests (`POST`, `PUT`, `DELETE`, etc.), copies its value into an HTTP header named `X-XSRF-TOKEN`. When using non-standard cookie or header names (e.g., `_csrf`), configure it via `provideHttpClient(withXsrfConfiguration({ cookieName: 'MY_CSRF_COOKIE', headerName: 'X-MY-CSRF-HEADER' }))`. For cross-origin requests, Angular intentionally omits CSRF headers for security unless explicitly handled.",
            keyPoints = listOf(
                "Explains the Double-Submit Cookie pattern for CSRF prevention in SPAs.",
                "Identifies default Angular cookie (XSRF-TOKEN) and header (X-XSRF-TOKEN) names.",
                "Demonstrates configuring custom names using withXsrfConfiguration in provideHttpClient.",
                "Explains that CSRF tokens are applied only to mutating requests (non-GET/HEAD).",
                "Notes that CSRF tokens are omitted from cross-origin requests by default for security."
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_130",
            trackId = "angular_interview",
            conceptId = "ng_http_networking",
            conceptName = "HttpClient, Interceptors & Networking",
            title = "Testing HTTP Services with HttpTestingController and provideHttpClientTesting",
            question = "How do you unit test an Angular service making HTTP requests using `provideHttpClientTesting()` and `HttpTestingController` without hitting real network endpoints?",
            shortAnswer = "Tests configure `TestBed.configureTestingModule({ providers: [provideHttpClient(), provideHttpClientTesting(), MyService] })`. In the test: inject `HttpTestingController` and `MyService`. Call the service method: `myService.getUser(1).subscribe(user => expect(user.name).toBe('Alice'));`. Verify and flush the mock request: `const req = httpTestingController.expectOne('/api/users/1'); expect(req.request.method).toBe('GET'); req.flush({ id: 1, name: 'Alice' });`. In `afterEach`, call `httpTestingController.verify()` to guarantee no unexpected or unhandled HTTP requests were made.",
            keyPoints = listOf(
                "Explains setup with provideHttpClient() and provideHttpClientTesting().",
                "Injects HttpTestingController to intercept and assert outgoing requests.",
                "Demonstrates expectOne() matching URL and asserting HTTP method.",
                "Shows using req.flush() to return simulated JSON or error payloads.",
                "Explains httpTestingController.verify() in afterEach to catch dangling/unasserted requests."
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_131",
            trackId = "angular_interview",
            conceptId = "ng_http_networking",
            conceptName = "HttpClient, Interceptors & Networking",
            title = "Server-Sent Events (SSE) Integration in Angular",
            question = "How do you integrate Server-Sent Events (SSE) in Angular using the native `EventSource` API wrapped in an RxJS Observable? How do you ensure connection cleanup upon component destruction?",
            shortAnswer = "SSE provides unidirectional, real-time server-to-client streaming over HTTP. Because `HttpClient` does not natively support SSE streaming, it is wrapped in an `Observable`: `return new Observable<string>(observer => { const eventSource = new EventSource('/api/stream'); eventSource.onmessage = (event) => observer.next(event.data); eventSource.onerror = (err) => observer.error(err); return () => eventSource.close(); });`. The teardown function `() => eventSource.close()` executes automatically when the RxJS stream is unsubscribed (e.g., via `takeUntilDestroyed`), ensuring the persistent HTTP connection closes immediately when the component unmounts.",
            keyPoints = listOf(
                "Defines Server-Sent Events (SSE) as unidirectional server-push HTTP streams.",
                "Wraps browser EventSource API inside an RxJS Observable factory.",
                "Emits stream data via observer.next() and errors via observer.error().",
                "Implements observable teardown logic calling eventSource.close().",
                "Highlights automatic cleanup integration with takeUntilDestroyed() to prevent open connections."
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_ng_132",
            trackId = "angular_interview",
            conceptId = "ng_http_networking",
            conceptName = "HttpClient, Interceptors & Networking",
            title = "WebSocket Architecture and Reconnection Resilience in Angular",
            question = "How do you architect a resilient, bi-directional WebSocket service in Angular using `webSocket` from `rxjs/webSocket`? How do you implement automatic reconnect with exponential backoff?",
            shortAnswer = "`rxjs/webSocket` wraps native WebSockets into an RxJS Subject. For resilience, combine it with retry logic: `const socket\$ = webSocket({ url: 'wss://api.example.com/ws', deserializer: msg => JSON.parse(msg.data) }); return socket\$.pipe(retry({ delay: (err, retryCount) => timer(Math.min(1000 * 2 ** retryCount, 30000)) }));`. Subscribing connects the socket, calling `.next(message)` transmits data, and unsubscribing cleanly disconnects the socket. The `retry` operator monitors connection drops (network loss, server restart) and reconnects using exponential backoff without resetting application state.",
            keyPoints = listOf(
                "Explains rxjs/webSocket wrapper acting as a Subject (both Observer and Observable).",
                "Demonstrates connecting, sending messages (.next()), and receiving messages.",
                "Implements auto-reconnect strategy using retry operator with exponential backoff delay.",
                "Explains clean disconnection lifecycle when the observable subscription completes.",
                "Discusses heartbeat/ping-pong mechanisms to detect silent dead connections."
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_ng_133",
            trackId = "angular_interview",
            conceptId = "ng_http_networking",
            conceptName = "HttpClient, Interceptors & Networking",
            title = "Network Cancellation and Request Deduplication in Search Forms",
            question = "How do you coordinate `debounceTime`, `distinctUntilChanged`, and `switchMap` in an Angular search component to deduplicate requests and cancel stale in-flight HTTP calls?",
            shortAnswer = "In real-time search inputs, rapid typing generates high-frequency events. The search stream is piped: `searchControl.valueChanges.pipe(debounceTime(300), map(query => query.trim()), distinctUntilChanged(), switchMap(query => query ? this.searchService.search(query) : of([])))`. (1) `debounceTime(300)` waits 300ms of user silence before emitting; (2) `distinctUntilChanged()` discards emissions identical to the previous query (e.g., typing and deleting the same character); (3) `switchMap` cancels the previous pending HTTP request if a new query arrives while the request is in-flight, preventing race conditions and stale response overwrites.",
            keyPoints = listOf(
                "Explains debounceTime(300) throttling rapid keyboard input.",
                "Explains distinctUntilChanged() eliminating redundant duplicate queries.",
                "Explains switchMap canceling pending in-flight requests when a new search starts.",
                "Identifies prevention of race condition where old slow request overwrites new fast request.",
                "Shows handling empty queries cleanly with of([]) without triggering network calls."
            ),
            difficulty = "Senior"
        )
    )
    private fun part8(): List<InterviewQuestion> = listOf(
        InterviewQuestion(
            id = "iq_ng_134",
            trackId = "angular_interview",
            conceptId = "ng_http_networking",
            conceptName = "HttpClient, Interceptors & Networking",
            title = "CORS Troubleshooting: Backend Headers vs Angular Reverse Proxy",
            question = "What causes Cross-Origin Resource Sharing (CORS) errors in Angular during development? Why cannot CORS be resolved purely in frontend code, and how does `proxy.conf.json` fix it locally?",
            shortAnswer = "CORS is a browser security mechanism that blocks web applications from making cross-origin requests unless the remote server explicitly sends `Access-Control-Allow-Origin` headers. It cannot be fixed purely in frontend Angular code because browser security policies enforce it. In production, CORS must be configured on the backend server or reverse proxy (Nginx/Cloudflare). In development, Angular CLI solves this using `proxy.conf.json`: `\"/api\": { \"target\": \"http://backend:8080\", \"secure\": false, \"changeOrigin\": true }`. The browser sends requests to localhost (same-origin), and the Node dev server proxies them to the backend server-to-server, bypassing browser CORS checks.",
            keyPoints = listOf(
                "Defines CORS as a browser-enforced security mechanism restricting cross-origin requests.",
                "Explains why frontend code cannot bypass CORS headers returned by remote servers.",
                "Describes browser preflight OPTIONS requests.",
                "Demonstrates configuring proxy.conf.json with target, secure, and changeOrigin.",
                "Explains production solutions: configuring backend CORS headers or Nginx reverse proxies."
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_135",
            trackId = "angular_interview",
            conceptId = "ng_http_networking",
            conceptName = "HttpClient, Interceptors & Networking",
            title = "GraphQL Integration: Apollo Angular vs REST with HttpClient",
            question = "How does GraphQL integration with Apollo Angular differ from standard REST with `HttpClient`? What are the tradeoffs in caching, payload size, and tooling?",
            shortAnswer = "REST with `HttpClient` targets fixed endpoints returning fixed data contracts, often leading to over-fetching (unneeded fields) or under-fetching (requiring waterfall requests). Apollo Angular (`apollo-angular`) queries a single GraphQL endpoint with precise query documents: `apollo.watchQuery({ query: GET_USER, variables: { id } })`. Key advantages: (1) Apollo Client features a normalized in-memory cache that automatically updates related UI components when an entity field changes; (2) Eliminates over-fetching; (3) Generates end-to-end TypeScript types via GraphQL Code Generator. Tradeoffs: added bundle size, complexity of GraphQL server schema maintenance, and loss of standard HTTP caching headers.",
            keyPoints = listOf(
                "Contrasts REST endpoint-centric model with GraphQL declarative query model.",
                "Explains over-fetching and under-fetching problems solved by GraphQL.",
                "Describes Apollo Client normalized in-memory cache and watchQuery reactivity.",
                "Explains type generation using GraphQL Code Generator for type safety.",
                "Discusses tradeoffs: bundle weight, learning curve, and loss of standard HTTP CDN caching."
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_ng_136",
            trackId = "angular_interview",
            conceptId = "ng_http_networking",
            conceptName = "HttpClient, Interceptors & Networking",
            title = "Exponential Backoff and Jitter in API Retry Pipelines",
            question = "Why is naive retry logic harmful to backend infrastructure during outages? How do you implement retry with exponential backoff and randomized jitter in Angular?",
            shortAnswer = "Naive retries (`retry(3)`) immediately pound a struggling backend server with repeated requests at identical timestamps, creating a 'thundering herd' problem that prolongs outages. Exponential backoff exponentially increases the delay between retries: `delay = base * 2^attempt`. Adding full jitter randomizes the delay: `delay = Math.random() * (base * 2^attempt)`. In RxJS: `http.get(url).pipe(retry({ count: 3, delay: (err, retryCount) => { if (err.status >= 500) { const delayTime = Math.random() * (1000 * Math.pow(2, retryCount)); return timer(delayTime); } return throwError(() => err); } }))`. Client errors (4xx) are failed immediately, retrying only transient 5xx or network errors.",
            keyPoints = listOf(
                "Explains the thundering herd problem caused by naive, un-delayed retries.",
                "Defines exponential backoff formula increasing wait times between attempts.",
                "Explains randomized jitter smoothing retry traffic spikes across clients.",
                "Demonstrates RxJS retry operator with conditional delay function checking error status.",
                "Emphasizes skipping retries for client 4xx errors (e.g. 401, 403, 404) that cannot recover."
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_ng_137",
            trackId = "angular_interview",
            conceptId = "ng_http_networking",
            conceptName = "HttpClient, Interceptors & Networking",
            title = "Blob and File Downloads with Progress and Memory Management",
            question = "How do you download large binary files (PDFs, ZIPs) using `HttpClient` with `responseType: 'blob'` without running out of browser memory?",
            shortAnswer = "Request the file: `this.http.get(url, { responseType: 'blob', observe: 'response' }).subscribe(response => { const blob = response.body!; const downloadUrl = URL.createObjectURL(blob); const anchor = document.createElement('a'); anchor.href = downloadUrl; anchor.download = 'export.pdf'; anchor.click(); URL.revokeObjectURL(downloadUrl); });`. Key considerations: (1) `responseType: 'blob'` instructs Angular to treat the stream as raw binary data; (2) `URL.createObjectURL` creates a temporary browser blob URI; (3) Calling `URL.revokeObjectURL(downloadUrl)` immediately after triggering the download releases the blob memory reference from browser memory, preventing major memory leaks with multi-megabyte files.",
            keyPoints = listOf(
                "Shows configuring HttpClient with responseType: 'blob'.",
                "Demonstrates creating blob URL via URL.createObjectURL(blob).",
                "Creates synthetic anchor tag to initiate native browser file download.",
                "Highlights critical cleanup: calling URL.revokeObjectURL() to release memory.",
                "Discusses limitations of in-memory blobs for multi-gigabyte files vs direct stream links."
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_138",
            trackId = "angular_interview",
            conceptId = "ng_http_networking",
            conceptName = "HttpClient, Interceptors & Networking",
            title = "HTTP Request Deduplication / In-Flight Request Merging",
            question = "How do you prevent duplicate in-flight HTTP requests when multiple components simultaneously request the exact same backend endpoint on page load?",
            shortAnswer = "When multiple components mount at the same time and request identical data (e.g., current user profile), redundant network calls occur. This is resolved by caching the in-flight `Observable` in a service: `private cache = new Map<string, Observable<any>>(); getData(url: string): Observable<any> { if (!this.cache.has(url)) { const req\$ = this.http.get(url).pipe(shareReplay({ bufferSize: 1, refCount: true }), finalize(() => this.cache.delete(url))); this.cache.set(url, req\$); } return this.cache.get(url)!; }`. `shareReplay` multicasts the single network call to all concurrent subscribers, and `finalize` cleans the map when the request completes.",
            keyPoints = listOf(
                "Identifies the duplicate request problem when sibling components mount simultaneously.",
                "Maintains an in-flight request map storing Observable references.",
                "Applies shareReplay({ bufferSize: 1, refCount: true }) to multicast single HTTP response.",
                "Uses finalize operator to purge the request from the cache once complete.",
                "Ensures subsequent requests after completion can fetch fresh data if desired."
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_ng_139",
            trackId = "angular_interview",
            conceptId = "ng_http_networking",
            conceptName = "HttpClient, Interceptors & Networking",
            title = "HttpClient Memory Leaks: Myth vs Reality",
            question = "Do `HttpClient` subscriptions cause memory leaks if left uncompleted in destroyed components? What are the rare scenarios where HTTP leaks can actually occur?",
            shortAnswer = "In 99% of standard cases, `HttpClient` requests do NOT cause memory leaks because Angular's `HttpClient` automatically emits `complete` immediately after emitting the single response or error, which terminates the subscription and releases references. However, HTTP leaks CAN occur if: (1) An infinite operator pipeline is chained to the HTTP call (e.g., `interval(1000).pipe(switchMap(() => http.get()))`); (2) The request hangs indefinitely without a timeout; or (3) A pending long-running HTTP request retains a reference to a large component closure in its `subscribe()` callback while the user rapidly navigates away, delaying component garbage collection until the request finishes. Using `takeUntilDestroyed()` or `AsyncPipe` is standard hygiene.",
            keyPoints = listOf(
                "Clarifies that HttpClient observables complete automatically after response/error emission.",
                "Explains that standard single-shot HTTP subscriptions terminate without manual unsubscribe.",
                "Identifies scenarios where leaks occur: chaining with infinite streams (interval, subjects).",
                "Explains delayed garbage collection when in-flight request callbacks retain component closures.",
                "Recommends takeUntilDestroyed() or timeout() as defensive best practice."
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_140",
            trackId = "angular_interview",
            conceptId = "ng_http_networking",
            conceptName = "HttpClient, Interceptors & Networking",
            title = "Batching HTTP Requests and Network Waterfalls",
            question = "How do you eliminate network waterfalls in Angular? Contrast serial HTTP chaining (`concatMap`) with parallel execution (`forkJoin`) and request batching.",
            shortAnswer = "Network waterfalls occur when requests execute sequentially instead of concurrently, drastically increasing total latency: `concatMap` waits for request A before starting B. When requests are independent (e.g., fetching user profile, notifications, and dashboard stats), `forkJoin([this.http.get('/user'), this.http.get('/notifications'), this.http.get('/stats')])` dispatches all requests simultaneously in parallel, completing when all succeed. For enterprise backends supporting batch endpoints (`POST /api/batch`), an HTTP interceptor or batching service can collect individual requests made within a 10ms microtask window and combine them into a single HTTP payload, drastically reducing round-trips.",
            keyPoints = listOf(
                "Explains network waterfalls and cumulative latency impact on page loading.",
                "Contrasts serial execution (concatMap) with parallel concurrent execution (forkJoin).",
                "Demonstrates forkJoin executing independent requests in parallel.",
                "Explains request batching combining multiple calls into a single batch endpoint.",
                "Discusses error isolation in parallel calls (using inner catchError on individual observables)."
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_ng_141",
            trackId = "angular_interview",
            conceptId = "ng_perf_ssr_testing",
            conceptName = "Performance, SSR, Hydration & Testing",
            title = "Modern Angular SSR Architecture with @angular/ssr",
            question = "How does modern Server-Side Rendering (SSR) work in Angular using `@angular/ssr`? How does it replace the legacy `@nguniversal` package and integrate with the Node.js runtime?",
            shortAnswer = "Starting in Angular 17, `@angular/ssr` is the official package replacing legacy `@nguniversal/express-engine`. It integrates natively with the new Vite/esbuild Application Builder (`@angular-devkit/build-angular:application`). During server build, Angular compiles a single `main.server.ts` entry point that exports a `CommonEngine` or `renderApplication` handler. When an HTTP request hits the Node/Express server, `CommonEngine.render({ bootstrap, document, url })` renders the component tree to static HTML, serializes state into `<script id=\"ng-state\">` tags, and streams or sends the full HTML to the client for immediate First Contentful Paint (FCP).",
            keyPoints = listOf(
                "Defines @angular/ssr as the modern official package replacing legacy @nguniversal.",
                "Explains build integration with esbuild/Vite application builder producing server bundle.",
                "Describes CommonEngine.render() taking bootstrap, HTML template, and request URL.",
                "Explains server-rendered HTML delivery improving First Contentful Paint (FCP) and SEO.",
                "Highlights modern server bootstrap configuration: provideServerRendering()."
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_142",
            trackId = "angular_interview",
            conceptId = "ng_perf_ssr_testing",
            conceptName = "Performance, SSR, Hydration & Testing",
            title = "Non-Destructive Hydration and DOM Node Reuse",
            question = "What is non-destructive hydration in Angular 16+? How does `provideClientHydration()` eliminate page flicker compared to legacy destructive DOM replacement?",
            shortAnswer = "In legacy Angular Universal, the client-side app discarded the server-rendered HTML entirely upon bootstrap, clearing the DOM and rebuilding every node from scratch, causing a jarring screen flash/flicker and resetting scroll position and inputs. Non-destructive hydration (`provideClientHydration()`) traverses the existing server-rendered DOM, matches client components to their existing HTML DOM nodes, attaches event listeners, and connects reactive data structures without destroying or recreating any DOM elements. This eliminates flicker, improves Largest Contentful Paint (LCP), and delivers seamless visual continuity.",
            keyPoints = listOf(
                "Identifies the flicker problem in legacy SSR caused by DOM destruction and rebuilding.",
                "Explains non-destructive hydration matching server DOM nodes to client component templates.",
                "Demonstrates configuring provideClientHydration() in app.config.ts.",
                "Highlights preserving existing DOM nodes and attaching event listeners directly.",
                "Explains performance improvements in Core Web Vitals (LCP, CLS, FID/INP)."
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_143",
            trackId = "angular_interview",
            conceptId = "ng_perf_ssr_testing",
            conceptName = "Performance, SSR, Hydration & Testing",
            title = "Event Replay with JSAction in Angular Hydration",
            question = "What is Event Replay in Angular 18+ client hydration? How does `withEventReplay()` capture user interactions occurring before hydration finishes and replay them seamlessly?",
            shortAnswer = "When a page renders via SSR, there is a time gap between the initial HTML paint and the completion of JavaScript hydration during which user clicks would normally be lost (the 'uncanny valley'). Configured via `provideClientHydration(withEventReplay())`, Angular injects a tiny (~1KB) inline script (JSAction) into the server HTML. This script captures and records early user events (clicks, keypresses, focus) in an in-memory queue. Once the Angular client bundle finishes downloading and hydrating, Angular replays those recorded events on the newly hydrated components in exact order, ensuring no user actions are lost.",
            keyPoints = listOf(
                "Defines the uncanny valley: user interacting with SSR page before JS hydration completes.",
                "Explains withEventReplay() feature flag in provideClientHydration.",
                "Describes lightweight inline JSAction listener recording events during page load.",
                "Explains event replaying in exact chronological order once components finish hydration.",
                "Highlights direct positive impact on Interaction to Next Paint (INP) metric."
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_ng_144",
            trackId = "angular_interview",
            conceptId = "ng_perf_ssr_testing",
            conceptName = "Performance, SSR, Hydration & Testing",
            title = "Incremental and Deferred Hydration with @defer",
            question = "What is Incremental / Deferred Hydration introduced in Angular 18-19? How does `@defer (hydrate on interaction)` optimize client-side CPU overhead for below-the-fold components?",
            shortAnswer = "Traditional hydration hydrates the entire page at once upon bootstrap, consuming significant main-thread CPU. Incremental hydration combines `@defer` blocks with hydration triggers: `@defer (hydrate on idle; hydrate on interaction)` or `@defer (hydrate when viewport)`. While the server renders the full HTML during SSR, the client keeps these deferred sections in a lightweight, inactive HTML state. Client JavaScript for that component is only downloaded and hydrated when the trigger condition is met (e.g., when scrolled into view or clicked). This drastically slashes Initial Total Blocking Time (TBT) and JavaScript parse/execution costs.",
            keyPoints = listOf(
                "Explains the limitation of monolithic full-page hydration on initial CPU workload.",
                "Defines incremental hydration: server renders HTML, client delays hydration until needed.",
                "Shows @defer hydration syntax: (hydrate on idle), (hydrate on interaction), (hydrate when viewport).",
                "Explains lazy downloading of component code only when hydration trigger is satisfied.",
                "Highlights dramatic reduction in Total Blocking Time (TBT) on mobile devices."
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_ng_145",
            trackId = "angular_interview",
            conceptId = "ng_perf_ssr_testing",
            conceptName = "Performance, SSR, Hydration & Testing",
            title = "TransferState API: Preventing Duplicate Network Requests",
            question = "What is the `TransferState` API in Angular SSR? How does `withHttpTransferCache()` automatically serialize server HTTP responses to prevent duplicate client fetches?",
            shortAnswer = "Without state transfer, the server makes HTTP API calls to render the HTML, and then upon client bootstrap, the client application re-executes the exact same HTTP requests, doubling server load and causing layout shifts. `TransferState` is an in-memory key-value store. Modern Angular provides `withHttpTransferCache()` in `provideClientHydration()`. The server automatically intercepts GET/HEAD HTTP requests, serializes the response bodies into a `<script id=\"ng-state\" type=\"application/json\">` tag in the HTML footer. On the client, `HttpClient` checks this cache first; if present, it returns the cached data immediately and removes it, eliminating duplicate network calls.",
            keyPoints = listOf(
                "Identifies duplicate HTTP fetch problem between server render and client bootstrap.",
                "Explains TransferState key-value store serialized into HTML payload.",
                "Describes withHttpTransferCache() automated transfer of HttpClient GET responses.",
                "Explains client-side cache consumption: HttpClient reads script tag and bypasses network.",
                "Discusses configuring transfer cache filters and headers via TransferCacheOptions."
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_146",
            trackId = "angular_interview",
            conceptId = "ng_perf_ssr_testing",
            conceptName = "Performance, SSR, Hydration & Testing",
            title = "Vite and esbuild Application Builder in Modern Angular CLI",
            question = "How does the modern `@angular-devkit/build-angular:application` builder utilize esbuild and Vite? What architectural improvements does it offer over legacy Webpack builds?",
            shortAnswer = "Starting in Angular 17, Angular CLI defaults to the `application` builder powered by esbuild for bundling and Vite for the local development dev-server. esbuild is written in Go and parallelizes compilation, delivering 3x-10x faster build times and native ESM module emission. Vite provides instantaneous dev server start and lightning-fast Hot Module Replacement (HMR) by serving source code over native ESM without re-bundling the entire application on every file change. The application builder also simultaneously compiles both browser and server (SSR) bundles in a single unified pipeline.",
            keyPoints = listOf(
                "Identifies modern application builder replacing legacy Webpack-based browser builder.",
                "Explains esbuild engine benefits: written in Go, massive multi-core compilation speedup.",
                "Explains Vite dev server role: native ESM serving, fast startup, instant HMR.",
                "Describes unified build pipeline producing both client and server bundles concurrently.",
                "Mentions bundle size improvements through advanced tree-shaking and modern JS targets."
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_147",
            trackId = "angular_interview",
            conceptId = "ng_perf_ssr_testing",
            conceptName = "Performance, SSR, Hydration & Testing",
            title = "NgOptimizedImage Directive: Core Web Vitals and LCP Optimization",
            question = "How does the `NgOptimizedImage` (`ngSrc`) directive optimize Largest Contentful Paint (LCP) and prevent Cumulative Layout Shift (CLS)?",
            shortAnswer = "`NgOptimizedImage` enforces modern web imaging standards: (1) Requires explicit `width` and `height` (or `fill`) attributes, reserving DOM aspect-ratio space to eliminate Cumulative Layout Shift (CLS); (2) Generates automatic `srcset` and `sizes` attributes for responsive resolution switching; (3) The `priority` attribute flags LCP hero images, automatically disabling lazy loading, adding high fetchpriority (`fetchpriority=\"high\"`), and generating `<link rel=\"preload\">` in the HTML head during SSR; (4) Supports built-in image CDN loaders (Cloudinary, Imgix, Cloudflare); (5) Warns in console if images are distorted or oversized relative to rendered size.",
            keyPoints = listOf(
                "Explains ngSrc replacing plain src attribute for automated optimization.",
                "Details CLS prevention via mandatory width/height or fill layout attributes.",
                "Explains priority attribute optimizing LCP via preloading and fetchpriority='high'.",
                "Describes automatic responsive srcset generation and image CDN loader integration.",
                "Highlights built-in runtime warnings for distorted aspect ratios or heavy images."
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_148",
            trackId = "angular_interview",
            conceptId = "ng_perf_ssr_testing",
            conceptName = "Performance, SSR, Hydration & Testing",
            title = "Optimizing Core Web Vitals: LCP, INP, and CLS in Angular",
            question = "Detail specific techniques in Angular to optimize the three Core Web Vitals: Largest Contentful Paint (LCP), Interaction to Next Paint (INP), and Cumulative Layout Shift (CLS).",
            shortAnswer = "(1) LCP: Use SSR with non-destructive hydration for fast initial HTML; apply `ngSrc` with `priority` on hero images; preconnect to font/API domains; eliminate render-blocking CSS/JS. (2) INP (Interaction to Next Paint): Replace Zone.js with Zoneless/Signals to remove macro-task monkey-patching overhead; break long-running tasks across microtasks using `scheduler.yield()` or Web Workers; use `@defer (hydrate on interaction)` with Event Replay. (3) CLS: Always reserve explicit dimensions for images/embeds/ads; use `@defer` with `@placeholder (minimum 200ms)` matching actual content size; inline critical fonts (`font-display: optional` or `swap`) to eliminate FOIT/FOUT font shift.",
            keyPoints = listOf(
                "Details LCP optimizations: SSR, ngSrc priority, preconnect, critical resource inlining.",
                "Details INP optimizations: Zoneless change detection, yielding to main thread, Event Replay.",
                "Details CLS optimizations: aspect-ratio boxes, placeholder dimensions in @defer, font inlining.",
                "Explains Total Blocking Time (TBT) relationship to INP during page initialization.",
                "Describes measuring Web Vitals using Chrome Web Vitals library inside Angular services."
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_ng_149",
            trackId = "angular_interview",
            conceptId = "ng_perf_ssr_testing",
            conceptName = "Performance, SSR, Hydration & Testing",
            title = "XSS Defense and DomSanitizer Security Contexts",
            question = "How does Angular defend against Cross-Site Scripting (XSS) by default? When and why is using `DomSanitizer.bypassSecurityTrustHtml` extremely dangerous?",
            shortAnswer = "Angular treats all untrusted values as untrusted by default. When binding values to `[innerHTML]`, `[href]`, or `[src]`, Angular automatically sanitizes values across six security contexts (`HTML`, `STYLE`, `SCRIPT`, `URL`, `RESOURCE_URL`) by stripping executable `<script>` tags, inline event attributes (`onerror=alert(1)`), and dangerous URI schemes (`javascript:`). `DomSanitizer` provides bypass methods (`bypassSecurityTrustHtml`, `bypassSecurityTrustResourceUrl`). Using them tells Angular to skip sanitization entirely. If user-supplied input or unsanitized API content is passed into a bypass method, an attacker can execute arbitrary JavaScript in the victim's session, resulting in complete account takeover.",
            keyPoints = listOf(
                "Explains Angular default auto-sanitization across HTML, Style, and URL contexts.",
                "Identifies dangerous DOM attributes stripped by default (scripts, onerror, javascript: URIs).",
                "Explains bypassSecurityTrustHtml and other bypass methods in DomSanitizer.",
                "Details the severe security vulnerability when bypass methods receive untrusted user inputs.",
                "Recommends safe alternatives: DOMPurify sanitization before bypassing if HTML is unavoidable."
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_150",
            trackId = "angular_interview",
            conceptId = "ng_perf_ssr_testing",
            conceptName = "Performance, SSR, Hydration & Testing",
            title = "Strict Content Security Policy (CSP) and Nonce Injection",
            question = "How do you configure a strict Content Security Policy (CSP) without `unsafe-inline` in an Angular application? How does Angular support cryptographic nonces for inline styles?",
            shortAnswer = "A strict CSP header blocks XSS by rejecting unauthorized inline scripts/styles: `Content-Security-Policy: default-src 'self'; script-src 'nonce-{random}'; style-src 'nonce-{random}'`. Angular components historically injected inline `<style>` tags into the `<head>` dynamically. To support strict CSP without `unsafe-inline`, Angular allows passing a cryptographic nonce via an HTML attribute `<app-root ngCspNonce=\"{random}\">` or DI token `CSP_NONCE`. In SSR, the server generates a unique cryptographic nonce per request, injects it into both the HTTP response header and `CSP_NONCE`, and Angular automatically appends `nonce=\"...\"` to every injected style tag.",
            keyPoints = listOf(
                "Defines strict Content Security Policy (CSP) and why unsafe-inline weakens security.",
                "Explains the challenge of Angular dynamically injecting component style tags into <head>.",
                "Demonstrates configuring CSP_NONCE injection token or ngCspNonce attribute.",
                "Describes SSR server generating per-request random nonces matching HTTP CSP headers.",
                "Confirms that modern Angular esbuild pipeline produces zero inline script tags by default."
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_ng_151",
            trackId = "angular_interview",
            conceptId = "ng_perf_ssr_testing",
            conceptName = "Performance, SSR, Hydration & Testing",
            title = "Component Test Harnesses in Angular CDK",
            question = "What are Component Test Harnesses (`@angular/cdk/testing`)? Why do they produce more resilient, maintainable unit and E2E tests than direct DOM querying (`By.css`)?",
            shortAnswer = "Component Test Harnesses implement the Page Object Pattern for UI components. Instead of brittle DOM queries (`fixture.debugElement.query(By.css('.mat-select-trigger')).nativeElement.click()`), tests interact with a strongly typed harness: `const select = await loader.getHarness(MatSelectHarness); await select.open(); await select.clickOptions({ text: 'Admin' });`. Harnesses encapsulate internal DOM structure, CSS class names, and ARIA attributes. When component internals or design systems change (e.g., updating Angular Material versions), component test cases do not break as long as the public harness contract is preserved. Harnesses work interchangeably across Karma, Jest, Vitest, and Playwright.",
            keyPoints = listOf(
                "Defines Component Test Harnesses as strongly typed Page Objects for UI components.",
                "Contrasts harness API with brittle manual CSS selector queries (By.css).",
                "Shows harnessing Angular Material components (e.g. MatSelectHarness, MatButtonHarness).",
                "Explains decoupling tests from internal component DOM structures and CSS class names.",
                "Highlights cross-runner portability across unit tests (Karma/Vitest) and E2E (Playwright)."
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_152",
            trackId = "angular_interview",
            conceptId = "ng_perf_ssr_testing",
            conceptName = "Performance, SSR, Hydration & Testing",
            title = "Migrating from Karma to Vitest and Jest in Modern Angular",
            question = "Why has Karma been deprecated by the Angular team? How does migrating to Vitest or Jest improve CI/CD pipeline test execution speed and developer experience?",
            shortAnswer = "Karma runs tests inside actual browser instances (Chrome/Firefox) via an outdated socket-based architecture. It is slow to start, requires heavy headless browser dependencies on CI machines, and does not support modern ESM tooling natively. Karma has been officially deprecated. Modern Angular supports Vitest and Jest using native Node.js environments (jsdom or happy-dom). Vitest utilizes the same Vite pipeline as the modern Angular dev server, supporting instant watch-mode updates, multi-threaded test isolation, out-of-the-box ESM/TypeScript execution, and cuts CI test execution times by 70-80% without browser spin-up overhead.",
            keyPoints = listOf(
                "Explains reasons for Karma deprecation: slow browser launch, heavy CI overhead, legacy architecture.",
                "Describes Vitest and Jest running in Node.js with simulated DOM (jsdom / happy-dom).",
                "Highlights Vitest integration with Vite build tools and native ESM compilation.",
                "Demonstrates massive speed improvements in CI test suites (multi-threading, no browser launch).",
                "Explains watch mode and developer feedback loop enhancements."
            ),
            difficulty = "Senior"
        )
    )
    private fun part9(): List<InterviewQuestion> = listOf(
        InterviewQuestion(
            id = "iq_ng_153",
            trackId = "angular_interview",
            conceptId = "ng_perf_ssr_testing",
            conceptName = "Performance, SSR, Hydration & Testing",
            title = "E2E Testing Angular Applications with Playwright",
            question = "How do you architect an End-to-End (E2E) testing suite for modern Angular using Playwright? How do you handle client hydration wait times and network mock fixtures?",
            shortAnswer = "Playwright provides modern, reliable E2E testing across Chromium, WebKit, and Firefox. In Angular, Playwright tests use `page.goto('/login')` and leverage user-facing locators (`page.getByRole('button', { name: 'Submit' })`). Playwright's auto-waiting mechanism automatically waits for elements to be actionable, eliminating manual `waitForTimeout` sleeps. For SSR/Hydration apps, tests can assert that server-rendered text is visible, verify no console hydration mismatch errors occurred, and use `page.route('**/api/**', route => route.fulfill({ json: mockData }))` to intercept network calls deterministically without hitting staging databases.",
            keyPoints = listOf(
                "Explains Playwright architecture and advantages over legacy Protractor.",
                "Demonstrates role-based locators (getByRole, getByLabel) promoting accessibility.",
                "Explains automatic waiting avoiding brittle manual sleep calls.",
                "Describes intercepting and mocking backend API calls via page.route().",
                "Validates hydration stability by asserting no console hydration mismatch warnings."
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_ng_154",
            trackId = "angular_interview",
            conceptId = "ng_perf_ssr_testing",
            conceptName = "Performance, SSR, Hydration & Testing",
            title = "Chrome DevTools Memory Profiling and Heap Snapshots in Angular",
            question = "How do you identify and diagnose JavaScript memory leaks in Angular applications using Chrome DevTools Heap Snapshots and Allocation Instrumentation?",
            shortAnswer = "To diagnose leaks: (1) Record a baseline Heap Snapshot in Chrome DevTools; (2) Perform user actions in the app (e.g., navigate to a route 5 times and return to home); (3) Force garbage collection (trash icon) and capture a second Heap Snapshot; (4) In the 'Comparison' view, filter by 'Constructor' names like `Component`, `Subscription`, or `HTMLDivElement` to identify objects whose count increased; (5) Inspect the 'Retainers' tree for retained components: look for active RxJS subscriptions (`Subject`, `Observable`), window event listeners (`fromEvent`), or global singletons retaining component closures in memory.",
            keyPoints = listOf(
                "Outlines step-by-step heap snapshot comparison workflow in Chrome DevTools.",
                "Explains forcing manual garbage collection before taking comparison snapshots.",
                "Identifies key leaky objects in comparison view: Components, Subjects, Subscribers, Detached DOM.",
                "Explains inspecting Retainers tree to trace the retaining root reference.",
                "Points out common culprits: global service event listeners, uncleared intervals, uncompleted streams."
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_ng_155",
            trackId = "angular_interview",
            conceptId = "ng_perf_ssr_testing",
            conceptName = "Performance, SSR, Hydration & Testing",
            title = "Detached DOM Tree Leaks in Angular",
            question = "What is a 'Detached DOM tree' memory leak in Angular? How do uncleaned ViewContainerRef operations or direct Renderer2 element references cause detached DOM retention?",
            shortAnswer = "A Detached DOM tree leak occurs when DOM nodes have been removed from the visible page document by Angular, but JavaScript memory references (closures, services, arrays) still hold pointers to those DOM nodes, preventing the browser's garbage collector from freeing the memory. In Angular, this happens when: (1) Storing a `NativeElement` or `ViewRef` in a singleton service array without pruning on component destroy; (2) Attaching a native event listener via `Renderer2` or `addEventListener` on a DOM node without calling the teardown removal function; (3) Storing DOM elements in global cache objects.",
            keyPoints = listOf(
                "Defines a Detached DOM tree: nodes removed from active document but retained in JS memory.",
                "Explains how detached DOM trees prevent garbage collection of entire subtrees and components.",
                "Identifies causes: singleton services holding ViewRef or nativeElement references.",
                "Explains uncleaned DOM event listeners (addEventListener without removeEventListener).",
                "Demonstrates locating detached nodes in Chrome DevTools Heap Snapshots under 'Detached'."
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_ng_156",
            trackId = "angular_interview",
            conceptId = "ng_perf_ssr_testing",
            conceptName = "Performance, SSR, Hydration & Testing",
            title = "Offloading CPU Intensive Work with Angular Web Workers",
            question = "How do you offload CPU-intensive computations (e.g., large CSV parsing, cryptographic hashing, image processing) to Web Workers using Angular CLI (`ng generate web-worker`)?",
            shortAnswer = "Angular CLI automates Web Worker setup: `ng generate web-worker my-worker`. This creates a worker script and configures `tsconfig.worker.json`. The main component instantiates the worker: `const worker = new Worker(new URL('./my-worker.worker', import.meta.url), { type: 'module' });`. Communication is asynchronous: `worker.postMessage({ data: rawCsv });` sends data across threads via structured cloning. The worker processes the heavy computation in a background thread without blocking the browser main thread, emitting results back via `postMessage(parsedResults)`. The main thread listens via `worker.onmessage`, keeping UI animations and frame rates locked at a smooth 60fps.",
            keyPoints = listOf(
                "Explains using ng generate web-worker to scaffold Web Worker with Angular CLI.",
                "Shows instantiating worker using new Worker(new URL(..., import.meta.url)).",
                "Describes postMessage and onmessage event-driven bi-directional communication.",
                "Explains offloading heavy CPU computation off the main browser UI thread.",
                "Highlights UI responsiveness benefits: maintaining 60fps frame rate without freezing inputs."
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_157",
            trackId = "angular_interview",
            conceptId = "ng_perf_ssr_testing",
            conceptName = "Performance, SSR, Hydration & Testing",
            title = "PWA and Service Worker Caching Strategies in Angular",
            question = "How does `@angular/pwa` configure Service Workers? Explain the difference between `performance` and `freshness` data caching strategies in `ngsw-config.json`.",
            shortAnswer = "Adding `@angular/pwa` configures `ngsw-config.json` and registers Angular's Service Worker (`provideServiceWorker`). In `dataGroups`: (1) `cacheConfig: { strategy: 'performance' }` (Cache-First): checks the cache first; if cached, returns it immediately and only fetches from network if the cache has expired (TTL). Ideal for static resources and rarely changing datasets (e.g., product catalog). (2) `cacheConfig: { strategy: 'freshness' }` (Network-First): attempts network fetch first with a configurable `timeout`; falls back to cache only if offline or network times out. Ideal for volatile data like stock prices or account balances.",
            keyPoints = listOf(
                "Explains @angular/pwa setup and ngsw-config.json configuration manifest.",
                "Defines assetGroups for static app shell caching (installMode: prefetch/lazy).",
                "Explains strategy: 'performance' (Cache-First) for fast response times and offline capability.",
                "Explains strategy: 'freshness' (Network-First) with networkTimeout for volatile data.",
                "Discusses SwUpdate service for detecting and prompting users for application updates."
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_158",
            trackId = "angular_interview",
            conceptId = "ng_perf_ssr_testing",
            conceptName = "Performance, SSR, Hydration & Testing",
            title = "Virtual Scrolling with Angular CDK CdkVirtualScrollViewport",
            question = "How does `CdkVirtualScrollViewport` from `@angular/cdk/scrolling` render massive datasets (100,000+ items) efficiently without browser DOM degradation?",
            shortAnswer = "Rendering tens of thousands of DOM elements degrades browser performance due to excessive memory usage and heavy layout recalculations. `CdkVirtualScrollViewport` renders only the small subset of items that currently fit inside the visible scroll viewport plus a small buffer before and after. Template syntax: `<cdk-virtual-scroll-viewport itemSize=\"50\" class=\"viewport\"><div *cdkVirtualFor=\"let item of largeList\">{{ item.name }}</div></cdk-virtual-scroll-viewport>`. As the user scrolls, the viewport dynamically recycles and rebinds existing DOM nodes and translates their CSS positioning, maintaining a constant DOM node count (~20-30 elements) regardless of list size.",
            keyPoints = listOf(
                "Explains browser rendering bottlenecks when rendering massive DOM lists.",
                "Describes virtual scrolling mechanism: rendering only items in the visible viewport.",
                "Shows template syntax: cdk-virtual-scroll-viewport with itemSize and *cdkVirtualFor.",
                "Explains DOM node recycling and constant memory footprint regardless of item count.",
                "Discusses handling dynamic item sizes with autosize virtual scroll strategies."
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_159",
            trackId = "angular_interview",
            conceptId = "ng_perf_ssr_testing",
            conceptName = "Performance, SSR, Hydration & Testing",
            title = "SSR Without DOM: Safe Browser vs Server Code Execution",
            question = "How do you safeguard code that relies on browser-only globals (`window`, `document`, `localStorage`) from crashing during Angular SSR? Compare `isPlatformBrowser` with `afterNextRender`.",
            shortAnswer = "In Node.js SSR, browser globals like `window`, `document`, and `localStorage` do not exist; accessing them directly throws `ReferenceError: window is not defined`. Safe patterns: (1) `isPlatformBrowser(platformId)`: Inject `PLATFORM_ID` and wrap browser calls in `if (isPlatformBrowser(this.platformId)) { ... }`. (2) `afterNextRender` / `afterRender`: Modern Angular provides these lifecycle functions which are guaranteed *never* to execute on the server during SSR. Code placed inside `afterNextRender(() => { const w = window.innerWidth; })` runs purely in the client browser after initial DOM rendering, eliminating runtime platform checks.",
            keyPoints = listOf(
                "Explains why accessing window/localStorage crashes Node.js server rendering.",
                "Demonstrates injecting PLATFORM_ID and using isPlatformBrowser / isPlatformServer.",
                "Introduces modern afterNextRender() and afterRender() hooks as browser-only guarantees.",
                "Explains abstracting browser storage into injectable services for mockability.",
                "Warns against performing DOM reads during SSR constructor or ngOnInit."
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_160",
            trackId = "angular_interview",
            conceptId = "ng_perf_ssr_testing",
            conceptName = "Performance, SSR, Hydration & Testing",
            title = "Static Site Generation (SSG) and Prerendering in Modern Angular",
            question = "How does Static Site Generation (SSG) / Prerendering work in Angular 17+? How do you configure parameterized routes using a routes file or `prerender` options?",
            shortAnswer = "SSG prerenders routes to static static HTML files during the build step (`ng build`), eliminating server runtime rendering overhead and enabling zero-compute hosting on static CDNs (S3, Vercel, Netlify). Configured in `angular.json` under `prerender: true` for the application builder. For dynamic routes with parameters (e.g., `/products/:id`), Angular allows specifying a parameterized routes file: `prerender: { discoverRoutes: true, routesFile: 'routes.txt' }` or implementing dynamic route generators. During build, Angular loops through all discovered routes, runs SSR locally, writes out `index.html` per route, and packages them for CDN distribution.",
            keyPoints = listOf(
                "Defines Static Site Generation (SSG) as pre-compiling routes to static HTML at build time.",
                "Explains benefits: zero server compute, instant CDN TTFB, improved security and SEO.",
                "Demonstrates configuring prerender options in angular.json application builder.",
                "Explains providing dynamic routes via routesFile or route discovery functions.",
                "Contrasts SSG build-time rendering with SSR on-demand request-time rendering."
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_161",
            trackId = "angular_interview",
            conceptId = "ng_perf_ssr_testing",
            conceptName = "Performance, SSR, Hydration & Testing",
            title = "Internationalization (i18n): Compile-Time vs Runtime Localization",
            question = "Compare compile-time localization using `@angular/localize` with runtime localization using libraries like `ngx-translate` or `@transloco`. What are the bundle size and performance tradeoffs?",
            shortAnswer = "`@angular/localize` is Angular's official compile-time i18n solution. The compiler inlines translated strings directly into template bytecode during build, creating separate production bundles per locale (e.g., `/en/`, `/es/`). This results in zero runtime translation overhead, zero runtime translation JSON parsing, and the smallest possible client bundle. However, switching languages requires a full page reload. Runtime libraries (`ngx-translate`, `@transloco`) load JSON translation files dynamically at runtime over HTTP, allowing instant in-place language switching without reloads, but introducing extra runtime bundle weight and potential translation flickering.",
            keyPoints = listOf(
                "Explains compile-time localization inlining translations directly into build artifacts.",
                "Explains runtime localization downloading JSON translation dictionaries on demand.",
                "Highlights compile-time benefits: zero runtime overhead, minimal bundle size, SEO friendly.",
                "Identifies compile-time limitation: switching languages requires navigating to locale URL.",
                "Highlights runtime benefits: instant language switching without full page reload."
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_ng_162",
            trackId = "angular_interview",
            conceptId = "ng_perf_ssr_testing",
            conceptName = "Performance, SSR, Hydration & Testing",
            title = "Microtask Queue Starvation vs Macrotask Scheduling in Angular",
            question = "How can recursive microtask scheduling starve the browser rendering pipeline in Angular? How do you use macrotask scheduling (`setTimeout`, `scheduler.yield`) to prevent UI unresponsiveness?",
            shortAnswer = "The browser event loop processes all queued microtasks (`Promise.resolve()`, `queueMicrotask()`) before yielding control to the rendering pipeline or executing macrotasks. If Angular components recursively queue microtasks (e.g., chained Promise resolutions or uncontrolled signal write effects), the microtask queue never empties, starving the browser of render opportunities and freezing the UI. To prevent this, long-running batch work should yield to the main thread via macrotasks: `setTimeout(fn, 0)`, `requestAnimationFrame()`, or modern `scheduler.yield()`, allowing the browser to recalculate styles, paint frames, and process user input.",
            keyPoints = listOf(
                "Explains event loop execution order: call stack -> microtasks -> render -> macrotasks.",
                "Defines microtask queue starvation: continuous microtask queuing preventing browser paint.",
                "Identifies dangerous patterns: recursive Promise chains or cascading reactive effects.",
                "Demonstrates breaking long-running operations with setTimeout(fn, 0) or scheduler.yield().",
                "Explains impact on Interaction to Next Paint (INP) and frame drop prevention."
            ),
            difficulty = "Staff"
        ),
        InterviewQuestion(
            id = "iq_ng_163",
            trackId = "angular_interview",
            conceptId = "ng_perf_ssr_testing",
            conceptName = "Performance, SSR, Hydration & Testing",
            title = "Font Optimization and Inline Font Inlining in Angular CLI",
            question = "How does Angular CLI automatically optimize web fonts during production builds? How does font inlining prevent Flash of Unstyled Text (FOUT) and Flash of Invisible Text (FOIT)?",
            shortAnswer = "In production builds, Angular CLI automatically detects external Google Fonts or Typekit links declared in `index.html`. It fetches the CSS font definitions at build time and inlines the `@font-face` declarations directly into the `<head>` of the generated HTML. Additionally, it can preconnect to font domains (`preconnect`) and inlines critical font glyphs. This eliminates the extra network round-trip to download font stylesheets, significantly reducing Flash of Invisible Text (FOIT) and Flash of Unstyled Text (FOUT), which directly improves First Contentful Paint (FCP) and Cumulative Layout Shift (CLS).",
            keyPoints = listOf(
                "Explains Angular CLI automated build-time font inlining for Google Fonts.",
                "Defines FOUT (Flash of Unstyled Text) and FOIT (Flash of Invisible Text).",
                "Describes eliminating the font stylesheet network round-trip at page load.",
                "Explains preconnect and preload resource hints generated for font files.",
                "Connects font optimization directly to Cumulative Layout Shift (CLS) reduction."
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_164",
            trackId = "angular_interview",
            conceptId = "ng_perf_ssr_testing",
            conceptName = "Performance, SSR, Hydration & Testing",
            title = "Angular Animations vs Pure CSS Transitions: Performance Tradeoffs",
            question = "Compare `@angular/animations` with pure CSS transitions/animations. When should you use pure CSS over the Angular animations engine for 60fps performance?",
            shortAnswer = "`@angular/animations` provides a powerful DSL (`trigger`, `transition`, `animate`) integrated with Angular component lifecycles (e.g., `:enter`, `:leave` animations when elements are inserted/removed by `*ngIf` or `@if`). However, it runs JavaScript animation drivers, adds ~15KB to bundle size, and can consume main-thread CPU. Pure CSS transitions and keyframe animations running on `transform` and `opacity` are composited on the browser's GPU compositor thread, guaranteeing silky 60fps performance even when the JavaScript main thread is busy. Best practice: use pure CSS for hover states, loaders, and micro-interactions; use Angular animations or View Transitions for complex lifecycle-bound route transitions.",
            keyPoints = listOf(
                "Explains @angular/animations DSL and lifecycle awareness (:enter, :leave).",
                "Identifies tradeoffs of Angular animation engine: bundle weight, JS driver overhead.",
                "Explains GPU hardware acceleration for pure CSS transform and opacity transitions.",
                "Describes compositor thread execution independent of main-thread JavaScript execution.",
                "Provides architectural rule: CSS for UI micro-interactions; Angular/View Transitions for layout routes."
            ),
            difficulty = "Senior"
        ),
        InterviewQuestion(
            id = "iq_ng_165",
            trackId = "angular_interview",
            conceptId = "ng_perf_ssr_testing",
            conceptName = "Performance, SSR, Hydration & Testing",
            title = "Enterprise Production Deployment Checklist and Security Auditing",
            question = "What critical security and performance configurations must be verified before deploying an enterprise Angular application to production?",
            shortAnswer = "An enterprise deployment checklist includes: (1) Production Build: verify `optimization: true`, `sourceMap: false` (or private upload to Sentry), and strict tree-shaking; (2) Security Headers: configure strict CSP with nonces, HSTS (`Strict-Transport-Security`), `X-Frame-Options: DENY`, `X-Content-Type-Options: nosniff`; (3) CORS & CSRF: enable Double-Submit cookies (`withXsrfConfiguration`) and restrict CORS origins; (4) SSR Hygiene: verify `isPlatformBrowser` guards and eliminate memory leaks in singleton services; (5) Dependency Audit: run `npm audit` and Snyk checks to eliminate vulnerable transitive packages; (6) Cache-Control: set `immutable` long-term caching for hashed JS/CSS assets and `no-cache` for `index.html`.",
            keyPoints = listOf(
                "Verifies production build flags: optimization, minification, tree-shaking, source-map protection.",
                "Configures essential HTTP security headers: CSP, HSTS, X-Frame-Options, X-Content-Type-Options.",
                "Validates CSRF protection and CORS origin lockdown.",
                "Audits SSR safety: ensuring no server-side memory leaks or unhandled browser global references.",
                "Details CDN caching strategy: immutable caching for hashed bundles vs no-cache for index.html."
            ),
            difficulty = "Staff"
        )
    )
}
