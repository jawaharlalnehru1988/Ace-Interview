package com.example.presentation.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.SuccessGreen

object JavaCodeHighlighter {

    val KeywordColor = Color(0xFFC678DD)      // Vibrant purple
    val TypeColor = Color(0xFFE5C07B)         // Golden amber / Yellow-orange
    val MethodColor = Color(0xFF61AFEF)       // Sky blue
    val StringColor = Color(0xFF98C379)       // Emerald green
    val NumberColor = Color(0xFFD19A66)       // Warm peach / Orange
    val CommentColor = Color(0xFF7F848E)      // Muted slate gray
    val AnnotationColor = Color(0xFFE5C07B)   // Warm gold
    val OperatorColor = Color(0xFF56B6C2)     // Cyan
    val PunctuationColor = Color(0xFFABB2BF)  // Soft silver
    val DefaultColor = Color(0xFFE6EDF3)      // Crisp soft white

    private val KEYWORDS = setOf(
        "abstract", "assert", "boolean", "break", "byte", "case", "catch", "char",
        "class", "const", "continue", "default", "do", "double", "else", "enum",
        "extends", "final", "finally", "float", "for", "goto", "if", "implements",
        "import", "instanceof", "int", "interface", "long", "native", "new",
        "package", "private", "protected", "public", "return", "short", "static",
        "strictfp", "super", "switch", "synchronized", "this", "throw", "throws",
        "transient", "try", "void", "volatile", "while", "null", "true", "false",
        "var", "record", "sealed", "permits", "yield"
    )

    private val STANDARD_TYPES = setOf(
        "String", "Integer", "Long", "Double", "Float", "Boolean", "Character",
        "Byte", "Short", "Object", "Math", "Arrays", "Collections", "List",
        "ArrayList", "LinkedList", "Map", "HashMap", "TreeMap", "LinkedHashMap",
        "Set", "HashSet", "TreeSet", "LinkedHashSet", "Queue", "Deque",
        "ArrayDeque", "PriorityQueue", "Stack", "StringBuilder", "StringBuffer",
        "TreeNode", "ListNode", "Solution", "Comparator", "Comparable", "Iterator",
        "Optional", "Stream", "Collectors", "BigInteger", "BigDecimal"
    )

    private val TOKEN_REGEX = Regex(
        "(?://.*|/\\*[\\s\\S]*?\\*/)" + // Comments
        "|(?:\"(?:\\\\.|[^\"\\\\])*\"|'(?:\\\\.|[^'\\\\])*')" + // Strings and Characters
        "|(?:@[A-Za-z_][A-Za-z0-9_]*)" + // Annotations
        "|(?:\\b0x[0-9a-fA-F]+\\b|\\b\\d+(?:\\.\\d+)?(?:[fFdDlL])?\\b)" + // Numbers
        "|(?:\\b[A-Za-z_][A-Za-z0-9_]*\\b(?=\\s*\\())" + // Methods
        "|(?:\\b[A-Za-z_][A-Za-z0-9_]*\\b)" + // Identifiers & keywords
        "|(?:[+\\-*/%=<>!&|^~?:]+)" + // Operators
        "|(?:\n)" + // Newline
        "|(?:\\s+)" + // Whitespace
        "|(?:.)" // Delimiters & punctuation
    )

