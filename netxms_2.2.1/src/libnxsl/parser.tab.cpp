/* A Bison parser, made by GNU Bison 3.0.4.  */

/* Bison implementation for Yacc-like parsers in C

   Copyright (C) 1984, 1989-1990, 2000-2015 Free Software Foundation, Inc.

   This program is free software: you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation, either version 3 of the License, or
   (at your option) any later version.

   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.

   You should have received a copy of the GNU General Public License
   along with this program.  If not, see <http://www.gnu.org/licenses/>.  */

/* As a special exception, you may create a larger work that contains
   part or all of the Bison parser skeleton and distribute that work
   under terms of your choice, so long as that work isn't itself a
   parser generator using the skeleton or a modified version thereof
   as a parser skeleton.  Alternatively, if you modify or redistribute
   the parser skeleton itself, you may (at your option) remove this
   special exception, which will cause the skeleton and the resulting
   Bison output files to be licensed under the GNU General Public
   License without this special exception.

   This special exception was added by the Free Software Foundation in
   version 2.2 of Bison.  */

/* C LALR(1) parser skeleton written by Richard Stallman, by
   simplifying the original so-called "semantic" parser.  */

/* All symbols defined below should begin with yy or YY, to avoid
   infringing on user name space.  This should be done even for local
   variables, as they might otherwise be expanded by user macros.
   There are some unavoidable exceptions within include files to
   define necessary library symbols; they are noted "INFRINGES ON
   USER NAME SPACE" below.  */

/* Identify Bison output.  */
#define YYBISON 1

/* Bison version.  */
#define YYBISON_VERSION "3.0.4"

/* Skeleton name.  */
#define YYSKELETON_NAME "yacc.c"

/* Pure parsers.  */
#define YYPURE 1

/* Push parsers.  */
#define YYPUSH 0

/* Pull parsers.  */
#define YYPULL 1




/* Copy the first part of user declarations.  */
#line 1 "parser.y" /* yacc.c:339  */


#pragma warning(disable : 4065 4102)

#define YYERROR_VERBOSE
#define YYINCLUDED_STDLIB_H
#define YYDEBUG			1

#include <nms_common.h>
#include "libnxsl.h"
#include "parser.tab.hpp"

void yyerror(yyscan_t scanner, NXSL_Lexer *pLexer, NXSL_Compiler *pCompiler,
             NXSL_Program *pScript, const char *pszText);
int yylex(YYSTYPE *lvalp, yyscan_t scanner);


#line 84 "parser.tab.cpp" /* yacc.c:339  */

# ifndef YY_NULLPTR
#  if defined __cplusplus && 201103L <= __cplusplus
#   define YY_NULLPTR nullptr
#  else
#   define YY_NULLPTR 0
#  endif
# endif

/* Enabling verbose error messages.  */
#ifdef YYERROR_VERBOSE
# undef YYERROR_VERBOSE
# define YYERROR_VERBOSE 1
#else
# define YYERROR_VERBOSE 0
#endif

/* In a future release of Bison, this section will be replaced
   by #include "parser.tab.hpp".  */
#ifndef YY_YY_PARSER_TAB_HPP_INCLUDED
# define YY_YY_PARSER_TAB_HPP_INCLUDED
/* Debug traces.  */
#ifndef YYDEBUG
# define YYDEBUG 1
#endif
#if YYDEBUG
extern int yydebug;
#endif

/* Token type.  */
#ifndef YYTOKENTYPE
# define YYTOKENTYPE
  enum yytokentype
  {
    T_ABORT = 258,
    T_ARRAY = 259,
    T_BREAK = 260,
    T_CASE = 261,
    T_CATCH = 262,
    T_CONST = 263,
    T_CONTINUE = 264,
    T_DEFAULT = 265,
    T_DO = 266,
    T_ELSE = 267,
    T_EXIT = 268,
    T_FALSE = 269,
    T_FOR = 270,
    T_FOREACH = 271,
    T_GLOBAL = 272,
    T_IF = 273,
    T_NEW = 274,
    T_NULL = 275,
    T_PRINT = 276,
    T_PRINTLN = 277,
    T_RETURN = 278,
    T_SELECT = 279,
    T_SUB = 280,
    T_SWITCH = 281,
    T_TRUE = 282,
    T_TRY = 283,
    T_TYPE_INT32 = 284,
    T_TYPE_INT64 = 285,
    T_TYPE_REAL = 286,
    T_TYPE_STRING = 287,
    T_TYPE_UINT32 = 288,
    T_TYPE_UINT64 = 289,
    T_USE = 290,
    T_WHEN = 291,
    T_WHILE = 292,
    T_COMPOUND_IDENTIFIER = 293,
    T_IDENTIFIER = 294,
    T_STRING = 295,
    T_INT32 = 296,
    T_UINT32 = 297,
    T_INT64 = 298,
    T_UINT64 = 299,
    T_REAL = 300,
    T_ASSIGN_ADD = 301,
    T_ASSIGN_SUB = 302,
    T_ASSIGN_MUL = 303,
    T_ASSIGN_DIV = 304,
    T_ASSIGN_REM = 305,
    T_ASSIGN_CONCAT = 306,
    T_ASSIGN_AND = 307,
    T_ASSIGN_OR = 308,
    T_ASSIGN_XOR = 309,
    T_OR = 310,
    T_AND = 311,
    T_NE = 312,
    T_EQ = 313,
    T_LIKE = 314,
    T_ILIKE = 315,
    T_MATCH = 316,
    T_IMATCH = 317,
    T_LE = 318,
    T_GE = 319,
    T_LSHIFT = 320,
    T_RSHIFT = 321,
    T_INC = 322,
    T_DEC = 323,
    NEGATE = 324,
    T_REF = 325,
    T_POST_INC = 326,
    T_POST_DEC = 327
  };
#endif

/* Value type.  */
#if ! defined YYSTYPE && ! defined YYSTYPE_IS_DECLARED

union YYSTYPE
{
#line 28 "parser.y" /* yacc.c:355  */

	INT32 valInt32;
	UINT32 valUInt32;
	INT64 valInt64;
	UINT64 valUInt64;
	char *valStr;
	double valReal;
	NXSL_Value *pConstant;
	NXSL_Instruction *pInstruction;

#line 208 "parser.tab.cpp" /* yacc.c:355  */
};

typedef union YYSTYPE YYSTYPE;
# define YYSTYPE_IS_TRIVIAL 1
# define YYSTYPE_IS_DECLARED 1
#endif



int yyparse (yyscan_t scanner, NXSL_Lexer *pLexer, NXSL_Compiler *pCompiler, NXSL_Program *pScript);

#endif /* !YY_YY_PARSER_TAB_HPP_INCLUDED  */

/* Copy the second part of user declarations.  */

#line 224 "parser.tab.cpp" /* yacc.c:358  */

#ifdef short
# undef short
#endif

#ifdef YYTYPE_UINT8
typedef YYTYPE_UINT8 yytype_uint8;
#else
typedef unsigned char yytype_uint8;
#endif

#ifdef YYTYPE_INT8
typedef YYTYPE_INT8 yytype_int8;
#else
typedef signed char yytype_int8;
#endif

#ifdef YYTYPE_UINT16
typedef YYTYPE_UINT16 yytype_uint16;
#else
typedef unsigned short int yytype_uint16;
#endif

#ifdef YYTYPE_INT16
typedef YYTYPE_INT16 yytype_int16;
#else
typedef short int yytype_int16;
#endif

#ifndef YYSIZE_T
# ifdef __SIZE_TYPE__
#  define YYSIZE_T __SIZE_TYPE__
# elif defined size_t
#  define YYSIZE_T size_t
# elif ! defined YYSIZE_T
#  include <stddef.h> /* INFRINGES ON USER NAME SPACE */
#  define YYSIZE_T size_t
# else
#  define YYSIZE_T unsigned int
# endif
#endif

#define YYSIZE_MAXIMUM ((YYSIZE_T) -1)

#ifndef YY_
# if defined YYENABLE_NLS && YYENABLE_NLS
#  if ENABLE_NLS
#   include <libintl.h> /* INFRINGES ON USER NAME SPACE */
#   define YY_(Msgid) dgettext ("bison-runtime", Msgid)
#  endif
# endif
# ifndef YY_
#  define YY_(Msgid) Msgid
# endif
#endif

#ifndef YY_ATTRIBUTE
# if (defined __GNUC__                                               \
      && (2 < __GNUC__ || (__GNUC__ == 2 && 96 <= __GNUC_MINOR__)))  \
     || defined __SUNPRO_C && 0x5110 <= __SUNPRO_C
#  define YY_ATTRIBUTE(Spec) __attribute__(Spec)
# else
#  define YY_ATTRIBUTE(Spec) /* empty */
# endif
#endif

#ifndef YY_ATTRIBUTE_PURE
# define YY_ATTRIBUTE_PURE   YY_ATTRIBUTE ((__pure__))
#endif

#ifndef YY_ATTRIBUTE_UNUSED
# define YY_ATTRIBUTE_UNUSED YY_ATTRIBUTE ((__unused__))
#endif

#if !defined _Noreturn \
     && (!defined __STDC_VERSION__ || __STDC_VERSION__ < 201112)
# if defined _MSC_VER && 1200 <= _MSC_VER
#  define _Noreturn __declspec (noreturn)
# else
#  define _Noreturn YY_ATTRIBUTE ((__noreturn__))
# endif
#endif

/* Suppress unused-variable warnings by "using" E.  */
#if ! defined lint || defined __GNUC__
# define YYUSE(E) ((void) (E))
#else
# define YYUSE(E) /* empty */
#endif

#if defined __GNUC__ && 407 <= __GNUC__ * 100 + __GNUC_MINOR__
/* Suppress an incorrect diagnostic about yylval being uninitialized.  */
# define YY_IGNORE_MAYBE_UNINITIALIZED_BEGIN \
    _Pragma ("GCC diagnostic push") \
    _Pragma ("GCC diagnostic ignored \"-Wuninitialized\"")\
    _Pragma ("GCC diagnostic ignored \"-Wmaybe-uninitialized\"")
# define YY_IGNORE_MAYBE_UNINITIALIZED_END \
    _Pragma ("GCC diagnostic pop")
#else
# define YY_INITIAL_VALUE(Value) Value
#endif
#ifndef YY_IGNORE_MAYBE_UNINITIALIZED_BEGIN
# define YY_IGNORE_MAYBE_UNINITIALIZED_BEGIN
# define YY_IGNORE_MAYBE_UNINITIALIZED_END
#endif
#ifndef YY_INITIAL_VALUE
# define YY_INITIAL_VALUE(Value) /* Nothing. */
#endif


#if ! defined yyoverflow || YYERROR_VERBOSE

/* The parser invokes alloca or malloc; define the necessary symbols.  */

# ifdef YYSTACK_USE_ALLOCA
#  if YYSTACK_USE_ALLOCA
#   ifdef __GNUC__
#    define YYSTACK_ALLOC __builtin_alloca
#   elif defined __BUILTIN_VA_ARG_INCR
#    include <alloca.h> /* INFRINGES ON USER NAME SPACE */
#   elif defined _AIX
#    define YYSTACK_ALLOC __alloca
#   elif defined _MSC_VER
#    include <malloc.h> /* INFRINGES ON USER NAME SPACE */
#    define alloca _alloca
#   else
#    define YYSTACK_ALLOC alloca
#    if ! defined _ALLOCA_H && ! defined EXIT_SUCCESS
#     include <stdlib.h> /* INFRINGES ON USER NAME SPACE */
      /* Use EXIT_SUCCESS as a witness for stdlib.h.  */
#     ifndef EXIT_SUCCESS
#      define EXIT_SUCCESS 0
#     endif
#    endif
#   endif
#  endif
# endif

# ifdef YYSTACK_ALLOC
   /* Pacify GCC's 'empty if-body' warning.  */
#  define YYSTACK_FREE(Ptr) do { /* empty */; } while (0)
#  ifndef YYSTACK_ALLOC_MAXIMUM
    /* The OS might guarantee only one guard page at the bottom of the stack,
       and a page size can be as small as 4096 bytes.  So we cannot safely
       invoke alloca (N) if N exceeds 4096.  Use a slightly smaller number
       to allow for a few compiler-allocated temporary stack slots.  */
#   define YYSTACK_ALLOC_MAXIMUM 4032 /* reasonable circa 2006 */
#  endif
# else
#  define YYSTACK_ALLOC YYMALLOC
#  define YYSTACK_FREE YYFREE
#  ifndef YYSTACK_ALLOC_MAXIMUM
#   define YYSTACK_ALLOC_MAXIMUM YYSIZE_MAXIMUM
#  endif
#  if (defined __cplusplus && ! defined EXIT_SUCCESS \
       && ! ((defined YYMALLOC || defined malloc) \
             && (defined YYFREE || defined free)))
#   include <stdlib.h> /* INFRINGES ON USER NAME SPACE */
#   ifndef EXIT_SUCCESS
#    define EXIT_SUCCESS 0
#   endif
#  endif
#  ifndef YYMALLOC
#   define YYMALLOC malloc
#   if ! defined malloc && ! defined EXIT_SUCCESS
void *malloc (YYSIZE_T); /* INFRINGES ON USER NAME SPACE */
#   endif
#  endif
#  ifndef YYFREE
#   define YYFREE free
#   if ! defined free && ! defined EXIT_SUCCESS
void free (void *); /* INFRINGES ON USER NAME SPACE */
#   endif
#  endif
# endif
#endif /* ! defined yyoverflow || YYERROR_VERBOSE */


#if (! defined yyoverflow \
     && (! defined __cplusplus \
         || (defined YYSTYPE_IS_TRIVIAL && YYSTYPE_IS_TRIVIAL)))

/* A type that is properly aligned for any stack member.  */
union yyalloc
{
  yytype_int16 yyss_alloc;
  YYSTYPE yyvs_alloc;
};

/* The size of the maximum gap between one aligned stack and the next.  */
# define YYSTACK_GAP_MAXIMUM (sizeof (union yyalloc) - 1)

/* The size of an array large to enough to hold all stacks, each with
   N elements.  */
# define YYSTACK_BYTES(N) \
     ((N) * (sizeof (yytype_int16) + sizeof (YYSTYPE)) \
      + YYSTACK_GAP_MAXIMUM)

# define YYCOPY_NEEDED 1

/* Relocate STACK from its old location to the new one.  The
   local variables YYSIZE and YYSTACKSIZE give the old and new number of
   elements in the stack, and YYPTR gives the new location of the
   stack.  Advance YYPTR to a properly aligned location for the next
   stack.  */
# define YYSTACK_RELOCATE(Stack_alloc, Stack)                           \
    do                                                                  \
      {                                                                 \
        YYSIZE_T yynewbytes;                                            \
        YYCOPY (&yyptr->Stack_alloc, Stack, yysize);                    \
        Stack = &yyptr->Stack_alloc;                                    \
        yynewbytes = yystacksize * sizeof (*Stack) + YYSTACK_GAP_MAXIMUM; \
        yyptr += yynewbytes / sizeof (*yyptr);                          \
      }                                                                 \
    while (0)

#endif

#if defined YYCOPY_NEEDED && YYCOPY_NEEDED
/* Copy COUNT objects from SRC to DST.  The source and destination do
   not overlap.  */
# ifndef YYCOPY
#  if defined __GNUC__ && 1 < __GNUC__
#   define YYCOPY(Dst, Src, Count) \
      __builtin_memcpy (Dst, Src, (Count) * sizeof (*(Src)))
#  else
#   define YYCOPY(Dst, Src, Count)              \
      do                                        \
        {                                       \
          YYSIZE_T yyi;                         \
          for (yyi = 0; yyi < (Count); yyi++)   \
            (Dst)[yyi] = (Src)[yyi];            \
        }                                       \
      while (0)
#  endif
# endif
#endif /* !YYCOPY_NEEDED */

/* YYFINAL -- State number of the termination state.  */
#define YYFINAL  141
/* YYLAST -- Last index in YYTABLE.  */
#define YYLAST   1880

/* YYNTOKENS -- Number of terminals.  */
#define YYNTOKENS  99
/* YYNNTS -- Number of nonterminals.  */
#define YYNNTS  113
/* YYNRULES -- Number of rules.  */
#define YYNRULES  261
/* YYNSTATES -- Number of states.  */
#define YYNSTATES  448

/* YYTRANSLATE[YYX] -- Symbol number corresponding to YYX as returned
   by yylex, with out-of-bounds checking.  */
#define YYUNDEFTOK  2
#define YYMAXUTOK   327

#define YYTRANSLATE(YYX)                                                \
  ((unsigned int) (YYX) <= YYMAXUTOK ? yytranslate[YYX] : YYUNDEFTOK)

/* YYTRANSLATE[TOKEN-NUM] -- Symbol number corresponding to TOKEN-NUM
   as returned by yylex, without out-of-bounds checking.  */
