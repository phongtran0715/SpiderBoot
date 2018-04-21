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

#ifndef YY_MP_PARSER_TAB_HPP_INCLUDED
# define YY_MP_PARSER_TAB_HPP_INCLUDED
/* Debug traces.  */
#ifndef YYDEBUG
# define YYDEBUG 1
#endif
#if YYDEBUG
extern int mpdebug;
#endif

/* Token type.  */
#ifndef YYTOKENTYPE
# define YYTOKENTYPE
  enum yytokentype
  {
    ENTERPRISE_SYM = 258,
    TRAP_TYPE_SYM = 259,
    VARIABLES_SYM = 260,
    EXPLICIT_SYM = 261,
    IMPLICIT_SYM = 262,
    IMPLIED_SYM = 263,
    RIGHT_BRACE_SYM = 264,
    LEFT_BRACE_SYM = 265,
    RIGHT_BRACKET_SYM = 266,
    LEFT_BRACKET_SYM = 267,
    DEFINITIONS_SYM = 268,
    ASSIGNMENT_SYM = 269,
    BEGIN_SYM = 270,
    END_SYM = 271,
    FROM_SYM = 272,
    IMPORTS_SYM = 273,
    EXPORTS_SYM = 274,
    COMMA_SYM = 275,
    SEMI_COLON_SYM = 276,
    DOT_SYM = 277,
    DESCRIPTION_SYM = 278,
    ORGANIZATION_SYM = 279,
    CONTACT_SYM = 280,
    UPDATE_SYM = 281,
    MODULE_IDENTITY_SYM = 282,
    MODULE_COMPLIANCE_SYM = 283,
    OBJECT_IDENTIFIER_SYM = 284,
    OBJECT_TYPE_SYM = 285,
    OBJECT_GROUP_SYM = 286,
    OBJECT_IDENTITY_SYM = 287,
    OBJECTS_SYM = 288,
    MANDATORY_GROUPS_SYM = 289,
    GROUP_SYM = 290,
    AGENT_CAPABILITIES_SYM = 291,
    KEYWORD_SYM = 292,
    KEYWORD_VALUE_SYM = 293,
    KEYWORD_BIND_SYM = 294,
    TOKEN_SYM = 295,
    INTEGER_SYM = 296,
    INTEGER32_SYM = 297,
    UNSIGNED32_SYM = 298,
    GAUGE32_SYM = 299,
    COUNTER_SYM = 300,
    COUNTER32_SYM = 301,
    COUNTER64_SYM = 302,
    BITS_SYM = 303,
    STRING_SYM = 304,
    OCTET_SYM = 305,
    SEQUENCE_SYM = 306,
    OF_SYM = 307,
    TIMETICKS_SYM = 308,
    IP_ADDRESS_SYM = 309,
    NETWORK_ADDRESS_SYM = 310,
    OPAQUE_SYM = 311,
    REVISION_SYM = 312,
    TEXTUAL_CONVENTION_SYM = 313,
    ACCESS_SYM = 314,
    MAX_ACCESS_SYM = 315,
    MIN_ACCESS_SYM = 316,
    SYNTAX_SYM = 317,
    STATUS_SYM = 318,
    INDEX_SYM = 319,
    REFERENCE_SYM = 320,
    DEFVAL_SYM = 321,
    LEFT_PAREN_SYM = 322,
    RIGHT_PAREN_SYM = 323,
    NOTIFICATIONS_SYM = 324,
    NOTIFICATION_GROUP_SYM = 325,
    NOTIFICATION_TYPE_SYM = 326,
    SIZE_SYM = 327,
    BAR_SYM = 328,
    VARIATION_SYM = 329,
    WRITE_SYNTAX_SYM = 330,
    SUPPORTS_SYM = 331,
    INCLUDES_SYM = 332,
    CREATION_REQUIRES_SYM = 333,
    PRODUCT_RELEASE_SYM = 334,
    CHOICE_SYM = 335,
    UNITS_SYM = 336,
    AUGMENTS_SYM = 337,
    OBJECT_SYM = 338,
    TAGS_SYM = 339,
    AUTOMATIC_SYM = 340,
    MIN_SYM = 341,
    MAX_SYM = 342,
    MODULE_SYM = 343,
    MACRO_SYM = 344,
    UCASEFIRST_IDENT_SYM = 345,
    LCASEFIRST_IDENT_SYM = 346,
    BSTRING_SYM = 347,
    HSTRING_SYM = 348,
    CSTRING_SYM = 349,
    DISPLAY_HINT_SYM = 350,
    NUMBER_SYM = 351
  };
#endif

/* Value type.  */
#if ! defined YYSTYPE && ! defined YYSTYPE_IS_DECLARED

union YYSTYPE
{
#line 114 "parser.y" /* yacc.c:1909  */

   int nInteger;
   char *pszString;
   MP_NUMERIC_VALUE number;
   Array *pList;
   ObjectArray<MP_SUBID> *pOID;
   ObjectArray<MP_IMPORT_MODULE> *pImportList;
   MP_IMPORT_MODULE *pImports;
   MP_OBJECT *pObject;
   MP_SUBID *pSubId;
   MP_SYNTAX *pSyntax;

#line 164 "parser.tab.hpp" /* yacc.c:1909  */
};

typedef union YYSTYPE YYSTYPE;
# define YYSTYPE_IS_TRIVIAL 1
# define YYSTYPE_IS_DECLARED 1
#endif


extern YYSTYPE mplval;

int mpparse (void);

#endif /* !YY_MP_PARSER_TAB_HPP_INCLUDED  */