    fun highlight(code: String): AnnotatedString {
        return buildAnnotatedString {
            var lastIndex = 0
            for (match in TOKEN_REGEX.findAll(code)) {
                val token = match.value
                val range = match.range

                // Catch any unparsed gaps
                if (range.first > lastIndex) {
                    append(code.substring(lastIndex, range.first))
                }
                lastIndex = range.last + 1

                when {
                    token.startsWith("//") || token.startsWith("/*") -> {
                        val style = SpanStyle(
                            color = CommentColor,
                            fontStyle = FontStyle.Italic
                        )
                        pushStyle(style)
                        append(token)
                        pop()
                    }
                    token.startsWith("\"") || token.startsWith("'") -> {
                        pushStyle(SpanStyle(color = StringColor))
                        append(token)
                        pop()
                    }
                    token.startsWith("@") -> {
                        pushStyle(SpanStyle(color = AnnotationColor, fontWeight = FontWeight.SemiBold))
                        append(token)
                        pop()
                    }
                    token.firstOrNull()?.isDigit() == true -> {
                        pushStyle(SpanStyle(color = NumberColor))
                        append(token)
                        pop()
                    }
                    KEYWORDS.contains(token) -> {
                        pushStyle(SpanStyle(color = KeywordColor, fontWeight = FontWeight.SemiBold))
                        append(token)
                        pop()
                    }
                    STANDARD_TYPES.contains(token) -> {
                        pushStyle(SpanStyle(color = TypeColor, fontWeight = FontWeight.Medium))
                        append(token)
                        pop()
                    }
                    token.length > 1 && token.first().isUpperCase() && token.all { it.isLetterOrDigit() || it == '_' } -> {
                        // Other PascalCase types
                        pushStyle(SpanStyle(color = TypeColor))
                        append(token)
                        pop()
                    }
                    // Check if matched as method call (followed by '(')
                    code.getOrNull(range.last + 1)?.let { after ->
                        if (after == '(' || (after.isWhitespace() && code.indexOf('(', range.last + 1) != -1 && code.substring(range.last + 1, code.indexOf('(', range.last + 1)).isBlank())) {
                            true
                        } else false
                    } == true && token.all { it.isLetterOrDigit() || it == '_' } -> {
                        pushStyle(SpanStyle(color = MethodColor))
                        append(token)
                        pop()
                    }
                    token.length == 1 && "+-*/%=<>!&|^~?:".contains(token[0]) || token.length > 1 && token.all { "+-*/%=<>!&|^~?:".contains(it) } -> {
                        pushStyle(SpanStyle(color = OperatorColor))
                        append(token)
                        pop()
                    }
                    token.length == 1 && "()[{]},;.".contains(token[0]) -> {
                        pushStyle(SpanStyle(color = PunctuationColor))
                        append(token)
                        pop()
                    }
                    else -> {
                        pushStyle(SpanStyle(color = DefaultColor))
                        append(token)
                        pop()
                    }
                }
            }
            if (lastIndex < code.length) {
                append(code.substring(lastIndex))
            }
        }
    }
}

object JsCodeHighlighter {

    val KeywordColor = Color(0xFFC678DD)      // Vibrant purple
    val BuiltinColor = Color(0xFFE5C07B)      // Golden amber
    val FunctionColor = Color(0xFF61AFEF)     // Sky blue
    val StringColor = Color(0xFF98C379)       // Emerald green
    val NumberColor = Color(0xFFD19A66)       // Warm peach
    val CommentColor = Color(0xFF7F848E)      // Muted slate gray
    val OperatorColor = Color(0xFF56B6C2)     // Cyan
    val PunctuationColor = Color(0xFFABB2BF)  // Soft silver
    val DefaultColor = Color(0xFFE6EDF3)      // Crisp soft white

    private val KEYWORDS = setOf(
        "var", "let", "const", "function", "return", "if", "else", "for", "while",
        "do", "switch", "case", "default", "break", "continue", "try", "catch",
        "finally", "throw", "class", "extends", "super", "new", "this", "typeof",
        "instanceof", "in", "of", "delete", "void", "yield", "await", "async",
        "import", "export", "from", "as", "null", "undefined", "true", "false", "NaN", "Infinity"
    )

    private val BUILTINS = setOf(
        "Object", "Array", "String", "Number", "Boolean", "Symbol", "BigInt",
        "Function", "Promise", "Map", "Set", "WeakMap", "WeakSet", "Date",
        "RegExp", "Error", "TypeError", "RangeError", "ReferenceError", "SyntaxError",
        "JSON", "Math", "Reflect", "Proxy", "console", "window", "global", "globalThis",
        "parseInt", "parseFloat", "isNaN", "isFinite", "setTimeout", "clearTimeout",
        "setInterval", "clearInterval"
    )

    private val TOKEN_REGEX = Regex(
        "(?://.*|/\\*[\\s\\S]*?\\*/)" + // Comments
        "|(?:\"(?:\\\\.|[^\"\\\\])*\"|'(?:\\\\.|[^'\\\\])*'|`(?:\\\\.|[^`\\\\])*`)" + // Strings & Template Literals
        "|(?:\\b0x[0-9a-fA-F]+\\b|\\b0b[01]+\\b|\\b0o[0-7]+\\b|\\b\\d+(?:\\.\\d+)?(?:[eE][+-]?\\d+)?\\b)" + // Numbers
        "|(?:\\b[A-Za-z_$][A-Za-z0-9_$]*\\b(?=\\s*\\())" + // Function calls
        "|(?:\\b[A-Za-z_$][A-Za-z0-9_$]*\\b)" + // Identifiers & keywords
        "|(?:=>|===|!==|==|!=|<=|>=|&&|\\|\\||\\?\\?|\\?\\.|[+\\-*/%=<>!&|^~?:])" + // Operators
        "|(?:\n)" + // Newline
        "|(?:\\s+)" + // Whitespace
        "|(?:.)" // Delimiters & punctuation
    )