static const yytype_uint8 yytranslate[] =
{
       0,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,    83,     2,    98,     2,    80,    63,     2,
      97,    94,    78,    76,    93,    77,    58,    79,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,    56,    92,
      70,    46,    72,    57,    87,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,    90,     2,    91,    62,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,    95,    61,    96,    84,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     1,     2,     3,     4,
       5,     6,     7,     8,     9,    10,    11,    12,    13,    14,
      15,    16,    17,    18,    19,    20,    21,    22,    23,    24,
      25,    26,    27,    28,    29,    30,    31,    32,    33,    34,
      35,    36,    37,    38,    39,    40,    41,    42,    43,    44,
      45,    47,    48,    49,    50,    51,    52,    53,    54,    55,
      59,    60,    64,    65,    66,    67,    68,    69,    71,    73,
      74,    75,    81,    82,    85,    86,    88,    89
};

#if YYDEBUG
  /* YYRLINE[YYN] -- Source line where rule number YYN was defined.  */
static const yytype_uint16 yyrline[] =
{
       0,   120,   120,   134,   149,   165,   166,   170,   171,   172,
     173,   177,   181,   182,   186,   200,   208,   209,   214,   213,
     235,   236,   241,   240,   246,   254,   258,   259,   263,   264,
     265,   270,   274,   269,   286,   290,   291,   298,   299,   304,
     304,   310,   310,   316,   316,   322,   322,   328,   328,   334,
     334,   340,   340,   346,   346,   352,   352,   358,   358,   363,
     363,   368,   368,   373,   373,   378,   378,   383,   383,   388,
     388,   393,   393,   398,   398,   403,   407,   407,   412,   412,
     417,   417,   422,   422,   427,   427,   432,   432,   437,   437,
     442,   442,   447,   451,   455,   459,   464,   469,   474,   479,
     484,   488,   492,   496,   501,   506,   511,   516,   520,   524,
     528,   532,   536,   540,   544,   548,   552,   556,   560,   564,
     568,   572,   576,   580,   584,   588,   592,   596,   600,   604,
     608,   612,   616,   620,   620,   625,   625,   630,   634,   638,
     643,   647,   642,   655,   659,   660,   661,   662,   663,   664,
     669,   674,   682,   690,   689,   694,   702,   701,   706,   714,
     713,   718,   725,   726,   730,   736,   740,   744,   748,   752,
     756,   763,   764,   765,   766,   767,   768,   769,   770,   771,
     772,   773,   774,   787,   803,   808,   817,   821,   825,   829,
     836,   842,   851,   850,   858,   862,   870,   869,   879,   884,
     890,   878,   907,   911,   917,   926,   925,   944,   949,   943,
     962,   961,   976,   975,   988,   987,   993,  1001,  1000,  1008,
    1007,  1017,  1018,  1023,  1022,  1042,  1044,  1050,  1054,  1062,
    1065,  1061,  1083,  1083,  1084,  1084,  1088,  1092,  1093,  1097,
    1102,  1110,  1118,  1126,  1137,  1142,  1150,  1151,  1155,  1156,
    1164,  1171,  1176,  1180,  1185,  1189,  1193,  1197,  1201,  1205,
    1209,  1213
};
#endif

#if YYDEBUG || YYERROR_VERBOSE || 0
/* YYTNAME[SYMBOL-NUM] -- String name of the symbol SYMBOL-NUM.
   First, the terminals, then, starting at YYNTOKENS, nonterminals.  */
static const char *const yytname[] =
{
  "$end", "error", "$undefined", "T_ABORT", "T_ARRAY", "T_BREAK",
  "T_CASE", "T_CATCH", "T_CONST", "T_CONTINUE", "T_DEFAULT", "T_DO",
  "T_ELSE", "T_EXIT", "T_FALSE", "T_FOR", "T_FOREACH", "T_GLOBAL", "T_IF",
  "T_NEW", "T_NULL", "T_PRINT", "T_PRINTLN", "T_RETURN", "T_SELECT",
  "T_SUB", "T_SWITCH", "T_TRUE", "T_TRY", "T_TYPE_INT32", "T_TYPE_INT64",
  "T_TYPE_REAL", "T_TYPE_STRING", "T_TYPE_UINT32", "T_TYPE_UINT64",
  "T_USE", "T_WHEN", "T_WHILE", "T_COMPOUND_IDENTIFIER", "T_IDENTIFIER",
  "T_STRING", "T_INT32", "T_UINT32", "T_INT64", "T_UINT64", "T_REAL",
  "'='", "T_ASSIGN_ADD", "T_ASSIGN_SUB", "T_ASSIGN_MUL", "T_ASSIGN_DIV",
  "T_ASSIGN_REM", "T_ASSIGN_CONCAT", "T_ASSIGN_AND", "T_ASSIGN_OR",
  "T_ASSIGN_XOR", "':'", "'?'", "'.'", "T_OR", "T_AND", "'|'", "'^'",
  "'&'", "T_NE", "T_EQ", "T_LIKE", "T_ILIKE", "T_MATCH", "T_IMATCH", "'<'",
  "T_LE", "'>'", "T_GE", "T_LSHIFT", "T_RSHIFT", "'+'", "'-'", "'*'",
  "'/'", "'%'", "T_INC", "T_DEC", "'!'", "'~'", "NEGATE", "T_REF", "'@'",
  "T_POST_INC", "T_POST_DEC", "'['", "']'", "';'", "','", "')'", "'{'",
  "'}'", "'('", "'#'", "$accept", "Script", "Module", "ModuleComponent",
  "ConstStatement", "ConstList", "ConstDefinition", "UseStatement",
  "AnyIdentifier", "Function", "$@1", "ParameterDeclaration",
  "IdentifierList", "$@2", "Block", "StatementList", "StatementOrBlock",
  "TryCatchBlock", "$@3", "$@4", "Statement", "Expression", "$@5", "$@6",
  "$@7", "$@8", "$@9", "$@10", "$@11", "$@12", "$@13", "$@14", "$@15",
  "$@16", "$@17", "$@18", "$@19", "$@20", "$@21", "$@22", "$@23", "$@24",
  "$@25", "$@26", "$@27", "$@28", "$@29", "$@30", "$@31", "$@32", "$@33",
  "$@34", "Operand", "TypeCast", "ArrayInitializer", "$@35",
  "ArrayElements", "$@36", "HashMapInitializer", "$@37", "HashMapElements",
  "HashMapElement", "BuiltinType", "BuiltinStatement", "SimpleStatement",
  "SimpleStatementKeyword", "PrintlnStatement", "IfStatement", "$@38",
  "IfBody", "ElseStatement", "$@39", "ForStatement", "$@40", "$@41",
  "$@42", "ForEachStatement", "ForEach", "ForEachBody", "$@43",
  "WhileStatement", "$@44", "$@45", "DoStatement", "$@46",
  "SwitchStatement", "$@47", "CaseList", "$@48", "Case", "$@49", "$@50",
  "Default", "SelectStatement", "$@51", "SelectOptions", "SelectList",
  "SelectEntry", "$@52", "$@53", "ArrayStatement", "$@54", "$@55",
  "GlobalStatement", "GlobalVariableList", "GlobalVariableDeclaration",
  "New", "FunctionCall", "ParameterList", "Parameter", "FunctionName",
  "StorageItem", "Constant", YY_NULLPTR
};
#endif

# ifdef YYPRINT
/* YYTOKNUM[NUM] -- (External) token number corresponding to the
   (internal) symbol number NUM (which must be that of a token).  */
static const yytype_uint16 yytoknum[] =
{
       0,   256,   257,   258,   259,   260,   261,   262,   263,   264,
     265,   266,   267,   268,   269,   270,   271,   272,   273,   274,
     275,   276,   277,   278,   279,   280,   281,   282,   283,   284,
     285,   286,   287,   288,   289,   290,   291,   292,   293,   294,
     295,   296,   297,   298,   299,   300,    61,   301,   302,   303,
     304,   305,   306,   307,   308,   309,    58,    63,    46,   310,
     311,   124,    94,    38,   312,   313,   314,   315,   316,   317,
      60,   318,    62,   319,   320,   321,    43,    45,    42,    47,
      37,   322,   323,    33,   126,   324,   325,    64,   326,   327,
      91,    93,    59,    44,    41,   123,   125,    40,    35
};
# endif

#define YYPACT_NINF -197

#define yypact_value_is_default(Yystate) \
  (!!((Yystate) == (-197)))

#define YYTABLE_NINF -215

#define yytable_value_is_error(Yytable_value) \
  0

  /* YYPACT[STATE-NUM] -- Index in YYTABLE of the portion describing
     STATE-NUM.  */
static const yytype_int16 yypact[] =
{
     431,  -197,  -197,   -74,   -18,   -55,  -197,  -197,  -197,   -59,
     -58,     4,   -49,     3,  -197,  -197,    27,  -197,  -197,    13,
    -197,  -197,  -197,  -197,  -197,  -197,  -197,  -197,  -197,   -23,
    -197,  -197,   280,  -197,  -197,  -197,  -197,  -197,  -197,   817,
     -86,   -22,   -16,   817,   817,  -197,   527,   817,   -20,    55,
    -197,   431,  -197,  -197,  -197,  -197,  -197,  -197,  -197,  1421,
    -197,  -197,  -197,  -197,   -33,  -197,   -13,   817,  -197,  -197,
    -197,  -197,   817,  -197,  -197,  -197,  -197,  -197,  -197,  -197,
    -197,   601,   531,  -197,    44,  -197,    38,    -7,    -5,  -197,
     527,   889,    47,  -197,    50,    -3,     5,   817,     2,  -197,
    1457,    61,     9,  -197,    16,     7,  -197,  -197,    20,    17,
     817,  -197,  -197,  -197,  -197,  -197,  -197,  -197,  -197,  -197,
    -197,  -197,   817,  -197,   -76,    19,    23,  -197,   817,  -197,
    -197,   817,  -197,   -76,   -76,    22,   527,  1421,  1003,  -197,
     817,  -197,  -197,  -197,   817,  -197,  -197,   817,   817,   817,
     817,   817,   817,   817,   817,   817,   817,   817,   817,   817,
     817,   817,   817,   817,   817,   817,   817,    81,   817,  -197,
     817,  -197,   590,  1041,  -197,   149,  -197,   590,    32,    34,
     817,  -197,  -197,  -197,  -197,  -197,  -197,  -197,  -197,  -197,
    -197,  -197,    37,    39,   344,  -197,   -18,    95,   202,  1493,
      79,    44,   817,  -197,    97,  1079,   673,  -197,    41,   -32,
     817,   132,  -197,   817,   590,   817,   817,   817,   817,   817,
     817,   817,   817,   817,    51,  -197,   817,  -197,   817,   662,
     734,  -197,  -197,  -197,  1117,   817,   805,   817,   817,  1735,
    1763,  1790,   -46,   -46,   -46,   -46,   -46,   -46,   103,   103,
     103,   103,    15,    15,    43,    43,   -76,   -76,   -76,   -34,
     745,  1565,  1155,  -197,   817,  -197,   961,   590,   817,   817,
     817,   817,   817,   817,   817,   817,   817,    49,  -197,  -197,
    -197,    46,  -197,  -197,  -197,    52,   590,  -197,  -197,  -197,
      53,   817,    54,  -197,     7,    57,  1193,  -197,  1231,   590,
     590,   590,   590,   590,   590,   590,   590,   590,  1601,    77,
      82,  1269,    90,   817,   817,  -197,  1636,   875,   946,   817,
    -197,    91,   545,  -197,   527,   590,  -197,   590,   590,   590,
     590,   590,   590,   590,   590,   590,    44,   817,   817,  -197,
     527,  -197,  1307,   109,  -197,  -197,    92,     7,  -197,   817,
    -197,   817,    98,  -197,  1670,  1705,  -197,   590,  -197,   817,
    -197,  -197,  -197,  -197,  -197,  -197,  -197,  -197,  -197,  -197,
    -197,  -197,  1345,  1529,   136,  -197,  -197,  -197,    94,   109,
     182,  -197,   527,   590,  -197,   817,   299,   439,   817,   590,
     817,   817,   817,   817,   817,   817,   817,   817,   102,  -197,
    -197,  -197,   817,  -197,  -197,   271,   196,   201,  -197,  -197,
    -197,  -197,   590,   590,   590,   590,   590,   590,   590,   590,
     590,  -197,   817,   527,   590,  -197,  -197,   153,   114,   182,
    1383,  -197,   155,   158,   159,   527,  -197,  -197,  -197,   527,
     527,   527,  -197,   527,  -197,  -197,  -197,  -197
};

  /* YYDEFACT[STATE-NUM] -- Default reduction number in state STATE-NUM.
     Performed when YYTABLE does not specify something else to do.  Zero
     means the default is an error.  */
static const yytype_uint16 yydefact[] =
{
       4,   186,   232,     0,     0,     0,   210,   187,   260,     0,
       0,     0,     0,     0,   261,   189,     0,   188,   223,     0,
     212,   259,    31,   165,   166,   169,   170,   167,   168,     0,
     207,   150,   149,   253,   254,   255,   256,   257,   258,     0,
       0,     0,     0,     0,     0,    36,    27,     0,     0,     0,
       2,     6,     7,    10,     8,    29,     9,    30,    28,     3,
     143,   145,   146,   147,     0,    35,     0,   185,   172,   173,
     176,   177,     0,   175,   174,   178,   179,   180,   181,   148,
     144,     0,    94,   151,     0,   182,     0,     0,    13,   183,
       0,     0,     0,   234,   239,     0,   238,     0,   241,   191,
       0,     0,     0,    18,     0,     0,    17,    16,     0,     0,
       0,    39,    41,    43,    45,    47,    49,    51,    53,    55,
     105,   106,     0,   250,   100,   159,   153,   103,     0,   107,
     104,     0,   108,   101,   102,     0,    27,     0,     0,   251,
       0,     1,     5,   140,     0,   135,   133,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,    34,
       0,   171,   184,     0,   202,   149,   245,   248,     0,   247,
       0,    57,    59,    61,    63,    65,    67,    69,    71,    73,
     109,   110,    24,     0,     0,    11,     0,     0,   149,     0,
       0,     0,     0,   236,     0,     0,     0,   190,   226,     0,
       0,     0,    15,     0,    38,     0,     0,     0,     0,     0,
       0,     0,     0,     0,    99,   161,     0,   155,     0,     0,
       0,    25,    26,    37,     0,     0,   139,     0,     0,   131,
     132,   130,   125,   124,   120,   121,   122,   123,   126,   127,
     128,   129,   137,   138,   115,   116,   117,   118,   119,    96,
       0,     0,     0,   205,     0,   244,     0,    93,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,   233,    14,
      12,     0,   204,   198,   203,     0,   240,   237,   192,   243,
       0,     0,     0,    21,     0,     0,     0,    32,     0,    40,
      42,    44,    46,    48,    50,    52,    54,    56,     0,     0,
     163,   156,     0,     0,     0,   252,     0,   136,   134,     0,
      98,     0,    92,   152,     0,   249,   246,    58,    60,    62,
      64,    66,    68,    70,    72,    74,     0,     0,     0,   235,
       0,   242,     0,     0,    19,    20,     0,     0,   208,     0,
     160,     0,     0,   154,     0,     0,   141,    95,    97,     0,
      76,    78,    80,    82,    84,    86,    88,    90,   113,   114,
     206,    23,     0,     0,   194,   193,   225,   229,     0,   228,
       0,    33,     0,   164,   162,     0,    92,    92,     0,    75,
       0,     0,     0,     0,     0,     0,     0,     0,     0,   199,
     196,   195,     0,   224,   227,     0,   222,   216,   209,   157,
     111,   112,   142,    77,    79,    81,    83,    85,    87,    89,
      91,   211,     0,     0,   230,   219,   217,     0,     0,     0,
       0,   197,     0,     0,     0,    27,   213,   215,   200,    27,
      27,    27,   221,     0,   231,   220,   218,   201
};

  /* YYPGOTO[NTERM-NUM].  */
static const yytype_int16 yypgoto[] =
{
    -197,  -197,   166,  -197,  -197,    24,  -197,  -197,  -197,  -197,
    -197,  -197,  -196,  -197,  -102,  -135,     0,  -197,  -197,  -197,
    -197,     6,  -197,  -197,  -197,  -197,  -197,  -197,  -197,  -197,
    -197,  -197,  -197,  -197,  -197,  -197,  -197,  -197,  -197,  -197,
    -197,  -197,  -197,  -197,  -197,  -197,  -197,  -197,  -197,  -197,
    -197,  -197,  -197,  -197,  -197,  -197,  -167,  -197,  -197,  -197,
    -118,  -197,  -197,  -197,  -197,  -197,  -197,  -197,  -197,  -197,
    -197,  -197,  -197,  -197,  -197,  -197,  -197,  -197,  -197,  -197,
    -197,  -197,  -197,  -197,  -197,  -197,  -197,  -194,  -197,  -197,
    -197,  -197,  -197,  -197,  -197,  -197,  -142,  -197,  -197,  -197,
    -197,  -197,  -197,  -197,    35,  -197,  -197,  -197,  -186,  -197,
     -15,    -6,  -192
};

  /* YYDEFGOTO[NTERM-NUM].  */
