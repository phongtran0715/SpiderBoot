/* A Bison parser, made by GNU Bison 3.0.4.  */

/* Bison interface for Yacc-like parsers in C

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
#line 28 "parser.y" /* yacc.c:1909  */

	INT32 valInt32;
	UINT32 valUInt32;
	INT64 valInt64;
	UINT64 valUInt64;
	char *valStr;
	double valReal;
	NXSL_Value *pConstant;
	NXSL_Instruction *pInstruction;

#line 138 "parser.tab.hpp" /* yacc.c:1909  */
};

typedef union YYSTYPE YYSTYPE;
# define YYSTYPE_IS_TRIVIAL 1
# define YYSTYPE_IS_DECLARED 1
#endif



int yyparse (yyscan_t scanner, NXSL_Lexer *pLexer, NXSL_Compiler *pCompiler, NXSL_Program *pScript);

#endif /* !YY_YY_PARSER_TAB_HPP_INCLUDED  */