    fun highlight(code: String): AnnotatedString {
        return buildAnnotatedString {
            var lastIndex = 0
            for (match in TOKEN_REGEX.findAll(code)) {
                val token = match.value
                val range = match.range

                if (range.first > lastIndex) {
                    append(code.substring(lastIndex, range.first))
                }
                lastIndex = range.last + 1

                when {
                    token.startsWith("//") || token.startsWith("/*") -> {
                        pushStyle(SpanStyle(color = CommentColor, fontStyle = FontStyle.Italic))
                        append(token)
                        pop()
                    }
                    token.startsWith("\"") || token.startsWith("'") || token.startsWith("`") -> {
                        pushStyle(SpanStyle(color = StringColor))
                        append(token)
                        pop()
                    }
                    token.firstOrNull()?.isDigit() == true -> {
                        pushStyle(SpanStyle(color = NumberColor))
                        append(token)
                        pop()
                    }
                    KEYWORDS.contains(token) -> {
                        pushStyle(SpanStyle(color = KeywordColor, fontWeight = FontWeight.SemiBold))
                        append(token)
                        pop()
                    }
                    BUILTINS.contains(token) -> {
                        pushStyle(SpanStyle(color = BuiltinColor, fontWeight = FontWeight.Medium))
                        append(token)
                        pop()
                    }
                    code.getOrNull(range.last + 1)?.let { after ->
                        if (after == '(' || (after.isWhitespace() && code.indexOf('(', range.last + 1) != -1 && code.substring(range.last + 1, code.indexOf('(', range.last + 1)).isBlank())) {
                            true
                        } else false
                    } == true && token.all { it.isLetterOrDigit() || it == '_' || it == '$' } -> {
                        pushStyle(SpanStyle(color = FunctionColor))
                        append(token)
                        pop()
                    }
                    token == "=>" || (token.length > 1 && "+-*/%=<>!&|^~?:".contains(token[0])) || "+-*/%=<>!&|^~?:".contains(token) -> {
                        pushStyle(SpanStyle(color = OperatorColor))
                        append(token)
                        pop()
                    }
                    "()[{]},;.".contains(token) -> {
                        pushStyle(SpanStyle(color = PunctuationColor))
                        append(token)
                        pop()
                    }
                    else -> {
                        pushStyle(SpanStyle(color = DefaultColor))
                        append(token)
                        pop()
                    }
                }
            }
            if (lastIndex < code.length) {
                append(code.substring(lastIndex))
            }
        }
    }
}

/**
 * A beautiful IDE-style code block with syntax highlighting, line numbers,
 * horizontal scrolling for preserved indentation, and window controls.
 */