static const yytype_int16 yydefgoto[] =
{
      -1,    49,    50,    51,    52,    87,    88,    53,   108,    54,
     209,   294,   193,   277,    55,   135,   136,    57,   105,   347,
      58,   137,   215,   216,   217,   218,   219,   220,   221,   222,
     223,   268,   269,   270,   271,   272,   273,   274,   275,   276,
     390,   391,   392,   393,   394,   395,   396,   397,   238,   237,
     235,   388,    60,    61,    62,   228,   312,   352,    63,   226,
     309,   310,    64,    65,    66,    67,    68,    69,   340,   375,
     401,   423,    70,   338,   422,   443,    71,    72,   174,   324,
      73,   109,   382,    74,    90,    75,   104,   406,   429,   407,
     434,   433,   428,    76,   101,   292,   378,   379,   402,   432,
      77,    84,   201,    78,    95,    96,    79,    80,   178,   179,
      81,    82,    83
};

  /* YYTABLE[YYPACT[STATE-NUM]] -- What to do in state STATE-NUM.  If
     positive, shift that token.  If negative, reduce the rule whose
     number is the opposite.  If YYTABLE_NINF, syntax error.  */
static const yytype_int16 yytable[] =
{
      56,   232,   279,   211,   103,   285,    59,   192,    93,   125,
     167,   126,   319,   295,   168,   106,   107,   127,    85,   139,
     290,    86,   100,   130,   156,   157,   158,   159,   160,   161,
     162,   163,   164,   165,   166,   129,   132,    89,    91,    92,
     167,     8,    98,    94,   168,   124,    13,    14,    97,   133,
     134,    56,   102,   138,    21,   141,    23,    24,    25,    26,
      27,    28,   293,   123,   170,    31,    32,    33,    34,    35,
      36,    37,    38,   172,   321,   128,    48,   140,   173,   171,
     326,   131,    48,   192,   194,   195,   200,   177,   196,   203,
     197,   162,   163,   164,   165,   166,   202,   199,   204,   206,
     208,   167,    46,   205,    39,   168,   123,    40,    41,    42,
      43,    44,   212,   210,   213,   225,   214,   227,   231,    99,
     259,   164,   165,   166,    47,    48,   265,   266,   224,   167,
     -22,   278,   281,   168,   229,   284,    94,   230,   291,   297,
     371,   168,   336,   337,   339,   377,   234,   341,   400,   343,
     236,   345,   260,   239,   240,   241,   242,   243,   244,   245,
     246,   247,   248,   249,   250,   251,   252,   253,   254,   255,
     256,   257,   258,   350,   261,   351,   262,   160,   161,   162,
     163,   164,   165,   166,   353,   358,   267,   380,   405,   167,
     403,   385,   344,   168,   421,   110,   111,   112,   113,   114,
     115,   116,   117,   118,   119,   264,   427,  -214,   286,   435,
     436,   439,   177,   426,   440,   441,   296,   142,   409,   298,
     280,   299,   300,   301,   302,   303,   304,   305,   306,   307,
     120,   121,   308,   384,   311,   437,   122,   404,     0,   287,
       0,   316,     0,   317,   318,   381,   123,     0,   110,   111,
     112,   113,   114,   115,   116,   117,   118,   119,   282,     0,
       0,     0,     0,     0,     0,     0,   177,     0,     0,     0,
     325,     0,   177,     0,   327,   328,   329,   330,   331,   332,
     333,   334,   335,   120,   121,     8,     0,     0,     0,   122,
       0,    14,     0,     0,     0,     0,     0,   342,    21,   123,
     442,     0,     0,     0,   444,   445,   446,     0,     0,     0,
     425,    33,    34,    35,    36,    37,    38,     0,     0,   354,
     355,     0,     0,     0,   370,   357,   110,   111,   112,   113,
     114,   115,   116,   117,   118,   119,     0,     0,     0,     0,
     374,     0,     0,   372,   373,   359,   360,   361,   362,   363,
       0,   364,   365,   366,   367,   383,     0,   308,     8,     0,
       0,   120,   121,     0,    14,   389,     0,   122,     0,     0,
       0,    21,     0,     0,     0,     0,     0,   123,     0,     0,
     368,   369,   408,     0,    33,    34,    35,    36,    37,    38,
       0,   311,     0,   410,   412,     0,   413,   414,   415,   416,
     417,   418,   419,   420,     0,     0,     0,     0,   424,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,   431,     0,     0,     0,     0,   430,     0,
       0,     0,     0,     0,     1,     2,     3,     0,     0,     4,
       5,     0,     6,   447,     7,     8,     9,    10,    11,    12,
      13,    14,    15,    16,    17,    18,    19,    20,    21,    22,
      23,    24,    25,    26,    27,    28,    29,     0,    30,    31,
      32,    33,    34,    35,    36,    37,    38,     0,     0,     0,
       0,     0,     0,     0,     0,   359,   360,   361,   362,   363,
       0,   364,   365,   366,   367,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,    39,     0,
       0,    40,    41,    42,    43,    44,     0,     0,     0,     0,
     368,   369,     0,    45,     0,     0,    46,     0,    47,    48,
       1,     2,     3,   411,     0,     0,     5,     0,     6,     0,
       7,     8,     9,    10,    11,    12,    13,    14,    15,    16,
      17,    18,     0,    20,    21,    22,    23,    24,    25,    26,
      27,    28,     0,     0,    30,    31,    32,    33,    34,    35,
      36,    37,    38,     0,     0,     0,     0,   180,   181,   182,
     183,   184,   185,   186,   187,   188,   189,     0,     0,     0,
       0,   359,   360,   361,   362,   363,     0,   364,   365,   366,
     367,     0,     0,     0,    39,     0,     0,    40,    41,    42,
      43,    44,   190,   191,     0,     8,     0,     0,     0,    45,
      13,    14,    46,     0,    47,    48,   368,   369,    21,     0,
      23,    24,    25,    26,    27,    28,     0,     0,     0,    31,
     175,    33,    34,    35,    36,    37,    38,   143,   144,   145,
     146,   147,   148,   149,   150,   151,   152,   153,   154,   155,
     156,   157,   158,   159,   160,   161,   162,   163,   164,   165,
     166,     0,     0,     0,     0,     0,   167,     0,    39,     0,
     168,    40,    41,    42,    43,    44,     0,     8,     0,     0,
       0,     0,    13,    14,     0,   176,     0,     0,    47,    48,
      21,     0,    23,    24,    25,    26,    27,    28,     0,     0,
       0,    31,   175,    33,    34,    35,    36,    37,    38,   143,
     144,   145,   146,   147,   148,   149,   150,   151,   152,   153,
     154,   155,   156,   157,   158,   159,   160,   161,   162,   163,
     164,   165,   166,     0,     0,     0,     0,     0,   167,     0,
      39,     0,   313,    40,    41,    42,    43,    44,     0,     8,
       0,     0,     0,     0,    13,    14,     0,   289,     0,     0,
      47,    48,    21,     0,    23,    24,    25,    26,    27,    28,
       0,     0,     0,    31,   175,    33,    34,    35,    36,    37,
      38,   143,   144,   145,   146,   147,   148,   149,   150,   151,
     152,   153,   154,   155,   156,   157,   158,   159,   160,   161,
     162,   163,   164,   165,   166,     0,     0,     0,     0,     0,
     167,     0,    39,     0,   314,    40,    41,    42,    43,    44,
       0,     8,     0,     0,     0,     0,    13,    14,     0,   320,
       0,     0,    47,    48,    21,     0,    23,    24,    25,    26,
      27,    28,     0,     0,     0,    31,    32,    33,    34,    35,
      36,    37,    38,     0,   145,   146,   147,   148,   149,   150,
     151,   152,   153,   154,   155,   156,   157,   158,   159,   160,
     161,   162,   163,   164,   165,   166,     0,     0,     0,     0,
       0,   167,     0,     0,    39,   168,     0,    40,    41,    42,
      43,    44,     0,     8,     0,     0,     0,     0,    13,    14,
       0,     0,     0,     0,    47,    48,    21,     0,    23,    24,
      25,    26,    27,    28,     0,     0,     0,    31,   198,    33,
      34,    35,    36,    37,    38,   146,   147,   148,   149,   150,
     151,   152,   153,   154,   155,   156,   157,   158,   159,   160,
     161,   162,   163,   164,   165,   166,     0,     0,     0,     0,
       0,   167,     0,     0,     0,   168,    39,     0,     0,    40,
      41,    42,    43,    44,     0,     8,     0,     0,     0,     0,
      13,    14,     0,     0,     0,     0,    47,    48,    21,     0,
      23,    24,    25,    26,    27,    28,     0,     0,     0,    31,
     175,    33,    34,    35,    36,    37,    38,   147,   148,   149,
     150,   151,   152,   153,   154,   155,   156,   157,   158,   159,
     160,   161,   162,   163,   164,   165,   166,     0,     0,     0,
       0,     0,   167,     0,     0,     0,   168,     0,    39,     0,
       0,    40,    41,    42,    43,    44,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,    47,    48,
     143,   144,   145,   146,   147,   148,   149,   150,   151,   152,
     153,   154,   155,   156,   157,   158,   159,   160,   161,   162,
     163,   164,   165,   166,     0,     0,     0,     0,     0,   167,
       0,     0,     0,   168,     0,     0,     0,   233,   143,   144,
     145,   146,   147,   148,   149,   150,   151,   152,   153,   154,
     155,   156,   157,   158,   159,   160,   161,   162,   163,   164,
     165,   166,     0,     0,     0,     0,     0,   167,     0,     0,
       0,   168,     0,     0,     0,   263,   143,   144,   145,   146,
     147,   148,   149,   150,   151,   152,   153,   154,   155,   156,
     157,   158,   159,   160,   161,   162,   163,   164,   165,   166,
       0,     0,     0,     0,     0,   167,     0,     0,     0,   168,
       0,     0,     0,   288,   143,   144,   145,   146,   147,   148,
     149,   150,   151,   152,   153,   154,   155,   156,   157,   158,
     159,   160,   161,   162,   163,   164,   165,   166,     0,     0,
       0,     0,     0,   167,     0,     0,     0,   168,     0,     0,
       0,   315,   143,   144,   145,   146,   147,   148,   149,   150,
     151,   152,   153,   154,   155,   156,   157,   158,   159,   160,
     161,   162,   163,   164,   165,   166,     0,     0,     0,     0,
       0,   167,     0,     0,     0,   168,     0,     0,     0,   323,
     143,   144,   145,   146,   147,   148,   149,   150,   151,   152,
     153,   154,   155,   156,   157,   158,   159,   160,   161,   162,
     163,   164,   165,   166,     0,     0,     0,     0,     0,   167,
       0,     0,     0,   168,     0,     0,     0,   346,   143,   144,
     145,   146,   147,   148,   149,   150,   151,   152,   153,   154,
     155,   156,   157,   158,   159,   160,   161,   162,   163,   164,
     165,   166,     0,     0,     0,     0,     0,   167,     0,     0,
       0,   168,     0,     0,     0,   348,   143,   144,   145,   146,
     147,   148,   149,   150,   151,   152,   153,   154,   155,   156,
     157,   158,   159,   160,   161,   162,   163,   164,   165,   166,
       0,     0,     0,     0,     0,   167,     0,     0,     0,   168,
       0,     0,     0,  -158,   143,   144,   145,   146,   147,   148,
     149,   150,   151,   152,   153,   154,   155,   156,   157,   158,
     159,   160,   161,   162,   163,   164,   165,   166,     0,     0,
       0,     0,     0,   167,     0,     0,     0,   168,     0,     0,
       0,   376,   143,   144,   145,   146,   147,   148,   149,   150,
     151,   152,   153,   154,   155,   156,   157,   158,   159,   160,
     161,   162,   163,   164,   165,   166,     0,     0,     0,     0,
       0,   167,     0,     0,     0,   168,     0,     0,     0,   398,
     143,   144,   145,   146,   147,   148,   149,   150,   151,   152,
     153,   154,   155,   156,   157,   158,   159,   160,   161,   162,
     163,   164,   165,   166,     0,     0,     0,     0,     0,   167,
       0,     0,     0,   168,     0,     0,     0,   438,   143,   144,
     145,   146,   147,   148,   149,   150,   151,   152,   153,   154,
     155,   156,   157,   158,   159,   160,   161,   162,   163,   164,
     165,   166,     0,     0,     0,     0,     0,   167,     0,     0,
       0,   168,     0,   169,   143,   144,   145,   146,   147,   148,
     149,   150,   151,   152,   153,   154,   155,   156,   157,   158,
     159,   160,   161,   162,   163,   164,   165,   166,     0,     0,
       0,     0,     0,   167,     0,     0,     0,   168,     0,   207,
     143,   144,   145,   146,   147,   148,   149,   150,   151,   152,
     153,   154,   155,   156,   157,   158,   159,   160,   161,   162,
     163,   164,   165,   166,     0,     0,     0,     0,     0,   167,
       0,     0,     0,   168,     0,   283,   143,   144,   145,   146,
     147,   148,   149,   150,   151,   152,   153,   154,   155,   156,
     157,   158,   159,   160,   161,   162,   163,   164,   165,   166,
       0,     0,     0,     0,     0,   167,     0,     0,     0,   168,
       0,   399,   143,   144,   145,   146,   147,   148,   149,   150,
     151,   152,   153,   154,   155,   156,   157,   158,   159,   160,
     161,   162,   163,   164,   165,   166,     0,     0,     0,     0,
       0,   167,     0,     0,     0,   168,   322,   349,   143,   144,
     145,   146,   147,   148,   149,   150,   151,   152,   153,   154,
     155,   156,   157,   158,   159,   160,   161,   162,   163,   164,
     165,   166,     0,     0,     0,     0,     0,   167,     0,     0,
       0,   168,   356,   143,   144,   145,   146,   147,   148,   149,
     150,   151,   152,   153,   154,   155,   156,   157,   158,   159,
     160,   161,   162,   163,   164,   165,   166,     0,     0,     0,
       0,     0,   167,     0,     0,     0,   168,   143,   144,   145,
     146,   147,   148,   149,   150,   151,   152,   153,   154,   155,
     156,   157,   158,   159,   160,   161,   162,   163,   164,   165,
     166,     0,     0,     0,     0,     0,   167,     0,     0,     0,
     168,   386,   143,   144,   145,   146,   147,   148,   149,   150,
     151,   152,   153,   154,   155,   156,   157,   158,   159,   160,
     161,   162,   163,   164,   165,   166,     0,     0,     0,     0,
       0,   167,     0,     0,     0,   168,   387,   148,   149,   150,
     151,   152,   153,   154,   155,   156,   157,   158,   159,   160,
     161,   162,   163,   164,   165,   166,     0,     0,     0,     0,
       0,   167,     0,     0,     0,   168,   149,   150,   151,   152,
     153,   154,   155,   156,   157,   158,   159,   160,   161,   162,
     163,   164,   165,   166,     0,     0,     0,     0,     0,   167,
       0,     0,     0,   168,   150,   151,   152,   153,   154,   155,
     156,   157,   158,   159,   160,   161,   162,   163,   164,   165,
     166,     0,     0,     0,     0,     0,   167,     0,     0,     0,
     168
};

