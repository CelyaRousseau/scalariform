package scalariform.lexer

import scalariform.lexer.Tokens._
import scalariform.utils.Utils

class HiddenTokens(val tokens: List[HiddenToken]) extends Iterable[HiddenToken] {

  def removeInitialWhitespace = new HiddenTokens(tokens.dropWhile(_.isInstanceOf[Whitespace]))

  def iterator: Iterator[HiddenToken] = tokens.iterator

  val comments: List[Comment] = tokens collect { case comment: Comment ⇒ comment }

  val scalaDocComments: List[ScalaDocComment] = tokens collect { case comment @ ScalaDocComment(_) ⇒ comment }

  val whitespaces: List[Whitespace] = tokens collect { case whitespace @ Whitespace(_) ⇒ whitespace }

  def firstTokenOption = tokens.headOption
  
  def lastTokenOption = tokens.lastOption

  def containsNewline = text contains '\n'

  def containsComment = comments.nonEmpty

  lazy val text = tokens.map(_.token.text).mkString

  lazy val rawText = tokens.map(_.token.rawText).mkString

  lazy val newlines: Option[Token] =
    if (containsNewline) {
      require(tokens.nonEmpty)
      val tokenType = if (text matches HiddenTokens.BLANK_LINE_PATTERN) NEWLINES else NEWLINE
      val first = tokens.head.token
      val last = tokens.last.token
      val token = Token(tokenType, text, first.offset, rawText)
      Some(token)
    } else
      None

  def rawTokens = tokens.map(_.token)

}

object HiddenTokens {

  val BLANK_LINE_PATTERN = """(?s).*\n\s*\n.*"""

}