@Composable
fun DsaCodeBlock(
    code: String,
    modifier: Modifier = Modifier,
    language: String = "Java",
    isCopied: Boolean = false,
    onCopy: () -> Unit
) {
    val highlightedCode = remember(code, language) {
        if (language.equals("JavaScript", ignoreCase = true) || language.equals("js", ignoreCase = true)) {
            JsCodeHighlighter.highlight(code)
        } else {
            JavaCodeHighlighter.highlight(code)
        }
    }
    val lineCount = remember(code) { code.lines().size.coerceAtLeast(1) }
    val lineNumbersText = remember(lineCount) {
        (1..lineCount).joinToString("\n") { it.toString() }
    }

    Surface(
        shape = RoundedCornerShape(14.dp),
        color = Color(0xFF13161D),
        border = BorderStroke(1.dp, Color(0xFF282D38)),
        modifier = modifier.fillMaxWidth()
    ) {
        Column {
            // IDE Window Header Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF1A1E27))
                    .padding(horizontal = 14.dp, vertical = 9.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // macOS Style Window Dots
                Row(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .background(Color(0xFFFF5F56), CircleShape)
                    )
                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .background(Color(0xFFFFBD2E), CircleShape)
                    )
                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .background(Color(0xFF27C93F), CircleShape)
                    )

                    Spacer(modifier = Modifier.width(6.dp))

                    // Language Badge
                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f),
                        border = BorderStroke(0.5.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.4f))
                    ) {
                        Text(
                            text = language.uppercase(),
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.Bold,
                                fontSize = 9.5.sp,
                                letterSpacing = 0.8.sp
                            ),
                            color = MaterialTheme.colorScheme.primary
                        )
                    }

                    Text(
                        text = "• $lineCount lines",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium
                        ),
                        color = Color(0xFF6B7280)
                    )
                }

                // Copy Code Action Button
                Surface(
                    shape = RoundedCornerShape(7.dp),
                    color = if (isCopied) SuccessGreen.copy(alpha = 0.16f) else Color(0xFF242A36),
                    border = BorderStroke(
                        0.5.dp,
                        if (isCopied) SuccessGreen else Color(0xFF383F4F)
                    ),
                    modifier = Modifier.clickable { onCopy() }
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            imageVector = if (isCopied) Icons.Filled.Check else Icons.Filled.ContentCopy,
                            contentDescription = "Copy Solution Code",
                            tint = if (isCopied) SuccessGreen else Color(0xFFD6DEEB),
                            modifier = Modifier.size(12.dp)
                        )
                        Text(
                            text = if (isCopied) "Copied" else "Copy",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 11.sp
                            ),
                            color = if (isCopied) SuccessGreen else Color(0xFFD6DEEB)
                        )
                    }
                }
            }

            // Code Editor Body with Line Numbers and Code
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp)
            ) {
                // Line Numbers Gutter
                Text(
                    text = lineNumbersText,
                    modifier = Modifier
                        .padding(start = 12.dp, end = 10.dp),
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontFamily = FontFamily.Monospace,
                        fontSize = 12.sp,
                        lineHeight = 20.sp,
                        fontWeight = FontWeight.Normal
                    ),
                    color = Color(0xFF4C5364)
                )

                // Vertical Divider Line between numbers and code
                Box(
                    modifier = Modifier
                        .width(1.dp)
                        .background(Color(0xFF262C38))
                )

                // Horizontally scrollable code text
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .horizontalScroll(rememberScrollState())
                        .padding(start = 12.dp, end = 16.dp)
                ) {
                    Text(
                        text = highlightedCode,
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontFamily = FontFamily.Monospace,
                            fontSize = 12.sp,
                            lineHeight = 20.sp
                        )
                    )
                }
            }
        }
    }
}

/**
 * Parses markdown code blocks (```java ... ``` or ```javascript ... ```) inside prompts
 * and renders formatted text with embedded, syntax-highlighted IDE code blocks.
 */
@Composable
fun RichCodePromptView(
    prompt: String,
    modifier: Modifier = Modifier,
    defaultLanguage: String = "Java"
) {
    // Check if prompt contains ``` code blocks
    val codeBlockRegex = Regex("```(?:(java|javascript|js))?\\s*\\n([\\s\\S]*?)```", RegexOption.IGNORE_CASE)
    val matches = codeBlockRegex.findAll(prompt).toList()

    if (matches.isEmpty()) {
        Text(
            text = prompt,
            style = MaterialTheme.typography.bodyMedium.copy(lineHeight = 22.sp),
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.9f),
            modifier = modifier
        )
    } else {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            var lastIndex = 0
            for (match in matches) {
                val textBefore = prompt.substring(lastIndex, match.range.first).trim()
                if (textBefore.isNotEmpty()) {
                    Text(
                        text = textBefore,
                        style = MaterialTheme.typography.bodyMedium.copy(lineHeight = 22.sp),
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.9f)
                    )
                }

                val detectedLang = match.groupValues[1].let {
                    if (it.isBlank()) defaultLanguage else it
                }
                val code = match.groupValues[2].trimEnd()

                DsaCodeBlock(
                    code = code,
                    language = if (detectedLang.contains("js", ignoreCase = true)) "JavaScript" else "Java",
                    isCopied = false,
                    onCopy = {}
                )

                lastIndex = match.range.last + 1
            }

            if (lastIndex < prompt.length) {
                val remainingText = prompt.substring(lastIndex).trim()
                if (remainingText.isNotEmpty()) {
                    Text(
                        text = remainingText,
                        style = MaterialTheme.typography.bodyMedium.copy(lineHeight = 22.sp),
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.9f)
                    )
                }
            }
        }
    }
}