static const yytype_int16 yycheck[] =
{
       0,   136,   194,   105,    19,   201,     0,    39,     4,    95,
      86,    97,    46,   209,    90,    38,    39,    39,    92,    39,
     206,    39,    16,    39,    70,    71,    72,    73,    74,    75,
      76,    77,    78,    79,    80,    41,    42,    92,    97,    97,
      86,    14,    39,    39,    90,    39,    19,    20,    97,    43,
      44,    51,    39,    47,    27,     0,    29,    30,    31,    32,
      33,    34,    94,    97,    97,    38,    39,    40,    41,    42,
      43,    44,    45,    67,   260,    97,    98,    97,    72,    92,
     266,    97,    98,    39,    46,    92,    39,    81,    93,    92,
      90,    76,    77,    78,    79,    80,    46,    91,    93,    97,
      39,    86,    95,    97,    77,    90,    97,    80,    81,    82,
      83,    84,    92,    97,    97,    96,   110,    94,    96,    92,
      39,    78,    79,    80,    97,    98,    94,    93,   122,    86,
      93,    92,    37,    90,   128,    56,    39,   131,    97,     7,
     336,    90,    93,    97,    92,    36,   140,    94,    12,    95,
     144,    94,   167,   147,   148,   149,   150,   151,   152,   153,
     154,   155,   156,   157,   158,   159,   160,   161,   162,   163,
     164,   165,   166,    96,   168,    93,   170,    74,    75,    76,
      77,    78,    79,    80,    94,    94,   180,    95,     6,    86,
      96,    93,   294,    90,    92,    46,    47,    48,    49,    50,
      51,    52,    53,    54,    55,    56,    10,     6,   202,    56,
      96,    56,   206,   405,    56,    56,   210,    51,   385,   213,
     196,   215,   216,   217,   218,   219,   220,   221,   222,   223,
      81,    82,   226,   351,   228,   429,    87,   379,    -1,   204,
      -1,   235,    -1,   237,   238,   347,    97,    -1,    46,    47,
      48,    49,    50,    51,    52,    53,    54,    55,    56,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,   260,    -1,    -1,    -1,
     264,    -1,   266,    -1,   268,   269,   270,   271,   272,   273,
     274,   275,   276,    81,    82,    14,    -1,    -1,    -1,    87,
      -1,    20,    -1,    -1,    -1,    -1,    -1,   291,    27,    97,
     435,    -1,    -1,    -1,   439,   440,   441,    -1,    -1,    -1,
      39,    40,    41,    42,    43,    44,    45,    -1,    -1,   313,
     314,    -1,    -1,    -1,   324,   319,    46,    47,    48,    49,
      50,    51,    52,    53,    54,    55,    -1,    -1,    -1,    -1,
     340,    -1,    -1,   337,   338,    46,    47,    48,    49,    50,
      -1,    52,    53,    54,    55,   349,    -1,   351,    14,    -1,
      -1,    81,    82,    -1,    20,   359,    -1,    87,    -1,    -1,
      -1,    27,    -1,    -1,    -1,    -1,    -1,    97,    -1,    -1,
      81,    82,   382,    -1,    40,    41,    42,    43,    44,    45,
      -1,   385,    -1,    94,   388,    -1,   390,   391,   392,   393,
     394,   395,   396,   397,    -1,    -1,    -1,    -1,   402,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,   423,    -1,    -1,    -1,    -1,   422,    -1,
      -1,    -1,    -1,    -1,     3,     4,     5,    -1,    -1,     8,
       9,    -1,    11,   443,    13,    14,    15,    16,    17,    18,
      19,    20,    21,    22,    23,    24,    25,    26,    27,    28,
      29,    30,    31,    32,    33,    34,    35,    -1,    37,    38,
      39,    40,    41,    42,    43,    44,    45,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    46,    47,    48,    49,    50,
      -1,    52,    53,    54,    55,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    77,    -1,
      -1,    80,    81,    82,    83,    84,    -1,    -1,    -1,    -1,
      81,    82,    -1,    92,    -1,    -1,    95,    -1,    97,    98,
       3,     4,     5,    94,    -1,    -1,     9,    -1,    11,    -1,
      13,    14,    15,    16,    17,    18,    19,    20,    21,    22,
      23,    24,    -1,    26,    27,    28,    29,    30,    31,    32,
      33,    34,    -1,    -1,    37,    38,    39,    40,    41,    42,
      43,    44,    45,    -1,    -1,    -1,    -1,    46,    47,    48,
      49,    50,    51,    52,    53,    54,    55,    -1,    -1,    -1,
      -1,    46,    47,    48,    49,    50,    -1,    52,    53,    54,
      55,    -1,    -1,    -1,    77,    -1,    -1,    80,    81,    82,
      83,    84,    81,    82,    -1,    14,    -1,    -1,    -1,    92,
      19,    20,    95,    -1,    97,    98,    81,    82,    27,    -1,
      29,    30,    31,    32,    33,    34,    -1,    -1,    -1,    38,
      39,    40,    41,    42,    43,    44,    45,    57,    58,    59,
      60,    61,    62,    63,    64,    65,    66,    67,    68,    69,
      70,    71,    72,    73,    74,    75,    76,    77,    78,    79,
      80,    -1,    -1,    -1,    -1,    -1,    86,    -1,    77,    -1,
      90,    80,    81,    82,    83,    84,    -1,    14,    -1,    -1,
      -1,    -1,    19,    20,    -1,    94,    -1,    -1,    97,    98,
      27,    -1,    29,    30,    31,    32,    33,    34,    -1,    -1,
      -1,    38,    39,    40,    41,    42,    43,    44,    45,    57,
      58,    59,    60,    61,    62,    63,    64,    65,    66,    67,
      68,    69,    70,    71,    72,    73,    74,    75,    76,    77,
      78,    79,    80,    -1,    -1,    -1,    -1,    -1,    86,    -1,
      77,    -1,    90,    80,    81,    82,    83,    84,    -1,    14,
      -1,    -1,    -1,    -1,    19,    20,    -1,    94,    -1,    -1,
      97,    98,    27,    -1,    29,    30,    31,    32,    33,    34,
      -1,    -1,    -1,    38,    39,    40,    41,    42,    43,    44,
      45,    57,    58,    59,    60,    61,    62,    63,    64,    65,
      66,    67,    68,    69,    70,    71,    72,    73,    74,    75,
      76,    77,    78,    79,    80,    -1,    -1,    -1,    -1,    -1,
      86,    -1,    77,    -1,    90,    80,    81,    82,    83,    84,
      -1,    14,    -1,    -1,    -1,    -1,    19,    20,    -1,    94,
      -1,    -1,    97,    98,    27,    -1,    29,    30,    31,    32,
      33,    34,    -1,    -1,    -1,    38,    39,    40,    41,    42,
      43,    44,    45,    -1,    59,    60,    61,    62,    63,    64,
      65,    66,    67,    68,    69,    70,    71,    72,    73,    74,
      75,    76,    77,    78,    79,    80,    -1,    -1,    -1,    -1,
      -1,    86,    -1,    -1,    77,    90,    -1,    80,    81,    82,
      83,    84,    -1,    14,    -1,    -1,    -1,    -1,    19,    20,
      -1,    -1,    -1,    -1,    97,    98,    27,    -1,    29,    30,
      31,    32,    33,    34,    -1,    -1,    -1,    38,    39,    40,
      41,    42,    43,    44,    45,    60,    61,    62,    63,    64,
      65,    66,    67,    68,    69,    70,    71,    72,    73,    74,
      75,    76,    77,    78,    79,    80,    -1,    -1,    -1,    -1,
      -1,    86,    -1,    -1,    -1,    90,    77,    -1,    -1,    80,
      81,    82,    83,    84,    -1,    14,    -1,    -1,    -1,    -1,
      19,    20,    -1,    -1,    -1,    -1,    97,    98,    27,    -1,
      29,    30,    31,    32,    33,    34,    -1,    -1,    -1,    38,
      39,    40,    41,    42,    43,    44,    45,    61,    62,    63,
      64,    65,    66,    67,    68,    69,    70,    71,    72,    73,
      74,    75,    76,    77,    78,    79,    80,    -1,    -1,    -1,
      -1,    -1,    86,    -1,    -1,    -1,    90,    -1,    77,    -1,
      -1,    80,    81,    82,    83,    84,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    97,    98,
      57,    58,    59,    60,    61,    62,    63,    64,    65,    66,
      67,    68,    69,    70,    71,    72,    73,    74,    75,    76,
      77,    78,    79,    80,    -1,    -1,    -1,    -1,    -1,    86,
      -1,    -1,    -1,    90,    -1,    -1,    -1,    94,    57,    58,
      59,    60,    61,    62,    63,    64,    65,    66,    67,    68,
      69,    70,    71,    72,    73,    74,    75,    76,    77,    78,
      79,    80,    -1,    -1,    -1,    -1,    -1,    86,    -1,    -1,
      -1,    90,    -1,    -1,    -1,    94,    57,    58,    59,    60,
      61,    62,    63,    64,    65,    66,    67,    68,    69,    70,
      71,    72,    73,    74,    75,    76,    77,    78,    79,    80,
      -1,    -1,    -1,    -1,    -1,    86,    -1,    -1,    -1,    90,
      -1,    -1,    -1,    94,    57,    58,    59,    60,    61,    62,
      63,    64,    65,    66,    67,    68,    69,    70,    71,    72,
      73,    74,    75,    76,    77,    78,    79,    80,    -1,    -1,
      -1,    -1,    -1,    86,    -1,    -1,    -1,    90,    -1,    -1,
      -1,    94,    57,    58,    59,    60,    61,    62,    63,    64,
      65,    66,    67,    68,    69,    70,    71,    72,    73,    74,
      75,    76,    77,    78,    79,    80,    -1,    -1,    -1,    -1,
      -1,    86,    -1,    -1,    -1,    90,    -1,    -1,    -1,    94,
      57,    58,    59,    60,    61,    62,    63,    64,    65,    66,
      67,    68,    69,    70,    71,    72,    73,    74,    75,    76,
      77,    78,    79,    80,    -1,    -1,    -1,    -1,    -1,    86,
      -1,    -1,    -1,    90,    -1,    -1,    -1,    94,    57,    58,
      59,    60,    61,    62,    63,    64,    65,    66,    67,    68,
      69,    70,    71,    72,    73,    74,    75,    76,    77,    78,
      79,    80,    -1,    -1,    -1,    -1,    -1,    86,    -1,    -1,
      -1,    90,    -1,    -1,    -1,    94,    57,    58,    59,    60,
      61,    62,    63,    64,    65,    66,    67,    68,    69,    70,
      71,    72,    73,    74,    75,    76,    77,    78,    79,    80,
      -1,    -1,    -1,    -1,    -1,    86,    -1,    -1,    -1,    90,
      -1,    -1,    -1,    94,    57,    58,    59,    60,    61,    62,
      63,    64,    65,    66,    67,    68,    69,    70,    71,    72,
      73,    74,    75,    76,    77,    78,    79,    80,    -1,    -1,
      -1,    -1,    -1,    86,    -1,    -1,    -1,    90,    -1,    -1,
      -1,    94,    57,    58,    59,    60,    61,    62,    63,    64,
      65,    66,    67,    68,    69,    70,    71,    72,    73,    74,
      75,    76,    77,    78,    79,    80,    -1,    -1,    -1,    -1,
      -1,    86,    -1,    -1,    -1,    90,    -1,    -1,    -1,    94,
      57,    58,    59,    60,    61,    62,    63,    64,    65,    66,
      67,    68,    69,    70,    71,    72,    73,    74,    75,    76,
      77,    78,    79,    80,    -1,    -1,    -1,    -1,    -1,    86,
      -1,    -1,    -1,    90,    -1,    -1,    -1,    94,    57,    58,
      59,    60,    61,    62,    63,    64,    65,    66,    67,    68,
      69,    70,    71,    72,    73,    74,    75,    76,    77,    78,
      79,    80,    -1,    -1,    -1,    -1,    -1,    86,    -1,    -1,
      -1,    90,    -1,    92,    57,    58,    59,    60,    61,    62,
      63,    64,    65,    66,    67,    68,    69,    70,    71,    72,
      73,    74,    75,    76,    77,    78,    79,    80,    -1,    -1,
      -1,    -1,    -1,    86,    -1,    -1,    -1,    90,    -1,    92,
      57,    58,    59,    60,    61,    62,    63,    64,    65,    66,
      67,    68,    69,    70,    71,    72,    73,    74,    75,    76,
      77,    78,    79,    80,    -1,    -1,    -1,    -1,    -1,    86,
      -1,    -1,    -1,    90,    -1,    92,    57,    58,    59,    60,
      61,    62,    63,    64,    65,    66,    67,    68,    69,    70,
      71,    72,    73,    74,    75,    76,    77,    78,    79,    80,
      -1,    -1,    -1,    -1,    -1,    86,    -1,    -1,    -1,    90,
      -1,    92,    57,    58,    59,    60,    61,    62,    63,    64,
      65,    66,    67,    68,    69,    70,    71,    72,    73,    74,
      75,    76,    77,    78,    79,    80,    -1,    -1,    -1,    -1,
      -1,    86,    -1,    -1,    -1,    90,    91,    56,    57,    58,
      59,    60,    61,    62,    63,    64,    65,    66,    67,    68,
      69,    70,    71,    72,    73,    74,    75,    76,    77,    78,
      79,    80,    -1,    -1,    -1,    -1,    -1,    86,    -1,    -1,
      -1,    90,    56,    57,    58,    59,    60,    61,    62,    63,
      64,    65,    66,    67,    68,    69,    70,    71,    72,    73,
      74,    75,    76,    77,    78,    79,    80,    -1,    -1,    -1,
      -1,    -1,    86,    -1,    -1,    -1,    90,    57,    58,    59,
      60,    61,    62,    63,    64,    65,    66,    67,    68,    69,
      70,    71,    72,    73,    74,    75,    76,    77,    78,    79,
      80,    -1,    -1,    -1,    -1,    -1,    86,    -1,    -1,    -1,
      90,    91,    57,    58,    59,    60,    61,    62,    63,    64,
      65,    66,    67,    68,    69,    70,    71,    72,    73,    74,
      75,    76,    77,    78,    79,    80,    -1,    -1,    -1,    -1,
      -1,    86,    -1,    -1,    -1,    90,    91,    62,    63,    64,
      65,    66,    67,    68,    69,    70,    71,    72,    73,    74,
      75,    76,    77,    78,    79,    80,    -1,    -1,    -1,    -1,
      -1,    86,    -1,    -1,    -1,    90,    63,    64,    65,    66,
      67,    68,    69,    70,    71,    72,    73,    74,    75,    76,
      77,    78,    79,    80,    -1,    -1,    -1,    -1,    -1,    86,
      -1,    -1,    -1,    90,    64,    65,    66,    67,    68,    69,
      70,    71,    72,    73,    74,    75,    76,    77,    78,    79,
      80,    -1,    -1,    -1,    -1,    -1,    86,    -1,    -1,    -1,
      90
};

  /* YYSTOS[STATE-NUM] -- The (internal number of the) accessing
     symbol of state STATE-NUM.  */
static const yytype_uint8 yystos[] =
{
       0,     3,     4,     5,     8,     9,    11,    13,    14,    15,
      16,    17,    18,    19,    20,    21,    22,    23,    24,    25,
      26,    27,    28,    29,    30,    31,    32,    33,    34,    35,
      37,    38,    39,    40,    41,    42,    43,    44,    45,    77,
      80,    81,    82,    83,    84,    92,    95,    97,    98,   100,
     101,   102,   103,   106,   108,   113,   115,   116,   119,   120,
     151,   152,   153,   157,   161,   162,   163,   164,   165,   166,
     171,   175,   176,   179,   182,   184,   192,   199,   202,   205,
     206,   209,   210,   211,   200,    92,    39,   104,   105,    92,
     183,    97,    97,     4,    39,   203,   204,    97,    39,    92,
     120,   193,    39,   209,   185,   117,    38,    39,   107,   180,
      46,    47,    48,    49,    50,    51,    52,    53,    54,    55,
      81,    82,    87,    97,   120,    95,    97,    39,    97,   210,
      39,    97,   210,   120,   120,   114,   115,   120,   120,    39,
      97,     0,   101,    57,    58,    59,    60,    61,    62,    63,
      64,    65,    66,    67,    68,    69,    70,    71,    72,    73,
      74,    75,    76,    77,    78,    79,    80,    86,    90,    92,
      97,    92,   120,   120,   177,    39,    94,   120,   207,   208,
      46,    47,    48,    49,    50,    51,    52,    53,    54,    55,
      81,    82,    39,   111,    46,    92,    93,   115,    39,   120,
      39,   201,    46,    92,    93,   120,    97,    92,    39,   109,
      97,   113,    92,    97,   120,   121,   122,   123,   124,   125,
     126,   127,   128,   129,   120,    96,   158,    94,   154,   120,
     120,    96,   114,    94,   120,   149,   120,   148,   147,   120,
     120,   120,   120,   120,   120,   120,   120,   120,   120,   120,
     120,   120,   120,   120,   120,   120,   120,   120,   120,    39,
     209,   120,   120,    94,    56,    94,    93,   120,   130,   131,
     132,   133,   134,   135,   136,   137,   138,   112,    92,   211,
     104,    37,    56,    92,    56,   111,   120,   203,    94,    94,
     207,    97,   194,    94,   110,   111,   120,     7,   120,   120,
     120,   120,   120,   120,   120,   120,   120,   120,   120,   159,
     160,   120,   155,    90,    90,    94,   120,   120,   120,    46,
      94,   207,    91,    94,   178,   120,   207,   120,   120,   120,
     120,   120,   120,   120,   120,   120,    93,    97,   172,    92,
     167,    94,   120,    95,   113,    94,    94,   118,    94,    56,
      96,    93,   156,    94,   120,   120,    56,   120,    94,    46,
      47,    48,    49,    50,    52,    53,    54,    55,    81,    82,
     115,   111,   120,   120,   115,   168,    94,    36,   195,   196,
      95,   113,   181,   120,   159,    93,    91,    91,   150,   120,
     139,   140,   141,   142,   143,   144,   145,   146,    94,    92,
      12,   169,   197,    96,   195,     6,   186,   188,   115,   155,
      94,    94,   120,   120,   120,   120,   120,   120,   120,   120,
     120,    92,   173,   170,   120,    39,   211,    10,   191,   187,
     120,   115,   198,   190,   189,    56,    96,   186,    94,    56,
      56,    56,   114,   174,   114,   114,   114,   115
};

  /* YYR1[YYN] -- Symbol number of symbol that rule YYN derives.  */
