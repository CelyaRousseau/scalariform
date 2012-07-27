package scalariform.formatter

import scalariform.formatter.preferences._

class ParenAndBracketSpacingTest extends AbstractExpressionFormatterTest {

  {
    implicit val formattingPreferences = FormattingPreferences.setPreference(SpaceInsideParentheses, true)
    "()" ==> "()"
    "(a: Int) => 3" ==> "( a: Int ) => 3"
    "(3)" ==> "( 3 )"
    "(3, 4)" ==> "( 3, 4 )"
    "for (n <- 1 to 10) yield foo(n, n)" ==> "for ( n <- 1 to 10 ) yield foo( n, n )"
  }

  {
    implicit val formattingPreferences = FormattingPreferences.setPreference(SpaceInsideBrackets, true)
    "x: List[String]" ==> "x: List[ String ]"
    "foo[Bar](baz)" ==> "foo[ Bar ](baz)"
    "{ class A[B] { private[this] val bob } }" ==> "{ class A[ B ] { private[ this ] val bob } }"
    "super[X].y" ==> "super[ X ].y"
    "foo[Bar](baz)[Biz]" ==> "foo[ Bar ](baz)[ Biz ]"
    "foo[Bar][Baz][Buz]" ==> "foo[ Bar ][ Baz ][ Buz ]"
  }

}