static const yytype_uint8 yyr1[] =
{
       0,    99,   100,   100,   100,   101,   101,   102,   102,   102,
     102,   103,   104,   104,   105,   106,   107,   107,   109,   108,
     110,   110,   112,   111,   111,   113,   114,   114,   115,   115,
     115,   117,   118,   116,   119,   119,   119,   120,   120,   121,
     120,   122,   120,   123,   120,   124,   120,   125,   120,   126,
     120,   127,   120,   128,   120,   129,   120,   130,   120,   131,
     120,   132,   120,   133,   120,   134,   120,   135,   120,   136,
     120,   137,   120,   138,   120,   120,   139,   120,   140,   120,
     141,   120,   142,   120,   143,   120,   144,   120,   145,   120,
     146,   120,   120,   120,   120,   120,   120,   120,   120,   120,
     120,   120,   120,   120,   120,   120,   120,   120,   120,   120,
     120,   120,   120,   120,   120,   120,   120,   120,   120,   120,
     120,   120,   120,   120,   120,   120,   120,   120,   120,   120,
     120,   120,   120,   147,   120,   148,   120,   120,   120,   120,
     149,   150,   120,   120,   151,   151,   151,   151,   151,   151,
     151,   151,   152,   154,   153,   153,   156,   155,   155,   158,
     157,   157,   159,   159,   160,   161,   161,   161,   161,   161,
     161,   162,   162,   162,   162,   162,   162,   162,   162,   162,
     162,   162,   162,   162,   163,   163,   164,   164,   164,   164,
     165,   165,   167,   166,   168,   168,   170,   169,   172,   173,
     174,   171,   175,   176,   176,   178,   177,   180,   181,   179,
     183,   182,   185,   184,   187,   186,   186,   189,   188,   190,
     188,   191,   191,   193,   192,   194,   194,   195,   195,   197,
     198,   196,   200,   199,   201,   199,   202,   203,   203,   204,
     204,   205,   205,   205,   206,   206,   207,   207,   208,   208,
     209,   210,   210,   211,   211,   211,   211,   211,   211,   211,
     211,   211
};

  /* YYR2[YYN] -- Number of symbols on the right hand side of rule YYN.  */
static const yytype_uint8 yyr2[] =
{
       0,     2,     1,     1,     0,     2,     1,     1,     1,     1,
       1,     3,     3,     1,     3,     3,     1,     1,     0,     5,
       2,     1,     0,     4,     1,     3,     2,     0,     1,     1,
       1,     0,     0,     6,     2,     1,     1,     3,     3,     0,
       4,     0,     4,     0,     4,     0,     4,     0,     4,     0,
       4,     0,     4,     0,     4,     0,     4,     0,     4,     0,
       4,     0,     4,     0,     4,     0,     4,     0,     4,     0,
       4,     0,     4,     0,     4,     6,     0,     7,     0,     7,
       0,     7,     0,     7,     0,     7,     0,     7,     0,     7,
       0,     7,     4,     3,     1,     5,     3,     5,     4,     3,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     7,     7,     5,     5,     3,     3,     3,     3,     3,
       3,     3,     3,     3,     3,     3,     3,     3,     3,     3,
       3,     3,     3,     0,     4,     0,     4,     3,     3,     3,
       0,     0,     7,     1,     1,     1,     1,     1,     1,     1,
       1,     1,     4,     0,     5,     3,     0,     4,     1,     0,
       5,     3,     3,     1,     3,     1,     1,     1,     1,     1,
       1,     2,     1,     1,     1,     1,     1,     1,     1,     1,
       1,     1,     2,     2,     2,     1,     1,     1,     1,     1,
       3,     2,     0,     6,     1,     2,     0,     3,     0,     0,
       0,    12,     2,     4,     4,     0,     4,     0,     0,     7,
       0,     8,     0,     9,     0,     3,     1,     0,     5,     0,
       5,     3,     0,     0,     7,     3,     0,     2,     1,     0,
       0,     6,     0,     4,     0,     5,     3,     3,     1,     1,
       3,     2,     5,     4,     3,     2,     3,     1,     1,     3,
       2,     2,     4,     1,     1,     1,     1,     1,     1,     1,
       1,     1
};


#define yyerrok         (yyerrstatus = 0)
#define yyclearin       (yychar = YYEMPTY)
#define YYEMPTY         (-2)
#define YYEOF           0

#define YYACCEPT        goto yyacceptlab
#define YYABORT         goto yyabortlab
#define YYERROR         goto yyerrorlab


#define YYRECOVERING()  (!!yyerrstatus)

#define YYBACKUP(Token, Value)                                  \
do                                                              \
  if (yychar == YYEMPTY)                                        \
    {                                                           \
      yychar = (Token);                                         \
      yylval = (Value);                                         \
      YYPOPSTACK (yylen);                                       \
      yystate = *yyssp;                                         \
      goto yybackup;                                            \
    }                                                           \
  else                                                          \
    {                                                           \
      yyerror (scanner, pLexer, pCompiler, pScript, YY_("syntax error: cannot back up")); \
      YYERROR;                                                  \
    }                                                           \
while (0)

/* Error token number */
#define YYTERROR        1
#define YYERRCODE       256



/* Enable debugging if requested.  */
#if YYDEBUG

# ifndef YYFPRINTF
#  include <stdio.h> /* INFRINGES ON USER NAME SPACE */
#  define YYFPRINTF fprintf
# endif

# define YYDPRINTF(Args)                        \
do {                                            \
  if (yydebug)                                  \
    YYFPRINTF Args;                             \
} while (0)

/* This macro is provided for backward compatibility. */
#ifndef YY_LOCATION_PRINT
# define YY_LOCATION_PRINT(File, Loc) ((void) 0)
#endif


# define YY_SYMBOL_PRINT(Title, Type, Value, Location)                    \
do {                                                                      \
  if (yydebug)                                                            \
    {                                                                     \
      YYFPRINTF (stderr, "%s ", Title);                                   \
      yy_symbol_print (stderr,                                            \
                  Type, Value, scanner, pLexer, pCompiler, pScript); \
      YYFPRINTF (stderr, "\n");                                           \
    }                                                                     \
} while (0)


/*----------------------------------------.
| Print this symbol's value on YYOUTPUT.  |
`----------------------------------------*/

static void
yy_symbol_value_print (FILE *yyoutput, int yytype, YYSTYPE const * const yyvaluep, yyscan_t scanner, NXSL_Lexer *pLexer, NXSL_Compiler *pCompiler, NXSL_Program *pScript)
{
  FILE *yyo = yyoutput;
  YYUSE (yyo);
  YYUSE (scanner);
  YYUSE (pLexer);
  YYUSE (pCompiler);
  YYUSE (pScript);
  if (!yyvaluep)
    return;
# ifdef YYPRINT
  if (yytype < YYNTOKENS)
    YYPRINT (yyoutput, yytoknum[yytype], *yyvaluep);
# endif
  YYUSE (yytype);
}


/*--------------------------------.
| Print this symbol on YYOUTPUT.  |
`--------------------------------*/

static void
yy_symbol_print (FILE *yyoutput, int yytype, YYSTYPE const * const yyvaluep, yyscan_t scanner, NXSL_Lexer *pLexer, NXSL_Compiler *pCompiler, NXSL_Program *pScript)
{
  YYFPRINTF (yyoutput, "%s %s (",
             yytype < YYNTOKENS ? "token" : "nterm", yytname[yytype]);

  yy_symbol_value_print (yyoutput, yytype, yyvaluep, scanner, pLexer, pCompiler, pScript);
  YYFPRINTF (yyoutput, ")");
}

/*------------------------------------------------------------------.
| yy_stack_print -- Print the state stack from its BOTTOM up to its |
| TOP (included).                                                   |
`------------------------------------------------------------------*/

static void
yy_stack_print (yytype_int16 *yybottom, yytype_int16 *yytop)
{
  YYFPRINTF (stderr, "Stack now");
  for (; yybottom <= yytop; yybottom++)
    {
      int yybot = *yybottom;
      YYFPRINTF (stderr, " %d", yybot);
    }
  YYFPRINTF (stderr, "\n");
}

# define YY_STACK_PRINT(Bottom, Top)                            \
do {                                                            \
  if (yydebug)                                                  \
    yy_stack_print ((Bottom), (Top));                           \
} while (0)


/*------------------------------------------------.
| Report that the YYRULE is going to be reduced.  |
`------------------------------------------------*/

static void
yy_reduce_print (yytype_int16 *yyssp, YYSTYPE *yyvsp, int yyrule, yyscan_t scanner, NXSL_Lexer *pLexer, NXSL_Compiler *pCompiler, NXSL_Program *pScript)
{
  unsigned long int yylno = yyrline[yyrule];
  int yynrhs = yyr2[yyrule];
  int yyi;
  YYFPRINTF (stderr, "Reducing stack by rule %d (line %lu):\n",
             yyrule - 1, yylno);
  /* The symbols being reduced.  */
  for (yyi = 0; yyi < yynrhs; yyi++)
    {
      YYFPRINTF (stderr, "   $%d = ", yyi + 1);
      yy_symbol_print (stderr,
                       yystos[yyssp[yyi + 1 - yynrhs]],
                       &(yyvsp[(yyi + 1) - (yynrhs)])
                                              , scanner, pLexer, pCompiler, pScript);
      YYFPRINTF (stderr, "\n");
    }
}

# define YY_REDUCE_PRINT(Rule)          \
do {                                    \
  if (yydebug)                          \
    yy_reduce_print (yyssp, yyvsp, Rule, scanner, pLexer, pCompiler, pScript); \
} while (0)

/* Nonzero means print parse trace.  It is left uninitialized so that
   multiple parsers can coexist.  */
int yydebug;
#else /* !YYDEBUG */
# define YYDPRINTF(Args)
# define YY_SYMBOL_PRINT(Title, Type, Value, Location)
# define YY_STACK_PRINT(Bottom, Top)
# define YY_REDUCE_PRINT(Rule)
#endif /* !YYDEBUG */


/* YYINITDEPTH -- initial size of the parser's stacks.  */
#ifndef YYINITDEPTH
# define YYINITDEPTH 200
#endif

/* YYMAXDEPTH -- maximum size the stacks can grow to (effective only
   if the built-in stack extension method is used).

   Do not make this value too large; the results are undefined if
   YYSTACK_ALLOC_MAXIMUM < YYSTACK_BYTES (YYMAXDEPTH)
   evaluated with infinite-precision integer arithmetic.  */

#ifndef YYMAXDEPTH
# define YYMAXDEPTH 10000
#endif


#if YYERROR_VERBOSE

# ifndef yystrlen
#  if defined __GLIBC__ && defined _STRING_H
#   define yystrlen strlen
#  else
/* Return the length of YYSTR.  */
static YYSIZE_T
yystrlen (const char *yystr)
{
  YYSIZE_T yylen;
  for (yylen = 0; yystr[yylen]; yylen++)
    continue;
  return yylen;
}
#  endif
# endif

# ifndef yystpcpy
#  if defined __GLIBC__ && defined _STRING_H && defined _GNU_SOURCE
#   define yystpcpy stpcpy
#  else
/* Copy YYSRC to YYDEST, returning the address of the terminating '\0' in
   YYDEST.  */
static char *
yystpcpy (char *yydest, const char *yysrc)
{
  char *yyd = yydest;
  const char *yys = yysrc;

  while ((*yyd++ = *yys++) != '\0')
    continue;

  return yyd - 1;
}
#  endif
# endif

# ifndef yytnamerr
/* Copy to YYRES the contents of YYSTR after stripping away unnecessary
   quotes and backslashes, so that it's suitable for yyerror.  The
   heuristic is that double-quoting is unnecessary unless the string
   contains an apostrophe, a comma, or backslash (other than
   backslash-backslash).  YYSTR is taken from yytname.  If YYRES is
   null, do not copy; instead, return the length of what the result
   would have been.  */
static YYSIZE_T
yytnamerr (char *yyres, const char *yystr)
{
  if (*yystr == '"')
    {
      YYSIZE_T yyn = 0;
      char const *yyp = yystr;

      for (;;)
        switch (*++yyp)
          {
          case '\'':
          case ',':
            goto do_not_strip_quotes;

          case '\\':
            if (*++yyp != '\\')
              goto do_not_strip_quotes;
            /* Fall through.  */
          default:
            if (yyres)
              yyres[yyn] = *yyp;
            yyn++;
            break;

          case '"':
            if (yyres)
              yyres[yyn] = '\0';
            return yyn;
          }
    do_not_strip_quotes: ;
    }

  if (! yyres)
    return yystrlen (yystr);

  return yystpcpy (yyres, yystr) - yyres;
}
# endif

/* Copy into *YYMSG, which is of size *YYMSG_ALLOC, an error message
   about the unexpected token YYTOKEN for the state stack whose top is
   YYSSP.

   Return 0 if *YYMSG was successfully written.  Return 1 if *YYMSG is
   not large enough to hold the message.  In that case, also set
   *YYMSG_ALLOC to the required number of bytes.  Return 2 if the
   required number of bytes is too large to store.  */
static int
yysyntax_error (YYSIZE_T *yymsg_alloc, char **yymsg,
                yytype_int16 *yyssp, int yytoken)
{
  YYSIZE_T yysize0 = yytnamerr (YY_NULLPTR, yytname[yytoken]);
  YYSIZE_T yysize = yysize0;
  enum { YYERROR_VERBOSE_ARGS_MAXIMUM = 5 };
  /* Internationalized format string. */
  const char *yyformat = YY_NULLPTR;
  /* Arguments of yyformat. */
  char const *yyarg[YYERROR_VERBOSE_ARGS_MAXIMUM];
  /* Number of reported tokens (one for the "unexpected", one per
     "expected"). */
  int yycount = 0;

  /* There are many possibilities here to consider:
     - If this state is a consistent state with a default action, then
       the only way this function was invoked is if the default action
       is an error action.  In that case, don't check for expected
       tokens because there are none.
     - The only way there can be no lookahead present (in yychar) is if
       this state is a consistent state with a default action.  Thus,
       detecting the absence of a lookahead is sufficient to determine
       that there is no unexpected or expected token to report.  In that
       case, just report a simple "syntax error".
     - Don't assume there isn't a lookahead just because this state is a
       consistent state with a default action.  There might have been a
       previous inconsistent state, consistent state with a non-default
       action, or user semantic action that manipulated yychar.
     - Of course, the expected token list depends on states to have
       correct lookahead information, and it depends on the parser not
       to perform extra reductions after fetching a lookahead from the
       scanner and before detecting a syntax error.  Thus, state merging
       (from LALR or IELR) and default reductions corrupt the expected
       token list.  However, the list is correct for canonical LR with
       one exception: it will still contain any token that will not be
       accepted due to an error action in a later state.
  */
  if (yytoken != YYEMPTY)
    {
      int yyn = yypact[*yyssp];
      yyarg[yycount++] = yytname[yytoken];
      if (!yypact_value_is_default (yyn))
        {
          /* Start YYX at -YYN if negative to avoid negative indexes in
             YYCHECK.  In other words, skip the first -YYN actions for
             this state because they are default actions.  */
          int yyxbegin = yyn < 0 ? -yyn : 0;
          /* Stay within bounds of both yycheck and yytname.  */
          int yychecklim = YYLAST - yyn + 1;
          int yyxend = yychecklim < YYNTOKENS ? yychecklim : YYNTOKENS;
          int yyx;

          for (yyx = yyxbegin; yyx < yyxend; ++yyx)
            if (yycheck[yyx + yyn] == yyx && yyx != YYTERROR
                && !yytable_value_is_error (yytable[yyx + yyn]))
              {
                if (yycount == YYERROR_VERBOSE_ARGS_MAXIMUM)
                  {
                    yycount = 1;
                    yysize = yysize0;
                    break;
                  }
                yyarg[yycount++] = yytname[yyx];
                {
                  YYSIZE_T yysize1 = yysize + yytnamerr (YY_NULLPTR, yytname[yyx]);
                  if (! (yysize <= yysize1
                         && yysize1 <= YYSTACK_ALLOC_MAXIMUM))
                    return 2;
                  yysize = yysize1;
                }
              }
        }
    }

  switch (yycount)
    {
# define YYCASE_(N, S)                      \
      case N:                               \
        yyformat = S;                       \
      break
      YYCASE_(0, YY_("syntax error"));
      YYCASE_(1, YY_("syntax error, unexpected %s"));
      YYCASE_(2, YY_("syntax error, unexpected %s, expecting %s"));
      YYCASE_(3, YY_("syntax error, unexpected %s, expecting %s or %s"));
      YYCASE_(4, YY_("syntax error, unexpected %s, expecting %s or %s or %s"));
      YYCASE_(5, YY_("syntax error, unexpected %s, expecting %s or %s or %s or %s"));
# undef YYCASE_
    }

  {
    YYSIZE_T yysize1 = yysize + yystrlen (yyformat);
    if (! (yysize <= yysize1 && yysize1 <= YYSTACK_ALLOC_MAXIMUM))
      return 2;
    yysize = yysize1;
  }

  if (*yymsg_alloc < yysize)
    {
      *yymsg_alloc = 2 * yysize;
      if (! (yysize <= *yymsg_alloc
             && *yymsg_alloc <= YYSTACK_ALLOC_MAXIMUM))
        *yymsg_alloc = YYSTACK_ALLOC_MAXIMUM;
      return 1;
    }

  /* Avoid sprintf, as that infringes on the user's name space.
     Don't have undefined behavior even if the translation
     produced a string with the wrong number of "%s"s.  */
  {
    char *yyp = *yymsg;
    int yyi = 0;
    while ((*yyp = *yyformat) != '\0')
      if (*yyp == '%' && yyformat[1] == 's' && yyi < yycount)
        {
          yyp += yytnamerr (yyp, yyarg[yyi++]);
          yyformat += 2;
        }
      else
        {
          yyp++;
          yyformat++;
        }
  }
  return 0;
}
#endif /* YYERROR_VERBOSE */

/*-----------------------------------------------.
| Release the memory associated to this symbol.  |
`-----------------------------------------------*/

static void
yydestruct (const char *yymsg, int yytype, YYSTYPE *yyvaluep, yyscan_t scanner, NXSL_Lexer *pLexer, NXSL_Compiler *pCompiler, NXSL_Program *pScript)
{
  YYUSE (yyvaluep);
  YYUSE (scanner);
  YYUSE (pLexer);
  YYUSE (pCompiler);
  YYUSE (pScript);
  if (!yymsg)
    yymsg = "Deleting";
  YY_SYMBOL_PRINT (yymsg, yytype, yyvaluep, yylocationp);

  YY_IGNORE_MAYBE_UNINITIALIZED_BEGIN
  switch (yytype)
    {
          case 38: /* T_COMPOUND_IDENTIFIER  */
#line 110 "parser.y" /* yacc.c:1257  */
      { free(((*yyvaluep).valStr)); }
#line 1702 "parser.tab.cpp" /* yacc.c:1257  */
        break;

    case 39: /* T_IDENTIFIER  */
#line 110 "parser.y" /* yacc.c:1257  */
      { free(((*yyvaluep).valStr)); }
#line 1708 "parser.tab.cpp" /* yacc.c:1257  */
        break;

    case 40: /* T_STRING  */
#line 110 "parser.y" /* yacc.c:1257  */
      { free(((*yyvaluep).valStr)); }
#line 1714 "parser.tab.cpp" /* yacc.c:1257  */
        break;

    case 107: /* AnyIdentifier  */
#line 110 "parser.y" /* yacc.c:1257  */
      { free(((*yyvaluep).valStr)); }
#line 1720 "parser.tab.cpp" /* yacc.c:1257  */
        break;

    case 164: /* SimpleStatementKeyword  */
#line 112 "parser.y" /* yacc.c:1257  */
      { delete ((*yyvaluep).pInstruction); }
#line 1726 "parser.tab.cpp" /* yacc.c:1257  */
        break;

    case 209: /* FunctionName  */
#line 110 "parser.y" /* yacc.c:1257  */
      { free(((*yyvaluep).valStr)); }
#line 1732 "parser.tab.cpp" /* yacc.c:1257  */
        break;

    case 211: /* Constant  */
#line 111 "parser.y" /* yacc.c:1257  */
      { delete ((*yyvaluep).pConstant); }
#line 1738 "parser.tab.cpp" /* yacc.c:1257  */
        break;


      default:
        break;
    }
  YY_IGNORE_MAYBE_UNINITIALIZED_END
}




/*----------.
| yyparse.  |
`----------*/

int
yyparse (yyscan_t scanner, NXSL_Lexer *pLexer, NXSL_Compiler *pCompiler, NXSL_Program *pScript)
{
/* The lookahead symbol.  */
int yychar;


/* The semantic value of the lookahead symbol.  */
/* Default value used for initialization, for pacifying older GCCs
   or non-GCC compilers.  */
YY_INITIAL_VALUE (static YYSTYPE yyval_default;)
YYSTYPE yylval YY_INITIAL_VALUE (= yyval_default);

    /* Number of syntax errors so far.  */
    int yynerrs;

    int yystate;
    /* Number of tokens to shift before error messages enabled.  */
    int yyerrstatus;

    /* The stacks and their tools:
       'yyss': related to states.
       'yyvs': related to semantic values.

       Refer to the stacks through separate pointers, to allow yyoverflow
       to reallocate them elsewhere.  */

    /* The state stack.  */
    yytype_int16 yyssa[YYINITDEPTH];
    yytype_int16 *yyss;
    yytype_int16 *yyssp;

    /* The semantic value stack.  */
    YYSTYPE yyvsa[YYINITDEPTH];
    YYSTYPE *yyvs;
    YYSTYPE *yyvsp;

    YYSIZE_T yystacksize;

  int yyn;
  int yyresult;
  /* Lookahead token as an internal (translated) token number.  */
  int yytoken = 0;
  /* The variables used to return semantic value and location from the
     action routines.  */
  YYSTYPE yyval;

#if YYERROR_VERBOSE
  /* Buffer for error messages, and its allocated size.  */
  char yymsgbuf[128];
  char *yymsg = yymsgbuf;
  YYSIZE_T yymsg_alloc = sizeof yymsgbuf;
#endif

#define YYPOPSTACK(N)   (yyvsp -= (N), yyssp -= (N))

  /* The number of symbols on the RHS of the reduced rule.
     Keep to zero when no symbol should be popped.  */
  int yylen = 0;

  yyssp = yyss = yyssa;
  yyvsp = yyvs = yyvsa;
  yystacksize = YYINITDEPTH;

  YYDPRINTF ((stderr, "Starting parse\n"));

  yystate = 0;
  yyerrstatus = 0;
  yynerrs = 0;
  yychar = YYEMPTY; /* Cause a token to be read.  */
  goto yysetstate;

/*------------------------------------------------------------.
| yynewstate -- Push a new state, which is found in yystate.  |
`------------------------------------------------------------*/
 yynewstate:
  /* In all cases, when you get here, the value and location stacks
     have just been pushed.  So pushing a state here evens the stacks.  */
  yyssp++;

 yysetstate:
  *yyssp = yystate;

  if (yyss + yystacksize - 1 <= yyssp)
    {
      /* Get the current used size of the three stacks, in elements.  */
      YYSIZE_T yysize = yyssp - yyss + 1;

#ifdef yyoverflow
      {
        /* Give user a chance to reallocate the stack.  Use copies of
           these so that the &'s don't force the real ones into
           memory.  */
        YYSTYPE *yyvs1 = yyvs;
        yytype_int16 *yyss1 = yyss;

        /* Each stack pointer address is followed by the size of the
           data in use in that stack, in bytes.  This used to be a
           conditional around just the two extra args, but that might
           be undefined if yyoverflow is a macro.  */
        yyoverflow (YY_("memory exhausted"),
                    &yyss1, yysize * sizeof (*yyssp),
                    &yyvs1, yysize * sizeof (*yyvsp),
                    &yystacksize);

        yyss = yyss1;
        yyvs = yyvs1;
      }
#else /* no yyoverflow */
# ifndef YYSTACK_RELOCATE
      goto yyexhaustedlab;
# else
      /* Extend the stack our own way.  */
      if (YYMAXDEPTH <= yystacksize)
        goto yyexhaustedlab;
      yystacksize *= 2;
      if (YYMAXDEPTH < yystacksize)
        yystacksize = YYMAXDEPTH;

      {
        yytype_int16 *yyss1 = yyss;
        union yyalloc *yyptr =
          (union yyalloc *) YYSTACK_ALLOC (YYSTACK_BYTES (yystacksize));
        if (! yyptr)
          goto yyexhaustedlab;
        YYSTACK_RELOCATE (yyss_alloc, yyss);
        YYSTACK_RELOCATE (yyvs_alloc, yyvs);
#  undef YYSTACK_RELOCATE
        if (yyss1 != yyssa)
          YYSTACK_FREE (yyss1);
      }
# endif
#endif /* no yyoverflow */

      yyssp = yyss + yysize - 1;
      yyvsp = yyvs + yysize - 1;

      YYDPRINTF ((stderr, "Stack size increased to %lu\n",
                  (unsigned long int) yystacksize));

      if (yyss + yystacksize - 1 <= yyssp)
        YYABORT;
    }

  YYDPRINTF ((stderr, "Entering state %d\n", yystate));

  if (yystate == YYFINAL)
    YYACCEPT;

  goto yybackup;

/*-----------.
| yybackup.  |
`-----------*/
yybackup:

  /* Do appropriate processing given the current state.  Read a
     lookahead token if we need one and don't already have one.  */

  /* First try to decide what to do without reference to lookahead token.  */
  yyn = yypact[yystate];
  if (yypact_value_is_default (yyn))
    goto yydefault;

  /* Not known => get a lookahead token if don't already have one.  */

  /* YYCHAR is either YYEMPTY or YYEOF or a valid lookahead symbol.  */
  if (yychar == YYEMPTY)
    {
      YYDPRINTF ((stderr, "Reading a token: "));
      yychar = yylex (&yylval, scanner);
    }

  if (yychar <= YYEOF)
    {
      yychar = yytoken = YYEOF;
      YYDPRINTF ((stderr, "Now at end of input.\n"));
    }
  else
    {
      yytoken = YYTRANSLATE (yychar);
      YY_SYMBOL_PRINT ("Next token is", yytoken, &yylval, &yylloc);
    }

  /* If the proper action on seeing token YYTOKEN is to reduce or to
     detect an error, take that action.  */
  yyn += yytoken;
  if (yyn < 0 || YYLAST < yyn || yycheck[yyn] != yytoken)
    goto yydefault;
  yyn = yytable[yyn];
  if (yyn <= 0)
    {
      if (yytable_value_is_error (yyn))
        goto yyerrlab;
      yyn = -yyn;
      goto yyreduce;
    }

  /* Count tokens shifted since error; after three, turn off error
     status.  */
  if (yyerrstatus)
    yyerrstatus--;

  /* Shift the lookahead token.  */
  YY_SYMBOL_PRINT ("Shifting", yytoken, &yylval, &yylloc);

  /* Discard the shifted token.  */
  yychar = YYEMPTY;

  yystate = yyn;
  YY_IGNORE_MAYBE_UNINITIALIZED_BEGIN
  *++yyvsp = yylval;
  YY_IGNORE_MAYBE_UNINITIALIZED_END

  goto yynewstate;


/*-----------------------------------------------------------.
| yydefault -- do the default action for the current state.  |
`-----------------------------------------------------------*/
yydefault:
  yyn = yydefact[yystate];
  if (yyn == 0)
    goto yyerrlab;
  goto yyreduce;


/*-----------------------------.
| yyreduce -- Do a reduction.  |
`-----------------------------*/
yyreduce:
  /* yyn is the number of a rule to reduce with.  */
  yylen = yyr2[yyn];

  /* If YYLEN is nonzero, implement the default value of the action:
     '$$ = $1'.

     Otherwise, the following line sets YYVAL to garbage.
     This behavior is undocumented and Bison
     users should not rely upon it.  Assigning to YYVAL
     unconditionally makes the parser a bit smaller, and it avoids a
     GCC warning that YYVAL may be used uninitialized.  */
  yyval = yyvsp[1-yylen];


  YY_REDUCE_PRINT (yyn);
  switch (yyn)
    {
        case 2:
#line 121 "parser.y" /* yacc.c:1646  */
    {
	char szErrorText[256];

	// Add implicit main() function
	if (!pScript->addFunction("$main", 0, szErrorText))
	{
		pCompiler->error(szErrorText);
		YYERROR;
	}
	
	// Implicit return
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_RET_NULL));
}
#line 2018 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 3:
#line 135 "parser.y" /* yacc.c:1646  */
    {
	char szErrorText[256];

	// Add implicit return
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_RETURN));

	// Add implicit main() function
	if (!pScript->addFunction("$main", 0, szErrorText))
	{
		pCompiler->error(szErrorText);
		YYERROR;
	}
}
#line 2036 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 4:
#line 149 "parser.y" /* yacc.c:1646  */
    {
	char szErrorText[256];

	// Add implicit return
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_RET_NULL));

	// Add implicit main() function
	if (!pScript->addFunction("$main", 0, szErrorText))
	{
		pCompiler->error(szErrorText);
		YYERROR;
	}
}
#line 2054 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 14:
#line 187 "parser.y" /* yacc.c:1646  */
    {
	if (!pScript->addConstant((yyvsp[-2].valStr), (yyvsp[0].pConstant)))
	{
		pCompiler->error("Constant already defined");
		delete_and_null((yyvsp[0].pConstant));
		YYERROR;
	}
	safe_free_and_null((yyvsp[-2].valStr));
	(yyvsp[0].pConstant) = NULL;
}
#line 2069 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 15:
#line 201 "parser.y" /* yacc.c:1646  */
    {
	pScript->addRequiredModule((yyvsp[-1].valStr), pLexer->getCurrLine());
	safe_free_and_null((yyvsp[-1].valStr));
}
#line 2078 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 18:
#line 214 "parser.y" /* yacc.c:1646  */
    {
		char szErrorText[256];

		pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_JMP, INVALID_ADDRESS));
		
		if (!pScript->addFunction((yyvsp[0].valStr), INVALID_ADDRESS, szErrorText))
		{
			pCompiler->error(szErrorText);
			YYERROR;
		}
		safe_free_and_null((yyvsp[0].valStr));
		pCompiler->setIdentifierOperation(OPCODE_BIND);
	}
#line 2096 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 19:
#line 228 "parser.y" /* yacc.c:1646  */
    {
		pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_RET_NULL));
		pScript->resolveLastJump(OPCODE_JMP);
	}
#line 2105 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 22:
#line 241 "parser.y" /* yacc.c:1646  */
    {
		pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), pCompiler->getIdentifierOperation(), (yyvsp[0].valStr)));
		(yyvsp[0].valStr) = NULL;
	}
#line 2114 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 24:
#line 247 "parser.y" /* yacc.c:1646  */
    {
		pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), pCompiler->getIdentifierOperation(), (yyvsp[0].valStr)));
		(yyvsp[0].valStr) = NULL;
	}
#line 2123 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 31:
#line 270 "parser.y" /* yacc.c:1646  */
    {
		pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_CATCH, INVALID_ADDRESS));
	}
#line 2131 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 32:
#line 274 "parser.y" /* yacc.c:1646  */
    {
		pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_CPOP));
		pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_JMP, INVALID_ADDRESS));
		pScript->resolveLastJump(OPCODE_CATCH);
	}
#line 2141 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 33:
#line 280 "parser.y" /* yacc.c:1646  */
    {
		pScript->resolveLastJump(OPCODE_JMP);
	}
#line 2149 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 34:
#line 287 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_POP, (short)1));
}
#line 2157 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 36:
#line 292 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_NOP));
}
#line 2165 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 38:
#line 300 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_SET, (yyvsp[-2].valStr)));
	(yyvsp[-2].valStr) = NULL;
}
#line 2174 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 39:
#line 304 "parser.y" /* yacc.c:1646  */
    { pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_PUSH_VARIABLE, strdup((yyvsp[-1].valStr)))); }
#line 2180 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 40:
#line 305 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_ADD));
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_SET, (yyvsp[-3].valStr)));
	(yyvsp[-3].valStr) = NULL;
}
#line 2190 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 41:
#line 310 "parser.y" /* yacc.c:1646  */
    { pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_PUSH_VARIABLE, strdup((yyvsp[-1].valStr)))); }
#line 2196 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 42:
#line 311 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_SUB));
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_SET, (yyvsp[-3].valStr)));
	(yyvsp[-3].valStr) = NULL;
}
#line 2206 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 43:
#line 316 "parser.y" /* yacc.c:1646  */
    { pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_PUSH_VARIABLE, strdup((yyvsp[-1].valStr)))); }
#line 2212 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 44:
#line 317 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_MUL));
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_SET, (yyvsp[-3].valStr)));
	(yyvsp[-3].valStr) = NULL;
}
#line 2222 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 45:
#line 322 "parser.y" /* yacc.c:1646  */
    { pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_PUSH_VARIABLE, strdup((yyvsp[-1].valStr)))); }
#line 2228 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 46:
#line 323 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_DIV));
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_SET, (yyvsp[-3].valStr)));
	(yyvsp[-3].valStr) = NULL;
}
#line 2238 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 47:
#line 328 "parser.y" /* yacc.c:1646  */
    { pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_PUSH_VARIABLE, strdup((yyvsp[-1].valStr)))); }
#line 2244 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 48:
#line 329 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_REM));
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_SET, (yyvsp[-3].valStr)));
	(yyvsp[-3].valStr) = NULL;
}
#line 2254 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 49:
#line 334 "parser.y" /* yacc.c:1646  */
    { pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_PUSH_VARIABLE, strdup((yyvsp[-1].valStr)))); }
#line 2260 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 50:
#line 335 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_CONCAT));
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_SET, (yyvsp[-3].valStr)));
	(yyvsp[-3].valStr) = NULL;
}
#line 2270 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 51:
#line 340 "parser.y" /* yacc.c:1646  */
    { pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_PUSH_VARIABLE, strdup((yyvsp[-1].valStr)))); }
#line 2276 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 52:
#line 341 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_BIT_AND));
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_SET, (yyvsp[-3].valStr)));
	(yyvsp[-3].valStr) = NULL;
}
#line 2286 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 53:
#line 346 "parser.y" /* yacc.c:1646  */
    { pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_PUSH_VARIABLE, strdup((yyvsp[-1].valStr)))); }
#line 2292 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 54:
#line 347 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_BIT_OR));
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_SET, (yyvsp[-3].valStr)));
	(yyvsp[-3].valStr) = NULL;
}
#line 2302 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 55:
#line 352 "parser.y" /* yacc.c:1646  */
    { pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_PUSH_VARIABLE, strdup((yyvsp[-1].valStr)))); }
#line 2308 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 56:
#line 353 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_BIT_XOR));
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_SET, (yyvsp[-3].valStr)));
	(yyvsp[-3].valStr) = NULL;
}
#line 2318 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 57:
#line 358 "parser.y" /* yacc.c:1646  */
    { pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_STORAGE_READ, (short)1)); }
#line 2324 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 58:
#line 359 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_ADD));
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_STORAGE_WRITE));
}
#line 2333 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 59:
#line 363 "parser.y" /* yacc.c:1646  */
    { pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_STORAGE_READ, (short)1)); }
#line 2339 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 60:
#line 364 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_SUB));
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_STORAGE_WRITE));
}
#line 2348 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 61:
#line 368 "parser.y" /* yacc.c:1646  */
    { pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_STORAGE_READ, (short)1)); }
#line 2354 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 62:
#line 369 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_MUL));
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_STORAGE_WRITE));
}
#line 2363 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 63:
#line 373 "parser.y" /* yacc.c:1646  */
    { pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_STORAGE_READ, (short)1)); }
#line 2369 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 64:
#line 374 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_DIV));
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_STORAGE_WRITE));
}
#line 2378 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 65:
#line 378 "parser.y" /* yacc.c:1646  */
    { pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_STORAGE_READ, (short)1)); }
#line 2384 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 66:
#line 379 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_REM));
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_STORAGE_WRITE));
}
#line 2393 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 67:
#line 383 "parser.y" /* yacc.c:1646  */
    { pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_STORAGE_READ, (short)1)); }
#line 2399 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 68:
#line 384 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_CONCAT));
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_STORAGE_WRITE));
}
#line 2408 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 69:
#line 388 "parser.y" /* yacc.c:1646  */
    { pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_STORAGE_READ, (short)1)); }
#line 2414 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 70:
#line 389 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_BIT_AND));
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_STORAGE_WRITE));
}
#line 2423 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 71:
#line 393 "parser.y" /* yacc.c:1646  */
    { pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_STORAGE_READ, (short)1)); }
#line 2429 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 72:
#line 394 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_BIT_OR));
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_STORAGE_WRITE));
}
#line 2438 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 73:
#line 398 "parser.y" /* yacc.c:1646  */
    { pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_STORAGE_READ, (short)1)); }
#line 2444 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 74:
#line 399 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_BIT_XOR));
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_STORAGE_WRITE));
}
#line 2453 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 75:
#line 404 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_SET_ELEMENT));
}
#line 2461 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 76:
#line 407 "parser.y" /* yacc.c:1646  */
    { pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_PEEK_ELEMENT)); }
#line 2467 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 77:
#line 408 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_ADD));
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_SET_ELEMENT));
}
#line 2476 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 78:
#line 412 "parser.y" /* yacc.c:1646  */
    { pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_PEEK_ELEMENT)); }
#line 2482 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 79:
#line 413 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_SUB));
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_SET_ELEMENT));
}
#line 2491 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 80:
#line 417 "parser.y" /* yacc.c:1646  */
    { pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_PEEK_ELEMENT)); }
#line 2497 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 81:
#line 418 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_MUL));
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_SET_ELEMENT));
}
#line 2506 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 82:
#line 422 "parser.y" /* yacc.c:1646  */
    { pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_PEEK_ELEMENT)); }
#line 2512 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 83:
#line 423 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_DIV));
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_SET_ELEMENT));
}
#line 2521 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 84:
#line 427 "parser.y" /* yacc.c:1646  */
    { pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_PEEK_ELEMENT)); }
#line 2527 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 85:
#line 428 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_CONCAT));
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_SET_ELEMENT));
}
#line 2536 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 86:
#line 432 "parser.y" /* yacc.c:1646  */
    { pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_PEEK_ELEMENT)); }
#line 2542 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 87:
#line 433 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_BIT_AND));
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_SET_ELEMENT));
}
#line 2551 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 88:
#line 437 "parser.y" /* yacc.c:1646  */
    { pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_PEEK_ELEMENT)); }
#line 2557 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 89:
#line 438 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_BIT_OR));
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_SET_ELEMENT));
}
#line 2566 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 90:
#line 442 "parser.y" /* yacc.c:1646  */
    { pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_PEEK_ELEMENT)); }
#line 2572 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 91:
#line 443 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_BIT_XOR));
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_SET_ELEMENT));
}
#line 2581 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 92:
#line 448 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_GET_ELEMENT));
}
#line 2589 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 93:
#line 452 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_STORAGE_WRITE));
}
#line 2597 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 94:
#line 456 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_STORAGE_READ));
}
#line 2605 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 95:
#line 460 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_SET_ATTRIBUTE, (yyvsp[-2].valStr)));
	(yyvsp[-2].valStr) = NULL;
}
#line 2614 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 96:
#line 465 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_GET_ATTRIBUTE, (yyvsp[0].valStr)));
	(yyvsp[0].valStr) = NULL;
}
#line 2623 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 97:
#line 470 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_CALL_METHOD, (yyvsp[-2].valStr), (yyvsp[-1].valInt32)));
	(yyvsp[-2].valStr) = NULL;
}
#line 2632 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 98:
#line 475 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_CALL_METHOD, (yyvsp[-1].valStr), 0));
	(yyvsp[-1].valStr) = NULL;
}
#line 2641 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 99:
#line 480 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_SAFE_GET_ATTR, (yyvsp[-2].valStr)));
	(yyvsp[-2].valStr) = NULL;
}
#line 2650 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 100:
#line 485 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_NEG));
}
#line 2658 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 101:
#line 489 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_NOT));
}
#line 2666 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 102:
#line 493 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_BIT_NOT));
}
#line 2674 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 103:
#line 497 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_INCP, (yyvsp[0].valStr)));
	(yyvsp[0].valStr) = NULL;
}
#line 2683 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 104:
#line 502 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_DECP, (yyvsp[0].valStr)));
	(yyvsp[0].valStr) = NULL;
}
#line 2692 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 105:
#line 507 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_INC, (yyvsp[-1].valStr)));
	(yyvsp[-1].valStr) = NULL;
}
#line 2701 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 106:
#line 512 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_DEC, (yyvsp[-1].valStr)));
	(yyvsp[-1].valStr) = NULL;
}
#line 2710 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 107:
#line 517 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_STORAGE_INCP));
}
#line 2718 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 108:
#line 521 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_STORAGE_DECP));
}
#line 2726 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 109:
#line 525 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_STORAGE_INC));
}
#line 2734 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 110:
#line 529 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_STORAGE_DEC));
}
#line 2742 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 111:
#line 533 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_INCP_ELEMENT));
}
#line 2750 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 112:
#line 537 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_DECP_ELEMENT));
}
#line 2758 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 113:
#line 541 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_INC_ELEMENT));
}
#line 2766 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 114:
#line 545 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_DEC_ELEMENT));
}
#line 2774 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 115:
#line 549 "parser.y" /* yacc.c:1646  */
    { 
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_ADD));
}
#line 2782 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 116:
#line 553 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_SUB));
}
#line 2790 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 117:
#line 557 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_MUL));
}
#line 2798 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 118:
#line 561 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_DIV));
}
#line 2806 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 119:
#line 565 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_REM));
}
#line 2814 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 120:
#line 569 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_LIKE));
}
#line 2822 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 121:
#line 573 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_ILIKE));
}
#line 2830 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 122:
#line 577 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_MATCH));
}
#line 2838 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 123:
#line 581 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_IMATCH));
}
#line 2846 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 124:
#line 585 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_EQ));
}
#line 2854 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 125:
#line 589 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_NE));
}
#line 2862 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 126:
#line 593 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_LT));
}
#line 2870 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 127:
#line 597 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_LE));
}
#line 2878 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 128:
#line 601 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_GT));
}
#line 2886 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 129:
#line 605 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_GE));
}
#line 2894 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 130:
#line 609 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_BIT_AND));
}
#line 2902 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 131:
#line 613 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_BIT_OR));
}
#line 2910 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 132:
#line 617 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_BIT_XOR));
}
#line 2918 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 133:
#line 620 "parser.y" /* yacc.c:1646  */
    { pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_JZ_PEEK, INVALID_ADDRESS)); }
#line 2924 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 134:
#line 621 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_AND));
	pScript->resolveLastJump(OPCODE_JZ_PEEK);
}
#line 2933 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 135:
#line 625 "parser.y" /* yacc.c:1646  */
    { pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_JNZ_PEEK, INVALID_ADDRESS)); }
#line 2939 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 136:
#line 626 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_OR));
	pScript->resolveLastJump(OPCODE_JNZ_PEEK);
}
#line 2948 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 137:
#line 631 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_LSHIFT));
}
#line 2956 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 138:
#line 635 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_RSHIFT));
}
#line 2964 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 139:
#line 639 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_CONCAT));
}
#line 2972 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 140:
#line 643 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_JZ, INVALID_ADDRESS));
}
#line 2980 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 141:
#line 647 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_JMP, INVALID_ADDRESS));
	pScript->resolveLastJump(OPCODE_JZ);
}
#line 2989 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 142:
#line 652 "parser.y" /* yacc.c:1646  */
    {
	pScript->resolveLastJump(OPCODE_JMP);
}
#line 2997 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 149:
#line 665 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_PUSH_VARIABLE, (yyvsp[0].valStr)));
	(yyvsp[0].valStr) = NULL;
}
#line 3006 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 150:
#line 670 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_PUSH_CONSTREF, (yyvsp[0].valStr)));
	(yyvsp[0].valStr) = NULL;
}
#line 3015 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 151:
#line 675 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_PUSH_CONSTANT, (yyvsp[0].pConstant)));
	(yyvsp[0].pConstant) = NULL;
}
#line 3024 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 152:
#line 683 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_CAST, (short)(yyvsp[-3].valInt32)));
}
#line 3032 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 153:
#line 690 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_NEW_ARRAY));
}
#line 3040 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 155:
#line 695 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_NEW_ARRAY));
}
#line 3048 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 156:
#line 702 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_ADD_TO_ARRAY));
}
#line 3056 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 158:
#line 707 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_ADD_TO_ARRAY));
}
#line 3064 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 159:
#line 714 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_NEW_HASHMAP));
}
#line 3072 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 161:
#line 719 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_NEW_HASHMAP));
}
#line 3080 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 164:
#line 731 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_HASHMAP_SET));
}
#line 3088 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 165:
#line 737 "parser.y" /* yacc.c:1646  */
    {
	(yyval.valInt32) = NXSL_DT_INT32;
}
#line 3096 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 166:
#line 741 "parser.y" /* yacc.c:1646  */
    {
	(yyval.valInt32) = NXSL_DT_INT64;
}
#line 3104 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 167:
#line 745 "parser.y" /* yacc.c:1646  */
    {
	(yyval.valInt32) = NXSL_DT_UINT32;
}
#line 3112 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 168:
#line 749 "parser.y" /* yacc.c:1646  */
    {
	(yyval.valInt32) = NXSL_DT_UINT64;
}
#line 3120 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 169:
#line 753 "parser.y" /* yacc.c:1646  */
    {
	(yyval.valInt32) = NXSL_DT_REAL;
}
#line 3128 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 170:
#line 757 "parser.y" /* yacc.c:1646  */
    {
	(yyval.valInt32) = NXSL_DT_STRING;
}
#line 3136 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 182:
#line 775 "parser.y" /* yacc.c:1646  */
    {
	if (pCompiler->canUseBreak())
	{
		pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_NOP));
		pCompiler->addBreakAddr(pScript->getCodeSize() - 1);
	}
	else
	{
		pCompiler->error("\"break\" statement can be used only within loops, \"switch\", and \"select\" statements");
		YYERROR;
	}
}
#line 3153 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 183:
#line 788 "parser.y" /* yacc.c:1646  */
    {
	UINT32 dwAddr = pCompiler->peekAddr();
	if (dwAddr != INVALID_ADDRESS)
	{
		pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_JMP, dwAddr));
	}
	else
	{
		pCompiler->error("\"continue\" statement can be used only within loops");
		YYERROR;
	}
}
#line 3170 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 184:
#line 804 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction((yyvsp[-1].pInstruction));
	(yyvsp[-1].pInstruction) = NULL;
}
#line 3179 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 185:
#line 809 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_PUSH_CONSTANT, new NXSL_Value));
	pScript->addInstruction((yyvsp[0].pInstruction));
	(yyvsp[0].pInstruction) = NULL;
}
#line 3189 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 186:
#line 818 "parser.y" /* yacc.c:1646  */
    {
	(yyval.pInstruction) = new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_ABORT);
}
#line 3197 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 187:
#line 822 "parser.y" /* yacc.c:1646  */
    {
	(yyval.pInstruction) = new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_EXIT);
}
#line 3205 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 188:
#line 826 "parser.y" /* yacc.c:1646  */
    {
	(yyval.pInstruction) = new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_RETURN);
}
#line 3213 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 189:
#line 830 "parser.y" /* yacc.c:1646  */
    {
	(yyval.pInstruction) = new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_PRINT);
}
#line 3221 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 190:
#line 837 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_PUSH_CONSTANT, new NXSL_Value(_T("\n"))));
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_CONCAT));
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_PRINT));
}
#line 3231 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 191:
#line 843 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_PUSH_CONSTANT, new NXSL_Value(_T("\n"))));
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_PRINT));
}
#line 3240 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 192:
#line 851 "parser.y" /* yacc.c:1646  */
    {
		pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_JZ, INVALID_ADDRESS));
	}
#line 3248 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 194:
#line 859 "parser.y" /* yacc.c:1646  */
    {
	pScript->resolveLastJump(OPCODE_JZ);
}
#line 3256 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 195:
#line 863 "parser.y" /* yacc.c:1646  */
    {
	pScript->resolveLastJump(OPCODE_JMP);
}
#line 3264 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 196:
#line 870 "parser.y" /* yacc.c:1646  */
    {
		pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_JMP, INVALID_ADDRESS));
		pScript->resolveLastJump(OPCODE_JZ);
	}
#line 3273 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 198:
#line 879 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_POP, (short)1));
	pCompiler->pushAddr(pScript->getCodeSize());
}
#line 3282 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 199:
#line 884 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_JZ, INVALID_ADDRESS));
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_JMP, INVALID_ADDRESS));
	pCompiler->pushAddr(pScript->getCodeSize());
}
#line 3292 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 200:
#line 890 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_POP, (short)1));
	UINT32 addrPart3 = pCompiler->popAddr();
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_JMP, pCompiler->popAddr()));
	pCompiler->pushAddr(addrPart3);
	pCompiler->newBreakLevel();
	pScript->resolveLastJump(OPCODE_JMP);
}
#line 3305 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 201:
#line 899 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_JMP, pCompiler->popAddr()));
	pScript->resolveLastJump(OPCODE_JZ);
	pCompiler->closeBreakLevel(pScript);
}
#line 3315 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 203:
#line 912 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_PUSH_CONSTANT, new NXSL_Value((yyvsp[-1].valStr))));
	safe_free_and_null((yyvsp[-1].valStr));
}
#line 3324 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 204:
#line 918 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_PUSH_CONSTANT, new NXSL_Value((yyvsp[-1].valStr))));
	safe_free_and_null((yyvsp[-1].valStr));
}
#line 3333 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 205:
#line 926 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_FOREACH));
	pCompiler->pushAddr(pScript->getCodeSize());
	pCompiler->newBreakLevel();
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_NEXT));
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_JZ, INVALID_ADDRESS));
}
#line 3345 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 206:
#line 934 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_JMP, pCompiler->popAddr()));
	pScript->resolveLastJump(OPCODE_JZ);
	pCompiler->closeBreakLevel(pScript);
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_POP, (short)1));
}
#line 3356 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 207:
#line 944 "parser.y" /* yacc.c:1646  */
    {
	pCompiler->pushAddr(pScript->getCodeSize());
	pCompiler->newBreakLevel();
}
#line 3365 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 208:
#line 949 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_JZ, INVALID_ADDRESS));
}
#line 3373 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 209:
#line 953 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_JMP, pCompiler->popAddr()));
	pScript->resolveLastJump(OPCODE_JZ);
	pCompiler->closeBreakLevel(pScript);
}
#line 3383 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 210:
#line 962 "parser.y" /* yacc.c:1646  */
    {
	pCompiler->pushAddr(pScript->getCodeSize());
	pCompiler->newBreakLevel();
}
#line 3392 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 211:
#line 967 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(),
				OPCODE_JNZ, pCompiler->popAddr()));
	pCompiler->closeBreakLevel(pScript);
}
#line 3402 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 212:
#line 976 "parser.y" /* yacc.c:1646  */
    { 
	pCompiler->newBreakLevel();
}
#line 3410 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 213:
#line 980 "parser.y" /* yacc.c:1646  */
    {
	pCompiler->closeBreakLevel(pScript);
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_POP, (short)1));
}
#line 3419 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 214:
#line 988 "parser.y" /* yacc.c:1646  */
    { 
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_JMP, pScript->getCodeSize() + 3));
	pScript->resolveLastJump(OPCODE_JZ);
}
#line 3428 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 216:
#line 994 "parser.y" /* yacc.c:1646  */
    {
	pScript->resolveLastJump(OPCODE_JZ);
}
#line 3436 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 217:
#line 1001 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_CASE, (yyvsp[0].pConstant)));
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_JZ, INVALID_ADDRESS));
	(yyvsp[0].pConstant) = NULL;
}
#line 3446 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 219:
#line 1008 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_CASE_CONST, (yyvsp[0].valStr)));
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_JZ, INVALID_ADDRESS));
	(yyvsp[0].valStr) = NULL;
}
#line 3456 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 223:
#line 1023 "parser.y" /* yacc.c:1646  */
    { 
	pCompiler->newBreakLevel();
	pCompiler->newSelectLevel();
}
#line 3465 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 224:
#line 1028 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_SELECT, (yyvsp[-4].valStr), (yyvsp[-1].valInt32)));
	pCompiler->closeBreakLevel(pScript);

	UINT32 addr = pCompiler->popSelectJumpAddr();
	if (addr != INVALID_ADDRESS)
	{
		pScript->createJumpAt(addr, pScript->getCodeSize());
	}
	pCompiler->closeSelectLevel();
}
#line 3481 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 226:
#line 1044 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_PUSH_CONSTANT, new NXSL_Value()));
}
#line 3489 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 227:
#line 1051 "parser.y" /* yacc.c:1646  */
    { 
	(yyval.valInt32) = (yyvsp[0].valInt32) + 1; 
}
#line 3497 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 228:
#line 1055 "parser.y" /* yacc.c:1646  */
    {
	(yyval.valInt32) = 1;
}
#line 3505 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 229:
#line 1062 "parser.y" /* yacc.c:1646  */
    {
}
#line 3512 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 230:
#line 1065 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_PUSHCP, (short)2));
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_JMP, INVALID_ADDRESS));
	UINT32 addr = pCompiler->popSelectJumpAddr();
	if (addr != INVALID_ADDRESS)
	{
		pScript->createJumpAt(addr, pScript->getCodeSize());
	}
}
#line 3526 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 231:
#line 1075 "parser.y" /* yacc.c:1646  */
    {
	pCompiler->pushSelectJumpAddr(pScript->getCodeSize());
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_NOP));
	pScript->resolveLastJump(OPCODE_JMP);
}
#line 3536 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 232:
#line 1083 "parser.y" /* yacc.c:1646  */
    { pCompiler->setIdentifierOperation(OPCODE_ARRAY); }
#line 3542 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 234:
#line 1084 "parser.y" /* yacc.c:1646  */
    { pCompiler->setIdentifierOperation(OPCODE_GLOBAL_ARRAY); }
#line 3548 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 239:
#line 1098 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_GLOBAL, (yyvsp[0].valStr), 0));
	(yyvsp[0].valStr) = NULL;
}
#line 3557 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 240:
#line 1103 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_GLOBAL, (yyvsp[-2].valStr), 1));
	(yyvsp[-2].valStr) = NULL;
}
#line 3566 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 241:
#line 1111 "parser.y" /* yacc.c:1646  */
    {
	char fname[256];
	snprintf(fname, 256, "__new@%s", (yyvsp[0].valStr)); 
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_CALL_EXTERNAL, strdup(fname), 0));
	free((yyvsp[0].valStr));
	(yyvsp[0].valStr) = NULL;
}
#line 3578 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 242:
#line 1119 "parser.y" /* yacc.c:1646  */
    {
	char fname[256];
	snprintf(fname, 256, "__new@%s", (yyvsp[-3].valStr)); 
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_CALL_EXTERNAL, strdup(fname), (yyvsp[-1].valInt32)));
	free((yyvsp[-3].valStr));
	(yyvsp[-3].valStr) = NULL;
}
#line 3590 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 243:
#line 1127 "parser.y" /* yacc.c:1646  */
    {
	char fname[256];
	snprintf(fname, 256, "__new@%s", (yyvsp[-2].valStr)); 
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_CALL_EXTERNAL, strdup(fname), 0));
	free((yyvsp[-2].valStr));
	(yyvsp[-2].valStr) = NULL;
}
#line 3602 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 244:
#line 1138 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_CALL_EXTERNAL, (yyvsp[-2].valStr), (yyvsp[-1].valInt32)));
	(yyvsp[-2].valStr) = NULL;
}
#line 3611 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 245:
#line 1143 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_CALL_EXTERNAL, (yyvsp[-1].valStr), 0));
	(yyvsp[-1].valStr) = NULL;
}
#line 3620 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 246:
#line 1150 "parser.y" /* yacc.c:1646  */
    { (yyval.valInt32) = (yyvsp[0].valInt32) + 1; }
#line 3626 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 247:
#line 1151 "parser.y" /* yacc.c:1646  */
    { (yyval.valInt32) = 1; }
#line 3632 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 249:
#line 1157 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_NAME, (yyvsp[-2].valStr)));
	(yyvsp[-2].valStr) = NULL;
}
#line 3641 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 250:
#line 1165 "parser.y" /* yacc.c:1646  */
    {
	(yyval.valStr) = (yyvsp[-1].valStr);
}
#line 3649 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 251:
#line 1172 "parser.y" /* yacc.c:1646  */
    {
	pScript->addInstruction(new NXSL_Instruction(pLexer->getCurrLine(), OPCODE_PUSH_CONSTANT, new NXSL_Value((yyvsp[0].valStr))));
	safe_free_and_null((yyvsp[0].valStr));
}
#line 3658 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 253:
#line 1181 "parser.y" /* yacc.c:1646  */
    {
	(yyval.pConstant) = new NXSL_Value((yyvsp[0].valStr));
	safe_free_and_null((yyvsp[0].valStr));
}
#line 3667 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 254:
#line 1186 "parser.y" /* yacc.c:1646  */
    {
	(yyval.pConstant) = new NXSL_Value((yyvsp[0].valInt32));
}
#line 3675 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 255:
#line 1190 "parser.y" /* yacc.c:1646  */
    {
	(yyval.pConstant) = new NXSL_Value((yyvsp[0].valUInt32));
}
#line 3683 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 256:
#line 1194 "parser.y" /* yacc.c:1646  */
    {
	(yyval.pConstant) = new NXSL_Value((yyvsp[0].valInt64));
}
#line 3691 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 257:
#line 1198 "parser.y" /* yacc.c:1646  */
    {
	(yyval.pConstant) = new NXSL_Value((yyvsp[0].valUInt64));
}
#line 3699 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 258:
#line 1202 "parser.y" /* yacc.c:1646  */
    {
	(yyval.pConstant) = new NXSL_Value((yyvsp[0].valReal));
}
#line 3707 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 259:
#line 1206 "parser.y" /* yacc.c:1646  */
    {
	(yyval.pConstant) = new NXSL_Value((LONG)1);
}
#line 3715 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 260:
#line 1210 "parser.y" /* yacc.c:1646  */
    {
	(yyval.pConstant) = new NXSL_Value((LONG)0);
}
#line 3723 "parser.tab.cpp" /* yacc.c:1646  */
    break;

  case 261:
#line 1214 "parser.y" /* yacc.c:1646  */
    {
	(yyval.pConstant) = new NXSL_Value;
}
#line 3731 "parser.tab.cpp" /* yacc.c:1646  */
    break;


#line 3735 "parser.tab.cpp" /* yacc.c:1646  */
      default: break;
    }
  /* User semantic actions sometimes alter yychar, and that requires
     that yytoken be updated with the new translation.  We take the
     approach of translating immediately before every use of yytoken.
     One alternative is translating here after every semantic action,
     but that translation would be missed if the semantic action invokes
     YYABORT, YYACCEPT, or YYERROR immediately after altering yychar or
     if it invokes YYBACKUP.  In the case of YYABORT or YYACCEPT, an
     incorrect destructor might then be invoked immediately.  In the
     case of YYERROR or YYBACKUP, subsequent parser actions might lead
     to an incorrect destructor call or verbose syntax error message
     before the lookahead is translated.  */
  YY_SYMBOL_PRINT ("-> $$ =", yyr1[yyn], &yyval, &yyloc);

  YYPOPSTACK (yylen);
  yylen = 0;
  YY_STACK_PRINT (yyss, yyssp);

  *++yyvsp = yyval;

  /* Now 'shift' the result of the reduction.  Determine what state
     that goes to, based on the state we popped back to and the rule
     number reduced by.  */

  yyn = yyr1[yyn];

  yystate = yypgoto[yyn - YYNTOKENS] + *yyssp;
  if (0 <= yystate && yystate <= YYLAST && yycheck[yystate] == *yyssp)
    yystate = yytable[yystate];
  else
    yystate = yydefgoto[yyn - YYNTOKENS];

  goto yynewstate;


/*--------------------------------------.
| yyerrlab -- here on detecting error.  |
`--------------------------------------*/
yyerrlab:
  /* Make sure we have latest lookahead translation.  See comments at
     user semantic actions for why this is necessary.  */
  yytoken = yychar == YYEMPTY ? YYEMPTY : YYTRANSLATE (yychar);

  /* If not already recovering from an error, report this error.  */
  if (!yyerrstatus)
    {
      ++yynerrs;
#if ! YYERROR_VERBOSE
      yyerror (scanner, pLexer, pCompiler, pScript, YY_("syntax error"));
#else
# define YYSYNTAX_ERROR yysyntax_error (&yymsg_alloc, &yymsg, \
                                        yyssp, yytoken)
      {
        char const *yymsgp = YY_("syntax error");
        int yysyntax_error_status;
        yysyntax_error_status = YYSYNTAX_ERROR;
        if (yysyntax_error_status == 0)
          yymsgp = yymsg;
        else if (yysyntax_error_status == 1)
          {
            if (yymsg != yymsgbuf)
              YYSTACK_FREE (yymsg);
            yymsg = (char *) YYSTACK_ALLOC (yymsg_alloc);
            if (!yymsg)
              {
                yymsg = yymsgbuf;
                yymsg_alloc = sizeof yymsgbuf;
                yysyntax_error_status = 2;
              }
            else
              {
                yysyntax_error_status = YYSYNTAX_ERROR;
                yymsgp = yymsg;
              }
          }
        yyerror (scanner, pLexer, pCompiler, pScript, yymsgp);
        if (yysyntax_error_status == 2)
          goto yyexhaustedlab;
      }
# undef YYSYNTAX_ERROR
#endif
    }



  if (yyerrstatus == 3)
    {
      /* If just tried and failed to reuse lookahead token after an
         error, discard it.  */

      if (yychar <= YYEOF)
        {
          /* Return failure if at end of input.  */
          if (yychar == YYEOF)
            YYABORT;
        }
      else
        {
          yydestruct ("Error: discarding",
                      yytoken, &yylval, scanner, pLexer, pCompiler, pScript);
          yychar = YYEMPTY;
        }
    }

  /* Else will try to reuse lookahead token after shifting the error
     token.  */
  goto yyerrlab1;


/*---------------------------------------------------.
| yyerrorlab -- error raised explicitly by YYERROR.  |
`---------------------------------------------------*/
yyerrorlab:

  /* Pacify compilers like GCC when the user code never invokes
     YYERROR and the label yyerrorlab therefore never appears in user
     code.  */
  if (/*CONSTCOND*/ 0)
     goto yyerrorlab;

  /* Do not reclaim the symbols of the rule whose action triggered
     this YYERROR.  */
  YYPOPSTACK (yylen);
  yylen = 0;
  YY_STACK_PRINT (yyss, yyssp);
  yystate = *yyssp;
  goto yyerrlab1;


/*-------------------------------------------------------------.
| yyerrlab1 -- common code for both syntax error and YYERROR.  |
`-------------------------------------------------------------*/
yyerrlab1:
  yyerrstatus = 3;      /* Each real token shifted decrements this.  */

  for (;;)
    {
      yyn = yypact[yystate];
      if (!yypact_value_is_default (yyn))
        {
          yyn += YYTERROR;
          if (0 <= yyn && yyn <= YYLAST && yycheck[yyn] == YYTERROR)
            {
              yyn = yytable[yyn];
              if (0 < yyn)
                break;
            }
        }

      /* Pop the current state because it cannot handle the error token.  */
      if (yyssp == yyss)
        YYABORT;


      yydestruct ("Error: popping",
                  yystos[yystate], yyvsp, scanner, pLexer, pCompiler, pScript);
      YYPOPSTACK (1);
      yystate = *yyssp;
      YY_STACK_PRINT (yyss, yyssp);
    }

  YY_IGNORE_MAYBE_UNINITIALIZED_BEGIN
  *++yyvsp = yylval;
  YY_IGNORE_MAYBE_UNINITIALIZED_END


  /* Shift the error token.  */
  YY_SYMBOL_PRINT ("Shifting", yystos[yyn], yyvsp, yylsp);

  yystate = yyn;
  goto yynewstate;


/*-------------------------------------.
| yyacceptlab -- YYACCEPT comes here.  |
`-------------------------------------*/
yyacceptlab:
  yyresult = 0;
  goto yyreturn;

/*-----------------------------------.
| yyabortlab -- YYABORT comes here.  |
`-----------------------------------*/
yyabortlab:
  yyresult = 1;
  goto yyreturn;

#if !defined yyoverflow || YYERROR_VERBOSE
/*-------------------------------------------------.
| yyexhaustedlab -- memory exhaustion comes here.  |
`-------------------------------------------------*/
yyexhaustedlab:
  yyerror (scanner, pLexer, pCompiler, pScript, YY_("memory exhausted"));
  yyresult = 2;
  /* Fall through.  */
#endif

yyreturn:
  if (yychar != YYEMPTY)
    {
      /* Make sure we have latest lookahead translation.  See comments at
         user semantic actions for why this is necessary.  */
      yytoken = YYTRANSLATE (yychar);
      yydestruct ("Cleanup: discarding lookahead",
                  yytoken, &yylval, scanner, pLexer, pCompiler, pScript);
    }
  /* Do not reclaim the symbols of the rule whose action triggered
     this YYABORT or YYACCEPT.  */
  YYPOPSTACK (yylen);
  YY_STACK_PRINT (yyss, yyssp);
  while (yyssp != yyss)
    {
      yydestruct ("Cleanup: popping",
                  yystos[*yyssp], yyvsp, scanner, pLexer, pCompiler, pScript);
      YYPOPSTACK (1);
    }
#ifndef yyoverflow
  if (yyss != yyssa)
    YYSTACK_FREE (yyss);
#endif
#if YYERROR_VERBOSE
  if (yymsg != yymsgbuf)
    YYSTACK_FREE (yymsg);
#endif
  return yyresult;
}
